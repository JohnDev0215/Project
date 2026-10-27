package com.byd.systemsettings.hotspot.view.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.BydInvalidButton;
import com.byd.systemsettings.R;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
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
    public int getType(int i8) {
        return (i8 == 0 || i8 != 1) ? 1 : 2;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_security_hotspot_right;
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
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApSecurityTypePager.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i8) {
                if (i8 == R.id.rbtn_cipher_security_hotspot_right) {
                    ApSecurityTypePager.this.typeIndex = 0;
                } else if (i8 == R.id.rbtn_wpa3_cipher_security_hotspot_right) {
                    ApSecurityTypePager.this.typeIndex = 1;
                }
                x3.a.a(ApSecurityTypePager.this.TAG, "---initData--onCheckedChanged--typeIndex = " + ApSecurityTypePager.this.typeIndex);
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

    @Override // z3.e
    public void initViews(Context context) {
        this.rg = (RadioGroup) this.mRootView.findViewById(R.id.rg_security_hotspot_right);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_security_hotspot_right);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_security_hotspot_right);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_security_hotspot_right).setOnClickListener(this);
    }

    @Override // z3.e
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        w3.a aVar;
        int id = view.getId();
        if (id == R.id.btn_ok_security_hotspot_right) {
            w3.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, Integer.valueOf(getType(this.typeIndex)), null);
                return;
            }
            return;
        }
        if (id != R.id.btn_cancel_security_hotspot_right || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
    }

    @Override // z3.e
    public void onLtrRudderL(boolean z7) {
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        x3.a.a(this.TAG, "onLtrRudderR land = " + z7);
        setViewGroupRtl(this.llBottom, true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.e
    public void updateData(BydData bydData) {
        x3.a.a(this.TAG, "data = " + bydData);
        if (bydData == null) {
            return;
        }
        this.params = (HotspotParams) bydData;
        int apSecurityType = this.params.getApSecurityType();
        x3.a.a(this.TAG, "---updateData---type = " + apSecurityType);
        if (apSecurityType == 1 || apSecurityType != 2) {
            this.typeIndex = 0;
        } else {
            this.typeIndex = 1;
        }
        int i8 = this.typeIndex;
        if (i8 >= 0 && i8 < this.rg.getChildCount()) {
            RadioGroup radioGroup = this.rg;
            radioGroup.check(radioGroup.getChildAt(this.typeIndex).getId());
        }
        this.btnOk.setInvalid(true);
    }

    @Override // z3.g
    public WifiInputConnectPresenter newP() {
        return new WifiInputConnectPresenter();
    }
}
