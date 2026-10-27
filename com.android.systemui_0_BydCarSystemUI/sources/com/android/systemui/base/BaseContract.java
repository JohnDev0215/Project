package com.android.systemui.base;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class BaseContract {

    public interface BasePresenter<T extends BaseView> {
        void addListener();

        void attachView(T view);

        void detachView();

        void initData();

        void removeListener();
    }

    public interface BaseView {
        Context getContext();
    }
}
