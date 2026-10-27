package com.byd.overseaappstore.ui.view.base;

import android.content.Context;
import android.view.ViewGroup;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import spi.ro;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u0001*\b\b\u0001\u0010\u0003*\u00020\u00022\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u00050\u0004B\u0015\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/byd/overseaappstore/ui/view/base/BaseRvAdapter;", "Data", "Landroidx/viewbinding/ViewBinding;", "ContentBinding", "Lcom/byd/overseaappstore/ui/view/base/AbsAdapter;", "Lcom/byd/overseaappstore/ui/view/base/AbsHolder;", "Landroidx/recyclerview/widget/DiffUtil$ItemCallback;", "differ", "<init>", "(Landroidx/recyclerview/widget/DiffUtil$ItemCallback;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public abstract class BaseRvAdapter<Data, ContentBinding extends ViewBinding> extends AbsAdapter<Data, AbsHolder> {

    @Nullable
    public Function3<? super Data, ? super ContentBinding, ? super Integer, Unit> a;

    @NotNull
    public Map<Integer, Function3<Data, Integer, Integer, Unit>> b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public BaseRvAdapter(@NotNull DiffUtil.ItemCallback<Data> differ) {
        super(differ);
        Intrinsics.checkNotNullParameter(differ, "differ");
        this.b = new LinkedHashMap();
    }

    public final void a(int i, @NotNull final Function3<? super Data, ? super Integer, ? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.b.computeIfAbsent(Integer.valueOf(i), new Function() { // from class: spi.j1
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                Function3 block2 = block;
                Integer it = (Integer) obj;
                Intrinsics.checkNotNullParameter(block2, "$block");
                Intrinsics.checkNotNullParameter(it, "it");
                return block2;
            }
        });
    }

    @NotNull
    public abstract ContentBinding b(@NotNull Context context, @NotNull ViewGroup viewGroup);

    public abstract void c(@NotNull ContentViewHolder<ContentBinding> contentViewHolder, Data data, int i);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    /* JADX INFO: renamed from: d, reason: merged with bridge method [inline-methods] */
    public final void onBindViewHolder(@NotNull AbsHolder holder, int i) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        c((ContentViewHolder) holder, getCurrentList().get(i), i);
        holder.itemView.setOnClickListener(new ro(this, holder, 1));
    }

    public abstract void e(@NotNull ContentViewHolder<ContentBinding> contentViewHolder, Data data, int i, @NotNull List<Object> list);

    /* JADX WARN: Multi-variable type inference failed */
    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i, List payloads) {
        AbsHolder holder = (AbsHolder) viewHolder;
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(payloads, "payloads");
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, i);
        } else {
            e((ContentViewHolder) holder, getCurrentList().get(i), i, payloads);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        Context context = parent.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "parent.context");
        return new ContentViewHolder(b(context, parent));
    }
}
