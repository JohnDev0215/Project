package defpackage;

import java.util.Comparator;
import org.chromium.base.LocaleUtils;
import org.chromium.chrome.browser.preferences.SharedPreferencesManager;

/* JADX INFO: compiled from: AppLocaleUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class u6 {
    public static final a a = new a();

    /* JADX INFO: compiled from: AppLocaleUtils.java */
    public class a implements Comparator<String> {
        @Override // java.util.Comparator
        public final int compare(String str, String str2) {
            return LocaleUtils.c(str).compareTo(LocaleUtils.c(str2));
        }
    }

    public static String a() {
        if (yj.a()) {
            return null;
        }
        return SharedPreferencesManager.getInstance().g("Chrome.Language.ApplicationOverrideLanguage", null);
    }
}
