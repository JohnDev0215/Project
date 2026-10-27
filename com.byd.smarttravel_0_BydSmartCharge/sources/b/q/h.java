package b.q;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Property<T, PointF> f1464a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PathMeasure f1465b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f1466c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f1467d;
    public final PointF e;
    public float f;

    public h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f1467d = new float[2];
        this.e = new PointF();
        this.f1464a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f1465b = pathMeasure;
        this.f1466c = pathMeasure.getLength();
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
        this.f1465b.getPosTan(this.f1466c * f.floatValue(), this.f1467d, null);
        PointF pointF = this.e;
        float[] fArr = this.f1467d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f1464a.set(t, pointF);
    }
}
