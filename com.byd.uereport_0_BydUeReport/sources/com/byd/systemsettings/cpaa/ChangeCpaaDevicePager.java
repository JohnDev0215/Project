package com.byd.systemsettings.cpaa;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.byd.mvp.callback.PagerBtnType;
import com.byd.systemsettings.R;
import z3.a;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class ChangeCpaaDevicePager extends a {
    private static final String TAG_W = "ChangeCpaaDevicePager";
    private final boolean mSupportAA;
    private final boolean mSupportCarPlay;
    private ViewGroup opertionBottom;
    private TextView tvMsg;
    private TextView tvTitle;

    public ChangeCpaaDevicePager(Context context, boolean z7, boolean z8) {
        super(context);
        this.mSupportCarPlay = z7;
        this.mSupportAA = z8;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // z3.a
    public String getDialogTag() {
        return TAG_W;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.dialog_btn_left_hight_light;
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
        this.tvTitle.setText(context.getString(R.string.title_swtich_device));
        if (this.mSupportCarPlay) {
            this.tvMsg.setText(context.getString(R.string.cp_switch_device_tip));
        } else if (this.mSupportAA) {
            this.tvMsg.setText(context.getString(R.string.aa_switch_device_tip));
        } else {
            this.tvMsg.setText(context.getString(R.string.carplay_switch_device));
        }
    }

    @Override // z3.a
    public int initHeight(Context context) {
        return (int) context.getResources().getDimension(R.dimen.dp_312);
    }

    @Override // z3.e
    public void initViews(Context context) {
        this.tvTitle = (TextView) this.mRootView.findViewById(R.id.tv_title_dialog_btn_left_hight_light);
        this.tvMsg = (TextView) this.mRootView.findViewById(R.id.tv_msg_dialog_btn_left_hight_light);
        this.opertionBottom = (ViewGroup) this.mRootView.findViewById(R.id.ll_opertion_dialog_bottom);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.btn_ok_dialog_bottom);
        textView.setText(context.getString(R.string.dilink_yes));
        textView.setOnClickListener(this);
        TextView textView2 = (TextView) this.mRootView.findViewById(R.id.btn_cancel_dialog_bottom);
        textView2.setOnClickListener(this);
        textView2.setText(context.getString(R.string.dilink_no));
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
        if (id == R.id.btn_ok_dialog_bottom) {
            w3.a aVar = this.mBtnCallback;
            if (aVar != null) {
                aVar.onBtnClicked(PagerBtnType.BOTTOM_LEFT);
            }
            finish();
            return;
        }
        if (id == R.id.btn_cancel_dialog_bottom) {
            w3.a aVar2 = this.mBtnCallback;
            if (aVar2 != null) {
                aVar2.onBtnClicked(PagerBtnType.BOTTOM_RIGHT);
            }
            finish();
        }
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        setViewGroupRtl(this.opertionBottom, true);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        setViewGroupRtl(this.opertionBottom, false);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }
}
