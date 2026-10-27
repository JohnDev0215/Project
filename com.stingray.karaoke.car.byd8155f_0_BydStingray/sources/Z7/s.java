package Z7;

import H7.InterfaceC0320e;
import o8.C2058c;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class s extends A7.i implements InterfaceC2782k {

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public static final s f10955t = new s(1);

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        C2058c c2058c = (C2058c) obj;
        A7.m.f(c2058c, "p0");
        C2058c c2058c2 = q.f10951a;
        A.f10903g.getClass();
        M.t tVar = z.f10989b;
        m7.e eVar = new m7.e(1, 7, 20);
        A7.m.f(tVar, "configuredReportLevels");
        B b10 = (B) ((E8.j) tVar.l).b(c2058c);
        if (b10 != null) {
            return b10;
        }
        M.t tVar2 = q.c;
        tVar2.getClass();
        r rVar = (r) ((E8.j) tVar2.l).b(c2058c);
        if (rVar == null) {
            return B.IGNORE;
        }
        m7.e eVar2 = rVar.f10954b;
        return (eVar2 == null || eVar2.o - eVar.o > 0) ? rVar.f10953a : rVar.c;
    }

    @Override // A7.AbstractC0056d, H7.InterfaceC0317b
    public final String getName() {
        return "getDefaultReportLevelForAnnotation";
    }

    @Override // A7.AbstractC0056d
    public final InterfaceC0320e h() {
        return A7.A.f443a.c(q.class, "compiler.common.jvm");
    }

    @Override // A7.AbstractC0056d
    public final String i() {
        return "getDefaultReportLevelForAnnotation(Lorg/jetbrains/kotlin/name/FqName;)Lorg/jetbrains/kotlin/load/java/ReportLevel;";
    }
}
