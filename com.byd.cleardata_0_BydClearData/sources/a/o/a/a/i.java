package a.o.a.a;

import a.g.d.c;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class i extends a.o.a.a.h {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    static final PorterDuff.Mode f624c = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private h f625d;
    private PorterDuffColorFilter e;
    private ColorFilter f;
    private boolean g;
    private boolean h;
    private Drawable.ConstantState i;
    private final float[] j;
    private final Matrix k;
    private final Rect l;

    private static class b extends f {
        b() {
        }

        b(b bVar) {
            super(bVar);
        }

        private void f(TypedArray typedArray, XmlPullParser xmlPullParser) {
            String string = typedArray.getString(0);
            if (string != null) {
                this.f631b = string;
            }
            String string2 = typedArray.getString(1);
            if (string2 != null) {
                this.f630a = a.g.d.c.d(string2);
            }
            this.f632c = androidx.core.content.c.g.g(typedArray, xmlPullParser, "fillType", 2, 0);
        }

        @Override // a.o.a.a.i.f
        public boolean c() {
            return true;
        }

        public void e(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
                TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.o.a.a.a.f610d);
                f(typedArrayK, xmlPullParser);
                typedArrayK.recycle();
            }
        }
    }

    private static class c extends f {
        private int[] e;
        androidx.core.content.c.b f;
        float g;
        androidx.core.content.c.b h;
        float i;
        float j;
        float k;
        float l;
        float m;
        Paint.Cap n;
        Paint.Join o;
        float p;

        c() {
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
        }

        c(c cVar) {
            super(cVar);
            this.g = 0.0f;
            this.i = 1.0f;
            this.j = 1.0f;
            this.k = 0.0f;
            this.l = 1.0f;
            this.m = 0.0f;
            this.n = Paint.Cap.BUTT;
            this.o = Paint.Join.MITER;
            this.p = 4.0f;
            this.e = cVar.e;
            this.f = cVar.f;
            this.g = cVar.g;
            this.i = cVar.i;
            this.h = cVar.h;
            this.f632c = cVar.f632c;
            this.j = cVar.j;
            this.k = cVar.k;
            this.l = cVar.l;
            this.m = cVar.m;
            this.n = cVar.n;
            this.o = cVar.o;
            this.p = cVar.p;
        }

        private Paint.Cap e(int i, Paint.Cap cap) {
            return i != 0 ? i != 1 ? i != 2 ? cap : Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        }

        private Paint.Join f(int i, Paint.Join join) {
            return i != 0 ? i != 1 ? i != 2 ? join : Paint.Join.BEVEL : Paint.Join.ROUND : Paint.Join.MITER;
        }

        private void h(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) {
            this.e = null;
            if (androidx.core.content.c.g.j(xmlPullParser, "pathData")) {
                String string = typedArray.getString(0);
                if (string != null) {
                    this.f631b = string;
                }
                String string2 = typedArray.getString(2);
                if (string2 != null) {
                    this.f630a = a.g.d.c.d(string2);
                }
                this.h = androidx.core.content.c.g.e(typedArray, xmlPullParser, theme, "fillColor", 1, 0);
                this.j = androidx.core.content.c.g.f(typedArray, xmlPullParser, "fillAlpha", 12, this.j);
                this.n = e(androidx.core.content.c.g.g(typedArray, xmlPullParser, "strokeLineCap", 8, -1), this.n);
                this.o = f(androidx.core.content.c.g.g(typedArray, xmlPullParser, "strokeLineJoin", 9, -1), this.o);
                this.p = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeMiterLimit", 10, this.p);
                this.f = androidx.core.content.c.g.e(typedArray, xmlPullParser, theme, "strokeColor", 3, 0);
                this.i = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeAlpha", 11, this.i);
                this.g = androidx.core.content.c.g.f(typedArray, xmlPullParser, "strokeWidth", 4, this.g);
                this.l = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathEnd", 6, this.l);
                this.m = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathOffset", 7, this.m);
                this.k = androidx.core.content.c.g.f(typedArray, xmlPullParser, "trimPathStart", 5, this.k);
                this.f632c = androidx.core.content.c.g.g(typedArray, xmlPullParser, "fillType", 13, this.f632c);
            }
        }

        @Override // a.o.a.a.i.e
        public boolean a() {
            return this.h.i() || this.f.i();
        }

        @Override // a.o.a.a.i.e
        public boolean b(int[] iArr) {
            return this.f.j(iArr) | this.h.j(iArr);
        }

        public void g(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.o.a.a.a.f609c);
            h(typedArrayK, xmlPullParser, theme);
            typedArrayK.recycle();
        }

        float getFillAlpha() {
            return this.j;
        }

        int getFillColor() {
            return this.h.e();
        }

        float getStrokeAlpha() {
            return this.i;
        }

        int getStrokeColor() {
            return this.f.e();
        }

        float getStrokeWidth() {
            return this.g;
        }

        float getTrimPathEnd() {
            return this.l;
        }

        float getTrimPathOffset() {
            return this.m;
        }

        float getTrimPathStart() {
            return this.k;
        }

        void setFillAlpha(float f) {
            this.j = f;
        }

        void setFillColor(int i) {
            this.h.k(i);
        }

        void setStrokeAlpha(float f) {
            this.i = f;
        }

        void setStrokeColor(int i) {
            this.f.k(i);
        }

        void setStrokeWidth(float f) {
            this.g = f;
        }

        void setTrimPathEnd(float f) {
            this.l = f;
        }

        void setTrimPathOffset(float f) {
            this.m = f;
        }

        void setTrimPathStart(float f) {
            this.k = f;
        }
    }

    private static class d extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Matrix f626a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final ArrayList<e> f627b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f628c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private float f629d;
        private float e;
        private float f;
        private float g;
        private float h;
        private float i;
        final Matrix j;
        int k;
        private int[] l;
        private String m;

        public d() {
            super();
            this.f626a = new Matrix();
            this.f627b = new ArrayList<>();
            this.f628c = 0.0f;
            this.f629d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            this.j = new Matrix();
            this.m = null;
        }

        public d(d dVar, a.d.a<String, Object> aVar) {
            f bVar;
            super();
            this.f626a = new Matrix();
            this.f627b = new ArrayList<>();
            this.f628c = 0.0f;
            this.f629d = 0.0f;
            this.e = 0.0f;
            this.f = 1.0f;
            this.g = 1.0f;
            this.h = 0.0f;
            this.i = 0.0f;
            Matrix matrix = new Matrix();
            this.j = matrix;
            this.m = null;
            this.f628c = dVar.f628c;
            this.f629d = dVar.f629d;
            this.e = dVar.e;
            this.f = dVar.f;
            this.g = dVar.g;
            this.h = dVar.h;
            this.i = dVar.i;
            this.l = dVar.l;
            String str = dVar.m;
            this.m = str;
            this.k = dVar.k;
            if (str != null) {
                aVar.put(str, this);
            }
            matrix.set(dVar.j);
            ArrayList<e> arrayList = dVar.f627b;
            for (int i = 0; i < arrayList.size(); i++) {
                e eVar = arrayList.get(i);
                if (eVar instanceof d) {
                    this.f627b.add(new d((d) eVar, aVar));
                } else {
                    if (eVar instanceof c) {
                        bVar = new c((c) eVar);
                    } else {
                        if (!(eVar instanceof b)) {
                            throw new IllegalStateException("Unknown object in the tree!");
                        }
                        bVar = new b((b) eVar);
                    }
                    this.f627b.add(bVar);
                    String str2 = bVar.f631b;
                    if (str2 != null) {
                        aVar.put(str2, bVar);
                    }
                }
            }
        }

        private void d() {
            this.j.reset();
            this.j.postTranslate(-this.f629d, -this.e);
            this.j.postScale(this.f, this.g);
            this.j.postRotate(this.f628c, 0.0f, 0.0f);
            this.j.postTranslate(this.h + this.f629d, this.i + this.e);
        }

        private void e(TypedArray typedArray, XmlPullParser xmlPullParser) {
            this.l = null;
            this.f628c = androidx.core.content.c.g.f(typedArray, xmlPullParser, "rotation", 5, this.f628c);
            this.f629d = typedArray.getFloat(1, this.f629d);
            this.e = typedArray.getFloat(2, this.e);
            this.f = androidx.core.content.c.g.f(typedArray, xmlPullParser, "scaleX", 3, this.f);
            this.g = androidx.core.content.c.g.f(typedArray, xmlPullParser, "scaleY", 4, this.g);
            this.h = androidx.core.content.c.g.f(typedArray, xmlPullParser, "translateX", 6, this.h);
            this.i = androidx.core.content.c.g.f(typedArray, xmlPullParser, "translateY", 7, this.i);
            String string = typedArray.getString(0);
            if (string != null) {
                this.m = string;
            }
            d();
        }

        @Override // a.o.a.a.i.e
        public boolean a() {
            for (int i = 0; i < this.f627b.size(); i++) {
                if (this.f627b.get(i).a()) {
                    return true;
                }
            }
            return false;
        }

        @Override // a.o.a.a.i.e
        public boolean b(int[] iArr) {
            boolean zB = false;
            for (int i = 0; i < this.f627b.size(); i++) {
                zB |= this.f627b.get(i).b(iArr);
            }
            return zB;
        }

        public void c(Resources resources, AttributeSet attributeSet, Resources.Theme theme, XmlPullParser xmlPullParser) {
            TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.o.a.a.a.f608b);
            e(typedArrayK, xmlPullParser);
            typedArrayK.recycle();
        }

        public String getGroupName() {
            return this.m;
        }

        public Matrix getLocalMatrix() {
            return this.j;
        }

        public float getPivotX() {
            return this.f629d;
        }

        public float getPivotY() {
            return this.e;
        }

        public float getRotation() {
            return this.f628c;
        }

        public float getScaleX() {
            return this.f;
        }

        public float getScaleY() {
            return this.g;
        }

        public float getTranslateX() {
            return this.h;
        }

        public float getTranslateY() {
            return this.i;
        }

        public void setPivotX(float f) {
            if (f != this.f629d) {
                this.f629d = f;
                d();
            }
        }

        public void setPivotY(float f) {
            if (f != this.e) {
                this.e = f;
                d();
            }
        }

        public void setRotation(float f) {
            if (f != this.f628c) {
                this.f628c = f;
                d();
            }
        }

        public void setScaleX(float f) {
            if (f != this.f) {
                this.f = f;
                d();
            }
        }

        public void setScaleY(float f) {
            if (f != this.g) {
                this.g = f;
                d();
            }
        }

        public void setTranslateX(float f) {
            if (f != this.h) {
                this.h = f;
                d();
            }
        }

        public void setTranslateY(float f) {
            if (f != this.i) {
                this.i = f;
                d();
            }
        }
    }

    private static abstract class e {
        private e() {
        }

        public boolean a() {
            return false;
        }

        public boolean b(int[] iArr) {
            return false;
        }
    }

    private static abstract class f extends e {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        protected c.b[] f630a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        String f631b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f632c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        int f633d;

        public f() {
            super();
            this.f630a = null;
            this.f632c = 0;
        }

        public f(f fVar) {
            super();
            this.f630a = null;
            this.f632c = 0;
            this.f631b = fVar.f631b;
            this.f633d = fVar.f633d;
            this.f630a = a.g.d.c.f(fVar.f630a);
        }

        public boolean c() {
            return false;
        }

        public void d(Path path) {
            path.reset();
            c.b[] bVarArr = this.f630a;
            if (bVarArr != null) {
                c.b.e(bVarArr, path);
            }
        }

        public c.b[] getPathData() {
            return this.f630a;
        }

        public String getPathName() {
            return this.f631b;
        }

        public void setPathData(c.b[] bVarArr) {
            if (a.g.d.c.b(this.f630a, bVarArr)) {
                a.g.d.c.j(this.f630a, bVarArr);
            } else {
                this.f630a = a.g.d.c.f(bVarArr);
            }
        }
    }

    private static class g {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private static final Matrix f634a = new Matrix();

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Path f635b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Path f636c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private final Matrix f637d;
        Paint e;
        Paint f;
        private PathMeasure g;
        private int h;
        final d i;
        float j;
        float k;
        float l;
        float m;
        int n;
        String o;
        Boolean p;
        final a.d.a<String, Object> q;

        public g() {
            this.f637d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            this.q = new a.d.a<>();
            this.i = new d();
            this.f635b = new Path();
            this.f636c = new Path();
        }

        public g(g gVar) {
            this.f637d = new Matrix();
            this.j = 0.0f;
            this.k = 0.0f;
            this.l = 0.0f;
            this.m = 0.0f;
            this.n = 255;
            this.o = null;
            this.p = null;
            a.d.a<String, Object> aVar = new a.d.a<>();
            this.q = aVar;
            this.i = new d(gVar.i, aVar);
            this.f635b = new Path(gVar.f635b);
            this.f636c = new Path(gVar.f636c);
            this.j = gVar.j;
            this.k = gVar.k;
            this.l = gVar.l;
            this.m = gVar.m;
            this.h = gVar.h;
            this.n = gVar.n;
            this.o = gVar.o;
            String str = gVar.o;
            if (str != null) {
                aVar.put(str, this);
            }
            this.p = gVar.p;
        }

        private static float a(float f, float f2, float f3, float f4) {
            return (f * f4) - (f2 * f3);
        }

        private void c(d dVar, Matrix matrix, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            dVar.f626a.set(matrix);
            dVar.f626a.preConcat(dVar.j);
            canvas.save();
            for (int i3 = 0; i3 < dVar.f627b.size(); i3++) {
                e eVar = dVar.f627b.get(i3);
                if (eVar instanceof d) {
                    c((d) eVar, dVar.f626a, canvas, i, i2, colorFilter);
                } else if (eVar instanceof f) {
                    d(dVar, (f) eVar, canvas, i, i2, colorFilter);
                }
            }
            canvas.restore();
        }

        private void d(d dVar, f fVar, Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            float f = i / this.l;
            float f2 = i2 / this.m;
            float fMin = Math.min(f, f2);
            Matrix matrix = dVar.f626a;
            this.f637d.set(matrix);
            this.f637d.postScale(f, f2);
            float fE = e(matrix);
            if (fE == 0.0f) {
                return;
            }
            fVar.d(this.f635b);
            Path path = this.f635b;
            this.f636c.reset();
            if (fVar.c()) {
                this.f636c.setFillType(fVar.f632c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                this.f636c.addPath(path, this.f637d);
                canvas.clipPath(this.f636c);
                return;
            }
            c cVar = (c) fVar;
            float f3 = cVar.k;
            if (f3 != 0.0f || cVar.l != 1.0f) {
                float f4 = cVar.m;
                float f5 = (f3 + f4) % 1.0f;
                float f6 = (cVar.l + f4) % 1.0f;
                if (this.g == null) {
                    this.g = new PathMeasure();
                }
                this.g.setPath(this.f635b, false);
                float length = this.g.getLength();
                float f7 = f5 * length;
                float f8 = f6 * length;
                path.reset();
                if (f7 > f8) {
                    this.g.getSegment(f7, length, path, true);
                    this.g.getSegment(0.0f, f8, path, true);
                } else {
                    this.g.getSegment(f7, f8, path, true);
                }
                path.rLineTo(0.0f, 0.0f);
            }
            this.f636c.addPath(path, this.f637d);
            if (cVar.h.l()) {
                androidx.core.content.c.b bVar = cVar.h;
                if (this.f == null) {
                    Paint paint = new Paint(1);
                    this.f = paint;
                    paint.setStyle(Paint.Style.FILL);
                }
                Paint paint2 = this.f;
                if (bVar.h()) {
                    Shader shaderF = bVar.f();
                    shaderF.setLocalMatrix(this.f637d);
                    paint2.setShader(shaderF);
                    paint2.setAlpha(Math.round(cVar.j * 255.0f));
                } else {
                    paint2.setShader(null);
                    paint2.setAlpha(255);
                    paint2.setColor(i.a(bVar.e(), cVar.j));
                }
                paint2.setColorFilter(colorFilter);
                this.f636c.setFillType(cVar.f632c == 0 ? Path.FillType.WINDING : Path.FillType.EVEN_ODD);
                canvas.drawPath(this.f636c, paint2);
            }
            if (cVar.f.l()) {
                androidx.core.content.c.b bVar2 = cVar.f;
                if (this.e == null) {
                    Paint paint3 = new Paint(1);
                    this.e = paint3;
                    paint3.setStyle(Paint.Style.STROKE);
                }
                Paint paint4 = this.e;
                Paint.Join join = cVar.o;
                if (join != null) {
                    paint4.setStrokeJoin(join);
                }
                Paint.Cap cap = cVar.n;
                if (cap != null) {
                    paint4.setStrokeCap(cap);
                }
                paint4.setStrokeMiter(cVar.p);
                if (bVar2.h()) {
                    Shader shaderF2 = bVar2.f();
                    shaderF2.setLocalMatrix(this.f637d);
                    paint4.setShader(shaderF2);
                    paint4.setAlpha(Math.round(cVar.i * 255.0f));
                } else {
                    paint4.setShader(null);
                    paint4.setAlpha(255);
                    paint4.setColor(i.a(bVar2.e(), cVar.i));
                }
                paint4.setColorFilter(colorFilter);
                paint4.setStrokeWidth(cVar.g * fMin * fE);
                canvas.drawPath(this.f636c, paint4);
            }
        }

        private float e(Matrix matrix) {
            float[] fArr = {0.0f, 1.0f, 1.0f, 0.0f};
            matrix.mapVectors(fArr);
            float fHypot = (float) Math.hypot(fArr[0], fArr[1]);
            float fHypot2 = (float) Math.hypot(fArr[2], fArr[3]);
            float fA = a(fArr[0], fArr[1], fArr[2], fArr[3]);
            float fMax = Math.max(fHypot, fHypot2);
            if (fMax > 0.0f) {
                return Math.abs(fA) / fMax;
            }
            return 0.0f;
        }

        public void b(Canvas canvas, int i, int i2, ColorFilter colorFilter) {
            c(this.i, f634a, canvas, i, i2, colorFilter);
        }

        public boolean f() {
            if (this.p == null) {
                this.p = Boolean.valueOf(this.i.a());
            }
            return this.p.booleanValue();
        }

        public boolean g(int[] iArr) {
            return this.i.b(iArr);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.n;
        }

        public void setAlpha(float f) {
            setRootAlpha((int) (f * 255.0f));
        }

        public void setRootAlpha(int i) {
            this.n = i;
        }
    }

    private static class h extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f638a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        g f639b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        ColorStateList f640c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        PorterDuff.Mode f641d;
        boolean e;
        Bitmap f;
        ColorStateList g;
        PorterDuff.Mode h;
        int i;
        boolean j;
        boolean k;
        Paint l;

        public h() {
            this.f640c = null;
            this.f641d = i.f624c;
            this.f639b = new g();
        }

        public h(h hVar) {
            this.f640c = null;
            this.f641d = i.f624c;
            if (hVar != null) {
                this.f638a = hVar.f638a;
                g gVar = new g(hVar.f639b);
                this.f639b = gVar;
                Paint paint = hVar.f639b.f;
                if (paint != null) {
                    gVar.f = new Paint(paint);
                }
                Paint paint2 = hVar.f639b.e;
                if (paint2 != null) {
                    this.f639b.e = new Paint(paint2);
                }
                this.f640c = hVar.f640c;
                this.f641d = hVar.f641d;
                this.e = hVar.e;
            }
        }

        public boolean a(int i, int i2) {
            return i == this.f.getWidth() && i2 == this.f.getHeight();
        }

        public boolean b() {
            return !this.k && this.g == this.f640c && this.h == this.f641d && this.j == this.e && this.i == this.f639b.getRootAlpha();
        }

        public void c(int i, int i2) {
            if (this.f == null || !a(i, i2)) {
                this.f = Bitmap.createBitmap(i, i2, Bitmap.Config.ARGB_8888);
                this.k = true;
            }
        }

        public void d(Canvas canvas, ColorFilter colorFilter, Rect rect) {
            canvas.drawBitmap(this.f, (Rect) null, rect, e(colorFilter));
        }

        public Paint e(ColorFilter colorFilter) {
            if (!f() && colorFilter == null) {
                return null;
            }
            if (this.l == null) {
                Paint paint = new Paint();
                this.l = paint;
                paint.setFilterBitmap(true);
            }
            this.l.setAlpha(this.f639b.getRootAlpha());
            this.l.setColorFilter(colorFilter);
            return this.l;
        }

        public boolean f() {
            return this.f639b.getRootAlpha() < 255;
        }

        public boolean g() {
            return this.f639b.f();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f638a;
        }

        public boolean h(int[] iArr) {
            boolean zG = this.f639b.g(iArr);
            this.k |= zG;
            return zG;
        }

        public void i() {
            this.g = this.f640c;
            this.h = this.f641d;
            this.i = this.f639b.getRootAlpha();
            this.j = this.e;
            this.k = false;
        }

        public void j(int i, int i2) {
            this.f.eraseColor(0);
            this.f639b.b(new Canvas(this.f), i, i2, null);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new i(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            return new i(this);
        }
    }

    /* JADX INFO: renamed from: a.o.a.a.i$i, reason: collision with other inner class name */
    private static class C0027i extends Drawable.ConstantState {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Drawable.ConstantState f642a;

        public C0027i(Drawable.ConstantState constantState) {
            this.f642a = constantState;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.f642a.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.f642a.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            i iVar = new i();
            iVar.f623b = (VectorDrawable) this.f642a.newDrawable();
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources) {
            i iVar = new i();
            iVar.f623b = (VectorDrawable) this.f642a.newDrawable(resources);
            return iVar;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources resources, Resources.Theme theme) {
            i iVar = new i();
            iVar.f623b = (VectorDrawable) this.f642a.newDrawable(resources, theme);
            return iVar;
        }
    }

    i() {
        this.h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f625d = new h();
    }

    i(h hVar) {
        this.h = true;
        this.j = new float[9];
        this.k = new Matrix();
        this.l = new Rect();
        this.f625d = hVar;
        this.e = updateTintFilter(this.e, hVar.f640c, hVar.f641d);
    }

    static int a(int i, float f2) {
        return (i & 16777215) | (((int) (Color.alpha(i) * f2)) << 24);
    }

    public static i b(Resources resources, int i, Resources.Theme theme) {
        int next;
        if (Build.VERSION.SDK_INT >= 24) {
            i iVar = new i();
            Drawable drawableA = androidx.core.content.c.f.a(resources, i, theme);
            iVar.f623b = drawableA;
            iVar.i = new C0027i(drawableA.getConstantState());
            return iVar;
        }
        try {
            XmlResourceParser xml = resources.getXml(i);
            AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
            do {
                next = xml.next();
                if (next == 2) {
                    break;
                }
            } while (next != 1);
            if (next == 2) {
                return c(resources, xml, attributeSetAsAttributeSet, theme);
            }
            throw new XmlPullParserException("No start tag found");
        } catch (IOException | XmlPullParserException e2) {
            Log.e("VectorDrawableCompat", "parser error", e2);
            return null;
        }
    }

    public static i c(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        i iVar = new i();
        iVar.inflate(resources, xmlPullParser, attributeSet, theme);
        return iVar;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private void e(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        int i;
        int i2;
        f fVar;
        h hVar = this.f625d;
        g gVar = hVar.f639b;
        ArrayDeque arrayDeque = new ArrayDeque();
        arrayDeque.push(gVar.i);
        int eventType = xmlPullParser.getEventType();
        int depth = xmlPullParser.getDepth() + 1;
        boolean z = true;
        while (eventType != 1 && (xmlPullParser.getDepth() >= depth || eventType != 3)) {
            if (eventType == 2) {
                String name = xmlPullParser.getName();
                d dVar = (d) arrayDeque.peek();
                if ("path".equals(name)) {
                    c cVar = new c();
                    cVar.g(resources, attributeSet, theme, xmlPullParser);
                    dVar.f627b.add(cVar);
                    if (cVar.getPathName() != null) {
                        gVar.q.put(cVar.getPathName(), cVar);
                    }
                    z = false;
                    fVar = cVar;
                } else if ("clip-path".equals(name)) {
                    b bVar = new b();
                    bVar.e(resources, attributeSet, theme, xmlPullParser);
                    dVar.f627b.add(bVar);
                    String pathName = bVar.getPathName();
                    fVar = bVar;
                    if (pathName != null) {
                        gVar.q.put(bVar.getPathName(), bVar);
                        fVar = bVar;
                    }
                } else if ("group".equals(name)) {
                    d dVar2 = new d();
                    dVar2.c(resources, attributeSet, theme, xmlPullParser);
                    dVar.f627b.add(dVar2);
                    arrayDeque.push(dVar2);
                    if (dVar2.getGroupName() != null) {
                        gVar.q.put(dVar2.getGroupName(), dVar2);
                    }
                    i = hVar.f638a;
                    i2 = dVar2.k;
                    hVar.f638a = i2 | i;
                }
                i = hVar.f638a;
                i2 = fVar.f633d;
                hVar.f638a = i2 | i;
            } else if (eventType == 3 && "group".equals(xmlPullParser.getName())) {
                arrayDeque.pop();
            }
            eventType = xmlPullParser.next();
        }
        if (z) {
            throw new XmlPullParserException("no path defined");
        }
    }

    private boolean f() {
        return Build.VERSION.SDK_INT >= 17 && isAutoMirrored() && androidx.core.graphics.drawable.a.f(this) == 1;
    }

    private static PorterDuff.Mode g(int i, PorterDuff.Mode mode) {
        if (i == 3) {
            return PorterDuff.Mode.SRC_OVER;
        }
        if (i == 5) {
            return PorterDuff.Mode.SRC_IN;
        }
        if (i == 9) {
            return PorterDuff.Mode.SRC_ATOP;
        }
        switch (i) {
            case 14:
                return PorterDuff.Mode.MULTIPLY;
            case 15:
                return PorterDuff.Mode.SCREEN;
            case 16:
                return PorterDuff.Mode.ADD;
            default:
                return mode;
        }
    }

    private void i(TypedArray typedArray, XmlPullParser xmlPullParser, Resources.Theme theme) throws XmlPullParserException {
        h hVar = this.f625d;
        g gVar = hVar.f639b;
        hVar.f641d = g(androidx.core.content.c.g.g(typedArray, xmlPullParser, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList colorStateListC = androidx.core.content.c.g.c(typedArray, xmlPullParser, theme, "tint", 1);
        if (colorStateListC != null) {
            hVar.f640c = colorStateListC;
        }
        hVar.e = androidx.core.content.c.g.a(typedArray, xmlPullParser, "autoMirrored", 5, hVar.e);
        gVar.l = androidx.core.content.c.g.f(typedArray, xmlPullParser, "viewportWidth", 7, gVar.l);
        float f2 = androidx.core.content.c.g.f(typedArray, xmlPullParser, "viewportHeight", 8, gVar.m);
        gVar.m = f2;
        if (gVar.l <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
        }
        if (f2 <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
        }
        gVar.j = typedArray.getDimension(3, gVar.j);
        float dimension = typedArray.getDimension(2, gVar.k);
        gVar.k = dimension;
        if (gVar.j <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires width > 0");
        }
        if (dimension <= 0.0f) {
            throw new XmlPullParserException(typedArray.getPositionDescription() + "<vector> tag requires height > 0");
        }
        gVar.setAlpha(androidx.core.content.c.g.f(typedArray, xmlPullParser, "alpha", 4, gVar.getAlpha()));
        String string = typedArray.getString(0);
        if (string != null) {
            gVar.o = string;
            gVar.q.put(string, gVar);
        }
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme theme) {
        super.applyTheme(theme);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable drawable = this.f623b;
        if (drawable == null) {
            return false;
        }
        androidx.core.graphics.drawable.a.b(drawable);
        return false;
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    Object d(String str) {
        return this.f625d.f639b.q.get(str);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.draw(canvas);
            return;
        }
        copyBounds(this.l);
        if (this.l.width() <= 0 || this.l.height() <= 0) {
            return;
        }
        ColorFilter colorFilter = this.f;
        if (colorFilter == null) {
            colorFilter = this.e;
        }
        canvas.getMatrix(this.k);
        this.k.getValues(this.j);
        float fAbs = Math.abs(this.j[0]);
        float fAbs2 = Math.abs(this.j[4]);
        float fAbs3 = Math.abs(this.j[1]);
        float fAbs4 = Math.abs(this.j[3]);
        if (fAbs3 != 0.0f || fAbs4 != 0.0f) {
            fAbs = 1.0f;
            fAbs2 = 1.0f;
        }
        int iMin = Math.min(2048, (int) (this.l.width() * fAbs));
        int iMin2 = Math.min(2048, (int) (this.l.height() * fAbs2));
        if (iMin <= 0 || iMin2 <= 0) {
            return;
        }
        int iSave = canvas.save();
        Rect rect = this.l;
        canvas.translate(rect.left, rect.top);
        if (f()) {
            canvas.translate(this.l.width(), 0.0f);
            canvas.scale(-1.0f, 1.0f);
        }
        this.l.offsetTo(0, 0);
        this.f625d.c(iMin, iMin2);
        if (!this.h) {
            this.f625d.j(iMin, iMin2);
        } else if (!this.f625d.b()) {
            this.f625d.j(iMin, iMin2);
            this.f625d.i();
        }
        this.f625d.d(canvas, colorFilter, this.l);
        canvas.restoreToCount(iSave);
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable drawable = this.f623b;
        return drawable != null ? androidx.core.graphics.drawable.a.d(drawable) : this.f625d.f639b.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.f623b;
        return drawable != null ? drawable.getChangingConfigurations() : super.getChangingConfigurations() | this.f625d.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable drawable = this.f623b;
        return drawable != null ? androidx.core.graphics.drawable.a.e(drawable) : this.f;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        Drawable drawable = this.f623b;
        if (drawable != null && Build.VERSION.SDK_INT >= 24) {
            return new C0027i(drawable.getConstantState());
        }
        this.f625d.f638a = getChangingConfigurations();
        return this.f625d;
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.f623b;
        return drawable != null ? drawable.getIntrinsicHeight() : (int) this.f625d.f639b.k;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.f623b;
        return drawable != null ? drawable.getIntrinsicWidth() : (int) this.f625d.f639b.j;
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return -3;
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect rect) {
        return super.getPadding(rect);
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    void h(boolean z) {
        this.h = z;
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet) throws XmlPullParserException, IOException {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.inflate(resources, xmlPullParser, attributeSet);
        } else {
            inflate(resources, xmlPullParser, attributeSet, null);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources resources, XmlPullParser xmlPullParser, AttributeSet attributeSet, Resources.Theme theme) throws XmlPullParserException, IOException {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.g(drawable, resources, xmlPullParser, attributeSet, theme);
            return;
        }
        h hVar = this.f625d;
        hVar.f639b = new g();
        TypedArray typedArrayK = androidx.core.content.c.g.k(resources, theme, attributeSet, a.o.a.a.a.f607a);
        i(typedArrayK, xmlPullParser, theme);
        typedArrayK.recycle();
        hVar.f638a = getChangingConfigurations();
        hVar.k = true;
        e(resources, xmlPullParser, attributeSet, theme);
        this.e = updateTintFilter(this.e, hVar.f640c, hVar.f641d);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.invalidateSelf();
        } else {
            super.invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.f623b;
        return drawable != null ? androidx.core.graphics.drawable.a.h(drawable) : this.f625d.e;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        h hVar;
        ColorStateList colorStateList;
        Drawable drawable = this.f623b;
        return drawable != null ? drawable.isStateful() : super.isStateful() || ((hVar = this.f625d) != null && (hVar.g() || ((colorStateList = this.f625d.f640c) != null && colorStateList.isStateful())));
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.mutate();
            return this;
        }
        if (!this.g && super.mutate() == this) {
            this.f625d = new h(this.f625d);
            this.g = true;
        }
        return this;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        PorterDuff.Mode mode;
        Drawable drawable = this.f623b;
        if (drawable != null) {
            return drawable.setState(iArr);
        }
        boolean z = false;
        h hVar = this.f625d;
        ColorStateList colorStateList = hVar.f640c;
        if (colorStateList != null && (mode = hVar.f641d) != null) {
            this.e = updateTintFilter(this.e, colorStateList, mode);
            invalidateSelf();
            z = true;
        }
        if (!hVar.g() || !hVar.h(iArr)) {
            return z;
        }
        invalidateSelf();
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.scheduleSelf(runnable, j);
        } else {
            super.scheduleSelf(runnable, j);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.setAlpha(i);
        } else if (this.f625d.f639b.getRootAlpha() != i) {
            this.f625d.f639b.setRootAlpha(i);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.j(drawable, z);
        } else {
            this.f625d.e = z;
        }
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int i) {
        super.setChangingConfigurations(i);
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int i, PorterDuff.Mode mode) {
        super.setColorFilter(i, mode);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        } else {
            this.f = colorFilter;
            invalidateSelf();
        }
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean z) {
        super.setFilterBitmap(z);
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float f2, float f3) {
        super.setHotspot(f2, f3);
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int i, int i2, int i3, int i4) {
        super.setHotspotBounds(i, i2, i3, i4);
    }

    @Override // a.o.a.a.h, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] iArr) {
        return super.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTint(int i) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.n(drawable, i);
        } else {
            setTintList(ColorStateList.valueOf(i));
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.o(drawable, colorStateList);
            return;
        }
        h hVar = this.f625d;
        if (hVar.f640c != colorStateList) {
            hVar.f640c = colorStateList;
            this.e = updateTintFilter(this.e, colorStateList, hVar.f641d);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable, androidx.core.graphics.drawable.b
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            androidx.core.graphics.drawable.a.p(drawable, mode);
            return;
        }
        h hVar = this.f625d;
        if (hVar.f641d != mode) {
            hVar.f641d = mode;
            this.e = updateTintFilter(this.e, hVar.f640c, mode);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable = this.f623b;
        return drawable != null ? drawable.setVisible(z, z2) : super.setVisible(z, z2);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable runnable) {
        Drawable drawable = this.f623b;
        if (drawable != null) {
            drawable.unscheduleSelf(runnable);
        } else {
            super.unscheduleSelf(runnable);
        }
    }

    PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter porterDuffColorFilter, ColorStateList colorStateList, PorterDuff.Mode mode) {
        if (colorStateList == null || mode == null) {
            return null;
        }
        return new PorterDuffColorFilter(colorStateList.getColorForState(getState(), 0), mode);
    }
}
