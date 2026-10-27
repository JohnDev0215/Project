package V4;

import android.graphics.Typeface;
import android.text.TextPaint;
import com.bumptech.glide.d;

/* JADX INFO: loaded from: classes.dex */
public final class b extends d {
    public final /* synthetic */ TextPaint d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final /* synthetic */ d f9463e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ c f9464f;

    public b(c cVar, TextPaint textPaint, d dVar) {
        this.f9464f = cVar;
        this.d = textPaint;
        this.f9463e = dVar;
    }

    @Override // com.bumptech.glide.d
    public final void I(int i7) {
        this.f9463e.I(i7);
    }

    @Override // com.bumptech.glide.d
    public final void J(Typeface typeface, boolean z6) {
        this.f9464f.g(this.d, typeface);
        this.f9463e.J(typeface, z6);
    }
}
