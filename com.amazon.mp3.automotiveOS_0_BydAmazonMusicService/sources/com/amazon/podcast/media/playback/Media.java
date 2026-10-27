package com.amazon.podcast.media.playback;

import Podcast.PlaybackInterface.v1_0.MediaActionsElement;
import Podcast.PlaybackInterface.v1_0.MediaControlsElement;
import Podcast.PlaybackInterface.v1_0.MediaEventsElement;
import Podcast.PlaybackInterface.v1_0.MediaMetadataElement;
import Podcast.PlaybackInterface.v1_0.SetMediaMethod;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.external.car.PodcastMediaMetadata;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/* JADX INFO: loaded from: classes5.dex */
public final class Media {
    private MediaActionsElement mediaActionsElement;
    private MediaControlsElement mediaControlsElement;
    private MediaEventsElement mediaEventsElement;
    private MediaMetadataElement mediaMetadataElement;
    private String mediaUri;
    private PodcastMediaMetadata podcastMediaMetadata;
    private final Set<MediaMetadataCallback> mediaMetadataCallbacks = new CopyOnWriteArraySet();
    private final Set<MediaControlsCallback> mediaControlsCallbacks = new CopyOnWriteArraySet();
    private final Set<PodcastMediaMetadataCallback> podcastMediaMetadataCallbacks = new CopyOnWriteArraySet();
    private boolean isPlaying = false;

    public interface MediaControlsCallback {
        void onMediaControlsChange(final MediaControlsElement controlsElement);
    }

    public interface MediaMetadataCallback {
        void onMediaMetadataChange(final MediaMetadataElement metadataElement);
    }

    public interface PodcastMediaMetadataCallback {
        void onPodcastMediaMetadataChange(final PodcastMediaMetadata podcastMediaMetadata);
    }

    public void addMediaMetadataCallback(final MediaMetadataCallback callback) {
        this.mediaMetadataCallbacks.add(callback);
    }

    public void removeMediaMetadataCallback(final MediaMetadataCallback callback) {
        this.mediaMetadataCallbacks.remove(callback);
    }

    public void addMediaControlsCallback(final MediaControlsCallback callback) {
        this.mediaControlsCallbacks.add(callback);
    }

    public void removeMediaControlsCallback(final MediaControlsCallback callback) {
        this.mediaControlsCallbacks.remove(callback);
    }

    public void addPodcastMediaMetadataCallback(final PodcastMediaMetadataCallback callback) {
        this.podcastMediaMetadataCallbacks.add(callback);
    }

    public void removePodcastMediaMetadataCallback(final PodcastMediaMetadataCallback callback) {
        this.podcastMediaMetadataCallbacks.remove(callback);
    }

    public String mediaId() {
        MediaMetadataElement mediaMetadataElement = this.mediaMetadataElement;
        if (mediaMetadataElement == null) {
            return null;
        }
        return mediaMetadataElement.getMediaId();
    }

    public String mediaCollectionId() {
        MediaMetadataElement mediaMetadataElement = this.mediaMetadataElement;
        if (mediaMetadataElement == null) {
            return null;
        }
        return mediaMetadataElement.getMediaCollectionId();
    }

    public MediaMetadataElement getMediaMetadataElement() {
        return this.mediaMetadataElement;
    }

    public MediaEventsElement getMediaEventsElement() {
        return this.mediaEventsElement;
    }

    public MediaControlsElement getMediaControlsElement() {
        return this.mediaControlsElement;
    }

    public MediaActionsElement getMediaActionsElement() {
        return this.mediaActionsElement;
    }

    public PodcastMediaMetadata getPodcastMediaMetadata() {
        return this.podcastMediaMetadata;
    }

    public void clear() {
        this.mediaMetadataElement = null;
        this.mediaActionsElement = null;
        this.mediaControlsElement = null;
        this.mediaEventsElement = null;
        postMetadataUpdate(null, null);
        postControlsUpdate(null);
        this.isPlaying = false;
        this.podcastMediaMetadata = null;
    }

    public String getMediaUri() {
        return this.mediaUri;
    }

    void setMediaUri(final String uri) {
        this.mediaUri = uri;
    }

    void setMedia(final SetMediaMethod method) {
        this.mediaMetadataElement = method.getMetadata();
        this.mediaEventsElement = method.getEvents();
        this.mediaControlsElement = method.getControls();
        this.mediaActionsElement = method.getActions();
        PodcastMediaMetadata podcastMediaMetadataConvertToPodcastMediaMetadata = convertToPodcastMediaMetadata(this.mediaMetadataElement);
        this.podcastMediaMetadata = podcastMediaMetadataConvertToPodcastMediaMetadata;
        postMetadataUpdate(this.mediaMetadataElement, podcastMediaMetadataConvertToPodcastMediaMetadata);
        postControlsUpdate(this.mediaControlsElement);
    }

    private PodcastMediaMetadata convertToPodcastMediaMetadata(MediaMetadataElement mediaMetadataElement) {
        if (mediaMetadataElement == null) {
            return null;
        }
        return new PodcastMediaMetadata(mediaMetadataElement.getPodcastDeeplink(), mediaMetadataElement.getMediaCollectionId(), mediaMetadataElement.getMediaId(), mediaMetadataElement.getSubtitle(), mediaMetadataElement.getTitle(), mediaMetadataElement.getArtwork(), mediaMetadataElement.getDurationSeconds());
    }

    private void postMetadataUpdate(final MediaMetadataElement element, final PodcastMediaMetadata podcastMediaMetadata) {
        if (this.mediaMetadataCallbacks.isEmpty() && this.podcastMediaMetadataCallbacks.isEmpty()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Media.1
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = Media.this.mediaMetadataCallbacks.iterator();
                while (it.hasNext()) {
                    ((MediaMetadataCallback) it.next()).onMediaMetadataChange(element);
                }
                Iterator it2 = Media.this.podcastMediaMetadataCallbacks.iterator();
                while (it2.hasNext()) {
                    ((PodcastMediaMetadataCallback) it2.next()).onPodcastMediaMetadataChange(podcastMediaMetadata);
                }
            }
        });
    }

    private void postControlsUpdate(final MediaControlsElement element) {
        if (this.mediaControlsCallbacks.isEmpty()) {
            return;
        }
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.media.playback.Media.2
            @Override // java.lang.Runnable
            public void run() {
                Iterator it = Media.this.mediaControlsCallbacks.iterator();
                while (it.hasNext()) {
                    ((MediaControlsCallback) it.next()).onMediaControlsChange(element);
                }
            }
        });
    }

    public void startedPlaying() {
        this.isPlaying = true;
    }

    public boolean hasStartedPlaying() {
        return this.isPlaying;
    }
}
