package com.byd.systemsettings.btsetting.view.pager;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.bt.model.PagerBtDevice;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.R;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtPaireFailedPager extends a {
    private PagerBtDevice device;
    private LinearLayout operationBottom;
    private TextView tvHint;
    private TextView tvName;

    public BtPaireFailedPager(Context context) {
        super(context);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.pager_bt_pair_failed;
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
        this.tvName = (TextView) this.mRootView.findViewById(R.id.tv_bt_name_bt_pair_failed);
        this.tvHint = (TextView) this.mRootView.findViewById(R.id.tv_failed_hint_bt_pair_failed);
        this.operationBottom = (LinearLayout) this.mRootView.findViewById(R.id.ll_operation_bottom_bt_pair_failed);
        Button button = (Button) this.mRootView.findViewById(R.id.btn_repair_bt_pair_failed);
        Button button2 = (Button) this.mRootView.findViewById(R.id.btn_cancel_bt_pair_failed);
        button.setOnClickListener(this);
        button2.setOnClickListener(this);
        boolean zHasTextOverView = hasTextOverView(this.operationBottom);
        x3.a.a(this.TAG, "----initViews---hasOver: " + zHasTextOverView);
        if (zHasTextOverView) {
            changeLayoutToVertical(this.operationBottom);
        }
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
        int id = view.getId();
        if (id == R.id.btn_repair_bt_pair_failed) {
            w3.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(getClass(), PagerBtnType.BOTTOM_LEFT, this.device, null);
                return;
            }
            return;
        }
        if (id != R.id.btn_cancel_bt_pair_failed || (aVar = this.mBtnCallback) == null) {
            return;
        }
        aVar.onBtnClicked(getClass(), PagerBtnType.BOTTOM_RIGHT, this.device, null);
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        setViewGroupRtl(this.operationBottom, true);
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
            this.tvName.setText("");
        } else {
            this.tvName.setText(String.format(this.mRootView.getContext().getString(R.string.title_bt_pair_failed), this.device.getName()));
        }
    }
}
