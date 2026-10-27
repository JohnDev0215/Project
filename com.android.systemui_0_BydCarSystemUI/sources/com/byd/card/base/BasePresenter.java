package com.byd.card.base;

import android.content.Context;
import com.byd.card.base.IModel;
import com.byd.card.base.IView;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public abstract class BasePresenter<V extends IView, K extends IModel> implements IPresenter<V> {
    protected Context mContext;
    protected K mModel;
    protected V mView;
    private WeakReference<V> weakReference;

    protected abstract K createModule();

    @Override // com.byd.card.base.IPresenter
    public void attachView(V v) {
        this.weakReference = new WeakReference<>(v);
        this.mView = v;
        if (this.mModel == null) {
            this.mModel = (K) createModule();
        }
    }

    @Override // com.byd.card.base.IPresenter
    public void detachView() {
        if (isViewAttach()) {
            this.weakReference.clear();
            this.weakReference = null;
        }
        if (this.mModel != null) {
            this.mModel = null;
        }
        if (this.mView != null) {
            this.mView = null;
        }
    }

    @Override // com.byd.card.base.IPresenter
    public boolean isViewAttach() {
        WeakReference<V> weakReference = this.weakReference;
        return (weakReference == null || weakReference.get() == null) ? false : true;
    }

    protected V getView() {
        return this.weakReference.get();
    }
}
