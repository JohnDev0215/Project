package c.b.a.a.t;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f2159a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0061a f2160b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2161c;

    /* JADX INFO: renamed from: c.b.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0061a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0061a interfaceC0061a, Typeface typeface) {
        this.f2159a = typeface;
        this.f2160b = interfaceC0061a;
    }

    private void d(Typeface typeface) {
        if (this.f2161c) {
            return;
        }
        this.f2160b.a(typeface);
    }

    @Override // c.b.a.a.t.f
    public void a(int i) {
        d(this.f2159a);
    }

    @Override // c.b.a.a.t.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f2161c = true;
    }
}
