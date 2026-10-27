package com.byd.systemsettings.hotspot.view.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;

/* JADX INFO: loaded from: classes.dex */
public class ApSecurityTypePager extends a<WifiInputConnectPresenter> {
    private BydInvalidButton btnOk;
    private ViewGroup llBottom;
    private volatile HotspotParams params;
    private RadioGroup rg;
    private int typeIndex;

    public ApSecurityTypePager(Context context) {
        super(context);
        this.typeIndex = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getType(int i) {
        return (i == 0 || i != 1) ? 1 : 2;
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_security_hotspot_right;
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
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApSecurityTypePager.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (i == R.id.rbtn_cipher_security_hotspot_right) {
                    ApSecurityTypePager.this.typeIndex = 0;
                } else if (i == R.id.rbtn_wpa3_cipher_security_hotspot_right) {
                    ApSecurityTypePager.this.typeIndex = 1;
                }
                b.b.m.c.a.a(ApSecurityTypePager.this.TAG, "---initData--onCheckedChanged--typeIndex = " + ApSecurityTypePager.this.typeIndex);
                if (ApSecurityTypePager.this.params != null) {
                    BydInvalidButton bydInvalidButton = ApSecurityTypePager.this.btnOk;
                    int apSecurityType = ApSecurityTypePager.this.params.getApSecurityType();
                    ApSecurityTypePager apSecurityTypePager = ApSecurityTypePager.this;
                    bydInvalidButton.setInvalid(apSecurityType == apSecurityTypePager.getType(apSecurityTypePager.typeIndex));
                }
            }
        });
        this.typeIndex = 0;
        RadioGroup radioGroup = this.rg;
        radioGroup.check(radioGroup.getChildAt(0).getId());
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.rg = (RadioGroup) this.mRootView.findViewById(R.id.rg_security_hotspot_right);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_security_hotspot_right);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_security_hotspot_right);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_security_hotspot_right).setOnClickListener(this);
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.m.b.a aVar;
        int id = view.getId();
        if (id != R.id.btn_cancel_security_hotspot_right) {
            if (id == R.id.btn_ok_security_hotspot_right && (aVar = this.mBtnCallback) != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, Integer.valueOf(getType(this.typeIndex)), null);
                return;
            }
            return;
        }
        b.b.m.b.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
        }
    }

    @Override // b.b.m.e.f
    public void onLtrRudderL(boolean z) {
    }

    @Override // b.b.m.e.f
    public void onLtrRudderR(boolean z) {
        b.b.m.c.a.a(this.TAG, "onLtrRudderR land = " + z);
        setViewGroupRtl(this.llBottom, true);
    }

    @Override // b.b.m.e.f
    public void onRtlRudderL(boolean z) {
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // b.b.m.e.f
    public void updateData(BydData bydData) {
        b.b.m.c.a.a(this.TAG, "data = " + bydData);
        if (bydData == null) {
            return;
        }
        this.params = (HotspotParams) bydData;
        int apSecurityType = this.params.getApSecurityType();
        b.b.m.c.a.a(this.TAG, "---updateData---type = " + apSecurityType);
        if (apSecurityType == 1 || apSecurityType != 2) {
            this.typeIndex = 0;
        } else {
            this.typeIndex = 1;
        }
        int i = this.typeIndex;
        if (i >= 0 && i < this.rg.getChildCount()) {
            RadioGroup radioGroup = this.rg;
            radioGroup.check(radioGroup.getChildAt(this.typeIndex).getId());
        }
        this.btnOk.setInvalid(true);
    }

    @Override // b.b.m.e.h
    public WifiInputConnectPresenter newP() {
        return new WifiInputConnectPresenter();
    }
}
