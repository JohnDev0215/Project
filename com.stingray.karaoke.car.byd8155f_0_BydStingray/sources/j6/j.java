package j6;

import S8.InterfaceC0725w;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class j extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ n l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ String f17048m;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public j(n nVar, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = nVar;
        this.f17048m = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new j(this.l, this.f17048m, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((j) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x003a  */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.String r0 = r6.f17048m
            j6.n r6 = r6.l
            r7.a r1 = r7.EnumC2303a.l
            h8.AbstractC1386c.u(r7)
            r7 = 0
            J5.c r1 = r6.f17057a     // Catch: java.lang.Exception -> L55
            r6.d r1 = r1.b()     // Catch: java.lang.Exception -> L55
            com.stingray.kca.model.ScoringDetailResponse r1 = r6.d.g(r1, r0)     // Catch: java.lang.Exception -> L55
            java.lang.Integer r2 = r1.f13202a     // Catch: java.lang.Exception -> L55
            if (r2 == 0) goto L3a
            int r2 = r2.intValue()     // Catch: java.lang.Exception -> L55
            java.lang.Float r3 = r1.f13203b     // Catch: java.lang.Exception -> L55
            if (r3 == 0) goto L3a
            float r3 = r3.floatValue()     // Catch: java.lang.Exception -> L55
            java.lang.Integer r4 = r1.c     // Catch: java.lang.Exception -> L55
            if (r4 == 0) goto L3a
            int r4 = r4.intValue()     // Catch: java.lang.Exception -> L55
            java.lang.Float r1 = r1.d     // Catch: java.lang.Exception -> L55
            if (r1 == 0) goto L3a
            float r1 = r1.floatValue()     // Catch: java.lang.Exception -> L55
            n6.G r5 = new n6.G     // Catch: java.lang.Exception -> L55
            r5.<init>(r2, r3, r4, r1)     // Catch: java.lang.Exception -> L55
            goto L3b
        L3a:
            r5 = r7
        L3b:
            if (r5 == 0) goto L54
            V8.f0 r1 = r6.f17060f     // Catch: java.lang.Exception -> L55
            java.lang.Object r1 = r1.getValue()     // Catch: java.lang.Exception -> L55
            java.util.Map r1 = (java.util.Map) r1     // Catch: java.lang.Exception -> L55
            java.util.LinkedHashMap r1 = n7.z.z(r1)     // Catch: java.lang.Exception -> L55
            r1.put(r0, r5)     // Catch: java.lang.Exception -> L55
            V8.f0 r6 = r6.f17060f     // Catch: java.lang.Exception -> L55
            r6.getClass()     // Catch: java.lang.Exception -> L55
            r6.m(r7, r1)     // Catch: java.lang.Exception -> L55
        L54:
            return r5
        L55:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: j6.j.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
