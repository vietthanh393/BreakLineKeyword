package com.vietthanh.tikidisplaykeyword;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.vietthanh.tikidisplaykeyword.adapter.KeywordAdapter;
import com.vietthanh.tikidisplaykeyword.model.KeywordModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends Activity implements MainView {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private MainPresenter mMainPresenter;
    private KProgressHUD mProgressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mMainPresenter = new MainPresenterImpl(this, this);
        init();
        handleProgress(true);
        mMainPresenter.getKeyword();
    }

    private void init() {
        mProgressDialog = KProgressHUD.create(this)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE);
        mProgressDialog.setCancellable(false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onSuccess(final List<KeywordModel> data) {
        KeywordAdapter keywordAdapter = new KeywordAdapter(data, this);
        mRecyclerView.setAdapter(keywordAdapter);
        handleProgress(false);
    }

    @Override
    public void onFailure() {
        handleProgress(false);
    }

    @Override
    public void handleProgress(final boolean isShow) {
        if (mProgressDialog == null) {
            return;
        }
        if (isShow) {
            mProgressDialog.show();
        } else {
            mProgressDialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
