package d.b.a.a.t;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f3972a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0123a f3973b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3974c;

    /* JADX INFO: renamed from: d.b.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0123a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0123a interfaceC0123a, Typeface typeface) {
        this.f3972a = typeface;
        this.f3973b = interfaceC0123a;
    }

    private void d(Typeface typeface) {
        if (this.f3974c) {
            return;
        }
        this.f3973b.a(typeface);
    }

    @Override // d.b.a.a.t.f
    public void a(int i) {
        d(this.f3972a);
    }

    @Override // d.b.a.a.t.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f3974c = true;
    }
}
