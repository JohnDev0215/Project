package com.byd.systemsettings.hotspot.view.pager;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.mvp.ui.widget.BydInvalidButton;
import com.byd.systemsettings.R;
import com.byd.systemsettings.hotspot.model.HotspotParams;
import com.byd.systemsettings.ui.widget.BydTextWatcher;
import com.byd.systemsettings.wifi2.present.WifiInputConnectPresenter;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
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
    private int typeIndex;
    private int unitIndex;

    public ApFlowLimitedPager(Context context) {
        super(context);
        this.typeIndex = 0;
        this.showCustomLimit = false;
        this.unitIndex = 0;
    }

    private int flowLimitedToIndex(int i8) {
        if (i8 == -1) {
            return 2;
        }
        if (i8 == 100) {
            return 3;
        }
        if (i8 == 200) {
            return 4;
        }
        return i8 == 500 ? 5 : 0;
    }

    private int getFlowLimited() {
        try {
            return Integer.parseInt(this.etCustom.getText().toString().trim());
        } catch (NumberFormatException e8) {
            e8.printStackTrace();
            return -1;
        }
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_flow_limited_hotspot_right;
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
        this.rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() { // from class: com.byd.systemsettings.hotspot.view.pager.ApFlowLimitedPager.1
            @Override // android.widget.RadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(RadioGroup radioGroup, int i8) {
                ApFlowLimitedPager.this.showCustomLimit = false;
                if (i8 == R.id.rbtn_custom_hotspot_right) {
                    ApFlowLimitedPager.this.showCustomLimit = true;
                } else if (i8 == R.id.rbtn_unlimited_hotspot_right) {
                    ApFlowLimitedPager.this.curFlowLimited = -1;
                } else if (i8 == R.id.rbtn_100mb_hotspot_right) {
                    ApFlowLimitedPager.this.curFlowLimited = 100;
                } else if (i8 == R.id.rbtn_200mb_hotspot_right) {
                    ApFlowLimitedPager.this.curFlowLimited = 200;
                } else if (i8 == R.id.rbtn_500mb_hotspot_right) {
                    ApFlowLimitedPager.this.curFlowLimited = 500;
                }
                ApFlowLimitedPager.this.llCustom.setVisibility(ApFlowLimitedPager.this.showCustomLimit ? 0 : 8);
                if (ApFlowLimitedPager.this.params != null) {
                    ApFlowLimitedPager.this.btnOk.setInvalid(ApFlowLimitedPager.this.curFlowLimited == ApFlowLimitedPager.this.params.getFlowLimited());
                }
            }
        });
        this.unitIndex = 1;
        EditText editText = this.etCustom;
        editText.addTextChangedListener(new BydTextWatcher(editText, BydTextWatcher.REGEX_DIGITS));
        this.typeIndex = 0;
        RadioGroup radioGroup = this.rg;
        radioGroup.check(radioGroup.getChildAt(0).getId());
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.rg = (RadioGroup) this.mRootView.findViewById(R.id.rg_flow_limited_hotspot_right);
        int iFlowLimitedToIndex = flowLimitedToIndex(this.curFlowLimited);
        RadioGroup radioGroup = this.rg;
        radioGroup.check(radioGroup.getChildAt(iFlowLimitedToIndex).getId());
        View viewFindViewById = this.rg.findViewById(R.id.ll_view_custom_flow_limited);
        this.llCustom = viewFindViewById;
        this.etCustom = (EditText) viewFindViewById.findViewById(R.id.et_custom_value_custom_flow_limited);
        this.llCustom.findViewById(R.id.iv_delete_custom_flow_limited).setOnClickListener(this);
        BydInvalidButton bydInvalidButton = (BydInvalidButton) this.mRootView.findViewById(R.id.btn_ok_flow_limited_hotspot_right);
        this.btnOk = bydInvalidButton;
        bydInvalidButton.setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_flow_limited_hotspot_right).setOnClickListener(this);
        this.llCustom.setVisibility(iFlowLimitedToIndex == 0 ? 0 : 8);
    }

    @Override // z3.e
    public void loadData() {
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        w3.a aVar;
        int id = view.getId();
        if (id == R.id.btn_ok_flow_limited_hotspot_right) {
            w3.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, Integer.valueOf(this.typeIndex), null);
                return;
            }
            return;
        }
        if (id != R.id.btn_cancel_flow_limited_hotspot_right || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, null, null);
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
        int apBand = this.params.getApBand();
        if (apBand == 1) {
            this.typeIndex = 1;
        } else if (apBand == 2 || apBand == 4) {
            this.typeIndex = 0;
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
