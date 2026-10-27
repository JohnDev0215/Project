package com.byd.uereport;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;
import u0.d;

/* JADX INFO: loaded from: classes2.dex */
public class DataBinderMapperImpl extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f3036a = new SparseIntArray(0);

    @Override // u0.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(5);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.byd.base.DataBinderMapperImpl());
        arrayList.add(new com.byd.bydcommonlib.DataBinderMapperImpl());
        arrayList.add(new com.byd.displaymode.DataBinderMapperImpl());
        arrayList.add(new com.byd.shareddata.DataBinderMapperImpl());
        return arrayList;
    }
}
