package b.d.a.a.l;

import android.graphics.drawable.Drawable;
import android.util.Property;
import java.util.WeakHashMap;

/* JADX INFO: loaded from: classes.dex */
public class d extends Property<Drawable, Integer> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Property<Drawable, Integer> f1973b = new d();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final WeakHashMap<Drawable, Integer> f1974a;

    public d() {
        super(Integer.class, "drawableAlphaCompat");
        this.f1974a = new WeakHashMap<>();
    }

    @Override // android.util.Property
    public Integer get(Drawable drawable) {
        return Integer.valueOf(drawable.getAlpha());
    }

    @Override // android.util.Property
    public void set(Drawable drawable, Integer num) {
        drawable.setAlpha(num.intValue());
    }
}
