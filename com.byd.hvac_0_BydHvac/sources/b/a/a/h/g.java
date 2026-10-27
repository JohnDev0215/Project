package b.a.a.h;

import android.content.Context;
import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class g {
    public static boolean a(Context context) {
        Locale locale;
        LocaleList locales = context.getResources().getConfiguration().getLocales();
        if (locales == null || locales.size() <= 0 || (locale = locales.get(0)) == null) {
            return true;
        }
        return locale.toLanguageTag().equals("zh-CN");
    }
}
