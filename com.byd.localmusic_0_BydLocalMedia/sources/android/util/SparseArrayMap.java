package android.util;

import java.util.function.Consumer;

/* JADX INFO: loaded from: classes3.dex */
public class SparseArrayMap<T> {
    private final SparseArray<ArrayMap<String, T>> mData = new SparseArray<>();

    public void add(int i, String str, T t) {
        ArrayMap<String, T> arrayMap = this.mData.get(i);
        if (arrayMap == null) {
            arrayMap = new ArrayMap<>();
            this.mData.put(i, arrayMap);
        }
        arrayMap.put(str, t);
    }

    public void clear() {
        for (int i = 0; i < this.mData.size(); i++) {
            this.mData.valueAt(i).clear();
        }
    }

    public boolean contains(int i, String str) {
        return this.mData.contains(i) && this.mData.get(i).containsKey(str);
    }

    public void delete(int i) {
        this.mData.delete(i);
    }

    public T delete(int i, String str) {
        ArrayMap<String, T> arrayMap = this.mData.get(i);
        if (arrayMap != null) {
            return arrayMap.remove(str);
        }
        return null;
    }

    public T get(int i, String str) {
        ArrayMap<String, T> arrayMap = this.mData.get(i);
        if (arrayMap != null) {
            return arrayMap.get(str);
        }
        return null;
    }

    public T getOrDefault(int i, String str, T t) {
        ArrayMap<String, T> arrayMap;
        return (this.mData.contains(i) && (arrayMap = this.mData.get(i)) != null && arrayMap.containsKey(str)) ? arrayMap.get(str) : t;
    }

    public int indexOfKey(int i) {
        return this.mData.indexOfKey(i);
    }

    public int indexOfKey(int i, String str) {
        ArrayMap<String, T> arrayMap = this.mData.get(i);
        if (arrayMap != null) {
            return arrayMap.indexOfKey(str);
        }
        return -1;
    }

    public int keyAt(int i) {
        return this.mData.keyAt(i);
    }

    public String keyAt(int i, int i2) {
        return this.mData.valueAt(i).keyAt(i2);
    }

    public int numMaps() {
        return this.mData.size();
    }

    public int numElementsForKey(int i) {
        ArrayMap<String, T> arrayMap = this.mData.get(i);
        if (arrayMap == null) {
            return 0;
        }
        return arrayMap.size();
    }

    public T valueAt(int i, int i2) {
        return this.mData.valueAt(i).valueAt(i2);
    }

    public void forEach(Consumer<T> consumer) {
        for (int iNumMaps = numMaps() - 1; iNumMaps >= 0; iNumMaps--) {
            ArrayMap<String, T> arrayMapValueAt = this.mData.valueAt(iNumMaps);
            for (int size = arrayMapValueAt.size() - 1; size >= 0; size--) {
                consumer.accept(arrayMapValueAt.valueAt(size));
            }
        }
    }
}
