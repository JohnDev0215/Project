package y2;

import com.bydux.api.common.tools.util.ListUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Properties f6002a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f6003a = new b();
    }

    public b() {
        this.f6002a = null;
        e();
    }

    public static b c() {
        return a.f6003a;
    }

    public final String a(char c3) {
        String property = d().getProperty(Integer.toHexString(c3).toUpperCase());
        if (f(property)) {
            return property;
        }
        return null;
    }

    public String[] b(char c3) {
        String strA = a(c3);
        if (strA == null) {
            return null;
        }
        return strA.substring(strA.indexOf("(") + 1, strA.lastIndexOf(")")).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public final Properties d() {
        return this.f6002a;
    }

    public final void e() {
        try {
            g(new Properties());
            d().load(e.b("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e3) {
            e3.printStackTrace();
        } catch (IOException e4) {
            e4.printStackTrace();
        }
    }

    public final boolean f(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    public final void g(Properties properties) {
        this.f6002a = properties;
    }
}
