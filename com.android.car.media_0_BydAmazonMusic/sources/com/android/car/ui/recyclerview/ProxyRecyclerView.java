package com.android.car.ui.recyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes2.dex */
public final class ProxyRecyclerView extends RecyclerView {

    @NonNull
    public CarUiRecyclerView a;

    private ProxyRecyclerView(@NonNull Context context) {
        super(context);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void addItemDecoration(@NonNull RecyclerView.ItemDecoration itemDecoration) {
        this.a.addItemDecoration(itemDecoration);
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final void focusableViewAvailable(View view) {
        this.a.focusableViewAvailable(view);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    @Nullable
    public RecyclerView.Adapter getAdapter() {
        return this.a.getAdapter();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void invalidateItemDecorations() {
        this.a.invalidateItemDecorations();
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public final void scrollToPosition(int i) {
        this.a.scrollToPosition(i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(@Nullable RecyclerView.Adapter adapter) {
        this.a.setAdapter(adapter);
    }

    private ProxyRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    private ProxyRecyclerView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public ProxyRecyclerView(@NonNull Context context, @NonNull CarUiRecyclerView carUiRecyclerView) {
        super(context);
        this.a = carUiRecyclerView;
    }
}
