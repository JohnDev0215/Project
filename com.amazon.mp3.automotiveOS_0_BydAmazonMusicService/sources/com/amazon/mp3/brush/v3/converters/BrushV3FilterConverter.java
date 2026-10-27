package com.amazon.mp3.brush.v3.converters;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.page.api.model.Entity;
import com.amazon.music.page.api.model.Filter;
import com.amazon.music.views.library.converter.BaseModelConverter;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushV3FilterConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00042\u00020\u0001:\u0001\u0004B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3FilterConverter;", "Lcom/amazon/music/views/library/converter/BaseModelConverter;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BrushV3FilterConverter implements BaseModelConverter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BrushV3FilterConverter.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004J(\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u00052\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u001e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u00042\u000e\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004J \u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004H\u0002¨\u0006\u0013"}, d2 = {"Lcom/amazon/mp3/brush/v3/converters/BrushV3FilterConverter$Companion;", "", "()V", "convert", "", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "filters", "Lcom/amazon/music/page/api/model/Filter;", "convertEntityToFilterItemModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", PageUriUtils.FILTER_QUERY_PARAM, "Lcom/amazon/music/page/api/model/Entity;", "parentCollectionModel", "collectionId", "", "convertFilterCollectionList", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "collectionModel", "convertFilterListToFilterCollectionModel", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<FilterCollectionModel> convert(List<? extends Filter> filters) {
            return convertFilterListToFilterCollectionModel(filters);
        }

        private final FilterItemModel convertEntityToFilterItemModel(Entity filter, FilterCollectionModel parentCollectionModel, String collectionId) {
            if ((filter == null ? null : filter.getButton()) == null) {
                return null;
            }
            String id = filter.getButton().getId();
            String title = filter.getButton().getTitle();
            Intrinsics.checkNotNullExpressionValue(title, "filter.button.title");
            Boolean isSelected = filter.getButton().getIsSelected();
            Intrinsics.checkNotNullExpressionValue(isSelected, "filter.button.isSelected");
            return new FilterItemModel(id, title, null, isSelected.booleanValue(), filter.getButton().getBlockRef(), null, false, parentCollectionModel, collectionId, 100, null);
        }

        private final List<FilterCollectionModel> convertFilterListToFilterCollectionModel(List<? extends Filter> filters) {
            if (filters == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            for (Filter filter : filters) {
                String title = filter.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "filterCollection.title");
                FilterCollectionModel filterCollectionModel = new FilterCollectionModel(title, CollectionsKt__CollectionsKt.emptyList(), filter.getBlockRef(), null, false, null, false, 104, null);
                List<Entity> entities = filter.getEntities();
                Intrinsics.checkNotNullExpressionValue(entities, "filterCollection.entities");
                ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(entities, 10));
                Iterator<T> it = entities.iterator();
                while (it.hasNext()) {
                    arrayList2.add(BrushV3FilterConverter.INSTANCE.convertEntityToFilterItemModel((Entity) it.next(), filterCollectionModel, filter.getId()));
                }
                filterCollectionModel.setFilters(arrayList2);
                arrayList.add(filterCollectionModel);
            }
            return arrayList;
        }

        public final List<RefinementPillModel> convertFilterCollectionList(List<FilterCollectionModel> collectionModel) {
            if (collectionModel == null) {
                return null;
            }
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(collectionModel, 10));
            Iterator<T> it = collectionModel.iterator();
            while (it.hasNext()) {
                arrayList.add(new RefinementPillModel(null, (FilterCollectionModel) it.next(), false, null, null, 28, null));
            }
            return arrayList;
        }
    }
}
