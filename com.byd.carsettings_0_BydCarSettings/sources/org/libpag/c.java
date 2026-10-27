package org.libpag;

/* JADX INFO: loaded from: classes3.dex */
public class c implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ d f4055a;

    public c(d dVar) {
        this.f4055a = dVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f4055a.f4057b.quitSafely();
    }
}
