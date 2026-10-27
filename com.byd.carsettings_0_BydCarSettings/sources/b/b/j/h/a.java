package b.b.j.h;

import android.content.Context;
import b.b.a.e.c;
import b.b.a.f.f;

/* JADX INFO: compiled from: KeyConstants.java */
/* JADX INFO: loaded from: classes2.dex */
public class a {
    public static String a(Context context) {
        return (context == null || context.getResources() == null || context.getResources().getConfiguration() == null || context.getResources().getConfiguration().getLocales() == null || !"zh".equalsIgnoreCase(context.getResources().getConfiguration().getLocales().get(0).getLanguage())) ? "file:android_asset/h5/nfc_user_agreement_en.html" : "file:android_asset/h5/nfc_user_agreement_zh.html";
    }

    public static String b(Context context) {
        String strB;
        String strA;
        int i = (context.getResources().getConfiguration().uiMode & 48) == 32 ? 2 : 1;
        b.b.a.d.a aVarB = c.b().a().b();
        if (aVarB != null) {
            strB = aVarB.b();
            strA = aVarB.a();
        } else {
            strB = "NO";
            strA = "eu";
        }
        String str = String.format("https://dilinkappoversea-%1$s.%2$s.auto:8666/#/text/fileNotice?agreementType=%3$d&softType=%4$d&language=%5$s&country=%6$s&modelType=%7$d", strA, "byd", 1004, 0, "en", strB, Integer.valueOf(i));
        f.b("getUseHelpH5", "helpUrl:" + str, new Object[0]);
        return str;
    }
}
