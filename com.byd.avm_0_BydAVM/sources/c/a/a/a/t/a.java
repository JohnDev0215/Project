package c.a.a.a.t;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f2038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0052a f2039b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2040c;

    /* JADX INFO: renamed from: c.a.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0052a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0052a interfaceC0052a, Typeface typeface) {
        this.f2038a = typeface;
        this.f2039b = interfaceC0052a;
    }

    private void d(Typeface typeface) {
        if (this.f2040c) {
            return;
        }
        this.f2039b.a(typeface);
    }

    @Override // c.a.a.a.t.f
    public void a(int i) {
        d(this.f2038a);
    }

    @Override // c.a.a.a.t.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f2040c = true;
    }
}
