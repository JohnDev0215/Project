package c.n;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f2441a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PathMeasure f2442b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f2443c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f2444d;
    private final PointF e;
    private float f;

    h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f2444d = new float[2];
        this.e = new PointF();
        this.f2441a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f2442b = pathMeasure;
        this.f2443c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(T t) {
        return Float.valueOf(this.f);
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(T t, Float f) {
        this.f = f.floatValue();
        this.f2442b.getPosTan(this.f2443c * f.floatValue(), this.f2444d, null);
        PointF pointF = this.e;
        float[] fArr = this.f2444d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f2441a.set(t, pointF);
    }
}
