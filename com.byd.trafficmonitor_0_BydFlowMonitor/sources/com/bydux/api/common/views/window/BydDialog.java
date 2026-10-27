package com.bydux.api.common.views.window;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import com.bydux.api.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.views.ctrl.BydTimer;
import com.bydux.api.common.views.skin.listener.ISkinUpdate;
import com.bydux.api.common.views.skin.loader.SkinInflaterFactory;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes.dex */
public class BydDialog implements ISkinUpdate {
    private Context mContext;
    private Dialog mDialog;
    private BydTimer mTimer;
    private final String TAG = getClass().getSimpleName();
    private BydDialogListener mDialogListener = null;
    private SkinInflaterFactory mSkinInflaterFactory = new SkinInflaterFactory();

    public interface BydDialogListener {
        void onDialogDismiss();
    }

    public BydDialog(Context context) {
        this.mContext = context;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void release() {
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
        SkinManager.getInstance().detach(this);
        this.mSkinInflaterFactory.clean();
    }

    public void closeDialog() {
        BydLogHelper.logV(this.TAG, "closeDialog", null);
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            BydDialogListener bydDialogListener = this.mDialogListener;
            if (bydDialogListener != null) {
                bydDialogListener.onDialogDismiss();
            }
            TextUtils.isEmpty(onWindowAction());
        }
        this.mDialog = null;
        release();
    }

    @Override // com.bydux.api.common.views.skin.listener.ISkinUpdate
    public void onSkinUpdate() {
        BydLogHelper.logV(this.TAG, "onSkinUpdate", "skin=" + SkinManager.getInstance().getSkinPath());
        this.mSkinInflaterFactory.applySkin();
    }

    public String onWindowAction() {
        return null;
    }

    public void setBydDialogListener(BydDialogListener bydDialogListener) {
        this.mDialogListener = bydDialogListener;
    }

    public void setCloseTime(long j) {
        BydLogHelper.logV(this.TAG, "setCloseTime", "ms=" + j);
        BydTimer bydTimer = new BydTimer(this.mContext);
        this.mTimer = bydTimer;
        if (bydTimer != null) {
            bydTimer.setBydTimerListener(new BydTimer.BydTimerListener() { // from class: com.bydux.api.common.views.window.BydDialog.2
                @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
                public void onTimeUp(View view) {
                    BydDialog.this.closeDialog();
                }
            });
            this.mTimer.startTimer(j);
        }
    }

    public Dialog showDialog(int i2) {
        return showDialog(i2, 0, -1);
    }

    public Dialog showDialog(int i2, int i3) {
        return showDialog(i2, i3, -1);
    }

    public Dialog showDialog(int i2, int i3, int i4) {
        BydLogHelper.logV(this.TAG, "showDialog", "winType=" + i3);
        if (this.mDialog == null) {
            Dialog dialog = new Dialog(this.mContext, R.style.byd_dialog);
            this.mDialog = dialog;
            dialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.bydux.api.common.views.window.BydDialog.1
                @Override // android.content.DialogInterface.OnDismissListener
                public void onDismiss(DialogInterface dialogInterface) {
                    if (BydDialog.this.mDialogListener != null) {
                        BydDialog.this.mDialogListener.onDialogDismiss();
                    }
                    BydDialog.this.release();
                }
            });
            Window window = this.mDialog.getWindow();
            if (i3 > 0) {
                window.setType(i3);
            }
            if (i4 == 0) {
                window.setWindowAnimations(R.style.byd_dialog_anim);
            } else if (i4 > 0) {
                window.setWindowAnimations(i4);
            }
            if (window.getLayoutInflater().getFactory() == null) {
                BydLogHelper.logV(this.TAG, "showDialog", "dialog has no factory before");
                window.getLayoutInflater().setFactory(this.mSkinInflaterFactory);
                SkinManager.getInstance().attach(this);
            }
            this.mDialog.setContentView(i2);
        }
        this.mDialog.show();
        TextUtils.isEmpty(onWindowAction());
        return this.mDialog;
    }
}
