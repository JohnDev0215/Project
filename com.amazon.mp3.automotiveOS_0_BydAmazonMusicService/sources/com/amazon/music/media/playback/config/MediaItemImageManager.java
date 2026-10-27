package com.amazon.music.media.playback.config;

import android.graphics.Bitmap;
import android.net.Uri;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.config.ImagePlaceholderProvider;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.util.ListenerSet;
import com.amazon.music.media.playback.util.ObjectUtils;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.io.IOException;

/* JADX INFO: loaded from: classes4.dex */
public class MediaItemImageManager {
    private PlaybackConfig config;
    private Bitmap currentImage;
    private Uri desiredImageUri;
    private boolean fetchingImage;
    private boolean forceRefetch;
    private int height;
    private final MediaItem.ImageType[] imageTypes;
    private MediaItem item;
    private ItemLoader<Uri, Bitmap> loader;
    private ImagePlaceholderProvider.PlaceholderType placeholderType;
    private ImagePlaceholderProvider provider;
    private int width;
    private final Object lock = new Object();
    private final ListenerSet<OnBitmapChangedListener> listeners = new ListenerSet<>(OnBitmapChangedListener.class, ListenerSet.DispatchStrategy.MAIN_THREAD, ListenerSet.DuplicateStrategy.REMOVE_DUPLICATES);
    private PlaybackConfig.OnConfigurationChangedListener configListener = new PlaybackConfig.OnConfigurationChangedListener() { // from class: com.amazon.music.media.playback.config.MediaItemImageManager.1
        @Override // com.amazon.music.media.playback.config.PlaybackConfig.OnConfigurationChangedListener
        public void onConfigurationChanged(PlaybackConfig playbackConfig) {
            if (MediaItemImageManager.this.config == playbackConfig) {
                MediaItemImageManager.this.onConfigurationChanged();
            }
        }
    };

    public interface OnBitmapChangedListener {
        void onBitmapChanged(MediaItemImageManager mediaItemImageManager);
    }

    public MediaItemImageManager(PlaybackConfig playbackConfig, int i, int i2, MediaItem.ImageType... imageTypeArr) {
        this.width = i;
        this.height = i2;
        this.imageTypes = (imageTypeArr == null || imageTypeArr.length == 0) ? MediaItem.ANY_IMAGE_TYPE : imageTypeArr;
        usePlaceholder(ImagePlaceholderProvider.PlaceholderType.DEFAULT);
        setPlaybackConfig(playbackConfig);
    }

    public void addOnBitmapChangedListener(OnBitmapChangedListener onBitmapChangedListener) {
        PlaybackConfig playbackConfig;
        boolean zIsEmpty = this.listeners.isEmpty();
        this.listeners.add(onBitmapChangedListener);
        if (!zIsEmpty || (playbackConfig = this.config) == null) {
            return;
        }
        playbackConfig.addOnConfigurationChangedListener(this.configListener);
        this.configListener.onConfigurationChanged(this.config);
    }

    public void removeOnBitmapChangedListener(OnBitmapChangedListener onBitmapChangedListener) {
        PlaybackConfig playbackConfig;
        if (this.listeners.isEmpty()) {
            return;
        }
        this.listeners.remove(onBitmapChangedListener);
        if (!this.listeners.isEmpty() || (playbackConfig = this.config) == null) {
            return;
        }
        playbackConfig.removeOnConfigurationChangedListener(this.configListener);
    }

    private void setImagePlaceholderProvider(ImagePlaceholderProvider imagePlaceholderProvider) {
        if (this.provider != imagePlaceholderProvider) {
            this.provider = imagePlaceholderProvider;
            ImagePlaceholderProvider.PlaceholderType placeholderType = this.placeholderType;
            if (placeholderType != null) {
                usePlaceholder(placeholderType);
            }
        }
    }

    private void setImageLoader(ItemLoader<Uri, Bitmap> itemLoader) {
        synchronized (this.lock) {
            if (this.loader == itemLoader) {
                return;
            }
            this.loader = itemLoader;
            if (this.desiredImageUri == null) {
                return;
            }
            if (itemLoader == null) {
                usePlaceholder(ImagePlaceholderProvider.PlaceholderType.ERROR);
            } else if (this.placeholderType == null) {
                usePlaceholder(ImagePlaceholderProvider.PlaceholderType.PLACEHOLDER);
                startFetching();
            }
        }
    }

    public void setPlaybackConfig(PlaybackConfig playbackConfig) {
        PlaybackConfig playbackConfig2 = this.config;
        if (playbackConfig2 != playbackConfig) {
            if (playbackConfig2 != null) {
                playbackConfig2.removeOnConfigurationChangedListener(this.configListener);
            }
            this.config = playbackConfig;
            if (playbackConfig != null && !this.listeners.isEmpty()) {
                this.config.addOnConfigurationChangedListener(this.configListener);
            }
            onConfigurationChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onConfigurationChanged() {
        PlaybackConfig playbackConfig = this.config;
        if (playbackConfig == null) {
            setImageLoader(null);
            setImagePlaceholderProvider(null);
        } else {
            setImageLoader(ScalingImageLoaders.getInstance(playbackConfig).getImageLoader(this.width, this.height));
            setImagePlaceholderProvider(this.config.getPlaceholderProvider());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void usePlaceholder(ImagePlaceholderProvider.PlaceholderType placeholderType) {
        this.placeholderType = placeholderType;
        if (placeholderType != null) {
            ImagePlaceholderProvider imagePlaceholderProvider = this.provider;
            setCurrentImage(imagePlaceholderProvider == null ? null : imagePlaceholderProvider.getPlaceholderImage(this.imageTypes[0], placeholderType, this.desiredImageUri, this.width, this.height));
        }
    }

    public void setMediaItem(MediaItem mediaItem) {
        setMediaItem(mediaItem, false);
    }

    public void setMediaItem(MediaItem mediaItem, boolean z) {
        synchronized (this.lock) {
            if (this.item == mediaItem) {
                return;
            }
            this.item = mediaItem;
            PlaybackConfig playbackConfig = this.config;
            if (playbackConfig != null && playbackConfig.isUseLocalImageUri() && this.config.getLocalImageUriProvider() != null) {
                createImageUriInBackground(mediaItem);
            } else {
                Uri uri = getUri(mediaItem, this.width, this.height);
                if (!z && (uri != null || mediaItem == null)) {
                    setDesiredUri(uri);
                } else {
                    createImageUriInBackground(mediaItem);
                }
            }
        }
    }

    private void createImageUriInBackground(final MediaItem mediaItem) {
        ThreadUtils.runInBackground(new Runnable() { // from class: com.amazon.music.media.playback.config.MediaItemImageManager.2
            @Override // java.lang.Runnable
            public void run() {
                Uri uriCreateImageUri;
                if (mediaItem != null && MediaItemImageManager.this.config.isUseLocalImageUri() && MediaItemImageManager.this.config.getLocalImageUriProvider() != null) {
                    uriCreateImageUri = MediaItemImageManager.this.config.getLocalImageUriProvider().createImageUri(new MediaItem.ImageInfo(mediaItem, MediaItem.ImageType.MEDIA_ITEM, MediaItemImageManager.this.width, MediaItemImageManager.this.height));
                } else {
                    MediaItem mediaItem2 = mediaItem;
                    uriCreateImageUri = mediaItem2 != null ? mediaItem2.createImageUri(MediaItemImageManager.this.width, MediaItemImageManager.this.height, MediaItemImageManager.this.imageTypes) : null;
                }
                synchronized (MediaItemImageManager.this.lock) {
                    if (mediaItem == MediaItemImageManager.this.item) {
                        MediaItemImageManager.this.setDesiredUri(uriCreateImageUri);
                    }
                }
            }
        });
    }

    public MediaItem getMediaItem() {
        return this.item;
    }

    public Uri getImageUri() {
        return this.desiredImageUri;
    }

    protected Uri getUri(MediaItem mediaItem, int i, int i2) {
        if (mediaItem == null) {
            return null;
        }
        return mediaItem.getImageUri(i, i2, this.imageTypes);
    }

    public void setArtworkUri(Uri uri) {
        setDesiredUri(uri);
    }

    public void reset() {
        this.desiredImageUri = null;
        this.currentImage = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDesiredUri(Uri uri) {
        synchronized (this.lock) {
            if (ObjectUtils.equal(uri, this.desiredImageUri)) {
                return;
            }
            this.desiredImageUri = uri;
            ItemLoader<Uri, Bitmap> itemLoader = this.loader;
            if (itemLoader == null || uri == null) {
                usePlaceholder(uri == null ? ImagePlaceholderProvider.PlaceholderType.DEFAULT : ImagePlaceholderProvider.PlaceholderType.ERROR);
            } else {
                Bitmap ifCached = itemLoader.getIfCached(uri);
                if (ifCached != null) {
                    usePlaceholder(null);
                    setCurrentImage(ifCached);
                } else if (!this.fetchingImage) {
                    startFetching();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setCurrentImage(Bitmap bitmap) {
        if (this.currentImage != bitmap) {
            this.currentImage = bitmap;
            this.listeners.proxy().onBitmapChanged(this);
        }
    }

    public Bitmap getBitmap() {
        return this.currentImage;
    }

    public ImagePlaceholderProvider.PlaceholderType getPlaceholderType() {
        return this.placeholderType;
    }

    private void startFetching() {
        synchronized (this.lock) {
            usePlaceholder(ImagePlaceholderProvider.PlaceholderType.PLACEHOLDER);
            if (this.fetchingImage) {
                return;
            }
            this.fetchingImage = true;
            ThreadUtils.postInBackground(new Runnable() { // from class: com.amazon.music.media.playback.config.MediaItemImageManager.3
                @Override // java.lang.Runnable
                public void run() {
                    ItemLoader itemLoader;
                    Uri uri;
                    ItemLoader itemLoader2 = null;
                    Uri uri2 = null;
                    Bitmap bitmap = null;
                    boolean z = false;
                    while (true) {
                        synchronized (MediaItemImageManager.this.lock) {
                            boolean z2 = (MediaItemImageManager.this.loader == itemLoader2 && ObjectUtils.equal(uri2, MediaItemImageManager.this.desiredImageUri)) ? false : true;
                            itemLoader = MediaItemImageManager.this.loader;
                            if (MediaItemImageManager.this.desiredImageUri == null || itemLoader == null || (!z2 && !MediaItemImageManager.this.forceRefetch)) {
                                break;
                            }
                            uri = MediaItemImageManager.this.desiredImageUri;
                            MediaItemImageManager.this.forceRefetch = false;
                        }
                        try {
                            bitmap = (Bitmap) itemLoader.load(uri);
                            z = false;
                        } catch (IOException unused) {
                            z = true;
                        }
                        uri2 = uri;
                        itemLoader2 = itemLoader;
                    }
                    if (MediaItemImageManager.this.desiredImageUri != null && itemLoader != null) {
                        if (z) {
                            MediaItemImageManager.this.usePlaceholder(ImagePlaceholderProvider.PlaceholderType.ERROR);
                        } else {
                            MediaItemImageManager.this.usePlaceholder(null);
                            MediaItemImageManager.this.setCurrentImage(bitmap);
                        }
                    }
                    MediaItemImageManager.this.fetchingImage = false;
                }
            });
        }
    }
}
