package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.smoothstream.manifest.ProtectionHeader;
import com.amazon.avod.content.smoothstream.manifest.RefreshableManifest;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.playback.drm.DrmScheme;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.io.BaseEncoding;
import java.nio.ByteBuffer;
import java.util.Arrays;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class StreamingUtils {
    public static long resolveTimeToVideoStreamBoundary(long j, @Nonnull StreamIndex streamIndex, @Nonnull StreamIndex streamIndex2) throws ContentException {
        Preconditions.checkNotNull(streamIndex, "videoStreamIndex");
        Preconditions.checkNotNull(streamIndex2, "audioStreamIndex");
        try {
            int chunkIndexFromNanos = streamIndex.getChunkIndexFromNanos(j);
            long chunkTimeInNanos = streamIndex.getChunkTimeInNanos(chunkIndexFromNanos);
            if (SmoothStreamingPlaybackConfig.INSTANCE.shouldCorrectAudioStreamBoundaries()) {
                while (chunkTimeInNanos < streamIndex2.getStreamStartTimestampNanos()) {
                    DLog.devf("StreamingUtils: incrementing chunkindex due to audio stream start out of bounds");
                    chunkIndexFromNanos++;
                    chunkTimeInNanos = streamIndex.getChunkTimeInNanos(chunkIndexFromNanos);
                }
                while (streamIndex2.getChunkTimeInNanos(streamIndex2.getChunkIndexFromNanos(chunkTimeInNanos)) < streamIndex.getStreamStartTimestampNanos()) {
                    DLog.devf("StreamingUtils: incrementing chunkindex due to video stream start out of bounds");
                    chunkIndexFromNanos++;
                    chunkTimeInNanos = streamIndex.getChunkTimeInNanos(chunkIndexFromNanos);
                }
                while (streamIndex.getChunkTimeInNanos(chunkIndexFromNanos) > streamIndex2.getStreamEndTimestampNanos()) {
                    DLog.devf("StreamingUtils: decrementing chunkindex due to audio stream start out of bounds");
                    chunkIndexFromNanos--;
                    chunkTimeInNanos = streamIndex.getChunkTimeInNanos(chunkIndexFromNanos);
                }
            }
            return chunkTimeInNanos;
        } catch (IndexOutOfBoundsException e) {
            throw new ContentException(ContentException.ContentError.VIDEOSTREAM_BOUNDARY_NOT_IN_RANGE, e.getMessage() == null ? " " : e.getMessage());
        }
    }

    public static byte[] getPssh(byte[] bArr) {
        return Arrays.copyOfRange(bArr, bArr[8] != 0 ? (ByteBuffer.wrap(bArr).getInt(28) * 16) + 28 + 4 + 4 : 32, bArr.length);
    }

    public static ProtectionHeader getPsshPayloadFromManifest(RefreshableManifest refreshableManifest) {
        final ProtectionHeader protectionHeader = refreshableManifest.getProtectionHeader();
        try {
            byte[] bArrDecode = BaseEncoding.base64().decode(protectionHeader.getBase64EncodedData());
            if (isAmazonProprietary(bArrDecode)) {
                DLog.devf("amazon propreitary pssh");
                return protectionHeader;
            }
            final String strEncode = BaseEncoding.base64().encode(getPssh(bArrDecode));
            DLog.devf("Pssh Payload after adjusting the pssh box from manifest %s", strEncode);
            ProtectionHeader protectionHeader2 = new ProtectionHeader() { // from class: com.amazon.avod.content.smoothstream.StreamingUtils.1
                @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
                @Nonnull
                public String getBase64EncodedData() {
                    return strEncode;
                }

                @Override // com.amazon.avod.content.smoothstream.manifest.ProtectionHeader
                @Nonnull
                public DrmScheme getDrmScheme() {
                    return protectionHeader.getDrmScheme();
                }
            };
            DLog.devf("Dash Compliant full pssh box");
            return protectionHeader2;
        } catch (ArrayIndexOutOfBoundsException | IllegalArgumentException | NullPointerException unused) {
            return protectionHeader;
        }
    }

    public static boolean isAmazonProprietary(byte[] bArr) {
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        int length = bArr.length;
        return (length >= 8 && bArr[4] == 112 && bArr[5] == 115 && bArr[6] == 115 && bArr[7] == 104 && length == byteBufferWrap.getInt(0)) ? false : true;
    }
}
