package com.byd.autoservice;

import android.util.SparseIntArray;
import java.util.ArrayList;
import java.util.List;
import q0.d;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final SparseIntArray f1825a = new SparseIntArray(0);

    @Override // q0.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(2);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        arrayList.add(new com.byd.bydnaviapi.DataBinderMapperImpl());
        return arrayList;
    }
}
