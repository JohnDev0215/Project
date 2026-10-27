package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class s implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGView f4087a;

    public s(PAGView pAGView) {
        this.f4087a = pAGView;
    }

    @Override // java.lang.Runnable
    public void run() {
        PAGView pAGView = this.f4087a;
        pAGView.w = pAGView.i.getCurrentPlayTime();
        this.f4087a.i.cancel();
    }
}
