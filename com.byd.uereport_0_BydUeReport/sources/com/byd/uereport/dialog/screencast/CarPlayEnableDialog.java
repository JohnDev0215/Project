package com.byd.uereport.dialog.screencast;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.cpaa.carplay.CarPlayManager;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;
import g5.c;
import ts.car.service.carplay.data.ConnectRequest;

/* JADX INFO: loaded from: classes2.dex */
@Deprecated
public class CarPlayEnableDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3365d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public String f3366e;

    public final void c() {
        if (this.f3365d == null) {
            return;
        }
        ConnectRequest connectRequest = new ConnectRequest();
        connectRequest.setDeviceId(this.f3365d);
        connectRequest.setConnectType(2);
        connectRequest.setUserRequest(true);
        CarPlayManager.getInstance(this.mContext).connectCarPlay(connectRequest);
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    @SuppressLint({"StringFormatInvalid"})
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_confirm);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_cancel);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_title);
        if (!TextUtils.isEmpty(this.f3366e)) {
            if (this.f3366e.length() > 10) {
                textView3.setText(String.format(this.mContext.getString(R.string.cp_enable_content_tip), this.f3366e.substring(0, 10) + "..."));
            } else {
                textView3.setText(String.format(this.mContext.getString(R.string.cp_enable_content_tip), this.f3366e));
            }
        }
        textView.setOnClickListener(this);
        textView2.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_car_play_enable_tip_right_rudder : R.layout.dialog_car_play_enable_tip;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_confirm) {
            c();
        }
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
