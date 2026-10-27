package p;

import java.util.Map;
import kotlin.collections.builders.MapBuilder;

/* JADX INFO: loaded from: classes.dex */
public final class je1 implements Map.Entry, g81 {
    public final MapBuilder c;
    public final int e;

    public je1(MapBuilder mapBuilder, int i) {
        v03.j(mapBuilder, "map");
        this.c = mapBuilder;
        this.e = i;
    }

    @Override // java.util.Map.Entry
    public final boolean equals(Object obj) {
        if (obj instanceof Map.Entry) {
            Map.Entry entry = (Map.Entry) obj;
            if (v03.b(entry.getKey(), getKey()) && v03.b(entry.getValue(), getValue())) {
                return true;
            }
        }
        return false;
    }

    @Override // java.util.Map.Entry
    public final Object getKey() {
        return this.c.keysArray[this.e];
    }

    @Override // java.util.Map.Entry
    public final Object getValue() {
        Object[] objArr = this.c.valuesArray;
        v03.h(objArr);
        return objArr[this.e];
    }

    @Override // java.util.Map.Entry
    public final int hashCode() {
        Object key = getKey();
        int iHashCode = key != null ? key.hashCode() : 0;
        Object value = getValue();
        return iHashCode ^ (value != null ? value.hashCode() : 0);
    }

    @Override // java.util.Map.Entry
    public final Object setValue(Object obj) {
        MapBuilder mapBuilder = this.c;
        mapBuilder.h();
        Object[] objArrF = mapBuilder.f();
        int i = this.e;
        Object obj2 = objArrF[i];
        objArrF[i] = obj;
        return obj2;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getKey());
        sb.append('=');
        sb.append(getValue());
        return sb.toString();
    }
}
