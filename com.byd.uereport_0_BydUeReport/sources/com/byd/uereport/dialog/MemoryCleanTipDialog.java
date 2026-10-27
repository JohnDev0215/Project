package com.byd.uereport.dialog;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import com.byd.uereport.R;
import g5.c;

/* JADX INFO: loaded from: classes2.dex */
public class MemoryCleanTipDialog extends BydFullScreenDialog {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3229d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f3230e;

    public MemoryCleanTipDialog(Context context) {
        super(context);
        this.f3230e = true;
    }

    public int c() {
        return this.f3229d;
    }

    public final void d(int i8, TextView textView) {
        String str = String.format(this.mContext.getResources().getString(R.string.text_clean_des), i8 != 1 ? i8 != 2 ? i8 != 3 ? "15%" : "3%" : "5%" : "10%");
        if (Build.VERSION.SDK_INT >= 24) {
            textView.setText(Html.fromHtml(str, 0), TextView.BufferType.SPANNABLE);
        } else {
            textView.setText(Html.fromHtml(str), TextView.BufferType.SPANNABLE);
        }
    }

    @Override // b5.g, android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
    }

    public void e(int i8) {
        this.f3229d = i8;
    }

    public final void f() {
        Intent intent = new Intent();
        intent.setPackage("com.byd.cleardata");
        intent.setClassName("com.byd.cleardata", "com.byd.cleardata.ui.MainActivity");
        intent.addFlags(268435456);
        try {
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e8) {
            e8.printStackTrace();
        }
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        TextView textView = (TextView) view.findViewById(R.id.tv_yes);
        TextView textView2 = (TextView) view.findViewById(R.id.tv_no);
        d(this.f3229d, (TextView) view.findViewById(R.id.tv_clean_tip));
        textView.setOnClickListener(this);
        if (this.f3229d != 3) {
            textView2.setOnClickListener(this);
        } else {
            this.f3230e = false;
            textView2.setVisibility(8);
        }
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return c.e() == 1 ? R.layout.dialog_clean_memory_tip_right_rudder : R.layout.dialog_clean_memory_tip;
    }

    @Override // b5.g
    public boolean isCanceledOnTouchOutside() {
        return this.f3230e;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == R.id.tv_yes) {
            f();
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
