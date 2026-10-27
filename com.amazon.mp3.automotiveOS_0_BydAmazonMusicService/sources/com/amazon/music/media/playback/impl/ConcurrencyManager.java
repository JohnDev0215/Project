package com.amazon.music.media.playback.impl;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.concurrency.ConcurrencyException;
import com.amazon.music.media.playback.concurrency.ConcurrencyProvider;
import com.amazon.music.media.playback.concurrency.ConcurrencyResolutionPolicy;
import com.amazon.music.media.playback.concurrency.ConcurrentDeviceInfo;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.media.playback.util.ThreadUtils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class ConcurrencyManager {
    private static final long POSITION_CHANGE_TOLERANCE_MILLIS = TimeUnit.SECONDS.toMillis(30);
    private static final Logger logger = LoggerFactory.getLogger("ConcurrencyManager");
    private MediaItem actualMediaItem;
    private long actualPosition;
    private long actualPositionTime;
    private ConcurrencyProvider concurrencyProvider;
    private String deviceTerminating;
    private String deviceToTerminate;
    private final OnConcurrencyExceptionListener listener;
    private MediaItem reportedMediaItem;
    private long reportedPosition;
    private long reportedPositionTime;
    private ConcurrencyResolutionPolicy resolutionPolicy;
    private boolean updateInProgress;
    private List<ConcurrentDeviceInfo> updateResult;
    private boolean canResolveWithoutInteraction = true;
    private int reportedStatus = 2;
    private int actualStatus = 2;
    private final Runnable updateRunnable = new Runnable() { // from class: com.amazon.music.media.playback.impl.ConcurrencyManager.1
        /* JADX WARN: Removed duplicated region for block: B:22:0x00ae  */
        @Override // java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void run() {
            /*
                Method dump skipped, instruction units count: 212
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.media.playback.impl.ConcurrencyManager.AnonymousClass1.run():void");
        }
    };
    private final Runnable postUpdateRunnable = new Runnable() { // from class: com.amazon.music.media.playback.impl.ConcurrencyManager.2
        @Override // java.lang.Runnable
        public void run() {
            ConcurrentDeviceInfo concurrentDeviceInfoAutoResolve;
            ConcurrencyManager.this.updateInProgress = false;
            if (ConcurrencyManager.this.updateResult != null) {
                ConcurrencyManager.this.reportedStatus = 2;
            }
            if (ConcurrencyManager.this.updateResult != null && ConcurrencyManager.this.canResolveWithoutInteraction && ConcurrencyManager.this.deviceToTerminate == null && ConcurrencyManager.this.resolutionPolicy != null && (concurrentDeviceInfoAutoResolve = ConcurrencyManager.this.resolutionPolicy.autoResolve(ConcurrencyManager.this.updateResult)) != null) {
                ConcurrencyManager.this.deviceToTerminate = concurrentDeviceInfoAutoResolve.getName();
            }
            if (ConcurrencyManager.this.updateResult != null && ConcurrencyManager.this.deviceToTerminate == null) {
                ConcurrencyException concurrencyException = new ConcurrencyException(ConcurrencyManager.this.updateResult, ConcurrencyManager.this.canResolveWithoutInteraction);
                if (ConcurrencyManager.this.actualStatus == 0) {
                    ConcurrencyManager.this.actualStatus = 1;
                }
                ConcurrencyManager.this.listener.onConcurrencyException(concurrencyException);
            }
            ConcurrencyManager.this.updateIfNeeded();
        }
    };

    public interface OnConcurrencyExceptionListener {
        @MainThread
        void onConcurrencyException(ConcurrencyException concurrencyException);
    }

    public ConcurrencyManager(@NonNull OnConcurrencyExceptionListener onConcurrencyExceptionListener) {
        this.listener = (OnConcurrencyExceptionListener) Validate.notNull(onConcurrencyExceptionListener);
    }

    public void setConcurrencyProvider(ConcurrencyProvider concurrencyProvider) {
        if (this.concurrencyProvider != concurrencyProvider) {
            this.concurrencyProvider = concurrencyProvider;
            updateIfNeeded();
        }
    }

    @MainThread
    public void autoResolveNextConcurrencyCheck() {
        logger.debug("Setting device to terminate");
        this.deviceToTerminate = "";
        updateIfNeeded();
    }

    public void setConcurrencyResolutionPolicy(ConcurrencyResolutionPolicy concurrencyResolutionPolicy) {
        this.resolutionPolicy = concurrencyResolutionPolicy;
    }

    public void onQualifyingInteraction() {
        this.canResolveWithoutInteraction = true;
    }

    @MainThread
    public void updatePlayStatus(PlayStatus playStatus, MediaItem mediaItem, long j, String str) {
        this.actualStatus = getConcurrencyStatus(playStatus, mediaItem);
        this.actualMediaItem = mediaItem;
        this.actualPosition = j;
        this.actualPositionTime = Clock.now();
        if (str != null) {
            this.deviceToTerminate = str;
        }
        updateIfNeeded();
    }

    private int getConcurrencyStatus(PlayStatus playStatus, MediaItem mediaItem) {
        if (playStatus.willPlay()) {
            return 0;
        }
        return mediaItem == null ? 2 : 1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIfNeeded() {
        if (this.concurrencyProvider == null || this.updateInProgress) {
            return;
        }
        int i = this.actualStatus;
        int i2 = this.reportedStatus;
        if (i != i2 || i == 0) {
            if (i == 1 && i2 == 2) {
                return;
            }
            MediaItem mediaItem = this.actualMediaItem;
            if (mediaItem == null && i2 == 0) {
                return;
            }
            if (mediaItem == this.reportedMediaItem && i == i2 && this.deviceToTerminate == null && Math.abs(((this.reportedPosition - this.actualPosition) + this.actualPositionTime) - this.reportedPositionTime) <= POSITION_CHANGE_TOLERANCE_MILLIS) {
                return;
            }
            this.updateInProgress = true;
            this.deviceTerminating = this.deviceToTerminate;
            this.deviceToTerminate = null;
            this.reportedStatus = this.actualStatus;
            this.reportedMediaItem = this.actualMediaItem;
            this.reportedPosition = this.actualPosition;
            this.reportedPositionTime = this.actualPositionTime;
            if (this.concurrencyProvider.shouldUpdateInBackground()) {
                ThreadUtils.postInBackground(this.updateRunnable);
            } else {
                this.updateRunnable.run();
            }
        }
    }
}
