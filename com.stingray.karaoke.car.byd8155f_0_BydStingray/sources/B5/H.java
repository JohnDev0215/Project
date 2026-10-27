package B5;

import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Pair;
import d5.C1078G;
import g.C1273d;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import l3.AbstractServiceC1765v;
import l3.C1754j;
import l3.C1764u;
import l3.P;
import l3.RunnableC1760p;
import l3.RunnableC1761q;
import l3.RunnableC1762s;
import l3.RunnableC1763t;
import q2.C2171b;

/* JADX INFO: loaded from: classes.dex */
public final class H extends Handler {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f656a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f657b;

    public /* synthetic */ H() {
        this.f656a = 16;
    }

    private final void a(Message message) {
        AbstractServiceC1765v abstractServiceC1765v = (AbstractServiceC1765v) this.f657b;
        if (abstractServiceC1765v == null) {
            removeCallbacksAndMessages(null);
            return;
        }
        Bundle data = message.getData();
        int i7 = message.what;
        C1764u c1764u = abstractServiceC1765v.f18610m;
        switch (i7) {
            case 1:
                Bundle bundle = data.getBundle("data_root_hints");
                P.o(bundle);
                String string = data.getString("data_package_name");
                int i10 = data.getInt("data_calling_pid");
                int i11 = data.getInt("data_calling_uid");
                C1764u c1764u2 = new C1764u(message.replyTo, 1);
                AbstractServiceC1765v abstractServiceC1765v2 = (AbstractServiceC1765v) c1764u.f18608m;
                if (string != null) {
                    String[] packagesForUid = abstractServiceC1765v2.getPackageManager().getPackagesForUid(i11);
                    if (packagesForUid != null) {
                        for (String str : packagesForUid) {
                            if (str.equals(string)) {
                                abstractServiceC1765v2.f18614r.c(new RunnableC1760p(i10, i11, bundle, string, c1764u, c1764u2));
                                return;
                            }
                        }
                    }
                } else {
                    abstractServiceC1765v2.getClass();
                }
                throw new IllegalArgumentException("Package/uid mismatch: uid=" + i11 + " package=" + string);
            case 2:
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new RunnableC1761q(c1764u, new C1764u(message.replyTo, 1), 0));
                return;
            case 3:
                Bundle bundle2 = data.getBundle("data_options");
                P.o(bundle2);
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new l3.r(c1764u, new C1764u(message.replyTo, 1), data.getString("data_media_item_id"), data.getBinder("data_callback_token"), bundle2));
                return;
            case 4:
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new b7.p(c1764u, new C1764u(message.replyTo, 1), data.getString("data_media_item_id"), data.getBinder("data_callback_token")));
                return;
            case 5:
                String string2 = data.getString("data_media_item_id");
                C1273d c1273d = (C1273d) data.getParcelable("data_result_receiver");
                C1764u c1764u3 = new C1764u(message.replyTo, 1);
                c1764u.getClass();
                if (TextUtils.isEmpty(string2) || c1273d == null) {
                    return;
                }
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new RunnableC1762s(c1764u, c1764u3, string2, c1273d));
                return;
            case 6:
                Bundle bundle3 = data.getBundle("data_root_hints");
                P.o(bundle3);
                C1764u c1764u4 = new C1764u(message.replyTo, 1);
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new RunnableC1763t(data.getInt("data_calling_uid"), data.getInt("data_calling_pid"), bundle3, data.getString("data_package_name"), c1764u, c1764u4));
                return;
            case 7:
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new RunnableC1761q(c1764u, new C1764u(message.replyTo, 1), 1));
                return;
            case 8:
                Bundle bundle4 = data.getBundle("data_search_extras");
                P.o(bundle4);
                String string3 = data.getString("data_search_query");
                C1273d c1273d2 = (C1273d) data.getParcelable("data_result_receiver");
                C1764u c1764u5 = new C1764u(message.replyTo, 1);
                c1764u.getClass();
                if (TextUtils.isEmpty(string3) || c1273d2 == null) {
                    return;
                }
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new RunnableC1762s(c1764u, c1764u5, string3, bundle4, c1273d2));
                return;
            case 9:
                Bundle bundle5 = data.getBundle("data_custom_action_extras");
                P.o(bundle5);
                String string4 = data.getString("data_custom_action");
                C1273d c1273d3 = (C1273d) data.getParcelable("data_result_receiver");
                C1764u c1764u6 = new C1764u(message.replyTo, 1);
                c1764u.getClass();
                if (TextUtils.isEmpty(string4) || c1273d3 == null) {
                    return;
                }
                ((AbstractServiceC1765v) c1764u.f18608m).f18614r.c(new l3.r(c1764u, c1764u6, string4, bundle5, c1273d3));
                return;
            default:
                h6.n.t("MBServiceCompat", "Unhandled message: " + message + "\n  Service version: 2\n  Client version: " + message.arg1);
                return;
        }
    }

    private final void b(Message message) {
        Set set;
        Pair pair = (Pair) message.obj;
        Object obj = pair.first;
        Object obj2 = pair.second;
        int i7 = message.what;
        if (i7 == 1) {
            C2171b c2171b = (C2171b) this.f657b;
            if (obj == c2171b.f21182x) {
                if (c2171b.o == 2 || c2171b.i()) {
                    c2171b.f21182x = null;
                    boolean z6 = obj2 instanceof Exception;
                    m2.b bVar = c2171b.c;
                    if (z6) {
                        bVar.t0((Exception) obj2, false);
                        return;
                    }
                    try {
                        c2171b.f21164b.j((byte[]) obj2);
                        bVar.f18945m = null;
                        HashSet hashSet = (HashSet) bVar.l;
                        d5.I iW = d5.I.w(hashSet);
                        hashSet.clear();
                        C1078G c1078gListIterator = iW.listIterator(0);
                        while (c1078gListIterator.hasNext()) {
                            C2171b c2171b2 = (C2171b) c1078gListIterator.next();
                            if (c2171b2.l()) {
                                c2171b2.h(true);
                            }
                        }
                        return;
                    } catch (Exception e10) {
                        bVar.t0(e10, true);
                        return;
                    }
                }
                return;
            }
            return;
        }
        if (i7 != 2) {
            return;
        }
        C2171b c2171b3 = (C2171b) this.f657b;
        if (obj == c2171b3.f21181w && c2171b3.i()) {
            c2171b3.f21181w = null;
            if ((obj2 instanceof Exception) || (obj2 instanceof NoSuchMethodError)) {
                c2171b3.k((Throwable) obj2, false);
                return;
            }
            try {
                byte[] bArrF = c2171b3.f21164b.f(c2171b3.f21179u, (byte[]) obj2);
                if (c2171b3.f21180v != null && bArrF != null && bArrF.length != 0) {
                    c2171b3.f21180v = bArrF;
                }
                c2171b3.o = 4;
                h2.h hVar = c2171b3.f21168h;
                synchronized (hVar.l) {
                    set = hVar.f15213n;
                }
                Iterator it = set.iterator();
                while (it.hasNext()) {
                    ((q2.k) it.next()).a();
                }
            } catch (Exception | NoSuchMethodError e11) {
                c2171b3.k(e11, true);
            }
        }
    }

    public void c(Runnable runnable) {
        if (Thread.currentThread() == getLooper().getThread()) {
            runnable.run();
        } else {
            post(runnable);
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x00b8  */
    /* JADX WARN: Removed duplicated region for block: B:65:0x00c3 A[ORIG_RETURN, RETURN] */
    @Override // android.os.Handler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void handleMessage(android.os.Message r29) {
        /*
            Method dump skipped, instruction units count: 2990
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B5.H.handleMessage(android.os.Message):void");
    }

    @Override // android.os.Handler
    public boolean sendMessageAtTime(Message message, long j3) {
        switch (this.f656a) {
            case 16:
                Bundle data = message.getData();
                ClassLoader classLoader = C1754j.class.getClassLoader();
                classLoader.getClass();
                data.setClassLoader(classLoader);
                data.putInt("data_calling_uid", Binder.getCallingUid());
                int callingPid = Binder.getCallingPid();
                if (callingPid > 0) {
                    data.putInt("data_calling_pid", callingPid);
                } else if (!data.containsKey("data_calling_pid")) {
                    data.putInt("data_calling_pid", -1);
                }
                break;
        }
        return super.sendMessageAtTime(message, j3);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ H(Looper looper, Object obj, int i7) {
        super(looper);
        this.f656a = i7;
        this.f657b = obj;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ H(Object obj, Looper looper, int i7) {
        super(looper);
        this.f656a = i7;
        this.f657b = obj;
    }
}
