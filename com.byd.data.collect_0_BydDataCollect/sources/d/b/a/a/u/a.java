package d.b.a.a.u;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f4708a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0088a f4709b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4710c;

    /* JADX INFO: renamed from: d.b.a.a.u.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0088a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0088a interfaceC0088a, Typeface typeface) {
        this.f4708a = typeface;
        this.f4709b = interfaceC0088a;
    }

    private void d(Typeface typeface) {
        if (this.f4710c) {
            return;
        }
        this.f4709b.a(typeface);
    }

    @Override // d.b.a.a.u.f
    public void a(int i2) {
        d(this.f4708a);
    }

    @Override // d.b.a.a.u.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f4710c = true;
    }
}
