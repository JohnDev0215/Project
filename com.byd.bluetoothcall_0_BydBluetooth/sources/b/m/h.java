package b.m;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f2465a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PathMeasure f2466b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f2467c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f2468d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PointF f2469e;
    private float f;

    h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f2468d = new float[2];
        this.f2469e = new PointF();
        this.f2465a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f2466b = pathMeasure;
        this.f2467c = pathMeasure.getLength();
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
        this.f2466b.getPosTan(this.f2467c * f.floatValue(), this.f2468d, null);
        PointF pointF = this.f2469e;
        float[] fArr = this.f2468d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f2465a.set(t, pointF);
    }
}
