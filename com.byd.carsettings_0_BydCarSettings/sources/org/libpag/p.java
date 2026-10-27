package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class p implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGView f4084a;

    public p(PAGView pAGView) {
        this.f4084a = pAGView;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zIsOpaque = this.f4084a.isOpaque();
        this.f4084a.setOpaque(!zIsOpaque);
        this.f4084a.setOpaque(zIsOpaque);
    }
}
