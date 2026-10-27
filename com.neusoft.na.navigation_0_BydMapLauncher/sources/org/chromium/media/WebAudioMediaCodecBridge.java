package org.chromium.media;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import org.chromium.base.annotations.JNINamespace;

/* JADX INFO: loaded from: classes3.dex */
@JNINamespace("media")
class WebAudioMediaCodecBridge {
    private static final String TAG = "cr.media";
    static final long TIMEOUT_MICROSECONDS = 500;

    private static native void nativeInitializeDestination(long j, int i, int i2, long j2);

    private static native void nativeOnChunkDecoded(long j, ByteBuffer byteBuffer, int i, int i2, int i3);

    WebAudioMediaCodecBridge() {
    }

    private static String createTempFile(Context context) throws IOException {
        return File.createTempFile("webaudio", ".dat", context.getCacheDir()).getAbsolutePath();
    }

    /* JADX WARN: Removed duplicated region for block: B:64:0x011e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static boolean decodeAudioFile(android.content.Context r29, long r30, int r32, long r33) {
        /*
            Method dump skipped, instruction units count: 625
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.WebAudioMediaCodecBridge.decodeAudioFile(android.content.Context, long, int, long):boolean");
    }
}
