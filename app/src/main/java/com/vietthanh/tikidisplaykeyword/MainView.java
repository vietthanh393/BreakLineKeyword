package com.vietthanh.tikidisplaykeyword;

import com.vietthanh.tikidisplaykeyword.model.KeywordModel;

import java.util.List;

public interface MainView {
    void onSuccess(final List<KeywordModel> data);

    void onFailure();

    void handleProgress(final boolean isShow);
}
