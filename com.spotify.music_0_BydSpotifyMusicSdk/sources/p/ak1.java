package p;

import android.util.Log;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class ak1 {
    public static final Charset b = Charset.forName("UTF-8");
    public final gu0 a;

    public ak1(gu0 gu0Var) {
        this.a = gu0Var;
    }

    public static HashMap a(String str) {
        JSONObject jSONObject = new JSONObject(str);
        HashMap map = new HashMap();
        Iterator<String> itKeys = jSONObject.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            String strOptString = null;
            if (!jSONObject.isNull(next)) {
                strOptString = jSONObject.optString(next, null);
            }
            map.put(next, strOptString);
        }
        return map;
    }

    public static void d(File file) {
        if (file.exists() && file.delete()) {
            file.getAbsolutePath();
        }
    }

    public final Map b(String str, boolean z) throws Throwable {
        gu0 gu0Var = this.a;
        File fileL = z ? gu0Var.l(str, "internal-keys") : gu0Var.l(str, "keys");
        if (!fileL.exists() || fileL.length() == 0) {
            d(fileL);
            return Collections.emptyMap();
        }
        FileInputStream fileInputStream = null;
        try {
            try {
                FileInputStream fileInputStream2 = new FileInputStream(fileL);
                try {
                    HashMap mapA = a(zu.G0(fileInputStream2));
                    zu.q(fileInputStream2, "Failed to close user metadata file.");
                    return mapA;
                } catch (Exception unused) {
                    fileInputStream = fileInputStream2;
                    d(fileL);
                    zu.q(fileInputStream, "Failed to close user metadata file.");
                    return Collections.emptyMap();
                } catch (Throwable th) {
                    th = th;
                    fileInputStream = fileInputStream2;
                    zu.q(fileInputStream, "Failed to close user metadata file.");
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception unused2) {
        }
    }

    public final String c(String str) {
        FileInputStream fileInputStream;
        File fileL = this.a.l(str, "user-data");
        FileInputStream fileInputStream2 = null;
        if (!fileL.exists() || fileL.length() == 0) {
            Log.isLoggable("FirebaseCrashlytics", 3);
            d(fileL);
            return null;
        }
        try {
            fileInputStream = new FileInputStream(fileL);
        } catch (Exception unused) {
            fileInputStream = null;
        } catch (Throwable th) {
            th = th;
            zu.q(fileInputStream2, "Failed to close user metadata file.");
            throw th;
        }
        try {
            try {
                JSONObject jSONObject = new JSONObject(zu.G0(fileInputStream));
                String strOptString = !jSONObject.isNull("userId") ? jSONObject.optString("userId", null) : null;
                Log.isLoggable("FirebaseCrashlytics", 3);
                zu.q(fileInputStream, "Failed to close user metadata file.");
                return strOptString;
            } catch (Throwable th2) {
                th = th2;
                fileInputStream2 = fileInputStream;
                zu.q(fileInputStream2, "Failed to close user metadata file.");
                throw th;
            }
        } catch (Exception unused2) {
            d(fileL);
            zu.q(fileInputStream, "Failed to close user metadata file.");
            return null;
        }
    }
}
