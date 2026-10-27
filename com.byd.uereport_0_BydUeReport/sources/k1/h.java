package k1;

import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PointF;
import android.util.Property;

/* JADX INFO: compiled from: PathProperty.java */
/* JADX INFO: loaded from: classes.dex */
public class h<T> extends Property<T, Float> {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Property<T, PointF> f5747a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final PathMeasure f5748b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f5749c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float[] f5750d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final PointF f5751e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f5752f;

    public h(Property<T, PointF> property, Path path) {
        super(Float.class, property.getName());
        this.f5750d = new float[2];
        this.f5751e = new PointF();
        this.f5747a = property;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        this.f5748b = pathMeasure;
        this.f5749c = pathMeasure.getLength();
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
    public Float get(T t7) {
        return Float.valueOf(this.f5752f);
    }

    @Override // android.util.Property
    /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
    public void set(T t7, Float f8) {
        this.f5752f = f8.floatValue();
        this.f5748b.getPosTan(this.f5749c * f8.floatValue(), this.f5750d, null);
        PointF pointF = this.f5751e;
        float[] fArr = this.f5750d;
        pointF.x = fArr[0];
        pointF.y = fArr[1];
        this.f5747a.set(t7, pointF);
    }
}
