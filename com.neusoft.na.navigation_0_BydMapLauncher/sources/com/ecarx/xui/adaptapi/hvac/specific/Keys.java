package com.ecarx.xui.adaptapi.hvac.specific;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class Keys {
    private ArrayList<Integer> _keys = new ArrayList<>();

    public void addKey(int i) {
        this._keys.add(Integer.valueOf(i));
    }

    public void removeKey(int i) {
        this._keys.remove(i);
    }

    public int getKey(int i) {
        return this._keys.get(i).intValue();
    }

    public int size() {
        return this._keys.size();
    }
}
