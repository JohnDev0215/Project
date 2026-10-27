package com.drakeet.multitype;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ItemViewBinder.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0004B\u0005¢\u0006\u0002\u0010\u0005J\u001b\u0010\u0006\u001a\u00028\u00012\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u001d\u0010\u0006\u001a\u00028\u00012\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/drakeet/multitype/ItemViewBinder;", "T", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lcom/drakeet/multitype/ItemViewDelegate;", "()V", "onCreateViewHolder", "context", "Landroid/content/Context;", "parent", "Landroid/view/ViewGroup;", "(Landroid/content/Context;Landroid/view/ViewGroup;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "inflater", "Landroid/view/LayoutInflater;", "(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;)Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "multitype"}, k = 1, mv = {1, 4, 2})
public abstract class ItemViewBinder<T, VH extends RecyclerView.ViewHolder> extends ItemViewDelegate<T, VH> {
    public abstract VH onCreateViewHolder(LayoutInflater inflater, ViewGroup parent);

    @Override // com.drakeet.multitype.ItemViewDelegate
    public final VH onCreateViewHolder(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "LayoutInflater.from(context)");
        return (VH) onCreateViewHolder(layoutInflaterFrom, parent);
    }
}
