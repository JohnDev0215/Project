package p;

import android.content.res.Configuration;
import android.os.LocaleList;

/* JADX INFO: loaded from: classes.dex */
public abstract class yw {
    public static LocaleList a(Configuration configuration) {
        return configuration.getLocales();
    }

    public static void b(Configuration configuration, ac1 ac1Var) {
        configuration.setLocales((LocaleList) ac1Var.a.a());
    }
}
