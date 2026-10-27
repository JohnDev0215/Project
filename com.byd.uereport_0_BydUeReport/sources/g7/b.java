package g7;

import com.bydux.api.common.tools.util.ListUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes2.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Properties f5099a;

    public static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5100a = new b();
    }

    public b() {
        this.f5099a = null;
        e();
    }

    public static b c() {
        return a.f5100a;
    }

    public final String a(char c8) {
        String property = d().getProperty(Integer.toHexString(c8).toUpperCase());
        if (f(property)) {
            return property;
        }
        return null;
    }

    public String[] b(char c8) {
        String strA = a(c8);
        if (strA == null) {
            return null;
        }
        return strA.substring(strA.indexOf("(") + 1, strA.lastIndexOf(")")).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public final Properties d() {
        return this.f5099a;
    }

    public final void e() {
        try {
            g(new Properties());
            d().load(e.b("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e8) {
            e8.printStackTrace();
        } catch (IOException e9) {
            e9.printStackTrace();
        }
    }

    public final boolean f(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    public final void g(Properties properties) {
        this.f5099a = properties;
    }
}
