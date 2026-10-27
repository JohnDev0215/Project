package N5;

import S8.AbstractC0727y;
import S8.InterfaceC0725w;
import com.stingray.shared.logs.segment.ActionOwner;
import com.stingray.shared.logs.segment.player.ContentCompletedReason;
import h8.AbstractC1386c;
import java.util.Iterator;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class E extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ ContentCompletedReason l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D6.a f4599m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Z f4600n;
    public final /* synthetic */ f0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ D6.b f4601p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ ActionOwner f4602q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public E(ContentCompletedReason contentCompletedReason, D6.a aVar, Z z6, f0 f0Var, D6.b bVar, ActionOwner actionOwner, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = contentCompletedReason;
        this.f4599m = aVar;
        this.f4600n = z6;
        this.o = f0Var;
        this.f4601p = bVar;
        this.f4602q = actionOwner;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new E(this.l, this.f4599m, this.f4600n, this.o, this.f4601p, this.f4602q, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        E e10 = (E) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        e10.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        String str;
        Object next;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        ContentCompletedReason contentCompletedReason = this.l;
        ContentCompletedReason contentCompletedReason2 = ContentCompletedReason.COMPLETED;
        if (contentCompletedReason == contentCompletedReason2) {
            D6.a aVar = this.f4599m;
            if (aVar.d >= aVar.f1196e) {
                contentCompletedReason = contentCompletedReason2;
            } else {
                q0 q0Var = this.f4600n.c;
                String str2 = this.o.d;
                q0Var.getClass();
                A7.m.f(str2, "uniqueId");
                Iterator it = q0Var.f4740h.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                    if (A7.m.a(((f0) next).d, str2)) {
                        break;
                    }
                }
                contentCompletedReason = next != null ? ContentCompletedReason.SONG_CHANGED : ContentCompletedReason.REMOVED_FROM_QUEUE;
            }
        }
        D6.e eVar = this.f4600n.f4654e;
        D6.b bVar = this.f4601p;
        D6.a aVar2 = this.f4599m;
        ActionOwner actionOwner = this.f4602q;
        eVar.getClass();
        A7.m.f(contentCompletedReason, "reason");
        A7.m.f(actionOwner, "actionOwner");
        if (eVar.f1214g) {
            if (aVar2.f1194a.equals(eVar.d)) {
                B5.z zVarA = D6.e.a(bVar, aVar2, actionOwner);
                int i7 = D6.c.f1207a[contentCompletedReason.ordinal()];
                if (i7 == 1) {
                    str = "Completed";
                } else if (i7 == 2) {
                    str = "Skipped";
                } else if (i7 == 3) {
                    str = "SongChanged";
                } else if (i7 == 4) {
                    str = "RemovedFromQueue";
                } else {
                    if (i7 != 5) {
                        throw new A9.k();
                    }
                    str = "Stopped";
                }
                zVarA.k(str, "reason");
                eVar.c("Karaoke Content Completed", zVarA, eVar.f1212e);
                eVar.f1214g = false;
                eVar.d = "";
            }
        }
        String str3 = this.o.f4691a.f13136a;
        if (str3 != null) {
            D6.a aVar3 = this.f4599m;
            Z z6 = this.f4600n;
            int i10 = aVar3.d;
            if (i10 > 0) {
                j6.n nVar = z6.f4656f;
                synchronized (nVar) {
                    AbstractC0727y.w(nVar.f17058b, null, null, new j6.d(nVar, str3, i10, null), 3);
                }
            }
        }
        return m7.q.f19267a;
    }
}
