package p;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public abstract class k8 {
    public static void a(Configuration configuration, Configuration configuration2, Configuration configuration3) {
        LocaleList locales = configuration.getLocales();
        LocaleList locales2 = configuration2.getLocales();
        if (locales.equals(locales2)) {
            return;
        }
        configuration3.setLocales(locales2);
        configuration3.locale = configuration2.locale;
    }

    public static ac1 b(Configuration configuration) {
        return ac1.a(configuration.getLocales().toLanguageTags());
    }

    public static void c(ac1 ac1Var) {
        LocaleList.setDefault(LocaleList.forLanguageTags(((cc1) ac1Var.a).a.toLanguageTags()));
    }

    public static void d(Configuration configuration, ac1 ac1Var) {
        configuration.setLocales(LocaleList.forLanguageTags(((cc1) ac1Var.a).a.toLanguageTags()));
    }
}
