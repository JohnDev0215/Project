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
import com.bydux.api.common.tools.cmd.BydAdbHelper;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: TextLayer.java */
/* JADX INFO: loaded from: classes.dex */
public class g extends com.airbnb.lottie.model.layer.a {
    private final Paint A;
    private final Map<com.airbnb.lottie.model.c, List<com.airbnb.lottie.r.b.d>> B;
    private final b.d.d<String> C;
    private final n D;
    private final com.airbnb.lottie.f E;
    private final com.airbnb.lottie.d F;
    private com.airbnb.lottie.r.c.a<Integer, Integer> G;
    private com.airbnb.lottie.r.c.a<Integer, Integer> H;
    private com.airbnb.lottie.r.c.a<Float, Float> I;
    private com.airbnb.lottie.r.c.a<Float, Float> J;
    private final StringBuilder w;
    private final RectF x;
    private final Matrix y;
    private final Paint z;

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
        static final /* synthetic */ int[] f3027a;

        static {
            int[] iArr = new int[DocumentData.Justification.values().length];
            f3027a = iArr;
            try {
                iArr[DocumentData.Justification.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f3027a[DocumentData.Justification.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f3027a[DocumentData.Justification.CENTER.ordinal()] = 3;
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
        this.w = new StringBuilder(2);
        this.x = new RectF();
        this.y = new Matrix();
        this.z = new a(1);
        this.A = new b(1);
        this.B = new HashMap();
        this.C = new b.d.d<>();
        this.E = fVar;
        this.F = layer.a();
        n nVarA = layer.q().a();
        this.D = nVarA;
        nVarA.a(this);
        j(nVarA);
        k kVarR = layer.r();
        if (kVarR != null && (aVar2 = kVarR.f2998a) != null) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = aVar2.a();
            this.G = aVarA;
            aVarA.a(this);
            j(this.G);
        }
        if (kVarR != null && (aVar = kVarR.f2999b) != null) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = aVar.a();
            this.H = aVarA2;
            aVarA2.a(this);
            j(this.H);
        }
        if (kVarR != null && (bVar2 = kVarR.f3000c) != null) {
            com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = bVar2.a();
            this.I = aVarA3;
            aVarA3.a(this);
            j(this.I);
        }
        if (kVarR == null || (bVar = kVarR.f3001d) == null) {
            return;
        }
        com.airbnb.lottie.r.c.a<Float, Float> aVarA4 = bVar.a();
        this.J = aVarA4;
        aVarA4.a(this);
        j(this.J);
    }

    private void J(DocumentData.Justification justification, Canvas canvas, float f) {
        int i = c.f3027a[justification.ordinal()];
        if (i == 2) {
            canvas.translate(-f, 0.0f);
        } else {
            if (i != 3) {
                return;
            }
            canvas.translate((-f) / 2.0f, 0.0f);
        }
    }

    private String K(String str, int i) {
        int iCodePointAt = str.codePointAt(i);
        int iCharCount = Character.charCount(iCodePointAt) + i;
        while (iCharCount < str.length()) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (!W(iCodePointAt2)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt2);
            iCodePointAt = (iCodePointAt * 31) + iCodePointAt2;
        }
        long j = iCodePointAt;
        if (this.C.d(j)) {
            return this.C.f(j);
        }
        this.w.setLength(0);
        while (i < iCharCount) {
            int iCodePointAt3 = str.codePointAt(i);
            this.w.appendCodePoint(iCodePointAt3);
            i += Character.charCount(iCodePointAt3);
        }
        String string = this.w.toString();
        this.C.j(j, string);
        return string;
    }

    private void L(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    private void M(com.airbnb.lottie.model.c cVar, Matrix matrix, float f, DocumentData documentData, Canvas canvas) {
        List<com.airbnb.lottie.r.b.d> listT = T(cVar);
        for (int i = 0; i < listT.size(); i++) {
            Path pathH = listT.get(i).h();
            pathH.computeBounds(this.x, false);
            this.y.set(matrix);
            this.y.preTranslate(0.0f, ((float) (-documentData.g)) * h.e());
            this.y.preScale(f, f);
            pathH.transform(this.y);
            if (documentData.k) {
                P(pathH, this.z, canvas);
                P(pathH, this.A, canvas);
            } else {
                P(pathH, this.A, canvas);
                P(pathH, this.z, canvas);
            }
        }
    }

    private void N(String str, DocumentData documentData, Canvas canvas) {
        if (documentData.k) {
            L(str, this.z, canvas);
            L(str, this.A, canvas);
        } else {
            L(str, this.A, canvas);
            L(str, this.z, canvas);
        }
    }

    private void O(String str, DocumentData documentData, Canvas canvas, float f) {
        int length = 0;
        while (length < str.length()) {
            String strK = K(str, length);
            length += strK.length();
            N(strK, documentData, canvas);
            float fMeasureText = this.z.measureText(strK, 0, 1);
            float fFloatValue = documentData.f2898e / 10.0f;
            com.airbnb.lottie.r.c.a<Float, Float> aVar = this.J;
            if (aVar != null) {
                fFloatValue += aVar.h().floatValue();
            }
            canvas.translate(fMeasureText + (fFloatValue * f), 0.0f);
        }
    }

    private void P(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void Q(String str, DocumentData documentData, Matrix matrix, com.airbnb.lottie.model.b bVar, Canvas canvas, float f, float f2) {
        for (int i = 0; i < str.length(); i++) {
            com.airbnb.lottie.model.c cVarE = this.F.c().e(com.airbnb.lottie.model.c.c(str.charAt(i), bVar.a(), bVar.c()));
            if (cVarE != null) {
                M(cVarE, matrix, f2, documentData, canvas);
                float fB = ((float) cVarE.b()) * f2 * h.e() * f;
                float fFloatValue = documentData.f2898e / 10.0f;
                com.airbnb.lottie.r.c.a<Float, Float> aVar = this.J;
                if (aVar != null) {
                    fFloatValue += aVar.h().floatValue();
                }
                canvas.translate(fB + (fFloatValue * f), 0.0f);
            }
        }
    }

    private void R(DocumentData documentData, Matrix matrix, com.airbnb.lottie.model.b bVar, Canvas canvas) {
        float f = ((float) documentData.f2896c) / 100.0f;
        float fG = h.g(matrix);
        String str = documentData.f2894a;
        float fE = ((float) documentData.f) * h.e();
        List<String> listV = V(str);
        int size = listV.size();
        for (int i = 0; i < size; i++) {
            String str2 = listV.get(i);
            float fU = U(str2, bVar, f, fG);
            canvas.save();
            J(documentData.f2897d, canvas, fU);
            canvas.translate(0.0f, (i * fE) - (((size - 1) * fE) / 2.0f));
            Q(str2, documentData, matrix, bVar, canvas, fG, f);
            canvas.restore();
        }
    }

    private void S(DocumentData documentData, com.airbnb.lottie.model.b bVar, Matrix matrix, Canvas canvas) {
        float fG = h.g(matrix);
        Typeface typefaceA = this.E.A(bVar.a(), bVar.c());
        if (typefaceA == null) {
            return;
        }
        String str = documentData.f2894a;
        if (this.E.z() != null) {
            throw null;
        }
        this.z.setTypeface(typefaceA);
        this.z.setTextSize((float) (documentData.f2896c * ((double) h.e())));
        this.A.setTypeface(this.z.getTypeface());
        this.A.setTextSize(this.z.getTextSize());
        float fE = ((float) documentData.f) * h.e();
        List<String> listV = V(str);
        int size = listV.size();
        for (int i = 0; i < size; i++) {
            String str2 = listV.get(i);
            J(documentData.f2897d, canvas, this.A.measureText(str2));
            canvas.translate(0.0f, (i * fE) - (((size - 1) * fE) / 2.0f));
            O(str2, documentData, canvas, fG);
            canvas.setMatrix(matrix);
        }
    }

    private List<com.airbnb.lottie.r.b.d> T(com.airbnb.lottie.model.c cVar) {
        if (this.B.containsKey(cVar)) {
            return this.B.get(cVar);
        }
        List<j> listA = cVar.a();
        int size = listA.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new com.airbnb.lottie.r.b.d(this.E, this, listA.get(i)));
        }
        this.B.put(cVar, arrayList);
        return arrayList;
    }

    private float U(String str, com.airbnb.lottie.model.b bVar, float f, float f2) {
        float fB = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            com.airbnb.lottie.model.c cVarE = this.F.c().e(com.airbnb.lottie.model.c.c(str.charAt(i), bVar.a(), bVar.c()));
            if (cVarE != null) {
                fB = (float) (((double) fB) + (cVarE.b() * ((double) f) * ((double) h.e()) * ((double) f2)));
            }
        }
        return fB;
    }

    private List<String> V(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll(BydAdbHelper.COMMAND_LINE_END, "\r").split("\r"));
    }

    private boolean W(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 19;
    }

    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.F.b().width(), this.F.b().height());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.model.layer.a, com.airbnb.lottie.model.e
    public <T> void f(T t, com.airbnb.lottie.v.c<T> cVar) {
        super.f(t, cVar);
        if (t == com.airbnb.lottie.k.f2880a) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar = this.G;
            if (aVar != null) {
                aVar.m(cVar);
                return;
            }
            if (cVar == 0) {
                if (aVar != null) {
                    C(aVar);
                }
                this.G = null;
                return;
            } else {
                p pVar = new p(cVar);
                this.G = pVar;
                pVar.a(this);
                j(this.G);
                return;
            }
        }
        if (t == com.airbnb.lottie.k.f2881b) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.H;
            if (aVar2 != null) {
                aVar2.m(cVar);
                return;
            }
            if (cVar == 0) {
                if (aVar2 != null) {
                    C(aVar2);
                }
                this.H = null;
                return;
            } else {
                p pVar2 = new p(cVar);
                this.H = pVar2;
                pVar2.a(this);
                j(this.H);
                return;
            }
        }
        if (t == com.airbnb.lottie.k.o) {
            com.airbnb.lottie.r.c.a<Float, Float> aVar3 = this.I;
            if (aVar3 != null) {
                aVar3.m(cVar);
                return;
            }
            if (cVar == 0) {
                if (aVar3 != null) {
                    C(aVar3);
                }
                this.I = null;
                return;
            } else {
                p pVar3 = new p(cVar);
                this.I = pVar3;
                pVar3.a(this);
                j(this.I);
                return;
            }
        }
        if (t == com.airbnb.lottie.k.p) {
            com.airbnb.lottie.r.c.a<Float, Float> aVar4 = this.J;
            if (aVar4 != null) {
                aVar4.m(cVar);
                return;
            }
            if (cVar == 0) {
                if (aVar4 != null) {
                    C(aVar4);
                }
                this.J = null;
            } else {
                p pVar4 = new p(cVar);
                this.J = pVar4;
                pVar4.a(this);
                j(this.J);
            }
        }
    }

    @Override // com.airbnb.lottie.model.layer.a
    void t(Canvas canvas, Matrix matrix, int i) {
        canvas.save();
        if (!this.E.e0()) {
            canvas.setMatrix(matrix);
        }
        DocumentData documentDataH = this.D.h();
        com.airbnb.lottie.model.b bVar = this.F.g().get(documentDataH.f2895b);
        if (bVar == null) {
            canvas.restore();
            return;
        }
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar = this.G;
        if (aVar != null) {
            this.z.setColor(aVar.h().intValue());
        } else {
            this.z.setColor(documentDataH.h);
        }
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.H;
        if (aVar2 != null) {
            this.A.setColor(aVar2.h().intValue());
        } else {
            this.A.setColor(documentDataH.i);
        }
        int iIntValue = ((this.u.h() == null ? 100 : this.u.h().h().intValue()) * 255) / 100;
        this.z.setAlpha(iIntValue);
        this.A.setAlpha(iIntValue);
        com.airbnb.lottie.r.c.a<Float, Float> aVar3 = this.I;
        if (aVar3 != null) {
            this.A.setStrokeWidth(aVar3.h().floatValue());
        } else {
            this.A.setStrokeWidth((float) (documentDataH.j * ((double) h.e()) * ((double) h.g(matrix))));
        }
        if (this.E.e0()) {
            R(documentDataH, matrix, bVar, canvas);
        } else {
            S(documentDataH, bVar, matrix, canvas);
        }
        canvas.restore();
    }
}
