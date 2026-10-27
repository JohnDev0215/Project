package K6;

import T.X;
import m7.q;
import q7.InterfaceC2189c;
import y.y0;
import y0.z;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends s7.h implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f3703m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f3704n;
    public final /* synthetic */ n o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ y0 f3705p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ X8.c f3706q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ X f3707r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(n nVar, y0 y0Var, X8.c cVar, X x8, InterfaceC2189c interfaceC2189c) {
        super(interfaceC2189c);
        this.o = nVar;
        this.f3705p = y0Var;
        this.f3706q = cVar;
        this.f3707r = x8;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        h hVar = new h(this.o, this.f3705p, this.f3706q, this.f3707r, interfaceC2189c);
        hVar.f3704n = obj;
        return hVar;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((h) create((z) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x00a9  */
    /* JADX WARN: Removed duplicated region for block: B:22:0x00b0  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00ba  */
    /* JADX WARN: Type inference failed for: r12v10, types: [java.lang.Iterable, java.lang.Object, java.util.Collection] */
    /* JADX WARN: Type inference failed for: r5v1, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:15:0x004b -> B:17:0x004e). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions count limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            Method dump skipped, instruction units count: 206
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: K6.h.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
