package a.n;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f538a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PathMeasure f539b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f540c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f541d;
    private final PointF e;
    private float f;

    h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f541d = new float[2];
        this.e = new PointF();
        this.f538a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f539b = pathMeasure;
        this.f540c = pathMeasure.getLength();
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
        this.f539b.getPosTan(this.f540c * f.floatValue(), this.f541d, null);
        PointF pointF = this.e;
        float[] fArr = this.f541d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f538a.set(t, pointF);
    }
}
