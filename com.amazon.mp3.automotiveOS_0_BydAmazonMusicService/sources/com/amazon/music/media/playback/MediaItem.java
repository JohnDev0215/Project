package com.amazon.music.media.playback;

import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.WorkerThread;
import com.amazon.music.contentEncoding.ContentEncoding;
import com.amazon.music.media.playback.MediaCollectionId;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.media.playback.MediaLink;
import com.amazon.music.media.playback.access.MediaAccessInfo;
import com.amazon.music.media.playback.config.BitRateSelection;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.StrictMode;
import com.amazon.music.media.playback.util.StringUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.definition.playback.AudioVideoToggleMetricsInfo;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.platform.data.entity.EntityItem;
import com.amazon.music.platform.data.entity.EntityItemIdentifier;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.identifiers.Asin;
import com.amazon.music.platform.data.identifiers.LocalId;
import com.amazon.music.platform.data.identifiers.ObjectId;
import com.amazon.music.platform.data.identifiers.Url;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public abstract class MediaItem implements EntityItem {
    private final MediaAccessInfo accessInfo;
    private String albumName;
    private String artistName;
    private PlaybackAttributes bestAvailableAttributes;
    private Map<ImageInfo, Uri> cachedImageUris;
    private volatile boolean canFetchLinks;
    private boolean canSeekOverride;
    private final List<MediaCollectionInfo> collections;
    private List<ContentEncoding> contentEncodings;
    private boolean customerSelectedEntity;
    private boolean directedPlay;
    private long durationMillis;
    private volatile boolean fetchingLinks;
    private final ImageUriProvider imageUriProvider;
    private Map<ImageInfo, Object> imagesBeingFetched;
    private final Object imagesLock;
    private boolean isVoiceInitiated;
    private volatile List<MediaLink> links;
    private final ListenerSet<MediaItemListener> listeners;
    private final Object lock = new Object();
    private boolean mCanLike;
    protected CuePoints mCuePoints;
    private EntityItemIdentifier mEntityItemIdentifier;
    protected LikeState mLikeState;
    private MediaItemLikesProvider mMediaItemLikesProvider;
    private MediaItemId mOverriddenPrimaryId;
    private final Map<MediaItemId.Type, MediaItemId> mediaItemIds;
    private final MediaLinkProvider mediaLinkProvider;
    private final Map<String, String> metricsContext;
    private final String name;
    private final PlaybackPageType playbackPageType;
    private final List<MediaCollectionInfo> readOnlyCollections;
    private String sessionId;
    private MediaCollectionInfo sourceCollection;
    private final Type type;
    public static final ImageUriProvider DEFAULT_IMAGE_URI_PROVIDER = new SimpleImageUriProvider();
    public static final ImageType[] ANY_IMAGE_TYPE = {ImageType.MEDIA_ITEM, ImageType.ALBUM, ImageType.ARTIST};

    public enum ImageType {
        ARTIST,
        ALBUM,
        MEDIA_ITEM
    }

    public interface ImageUriProvider {
        @WorkerThread
        Uri createImageUri(@NonNull ImageInfo imageInfo);

        Uri getImageUri(@NonNull ImageInfo imageInfo);
    }

    public interface MediaItemLikesProvider {
        void fetchAndInitializeLikeState(MediaItem mediaItem, List<String> list);

        void setLikeState(String str, LikeState likeState);
    }

    public boolean canRate() {
        return false;
    }

    protected Uri getAlbumImageUri(int i, int i2) {
        return null;
    }

    protected Uri getArtistImageUri(int i, int i2) {
        return null;
    }

    protected Uri getMediaItemImageUri(int i, int i2) {
        return null;
    }

    public int getRating() {
        return 0;
    }

    public AudioVideoToggleMetricsInfo getToggleMetricsInfo() {
        return null;
    }

    public boolean isInterlude() {
        return false;
    }

    public void setRating(int i, long j) {
    }

    public static String getMediaItemIdString(@Nullable MediaItem mediaItem, @NonNull MediaItemId.Type type, @Nullable String str) {
        MediaItemId mediaItemId;
        if (mediaItem == null || (mediaItemId = mediaItem.getMediaItemId(type)) == null) {
            return str;
        }
        String id = mediaItemId.getId();
        return StringUtils.isEmpty(id) ? str : id;
    }

    protected MediaItem(MediaItemId mediaItemId, String str, long j, @NonNull Type type, @NonNull MediaAccessInfo mediaAccessInfo, List<MediaLink> list, MediaLinkProvider mediaLinkProvider, ImageUriProvider imageUriProvider, @NonNull PlaybackPageType playbackPageType, boolean z, MediaCollectionInfo mediaCollectionInfo, MediaCollectionInfo... mediaCollectionInfoArr) {
        EnumMap enumMap = new EnumMap(MediaItemId.Type.class);
        this.mediaItemIds = enumMap;
        this.artistName = "";
        this.albumName = "";
        this.canFetchLinks = true;
        this.imagesLock = new Object();
        this.sessionId = "";
        this.listeners = new ListenerSet<>(MediaItemListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_ALL_OTHERS);
        this.metricsContext = new HashMap();
        this.bestAvailableAttributes = PlaybackAttributes.UNKNOWN;
        this.canSeekOverride = true;
        this.mLikeState = LikeState.NEUTRAL;
        this.mCanLike = false;
        this.mEntityItemIdentifier = null;
        if (mediaItemId != null) {
            enumMap.put(mediaItemId.getType(), mediaItemId);
        }
        this.name = str != null ? str : "";
        this.durationMillis = j;
        this.type = (Type) Validate.notNull(type);
        this.accessInfo = (MediaAccessInfo) Validate.notNull(mediaAccessInfo);
        this.links = list != null ? list : Collections.emptyList();
        this.mediaLinkProvider = mediaLinkProvider;
        this.imageUriProvider = imageUriProvider != null ? imageUriProvider : DEFAULT_IMAGE_URI_PROVIDER;
        this.playbackPageType = (PlaybackPageType) Validate.notNull(playbackPageType);
        this.directedPlay = z;
        this.sourceCollection = mediaCollectionInfo;
        this.collections = new ArrayList(mediaCollectionInfoArr.length + 1);
        if (mediaCollectionInfo != null) {
            addMediaCollectionInfo(mediaCollectionInfo);
        }
        for (MediaCollectionInfo mediaCollectionInfo2 : mediaCollectionInfoArr) {
            addMediaCollectionInfo(mediaCollectionInfo2);
        }
        this.readOnlyCollections = Collections.unmodifiableList(this.collections);
        Map<MediaItemId.Type, MediaItemId> map = this.mediaItemIds;
        MediaItemId.Type type2 = MediaItemId.Type.ASIN;
        if (map.get(type2) != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new Asin(this.mediaItemIds.get(type2).toString()), SingleType.Track.INSTANCE);
            return;
        }
        Map<MediaItemId.Type, MediaItemId> map2 = this.mediaItemIds;
        MediaItemId.Type type3 = MediaItemId.Type.LOCAL_URI;
        if (map2.get(type3) != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new LocalId(this.mediaItemIds.get(type3).toString()), SingleType.Track.INSTANCE);
            return;
        }
        Map<MediaItemId.Type, MediaItemId> map3 = this.mediaItemIds;
        MediaItemId.Type type4 = MediaItemId.Type.LUID;
        if (map3.get(type4) != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new ObjectId(this.mediaItemIds.get(type4).toString()), SingleType.Track.INSTANCE);
            return;
        }
        Map<MediaItemId.Type, MediaItemId> map4 = this.mediaItemIds;
        MediaItemId.Type type5 = MediaItemId.Type.INTERLUDE;
        if (map4.get(type5) != null) {
            Map<MediaItemId.Type, MediaItemId> map5 = this.mediaItemIds;
            MediaItemId.Type type6 = MediaItemId.Type.URL;
            if (map5.get(type6) != null) {
                this.mEntityItemIdentifier = new EntityItemIdentifier(new Url(this.mediaItemIds.get(type6).toString()), SingleType.Interlude.INSTANCE);
                return;
            } else {
                this.mEntityItemIdentifier = new EntityItemIdentifier(new Url(this.mediaItemIds.get(type5).toString()), SingleType.Interlude.INSTANCE);
                return;
            }
        }
        Map<MediaItemId.Type, MediaItemId> map6 = this.mediaItemIds;
        MediaItemId.Type type7 = MediaItemId.Type.URL;
        if (map6.get(type7) != null) {
            if (type == Type.AD_CONTENT) {
                this.mEntityItemIdentifier = new EntityItemIdentifier(new Url(this.mediaItemIds.get(type7).toString()), SingleType.Ad.INSTANCE);
                return;
            } else {
                this.mEntityItemIdentifier = new EntityItemIdentifier(new Url(this.mediaItemIds.get(type7).toString()), SingleType.Track.INSTANCE);
                return;
            }
        }
        Map<MediaItemId.Type, MediaItemId> map7 = this.mediaItemIds;
        MediaItemId.Type type8 = MediaItemId.Type.CONTENT_URI;
        if (map7.get(type8) != null) {
            this.mEntityItemIdentifier = new EntityItemIdentifier(new LocalId(this.mediaItemIds.get(type8).toString()), SingleType.Track.INSTANCE);
        }
    }

    public void addMediaItemId(@NonNull MediaItemId mediaItemId) {
        this.mediaItemIds.put(mediaItemId.getType(), mediaItemId);
    }

    public void setOverriddenPrimaryId(@NonNull MediaItemId mediaItemId) {
        this.mOverriddenPrimaryId = mediaItemId;
    }

    @Nullable
    public MediaItemId getOverriddenPrimaryId() {
        return this.mOverriddenPrimaryId;
    }

    @NonNull
    public Map<String, String> getMetricsContext() {
        return this.metricsContext;
    }

    public void setMetricsContext(@NonNull Map<String, String> map) {
        this.metricsContext.clear();
        this.metricsContext.putAll(map);
    }

    public boolean isVoiceInitiated() {
        return this.isVoiceInitiated;
    }

    public void setVoiceInitiated(boolean z) {
        this.isVoiceInitiated = z;
    }

    public MediaItemId getMediaItemId() {
        if (this.mediaItemIds.isEmpty()) {
            return null;
        }
        for (MediaItemId.Type type : MediaItemId.Type.values()) {
            MediaItemId mediaItemId = this.mediaItemIds.get(type);
            if (mediaItemId != null) {
                return mediaItemId;
            }
        }
        return null;
    }

    public boolean isOffSetPlaybackEnabled() {
        return this.mCuePoints != null;
    }

    public void setOffSetPlaybackCuePoints(CuePoints cuePoints) {
        this.mCuePoints = cuePoints;
    }

    public CuePoints getOffSetCuePoints() {
        return this.mCuePoints;
    }

    @Nullable
    public MediaCollectionInfo getSourceMediaCollectionInfo() {
        return this.sourceCollection;
    }

    public void setSourceMediaCollectionInfo(MediaCollectionInfo mediaCollectionInfo) {
        MediaCollectionInfo mediaCollectionInfo2 = this.sourceCollection;
        if (mediaCollectionInfo2 == mediaCollectionInfo) {
            return;
        }
        if (mediaCollectionInfo2 != null) {
            this.collections.remove(0);
        }
        this.sourceCollection = mediaCollectionInfo;
        if (mediaCollectionInfo != null) {
            this.collections.add(0, mediaCollectionInfo);
        }
        if (mediaCollectionInfo != null) {
            if (mediaCollectionInfo.getType() == MediaCollectionType.ALBUM && StringUtils.isEmpty(this.albumName)) {
                this.albumName = mediaCollectionInfo.getName();
            } else if (mediaCollectionInfo.getType() == MediaCollectionType.ARTIST && StringUtils.isEmpty(this.artistName)) {
                this.artistName = mediaCollectionInfo.getName();
            }
        }
    }

    public MediaCollectionInfo getMediaCollectionInfo(MediaCollectionType mediaCollectionType, MediaCollectionId.Type type) {
        for (MediaCollectionInfo mediaCollectionInfo : this.collections) {
            if (mediaCollectionType == null || mediaCollectionInfo.getType() == mediaCollectionType) {
                if (type == null || (mediaCollectionInfo.getId() != null && mediaCollectionInfo.getId().getType() == type)) {
                    return mediaCollectionInfo;
                }
            }
        }
        return null;
    }

    public List<MediaCollectionInfo> getCollections() {
        return this.readOnlyCollections;
    }

    public void addMediaCollectionInfo(MediaCollectionInfo mediaCollectionInfo) {
        if (this.collections.contains(mediaCollectionInfo) || mediaCollectionInfo == null) {
            return;
        }
        MediaCollectionType type = mediaCollectionInfo.getType();
        int size = this.collections.size();
        while (true) {
            size--;
            if (size >= 0) {
                MediaCollectionInfo mediaCollectionInfo2 = this.collections.get(size);
                if (mediaCollectionInfo2.getType() == type) {
                    if (mediaCollectionInfo2.getId() == null) {
                        this.collections.remove(size);
                    } else if (mediaCollectionInfo.getId() == null) {
                        return;
                    }
                }
            } else {
                this.collections.add(mediaCollectionInfo);
                if (mediaCollectionInfo.getType() == MediaCollectionType.ALBUM && StringUtils.isEmpty(this.albumName)) {
                    this.albumName = mediaCollectionInfo.getName();
                    return;
                } else {
                    if (mediaCollectionInfo.getType() == MediaCollectionType.ARTIST && StringUtils.isEmpty(this.artistName)) {
                        this.artistName = mediaCollectionInfo.getName();
                        return;
                    }
                    return;
                }
            }
        }
    }

    public MediaItemId getMediaItemId(@NonNull MediaItemId.Type type) {
        return this.mediaItemIds.get(type);
    }

    public Type getType() {
        return this.type;
    }

    public boolean isOwned() {
        return this.accessInfo.isOwned();
    }

    public boolean isBOP() {
        return this.accessInfo.isBOP();
    }

    @NonNull
    public String getName() {
        return this.name;
    }

    @NonNull
    public String getArtistName() {
        return this.artistName;
    }

    @NonNull
    public String getAlbumName() {
        return this.albumName;
    }

    public long getDurationMillis() {
        return this.durationMillis;
    }

    public PlaybackAttributes getBestAvailablePlaybackAttributes() {
        return this.bestAvailableAttributes;
    }

    public void setBestAvailablePlaybackAttributes(PlaybackAttributes playbackAttributes) {
        this.bestAvailableAttributes = playbackAttributes;
    }

    public final void setDurationMillis(long j) {
        this.durationMillis = j;
    }

    private ImageType[] getActualImageTypes(ImageType[] imageTypeArr) {
        return (imageTypeArr == null || imageTypeArr.length == 0 || imageTypeArr[0] == null) ? ANY_IMAGE_TYPE : imageTypeArr;
    }

    public final Uri getImageUri(int i, int i2, ImageType... imageTypeArr) {
        for (ImageType imageType : getActualImageTypes(imageTypeArr)) {
            Uri imageUri = getImageUri(new ImageInfo(this, imageType, i, i2));
            if (imageUri != null) {
                return imageUri;
            }
        }
        return null;
    }

    @WorkerThread
    public final Uri createImageUri(int i, int i2, ImageType... imageTypeArr) {
        for (ImageType imageType : getActualImageTypes(imageTypeArr)) {
            Uri uriCreateImageUri = createImageUri(new ImageInfo(this, imageType, i, i2));
            if (uriCreateImageUri != null) {
                return uriCreateImageUri;
            }
        }
        return null;
    }

    private Uri getImageUri(ImageInfo imageInfo) {
        synchronized (this.imagesLock) {
            Map<ImageInfo, Uri> map = this.cachedImageUris;
            if (map != null && map.keySet().contains(imageInfo)) {
                return this.cachedImageUris.get(imageInfo);
            }
            return this.imageUriProvider.getImageUri(imageInfo);
        }
    }

    @WorkerThread
    private Uri createImageUri(ImageInfo imageInfo) {
        Uri uri;
        synchronized (this.imagesLock) {
            Map<ImageInfo, Uri> map = this.cachedImageUris;
            if (map != null && map.keySet().contains(imageInfo)) {
                return this.cachedImageUris.get(imageInfo);
            }
            if (this.imagesBeingFetched == null) {
                this.imagesBeingFetched = new HashMap();
            }
            Object obj = this.imagesBeingFetched.get(imageInfo);
            if (obj == null) {
                this.imagesBeingFetched.put(imageInfo, imageInfo);
            }
            if (obj != null) {
                synchronized (obj) {
                    while (true) {
                        synchronized (this.imagesLock) {
                            Map<ImageInfo, Uri> map2 = this.cachedImageUris;
                            if (map2 != null && map2.keySet().contains(imageInfo)) {
                                uri = this.cachedImageUris.get(imageInfo);
                            }
                        }
                        try {
                            obj.wait();
                        } catch (InterruptedException unused) {
                        }
                    }
                }
                return uri;
            }
            Uri uriCreateImageUri = this.imageUriProvider.createImageUri(imageInfo);
            synchronized (imageInfo) {
                synchronized (this.imagesLock) {
                    if (this.cachedImageUris == null) {
                        this.cachedImageUris = new HashMap();
                    }
                    this.cachedImageUris.put(imageInfo, uriCreateImageUri);
                    this.imagesBeingFetched.remove(imageInfo);
                    imageInfo.notifyAll();
                }
            }
            return uriCreateImageUri;
        }
    }

    @WorkerThread
    protected Uri createMediaItemImageUri(int i, int i2) {
        return getMediaItemImageUri(i, i2);
    }

    @WorkerThread
    protected Uri createArtistImageUri(int i, int i2) {
        return getArtistImageUri(i, i2);
    }

    @WorkerThread
    protected Uri createAlbumImageUri(int i, int i2) {
        return getAlbumImageUri(i, i2);
    }

    @NonNull
    public List<MediaLink> getMediaLinks() {
        return this.links;
    }

    protected final void setMediaLinks(List<MediaLink> list) {
        this.links = list != null ? new ArrayList<>(list) : Collections.emptyList();
        this.fetchingLinks = false;
        this.listeners.proxy().onMediaLinksChanged(this);
    }

    public boolean isFetchingMediaLinks() {
        return this.fetchingLinks;
    }

    public void fetchMediaLinks(final BitRateSelection bitRateSelection, final boolean z, final List<MediaLink.Type> list) {
        synchronized (this.lock) {
            if (!isFetchingMediaLinks() && canFetchMediaLinks()) {
                onFetchingLinks();
                ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.MediaItem.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            MediaLinks mediaLinksDoFetchLinks = MediaItem.this.doFetchLinks(bitRateSelection, z, list);
                            PlaybackAttributes bestAvailablePlaybackAttributes = mediaLinksDoFetchLinks.getBestAvailablePlaybackAttributes();
                            if (!PlaybackAttributes.UNKNOWN.equals(bestAvailablePlaybackAttributes)) {
                                MediaItem.this.setBestAvailablePlaybackAttributes(bestAvailablePlaybackAttributes);
                            }
                            MediaItem.this.setMediaLinks(mediaLinksDoFetchLinks.getLinks());
                        } catch (PlaybackException e) {
                            MediaItem.this.setCanFetchMediaLinks(e.getRecoverability().canRecoverMediaItem());
                            MediaItem.this.onFetchLinksFailed(e);
                        }
                    }
                });
            }
        }
    }

    public boolean canFetchMediaLinks() {
        return this.canFetchLinks;
    }

    public void addMediaItemListener(MediaItemListener mediaItemListener) {
        this.listeners.add(mediaItemListener);
    }

    public void removeMediaItemListener(MediaItemListener mediaItemListener) {
        this.listeners.remove(mediaItemListener);
    }

    public boolean isDirectedPlay() {
        return this.directedPlay;
    }

    public void setDirectedPlay(boolean z) {
        this.directedPlay = z;
    }

    public boolean isCustomerSelectedEntity() {
        return this.customerSelectedEntity;
    }

    public void setCustomerSelectedEntity(boolean z) {
        this.customerSelectedEntity = z;
    }

    protected final void setCanFetchMediaLinks(boolean z) {
        synchronized (this.lock) {
            this.canFetchLinks = z;
        }
    }

    public void setCanSeekOverride(boolean z) {
        this.canSeekOverride = z;
    }

    protected final void onFetchingLinks() {
        synchronized (this.lock) {
            this.fetchingLinks = true;
        }
    }

    protected final void onFetchLinksFailed(PlaybackException playbackException) {
        synchronized (this.lock) {
            this.fetchingLinks = false;
        }
        this.listeners.proxy().onErrorFetchingMediaLinks(this, playbackException);
    }

    protected MediaLinks doFetchLinks(BitRateSelection bitRateSelection, boolean z, List<MediaLink.Type> list) throws PlaybackException {
        MediaLinkProvider mediaLinkProvider = this.mediaLinkProvider;
        if (mediaLinkProvider == null) {
            return MediaLinks.EMPTY;
        }
        if (list == null) {
            return mediaLinkProvider.getMediaLinks(this, bitRateSelection);
        }
        return mediaLinkProvider.getMediaLinks(this, bitRateSelection, z, list);
    }

    public String toString() {
        String simpleName = getClass().getSimpleName();
        if (!simpleName.contains("Media")) {
            simpleName = "MediaItem";
        }
        String str = simpleName + "@" + System.identityHashCode(this);
        if (StringUtils.shouldRedact()) {
            return str;
        }
        return str + "{'" + this.name + "' by " + this.artistName + "; ids: " + StringUtils.toString(this.mediaItemIds.values()) + ", collections: " + StringUtils.toString(this.collections) + "}";
    }

    public boolean hasSameId(@NonNull MediaItem mediaItem) {
        for (MediaItemId mediaItemId : this.mediaItemIds.values()) {
            if (mediaItemId != null && mediaItemId.equals(mediaItem.getMediaItemId(mediaItemId.getType()))) {
                return true;
            }
        }
        return false;
    }

    public List<MediaItemId> getMediaItemIds() {
        return new ArrayList(this.mediaItemIds.values());
    }

    public String getSessionId() {
        return this.sessionId;
    }

    public void setSessionId(String str) {
        this.sessionId = StringUtils.nullToEmpty(str);
    }

    @NonNull
    public PlaybackPageType getPlaybackPageType() {
        return this.playbackPageType;
    }

    public boolean canSeek() {
        return !getType().isLive() && this.canSeekOverride;
    }

    public MediaAccessInfo getMediaAccessInfo() {
        return this.accessInfo;
    }

    public void fetchAndInitializeLikeState() {
        List<String> listSingletonList = Collections.singletonList(getMediaItemIdString(this, MediaItemId.Type.ASIN, null));
        MediaItemLikesProvider mediaItemLikesProvider = this.mMediaItemLikesProvider;
        if (mediaItemLikesProvider != null) {
            mediaItemLikesProvider.fetchAndInitializeLikeState(this, listSingletonList);
        }
    }

    public void initializeLikeState(LikeState likeState) {
        this.mLikeState = likeState;
    }

    public void setCanLike(boolean z) {
        this.mCanLike = z;
    }

    public boolean getCanLike() {
        return this.mCanLike;
    }

    public LikeState getLikeState() {
        return this.mLikeState;
    }

    public void setLikeState(LikeState likeState) {
        if (this.mLikeState == likeState) {
            return;
        }
        MediaItemLikesProvider mediaItemLikesProvider = this.mMediaItemLikesProvider;
        if (mediaItemLikesProvider != null) {
            mediaItemLikesProvider.setLikeState(getMediaItemIdString(this, MediaItemId.Type.ASIN, null), likeState);
        }
        this.mLikeState = likeState;
    }

    public void updateLikeStateFromRating(int i) {
        this.mLikeState = getLikeStateFromRating(i);
    }

    public LikeState getLikeStateFromRating(int i) {
        if (i == 1) {
            return LikeState.LIKE;
        }
        if (i == 2) {
            return LikeState.DISLIKE;
        }
        return LikeState.NEUTRAL;
    }

    @Nullable
    public List<ContentEncoding> getContentEncodings() {
        return this.contentEncodings;
    }

    public void setContentEncodings(@Nullable List<ContentEncoding> list) {
        this.contentEncodings = list;
    }

    @Override // com.amazon.music.platform.data.entity.EntityItem
    @Nullable
    public EntityItemIdentifier getEntityItemIdentifier() {
        return this.mEntityItemIdentifier;
    }

    public static class Type {
        private final boolean live;
        private final String name;
        private final boolean owned;
        private final boolean prime;
        private final boolean sports;
        private static final Map<String, Type> types = new HashMap();
        public static final Type UNAVAILABLE = getType("UNAVAILABLE", false, false, false, false);
        public static final Type LIVE_SPORTS = getType("LIVE", false, true, false, true);
        public static final Type ONDEMAND_SPORTS = getType("ONDEMAND", false, true, false, false);
        public static final Type LOCAL_CONTENT = getType("LOCAL_CONTENT", true, false, false, false);
        public static final Type CLOUD_CONTENT = getType("CLOUD_CONTENT", true, false, false, false);
        public static final Type OWNED_CONTENT = getType("OWNED_CONTENT", true, false, false, false);
        public static final Type PRIME_CONTENT = getType("PRIME_CONTENT", false, false, true, false);
        public static final Type UNLIMITED_CONTENT = getType("UNLIMITED_CONTENT", false, false, false, false);
        public static final Type NIGHTWING_CONTENT = getType("NIGHTWING_CONTENT", false, false, false, false);
        public static final Type AD_CONTENT = getType("AD_CONTENT", false, false, false, false);
        public static final Type HTTPS_CONTENT = getType("HTTPS_CONTENT", false, false, false, false);
        public static final Type PODCAST_CONTENT = getType("PODCAST_CONTENT", false, false, false, false);

        public static Type getType(@NonNull String str, boolean z, boolean z2, boolean z3, boolean z4) {
            return getType(str, str, z, z2, z3, z4);
        }

        private static Type getType(@NonNull String str, @NonNull String str2, boolean z, boolean z2, boolean z3, boolean z4) {
            Type type;
            Map<String, Type> map = types;
            synchronized (map) {
                type = map.get(str);
                if (type == null) {
                    type = new Type(str2, z, z2, z3, z4);
                    map.put(str, type);
                } else if (type.owned != z || type.sports != z2 || type.prime != z3 || type.live != z4) {
                    StrictMode.crashIfStrict("getType(" + str2 + ", " + z + ", " + z2 + ", " + z3 + ", " + z4 + ") called after getType(" + str2 + ", " + type.owned + ", " + type.sports + ", " + type.prime + ", " + type.live + "). Either a mistake was made or the metrics definitions aren't specific enough, in which case either they should be changed or this crashIfStrict statement should be removed.");
                    StringBuilder sb = new StringBuilder();
                    sb.append(str2);
                    sb.append("<");
                    sb.append(z);
                    sb.append(", ");
                    sb.append(z2);
                    sb.append(", ");
                    sb.append(z3);
                    sb.append(", ");
                    sb.append(z4);
                    sb.append(">");
                    type = getType(sb.toString(), str2, z, z2, z3, z4);
                }
            }
            return type;
        }

        private Type(String str, boolean z, boolean z2, boolean z3, boolean z4) {
            this.name = (String) Validate.notEmpty(str);
            this.owned = z;
            this.sports = z2;
            this.prime = z3;
            this.live = z4;
        }

        public boolean isOwned() {
            return this.owned;
        }

        public boolean isSports() {
            return this.sports;
        }

        public boolean isLive() {
            return this.live;
        }

        public String toString() {
            return this.name;
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.media.playback.MediaItem$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$media$playback$MediaItem$ImageType;

        static {
            int[] iArr = new int[ImageType.values().length];
            $SwitchMap$com$amazon$music$media$playback$MediaItem$ImageType = iArr;
            try {
                iArr[ImageType.ARTIST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$media$playback$MediaItem$ImageType[ImageType.ALBUM.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    public static class SimpleImageUriProvider implements ImageUriProvider {
        @Override // com.amazon.music.media.playback.MediaItem.ImageUriProvider
        public Uri getImageUri(@NonNull ImageInfo imageInfo) {
            int i = AnonymousClass2.$SwitchMap$com$amazon$music$media$playback$MediaItem$ImageType[imageInfo.imageType.ordinal()];
            if (i != 1) {
                return i != 2 ? imageInfo.mediaItem.getMediaItemImageUri(imageInfo.width, imageInfo.height) : imageInfo.mediaItem.getAlbumImageUri(imageInfo.width, imageInfo.height);
            }
            return imageInfo.mediaItem.getArtistImageUri(imageInfo.width, imageInfo.height);
        }

        @Override // com.amazon.music.media.playback.MediaItem.ImageUriProvider
        @WorkerThread
        public Uri createImageUri(@NonNull ImageInfo imageInfo) {
            int i = AnonymousClass2.$SwitchMap$com$amazon$music$media$playback$MediaItem$ImageType[imageInfo.imageType.ordinal()];
            if (i != 1) {
                return i != 2 ? imageInfo.mediaItem.createMediaItemImageUri(imageInfo.width, imageInfo.height) : imageInfo.mediaItem.createAlbumImageUri(imageInfo.width, imageInfo.height);
            }
            return imageInfo.mediaItem.createArtistImageUri(imageInfo.width, imageInfo.height);
        }
    }

    public static class ImageInfo {
        private final int height;
        private final ImageType imageType;
        private final MediaItem mediaItem;
        private final int width;

        public ImageInfo(@NonNull MediaItem mediaItem, @NonNull ImageType imageType, int i, int i2) {
            this.mediaItem = mediaItem;
            this.imageType = imageType;
            this.width = i;
            this.height = i2;
        }

        @NonNull
        public MediaItem getMediaItem() {
            return this.mediaItem;
        }

        @NonNull
        public ImageType getImageType() {
            return this.imageType;
        }

        public int getWidth() {
            return this.width;
        }

        public int getHeight() {
            return this.height;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            ImageInfo imageInfo = (ImageInfo) obj;
            return this.width == imageInfo.width && this.height == imageInfo.height && this.imageType == imageInfo.imageType && this.mediaItem == imageInfo.mediaItem;
        }

        public int hashCode() {
            return (((((this.mediaItem.hashCode() * 31) + this.imageType.hashCode()) * 31) + this.width) * 31) + this.height;
        }
    }

    public void setLikesProvider(MediaItemLikesProvider mediaItemLikesProvider) {
        this.mMediaItemLikesProvider = mediaItemLikesProvider;
    }
}
