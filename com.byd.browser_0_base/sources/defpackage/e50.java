package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import androidx.browser.customtabs.CustomTabsSessionToken;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.chrome.browser.base.SplitCompatCustomTabsService;
import org.chromium.chrome.browser.customtabs.CustomTabBottomBarView;
import org.chromium.chrome.browser.customtabs.CustomTabsConnection;
import org.chromium.chrome.browser.customtabs.a;
import org.chromium.chrome.browser.customtabs.b;
import org.chromium.chrome.browser.toolbar.g;

/* JADX INFO: compiled from: CustomTabsConnectionServiceImpl.java */
/* JADX INFO: loaded from: classes.dex */
public class e50 extends SplitCompatCustomTabsService.a {
    public CustomTabsConnection a;
    public Intent b;

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final void a(CustomTabsSessionToken customTabsSessionToken) {
        CustomTabsConnection customTabsConnection = this.a;
        customTabsConnection.getClass();
        PostTask.d(rh3.a, new a9(customTabsConnection, 4, customTabsSessionToken));
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final Bundle b(String str) {
        CustomTabsConnection customTabsConnection = this.a;
        customTabsConnection.getClass();
        str.getClass();
        if (!str.equals("getGreatestScrollPercentage") || !customTabsConnection.i("CCTRealTimeEngagementSignals")) {
            return null;
        }
        nw2<Integer> nw2Var = customTabsConnection.h;
        Integer num = nw2Var != null ? nw2Var.get() : null;
        if (num == null) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt("greatestScrollPercentage", num.intValue());
        return bundle;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final boolean c(CustomTabsSessionToken customTabsSessionToken, Uri uri, Bundle bundle, List<Bundle> list) {
        if (!n()) {
            return false;
        }
        CustomTabsConnection customTabsConnection = this.a;
        customTabsConnection.getClass();
        TraceEvent traceEventH0 = TraceEvent.H0("CustomTabsConnection.mayLaunchUrl", null);
        try {
            boolean zM = customTabsConnection.m(customTabsSessionToken, uri, bundle, list);
            customTabsConnection.k(Boolean.valueOf(zM), "mayLaunchUrl(" + uri + ")");
            if (traceEventH0 != null) {
                traceEventH0.close();
            }
            return zM;
        } catch (Throwable th) {
            if (traceEventH0 != null) {
                try {
                    traceEventH0.close();
                } catch (Throwable unused) {
                }
            }
            throw th;
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final boolean d(CustomTabsSessionToken customTabsSessionToken) {
        boolean z;
        CustomTabsConnection customTabsConnection = this.a;
        customTabsConnection.getClass();
        b bVar = new b(customTabsConnection);
        d50 d50Var = new d50(customTabsSessionToken);
        z22 z22Var = new z22(d50Var);
        a aVar = customTabsConnection.c;
        int callingUid = Binder.getCallingUid();
        synchronized (aVar) {
            z = false;
            if (customTabsSessionToken.c != null) {
                if (aVar.c.containsKey(customTabsSessionToken)) {
                    a.e eVar = (a.e) aVar.c.get(customTabsSessionToken);
                    eVar.b = customTabsSessionToken.c;
                    eVar.p = false;
                } else {
                    aVar.c.put(customTabsSessionToken, new a.e(xz.a, callingUid, customTabsSessionToken.c, bVar, z22Var, d50Var));
                }
                z = true;
            }
        }
        customTabsConnection.k(Boolean.valueOf(z), "newSession()");
        return z;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final void e(Intent intent) {
        this.b = intent;
        CustomTabsConnection customTabsConnectionE = CustomTabsConnection.e();
        this.a = customTabsConnectionE;
        customTabsConnectionE.k(Boolean.TRUE, "Service#onBind()");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final void f() {
        b62.b().d();
        jb2.b();
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final void g() {
        CustomTabsConnection customTabsConnection = this.a;
        if (customTabsConnection != null) {
            customTabsConnection.k(Boolean.TRUE, "Service#onUnbind()");
        }
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final int h(CustomTabsSessionToken customTabsSessionToken, String str) {
        if (!n()) {
            return -1;
        }
        CustomTabsConnection customTabsConnection = this.a;
        customTabsConnection.e.get();
        if (!CustomTabsConnection.h()) {
            customTabsConnection.b.a(customTabsSessionToken);
        }
        a aVar = customTabsConnection.c;
        aVar.getClass();
        int iIntValue = ((Integer) aVar.a(customTabsSessionToken, -3, new ke0(11, str))).intValue();
        customTabsConnection.k(Integer.valueOf(iIntValue), "postMessage");
        return iIntValue;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final boolean i(int i, Uri uri, CustomTabsSessionToken customTabsSessionToken) {
        this.a.getClass();
        w40 w40VarF = ((s50) pp.d()).f();
        w40VarF.getClass();
        if (uri == null) {
            Log.w("cr_CustomTabFiles", "Received a null uri");
        } else if (i != 1) {
            C0175b.s("Unknown FilePurpose ", i, "cr_CustomTabFiles");
        } else {
            Bitmap bitmapC = pm0.c(w40VarF.a, uri);
            if (bitmapC != null) {
                w40VarF.b.get().a.put(customTabsSessionToken, bitmapC);
                w40VarF.c = true;
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0028  */
    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean j(androidx.browser.customtabs.CustomTabsSessionToken r7, android.net.Uri r8) {
        /*
            r6 = this;
            ft1 r8 = defpackage.ft1.a(r8)
            r0 = 0
            if (r8 != 0) goto L8
            return r0
        L8:
            org.chromium.chrome.browser.customtabs.CustomTabsConnection r6 = r6.a
            java.util.concurrent.atomic.AtomicBoolean r1 = r6.e
            boolean r1 = r1.get()
            if (r1 != 0) goto L13
            goto L51
        L13:
            boolean r1 = org.chromium.chrome.browser.customtabs.CustomTabsConnection.h()
            r2 = 1
            if (r1 != 0) goto L28
            bj2 r1 = r6.b
            d40 r1 = r1.a(r7)
            if (r1 == 0) goto L24
            r1 = r2
            goto L25
        L24:
            r1 = r0
        L25:
            if (r1 != 0) goto L28
            goto L51
        L28:
            org.chromium.chrome.browser.customtabs.a r1 = r6.c
            r1.getClass()
            java.lang.Boolean r3 = java.lang.Boolean.FALSE
            wd0 r4 = new wd0
            r5 = 4
            r4.<init>(r5)
            java.lang.Object r1 = r1.a(r7, r3, r4)
            java.lang.Boolean r1 = (java.lang.Boolean) r1
            boolean r1 = r1.booleanValue()
            if (r1 != 0) goto L42
            goto L51
        L42:
            int r0 = android.os.Binder.getCallingUid()
            d73 r1 = defpackage.rh3.a
            vy2 r3 = new vy2
            r3.<init>(r6, r7, r0, r8)
            org.chromium.base.task.PostTask.c(r1, r3)
            r0 = r2
        L51:
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r1 = "requestPostMessageChannel() with origin "
            r7.<init>(r1)
            java.lang.String r8 = r8.toString()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            java.lang.Boolean r8 = java.lang.Boolean.valueOf(r0)
            r6.k(r8, r7)
            java.lang.String r6 = "CustomTabs.PostMessage.RequestPostMessageChannel"
            defpackage.oj2.O(r6, r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.e50.j(androidx.browser.customtabs.CustomTabsSessionToken, android.net.Uri):boolean");
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final boolean k(CustomTabsSessionToken customTabsSessionToken, Bundle bundle) {
        Bundle bundle2;
        ArrayList<Bundle> parcelableArrayList;
        Bitmap bitmap;
        String strK;
        boolean zBooleanValue = false;
        if (!n()) {
            return false;
        }
        CustomTabsConnection customTabsConnection = this.a;
        if (customTabsConnection.d) {
            fa1.k("ChromeConnection", "updateVisuals: %s", CustomTabsConnection.a(bundle));
        }
        final d40 d40VarA = customTabsConnection.b.a(customTabsSessionToken);
        if (d40VarA != null) {
            ComponentName componentName = q11.a;
            final int[] intArray = null;
            try {
                bundle2 = bundle.getBundle("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE");
            } catch (Throwable unused) {
                Log.e("cr_IntentUtils", "getBundle failed on bundle " + bundle);
                bundle2 = null;
            }
            final ArrayList arrayList = new ArrayList();
            final ArrayList arrayList2 = new ArrayList();
            final ArrayList arrayList3 = new ArrayList();
            if (bundle2 != null) {
                int iK = q11.k("android.support.customtabs.customaction.ID", 0, bundle2);
                Bitmap bitmap2 = (Bitmap) q11.m(bundle2, "android.support.customtabs.customaction.ICON");
                if (bitmap2 == null) {
                    bitmap2 = null;
                }
                String strK2 = o20.k(bundle2);
                if (bitmap2 != null && strK2 != null) {
                    arrayList.add(Integer.valueOf(iK));
                    arrayList2.add(strK2);
                    arrayList3.add(bitmap2);
                }
            }
            try {
                parcelableArrayList = bundle.getParcelableArrayList("android.support.customtabs.extra.TOOLBAR_ITEMS");
            } catch (Throwable unused2) {
                Log.e("cr_IntentUtils", "getParcelableArrayList failed on bundle " + bundle);
                parcelableArrayList = null;
            }
            if (parcelableArrayList != null) {
                for (Bundle bundle3 : parcelableArrayList) {
                    int iK2 = q11.k("android.support.customtabs.customaction.ID", 0, bundle3);
                    if (!arrayList.contains(Integer.valueOf(iK2))) {
                        if (bundle3 == null || (bitmap = (Bitmap) q11.m(bundle3, "android.support.customtabs.customaction.ICON")) == null) {
                            bitmap = null;
                        }
                        if (bitmap != null && (strK = o20.k(bundle3)) != null) {
                            arrayList.add(Integer.valueOf(iK2));
                            arrayList2.add(strK);
                            arrayList3.add(bitmap);
                        }
                    }
                }
            }
            boolean zBooleanValue2 = true;
            if (!arrayList.isEmpty()) {
                d73 d73Var = rh3.a;
                Callable callable = new Callable() { // from class: x40
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        m20 next;
                        boolean z;
                        g gVar;
                        HashSet hashSet = CustomTabsConnection.o;
                        boolean z2 = true;
                        int i = 0;
                        while (true) {
                            List list = arrayList;
                            if (i >= list.size()) {
                                return Boolean.valueOf(z2);
                            }
                            int iIntValue = ((Integer) list.get(i)).intValue();
                            Bitmap bitmap3 = (Bitmap) arrayList3.get(i);
                            String str = (String) arrayList2.get(i);
                            d40 d40Var = d40VarA;
                            Iterator<m20> it = d40Var.e.e().iterator();
                            while (true) {
                                if (!it.hasNext()) {
                                    next = null;
                                    break;
                                }
                                next = it.next();
                                if (iIntValue == next.getId()) {
                                    break;
                                }
                            }
                            if (next == null) {
                                fa1.k("CctSessionHandler", "Custom toolbar button with ID %d not found", Integer.valueOf(iIntValue));
                            } else {
                                next.b(bitmap3, str);
                                if (next.c()) {
                                    u40 u40Var = d40Var.f.get();
                                    Activity activity = u40Var.d;
                                    if (next.g(activity)) {
                                        int id = next.getId();
                                        List<m20> listO = u40Var.a.o();
                                        int i2 = 0;
                                        while (true) {
                                            if (i2 >= listO.size()) {
                                                i2 = -1;
                                                break;
                                            }
                                            if (listO.get(i2).getId() == id) {
                                                break;
                                            }
                                            i2++;
                                        }
                                        if (i2 != -1 && (gVar = u40Var.k) != null) {
                                            gVar.k.a.G(i2, next.a(activity), next.d());
                                        }
                                    }
                                } else {
                                    d30 d30Var = d40Var.g.get();
                                    ImageButton imageButton = (ImageButton) d30Var.b().findViewById(next.getId());
                                    imageButton.setContentDescription(next.d());
                                    imageButton.setImageDrawable(next.a(d30Var.e));
                                }
                                z = true;
                                z2 &= z;
                                i++;
                            }
                            z = false;
                            z2 &= z;
                            i++;
                        }
                    }
                };
                Object obj = PostTask.a;
                FutureTask futureTask = new FutureTask(callable);
                PostTask.d(d73Var, futureTask);
                try {
                    zBooleanValue2 = true & ((Boolean) futureTask.get()).booleanValue();
                } catch (Exception e) {
                    throw new uv(e);
                }
            }
            if (bundle.containsKey("android.support.customtabs.extra.EXTRA_REMOTEVIEWS")) {
                final RemoteViews remoteViews = (RemoteViews) q11.m(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS");
                try {
                    intArray = bundle.getIntArray("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS");
                } catch (Throwable unused3) {
                    Log.e("cr_IntentUtils", "getIntArray failed on bundle " + bundle);
                }
                final PendingIntent pendingIntent = (PendingIntent) q11.m(bundle, "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT");
                d73 d73Var2 = rh3.a;
                Callable callable2 = new Callable() { // from class: y40
                    @Override // java.util.concurrent.Callable
                    public final Object call() {
                        HashSet hashSet = CustomTabsConnection.o;
                        d30 d30Var = d40VarA.g.get();
                        d30Var.getClass();
                        n3.X0("CustomTabsRemoteViewsUpdated");
                        RemoteViews remoteViews2 = remoteViews;
                        boolean zE = true;
                        if (remoteViews2 == null) {
                            CustomTabBottomBarView customTabBottomBarView = d30Var.m;
                            if (customTabBottomBarView == null) {
                                zE = false;
                            } else {
                                if (customTabBottomBarView != null) {
                                    customTabBottomBarView.setSwipeHandler(null);
                                    d30Var.p = null;
                                }
                                d30Var.m.animate().alpha(0.0f).translationY(d30Var.m.getHeight()).setInterpolator(pc.c).setDuration(400L).withEndAction(new f30(d30Var)).start();
                                d30Var.g.k(0, 0);
                                d30Var.o = null;
                                d30Var.n = null;
                            }
                        } else {
                            d30Var.o = intArray;
                            d30Var.n = pendingIntent;
                            if (d30Var.b().getChildCount() > 1) {
                                d30Var.b().removeViewAt(1);
                            }
                            zE = d30Var.e(remoteViews2);
                        }
                        return Boolean.valueOf(zE);
                    }
                };
                Object obj2 = PostTask.a;
                FutureTask futureTask2 = new FutureTask(callable2);
                PostTask.d(d73Var2, futureTask2);
                try {
                    zBooleanValue2 &= ((Boolean) futureTask2.get()).booleanValue();
                } catch (Exception e2) {
                    throw new uv(e2);
                }
            }
            if (bq.f.a() && bundle.containsKey("androidx.browser.customtabs.extra.SECONDARY_TOOLBAR_SWIPE_UP_ACTION")) {
                PendingIntent pendingIntent2 = (PendingIntent) q11.m(bundle, "androidx.browser.customtabs.extra.SECONDARY_TOOLBAR_SWIPE_UP_ACTION");
                d73 d73Var3 = rh3.a;
                z40 z40Var = new z40(d40VarA, 0, pendingIntent2);
                Object obj3 = PostTask.a;
                FutureTask futureTask3 = new FutureTask(z40Var);
                PostTask.d(d73Var3, futureTask3);
                try {
                    zBooleanValue = ((Boolean) futureTask3.get()).booleanValue() & zBooleanValue2;
                } catch (Exception e3) {
                    throw new uv(e3);
                }
            } else {
                zBooleanValue = zBooleanValue2;
            }
            customTabsConnection.k(Boolean.valueOf(zBooleanValue), "updateVisuals()");
        }
        return zBooleanValue;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final boolean l(int i, Uri uri, CustomTabsSessionToken customTabsSessionToken) {
        ft1 ft1VarA = ft1.a(uri);
        boolean zG = false;
        if (ft1VarA == null) {
            return false;
        }
        CustomTabsConnection customTabsConnection = this.a;
        if (customTabsConnection.e.get()) {
            a aVar = customTabsConnection.c;
            synchronized (aVar) {
                zG = aVar.g(customTabsSessionToken, i, ft1VarA, false);
            }
        } else {
            customTabsConnection.c.b(customTabsSessionToken).U0(i, Uri.parse(ft1VarA.toString()), false, null);
        }
        return zG;
    }

    @Override // org.chromium.chrome.browser.base.SplitCompatCustomTabsService.a
    public final boolean m(long j) {
        if (n()) {
            return this.a.u();
        }
        return false;
    }

    public final boolean n() {
        if (this.b == null) {
            return true;
        }
        if (gn0.b(false, true)) {
            return false;
        }
        this.b = null;
        return true;
    }
}
