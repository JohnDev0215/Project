package b6;

import S8.InterfaceC0725w;
import V8.f0;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.KaraokeItem;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import m7.q;
import n7.AbstractC1998m;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class m extends s7.i implements InterfaceC2785n {
    public ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f12261m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ o f12262n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public m(o oVar, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.f12262n = oVar;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new m(this.f12262n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        return ((m) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2)).invokeSuspend(q.f19267a);
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        ArrayList arrayList;
        EnumC2303a enumC2303a = EnumC2303a.l;
        int i7 = this.f12261m;
        q qVar = q.f19267a;
        o oVar = this.f12262n;
        try {
            if (i7 == 0) {
                AbstractC1386c.u(obj);
                ArrayList arrayListA0 = AbstractC1998m.A0((Collection) oVar.f12273w.getValue());
                oVar.t(LoadingInterface$LoadingState.Loading);
                this.l = arrayListA0;
                this.f12261m = 1;
                Object objP = o.p(oVar, true, this);
                if (objP == enumC2303a) {
                    return enumC2303a;
                }
                obj = objP;
                arrayList = arrayListA0;
            } else {
                if (i7 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                arrayList = this.l;
                AbstractC1386c.u(obj);
            }
            List<KaraokeItem> list = (List) obj;
            int size = arrayList.size();
            LinkedHashSet linkedHashSet = new LinkedHashSet();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = ((KaraokeItem) it.next()).f13107a;
                if (str != null) {
                    linkedHashSet.add(str);
                }
            }
            for (KaraokeItem karaokeItem : list) {
                if (!AbstractC1998m.T(linkedHashSet, karaokeItem.f13107a)) {
                    arrayList.add(karaokeItem);
                }
            }
            if (arrayList.size() == size) {
                oVar.f12272v = true;
            } else {
                f0 f0Var = oVar.f12273w;
                f0Var.getClass();
                f0Var.m(null, arrayList);
            }
            oVar.t(LoadingInterface$LoadingState.Done);
            return qVar;
        } catch (Throwable unused) {
            oVar.t(LoadingInterface$LoadingState.Done);
            oVar.c.g(new io.sentry.hints.i(new p()));
            return qVar;
        }
    }
}
