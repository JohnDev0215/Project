package com.alibaba.fastjson;

import b.a.a.g;
import b.a.a.h.b;
import b.a.a.j.h;
import b.a.a.n.l;
import com.alibaba.fastjson.parser.Feature;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.ObjectStreamClass;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

/* JADX INFO: loaded from: classes.dex */
public class JSONObject extends b.a.a.a implements Map<String, Object>, Cloneable, Serializable, InvocationHandler {
    private static final long serialVersionUID = 1;
    private final Map<String, Object> map;

    public static class a extends ObjectInputStream {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static Field[] f2359a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static volatile boolean f2360b;

        public a(ObjectInputStream objectInputStream) throws IOException {
            super(objectInputStream);
            int i = 0;
            while (true) {
                try {
                    Field[] fieldArr = f2359a;
                    if (i >= fieldArr.length) {
                        return;
                    }
                    Field field = fieldArr[i];
                    field.set(this, field.get(objectInputStream));
                    i++;
                } catch (IllegalAccessException unused) {
                    f2360b = true;
                    return;
                }
            }
        }

        public static void a() {
            if (f2359a != null || f2360b) {
                return;
            }
            try {
                Field[] declaredFields = ObjectInputStream.class.getDeclaredFields();
                String[] strArr = {"bin", "passHandle", "handles", "curContext"};
                Field[] fieldArr = new Field[4];
                for (int i = 0; i < 4; i++) {
                    Field fieldW = l.W(ObjectInputStream.class, strArr[i], declaredFields);
                    fieldW.setAccessible(true);
                    fieldArr[i] = fieldW;
                }
                f2359a = fieldArr;
            } catch (Throwable unused) {
                f2360b = true;
            }
        }

        @Override // java.io.ObjectInputStream
        public void readStreamHeader() throws IOException {
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveClass(ObjectStreamClass objectStreamClass) throws Throwable {
            String name = objectStreamClass.getName();
            if (name.length() > 2) {
                int iLastIndexOf = name.lastIndexOf(91);
                if (iLastIndexOf != -1) {
                    name = name.substring(iLastIndexOf + 1);
                }
                if (name.length() > 2 && name.charAt(0) == 'L' && name.charAt(name.length() - 1) == ';') {
                    name = name.substring(1, name.length() - 1);
                }
                if (l.Q(name) == null) {
                    h.g.i(name, null, Feature.SupportAutoType.mask);
                }
            }
            return super.resolveClass(objectStreamClass);
        }

        @Override // java.io.ObjectInputStream
        public Class<?> resolveProxyClass(String[] strArr) throws IOException, ClassNotFoundException {
            for (String str : strArr) {
                if (l.Q(str) == null) {
                    h.g.h(str, null);
                }
            }
            return super.resolveProxyClass(strArr);
        }
    }

    public JSONObject() {
        this(16, false);
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        a.a();
        if (a.f2359a != null && !a.f2360b) {
            try {
                new a(objectInputStream).defaultReadObject();
                return;
            } catch (NotActiveException unused) {
            }
        }
        objectInputStream.defaultReadObject();
        for (Map.Entry<String, Object> entry : this.map.entrySet()) {
            String key = entry.getKey();
            if (key != null) {
                h.g.g(key.getClass());
            }
            Object value = entry.getValue();
            if (value != null) {
                h.g.g(value.getClass());
            }
        }
    }

    @Override // java.util.Map
    public void clear() {
        this.map.clear();
    }

    public Object clone() {
        return new JSONObject((Map<String, Object>) (this.map instanceof LinkedHashMap ? new LinkedHashMap(this.map) : new HashMap(this.map)));
    }

    @Override // java.util.Map
    public boolean containsKey(Object obj) {
        boolean zContainsKey = this.map.containsKey(obj);
        return !zContainsKey ? ((obj instanceof Number) || (obj instanceof Character) || (obj instanceof Boolean) || (obj instanceof UUID)) ? this.map.containsKey(obj.toString()) : zContainsKey : zContainsKey;
    }

    @Override // java.util.Map
    public boolean containsValue(Object obj) {
        return this.map.containsValue(obj);
    }

    @Override // java.util.Map
    public Set<Map.Entry<String, Object>> entrySet() {
        return this.map.entrySet();
    }

    @Override // java.util.Map
    public boolean equals(Object obj) {
        return this.map.equals(obj);
    }

    public JSONObject fluentClear() {
        this.map.clear();
        return this;
    }

    public JSONObject fluentPut(String str, Object obj) {
        this.map.put(str, obj);
        return this;
    }

    public JSONObject fluentPutAll(Map<? extends String, ?> map) {
        this.map.putAll(map);
        return this;
    }

    public JSONObject fluentRemove(Object obj) {
        this.map.remove(obj);
        return this;
    }

    @Override // java.util.Map
    public Object get(Object obj) {
        Object obj2 = this.map.get(obj);
        return obj2 == null ? ((obj instanceof Number) || (obj instanceof Character) || (obj instanceof Boolean) || (obj instanceof UUID)) ? this.map.get(obj.toString()) : obj2 : obj2;
    }

    public BigDecimal getBigDecimal(String str) {
        return l.i(get(str));
    }

    public BigInteger getBigInteger(String str) {
        return l.j(get(str));
    }

    public Boolean getBoolean(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return l.k(obj);
    }

    public boolean getBooleanValue(String str) {
        Boolean boolK = l.k(get(str));
        if (boolK == null) {
            return false;
        }
        return boolK.booleanValue();
    }

    public Byte getByte(String str) {
        return l.l(get(str));
    }

    public byte getByteValue(String str) {
        Byte bL = l.l(get(str));
        if (bL == null) {
            return (byte) 0;
        }
        return bL.byteValue();
    }

    public byte[] getBytes(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return l.m(obj);
    }

    public Date getDate(String str) {
        return l.o(get(str));
    }

    public Double getDouble(String str) {
        return l.q(get(str));
    }

    public double getDoubleValue(String str) {
        Double dQ = l.q(get(str));
        if (dQ == null) {
            return 0.0d;
        }
        return dQ.doubleValue();
    }

    public Float getFloat(String str) {
        return l.s(get(str));
    }

    public float getFloatValue(String str) {
        Float fS = l.s(get(str));
        if (fS == null) {
            return 0.0f;
        }
        return fS.floatValue();
    }

    public Map<String, Object> getInnerMap() {
        return this.map;
    }

    public int getIntValue(String str) {
        Integer numT = l.t(get(str));
        if (numT == null) {
            return 0;
        }
        return numT.intValue();
    }

    public Integer getInteger(String str) {
        return l.t(get(str));
    }

    public JSONArray getJSONArray(String str) {
        Object obj = this.map.get(str);
        return obj instanceof JSONArray ? (JSONArray) obj : obj instanceof List ? new JSONArray((List<Object>) obj) : obj instanceof String ? (JSONArray) b.a.a.a.parse((String) obj) : (JSONArray) b.a.a.a.toJSON(obj);
    }

    public JSONObject getJSONObject(String str) {
        Object obj = this.map.get(str);
        return obj instanceof JSONObject ? (JSONObject) obj : obj instanceof Map ? new JSONObject((Map<String, Object>) obj) : obj instanceof String ? b.a.a.a.parseObject((String) obj) : (JSONObject) b.a.a.a.toJSON(obj);
    }

    public Long getLong(String str) {
        return l.w(get(str));
    }

    public long getLongValue(String str) {
        Long lW = l.w(get(str));
        if (lW == null) {
            return 0L;
        }
        return lW.longValue();
    }

    public <T> T getObject(String str, Class<T> cls) {
        return (T) l.u(this.map.get(str), cls);
    }

    public Short getShort(String str) {
        return l.x(get(str));
    }

    public short getShortValue(String str) {
        Short shX = l.x(get(str));
        if (shX == null) {
            return (short) 0;
        }
        return shX.shortValue();
    }

    public java.sql.Date getSqlDate(String str) {
        return l.y(get(str));
    }

    public String getString(String str) {
        Object obj = get(str);
        if (obj == null) {
            return null;
        }
        return obj.toString();
    }

    public Timestamp getTimestamp(String str) {
        return l.B(get(str));
    }

    @Override // java.util.Map
    public int hashCode() {
        return this.map.hashCode();
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        Class<?>[] parameterTypes = method.getParameterTypes();
        String strName = null;
        if (parameterTypes.length == 1) {
            if (method.getName().equals("equals")) {
                return Boolean.valueOf(equals(objArr[0]));
            }
            if (method.getReturnType() != Void.TYPE) {
                throw new JSONException("illegal setter");
            }
            b bVar = (b) l.O(method, b.class);
            String strName2 = (bVar == null || bVar.name().length() == 0) ? null : bVar.name();
            if (strName2 == null) {
                String name = method.getName();
                if (!name.startsWith("set")) {
                    throw new JSONException("illegal setter");
                }
                String strSubstring = name.substring(3);
                if (strSubstring.length() == 0) {
                    throw new JSONException("illegal setter");
                }
                strName2 = Character.toLowerCase(strSubstring.charAt(0)) + strSubstring.substring(1);
            }
            this.map.put(strName2, objArr[0]);
            return null;
        }
        if (parameterTypes.length != 0) {
            throw new UnsupportedOperationException(method.toGenericString());
        }
        if (method.getReturnType() == Void.TYPE) {
            throw new JSONException("illegal getter");
        }
        b bVar2 = (b) l.O(method, b.class);
        if (bVar2 != null && bVar2.name().length() != 0) {
            strName = bVar2.name();
        }
        if (strName == null) {
            String name2 = method.getName();
            if (name2.startsWith("get")) {
                String strSubstring2 = name2.substring(3);
                if (strSubstring2.length() == 0) {
                    throw new JSONException("illegal getter");
                }
                strName = Character.toLowerCase(strSubstring2.charAt(0)) + strSubstring2.substring(1);
            } else {
                if (!name2.startsWith("is")) {
                    if (name2.startsWith("hashCode")) {
                        return Integer.valueOf(hashCode());
                    }
                    if (name2.startsWith("toString")) {
                        return toString();
                    }
                    throw new JSONException("illegal getter");
                }
                String strSubstring3 = name2.substring(2);
                if (strSubstring3.length() == 0) {
                    throw new JSONException("illegal getter");
                }
                strName = Character.toLowerCase(strSubstring3.charAt(0)) + strSubstring3.substring(1);
            }
        }
        return l.h(this.map.get(strName), method.getGenericReturnType(), h.s());
    }

    @Override // java.util.Map
    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    @Override // java.util.Map
    public Set<String> keySet() {
        return this.map.keySet();
    }

    @Override // java.util.Map
    public void putAll(Map<? extends String, ? extends Object> map) {
        this.map.putAll(map);
    }

    @Override // java.util.Map
    public Object remove(Object obj) {
        return this.map.remove(obj);
    }

    @Override // java.util.Map
    public int size() {
        return this.map.size();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // b.a.a.a
    public <T> T toJavaObject(Class<T> cls) {
        return (cls == Map.class || cls == JSONObject.class || cls == b.a.a.a.class) ? this : (cls != Object.class || containsKey(b.a.a.a.DEFAULT_TYPE_KEY)) ? (T) l.v(this, cls, h.s()) : this;
    }

    @Override // java.util.Map
    public Collection<Object> values() {
        return this.map.values();
    }

    public JSONObject(Map<String, Object> map) {
        if (map == null) {
            throw new IllegalArgumentException("map is null.");
        }
        this.map = map;
    }

    @Override // java.util.Map
    public Object put(String str, Object obj) {
        return this.map.put(str, obj);
    }

    public <T> T getObject(String str, Type type) {
        return (T) l.h(this.map.get(str), type, h.s());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public <T> T toJavaObject(Class<T> cls, h hVar, int i) {
        return cls == Map.class ? this : (cls != Object.class || containsKey(b.a.a.a.DEFAULT_TYPE_KEY)) ? (T) l.v(this, cls, hVar) : this;
    }

    public JSONObject(boolean z) {
        this(16, z);
    }

    public <T> T getObject(String str, g gVar) {
        T t = (T) this.map.get(str);
        return gVar == null ? t : (T) l.h(t, gVar.a(), h.s());
    }

    public JSONObject(int i) {
        this(i, false);
    }

    public JSONObject(int i, boolean z) {
        if (z) {
            this.map = new LinkedHashMap(i);
        } else {
            this.map = new HashMap(i);
        }
    }
}
