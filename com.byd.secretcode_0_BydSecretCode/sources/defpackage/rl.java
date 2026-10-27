package defpackage;

import com.bydux.api.common.tools.util.ListUtils;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/* JADX INFO: loaded from: classes.dex */
public class rl {
    public Properties a;

    public static class a {
        public static final rl a = new rl();
    }

    public rl() {
        this.a = null;
        e();
    }

    public static rl c() {
        return a.a;
    }

    public final String a(char c) {
        String property = d().getProperty(Integer.toHexString(c).toUpperCase());
        if (f(property)) {
            return property;
        }
        return null;
    }

    public String[] b(char c) {
        String strA = a(c);
        if (strA == null) {
            return null;
        }
        return strA.substring(strA.indexOf("(") + 1, strA.lastIndexOf(")")).split(ListUtils.DEFAULT_JOIN_SEPARATOR);
    }

    public final Properties d() {
        return this.a;
    }

    public final void e() {
        try {
            g(new Properties());
            d().load(ul.b("/pinyindb/unicode_to_hanyu_pinyin.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public final boolean f(String str) {
        return str != null && !str.equals("(none0)") && str.startsWith("(") && str.endsWith(")");
    }

    public final void g(Properties properties) {
        this.a = properties;
    }
}
