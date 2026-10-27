package spi;

import androidx.collection.ArraySet;
import androidx.recyclerview.widget.DiffUtil;
import com.byd.overseaappstore.module.ability.data.upper.FeatureThemTopicGroup;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes.dex */
public final class fb extends DiffUtil.ItemCallback<FeatureThemTopicGroup> {

    @NotNull
    public final ArraySet<Integer> a;

    public fb(@NotNull ArraySet<Integer> picErrorCollect) {
        Intrinsics.checkNotNullParameter(picErrorCollect, "picErrorCollect");
        this.a = picErrorCollect;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areContentsTheSame(FeatureThemTopicGroup featureThemTopicGroup, FeatureThemTopicGroup featureThemTopicGroup2) {
        FeatureThemTopicGroup oldItem = featureThemTopicGroup;
        FeatureThemTopicGroup newItem = featureThemTopicGroup2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return false;
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final boolean areItemsTheSame(FeatureThemTopicGroup featureThemTopicGroup, FeatureThemTopicGroup featureThemTopicGroup2) {
        FeatureThemTopicGroup oldItem = featureThemTopicGroup;
        FeatureThemTopicGroup newItem = featureThemTopicGroup2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return !this.a.contains(Integer.valueOf(newItem.getId())) && oldItem.getId() == newItem.getId() && Intrinsics.areEqual(oldItem.getPicUrl(), newItem.getPicUrl());
    }

    @Override // androidx.recyclerview.widget.DiffUtil.ItemCallback
    public final Object getChangePayload(FeatureThemTopicGroup featureThemTopicGroup, FeatureThemTopicGroup featureThemTopicGroup2) {
        FeatureThemTopicGroup oldItem = featureThemTopicGroup;
        FeatureThemTopicGroup newItem = featureThemTopicGroup2;
        Intrinsics.checkNotNullParameter(oldItem, "oldItem");
        Intrinsics.checkNotNullParameter(newItem, "newItem");
        return newItem;
    }
}
