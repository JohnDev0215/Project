package w1;

import a2.h;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.layer.Layer;
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import o1.l;
import r1.n;
import r1.p;
import u1.k;
import v1.j;

/* JADX INFO: compiled from: TextLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends w1.a {
    public final Paint A;
    public final Paint B;
    public final Map<t1.c, List<q1.d>> C;
    public final v.d<String> D;
    public final n E;
    public final o1.g F;
    public final o1.e G;
    public r1.a<Integer, Integer> H;
    public r1.a<Integer, Integer> I;
    public r1.a<Integer, Integer> J;
    public r1.a<Integer, Integer> K;
    public r1.a<Float, Float> L;
    public r1.a<Float, Float> M;
    public r1.a<Float, Float> N;
    public r1.a<Float, Float> O;
    public r1.a<Float, Float> P;
    public r1.a<Float, Float> Q;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final StringBuilder f8707x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final RectF f8708y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final Matrix f8709z;

    /* JADX INFO: compiled from: TextLayer.java */
    public class a extends Paint {
        public a(int i8) {
            super(i8);
            setStyle(Paint.Style.FILL);
        }
    }

    /* JADX INFO: compiled from: TextLayer.java */
    public class b extends Paint {
        public b(int i8) {
            super(i8);
            setStyle(Paint.Style.STROKE);
        }
    }

    /* JADX INFO: compiled from: TextLayer.java */
    public static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final /* synthetic */ int[] f8712a;

        static {
            int[] iArr = new int[DocumentData.Justification.values().length];
            f8712a = iArr;
            try {
                iArr[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f8712a[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f8712a[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public g(o1.g gVar, Layer layer) {
        u1.b bVar;
        u1.b bVar2;
        u1.a aVar;
        u1.a aVar2;
        super(gVar, layer);
        this.f8707x = new StringBuilder(2);
        this.f8708y = new RectF();
        this.f8709z = new Matrix();
        this.A = new a(1);
        this.B = new b(1);
        this.C = new HashMap();
        this.D = new v.d<>();
        this.F = gVar;
        this.G = layer.a();
        n nVarA = layer.q().a();
        this.E = nVarA;
        nVarA.a(this);
        k(nVarA);
        k kVarR = layer.r();
        if (kVarR != null && (aVar2 = kVarR.f8130a) != null) {
            r1.a<Integer, Integer> aVarA = aVar2.a();
            this.H = aVarA;
            aVarA.a(this);
            k(this.H);
        }
        if (kVarR != null && (aVar = kVarR.f8131b) != null) {
            r1.a<Integer, Integer> aVarA2 = aVar.a();
            this.J = aVarA2;
            aVarA2.a(this);
            k(this.J);
        }
        if (kVarR != null && (bVar2 = kVarR.f8132c) != null) {
            r1.a<Float, Float> aVarA3 = bVar2.a();
            this.L = aVarA3;
            aVarA3.a(this);
            k(this.L);
        }
        if (kVarR == null || (bVar = kVarR.f8133d) == null) {
            return;
        }
        r1.a<Float, Float> aVarA4 = bVar.a();
        this.N = aVarA4;
        aVarA4.a(this);
        k(this.N);
    }

    public final void L(DocumentData.Justification justification, Canvas canvas, float f8) {
        int i8 = c.f8712a[justification.ordinal()];
        if (i8 == 2) {
            canvas.translate(-f8, 0.0f);
        } else {
            if (i8 != 3) {
                return;
            }
            canvas.translate((-f8) / 2.0f, 0.0f);
        }
    }

    public final String M(String str, int i8) {
        int iCodePointAt = str.codePointAt(i8);
        int iCharCount = Character.charCount(iCodePointAt) + i8;
        while (iCharCount < str.length()) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (!Y(iCodePointAt2)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt2);
            iCodePointAt = (iCodePointAt * 31) + iCodePointAt2;
        }
        long j8 = iCodePointAt;
        if (this.D.d(j8)) {
            return this.D.f(j8);
        }
        this.f8707x.setLength(0);
        while (i8 < iCharCount) {
            int iCodePointAt3 = str.codePointAt(i8);
            this.f8707x.appendCodePoint(iCodePointAt3);
            i8 += Character.charCount(iCodePointAt3);
        }
        String string = this.f8707x.toString();
        this.D.k(j8, string);
        return string;
    }

    public final void N(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    public final void O(t1.c cVar, Matrix matrix, float f8, DocumentData documentData, Canvas canvas) {
        List<q1.d> listV = V(cVar);
        for (int i8 = 0; i8 < listV.size(); i8++) {
            Path pathA = listV.get(i8).a();
            pathA.computeBounds(this.f8708y, false);
            this.f8709z.set(matrix);
            this.f8709z.preTranslate(0.0f, (-documentData.f2452g) * h.e());
            this.f8709z.preScale(f8, f8);
            pathA.transform(this.f8709z);
            if (documentData.f2456k) {
                R(pathA, this.A, canvas);
                R(pathA, this.B, canvas);
            } else {
                R(pathA, this.B, canvas);
                R(pathA, this.A, canvas);
            }
        }
    }

    public final void P(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.f2456k) {
            N(str, this.A, canvas);
            N(str, this.B, canvas);
        } else {
            N(str, this.B, canvas);
            N(str, this.A, canvas);
        }
    }

    public final void Q(String str, DocumentData documentData, Canvas canvas, float f8) {
        float fFloatValue;
        int length = 0;
        while (length < str.length()) {
            String strM = M(str, length);
            length += strM.length();
            P(strM, documentData, canvas);
            float fMeasureText = this.A.measureText(strM, 0, 1);
            float f9 = documentData.f2450e / 10.0f;
            r1.a<Float, Float> aVar = this.O;
            if (aVar != null) {
                fFloatValue = aVar.h().floatValue();
            } else {
                r1.a<Float, Float> aVar2 = this.N;
                if (aVar2 != null) {
                    fFloatValue = aVar2.h().floatValue();
                } else {
                    canvas.translate(fMeasureText + (f9 * f8), 0.0f);
                }
            }
            f9 += fFloatValue;
            canvas.translate(fMeasureText + (f9 * f8), 0.0f);
        }
    }

    public final void R(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    public final void S(String str, DocumentData documentData, Matrix matrix, t1.b bVar, Canvas canvas, float f8, float f9) {
        float fFloatValue;
        for (int i8 = 0; i8 < str.length(); i8++) {
            t1.c cVarE = this.G.c().e(t1.c.c(str.charAt(i8), bVar.a(), bVar.c()));
            if (cVarE != null) {
                O(cVarE, matrix, f9, documentData, canvas);
                float fB = ((float) cVarE.b()) * f9 * h.e() * f8;
                float f10 = documentData.f2450e / 10.0f;
                r1.a<Float, Float> aVar = this.O;
                if (aVar != null) {
                    fFloatValue = aVar.h().floatValue();
                } else {
                    r1.a<Float, Float> aVar2 = this.N;
                    if (aVar2 != null) {
                        fFloatValue = aVar2.h().floatValue();
                    }
                    canvas.translate(fB + (f10 * f8), 0.0f);
                }
                f10 += fFloatValue;
                canvas.translate(fB + (f10 * f8), 0.0f);
            }
        }
    }

    public final void T(DocumentData documentData, Matrix matrix, t1.b bVar, Canvas canvas) {
        float fFloatValue;
        r1.a<Float, Float> aVar = this.Q;
        if (aVar != null) {
            fFloatValue = aVar.h().floatValue();
        } else {
            r1.a<Float, Float> aVar2 = this.P;
            fFloatValue = aVar2 != null ? aVar2.h().floatValue() : documentData.f2448c;
        }
        float f8 = fFloatValue / 100.0f;
        float fG = h.g(matrix);
        String str = documentData.f2446a;
        float fE = documentData.f2451f * h.e();
        List<String> listX = X(str);
        int size = listX.size();
        for (int i8 = 0; i8 < size; i8++) {
            String str2 = listX.get(i8);
            float fW = W(str2, bVar, f8, fG);
            canvas.save();
            L(documentData.f2449d, canvas, fW);
            canvas.translate(0.0f, (i8 * fE) - (((size - 1) * fE) / 2.0f));
            S(str2, documentData, matrix, bVar, canvas, fG, f8);
            canvas.restore();
        }
    }

    public final void U(DocumentData documentData, t1.b bVar, Matrix matrix, Canvas canvas) {
        float fFloatValue;
        float fG = h.g(matrix);
        Typeface typefaceD = this.F.D(bVar.a(), bVar.c());
        if (typefaceD == null) {
            return;
        }
        String str = documentData.f2446a;
        if (this.F.C() != null) {
            throw null;
        }
        this.A.setTypeface(typefaceD);
        r1.a<Float, Float> aVar = this.Q;
        if (aVar != null) {
            fFloatValue = aVar.h().floatValue();
        } else {
            r1.a<Float, Float> aVar2 = this.P;
            fFloatValue = aVar2 != null ? aVar2.h().floatValue() : documentData.f2448c;
        }
        this.A.setTextSize(fFloatValue * h.e());
        this.B.setTypeface(this.A.getTypeface());
        this.B.setTextSize(this.A.getTextSize());
        float fE = documentData.f2451f * h.e();
        List<String> listX = X(str);
        int size = listX.size();
        for (int i8 = 0; i8 < size; i8++) {
            String str2 = listX.get(i8);
            L(documentData.f2449d, canvas, this.B.measureText(str2));
            canvas.translate(0.0f, (i8 * fE) - (((size - 1) * fE) / 2.0f));
            Q(str2, documentData, canvas, fG);
            canvas.setMatrix(matrix);
        }
    }

    public final List<q1.d> V(t1.c cVar) {
        if (this.C.containsKey(cVar)) {
            return this.C.get(cVar);
        }
        List<j> listA = cVar.a();
        int size = listA.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i8 = 0; i8 < size; i8++) {
            arrayList.add(new q1.d(this.F, this, listA.get(i8)));
        }
        this.C.put(cVar, arrayList);
        return arrayList;
    }

    public final float W(String str, t1.b bVar, float f8, float f9) {
        float fB = 0.0f;
        for (int i8 = 0; i8 < str.length(); i8++) {
            t1.c cVarE = this.G.c().e(t1.c.c(str.charAt(i8), bVar.a(), bVar.c()));
            if (cVarE != null) {
                fB = (float) (((double) fB) + (cVarE.b() * ((double) f8) * ((double) h.e()) * ((double) f9)));
            }
        }
        return fB;
    }

    public final List<String> X(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll(BydAdbHelper.COMMAND_LINE_END, "\r").split("\r"));
    }

    public final boolean Y(int i8) {
        return Character.getType(i8) == 16 || Character.getType(i8) == 27 || Character.getType(i8) == 6 || Character.getType(i8) == 28 || Character.getType(i8) == 19;
    }

    @Override // w1.a, q1.e
    public void b(RectF rectF, Matrix matrix, boolean z7) {
        super.b(rectF, matrix, z7);
        rectF.set(0.0f, 0.0f, this.G.b().width(), this.G.b().height());
    }

    @Override // w1.a, t1.e
    public <T> void i(T t7, b2.c<T> cVar) {
        super.i(t7, cVar);
        if (t7 == l.f6828a) {
            r1.a<Integer, Integer> aVar = this.I;
            if (aVar != null) {
                E(aVar);
            }
            if (cVar == null) {
                this.I = null;
                return;
            }
            p pVar = new p(cVar);
            this.I = pVar;
            pVar.a(this);
            k(this.I);
            return;
        }
        if (t7 == l.f6829b) {
            r1.a<Integer, Integer> aVar2 = this.K;
            if (aVar2 != null) {
                E(aVar2);
            }
            if (cVar == null) {
                this.K = null;
                return;
            }
            p pVar2 = new p(cVar);
            this.K = pVar2;
            pVar2.a(this);
            k(this.K);
            return;
        }
        if (t7 == l.f6842o) {
            r1.a<Float, Float> aVar3 = this.M;
            if (aVar3 != null) {
                E(aVar3);
            }
            if (cVar == null) {
                this.M = null;
                return;
            }
            p pVar3 = new p(cVar);
            this.M = pVar3;
            pVar3.a(this);
            k(this.M);
            return;
        }
        if (t7 == l.f6843p) {
            r1.a<Float, Float> aVar4 = this.O;
            if (aVar4 != null) {
                E(aVar4);
            }
            if (cVar == null) {
                this.O = null;
                return;
            }
            p pVar4 = new p(cVar);
            this.O = pVar4;
            pVar4.a(this);
            k(this.O);
            return;
        }
        if (t7 == l.B) {
            r1.a<Float, Float> aVar5 = this.Q;
            if (aVar5 != null) {
                E(aVar5);
            }
            if (cVar == null) {
                this.Q = null;
                return;
            }
            p pVar5 = new p(cVar);
            this.Q = pVar5;
            pVar5.a(this);
            k(this.Q);
        }
    }

    @Override // w1.a
    public void v(Canvas canvas, Matrix matrix, int i8) {
        canvas.save();
        if (!this.F.j0()) {
            canvas.setMatrix(matrix);
        }
        DocumentData documentDataH = this.E.h();
        t1.b bVar = this.G.g().get(documentDataH.f2447b);
        if (bVar == null) {
            canvas.restore();
            return;
        }
        r1.a<Integer, Integer> aVar = this.I;
        if (aVar != null) {
            this.A.setColor(aVar.h().intValue());
        } else {
            r1.a<Integer, Integer> aVar2 = this.H;
            if (aVar2 != null) {
                this.A.setColor(aVar2.h().intValue());
            } else {
                this.A.setColor(documentDataH.f2453h);
            }
        }
        r1.a<Integer, Integer> aVar3 = this.K;
        if (aVar3 != null) {
            this.B.setColor(aVar3.h().intValue());
        } else {
            r1.a<Integer, Integer> aVar4 = this.J;
            if (aVar4 != null) {
                this.B.setColor(aVar4.h().intValue());
            } else {
                this.B.setColor(documentDataH.f2454i);
            }
        }
        int iIntValue = ((this.f8691v.h() == null ? 100 : this.f8691v.h().h().intValue()) * 255) / 100;
        this.A.setAlpha(iIntValue);
        this.B.setAlpha(iIntValue);
        r1.a<Float, Float> aVar5 = this.M;
        if (aVar5 != null) {
            this.B.setStrokeWidth(aVar5.h().floatValue());
        } else {
            r1.a<Float, Float> aVar6 = this.L;
            if (aVar6 != null) {
                this.B.setStrokeWidth(aVar6.h().floatValue());
            } else {
                this.B.setStrokeWidth(documentDataH.f2455j * h.e() * h.g(matrix));
            }
        }
        if (this.F.j0()) {
            T(documentDataH, matrix, bVar, canvas);
        } else {
            U(documentDataH, bVar, matrix, canvas);
        }
        canvas.restore();
    }
}
