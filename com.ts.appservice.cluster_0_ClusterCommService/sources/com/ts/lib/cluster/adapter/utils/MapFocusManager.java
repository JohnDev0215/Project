package com.ts.lib.cluster.adapter.utils;

import android.util.Log;
import com.ts.lib.cluster.adapter.someip.SomeIpClusterAdapter;
import java.util.LinkedList;

/* JADX INFO: loaded from: classes.dex */
public class MapFocusManager {
    private static final int ILLEGAL_MAP_TYPE = 0;
    private LinkedList<Integer> mFocusedMapList = new LinkedList<>();
    private OnMapFocusChangeListener mMapFocusChangeListener;

    public synchronized boolean requestMapFocus(int mapType) {
        int currentFocusMap;
        if (mapType <= 0) {
            Log.w(SomeIpClusterAdapter.TAG, "illegal mapType:" + mapType);
            return false;
        }
        int index = this.mFocusedMapList.indexOf(Integer.valueOf(mapType));
        if (index != 0) {
            if (index > 0) {
                int currentFocusMap2 = this.mFocusedMapList.get(0).intValue();
                this.mFocusedMapList.remove(index);
                this.mFocusedMapList.addFirst(Integer.valueOf(mapType));
                callbackFocusChangedToListener(currentFocusMap2, mapType);
            } else {
                if (this.mFocusedMapList.size() != 0) {
                    currentFocusMap = this.mFocusedMapList.get(0).intValue();
                } else {
                    currentFocusMap = 0;
                }
                this.mFocusedMapList.addFirst(Integer.valueOf(mapType));
                callbackFocusChangedToListener(currentFocusMap, mapType);
            }
        }
        return true;
    }

    public synchronized boolean abandonMapFocus(int mapType) {
        int newFocusMap;
        int index = this.mFocusedMapList.indexOf(Integer.valueOf(mapType));
        if (index == 0) {
            this.mFocusedMapList.removeFirst();
            if (this.mFocusedMapList.size() == 0) {
                newFocusMap = 0;
            } else {
                newFocusMap = this.mFocusedMapList.getFirst().intValue();
            }
            callbackFocusChangedToListener(mapType, newFocusMap);
            return true;
        }
        if (index > 0) {
            this.mFocusedMapList.remove(index);
            return true;
        }
        return false;
    }

    public synchronized void registerMapFocusChangeListener(OnMapFocusChangeListener listener) {
        this.mMapFocusChangeListener = listener;
    }

    private void callbackFocusChangedToListener(int previousMap, int focusMap) {
        OnMapFocusChangeListener onMapFocusChangeListener = this.mMapFocusChangeListener;
        if (onMapFocusChangeListener != null) {
            onMapFocusChangeListener.onMapFocusChanged(previousMap, focusMap);
        }
    }
}
