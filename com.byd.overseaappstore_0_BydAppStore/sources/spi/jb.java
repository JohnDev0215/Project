package spi;

import androidx.collection.ArraySet;
import androidx.recyclerview.widget.DiffUtil;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class jb extends DiffUtil.ItemCallback<Software> {

    @NotNull
    public final ArraySet<String> a;

    public jb(@NotNull ArraySet<String> picErrorCollect) {
        Intrinsics.checkNotNullParameter(picErrorCollect, "picErrorCollect");
        this.a = picErrorCollect;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areContentsTheSame(Software software, Software software2) {
        Software oldItem = software;
        Software newItem = software2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return oldItem.getState() == newItem.getState() && oldItem.getPercentCount() == newItem.getPercentCount() && oldItem.getTotalSize() == newItem.getTotalSize();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areItemsTheSame(Software software, Software software2) {
        Software oldItem = software;
        Software newItem = software2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return !this.a.contains(newItem.getPackageName()) && Intrinsics.areEqual(oldItem.getPackageName(), newItem.getPackageName());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final Object getChangePayload(Software software, Software software2) {
        Software oldItem = software;
        Software newItem = software2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return newItem;
    }
}
