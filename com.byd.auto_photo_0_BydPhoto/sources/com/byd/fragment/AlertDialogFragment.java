package com.byd.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import com.byd.util.w;

/* JADX INFO: loaded from: classes2.dex */
public class AlertDialogFragment extends BaseDialogFragment {
    public static final int BUTTON_TYPE_SINGLE_CANCEL = 2;
    public static final int BUTTON_TYPE_SINGLE_SURE = 1;
    public static final int ID_CANCEL = 1;
    public static final int ID_COMFIRM = 0;
    protected int cancelButtonText;
    protected int msg;
    protected String msgContent;
    private OnAlertDialogButtonClick onAlertDialogButtonClick;
    protected int sureButtonText;
    protected int title;
    private TextView tvMsg;
    private int contentGravity = 17;
    private int btnConfig = -1;

    public interface OnAlertDialogButtonClick {
        boolean onClick(int i);
    }

    public static AlertDialogFragment newInstance(int i, int i2) {
        return newInstance(i, i2, 0, 0);
    }

    public static AlertDialogFragment newInstance(int i, int i2, int i3, int i4) {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.configUi(i, i2, i3, i4);
        return alertDialogFragment;
    }

    public void configUi(int i, int i2, int i3, int i4) {
        this.title = i;
        this.msg = i2;
        this.sureButtonText = i3;
        this.cancelButtonText = i4;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        int i = this.btnConfig;
        return i != -1 ? i : super.getButtonConfig();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    public String getCancelButtonText() {
        return this.cancelButtonText != 0 ? getResources().getString(this.cancelButtonText) : super.getCancelButtonText();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_alert_dialog;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    public String getSureButtonText() {
        return this.sureButtonText != 0 ? getResources().getString(this.sureButtonText) : super.getSureButtonText();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    public String getTitle() {
        return this.title != 0 ? getResources().getString(this.title) : super.getTitle();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onCancelClick() {
        OnAlertDialogButtonClick onAlertDialogButtonClick = this.onAlertDialogButtonClick;
        if (onAlertDialogButtonClick != null) {
            onAlertDialogButtonClick.onClick(1);
        }
        super.onCancelClick();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.fragment.BaseDialogSuperFragment
    public void onSureClick() {
        if (this.onAlertDialogButtonClick != null) {
            setCancelable(false);
            if (!this.onAlertDialogButtonClick.onClick(0)) {
                setCancelable(true);
                return;
            }
        }
        super.onSureClick();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        TextView textView = (TextView) view.findViewById(R.id.alert_msg);
        this.tvMsg = textView;
        textView.setGravity(this.contentGravity);
        int i = this.msg;
        if (i == 0 && this.msgContent == null) {
            w.c("AlertDialog dismiss by msg is null");
            dismissAllowingStateLoss();
        } else if (i != 0) {
            this.tvMsg.setText(i);
        } else {
            this.tvMsg.setText(this.msgContent);
        }
    }

    public void setBtnConfig(int i) {
        this.btnConfig = i;
    }

    public void setContentGravity(int i) {
        this.contentGravity = i;
    }

    public void setMsg(int i) {
        TextView textView;
        this.msg = i;
        if (!isVisible() || (textView = this.tvMsg) == null) {
            return;
        }
        textView.setText(i);
    }

    public void setMsgContent(String str) {
        this.msgContent = str;
    }

    public void setOnAlertDialogButtonClick(OnAlertDialogButtonClick onAlertDialogButtonClick) {
        this.onAlertDialogButtonClick = onAlertDialogButtonClick;
    }

    public void setTitle(int i) {
        this.title = i;
    }
}
