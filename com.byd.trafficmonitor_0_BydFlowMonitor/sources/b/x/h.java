package b.x;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Property<T, PointF> f3255a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PathMeasure f3256b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f3257c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f3258d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PointF f3259e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f3260f;

    public h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f3258d = new float[2];
        this.f3259e = new PointF();
        this.f3255a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f3256b = pathMeasure;
        this.f3257c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(T t) {
        return Float.valueOf(this.f3260f);
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(T t, Float f2) {
        this.f3260f = f2.floatValue();
        this.f3256b.getPosTan(this.f3257c * f2.floatValue(), this.f3258d, null);
        PointF pointF = this.f3259e;
        float[] fArr = this.f3258d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f3255a.set(t, pointF);
    }
}
