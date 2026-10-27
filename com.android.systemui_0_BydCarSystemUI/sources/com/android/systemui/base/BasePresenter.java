package com.android.systemui.base;

import com.android.systemui.base.BaseContract;
import com.android.systemui.base.BaseContract.BaseView;

/* JADX INFO: loaded from: classes.dex */
public class BasePresenter<T extends BaseContract.BaseView> implements BaseContract.BasePresenter<T> {
    protected T mView;

    @Override // com.android.systemui.base.BaseContract.BasePresenter
    public void addListener() {
    }

    @Override // com.android.systemui.base.BaseContract.BasePresenter
    public void initData() {
    }

    @Override // com.android.systemui.base.BaseContract.BasePresenter
    public void removeListener() {
    }

    @Override // com.android.systemui.base.BaseContract.BasePresenter
    public void attachView(T view) {
        this.mView = view;
    }

    @Override // com.android.systemui.base.BaseContract.BasePresenter
    public void detachView() {
        if (this.mView != null) {
            this.mView = null;
        }
    }
}
