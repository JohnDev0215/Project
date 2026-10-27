package com.byd.music.base;

import android.content.Context;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BasePopupWindow<DB extends ViewDataBinding, VM extends ViewModel> extends PopupWindow {
    public static final int DIRECTION_BOTTOM = 3;
    public static final int DIRECTION_LEFT = 4;
    public static final int DIRECTION_RIGHT = 2;
    public static final int DIRECTION_RIGHT_TOP = 5;
    public static final int DIRECTION_TOP = 1;
    protected DB dataBinding;
    protected Context mContext;
    protected VM viewModel;

    protected abstract int animStyle();

    protected abstract int height();

    protected abstract void initData();

    protected abstract void initView();

    protected abstract int layoutId();

    protected abstract int viewmodel();

    protected abstract int width();

    public BasePopupWindow(Context context, VM vm) {
        super(context);
        this.mContext = context;
        DB db = (DB) DataBindingUtil.inflate(LayoutInflater.from(context), layoutId(), null, false);
        this.dataBinding = db;
        this.viewModel = vm;
        db.setVariable(viewmodel(), vm);
        initData();
        initView();
        setContentView(this.dataBinding.getRoot());
        setWidth(width());
        setHeight(height());
        setFocusable(true);
        setAnimationStyle(animStyle());
    }

    public void showAtLocation(View view, int i, float f) {
        int width;
        int iDp2px;
        int height;
        setFocusable(false);
        this.dataBinding.getRoot().measure(0, 0);
        int measuredWidth = this.dataBinding.getRoot().getMeasuredWidth();
        int measuredHeight = this.dataBinding.getRoot().getMeasuredHeight();
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (i != 1) {
            if (i == 2) {
                width = iArr[0] + view.getWidth() + dp2px(f);
                height = iArr[1] + (view.getHeight() / 2);
                measuredHeight /= 2;
            } else if (i == 4) {
                width = (iArr[0] - measuredWidth) - dp2px(f);
                height = iArr[1] + (view.getHeight() / 2);
                measuredHeight /= 2;
            } else if (i == 5) {
                width = iArr[0] + view.getWidth() + dp2px(f);
                height = iArr[1] + view.getHeight();
            } else {
                width = (iArr[0] + (view.getWidth() / 2)) - (measuredWidth / 2);
                iDp2px = iArr[1] + view.getHeight() + dp2px(f);
            }
            iDp2px = height - measuredHeight;
        } else {
            width = (iArr[0] + (view.getWidth() / 2)) - (measuredWidth / 2);
            iDp2px = (iArr[1] - measuredHeight) - dp2px(f);
        }
        showAtLocation(view, 0, width, iDp2px);
        update();
    }

    private int dp2px(float f) {
        return (int) TypedValue.applyDimension(1, f, this.mContext.getResources().getDisplayMetrics());
    }
}
