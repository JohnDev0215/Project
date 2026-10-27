package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.models.base.BaseViewModel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementPillModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B;\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\u0002\u0010\fJ\u0013\u0010\u0019\u001a\u00020\u00072\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0096\u0002J\b\u0010\u001c\u001a\u00020\tH\u0016J\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0005R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0006\u001a\u00020\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0015\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u0006 "}, d2 = {"Lcom/amazon/music/views/library/models/RefinementPillModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "content", "Lcom/amazon/music/views/library/models/FilterModel;", "isFocused", "", "viewId", "", "parentRefinementListModel", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "(Ljava/lang/String;Lcom/amazon/music/views/library/models/FilterModel;ZLjava/lang/Integer;Lcom/amazon/music/views/library/models/ExposedRefinementListModel;)V", "getContent", "()Lcom/amazon/music/views/library/models/FilterModel;", "setContent", "(Lcom/amazon/music/views/library/models/FilterModel;)V", "()Z", "setFocused", "(Z)V", "getParentRefinementListModel", "()Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getViewId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "equals", "other", "", "hashCode", "updateFilterModel", "", "filterModel", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementPillModel extends BaseViewModel {
    private FilterModel content;
    private boolean isFocused;
    private final ExposedRefinementListModel parentRefinementListModel;
    private final Integer viewId;

    public /* synthetic */ RefinementPillModel(String str, FilterModel filterModel, boolean z, Integer num, ExposedRefinementListModel exposedRefinementListModel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, filterModel, (i & 4) != 0 ? false : z, (i & 8) != 0 ? null : num, (i & 16) != 0 ? null : exposedRefinementListModel);
    }

    public final FilterModel getContent() {
        return this.content;
    }

    public final void setContent(FilterModel filterModel) {
        this.content = filterModel;
    }

    /* JADX INFO: renamed from: isFocused, reason: from getter */
    public final boolean getIsFocused() {
        return this.isFocused;
    }

    public final void setFocused(boolean z) {
        this.isFocused = z;
    }

    public final Integer getViewId() {
        return this.viewId;
    }

    public final ExposedRefinementListModel getParentRefinementListModel() {
        return this.parentRefinementListModel;
    }

    public RefinementPillModel(String str, FilterModel filterModel, boolean z, Integer num, ExposedRefinementListModel exposedRefinementListModel) {
        super(str, null, 2, 0 == true ? 1 : 0);
        this.content = filterModel;
        this.isFocused = z;
        this.viewId = num;
        this.parentRefinementListModel = exposedRefinementListModel;
    }

    public final void updateFilterModel(FilterModel filterModel) {
        Intrinsics.checkNotNullParameter(filterModel, "filterModel");
        this.content = filterModel;
        notifyChanged(11);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(RefinementPillModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            RefinementPillModel refinementPillModel = (RefinementPillModel) other;
            return Intrinsics.areEqual(this.content, refinementPillModel.content) && this.isFocused == refinementPillModel.isFocused && Intrinsics.areEqual(this.viewId, refinementPillModel.viewId);
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.RefinementPillModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        FilterModel filterModel = this.content;
        return iHashCode + (filterModel == null ? 0 : filterModel.hashCode());
    }
}
