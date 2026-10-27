package com.ts.dm.service.view;

import android.content.Context;
import android.content.DialogInterface;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.ts.dm.service.R;

/* JADX INFO: loaded from: classes.dex */
public class LoadingDialog extends CommonDialog implements DialogInterface.OnDismissListener, DialogInterface.OnShowListener {
    private Animation mAnimation;
    private ImageView mLoadingIcon;
    private TextView mLoadingTv;

    public LoadingDialog(Context context) {
        super(context);
        setContentView(R.layout.dialog_loading);
        this.mLoadingTv = (TextView) findViewById(R.id.loading_tv);
        this.mLoadingIcon = (ImageView) findViewById(R.id.loading_icon);
        this.mAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate);
        setCanceledOnTouchOutside(false);
        setCancelable(false);
        setOnDismissListener(this);
        setOnShowListener(this);
    }

    @Override // com.ts.dm.service.view.CommonDialog, android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        if (this.mListener != null) {
            this.mListener.onShowChanged(true);
        }
        this.mLoadingIcon.startAnimation(this.mAnimation);
    }

    @Override // com.ts.dm.service.view.CommonDialog, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        if (this.mListener != null) {
            this.mListener.onShowChanged(false);
        }
        this.mAnimation.cancel();
    }

    @Override // com.ts.dm.service.view.CommonDialog
    public void setDialogContent(String content) {
        this.mLoadingTv.setText(content);
    }
}
