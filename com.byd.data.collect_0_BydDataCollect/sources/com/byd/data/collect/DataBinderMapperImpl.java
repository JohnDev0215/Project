package com.byd.data.collect;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends androidx.databinding.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SparseIntArray f3126a = new SparseIntArray(0);

    @Override // androidx.databinding.d
    public List<androidx.databinding.d> a() {
        ArrayList arrayList = new ArrayList(3);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.byd.bms.DataBinderMapperImpl());
        arrayList.add(new com.byd.data.common.DataBinderMapperImpl());
        return arrayList;
    }
}
