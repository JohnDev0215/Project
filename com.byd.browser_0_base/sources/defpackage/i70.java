package defpackage;

import android.content.Context;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import defpackage.j70;
import java.util.HashSet;
import java.util.List;

/* JADX INFO: compiled from: DefaultBrowserInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class i70 extends i9<j70.a> {
    @Override // defpackage.i9
    public final j70.a b() {
        Context context = xz.a;
        j70.a aVar = new j70.a();
        ResolveInfo resolveInfoE = wt1.e();
        if (resolveInfoE != null && resolveInfoE.match != 0) {
            aVar.d = true;
            Object obj = j70.a;
            aVar.b = TextUtils.equals(context.getPackageName(), resolveInfoE.activityInfo.applicationInfo.packageName);
            aVar.c = (resolveInfoE.activityInfo.applicationInfo.flags & 1) != 0;
        }
        HashSet hashSet = new HashSet();
        List<ResolveInfo> listC = wt1.c(wt1.a, 983040);
        if (listC != null) {
            for (ResolveInfo resolveInfo : listC) {
                if (hashSet.add(resolveInfo.activityInfo.packageName)) {
                    Object obj2 = j70.a;
                    if ((resolveInfo.activityInfo.applicationInfo.flags & 1) != 0) {
                        if (TextUtils.equals(context.getPackageName(), resolveInfo.activityInfo.applicationInfo.packageName)) {
                            aVar.a = true;
                        }
                        aVar.f++;
                    }
                }
            }
        }
        aVar.e = hashSet.size();
        return aVar;
    }

    @Override // defpackage.i9
    public final void l(j70.a aVar) {
        j70.a aVar2 = aVar;
        if (aVar2 == null) {
            return;
        }
        Object obj = j70.a;
        oj2.Q(aVar2.f, aVar2.a ? "Mobile.DefaultBrowser.SystemBrowserCount.ChromeSystem" : "Mobile.DefaultBrowser.SystemBrowserCount.ChromeNotSystem");
        oj2.Q(aVar2.e, !aVar2.d ? "Mobile.DefaultBrowser.BrowserCount.NoDefault" : aVar2.b ? "Mobile.DefaultBrowser.BrowserCount.ChromeDefault" : "Mobile.DefaultBrowser.BrowserCount.OtherDefault");
        oj2.U(!aVar2.d ? 0 : aVar2.b ? aVar2.c ? 1 : 2 : aVar2.c ? 3 : 4, 5, "Mobile.DefaultBrowser.State");
    }
}
