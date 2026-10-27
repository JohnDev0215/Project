package com.amazon.avod.content.smoothstream;

import com.amazon.avod.connectivity.NetworkConnectionManager;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.event.ContentEventFragmentConsumed;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.LiveStreamingRestartEvent;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.exceptions.CorruptDataStreamException;
import com.amazon.avod.content.exceptions.StreamSeekOverException;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.urlvending.ClosePlayerEvent;
import com.amazon.avod.content.urlvending.ContentUrl;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.media.framework.memory.DoublingGrowableBuffer;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.memory.SlowGrowingDirectBuffer;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.perf.Profiler;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.threading.ExecutorBuilder;
import com.amazon.avod.threading.Tickers;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import com.google.common.base.Stopwatch;
import com.google.common.collect.Maps;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
class FragmentStream {
    private static final int INITIAL_FRAGMENT_BUFFER_SIZE_BYTES = (int) DataUnit.KILOBYTES.toBytes(256.0f);
    private final ContentAccessor mAccessor;
    private final ContentSessionContext mContext;
    private int mCorruptFragmentChunkIndex;
    private Future<FragmentWrapper> mCurrentFragmentFuture;
    private int mCurrentFragmentIndex;
    private final DrmScheme mDrmScheme;
    private final ContentManagementEventBus mEventBus;
    private ExecutorService mExecutor;
    private final FpsReporterInterface mFpsReporter;
    private final GrowableBuffer mFragmentBuffer;
    private final SurgingResourcePool<DirectBuffer> mFragmentParsingBufferPool;
    private final boolean mFragmentRepairEnabledOnWAN;
    private final Map<SmoothStreamingURI, Fragment> mInitFragmentMap;
    private boolean mIsRestartRequested;
    private final Mp4FragmentParser mMp4FragmentParser;
    private final Object mMutex;
    private final NetworkConnectionManager mNetworkConnectionManager;
    private final boolean mShouldConsumeExactAudioFragment;
    private final StreamIndex mStream;
    private final boolean mUseDashFragmentParserForSmooth;

    @Immutable
    public static class FragmentWrapper {
        private final Fragment mFragment;
        private final Fragment mInitFragment;
        private final SmoothStreamingURI mInitUri;
        private final SmoothStreamingURI mUri;

        private FragmentWrapper(SmoothStreamingURI smoothStreamingURI, Fragment fragment, SmoothStreamingURI smoothStreamingURI2, Fragment fragment2) {
            this.mUri = smoothStreamingURI;
            this.mFragment = fragment;
            this.mInitUri = smoothStreamingURI2;
            this.mInitFragment = fragment2;
        }

        public SmoothStreamingURI getURI() {
            return this.mUri;
        }

        public Fragment getFragment() {
            return this.mFragment;
        }

        public SmoothStreamingURI getInitURI() {
            return this.mInitUri;
        }

        public Fragment getInitFragment() {
            return this.mInitFragment;
        }
    }

    public FragmentStream(ContentSessionContext contentSessionContext, StreamIndex streamIndex, Mp4FragmentParser mp4FragmentParser, ContentAccessor contentAccessor, ContentManagementEventBus contentManagementEventBus, SurgingResourcePool<DirectBuffer> surgingResourcePool, @Nonnull DrmScheme drmScheme) {
        NetworkConnectionManager networkConnectionManager = NetworkConnectionManager.getInstance();
        FpsReporter fpsReporter = new FpsReporter(contentManagementEventBus);
        DoublingGrowableBuffer doublingGrowableBuffer = new DoublingGrowableBuffer(INITIAL_FRAGMENT_BUFFER_SIZE_BYTES);
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        this(contentSessionContext, streamIndex, mp4FragmentParser, contentManagementEventBus, contentAccessor, networkConnectionManager, fpsReporter, doublingGrowableBuffer, smoothStreamingPlaybackConfig.shouldConsumeExactAudioFragment(contentSessionContext.getSpecification().isLiveStream()), smoothStreamingPlaybackConfig.fragmentRepairEnabledOnWAN(), surgingResourcePool, drmScheme, smoothStreamingPlaybackConfig.getUseDashFragmentParserForSmoothStream());
    }

    FragmentStream(ContentSessionContext contentSessionContext, StreamIndex streamIndex, Mp4FragmentParser mp4FragmentParser, ContentManagementEventBus contentManagementEventBus, ContentAccessor contentAccessor, NetworkConnectionManager networkConnectionManager, FpsReporterInterface fpsReporterInterface, GrowableBuffer growableBuffer, boolean z, boolean z2, SurgingResourcePool<DirectBuffer> surgingResourcePool, @Nonnull DrmScheme drmScheme, boolean z3) {
        this.mMutex = new Object();
        this.mInitFragmentMap = Maps.newHashMap();
        this.mCurrentFragmentIndex = Integer.MIN_VALUE;
        this.mCorruptFragmentChunkIndex = Integer.MIN_VALUE;
        this.mIsRestartRequested = false;
        this.mContext = contentSessionContext;
        this.mStream = streamIndex;
        this.mMp4FragmentParser = mp4FragmentParser;
        this.mEventBus = contentManagementEventBus;
        this.mAccessor = contentAccessor;
        this.mNetworkConnectionManager = networkConnectionManager;
        this.mFpsReporter = fpsReporterInterface;
        this.mFragmentBuffer = growableBuffer;
        this.mShouldConsumeExactAudioFragment = z;
        this.mFragmentRepairEnabledOnWAN = z2;
        this.mFragmentParsingBufferPool = surgingResourcePool;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
        this.mUseDashFragmentParserForSmooth = z3;
    }

    public void startAtNanos(long j) {
        startAtNanos(j, ExecutorBuilder.newBuilderFor(this, this.mStream.getType().toString()).withFixedThreadPoolSize(1).withProfilerTraceLevel(Profiler.TraceLevel.DEBUG).build());
    }

    void startAtNanos(long j, ExecutorService executorService) {
        synchronized (this.mMutex) {
            Preconditions.checkState(!isRunning(), "Can't call start() twice!");
            if (this.mStream.getType() == StreamType.SUBTITLES) {
                this.mAccessor.enableStream(this.mStream.getType());
            }
            this.mExecutor = executorService;
            StreamIndex streamIndex = this.mStream;
            SmoothStreamingURI smoothStreamingURI = new SmoothStreamingURI(streamIndex, streamIndex.getChunkIndexFromNanos(j));
            this.mContext.getState().updateConsumptionHead(this.mStream.getIndex(), smoothStreamingURI.getChunkIndex());
            submitFuture(smoothStreamingURI);
        }
    }

    public void stop() {
        synchronized (this.mMutex) {
            if (isRunning()) {
                if (this.mStream.getType() == StreamType.SUBTITLES) {
                    this.mAccessor.disableStream(this.mStream.getType());
                }
                cancelFuture();
                this.mExecutor.shutdown();
                this.mExecutor = null;
                Iterator<Fragment> it = this.mInitFragmentMap.values().iterator();
                while (it.hasNext()) {
                    it.next().release(null);
                }
                this.mInitFragmentMap.clear();
            }
        }
    }

    public void seekToNanos(long j) {
        synchronized (this.mMutex) {
            if (isRunning()) {
                cancelFuture();
                StreamIndex streamIndex = this.mStream;
                SmoothStreamingURI smoothStreamingURI = new SmoothStreamingURI(streamIndex, streamIndex.getChunkIndexFromNanos(j));
                this.mContext.getState().updateConsumptionHead(this.mStream.getIndex(), smoothStreamingURI.getChunkIndex());
                this.mAccessor.seek(j);
                submitFuture(smoothStreamingURI);
            }
        }
    }

    public boolean hasNext() {
        synchronized (this.mMutex) {
            if (!isRunning()) {
                return false;
            }
            if (this.mContext.getSpecification().isLiveStream()) {
                return true;
            }
            return this.mCurrentFragmentFuture != null;
        }
    }

    @Nullable
    public FragmentWrapper readNext() throws ContentException {
        FragmentWrapper futureIfDone;
        synchronized (this.mMutex) {
            Preconditions.checkState(isRunning());
            Preconditions.checkState(hasNext());
            futureIfDone = readFutureIfDone();
            boolean zIsLastPlayableChunk = this.mStream.isLastPlayableChunk(this.mCurrentFragmentIndex);
            if (futureIfDone != null) {
                this.mContext.getState().updateConsumptionHead(this.mStream.getIndex(), futureIfDone.mUri.getChunkIndex());
                this.mEventBus.postEvent(new ContentEventFragmentConsumed(this.mContext.getContent(), this.mContext.getSessionType(), futureIfDone.mUri));
            }
            if (this.mCurrentFragmentFuture == null && !zIsLastPlayableChunk) {
                submitFuture(new SmoothStreamingURI(this.mStream, this.mCurrentFragmentIndex + 1));
            }
            if (futureIfDone == null && this.mContext.getSpecification().isLiveStream()) {
                postCloseIfNecessary(this.mStream, this.mContext.getState().getPlayPositionInNanos(), Boolean.valueOf(this.mContext.getManifest().isDynamic()));
            }
        }
        return futureIfDone;
    }

    private void postCloseIfNecessary(StreamIndex streamIndex, long j, Boolean bool) {
        if (bool == null || bool.booleanValue() || !streamIndex.isLastPlayableChunk(streamIndex.getChunkIndexFromNanos(j))) {
            return;
        }
        DLog.logf("result = null and LastPlayableChunk : %d, chunkCount = %d. Posting close", Integer.valueOf(this.mCurrentFragmentIndex), Integer.valueOf(streamIndex.getNumChunks()));
        this.mEventBus.postEvent(new ClosePlayerEvent());
    }

    private boolean isRunning() {
        Preconditions.checkState(Thread.holdsLock(this.mMutex));
        return this.mExecutor != null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void submitFuture(@Nonnull SmoothStreamingURI smoothStreamingURI) {
        Preconditions.checkState(Thread.holdsLock(this.mMutex));
        Preconditions.checkNotNull(smoothStreamingURI);
        if (this.mExecutor.isShutdown()) {
            return;
        }
        this.mCurrentFragmentFuture = this.mExecutor.submit(new FragmentTask((smoothStreamingURI.isAudio() && this.mShouldConsumeExactAudioFragment) ? smoothStreamingURI.getSmoothStreamingUriForQuality(this.mContext.getStreamSelections().getStartAudioQualityLevel()) : smoothStreamingURI));
        this.mCurrentFragmentIndex = smoothStreamingURI.getChunkIndex();
    }

    private void cancelFuture() {
        Preconditions.checkState(Thread.holdsLock(this.mMutex));
        Future<FragmentWrapper> future = this.mCurrentFragmentFuture;
        if (future != null) {
            future.cancel(true);
            FragmentWrapper futureIfDone = null;
            try {
                futureIfDone = readFutureIfDone();
            } catch (CorruptDataStreamException | StreamSeekOverException unused) {
            }
            if (this.mCorruptFragmentChunkIndex != Integer.MIN_VALUE) {
                restrictDownloaderForCorruptFragment(true, Integer.MIN_VALUE);
            }
            if (futureIfDone != null) {
                futureIfDone.getFragment().release(this.mFragmentParsingBufferPool);
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.amazon.avod.content.smoothstream.FragmentStream.FragmentWrapper readFutureIfDone() throws java.lang.Throwable {
        /*
            r6 = this;
            java.lang.Object r0 = r6.mMutex
            boolean r0 = java.lang.Thread.holdsLock(r0)
            com.google.common.base.Preconditions.checkState(r0)
            java.util.concurrent.Future<com.amazon.avod.content.smoothstream.FragmentStream$FragmentWrapper> r0 = r6.mCurrentFragmentFuture
            r1 = 0
            if (r0 == 0) goto L4d
            boolean r0 = r0.isDone()
            if (r0 != 0) goto L15
            goto L4d
        L15:
            r0 = 1
            r2 = 0
            java.util.concurrent.Future<com.amazon.avod.content.smoothstream.FragmentStream$FragmentWrapper> r3 = r6.mCurrentFragmentFuture     // Catch: java.lang.Throwable -> L22 java.util.concurrent.ExecutionException -> L24 java.util.concurrent.CancellationException -> L48 java.lang.InterruptedException -> L4b
            java.lang.Object r3 = r3.get()     // Catch: java.lang.Throwable -> L22 java.util.concurrent.ExecutionException -> L24 java.util.concurrent.CancellationException -> L48 java.lang.InterruptedException -> L4b
            com.amazon.avod.content.smoothstream.FragmentStream$FragmentWrapper r3 = (com.amazon.avod.content.smoothstream.FragmentStream.FragmentWrapper) r3     // Catch: java.lang.Throwable -> L22 java.util.concurrent.ExecutionException -> L24 java.util.concurrent.CancellationException -> L48 java.lang.InterruptedException -> L4b
            r6.mCurrentFragmentFuture = r1
            return r3
        L22:
            r0 = move-exception
            goto L43
        L24:
            r3 = move-exception
            java.lang.Throwable r3 = r3.getCause()     // Catch: java.lang.Throwable -> L22
            boolean r4 = r3 instanceof com.amazon.avod.content.exceptions.CorruptDataStreamException     // Catch: java.lang.Throwable -> L22
            if (r4 != 0) goto L3c
            boolean r4 = r3 instanceof com.amazon.avod.content.exceptions.StreamSeekOverException     // Catch: java.lang.Throwable -> L22
            if (r4 == 0) goto L34
            com.amazon.avod.content.exceptions.StreamSeekOverException r3 = (com.amazon.avod.content.exceptions.StreamSeekOverException) r3     // Catch: java.lang.Throwable -> L3f
            throw r3     // Catch: java.lang.Throwable -> L3f
        L34:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L22
            java.lang.String r4 = "Callable is not CorruptDataStreamException!"
            r0.<init>(r4, r3)     // Catch: java.lang.Throwable -> L22
            throw r0     // Catch: java.lang.Throwable -> L22
        L3c:
            com.amazon.avod.content.exceptions.CorruptDataStreamException r3 = (com.amazon.avod.content.exceptions.CorruptDataStreamException) r3     // Catch: java.lang.Throwable -> L3f
            throw r3     // Catch: java.lang.Throwable -> L3f
        L3f:
            r2 = move-exception
            r5 = r2
            r2 = r0
            r0 = r5
        L43:
            if (r2 != 0) goto L47
            r6.mCurrentFragmentFuture = r1
        L47:
            throw r0
        L48:
            r6.mCurrentFragmentFuture = r1
            return r1
        L4b:
            r6.mCurrentFragmentFuture = r1
        L4d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.FragmentStream.readFutureIfDone():com.amazon.avod.content.smoothstream.FragmentStream$FragmentWrapper");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void restrictDownloaderForCorruptFragment(boolean z, int i) {
        String str;
        this.mCorruptFragmentChunkIndex = i;
        AtomicInteger concurrentFragmentRepairCount = this.mContext.getState().getConcurrentFragmentRepairCount();
        if (z) {
            concurrentFragmentRepairCount.decrementAndGet();
            if (concurrentFragmentRepairCount.get() > 0) {
                DLog.logf("Another fragment repair is in progress, decrementing count and returning");
                return;
            }
        } else {
            concurrentFragmentRepairCount.incrementAndGet();
        }
        if (this.mCorruptFragmentChunkIndex == Integer.MIN_VALUE) {
            str = "Fragment repaired";
        } else {
            str = "Corrupt fragment chunk index: " + this.mCorruptFragmentChunkIndex;
        }
        DLog.logf("Restricting fragment downloader: %s, %s", Boolean.valueOf(z), str);
        this.mContext.getState().setRestrictPlaybackToBufferedContent(z);
        this.mAccessor.onContentContextChanged();
    }

    @Immutable
    private class FragmentTask implements Callable<FragmentWrapper> {
        private final boolean mIsAudio;
        private final Stopwatch mTimer = Stopwatch.createUnstarted(Tickers.androidTicker());
        private final SmoothStreamingURI mUri;

        public FragmentTask(@Nonnull SmoothStreamingURI smoothStreamingURI) {
            this.mUri = smoothStreamingURI;
            this.mIsAudio = smoothStreamingURI.getStream().isAudio();
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.concurrent.Callable
        public FragmentWrapper call() throws ContentException {
            Fragment fragment;
            SmoothStreamingURI smoothStreamingURI;
            Fragment fragment2;
            Fragment fragment3;
            FragmentStreamRequestResult fragment4 = readFragment(this.mUri);
            SmoothStreamingURI responseURI = null;
            if (fragment4 == null || (fragment = parseFragment(fragment4)) == null) {
                return null;
            }
            SmoothStreamingURI responseURI2 = fragment4.getResponseURI();
            if (FragmentStream.this.mStream.requiresInitFragments()) {
                SmoothStreamingURI initFragmentUri = responseURI2.getInitFragmentUri();
                if (FragmentStream.this.mInitFragmentMap.containsKey(initFragmentUri)) {
                    smoothStreamingURI = initFragmentUri;
                    fragment2 = (Fragment) FragmentStream.this.mInitFragmentMap.get(initFragmentUri);
                } else {
                    FragmentStreamRequestResult fragment5 = readFragment(initFragmentUri);
                    if (fragment5 != null) {
                        responseURI = fragment5.getResponseURI();
                        fragment3 = parseFragment(fragment5);
                        if (fragment3 != null) {
                            FragmentStream.this.mInitFragmentMap.put(initFragmentUri, fragment3);
                        }
                    } else {
                        fragment3 = null;
                    }
                    smoothStreamingURI = responseURI;
                    fragment2 = fragment3;
                }
            } else {
                smoothStreamingURI = null;
                fragment2 = null;
            }
            FragmentStream.this.mFpsReporter.processFragment(FragmentStream.this.mContext, responseURI2, fragment);
            if (FragmentStream.this.mCorruptFragmentChunkIndex != Integer.MIN_VALUE && responseURI2.getChunkIndex() == FragmentStream.this.mCorruptFragmentChunkIndex) {
                FragmentStream.this.restrictDownloaderForCorruptFragment(true, Integer.MIN_VALUE);
            }
            return new FragmentWrapper(responseURI2, fragment, smoothStreamingURI, fragment2);
        }

        @Nullable
        private FragmentStreamRequestResult readFragment(SmoothStreamingURI smoothStreamingURI) throws ContentException {
            FragmentStreamRequestResult fragmentStream;
            this.mTimer.start();
            try {
                try {
                    try {
                        fragmentStream = FragmentStream.this.mAccessor.getFragmentStream(smoothStreamingURI);
                    } catch (IndexOutOfBoundsException e) {
                        DLog.warnf("FragmentStream: falling out of the manifest's edge causing VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE.Self correcting to nearest point within the latest manifest");
                        ContentException.ContentError contentError = ContentException.ContentError.VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE;
                        throw new StreamSeekOverException("Fell out of manifest's edge", contentError, new ContentException(contentError, e), (smoothStreamingURI == null || !smoothStreamingURI.isAudio()) ? StreamType.VIDEO : StreamType.AUDIO, -1L, FragmentStream.this.mContext.getState().resolveToMediaTimeWindowBoundary(FragmentStream.this.mContext.getState().getPlayPositionInNanos(), false));
                    }
                } catch (ContentException e2) {
                    handleReadOrParseFailure(smoothStreamingURI, e2);
                    this.mTimer.stop();
                    fragmentStream = null;
                }
                if (fragmentStream == null) {
                    return null;
                }
                SmoothStreamingURI responseURI = fragmentStream.getResponseURI();
                long jElapsed = this.mTimer.elapsed(TimeUnit.MILLISECONDS);
                if (jElapsed >= 1500) {
                    DLog.logf("Took %s ms to read fragment %s", Long.valueOf(jElapsed), responseURI);
                }
                return fragmentStream;
            } finally {
                this.mTimer.stop();
            }
        }

        @Nullable
        private Fragment parseFragment(FragmentStreamRequestResult fragmentStreamRequestResult) throws CorruptDataStreamException {
            DirectBuffer slowGrowingDirectBuffer;
            Fragment fragment;
            SmoothStreamingURI responseURI = fragmentStreamRequestResult.getResponseURI();
            boolean zIsInitFragment = responseURI.isInitFragment();
            DirectBuffer directBuffer = null;
            try {
                try {
                    this.mTimer.reset().start();
                    ByteBuffer byteBuffer = fragmentStreamRequestResult.getByteBuffer();
                    if ((ContentUrl.isDashUrl(FragmentStream.this.mContext.getContentUrl()) || FragmentStream.this.mUseDashFragmentParserForSmooth) && byteBuffer.isDirect()) {
                        byteBuffer.rewind();
                        fragment = FragmentStream.this.mMp4FragmentParser.parse(byteBuffer, this.mIsAudio);
                        slowGrowingDirectBuffer = null;
                    } else {
                        if (!zIsInitFragment) {
                            slowGrowingDirectBuffer = (DirectBuffer) FragmentStream.this.mFragmentParsingBufferPool.requestResource();
                        } else {
                            slowGrowingDirectBuffer = new SlowGrowingDirectBuffer(FragmentStream.INITIAL_FRAGMENT_BUFFER_SIZE_BYTES);
                        }
                        try {
                            fragment = FragmentStream.this.mMp4FragmentParser.parse(getDataBuffer(fragmentStreamRequestResult), 0, fragmentStreamRequestResult.getFragmentLength(), this.mIsAudio, slowGrowingDirectBuffer);
                        } catch (MediaException e) {
                            e = e;
                            releaseResourceIfNecessary(zIsInitFragment, slowGrowingDirectBuffer);
                            handleReadOrParseFailure(responseURI, e);
                        } catch (IOException e2) {
                            e = e2;
                            directBuffer = slowGrowingDirectBuffer;
                            releaseResourceIfNecessary(zIsInitFragment, directBuffer);
                            throw new CorruptDataStreamException(e.getMessage(), new ContentException(ContentException.ContentError.CDN_ERROR), responseURI, FragmentStream.this.getManifestUrl());
                        }
                    }
                } catch (MediaException e3) {
                    e = e3;
                    slowGrowingDirectBuffer = null;
                } catch (IOException e4) {
                    e = e4;
                }
                if (FragmentStream.this.mContext.getSpecification().isLiveStream() && FragmentStream.this.mContext.getManifest().isEncrypted() && !zIsInitFragment && (responseURI.isAudio() || responseURI.isVideo())) {
                    if (FragmentStream.this.mContext.getState().getKeyRotationState().checkKeyRotated(fragment.getPssh(FragmentStream.this.mDrmScheme == DrmScheme.PLAYREADY), responseURI.isVideo())) {
                        if (responseURI.isVideo() && !FragmentStream.this.mIsRestartRequested) {
                            FragmentStream.this.mIsRestartRequested = true;
                            FragmentStream.this.mEventBus.postEvent(LiveStreamingRestartEvent.newKeyRotationEvent(TimeUnit.MILLISECONDS.convert(TimeSpan.nanosecondsFromTimeScale(fragment.getFragmentPresentationTime(), responseURI.getQualityLevel().getTimeScale()) + responseURI.getDurationInNanos(), TimeUnit.NANOSECONDS)));
                        }
                        releaseResourceIfNecessary(zIsInitFragment, slowGrowingDirectBuffer);
                        DLog.logf("fragment with a new keyid detected uri: %s. Prevent submission to render.", responseURI);
                        return null;
                    }
                }
                this.mTimer.stop();
                long jElapsed = this.mTimer.elapsed(TimeUnit.MILLISECONDS);
                if (jElapsed >= 500) {
                    DLog.logf("Took %s ns to parse fragment %s", Long.valueOf(jElapsed), responseURI);
                }
                return fragment;
            } finally {
                this.mTimer.stop();
            }
        }

        private void releaseResourceIfNecessary(boolean z, DirectBuffer directBuffer) {
            if (z || directBuffer == null) {
                return;
            }
            FragmentStream.this.mFragmentParsingBufferPool.releaseResource(directBuffer);
        }

        private void handleReadOrParseFailure(SmoothStreamingURI smoothStreamingURI, MediaException mediaException) throws CorruptDataStreamException {
            CorruptDataStreamException corruptDataStreamException = new CorruptDataStreamException(String.format(Locale.US, "Unable to read or parse fragment %s (this is a recoverable error)", smoothStreamingURI), mediaException, smoothStreamingURI, FragmentStream.this.getManifestUrl());
            DLog.warnf(corruptDataStreamException.toString());
            if (FragmentStream.this.mNetworkConnectionManager.hasDataConnection()) {
                if (FragmentStream.this.mNetworkConnectionManager.hasWANConnection() && FragmentStream.this.mContext.isDownload()) {
                    if (FragmentStream.this.mContext.getState().isWANStreamingEnabled() && FragmentStream.this.mFragmentRepairEnabledOnWAN) {
                        FragmentStream.this.restrictDownloaderForCorruptFragment(false, smoothStreamingURI.getChunkIndex());
                    } else {
                        DLog.logf("WAN streaming is disabled, cannot repair corrupt or missing Fragment, throwing exception to seek over it!");
                        throw corruptDataStreamException;
                    }
                }
                FragmentStream.this.mEventBus.postEvent(new RetriableContentEventError(FragmentStream.this.mContext.getContent(), FragmentStream.this.mContext.getSessionType(), corruptDataStreamException, null, smoothStreamingURI, 1, null));
                FragmentStream.this.mAccessor.notifyFragmentStreamCorrupt(smoothStreamingURI);
                synchronized (FragmentStream.this.mMutex) {
                    FragmentStream.this.mCurrentFragmentFuture = null;
                    FragmentStream.this.submitFuture(this.mUri);
                }
                return;
            }
            DLog.logf("No data connection, cannot repair corrupt or missing Fragment, throwing exception to seek over it!");
            throw corruptDataStreamException;
        }

        /* JADX WARN: Removed duplicated region for block: B:8:0x0013  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private byte[] getDataBuffer(com.amazon.avod.content.smoothstream.FragmentStreamRequestResult r5) {
            /*
                r4 = this;
                java.nio.ByteBuffer r0 = r5.getByteBuffer()
                r1 = 0
                boolean r2 = r0.hasArray()     // Catch: java.lang.UnsupportedOperationException -> L13
                if (r2 == 0) goto L13
                boolean r2 = r0.isDirect()     // Catch: java.lang.UnsupportedOperationException -> L13
                if (r2 != 0) goto L13
                r2 = 1
                goto L14
            L13:
                r2 = r1
            L14:
                if (r2 == 0) goto L1b
                byte[] r5 = r0.array()
                goto L42
            L1b:
                r0.rewind()
                int r2 = r0.capacity()
                com.amazon.avod.content.smoothstream.FragmentStream r3 = com.amazon.avod.content.smoothstream.FragmentStream.this
                com.amazon.avod.media.framework.memory.GrowableBuffer r3 = com.amazon.avod.content.smoothstream.FragmentStream.access$2200(r3)
                r3.ensureCapacity(r2)
                com.amazon.avod.content.smoothstream.FragmentStream r3 = com.amazon.avod.content.smoothstream.FragmentStream.this
                com.amazon.avod.media.framework.memory.GrowableBuffer r3 = com.amazon.avod.content.smoothstream.FragmentStream.access$2200(r3)
                byte[] r3 = r3.getBuffer()
                r0.get(r3, r1, r2)     // Catch: java.lang.Throwable -> L43
                com.amazon.avod.content.smoothstream.FragmentStream r0 = com.amazon.avod.content.smoothstream.FragmentStream.this
                com.amazon.avod.content.downloading.ContentAccessor r0 = com.amazon.avod.content.smoothstream.FragmentStream.access$800(r0)
                r0.releaseFragment(r5)
                r5 = r3
            L42:
                return r5
            L43:
                r0 = move-exception
                com.amazon.avod.content.smoothstream.FragmentStream r1 = com.amazon.avod.content.smoothstream.FragmentStream.this
                com.amazon.avod.content.downloading.ContentAccessor r1 = com.amazon.avod.content.smoothstream.FragmentStream.access$800(r1)
                r1.releaseFragment(r5)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.FragmentStream.FragmentTask.getDataBuffer(com.amazon.avod.content.smoothstream.FragmentStreamRequestResult):byte[]");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String getManifestUrl() {
        ContentUrlSelector contentUrlSelector = this.mContext.getContentUrlSelector();
        if (contentUrlSelector == null) {
            return null;
        }
        return contentUrlSelector.getCurrentContentUrl().getUrl();
    }
}
