package com.byd.fragment;

import android.net.Uri;

/* JADX INFO: loaded from: classes2.dex */
public abstract class MediaDataBaseFragment extends SubBaseFragment {
    protected boolean isDataChange;

    public void dismissAllDialog() {
    }

    public boolean isDataChange() {
        return this.isDataChange;
    }

    public abstract void onDataChange(Uri uri, String str);

    @Override // com.byd.fragment.SubBaseFragment, b.a.k.h.b
    public abstract /* synthetic */ void onExternalEject(String str, boolean z);

    public void setDataChange(boolean z) {
        this.isDataChange = z;
    }
}
