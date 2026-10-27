package c.b.a.a.f0;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Typeface f3813a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0083a f3814b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f3815c;

    /* JADX INFO: renamed from: c.b.a.a.f0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0083a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0083a interfaceC0083a, Typeface typeface) {
        this.f3813a = typeface;
        this.f3814b = interfaceC0083a;
    }

    @Override // c.b.a.a.f0.f
    public void a(int i2) {
        d(this.f3813a);
    }

    @Override // c.b.a.a.f0.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f3815c = true;
    }

    public final void d(Typeface typeface) {
        if (this.f3815c) {
            return;
        }
        this.f3814b.a(typeface);
    }
}
