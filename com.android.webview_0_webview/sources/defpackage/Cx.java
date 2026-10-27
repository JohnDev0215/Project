package defpackage;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class Cx {
    public final Bx a;

    public Cx(Bx bx) {
        this.a = bx;
    }

    public final boolean a(List list, Ex ex) {
        String str = ex.k;
        if (str == null) {
            return false;
        }
        Iterator it = list.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = ((ResolveInfo) it.next()).activityInfo;
            if (activityInfo != null && str.equals(activityInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public final boolean b(List list, Intent intent) {
        Objects.requireNonNull((C1329pQ) this.a);
        ArrayList arrayListC = C1329pQ.c(list, null);
        if (arrayListC.size() == 1) {
            Bx bx = this.a;
            Objects.requireNonNull((C1329pQ) bx);
        }
        return false;
    }

    public final int c(String str, String str2, Ex ex) {
        Intent intent = new Intent("android.intent.action.VIEW", new Uri.Builder().scheme("market").authority("details").appendQueryParameter("id", str).appendQueryParameter("referrer", Uri.decode(str2)).build());
        intent.addCategory("android.intent.category.BROWSABLE");
        intent.setPackage("com.android.vending");
        intent.addFlags(268435456);
        String str3 = ex.c;
        if (str3 != null) {
            intent.putExtra("android.intent.extra.REFERRER", Uri.parse(str3));
        }
        List listE = ((C1329pQ) this.a).e(intent);
        if (!((listE == null || listE.isEmpty()) ? false : true)) {
            return 3;
        }
        if (ex.b) {
            ((C1329pQ) this.a).g(intent, false);
            return 2;
        }
        ((C1329pQ) this.a).g(intent, false);
        return 0;
    }
}
