package spi;

import android.content.Context;
import android.text.TextUtils;
import com.byd.car.locale.ccTLD;
import java.util.Locale;

/* JADX INFO: compiled from: CommonUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class i2 {
    public static boolean a(Context context) {
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        if (locale == null) {
            return false;
        }
        String language = locale.getLanguage();
        if (TextUtils.isEmpty(language)) {
            return false;
        }
        return "zh".equals(language) || ccTLD.ARGENTINA.equals(language) || "iw".equals(language);
    }
}
