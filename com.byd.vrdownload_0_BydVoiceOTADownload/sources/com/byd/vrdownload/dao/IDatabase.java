package com.byd.vrdownload.dao;

import android.util.SparseArray;
import java.util.HashMap;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDatabase<T> {
    T find(int i);

    List<T> getAll();

    SparseArray<T> getAllIdMap();

    HashMap<String, T> getAllMap();

    Maintainer<T> getMaintainer();

    void insert(T t);

    boolean remove(int i);

    void update(T t);
}
