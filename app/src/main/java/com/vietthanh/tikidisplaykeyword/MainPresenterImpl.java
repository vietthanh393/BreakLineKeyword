package com.vietthanh.tikidisplaykeyword;

import android.content.Context;
import android.text.TextUtils;

import com.vietthanh.tikidisplaykeyword.common.api.TikiAPI;
import com.vietthanh.tikidisplaykeyword.common.util.ColorUtil;
import com.vietthanh.tikidisplaykeyword.model.KeywordModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainPresenterImpl implements MainPresenter {
    private MainView mMainView;
    private CompositeDisposable mCompositeDisposable;
    private Context mContext;

    public MainPresenterImpl(final MainView mainView, final Context context) {
        mMainView = mainView;
        mContext = context;
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void getKeyword() {
        TikiAPI tikiAPI = new TikiAPI();
        mCompositeDisposable.add((tikiAPI.getApi(mContext))
                .getKeywords()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(data -> {
                    if (data != null && data.length > 0) {
                        List<KeywordModel> listData = new ArrayList<>();

                        for (String key : data) {
                            KeywordModel model = new KeywordModel();
                            model.setColor(ColorUtil.generateColor());
                            model.setKeyword(breakLine(key.trim()));
                            listData.add(model);
                        }

                        mMainView.onSuccess(listData);
                    } else {
                        mMainView.onFailure();
                    }
                }, error -> {
                    mMainView.onFailure();
                }));

    }

    private String breakLine(String keyword) {
        if (TextUtils.isEmpty(keyword)) {
            return "";
        }
        String BREAKLINE = "\n";
        String SPACE = " ";

        String[] splitString = keyword.split(SPACE);
        if (splitString.length == 1) {
            return keyword;
        }

        StringBuilder result = new StringBuilder();

        int totalCharacter = keyword.replaceAll(SPACE, "").length();
        int min = totalCharacter;
        StringBuilder firstLine = new StringBuilder();

        for (int i = 0; i < splitString.length - 1; i++) {
            firstLine.append(splitString[i]);
            int firstLineLength = firstLine.length();
            int secondLineLength = totalCharacter - firstLineLength;
            int diff = Math.abs(firstLineLength - secondLineLength);
            if (diff < min) {
                min = diff;
                int index = firstLineLength + i;

                result = new StringBuilder();
                result.append(keyword.substring(0, index));
                result.append(BREAKLINE);
                result.append(keyword.substring(index));
            }
        }
        return result.toString();
    }
}
