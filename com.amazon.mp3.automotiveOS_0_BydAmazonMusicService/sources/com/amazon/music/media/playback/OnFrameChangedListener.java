package com.amazon.music.media.playback;

import com.amazon.digitalmusicplayback.AudioFormat;
import java.nio.ByteBuffer;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
public interface OnFrameChangedListener {
    void frameReady(int i, int i2, AudioFormat audioFormat, ArrayList<ByteBuffer> arrayList);
}
