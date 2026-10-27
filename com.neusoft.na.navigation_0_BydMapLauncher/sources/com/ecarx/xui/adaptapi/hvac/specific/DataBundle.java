package com.ecarx.xui.adaptapi.hvac.specific;

import android.util.SparseArray;

/* JADX INFO: loaded from: classes2.dex */
public class DataBundle {
    private SparseArray<Object> _map;

    public DataBundle() {
        this._map = null;
        this._map = new SparseArray<>();
    }

    public void putInt(int i, int i2) {
        this._map.put(i, Integer.valueOf(i2));
    }

    public int getInt(int i, int i2) {
        Object obj = this._map.get(i);
        return obj == null ? i2 : ((Integer) obj).intValue();
    }

    public void putBoolean(int i, boolean z) {
        this._map.put(i, Boolean.valueOf(z));
    }

    public boolean getBoolean(int i, boolean z) {
        Object obj = this._map.get(i);
        return obj == null ? z : ((Boolean) obj).booleanValue();
    }

    public void putString(int i, String str) {
        this._map.put(i, str);
    }

    public String getString(int i, String str) {
        Object obj = this._map.get(i);
        return obj == null ? str : (String) obj;
    }

    public void remove(int i) {
        this._map.remove(i);
    }
}
