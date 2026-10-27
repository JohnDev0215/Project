package com.amazon.mp3.auto.carmode;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceManager;
import com.amazon.digitalmusicxp.enums.PlayModeEnum;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.library.item.Album;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.Playlist;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.playlist.CatalogPlaylist;
import com.amazon.mp3.prime.browse.metadata.PrimeAlbum;
import com.amazon.mp3.prime.browse.metadata.PrimePlaylist;
import com.amazon.mp3.search.model.Station;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.EligibilityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.platform.data.eligibility.ContentEligibility;
import com.amazon.music.platform.data.eligibility.PlaymodeEligibility;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.providers.EntityEligibilitiesProvider;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import com.amazon.music.views.library.models.SimpleHorizontalTileModel;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import com.amazon.podcast.media.playback.Media;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModePresetsUtility.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b<\u0010=J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u0006H\u0007J\"\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007J\"\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0007J4\u0010\u001b\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u001a2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\n\u0010\u001c\u001a\u0004\u0018\u00010\u0012H\u0002J\u000e\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u0003\u001a\u00020\u0002J\u0016\u0010 \u001a\u00020\u000f2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020\u001dJ\"\u0010$\u001a\u0004\u0018\u00010\u00142\u0006\u0010!\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\"2\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002J \u0010)\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020%2\b\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010(\u001a\u00020'J\u001e\u0010)\u001a\u00020\u000f2\u0006\u0010&\u001a\u00020%2\u0006\u0010\u000e\u001a\u00020\u00142\u0006\u0010(\u001a\u00020'J\u000e\u0010*\u001a\u00020'2\u0006\u0010\u0007\u001a\u00020\u0006J\"\u0010+\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u001a2\u0006\u0010\u0003\u001a\u00020\u0002J\"\u0010,\u001a\u0016\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u0018j\n\u0012\u0004\u0012\u00020\u0019\u0018\u0001`\u001a2\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010.\u001a\u0004\u0018\u00010-J\u0010\u00101\u001a\u00020\u000f2\b\u00100\u001a\u0004\u0018\u00010/J\u0010\u00102\u001a\u00020\u000f2\b\u00100\u001a\u0004\u0018\u00010/J\u0010\u00105\u001a\u0004\u0018\u0001042\u0006\u00103\u001a\u00020\u001dR\u001c\u00107\u001a\n 6*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b7\u00108R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;¨\u0006>"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModePresetsUtility;", "", "Landroid/content/Context;", "context", "", "isPresetsFull", "Lcom/amazon/mp3/library/item/CatalogContent;", "content", "isContentEligible", "item", "isPreset", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "", "addToPresets", "removeFromPresets", "Lcom/google/gson/Gson;", "gson", "", "jsonString", "Ljava/lang/reflect/Type;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "Ljava/util/ArrayList;", "Lcom/amazon/music/views/library/models/SimpleHorizontalTileModel;", "Lkotlin/collections/ArrayList;", "deserializeModels", "getContentMetadataGson", "", "getDefaultPresetCount", "presetCount", "setDefaultPresetCount", "contentType", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "convertContentTypeToSubtitle", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "sendMetrics", "getEntityType", "getUserPresets", "getUserDeletedPresets", "Lcom/amazon/podcast/external/car/PodcastMediaMetadata;", "getPodcastMediaMetadata", "Lcom/amazon/podcast/media/playback/Media$PodcastMediaMetadataCallback;", "callBack", "addPodcastMediaMetadataCallback", "removePodcastMediaMetadataCallback", "state", "Lcom/amazon/mp3/playback/PlayerViewModel$PlayerState;", "convertPlaybackState", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/google/gson/ExclusionStrategy;", "strategy", "Lcom/google/gson/ExclusionStrategy;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModePresetsUtility {
    public static final CarModePresetsUtility INSTANCE = new CarModePresetsUtility();
    private static final String TAG = CarModePresetsUtility.class.getSimpleName();
    private static final ExclusionStrategy strategy = new ExclusionStrategy() { // from class: com.amazon.mp3.auto.carmode.CarModePresetsUtility$strategy$1
        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipField(FieldAttributes field) {
            Intrinsics.checkNotNullParameter(field, "field");
            return false;
        }

        @Override // com.google.gson.ExclusionStrategy
        public boolean shouldSkipClass(Class<?> clazz) {
            if (Intrinsics.areEqual(clazz, EntityItemIdentifier.class) ? true : Intrinsics.areEqual(clazz, PlaymodeEligibility.class)) {
                return true;
            }
            return Intrinsics.areEqual(clazz, ContentEligibility.class);
        }
    };

    private CarModePresetsUtility() {
    }

    public final int getDefaultPresetCount(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return PreferenceManager.getDefaultSharedPreferences(context).getInt("USER_DEFAULT_PRESET_COUNT", 15);
    }

    public final void setDefaultPresetCount(Context context, int presetCount) {
        Intrinsics.checkNotNullParameter(context, "context");
        PreferenceManager.getDefaultSharedPreferences(context).edit().putInt("USER_DEFAULT_PRESET_COUNT", presetCount).apply();
    }

    public final String convertContentTypeToSubtitle(int contentType, ContentMetadata contentMetadata, Context context) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentType == 0) {
            if (context == null) {
                return null;
            }
            return context.getString(R.string.dmusic_library_item_name_album);
        }
        if (contentType == 1) {
            if (contentMetadata instanceof PlaylistMetadata) {
                if (context == null) {
                    return null;
                }
                return context.getString(R.string.dmusic_library_item_name_playlist);
            }
            if (!(contentMetadata instanceof UserPlaylistMetadata) || context == null) {
                return null;
            }
            return context.getString(R.string.car_mode_playlist_from_your_library_subtitle);
        }
        if (contentType == 2) {
            if (context == null) {
                return null;
            }
            return context.getString(R.string.dmusic_library_item_name_track);
        }
        if (contentType == 3) {
            if (context == null) {
                return null;
            }
            return context.getString(R.string.dmusic_library_item_name_station);
        }
        if (contentType == 4) {
            if (context == null) {
                return null;
            }
            return context.getString(R.string.dmusic_library_item_name_artist);
        }
        if ((contentType == 11 || contentType == 12) && context != null) {
            return context.getString(R.string.dmusic_prime_search_top_hit_podcast_show_subtitle);
        }
        return null;
    }

    @JvmStatic
    public static final boolean isPresetsFull(Context context) {
        int defaultPresetCount;
        Intrinsics.checkNotNullParameter(context, "context");
        if (AmazonApplication.getCapabilities().isCarModeV2Enabled()) {
            defaultPresetCount = CarModeConfigs.INSTANCE.getCarModeAddToPresetsLimit();
        } else {
            defaultPresetCount = 18 - INSTANCE.getDefaultPresetCount(context);
            List<PresetIdentifier> deletedPresetIdentifiers = PresetIdentifierHelper.INSTANCE.getDeletedPresetIdentifiers(context);
            if (deletedPresetIdentifiers != null) {
                defaultPresetCount += deletedPresetIdentifiers.size();
            }
        }
        List<PresetIdentifier> presetIdentifiers = PresetIdentifierHelper.INSTANCE.getPresetIdentifiers(context);
        if (presetIdentifiers != null) {
            defaultPresetCount -= presetIdentifiers.size();
        }
        return defaultPresetCount <= 0;
    }

    @JvmStatic
    public static final boolean isContentEligible(CatalogContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        int i = 1;
        if (!Feature.sonic_rush.isEnabled() || !Feature.supports_eligibility.isEnabled() || !(content instanceof EntityEligibilitiesProvider)) {
            if (ContentAccessUtil.getCatalogContentUnavailableReason(content, ContentAccessUtil.ContentAccessOperation.STREAM) != null) {
                return false;
            }
        } else {
            PlayModeEnum playMode = new EligibilityUtil(null, i, 0 == true ? 1 : 0).getPlayMode((EntityEligibilitiesProvider) content);
            if (playMode != PlayModeEnum.ON_DEMAND && playMode != PlayModeEnum.AUGMENTED) {
                return false;
            }
        }
        return true;
    }

    @JvmStatic
    public static final boolean isPreset(Context context, CatalogContent item) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        PresetIdentifier presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier(item);
        if (presetIdentifierAsPresetIdentifier == null) {
            return true;
        }
        return PresetIdentifierHelper.check(context, presetIdentifierAsPresetIdentifier.getIdentifier());
    }

    @JvmStatic
    public static final void addToPresets(Activity activity, CatalogContent item, PageType pageType) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(item, "item");
        if (activity.isFinishing() || activity.isDestroyed()) {
            return;
        }
        Context context = activity.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (isPresetsFull(context)) {
            new BauhausDialogBuilder(activity).setTitle(R.string.alert_title_presets_full).setNeutralButton(R.string.dmusic_button_ok, null).setMessage(R.string.alert_presets_full).create().show();
            return;
        }
        PresetIdentifier presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier(item);
        if (presetIdentifierAsPresetIdentifier == null) {
            return;
        }
        CarModePresetsUtility carModePresetsUtility = INSTANCE;
        carModePresetsUtility.sendMetrics(ActionType.CAR_MODE_OVERFLOW_ADD_TO_PRESETS, pageType, carModePresetsUtility.getEntityType(item));
        PresetIdentifierHelper.add(context, presetIdentifierAsPresetIdentifier);
        BauhausToastUtils.showTextToast(activity, R.string.dmusic_added_to_presets, 0);
    }

    @JvmStatic
    public static final void removeFromPresets(Context context, CatalogContent item, PageType pageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(item, "item");
        PresetIdentifier presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier(item);
        if (presetIdentifierAsPresetIdentifier == null) {
            return;
        }
        CarModePresetsUtility carModePresetsUtility = INSTANCE;
        carModePresetsUtility.sendMetrics(ActionType.CAR_MODE_OVERFLOW_REMOVE_FROM_PRESETS, pageType, carModePresetsUtility.getEntityType(item));
        PresetIdentifierHelper.delete$default(context, presetIdentifierAsPresetIdentifier, false, 4, null);
        BauhausToastUtils.showTextToast(context, R.string.dmusic_removed_from_presets, 0);
    }

    public final void sendMetrics(ActionType actionType, PageType pageType, EntityType entityType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withPageType(pageType).withEntityType(entityType).withInteractionType(InteractionType.TAP).build());
    }

    public final void sendMetrics(ActionType actionType, String pageType, EntityType entityType) {
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(entityType, "entityType");
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withCustomPageType(pageType).withEntityType(entityType).withInteractionType(InteractionType.TAP).build());
    }

    public final EntityType getEntityType(CatalogContent content) {
        Intrinsics.checkNotNullParameter(content, "content");
        if (content instanceof Album ? true : content instanceof PrimeAlbum) {
            return EntityType.ALBUM;
        }
        return content instanceof CatalogPlaylist ? true : content instanceof PrimePlaylist ? EntityType.PLAYLIST : content instanceof Playlist ? EntityType.USER_PLAYLIST : content instanceof Station ? EntityType.STATION : EntityType.UNKNOWN;
    }

    public final ArrayList<SimpleHorizontalTileModel> getUserPresets(Context context) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Type type = new TypeToken<List<? extends SimpleHorizontalTileModel>>() { // from class: com.amazon.mp3.auto.carmode.CarModePresetsUtility$getUserPresets$type$1
        }.getType();
        Gson contentMetadataGson = getContentMetadataGson();
        if (contentMetadataGson != null && (string = PreferenceManager.getDefaultSharedPreferences(context).getString("USER_ADD_PRESETS_PREFERENCE_KEY", null)) != null) {
            Log.debug(TAG, "Retrieved Json string for user added presets %s", string);
            CarModePresetsUtility carModePresetsUtility = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(type, "type");
            return carModePresetsUtility.deserializeModels(contentMetadataGson, string, type);
        }
        return new ArrayList<>();
    }

    public final ArrayList<SimpleHorizontalTileModel> getUserDeletedPresets(Context context) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        Type type = new TypeToken<List<? extends SimpleHorizontalTileModel>>() { // from class: com.amazon.mp3.auto.carmode.CarModePresetsUtility$getUserDeletedPresets$type$1
        }.getType();
        Gson contentMetadataGson = getContentMetadataGson();
        if (contentMetadataGson != null && (string = PreferenceManager.getDefaultSharedPreferences(context).getString("USER_DELETE_PRESETS_PREFERENCE_KEY", null)) != null) {
            Log.debug(TAG, "Retrieved Json string for user deleted presets %s", string);
            CarModePresetsUtility carModePresetsUtility = INSTANCE;
            Intrinsics.checkNotNullExpressionValue(type, "type");
            return carModePresetsUtility.deserializeModels(contentMetadataGson, string, type);
        }
        return new ArrayList<>();
    }

    private final ArrayList<SimpleHorizontalTileModel> deserializeModels(Gson gson, String jsonString, Type type) {
        try {
            return (ArrayList) gson.fromJson(jsonString, type);
        } catch (Exception e) {
            Log.error(TAG, "Unable to deserialize models", e);
            return null;
        }
    }

    private final Gson getContentMetadataGson() {
        RuntimeTypeAdapterFactory runtimeTypeAdapterFactoryRegisterSubtype = RuntimeTypeAdapterFactory.of(ContentMetadata.class, "contentMetadataType").registerSubtype(PlaylistMetadata.class).registerSubtype(AlbumMetadata.class).registerSubtype(StationMetadata.class).registerSubtype(UserPlaylistMetadata.class).registerSubtype(TrackMetadata.class).registerSubtype(ArtistMetadata.class);
        Intrinsics.checkNotNullExpressionValue(runtimeTypeAdapterFactoryRegisterSubtype, "of(ContentMetadata::clas…tistMetadata::class.java)");
        return new GsonBuilder().registerTypeAdapterFactory(runtimeTypeAdapterFactoryRegisterSubtype).registerTypeAdapter(Uri.class, new UriGsonSerializerDeserializer()).setExclusionStrategies(strategy).create();
    }

    public final PodcastMediaMetadata getPodcastMediaMetadata() {
        Media media;
        if (!Podcast.getPlayback().isPodcastLastPlayed() || (media = Podcast.getPlayback().getMedia()) == null) {
            return null;
        }
        return media.getPodcastMediaMetadata();
    }

    public final void addPodcastMediaMetadataCallback(Media.PodcastMediaMetadataCallback callBack) {
        Media media = Podcast.getPlayback().getMedia();
        if (callBack == null || media == null) {
            return;
        }
        media.addPodcastMediaMetadataCallback(callBack);
    }

    public final void removePodcastMediaMetadataCallback(Media.PodcastMediaMetadataCallback callBack) {
        Media media = Podcast.getPlayback().getMedia();
        if (callBack == null || media == null) {
            return;
        }
        media.removePodcastMediaMetadataCallback(callBack);
    }

    public final PlayerViewModel.PlayerState convertPlaybackState(int state) {
        if (state == 2) {
            return PlayerViewModel.PlayerState.PAUSED;
        }
        if (state == 3) {
            return PlayerViewModel.PlayerState.PLAYING;
        }
        if (state == 6) {
            return PlayerViewModel.PlayerState.LOADING;
        }
        return PlayerViewModel.PlayerState.PLAYING;
    }
}
