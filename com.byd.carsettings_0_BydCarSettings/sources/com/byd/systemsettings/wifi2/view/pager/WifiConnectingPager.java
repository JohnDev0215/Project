package com.byd.systemsettings.wifi2.view.pager;

import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.wifi2.model.WifiScanResult;
import com.byd.systemsettings.wifi2.present.ConnectedWifiInfoPresenter;

/* JADX INFO: loaded from: classes.dex */
public class WifiConnectingPager extends a<ConnectedWifiInfoPresenter> {
    private volatile boolean isScanning;
    private ImageView ivConnecting;
    private volatile WifiScanResult result;
    private TextView tvName;

    public WifiConnectingPager(Context context) {
        super(context);
        this.isScanning = false;
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_wifi_connecting;
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ g mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.h
    /* JADX INFO: renamed from: getP */
    public /* bridge */ /* synthetic */ Object mo7getP() {
        return super.mo7getP();
    }

    @Override // b.b.m.e.f
    public void initData(Context context) {
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_wifi_name_pager_wifi_connecting);
        this.ivConnecting = (ImageView) this.mRootView.findViewById(R.id.iv_connecting_pager_wifi_connecting);
        this.mRootView.findViewById(R.id.btn_cancel_pager_wifi_connecting).setOnClickListener(this);
    }

    @Override // b.b.m.e.f
    public void loadData() {
        b.b.m.c.a.a(this.TAG, "---loadData---");
        if (this.result != null) {
            this.tvName.setText(this.result.getName());
        } else {
            this.tvName.setText("");
        }
        if (this.isScanning) {
            return;
        }
        startRefreshAnimation();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.getId() != R.id.btn_cancel_pager_wifi_connecting) {
            return;
        }
        onScanFinished();
        b.b.m.b.a aVar = this.mBtnCallback;
        if (aVar != null) {
            aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_MIDDLE, null, null);
        }
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onDestroy() {
        super.onDestroy();
        onScanFinished();
    }

    @Override // b.b.m.e.a, b.b.m.e.f
    public void onHide() {
        super.onHide();
        onScanFinished();
    }

    public void onScanFinished() {
        b.b.m.c.a.a(this.TAG, "---onScanFinished---");
        this.ivConnecting.clearAnimation();
        this.isScanning = false;
    }

    public void startRefreshAnimation() {
        b.b.m.c.a.a(this.TAG, "---startRefreshAnimation---");
        this.isScanning = true;
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setStartOffset(0L);
        this.ivConnecting.startAnimation(rotateAnimation);
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        if (bydData == null || !(bydData instanceof WifiScanResult)) {
            return;
        }
        this.result = (WifiScanResult) bydData;
    }

    @Override // b.b.m.e.h
    public ConnectedWifiInfoPresenter newP() {
        return new ConnectedWifiInfoPresenter();
    }
}
