package c.b.a.a.s;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Typeface f1822a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0057a f1823b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1824c;

    /* JADX INFO: renamed from: c.b.a.a.s.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0057a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0057a interfaceC0057a, Typeface typeface) {
        this.f1822a = typeface;
        this.f1823b = interfaceC0057a;
    }

    @Override // c.b.a.a.s.f
    public void a(int i) {
        d(this.f1822a);
    }

    @Override // c.b.a.a.s.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f1824c = true;
    }

    public final void d(Typeface typeface) {
        if (this.f1824c) {
            return;
        }
        this.f1823b.a(typeface);
    }
}
