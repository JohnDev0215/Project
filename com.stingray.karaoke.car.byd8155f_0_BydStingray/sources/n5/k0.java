package N5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.kca.model.KaraokeSongResponseV2;
import com.stingray.shared.logs.segment.ActionOwner;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class k0 extends s7.i implements InterfaceC2785n {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final /* synthetic */ String f4704A;
    public i0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public List f4705m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f4706n;
    public ArrayList o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public int f4707p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public int f4708q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ boolean f4709r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ q0 f4710s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4711t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ int f4712u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ List f4713v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final /* synthetic */ Integer f4714w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final /* synthetic */ boolean f4715x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final /* synthetic */ String f4716y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final /* synthetic */ F6.b f4717z;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public k0(boolean z6, q0 q0Var, ActionOwner actionOwner, int i7, List list, Integer num, boolean z10, String str, F6.b bVar, String str2, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f4709r = z6;
        this.f4710s = q0Var;
        this.f4711t = actionOwner;
        this.f4712u = i7;
        this.f4713v = list;
        this.f4714w = num;
        this.f4715x = z10;
        this.f4716y = str;
        this.f4717z = bVar;
        this.f4704A = str2;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new k0(this.f4709r, this.f4710s, this.f4711t, this.f4712u, this.f4713v, this.f4714w, this.f4715x, this.f4716y, this.f4717z, this.f4704A, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((k0) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(m7.q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        i0 i0Var;
        Object objF;
        List list;
        int i7;
        Integer num;
        ArrayList arrayList;
        List list2 = this.f4713v;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i10 = this.f4708q;
        q0 q0Var = this.f4710s;
        try {
            if (i10 == 0) {
                AbstractC1386c.u(obj);
                if (this.f4709r) {
                    q0Var.f4740h.clear();
                }
                i0Var = new i0(q0Var.f4743k, q0Var.f4742j, true, false, this.f4711t, 8);
                int i11 = this.f4712u;
                ArrayList arrayList2 = q0Var.f4740h;
                int iMin = Math.min(i11, arrayList2.size());
                int iMin2 = Math.min(100 - arrayList2.size(), list2.size());
                List listSubList = list2.subList(0, iMin2);
                Integer num2 = this.f4714w;
                Integer num3 = (num2 == null || num2.intValue() <= iMin2) ? null : new Integer(num2.intValue() - iMin2);
                ArrayList arrayList3 = new ArrayList();
                K5.F f10 = q0Var.f4736b;
                List listZ0 = AbstractC1998m.z0(listSubList);
                this.l = i0Var;
                this.f4705m = listSubList;
                this.f4706n = num3;
                this.o = arrayList3;
                this.f4707p = iMin;
                this.f4708q = 1;
                f10.getClass();
                Z8.e eVar = S8.E.f8084a;
                objF = AbstractC0727y.F(Z8.d.f11010n, new K5.C(f10, listZ0, null), this);
                if (objF == enumC2303a) {
                    return enumC2303a;
                }
                list = listSubList;
                i7 = iMin;
                num = num3;
                arrayList = arrayList3;
            } else {
                if (i10 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i7 = this.f4707p;
                arrayList = this.o;
                Integer num4 = this.f4706n;
                List list3 = this.f4705m;
                i0 i0Var2 = this.l;
                AbstractC1386c.u(obj);
                num = num4;
                list = list3;
                i0Var = i0Var2;
                objF = obj;
            }
            Iterator it = ((List) objF).iterator();
            while (it.hasNext()) {
                arrayList.add(new f0((KaraokeSongResponseV2) it.next()));
            }
            int i12 = q0Var.f4743k;
            ArrayList arrayList4 = q0Var.f4740h;
            if (i12 < 0) {
                arrayList4.addAll(arrayList);
                i0Var.f4699a = 0;
                i0Var.f4700b = (f0) arrayList4.get(0);
            } else if (this.f4715x) {
                i0Var.f4699a = i7;
                i0Var.f4700b = (f0) arrayList.get(0);
                arrayList4.addAll(i0Var.f4699a, arrayList);
            } else {
                arrayList4.addAll(i7, arrayList);
            }
            String str = this.f4716y;
            F6.b bVar = this.f4717z;
            if (str == null || Q8.h.M0(str)) {
                String str2 = this.f4704A;
                if (str2 != null && !Q8.h.M0(str2)) {
                    q0Var.c.b(this.f4711t, bVar != null ? bVar.f2425a : null, num, this.f4704A, bVar != null ? bVar.f2426b : null, list, this.f4709r);
                }
            } else {
                F6.a aVar = q0Var.c;
                String str3 = bVar != null ? bVar.f2426b : null;
                aVar.c(this.f4711t, bVar != null ? bVar.f2425a : null, num, this.f4716y, str3, list, this.f4709r);
            }
            q0Var.m(i0Var, false);
        } catch (Exception unused) {
            q0Var.k();
        }
        return m7.q.f19267a;
    }
}
