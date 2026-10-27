package com.byd.music.view.smartRefresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import com.byd.music.R;
import com.scwang.smart.refresh.layout.api.RefreshFooter;
import com.scwang.smart.refresh.layout.api.RefreshKernel;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.RefreshState;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;

/* JADX INFO: loaded from: classes5.dex */
public class LimitFooterView extends RelativeLayout implements RefreshFooter {
    public void finish() {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public View getView() {
        return this;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public int onFinish(RefreshLayout refreshLayout, boolean z) {
        return 0;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onHorizontalDrag(float f, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onInitialized(RefreshKernel refreshKernel, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onMoving(boolean z, float f, int i, int i2, int i3) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onReleased(RefreshLayout refreshLayout, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void onStartAnimator(RefreshLayout refreshLayout, int i, int i2) {
    }

    @Override // com.scwang.smart.refresh.layout.listener.OnStateChangedListener
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState refreshState, RefreshState refreshState2) {
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshFooter
    public boolean setNoMoreData(boolean z) {
        return false;
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public void setPrimaryColors(int... iArr) {
    }

    public void start() {
    }

    public void stop() {
    }

    public LimitFooterView(Context context) {
        super(context);
        initView();
    }

    public LimitFooterView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initView();
    }

    public LimitFooterView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        initView();
    }

    private void initView() {
        setGravity(17);
        LayoutInflater.from(getContext()).inflate(R.layout.layout_smart_refresh_view, this);
    }

    @Override // com.scwang.smart.refresh.layout.api.RefreshComponent
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }
}
