package j7;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: renamed from: j7.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1554a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Logger f17064a = Logger.getLogger(AbstractC1554a.class.getName());

    public static Object a(Object obj, ArrayList arrayList) {
        if (obj == null) {
            return null;
        }
        boolean z6 = obj instanceof byte[];
        Logger logger = f17064a;
        if (z6) {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("_placeholder", true);
                jSONObject.put("num", arrayList.size());
                arrayList.add((byte[]) obj);
                return jSONObject;
            } catch (JSONException e10) {
                logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e10);
                return null;
            }
        }
        if (obj instanceof JSONArray) {
            JSONArray jSONArray = new JSONArray();
            JSONArray jSONArray2 = (JSONArray) obj;
            int length = jSONArray2.length();
            for (int i7 = 0; i7 < length; i7++) {
                try {
                    jSONArray.put(i7, a(jSONArray2.get(i7), arrayList));
                } catch (JSONException e11) {
                    logger.log(Level.WARNING, "An error occured while putting packet data to JSONObject", (Throwable) e11);
                    return null;
                }
            }
            return jSONArray;
        }
        if (!(obj instanceof JSONObject)) {
            return obj;
        }
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = (JSONObject) obj;
        Iterator<String> itKeys = jSONObject3.keys();
        while (itKeys.hasNext()) {
            String next = itKeys.next();
            try {
                jSONObject2.put(next, a(jSONObject3.get(next), arrayList));
            } catch (JSONException e12) {
                logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e12);
                return null;
            }
        }
        return jSONObject2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r5v2, types: [org.json.JSONObject] */
    public static Object b(Object obj, byte[][] bArr) {
        boolean z6 = obj instanceof JSONArray;
        Logger logger = f17064a;
        if (z6) {
            JSONArray jSONArray = (JSONArray) obj;
            int length = jSONArray.length();
            for (int i7 = 0; i7 < length; i7++) {
                try {
                    jSONArray.put(i7, b(jSONArray.get(i7), bArr));
                } catch (JSONException e10) {
                    logger.log(Level.WARNING, "An error occured while putting packet data to JSONObject", (Throwable) e10);
                    return null;
                }
            }
            return jSONArray;
        }
        if (obj instanceof JSONObject) {
            obj = (JSONObject) obj;
            if (obj.optBoolean("_placeholder")) {
                int iOptInt = obj.optInt("num", -1);
                if (iOptInt < 0 || iOptInt >= bArr.length) {
                    return null;
                }
                return bArr[iOptInt];
            }
            Iterator<String> itKeys = obj.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                try {
                    obj.put(next, b(obj.get(next), bArr));
                } catch (JSONException e11) {
                    logger.log(Level.WARNING, "An error occured while putting data to JSONObject", (Throwable) e11);
                    return null;
                }
            }
        }
        return obj;
    }
}
