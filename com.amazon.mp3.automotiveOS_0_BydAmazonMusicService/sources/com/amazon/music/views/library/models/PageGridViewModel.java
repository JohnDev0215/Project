package com.amazon.music.views.library.models;

import com.amazon.music.views.library.models.base.BaseViewContainerModel;
import com.amazon.music.views.library.models.base.BaseViewModel;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PageGridViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b!\b\u0086\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0089\u0001\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u0003\u0012\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\b\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\b8\u00109J\u0093\u0001\u0010\u0014\u001a\u00020\u00002\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\b\b\u0002\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\n2\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\b2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012HÆ\u0001J\t\u0010\u0015\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0016\u001a\u00020\nHÖ\u0001J\u0013\u0010\u001a\u001a\u00020\u00192\b\u0010\u0018\u001a\u0004\u0018\u00010\u0017HÖ\u0003R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u001b\u001a\u0004\b\u001e\u0010\u001dR\u0019\u0010\u0006\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001b\u001a\u0004\b\u001f\u0010\u001dR\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00038\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b \u0010\u001dR(\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\b8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\t\u0010!\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%R\u001a\u0010\u000b\u001a\u00020\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010&\u001a\u0004\b'\u0010(R\"\u0010\f\u001a\u00020\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\f\u0010&\u001a\u0004\b)\u0010(\"\u0004\b*\u0010+R$\u0010\r\u001a\u0004\u0018\u00010\u00038\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u001b\u001a\u0004\b,\u0010\u001d\"\u0004\b-\u0010.R\u001f\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\b8\u0006¢\u0006\f\n\u0004\b\u000f\u0010!\u001a\u0004\b/\u0010#R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u00100\u001a\u0004\b1\u00102R$\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0013\u00103\u001a\u0004\b4\u00105\"\u0004\b6\u00107¨\u0006:"}, d2 = {"Lcom/amazon/music/views/library/models/PageGridViewModel;", "Lcom/amazon/music/views/library/models/base/BaseViewContainerModel;", "Lcom/amazon/music/views/library/models/base/BaseViewModel;", "", "viewId", "nestedModelViewId", "title", "subTitle", "", "models", "", "tileType", "pageIndex", "nextPageToken", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "filters", "Lcom/amazon/music/views/library/models/PageGridViewModelContext;", "context", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "header", "copy", "toString", "hashCode", "", "other", "", "equals", "Ljava/lang/String;", "getViewId", "()Ljava/lang/String;", "getNestedModelViewId", "getTitle", "getSubTitle", "Ljava/util/List;", "getModels", "()Ljava/util/List;", "setModels", "(Ljava/util/List;)V", "I", "getTileType", "()I", "getPageIndex", "setPageIndex", "(I)V", "getNextPageToken", "setNextPageToken", "(Ljava/lang/String;)V", "getFilters", "Lcom/amazon/music/views/library/models/PageGridViewModelContext;", "getContext", "()Lcom/amazon/music/views/library/models/PageGridViewModelContext;", "Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "getHeader", "()Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "setHeader", "(Lcom/amazon/music/views/library/models/ExposedRefinementListModel;)V", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;IILjava/lang/String;Ljava/util/List;Lcom/amazon/music/views/library/models/PageGridViewModelContext;Lcom/amazon/music/views/library/models/ExposedRefinementListModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final /* data */ class PageGridViewModel extends BaseViewContainerModel<BaseViewModel> {
    private final PageGridViewModelContext context;
    private final List<FilterCollectionModel> filters;
    private ExposedRefinementListModel header;
    private List<? extends BaseViewModel> models;
    private final String nestedModelViewId;
    private String nextPageToken;
    private int pageIndex;
    private final String subTitle;
    private final int tileType;
    private final String title;
    private final String viewId;

    public final PageGridViewModel copy(String viewId, String nestedModelViewId, String title, String subTitle, List<? extends BaseViewModel> models, int tileType, int pageIndex, String nextPageToken, List<FilterCollectionModel> filters, PageGridViewModelContext context, ExposedRefinementListModel header) {
        Intrinsics.checkNotNullParameter(models, "models");
        return new PageGridViewModel(viewId, nestedModelViewId, title, subTitle, models, tileType, pageIndex, nextPageToken, filters, context, header);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PageGridViewModel)) {
            return false;
        }
        PageGridViewModel pageGridViewModel = (PageGridViewModel) other;
        return Intrinsics.areEqual(this.viewId, pageGridViewModel.viewId) && Intrinsics.areEqual(this.nestedModelViewId, pageGridViewModel.nestedModelViewId) && Intrinsics.areEqual(this.title, pageGridViewModel.title) && Intrinsics.areEqual(this.subTitle, pageGridViewModel.subTitle) && Intrinsics.areEqual(getModels(), pageGridViewModel.getModels()) && getTileType() == pageGridViewModel.getTileType() && this.pageIndex == pageGridViewModel.pageIndex && Intrinsics.areEqual(this.nextPageToken, pageGridViewModel.nextPageToken) && Intrinsics.areEqual(this.filters, pageGridViewModel.filters) && Intrinsics.areEqual(this.context, pageGridViewModel.context) && Intrinsics.areEqual(this.header, pageGridViewModel.header);
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewModel
    public int hashCode() {
        String str = this.viewId;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        String str2 = this.nestedModelViewId;
        int iHashCode2 = (iHashCode + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.title;
        int iHashCode3 = (iHashCode2 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.subTitle;
        int iHashCode4 = (((((((iHashCode3 + (str4 == null ? 0 : str4.hashCode())) * 31) + getModels().hashCode()) * 31) + Integer.hashCode(getTileType())) * 31) + Integer.hashCode(this.pageIndex)) * 31;
        String str5 = this.nextPageToken;
        int iHashCode5 = (iHashCode4 + (str5 == null ? 0 : str5.hashCode())) * 31;
        List<FilterCollectionModel> list = this.filters;
        int iHashCode6 = (iHashCode5 + (list == null ? 0 : list.hashCode())) * 31;
        PageGridViewModelContext pageGridViewModelContext = this.context;
        int iHashCode7 = (iHashCode6 + (pageGridViewModelContext == null ? 0 : pageGridViewModelContext.hashCode())) * 31;
        ExposedRefinementListModel exposedRefinementListModel = this.header;
        return iHashCode7 + (exposedRefinementListModel != null ? exposedRefinementListModel.hashCode() : 0);
    }

    public String toString() {
        return "PageGridViewModel(viewId=" + ((Object) this.viewId) + ", nestedModelViewId=" + ((Object) this.nestedModelViewId) + ", title=" + ((Object) this.title) + ", subTitle=" + ((Object) this.subTitle) + ", models=" + getModels() + ", tileType=" + getTileType() + ", pageIndex=" + this.pageIndex + ", nextPageToken=" + ((Object) this.nextPageToken) + ", filters=" + this.filters + ", context=" + this.context + ", header=" + this.header + ')';
    }

    public /* synthetic */ PageGridViewModel(String str, String str2, String str3, String str4, List list, int i, int i2, String str5, List list2, PageGridViewModelContext pageGridViewModelContext, ExposedRefinementListModel exposedRefinementListModel, int i3, DefaultConstructorMarker defaultConstructorMarker) {
        this((i3 & 1) != 0 ? null : str, (i3 & 2) != 0 ? null : str2, (i3 & 4) != 0 ? null : str3, (i3 & 8) != 0 ? null : str4, list, i, i2, str5, (i3 & 256) != 0 ? null : list2, (i3 & 512) != 0 ? null : pageGridViewModelContext, (i3 & 1024) != 0 ? null : exposedRefinementListModel);
    }

    public final String getViewId() {
        return this.viewId;
    }

    public final String getNestedModelViewId() {
        return this.nestedModelViewId;
    }

    public final String getTitle() {
        return this.title;
    }

    public final String getSubTitle() {
        return this.subTitle;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContainerModel
    public List<BaseViewModel> getModels() {
        return this.models;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContainerModel
    public void setModels(List<? extends BaseViewModel> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.models = list;
    }

    @Override // com.amazon.music.views.library.models.base.BaseViewContainerModel
    public int getTileType() {
        return this.tileType;
    }

    public final int getPageIndex() {
        return this.pageIndex;
    }

    public final void setPageIndex(int i) {
        this.pageIndex = i;
    }

    public final String getNextPageToken() {
        return this.nextPageToken;
    }

    public final List<FilterCollectionModel> getFilters() {
        return this.filters;
    }

    public final PageGridViewModelContext getContext() {
        return this.context;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PageGridViewModel(String str, String str2, String str3, String str4, List<? extends BaseViewModel> models, int i, int i2, String str5, List<FilterCollectionModel> list, PageGridViewModelContext pageGridViewModelContext, ExposedRefinementListModel exposedRefinementListModel) {
        super(str, models, i, null, 8, null);
        Intrinsics.checkNotNullParameter(models, "models");
        this.viewId = str;
        this.nestedModelViewId = str2;
        this.title = str3;
        this.subTitle = str4;
        this.models = models;
        this.tileType = i;
        this.pageIndex = i2;
        this.nextPageToken = str5;
        this.filters = list;
        this.context = pageGridViewModelContext;
        this.header = exposedRefinementListModel;
    }

    public final ExposedRefinementListModel getHeader() {
        return this.header;
    }

    public final void setHeader(ExposedRefinementListModel exposedRefinementListModel) {
        this.header = exposedRefinementListModel;
    }
}
