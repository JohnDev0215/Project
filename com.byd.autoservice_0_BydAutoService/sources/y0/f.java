package y0;

import android.animation.ObjectAnimator;
import android.animation.TypeConverter;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.Property;

/* JADX INFO: compiled from: ObjectAnimatorUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class f {
    public static <T> ObjectAnimator a(T t3, Property<T, PointF> property, Path path) {
        return Build.VERSION.SDK_INT >= 21 ? ObjectAnimator.ofObject(t3, property, (TypeConverter) null, path) : ObjectAnimator.ofFloat(t3, new h(property, path), 0.0f, 1.0f);
    }
}
