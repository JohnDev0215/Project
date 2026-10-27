package e.a.a;

import com.bydux.api.common.tools.util.ListUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Properties f4979a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f4980a = new b();
    }

    public b() {
        this.f4979a = null;
        e();
    }

    public static b c() {
        return a.f4980a;
    }

    public final String a(char c2) {
        String property = d().getProperty(Integer.toHexString(c2).toUpperCase());
        if (f(property)) {
            return property;
        }
        return null;
    }

    public String[] b(char c2) {
        String strA = a(c2);
        if (strA == null) {
            return null;
        }
        return strA.substring(strA.indexOf("(") + 1, strA.lastIndexOf(")")).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public final Properties d() {
        return this.f4979a;
    }

    public final void e() {
        try {
            g(new Properties());
            d().load(e.b("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    public final boolean f(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    public final void g(Properties properties) {
        this.f4979a = properties;
    }
}
