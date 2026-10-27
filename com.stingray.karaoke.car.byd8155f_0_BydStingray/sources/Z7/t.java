package Z7;

import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public final class t {
    public static final t c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final v f10956a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f10957b;

    static {
        C2058c c2058c = q.f10951a;
        m7.e eVar = m7.e.f19254p;
        A7.m.f(eVar, "configuredKotlinVersion");
        r rVar = q.d;
        m7.e eVar2 = rVar.f10954b;
        B b10 = (eVar2 == null || eVar2.o - eVar.o > 0) ? rVar.f10953a : rVar.c;
        A7.m.f(b10, "globalReportLevel");
        v vVar = new v(b10, b10 == B.WARN ? null : b10);
        s sVar = s.f10955t;
        c = new t(vVar);
    }

    public t(v vVar) {
        s sVar = s.f10955t;
        this.f10956a = vVar;
        this.f10957b = vVar.d || sVar.b(q.f10951a) == B.IGNORE;
    }

    public final String toString() {
        return "JavaTypeEnhancementState(jsr305=" + this.f10956a + ", getReportLevelForAnnotation=" + s.f10955t + ')';
    }
}
