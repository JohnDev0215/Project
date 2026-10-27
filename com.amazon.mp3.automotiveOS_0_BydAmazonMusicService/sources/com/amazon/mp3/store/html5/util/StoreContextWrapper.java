package com.amazon.mp3.store.html5.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import com.amazon.mp3.AmazonApplication;

/* JADX INFO: loaded from: classes4.dex */
public class StoreContextWrapper extends ContextWrapper {
    private Context mContext;

    public StoreContextWrapper() {
        super(AmazonApplication.getContext());
    }

    public void setContext(final Context context) {
        this.mContext = context;
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Object getSystemService(final String name) {
        Context context = this.mContext;
        return context != null ? context.getSystemService(name) : super.getSystemService(name);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources getResources() {
        Context context = this.mContext;
        return context != null ? context.getResources() : super.getResources();
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public Resources.Theme getTheme() {
        Context context = this.mContext;
        return context != null ? context.getTheme() : super.getTheme();
    }
}
