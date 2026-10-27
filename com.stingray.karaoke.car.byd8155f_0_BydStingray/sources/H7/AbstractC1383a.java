package h7;

import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: h7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1383a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f15599a = Logger.getLogger(AbstractC1383a.class.getName());

    public static boolean a(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof byte[]) {
            return true;
        }
        boolean z6 = obj instanceof JSONArray;
        Logger logger = f15599a;
        if (z6) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i7 = 0; i7 < length; i7++) {
                try {
                    if (a(jSONArray.isNull(i7) ? null : jSONArray.get(i7))) {
                        return true;
                    }
                } catch (JSONException e10) {
                    logger.log(Level.WARNING, "An error occured while retrieving data from JSONArray", (Throwable) e10);
                    return false;
                }
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jSONObject = (JSONObject) obj;
            Iterator<String> itKeys = jSONObject.keys();
            while (itKeys.hasNext()) {
                try {
                    if (a(jSONObject.get(itKeys.next()))) {
                        return true;
                    }
                } catch (JSONException e11) {
                    logger.log(Level.WARNING, "An error occured while retrieving data from JSONObject", (Throwable) e11);
                }
            }
        }
        return false;
    }
}
