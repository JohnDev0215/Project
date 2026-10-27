package com.byd.globalnotification.utils.binding;

import a.p.e.s;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class CenterLinearManagerLayout extends LinearLayoutManager {
    public a H;

    public static class a extends s {
        public a(Context context) {
            super(context);
        }

        @Override // a.p.e.s
        public int f(int i, int i2, int i3, int i4, int i5) {
            return (((i4 - i3) / 2) + i3) - (((i2 - i) / 2) + i);
        }

        @Override // a.p.e.s
        public float g(DisplayMetrics displayMetrics) {
            return 200.0f / displayMetrics.densityDpi;
        }
    }

    public CenterLinearManagerLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.H = new a(context);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public void W0(RecyclerView recyclerView, RecyclerView.y yVar, int i) {
        a aVar = this.H;
        aVar.f1677a = i;
        X0(aVar);
    }
}
