package com.byd.cleardata.ui.view;

import android.os.SystemClock;
import android.view.View;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements View.OnClickListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected long f2923b = 0;

    public abstract void a(View view);

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (SystemClock.uptimeMillis() - this.f2923b < 500) {
            return;
        }
        this.f2923b = SystemClock.uptimeMillis();
        a(view);
    }
}
