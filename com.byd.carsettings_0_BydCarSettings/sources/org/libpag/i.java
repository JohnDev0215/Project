package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class i implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGImageView f4062a;

    public i(PAGImageView pAGImageView) {
        this.f4062a = pAGImageView;
    }

    @Override // java.lang.Runnable
    public void run() {
        PAGImageView pAGImageView = this.f4062a;
        pAGImageView.z = pAGImageView.f4032e.getCurrentPlayTime();
        this.f4062a.f4032e.cancel();
    }
}
