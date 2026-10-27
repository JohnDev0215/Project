package d.b.a.a.t;

import android.graphics.Typeface;

/* JADX INFO: compiled from: CancelableFontCallback.java */
/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f4790a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0084a f4791b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f4792c;

    /* JADX INFO: renamed from: d.b.a.a.t.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: CancelableFontCallback.java */
    public interface InterfaceC0084a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0084a interfaceC0084a, Typeface typeface) {
        this.f4790a = typeface;
        this.f4791b = interfaceC0084a;
    }

    private void d(Typeface typeface) {
        if (this.f4792c) {
            return;
        }
        this.f4791b.a(typeface);
    }

    @Override // d.b.a.a.t.f
    public void a(int i) {
        d(this.f4790a);
    }

    @Override // d.b.a.a.t.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f4792c = true;
    }
}
