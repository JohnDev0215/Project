package com.alibaba.fastjson;

import c2.a;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import f2.h;
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
import l2.l;

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
        JSONObject.a.e();
        if (JSONObject.a.f2523d != null && !JSONObject.a.f2524e) {
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
                    h.f4913g.h(name, null);
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

    public JSONArray fluentSet(int i8, Object obj) {
        set(i8, obj);
        return this;
    }

    @Override // java.util.List
    public Object get(int i8) {
        return this.list.get(i8);
    }

    public BigDecimal getBigDecimal(int i8) {
        return l.i(get(i8));
    }

    public BigInteger getBigInteger(int i8) {
        return l.j(get(i8));
    }

    public Boolean getBoolean(int i8) {
        Object obj = get(i8);
        if (obj == null) {
            return null;
        }
        return l.k(obj);
    }

    public boolean getBooleanValue(int i8) {
        Object obj = get(i8);
        if (obj == null) {
            return false;
        }
        return l.k(obj).booleanValue();
    }

    public Byte getByte(int i8) {
        return l.l(get(i8));
    }

    public byte getByteValue(int i8) {
        Byte bL = l.l(get(i8));
        if (bL == null) {
            return (byte) 0;
        }
        return bL.byteValue();
    }

    public Type getComponentType() {
        return this.componentType;
    }

    public Date getDate(int i8) {
        return l.o(get(i8));
    }

    public Double getDouble(int i8) {
        return l.q(get(i8));
    }

    public double getDoubleValue(int i8) {
        Double dQ = l.q(get(i8));
        if (dQ == null) {
            return 0.0d;
        }
        return dQ.doubleValue();
    }

    public Float getFloat(int i8) {
        return l.s(get(i8));
    }

    public float getFloatValue(int i8) {
        Float fS = l.s(get(i8));
        if (fS == null) {
            return 0.0f;
        }
        return fS.floatValue();
    }

    public int getIntValue(int i8) {
        Integer numT = l.t(get(i8));
        if (numT == null) {
            return 0;
        }
        return numT.intValue();
    }

    public Integer getInteger(int i8) {
        return l.t(get(i8));
    }

    public JSONArray getJSONArray(int i8) {
        Object obj = this.list.get(i8);
        return obj instanceof JSONArray ? (JSONArray) obj : obj instanceof List ? new JSONArray((List<Object>) obj) : (JSONArray) a.toJSON(obj);
    }

    public JSONObject getJSONObject(int i8) {
        Object obj = this.list.get(i8);
        return obj instanceof JSONObject ? (JSONObject) obj : obj instanceof Map ? new JSONObject((Map<String, Object>) obj) : (JSONObject) a.toJSON(obj);
    }

    public Long getLong(int i8) {
        return l.w(get(i8));
    }

    public long getLongValue(int i8) {
        Long lW = l.w(get(i8));
        if (lW == null) {
            return 0L;
        }
        return lW.longValue();
    }

    public <T> T getObject(int i8, Class<T> cls) {
        return (T) l.u(this.list.get(i8), cls);
    }

    public Object getRelatedArray() {
        return this.relatedArray;
    }

    public Short getShort(int i8) {
        return l.x(get(i8));
    }

    public short getShortValue(int i8) {
        Short shX = l.x(get(i8));
        if (shX == null) {
            return (short) 0;
        }
        return shX.shortValue();
    }

    public java.sql.Date getSqlDate(int i8) {
        return l.y(get(i8));
    }

    public String getString(int i8) {
        return l.A(get(i8));
    }

    public Timestamp getTimestamp(int i8) {
        return l.B(get(i8));
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
    public Object set(int i8, Object obj) {
        if (i8 == -1) {
            this.list.add(obj);
            return null;
        }
        if (this.list.size() > i8) {
            return this.list.set(i8, obj);
        }
        for (int size = this.list.size(); size < i8; size++) {
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
    public List<Object> subList(int i8, int i9) {
        return this.list.subList(i8, i9);
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
    public void add(int i8, Object obj) {
        this.list.add(i8, obj);
    }

    @Override // java.util.List
    public boolean addAll(int i8, Collection<? extends Object> collection) {
        return this.list.addAll(i8, collection);
    }

    public JSONArray fluentAdd(int i8, Object obj) {
        this.list.add(i8, obj);
        return this;
    }

    public JSONArray fluentAddAll(int i8, Collection<?> collection) {
        this.list.addAll(i8, collection);
        return this;
    }

    public JSONArray fluentRemove(int i8) {
        this.list.remove(i8);
        return this;
    }

    @Override // java.util.List
    public ListIterator<Object> listIterator(int i8) {
        return this.list.listIterator(i8);
    }

    @Override // java.util.List
    public Object remove(int i8) {
        return this.list.remove(i8);
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

    public <T> T getObject(int i8, Type type) {
        Object obj = this.list.get(i8);
        if (type instanceof Class) {
            return (T) l.u(obj, (Class) type);
        }
        return (T) a.parseObject(a.toJSONString(obj), type, new Feature[0]);
    }

    public JSONArray(int i8) {
        this.list = new ArrayList(i8);
    }
}
