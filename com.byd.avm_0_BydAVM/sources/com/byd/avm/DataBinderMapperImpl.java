package com.byd.avm;

import android.util.SparseIntArray;
import androidx.databinding.d;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SparseIntArray f2118a = new SparseIntArray(0);

    @Override // androidx.databinding.d
    public List<d> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }
}
