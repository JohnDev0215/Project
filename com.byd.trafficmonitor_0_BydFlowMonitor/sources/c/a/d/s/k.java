package c.a.d.s;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/* JADX INFO: compiled from: PropertiesUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class k {
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r1v2 */
    public static Properties a(Context context) throws Throwable {
        InputStream inputStreamOpen;
        Properties properties = new Properties();
        ?? r1 = 0;
        try {
            try {
                inputStreamOpen = context.getAssets().open("Encode/code.properties");
            } catch (IOException e2) {
                e = e2;
                inputStreamOpen = null;
            } catch (Throwable th) {
                th = th;
                try {
                    r1.close();
                } catch (IOException e3) {
                    e3.printStackTrace();
                }
                throw th;
            }
            try {
                properties.load(inputStreamOpen);
                try {
                    inputStreamOpen.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
                return properties;
            } catch (IOException e5) {
                e = e5;
                e.printStackTrace();
                try {
                    inputStreamOpen.close();
                } catch (IOException e6) {
                    e6.printStackTrace();
                }
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            r1 = context;
            r1.close();
            throw th;
        }
    }

    public static String b(Context context, String str) throws Throwable {
        Properties propertiesA = a(context);
        if (propertiesA != null) {
            return propertiesA.getProperty(str);
        }
        return null;
    }
}
