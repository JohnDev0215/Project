package com.byd.card.view.dialog;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;
import com.android.systemui.R;
import com.byd.card.drag.ApplicationInfo;
import com.byd.card.view.activity.MoreAppActivity;

/* JADX INFO: loaded from: classes.dex */
public class SecondConfirmDialog extends BaseDialog {
    private OnClickListener listener;
    private TextView mCancel;
    private TextView mConfirm;

    public interface OnClickListener {
        void onClick(View view);
    }

    public SecondConfirmDialog(MoreAppActivity activity, int layoutId, ApplicationInfo applicationInfo) {
        super(activity, layoutId, applicationInfo);
        setCanceledOnTouchOutside(true);
    }

    @Override // com.byd.card.view.dialog.BaseDialog
    public void finishInitView(Dialog dialog) {
        this.mConfirm = (TextView) dialog.findViewById(R.id.clear_history_confirm);
        this.mCancel = (TextView) dialog.findViewById(R.id.clear_history_cancel);
        initListener();
    }

    @Override // com.byd.card.view.dialog.BaseDialog
    public void onViewClick(View view) {
        OnClickListener onClickListener = this.listener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    private void initListener() {
        setOnClickListener(this.mConfirm);
        setOnClickListener(this.mCancel);
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }
}
