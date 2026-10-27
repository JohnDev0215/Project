package b.a.a.a.m;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Property;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class e extends Property<Drawable, Integer> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Property<Drawable, Integer> f1738a = new e();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final WeakHashMap<Drawable, Integer> f1739b;

    private e() {
        super(Integer.class, "drawableAlphaCompat");
        this.f1739b = new WeakHashMap<>();
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Integer get(Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 19) {
            return Integer.valueOf(drawable.getAlpha());
        }
        if (this.f1739b.containsKey(drawable)) {
            return this.f1739b.get(drawable);
        }
        return 255;
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(Drawable drawable, Integer num) {
        if (Build.VERSION.SDK_INT < 19) {
            this.f1739b.put(drawable, num);
        }
        drawable.setAlpha(num.intValue());
    }
}
