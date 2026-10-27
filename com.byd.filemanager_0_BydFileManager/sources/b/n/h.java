package b.n;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f2127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PathMeasure f2128b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f2129c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f2130d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PointF f2131e;
    private float f;

    h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f2130d = new float[2];
        this.f2131e = new PointF();
        this.f2127a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f2128b = pathMeasure;
        this.f2129c = pathMeasure.getLength();
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
        this.f2128b.getPosTan(this.f2129c * f.floatValue(), this.f2130d, null);
        PointF pointF = this.f2131e;
        float[] fArr = this.f2130d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f2127a.set(t, pointF);
    }
}
