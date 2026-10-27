package b.m;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f1858a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PathMeasure f1859b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f1860c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f1861d;
    private final PointF e;
    private float f;

    h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f1861d = new float[2];
        this.e = new PointF();
        this.f1858a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f1859b = pathMeasure;
        this.f1860c = pathMeasure.getLength();
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
        this.f1859b.getPosTan(this.f1860c * f.floatValue(), this.f1861d, null);
        PointF pointF = this.e;
        float[] fArr = this.f1861d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f1858a.set(t, pointF);
    }
}
