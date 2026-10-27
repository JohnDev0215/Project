package org.json;

import java.util.Collection;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class JSONWriter {
    public static String valueToString(Object obj) throws JSONException {
        if (obj == null || obj.equals(null)) {
            return "null";
        }
        if (obj instanceof JSONString) {
            try {
                String jSONString = ((JSONString) obj).toJSONString();
                if (jSONString != null) {
                    return jSONString;
                }
                throw new JSONException("Bad value from toJSONString: " + jSONString);
            } catch (Exception e) {
                throw new JSONException(e);
            }
        }
        if (obj instanceof Number) {
            String strNumberToString = JSONObject.numberToString((Number) obj);
            return JSONObject.NUMBER_PATTERN.matcher(strNumberToString).matches() ? strNumberToString : JSONObject.quote(strNumberToString);
        }
        if ((obj instanceof Boolean) || (obj instanceof JSONObject) || (obj instanceof JSONArray)) {
            return obj.toString();
        }
        if (obj instanceof Map) {
            return new JSONObject((Map<?, ?>) obj).toString();
        }
        if (obj instanceof Collection) {
            return new JSONArray((Collection<?>) obj).toString();
        }
        if (obj.getClass().isArray()) {
            return new JSONArray(obj).toString();
        }
        if (obj instanceof Enum) {
            return JSONObject.quote(((Enum) obj).name());
        }
        return JSONObject.quote(obj.toString());
    }
}
