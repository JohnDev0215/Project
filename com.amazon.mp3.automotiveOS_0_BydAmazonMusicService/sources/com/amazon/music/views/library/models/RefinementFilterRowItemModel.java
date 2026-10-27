package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import com.amazon.music.views.library.models.base.StateModel;
import kotlin.Metadata;

/* JADX INFO: compiled from: RefinementFilterRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00012\u00020\u0003B\u0019\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002¢\u0006\u0002\u0010\u0007R\u0016\u0010\u0006\u001a\u0004\u0018\u00010\u0002X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR&\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\u000b8V@VX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u000b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u0014\u001a\u00020\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0016\u0010\u0016\u001a\u0004\u0018\u00010\u00178VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"Lcom/amazon/music/views/library/models/RefinementFilterRowItemModel;", "Lcom/amazon/music/views/library/models/RefinementRowItemModel;", "Lcom/amazon/music/views/library/models/FilterItemModel;", "Lcom/amazon/music/views/library/models/base/StateModel;", ContentUtils.KEY_VIEW_REFERENCE_ID, "", "content", "(Ljava/lang/String;Lcom/amazon/music/views/library/models/FilterItemModel;)V", "getContent", "()Lcom/amazon/music/views/library/models/FilterItemModel;", "value", "", "isActive", "()Z", "setActive", "(Z)V", "isSelected", "subTitle", "getSubTitle", "()Ljava/lang/String;", "title", "getTitle", "viewId", "", "getViewId", "()Ljava/lang/Integer;", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class RefinementFilterRowItemModel extends RefinementRowItemModel<FilterItemModel> implements StateModel {
    private final FilterItemModel content;
    private boolean isActive;

    public FilterItemModel getContent() {
        return this.content;
    }

    public RefinementFilterRowItemModel(String str, FilterItemModel filterItemModel) {
        super(str);
        this.content = filterItemModel;
        FilterItemModel content = getContent();
        boolean z = false;
        if (content != null && content.getSelected()) {
            z = true;
        }
        this.isActive = z;
    }

    @Override // com.amazon.music.views.library.models.RefinementRowItemModel
    public boolean isSelected() {
        return isActive();
    }

    @Override // com.amazon.music.views.library.models.RefinementRowItemModel
    public String getTitle() {
        String title;
        FilterItemModel content = getContent();
        return (content == null || (title = content.getTitle()) == null) ? "" : title;
    }

    @Override // com.amazon.music.views.library.models.RefinementRowItemModel
    public String getSubTitle() {
        FilterItemModel content = getContent();
        if (content == null) {
            return null;
        }
        return content.getSubTitle();
    }

    public boolean isActive() {
        FilterItemModel content = getContent();
        return content != null && content.getSelected();
    }

    @Override // com.amazon.music.views.library.models.base.StateModel
    public void setActive(boolean z) {
        this.isActive = z;
        FilterItemModel content = getContent();
        if (content == null) {
            return;
        }
        content.setSelected(z);
    }

    public Integer getViewId() {
        FilterItemModel content = getContent();
        if (content == null) {
            return null;
        }
        return content.getViewId();
    }
}
