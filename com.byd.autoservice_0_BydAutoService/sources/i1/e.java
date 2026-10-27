package i1;

import android.content.Context;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import com.android.internal.app.LocalePicker;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

/* JADX INFO: compiled from: LanguageUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static String f3288a = "persist.vendor.byd.cfg.huc.steering.wheel.position";

    public static void a(Context context) {
        if (context == null) {
            return;
        }
        String str = SystemProperties.get("ro.build.type", (String) null);
        String string = Settings.System.getString(context.getContentResolver(), "BYD_RUN_MODE");
        BydLogHelper.logI("LanguageUtils", "---checkSupportAndSetDefLanguage---versionType: " + str + ", runMode：" + string);
        if ("userdebug".equals(str) && "RTL_TEST".equals(string)) {
            BydLogHelper.logE("LanguageUtils", "---checkSupportAndSetDefLanguage---Running on RTL_TEST mode, do not check language supported.");
            return;
        }
        Locale locale = context.getResources().getConfiguration().getLocales().get(0);
        BydLogHelper.logI("LanguageUtils", "---checkSupportAndSetDefLanguage---curLocale：" + locale.toString());
        String string2 = Settings.System.getString(context.getContentResolver(), "LANGUAGE_SUPPORT_ARR");
        BydLogHelper.logW("LanguageUtils", "---checkSupportAndSetDefLanguage---langSupportArr = " + string2);
        if (TextUtils.isEmpty(string2) || string2.contains(locale.toString())) {
            return;
        }
        BydLogHelper.logE("LanguageUtils", "--checkSupportAndSetDefLanguage--set default language to US, curLocale： " + locale.toString());
        LocalePicker.updateLocale(Locale.US);
    }

    public static void b(Context context) {
        if (context == null) {
            return;
        }
        String str = SystemProperties.get(f3288a, "0");
        BydLogHelper.logI("LanguageUtils", "---initSupportLanguageList---STEERING_WHEEL : " + str);
        ArrayList arrayList = new ArrayList();
        arrayList.add(Locale.SIMPLIFIED_CHINESE);
        arrayList.add(Locale.forLanguageTag("zh-HK"));
        arrayList.add(Locale.US);
        arrayList.add(Locale.forLanguageTag("es"));
        arrayList.add(Locale.forLanguageTag("es-LA"));
        arrayList.add(Locale.forLanguageTag("pt-PT"));
        arrayList.add(Locale.forLanguageTag("pt-BR"));
        arrayList.add(Locale.JAPANESE);
        arrayList.add(Locale.FRENCH);
        arrayList.add(Locale.GERMAN);
        arrayList.add(Locale.ITALIAN);
        arrayList.add(Locale.forLanguageTag("nl"));
        arrayList.add(Locale.forLanguageTag("th"));
        arrayList.add(Locale.forLanguageTag("sv"));
        arrayList.add(Locale.forLanguageTag("nb"));
        arrayList.add(Locale.forLanguageTag("fi"));
        arrayList.add(Locale.forLanguageTag("da"));
        if (Integer.valueOf(str).intValue() == 0) {
            arrayList.add(Locale.forLanguageTag("ar"));
            arrayList.add(Locale.forLanguageTag("iw"));
        }
        arrayList.add(Locale.forLanguageTag("hu"));
        arrayList.add(Locale.forLanguageTag("cs"));
        arrayList.add(Locale.forLanguageTag("pl"));
        arrayList.add(Locale.forLanguageTag("ms-MY"));
        arrayList.add(Locale.forLanguageTag("el"));
        arrayList.add(Locale.forLanguageTag("ru"));
        arrayList.add(Locale.forLanguageTag("uz-UZ"));
        arrayList.add(Locale.forLanguageTag("tr"));
        arrayList.add(Locale.forLanguageTag("in"));
        arrayList.add(Locale.KOREAN);
        arrayList.add(Locale.forLanguageTag("hi"));
        arrayList.add(Locale.forLanguageTag("sk"));
        arrayList.add(Locale.forLanguageTag("vi"));
        arrayList.add(Locale.forLanguageTag("fr-CA"));
        arrayList.add(Locale.forLanguageTag("en-AU"));
        arrayList.add(Locale.forLanguageTag("ca"));
        arrayList.add(Locale.forLanguageTag("hr"));
        arrayList.add(Locale.forLanguageTag("ro"));
        arrayList.add(Locale.forLanguageTag("uk"));
        arrayList.add(Locale.forLanguageTag("kk"));
        arrayList.add(Locale.forLanguageTag("bg"));
        StringBuilder sb = new StringBuilder();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            sb.append(((Locale) it.next()).toString());
            sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        }
        if (sb.toString().endsWith(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
            sb.deleteCharAt(sb.toString().length() - 1);
        }
        String string = sb.toString();
        BydLogHelper.logW("LanguageUtils", "---initSupport---langSupportArr: " + string);
        Settings.System.putString(context.getContentResolver(), "LANGUAGE_SUPPORT_ARR", string);
    }
}
