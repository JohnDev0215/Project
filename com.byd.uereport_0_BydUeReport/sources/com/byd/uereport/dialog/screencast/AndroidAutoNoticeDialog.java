package com.byd.uereport.dialog.screencast;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;
import com.byd.uereport.dialog.BydFullScreenDialog;

/* JADX INFO: loaded from: classes2.dex */
public class AndroidAutoNoticeDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3360d;

    public AndroidAutoNoticeDialog(Context context) {
        super(context);
    }

    public int c() {
        return this.f3360d;
    }

    public void d(int i8) {
        this.f3360d = i8;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_ok);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_content);
        TextView textView3 = (TextView) view.findViewById(R.id.tv_title);
        int i8 = this.f3360d;
        if (i8 == 0) {
            textView3.setText(getContext().getString(R.string.aa_title));
            textView2.setText(getContext().getString(R.string.aa_user_auth_tip));
        } else if (i8 == 1) {
            textView3.setText(getContext().getString(R.string.aa_low_battery_tip));
            textView2.setText(getContext().getString(R.string.aa_notice_content));
        }
        textView.setOnClickListener(this);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return R.layout.dialog_android_auto_notice;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
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
