package org.libpag;

import android.util.Log;

/* JADX INFO: renamed from: org.libpag.r, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes3.dex */
public class RunnableC0109r implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGView f4086a;

    public RunnableC0109r(PAGView pAGView) {
        this.f4086a = pAGView;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f4086a.m) {
            this.f4086a.i.start();
        } else {
            Log.e("PAGView", "AnimatorStartRunnable: PAGView is not attached to window");
        }
    }
}
