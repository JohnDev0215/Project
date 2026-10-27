package com.byd.systemsettings.hotspot.view.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import b.b.g.p.d;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.carsettings.ui.widget.TintRadioButton;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;

/* JADX INFO: loaded from: classes.dex */
public class ApFreqBandPager extends a<WifiInputConnectPresenter> {
    private BydInvalidButton btnOk;
    private ViewGroup llBottom;
    private volatile HotspotParams params;
    private TintRadioButton rbtn5GHz;
    private RadioGroup rg;
    private int typeIndex;
    private boolean unsupport5GHz;

    public ApFreqBandPager(Context context) {
        super(context);
        this.typeIndex = 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getFreqBand(int i) {
        return i != 1 ? 2 : 1;
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_freq_band_hotspot_right;
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
        this.unsupport5GHz = d.g.a().f0();
        b.b.m.c.a.a(this.TAG, "---initData---unsupport5GHz: " + this.unsupport5GHz);
        if (this.unsupport5GHz) {
            this.rbtn5GHz.setInvalid(true);
            this.rg.check(R.id.rbtn_g2_4_freq_band_hotspot_right);
        } else {
            this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApFreqBandPager.1
                @Override // android.widget.RadioGroup.OnCheckedChangeListener
                public void onCheckedChanged(RadioGroup radioGroup, int i) {
                    switch (i) {
                        case R.id.rbtn_g2_4_freq_band_hotspot_right /* 2131297810 */:
                            ApFreqBandPager.this.typeIndex = 1;
                            break;
                        case R.id.rbtn_g5_freq_band_hotspot_right /* 2131297811 */:
                            ApFreqBandPager.this.typeIndex = 0;
                            break;
                    }
                    b.b.m.c.a.a(ApFreqBandPager.this.TAG, "---initData--onCheckedChanged--typeIndex = " + ApFreqBandPager.this.typeIndex);
                    if (ApFreqBandPager.this.params != null) {
                        BydInvalidButton bydInvalidButton = ApFreqBandPager.this.btnOk;
                        int apBand = ApFreqBandPager.this.params.getApBand();
                        ApFreqBandPager apFreqBandPager = ApFreqBandPager.this;
                        bydInvalidButton.setInvalid(apBand == apFreqBandPager.getFreqBand(apFreqBandPager.typeIndex));
                    }
                }
            });
            this.typeIndex = 0;
            RadioGroup radioGroup = this.rg;
            radioGroup.check(radioGroup.getChildAt(0).getId());
        }
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.rg = (RadioGroup) this.mRootView.findViewById(R.id.rg_freq_band_hotspot_right);
        this.rbtn5GHz = (TintRadioButton) this.mRootView.findViewById(R.id.rbtn_g5_freq_band_hotspot_right);
        this.llBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_bottom_operation_freq_band_hotspot_right);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_freq_band_hotspot_right);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_freq_band_hotspot_right).setOnClickListener(this);
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.m.b.a aVar;
        int id = view.getId();
        if (id != R.id.btn_cancel_freq_band_hotspot_right) {
            if (id == R.id.btn_ok_freq_band_hotspot_right && (aVar = this.mBtnCallback) != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, Integer.valueOf(getFreqBand(this.typeIndex)), null);
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
        int apBand = this.params.getApBand();
        if (this.unsupport5GHz || apBand == 1) {
            this.typeIndex = 1;
        } else if (apBand == 2 || apBand != 4) {
            this.typeIndex = 0;
        } else {
            this.typeIndex = 0;
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
