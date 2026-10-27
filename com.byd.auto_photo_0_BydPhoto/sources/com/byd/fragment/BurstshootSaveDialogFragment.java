package com.byd.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import com.byd.auto_photo.R;
import com.byd.auto_photo.a;

/* JADX INFO: loaded from: classes2.dex */
public class BurstshootSaveDialogFragment extends AlertDialogFragment {
    private int selectNums;

    private void setCancel(boolean z) {
        setCancelable(z);
        if (getDialog() == null) {
            return;
        }
        getDialog().setCanceledOnTouchOutside(z);
    }

    @Override // com.byd.fragment.AlertDialogFragment, com.byd.fragment.BaseDialogSuperFragment
    public String getTitle() {
        if (a.f1178a.intValue() != 2) {
            return getResources().getString(R.string.save_tips);
        }
        return null;
    }

    @Override // com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setCancel(true);
    }

    @Override // com.byd.fragment.AlertDialogFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle bundle) {
        this.msgContent = this.selectNums < 2 ? String.format(getResources().getString(R.string.burst_save_single), Integer.valueOf(this.selectNums)) : String.format(getResources().getString(R.string.burst_save), Integer.valueOf(this.selectNums));
        super.onViewCreated(view, bundle);
    }

    public void setNums(int i) {
        this.selectNums = i;
    }
}
