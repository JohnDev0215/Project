package S3;

import S8.InterfaceC0725w;
import T.X;
import android.content.Context;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class t extends s7.i implements InterfaceC2785n {
    public Throwable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f7968m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f7969n;
    public final /* synthetic */ InterfaceC2786o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Context f7970p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ o f7971q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ String f7972r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ String f7973s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ String f7974t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ String f7975u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ X f7976v;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public t(InterfaceC2786o interfaceC2786o, Context context, o oVar, String str, String str2, String str3, String str4, X x8, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = interfaceC2786o;
        this.f7970p = context;
        this.f7971q = oVar;
        this.f7972r = str;
        this.f7973s = str2;
        this.f7974t = str3;
        this.f7975u = str4;
        this.f7976v = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new t(this.o, this.f7970p, this.f7971q, this.f7972r, this.f7973s, this.f7974t, this.f7975u, this.f7976v, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((t) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x0065, code lost:
    
        if (((java.lang.Boolean) r15).booleanValue() == false) goto L66;
     */
    /* JADX WARN: Removed duplicated region for block: B:17:0x0046  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0115 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x0120 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x00d6 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:51:0x00c8 -> B:53:0x00cb). Please report as a decompilation issue!!! */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 330
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: S3.t.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
