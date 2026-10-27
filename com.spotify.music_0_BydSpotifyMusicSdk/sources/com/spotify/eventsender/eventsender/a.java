package com.spotify.eventsender.eventsender;

import com.spotify.eventsender.droppedevents.EventState;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import p.ai0;
import p.gj0;
import p.gu0;
import p.l13;
import p.lk0;
import p.lu0;
import p.mk0;
import p.ou;
import p.pk0;
import p.qk0;
import p.rn2;
import p.ry0;
import p.u11;
import p.ub0;
import p.v03;
import p.wb0;
import p.wc1;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final List c;
    public final gu0 e;
    public final wc1 k;
    public final ub0 l;

    public a(List list, gu0 gu0Var, wc1 wc1Var, wb0 wb0Var) {
        v03.j(list, "dataSources");
        v03.j(wc1Var, "logger");
        this.c = list;
        this.e = gu0Var;
        this.k = wc1Var;
        this.l = wb0Var;
    }

    public final void a(qk0 qk0Var, mk0 mk0Var, final List list) {
        final EventState eventState = EventState.DELIVERED;
        List listJ0 = kotlin.sequences.a.J0(new rn2(new lu0(ou.Y(mk0Var.r), new ry0() { // from class: com.spotify.eventsender.eventsender.EventRouter$filterByIndices$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                int iIntValue = ((Number) obj).intValue();
                boolean z = false;
                if (iIntValue >= 0 && iIntValue < list.size()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }), new ry0() { // from class: com.spotify.eventsender.eventsender.EventRouter$filterByIndices$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                ai0 ai0Var = list.get(((Number) obj).intValue());
                ((wb0) this.l).g(ai0Var.b, eventState);
                return ai0Var;
            }
        }));
        final EventState eventState2 = EventState.REJECTED_BACKEND;
        ArrayList arrayListK0 = ou.k0(kotlin.sequences.a.J0(new rn2(new lu0(ou.Y(mk0Var.s), new ry0() { // from class: com.spotify.eventsender.eventsender.EventRouter$filterByIndices$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                int iIntValue = ((Number) obj).intValue();
                boolean z = false;
                if (iIntValue >= 0 && iIntValue < list.size()) {
                    z = true;
                }
                return Boolean.valueOf(z);
            }
        }), new ry0() { // from class: com.spotify.eventsender.eventsender.EventRouter$filterByIndices$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                ai0 ai0Var = list.get(((Number) obj).intValue());
                ((wb0) this.l).g(ai0Var.b, eventState2);
                return ai0Var;
            }
        })), listJ0);
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        Iterator it = arrayListK0.iterator();
        while (it.hasNext()) {
            linkedHashSet.add(Long.valueOf(((ai0) it.next()).a));
        }
        ((u11) this.k).v(linkedHashSet.size() + " events to be deleted from DB: " + ou.h0(linkedHashSet, null, null, null, null, 63));
        qk0Var.a(linkedHashSet, arrayListK0);
    }

    @Override // java.lang.Runnable
    public final synchronized void run() {
        v03 lk0Var;
        try {
            this.e.e();
            for (qk0 qk0Var : this.c) {
                l13 l13Var = qk0Var.get();
                while (l13Var instanceof pk0) {
                    List list = ((pk0) l13Var).f;
                    try {
                        try {
                            lk0Var = ((gj0) ((pk0) l13Var).g).b(list);
                        } catch (IOException e) {
                            lk0Var = new lk0("Error publishing events: " + e.getMessage());
                        }
                    } catch (MissingTransportException unused) {
                        lk0Var = new lk0("No HTTP transport available to publish events.");
                    }
                    if (lk0Var instanceof lk0) {
                        ((u11) this.k).v(((lk0) lk0Var).r);
                        return;
                    } else if (lk0Var instanceof mk0) {
                        a(qk0Var, (mk0) lk0Var, list);
                        l13Var = qk0Var.get();
                    }
                }
            }
            ((u11) this.k).getClass();
        } catch (Throwable th) {
            wc1 wc1Var = this.k;
            Object[] objArr = {th.getMessage()};
            wc1Var.getClass();
            ((u11) wc1Var).v(String.format("Exception during periodic event sync: %s", objArr));
        }
    }
}
