package N1;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import q7.InterfaceC2189c;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: N1.h, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0427h extends s7.i implements InterfaceC2785n {
    public Iterator l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Q1.d f4526m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f4527n;
    public int o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public /* synthetic */ Object f4528p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ List f4529q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ ArrayList f4530r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0427h(List list, ArrayList arrayList, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4529q = list;
        this.f4530r = arrayList;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        C0427h c0427h = new C0427h(this.f4529q, this.f4530r, interfaceC2189c);
        c0427h.f4528p = obj;
        return c0427h;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((C0427h) create(obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0042  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0066  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0095 A[RETURN] */
    @Override // s7.AbstractC2408a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) throws java.lang.Throwable {
        /*
            r10 = this;
            r7.a r0 = r7.EnumC2303a.l
            int r1 = r10.o
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L2f
            if (r1 == r3) goto L1e
            if (r1 != r2) goto L16
            java.util.Iterator r1 = r10.l
            java.lang.Object r4 = r10.f4528p
            java.util.List r4 = (java.util.List) r4
            h8.AbstractC1386c.u(r11)
            goto L3c
        L16:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L1e:
            java.lang.Object r1 = r10.f4527n
            Q1.d r4 = r10.f4526m
            java.util.Iterator r5 = r10.l
            java.lang.Object r6 = r10.f4528p
            java.util.List r6 = (java.util.List) r6
            h8.AbstractC1386c.u(r11)
            r9 = r6
            r6 = r4
            r4 = r9
            goto L5e
        L2f:
            h8.AbstractC1386c.u(r11)
            java.lang.Object r11 = r10.f4528p
            java.util.List r1 = r10.f4529q
            java.util.Iterator r1 = r1.iterator()
            java.util.ArrayList r4 = r10.f4530r
        L3c:
            boolean r5 = r1.hasNext()
            if (r5 == 0) goto L95
            java.lang.Object r5 = r1.next()
            Q1.d r5 = (Q1.d) r5
            r10.f4528p = r4
            r10.l = r1
            r10.f4526m = r5
            r10.f4527n = r11
            r10.o = r3
            java.lang.Object r6 = r5.a(r11, r10)
            if (r6 != r0) goto L59
            return r0
        L59:
            r9 = r1
            r1 = r11
            r11 = r6
            r6 = r5
            r5 = r9
        L5e:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L93
            N1.g r11 = new N1.g
            r7 = 0
            r11.<init>(r6, r7)
            r4.add(r11)
            r10.f4528p = r4
            r10.l = r5
            r10.f4526m = r7
            r10.f4527n = r7
            r10.o = r2
            Q1.f r11 = new Q1.f
            m7.n r7 = r6.d
            java.lang.Object r7 = r7.getValue()
            android.content.SharedPreferences r7 = (android.content.SharedPreferences) r7
            java.util.Set r8 = r6.f6113e
            r11.<init>(r7, r8)
            H5.d r6 = r6.f6112b
            java.lang.Object r11 = r6.g(r11, r1, r10)
            if (r11 != r0) goto L91
            return r0
        L91:
            r1 = r5
            goto L3c
        L93:
            r11 = r1
            goto L91
        L95:
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: N1.C0427h.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
