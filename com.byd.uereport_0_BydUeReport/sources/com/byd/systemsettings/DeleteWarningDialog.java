package com.byd.systemsettings;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.uereport.dialog.BydFullScreenDialog;
import o2.b;
import r4.a;

/* JADX INFO: loaded from: classes2.dex */
public class DeleteWarningDialog extends BydFullScreenDialog {
    private b.a mCallBack;
    private String mDeviceName;
    private String mType;

    public DeleteWarningDialog(Context context) {
        super(context);
    }

    public b.a getCallBack() {
        return this.mCallBack;
    }

    public String getDeviceName() {
        return this.mDeviceName;
    }

    public String getType() {
        return this.mType;
    }

    @Override // com.byd.uereport.dialog.BydFullScreenDialog, b5.g
    public void initContent(View view) {
        super.initContent(view);
        ((TextView) view.findViewById(com.byd.uereport.R.id.tv_title_identification)).setText(this.mContext.getString(com.byd.uereport.R.string.cp_warning));
        ImageView imageView = (ImageView) view.findViewById(com.byd.uereport.R.id.iv_type_identification);
        imageView.setImageResource(com.byd.uereport.R.drawable.icon_light_public_carplay);
        if ("Auto".equals(this.mType)) {
            imageView.setImageResource(com.byd.uereport.R.drawable.icon_light_public_android_auto);
        }
        ((TextView) view.findViewById(com.byd.uereport.R.id.tv_name_identification)).setText(this.mDeviceName);
        ((TextView) view.findViewById(com.byd.uereport.R.id.tv_msg_identification)).setText(this.mContext.getString(com.byd.uereport.R.string.cp_warning_des));
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
        return com.byd.uereport.R.layout.dialog_layout_delete_warn;
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

    public void setDeviceName(String str) {
        this.mDeviceName = str;
    }

    public void setDeviceType(String str) {
        this.mType = str;
    }
}
