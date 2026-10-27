package com.byd.fragment;

import com.byd.auto_photo.R;

/* JADX INFO: loaded from: classes2.dex */
public class FileDetailDialogFragment extends BaseFileDetailDialogFragment {
    @Override // com.byd.fragment.BaseFileDetailDialogFragment
    protected String getDetailTitle(String str) {
        return getActivity().getResources().getString(R.string.detail);
    }
}
