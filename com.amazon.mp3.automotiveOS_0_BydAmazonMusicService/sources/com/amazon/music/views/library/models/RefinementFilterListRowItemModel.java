package com.amazon.music.views.library.models;

import com.amazon.music.find.utils.ContentUtils;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: RefinementFilterListRowItemModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\t\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u001b\u0012\b\u0010\u0010\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\b\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0014\u0010\r\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\f¨\u0006\u0013"}, d2 = {"Lcom/amazon/music/views/library/models/RefinementFilterListRowItemModel;", "Lcom/amazon/music/views/library/models/RefinementRowItemModel;", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "content", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "getContent", "()Lcom/amazon/music/views/library/models/FilterCollectionModel;", "", "isSelected", "()Z", "", "getTitle", "()Ljava/lang/String;", "title", "getSubTitle", "subTitle", ContentUtils.KEY_VIEW_REFERENCE_ID, "<init>", "(Ljava/lang/String;Lcom/amazon/music/views/library/models/FilterCollectionModel;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class RefinementFilterListRowItemModel extends RefinementRowItemModel<FilterCollectionModel> {
    private final FilterCollectionModel content;

    @Override // com.amazon.music.views.library.models.RefinementRowItemModel
    public String getSubTitle() {
        return null;
    }

    public FilterCollectionModel getContent() {
        return this.content;
    }

    public RefinementFilterListRowItemModel(String str, FilterCollectionModel filterCollectionModel) {
        super(str);
        this.content = filterCollectionModel;
    }

    @Override // com.amazon.music.views.library.models.RefinementRowItemModel
    public boolean isSelected() {
        List<FilterItemModel> filters;
        FilterCollectionModel content = getContent();
        Object obj = null;
        if (content != null && (filters = content.getFilters()) != null) {
            Iterator<T> it = filters.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Object next = it.next();
                FilterItemModel filterItemModel = (FilterItemModel) next;
                if ((filterItemModel != null && filterItemModel.getSelected()) && !Intrinsics.areEqual(filterItemModel, getContent().getDefaultSelectedFilter())) {
                    obj = next;
                    break;
                }
            }
            obj = (FilterItemModel) obj;
        }
        return obj != null;
    }

    @Override // com.amazon.music.views.library.models.RefinementRowItemModel
    public String getTitle() {
        String title;
        FilterCollectionModel content = getContent();
        return (content == null || (title = content.getTitle()) == null) ? "" : title;
    }
}
