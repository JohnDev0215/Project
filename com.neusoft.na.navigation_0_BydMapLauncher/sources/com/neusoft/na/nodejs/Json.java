package com.neusoft.na.nodejs;

import java.util.Collection;
import java.util.Map;
import kotlin.text.Typography;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

/* JADX INFO: loaded from: classes2.dex */
class Json {
    Json() {
    }

    public static void append(StringBuilder sb, Object obj) {
        if (obj == null) {
            sb.append("null");
            return;
        }
        boolean z = true;
        if (obj instanceof Collection) {
            sb.append('[');
            for (Object obj2 : (Collection) obj) {
                if (!z) {
                    sb.append(',');
                }
                append(sb, obj2);
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof Object[]) {
            sb.append('[');
            Object[] objArr = (Object[]) obj;
            int length = objArr.length;
            int i = 0;
            while (i < length) {
                Object obj3 = objArr[i];
                if (!z) {
                    sb.append(',');
                }
                append(sb, obj3);
                i++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof boolean[]) {
            sb.append('[');
            boolean[] zArr = (boolean[]) obj;
            int length2 = zArr.length;
            int i2 = 0;
            while (i2 < length2) {
                boolean z2 = zArr[i2];
                if (!z) {
                    sb.append(',');
                }
                sb.append(z2);
                i2++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof byte[]) {
            sb.append('[');
            byte[] bArr = (byte[]) obj;
            int length3 = bArr.length;
            int i3 = 0;
            while (i3 < length3) {
                byte b = bArr[i3];
                if (!z) {
                    sb.append(',');
                }
                sb.append((int) b);
                i3++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof short[]) {
            sb.append('[');
            short[] sArr = (short[]) obj;
            int length4 = sArr.length;
            int i4 = 0;
            while (i4 < length4) {
                short s = sArr[i4];
                if (!z) {
                    sb.append(',');
                }
                sb.append((int) s);
                i4++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof int[]) {
            sb.append('[');
            int[] iArr = (int[]) obj;
            int length5 = iArr.length;
            int i5 = 0;
            while (i5 < length5) {
                int i6 = iArr[i5];
                if (!z) {
                    sb.append(',');
                }
                sb.append(i6);
                i5++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof long[]) {
            sb.append('[');
            long[] jArr = (long[]) obj;
            int length6 = jArr.length;
            int i7 = 0;
            while (i7 < length6) {
                long j = jArr[i7];
                if (!z) {
                    sb.append(',');
                }
                sb.append(j);
                i7++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof float[]) {
            sb.append('[');
            float[] fArr = (float[]) obj;
            int length7 = fArr.length;
            int i8 = 0;
            while (i8 < length7) {
                float f = fArr[i8];
                if (!z) {
                    sb.append(',');
                }
                sb.append(f);
                i8++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof double[]) {
            sb.append('[');
            double[] dArr = (double[]) obj;
            int length8 = dArr.length;
            int i9 = 0;
            while (i9 < length8) {
                double d = dArr[i9];
                if (!z) {
                    sb.append(',');
                }
                sb.append(d);
                i9++;
                z = false;
            }
            sb.append(']');
            return;
        }
        if (obj instanceof Map) {
            sb.append('{');
            for (Map.Entry entry : ((Map) obj).entrySet()) {
                if (!z) {
                    sb.append(',');
                }
                sb.append(Typography.quote).append(entry.getKey().toString()).append("\":");
                append(sb, entry.getValue());
                z = false;
            }
            sb.append('}');
            return;
        }
        if ((obj instanceof JSONArray) || (obj instanceof JSONObject) || (obj instanceof Number) || (obj instanceof Boolean)) {
            sb.append(obj);
        } else {
            sb.append(Typography.quote).append(obj).append(Typography.quote);
        }
    }

    public static String toString(Object obj) {
        StringBuilder sb = new StringBuilder();
        append(sb, obj);
        return sb.toString();
    }

    public static Object parse(String str) throws JSONException {
        Object objNextValue = new JSONTokener(str).nextValue();
        if (objNextValue == JSONObject.NULL) {
            return null;
        }
        return objNextValue;
    }
}
