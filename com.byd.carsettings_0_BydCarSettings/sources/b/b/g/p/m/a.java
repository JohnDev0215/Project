package b.b.g.p.m;

import android.content.Context;
import android.content.res.Resources;
import android.provider.Settings;
import android.text.TextUtils;
import b.b.a.h.j;
import com.byd.carsettings.R;
import com.byd.carsettings.system.model.BydLanguage;
import com.bydux.api.common.tools.util.ListUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* JADX INFO: compiled from: LanguageUtils.java */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static List<BydLanguage> f1420a = new ArrayList();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static Map<String, String> f1421b = new HashMap();

    public static int a(Locale locale) {
        if (locale == null) {
            return -1;
        }
        b.b.m.c.a.d("LanguageUtils", "---getAllLanguageIndex---locale: " + locale.toString());
        List<BydLanguage> listB = b();
        for (int i = 0; i < listB.size(); i++) {
            if (listB.get(i).getLocale().toString().equals(locale.toString())) {
                return i;
            }
        }
        return -1;
    }

    public static List<BydLanguage> b() {
        ArrayList arrayList = new ArrayList();
        for (BydLanguage bydLanguage : f1420a) {
            if (!j.c(bydLanguage.getText()) && !j.c(bydLanguage.getDescription())) {
                arrayList.add(bydLanguage);
            }
        }
        return arrayList;
    }

    public static int c(Locale locale) {
        if (locale == null) {
            return -1;
        }
        b.b.m.c.a.d("LanguageUtils", "---getLanguageIndex---locale: " + locale.toString());
        List<BydLanguage> listE = e();
        for (int i = 0; i < listE.size(); i++) {
            if (listE.get(i).getLocale().toString().equals(locale.toString())) {
                return i;
            }
        }
        return -1;
    }

    public static String d(Locale locale) {
        if (locale == null) {
            return "";
        }
        b.b.m.c.a.d("LanguageUtils", "---getLanguageText---locale: " + locale.toString());
        List<BydLanguage> listE = e();
        for (int i = 0; i < listE.size(); i++) {
            BydLanguage bydLanguage = listE.get(i);
            if (bydLanguage.getLocale().toString().equals(locale.toString())) {
                return bydLanguage.getText();
            }
        }
        for (int i2 = 0; i2 < f1420a.size(); i2++) {
            BydLanguage bydLanguage2 = f1420a.get(i2);
            if (bydLanguage2.getLocale().toString().equals(locale.toString())) {
                return bydLanguage2.getText();
            }
        }
        return "";
    }

    public static List<BydLanguage> e() {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < f1420a.size(); i++) {
            BydLanguage bydLanguage = f1420a.get(i);
            if (f1421b.get(bydLanguage.getLocale().toString()) != null) {
                arrayList.add(bydLanguage);
            }
        }
        b.b.m.c.a.d("LanguageUtils", "---getSupportLanguageList---list: " + arrayList);
        return arrayList;
    }

    public static void f(Context context) {
        f1421b.put(Locale.SIMPLIFIED_CHINESE.toString(), Locale.SIMPLIFIED_CHINESE.toString());
        Map<String, String> map = f1421b;
        Locale locale = Locale.US;
        map.put(locale.toString(), locale.toString());
        f1421b.put(Locale.GERMAN.toString(), Locale.GERMAN.toString());
        f1421b.put(Locale.forLanguageTag("es").toString(), Locale.forLanguageTag("es").toString());
        f1421b.put(Locale.forLanguageTag("es-LA").toString(), Locale.forLanguageTag("es-LA").toString());
        f1421b.put(Locale.forLanguageTag("pt-BR").toString(), Locale.forLanguageTag("pt-BR").toString());
        f1421b.put(Locale.forLanguageTag("pt-PT").toString(), Locale.forLanguageTag("pt-PT").toString());
        f1421b.put(Locale.FRENCH.toString(), Locale.FRENCH.toString());
        StringBuilder sb = new StringBuilder();
        Iterator<String> it = f1421b.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            sb.append(ListUtils.DEFAULT_JOIN_SEPARATOR);
        }
        if (sb.toString().endsWith(ListUtils.DEFAULT_JOIN_SEPARATOR)) {
            sb.deleteCharAt(sb.toString().length() - 1);
        }
        String string = sb.toString();
        b.b.m.c.a.g("LanguageUtils", "---initDefLanguageList---langSupportArr: " + string);
        Settings.System.putString(context.getContentResolver(), "LANGUAGE_SUPPORT_ARR", string);
    }

    public static synchronized void g(Context context) {
        if (context != null) {
            if (f1420a.isEmpty()) {
                b.b.m.c.a.d("LanguageUtils", "---initLanguageList---");
                f1420a.add(new BydLanguage(Locale.SIMPLIFIED_CHINESE, 1));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("zh-HK"), 2));
                f1420a.add(new BydLanguage(Locale.US, 3));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("es"), 4));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("es-LA"), 5));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("pt-PT"), 6));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("pt-BR"), 7));
                f1420a.add(new BydLanguage(Locale.JAPANESE, 8));
                f1420a.add(new BydLanguage(Locale.KOREAN, 9));
                f1420a.add(new BydLanguage(Locale.FRENCH, 10));
                f1420a.add(new BydLanguage(Locale.GERMAN, 11));
                f1420a.add(new BydLanguage(Locale.ITALIAN, 12));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("hi"), 13));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("ar"), 14));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("nl"), 15));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("th"), 16));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("sv"), 17));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("nb"), 18));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("fi"), 19));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("da"), 20));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("iw"), 21));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("ru"), 22));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("uz-UZ"), 23));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("tr"), 24));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("hu"), 25));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("sk"), 26));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("cs"), 27));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("pl"), 28));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("vi"), 29));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("in"), 30));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("ms-MY"), 31));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("fr-CA"), 32));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("en-AU"), 33));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("ca"), 34));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("hr"), 35));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("ro"), 36));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("el"), 37));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("uk"), 38));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("kk"), 39));
                f1420a.add(new BydLanguage(Locale.forLanguageTag("bg"), 40));
                Resources resources = context.getResources();
                String[] stringArray = resources.getStringArray(R.array.language_list);
                String[] stringArray2 = resources.getStringArray(R.array.language_description_list);
                for (int i = 0; i < f1420a.size(); i++) {
                    BydLanguage bydLanguage = f1420a.get(i);
                    if (stringArray != null && stringArray.length - 1 >= i) {
                        b.b.m.c.a.d("LanguageUtils", "---initLanguageList---i: " + i + ", " + bydLanguage.getLocale().toString() + ", " + stringArray[i]);
                        bydLanguage.setText(stringArray[i]);
                    }
                    if (stringArray2 != null && stringArray2.length - 1 >= i) {
                        bydLanguage.setDescription(stringArray2[i]);
                    }
                }
                String string = Settings.System.getString(context.getContentResolver(), "LANGUAGE_SUPPORT_ARR");
                b.b.m.c.a.a("LanguageUtils", "---initLanguageList---langSupportArr: " + string);
                if (TextUtils.isEmpty(string)) {
                    f(context);
                    return;
                }
                String[] strArrSplit = string.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
                if (strArrSplit != null && strArrSplit.length > 0) {
                    for (String str : strArrSplit) {
                        f1421b.put(str, str);
                    }
                    b.b.m.c.a.g("LanguageUtils", "---initLanguageList---supportedMap: " + f1421b);
                    return;
                }
                f(context);
            }
        }
    }

    public static void h(Context context) {
        List<BydLanguage> list = f1420a;
        if (list == null || list.isEmpty()) {
            g(context);
        }
        b.b.m.c.a.d("LanguageUtils", "---updateDescriptionLanguageChanged---");
        String[] stringArray = context.getResources().getStringArray(R.array.language_description_list);
        for (int i = 0; i < f1420a.size(); i++) {
            if (stringArray != null && stringArray.length - 1 >= i) {
                f1420a.get(i).setDescription(stringArray[i]);
            }
        }
    }
}
