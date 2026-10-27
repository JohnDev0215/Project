package com.airbnb.lottie.model.layer;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import com.airbnb.lottie.model.DocumentData;
import com.airbnb.lottie.model.content.j;
import com.airbnb.lottie.model.i.k;
import com.airbnb.lottie.r.c.n;
import com.airbnb.lottie.r.c.p;
import com.airbnb.lottie.u.h;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: TextLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends com.airbnb.lottie.model.layer.a {
    private final Paint A;
    private final Paint B;
    private final Map<com.airbnb.lottie.model.c, List<com.airbnb.lottie.r.b.d>> C;
    private final b.d.d<String> D;
    private final n E;
    private final com.airbnb.lottie.f F;
    private final com.airbnb.lottie.d G;
    private com.airbnb.lottie.r.c.a<Integer, Integer> H;
    private com.airbnb.lottie.r.c.a<Integer, Integer> I;
    private com.airbnb.lottie.r.c.a<Integer, Integer> J;
    private com.airbnb.lottie.r.c.a<Integer, Integer> K;
    private com.airbnb.lottie.r.c.a<Float, Float> L;
    private com.airbnb.lottie.r.c.a<Float, Float> M;
    private com.airbnb.lottie.r.c.a<Float, Float> N;
    private com.airbnb.lottie.r.c.a<Float, Float> O;
    private com.airbnb.lottie.r.c.a<Float, Float> P;
    private com.airbnb.lottie.r.c.a<Float, Float> Q;
    private final StringBuilder x;
    private final RectF y;
    private final Matrix z;

    /* JADX INFO: compiled from: TextLayer.java */
    class a extends Paint {
        a(int i) {
            super(i);
            setStyle(Paint.Style.FILL);
        }
    }

    /* JADX INFO: compiled from: TextLayer.java */
    class b extends Paint {
        b(int i) {
            super(i);
            setStyle(Paint.Style.STROKE);
        }
    }

    /* JADX INFO: compiled from: TextLayer.java */
    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f2642a;

        static {
            int[] iArr = new int[DocumentData.Justification.values().length];
            f2642a = iArr;
            try {
                iArr[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f2642a[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f2642a[DocumentData.Justification.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    g(com.airbnb.lottie.f fVar, Layer layer) {
        com.airbnb.lottie.model.i.b bVar;
        com.airbnb.lottie.model.i.b bVar2;
        com.airbnb.lottie.model.i.a aVar;
        com.airbnb.lottie.model.i.a aVar2;
        super(fVar, layer);
        this.x = new StringBuilder(2);
        this.y = new RectF();
        this.z = new Matrix();
        this.A = new a(1);
        this.B = new b(1);
        this.C = new HashMap();
        this.D = new b.d.d<>();
        this.F = fVar;
        this.G = layer.a();
        n nVarA = layer.q().a();
        this.E = nVarA;
        nVarA.a(this);
        k(nVarA);
        k kVarR = layer.r();
        if (kVarR != null && (aVar2 = kVarR.f2614a) != null) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = aVar2.a();
            this.H = aVarA;
            aVarA.a(this);
            k(this.H);
        }
        if (kVarR != null && (aVar = kVarR.f2615b) != null) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = aVar.a();
            this.J = aVarA2;
            aVarA2.a(this);
            k(this.J);
        }
        if (kVarR != null && (bVar2 = kVarR.f2616c) != null) {
            com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = bVar2.a();
            this.L = aVarA3;
            aVarA3.a(this);
            k(this.L);
        }
        if (kVarR == null || (bVar = kVarR.f2617d) == null) {
            return;
        }
        com.airbnb.lottie.r.c.a<Float, Float> aVarA4 = bVar.a();
        this.N = aVarA4;
        aVarA4.a(this);
        k(this.N);
    }

    private void L(DocumentData.Justification justification, Canvas canvas, float f) {
        int i = c.f2642a[justification.ordinal()];
        if (i == 2) {
            canvas.translate(-f, 0.0f);
        } else {
            if (i != 3) {
                return;
            }
            canvas.translate((-f) / 2.0f, 0.0f);
        }
    }

    private String M(String str, int i) {
        int iCodePointAt = str.codePointAt(i);
        int iCharCount = Character.charCount(iCodePointAt) + i;
        while (iCharCount < str.length()) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (!Y(iCodePointAt2)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt2);
            iCodePointAt = (iCodePointAt * 31) + iCodePointAt2;
        }
        long j = iCodePointAt;
        if (this.D.d(j)) {
            return this.D.f(j);
        }
        this.x.setLength(0);
        while (i < iCharCount) {
            int iCodePointAt3 = str.codePointAt(i);
            this.x.appendCodePoint(iCodePointAt3);
            i += Character.charCount(iCodePointAt3);
        }
        String string = this.x.toString();
        this.D.j(j, string);
        return string;
    }

    private void N(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    private void O(com.airbnb.lottie.model.c cVar, Matrix matrix, float f, DocumentData documentData, Canvas canvas) {
        List<com.airbnb.lottie.r.b.d> listV = V(cVar);
        for (int i = 0; i < listV.size(); i++) {
            Path pathH = listV.get(i).h();
            pathH.computeBounds(this.y, false);
            this.z.set(matrix);
            this.z.preTranslate(0.0f, (-documentData.g) * h.e());
            this.z.preScale(f, f);
            pathH.transform(this.z);
            if (documentData.k) {
                R(pathH, this.A, canvas);
                R(pathH, this.B, canvas);
            } else {
                R(pathH, this.B, canvas);
                R(pathH, this.A, canvas);
            }
        }
    }

    private void P(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.k) {
            N(str, this.A, canvas);
            N(str, this.B, canvas);
        } else {
            N(str, this.B, canvas);
            N(str, this.A, canvas);
        }
    }

    private void Q(String str, DocumentData documentData, Canvas canvas, float f) {
        float fFloatValue;
        int length = 0;
        while (length < str.length()) {
            String strM = M(str, length);
            length += strM.length();
            P(strM, documentData, canvas);
            float fMeasureText = this.A.measureText(strM, 0, 1);
            float f2 = documentData.f2514e / 10.0f;
            com.airbnb.lottie.r.c.a<Float, Float> aVar = this.O;
            if (aVar != null) {
                fFloatValue = aVar.h().floatValue();
            } else {
                com.airbnb.lottie.r.c.a<Float, Float> aVar2 = this.N;
                if (aVar2 != null) {
                    fFloatValue = aVar2.h().floatValue();
                } else {
                    canvas.translate(fMeasureText + (f2 * f), 0.0f);
                }
            }
            f2 += fFloatValue;
            canvas.translate(fMeasureText + (f2 * f), 0.0f);
        }
    }

    private void R(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void S(String str, DocumentData documentData, Matrix matrix, com.airbnb.lottie.model.b bVar, Canvas canvas, float f, float f2) {
        float fFloatValue;
        for (int i = 0; i < str.length(); i++) {
            com.airbnb.lottie.model.c cVarE = this.G.c().e(com.airbnb.lottie.model.c.c(str.charAt(i), bVar.a(), bVar.c()));
            if (cVarE != null) {
                O(cVarE, matrix, f2, documentData, canvas);
                float fB = ((float) cVarE.b()) * f2 * h.e() * f;
                float f3 = documentData.f2514e / 10.0f;
                com.airbnb.lottie.r.c.a<Float, Float> aVar = this.O;
                if (aVar != null) {
                    fFloatValue = aVar.h().floatValue();
                } else {
                    com.airbnb.lottie.r.c.a<Float, Float> aVar2 = this.N;
                    if (aVar2 != null) {
                        fFloatValue = aVar2.h().floatValue();
                    }
                    canvas.translate(fB + (f3 * f), 0.0f);
                }
                f3 += fFloatValue;
                canvas.translate(fB + (f3 * f), 0.0f);
            }
        }
    }

    private void T(DocumentData documentData, Matrix matrix, com.airbnb.lottie.model.b bVar, Canvas canvas) {
        float fFloatValue;
        com.airbnb.lottie.r.c.a<Float, Float> aVar = this.Q;
        if (aVar != null) {
            fFloatValue = aVar.h().floatValue();
        } else {
            com.airbnb.lottie.r.c.a<Float, Float> aVar2 = this.P;
            fFloatValue = aVar2 != null ? aVar2.h().floatValue() : documentData.f2512c;
        }
        float f = fFloatValue / 100.0f;
        float fG = h.g(matrix);
        String str = documentData.f2510a;
        float fE = documentData.f * h.e();
        List<String> listX = X(str);
        int size = listX.size();
        for (int i = 0; i < size; i++) {
            String str2 = listX.get(i);
            float fW = W(str2, bVar, f, fG);
            canvas.save();
            L(documentData.f2513d, canvas, fW);
            canvas.translate(0.0f, (i * fE) - (((size - 1) * fE) / 2.0f));
            S(str2, documentData, matrix, bVar, canvas, fG, f);
            canvas.restore();
        }
    }

    private void U(DocumentData documentData, com.airbnb.lottie.model.b bVar, Matrix matrix, Canvas canvas) {
        float fFloatValue;
        float fG = h.g(matrix);
        Typeface typefaceD = this.F.D(bVar.a(), bVar.c());
        if (typefaceD == null) {
            return;
        }
        String str = documentData.f2510a;
        if (this.F.C() != null) {
            throw null;
        }
        this.A.setTypeface(typefaceD);
        com.airbnb.lottie.r.c.a<Float, Float> aVar = this.Q;
        if (aVar != null) {
            fFloatValue = aVar.h().floatValue();
        } else {
            com.airbnb.lottie.r.c.a<Float, Float> aVar2 = this.P;
            fFloatValue = aVar2 != null ? aVar2.h().floatValue() : documentData.f2512c;
        }
        this.A.setTextSize(fFloatValue * h.e());
        this.B.setTypeface(this.A.getTypeface());
        this.B.setTextSize(this.A.getTextSize());
        float fE = documentData.f * h.e();
        List<String> listX = X(str);
        int size = listX.size();
        for (int i = 0; i < size; i++) {
            String str2 = listX.get(i);
            L(documentData.f2513d, canvas, this.B.measureText(str2));
            canvas.translate(0.0f, (i * fE) - (((size - 1) * fE) / 2.0f));
            Q(str2, documentData, canvas, fG);
            canvas.setMatrix(matrix);
        }
    }

    private List<com.airbnb.lottie.r.b.d> V(com.airbnb.lottie.model.c cVar) {
        if (this.C.containsKey(cVar)) {
            return this.C.get(cVar);
        }
        List<j> listA = cVar.a();
        int size = listA.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new com.airbnb.lottie.r.b.d(this.F, this, listA.get(i)));
        }
        this.C.put(cVar, arrayList);
        return arrayList;
    }

    private float W(String str, com.airbnb.lottie.model.b bVar, float f, float f2) {
        float fB = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            com.airbnb.lottie.model.c cVarE = this.G.c().e(com.airbnb.lottie.model.c.c(str.charAt(i), bVar.a(), bVar.c()));
            if (cVarE != null) {
                fB = (float) (((double) fB) + (cVarE.b() * ((double) f) * ((double) h.e()) * ((double) f2)));
            }
        }
        return fB;
    }

    private List<String> X(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private boolean Y(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 19;
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.G.b().width(), this.G.b().height());
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == com.airbnb.lottie.k.f2496a) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar = this.I;
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
        if (t == com.airbnb.lottie.k.f2497b) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.K;
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
        if (t == com.airbnb.lottie.k.o) {
            com.airbnb.lottie.r.c.a<Float, Float> aVar3 = this.M;
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
        if (t == com.airbnb.lottie.k.p) {
            com.airbnb.lottie.r.c.a<Float, Float> aVar4 = this.O;
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
        if (t == com.airbnb.lottie.k.B) {
            com.airbnb.lottie.r.c.a<Float, Float> aVar5 = this.Q;
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

    @Override // com.airbnb.lottie.model.layer.a
    void v(Canvas canvas, Matrix matrix, int i) {
        canvas.save();
        if (!this.F.j0()) {
            canvas.setMatrix(matrix);
        }
        DocumentData documentDataH = this.E.h();
        com.airbnb.lottie.model.b bVar = this.G.g().get(documentDataH.f2511b);
        if (bVar == null) {
            canvas.restore();
            return;
        }
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar = this.I;
        if (aVar != null) {
            this.A.setColor(aVar.h().intValue());
        } else {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.H;
            if (aVar2 != null) {
                this.A.setColor(aVar2.h().intValue());
            } else {
                this.A.setColor(documentDataH.h);
            }
        }
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar3 = this.K;
        if (aVar3 != null) {
            this.B.setColor(aVar3.h().intValue());
        } else {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar4 = this.J;
            if (aVar4 != null) {
                this.B.setColor(aVar4.h().intValue());
            } else {
                this.B.setColor(documentDataH.i);
            }
        }
        int iIntValue = ((this.v.h() == null ? 100 : this.v.h().h().intValue()) * 255) / 100;
        this.A.setAlpha(iIntValue);
        this.B.setAlpha(iIntValue);
        com.airbnb.lottie.r.c.a<Float, Float> aVar5 = this.M;
        if (aVar5 != null) {
            this.B.setStrokeWidth(aVar5.h().floatValue());
        } else {
            com.airbnb.lottie.r.c.a<Float, Float> aVar6 = this.L;
            if (aVar6 != null) {
                this.B.setStrokeWidth(aVar6.h().floatValue());
            } else {
                this.B.setStrokeWidth(documentDataH.j * h.e() * h.g(matrix));
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
