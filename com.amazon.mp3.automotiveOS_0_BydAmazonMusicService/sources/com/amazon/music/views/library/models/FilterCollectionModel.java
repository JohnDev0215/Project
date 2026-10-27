package com.amazon.music.views.library.models;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterCollectionModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b$\b\u0086\b\u0018\u0000 =2\u00020\u0001:\u0001=BU\u0012\u0006\u0010\u0016\u001a\u00020\u0014\u0012\u000e\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u001a\u0012\b\u0010!\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\u0004\u0012\b\b\u0002\u0010'\u001a\u00020\t\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u00101\u001a\u00020\t¢\u0006\u0004\b:\u0010;B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b:\u0010<J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\tH\u0016J\b\u0010\u000b\u001a\u00020\tH\u0016J\n\u0010\f\u001a\u0004\u0018\u00010\u0000H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0016J\u0006\u0010\u000f\u001a\u00020\u0000J\u0013\u0010\u0012\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0096\u0002J\b\u0010\u0013\u001a\u00020\u0004H\u0016J\t\u0010\u0015\u001a\u00020\u0014HÖ\u0001R\u0017\u0010\u0016\u001a\u00020\u00148\u0006¢\u0006\f\n\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R*\u0010\u001b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R\u0019\u0010!\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b!\u0010\u0017\u001a\u0004\b\"\u0010\u0019R\u0019\u0010#\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010'\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R$\u0010+\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.\"\u0004\b/\u00100R\u0017\u00101\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b1\u0010(\u001a\u0004\b1\u0010*R$\u00102\u001a\u0004\u0018\u00010\r8F@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b2\u0010,\u001a\u0004\b3\u0010.\"\u0004\b4\u00100R*\u00105\u001a\u0004\u0018\u00010\r8\u0006@\u0006X\u0087\u000e¢\u0006\u0018\n\u0004\b5\u0010,\u0012\u0004\b8\u00109\u001a\u0004\b6\u0010.\"\u0004\b7\u00100¨\u0006>"}, d2 = {"Lcom/amazon/music/views/library/models/FilterCollectionModel;", "Lcom/amazon/music/views/library/models/FilterModel;", "Landroid/os/Parcel;", "parcel", "", "flags", "", "writeToParcel", "describeContents", "", "isCollection", "isSelected", "getCollectionModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getItemModel", "deepCopy", "", "other", "equals", "hashCode", "", "toString", "title", "Ljava/lang/String;", "getTitle", "()Ljava/lang/String;", "", "filters", "Ljava/util/List;", "getFilters", "()Ljava/util/List;", "setFilters", "(Ljava/util/List;)V", "blockRef", "getBlockRef", "viewId", "Ljava/lang/Integer;", "getViewId", "()Ljava/lang/Integer;", "canHaveMultipleSelections", "Z", "getCanHaveMultipleSelections", "()Z", "defaultSelectedFilter", "Lcom/amazon/music/views/library/models/FilterItemModel;", "getDefaultSelectedFilter", "()Lcom/amazon/music/views/library/models/FilterItemModel;", "setDefaultSelectedFilter", "(Lcom/amazon/music/views/library/models/FilterItemModel;)V", "isSortCollection", "selectedSortingFilterModel", "getSelectedSortingFilterModel", "setSelectedSortingFilterModel", "selectedFilter", "getSelectedFilter", "setSelectedFilter", "getSelectedFilter$annotations", "()V", "<init>", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/Integer;ZLcom/amazon/music/views/library/models/FilterItemModel;Z)V", "(Landroid/os/Parcel;)V", "CREATOR", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class FilterCollectionModel implements FilterModel {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String blockRef;
    private final boolean canHaveMultipleSelections;
    private FilterItemModel defaultSelectedFilter;
    private List<FilterItemModel> filters;
    private final boolean isSortCollection;
    private FilterItemModel selectedFilter;
    private FilterItemModel selectedSortingFilterModel;
    private final String title;
    private final Integer viewId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public FilterCollectionModel getCollectionModel() {
        return this;
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public FilterItemModel getItemModel() {
        return null;
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public boolean isCollection() {
        return true;
    }

    public String toString() {
        return "FilterCollectionModel(title=" + this.title + ", filters=" + this.filters + ", blockRef=" + ((Object) this.blockRef) + ", viewId=" + this.viewId + ", canHaveMultipleSelections=" + this.canHaveMultipleSelections + ", defaultSelectedFilter=" + this.defaultSelectedFilter + ", isSortCollection=" + this.isSortCollection + ')';
    }

    public FilterCollectionModel(String title, List<FilterItemModel> filters, String str, Integer num, boolean z, FilterItemModel filterItemModel, boolean z2) throws Exception {
        Intrinsics.checkNotNullParameter(title, "title");
        Intrinsics.checkNotNullParameter(filters, "filters");
        this.title = title;
        this.filters = filters;
        this.blockRef = str;
        this.viewId = num;
        this.canHaveMultipleSelections = z;
        this.defaultSelectedFilter = filterItemModel;
        this.isSortCollection = z2;
        if (z2 && z) {
            throw new Exception("Multiple FilterModel selection is not allowed for sort FilterCollection");
        }
    }

    public /* synthetic */ FilterCollectionModel(String str, List list, String str2, Integer num, boolean z, FilterItemModel filterItemModel, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, str2, (i & 8) != 0 ? null : num, (i & 16) != 0 ? true : z, (i & 32) != 0 ? null : filterItemModel, (i & 64) != 0 ? false : z2);
    }

    public final String getTitle() {
        return this.title;
    }

    public final List<FilterItemModel> getFilters() {
        return this.filters;
    }

    public final void setFilters(List<FilterItemModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.filters = list;
    }

    public final Integer getViewId() {
        return this.viewId;
    }

    public final FilterItemModel getDefaultSelectedFilter() {
        return this.defaultSelectedFilter;
    }

    /* JADX INFO: renamed from: isSortCollection, reason: from getter */
    public final boolean getIsSortCollection() {
        return this.isSortCollection;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FilterCollectionModel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        String str = string == null ? "" : string;
        List listCreateTypedArrayList = parcel.createTypedArrayList(FilterItemModel.INSTANCE);
        List listEmptyList = listCreateTypedArrayList == null ? CollectionsKt__CollectionsKt.emptyList() : listCreateTypedArrayList;
        String string2 = parcel.readString();
        this(str, listEmptyList, string2 == null ? "" : string2, (Integer) parcel.readValue(Integer.TYPE.getClassLoader()), parcel.readByte() == 1, (FilterItemModel) parcel.readParcelable(FilterItemModel.class.getClassLoader()), parcel.readByte() == 1);
    }

    public final void setSelectedSortingFilterModel(FilterItemModel filterItemModel) {
        this.selectedSortingFilterModel = filterItemModel;
    }

    public final FilterItemModel getSelectedSortingFilterModel() {
        FilterItemModel filterItemModel = this.selectedSortingFilterModel;
        return filterItemModel == null ? this.defaultSelectedFilter : filterItemModel;
    }

    public final FilterItemModel getSelectedFilter() {
        return this.selectedFilter;
    }

    public final void setSelectedFilter(FilterItemModel filterItemModel) {
        this.selectedFilter = filterItemModel;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.title);
        parcel.writeTypedList(this.filters);
        parcel.writeString(this.blockRef);
        parcel.writeValue(this.viewId);
        parcel.writeByte(this.canHaveMultipleSelections ? (byte) 1 : (byte) 0);
        parcel.writeParcelable(this.defaultSelectedFilter, 0);
        parcel.writeByte(this.isSortCollection ? (byte) 1 : (byte) 0);
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public boolean isSelected() {
        if (this.isSortCollection) {
            return getSelectedSortingFilterModel() != null;
        }
        List<FilterItemModel> list = this.filters;
        if (!(list instanceof Collection) || !list.isEmpty()) {
            for (FilterItemModel filterItemModel : list) {
                if (filterItemModel == null ? false : filterItemModel.isSelected()) {
                    return true;
                }
            }
        }
        return false;
    }

    public final FilterCollectionModel deepCopy() {
        FilterCollectionModel filterCollectionModel = new FilterCollectionModel(this.title, CollectionsKt__CollectionsKt.emptyList(), this.blockRef, this.viewId, this.canHaveMultipleSelections, null, this.isSortCollection);
        ArrayList arrayList = new ArrayList();
        Iterator<FilterItemModel> it = this.filters.iterator();
        FilterItemModel filterItemModel = null;
        FilterItemModel filterItemModel2 = null;
        while (it.hasNext()) {
            FilterItemModel next = it.next();
            FilterItemModel filterItemModelDeepCopy$default = next == null ? null : FilterItemModel.deepCopy$default(next, null, 1, null);
            if (getSelectedSortingFilterModel() != null && Intrinsics.areEqual(filterItemModelDeepCopy$default, getSelectedSortingFilterModel())) {
                filterItemModel = filterItemModelDeepCopy$default;
            }
            FilterItemModel filterItemModel3 = this.defaultSelectedFilter;
            if (filterItemModel3 != null && Intrinsics.areEqual(filterItemModelDeepCopy$default, filterItemModel3)) {
                filterItemModel2 = filterItemModelDeepCopy$default;
            }
            if (filterItemModelDeepCopy$default != null) {
                filterItemModelDeepCopy$default.setParentCollection(filterCollectionModel);
            }
            arrayList.add(filterItemModelDeepCopy$default);
        }
        filterCollectionModel.filters = arrayList;
        filterCollectionModel.selectedSortingFilterModel = filterItemModel;
        filterCollectionModel.defaultSelectedFilter = filterItemModel2;
        return filterCollectionModel;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(FilterCollectionModel.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            FilterCollectionModel filterCollectionModel = (FilterCollectionModel) other;
            return Intrinsics.areEqual(this.title, filterCollectionModel.title) && Intrinsics.areEqual(this.filters, filterCollectionModel.filters) && Intrinsics.areEqual(this.blockRef, filterCollectionModel.blockRef) && Intrinsics.areEqual(this.viewId, filterCollectionModel.viewId) && this.canHaveMultipleSelections == filterCollectionModel.canHaveMultipleSelections && Intrinsics.areEqual(this.defaultSelectedFilter, filterCollectionModel.defaultSelectedFilter) && this.isSortCollection == filterCollectionModel.isSortCollection;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.FilterCollectionModel");
    }

    public int hashCode() {
        int iHashCode = ((this.title.hashCode() * 31) + this.filters.hashCode()) * 31;
        String str = this.blockRef;
        return iHashCode + (str == null ? 0 : str.hashCode());
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.models.FilterCollectionModel$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: FilterCollectionModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u001f\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/models/FilterCollectionModel$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "Landroid/os/Parcel;", "parcel", "createFromParcel", "", "size", "", "newArray", "(I)[Lcom/amazon/music/views/library/models/FilterCollectionModel;", "<init>", "()V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion implements Parcelable.Creator<FilterCollectionModel> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterCollectionModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FilterCollectionModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterCollectionModel[] newArray(int size) {
            return new FilterCollectionModel[size];
        }
    }
}
