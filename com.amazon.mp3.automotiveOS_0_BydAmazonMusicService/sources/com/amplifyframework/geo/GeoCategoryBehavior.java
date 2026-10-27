package com.amplifyframework.geo;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.geo.models.Coordinates;
import com.amplifyframework.geo.models.MapStyle;
import com.amplifyframework.geo.models.MapStyleDescriptor;
import com.amplifyframework.geo.options.GeoSearchByCoordinatesOptions;
import com.amplifyframework.geo.options.GeoSearchByTextOptions;
import com.amplifyframework.geo.options.GetMapStyleDescriptorOptions;
import com.amplifyframework.geo.result.GeoSearchResult;
import java.util.Collection;

/* JADX INFO: loaded from: classes5.dex */
public interface GeoCategoryBehavior {
    void getAvailableMaps(@NonNull Consumer<Collection<MapStyle>> consumer, @NonNull Consumer<GeoException> consumer2);

    void getDefaultMap(@NonNull Consumer<MapStyle> consumer, @NonNull Consumer<GeoException> consumer2);

    void getMapStyleDescriptor(@NonNull Consumer<MapStyleDescriptor> consumer, @NonNull Consumer<GeoException> consumer2);

    void getMapStyleDescriptor(@NonNull GetMapStyleDescriptorOptions getMapStyleDescriptorOptions, @NonNull Consumer<MapStyleDescriptor> consumer, @NonNull Consumer<GeoException> consumer2);

    void searchByCoordinates(@NonNull Coordinates coordinates, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2);

    void searchByCoordinates(@NonNull Coordinates coordinates, @NonNull GeoSearchByCoordinatesOptions geoSearchByCoordinatesOptions, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2);

    void searchByText(@NonNull String str, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2);

    void searchByText(@NonNull String str, @NonNull GeoSearchByTextOptions geoSearchByTextOptions, @NonNull Consumer<GeoSearchResult> consumer, @NonNull Consumer<GeoException> consumer2);
}
