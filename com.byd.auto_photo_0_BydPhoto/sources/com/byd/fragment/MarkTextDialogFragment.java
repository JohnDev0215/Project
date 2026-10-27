package com.byd.fragment;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public class MarkTextDialogFragment extends MarkTextDialogBaseFragment {
    @Override // com.byd.fragment.MarkTextDialogBaseFragment, com.byd.fragment.BaseDialogSuperFragment, androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        this.maxLen = 120;
        super.onCreate(bundle);
    }
}
