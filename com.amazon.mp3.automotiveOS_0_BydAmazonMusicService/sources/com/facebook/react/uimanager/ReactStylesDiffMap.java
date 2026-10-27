package com.facebook.react.uimanager;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;

/* JADX INFO: loaded from: classes6.dex */
public class ReactStylesDiffMap {
    final ReadableMap mBackingMap;

    public ReactStylesDiffMap(ReadableMap readableMap) {
        this.mBackingMap = readableMap;
    }

    public boolean hasKey(String str) {
        return this.mBackingMap.hasKey(str);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mBackingMap.isNull(str) ? z : this.mBackingMap.getBoolean(str);
    }

    public float getFloat(String str, float f) {
        return this.mBackingMap.isNull(str) ? f : (float) this.mBackingMap.getDouble(str);
    }

    public int getInt(String str, int i) {
        return this.mBackingMap.isNull(str) ? i : this.mBackingMap.getInt(str);
    }

    @Nullable
    public String getString(String str) {
        return this.mBackingMap.getString(str);
    }

    @Nullable
    public ReadableArray getArray(String str) {
        return this.mBackingMap.getArray(str);
    }

    @Nullable
    public ReadableMap getMap(String str) {
        return this.mBackingMap.getMap(str);
    }

    public String toString() {
        return "{ " + getClass().getSimpleName() + ": " + this.mBackingMap.toString() + " }";
    }
}
