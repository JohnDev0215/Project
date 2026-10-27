package Z7;

import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public abstract class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final C2058c f10951a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final C2058c[] f10952b;
    public static final M.t c;
    public static final r d;

    static {
        C2058c c2058c = new C2058c("org.jspecify.nullness");
        C2058c c2058c2 = new C2058c("org.jspecify.annotations");
        f10951a = c2058c2;
        C2058c c2058c3 = new C2058c("io.reactivex.rxjava3.annotations");
        C2058c c2058c4 = new C2058c("org.checkerframework.checker.nullness.compatqual");
        String strB = c2058c3.b();
        f10952b = new C2058c[]{new C2058c(strB.concat(".Nullable")), new C2058c(strB.concat(".NonNull"))};
        C2058c c2058c5 = new C2058c("org.jetbrains.annotations");
        r rVar = r.d;
        m7.i iVar = new m7.i(c2058c5, rVar);
        m7.i iVar2 = new m7.i(new C2058c("androidx.annotation"), rVar);
        m7.i iVar3 = new m7.i(new C2058c("android.support.annotation"), rVar);
        m7.i iVar4 = new m7.i(new C2058c("android.annotation"), rVar);
        m7.i iVar5 = new m7.i(new C2058c("com.android.annotations"), rVar);
        m7.i iVar6 = new m7.i(new C2058c("org.eclipse.jdt.annotation"), rVar);
        m7.i iVar7 = new m7.i(new C2058c("org.checkerframework.checker.nullness.qual"), rVar);
        m7.i iVar8 = new m7.i(c2058c4, rVar);
        m7.i iVar9 = new m7.i(new C2058c("javax.annotation"), rVar);
        m7.i iVar10 = new m7.i(new C2058c("edu.umd.cs.findbugs.annotations"), rVar);
        m7.i iVar11 = new m7.i(new C2058c("io.reactivex.annotations"), rVar);
        C2058c c2058c6 = new C2058c("androidx.annotation.RecentlyNullable");
        B b10 = B.WARN;
        m7.i iVar12 = new m7.i(c2058c6, new r(b10, 4));
        m7.i iVar13 = new m7.i(new C2058c("androidx.annotation.RecentlyNonNull"), new r(b10, 4));
        m7.i iVar14 = new m7.i(new C2058c("lombok"), rVar);
        m7.e eVar = new m7.e(1, 9, 0);
        B b11 = B.STRICT;
        c = new M.t(n7.z.s(iVar, iVar2, iVar3, iVar4, iVar5, iVar6, iVar7, iVar8, iVar9, iVar10, iVar11, iVar12, iVar13, iVar14, new m7.i(c2058c, new r(b10, eVar, b11)), new m7.i(c2058c2, new r(b10, new m7.e(1, 9, 0), b11)), new m7.i(c2058c3, new r(b10, new m7.e(1, 8, 0), b11))));
        d = new r(b10, 4);
    }
}
