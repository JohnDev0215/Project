package com.ts.dm.service.view;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ts.dm.service.R;
import com.ts.dm.service.data.ConnectionInfoBean;

/* JADX INFO: loaded from: classes.dex */
public class CommonDialog extends Dialog implements View.OnClickListener, DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private Context mContext;
    private TextView mDialogCancel;
    private TextView mDialogContent;
    private TextView mDialogRecommend;
    private TextView mDialogTitle;
    public IDialogListener mListener;
    private ConnectionInfoBean mTag;

    public interface IDialogListener {
        void onShowChanged(boolean z);
    }

    public CommonDialog(Context context) {
        super(context, R.style.DialogStyle);
        setContentView(R.layout.dialog_common);
        getWindow().setLayout(-1, -1);
        getWindow().setType(2038);
        getWindow().addFlags(256);
        getWindow().setGravity(17);
        getWindow().setBackgroundDrawableResource(R.color.bg);
        this.mContext = context;
        this.mDialogTitle = (TextView) findViewById(R.id.dialog_title);
        this.mDialogContent = (TextView) findViewById(R.id.dialog_content);
        this.mDialogCancel = (TextView) findViewById(R.id.dialog_cancel);
        this.mDialogRecommend = (TextView) findViewById(R.id.dialog_recommend);
        this.mDialogCancel.setOnClickListener(this);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setOnDismissListener(this);
        setOnShowListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.dialog_cancel) {
            dismiss();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        IDialogListener iDialogListener = this.mListener;
        if (iDialogListener != null) {
            iDialogListener.onShowChanged(true);
        }
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        IDialogListener iDialogListener = this.mListener;
        if (iDialogListener != null) {
            iDialogListener.onShowChanged(false);
        }
    }

    public void setDialogTitle(String content) {
        this.mDialogTitle.setVisibility(0);
        this.mDialogTitle.setText(content);
    }

    public void setDialogContent(String content) {
        this.mDialogContent.setVisibility(0);
        this.mDialogContent.setText(content);
    }

    public void setDialogCancel(String content, final View.OnClickListener listener) {
        this.mDialogCancel.setVisibility(0);
        this.mDialogCancel.setText(content);
        this.mDialogCancel.setOnClickListener(new View.OnClickListener() { // from class: com.ts.dm.service.view.-$$Lambda$CommonDialog$PiQY8wLSn1UFAwMpmfdEzKXRF_Y
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setDialogCancel$0$CommonDialog(listener, view);
            }
        });
        if (this.mDialogRecommend.getVisibility() == 0) {
            updateDialogButton();
        }
    }

    public /* synthetic */ void lambda$setDialogCancel$0$CommonDialog(View.OnClickListener listener, View view) {
        dismiss();
        if (listener != null) {
            listener.onClick(view);
        }
    }

    public void setDialogRecommend(String content, final View.OnClickListener listener) {
        this.mDialogRecommend.setVisibility(0);
        this.mDialogRecommend.setText(content);
        this.mDialogRecommend.setOnClickListener(new View.OnClickListener() { // from class: com.ts.dm.service.view.-$$Lambda$CommonDialog$LkhIU9yZWpeLYlQlWsXvJxsIZSs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setDialogRecommend$1$CommonDialog(listener, view);
            }
        });
        if (this.mDialogCancel.getVisibility() == 0) {
            updateDialogButton();
        }
    }

    public /* synthetic */ void lambda$setDialogRecommend$1$CommonDialog(View.OnClickListener listener, View view) {
        dismiss();
        if (listener != null) {
            listener.onClick(view);
        }
    }

    private void updateDialogButton() {
        LinearLayout.LayoutParams cancelPl = (LinearLayout.LayoutParams) this.mDialogCancel.getLayoutParams();
        cancelPl.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.commondialog_double_width);
        LinearLayout.LayoutParams recommendPl = (LinearLayout.LayoutParams) this.mDialogRecommend.getLayoutParams();
        recommendPl.width = this.mContext.getResources().getDimensionPixelSize(R.dimen.commondialog_double_width);
        recommendPl.leftMargin = this.mContext.getResources().getDimensionPixelSize(R.dimen.commondialog_double_marginleft);
        this.mDialogCancel.setLayoutParams(cancelPl);
        this.mDialogRecommend.setLayoutParams(recommendPl);
    }

    public void setDialogTag(ConnectionInfoBean info) {
        this.mTag = info;
    }

    public ConnectionInfoBean getDialogTag() {
        return this.mTag;
    }

    public void setDialogListener(IDialogListener listener) {
        this.mListener = listener;
    }
}
