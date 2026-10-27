package c.b.a.a.t;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f2721a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0070a f2722b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2723c;

    /* JADX INFO: renamed from: c.b.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0070a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0070a interfaceC0070a, Typeface typeface) {
        this.f2721a = typeface;
        this.f2722b = interfaceC0070a;
    }

    private void d(Typeface typeface) {
        if (this.f2723c) {
            return;
        }
        this.f2722b.a(typeface);
    }

    @Override // c.b.a.a.t.f
    public void a(int i) {
        d(this.f2721a);
    }

    @Override // c.b.a.a.t.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f2723c = true;
    }
}
