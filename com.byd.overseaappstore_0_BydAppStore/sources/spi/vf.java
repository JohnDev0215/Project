package spi;

import androidx.recyclerview.widget.DiffUtil;
import com.byd.overseaappstore.module.ability.data.upper.SoftwareInstalled;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class vf extends DiffUtil.ItemCallback<SoftwareInstalled> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areContentsTheSame(SoftwareInstalled softwareInstalled, SoftwareInstalled softwareInstalled2) {
        SoftwareInstalled oldItem = softwareInstalled;
        SoftwareInstalled newItem = softwareInstalled2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem.getPackageName(), oldItem.getPackageName());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areItemsTheSame(SoftwareInstalled softwareInstalled, SoftwareInstalled softwareInstalled2) {
        SoftwareInstalled oldItem = softwareInstalled;
        SoftwareInstalled newItem = softwareInstalled2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem.getPackageName(), newItem.getPackageName());
    }
}
