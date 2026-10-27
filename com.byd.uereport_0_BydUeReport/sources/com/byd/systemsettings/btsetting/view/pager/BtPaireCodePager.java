package com.byd.systemsettings.btsetting.view.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.byd.bt.model.PagerBtDevice;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.R;
import com.byd.systemsettings.system.utils.BrandResource;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtPaireCodePager extends a {
    private PagerBtDevice device;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tvBtName;

    public BtPaireCodePager(Context context) {
        super(context);
    }

    private void setText(TextView textView, String str) {
        if (textView == null || str == null) {
            return;
        }
        textView.setText(str);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_bt_pair_code;
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
        this.tvBtName = (TextView) this.mRootView.findViewById(R.id.tv_bt_name_pager_bt_pair_code);
        this.tv0 = (TextView) this.mRootView.findViewById(R.id.tv_pager_bt_pair_code_0);
        this.tv1 = (TextView) this.mRootView.findViewById(R.id.tv_pager_bt_pair_code_1);
        this.tv2 = (TextView) this.mRootView.findViewById(R.id.tv_pager_bt_pair_code_2);
        this.tv3 = (TextView) this.mRootView.findViewById(R.id.tv_pager_bt_pair_code_3);
        this.tv4 = (TextView) this.mRootView.findViewById(R.id.tv_pager_bt_pair_code_4);
        this.tv5 = (TextView) this.mRootView.findViewById(R.id.tv_pager_bt_pair_code_5);
        this.mRootView.findViewById(R.id.btn_cancel_pager_bt_pair_code).setOnClickListener(this);
        BrandResource.setTextColor(this.tv0, this.tv1, this.tv2, this.tv3, this.tv4, this.tv5);
    }

    @Override // z3.e
    public void loadData() {
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        w3.a aVar;
        if (view.getId() != R.id.btn_cancel_pager_bt_pair_code || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_MIDDLE, this.device, null);
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
        if (pagerBtDevice == null) {
            setText(this.tvBtName, "");
            setText(this.tv0, "");
            setText(this.tv1, "");
            setText(this.tv2, "");
            setText(this.tv3, "");
            setText(this.tv4, "");
            setText(this.tv5, "");
            return;
        }
        setText(this.tvBtName, pagerBtDevice.getName());
        int pairCode = this.device.getPairCode();
        setText(this.tv0, String.valueOf(pairCode / 100000));
        setText(this.tv1, String.valueOf((pairCode / 10000) % 10));
        setText(this.tv2, String.valueOf((pairCode / 1000) % 10));
        setText(this.tv3, String.valueOf((pairCode / 100) % 10));
        setText(this.tv4, String.valueOf((pairCode / 10) % 10));
        setText(this.tv5, String.valueOf(pairCode % 10));
    }
}
