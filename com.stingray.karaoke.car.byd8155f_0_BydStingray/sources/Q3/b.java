package Q3;

import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class b implements R3.a, c, e {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final O3.j f6127e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final X3.b f6128f;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float[] f6130h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final P3.a f6131i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final R3.f f6132j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final R3.f f6133k;
    public final ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final R3.f f6134m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final R3.f f6135n;
    public float o;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final PathMeasure f6125a = new PathMeasure();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Path f6126b = new Path();
    public final Path c = new Path();
    public final RectF d = new RectF();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f6129g = new ArrayList();

    public b(O3.j jVar, X3.b bVar, Paint.Cap cap, Paint.Join join, float f10, V3.a aVar, V3.b bVar2, ArrayList arrayList, V3.b bVar3) {
        P3.a aVar2 = new P3.a(1, 0);
        this.f6131i = aVar2;
        this.o = 0.0f;
        this.f6127e = jVar;
        this.f6128f = bVar;
        aVar2.setStyle(Paint.Style.STROKE);
        aVar2.setStrokeCap(cap);
        aVar2.setStrokeJoin(join);
        aVar2.setStrokeMiter(f10);
        this.f6133k = (R3.f) aVar.t0();
        this.f6132j = bVar2.t0();
        if (bVar3 == null) {
            this.f6134m = null;
        } else {
            this.f6134m = bVar3.t0();
        }
        this.l = new ArrayList(arrayList.size());
        this.f6130h = new float[arrayList.size()];
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            this.l.add(((V3.b) arrayList.get(i7)).t0());
        }
        bVar.d(this.f6133k);
        bVar.d(this.f6132j);
        for (int i10 = 0; i10 < this.l.size(); i10++) {
            bVar.d((R3.e) this.l.get(i10));
        }
        R3.e eVar = this.f6134m;
        if (eVar != null) {
            bVar.d(eVar);
        }
        this.f6133k.a(this);
        this.f6132j.a(this);
        for (int i11 = 0; i11 < arrayList.size(); i11++) {
            ((R3.e) this.l.get(i11)).a(this);
        }
        R3.f fVar = this.f6134m;
        if (fVar != null) {
            fVar.a(this);
        }
        if (bVar.i() != null) {
            R3.f fVarT0 = ((V3.b) bVar.i().l).t0();
            this.f6135n = fVarT0;
            fVarT0.a(this);
            bVar.d(fVarT0);
        }
    }

    @Override // Q3.e
    public final void a(RectF rectF, Matrix matrix, boolean z6) {
        Path path = this.f6126b;
        path.reset();
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.f6129g;
            if (i7 >= arrayList.size()) {
                RectF rectF2 = this.d;
                path.computeBounds(rectF2, false);
                float fI = this.f6132j.i() / 2.0f;
                rectF2.set(rectF2.left - fI, rectF2.top - fI, rectF2.right + fI, rectF2.bottom + fI);
                rectF.set(rectF2);
                rectF.set(rectF.left - 1.0f, rectF.top - 1.0f, rectF.right + 1.0f, rectF.bottom + 1.0f);
                return;
            }
            a aVar = (a) arrayList.get(i7);
            for (int i10 = 0; i10 < aVar.f6123a.size(); i10++) {
                path.addPath(((l) aVar.f6123a.get(i10)).e(), matrix);
            }
            i7++;
        }
    }

    @Override // R3.a
    public final void b() {
        this.f6127e.invalidateSelf();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0050  */
    @Override // Q3.c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void c(java.util.List r8, java.util.List r9) {
        /*
            r7 = this;
            java.util.ArrayList r8 = (java.util.ArrayList) r8
            int r0 = r8.size()
            int r0 = r0 + (-1)
            r1 = 0
            r2 = r1
        La:
            r3 = 2
            if (r0 < 0) goto L21
            java.lang.Object r4 = r8.get(r0)
            Q3.c r4 = (Q3.c) r4
            boolean r5 = r4 instanceof Q3.s
            if (r5 == 0) goto L1e
            Q3.s r4 = (Q3.s) r4
            int r5 = r4.c
            if (r5 != r3) goto L1e
            r2 = r4
        L1e:
            int r0 = r0 + (-1)
            goto La
        L21:
            if (r2 == 0) goto L26
            r2.d(r7)
        L26:
            int r8 = r9.size()
            int r8 = r8 + (-1)
        L2c:
            java.util.ArrayList r0 = r7.f6129g
            if (r8 < 0) goto L65
            java.lang.Object r4 = r9.get(r8)
            Q3.c r4 = (Q3.c) r4
            boolean r5 = r4 instanceof Q3.s
            if (r5 == 0) goto L50
            r5 = r4
            Q3.s r5 = (Q3.s) r5
            int r6 = r5.c
            if (r6 != r3) goto L50
            if (r1 == 0) goto L46
            r0.add(r1)
        L46:
            Q3.a r0 = new Q3.a
            r0.<init>(r5)
            r5.d(r7)
            r1 = r0
            goto L62
        L50:
            boolean r0 = r4 instanceof Q3.l
            if (r0 == 0) goto L62
            if (r1 != 0) goto L5b
            Q3.a r1 = new Q3.a
            r1.<init>(r2)
        L5b:
            java.util.ArrayList r0 = r1.f6123a
            Q3.l r4 = (Q3.l) r4
            r0.add(r4)
        L62:
            int r8 = r8 + (-1)
            goto L2c
        L65:
            if (r1 == 0) goto L6a
            r0.add(r1)
        L6a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: Q3.b.c(java.util.List, java.util.List):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:75:0x01e0  */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // Q3.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void f(android.graphics.Canvas r19, android.graphics.Matrix r20, int r21, a4.C0920a r22) {
        /*
            Method dump skipped, instruction units count: 583
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: Q3.b.f(android.graphics.Canvas, android.graphics.Matrix, int, a4.a):void");
    }
}
