package Q3;

import android.graphics.Paint;
import android.graphics.RectF;
import u.C2472l;
import x.AbstractC2641j;

/* JADX INFO: loaded from: classes.dex */
public final class i extends b {

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final boolean f6170p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C2472l f6171q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C2472l f6172r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final RectF f6173s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final int f6174t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final int f6175u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final R3.h f6176v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final R3.h f6177w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final R3.h f6178x;

    /* JADX WARN: Illegal instructions before constructor call */
    public i(O3.j jVar, X3.b bVar, W3.e eVar) {
        Paint.Join join;
        Paint.Join join2;
        int iC = AbstractC2641j.c(eVar.f9771g);
        Paint.Cap cap = iC != 0 ? iC != 1 ? Paint.Cap.SQUARE : Paint.Cap.ROUND : Paint.Cap.BUTT;
        int iC2 = AbstractC2641j.c(eVar.f9772h);
        if (iC2 == 0) {
            join = Paint.Join.MITER;
        } else if (iC2 == 1) {
            join = Paint.Join.ROUND;
        } else {
            if (iC2 != 2) {
                join2 = null;
                super(jVar, bVar, cap, join2, eVar.f9773i, eVar.c, eVar.f9770f, eVar.f9774j, eVar.f9775k);
                this.f6171q = new C2472l((Object) null);
                this.f6172r = new C2472l((Object) null);
                this.f6173s = new RectF();
                this.f6174t = eVar.f9767a;
                this.f6170p = eVar.l;
                this.f6175u = (int) (jVar.l.b() / 32.0f);
                R3.e eVarT0 = eVar.f9768b.t0();
                this.f6176v = (R3.h) eVarT0;
                eVarT0.a(this);
                bVar.d(eVarT0);
                R3.e eVarT02 = eVar.d.t0();
                this.f6177w = (R3.h) eVarT02;
                eVarT02.a(this);
                bVar.d(eVarT02);
                R3.e eVarT03 = eVar.f9769e.t0();
                this.f6178x = (R3.h) eVarT03;
                eVarT03.a(this);
                bVar.d(eVarT03);
            }
            join = Paint.Join.BEVEL;
        }
        join2 = join;
        super(jVar, bVar, cap, join2, eVar.f9773i, eVar.c, eVar.f9770f, eVar.f9774j, eVar.f9775k);
        this.f6171q = new C2472l((Object) null);
        this.f6172r = new C2472l((Object) null);
        this.f6173s = new RectF();
        this.f6174t = eVar.f9767a;
        this.f6170p = eVar.l;
        this.f6175u = (int) (jVar.l.b() / 32.0f);
        R3.e eVarT04 = eVar.f9768b.t0();
        this.f6176v = (R3.h) eVarT04;
        eVarT04.a(this);
        bVar.d(eVarT04);
        R3.e eVarT022 = eVar.d.t0();
        this.f6177w = (R3.h) eVarT022;
        eVarT022.a(this);
        bVar.d(eVarT022);
        R3.e eVarT032 = eVar.f9769e.t0();
        this.f6178x = (R3.h) eVarT032;
        eVarT032.a(this);
        bVar.d(eVarT032);
    }

    public final int d() {
        float f10 = this.f6177w.d;
        float f11 = this.f6175u;
        int iRound = Math.round(f10 * f11);
        int iRound2 = Math.round(this.f6178x.d * f11);
        int iRound3 = Math.round(this.f6176v.d * f11);
        int i7 = iRound != 0 ? 527 * iRound : 17;
        if (iRound2 != 0) {
            i7 = i7 * 31 * iRound2;
        }
        return iRound3 != 0 ? i7 * 31 * iRound3 : i7;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r5v10 ??, still in use, count: 1, list:
          (r5v10 ?? I:java.lang.Object) from 0x0050: INVOKE (r7v1 ?? I:u.l), (r1v7 ?? I:long), (r5v10 ?? I:java.lang.Object) VIRTUAL call: u.l.e(long, java.lang.Object):void A[MD:(long, java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    @Override // Q3.b, Q3.e
    public final void f(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r5v10 ??, still in use, count: 1, list:
          (r5v10 ?? I:java.lang.Object) from 0x0050: INVOKE (r7v1 ?? I:u.l), (r1v7 ?? I:long), (r5v10 ?? I:java.lang.Object) VIRTUAL call: u.l.e(long, java.lang.Object):void A[MD:(long, java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r17v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */
}
