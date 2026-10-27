package com.ts.appservice.power.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import com.ts.appservice.power.view.BasePresenter;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseLayout<T extends BasePresenter> extends FrameLayout {
    protected PowerViewControler mPop;
    protected T mPresenter;
    protected View mRootView;
    protected int mWarnFlag;

    protected abstract int attachLayoutRes();

    protected abstract void initPresenter(Context context);

    protected abstract void initView();

    public BaseLayout(Context context) {
        super(context);
        init(context);
    }

    public BaseLayout(Context context, int flag) {
        super(context);
        this.mWarnFlag = flag;
        init(context);
    }

    public BaseLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public BaseLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.mPop = PowerViewControler.getInstance(context);
        this.mRootView = LayoutInflater.from(context).inflate(attachLayoutRes(), this);
        initView();
        initPresenter(context);
    }

    public void onViewAdded() {
        T t = this.mPresenter;
        if (t != null) {
            t.onViewAdded();
        }
    }

    public void onResume() {
    }

    public void dismiss() {
        T t = this.mPresenter;
        if (t != null) {
            t.onDismiss();
        }
    }

    public void onPause() {
    }
}
