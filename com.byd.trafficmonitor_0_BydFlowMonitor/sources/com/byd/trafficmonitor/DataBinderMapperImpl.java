package com.byd.trafficmonitor;

import android.util.SparseIntArray;
import b.k.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f4374a = new SparseIntArray(0);

    @Override // b.k.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(4);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.byd.base.DataBinderMapperImpl());
        arrayList.add(new com.byd.bydcommonlib.DataBinderMapperImpl());
        arrayList.add(new com.byd.shareddata.DataBinderMapperImpl());
        return arrayList;
    }
}
