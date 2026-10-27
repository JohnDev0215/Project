package com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.downloading.progressive.SerializableStreamStatus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.content.smoothstream.quality.defaults.DefaultQualityConfiguration;
import com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore;
import com.amazon.avod.content.smoothstream.streamstate.CachedContentView;
import com.amazon.avod.media.framework.memory.ByteBufferInputStream;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.collect.Range;
import com.google.common.collect.Sets;
import com.google.common.io.Closeables;
import java.io.ByteArrayInputStream;
import java.nio.ByteBuffer;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: classes2.dex */
public class StatFileCheckpointPolicy implements CheckpointPolicy {
    private final SmoothStreamingContentStore mContentStore;
    private ContentSessionContext mContext;
    private final DefaultQualityConfiguration mDefaultQualityConfiguration;
    private final ExecutorService mExecutor;
    private StreamIndex mStreamIndex;
    private int mCheckpointModulus = 1;
    private int mCheckpointCounter = 0;

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public void checkpointInitFragments(StreamType streamType, Set<SmoothStreamingURI> set) {
    }

    public StatFileCheckpointPolicy(SmoothStreamingContentStore smoothStreamingContentStore, ExecutorService executorService, DefaultQualityConfiguration defaultQualityConfiguration) {
        this.mContentStore = smoothStreamingContentStore;
        this.mExecutor = executorService;
        this.mDefaultQualityConfiguration = defaultQualityConfiguration;
    }

    public void setCheckpointModulus(int i) {
        Preconditions.checkState(i > 0, "Invalid checkpoint modulus!");
        this.mCheckpointModulus = i;
    }

    public void initialize(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        this.mContext = contentSessionContext;
        this.mStreamIndex = streamIndex;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public void checkpoint(StreamType streamType, CachedContentView cachedContentView) {
        Preconditions.checkNotNull(cachedContentView);
        if (cachedContentView.isEmpty()) {
            DLog.warnf("(non-fatal) StreamState for stream %s is empty; skipping checkpoint", this.mStreamIndex.getType());
            return;
        }
        Range<Integer> containingRangeIgnoringQuality = cachedContentView.getContainingRangeIgnoringQuality(this.mStreamIndex.getChunkIndexFromNanos(0L));
        if (containingRangeIgnoringQuality == null) {
            DLog.warnf("(non-fatal) StreamState for stream %s does not contain a range with a chunk at timecode 0; skipping checkpoint", this.mStreamIndex.getType());
            return;
        }
        final SerializableStreamStatus serializableStreamStatus = new SerializableStreamStatus(this.mStreamIndex.getNumChunks(), ((Integer) containingRangeIgnoringQuality.upperEndpoint()).intValue());
        int i = this.mCheckpointCounter + 1;
        this.mCheckpointCounter = i;
        if (i >= this.mCheckpointModulus || serializableStreamStatus.getLastDownloaded() + 1 >= serializableStreamStatus.getTotalChunks()) {
            this.mCheckpointCounter = 0;
            this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.StatFileCheckpointPolicy.1
                @Override // java.lang.Runnable
                public void run() throws Throwable {
                    ByteArrayInputStream byteArrayInputStream;
                    Throwable th;
                    ByteArrayInputStream byteArrayInputStream2 = null;
                    try {
                        try {
                            byte[] bArrSerialize = serializableStreamStatus.serialize();
                            int length = bArrSerialize.length;
                            byteArrayInputStream = new ByteArrayInputStream(bArrSerialize);
                            try {
                                StatFileCheckpointPolicy.this.mContentStore.storeStatFile(StatFileCheckpointPolicy.this.mContext, StatFileCheckpointPolicy.this.mStreamIndex, byteArrayInputStream, length);
                                Closeables.closeQuietly(byteArrayInputStream);
                            } catch (ContentException unused) {
                                byteArrayInputStream2 = byteArrayInputStream;
                                DLog.warnf("(non-fatal) Failed to checkpoint streamState for stream %s...", StatFileCheckpointPolicy.this.mStreamIndex.getType());
                                Closeables.closeQuietly(byteArrayInputStream2);
                            } catch (Throwable th2) {
                                th = th2;
                                Closeables.closeQuietly(byteArrayInputStream);
                                throw th;
                            }
                        } catch (Throwable th3) {
                            byteArrayInputStream = byteArrayInputStream2;
                            th = th3;
                        }
                    } catch (ContentException unused2) {
                    }
                }
            });
        }
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public CachedContentView rebuildFromCheckpoint(StreamType streamType) throws Throwable {
        ByteBufferInputStream byteBufferInputStream;
        CachedContentView cachedContentView = new CachedContentView();
        if (this.mContentStore.isStatFileAvailable(this.mContext, this.mStreamIndex)) {
            ByteBuffer byteBuffer = null;
            try {
                ByteBuffer byteBufferLoadStatFile = this.mContentStore.loadStatFile(this.mContext, this.mStreamIndex);
                try {
                    byteBufferInputStream = new ByteBufferInputStream(byteBufferLoadStatFile);
                } catch (ContentException unused) {
                    byteBufferInputStream = null;
                } catch (Throwable th) {
                    th = th;
                    byteBufferInputStream = null;
                }
                try {
                    Range<Integer> rangeClosed = Range.closed(0, Integer.valueOf(SerializableStreamStatus.deserialize(byteBufferInputStream).getLastDownloaded()));
                    DLog.logf("Rebuilding stream %s from checkpointed state %s", this.mStreamIndex.getType(), rangeClosed);
                    cachedContentView.initialize(rangeClosed, this.mDefaultQualityConfiguration.getDefaultQualityLevel(this.mContext, this.mStreamIndex));
                    this.mContentStore.releaseBuffer(this.mContext.getSessionType(), byteBufferLoadStatFile);
                } catch (ContentException unused2) {
                    byteBuffer = byteBufferLoadStatFile;
                    try {
                        DLog.warnf("(non-fatal) Failed to read checkpointed streamState for stream %s...", this.mStreamIndex.getType());
                        this.mContentStore.releaseBuffer(this.mContext.getSessionType(), byteBuffer);
                    } catch (Throwable th2) {
                        th = th2;
                        this.mContentStore.releaseBuffer(this.mContext.getSessionType(), byteBuffer);
                        Closeables.closeQuietly(byteBufferInputStream);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    byteBuffer = byteBufferLoadStatFile;
                    this.mContentStore.releaseBuffer(this.mContext.getSessionType(), byteBuffer);
                    Closeables.closeQuietly(byteBufferInputStream);
                    throw th;
                }
            } catch (ContentException unused3) {
                byteBufferInputStream = null;
            } catch (Throwable th4) {
                th = th4;
                byteBufferInputStream = null;
            }
            Closeables.closeQuietly(byteBufferInputStream);
        }
        return cachedContentView;
    }

    @Override // com.amazon.avod.content.smoothstream.streamstate.checkpointpolicy.CheckpointPolicy
    public Set<SmoothStreamingURI> rebuildInitFragmentsFromCheckpoint(StreamType streamType) {
        return Sets.newHashSet();
    }
}
