package c.a.a.a.t;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f2335a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0051a f2336b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f2337c;

    /* JADX INFO: renamed from: c.a.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0051a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0051a interfaceC0051a, Typeface typeface) {
        this.f2335a = typeface;
        this.f2336b = interfaceC0051a;
    }

    private void d(Typeface typeface) {
        if (this.f2337c) {
            return;
        }
        this.f2336b.a(typeface);
    }

    @Override // c.a.a.a.t.f
    public void a(int i) {
        d(this.f2335a);
    }

    @Override // c.a.a.a.t.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f2337c = true;
    }
}
