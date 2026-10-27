package b.o;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Property<T, PointF> f2859a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final PathMeasure f2860b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final float f2861c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final float[] f2862d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final PointF f2863e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private float f2864f;

    h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f2862d = new float[2];
        this.f2863e = new PointF();
        this.f2859a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f2860b = pathMeasure;
        this.f2861c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(T t) {
        return Float.valueOf(this.f2864f);
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(T t, Float f2) {
        this.f2864f = f2.floatValue();
        this.f2860b.getPosTan(this.f2861c * f2.floatValue(), this.f2862d, null);
        PointF pointF = this.f2863e;
        float[] fArr = this.f2862d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f2859a.set(t, pointF);
    }
}
