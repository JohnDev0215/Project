package defpackage;

import android.content.Context;
import android.content.res.Configuration;
import android.os.LocaleList;
import java.util.Locale;

/* JADX INFO: compiled from: GlobalAppLocaleController.java */
/* JADX INFO: loaded from: classes.dex */
public final class xs0 {
    public static final xs0 d = new xs0();
    public Locale a = Locale.getDefault();
    public String b;
    public boolean c;

    public final Configuration a(Context context) {
        Configuration configuration = new Configuration();
        configuration.fontScale = 0.0f;
        String str = this.b;
        configuration.setLocales(LocaleList.forLanguageTags(String.format("%1$s,%2$s", str, context.getResources().getConfiguration().getLocales().toLanguageTags().replaceFirst(String.format("(^|,)%1$s$|%1$s,", str), ""))));
        return configuration;
    }
}
