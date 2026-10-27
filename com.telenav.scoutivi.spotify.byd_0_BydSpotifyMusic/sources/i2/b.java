package i2;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import i2.d;

/* JADX INFO: compiled from: DrawableCrossFadeTransition.java */
/* JADX INFO: loaded from: classes.dex */
public class b implements d<Drawable> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f6915a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final boolean f6916b;

    public b(int i7, boolean z6) {
        this.f6915a = i7;
        this.f6916b = z6;
    }

    @Override // i2.d
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public boolean a(Drawable drawable, d.a aVar) {
        Drawable drawableC = aVar.c();
        if (drawableC == null) {
            drawableC = new ColorDrawable(0);
        }
        TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{drawableC, drawable});
        transitionDrawable.setCrossFadeEnabled(this.f6916b);
        transitionDrawable.startTransition(this.f6915a);
        aVar.b(transitionDrawable);
        return true;
    }
}
