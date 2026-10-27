package com.amazon.mp3.util.extensions;

import com.amazon.mp3.baseviews.model.configuration.AlbumModelConfiguration;
import com.amazon.mp3.baseviews.model.configuration.ModelConfiguration;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import kotlin.Metadata;

/* JADX INFO: compiled from: ModelConfiguration.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u0002¨\u0006\u0003"}, d2 = {"getContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "Lcom/amazon/mp3/baseviews/model/configuration/ModelConfiguration;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class ModelConfigurationKt {
    public static final ContentMetadata getContentMetadata(ModelConfiguration modelConfiguration) {
        if (!(modelConfiguration instanceof AlbumModelConfiguration)) {
            return null;
        }
        AlbumModelConfiguration albumModelConfiguration = (AlbumModelConfiguration) modelConfiguration;
        if (!(albumModelConfiguration.getEntityItem() instanceof AlbumMetadata)) {
            return null;
        }
        EntityEligibilitiesProvider entityItem = albumModelConfiguration.getEntityItem();
        if (entityItem != null) {
            return (ContentMetadata) entityItem;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.ContentMetadata");
    }
}
