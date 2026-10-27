package com.byd.systemsettings.hotspot.view.pager;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import b.b.m.e.a;
import b.b.m.e.g;
import com.byd.base.view.segmentedbutton.SegmentedButtonGroup;
import com.byd.carsettings.R;
import com.byd.carsettings.ui.widget.BydInvalidButton;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;

/* JADX INFO: loaded from: classes.dex */
public class ApFlowLimitedPager extends a<WifiInputConnectPresenter> {
    private static final int UNIT_INDEX_GB = 1;
    private static final int UNIT_INDEX_MB = 0;
    private BydInvalidButton btnOk;
    private int curFlowLimited;
    private EditText etCustom;
    private View llCustom;
    private volatile HotspotParams params;
    private RadioGroup rg;
    private boolean showCustomLimit;
    private SegmentedButtonGroup srg;
    private int typeIndex;
    private int unitIndex;

    public ApFlowLimitedPager(Context context) {
        super(context);
        this.typeIndex = 0;
        this.showCustomLimit = false;
        this.unitIndex = 0;
    }

    private int flowLimitedToIndex(int i) {
        if (i == -1) {
            return 2;
        }
        if (i == 100) {
            return 3;
        }
        if (i == 200) {
            return 4;
        }
        return i == 500 ? 5 : 0;
    }

    private int getFlowLimited() {
        try {
            return Integer.parseInt(this.etCustom.getText().toString().trim());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            return -1;
        }
    }

    @Override // b.b.m.e.h
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // b.b.m.e.h
    public int getLayoutId() {
        return R.layout.pager_flow_limited_hotspot_right;
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
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApFlowLimitedPager.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                ApFlowLimitedPager.this.showCustomLimit = false;
                switch (i) {
                    case R.id.rbtn_100mb_hotspot_right /* 2131297757 */:
                        ApFlowLimitedPager.this.curFlowLimited = 100;
                        break;
                    case R.id.rbtn_200mb_hotspot_right /* 2131297764 */:
                        ApFlowLimitedPager.this.curFlowLimited = 200;
                        break;
                    case R.id.rbtn_500mb_hotspot_right /* 2131297767 */:
                        ApFlowLimitedPager.this.curFlowLimited = 500;
                        break;
                    case R.id.rbtn_custom_hotspot_right /* 2131297801 */:
                        ApFlowLimitedPager.this.showCustomLimit = true;
                        break;
                    case R.id.rbtn_unlimited_hotspot_right /* 2131297863 */:
                        ApFlowLimitedPager.this.curFlowLimited = -1;
                        break;
                }
                ApFlowLimitedPager.this.llCustom.setVisibility(ApFlowLimitedPager.this.showCustomLimit ? 0 : 8);
                if (ApFlowLimitedPager.this.params != null) {
                    ApFlowLimitedPager.this.btnOk.setInvalid(ApFlowLimitedPager.this.curFlowLimited == ApFlowLimitedPager.this.params.getFlowLimited());
                }
            }
        });
        this.unitIndex = 1;
        this.srg.setPosition(1);
        this.srg.setOnPositionChangedListener(new SegmentedButtonGroup.f() { // from class: com.byd.systemsettings.hotspot.view.pager.ApFlowLimitedPager.2
            @Override // com.byd.base.view.segmentedbutton.SegmentedButtonGroup.f
            public void onPositionChanged(View view, View view2, int i) {
                ApFlowLimitedPager.this.unitIndex = i;
            }
        });
        EditText editText = this.etCustom;
        editText.addTextChangedListener(new b.b.g.q.h.a(editText, b.b.g.q.h.a.REGEX_DIGITS));
        this.typeIndex = 0;
        RadioGroup radioGroup = this.rg;
        radioGroup.check(radioGroup.getChildAt(0).getId());
    }

    @Override // b.b.m.e.f
    public void initViews(Context context) {
        this.rg = (RadioGroup) this.mRootView.findViewById(R.id.rg_flow_limited_hotspot_right);
        int iFlowLimitedToIndex = flowLimitedToIndex(this.curFlowLimited);
        RadioGroup radioGroup = this.rg;
        radioGroup.check(radioGroup.getChildAt(iFlowLimitedToIndex).getId());
        View viewFindViewById = this.rg.findViewById(R.id.ll_view_custom_flow_limited);
        this.llCustom = viewFindViewById;
        this.etCustom = (EditText) viewFindViewById.findViewById(R.id.et_custom_value_custom_flow_limited);
        this.srg = (SegmentedButtonGroup) this.llCustom.findViewById(R.id.srg_custom_flow_limited);
        this.llCustom.findViewById(R.id.iv_delete_custom_flow_limited).setOnClickListener(this);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_flow_limited_hotspot_right);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_flow_limited_hotspot_right).setOnClickListener(this);
        this.llCustom.setVisibility(iFlowLimitedToIndex == 0 ? 0 : 8);
    }

    @Override // b.b.m.e.f
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        b.b.m.b.a aVar;
        int id = view.getId();
        if (id != R.id.btn_cancel_flow_limited_hotspot_right) {
            if (id == R.id.btn_ok_flow_limited_hotspot_right && (aVar = this.mBtnCallback) != null) {
                aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, Integer.valueOf(this.typeIndex), null);
                return;
            }
            return;
        }
        b.b.m.b.a aVar2 = this.mBtnCallback;
        if (aVar2 != null) {
            aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
        }
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
        if (apBand == 1) {
            this.typeIndex = 1;
        } else if (apBand == 2 || apBand == 4) {
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
