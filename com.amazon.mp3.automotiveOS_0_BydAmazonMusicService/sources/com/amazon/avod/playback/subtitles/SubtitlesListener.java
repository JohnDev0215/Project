package com.amazon.avod.playback.subtitles;

import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes2.dex */
public interface SubtitlesListener {
    void onRenderSubtitles(ByteBuffer byteBuffer, long j, long j2, long j3, String str);
}
