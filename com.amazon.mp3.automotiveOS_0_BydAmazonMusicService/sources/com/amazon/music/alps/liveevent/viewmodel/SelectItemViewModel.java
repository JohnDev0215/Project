package com.amazon.music.alps.liveevent.viewmodel;

import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SelectItemViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0013\b\u0086\b\u0018\u00002\u00020\u0001B5\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\u0019\u0010\u001aJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006HÖ\u0003R\u0019\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000b\u001a\u0004\b\u0013\u0010\rR$\u0010\u0014\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0014\u0010\u0016\"\u0004\b\u0017\u0010\u0018¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/alps/liveevent/viewmodel/SelectItemViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "toString", "", "hashCode", "", "other", "", "equals", "viewId", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "index", "I", "getIndex", "()I", "text", "getText", "isSelected", "Ljava/lang/Boolean;", "()Ljava/lang/Boolean;", "setSelected", "(Ljava/lang/Boolean;)V", "<init>", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/Boolean;)V", "DMMALPS_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class SelectItemViewModel extends BaseViewModel {
    private final int index;
    private Boolean isSelected;
    private final String text;
    private final String viewId;

    public SelectItemViewModel() {
        this(null, 0, null, null, 15, null);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SelectItemViewModel)) {
            return false;
        }
        SelectItemViewModel selectItemViewModel = (SelectItemViewModel) other;
        return Intrinsics.areEqual(this.viewId, selectItemViewModel.viewId) && this.index == selectItemViewModel.index && Intrinsics.areEqual(this.text, selectItemViewModel.text) && Intrinsics.areEqual(this.isSelected, selectItemViewModel.isSelected);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (((str == null ? 0 : str.hashCode()) * 31) + Integer.hashCode(this.index)) * 31;
        String str2 = this.text;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        Boolean bool = this.isSelected;
        return iHashCode2 + (bool != null ? bool.hashCode() : 0);
    }

    public String toString() {
        return "SelectItemViewModel(viewId=" + ((Object) this.viewId) + ", index=" + this.index + ", text=" + ((Object) this.text) + ", isSelected=" + this.isSelected + ')';
    }

    public /* synthetic */ SelectItemViewModel(String str, int i, String str2, Boolean bool, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this((i2 & 1) != 0 ? null : str, (i2 & 2) != 0 ? 0 : i, (i2 & 4) != 0 ? null : str2, (i2 & 8) != 0 ? null : bool);
    }

    public final int getIndex() {
        return this.index;
    }

    public final String getText() {
        return this.text;
    }

    /* JADX INFO: renamed from: isSelected, reason: from getter */
    public final Boolean getIsSelected() {
        return this.isSelected;
    }

    public final void setSelected(Boolean bool) {
        this.isSelected = bool;
    }

    public SelectItemViewModel(String str, int i, String str2, Boolean bool) {
        super(str, null, 2, null);
        this.viewId = str;
        this.index = i;
        this.text = str2;
        this.isSelected = bool;
    }
}
