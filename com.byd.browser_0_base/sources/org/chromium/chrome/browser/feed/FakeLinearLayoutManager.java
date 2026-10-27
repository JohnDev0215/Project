package org.chromium.chrome.browser.feed;

import android.content.Context;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import defpackage.j81;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class FakeLinearLayoutManager extends LinearLayoutManager implements j81 {
    public final ArrayList E;
    public final int F;
    public final int G;
    public final int H;

    public FakeLinearLayoutManager(Context context) {
        super(context);
        this.F = -1;
        this.G = -1;
        this.H = -1;
        this.E = new ArrayList();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.m
    public final int D() {
        return this.H;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.j81
    public final int b() {
        return this.F;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, defpackage.j81
    public final int d() {
        return this.G;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.m
    public final View u(int i) {
        if (i < 0) {
            return null;
        }
        ArrayList arrayList = this.E;
        if (i >= arrayList.size()) {
            return null;
        }
        return (View) arrayList.get(i);
    }
}
