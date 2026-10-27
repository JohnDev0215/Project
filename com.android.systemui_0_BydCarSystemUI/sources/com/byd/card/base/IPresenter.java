package com.byd.card.base;

import com.byd.card.base.IView;

/* JADX INFO: loaded from: classes.dex */
public interface IPresenter<V extends IView> {
    void attachView(V view);

    void detachView();

    boolean isViewAttach();
}
