package com.amazon.music.views.library.models;

import android.os.Parcel;
import android.os.Parcelable;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\n\u0002\u0010\u0000\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u0000 12\u00020\u0001:\u00011B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004Bc\b\u0007\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0012J\u0012\u0010$\u001a\u00020\u00002\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010J\b\u0010%\u001a\u00020\rH\u0016J\u0013\u0010&\u001a\u00020\n2\b\u0010'\u001a\u0004\u0018\u00010(H\u0096\u0002J\n\u0010)\u001a\u0004\u0018\u00010\u0010H\u0016J\n\u0010*\u001a\u0004\u0018\u00010\u0000H\u0016J\b\u0010+\u001a\u00020\rH\u0016J\b\u0010,\u001a\u00020\nH\u0016J\b\u0010-\u001a\u00020\nH\u0016J\u0018\u0010.\u001a\u00020/2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u00100\u001a\u00020\rH\u0016R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0014R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0011\u0010\u000e\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0017R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0017\"\u0004\b\u001d\u0010\u001eR\u0013\u0010\b\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010\u0014R\u0011\u0010\u0007\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0014R\u0015\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\n\n\u0002\u0010#\u001a\u0004\b!\u0010\"¨\u00062"}, d2 = {"Lcom/amazon/music/views/library/models/FilterItemModel;", "Lcom/amazon/music/views/library/models/FilterModel;", "parcel", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "browseId", "", "title", "subTitle", "selected", "", "blockRef", "viewId", "", "isDefaultFilter", "parentCollection", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "collectionId", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ZLjava/lang/String;Ljava/lang/Integer;ZLcom/amazon/music/views/library/models/FilterCollectionModel;Ljava/lang/String;)V", "getBlockRef", "()Ljava/lang/String;", "getBrowseId", "getCollectionId", "()Z", "getParentCollection", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setParentCollection", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "getSelected", "setSelected", "(Z)V", "getSubTitle", "getTitle", "getViewId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "deepCopy", "describeContents", "equals", "other", "", "getCollectionModel", "getItemModel", "hashCode", "isCollection", "isSelected", "writeToParcel", "", "flags", "CREATOR", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FilterItemModel implements FilterModel {

    /* JADX INFO: renamed from: CREATOR, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final String blockRef;
    private final String browseId;
    private final String collectionId;
    private final boolean isDefaultFilter;
    private FilterCollectionModel parentCollection;
    private boolean selected;
    private final String subTitle;
    private final String title;
    private final Integer viewId;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public FilterCollectionModel getCollectionModel() {
        return null;
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public FilterItemModel getItemModel() {
        return this;
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public boolean isCollection() {
        return false;
    }

    @JvmOverloads
    public FilterItemModel(String str, String title, String str2, boolean z, String str3, Integer num, boolean z2, FilterCollectionModel filterCollectionModel, String str4) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.browseId = str;
        this.title = title;
        this.subTitle = str2;
        this.selected = z;
        this.blockRef = str3;
        this.viewId = num;
        this.isDefaultFilter = z2;
        this.parentCollection = filterCollectionModel;
        this.collectionId = str4;
    }

    public /* synthetic */ FilterItemModel(String str, String str2, String str3, boolean z, String str4, Integer num, boolean z2, FilterCollectionModel filterCollectionModel, String str5, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2, (i & 4) != 0 ? null : str3, z, str4, (i & 32) != 0 ? null : num, (i & 64) != 0 ? false : z2, filterCollectionModel, (i & 256) != 0 ? null : str5);
    }

    public final String getBrowseId() {
        return this.browseId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    public final boolean getSelected() {
        return this.selected;
    }

    public final void setSelected(boolean z) {
        this.selected = z;
    }

    public final String getBlockRef() {
        return this.blockRef;
    }

    public final Integer getViewId() {
        return this.viewId;
    }

    /* JADX INFO: renamed from: isDefaultFilter, reason: from getter */
    public final boolean getIsDefaultFilter() {
        return this.isDefaultFilter;
    }

    public final FilterCollectionModel getParentCollection() {
        return this.parentCollection;
    }

    public final void setParentCollection(FilterCollectionModel filterCollectionModel) {
        this.parentCollection = filterCollectionModel;
    }

    public final String getCollectionId() {
        return this.collectionId;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public FilterItemModel(Parcel parcel) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        String string = parcel.readString();
        Intrinsics.checkNotNull(string);
        String string2 = parcel.readString();
        Intrinsics.checkNotNull(string2);
        Intrinsics.checkNotNullExpressionValue(string2, "parcel.readString()!!");
        String string3 = parcel.readString();
        Intrinsics.checkNotNull(string3);
        boolean z = parcel.readByte() != 0;
        String string4 = parcel.readString();
        Intrinsics.checkNotNull(string4);
        this(string, string2, string3, z, string4, (Integer) parcel.readValue(Integer.TYPE.getClassLoader()), parcel.readByte() != 0, null, parcel.readString());
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int flags) {
        Intrinsics.checkNotNullParameter(parcel, "parcel");
        parcel.writeString(this.browseId);
        parcel.writeString(this.title);
        parcel.writeString(this.subTitle);
        parcel.writeByte(this.selected ? (byte) 1 : (byte) 0);
        parcel.writeString(this.blockRef);
        parcel.writeValue(this.viewId);
        parcel.writeByte(this.isDefaultFilter ? (byte) 1 : (byte) 0);
    }

    @Override // com.amazon.music.views.library.models.FilterModel
    public boolean isSelected() {
        return this.selected;
    }

    public static /* synthetic */ FilterItemModel deepCopy$default(FilterItemModel filterItemModel, FilterCollectionModel filterCollectionModel, int i, Object obj) {
        if ((i & 1) != 0) {
            filterCollectionModel = null;
        }
        return filterItemModel.deepCopy(filterCollectionModel);
    }

    public final FilterItemModel deepCopy(FilterCollectionModel parentCollection) {
        return new FilterItemModel(this.browseId, this.title, this.subTitle, this.selected, this.blockRef, this.viewId, this.isDefaultFilter, parentCollection, this.collectionId);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(FilterItemModel.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            FilterItemModel filterItemModel = (FilterItemModel) other;
            return Intrinsics.areEqual(this.browseId, filterItemModel.browseId) && Intrinsics.areEqual(this.title, filterItemModel.title) && Intrinsics.areEqual(this.subTitle, filterItemModel.subTitle) && this.selected == filterItemModel.selected && Intrinsics.areEqual(this.blockRef, filterItemModel.blockRef) && this.isDefaultFilter == filterItemModel.isDefaultFilter && Intrinsics.areEqual(this.collectionId, filterItemModel.collectionId);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.FilterItemModel");
    }

    public int hashCode() {
        String str = this.browseId;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + this.title.hashCode()) * 31;
        String str2 = this.blockRef;
        return iHashCode + (str2 != null ? str2.hashCode() : 0);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.models.FilterItemModel$CREATOR, reason: from kotlin metadata */
    /* JADX INFO: compiled from: FilterItemModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u001f\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016¢\u0006\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/models/FilterItemModel$CREATOR;", "Landroid/os/Parcelable$Creator;", "Lcom/amazon/music/views/library/models/FilterItemModel;", "Landroid/os/Parcel;", "parcel", "createFromParcel", "", "size", "", "newArray", "(I)[Lcom/amazon/music/views/library/models/FilterItemModel;", "<init>", "()V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
    public static final class Companion implements Parcelable.Creator<FilterItemModel> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterItemModel createFromParcel(Parcel parcel) {
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            return new FilterItemModel(parcel);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public FilterItemModel[] newArray(int size) {
            return new FilterItemModel[size];
        }
    }
}
