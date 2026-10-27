package com.byd.uereport.dialog;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import b5.g;
import com.byd.cpaa.androidauto.AndroidAutoManager;
import com.byd.uereport.R;
import com.bydux.api.common.tools.util.PathInterpolatorUtils;

/* JADX INFO: loaded from: classes2.dex */
public abstract class BydFullScreenDialog extends g {
    private int animTime;
    private ViewGroup llDialog;
    private ViewGroup llMasks;

    public BydFullScreenDialog(Context context) {
        super(context);
        this.animTime = 200;
    }

    @Override // b5.g
    public void initContent(View view) {
        this.llMasks = (ViewGroup) view.findViewById(R.id.ll_dialog_masks);
        this.llDialog = (ViewGroup) view.findViewById(R.id.ll_dialog_content);
        this.llMasks.setOnClickListener(this);
    }

    @Override // b5.g
    public boolean isFullScreen() {
        return true;
    }

    public void onClick(View view) {
        if (isCanceledOnTouchOutside() && view.getId() == R.id.ll_dialog_masks) {
            dismiss();
        }
    }

    @Override // b5.g
    public void setWidthAndHeight(int[] iArr) {
    }

    @Override // b5.g, android.app.Dialog
    public void show() {
        super.show();
        ViewGroup viewGroup = this.llDialog;
        if (viewGroup != null) {
            viewGroup.animate().scaleX(1.0f).scaleY(1.0f).setDuration(this.animTime).setInterpolator(PathInterpolatorUtils.getUniformSpeed()).start();
            this.llDialog.animate().alpha(1.0f).setDuration(this.animTime).setInterpolator(PathInterpolatorUtils.getStandard()).start();
        }
        if (this.llMasks == null || AndroidAutoManager.getInstance(this.mContext).isAndroidAutoCasting()) {
            return;
        }
        this.llMasks.animate().alpha(1.0f).setDuration(this.animTime).setInterpolator(PathInterpolatorUtils.getStandard()).start();
    }
}
