package y0;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Property<T, PointF> f5903a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PathMeasure f5904b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f5905c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f5906d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PointF f5907e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f5908f;

    public h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f5906d = new float[2];
        this.f5907e = new PointF();
        this.f5903a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f5904b = pathMeasure;
        this.f5905c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(T t3) {
        return Float.valueOf(this.f5908f);
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(T t3, Float f3) {
        this.f5908f = f3.floatValue();
        this.f5904b.getPosTan(this.f5905c * f3.floatValue(), this.f5906d, null);
        PointF pointF = this.f5907e;
        float[] fArr = this.f5906d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f5903a.set(t3, pointF);
    }
}
