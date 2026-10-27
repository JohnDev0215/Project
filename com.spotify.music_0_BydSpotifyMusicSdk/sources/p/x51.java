package p;

import com.google.protobuf.ByteString;
import com.google.protobuf.InvalidProtocolBufferException;
import com.spotify.eventsender.Fragment;
import com.spotify.eventsender.eventsender.SequenceNumberProvider$SequenceNumberType;
import com.spotify.eventsender.internalerrors.EventSenderInternalError;
import com.spotify.messages.EventSenderInternalError2NonAuth;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import kotlin.Pair;

/* JADX INFO: loaded from: classes.dex */
public final class x51 implements qk0 {
    public static final ByteString h = ByteString.d("EventSenderInternalError2NonAuth");
    public final qj0 a;
    public final nk0 b;
    public final mb2 c;
    public final xt d;
    public final ei0 e;
    public final zb0 f;
    public final wc1 g;

    public x51(oj0 oj0Var, gj0 gj0Var, mb2 mb2Var, xt xtVar, ei0 ei0Var, zb0 zb0Var, wc1 wc1Var) {
        v03.j(wc1Var, "logger");
        this.a = oj0Var;
        this.b = gj0Var;
        this.c = mb2Var;
        this.d = xtVar;
        this.e = ei0Var;
        this.f = zb0Var;
        this.g = wc1Var;
    }

    @Override // p.qk0
    public final void a(LinkedHashSet linkedHashSet, ArrayList arrayList) {
        Object next;
        EventSenderInternalError eventSenderInternalError;
        if (arrayList.isEmpty()) {
            return;
        }
        int size = arrayList.size();
        wc1 wc1Var = this.g;
        if (size > 1) {
            ((u11) wc1Var).y("Error while setting ESIENA2 datasource as successful.", new IllegalStateException("Expected only one ESIENA2 event but got " + arrayList.size() + " events."));
            return;
        }
        ai0 ai0Var = (ai0) ou.c0(arrayList);
        if (v03.b(ai0Var.b, "EventSenderInternalError2NonAuth")) {
            Iterator it = ai0Var.e.iterator();
            while (true) {
                if (!it.hasNext()) {
                    next = null;
                    break;
                } else {
                    next = it.next();
                    if (v03.b(((pr1) next).a, "message")) {
                        break;
                    }
                }
            }
            pr1 pr1Var = (pr1) next;
            if (pr1Var == null) {
                return;
            }
            try {
                EventSenderInternalError2NonAuth eventSenderInternalError2NonAuthM = EventSenderInternalError2NonAuth.m((ByteString) pr1Var.b);
                if (eventSenderInternalError2NonAuthM.j() != eventSenderInternalError2NonAuthM.h()) {
                    ((u11) wc1Var).y("Error while updating ESIENA2 reported count.", new IllegalStateException("Expected same list size, but got " + eventSenderInternalError2NonAuthM.j() + " error types and " + eventSenderInternalError2NonAuthM.h() + " total counts."));
                    return;
                }
                i51 i51VarX = vr2.x(0, eventSenderInternalError2NonAuthM.j());
                ArrayList arrayList2 = new ArrayList();
                Iterator it2 = i51VarX.iterator();
                while (((h51) it2).k) {
                    String str = (String) eventSenderInternalError2NonAuthM.k().get(((h51) it2).b());
                    v03.i(str, "key");
                    EventSenderInternalError[] eventSenderInternalErrorArrValues = EventSenderInternalError.values();
                    int length = eventSenderInternalErrorArrValues.length;
                    int i = 0;
                    while (true) {
                        if (i >= length) {
                            eventSenderInternalError = null;
                            break;
                        }
                        eventSenderInternalError = eventSenderInternalErrorArrValues[i];
                        if (v03.b(eventSenderInternalError.a(), str)) {
                            break;
                        } else {
                            i++;
                        }
                    }
                    Pair pair = eventSenderInternalError != null ? new Pair(eventSenderInternalError, Long.valueOf(((Number) ((e51) eventSenderInternalError2NonAuthM.i()).get(r4)).intValue())) : null;
                    if (pair != null) {
                        arrayList2.add(pair);
                    }
                }
                ((oj0) this.a).i(kotlin.collections.c.k0(arrayList2));
            } catch (InvalidProtocolBufferException e) {
                ((u11) wc1Var).y("Couldn't parse ESIENA2.", e);
            }
        }
    }

    @Override // p.qk0
    public final l13 get() {
        oj0 oj0Var = (oj0) this.a;
        LinkedHashMap linkedHashMapH = oj0Var.h(oj0Var.a);
        boolean zIsEmpty = linkedHashMapH.isEmpty();
        ok0 ok0Var = ok0.f;
        if (!zIsEmpty) {
            Iterator it = linkedHashMapH.entrySet().iterator();
            while (it.hasNext()) {
                nj0 nj0Var = (nj0) ((Map.Entry) it.next()).getValue();
                long j = nj0Var.a;
                if (j > 0 && j > nj0Var.b) {
                    LinkedHashMap linkedHashMapH2 = oj0Var.h(oj0Var.a);
                    LinkedHashMap linkedHashMap = new LinkedHashMap();
                    for (Map.Entry entry : linkedHashMapH2.entrySet()) {
                        if (((nj0) entry.getValue()).a > 0) {
                            linkedHashMap.put(entry.getKey(), entry.getValue());
                        }
                    }
                    ArrayList<mj0> arrayList = new ArrayList(linkedHashMap.size());
                    for (Map.Entry entry2 : linkedHashMap.entrySet()) {
                        EventSenderInternalError eventSenderInternalError = (EventSenderInternalError) entry2.getKey();
                        nj0 nj0Var2 = (nj0) entry2.getValue();
                        long j2 = nj0Var2.a;
                        arrayList.add(new mj0(eventSenderInternalError, j2, j2 - nj0Var2.b));
                    }
                    if (arrayList.isEmpty()) {
                        return ok0Var;
                    }
                    ByteString byteString = h;
                    byte[] bArrQ = byteString.q();
                    v03.i(bArrQ, "EVENT_SEQ_ID.toByteArray()");
                    long jA = this.c.a("EventSenderInternalError2NonAuth", bArrQ, SequenceNumberProvider$SequenceNumberType.e);
                    lj0 lj0VarL = EventSenderInternalError2NonAuth.l();
                    for (mj0 mj0Var : arrayList) {
                        lj0VarL.e(mj0Var.a.a());
                        lj0VarL.d((int) mj0Var.b);
                        lj0VarL.f((int) mj0Var.c);
                    }
                    byte[] byteArray = ((EventSenderInternalError2NonAuth) lj0VarL.build()).toByteArray();
                    v03.i(byteArray, "errorMessageBuilder.build().toByteArray()");
                    ((u11) this.d).getClass();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    ei0 ei0Var = this.e;
                    zb0 zb0Var = this.f;
                    zb0Var.getClass();
                    v51<Fragment> v51VarH = ux2.f(byteArray, jCurrentTimeMillis, ei0Var, zb0Var, null).h();
                    v03.i(v51VarH, "fragmentsContainer.fragmentList");
                    ArrayList arrayList2 = new ArrayList(lu.W(v51VarH));
                    for (Fragment fragment : v51VarH) {
                        arrayList2.add(new pr1(fragment.getName(), fragment.getData()));
                    }
                    return new pk0(this.b, v03.B(new ai0(-1L, "EventSenderInternalError2NonAuth", byteString, jA, arrayList2)));
                }
            }
        }
        return ok0Var;
    }
}
