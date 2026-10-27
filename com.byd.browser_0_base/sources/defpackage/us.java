package defpackage;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.util.IntProperty;

/* JADX INFO: compiled from: ChromeTransitionDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class us extends LayerDrawable {
    public final a e;

    @NonNull
    public final Drawable f;

    @NonNull
    public final Drawable g;

    @NonNull
    public ObjectAnimator h;
    public boolean i;
    public int j;

    /* JADX INFO: compiled from: ChromeTransitionDrawable.java */
    public class a extends IntProperty<us> {
        public a() {
            super("ChromeTransitionDrawableProgress");
        }

        @Override // android.util.Property
        public final Integer get(Object obj) {
            return Integer.valueOf(((us) obj).j);
        }

        @Override // android.util.IntProperty
        public final void setValue(us usVar, int i) {
            usVar.a(i);
        }
    }

    /* JADX INFO: compiled from: ChromeTransitionDrawable.java */
    public static class b {
        public final ValueAnimator a;

        public b(ObjectAnimator objectAnimator) {
            this.a = objectAnimator;
        }
    }

    public us(@NonNull Drawable drawable, @NonNull Drawable drawable2) {
        super(new Drawable[]{drawable.mutate(), drawable2.mutate()});
        a aVar = new a();
        this.e = aVar;
        this.f = getDrawable(0);
        this.g = getDrawable(1);
        this.h = ObjectAnimator.ofInt(this, aVar, 255);
    }

    public final void a(int i) {
        this.j = i;
        if (this.i) {
            this.f.setAlpha(255 - i);
        }
        this.g.setAlpha(i);
    }

    public final b b() {
        if (this.h.isRunning()) {
            this.h.cancel();
        }
        a(0);
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(this, this.e, 255);
        this.h = objectAnimatorOfInt;
        objectAnimatorOfInt.setInterpolator(h21.f);
        this.h.start();
        return new b(this.h);
    }

    @Override // android.graphics.drawable.LayerDrawable, android.graphics.drawable.Drawable
    public final void draw(@NonNull Canvas canvas) {
        Drawable drawable = this.f;
        if (drawable.getAlpha() > 0) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.g;
        if (drawable2.getAlpha() > 0) {
            drawable2.draw(canvas);
        }
    }
}
