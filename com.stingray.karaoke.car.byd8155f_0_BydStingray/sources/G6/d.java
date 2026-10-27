package G6;

import S8.InterfaceC0725w;
import T.X;
import android.content.Context;
import m7.q;
import q7.InterfaceC2189c;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d extends s7.i implements InterfaceC2785n {
    public int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f2696m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f2697n;
    public final /* synthetic */ X o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f2698p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(Context context, String str, X x8, InterfaceC2782k interfaceC2782k, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f2696m = context;
        this.f2697n = str;
        this.o = x8;
        this.f2698p = interfaceC2782k;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new d(this.f2696m, this.f2697n, this.o, this.f2698p, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((d) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x004c  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00f5  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G6.d.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
