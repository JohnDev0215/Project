package defpackage;

import android.graphics.drawable.Animatable;
import android.graphics.drawable.Animatable2;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableWrapper;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.RotateDrawable;
import android.graphics.drawable.ScaleDrawable;
import android.os.Handler;
import android.os.Looper;
import androidx.appcompat.graphics.drawable.DrawableWrapperCompat;
import defpackage.l5;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.Callback;

/* JADX INFO: compiled from: AutoAnimatorDrawable.java */
/* JADX INFO: loaded from: classes2.dex */
public final class v9 extends DrawableWrapperCompat {
    public static final /* synthetic */ int g = 0;
    public boolean f;

    /* JADX INFO: compiled from: AutoAnimatorDrawable.java */
    public static final class a extends Animatable2.AnimationCallback {
        @Override // android.graphics.drawable.Animatable2.AnimationCallback
        public final void onAnimationEnd(Drawable drawable) {
            d.a.b(drawable);
        }
    }

    /* JADX INFO: compiled from: AutoAnimatorDrawable.java */
    public static final class b extends l5.a {
        public final Handler b = new Handler(Looper.getMainLooper());

        @Override // l5.a
        public final void b(Drawable drawable) {
            if (drawable instanceof Animatable) {
                this.b.post(new d92(27, drawable));
            }
        }
    }

    /* JADX INFO: compiled from: AutoAnimatorDrawable.java */
    public static final class c {
        public static final a a = new a();
    }

    /* JADX INFO: compiled from: AutoAnimatorDrawable.java */
    public static final class d {
        public static final b a = new b();
    }

    public v9(Drawable drawable) {
        super(drawable);
        a(this, new ps(22));
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void a(Drawable drawable, Callback<Animatable> callback) {
        if (drawable == 0) {
            return;
        }
        if (drawable instanceof Animatable) {
            callback.onResult((Animatable) drawable);
            return;
        }
        if (drawable != drawable.getCurrent()) {
            a(drawable.getCurrent(), callback);
        }
        if (drawable instanceof DrawableWrapper) {
            a(((DrawableWrapper) drawable).getDrawable(), callback);
            return;
        }
        if (drawable instanceof DrawableWrapperCompat) {
            a(((DrawableWrapperCompat) drawable).e, callback);
            return;
        }
        if (drawable instanceof LayerDrawable) {
            LayerDrawable layerDrawable = (LayerDrawable) drawable;
            for (int i = 0; i < layerDrawable.getNumberOfLayers(); i++) {
                a(layerDrawable.getDrawable(i), callback);
            }
            return;
        }
        if (drawable instanceof InsetDrawable) {
            a(((InsetDrawable) drawable).getDrawable(), callback);
        } else if (drawable instanceof RotateDrawable) {
            a(((RotateDrawable) drawable).getDrawable(), callback);
        } else if (drawable instanceof ScaleDrawable) {
            a(((ScaleDrawable) drawable).getDrawable(), callback);
        }
    }

    public static Drawable b(Drawable drawable) {
        if (drawable != null) {
            AtomicBoolean atomicBoolean = new AtomicBoolean();
            a(drawable, new l51(9, atomicBoolean));
            if (atomicBoolean.get()) {
                return new v9(drawable);
            }
        }
        return drawable;
    }

    @Override // androidx.appcompat.graphics.drawable.DrawableWrapperCompat, android.graphics.drawable.Drawable
    public final boolean setVisible(boolean z, boolean z2) {
        boolean visible = super.setVisible(z, z2);
        if (!z) {
            a(this, new fm3(11));
        } else if (visible || z2 || !this.f) {
            a(this, new xo(12));
        }
        this.f = true;
        return visible;
    }
}
