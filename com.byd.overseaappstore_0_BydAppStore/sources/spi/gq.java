package spi;

import androidx.recyclerview.widget.DiffUtil;
import com.byd.overseaappstore.module.ability.data.upper.ImagePath;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes.dex */
public final class gq extends DiffUtil.ItemCallback<ImagePath> {
    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areContentsTheSame(ImagePath imagePath, ImagePath imagePath2) {
        ImagePath oldItem = imagePath;
        ImagePath newItem = imagePath2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem.getPath(), newItem.getPath());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areItemsTheSame(ImagePath imagePath, ImagePath imagePath2) {
        ImagePath oldItem = imagePath;
        ImagePath newItem = imagePath2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return Intrinsics.areEqual(oldItem.getPath(), newItem.getPath());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final Object getChangePayload(ImagePath imagePath, ImagePath imagePath2) {
        ImagePath oldItem = imagePath;
        ImagePath newItem = imagePath2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return newItem;
    }
}
