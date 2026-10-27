package com.byd.card.base;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public class BaseContract {

    public interface IPresenter<T extends IView> {
        void attachView(T view);

        void detachView();
    }

    public interface IView {
        Context getContext();
    }
}
