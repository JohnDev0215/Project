package com.byd.cleardata.ui.g;

import androidx.recyclerview.widget.RecyclerView;

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
