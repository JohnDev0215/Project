package com.byd.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import com.byd.util.w;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class PastProgressFragment extends BaseDialogFragment {
    private boolean canAutoCancel = true;
    private String msg;
    private OnCancelListener onCancelListener;
    private int titleRes;
    private TextView tvMsg;
    private boolean uiStop;

    public interface OnCancelListener {
        void onCanceled();

        void onCanceledFail();
    }

    public PastProgressFragment(int i) {
        this.titleRes = i;
        setCancelable(false);
    }

    private void checkDismissAfterStop() {
        if (!this.uiStop || getDialog() == null) {
            return;
        }
        getDialog().dismiss();
        setShowsDialog(false);
        try {
            Field declaredField = getClass().getSuperclass().getDeclaredField("mDialog");
            declaredField.setAccessible(true);
            declaredField.set(this, null);
            w.c("dismiss after stop , force destroy dialog");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismiss() {
        this.showing = false;
        try {
            super.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkDismissAfterStop();
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        this.showing = false;
        try {
            super.dismissAllowingStateLoss();
        } catch (Exception e) {
            e.printStackTrace();
        }
        checkDismissAfterStop();
        w.c("progress dismiss " + getTag());
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getButtonConfig() {
        return 4;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.fragment_past_progress_dialog;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected String getTitle() {
        return this.titleRes != 0 ? getResources().getString(this.titleRes) : super.getTitle();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        w.c("Past progress onCancel");
        OnCancelListener onCancelListener = this.onCancelListener;
        if (onCancelListener != null) {
            onCancelListener.onCanceled();
        }
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected void onCancelClick() {
        OnCancelListener onCancelListener = this.onCancelListener;
        if (onCancelListener != null) {
            if (!this.canAutoCancel) {
                onCancelListener.onCanceledFail();
                return;
            }
            onCancelListener.onCanceled();
        }
        super.onCancelClick();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.cancelOutside = false;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        this.uiStop = false;
        super.onStart();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.uiStop = true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        TextView textView = (TextView) view.findViewById(R.id.tv_progress_tips);
        this.tvMsg = textView;
        textView.setText(this.msg);
        if (this.showing) {
            return;
        }
        dismissAllowingStateLoss();
    }

    public void setCanAutoCancel(boolean z) {
        this.canAutoCancel = z;
    }

    public void setOnCancelListener(OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }

    public void updateMsg(String str) {
        TextView textView;
        this.msg = str;
        if (!this.showing || (textView = this.tvMsg) == null) {
            return;
        }
        textView.setText(str);
    }
}
