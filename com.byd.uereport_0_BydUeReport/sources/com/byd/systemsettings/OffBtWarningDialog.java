package com.byd.systemsettings;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.byd.uereport.dialog.BydFullScreenDialog;
import o2.b;
import r4.a;

/* JADX INFO: loaded from: classes2.dex */
public class OffBtWarningDialog extends BydFullScreenDialog {
    private b.a mCallBack;
    public TextView tvMsg;

    public OffBtWarningDialog(Context context) {
        super(context);
    }

    public b.a getCallBack() {
        return this.mCallBack;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        ((TextView) view.findViewById(com.byd.uereport.R.id.tv_title_dialog_btn_left_hight_light)).setText(this.mContext.getString(com.byd.uereport.R.string.cp_warning));
        TextView textView = (TextView) view.findViewById(com.byd.uereport.R.id.tv_msg_dialog_btn_left_hight_light);
        this.tvMsg = textView;
        textView.setText(this.mContext.getString(com.byd.uereport.R.string.bt_confirm_msg_2));
        Button button = (Button) view.findViewById(com.byd.uereport.R.id.btn_ok_dialog_bottom);
        button.setOnClickListener(this);
        button.setText(this.mContext.getString(com.byd.uereport.R.string.dilink_yes));
        Button button2 = (Button) view.findViewById(com.byd.uereport.R.id.btn_cancel_dialog_bottom);
        button2.setOnClickListener(this);
        button2.setText(this.mContext.getString(com.byd.uereport.R.string.dilink_no));
        ((ViewGroup) view.findViewById(com.byd.uereport.R.id.ll_opertion_dialog_bottom)).setLayoutDirection(a.f7799a ? 1 : 0);
    }

    @Override // b5.g
    public void initData() {
    }

    @Override // b5.g
    public int initLayoutRes() {
        return com.byd.uereport.R.layout.dialog_layout_switch_warn;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, android.view.View.OnClickListener
    public void onClick(View view) {
        super.onClick(view);
        if (view.getId() == com.byd.uereport.R.id.btn_cancel_dialog_bottom) {
            dismiss();
        } else if (view.getId() == com.byd.uereport.R.id.btn_ok_dialog_bottom) {
            b.a aVar = this.mCallBack;
            if (aVar != null) {
                aVar.onOkResult(null);
            }
            dismiss();
        }
    }

    public void setCallBack(b.a aVar) {
        this.mCallBack = aVar;
    }
}
