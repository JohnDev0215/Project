package com.byd.systemsettings.btsetting.view.dialogpager;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.byd.mvp.model.BydData;
import com.byd.systemsettings.R;
import com.byd.systemsettings.system.model.BtPairCodeParams;
import com.byd.systemsettings.system.utils.BrandResource;
import y2.b;
import z3.a;
import z3.d;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class BtPairCodePager extends a {
    private BluetoothDevice curDevice;
    private final boolean isCpaaDisplay;
    private LinearLayout operationBottom;
    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tvBtName;
    private TextView tvTitle;

    public BtPairCodePager(Context context, boolean z7) {
        super(context);
        this.isCpaaDisplay = z7;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.a
    public String getDialogTag() {
        return "BtPairCodePager";
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.activity_bt_pair_code;
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

    @Override // z3.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_357);
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.mRootView.findViewById(R.id.iv_back_dialog_common_title).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_ok_dialog_bottom).setOnClickListener(this);
        this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom).setOnClickListener(this);
        LinearLayout linearLayout = (LinearLayout) this.mRootView.findViewById(R.id.bt_pair_code_bg);
        if (this.isCpaaDisplay) {
            linearLayout.setBackgroundResource(R.drawable.ambient_dialog_bg);
        }
        this.operationBottom = (LinearLayout) this.mRootView.findViewById(R.id.ll_opertion_dialog_bottom);
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_common_titles);
        this.tvBtName = (TextView) this.mRootView.findViewById(R.id.tv_bt_name_bt_pair_code);
        this.tv0 = (TextView) this.mRootView.findViewById(R.id.tv_bt_pair_code_0);
        this.tv1 = (TextView) this.mRootView.findViewById(R.id.tv_bt_pair_code_1);
        this.tv2 = (TextView) this.mRootView.findViewById(R.id.tv_bt_pair_code_2);
        this.tv3 = (TextView) this.mRootView.findViewById(R.id.tv_bt_pair_code_3);
        this.tv4 = (TextView) this.mRootView.findViewById(R.id.tv_bt_pair_code_4);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.tv_bt_pair_code_5);
        this.tv5 = textView;
        BrandResource.setTextColor(this.tv0, this.tv1, this.tv2, this.tv3, this.tv4, textView);
    }

    @Override // z3.a
    public int initWidth(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_560);
    }

    @Override // z3.g
    public f newP() {
        return null;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.iv_back_dialog_common_title || id == R.id.btn_cancel_dialog_bottom) {
            if (this.curDevice != null) {
                b.o.a().s(this.curDevice);
            }
            d dVar = this.mCallback;
            if (dVar != null) {
                dVar.onBackPress();
                return;
            }
            return;
        }
        if (id == R.id.btn_ok_dialog_bottom) {
            BluetoothDevice bluetoothDevice = this.curDevice;
            if (bluetoothDevice != null) {
                bluetoothDevice.setPairingConfirmation(true);
            }
            d dVar2 = this.mCallback;
            if (dVar2 != null) {
                dVar2.onBackPress();
            }
        }
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        x3.a.a(this.TAG, "-----onLtrRudderR-----");
        setViewGroupRtl(this.operationBottom, true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        x3.a.a(this.TAG, "-----onRtlRudderL-----");
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    @Override // z3.e
    public void updateData(BydData bydData) {
        if (bydData == null || !(bydData instanceof BtPairCodeParams)) {
            return;
        }
        BtPairCodeParams btPairCodeParams = (BtPairCodeParams) bydData;
        Intent intent = btPairCodeParams.intent;
        String str = btPairCodeParams.title;
        x3.a.a(this.TAG, "updateData", ", in = " + intent);
        if (intent == null) {
            return;
        }
        TextView textView = this.tvTitle;
        if (str == null) {
            str = "";
        }
        textView.setText(str);
        BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
        this.curDevice = bluetoothDevice;
        if (bluetoothDevice == null) {
            return;
        }
        int intExtra = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_VARIANT", Integer.MIN_VALUE);
        x3.a.a(this.TAG, "updateData", ", type = " + intExtra);
        String name = this.curDevice.getName();
        this.tvBtName.setText(name != null ? name : "");
        int intExtra2 = intent.getIntExtra("android.bluetooth.device.extra.PAIRING_KEY", Integer.MIN_VALUE);
        x3.a.a(this.TAG, "updateData", ", pairCode = " + intExtra2);
        int i8 = intExtra2 / 100000;
        int i9 = (intExtra2 / 10000) % 10;
        int i10 = (intExtra2 / 1000) % 10;
        int i11 = (intExtra2 / 100) % 10;
        x3.a.a(this.TAG, "updateData", ", code0： " + i8 + ", code1: " + i9 + ", code2: " + i10 + ", code3: " + i11);
        this.tv0.setText(String.valueOf(i8));
        this.tv1.setText(String.valueOf(i9));
        this.tv2.setText(String.valueOf(i10));
        this.tv3.setText(String.valueOf(i11));
        this.tv4.setText(String.valueOf((intExtra2 / 10) % 10));
        this.tv5.setText(String.valueOf(intExtra2 % 10));
    }
}
