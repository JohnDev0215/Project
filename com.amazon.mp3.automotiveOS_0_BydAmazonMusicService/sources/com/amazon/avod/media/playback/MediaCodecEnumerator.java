package com.amazon.avod.media.playback;

import android.annotation.TargetApi;
import android.media.MediaCodecInfo;
import android.media.MediaCodecList;
import android.util.Pair;
import java.util.List;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(16)
public class MediaCodecEnumerator {
    private static final MediaCodecEnumerator INSTANCE = new MediaCodecEnumerator();

    @Nonnull
    public static MediaCodecEnumerator getInstance() {
        return INSTANCE;
    }

    private MediaCodecEnumerator() {
    }

    @Nullable
    public MediaCodecInfo getSupportedCodec(@Nonnull String str) {
        MediaCodecInfo[] codecInfos = new MediaCodecList(1).getCodecInfos();
        if (codecInfos != null && codecInfos.length != 0) {
            for (MediaCodecInfo mediaCodecInfo : codecInfos) {
                for (String str2 : mediaCodecInfo.getSupportedTypes()) {
                    if (str.equalsIgnoreCase(str2) && !mediaCodecInfo.isEncoder()) {
                        return mediaCodecInfo;
                    }
                }
            }
        }
        return null;
    }

    @Nullable
    public Pair<String, MediaCodecInfo> getSupportedCodec(@Nonnull List<String> list) {
        for (String str : list) {
            MediaCodecInfo supportedCodec = getSupportedCodec(str);
            if (supportedCodec != null) {
                return Pair.create(str, supportedCodec);
            }
        }
        return null;
    }
}
