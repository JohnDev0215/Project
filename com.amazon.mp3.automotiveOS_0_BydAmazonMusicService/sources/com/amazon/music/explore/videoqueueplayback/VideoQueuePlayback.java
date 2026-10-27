package com.amazon.music.explore.videoqueueplayback;

import Touch.VideoQueueTemplateInterface.v1_0.VideoQueueElement;
import Touch.WidgetsInterface.v1_0.VideoCarouselElement;
import android.content.Context;
import android.view.TextureView;
import com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager;
import com.amazon.music.skyfire.core.OwnerRegistry;
import com.amazon.music.skyfire.core.template.Handlers;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class VideoQueuePlayback implements VideoQueuePlayerManager.Callback {
    private Context context;
    private MethodsDispatcher methodsDispatcher;
    private OwnerRegistry ownerRegistry;
    private VideoCarouselElement videoCarouselElement;
    private VideoQueueElement videoQueue;
    private VideoQueuePlayerManager videoQueuePlayerManager;
    private VideoScreenCallback videoScreenCallback;
    private final Logger logger = LoggerFactory.getLogger("VideoQueuePlayback");
    private final Set<PositionCallback> positionCallbacks = Collections.newSetFromMap(new ConcurrentHashMap());
    private final Set<ContentStateCallback> contentStateCallbacks = Collections.newSetFromMap(new ConcurrentHashMap());

    public interface ContentStateCallback {
        void onContentLoading();

        void onContentReady();
    }

    public interface PositionCallback {
        void onPlaybackFinished();

        void onPlaybackPositionChanged(int i, long j);
    }

    public interface VideoScreenCallback {
        TextureView getTextureView(int i);
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager.Callback
    public void onPlaybackStarted() {
    }

    public VideoQueuePlayback(Context context) {
        this.context = context;
        this.videoQueuePlayerManager = new VideoQueuePlayerManager(context, this, this);
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager.Callback
    public void onPlaybackPositionChanged(int i, long j) {
        postPlaybackPositionUpdates(this.positionCallbacks, i, j);
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager.Callback
    public void onPlaybackFinished() {
        postPlaybackFinishedUpdates(this.positionCallbacks);
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager.Callback
    public void onContentLoading() {
        if (CollectionUtils.isEmpty(this.contentStateCallbacks)) {
            return;
        }
        Iterator<ContentStateCallback> it = this.contentStateCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onContentLoading();
        }
    }

    @Override // com.amazon.music.explore.videoqueueplayback.VideoQueuePlayerManager.Callback
    public void onContentReady() {
        if (CollectionUtils.isEmpty(this.contentStateCallbacks)) {
            return;
        }
        Iterator<ContentStateCallback> it = this.contentStateCallbacks.iterator();
        while (it.hasNext()) {
            it.next().onContentReady();
        }
    }

    public void setOwnerRegistry(OwnerRegistry ownerRegistry) {
        this.ownerRegistry = ownerRegistry;
    }

    public void setMethodsDispatcher(MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }

    public void setVideoQueue(VideoQueueElement videoQueueElement) {
        this.videoQueue = videoQueueElement;
        prepareQueue();
    }

    public void setVideoPreviewQueue(VideoCarouselElement videoCarouselElement) {
        this.videoCarouselElement = videoCarouselElement;
        preparePreviewQueue();
    }

    public void setVideoScreenCallback(VideoScreenCallback videoScreenCallback) {
        this.videoScreenCallback = videoScreenCallback;
    }

    public int getDuration() {
        return this.videoQueuePlayerManager.getDuration();
    }

    public void removeVideoScreenCallback() {
        this.videoScreenCallback = null;
    }

    public void addPositionCallback(PositionCallback positionCallback) {
        this.positionCallbacks.add(positionCallback);
    }

    public void removePositionCallback(PositionCallback positionCallback) {
        if (positionCallback == null) {
            return;
        }
        this.positionCallbacks.remove(positionCallback);
    }

    public void addContentStateCallback(ContentStateCallback contentStateCallback) {
        this.contentStateCallbacks.add(contentStateCallback);
    }

    public void removeContentStateCallback(ContentStateCallback contentStateCallback) {
        if (contentStateCallback == null) {
            return;
        }
        this.contentStateCallbacks.remove(contentStateCallback);
    }

    public void shutdown() {
        this.videoQueuePlayerManager.shutdown();
    }

    public void playQueuePosition(int i) {
        this.videoQueuePlayerManager.playQueuePosition(i);
    }

    public void playPreviewQueuePosition(int i) {
        this.videoQueuePlayerManager.playPreviewQueuePosition(i);
    }

    public void play() {
        this.videoQueuePlayerManager.play();
    }

    public void pause() {
        this.videoQueuePlayerManager.pause();
    }

    TextureView getTextureView(int i) {
        VideoScreenCallback videoScreenCallback = this.videoScreenCallback;
        if (videoScreenCallback == null) {
            return null;
        }
        return videoScreenCallback.getTextureView(i);
    }

    private void prepareQueue() {
        this.videoQueuePlayerManager.prepareQueue(this.videoQueue);
    }

    private void preparePreviewQueue() {
        this.videoQueuePlayerManager.preparePreviewQueue(this.videoCarouselElement);
    }

    private void postPlaybackPositionUpdates(final Set<PositionCallback> set, final int i, final long j) {
        if (CollectionUtils.isEmpty(set)) {
            return;
        }
        Handlers.getForeground().post(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                VideoQueuePlayback.lambda$postPlaybackPositionUpdates$0(set, i, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$postPlaybackPositionUpdates$0(Set set, int i, long j) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((PositionCallback) it.next()).onPlaybackPositionChanged(i, j);
        }
    }

    private void postPlaybackFinishedUpdates(final Set<PositionCallback> set) {
        if (CollectionUtils.isEmpty(set)) {
            return;
        }
        Handlers.getForeground().post(new Runnable() { // from class: com.amazon.music.explore.videoqueueplayback.VideoQueuePlayback$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                VideoQueuePlayback.lambda$postPlaybackFinishedUpdates$1(set);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$postPlaybackFinishedUpdates$1(Set set) {
        Iterator it = set.iterator();
        while (it.hasNext()) {
            ((PositionCallback) it.next()).onPlaybackFinished();
        }
    }
}
