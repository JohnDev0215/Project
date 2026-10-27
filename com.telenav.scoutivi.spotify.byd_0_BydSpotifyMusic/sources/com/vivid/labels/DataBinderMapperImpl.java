package com.vivid.labels;

import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.ViewDataBinding;
import androidx.databinding.e;
import androidx.databinding.f;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import o3.b;

/* JADX INFO: loaded from: classes.dex */
public class DataBinderMapperImpl extends e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final SparseIntArray f5167a;

    private static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final HashMap<String, Integer> f5168a;

        static {
            HashMap<String, Integer> map = new HashMap<>(1);
            f5168a = map;
            map.put("layout/labels_item_0", Integer.valueOf(b.labels_item));
        }
    }

    static {
        SparseIntArray sparseIntArray = new SparseIntArray(1);
        f5167a = sparseIntArray;
        sparseIntArray.put(b.labels_item, 1);
    }

    @Override // androidx.databinding.e
    public List<e> a() {
        ArrayList arrayList = new ArrayList(1);
        arrayList.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
        return arrayList;
    }

    @Override // androidx.databinding.e
    public ViewDataBinding b(f fVar, View view, int i7) {
        int i8 = f5167a.get(i7);
        if (i8 <= 0) {
            return null;
        }
        Object tag = view.getTag();
        if (tag == null) {
            throw new RuntimeException("view must have a tag");
        }
        if (i8 != 1) {
            return null;
        }
        if ("layout/labels_item_0".equals(tag)) {
            return new p3.b(fVar, view);
        }
        throw new IllegalArgumentException("The tag for labels_item is invalid. Received: " + tag);
    }

    @Override // androidx.databinding.e
    public ViewDataBinding c(f fVar, View[] viewArr, int i7) {
        if (viewArr == null || viewArr.length == 0 || f5167a.get(i7) <= 0 || viewArr[0].getTag() != null) {
            return null;
        }
        throw new RuntimeException("view must have a tag");
    }

    @Override // androidx.databinding.e
    public int d(String str) {
        Integer num;
        if (str == null || (num = a.f5168a.get(str)) == null) {
            return 0;
        }
        return num.intValue();
    }
}
