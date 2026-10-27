package com.google.android.material.datepicker;

import android.content.Context;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
class n extends LinearLayoutManager {

    class a extends androidx.recyclerview.widget.h {
        a(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.h
        protected float v(DisplayMetrics displayMetrics) {
            return 100.0f / displayMetrics.densityDpi;
        }
    }

    n(Context context, int i, boolean z) {
        super(context, i, z);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.o
    public void e2(RecyclerView recyclerView, RecyclerView.a0 a0Var, int i) {
        a aVar = new a(recyclerView.getContext());
        aVar.p(i);
        f2(aVar);
    }
}
