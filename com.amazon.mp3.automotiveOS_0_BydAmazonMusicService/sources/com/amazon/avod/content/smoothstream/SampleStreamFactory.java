package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.downloading.ContentAccessor;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.media.framework.resources.SurgingDirectBufferPool;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DataUnit;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SampleStreamFactory {
    private static final int INITIAL_BUFFER_SIZE_AUDIO_BYTES;
    private static final int INITIAL_BUFFER_SIZE_SUBTITLE_BYTES;
    private static final int INITIAL_BUFFER_SIZE_VIDEO_BYTES;
    private final ContentManagementEventBus mContentTransport;
    private final DrmScheme mDrmScheme;
    private final Mp4FragmentParser mMp4FragmentParser;
    private final SampleStreamState mSampleStreamState = new SampleStreamState();
    private final SurgingResourcePool<DirectBuffer> mAudioFragmentParsingBufferPool = new SurgingDirectBufferPool(2, INITIAL_BUFFER_SIZE_AUDIO_BYTES);
    private final SurgingResourcePool<DirectBuffer> mVideoFragmentParsingBufferPool = new SurgingDirectBufferPool(2, INITIAL_BUFFER_SIZE_VIDEO_BYTES);
    private final SurgingResourcePool<DirectBuffer> mSubtitleFragmentParsingBufferPool = new SurgingDirectBufferPool(2, INITIAL_BUFFER_SIZE_SUBTITLE_BYTES);

    static {
        DataUnit dataUnit = DataUnit.KILOBYTES;
        INITIAL_BUFFER_SIZE_AUDIO_BYTES = (int) dataUnit.toBytes(128.0f);
        INITIAL_BUFFER_SIZE_VIDEO_BYTES = (int) dataUnit.toBytes(256.0f);
        INITIAL_BUFFER_SIZE_SUBTITLE_BYTES = (int) dataUnit.toBytes(64.0f);
    }

    public SampleStreamFactory(Mp4FragmentParser mp4FragmentParser, ContentManagementEventBus contentManagementEventBus, @Nonnull DrmScheme drmScheme) {
        this.mMp4FragmentParser = mp4FragmentParser;
        this.mContentTransport = contentManagementEventBus;
        this.mDrmScheme = (DrmScheme) Preconditions.checkNotNull(drmScheme, "drmScheme");
    }

    public SampleStream newAudioStream(ContentSessionContext contentSessionContext, ContentAccessor contentAccessor) {
        return new SampleStream(contentSessionContext, contentSessionContext.getStreamSelections().getAudioStream(), this.mMp4FragmentParser, contentAccessor, new AudioCodecData(), this.mContentTransport, this.mAudioFragmentParsingBufferPool, this.mDrmScheme, this.mSampleStreamState);
    }

    public SampleStream newVideoStream(ContentSessionContext contentSessionContext, ContentAccessor contentAccessor) {
        StreamIndex videoStream = contentSessionContext.getStreamSelections().getVideoStream();
        return new SampleStream(contentSessionContext, videoStream, this.mMp4FragmentParser, contentAccessor, new VideoCodecData(videoStream.getFourCC()), this.mContentTransport, this.mVideoFragmentParsingBufferPool, this.mDrmScheme, this.mSampleStreamState);
    }

    @Nullable
    public SampleStream newSubtitleStream(ContentSessionContext contentSessionContext, ContentAccessor contentAccessor) {
        StreamIndex subtitleStream = contentSessionContext.getStreamSelections().getSubtitleStream();
        if (subtitleStream == null) {
            return null;
        }
        return new SampleStream(contentSessionContext, subtitleStream, this.mMp4FragmentParser, contentAccessor, new SubtitleCodecData(), this.mContentTransport, this.mSubtitleFragmentParsingBufferPool, this.mDrmScheme, this.mSampleStreamState);
    }
}
