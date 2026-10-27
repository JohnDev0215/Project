package com.byd.bydcommonlib;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;
import u0.d;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f2680a = new SparseIntArray(0);

    @Override // u0.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }
}
