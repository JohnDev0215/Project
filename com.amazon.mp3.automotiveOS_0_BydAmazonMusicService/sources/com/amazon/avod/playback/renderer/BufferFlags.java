package com.amazon.avod.playback.renderer;

/* JADX INFO: loaded from: classes2.dex */
public class BufferFlags {
    private static final int CODEC_CONFIG_FLAG = 128;
    private static final int END_OF_STREAM_FLAG = 1;
    private static final int EXTRA_DATA_PRESENT_FLAG = 64;
    public static final int END_OF_STREAM = generate(false, true, false);
    public static final int CODEC_CONFIG = generate(true, false, false);

    private static int generate(boolean z, boolean z2, boolean z3) {
        int i = z ? 128 : 0;
        if (z2) {
            i |= 1;
        }
        return z3 ? i | 64 : i;
    }

    public static int getFlags(boolean z) {
        return generate(false, false, z);
    }
}
