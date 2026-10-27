package com.amazon.mp3.util.extensions;

import com.amazon.music.views.library.models.ExposedRefinementListModel;
import com.amazon.music.views.library.models.FilterCollectionModel;
import com.amazon.music.views.library.models.FilterItemModel;
import com.amazon.music.views.library.models.FilterModel;
import com.amazon.music.views.library.models.RefinementPillModel;
import com.amazon.music.views.library.recyclerview.adapters.UniversalAdapter;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Refinement.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\u0018\u0010\b\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0002\u001a\n\u0010\n\u001a\u00020\t*\u00020\u0000\u001a\f\u0010\f\u001a\u0004\u0018\u00010\u0000*\u00020\u000b¨\u0006\r"}, d2 = {"Lcom/amazon/music/views/library/models/ExposedRefinementListModel;", "", "resetRefinementFilterModels", "Lcom/amazon/music/views/library/models/RefinementPillModel;", "refinementPillModel", "resetCollectionRefinement", "Lcom/amazon/music/views/library/models/FilterItemModel;", "filterItemModel", "resetFilter", "", "areRefinementsApplied", "Lcom/amazon/music/views/library/recyclerview/adapters/UniversalAdapter;", "getRefinementsModel", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0})
public final class RefinementKt {
    public static final void resetRefinementFilterModels(ExposedRefinementListModel exposedRefinementListModel) {
        FilterItemModel itemModel;
        Intrinsics.checkNotNullParameter(exposedRefinementListModel, "<this>");
        List<RefinementPillModel> refinementList = exposedRefinementListModel.getRefinementList();
        if (refinementList == null) {
            return;
        }
        for (RefinementPillModel refinementPillModel : refinementList) {
            FilterModel content = refinementPillModel.getContent();
            boolean z = false;
            if (content != null && content.isCollection()) {
                z = true;
            }
            if (z) {
                resetCollectionRefinement(refinementPillModel);
            } else {
                FilterModel content2 = refinementPillModel.getContent();
                if (content2 != null && (itemModel = content2.getItemModel()) != null) {
                    resetFilter(itemModel, refinementPillModel);
                }
            }
        }
    }

    private static final void resetCollectionRefinement(RefinementPillModel refinementPillModel) {
        FilterCollectionModel collectionModel;
        List<FilterItemModel> listFilterNotNull;
        FilterModel content = refinementPillModel.getContent();
        if (content == null || (collectionModel = content.getCollectionModel()) == null) {
            return;
        }
        List<FilterItemModel> filters = collectionModel.getFilters();
        if (filters != null && (listFilterNotNull = CollectionsKt___CollectionsKt.filterNotNull(filters)) != null) {
            for (FilterItemModel filterItemModel : listFilterNotNull) {
                filterItemModel.setSelected(filterItemModel.getIsDefaultFilter());
            }
        }
        collectionModel.setSelectedSortingFilterModel(collectionModel.getDefaultSelectedFilter());
        refinementPillModel.updateFilterModel(collectionModel);
    }

    private static final void resetFilter(FilterItemModel filterItemModel, RefinementPillModel refinementPillModel) {
        if (filterItemModel.getSelected()) {
            filterItemModel.setSelected(false);
            refinementPillModel.updateFilterModel(filterItemModel);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x0079  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final boolean areRefinementsApplied(com.amazon.music.views.library.models.ExposedRefinementListModel r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.util.List r9 = r9.getFilterModels()
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Iterator r9 = r9.iterator()
        L12:
            boolean r1 = r9.hasNext()
            r2 = 1
            if (r1 == 0) goto L87
            java.lang.Object r1 = r9.next()
            r3 = r1
            com.amazon.music.views.library.models.FilterModel r3 = (com.amazon.music.views.library.models.FilterModel) r3
            boolean r4 = r3.isCollection()
            r5 = 0
            if (r4 == 0) goto L73
            com.amazon.music.views.library.models.FilterCollectionModel r3 = r3.getCollectionModel()
            r4 = 0
            if (r3 != 0) goto L2f
            goto L68
        L2f:
            java.util.List r3 = r3.getFilters()
            if (r3 != 0) goto L36
            goto L68
        L36:
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.util.Iterator r3 = r3.iterator()
        L3f:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L68
            java.lang.Object r6 = r3.next()
            r7 = r6
            com.amazon.music.views.library.models.FilterItemModel r7 = (com.amazon.music.views.library.models.FilterItemModel) r7
            if (r7 != 0) goto L50
        L4e:
            r8 = r5
            goto L57
        L50:
            boolean r8 = r7.getSelected()
            if (r8 != r2) goto L4e
            r8 = r2
        L57:
            if (r8 == 0) goto L61
            boolean r7 = r7.getIsDefaultFilter()
            if (r7 != 0) goto L61
            r7 = r2
            goto L62
        L61:
            r7 = r5
        L62:
            if (r7 == 0) goto L3f
            r4.add(r6)
            goto L3f
        L68:
            if (r4 != 0) goto L6b
            goto L79
        L6b:
            boolean r3 = r4.isEmpty()
            r3 = r3 ^ r2
            if (r3 != r2) goto L79
            goto L81
        L73:
            com.amazon.music.views.library.models.FilterItemModel r3 = r3.getItemModel()
            if (r3 != 0) goto L7b
        L79:
            r2 = r5
            goto L81
        L7b:
            boolean r3 = r3.getSelected()
            if (r3 != r2) goto L79
        L81:
            if (r2 == 0) goto L12
            r0.add(r1)
            goto L12
        L87:
            boolean r9 = r0.isEmpty()
            r9 = r9 ^ r2
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.util.extensions.RefinementKt.areRefinementsApplied(com.amazon.music.views.library.models.ExposedRefinementListModel):boolean");
    }

    public static final ExposedRefinementListModel getRefinementsModel(UniversalAdapter universalAdapter) {
        Object next;
        Intrinsics.checkNotNullParameter(universalAdapter, "<this>");
        Iterator<T> it = universalAdapter.getItems().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (next instanceof ExposedRefinementListModel) {
                break;
            }
        }
        if (next instanceof ExposedRefinementListModel) {
            return (ExposedRefinementListModel) next;
        }
        return null;
    }
}
