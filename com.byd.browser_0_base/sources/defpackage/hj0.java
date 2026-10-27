package defpackage;

import J.N;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build;
import android.os.StrictMode;
import android.os.TransactionTooLargeException;
import android.text.TextUtils;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.view.WindowManager;
import androidx.appcompat.app.AlertController;
import androidx.appcompat.app.AlertDialog;
import defpackage.aa2;
import defpackage.ij0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.chrome.browser.tab.Tab;
import org.chromium.components.external_intents.R$string;
import org.chromium.components.external_intents.R$style;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.url.GURL;

/* JADX INFO: compiled from: ExternalNavigationHandler.java */
/* JADX INFO: loaded from: classes2.dex */
public final class hj0 {
    public static final String[] c = {"com.google.android.instantapps.START", "com.google.android.instantapps.nmr1.INSTALL", "com.google.android.instantapps.nmr1.VIEW"};
    public final aj0 a;
    public AlertDialog b;

    /* JADX INFO: compiled from: ExternalNavigationHandler.java */
    public class a implements WindowAndroid.c {
        public final /* synthetic */ ij0 a;
        public final /* synthetic */ GURL b;
        public final /* synthetic */ GURL c;
        public final /* synthetic */ Intent d;

        public a(ij0 ij0Var, GURL gurl, GURL gurl2, Intent intent) {
            this.a = ij0Var;
            this.b = gurl;
            this.c = gurl2;
            this.d = intent;
        }

        @Override // org.chromium.ui.base.WindowAndroid.c
        public final void b(Intent intent, int i) {
            ij0 ij0Var = this.a;
            lb2<ij0.a> lb2Var = ij0Var.s;
            if (intent == null) {
                lb2Var.onResult(new ij0.a());
                return;
            }
            if (!intent.getAction().equals("android.intent.action.CREATE_SHORTCUT")) {
                Intent intent2 = this.d;
                intent2.setSelector(null);
                intent2.setPackage(intent.getComponent().getPackageName());
                hj0.this.x(intent2);
                lb2Var.onResult(new ij0.a(ij0Var, true));
                return;
            }
            GURL gurl = this.b;
            if (bk3.e(gurl)) {
                lb2Var.onResult(new ij0.a(gurl, ij0Var));
                return;
            }
            GURL gurl2 = this.c;
            if (gurl2.k()) {
                lb2Var.onResult(new ij0.a());
            } else {
                lb2Var.onResult(new ij0.a(gurl2, ij0Var));
            }
        }
    }

    /* JADX INFO: compiled from: ExternalNavigationHandler.java */
    public static class b<T> extends c<T> {
        public b(nw2 nw2Var) {
            super(nw2Var);
        }

        @Override // hj0.c, defpackage.nw2
        public final T get() {
            return (T) super.get();
        }
    }

    /* JADX INFO: compiled from: ExternalNavigationHandler.java */
    public static class c<T> implements nw2<T> {
        public T e;
        public nw2<T> f;

        public c(nw2<T> nw2Var) {
            this.f = nw2Var;
        }

        @Override // defpackage.nw2
        public final boolean f() {
            return true;
        }

        @Override // defpackage.nw2
        public T get() {
            nw2<T> nw2Var = this.f;
            if (nw2Var != null) {
                this.e = nw2Var.get();
                this.f = null;
            }
            return this.e;
        }
    }

    /* JADX INFO: compiled from: ExternalNavigationHandler.java */
    public static class d {
        public final int a;
        public final boolean b;
        public final GURL c;
        public final ij0 d;

        public d(int i) {
            this(i, false);
        }

        public static d a() {
            return new d(3);
        }

        public d(GURL gurl, ij0 ij0Var) {
            this(1, false);
            this.c = gurl;
            this.d = ij0Var;
        }

        public d(int i, boolean z) {
            this.a = i;
            this.b = z;
        }
    }

    /* JADX INFO: compiled from: ExternalNavigationHandler.java */
    public class e extends b<List<ResolveInfo>> {
        public final a g;

        /* JADX INFO: compiled from: ExternalNavigationHandler.java */
        public class a extends c<List<ResolveInfo>> {
            public a(Intent intent) {
                super(new p11(17, intent));
            }
        }

        public e(Intent intent, hj0 hj0Var) {
            super(new ij2(intent, 3, hj0Var));
            this.g = new a(intent);
        }
    }

    /* JADX INFO: compiled from: ExternalNavigationHandler.java */
    public static class f extends b<ResolveInfo> {
        public f(Intent intent) {
            super(new yo(19, intent));
        }
    }

    public hj0(cj0 cj0Var) {
        this.a = cj0Var;
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x001e  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0026  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void a(hj0.d r3, org.chromium.url.GURL r4) {
        /*
            boolean r4 = r4.k()
            int r0 = r3.a
            r1 = 2
            r2 = 1
            if (r4 == 0) goto L15
            if (r0 == 0) goto L13
            if (r0 == r2) goto L1e
            if (r0 == r1) goto L11
            goto L1e
        L11:
            r1 = 6
            goto L29
        L13:
            r1 = 4
            goto L29
        L15:
            if (r0 == 0) goto L22
            if (r0 == r2) goto L26
            r3 = 3
            if (r0 == r1) goto L20
            if (r0 == r3) goto L29
        L1e:
            r1 = 5
            goto L29
        L20:
            r1 = r3
            goto L29
        L22:
            boolean r3 = r3.b
            if (r3 == 0) goto L28
        L26:
            r1 = r2
            goto L29
        L28:
            r1 = 0
        L29:
            r3 = 7
            java.lang.String r4 = "Android.Intent.IntentUriNavigationResult"
            defpackage.oj2.U(r1, r3, r4)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hj0.a(hj0$d, org.chromium.url.GURL):void");
    }

    public static boolean b() {
        return N.MRiRQ_Ey(N.MWCIEpVs(0));
    }

    public static d c(Context context, Intent intent) {
        if (b()) {
            Log.i("cr_UrlHandler", "startActivity");
        }
        context.startActivity(intent);
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("org.chromium.chrome.browser.eenp");
        if (stringArrayListExtra != null && stringArrayListExtra.size() > 0) {
            n3.X0("MobileExternalNavigationDispatched");
        }
        return new d(0);
    }

    public static ArrayList e(List list) {
        boolean z;
        ActivityInfo activityInfo;
        ArrayList arrayList = new ArrayList();
        if (list == null) {
            return arrayList;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ResolveInfo resolveInfo = (ResolveInfo) it.next();
            IntentFilter intentFilter = resolveInfo.filter;
            boolean z2 = false;
            if (intentFilter != null && (intentFilter.countDataAuthorities() != 0 || intentFilter.countDataPaths() != 0)) {
                Iterator<IntentFilter.AuthorityEntry> itAuthoritiesIterator = intentFilter.authoritiesIterator();
                while (itAuthoritiesIterator != null && itAuthoritiesIterator.hasNext()) {
                    if ("*".equals(itAuthoritiesIterator.next().getHost())) {
                        z = true;
                        break;
                    }
                }
                z = false;
                if (!z && (TextUtils.isEmpty(null) || ((activityInfo = resolveInfo.activityInfo) != null && activityInfo.packageName.equals(null)))) {
                    z2 = true;
                }
            }
            if (z2) {
                ActivityInfo activityInfo2 = resolveInfo.activityInfo;
                if (activityInfo2 != null) {
                    arrayList.add(activityInfo2.packageName);
                } else {
                    arrayList.add("");
                }
            }
        }
        return arrayList;
    }

    public static boolean h(ij0 ij0Var) {
        aa2.c cVar = ij0Var.g.c;
        return (((ij0Var.d & 134217728) != 0) && ij0Var.e) || (cVar.f.c && !cVar.b);
    }

    public static boolean i(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return str.equals("about") || str.equals("chrome") || str.equals("chrome-native") || str.equals("devtools");
    }

    public static boolean j(Intent intent) {
        if (intent == null || intent.getData() == null) {
            return false;
        }
        String lastPathSegment = intent.getData().getLastPathSegment();
        return (lastPathSegment != null && lastPathSegment.endsWith(".pdf")) || "application/pdf".equals(intent.getType());
    }

    public static List n(Intent intent) {
        return wt1.c(intent, 65600);
    }

    public static boolean o(Intent intent) {
        Context context = xz.a;
        ResolveInfo resolveInfoD = wt1.d(intent, 65536);
        if (resolveInfoD == null) {
            return false;
        }
        String packageName = context.getPackageName();
        if (resolveInfoD.match != 0) {
            return true;
        }
        List<ResolveInfo> listC = wt1.c(intent, 65536);
        if (listC == null || listC.isEmpty()) {
            return false;
        }
        Iterator<ResolveInfo> it = listC.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ResolveInfo next = it.next();
            String str = next.activityInfo.packageName;
            if (!packageName.equals(str) && "com.google.android.apps.docs".equals(str) && j(intent)) {
                intent.setClassName(str, next.activityInfo.name);
                intent.putExtra("android.intent.extra.REFERRER", new Uri.Builder().scheme("android-app").authority(packageName).build());
                break;
            }
        }
        return true;
    }

    public static boolean p(List list, List list2) {
        if (list2 == null) {
            return false;
        }
        HashSet hashSet = new HashSet();
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
            hashSet.add(new ComponentName(activityInfo.packageName, activityInfo.name));
        }
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            ActivityInfo activityInfo2 = ((ResolveInfo) it2.next()).activityInfo;
            if (!hashSet.contains(new ComponentName(activityInfo2.packageName, activityInfo2.name))) {
                return false;
            }
        }
        return true;
    }

    public static boolean q(ResolveInfo resolveInfo, e eVar) {
        return !p(Arrays.asList(resolveInfo), eVar.get());
    }

    public static void r(Intent intent) {
        intent.setFlags(intent.getFlags() & 1007171600);
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setComponent(null);
        intent.setSelector(null);
    }

    public final List d(e eVar, ij0 ij0Var) {
        if ((ij0Var.d & 134217728) != 0) {
            ((cj0) this.a).getClass();
            if (Build.VERSION.SDK_INT >= 31 && bq.k0.a()) {
                return eVar.g.get();
            }
        }
        return eVar.get();
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final hj0.d f(defpackage.ij0 r7, org.chromium.url.GURL r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 225
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hj0.f(ij0, org.chromium.url.GURL, boolean):hj0$d");
    }

    public final boolean g(e eVar, ij0 ij0Var) {
        String str = ij0Var.l;
        if (str == null) {
            return false;
        }
        Iterator it = d(eVar, ij0Var).iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
            if (activityInfo != null && str.equals(activityInfo.packageName)) {
                if (!b()) {
                    return true;
                }
                Log.i("cr_UrlHandler", "Already in WebAPK");
                return true;
            }
        }
        return false;
    }

    public final boolean k(e eVar, Intent intent, ij0 ij0Var) {
        String strM = m(eVar, ij0Var);
        if (strM == null) {
            return false;
        }
        Intent intent2 = new Intent(intent);
        intent2.setPackage(strM);
        try {
            x(intent2);
            if (!b()) {
                return true;
            }
            Log.i("cr_UrlHandler", "Launched WebAPK");
            return true;
        } catch (ActivityNotFoundException unused) {
            if (b()) {
                Log.i("cr_UrlHandler", "WebAPK launch failed");
            }
            return false;
        }
    }

    public final void l(Intent intent, ij0 ij0Var, GURL gurl, boolean z) {
        if (z) {
            try {
                x(intent);
                lb2<ij0.a> lb2Var = ij0Var.s;
                if (lb2Var != null) {
                    Tab tab = ((cj0) this.a).a;
                    lb2Var.onResult(new ij0.a(ij0Var, (tab == null || tab.n() || !tab.isInitialized()) ? false : true));
                    return;
                }
                return;
            } catch (ActivityNotFoundException unused) {
            }
        }
        d dVarF = f(ij0Var, gurl, false);
        lb2<ij0.a> lb2Var2 = ij0Var.s;
        if (lb2Var2 != null) {
            if (dVarF.a == 3) {
                lb2Var2.onResult(new ij0.a());
            } else {
                lb2Var2.onResult(new ij0.a(dVarF.c, ij0Var));
            }
        }
    }

    public final String m(e eVar, ij0 ij0Var) {
        ArrayList arrayListE = e(d(eVar, ij0Var));
        if (arrayListE.size() != 1) {
            return null;
        }
        String str = (String) arrayListE.get(0);
        byte[] bArr = a8.h;
        byte[] bArr2 = a8.i;
        if (fq3.a == null) {
            fq3.a = bArr;
        }
        if (fq3.b == null) {
            fq3.b = bArr2;
        }
        if (fq3.c(xz.a, str)) {
            return (String) arrayListE.get(0);
        }
        return null;
    }

    public final d s(String str, String str2, ij0 ij0Var, GURL gurl) {
        Intent intent = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("market").authority("details").appendQueryParameter("id", str).appendQueryParameter("referrer", Uri.decode(str2)).build());
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setPackage("com.android.vending");
        intent.addFlags(268435456);
        if (!ij0Var.c.k()) {
            intent.putExtra("android.intent.extra.REFERRER", Uri.parse(ij0Var.c.j()));
        }
        List listN = n(intent);
        if (!((listN == null || listN.isEmpty()) ? false : true)) {
            if (b()) {
                Log.i("cr_UrlHandler", "Play Store not installed.");
            }
            return d.a();
        }
        if (!ij0Var.b) {
            x(intent);
            if (b()) {
                Log.i("cr_UrlHandler", "Intent to Play Store.");
            }
            return new d(0);
        }
        if (z(intent, ij0Var, gurl)) {
            if (b()) {
                Log.i("cr_UrlHandler", "Incognito intent to Play Store.");
            }
            return new d(2, false);
        }
        if (b()) {
            Log.i("cr_UrlHandler", "Failed to show incognito alert dialog.");
        }
        return d.a();
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x001e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean t(defpackage.ij0 r5, boolean r6) {
        /*
            r4 = this;
            boolean r0 = r5.k
            java.lang.String r1 = "cr_UrlHandler"
            r2 = 1
            r3 = 0
            if (r0 != 0) goto L19
            boolean r0 = r5.m
            if (r0 != 0) goto L19
            boolean r0 = b()
            if (r0 == 0) goto L17
            java.lang.String r0 = "Subframe navigation without user gesture."
            android.util.Log.i(r1, r0)
        L17:
            r0 = r2
            goto L1a
        L19:
            r0 = r3
        L1a:
            if (r0 != 0) goto L78
            if (r6 == 0) goto L20
        L1e:
            r4 = r3
            goto L42
        L20:
            boolean r0 = r5.f
            if (r0 == 0) goto L1e
            aj0 r4 = r4.a
            cj0 r4 = (defpackage.cj0) r4
            r4.getClass()
            int r4 = org.chromium.base.ApplicationStatus.getStateForApplication()
            if (r4 != r2) goto L33
            r4 = r2
            goto L34
        L33:
            r4 = r3
        L34:
            if (r4 != 0) goto L1e
            boolean r4 = b()
            if (r4 == 0) goto L41
            java.lang.String r4 = "App is not in foreground"
            android.util.Log.i(r1, r4)
        L41:
            r4 = r2
        L42:
            if (r4 != 0) goto L78
            if (r6 == 0) goto L47
            goto L5c
        L47:
            boolean r4 = r5.i
            if (r4 == 0) goto L5c
            boolean r4 = r5.j
            if (r4 != 0) goto L5c
            boolean r4 = b()
            if (r4 == 0) goto L5a
            java.lang.String r4 = "Navigation in background tab"
            android.util.Log.i(r1, r4)
        L5a:
            r4 = r2
            goto L5d
        L5c:
            r4 = r3
        L5d:
            if (r4 != 0) goto L78
            int r4 = r5.d
            r5 = 16777216(0x1000000, float:2.3509887E-38)
            r4 = r4 & r5
            if (r4 == 0) goto L73
            boolean r4 = b()
            if (r4 == 0) goto L71
            java.lang.String r4 = "Forward or back navigation"
            android.util.Log.i(r1, r4)
        L71:
            r4 = r2
            goto L74
        L73:
            r4 = r3
        L74:
            if (r4 == 0) goto L77
            goto L78
        L77:
            r2 = r3
        L78:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hj0.t(ij0, boolean):boolean");
    }

    /* JADX WARN: Removed duplicated region for block: B:116:0x0245  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x0277  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x02a8  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x02ae  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x02fd  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0344  */
    /* JADX WARN: Removed duplicated region for block: B:248:0x041f  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x047c  */
    /* JADX WARN: Removed duplicated region for block: B:279:0x0489  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x048c  */
    /* JADX WARN: Removed duplicated region for block: B:282:0x0492  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x04f9  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:392:0x0601 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:394:0x0609  */
    /* JADX WARN: Removed duplicated region for block: B:395:0x060b  */
    /* JADX WARN: Removed duplicated region for block: B:398:0x061b  */
    /* JADX WARN: Removed duplicated region for block: B:413:0x0666  */
    /* JADX WARN: Removed duplicated region for block: B:463:0x074b  */
    /* JADX WARN: Removed duplicated region for block: B:465:0x074e  */
    /* JADX WARN: Removed duplicated region for block: B:466:0x0757  */
    /* JADX WARN: Removed duplicated region for block: B:506:0x07f8  */
    /* JADX WARN: Removed duplicated region for block: B:509:0x07fd  */
    /* JADX WARN: Removed duplicated region for block: B:510:0x0803  */
    /* JADX WARN: Removed duplicated region for block: B:543:0x08a9  */
    /* JADX WARN: Removed duplicated region for block: B:546:0x08ae A[ADDED_TO_REGION, REMOVE] */
    /* JADX WARN: Removed duplicated region for block: B:547:0x08b4  */
    /* JADX WARN: Removed duplicated region for block: B:555:0x08d2  */
    /* JADX WARN: Removed duplicated region for block: B:558:0x08e8  */
    /* JADX WARN: Removed duplicated region for block: B:561:0x0905  */
    /* JADX WARN: Removed duplicated region for block: B:565:0x090c  */
    /* JADX WARN: Removed duplicated region for block: B:568:0x0921  */
    /* JADX WARN: Removed duplicated region for block: B:577:0x0965  */
    /* JADX WARN: Removed duplicated region for block: B:579:0x098a  */
    /* JADX WARN: Removed duplicated region for block: B:595:0x09cf  */
    /* JADX WARN: Removed duplicated region for block: B:597:0x09d3  */
    /* JADX WARN: Removed duplicated region for block: B:607:0x09fe  */
    /* JADX WARN: Removed duplicated region for block: B:608:0x0a05  */
    /* JADX WARN: Removed duplicated region for block: B:656:0x0ad6  */
    /* JADX WARN: Removed duplicated region for block: B:659:0x0ae8  */
    /* JADX WARN: Removed duplicated region for block: B:660:0x0aeb  */
    /* JADX WARN: Removed duplicated region for block: B:671:0x0b12  */
    /* JADX WARN: Removed duplicated region for block: B:672:0x0b18  */
    /* JADX WARN: Removed duplicated region for block: B:713:0x0c2d  */
    /* JADX WARN: Removed duplicated region for block: B:93:0x01f2  */
    /* JADX WARN: Removed duplicated region for block: B:97:0x0204  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final hj0.d u(defpackage.ij0 r32) {
        /*
            Method dump skipped, instruction units count: 3208
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.hj0.u(ij0):hj0$d");
    }

    public final AlertDialog v(Context context, final ij0 ij0Var, final Intent intent, final GURL gurl) {
        final AtomicBoolean atomicBoolean = new AtomicBoolean(false);
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        builder.i(R$string.external_app_leave_incognito_warning_title);
        builder.b(R$string.external_app_leave_incognito_warning);
        builder.f(R$string.external_app_leave_incognito_leave, new DialogInterface.OnClickListener() { // from class: dj0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                hj0 hj0Var = this.e;
                hj0Var.getClass();
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                if (atomicBoolean2.get()) {
                    return;
                }
                atomicBoolean2.set(true);
                hj0Var.l(intent, ij0Var, gurl, true);
            }
        });
        builder.e(R$string.external_app_leave_incognito_stay, new DialogInterface.OnClickListener() { // from class: ej0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                hj0 hj0Var = this.e;
                hj0Var.getClass();
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                if (atomicBoolean2.get()) {
                    return;
                }
                atomicBoolean2.set(true);
                hj0Var.l(intent, ij0Var, gurl, false);
            }
        });
        DialogInterface.OnCancelListener onCancelListener = new DialogInterface.OnCancelListener() { // from class: fj0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                hj0 hj0Var = this.e;
                hj0Var.getClass();
                AtomicBoolean atomicBoolean2 = atomicBoolean;
                if (atomicBoolean2.get()) {
                    return;
                }
                atomicBoolean2.set(true);
                hj0Var.l(intent, ij0Var, gurl, false);
            }
        };
        AlertController.AlertParams alertParams = builder.a;
        alertParams.l = onCancelListener;
        alertParams.m = new gk(1, this);
        return builder.k();
    }

    public final d w(Intent intent, boolean z, e eVar, f fVar, GURL gurl, GURL gurl2, ij0 ij0Var) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskWrites = StrictMode.allowThreadDiskWrites();
        try {
            if (intent != null) {
                try {
                    try {
                        if (j(intent)) {
                            o(intent);
                        }
                    } catch (ActivityNotFoundException unused) {
                        if (b()) {
                            Log.i("cr_UrlHandler", "Activity not found.");
                        }
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                        return d.a();
                    } catch (SecurityException unused2) {
                        StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                        return d.a();
                    }
                } catch (AndroidRuntimeException e2) {
                    Log.e("cr_UrlHandler", "Could not start Activity for intent " + intent.toString(), e2);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    return d.a();
                } catch (RuntimeException e3) {
                    ComponentName componentName = q11.a;
                    if (!(e3.getCause() instanceof TransactionTooLargeException)) {
                        throw e3;
                    }
                    Log.e("cr_IntentUtils", "Could not resolve Activity for intent " + intent.toString(), e3);
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
                    return d.a();
                }
            }
            Context contextA = xz.a(((cj0) this.a).e());
            if (contextA == null) {
                contextA = xz.a;
                intent.addFlags(268435456);
            }
            Context context = contextA;
            return z ? y(intent, eVar, fVar, gurl, gurl2, ij0Var, context) : c(context, intent);
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskWrites);
        }
    }

    public final void x(Intent intent) {
        w(intent, false, null, null, null, null, null);
    }

    public final d y(Intent intent, e eVar, f fVar, GURL gurl, GURL gurl2, ij0 ij0Var, Context context) {
        String str;
        ApplicationInfo applicationInfo;
        ResolveInfo resolveInfo = fVar.get();
        if (resolveInfo == null) {
            return d.a();
        }
        if (q(resolveInfo, eVar)) {
            return c(context, intent);
        }
        Intent intent2 = new Intent("android.intent.action.PICK_ACTIVITY");
        intent2.putExtra("android.intent.extra.INTENT", intent);
        PackageManager packageManager = context.getPackageManager();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        String packageName = context.getPackageName();
        Intent.ShortcutIconResource shortcutIconResource = new Intent.ShortcutIconResource();
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName, 128);
            str = (String) packageManager.getApplicationLabel(applicationInfo);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused) {
            str = "";
        }
        try {
            Resources resourcesForApplication = packageManager.getResourcesForApplication(applicationInfo);
            shortcutIconResource.packageName = packageName;
            String resourceName = resourcesForApplication.getResourceName(applicationInfo.icon);
            shortcutIconResource.resourceName = resourceName;
            resourcesForApplication.getDrawable(resourcesForApplication.getIdentifier(resourceName, null, null), null);
        } catch (PackageManager.NameNotFoundException | Resources.NotFoundException unused2) {
            Log.w("cr_UrlHandler", "No icon resource found for package: " + packageName);
            shortcutIconResource.packageName = "";
            shortcutIconResource.resourceName = "";
        }
        arrayList2.add(str);
        arrayList.add(shortcutIconResource);
        intent2.putExtra("android.intent.extra.shortcut.NAME", arrayList2);
        intent2.putExtra("android.intent.extra.shortcut.ICON_RESOURCE", arrayList);
        WindowAndroid windowAndroidF = ((cj0) this.a).f();
        a aVar = new a(ij0Var, gurl2, gurl, intent);
        org.chromium.ui.base.d dVar = windowAndroidF.e;
        if (dVar == null) {
            Objects.toString(intent2);
        } else {
            dVar.a(intent2, aVar, null);
        }
        return new d(2, false);
    }

    public final boolean z(Intent intent, ij0 ij0Var, GURL gurl) {
        aj0 aj0Var = this.a;
        cj0 cj0Var = (cj0) aj0Var;
        Context contextE = cj0Var.e();
        if (!((cj0Var.a != null && !cj0Var.c) && xz.a(contextE) != null)) {
            return false;
        }
        aj0Var.getClass();
        try {
            this.b = v(contextE, ij0Var, intent, gurl);
            return true;
        } catch (WindowManager.BadTokenException unused) {
            return false;
        }
    }
}
