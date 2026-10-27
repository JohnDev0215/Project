package com.byd.viewsup.adapters;

import android.view.View;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ICustomerOverScrollDecoratorAdapter<T extends View> implements IOverScrollDecoratorAdapter {
    protected boolean mIsCanRebound = true;

    public abstract Class<T> getChildClass();

    @Override // com.byd.viewsup.adapters.IOverScroller
    public abstract T getView();

    public abstract boolean setView(View view);

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionStart() {
        return this.mIsCanRebound;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public boolean preConditionEnd() {
        return this.mIsCanRebound;
    }

    @Override // com.byd.viewsup.adapters.IOverScrollDecoratorAdapter
    public void setCanRebound(boolean z) {
        this.mIsCanRebound = z;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public T translate2RecyclerView(View view) {
        if (view.getClass() == getChildClass()) {
            return view;
        }
        throw new RuntimeException(this + "\n你需要修改这里的逻辑,根据当前View转变成一个 " + getView());
    }
}
