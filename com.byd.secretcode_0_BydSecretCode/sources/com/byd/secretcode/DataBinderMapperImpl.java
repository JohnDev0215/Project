package com.byd.secretcode;

import android.util.SparseIntArray;
import defpackage.db;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends db {
    public static final SparseIntArray a = new SparseIntArray(0);

    @Override // defpackage.db
    public List<db> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }
}
