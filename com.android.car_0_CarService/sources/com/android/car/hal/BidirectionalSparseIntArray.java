package com.android.car.hal;

import android.util.SparseIntArray;

/* JADX INFO: loaded from: classes3.dex */
class BidirectionalSparseIntArray {
    private final SparseIntArray mInverseMap;
    private final SparseIntArray mMap;

    static BidirectionalSparseIntArray create(int[] keyValuePairs) {
        int inputLength = keyValuePairs.length;
        if (inputLength % 2 != 0) {
            throw new IllegalArgumentException("Odd number of key-value elements");
        }
        BidirectionalSparseIntArray biMap = new BidirectionalSparseIntArray(inputLength / 2);
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            biMap.put(keyValuePairs[i], keyValuePairs[i + 1]);
        }
        return biMap;
    }

    private BidirectionalSparseIntArray(int initialCapacity) {
        this.mMap = new SparseIntArray(initialCapacity);
        this.mInverseMap = new SparseIntArray(initialCapacity);
    }

    private void put(int key, int value) {
        this.mMap.put(key, value);
        this.mInverseMap.put(value, key);
    }

    int getValue(int key, int defaultValue) {
        return this.mMap.get(key, defaultValue);
    }

    int getKey(int value, int defaultKey) {
        return this.mInverseMap.get(value, defaultKey);
    }
}
