package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;
import com.android.webview.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.chromium.weblayer_private.TabImpl;

/* JADX INFO: renamed from: pQ, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class C1329pQ implements Bx {
    public final Context a = Is.a;
    public final TabImpl b;
    public boolean c;

    public C1329pQ(TabImpl tabImpl) {
        this.b = tabImpl;
    }

    public static void a(Intent intent) {
        ResolveInfo resolveInfoResolveActivity;
        List<ResolveInfo> listB;
        if (intent == null || !d(intent)) {
            return;
        }
        Context context = Is.a;
        try {
            C0683dt c0683dtA = C0683dt.A();
            try {
                resolveInfoResolveActivity = Is.a.getPackageManager().resolveActivity(intent, 0);
                c0683dtA.close();
            } finally {
            }
        } catch (RuntimeException e) {
            Zs.a(e, intent);
            resolveInfoResolveActivity = null;
        }
        if (resolveInfoResolveActivity == null) {
            return;
        }
        String packageName = context.getPackageName();
        if (resolveInfoResolveActivity.match != 0 || (listB = Zs.b(intent, R.anim.abc_fade_in)) == null || listB.isEmpty()) {
            return;
        }
        for (ResolveInfo resolveInfo : listB) {
            String str = resolveInfo.activityInfo.packageName;
            if (!packageName.equals(str) && "com.google.android.apps.docs".equals(str) && d(intent)) {
                intent.setClassName(str, resolveInfo.activityInfo.name);
                intent.putExtra("android.intent.extra.REFERRER", new Uri.Builder().scheme("android-app").authority(packageName).build());
                return;
            }
        }
    }

    public static ArrayList c(List list, String str) {
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

    public static boolean d(Intent intent) {
        if (intent == null || intent.getData() == null) {
            return false;
        }
        String lastPathSegment = intent.getData().getLastPathSegment();
        return (lastPathSegment != null && lastPathSegment.endsWith(".pdf")) || "application/pdf".equals(intent.getType());
    }

    public final Context b() {
        if (this.b.j.v() == null) {
            return this.a;
        }
        Activity activityA = Is.a(this.b.j.v());
        return activityA == null ? this.a : activityA;
    }

    public List e(Intent intent) {
        return Zs.b(intent, 64);
    }

    public final void f(Intent intent) {
        ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("org.chromium.chrome.browser.eenp");
        if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
            return;
        }
        Jt.a("MobileExternalNavigationDispatched");
    }

    public void g(Intent intent, boolean z) {
        try {
            a(intent);
            Context contextB = b();
            if (!(contextB instanceof Activity)) {
                intent.addFlags(268435456);
            }
            contextB.startActivity(intent);
            ArrayList<String> stringArrayListExtra = intent.getStringArrayListExtra("org.chromium.chrome.browser.eenp");
            if (stringArrayListExtra == null || stringArrayListExtra.size() <= 0) {
                return;
            }
            Jt.a("MobileExternalNavigationDispatched");
        } catch (RuntimeException e) {
            Os.a(e, intent);
        }
    }
}
