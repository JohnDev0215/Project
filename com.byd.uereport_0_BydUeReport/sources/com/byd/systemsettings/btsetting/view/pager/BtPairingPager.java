package com.byd.systemsettings.btsetting.view.pager;

import android.content.Context;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.bt.model.PagerBtDevice;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.R;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtPairingPager extends a {
    private PagerBtDevice device;
    private volatile boolean isScanning;
    private ImageView ivPairing;
    private TextView tvName;

    public BtPairingPager(Context context) {
        super(context);
        this.isScanning = false;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_bt_pairing;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public void initData(Context context) {
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_bt_name_pager_bt_pairing);
        this.ivPairing = (ImageView) this.mRootView.findViewById(R.id.iv_pairing_pager_bt_pairing);
        this.mRootView.findViewById(R.id.btn_cancel_pager_bt_pairing).setOnClickListener(this);
    }

    @Override // z3.e
    public void loadData() {
        if (this.isScanning) {
            return;
        }
        startRefreshAnimation();
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        w3.a aVar;
        if (view.getId() != R.id.btn_cancel_pager_bt_pairing || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_MIDDLE, this.device, null);
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        super.onDestroy();
        onScanFinished();
    }

    @Override // z3.a, z3.e
    public void onHide() {
        super.onHide();
        onScanFinished();
    }

    public void onScanFinished() {
        x3.a.a(this.TAG, "---onScanFinished---");
        this.ivPairing.clearAnimation();
        this.isScanning = false;
    }

    public void startRefreshAnimation() {
        x3.a.a(this.TAG, "---startRefreshAnimation---");
        this.isScanning = true;
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(800L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setStartOffset(0L);
        this.ivPairing.startAnimation(rotateAnimation);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.e
    public void updateData(BydData bydData) {
        super.updateData(bydData);
        if (bydData == null) {
            return;
        }
        PagerBtDevice pagerBtDevice = (PagerBtDevice) bydData;
        this.device = pagerBtDevice;
        if (pagerBtDevice != null) {
            this.tvName.setText(pagerBtDevice.getName());
        } else {
            this.tvName.setText("");
        }
    }
}
