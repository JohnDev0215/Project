package H5;

import A7.m;
import N5.C0446b;
import N5.C0449e;
import N5.Z;
import S8.AbstractC0727y;
import V8.f0;
import com.stingray.kca.model.AudioResource;
import com.stingray.kca.model.AudioResponse;
import h8.AbstractC1386c;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import m7.q;
import n6.w;
import n7.AbstractC1998m;
import n7.AbstractC2000o;
import n7.z;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import s7.i;
import t6.C2441b;
import t6.h;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class d extends i implements InterfaceC2786o {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public /* synthetic */ Object f2835m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public /* synthetic */ Object f2836n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ d(int i7, InterfaceC2189c interfaceC2189c, int i10) {
        super(i7, interfaceC2189c);
        this.l = i10;
    }

    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) {
        switch (this.l) {
            case 0:
                d dVar = new d(3, (InterfaceC2189c) obj3, 0);
                dVar.f2835m = (String) obj;
                dVar.f2836n = (String) obj2;
                return dVar.invokeSuspend(q.f19267a);
            case 1:
                d dVar2 = new d((C0449e) this.f2836n, (InterfaceC2189c) obj3);
                dVar2.f2835m = (h) obj;
                return dVar2.invokeSuspend(q.f19267a);
            case 2:
                d dVar3 = new d(3, (InterfaceC2189c) obj3, 2);
                dVar3.f2835m = (Q1.f) obj;
                dVar3.f2836n = (S1.b) obj2;
                return dVar3.invokeSuspend(q.f19267a);
            default:
                d dVar4 = new d(3, (InterfaceC2189c) obj3, 3);
                dVar4.f2835m = (h) obj;
                dVar4.f2836n = (w) obj2;
                return dVar4.invokeSuspend(q.f19267a);
        }
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        Integer num;
        C2441b c2441b;
        switch (this.l) {
            case 0:
                EnumC2303a enumC2303a = EnumC2303a.l;
                AbstractC1386c.u(obj);
                String str = (String) this.f2835m;
                return "https://karaoke-web-companion-prod.stingray.com/" + ((String) this.f2836n) + "/join?id=" + str;
            case 1:
                EnumC2303a enumC2303a2 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                h hVar = (h) this.f2835m;
                Integer num2 = (hVar == null || (c2441b = hVar.d) == null) ? null : new Integer(c2441b.f22475a);
                if (num2 == null) {
                    return null;
                }
                int iIntValue = num2.intValue();
                C0449e c0449e = (C0449e) this.f2836n;
                Z z6 = c0449e.f4687b;
                AudioResponse audioResponse = (AudioResponse) ((f0) z6.f4643V.l).getValue();
                AudioResource audioResourceF = z6.f(audioResponse != null ? audioResponse.f13077b : null);
                if (audioResourceF == null || (num = audioResourceF.c) == null) {
                    return null;
                }
                int iIntValue2 = num.intValue() * 1000;
                if (iIntValue2 < iIntValue) {
                    AbstractC0727y.w(c0449e.c, null, null, new C0446b(iIntValue, iIntValue2, c0449e, null), 3);
                }
                return q.f19267a;
            case 2:
                EnumC2303a enumC2303a3 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                Q1.f fVar = (Q1.f) this.f2835m;
                S1.b bVar = (S1.b) this.f2836n;
                Set setKeySet = bVar.a().keySet();
                ArrayList arrayList = new ArrayList(AbstractC2000o.J(setKeySet, 10));
                Iterator it = setKeySet.iterator();
                while (it.hasNext()) {
                    arrayList.add(((S1.d) it.next()).f7868a);
                }
                Map<String, ?> all = fVar.f6115a.getAll();
                m.e(all, "prefs.all");
                LinkedHashMap linkedHashMap = new LinkedHashMap();
                Iterator<Map.Entry<String, ?>> it2 = all.entrySet().iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        LinkedHashMap linkedHashMap2 = new LinkedHashMap(z.q(linkedHashMap.size()));
                        for (Map.Entry entry : linkedHashMap.entrySet()) {
                            Object key = entry.getKey();
                            Object value = entry.getValue();
                            if (value instanceof Set) {
                                value = AbstractC1998m.D0((Iterable) value);
                            }
                            linkedHashMap2.put(key, value);
                        }
                        LinkedHashMap linkedHashMap3 = new LinkedHashMap();
                        for (Map.Entry entry2 : linkedHashMap2.entrySet()) {
                            if (!arrayList.contains((String) entry2.getKey())) {
                                linkedHashMap3.put(entry2.getKey(), entry2.getValue());
                            }
                        }
                        S1.b bVar2 = new S1.b(z.z(bVar.a()), false);
                        for (Map.Entry entry3 : linkedHashMap3.entrySet()) {
                            String str2 = (String) entry3.getKey();
                            Object value2 = entry3.getValue();
                            if (value2 instanceof Boolean) {
                                bVar2.e(C7.a.y(str2), value2);
                            } else if (value2 instanceof Float) {
                                m.f(str2, "name");
                                bVar2.e(new S1.d(str2), value2);
                            } else if (value2 instanceof Integer) {
                                m.f(str2, "name");
                                bVar2.e(new S1.d(str2), value2);
                            } else if (value2 instanceof Long) {
                                m.f(str2, "name");
                                bVar2.e(new S1.d(str2), value2);
                            } else if (value2 instanceof String) {
                                bVar2.e(C7.a.a0(str2), value2);
                            } else if (value2 instanceof Set) {
                                m.f(str2, "name");
                                S1.d dVar = new S1.d(str2);
                                m.d(value2, "null cannot be cast to non-null type kotlin.collections.Set<kotlin.String>");
                                bVar2.e(dVar, (Set) value2);
                            }
                        }
                        return new S1.b(z.z(bVar2.a()), true);
                    }
                    Map.Entry<String, ?> next = it2.next();
                    String key2 = next.getKey();
                    Set set = fVar.f6116b;
                    if (set != null ? set.contains(key2) : true) {
                        linkedHashMap.put(next.getKey(), next.getValue());
                    }
                }
                break;
            default:
                EnumC2303a enumC2303a4 = EnumC2303a.l;
                AbstractC1386c.u(obj);
                return new m7.i((h) this.f2835m, (w) this.f2836n);
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public d(C0449e c0449e, InterfaceC2189c interfaceC2189c) {
        super(3, interfaceC2189c);
        this.l = 1;
        this.f2836n = c0449e;
    }
}
