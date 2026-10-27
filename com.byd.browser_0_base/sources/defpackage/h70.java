package defpackage;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import java.util.ArrayList;
import org.chromium.base.BuildInfo;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: DefaultBrowserInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class h70 extends ac<ArrayList<String>> {
    @Override // defpackage.i9
    public final Object b() {
        Context context = xz.a;
        ArrayList arrayList = new ArrayList(2);
        String str = BuildInfo.a.a.a;
        Object obj = j70.a;
        arrayList.add(str == null ? context.getString(R$string.menu_open_in_product_default) : context.getString(R$string.menu_open_in_product, str));
        PackageManager packageManager = context.getPackageManager();
        ResolveInfo resolveInfoE = wt1.e();
        SharedPreferencesManager.getInstance().l("applink.chrome_default_browser", (resolveInfoE == null || resolveInfoE.match == 0 || !TextUtils.equals(context.getPackageName(), resolveInfoE.activityInfo.packageName)) ? false : true);
        String string = (resolveInfoE == null || resolveInfoE.match == 0 || resolveInfoE.loadLabel(packageManager) == null) ? null : resolveInfoE.loadLabel(packageManager).toString();
        arrayList.add(string == null ? context.getString(R$string.menu_open_in_product_default) : context.getString(R$string.menu_open_in_product, string));
        return arrayList;
    }
}
