package com.google.android.material.internal;

import android.content.Context;
import android.util.AttributeSet;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import p.qj1;
import p.si1;

/* JADX INFO: loaded from: classes.dex */
public class NavigationMenuView extends RecyclerView implements qj1 {
    public NavigationMenuView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, 0);
        setLayoutManager(new LinearLayoutManager(1));
    }

    @Override // p.qj1
    public final void b(si1 si1Var) {
    }

    public int getWindowAnimations() {
        return 0;
    }
}
