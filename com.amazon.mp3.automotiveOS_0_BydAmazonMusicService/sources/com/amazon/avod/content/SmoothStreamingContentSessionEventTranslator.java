package com.amazon.avod.content;

import com.amazon.avod.content.event.ContentEventDownloadUpdate;
import com.amazon.avod.content.event.ContentEventDownloaded;
import com.amazon.avod.content.event.ContentEventFragmentDownloaded;
import com.amazon.avod.content.event.ContentEventSecondaryDownloadEligibilityStateChanged;
import com.amazon.avod.content.event.ContentEventSessionEnding;
import com.amazon.avod.content.event.ContentEventStartDownloading;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.ContentMetadataAcquiredEvent;
import com.amazon.avod.content.event.FatalContentEventError;
import com.amazon.avod.content.event.FragmentInformationHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.google.common.eventbus.Subscribe;

/* JADX INFO: loaded from: classes2.dex */
public class SmoothStreamingContentSessionEventTranslator {
    private final ContentManagementEventBus mEventBus;
    private ContentSessionEventListener mListener;

    public SmoothStreamingContentSessionEventTranslator(ContentManagementEventBus contentManagementEventBus) {
        this.mEventBus = contentManagementEventBus;
    }

    public void setListener(ContentSessionEventListener contentSessionEventListener) {
        this.mListener = contentSessionEventListener;
    }

    public void begin() {
        this.mEventBus.registerEventBusHandler(this);
    }

    public void end() {
        this.mEventBus.unregisterEventBusHandler(this);
    }

    @Subscribe
    public void handleErrorEvent(FatalContentEventError fatalContentEventError) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onFatalError(fatalContentEventError.getException());
        }
    }

    @Subscribe
    public void handleSessionEnding(ContentEventSessionEnding contentEventSessionEnding) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onSessionEnding();
        }
    }

    @Subscribe
    public void handleFragmentDownloadedEvent(ContentEventFragmentDownloaded contentEventFragmentDownloaded) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            FragmentInformationHolder fragmentInformationHolder = contentEventFragmentDownloaded.getFragmentInformationHolder();
            contentSessionEventListener.onContentFragmentDownloaded(fragmentInformationHolder.isVideo() ? SampleType.VIDEO_SAMPLE : SampleType.AUDIO_SAMPLE, fragmentInformationHolder.getPresentationTimeInNanos(), fragmentInformationHolder.getPresentationTimeInNanos() + fragmentInformationHolder.getDurationInNanos());
        }
    }

    @Subscribe
    public void handleDownloadUpdateEvent(ContentEventDownloadUpdate contentEventDownloadUpdate) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onContentFragmentDownloaded(contentEventDownloadUpdate.getSampleType(), 0L, 0L);
        }
    }

    @Subscribe
    public void handleDownloadedEvent(ContentEventDownloaded contentEventDownloaded) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onDownloadComplete();
        }
    }

    @Subscribe
    public void handleContentMetadataAcquiredEvent(ContentMetadataAcquiredEvent contentMetadataAcquiredEvent) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onContentMetadataAcquired(contentMetadataAcquiredEvent.getDrmAssetId());
        }
    }

    @Subscribe
    public void handleContentEventStartDownloading(ContentEventStartDownloading contentEventStartDownloading) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onDownloadStart(contentEventStartDownloading.getTotalNeededSizeInBytes());
        }
    }

    @Subscribe
    public void handleContentEventSecondaryDownloadEligibilityStateChanged(ContentEventSecondaryDownloadEligibilityStateChanged contentEventSecondaryDownloadEligibilityStateChanged) {
        ContentSessionEventListener contentSessionEventListener = this.mListener;
        if (contentSessionEventListener != null) {
            contentSessionEventListener.onAuxiliaryDownloadEligibilityStateChanged(contentEventSecondaryDownloadEligibilityStateChanged.areSecondaryDownloadsAllowed());
        }
    }
}
