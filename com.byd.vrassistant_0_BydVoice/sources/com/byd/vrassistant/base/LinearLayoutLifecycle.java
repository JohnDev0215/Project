package com.byd.vrassistant.base;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;

/* JADX INFO: loaded from: classes.dex */
public abstract class LinearLayoutLifecycle extends LinearLayout implements LifecycleOwner {
    private static final String TAG = "LinearLayoutLifecycle";
    LifecycleRegistry lifecycleRegistry;

    protected abstract void initView();

    protected abstract void initViewModel();

    public LinearLayoutLifecycle(Context context) {
        this(context, null);
    }

    public LinearLayoutLifecycle(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public LinearLayoutLifecycle(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i, 0);
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        lifecycleRegistry.setCurrentState(Lifecycle.State.CREATED);
        Log.i(TAG, "view 创建");
        initView();
        initViewModel();
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        return this.lifecycleRegistry;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP);
    }

    @Override // android.view.View
    public void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_RESUME);
            Log.i(TAG, "view 显示");
        } else if (i == 8 || i == 4) {
            this.lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE);
            Log.i(TAG, "view 隐藏");
        }
    }

    public void unInit() {
        this.lifecycleRegistry.setCurrentState(Lifecycle.State.DESTROYED);
        Log.i(TAG, "view 销毁");
    }
}
