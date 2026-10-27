package com.alibaba.fastjson;

import b.a.a.a;
import b.a.a.j.h;
import b.a.a.n.l;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import java.io.IOException;
import java.io.NotActiveException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public class JSONArray extends a implements List<Object>, Cloneable, RandomAccess, Serializable {
    private static final long serialVersionUID = 1;
    public transient Type componentType;
    private final List<Object> list;
    public transient Object relatedArray;

    public JSONArray() {
        this.list = new ArrayList();
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        JSONObject.a.a();
        if (JSONObject.a.f2359a != null && !JSONObject.a.f2360b) {
            try {
                new JSONObject.a(objectInputStream).defaultReadObject();
                return;
            } catch (NotActiveException unused) {
            }
        }
        objectInputStream.defaultReadObject();
        for (Object obj : this.list) {
            if (obj != null) {
                String name = obj.getClass().getName();
                if (l.Q(name) == null) {
                    h.g.h(name, null);
                }
            }
        }
    }

    @Override // java.util.List, java.util.Collection
    public boolean add(Object obj) {
        return this.list.add(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean addAll(Collection<?> collection) {
        return this.list.addAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public void clear() {
        this.list.clear();
    }

    public Object clone() {
        return new JSONArray(new ArrayList(this.list));
    }

    @Override // java.util.List, java.util.Collection
    public boolean contains(Object obj) {
        return this.list.contains(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean containsAll(Collection<?> collection) {
        return this.list.containsAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean equals(Object obj) {
        return this.list.equals(obj);
    }

    public JSONArray fluentAdd(Object obj) {
        this.list.add(obj);
        return this;
    }

    public JSONArray fluentAddAll(Collection<?> collection) {
        this.list.addAll(collection);
        return this;
    }

    public JSONArray fluentClear() {
        this.list.clear();
        return this;
    }

    public JSONArray fluentRemove(Object obj) {
        this.list.remove(obj);
        return this;
    }

    public JSONArray fluentRemoveAll(Collection<?> collection) {
        this.list.removeAll(collection);
        return this;
    }

    public JSONArray fluentRetainAll(Collection<?> collection) {
        this.list.retainAll(collection);
        return this;
    }

    public JSONArray fluentSet(int i, Object obj) {
        set(i, obj);
        return this;
    }

    @Override // java.util.List
    public Object get(int i) {
        return this.list.get(i);
    }

    public BigDecimal getBigDecimal(int i) {
        return l.i(get(i));
    }

    public BigInteger getBigInteger(int i) {
        return l.j(get(i));
    }

    public Boolean getBoolean(int i) {
        Object obj = get(i);
        if (obj == null) {
            return null;
        }
        return l.k(obj);
    }

    public boolean getBooleanValue(int i) {
        Object obj = get(i);
        if (obj == null) {
            return false;
        }
        return l.k(obj).booleanValue();
    }

    public Byte getByte(int i) {
        return l.l(get(i));
    }

    public byte getByteValue(int i) {
        Byte bL = l.l(get(i));
        if (bL == null) {
            return (byte) 0;
        }
        return bL.byteValue();
    }

    public Type getComponentType() {
        return this.componentType;
    }

    public Date getDate(int i) {
        return l.o(get(i));
    }

    public Double getDouble(int i) {
        return l.q(get(i));
    }

    public double getDoubleValue(int i) {
        Double dQ = l.q(get(i));
        if (dQ == null) {
            return 0.0d;
        }
        return dQ.doubleValue();
    }

    public Float getFloat(int i) {
        return l.s(get(i));
    }

    public float getFloatValue(int i) {
        Float fS = l.s(get(i));
        if (fS == null) {
            return 0.0f;
        }
        return fS.floatValue();
    }

    public int getIntValue(int i) {
        Integer numT = l.t(get(i));
        if (numT == null) {
            return 0;
        }
        return numT.intValue();
    }

    public Integer getInteger(int i) {
        return l.t(get(i));
    }

    public JSONArray getJSONArray(int i) {
        Object obj = this.list.get(i);
        return obj instanceof JSONArray ? (JSONArray) obj : obj instanceof List ? new JSONArray((List<Object>) obj) : (JSONArray) a.toJSON(obj);
    }

    public JSONObject getJSONObject(int i) {
        Object obj = this.list.get(i);
        return obj instanceof JSONObject ? (JSONObject) obj : obj instanceof Map ? new JSONObject((Map<String, Object>) obj) : (JSONObject) a.toJSON(obj);
    }

    public Long getLong(int i) {
        return l.w(get(i));
    }

    public long getLongValue(int i) {
        Long lW = l.w(get(i));
        if (lW == null) {
            return 0L;
        }
        return lW.longValue();
    }

    public <T> T getObject(int i, Class<T> cls) {
        return (T) l.u(this.list.get(i), cls);
    }

    public Object getRelatedArray() {
        return this.relatedArray;
    }

    public Short getShort(int i) {
        return l.x(get(i));
    }

    public short getShortValue(int i) {
        Short shX = l.x(get(i));
        if (shX == null) {
            return (short) 0;
        }
        return shX.shortValue();
    }

    public java.sql.Date getSqlDate(int i) {
        return l.y(get(i));
    }

    public String getString(int i) {
        return l.A(get(i));
    }

    public Timestamp getTimestamp(int i) {
        return l.B(get(i));
    }

    @Override // java.util.List, java.util.Collection
    public int hashCode() {
        return this.list.hashCode();
    }

    @Override // java.util.List
    public int indexOf(Object obj) {
        return this.list.indexOf(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override // java.util.List, java.util.Collection, java.lang.Iterable
    public Iterator<Object> iterator() {
        return this.list.iterator();
    }

    @Override // java.util.List
    public int lastIndexOf(Object obj) {
        return this.list.lastIndexOf(obj);
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator() {
        return this.list.listIterator();
    }

    @Override // java.util.List, java.util.Collection
    public boolean remove(Object obj) {
        return this.list.remove(obj);
    }

    @Override // java.util.List, java.util.Collection
    public boolean removeAll(Collection<?> collection) {
        return this.list.removeAll(collection);
    }

    @Override // java.util.List, java.util.Collection
    public boolean retainAll(Collection<?> collection) {
        return this.list.retainAll(collection);
    }

    @Override // java.util.List
    public Object set(int i, Object obj) {
        if (i == -1) {
            this.list.add(obj);
            return null;
        }
        if (this.list.size() > i) {
            return this.list.set(i, obj);
        }
        for (int size = this.list.size(); size < i; size++) {
            this.list.add(null);
        }
        this.list.add(obj);
        return null;
    }

    public void setComponentType(Type type) {
        this.componentType = type;
    }

    public void setRelatedArray(Object obj) {
        this.relatedArray = obj;
    }

    @Override // java.util.List, java.util.Collection
    public int size() {
        return this.list.size();
    }

    @Override // java.util.List
    public List<Object> subList(int i, int i2) {
        return this.list.subList(i, i2);
    }

    @Override // java.util.List, java.util.Collection
    public Object[] toArray() {
        return this.list.toArray();
    }

    public <T> List<T> toJavaList(Class<T> cls) {
        ArrayList arrayList = new ArrayList(size());
        h hVarS = h.s();
        Iterator<Object> it = iterator();
        while (it.hasNext()) {
            arrayList.add(l.f(it.next(), cls, hVarS));
        }
        return arrayList;
    }

    @Override // java.util.List
    public void add(int i, Object obj) {
        this.list.add(i, obj);
    }

    @Override // java.util.List
    public boolean addAll(int i, Collection<? extends Object> collection) {
        return this.list.addAll(i, collection);
    }

    public JSONArray fluentAdd(int i, Object obj) {
        this.list.add(i, obj);
        return this;
    }

    public JSONArray fluentAddAll(int i, Collection<?> collection) {
        this.list.addAll(i, collection);
        return this;
    }

    public JSONArray fluentRemove(int i) {
        this.list.remove(i);
        return this;
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator(int i) {
        return this.list.listIterator(i);
    }

    @Override // java.util.List
    public Object remove(int i) {
        return this.list.remove(i);
    }

    @Override // java.util.List, java.util.Collection
    public <T> T[] toArray(T[] tArr) {
        return (T[]) this.list.toArray(tArr);
    }

    public JSONArray(List<Object> list) {
        if (list != null) {
            this.list = list;
            return;
        }
        throw new IllegalArgumentException("list is null.");
    }

    public <T> T getObject(int i, Type type) {
        Object obj = this.list.get(i);
        if (type instanceof Class) {
            return (T) l.u(obj, (Class) type);
        }
        return (T) a.parseObject(a.toJSONString(obj), type, new Feature[0]);
    }

    public JSONArray(int i) {
        this.list = new ArrayList(i);
    }
}
