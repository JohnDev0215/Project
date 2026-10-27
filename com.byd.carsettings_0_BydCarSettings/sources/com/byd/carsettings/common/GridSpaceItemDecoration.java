package com.byd.carsettings.common;

import android.graphics.Rect;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import b.b.a.i.a;
import com.bydux.api.common.tools.util.ListUtils;

/* JADX INFO: loaded from: classes2.dex */
public class GridSpaceItemDecoration extends RecyclerView.ItemDecoration {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2791a = "GridSpaceItemDecoration";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f2792b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2793c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f2794d;

    public GridSpaceItemDecoration(int i, int i2, int i3) {
        this.f2792b = i;
        this.f2793c = i2;
        this.f2794d = i3;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        int i = this.f2792b;
        int i2 = childAdapterPosition % i;
        int i3 = this.f2794d;
        int i4 = (i2 * i3) / i;
        int i5 = i3 - (((i2 + 1) * i3) / i);
        boolean zD = a.d();
        rect.left = zD ? i5 : i4;
        if (!zD) {
            i4 = i5;
        }
        rect.right = i4;
        b.b.m.c.a.a("GridSpaceItemDecoration", "position:" + childAdapterPosition + "    columnIndex: " + i2 + "    left,right ->" + rect.left + ListUtils.DEFAULT_JOIN_SEPARATOR + rect.right);
        if (childAdapterPosition >= this.f2792b) {
            rect.top = this.f2793c;
        }
    }
}
