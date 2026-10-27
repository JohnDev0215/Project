package defpackage;

import J.N;
import android.animation.AnimatorSet;
import android.os.Handler;
import android.os.SystemClock;
import defpackage.hg1;
import defpackage.ne2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.BooleanSupplier;
import org.chromium.base.Callback;
import org.chromium.components.messages.b;

/* JADX INFO: compiled from: MessageQueueManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class fh1 implements ne2.a {
    public final hg1 b;
    public final mb3 a = new mb3(new d92(29, this));
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public final ne2 f = new ne2(this);

    /* JADX INFO: compiled from: MessageQueueManager.java */
    public static class a {
        public static int f;
        public final int a;
        public final pe2 b;
        public final Object c;
        public final kh1 d;
        public final boolean e;

        public a(Object obj, b bVar, pe2 pe2Var, boolean z) {
            this.b = pe2Var;
            this.c = obj;
            this.d = bVar;
            this.e = z;
            int i = f;
            f = i + 1;
            this.a = i;
        }
    }

    public fh1(hg1 hg1Var) {
        this.b = hg1Var;
    }

    public final void a(int i, a aVar) {
        kh1 kh1Var = aVar.d;
        HashMap map = this.d;
        pe2 pe2Var = aVar.b;
        List list = (List) map.get(pe2Var);
        list.remove(aVar);
        b bVar = (b) kh1Var;
        fa1.m("MessageQueueManager", "Removed message with ID %s and key %s from queue because of reason %s.", Integer.valueOf(bVar.a()), aVar.c, Integer.valueOf(i));
        if (list.isEmpty()) {
            map.remove(pe2Var);
            ((ne2.c) this.f.b.remove(pe2Var)).destroy();
        }
        Callback callback = (Callback) bVar.d.j(ng1.x);
        if (callback != null) {
            callback.onResult(Integer.valueOf(i));
        }
        bVar.i = true;
        if (i == 1 || i == 2 || i == 4) {
            if (bVar.a() == 1) {
                new Handler().postDelayed(new bn2(bVar), 1000L);
            }
            int iA = bVar.a();
            boolean z = i == 4;
            long jUptimeMillis = SystemClock.uptimeMillis() - bVar.j;
            String strT = z7.T(iA);
            oj2.Y(jUptimeMillis, "Android.Messages.TimeToAction.".concat(strT));
            if (z) {
                oj2.Y(jUptimeMillis, "Android.Messages.TimeToAction.Dismiss.".concat(strT));
            }
        }
        e();
        oj2.U(i, 10, "Android.Messages.Dismissed.".concat(z7.T(bVar.a())));
    }

    public final void b(Object obj, b bVar, pe2 pe2Var, boolean z) {
        HashMap map = this.c;
        if (map.containsKey(obj)) {
            throw new IllegalStateException("Message with the given key has already been enqueued");
        }
        HashMap map2 = this.d;
        List arrayList = (List) map2.get(pe2Var);
        if (arrayList == null) {
            arrayList = new ArrayList();
            map2.put(pe2Var, arrayList);
            ne2 ne2Var = this.f;
            ne2Var.getClass();
            int i = pe2Var.a;
            ne2.a aVar = ne2Var.a;
            ne2Var.b.put(pe2Var, i == 0 ? new ne2.d(aVar, pe2Var) : new ne2.b(aVar, pe2Var));
        }
        a aVar2 = new a(obj, bVar, pe2Var, z);
        arrayList.add(aVar2);
        map.put(obj, aVar2);
        if (N.ML2UWg2V("MessagesForAndroidStackingAnimation")) {
            f();
            return;
        }
        a aVarG = g();
        if (aVarG != null) {
            fa1.l("MessageQueueManager", "Currently displaying message with ID %s and key %s.", Integer.valueOf(((b) aVarG.d).a()), aVarG.c);
        }
        if (aVarG == aVar2) {
            int iA = bVar.a();
            oj2.U(iA, 39, "Android.Messages.Enqueued");
            oj2.U(iA, 39, "Android.Messages.Enqueued.Visible");
        } else {
            int iA2 = aVarG != null ? ((b) aVarG.d).a() : 0;
            int iA3 = bVar.a();
            oj2.U(iA3, 39, "Android.Messages.Enqueued");
            oj2.U(iA3, 39, "Android.Messages.Enqueued.Hidden");
            oj2.U(iA2, 39, "Android.Messages.Enqueued.Hiding");
        }
    }

    public final List<a> c() {
        a aVar = null;
        if (this.a.b()) {
            return Arrays.asList(null, null);
        }
        a aVar2 = null;
        for (List<a> list : this.d.values()) {
            if (!list.isEmpty() && Objects.equals((Boolean) this.e.get(((a) list.get(0)).b), Boolean.TRUE)) {
                for (a aVar3 : list) {
                    BooleanSupplier booleanSupplier = (BooleanSupplier) ((b) aVar3.d).d.j(ng1.y);
                    boolean asBoolean = booleanSupplier != null ? booleanSupplier.getAsBoolean() : true;
                    fa1.m("MessageQueueManager", "MessageStateHandler#shouldShow for message with ID %s and key %s in MessageQueueManager#getNextMessage returned %s.", Integer.valueOf(((b) aVar3.d).a()), aVar3.c, Boolean.valueOf(asBoolean));
                    if (asBoolean) {
                        boolean z = aVar3.e;
                        int i = aVar3.a;
                        if (aVar == null || (!aVar.e && z) || aVar.a > i) {
                            aVar2 = aVar;
                            aVar = aVar3;
                        } else if (aVar2 == null || (!aVar2.e && z) || aVar2.a > i) {
                            aVar2 = aVar3;
                        }
                    }
                }
            }
        }
        return Arrays.asList(aVar, aVar2);
    }

    public final void d(ih1 ih1Var) {
        HashMap map = this.e;
        pe2 pe2Var = ih1Var.b;
        int i = ih1Var.a;
        if (i != 2) {
            if (i == 1) {
                map.put(pe2Var, Boolean.FALSE);
                e();
                return;
            } else {
                if (i == 0) {
                    map.put(pe2Var, Boolean.TRUE);
                    e();
                    return;
                }
                return;
            }
        }
        List list = (List) this.d.get(pe2Var);
        map.remove(pe2Var);
        if (list != null) {
            while (!list.isEmpty()) {
                Object obj = ((a) list.get(0)).c;
                HashMap map2 = this.c;
                a aVar = (a) map2.get(obj);
                if (aVar != null) {
                    map2.remove(obj);
                    a(8, aVar);
                }
            }
        }
    }

    public final void e() {
        if (N.ML2UWg2V("MessagesForAndroidStackingAnimation")) {
            f();
        } else {
            g();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x0084  */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x0099  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.List<fh1.a> f() {
        /*
            Method dump skipped, instruction units count: 461
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.fh1.f():java.util.List");
    }

    public final a g() {
        int i = 0;
        a aVar = c().get(0);
        boolean zB = this.a.b();
        ve3 ve3Var = new ve3(16, this);
        hg1 hg1Var = this.b;
        if (hg1Var.a != aVar) {
            if (!hg1Var.e.isStarted()) {
                a aVar2 = hg1Var.a;
                if (aVar2 == null) {
                    hg1Var.a = aVar;
                    ((jq) hg1Var.d).d(new b80(hg1Var, 7, aVar, ve3Var));
                } else {
                    gg1 gg1Var = new gg1(hg1Var, ve3Var, i);
                    if (hg1Var.c != aVar2) {
                        gg1Var.run();
                    } else {
                        hg1Var.e.cancel();
                        hg1Var.e.removeAllListeners();
                        AnimatorSet animatorSetB = ((b) hg1Var.a.d).b(0, !zB);
                        if (animatorSetB == null) {
                            gg1Var.run();
                        } else {
                            AnimatorSet animatorSet = new AnimatorSet();
                            hg1Var.e = animatorSet;
                            animatorSet.play(animatorSetB);
                            ((jq) hg1Var.d).b();
                            hg1Var.e.addListener(new hg1.a(gg1Var));
                            hg1Var.i.onResult(hg1Var.e);
                        }
                    }
                }
            } else if (zB) {
                hg1Var.e.end();
                ve3Var.run();
            }
        }
        return aVar;
    }
}
