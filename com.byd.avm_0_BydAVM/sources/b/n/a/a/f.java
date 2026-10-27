package b.n.a.a;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.animation.Interpolator;
import com.bydux.api.common.tools.util.ListUtils;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: compiled from: PathInterpolatorCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class f implements Interpolator {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private float[] f1941a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private float[] f1942b;

    public f(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    private void a(float f, float f2, float f3, float f4) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f, f2, f3, f4, 1.0f, 1.0f);
        b(path);
    }

    private void b(Path path) {
        int i = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int iMin = Math.min(3000, ((int) (length / 0.002f)) + 1);
        if (iMin <= 0) {
            throw new IllegalArgumentException("The Path has a invalid length " + length);
        }
        this.f1941a = new float[iMin];
        this.f1942b = new float[iMin];
        float[] fArr = new float[2];
        for (int i2 = 0; i2 < iMin; i2++) {
            pathMeasure.getPosTan((i2 * length) / (iMin - 1), fArr, null);
            this.f1941a[i2] = fArr[0];
            this.f1942b[i2] = fArr[1];
        }
        if (Math.abs(this.f1941a[0]) <= 1.0E-5d && Math.abs(this.f1942b[0]) <= 1.0E-5d) {
            int i3 = iMin - 1;
            if (Math.abs(this.f1941a[i3] - 1.0f) <= 1.0E-5d && Math.abs(this.f1942b[i3] - 1.0f) <= 1.0E-5d) {
                float f = 0.0f;
                int i4 = 0;
                while (i < iMin) {
                    float[] fArr2 = this.f1941a;
                    int i5 = i4 + 1;
                    float f2 = fArr2[i4];
                    if (f2 < f) {
                        throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f2);
                    }
                    fArr2[i] = f2;
                    i++;
                    f = f2;
                    i4 = i5;
                }
                if (pathMeasure.nextContour()) {
                    throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The Path must start at (0,0) and end at (1,1) start: ");
        sb.append(this.f1941a[0]);
        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append(this.f1942b[0]);
        sb.append(" end:");
        int i6 = iMin - 1;
        sb.append(this.f1941a[i6]);
        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append(this.f1942b[i6]);
        throw new IllegalArgumentException(sb.toString());
    }

    private void c(float f, float f2) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f, f2, 1.0f, 1.0f);
        b(path);
    }

    private void d(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
            String strI = androidx.core.content.c.g.i(typedArray, xmlPullParser, "pathData", 4);
            Path pathE = b.g.d.c.e(strI);
            if (pathE != null) {
                b(pathE);
                return;
            }
            throw new InflateException("The path is null, which is created from " + strI);
        }
        if (!androidx.core.content.c.g.j(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        if (!androidx.core.content.c.g.j(xmlPullParser, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        float f = androidx.core.content.c.g.f(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
        float f2 = androidx.core.content.c.g.f(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
        boolean zJ = androidx.core.content.c.g.j(xmlPullParser, "controlX2");
        if (zJ != androidx.core.content.c.g.j(xmlPullParser, "controlY2")) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        }
        if (zJ) {
            a(f, f2, androidx.core.content.c.g.f(typedArray, xmlPullParser, "controlX2", 2, 0.0f), androidx.core.content.c.g.f(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
        } else {
            c(f, f2);
        }
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f) {
        if (f <= 0.0f) {
            return 0.0f;
        }
        if (f >= 1.0f) {
            return 1.0f;
        }
        int i = 0;
        int length = this.f1941a.length - 1;
        while (length - i > 1) {
            int i2 = (i + length) / 2;
            if (f < this.f1941a[i2]) {
                length = i2;
            } else {
                i = i2;
            }
        }
        float[] fArr = this.f1941a;
        float f2 = fArr[length] - fArr[i];
        if (f2 == 0.0f) {
            return this.f1942b[i];
        }
        float f3 = (f - fArr[i]) / f2;
        float[] fArr2 = this.f1942b;
        float f4 = fArr2[i];
        return f4 + (f3 * (fArr2[length] - f4));
    }

    public f(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.l);
        d(typedArrayK, xmlPullParser);
        typedArrayK.recycle();
    }
}
