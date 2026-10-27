package com.google.android.material.internal;

import a.b.o.i.g;
import a.b.o.i.n;
import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuView extends RecyclerView implements n {
    public NavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setLayoutManager(new LinearLayoutManager(1, false));
    }

    @Override // a.b.o.i.n
    public void d(g gVar) {
    }

    public int getWindowAnimations() {
        return 0;
    }
}
