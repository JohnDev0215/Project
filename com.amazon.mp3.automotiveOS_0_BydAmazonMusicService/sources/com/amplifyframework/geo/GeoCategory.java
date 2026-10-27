package com.amplifyframework.geo;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;
import com.amplifyframework.geo.models.Coordinates;
import com.amplifyframework.geo.models.MapStyle;
import com.amplifyframework.geo.models.MapStyleDescriptor;
import com.amplifyframework.geo.options.GeoSearchByCoordinatesOptions;
import com.amplifyframework.geo.options.GeoSearchByTextOptions;
import com.amplifyframework.geo.options.GetMapStyleDescriptorOptions;
import com.amplifyframework.geo.result.GeoSearchResult;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public final class GeoCategory extends Category<GeoCategoryPlugin<?>> implements GeoCategoryBehavior {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.GEO;
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void getAvailableMaps(@NonNull Consumer<Collection<MapStyle>> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().getAvailableMaps(consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void getDefaultMap(@NonNull Consumer<MapStyle> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().getDefaultMap(consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void getMapStyleDescriptor(@NonNull Consumer<MapStyleDescriptor> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().getMapStyleDescriptor(consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void getMapStyleDescriptor(@NonNull GetMapStyleDescriptorOptions getMapStyleDescriptorOptions, @NonNull Consumer<MapStyleDescriptor> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().getMapStyleDescriptor(getMapStyleDescriptorOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void searchByText(@NonNull String str, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().searchByText(str, consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void searchByText(@NonNull String str, @NonNull GeoSearchByTextOptions geoSearchByTextOptions, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().searchByText(str, geoSearchByTextOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void searchByCoordinates(@NonNull Coordinates coordinates, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().searchByCoordinates(coordinates, consumer, consumer2);
    }

    @Override // com.amplifyframework.geo.GeoCategoryBehavior
    public void searchByCoordinates(@NonNull Coordinates coordinates, @NonNull GeoSearchByCoordinatesOptions geoSearchByCoordinatesOptions, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2) {
        getSelectedPlugin().searchByCoordinates(coordinates, geoSearchByCoordinatesOptions, consumer, consumer2);
    }
}
