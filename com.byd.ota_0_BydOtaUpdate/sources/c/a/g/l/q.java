package c.a.g.l;

import android.text.TextUtils;
import com.byd.repository.bean.Description;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: compiled from: TranslateUtils.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2774a = "q";

    /* JADX INFO: compiled from: TranslateUtils.java */
    public class a implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return str.endsWith(".json");
        }
    }

    /* JADX INFO: compiled from: TranslateUtils.java */
    public class b extends c.b.a.u.a<HashMap<String, Description>> {
    }

    public static String a() {
        File[] fileArrListFiles = new File(c.a.b.b.a.o).listFiles(new FilenameFilter() { // from class: c.a.g.l.a
            @Override // java.io.FilenameFilter
            public final boolean accept(File file, String str) {
                return str.endsWith(".json");
            }
        });
        String str = f2774a;
        StringBuilder sb = new StringBuilder();
        sb.append("海外话术是否存在, getJsonFilePath - > files.length = ");
        sb.append(fileArrListFiles != null ? fileArrListFiles.length : -1);
        c.a.b.d.e.c(str, sb.toString());
        return (fileArrListFiles == null || fileArrListFiles.length <= 0) ? "" : fileArrListFiles[0].getAbsolutePath();
    }

    public static Description b(HashMap<String, Description> map, String str, String str2) {
        if (str.equals("zh")) {
            if (str2.equals("CN")) {
                Description description = map.get("zh-Hans-CN");
                return description == null ? map.get("zh-CN") : description;
            }
            Description description2 = map.get("zh-TW");
            return description2 == null ? map.get("zh-HK") : description2;
        }
        if (str.equals("fr")) {
            if (TextUtils.isEmpty(str2)) {
                return map.get("fr-FR");
            }
        } else if (str.equals("es")) {
            if (TextUtils.isEmpty(str2)) {
                return map.get("es-ES");
            }
            if (str2.equals("LA")) {
                return map.get("es-US");
            }
        }
        return null;
    }

    public static Description c() {
        HashMap<String, Description> mapD = d();
        if (mapD == null || mapD.size() == 0) {
            c.a.b.d.e.c(f2774a, "getTranslateDescription -> descriptionMap = " + mapD);
            return null;
        }
        String language = Locale.getDefault().getLanguage();
        String country = Locale.getDefault().getCountry();
        String str = f2774a;
        c.a.b.d.e.c(str, "getTranslateDescription -> key = " + language + "-" + country);
        StringBuilder sb = new StringBuilder();
        sb.append(language);
        sb.append("-");
        sb.append(country);
        Description description = mapD.get(sb.toString());
        if (description != null) {
            c.a.b.d.e.c(str, "getTranslateDescription -> exact description = " + description);
            return description;
        }
        Description descriptionB = b(mapD, language, country);
        if (descriptionB != null) {
            c.a.b.d.e.c(str, "getTranslateDescription -> getSpecialDescription = " + descriptionB);
            return descriptionB;
        }
        c.a.b.d.e.c(str, "getTranslateDescription -> descriptionMap.size() = " + mapD.size());
        for (String str2 : mapD.keySet()) {
            String str3 = f2774a;
            c.a.b.d.e.c(str3, "getTranslateDescription -> key = " + str2);
            if (str2.startsWith(language) || str2.toLowerCase().startsWith(language) || str2.toUpperCase().startsWith(language)) {
                Description description2 = mapD.get(str2);
                c.a.b.d.e.c(str3, "getTranslateDescription -> description = " + description2);
                c.a.b.d.e.c(str3, "getTranslateDescription -> key = " + str2);
                return description2;
            }
        }
        c.a.b.d.e.c(f2774a, "getTranslateDescription -> Not find");
        return null;
    }

    public static HashMap<String, Description> d() throws Throwable {
        String strA = a();
        if (TextUtils.isEmpty(strA)) {
            return null;
        }
        String strQ = c.a.b.d.d.q(strA);
        c.a.b.d.e.c(f2774a, "getTranslateMap -> jsonStr = " + strQ);
        if (TextUtils.isEmpty(strQ)) {
            return null;
        }
        return (HashMap) h.c(strQ, new b());
    }

    public static boolean e() {
        File[] fileArrListFiles = new File(c.a.b.b.a.o).listFiles(new a());
        String str = f2774a;
        StringBuilder sb = new StringBuilder();
        sb.append("海外话术是否存在, isJsonFileExists -> files.length = ");
        sb.append(fileArrListFiles != null ? fileArrListFiles.length : -1);
        c.a.b.d.e.c(str, sb.toString());
        return fileArrListFiles != null && fileArrListFiles.length > 0;
    }
}
