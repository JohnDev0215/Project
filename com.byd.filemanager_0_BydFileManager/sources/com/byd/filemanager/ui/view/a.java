package com.byd.filemanager.ui.view;

import android.os.SystemClock;
import android.view.View;

/* JADX INFO: compiled from: SingleClickListener.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected long f3893b = 0;

    public abstract void a(View view);

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (SystemClock.uptimeMillis() - this.f3893b < 500) {
            return;
        }
        this.f3893b = SystemClock.uptimeMillis();
        a(view);
    }
}
