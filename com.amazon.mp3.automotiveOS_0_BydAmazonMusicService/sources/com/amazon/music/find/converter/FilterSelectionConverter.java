package com.amazon.music.find.converter;

import android.content.Context;
import com.amazon.music.find.R;
import com.amazon.music.find.model.filter.AbstractFilterElement;
import com.amazon.music.find.model.filter.FilterContainer;
import com.amazon.music.find.model.filter.FilterMetadata;
import com.amazon.music.find.model.filter.SearchFilterSelections;
import com.amazon.music.find.providers.SearchFeaturesProvider;
import com.amazon.music.views.library.models.ButtonNavigatorModel;
import com.amazon.music.views.library.models.LinkNavigatorModel;
import com.amazon.music.views.library.models.PageGridViewModel;
import com.amazon.music.views.library.models.SectionGridViewModel;
import com.amazon.music.views.library.models.enums.IconType;
import com.amazon.music.views.library.models.enums.TargetType;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FilterSelectionConverter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0002H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/music/find/converter/FilterSelectionConverter;", "Lcom/amazon/music/find/converter/PageGridViewModelConverter;", "Lcom/amazon/music/find/model/filter/SearchFilterSelections;", "context", "Landroid/content/Context;", "searchFeaturesProvider", "Lcom/amazon/music/find/providers/SearchFeaturesProvider;", "(Landroid/content/Context;Lcom/amazon/music/find/providers/SearchFeaturesProvider;)V", "convert", "Lcom/amazon/music/views/library/models/PageGridViewModel;", "data", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FilterSelectionConverter implements PageGridViewModelConverter<SearchFilterSelections> {
    private final Context context;
    private final SearchFeaturesProvider searchFeaturesProvider;

    public FilterSelectionConverter(Context context, SearchFeaturesProvider searchFeaturesProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(searchFeaturesProvider, "searchFeaturesProvider");
        this.context = context;
        this.searchFeaturesProvider = searchFeaturesProvider;
    }

    @Override // com.amazon.music.find.converter.PageGridViewModelConverter
    public PageGridViewModel convert(SearchFilterSelections data) {
        String string;
        Intrinsics.checkNotNullParameter(data, "data");
        String string2 = this.context.getResources().getString(R.string.apply_filters);
        ButtonStyleKey buttonStyleKey = ButtonStyleKey.SOLID;
        TargetType targetType = TargetType.APPLY_FILTERS;
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.apply_filters)");
        ButtonNavigatorModel buttonNavigatorModel = new ButtonNavigatorModel("", string2, "", null, targetType, true, null, buttonStyleKey, null, null, 0, 1864, null);
        if (this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
            string = this.context.getResources().getString(R.string.clear_filters);
        } else {
            string = this.context.getResources().getString(R.string.reset_filters);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (searchFeaturesProvid…g(R.string.reset_filters)");
        List<AbstractFilterElement> filterElements = data.getFilterMetadata().getFilterElements();
        ArrayList arrayList = new ArrayList();
        for (Object obj : filterElements) {
            if (obj instanceof FilterContainer) {
                arrayList.add(obj);
            }
        }
        ArrayList<FilterContainer> arrayList2 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (!Intrinsics.areEqual(((FilterContainer) obj2).getId(), FilterMetadata.QUALITY_CONTAINER_ID) || this.searchFeaturesProvider.getAreQualityFiltersEnabled()) {
                arrayList2.add(obj2);
            }
        }
        int i = 10;
        ArrayList arrayList3 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList2, 10));
        for (FilterContainer filterContainer : arrayList2) {
            String title = filterContainer.getTitle();
            boolean isEnabled = filterContainer.getIsEnabled();
            TargetType targetType2 = TargetType.RESET_STATE;
            List<AbstractFilterElement> children = filterContainer.getChildren();
            ArrayList arrayList4 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(children, i));
            for (AbstractFilterElement abstractFilterElement : children) {
                arrayList4.add(new LinkNavigatorModel("", abstractFilterElement.getTitle(), abstractFilterElement.getId(), null, abstractFilterElement.getIsEnabled(), TargetType.URI, null, IconType.RADIO, data.containsSelection(abstractFilterElement.getId()), false, null, 1608, null));
            }
            ArrayList arrayList5 = arrayList3;
            arrayList5.add(new SectionGridViewModel("", arrayList4, 8, null, title, null, string, string, targetType2, null, false, null, isEnabled, null, null, null, null, 126504, null));
            arrayList3 = arrayList5;
            i = i;
        }
        return new PageGridViewModel("", null, null, null, CollectionsKt___CollectionsKt.plus((Collection<? extends ButtonNavigatorModel>) arrayList3, buttonNavigatorModel), 2, 0, null, null, null, null, 1550, null);
    }
}
