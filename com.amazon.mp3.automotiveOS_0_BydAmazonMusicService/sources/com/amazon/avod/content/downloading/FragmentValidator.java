package com.amazon.avod.content.downloading;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.FragmentValidationMode;
import com.amazon.avod.content.smoothstream.Mp4FragmentParser;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingDirectBufferPool;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.google.common.io.ByteStreams;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class FragmentValidator {
    private static final int INITIAL_BUFFER_SIZE_BYTES = (int) DataUnit.KILOBYTES.toBytes(256.0f);
    private final SurgingResourcePool<DirectBuffer> mFragmentValidationBufferPool = new SurgingDirectBufferPool(0, INITIAL_BUFFER_SIZE_BYTES);
    private final FragmentValidationMode mFragmentValidationMode;
    private final Mp4FragmentParser mMp4FragmentParser;

    public FragmentValidator(Mp4FragmentParser mp4FragmentParser, SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig) {
        this.mFragmentValidationMode = smoothStreamingPlaybackConfig.getDownloadValidationMode();
        this.mMp4FragmentParser = mp4FragmentParser;
    }

    public InputStream openValidatedStream(InputStream inputStream, int i, boolean z, GrowableBuffer growableBuffer, @Nullable URL url) throws ContentException {
        DLog.devf("validating fragment with mode %s", this.mFragmentValidationMode.name());
        if (this.mFragmentValidationMode == FragmentValidationMode.NONE) {
            return inputStream;
        }
        DirectBuffer directBufferRequestResource = this.mFragmentValidationBufferPool.requestResource();
        try {
            try {
                growableBuffer.ensureCapacity(i);
                byte[] buffer = growableBuffer.getBuffer();
                ByteStreams.readFully(inputStream, buffer, 0, i);
                if (!this.mMp4FragmentParser.validate(buffer, 0, i, this.mFragmentValidationMode == FragmentValidationMode.HEADER_ONLY, z, directBufferRequestResource)) {
                    throw new ContentException(ContentException.ContentError.CDN_ERROR, "fragment failed validation!", null, url);
                }
                return ByteStreams.limit(new ByteArrayInputStream(buffer), i);
            } catch (Exception e) {
                DLog.warnf("Fragment validation failed with: %s", e);
                throw new ContentException(ContentException.ContentError.CDN_ERROR, "fragment validation threw unexpected exception!", e, url);
            }
        } finally {
            this.mFragmentValidationBufferPool.releaseResource(directBufferRequestResource);
        }
    }

    public boolean validate(ByteBuffer byteBuffer, boolean z) throws ContentException {
        DLog.devf("validating fragment with mode %s", this.mFragmentValidationMode.name());
        return this.mFragmentValidationMode == FragmentValidationMode.NONE || this.mMp4FragmentParser.validate(byteBuffer, z);
    }
}
