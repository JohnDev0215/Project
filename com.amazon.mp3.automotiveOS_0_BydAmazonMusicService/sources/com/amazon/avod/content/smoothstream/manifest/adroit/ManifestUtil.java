package com.amazon.avod.content.smoothstream.manifest.adroit;

import com.amazon.avod.content.smoothstream.manifest.StreamType;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.smoothstream.Chunk;
import com.amazon.avod.playback.smoothstream.Manifest;
import com.amazon.avod.playback.smoothstream.QualityLevel;
import com.amazon.avod.playback.smoothstream.StreamIndex;
import com.amazon.avod.playback.smoothstream.VideoQualityLevel;
import java.util.Locale;

/* JADX INFO: loaded from: classes2.dex */
class ManifestUtil {
    ManifestUtil() {
    }

    public static TimeSpan getDuration(Manifest manifest) {
        return new TimeSpan(manifest.getDuration(), manifest.getTimeScale());
    }

    public static boolean isLastChunk(StreamIndex streamIndex, int i) {
        return streamIndex.getChunks().length - 1 == i;
    }

    public static int getChunkIndex(Manifest manifest, StreamIndex streamIndex, long j) {
        Chunk[] chunks = streamIndex.getChunks();
        long timeScale = manifest.getTimeScale();
        int length = chunks.length - 1;
        int i = 0;
        while (i <= length) {
            int i2 = (i + length) >>> 1;
            Chunk chunk = chunks[i2];
            long jNanosecondsFromTimeScale = TimeSpan.nanosecondsFromTimeScale(chunk.getTimeStamp(), timeScale);
            if (TimeSpan.nanosecondsFromTimeScale(chunk.getDuration(), timeScale) + jNanosecondsFromTimeScale <= j) {
                i = i2 + 1;
            } else {
                if (j >= jNanosecondsFromTimeScale) {
                    return i2;
                }
                length = i2 - 1;
            }
        }
        return chunks.length - 1;
    }

    public static long getChunksDurationInNanos(Manifest manifest, StreamIndex streamIndex, int i, int i2) {
        Chunk[] chunks = streamIndex.getChunks();
        if (chunks.length <= i2) {
            throw new IllegalArgumentException(String.format(Locale.US, "toChunkIndex(%d length: %d) is OOB", Integer.valueOf(i2), Integer.valueOf(chunks.length)));
        }
        long duration = 0;
        while (i <= i2) {
            duration += chunks[i].getDuration();
            i++;
        }
        return TimeSpan.nanosecondsFromTimeScale(duration, manifest.getTimeScale());
    }

    public static Chunk getChunkByIndex(StreamIndex streamIndex, int i) {
        Chunk[] chunks = streamIndex.getChunks();
        if (i < 0 || i >= chunks.length) {
            throw new IndexOutOfBoundsException();
        }
        return chunks[i];
    }

    public static long getChunkTimeStampInNanos(Manifest manifest, StreamIndex streamIndex, int i) {
        return TimeSpan.nanosecondsFromTimeScale(getChunkByIndex(streamIndex, i).getTimeStamp(), manifest.getTimeScale());
    }

    public static boolean isHDQuality(VideoQualityLevel videoQualityLevel) {
        return videoQualityLevel.getMaxHeight() >= 720 || videoQualityLevel.getMaxWidth() >= 1280;
    }

    public static int findClosestQualityIndex(QualityLevel[] qualityLevelArr, int i) {
        int length = qualityLevelArr.length;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < qualityLevelArr.length; i3++) {
            int iAbs = Math.abs(qualityLevelArr[i3].getBitrate() - i);
            if (iAbs < i2) {
                length = i3;
                i2 = iAbs;
            }
        }
        return length;
    }

    public static int getQualityLevelIndexLessThanEqual(QualityLevel[] qualityLevelArr, int i) {
        int i2 = -1;
        int i3 = Integer.MIN_VALUE;
        int i4 = Integer.MAX_VALUE;
        int i5 = -1;
        for (int i6 = 0; i6 < qualityLevelArr.length; i6++) {
            int bitrate = qualityLevelArr[i6].getBitrate();
            if (bitrate < i4) {
                i5 = i6;
                i4 = bitrate;
            }
            if (bitrate <= i && bitrate > i3) {
                i2 = i6;
                i3 = bitrate;
            }
        }
        return i2 < 0 ? i5 : i2;
    }

    public static int getQualityLevelIndexGreaterThanEqual(QualityLevel[] qualityLevelArr, int i) {
        int i2 = -1;
        int i3 = Integer.MAX_VALUE;
        int i4 = Integer.MIN_VALUE;
        int i5 = -1;
        for (int i6 = 0; i6 < qualityLevelArr.length; i6++) {
            int bitrate = qualityLevelArr[i6].getBitrate();
            if (bitrate > i4) {
                i5 = i6;
                i4 = bitrate;
            }
            if (bitrate >= i && bitrate < i3) {
                i2 = i6;
                i3 = bitrate;
            }
        }
        return i2 < 0 ? i5 : i2;
    }

    public static int getVideoStreamIndex(Manifest manifest) {
        return getStreamIndex(manifest, StreamType.VIDEO);
    }

    public static int getStreamIndex(Manifest manifest, StreamType streamType) {
        if (manifest == null) {
            throw new IllegalArgumentException();
        }
        if (manifest.getAvailableStreams() == null) {
            return -1;
        }
        for (int i = 0; i < manifest.getAvailableStreams().length; i++) {
            StreamIndex streamIndex = manifest.getAvailableStreams()[i];
            if (streamIndex.getType() != null && streamType == StreamType.fromString(streamIndex.getType())) {
                return i;
            }
        }
        return -1;
    }
}
