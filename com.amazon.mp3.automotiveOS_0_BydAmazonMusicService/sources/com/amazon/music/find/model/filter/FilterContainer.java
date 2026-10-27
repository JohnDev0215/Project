package com.amazon.music.find.model.filter;

import com.google.firebase.perf.util.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterContainer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0006HÆ\u0003J\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\nHÆ\u0003JA\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b2\b\b\u0002\u0010\t\u001a\u00020\nHÆ\u0001J\b\u0010\u001a\u001a\u00020\u0000H\u0016J\u0010\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u001b\u001a\u00020\u0000H\u0002J\u0013\u0010\u001c\u001a\u00020\u00062\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eHÖ\u0003J\t\u0010\u001f\u001a\u00020 HÖ\u0001J\t\u0010!\u001a\u00020\u0003HÖ\u0001R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0010R\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0014\u0010\u0004\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000f¨\u0006\""}, d2 = {"Lcom/amazon/music/find/model/filter/FilterContainer;", "Lcom/amazon/music/find/model/filter/AbstractFilterElement;", "id", "", "title", Constants.ENABLE_DISABLE, "", "children", "", "selectionMode", "Lcom/amazon/music/find/model/filter/SelectionMode;", "(Ljava/lang/String;Ljava/lang/String;ZLjava/util/List;Lcom/amazon/music/find/model/filter/SelectionMode;)V", "getChildren", "()Ljava/util/List;", "getId", "()Ljava/lang/String;", "()Z", "getSelectionMode", "()Lcom/amazon/music/find/model/filter/SelectionMode;", "getTitle", "component1", "component2", "component3", "component4", "component5", "copy", "deepCopy", "container", "equals", "other", "", "hashCode", "", "toString", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FilterContainer extends AbstractFilterElement {
    private final List<AbstractFilterElement> children;
    private final String id;
    private final boolean isEnabled;
    private final SelectionMode selectionMode;
    private final String title;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilterContainer copy$default(FilterContainer filterContainer, String str, String str2, boolean z, List list, SelectionMode selectionMode, int i, Object obj) {
        if ((i & 1) != 0) {
            str = filterContainer.getId();
        }
        if ((i & 2) != 0) {
            str2 = filterContainer.getTitle();
        }
        String str3 = str2;
        if ((i & 4) != 0) {
            z = filterContainer.getIsEnabled();
        }
        boolean z2 = z;
        if ((i & 8) != 0) {
            list = filterContainer.children;
        }
        List list2 = list;
        if ((i & 16) != 0) {
            selectionMode = filterContainer.selectionMode;
        }
        return filterContainer.copy(str, str3, z2, list2, selectionMode);
    }

    public final String component1() {
        return getId();
    }

    public final String component2() {
        return getTitle();
    }

    public final boolean component3() {
        return getIsEnabled();
    }

    public final List<AbstractFilterElement> component4() {
        return this.children;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final SelectionMode getSelectionMode() {
        return this.selectionMode;
    }

    public final FilterContainer copy(String id, String title, boolean isEnabled, List<? extends AbstractFilterElement> children, SelectionMode selectionMode) {
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(children, "children");
        Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
        return new FilterContainer(id, title, isEnabled, children, selectionMode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FilterContainer)) {
            return false;
        }
        FilterContainer filterContainer = (FilterContainer) other;
        return Intrinsics.areEqual(getId(), filterContainer.getId()) && Intrinsics.areEqual(getTitle(), filterContainer.getTitle()) && getIsEnabled() == filterContainer.getIsEnabled() && Intrinsics.areEqual(this.children, filterContainer.children) && this.selectionMode == filterContainer.selectionMode;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v3, types: [int] */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r1v9 */
    public int hashCode() {
        int iHashCode = ((getId().hashCode() * 31) + getTitle().hashCode()) * 31;
        boolean isEnabled = getIsEnabled();
        ?? r1 = isEnabled;
        if (isEnabled) {
            r1 = 1;
        }
        return ((((iHashCode + r1) * 31) + this.children.hashCode()) * 31) + this.selectionMode.hashCode();
    }

    public String toString() {
        return "FilterContainer(id=" + getId() + ", title=" + getTitle() + ", isEnabled=" + getIsEnabled() + ", children=" + this.children + ", selectionMode=" + this.selectionMode + ')';
    }

    public /* synthetic */ FilterContainer(String str, String str2, boolean z, List list, SelectionMode selectionMode, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? true : z, list, selectionMode);
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    public String getId() {
        return this.id;
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    /* JADX INFO: renamed from: isEnabled, reason: from getter */
    public boolean getIsEnabled() {
        return this.isEnabled;
    }

    public final List<AbstractFilterElement> getChildren() {
        return this.children;
    }

    public final SelectionMode getSelectionMode() {
        return this.selectionMode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FilterContainer(String id, String title, boolean z, List<? extends AbstractFilterElement> children, SelectionMode selectionMode) {
        super(id, title, z);
        Intrinsics.checkNotNullParameter(id, "id");
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(children, "children");
        Intrinsics.checkNotNullParameter(selectionMode, "selectionMode");
        this.id = id;
        this.title = title;
        this.isEnabled = z;
        this.children = children;
        this.selectionMode = selectionMode;
    }

    @Override // com.amazon.music.find.model.filter.AbstractFilterElement
    public FilterContainer deepCopy() {
        return deepCopy(this);
    }

    private final FilterContainer deepCopy(FilterContainer container) {
        String id = container.getId();
        String title = container.getTitle();
        boolean isEnabled = container.getIsEnabled();
        List<AbstractFilterElement> list = container.children;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((AbstractFilterElement) it.next()).deepCopy());
        }
        return new FilterContainer(id, title, isEnabled, arrayList, container.selectionMode);
    }
}
