package a.l.d;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public final class b0 implements Runnable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ArrayList f940a;

    public b0(ArrayList arrayList) {
        this.f940a = arrayList;
    }

    @Override // java.lang.Runnable
    public void run() {
        g0.o(this.f940a, 4);
    }
}
