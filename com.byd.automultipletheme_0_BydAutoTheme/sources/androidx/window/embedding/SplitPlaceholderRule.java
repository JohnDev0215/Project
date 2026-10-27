package androidx.window.embedding;

import android.content.Intent;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SplitPlaceholderRule.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u000b\n\u0002\u0010\u0000\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001BU\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n\u0012\b\b\u0002\u0010\f\u001a\u00020\n\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e\u0012\b\b\u0002\u0010\u000f\u001a\u00020\n¢\u0006\u0002\u0010\u0010J\u0013\u0010\u0018\u001a\u00020\b2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0096\u0002J\b\u0010\u001b\u001a\u00020\nH\u0016J\u0016\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u001d\u001a\u00020\u0004H\u0080\u0002¢\u0006\u0002\b\u001eR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\u0015R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001f"}, d2 = {"Landroidx/window/embedding/SplitPlaceholderRule;", "Landroidx/window/embedding/SplitRule;", "filters", "", "Landroidx/window/embedding/ActivityFilter;", "placeholderIntent", "Landroid/content/Intent;", "isSticky", "", "finishPrimaryWithSecondary", "", "minWidth", "minSmallestWidth", "splitRatio", "", "layoutDirection", "(Ljava/util/Set;Landroid/content/Intent;ZIIIFI)V", "getFilters", "()Ljava/util/Set;", "getFinishPrimaryWithSecondary", "()I", "()Z", "getPlaceholderIntent", "()Landroid/content/Intent;", "equals", "other", "", "hashCode", "plus", "filter", "plus$window_release", "window_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SplitPlaceholderRule extends SplitRule {
    private final Set<ActivityFilter> filters;
    private final int finishPrimaryWithSecondary;
    private final boolean isSticky;
    private final Intent placeholderIntent;

    public /* synthetic */ SplitPlaceholderRule(Set set, Intent intent, boolean z, int i, int i2, int i3, float f, int i4, int i5, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, intent, z, (i5 & 8) != 0 ? 1 : i, (i5 & 16) != 0 ? 0 : i2, (i5 & 32) != 0 ? 0 : i3, (i5 & 64) != 0 ? 0.5f : f, (i5 & 128) != 0 ? 3 : i4);
    }

    public final Intent getPlaceholderIntent() {
        return this.placeholderIntent;
    }

    /* JADX INFO: renamed from: isSticky, reason: from getter */
    public final boolean getIsSticky() {
        return this.isSticky;
    }

    public final int getFinishPrimaryWithSecondary() {
        return this.finishPrimaryWithSecondary;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SplitPlaceholderRule(Set<ActivityFilter> filters, Intent placeholderIntent, boolean z, int i, int i2, int i3, float f, int i4) {
        super(i2, i3, f, i4);
        Intrinsics.checkNotNullParameter(filters, "filters");
        Intrinsics.checkNotNullParameter(placeholderIntent, "placeholderIntent");
        this.placeholderIntent = placeholderIntent;
        this.isSticky = z;
        this.finishPrimaryWithSecondary = i;
        this.filters = CollectionsKt.toSet(filters);
    }

    public final Set<ActivityFilter> getFilters() {
        return this.filters;
    }

    public final SplitPlaceholderRule plus$window_release(ActivityFilter filter) {
        Intrinsics.checkNotNullParameter(filter, "filter");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.addAll(this.filters);
        linkedHashSet.add(filter);
        return new SplitPlaceholderRule(CollectionsKt.toSet(linkedHashSet), this.placeholderIntent, this.isSticky, this.finishPrimaryWithSecondary, getMinWidth(), getMinSmallestWidth(), getSplitRatio(), getLayoutDirection());
    }

    @Override // androidx.window.embedding.SplitRule
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SplitPlaceholderRule) || !super.equals(other)) {
            return false;
        }
        SplitPlaceholderRule splitPlaceholderRule = (SplitPlaceholderRule) other;
        return Intrinsics.areEqual(this.placeholderIntent, splitPlaceholderRule.placeholderIntent) && this.isSticky == splitPlaceholderRule.isSticky && this.finishPrimaryWithSecondary == splitPlaceholderRule.finishPrimaryWithSecondary && Intrinsics.areEqual(this.filters, splitPlaceholderRule.filters);
    }

    @Override // androidx.window.embedding.SplitRule
    public int hashCode() {
        return (((((((super.hashCode() * 31) + this.placeholderIntent.hashCode()) * 31) + ActivityRule$$ExternalSyntheticBackport0.m(this.isSticky)) * 31) + this.finishPrimaryWithSecondary) * 31) + this.filters.hashCode();
    }
}
