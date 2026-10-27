package d.a.a;

import com.bydux.api.common.tools.util.ListUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Properties f2630a;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final b f2631a = new b();
    }

    private b() {
        this.f2630a = null;
        e();
    }

    private String a(char c2) {
        String property = d().getProperty(Integer.toHexString(c2).toUpperCase());
        if (f(property)) {
            return property;
        }
        return null;
    }

    static b c() {
        return a.f2631a;
    }

    private Properties d() {
        return this.f2630a;
    }

    private void e() {
        try {
            g(new Properties());
            d().load(e.b("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private boolean f(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    private void g(Properties properties) {
        this.f2630a = properties;
    }

    String[] b(char c2) {
        String strA = a(c2);
        if (strA == null) {
            return null;
        }
        return strA.substring(strA.indexOf("(") + 1, strA.lastIndexOf(")")).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }
}
