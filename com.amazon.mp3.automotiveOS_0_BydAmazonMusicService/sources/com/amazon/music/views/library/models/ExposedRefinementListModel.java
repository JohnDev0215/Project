package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.models.base.BaseViewModel;
import com.amazon.music.views.library.models.enums.RefinementListType;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExposedRefinementListModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B7\u0012\b\u00102\u001a\u0004\u0018\u000101\u0012\u000e\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\f\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\b\b\u0002\u0010%\u001a\u00020$¢\u0006\u0004\b3\u00104J\u0019\u0010\u0006\u001a\u00020\u00052\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\u000e\u0010\t\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u0003J\u0006\u0010\u000b\u001a\u00020\nJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\fJ\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0003H\u0016J\u0013\u0010\u0013\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0096\u0002J\u0012\u0010\u0014\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0016J\b\u0010\u0015\u001a\u00020\u0003H\u0016R*\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\f8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR$\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0017\u0010%\u001a\u00020$8\u0006¢\u0006\f\n\u0004\b%\u0010&\u001a\u0004\b'\u0010(R\"\u0010\u0004\u001a\u00020\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0004\u0010)\u001a\u0004\b*\u0010+\"\u0004\b,\u0010-R*\u0010\b\u001a\u00020\u00032\u0006\u0010.\u001a\u00020\u00038\u0006@BX\u0086\u000e¢\u0006\u0012\n\u0004\b\b\u0010)\u001a\u0004\b/\u0010+\"\u0004\b0\u0010-¨\u00065"}, d2 = {"Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "Lcom/amazon/music/views/library/models/ScrollStateListener;", "", "scrollOffset", "", "show", "(Ljava/lang/Integer;)V", "visibility", "hide", "", "isExposed", "", "Lcom/amazon/music/views/library/models/FilterModel;", "getFilterModels", "newScrollOffset", "onScrollStateUpdated", "", "other", "equals", "areItemsTheSame", "hashCode", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "refinementList", "Ljava/util/List;", "getRefinementList", "()Ljava/util/List;", "setRefinementList", "(Ljava/util/List;)V", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "focusedModel", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "getFocusedModel", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "setFocusedModel", "(Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "Lcom/amazon/music/views/library/models/enums/RefinementListType;", "refinementListType", "Lcom/amazon/music/views/library/models/enums/RefinementListType;", "getRefinementListType", "()Lcom/amazon/music/views/library/models/enums/RefinementListType;", "I", "getScrollOffset", "()I", "setScrollOffset", "(I)V", "value", "getVisibility", "setVisibility", "", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "(Ljava/lang/String;Ljava/util/List;Lcom/amazon/music/views/library/models/FilterCollectionModel;Lcom/amazon/music/views/library/models/enums/RefinementListType;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class ExposedRefinementListModel extends BaseViewModel implements ScrollStateListener {
    private FilterCollectionModel focusedModel;
    private List<RefinementPillModel> refinementList;
    private final RefinementListType refinementListType;
    private int scrollOffset;
    private int visibility;

    public final List<RefinementPillModel> getRefinementList() {
        return this.refinementList;
    }

    public final void setRefinementList(List<RefinementPillModel> list) {
        this.refinementList = list;
    }

    public final FilterCollectionModel getFocusedModel() {
        return this.focusedModel;
    }

    public /* synthetic */ ExposedRefinementListModel(String str, List list, FilterCollectionModel filterCollectionModel, RefinementListType refinementListType, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, list, (i & 4) != 0 ? null : filterCollectionModel, (i & 8) != 0 ? RefinementListType.FILTER : refinementListType);
    }

    public final RefinementListType getRefinementListType() {
        return this.refinementListType;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExposedRefinementListModel(String str, List<RefinementPillModel> list, FilterCollectionModel filterCollectionModel, RefinementListType refinementListType) {
        super(str, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(refinementListType, "refinementListType");
        this.refinementList = list;
        this.focusedModel = filterCollectionModel;
        this.refinementListType = refinementListType;
        this.visibility = 8;
    }

    public final int getScrollOffset() {
        return this.scrollOffset;
    }

    public final void setScrollOffset(int i) {
        this.scrollOffset = i;
    }

    public final int getVisibility() {
        return this.visibility;
    }

    private final void setVisibility(int i) {
        if (i != this.visibility) {
            this.visibility = i;
            notifyChanged(13);
        }
    }

    public static /* synthetic */ void show$default(ExposedRefinementListModel exposedRefinementListModel, Integer num, int i, Object obj) {
        if ((i & 1) != 0) {
            num = null;
        }
        exposedRefinementListModel.show(num);
    }

    public final void show(Integer scrollOffset) {
        if (this.visibility != 0) {
            if (scrollOffset != null) {
                this.scrollOffset = scrollOffset.intValue();
            }
            setVisibility(0);
        }
    }

    public final int hide(int visibility) {
        if (this.visibility == 0 && (visibility == 8 || visibility == 4)) {
            setVisibility(visibility);
        }
        return this.scrollOffset;
    }

    public final boolean isExposed() {
        return this.visibility != 8;
    }

    public final List<FilterModel> getFilterModels() {
        ArrayList arrayList = new ArrayList();
        List<RefinementPillModel> list = this.refinementList;
        if (list != null) {
            Iterator<RefinementPillModel> it = list.iterator();
            while (it.hasNext()) {
                FilterModel content = it.next().getContent();
                if (content != null) {
                    arrayList.add(content);
                }
            }
        }
        return arrayList;
    }

    @Override // com.amazon.music.views.library.models.ScrollStateListener
    public void onScrollStateUpdated(int newScrollOffset) {
        this.scrollOffset = newScrollOffset;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ExposedRefinementListModel.class, other == null ? null : other.getClass()) || !super.equals(other)) {
            return false;
        }
        if (other != null) {
            ExposedRefinementListModel exposedRefinementListModel = (ExposedRefinementListModel) other;
            return Intrinsics.areEqual(this.refinementList, exposedRefinementListModel.refinementList) && Intrinsics.areEqual(this.focusedModel, exposedRefinementListModel.focusedModel) && this.scrollOffset == exposedRefinementListModel.scrollOffset && this.visibility == exposedRefinementListModel.visibility;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.ExposedRefinementListModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean areItemsTheSame(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ExposedRefinementListModel.class, other == null ? null : other.getClass())) {
            return false;
        }
        if (other != null) {
            return Intrinsics.areEqual(getViewReferenceId(), getViewReferenceId());
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.models.ExposedRefinementListModel");
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        List<RefinementPillModel> list = this.refinementList;
        return iHashCode + (list == null ? 0 : list.hashCode());
    }
}
