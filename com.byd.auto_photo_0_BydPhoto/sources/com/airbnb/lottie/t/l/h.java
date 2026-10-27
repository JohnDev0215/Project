package com.airbnb.lottie.t.l;

import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Typeface;
import androidx.annotation.Nullable;
import androidx.collection.LongSparseArray;
import com.airbnb.lottie.r.c.n;
import com.airbnb.lottie.r.c.p;
import com.airbnb.lottie.t.b;
import com.airbnb.lottie.t.j.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class h extends com.airbnb.lottie.t.l.a {
    private final Paint A;
    private final Map<com.airbnb.lottie.t.d, List<com.airbnb.lottie.r.b.d>> B;
    private final LongSparseArray<String> C;
    private final n D;
    private final com.airbnb.lottie.f E;
    private final com.airbnb.lottie.d F;

    @Nullable
    private com.airbnb.lottie.r.c.a<Integer, Integer> G;

    @Nullable
    private com.airbnb.lottie.r.c.a<Integer, Integer> H;

    @Nullable
    private com.airbnb.lottie.r.c.a<Float, Float> I;

    @Nullable
    private com.airbnb.lottie.r.c.a<Float, Float> J;
    private final StringBuilder w;
    private final RectF x;
    private final Matrix y;
    private final Paint z;

    class a extends Paint {
        a(int i) {
            super(i);
            setStyle(Paint.Style.FILL);
        }
    }

    class b extends Paint {
        b(int i) {
            super(i);
            setStyle(Paint.Style.STROKE);
        }
    }

    static /* synthetic */ class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f899a;

        static {
            int[] iArr = new int[b.a.values().length];
            f899a = iArr;
            try {
                iArr[b.a.LEFT_ALIGN.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f899a[b.a.RIGHT_ALIGN.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f899a[b.a.CENTER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    h(com.airbnb.lottie.f fVar, d dVar) {
        com.airbnb.lottie.t.j.b bVar;
        com.airbnb.lottie.t.j.b bVar2;
        com.airbnb.lottie.t.j.a aVar;
        com.airbnb.lottie.t.j.a aVar2;
        super(fVar, dVar);
        this.w = new StringBuilder(2);
        this.x = new RectF();
        this.y = new Matrix();
        this.z = new a(1);
        this.A = new b(1);
        this.B = new HashMap();
        this.C = new LongSparseArray<>();
        this.E = fVar;
        this.F = dVar.a();
        n nVarA = dVar.q().a();
        this.D = nVarA;
        nVarA.a(this);
        h(nVarA);
        k kVarR = dVar.r();
        if (kVarR != null && (aVar2 = kVarR.f806a) != null) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVarA = aVar2.a();
            this.G = aVarA;
            aVarA.a(this);
            h(this.G);
        }
        if (kVarR != null && (aVar = kVarR.f807b) != null) {
            com.airbnb.lottie.r.c.a<Integer, Integer> aVarA2 = aVar.a();
            this.H = aVarA2;
            aVarA2.a(this);
            h(this.H);
        }
        if (kVarR != null && (bVar2 = kVarR.f808c) != null) {
            com.airbnb.lottie.r.c.a<Float, Float> aVarA3 = bVar2.a();
            this.I = aVarA3;
            aVarA3.a(this);
            h(this.I);
        }
        if (kVarR == null || (bVar = kVarR.d) == null) {
            return;
        }
        com.airbnb.lottie.r.c.a<Float, Float> aVarA4 = bVar.a();
        this.J = aVarA4;
        aVarA4.a(this);
        h(this.J);
    }

    private void H(b.a aVar, Canvas canvas, float f) {
        float f2;
        int i = c.f899a[aVar.ordinal()];
        if (i == 2) {
            f2 = -f;
        } else if (i != 3) {
            return;
        } else {
            f2 = (-f) / 2.0f;
        }
        canvas.translate(f2, 0.0f);
    }

    private String I(String str, int i) {
        int iCodePointAt = str.codePointAt(i);
        int iCharCount = Character.charCount(iCodePointAt) + i;
        while (iCharCount < str.length()) {
            int iCodePointAt2 = str.codePointAt(iCharCount);
            if (!U(iCodePointAt2)) {
                break;
            }
            iCharCount += Character.charCount(iCodePointAt2);
            iCodePointAt = (iCodePointAt * 31) + iCodePointAt2;
        }
        long j = iCodePointAt;
        if (this.C.containsKey(j)) {
            return this.C.get(j);
        }
        this.w.setLength(0);
        while (i < iCharCount) {
            int iCodePointAt3 = str.codePointAt(i);
            this.w.appendCodePoint(iCodePointAt3);
            i += Character.charCount(iCodePointAt3);
        }
        String string = this.w.toString();
        this.C.put(j, string);
        return string;
    }

    private void J(String str, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawText(str, 0, str.length(), 0.0f, 0.0f, paint);
    }

    private void K(com.airbnb.lottie.t.d dVar, Matrix matrix, float f, com.airbnb.lottie.t.b bVar, Canvas canvas) {
        Paint paint;
        List<com.airbnb.lottie.r.b.d> listR = R(dVar);
        for (int i = 0; i < listR.size(); i++) {
            Path path = listR.get(i).getPath();
            path.computeBounds(this.x, false);
            this.y.set(matrix);
            this.y.preTranslate(0.0f, ((float) (-bVar.g)) * com.airbnb.lottie.w.h.e());
            this.y.preScale(f, f);
            path.transform(this.y);
            if (bVar.k) {
                N(path, this.z, canvas);
                paint = this.A;
            } else {
                N(path, this.A, canvas);
                paint = this.z;
            }
            N(path, paint, canvas);
        }
    }

    private void L(String str, com.airbnb.lottie.t.b bVar, Canvas canvas) {
        Paint paint;
        if (bVar.k) {
            J(str, this.z, canvas);
            paint = this.A;
        } else {
            J(str, this.A, canvas);
            paint = this.z;
        }
        J(str, paint, canvas);
    }

    private void M(String str, com.airbnb.lottie.t.b bVar, Canvas canvas, float f) {
        int length = 0;
        while (length < str.length()) {
            String strI = I(str, length);
            length += strI.length();
            L(strI, bVar, canvas);
            float fMeasureText = this.z.measureText(strI, 0, 1);
            float fFloatValue = bVar.e / 10.0f;
            com.airbnb.lottie.r.c.a<Float, Float> aVar = this.J;
            if (aVar != null) {
                fFloatValue += aVar.h().floatValue();
            }
            canvas.translate(fMeasureText + (fFloatValue * f), 0.0f);
        }
    }

    private void N(Path path, Paint paint, Canvas canvas) {
        if (paint.getColor() == 0) {
            return;
        }
        if (paint.getStyle() == Paint.Style.STROKE && paint.getStrokeWidth() == 0.0f) {
            return;
        }
        canvas.drawPath(path, paint);
    }

    private void O(String str, com.airbnb.lottie.t.b bVar, Matrix matrix, com.airbnb.lottie.t.c cVar, Canvas canvas, float f, float f2) {
        for (int i = 0; i < str.length(); i++) {
            com.airbnb.lottie.t.d dVar = this.F.c().get(com.airbnb.lottie.t.d.c(str.charAt(i), cVar.a(), cVar.c()));
            if (dVar != null) {
                K(dVar, matrix, f2, bVar, canvas);
                float fB = ((float) dVar.b()) * f2 * com.airbnb.lottie.w.h.e() * f;
                float fFloatValue = bVar.e / 10.0f;
                com.airbnb.lottie.r.c.a<Float, Float> aVar = this.J;
                if (aVar != null) {
                    fFloatValue += aVar.h().floatValue();
                }
                canvas.translate(fB + (fFloatValue * f), 0.0f);
            }
        }
    }

    private void P(com.airbnb.lottie.t.b bVar, Matrix matrix, com.airbnb.lottie.t.c cVar, Canvas canvas) {
        float f = ((float) bVar.f784c) / 100.0f;
        float fG = com.airbnb.lottie.w.h.g(matrix);
        String str = bVar.f782a;
        float fE = ((float) bVar.f) * com.airbnb.lottie.w.h.e();
        List<String> listT = T(str);
        int size = listT.size();
        for (int i = 0; i < size; i++) {
            String str2 = listT.get(i);
            float fS = S(str2, cVar, f, fG);
            canvas.save();
            H(bVar.d, canvas, fS);
            canvas.translate(0.0f, (i * fE) - (((size - 1) * fE) / 2.0f));
            O(str2, bVar, matrix, cVar, canvas, fG, f);
            canvas.restore();
        }
    }

    private void Q(com.airbnb.lottie.t.b bVar, com.airbnb.lottie.t.c cVar, Matrix matrix, Canvas canvas) {
        float fG = com.airbnb.lottie.w.h.g(matrix);
        Typeface typefaceA = this.E.A(cVar.a(), cVar.c());
        if (typefaceA == null) {
            return;
        }
        String str = bVar.f782a;
        if (this.E.z() != null) {
            throw null;
        }
        this.z.setTypeface(typefaceA);
        this.z.setTextSize((float) (bVar.f784c * ((double) com.airbnb.lottie.w.h.e())));
        this.A.setTypeface(this.z.getTypeface());
        this.A.setTextSize(this.z.getTextSize());
        float fE = ((float) bVar.f) * com.airbnb.lottie.w.h.e();
        List<String> listT = T(str);
        int size = listT.size();
        for (int i = 0; i < size; i++) {
            String str2 = listT.get(i);
            H(bVar.d, canvas, this.A.measureText(str2));
            canvas.translate(0.0f, (i * fE) - (((size - 1) * fE) / 2.0f));
            M(str2, bVar, canvas, fG);
            canvas.setMatrix(matrix);
        }
    }

    private List<com.airbnb.lottie.r.b.d> R(com.airbnb.lottie.t.d dVar) {
        if (this.B.containsKey(dVar)) {
            return this.B.get(dVar);
        }
        List<com.airbnb.lottie.t.k.n> listA = dVar.a();
        int size = listA.size();
        ArrayList arrayList = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            arrayList.add(new com.airbnb.lottie.r.b.d(this.E, this, listA.get(i)));
        }
        this.B.put(dVar, arrayList);
        return arrayList;
    }

    private float S(String str, com.airbnb.lottie.t.c cVar, float f, float f2) {
        float fB = 0.0f;
        for (int i = 0; i < str.length(); i++) {
            com.airbnb.lottie.t.d dVar = this.F.c().get(com.airbnb.lottie.t.d.c(str.charAt(i), cVar.a(), cVar.c()));
            if (dVar != null) {
                fB = (float) (((double) fB) + (dVar.b() * ((double) f) * ((double) com.airbnb.lottie.w.h.e()) * ((double) f2)));
            }
        }
        return fB;
    }

    private List<String> T(String str) {
        return Arrays.asList(str.replaceAll("\r\n", "\r").replaceAll("\n", "\r").split("\r"));
    }

    private boolean U(int i) {
        return Character.getType(i) == 16 || Character.getType(i) == 27 || Character.getType(i) == 6 || Character.getType(i) == 28 || Character.getType(i) == 19;
    }

    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.r.b.e
    public void a(RectF rectF, Matrix matrix, boolean z) {
        super.a(rectF, matrix, z);
        rectF.set(0.0f, 0.0f, this.F.b().width(), this.F.b().height());
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.airbnb.lottie.t.l.a, com.airbnb.lottie.t.f
    public <T> void g(T t, @Nullable com.airbnb.lottie.x.c<T> cVar) {
        com.airbnb.lottie.r.c.a<?, ?> aVar;
        com.airbnb.lottie.r.c.a<?, ?> aVar2;
        super.g(t, cVar);
        if (t == com.airbnb.lottie.k.f709a) {
            aVar = this.G;
            if (aVar == null) {
                if (cVar == 0) {
                    if (aVar != null) {
                        A(aVar);
                    }
                    this.G = null;
                    return;
                } else {
                    p pVar = new p(cVar);
                    this.G = pVar;
                    pVar.a(this);
                    aVar2 = this.G;
                    h(aVar2);
                    return;
                }
            }
            aVar.m(cVar);
        }
        if (t == com.airbnb.lottie.k.f710b) {
            aVar = this.H;
            if (aVar == null) {
                if (cVar == 0) {
                    if (aVar != null) {
                        A(aVar);
                    }
                    this.H = null;
                    return;
                } else {
                    p pVar2 = new p(cVar);
                    this.H = pVar2;
                    pVar2.a(this);
                    aVar2 = this.H;
                    h(aVar2);
                    return;
                }
            }
            aVar.m(cVar);
        }
        if (t == com.airbnb.lottie.k.o) {
            aVar = this.I;
            if (aVar == null) {
                if (cVar == 0) {
                    if (aVar != null) {
                        A(aVar);
                    }
                    this.I = null;
                    return;
                } else {
                    p pVar3 = new p(cVar);
                    this.I = pVar3;
                    pVar3.a(this);
                    aVar2 = this.I;
                    h(aVar2);
                    return;
                }
            }
            aVar.m(cVar);
        }
        if (t == com.airbnb.lottie.k.p) {
            aVar = this.J;
            if (aVar == null) {
                if (cVar == 0) {
                    if (aVar != null) {
                        A(aVar);
                    }
                    this.J = null;
                    return;
                } else {
                    p pVar4 = new p(cVar);
                    this.J = pVar4;
                    pVar4.a(this);
                    aVar2 = this.J;
                    h(aVar2);
                    return;
                }
            }
            aVar.m(cVar);
        }
    }

    @Override // com.airbnb.lottie.t.l.a
    void r(Canvas canvas, Matrix matrix, int i) {
        Paint paint;
        float fE;
        canvas.save();
        if (!this.E.e0()) {
            canvas.setMatrix(matrix);
        }
        com.airbnb.lottie.t.b bVarH = this.D.h();
        com.airbnb.lottie.t.c cVar = this.F.g().get(bVarH.f783b);
        if (cVar == null) {
            canvas.restore();
            return;
        }
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar = this.G;
        if (aVar != null) {
            this.z.setColor(aVar.h().intValue());
        } else {
            this.z.setColor(bVarH.h);
        }
        com.airbnb.lottie.r.c.a<Integer, Integer> aVar2 = this.H;
        if (aVar2 != null) {
            this.A.setColor(aVar2.h().intValue());
        } else {
            this.A.setColor(bVarH.i);
        }
        int iIntValue = ((this.u.h() == null ? 100 : this.u.h().h().intValue()) * 255) / 100;
        this.z.setAlpha(iIntValue);
        this.A.setAlpha(iIntValue);
        com.airbnb.lottie.r.c.a<Float, Float> aVar3 = this.I;
        if (aVar3 != null) {
            paint = this.A;
            fE = aVar3.h().floatValue();
        } else {
            float fG = com.airbnb.lottie.w.h.g(matrix);
            paint = this.A;
            fE = (float) (bVarH.j * ((double) com.airbnb.lottie.w.h.e()) * ((double) fG));
        }
        paint.setStrokeWidth(fE);
        if (this.E.e0()) {
            P(bVarH, matrix, cVar, canvas);
        } else {
            Q(bVarH, cVar, matrix, canvas);
        }
        canvas.restore();
    }
}
