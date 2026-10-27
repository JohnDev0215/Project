package com.amazon.avod.media;

import com.amazon.avod.media.playback.support.ConfigurablePlaybackSupportEvaluator;
import com.google.common.base.Preconditions;
import kotlin.Metadata;

/* JADX INFO: compiled from: VideoStreamType.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u000f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\n¨\u0006\u000b"}, d2 = {"Lcom/amazon/avod/media/VideoStreamType;", "", "fourCC", "", "(Ljava/lang/String;ILjava/lang/String;)V", "getFourCC", "()Ljava/lang/String;", ConfigurablePlaybackSupportEvaluator.PRS_AVC_OVERRIDE, ConfigurablePlaybackSupportEvaluator.PRS_HEVC_OVERRIDE, "DVHE", "AV01", "ATVPlaybackCore_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public enum VideoStreamType {
    H264("AVC1"),
    H265("HEV1"),
    DVHE("DVHE"),
    AV01("AV01");

    private final String fourCC;

    VideoStreamType(String str) {
        this.fourCC = str;
        Preconditions.checkArgument(str.length() == 4, "fourCC must be 4 characters long", new Object[0]);
    }

    public final String getFourCC() {
        return this.fourCC;
    }
}
