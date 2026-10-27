package com.drakeet.multitype;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ViewDelegate.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0002*\u00020\u00032\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00050\u0004:\u0001$B\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0003H\u0004J\u0018\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u0003H\u0002J\u001d\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0018J+\u0010\u0015\u001a\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0014\u001a\u00028\u00012\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0019J#\u0010\u001a\u001a\u00020\u00162\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u0017\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u001bJ\u0015\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u001d\u001a\u00020\u001eH&¢\u0006\u0002\u0010\u001fJ\u001d\u0010\u001c\u001a\u00028\u00012\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016¢\u0006\u0002\u0010\"J\u001e\u0010#\u001a\b\u0012\u0004\u0012\u00028\u00010\u00052\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020!H\u0016R\u0018\u0010\u0007\u001a\u00020\b*\u00020\u00038DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0018\u0010\u000b\u001a\u00020\b*\u00020\u00038DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u001e\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\u0005*\u00020\u00038DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010\u0010\u001a\u00020\b*\u00020\u00038DX\u0084\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\n¨\u0006%"}, d2 = {"Lcom/drakeet/multitype/ViewDelegate;", "T", "V", "Landroid/view/View;", "Lcom/drakeet/multitype/ItemViewDelegate;", "Lcom/drakeet/multitype/ViewDelegate$Holder;", "()V", "absoluteAdapterPosition", "", "getAbsoluteAdapterPosition", "(Landroid/view/View;)I", "bindingAdapterPosition", "getBindingAdapterPosition", "holder", "getHolder", "(Landroid/view/View;)Lcom/drakeet/multitype/ViewDelegate$Holder;", "layoutPosition", "getLayoutPosition", "getRecyclerLayoutParams", "Landroidx/recyclerview/widget/RecyclerView$LayoutParams;", "view", "onBindView", "", "item", "(Landroid/view/View;Ljava/lang/Object;)V", "(Lcom/drakeet/multitype/ViewDelegate$Holder;Landroid/view/View;Ljava/lang/Object;)V", "onBindViewHolder", "(Lcom/drakeet/multitype/ViewDelegate$Holder;Ljava/lang/Object;)V", "onCreateView", "context", "Landroid/content/Context;", "(Landroid/content/Context;)Landroid/view/View;", "parent", "Landroid/view/ViewGroup;", "(Landroid/content/Context;Landroid/view/ViewGroup;)Landroid/view/View;", "onCreateViewHolder", "Holder", "multitype"}, k = 1, mv = {1, 4, 2})
public abstract class ViewDelegate<T, V extends View> extends ItemViewDelegate<T, Holder<V>> {
    public abstract void onBindView(V view, T item);

    public abstract V onCreateView(Context context);

    protected final Holder<V> getHolder(View holder) throws IllegalAccessException {
        Intrinsics.checkNotNullParameter(holder, "$this$holder");
        Holder<V> holder2 = holder(holder);
        if (holder2 != null) {
            return holder2;
        }
        throw new IllegalAccessException("The view holder property can only be called after onCreateView()!");
    }

    protected final int getLayoutPosition(View layoutPosition) {
        Intrinsics.checkNotNullParameter(layoutPosition, "$this$layoutPosition");
        return getHolder(layoutPosition).getLayoutPosition();
    }

    protected final int getAbsoluteAdapterPosition(View absoluteAdapterPosition) {
        Intrinsics.checkNotNullParameter(absoluteAdapterPosition, "$this$absoluteAdapterPosition");
        return getHolder(absoluteAdapterPosition).getAbsoluteAdapterPosition();
    }

    protected final int getBindingAdapterPosition(View bindingAdapterPosition) {
        Intrinsics.checkNotNullParameter(bindingAdapterPosition, "$this$bindingAdapterPosition");
        return getHolder(bindingAdapterPosition).getBindingAdapterPosition();
    }

    public V onCreateView(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        return (V) onCreateView(context);
    }

    public void onBindView(Holder<V> holder, V view, T item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(view, "view");
        view.setTag(R.id.tagViewHolder, holder);
        onBindView(view, item);
    }

    protected final RecyclerView.LayoutParams getRecyclerLayoutParams(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            return (RecyclerView.LayoutParams) layoutParams;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.RecyclerView.LayoutParams");
    }

    private final Holder<V> holder(View view) {
        Object tag = view.getTag(R.id.tagViewHolder);
        if (!(tag instanceof Holder)) {
            tag = null;
        }
        return (Holder) tag;
    }

    @Override // com.drakeet.multitype.ItemViewDelegate
    public Holder<V> onCreateViewHolder(Context context, ViewGroup parent) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(parent, "parent");
        return new Holder<>(onCreateView(context, parent));
    }

    @Override // com.drakeet.multitype.ItemViewDelegate
    public void onBindViewHolder(Holder<V> holder, T item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        onBindView(holder, holder.getView(), item);
    }

    /* JADX INFO: compiled from: ViewDelegate.kt */
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0002\u0010\u0001*\u00020\u00022\u00020\u0003B\r\u0012\u0006\u0010\u0004\u001a\u00028\u0002¢\u0006\u0002\u0010\u0005R\u0013\u0010\u0004\u001a\u00028\u0002¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/drakeet/multitype/ViewDelegate$Holder;", "V", "Landroid/view/View;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "view", "(Landroid/view/View;)V", "getView", "()Landroid/view/View;", "Landroid/view/View;", "multitype"}, k = 1, mv = {1, 4, 2})
    public static final class Holder<V extends View> extends RecyclerView.ViewHolder {
        private final V view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Holder(V view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
        }

        public final V getView() {
            return this.view;
        }
    }
}
