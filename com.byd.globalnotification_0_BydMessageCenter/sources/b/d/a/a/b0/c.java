package b.d.a.a.b0;

import android.graphics.Typeface;
import android.text.TextPaint;

/* JADX INFO: loaded from: classes.dex */
public class c extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ TextPaint f1812a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final /* synthetic */ d f1813b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final /* synthetic */ b f1814c;

    public c(b bVar, TextPaint textPaint, d dVar) {
        this.f1814c = bVar;
        this.f1812a = textPaint;
        this.f1813b = dVar;
    }

    @Override // b.d.a.a.b0.d
    public void a(int i) {
        this.f1813b.a(i);
    }

    @Override // b.d.a.a.b0.d
    public void b(Typeface typeface, boolean z) {
        this.f1814c.g(this.f1812a, typeface);
        this.f1813b.b(typeface, z);
    }
}
