package N1;

import h8.AbstractC1386c;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class D extends s7.i implements InterfaceC2785n {
    public Throwable l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f4453m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ boolean f4454n;
    public final /* synthetic */ Q o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ int f4455p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public D(Q q4, int i7, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.o = q4;
        this.f4455p = i7;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        D d = new D(this.o, this.f4455p, interfaceC2189c);
        d.f4454n = ((Boolean) obj).booleanValue();
        return d;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        Boolean bool = (Boolean) obj;
        bool.booleanValue();
        return ((D) create(bool, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [int] */
    /* JADX WARN: Type inference failed for: r1v1, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v11 */
    /* JADX WARN: Type inference failed for: r1v12 */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v2, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v3 */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v9 */
    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        int iIntValue;
        Throwable th;
        boolean z6;
        c0 v10;
        ?? r12;
        ?? r13;
        EnumC2303a enumC2303a = EnumC2303a.l;
        ?? r14 = this.f4453m;
        Q q4 = this.o;
        try {
        } catch (Throwable th2) {
            if (r14 == 0) {
                iIntValue = this.f4455p;
                th = th2;
                r13 = r14;
                v10 = new V(th, iIntValue);
                r12 = r13;
                return new m7.i(v10, Boolean.valueOf((boolean) r12));
            }
            b0 b0VarF = q4.f();
            this.l = th2;
            this.f4454n = r14;
            this.f4453m = 2;
            Integer numA = b0VarF.a();
            if (numA == enumC2303a) {
                return enumC2303a;
            }
            z6 = r14 == true ? 1 : 0;
            obj = numA;
            th = th2;
        }
        if (r14 == 0) {
            AbstractC1386c.u(obj);
            boolean z10 = this.f4454n;
            this.f4454n = z10;
            this.f4453m = 1;
            obj = Q.e(q4, z10, this);
            r14 = z10;
            if (obj == enumC2303a) {
                return enumC2303a;
            }
        } else {
            if (r14 != 1) {
                if (r14 != 2) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                z6 = this.f4454n;
                th = this.l;
                AbstractC1386c.u(obj);
                iIntValue = ((Number) obj).intValue();
                r13 = z6;
                v10 = new V(th, iIntValue);
                r12 = r13;
                return new m7.i(v10, Boolean.valueOf((boolean) r12));
            }
            boolean z11 = this.f4454n;
            AbstractC1386c.u(obj);
            r14 = z11;
        }
        v10 = (c0) obj;
        r12 = r14;
        return new m7.i(v10, Boolean.valueOf((boolean) r12));
    }
}
