package com.amazon.music.connect.customerlookup.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import com.amazon.music.connect.R$id;
import com.amazon.music.connect.R$layout;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: CustomerLookupSuggestionsAdapter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001#B\u001b\u0012\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u0013¢\u0006\u0004\b!\u0010\"J\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0016J\u0018\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0005H\u0016J\u001e\u0010\u0010\u001a\u00020\n2\u0016\u0010\u000f\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000eJ\u0006\u0010\u0011\u001a\u00020\nJ\b\u0010\u0012\u001a\u00020\u0005H\u0016R#\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\n0\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0016\u0010\u0018\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0018\u0010\u0019R\u001c\u0010\u001c\u001a\n \u001b*\u0004\u0018\u00010\u001a0\u001a8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 ¨\u0006$"}, d2 = {"Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupSuggestionsAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupSuggestionsAdapter$ViewHolder;", "Landroid/view/ViewGroup;", "parent", "", "viewType", "onCreateViewHolder", "holder", "position", "", "onBindViewHolder", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "models", "replaceAll", "clear", "getItemCount", "Lkotlin/Function1;", "clickListener", "Lkotlin/jvm/functions/Function1;", "getClickListener", "()Lkotlin/jvm/functions/Function1;", "TAG", "Ljava/lang/String;", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "Landroidx/recyclerview/widget/SortedList;", "sortedList", "Landroidx/recyclerview/widget/SortedList;", "<init>", "(Lkotlin/jvm/functions/Function1;)V", "ViewHolder", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
public final class CustomerLookupSuggestionsAdapter extends RecyclerView.Adapter<ViewHolder> {
    private final String TAG;
    private final Function1<String, Unit> clickListener;
    private final Logger logger;
    private final SortedList<String> sortedList;

    /* JADX WARN: Multi-variable type inference failed */
    public CustomerLookupSuggestionsAdapter(Function1<? super String, Unit> clickListener) {
        Intrinsics.checkNotNullParameter(clickListener, "clickListener");
        this.clickListener = clickListener;
        String simpleName = Reflection.getOrCreateKotlinClass(CustomerLookupSuggestionsAdapter.class).getSimpleName();
        this.TAG = simpleName;
        this.logger = LoggerFactory.getLogger(simpleName);
        setHasStableIds(true);
        this.sortedList = new SortedList<>(String.class, new SortedList.Callback<String>() { // from class: com.amazon.music.connect.customerlookup.adapters.CustomerLookupSuggestionsAdapter$sortedList$1
            @Override // androidx.recyclerview.widget.SortedList.Callback, java.util.Comparator
            public int compare(String a2, String b) {
                Intrinsics.checkNotNullParameter(a2, "a");
                Intrinsics.checkNotNullParameter(b, "b");
                return a2.compareTo(b);
            }

            @Override // androidx.recyclerview.widget.ListUpdateCallback
            public void onInserted(int position, int count) {
                this.this$0.notifyItemRangeInserted(position, count);
            }

            @Override // androidx.recyclerview.widget.ListUpdateCallback
            public void onRemoved(int position, int count) {
                this.this$0.notifyItemRangeRemoved(position, count);
            }

            @Override // androidx.recyclerview.widget.ListUpdateCallback
            public void onMoved(int fromPosition, int toPosition) {
                this.this$0.notifyItemMoved(fromPosition, toPosition);
            }

            @Override // androidx.recyclerview.widget.SortedList.Callback
            public void onChanged(int position, int count) {
                this.this$0.notifyItemRangeChanged(position, count);
            }

            @Override // androidx.recyclerview.widget.SortedList.Callback
            public boolean areContentsTheSame(String oldItem, String newItem) {
                Intrinsics.checkNotNullParameter(oldItem, "oldItem");
                Intrinsics.checkNotNullParameter(newItem, "newItem");
                return oldItem.equals(newItem);
            }

            @Override // androidx.recyclerview.widget.SortedList.Callback
            public boolean areItemsTheSame(String item1, String item2) {
                Intrinsics.checkNotNullParameter(item1, "item1");
                Intrinsics.checkNotNullParameter(item2, "item2");
                return item1.equals(item2);
            }
        });
    }

    /* JADX INFO: compiled from: CustomerLookupSuggestionsAdapter.kt */
    @Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u0012\u0010\fJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\"\u0010\u0007\u001a\u00020\u00068\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u0017\u0010\u000e\u001a\u00020\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/connect/customerlookup/adapters/CustomerLookupSuggestionsAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "", "result", "", "bind", "Landroid/view/View;", "view", "Landroid/view/View;", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "Landroid/widget/TextView;", "suggestion", "Landroid/widget/TextView;", "getSuggestion", "()Landroid/widget/TextView;", "<init>", "DMMConnect_release"}, k = 1, mv = {1, 6, 0})
    public static final class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView suggestion;
        private View view;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public ViewHolder(View view) {
            super(view);
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
            View viewFindViewById = view.findViewById(R$id.suggestion_text);
            if (viewFindViewById == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
            }
            this.suggestion = (TextView) viewFindViewById;
        }

        public final View getView() {
            return this.view;
        }

        public final void bind(String result) {
            Intrinsics.checkNotNullParameter(result, "result");
            this.suggestion.setText(result);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(context);
        Intrinsics.checkNotNullExpressionValue(layoutInflaterFrom, "from(context)");
        View viewInflate = layoutInflaterFrom.inflate(R$layout.customer_lookup_suggestion_layout, parent, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…on_layout, parent, false)");
        return new ViewHolder(viewInflate);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final String result = this.sortedList.get(position);
        Intrinsics.checkNotNullExpressionValue(result, "result");
        holder.bind(result);
        holder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.connect.customerlookup.adapters.CustomerLookupSuggestionsAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CustomerLookupSuggestionsAdapter.m1908onBindViewHolder$lambda0(this.f$0, result, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m1908onBindViewHolder$lambda0(CustomerLookupSuggestionsAdapter this$0, String result, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Function1<String, Unit> function1 = this$0.clickListener;
        Intrinsics.checkNotNullExpressionValue(result, "result");
        function1.invoke(result);
    }

    public final void replaceAll(ArrayList<String> models) {
        Intrinsics.checkNotNullParameter(models, "models");
        this.sortedList.beginBatchedUpdates();
        this.sortedList.clear();
        this.sortedList.replaceAll(models);
        this.sortedList.endBatchedUpdates();
    }

    public final void clear() {
        this.sortedList.beginBatchedUpdates();
        this.sortedList.clear();
        this.sortedList.endBatchedUpdates();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.sortedList.size();
    }
}
