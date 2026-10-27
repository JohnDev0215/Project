package org.libpag;

import android.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class h implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGImageView f4061a;

    public h(PAGImageView pAGImageView) {
        this.f4061a = pAGImageView;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f4061a.I) {
            this.f4061a.f4032e.start();
        } else {
            Log.e("PAGImageView", "AnimatorStartRunnable: PAGView is not attached to window");
        }
    }
}
