package y6;

import A7.m;
import U5.i;
import V8.AbstractC0828q;
import V8.f0;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.ArrayList;
import java.util.Iterator;
import n7.AbstractC1998m;
import r1.C2272d;
import t6.C2442c;
import t6.C2444e;
import t6.C2445f;
import v6.C2553a;
import v6.C2554b;
import v6.C2555c;
import v6.C2557e;
import v6.C2558f;

/* JADX INFO: loaded from: classes.dex */
public final class f extends ConstraintLayout {
    public final f0 B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public x6.c f24158C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public w6.e f24159D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final C2445f f24160E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final ArrayList f24161F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public String f24162G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public int f24163H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public int f24164I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public final ArrayList f24165J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public t6.h f24166K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public boolean f24167L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public Paint f24168M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public b f24169N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f24170O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public final Typeface f24171P;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public f(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        m.f(context, "context");
        this.B = AbstractC0828q.c(Boolean.FALSE);
        this.f24160E = new C2445f();
        this.f24161F = new ArrayList();
        this.f24162G = "";
        this.f24164I = 1;
        this.f24165J = new ArrayList();
        this.f24170O = "";
        this.f24171P = Typeface.create(Typeface.createFromAsset(context.getAssets(), "fonts/NotoSans-Medium.ttf"), 0);
    }

    public final void k() {
        this.f24167L = false;
        this.f24164I = 0;
        this.f24166K = null;
        q();
        b bVar = this.f24169N;
        if (bVar != null) {
            bVar.b(0.0f);
        }
        Boolean bool = Boolean.FALSE;
        f0 f0Var = this.B;
        f0Var.getClass();
        f0Var.m(null, bool);
    }

    public final w6.e l(int i7, int i10, Typeface typeface) {
        x6.c cVar = this.f24158C;
        if (cVar == null) {
            m.j("configs");
            throw null;
        }
        x6.f fVar = cVar.c;
        m.f(typeface, "typeface");
        Paint paint = new Paint();
        paint.setColor(i7);
        float f10 = fVar.f23821a;
        paint.setTextSize(f10);
        paint.setLetterSpacing(0.03f);
        paint.setFlags(1027);
        Paint.Align align = Paint.Align.LEFT;
        paint.setTextAlign(align);
        paint.setTypeface(typeface);
        Paint paint2 = new Paint();
        paint2.setColor(i10);
        paint2.setTextSize(f10);
        paint2.setLetterSpacing(0.03f);
        paint2.setFlags(1027);
        paint2.setTextAlign(align);
        paint2.setTypeface(typeface);
        return new w6.e(paint, paint2, f10);
    }

    public final void m(x6.c cVar) {
        this.f24158C = cVar;
        C2445f c2445f = this.f24160E;
        c2445f.getClass();
        x6.e eVar = cVar.f23798b;
        m.f(eVar, "timingConfig");
        c2445f.f22483a = eVar;
        x6.b bVar = cVar.f23797a;
        Typeface typeface = this.f24171P;
        m.e(typeface, "typeface");
        this.f24159D = l(bVar.f23788a, bVar.f23789b, typeface);
        ArrayList arrayList = this.f24165J;
        arrayList.add(l(bVar.c, bVar.d, typeface));
        arrayList.add(l(bVar.f23790e, bVar.f23791f, typeface));
        arrayList.add(l(bVar.f23792g, bVar.f23793h, typeface));
        arrayList.add(l(bVar.f23794i, bVar.f23795j, typeface));
        x6.c cVar2 = this.f24158C;
        if (cVar2 == null) {
            m.j("configs");
            throw null;
        }
        x6.f fVar = cVar2.c;
        Paint paint = new Paint();
        paint.setColor(bVar.l);
        paint.setTextSize(fVar.f23821a);
        paint.setLetterSpacing(0.03f);
        paint.setFlags(1027);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTypeface(typeface);
        this.f24168M = paint;
        Context context = getContext();
        m.e(context, "getContext(...)");
        b bVar2 = new b(context, null, 0);
        this.f24169N = bVar2;
        bVar2.l = cVar;
        bVar2.a(0.0f);
        addView(this.f24169N);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0, types: [android.view.View, android.view.ViewGroup, y6.f] */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v15, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v19, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v2, types: [java.lang.Throwable] */
    public final void n(t6.h hVar) {
        w6.e eVar;
        m.f(hVar, "kmlSong");
        this.f24167L = true;
        ArrayList arrayList = hVar.f22494e;
        this.f24164I = arrayList.size();
        Boolean bool = Boolean.FALSE;
        f0 f0Var = this.B;
        f0Var.getClass();
        AttributeSet attributeSet = null;
        f0Var.m(null, bool);
        if (this.f24164I == 0) {
            k();
            return;
        }
        ArrayList arrayList2 = this.f24161F;
        int size = arrayList2.size();
        int i7 = this.f24164I;
        while (true) {
            ArrayList arrayList3 = this.f24165J;
            if (size >= i7) {
                ?? r22 = attributeSet;
                if (this.f24164I == 1) {
                    eVar = this.f24159D;
                    if (eVar == null) {
                        m.j("soloPaint");
                        throw r22;
                    }
                } else {
                    Object obj = arrayList3.get(0);
                    m.e(obj, "get(...)");
                    eVar = (w6.e) obj;
                }
                ((h) arrayList2.get(0)).setKaraokePaint(eVar);
                q();
                Iterator it = arrayList.iterator();
                String str = "";
                String str2 = "";
                float f10 = 0.0f;
                while (it.hasNext()) {
                    Iterator it2 = ((t6.g) it.next()).f22491b.iterator();
                    while (it2.hasNext()) {
                        Iterator it3 = ((C2444e) it2.next()).c.iterator();
                        while (it3.hasNext()) {
                            String strF0 = AbstractC1998m.f0(((C2442c) it3.next()).f22478b, "", null, null, new i(10), 30);
                            float fMeasureText = eVar.f23342a.measureText(strF0);
                            if (fMeasureText > f10) {
                                str2 = strF0;
                                f10 = fMeasureText;
                            }
                        }
                    }
                }
                this.f24162G = str2;
                Paint paint = this.f24168M;
                if (paint == null) {
                    m.j("creditsPaint");
                    throw null;
                }
                float f11 = 0.0f;
                for (String str3 : hVar.d.f22476b) {
                    float fMeasureText2 = paint.measureText(str3);
                    if (fMeasureText2 > f11) {
                        str = str3;
                        f11 = fMeasureText2;
                    }
                }
                this.f24170O = str;
                this.f24166K = hVar;
                b bVar = this.f24169N;
                if (bVar != null) {
                    Paint paint2 = this.f24168M;
                    if (paint2 == null) {
                        m.j("creditsPaint");
                        throw null;
                    }
                    bVar.setKaraokePaint(paint2);
                }
                if (getHeight() > 0) {
                    o(getWidth(), getHeight());
                    return;
                }
                return;
            }
            Context context = getContext();
            m.e(context, "getContext(...)");
            h hVar2 = new h(context, attributeSet);
            x6.c cVar = this.f24158C;
            if (cVar == null) {
                ?? r23 = attributeSet;
                m.j("configs");
                throw r23;
            }
            hVar2.B = cVar;
            hVar2.f24179G = cVar.f23797a.f23796k;
            Context context2 = hVar2.getContext();
            m.e(context2, "getContext(...)");
            g gVar = new g(context2);
            C2272d c2272d = new C2272d(-1, -1);
            gVar.setId(View.generateViewId());
            gVar.setLayoutParams(c2272d);
            x6.c cVar2 = hVar2.B;
            if (cVar2 == null) {
                ?? r24 = attributeSet;
                m.j("lyricsConfigs");
                throw r24;
            }
            int i10 = hVar2.f24179G;
            gVar.l = cVar2;
            Paint paint3 = gVar.f24172m;
            paint3.setColor(i10);
            paint3.setStyle(Paint.Style.FILL);
            paint3.setStrokeJoin(Paint.Join.ROUND);
            paint3.setStrokeCap(Paint.Cap.ROUND);
            hVar2.addView(gVar);
            hVar2.f24177E = gVar;
            Context context3 = hVar2.getContext();
            m.e(context3, "getContext(...)");
            c cVar3 = new c(context3, attributeSet, 0);
            cVar3.f24134m = new Paint();
            cVar3.f24135n = new Rect();
            cVar3.f24138r = true;
            hVar2.f24175C = cVar3;
            cVar3.l = cVar;
            cVar3.setId(View.generateViewId());
            c cVar4 = hVar2.f24175C;
            if (cVar4 == null) {
                ?? r25 = attributeSet;
                m.j("cueBarView");
                throw r25;
            }
            cVar4.setLayoutParams(new C2272d(-1, 0));
            c cVar5 = hVar2.f24175C;
            if (cVar5 == null) {
                m.j("cueBarView");
                throw null;
            }
            hVar2.addView(cVar5);
            Context context4 = hVar2.getContext();
            m.e(context4, "getContext(...)");
            a aVar = new a(context4, null, 0);
            aVar.o = new RectF();
            hVar2.f24176D = aVar;
            aVar.setLyricsConfigs(cVar);
            aVar.setAlpha(0.0f);
            a aVar2 = hVar2.f24176D;
            if (aVar2 == null) {
                m.j("breakView");
                throw null;
            }
            aVar2.setId(View.generateViewId());
            a aVar3 = hVar2.f24176D;
            if (aVar3 == null) {
                m.j("breakView");
                throw null;
            }
            aVar3.setLayoutParams(new C2272d(-1, -2));
            a aVar4 = hVar2.f24176D;
            if (aVar4 == null) {
                m.j("breakView");
                throw null;
            }
            hVar2.addView(aVar4);
            if (size > 0) {
                Object obj2 = arrayList3.get(size);
                m.e(obj2, "get(...)");
                hVar2.setKaraokePaint((w6.e) obj2);
            }
            size++;
            hVar2.setId(size);
            addView(hVar2);
            arrayList2.add(hVar2);
            attributeSet = null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:172:0x041d  */
    /* JADX WARN: Removed duplicated region for block: B:192:0x0460  */
    /* JADX WARN: Removed duplicated region for block: B:89:0x01e7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void o(float r26, float r27) {
        /*
            Method dump skipped, instruction units count: 1132
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: y6.f.o(float, float):void");
    }

    @Override // android.view.View
    public final void onSizeChanged(final int i7, final int i10, int i11, int i12) {
        super.onSizeChanged(i7, i10, i11, i12);
        post(new Runnable() { // from class: y6.e
            @Override // java.lang.Runnable
            public final void run() {
                this.l.o(i7, i10);
            }
        });
    }

    public final void p(int i7) {
        b bVar;
        boolean z6;
        this.f24163H = i7;
        if (this.f24167L) {
            return;
        }
        C2445f c2445f = this.f24160E;
        c2445f.f22484b = i7;
        C2555c c2555cC = c2445f.c();
        if (c2555cC != null && c2555cC.d()) {
            for (int i10 = 0; i10 < c2555cC.l; i10++) {
                ArrayList arrayList = this.f24161F;
                h hVar = (h) arrayList.get(i10);
                w6.b bVar2 = c2555cC.f23025q;
                C2558f c2558f = (C2558f) bVar2.get(i10);
                hVar.getClass();
                m.f(c2558f, "singer");
                if (c2558f.d()) {
                    w6.b bVar3 = c2558f.o;
                    boolean zD = bVar3.d();
                    ArrayList arrayList2 = hVar.f24180H;
                    if (zD) {
                        int i11 = 0;
                        for (C2557e c2557e : bVar3.l) {
                            int i12 = i11 + 1;
                            if (c2557e.d()) {
                                w6.c cVar = c2557e.f23029n;
                                if (cVar.f23341m) {
                                    ((d) arrayList2.get(i11)).setLineText(((Number) cVar.l).intValue() == -1 ? "" : c2557e.f23032r);
                                }
                                if (((Number) cVar.l).intValue() != -1) {
                                    d dVar = (d) arrayList2.get(i11);
                                    float fFloatValue = ((Number) c2557e.o.l).floatValue();
                                    dVar.getClass();
                                    dVar.f24152w = (int) (fFloatValue * 255);
                                    float fFloatValue2 = ((Number) c2557e.f23031q.l).floatValue();
                                    dVar.f24147r = fFloatValue2;
                                    if (fFloatValue2 == 0.0f) {
                                        dVar.f24150u = false;
                                        dVar.f24149t = true;
                                    } else if (fFloatValue2 == 1.0f) {
                                        dVar.f24150u = true;
                                        dVar.f24149t = false;
                                    } else {
                                        dVar.f24150u = true;
                                        dVar.f24149t = true;
                                    }
                                    dVar.invalidate();
                                }
                            }
                            i11 = i12;
                        }
                    }
                    C2554b c2554b = c2558f.f23033m;
                    if (c2554b.d()) {
                        if (((Boolean) c2554b.f23021n.l).booleanValue()) {
                            c cVar2 = hVar.f24175C;
                            if (cVar2 == null) {
                                m.j("cueBarView");
                                throw null;
                            }
                            float fFloatValue3 = ((Number) c2554b.f23020m.l).floatValue();
                            d dVar2 = (d) arrayList2.get(((Number) c2554b.l.l).intValue());
                            m.f(dVar2, "textView");
                            if (m.a(cVar2.f24139s, dVar2)) {
                                z6 = false;
                                cVar2.invalidate();
                            } else {
                                cVar2.f24139s = dVar2;
                                z6 = false;
                                C2272d c2272d = new C2272d(-1, 0);
                                c2272d.l = dVar2.getId();
                                c2272d.f21628i = dVar2.getId();
                                cVar2.setLayoutParams(c2272d);
                            }
                            cVar2.o = fFloatValue3;
                            cVar2.f24138r = z6;
                        } else {
                            c cVar3 = hVar.f24175C;
                            if (cVar3 == null) {
                                m.j("cueBarView");
                                throw null;
                            }
                            cVar3.f24139s = null;
                            cVar3.o = 0.0f;
                            cVar3.f24138r = true;
                            cVar3.invalidate();
                        }
                    }
                    C2553a c2553a = c2558f.l;
                    if (c2553a.d()) {
                        a aVar = hVar.f24176D;
                        if (aVar == null) {
                            m.j("breakView");
                            throw null;
                        }
                        float fFloatValue4 = ((Number) c2553a.l.l).floatValue();
                        float fFloatValue5 = ((Number) c2553a.f23019m.l).floatValue();
                        aVar.f24131p = fFloatValue4;
                        aVar.setAlpha(fFloatValue5);
                        aVar.invalidate();
                    }
                }
                h hVar2 = (h) arrayList.get(i10);
                w6.c cVar4 = ((C2558f) bVar2.get(i10)).f23034n;
                hVar2.getClass();
                w6.c cVar5 = c2555cC.f23024p;
                m.f(cVar4, "backgroundOpacity");
                if (cVar5.f23341m) {
                    g gVar = hVar2.f24177E;
                    if (gVar == null) {
                        m.j("backgroundView");
                        throw null;
                    }
                    gVar.setOpacity(((Number) cVar5.l).floatValue());
                }
                if (cVar4.f23341m) {
                    g gVar2 = hVar2.f24177E;
                    if (gVar2 == null) {
                        m.j("backgroundView");
                        throw null;
                    }
                    gVar2.setBackgroundOpacity(((Number) cVar4.l).floatValue());
                }
            }
        }
        if (c2555cC != null) {
            if (c2555cC.d()) {
                w6.c cVar6 = c2555cC.f23023n;
                Boolean boolValueOf = Boolean.valueOf(((Number) cVar6.l).floatValue() > 0.0f);
                f0 f0Var = this.B;
                f0Var.getClass();
                f0Var.m(null, boolValueOf);
                if (!cVar6.f23341m || (bVar = this.f24169N) == null) {
                    return;
                }
                bVar.b(((Number) cVar6.l).floatValue());
            }
        }
    }

    public final void q() {
        ArrayList arrayList = this.f24161F;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            if (i7 >= this.f24164I) {
                ((h) arrayList.get(i7)).setVisibility(8);
            } else {
                ((h) arrayList.get(i7)).setVisibility(0);
            }
            h hVar = (h) arrayList.get(i7);
            a aVar = hVar.f24176D;
            if (aVar == null) {
                m.j("breakView");
                throw null;
            }
            aVar.f24131p = 0.0f;
            aVar.setAlpha(0.0f);
            aVar.invalidate();
            c cVar = hVar.f24175C;
            if (cVar == null) {
                m.j("cueBarView");
                throw null;
            }
            cVar.f24139s = null;
            cVar.o = 0.0f;
            cVar.f24138r = true;
            cVar.invalidate();
            Iterator it = hVar.f24180H.iterator();
            while (it.hasNext()) {
                ((d) it.next()).setLineText("");
            }
            g gVar = hVar.f24177E;
            if (gVar == null) {
                m.j("backgroundView");
                throw null;
            }
            gVar.setOpacity(0.0f);
            g gVar2 = hVar.f24177E;
            if (gVar2 == null) {
                m.j("backgroundView");
                throw null;
            }
            gVar2.setBackgroundOpacity(0.0f);
        }
    }
}
