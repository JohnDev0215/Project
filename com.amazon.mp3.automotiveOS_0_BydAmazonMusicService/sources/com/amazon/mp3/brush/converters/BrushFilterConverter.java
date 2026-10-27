package com.amazon.mp3.brush.converters;

import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.musicensembleservice.brush.Entity;
import com.amazon.musicensembleservice.brush.Filter;
import com.amazon.musicensembleservice.brush.Genre;
import com.amazon.musicensembleservice.brush.MoodsAndActivities;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BrushFilterConverter.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushFilterConverter;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrushFilterConverter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: BrushFilterConverter.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004J\u001e\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\u0005H\u0002J \u0010\r\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u000e\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0004H\u0002¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/brush/converters/BrushFilterConverter$Companion;", "", "()V", "convert", "", "Lcom/amazon/music/views/library/models/FilterCollectionModel;", "filters", "Lcom/amazon/musicensembleservice/brush/Filter;", "convertEntityToFilterItemModel", "Lcom/amazon/music/views/library/models/FilterItemModel;", PageUriUtils.FILTER_QUERY_PARAM, "Lcom/amazon/musicensembleservice/brush/Entity;", "parentCollectionModel", "convertFilterListToFilterCollectionModel", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final List<FilterCollectionModel> convert(List<? extends Filter> filters) {
            return convertFilterListToFilterCollectionModel(filters);
        }

        private final FilterItemModel convertEntityToFilterItemModel(Entity filter, FilterCollectionModel parentCollectionModel) {
            if (filter instanceof Genre) {
                Genre genre = (Genre) filter;
                String browseId = genre.getBrowseId();
                String title = genre.getTitle();
                Intrinsics.checkNotNullExpressionValue(title, "filter.title");
                Boolean boolIsSelected = genre.isSelected();
                Intrinsics.checkNotNullExpressionValue(boolIsSelected, "filter.isSelected");
                return new FilterItemModel(browseId, title, null, boolIsSelected.booleanValue(), genre.getBlockRef(), null, false, parentCollectionModel, null, 356, null);
            }
            if (!(filter instanceof MoodsAndActivities)) {
                return null;
            }
            MoodsAndActivities moodsAndActivities = (MoodsAndActivities) filter;
            String browseId2 = moodsAndActivities.getBrowseId();
            String title2 = moodsAndActivities.getTitle();
            Intrinsics.checkNotNullExpressionValue(title2, "filter.title");
            Boolean boolIsSelected2 = moodsAndActivities.isSelected();
            Intrinsics.checkNotNullExpressionValue(boolIsSelected2, "filter.isSelected");
            return new FilterItemModel(browseId2, title2, null, boolIsSelected2.booleanValue(), moodsAndActivities.getBlockRef(), null, false, parentCollectionModel, null, 356, null);
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
                    arrayList2.add(BrushFilterConverter.INSTANCE.convertEntityToFilterItemModel((Entity) it.next(), filterCollectionModel));
                }
                filterCollectionModel.setFilters(arrayList2);
                arrayList.add(filterCollectionModel);
            }
            return arrayList;
        }
    }
}
