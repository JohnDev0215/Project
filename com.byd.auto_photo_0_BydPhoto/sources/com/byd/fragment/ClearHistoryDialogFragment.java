package com.byd.fragment;

import android.os.Bundle;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import com.byd.util.f;

/* JADX INFO: loaded from: classes2.dex */
public class ClearHistoryDialogFragment extends BaseDialogFragment {
    private OnClearHistoryListener onClearHistoryListener;

    public interface OnClearHistoryListener {
        void onClearFinish();
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment
    protected int getLayout() {
        return R.layout.layout_clear_history_dialog;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.byd.fragment.BaseDialogSuperFragment
    public void onSureClick() {
        f.k(getActivity()).e();
        super.onSureClick();
        OnClearHistoryListener onClearHistoryListener = this.onClearHistoryListener;
        if (onClearHistoryListener != null) {
            onClearHistoryListener.onClearFinish();
        }
    }

    public void setOnClearHistoryListener(OnClearHistoryListener onClearHistoryListener) {
        this.onClearHistoryListener = onClearHistoryListener;
    }
}
