package Touch.WidgetsInterface.v1_0;

import CoreInterface.v1_0.Element;
import com.amazon.core.api.CoreResolver;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonTypeIdResolver;
import javax.annotation.Nullable;
import org.immutables.value.Value;

/* JADX INFO: loaded from: classes.dex */
@JsonTypeInfo(include = JsonTypeInfo.As.PROPERTY, property = ContextMappingConstants.INTERFACE, use = JsonTypeInfo.Id.CUSTOM)
@JsonTypeIdResolver(CoreResolver.class)
@JsonDeserialize(as = ImmutableImageResources.class)
@JsonSerialize(as = ImmutableImageResources.class)
@Value.Immutable
public abstract class ImageResources extends Element {

    public enum Image {
        ALBUM_PLACEHOLDER,
        ARTIST_PLACEHOLDER,
        PLAYLIST_PLACEHOLDER,
        STATION_PLACEHOLDER,
        TRACK_PLACEHOLDER,
        VIDEO_PLACEHOLDER,
        MERCH_PLACEHOLDER,
        ARTWORK_STATION_BADGE,
        FAVORITE_ICON,
        FAVORITE_ON_ICON,
        MORE_ICON,
        PLAY_ICON,
        SHARE_ICON,
        SHUFFLE_ICON,
        STATION_ICON,
        SONG_HISTORY_ICON,
        SETTINGS_ICON,
        DEFAULT_ACTIVITY_FEEDS_ICON,
        NEW_ACTIVITY_FEEDS_ICON,
        DOWNLOAD_ICON,
        ADD_ICON
    }

    @Nullable
    public abstract Image activeImage();

    @Nullable
    public abstract Image image();
}
