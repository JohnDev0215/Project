package b.a.a.a.y;

import android.graphics.Typeface;

/* JADX INFO: loaded from: classes.dex */
public final class a extends f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Typeface f1774a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InterfaceC0054a f1775b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1776c;

    /* JADX INFO: renamed from: b.a.a.a.y.a$a, reason: collision with other inner class name */
    public interface InterfaceC0054a {
        void a(Typeface typeface);
    }

    public a(InterfaceC0054a interfaceC0054a, Typeface typeface) {
        this.f1774a = typeface;
        this.f1775b = interfaceC0054a;
    }

    private void d(Typeface typeface) {
        if (this.f1776c) {
            return;
        }
        this.f1775b.a(typeface);
    }

    @Override // b.a.a.a.y.f
    public void a(int i) {
        d(this.f1774a);
    }

    @Override // b.a.a.a.y.f
    public void b(Typeface typeface, boolean z) {
        d(typeface);
    }

    public void c() {
        this.f1776c = true;
    }
}
