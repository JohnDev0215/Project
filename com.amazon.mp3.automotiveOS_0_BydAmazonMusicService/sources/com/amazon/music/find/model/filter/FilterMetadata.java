package com.amazon.music.find.model.filter;

import com.amazon.music.views.library.models.FilterItemModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsJvmKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterMetadata.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u000f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u0019\u0010\t\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0001J\u0006\u0010\n\u001a\u00020\u0000J\u0013\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003J\u001e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00042\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0014H\u0002J\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\u0003J\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0003J\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0019\u001a\u00020\u001aJ\t\u0010\u001b\u001a\u00020\u001cHÖ\u0001J\t\u0010\u001d\u001a\u00020\u001aHÖ\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u001f"}, d2 = {"Lcom/amazon/music/find/model/filter/FilterMetadata;", "", "filterElements", "", "Lcom/amazon/music/find/model/filter/AbstractFilterElement;", "(Ljava/util/List;)V", "getFilterElements", "()Ljava/util/List;", "component1", "copy", "deepCopy", "equals", "", "other", "getAllAudioQualityFilters", "Lcom/amazon/music/find/model/filter/FilterElement;", "getAllFilterElements", "", "abstractElement", "childFilterElements", "", "getAllFilterItemModels", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getAllFilters", "getAudioQualityFilterById", "id", "", "hashCode", "", "toString", "Companion", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final /* data */ class FilterMetadata {
    public static final String DOLBY_ATMOS_FILTER_ID = "DOLBY_ATMOS_FILTER";
    public static final String LIBRARY_CONTAINER_ID = "LIBRARY_CONTAINER";
    public static final String MY_MUSIC_FILTER_ID = "MY_MUSIC_FILTER";
    public static final String QUALITY_CONTAINER_ID = "QUALITY_CONTAINER";
    public static final String SONY_360_FILTER_ID = "SONY_360_FILTER";
    public static final String UHD_FILTER_ID = "UHD_FILTER";
    private final List<AbstractFilterElement> filterElements;

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ FilterMetadata copy$default(FilterMetadata filterMetadata, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = filterMetadata.filterElements;
        }
        return filterMetadata.copy(list);
    }

    public final List<AbstractFilterElement> component1() {
        return this.filterElements;
    }

    public final FilterMetadata copy(List<? extends AbstractFilterElement> filterElements) {
        Intrinsics.checkNotNullParameter(filterElements, "filterElements");
        return new FilterMetadata(filterElements);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof FilterMetadata) && Intrinsics.areEqual(this.filterElements, ((FilterMetadata) other).filterElements);
    }

    public int hashCode() {
        return this.filterElements.hashCode();
    }

    public String toString() {
        return "FilterMetadata(filterElements=" + this.filterElements + ')';
    }

    /* JADX WARN: Multi-variable type inference failed */
    public FilterMetadata(List<? extends AbstractFilterElement> filterElements) {
        Intrinsics.checkNotNullParameter(filterElements, "filterElements");
        this.filterElements = filterElements;
    }

    public final List<AbstractFilterElement> getFilterElements() {
        return this.filterElements;
    }

    public final List<FilterElement> getAllFilters() {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = this.filterElements.iterator();
        while (it.hasNext()) {
            getAllFilterElements((AbstractFilterElement) it.next(), arrayList);
        }
        return arrayList;
    }

    private final void getAllFilterElements(AbstractFilterElement abstractElement, List<FilterElement> childFilterElements) {
        FilterElement filterElement = abstractElement instanceof FilterElement ? (FilterElement) abstractElement : null;
        if (filterElement != null) {
            childFilterElements.add(filterElement);
        }
        FilterContainer filterContainer = abstractElement instanceof FilterContainer ? (FilterContainer) abstractElement : null;
        List<AbstractFilterElement> children = filterContainer != null ? filterContainer.getChildren() : null;
        if (children == null) {
            return;
        }
        Iterator<T> it = children.iterator();
        while (it.hasNext()) {
            getAllFilterElements((AbstractFilterElement) it.next(), childFilterElements);
        }
    }

    public final List<FilterItemModel> getAllFilterItemModels() {
        List<FilterElement> allFilters = getAllFilters();
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(allFilters, 10));
        for (FilterElement filterElement : allFilters) {
            String id = filterElement.getId();
            String title = filterElement.getTitle();
            if (title == null) {
                title = "";
            }
            arrayList.add(new FilterItemModel(id, title, null, false, filterElement.getId(), null, false, null, null, 256, null));
        }
        return arrayList;
    }

    public final List<FilterElement> getAllAudioQualityFilters() {
        List<AbstractFilterElement> children;
        Iterator<T> it = this.filterElements.iterator();
        List<FilterElement> listFilterIsInstance = null;
        boolean z = false;
        Object obj = null;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
            } else {
                Object next = it.next();
                if (Intrinsics.areEqual(((AbstractFilterElement) next).getId(), QUALITY_CONTAINER_ID)) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj = next;
                }
            }
        }
        obj = null;
        FilterContainer filterContainer = obj instanceof FilterContainer ? (FilterContainer) obj : null;
        if (filterContainer != null && (children = filterContainer.getChildren()) != null) {
            listFilterIsInstance = CollectionsKt___CollectionsJvmKt.filterIsInstance(children, FilterElement.class);
        }
        return listFilterIsInstance == null ? CollectionsKt__CollectionsKt.emptyList() : listFilterIsInstance;
    }

    public final FilterElement getAudioQualityFilterById(String id) {
        Intrinsics.checkNotNullParameter(id, "id");
        Iterator<T> it = getAllAudioQualityFilters().iterator();
        Object obj = null;
        boolean z = false;
        Object obj2 = null;
        while (true) {
            if (it.hasNext()) {
                Object next = it.next();
                if (Intrinsics.areEqual(((FilterElement) next).getId(), id)) {
                    if (z) {
                        break;
                    }
                    z = true;
                    obj2 = next;
                }
            } else if (z) {
                obj = obj2;
            }
        }
        return (FilterElement) obj;
    }

    public final FilterMetadata deepCopy() {
        List<AbstractFilterElement> list = this.filterElements;
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((AbstractFilterElement) it.next()).deepCopy());
        }
        return new FilterMetadata(arrayList);
    }
}
