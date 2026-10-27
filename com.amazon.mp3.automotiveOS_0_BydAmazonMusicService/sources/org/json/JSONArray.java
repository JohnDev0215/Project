package org.json;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public class JSONArray implements Iterable<Object> {
    private final ArrayList<Object> myArrayList;

    public JSONArray() {
        this.myArrayList = new ArrayList<>();
    }

    public JSONArray(JSONTokener jSONTokener) throws JSONException {
        this();
        if (jSONTokener.nextClean() != '[') {
            throw jSONTokener.syntaxError("A JSONArray text must start with '['");
        }
        char cNextClean = jSONTokener.nextClean();
        if (cNextClean == 0) {
            throw jSONTokener.syntaxError("Expected a ',' or ']'");
        }
        if (cNextClean == ']') {
            return;
        }
        jSONTokener.back();
        while (true) {
            if (jSONTokener.nextClean() == ',') {
                jSONTokener.back();
                this.myArrayList.add(JSONObject.NULL);
            } else {
                jSONTokener.back();
                this.myArrayList.add(jSONTokener.nextValue());
            }
            char cNextClean2 = jSONTokener.nextClean();
            if (cNextClean2 == 0) {
                throw jSONTokener.syntaxError("Expected a ',' or ']'");
            }
            if (cNextClean2 != ',') {
                if (cNextClean2 != ']') {
                    throw jSONTokener.syntaxError("Expected a ',' or ']'");
                }
                return;
            }
            char cNextClean3 = jSONTokener.nextClean();
            if (cNextClean3 == 0) {
                throw jSONTokener.syntaxError("Expected a ',' or ']'");
            }
            if (cNextClean3 == ']') {
                return;
            } else {
                jSONTokener.back();
            }
        }
    }

    public JSONArray(String str) throws JSONException {
        this(new JSONTokener(str));
    }

    public JSONArray(Collection<?> collection) {
        if (collection == null) {
            this.myArrayList = new ArrayList<>();
        } else {
            this.myArrayList = new ArrayList<>(collection.size());
            addAll(collection, true);
        }
    }

    public JSONArray(Object obj) throws JSONException {
        this();
        if (!obj.getClass().isArray()) {
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
        addAll(obj, true);
    }

    @Override // java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.myArrayList.iterator();
    }

    public Object get(int i) throws JSONException {
        Object objOpt = opt(i);
        if (objOpt != null) {
            return objOpt;
        }
        throw new JSONException("JSONArray[" + i + "] not found.");
    }

    public int getInt(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof Number) {
            return ((Number) obj).intValue();
        }
        try {
            return Integer.parseInt(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, "int", obj, e);
        }
    }

    public JSONArray getJSONArray(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof JSONArray) {
            return (JSONArray) obj;
        }
        throw wrongValueFormatException(i, "JSONArray", obj, null);
    }

    public JSONObject getJSONObject(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof JSONObject) {
            return (JSONObject) obj;
        }
        throw wrongValueFormatException(i, "JSONObject", obj, null);
    }

    public long getLong(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof Number) {
            return ((Number) obj).longValue();
        }
        try {
            return Long.parseLong(obj.toString());
        } catch (Exception e) {
            throw wrongValueFormatException(i, "long", obj, e);
        }
    }

    public String getString(int i) throws JSONException {
        Object obj = get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        throw wrongValueFormatException(i, "String", obj, null);
    }

    public boolean isNull(int i) {
        return JSONObject.NULL.equals(opt(i));
    }

    public String join(String str) throws JSONException {
        int length = length();
        if (length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(JSONObject.valueToString(this.myArrayList.get(0)));
        for (int i = 1; i < length; i++) {
            sb.append(str);
            sb.append(JSONObject.valueToString(this.myArrayList.get(i)));
        }
        return sb.toString();
    }

    public int length() {
        return this.myArrayList.size();
    }

    public Object opt(int i) {
        if (i < 0 || i >= length()) {
            return null;
        }
        return this.myArrayList.get(i);
    }

    public int optInt(int i) {
        return optInt(i, 0);
    }

    public int optInt(int i, int i2) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? i2 : numberOptNumber.intValue();
    }

    public JSONObject optJSONObject(int i) {
        Object objOpt = opt(i);
        if (objOpt instanceof JSONObject) {
            return (JSONObject) objOpt;
        }
        return null;
    }

    public long optLong(int i) {
        return optLong(i, 0L);
    }

    public long optLong(int i, long j) {
        Number numberOptNumber = optNumber(i, null);
        return numberOptNumber == null ? j : numberOptNumber.longValue();
    }

    public Number optNumber(int i, Number number) {
        Object objOpt = opt(i);
        if (JSONObject.NULL.equals(objOpt)) {
            return number;
        }
        if (objOpt instanceof Number) {
            return (Number) objOpt;
        }
        if (objOpt instanceof String) {
            try {
                return JSONObject.stringToNumber((String) objOpt);
            } catch (Exception unused) {
            }
        }
        return number;
    }

    public String optString(int i) {
        return optString(i, "");
    }

    public String optString(int i, String str) {
        Object objOpt = opt(i);
        return JSONObject.NULL.equals(objOpt) ? str : objOpt.toString();
    }

    public JSONArray put(int i) {
        return put(Integer.valueOf(i));
    }

    public JSONArray put(long j) {
        return put(Long.valueOf(j));
    }

    public JSONArray put(Object obj) {
        JSONObject.testValidity(obj);
        this.myArrayList.add(obj);
        return this;
    }

    public JSONArray put(int i, int i2) throws JSONException {
        return put(i, Integer.valueOf(i2));
    }

    public JSONArray put(int i, long j) throws JSONException {
        return put(i, Long.valueOf(j));
    }

    public JSONArray put(int i, Object obj) throws JSONException {
        if (i < 0) {
            throw new JSONException("JSONArray[" + i + "] not found.");
        }
        if (i < length()) {
            JSONObject.testValidity(obj);
            this.myArrayList.set(i, obj);
            return this;
        }
        if (i == length()) {
            return put(obj);
        }
        this.myArrayList.ensureCapacity(i + 1);
        while (i != length()) {
            this.myArrayList.add(JSONObject.NULL);
        }
        return put(obj);
    }

    public boolean similar(Object obj) {
        if (!(obj instanceof JSONArray)) {
            return false;
        }
        int length = length();
        JSONArray jSONArray = (JSONArray) obj;
        if (length != jSONArray.length()) {
            return false;
        }
        for (int i = 0; i < length; i++) {
            Object obj2 = this.myArrayList.get(i);
            Object obj3 = jSONArray.myArrayList.get(i);
            if (obj2 != obj3) {
                if (obj2 == null) {
                    return false;
                }
                if (obj2 instanceof JSONObject) {
                    if (!((JSONObject) obj2).similar(obj3)) {
                        return false;
                    }
                } else if (obj2 instanceof JSONArray) {
                    if (!((JSONArray) obj2).similar(obj3)) {
                        return false;
                    }
                } else if ((obj2 instanceof Number) && (obj3 instanceof Number)) {
                    if (!JSONObject.isNumberSimilar((Number) obj2, (Number) obj3)) {
                        return false;
                    }
                } else if ((obj2 instanceof JSONString) && (obj3 instanceof JSONString)) {
                    if (!((JSONString) obj2).toJSONString().equals(((JSONString) obj3).toJSONString())) {
                        return false;
                    }
                } else if (!obj2.equals(obj3)) {
                    return false;
                }
            }
        }
        return true;
    }

    public String toString() {
        try {
            return toString(0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String toString(int i) throws JSONException {
        String string;
        StringWriter stringWriter = new StringWriter();
        synchronized (stringWriter.getBuffer()) {
            string = write(stringWriter, i, 0).toString();
        }
        return string;
    }

    public Writer write(Writer writer, int i, int i2) throws JSONException {
        try {
            int length = length();
            writer.write(91);
            int i3 = 0;
            if (length == 1) {
                try {
                    JSONObject.writeValue(writer, this.myArrayList.get(0), i, i2);
                } catch (Exception e) {
                    throw new JSONException("Unable to write JSONArray value at index: 0", e);
                }
            } else if (length != 0) {
                int i4 = i2 + i;
                boolean z = false;
                while (i3 < length) {
                    if (z) {
                        writer.write(44);
                    }
                    if (i > 0) {
                        writer.write(10);
                    }
                    JSONObject.indent(writer, i4);
                    try {
                        JSONObject.writeValue(writer, this.myArrayList.get(i3), i, i4);
                        i3++;
                        z = true;
                    } catch (Exception e2) {
                        throw new JSONException("Unable to write JSONArray value at index: " + i3, e2);
                    }
                }
                if (i > 0) {
                    writer.write(10);
                }
                JSONObject.indent(writer, i2);
            }
            writer.write(93);
            return writer;
        } catch (IOException e3) {
            throw new JSONException(e3);
        }
    }

    public List<Object> toList() {
        ArrayList arrayList = new ArrayList(this.myArrayList.size());
        for (Object obj : this.myArrayList) {
            if (obj == null || JSONObject.NULL.equals(obj)) {
                arrayList.add(null);
            } else if (obj instanceof JSONArray) {
                arrayList.add(((JSONArray) obj).toList());
            } else if (obj instanceof JSONObject) {
                arrayList.add(((JSONObject) obj).toMap());
            } else {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    public boolean isEmpty() {
        return this.myArrayList.isEmpty();
    }

    private void addAll(Collection<?> collection, boolean z) {
        ArrayList<Object> arrayList = this.myArrayList;
        arrayList.ensureCapacity(arrayList.size() + collection.size());
        if (z) {
            Iterator<?> it = collection.iterator();
            while (it.hasNext()) {
                put(JSONObject.wrap(it.next()));
            }
        } else {
            Iterator<?> it2 = collection.iterator();
            while (it2.hasNext()) {
                put(it2.next());
            }
        }
    }

    private void addAll(Iterable<?> iterable, boolean z) {
        if (z) {
            Iterator<?> it = iterable.iterator();
            while (it.hasNext()) {
                put(JSONObject.wrap(it.next()));
            }
        } else {
            Iterator<?> it2 = iterable.iterator();
            while (it2.hasNext()) {
                put(it2.next());
            }
        }
    }

    private void addAll(Object obj, boolean z) throws JSONException {
        if (obj.getClass().isArray()) {
            int length = Array.getLength(obj);
            ArrayList<Object> arrayList = this.myArrayList;
            arrayList.ensureCapacity(arrayList.size() + length);
            int i = 0;
            if (z) {
                while (i < length) {
                    put(JSONObject.wrap(Array.get(obj, i)));
                    i++;
                }
                return;
            } else {
                while (i < length) {
                    put(Array.get(obj, i));
                    i++;
                }
                return;
            }
        }
        if (obj instanceof JSONArray) {
            this.myArrayList.addAll(((JSONArray) obj).myArrayList);
        } else if (obj instanceof Collection) {
            addAll((Collection<?>) obj, z);
        } else {
            if (obj instanceof Iterable) {
                addAll((Iterable<?>) obj, z);
                return;
            }
            throw new JSONException("JSONArray initial value should be a string or collection or array.");
        }
    }

    private static JSONException wrongValueFormatException(int i, String str, Object obj, Throwable th) {
        if (obj == null) {
            return new JSONException("JSONArray[" + i + "] is not a " + str + " (null).", th);
        }
        if ((obj instanceof Map) || (obj instanceof Iterable) || (obj instanceof JSONObject)) {
            return new JSONException("JSONArray[" + i + "] is not a " + str + " (" + obj.getClass() + ").", th);
        }
        return new JSONException("JSONArray[" + i + "] is not a " + str + " (" + obj.getClass() + " : " + obj + ").", th);
    }
}
