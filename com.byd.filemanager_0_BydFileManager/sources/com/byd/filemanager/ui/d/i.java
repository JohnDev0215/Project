package com.byd.filemanager.ui.d;

import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: compiled from: OverScrollDecoratorHelper.java */
/* JADX INFO: loaded from: classes.dex */
public class i {
    public static b a(RecyclerView recyclerView, int i) {
        if (i == 0) {
            return new k(new j(recyclerView));
        }
        if (i == 1) {
            return new a(new j(recyclerView));
        }
        throw new IllegalArgumentException("orientation");
    }
}
