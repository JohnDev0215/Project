package com.byd.overseaappstore.ui.view.base;

import android.view.View;
import androidx.viewbinding.ViewBinding;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u00020\u0003B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\bB\u0011\b\u0016\u0012\u0006\u0010\u0006\u001a\u00028\u0000¢\u0006\u0004\b\u0007\u0010\t¨\u0006\n"}, d2 = {"Lcom/byd/overseaappstore/ui/view/base/ContentViewHolder;", "Landroidx/viewbinding/ViewBinding;", "Binding", "Lcom/byd/overseaappstore/ui/view/base/AbsHolder;", "Landroid/view/View;", "view", "binding", "<init>", "(Landroid/view/View;Landroidx/viewbinding/ViewBinding;)V", "(Landroidx/viewbinding/ViewBinding;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class ContentViewHolder<Binding extends ViewBinding> extends AbsHolder {

    @NotNull
    public Binding a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ContentViewHolder(@NotNull View view, @NotNull Binding binding) {
        super(view);
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(binding, "binding");
        this.a = binding;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ContentViewHolder(@NotNull Binding binding) {
        Intrinsics.checkNotNullParameter(binding, "binding");
        View root = binding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        this(root, binding);
    }
}
