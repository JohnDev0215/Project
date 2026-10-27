package spi;

import androidx.recyclerview.widget.DiffUtil;
import com.byd.overseaappstore.module.ability.data.upper.Software;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class a7 extends DiffUtil.ItemCallback<Software> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areContentsTheSame(Software software, Software software2) {
        Software oldItem = software;
        Software newItem = software2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return oldItem.getState() == newItem.getState() && oldItem.getPercentCount() == newItem.getPercentCount();
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areItemsTheSame(Software software, Software software2) {
        Software oldItem = software;
        Software newItem = software2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return oldItem.getId() == newItem.getId();
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
