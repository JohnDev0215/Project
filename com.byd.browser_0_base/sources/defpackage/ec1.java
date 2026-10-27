package defpackage;

import com.google.android.material.datepicker.a;

/* JADX INFO: compiled from: MaterialCalendar.java */
/* JADX INFO: loaded from: classes.dex */
public final class ec1 implements Runnable {
    public final /* synthetic */ int e;
    public final /* synthetic */ a f;

    public ec1(a aVar, int i) {
        this.f = aVar;
        this.e = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f.h0.k0(this.e);
    }
}
