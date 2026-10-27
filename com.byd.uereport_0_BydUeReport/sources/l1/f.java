package l1;

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
    public float[] f6093a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float[] f6094b;

    public f(Context context, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        this(context.getResources(), context.getTheme(), attributeSet, xmlPullParser);
    }

    public final void a(float f8, float f9, float f10, float f11) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.cubicTo(f8, f9, f10, f11, 1.0f, 1.0f);
        b(path);
    }

    public final void b(Path path) {
        int i8 = 0;
        PathMeasure pathMeasure = new PathMeasure(path, false);
        float length = pathMeasure.getLength();
        int iMin = Math.min(3000, ((int) (length / 0.002f)) + 1);
        if (iMin <= 0) {
            throw new IllegalArgumentException("The Path has a invalid length " + length);
        }
        this.f6093a = new float[iMin];
        this.f6094b = new float[iMin];
        float[] fArr = new float[2];
        for (int i9 = 0; i9 < iMin; i9++) {
            pathMeasure.getPosTan((i9 * length) / (iMin - 1), fArr, null);
            this.f6093a[i9] = fArr[0];
            this.f6094b[i9] = fArr[1];
        }
        if (Math.abs(this.f6093a[0]) <= 1.0E-5d && Math.abs(this.f6094b[0]) <= 1.0E-5d) {
            int i10 = iMin - 1;
            if (Math.abs(this.f6093a[i10] - 1.0f) <= 1.0E-5d && Math.abs(this.f6094b[i10] - 1.0f) <= 1.0E-5d) {
                float f8 = 0.0f;
                int i11 = 0;
                while (i8 < iMin) {
                    float[] fArr2 = this.f6093a;
                    int i12 = i11 + 1;
                    float f9 = fArr2[i11];
                    if (f9 < f8) {
                        throw new IllegalArgumentException("The Path cannot loop back on itself, x :" + f9);
                    }
                    fArr2[i8] = f9;
                    i8++;
                    f8 = f9;
                    i11 = i12;
                }
                if (pathMeasure.nextContour()) {
                    throw new IllegalArgumentException("The Path should be continuous, can't have 2+ contours");
                }
                return;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("The Path must start at (0,0) and end at (1,1) start: ");
        sb.append(this.f6093a[0]);
        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append(this.f6094b[0]);
        sb.append(" end:");
        int i13 = iMin - 1;
        sb.append(this.f6093a[i13]);
        sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        sb.append(this.f6094b[i13]);
        throw new IllegalArgumentException(sb.toString());
    }

    public final void c(float f8, float f9) {
        Path path = new Path();
        path.moveTo(0.0f, 0.0f);
        path.quadTo(f8, f9, 1.0f, 1.0f);
        b(path);
    }

    public final void d(TypedArray typedArray, XmlPullParser xmlPullParser) {
        if (f0.g.j(xmlPullParser, "pathData")) {
            String strI = f0.g.i(typedArray, xmlPullParser, "pathData", 4);
            Path pathE = g0.c.e(strI);
            if (pathE != null) {
                b(pathE);
                return;
            }
            throw new InflateException("The path is null, which is created from " + strI);
        }
        if (!f0.g.j(xmlPullParser, "controlX1")) {
            throw new InflateException("pathInterpolator requires the controlX1 attribute");
        }
        if (!f0.g.j(xmlPullParser, "controlY1")) {
            throw new InflateException("pathInterpolator requires the controlY1 attribute");
        }
        float f8 = f0.g.f(typedArray, xmlPullParser, "controlX1", 0, 0.0f);
        float f9 = f0.g.f(typedArray, xmlPullParser, "controlY1", 1, 0.0f);
        boolean zJ = f0.g.j(xmlPullParser, "controlX2");
        if (zJ != f0.g.j(xmlPullParser, "controlY2")) {
            throw new InflateException("pathInterpolator requires both controlX2 and controlY2 for cubic Beziers.");
        }
        if (zJ) {
            a(f8, f9, f0.g.f(typedArray, xmlPullParser, "controlX2", 2, 0.0f), f0.g.f(typedArray, xmlPullParser, "controlY2", 3, 0.0f));
        } else {
            c(f8, f9);
        }
    }

    @Override // android.animation.TimeInterpolator
    public float getInterpolation(float f8) {
        if (f8 <= 0.0f) {
            return 0.0f;
        }
        if (f8 >= 1.0f) {
            return 1.0f;
        }
        int i8 = 0;
        int length = this.f6093a.length - 1;
        while (length - i8 > 1) {
            int i9 = (i8 + length) / 2;
            if (f8 < this.f6093a[i9]) {
                length = i9;
            } else {
                i8 = i9;
            }
        }
        float[] fArr = this.f6093a;
        float f9 = fArr[length] - fArr[i8];
        if (f9 == 0.0f) {
            return this.f6094b[i8];
        }
        float f10 = (f8 - fArr[i8]) / f9;
        float[] fArr2 = this.f6094b;
        float f11 = fArr2[i8];
        return f11 + (f10 * (fArr2[length] - f11));
    }

    public f(Resources resources, Resources.Theme theme, AttributeSet attributeSet, XmlPullParser xmlPullParser) {
        TypedArray typedArrayK = f0.g.k(resources, theme, attributeSet, a.f6077l);
        d(typedArrayK, xmlPullParser);
        typedArrayK.recycle();
    }
}
