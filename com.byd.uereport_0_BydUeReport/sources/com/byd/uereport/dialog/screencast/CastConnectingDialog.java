package com.byd.uereport.dialog.screencast;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;

/* JADX INFO: loaded from: classes2.dex */
public class CastConnectingDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public String f3379d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f3380e;

    public CastConnectingDialog(Context context) {
        super(context);
    }

    public int c() {
        return this.f3380e;
    }

    public void d(int i8) {
        this.f3380e = i8;
    }

    public String getDeviceName() {
        return this.f3379d;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_connect_tip);
        if (TextUtils.isEmpty(this.f3379d)) {
            return;
        }
        if (this.f3379d.length() <= 10) {
            textView.setText(String.format(this.mContext.getString(R.string.cp_connecting_content_text), this.f3379d));
            return;
        }
        textView.setText(String.format(this.mContext.getString(R.string.cp_connecting_content_text), this.f3379d.substring(0, 10) + "..."));
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return this.f3380e == 1 ? R.layout.dialog_aa_cast_connecting : R.layout.dialog_cast_connecting;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() != R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    public void setDeviceName(String str) {
        this.f3379d = str;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g, android.app.Dialog
    public void show() {
        super.show();
    }
}
