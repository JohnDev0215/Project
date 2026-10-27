package com.android.launcher3.graphics;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

/* JADX INFO: loaded from: classes4.dex */
public class FragmentWithPreview extends Fragment {
    private Context mPreviewContext;

    public void onInit(Bundle savedInstanceState) {
    }

    @Override // android.app.Fragment
    public final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onInit(savedInstanceState);
    }

    @Override // android.app.Fragment
    public Context getContext() {
        Context context = this.mPreviewContext;
        return context != null ? context : getActivity();
    }

    void enterPreviewMode(Context context) {
        this.mPreviewContext = context;
    }

    public boolean isInPreviewMode() {
        return this.mPreviewContext != null;
    }
}
