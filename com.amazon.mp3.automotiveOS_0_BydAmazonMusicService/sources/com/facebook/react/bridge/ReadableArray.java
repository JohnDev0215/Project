package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes6.dex */
public interface ReadableArray {
    @NonNull
    ReadableArray getArray(int i);

    boolean getBoolean(int i);

    double getDouble(int i);

    @NonNull
    Dynamic getDynamic(int i);

    int getInt(int i);

    @NonNull
    ReadableMap getMap(int i);

    @NonNull
    String getString(int i);

    @NonNull
    ReadableType getType(int i);

    boolean isNull(int i);

    int size();

    @NonNull
    ArrayList<Object> toArrayList();
}
