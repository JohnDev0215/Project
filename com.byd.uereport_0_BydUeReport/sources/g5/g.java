package g5;

import android.os.Build;
import android.os.LocaleList;
import android.text.TextUtils;
import com.byd.systemsettings.system.BydPowerManagerDevice;
import java.util.Locale;

/* JADX INFO: compiled from: LanguageUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class g {
    public static String a() {
        Locale locale = Build.VERSION.SDK_INT >= 24 ? LocaleList.getDefault().get(0) : Locale.getDefault();
        String language = locale.getLanguage();
        String country = locale.getCountry();
        a.a("language:" + language + ",country:" + country);
        if (language.equals("zh")) {
            return "zh_CN";
        }
        if (TextUtils.isEmpty(country)) {
            return language;
        }
        return language + BydPowerManagerDevice.SEPARATOR_K_V + country;
    }
}
