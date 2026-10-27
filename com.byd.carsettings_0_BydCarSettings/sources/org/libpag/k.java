package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class k implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ PAGImageView f4065a;

    public k(PAGImageView pAGImageView) {
        this.f4065a = pAGImageView;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.f4065a.s != null) {
            this.f4065a.s.a();
        }
    }
}
