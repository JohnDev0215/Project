package com.amazon.avod.playback.renderer.mediacodec;

import java.util.concurrent.Callable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class MediaCodecSanityEvaluator {
    private static final Double MIN_FPS = Double.valueOf(23.97d);

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:70:0x020c  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x0212  */
    /* JADX WARN: Type inference failed for: r10v19 */
    /* JADX WARN: Type inference failed for: r10v20 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r9v12 */
    /* JADX WARN: Type inference failed for: r9v14 */
    /* JADX WARN: Type inference failed for: r9v15 */
    /* JADX WARN: Type inference failed for: r9v16 */
    /* JADX WARN: Type inference failed for: r9v17 */
    /* JADX WARN: Type inference failed for: r9v18 */
    /* JADX WARN: Type inference failed for: r9v2, types: [com.amazon.avod.playback.renderer.mediacodec.MediaCodecSanityEvaluator$1] */
    /* JADX WARN: Type inference failed for: r9v3 */
    /* JADX WARN: Type inference failed for: r9v4, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARN: Type inference failed for: r9v5 */
    /* JADX WARN: Type inference failed for: r9v6, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARN: Type inference failed for: r9v7, types: [java.util.concurrent.ExecutorService] */
    /* JADX WARN: Type inference failed for: r9v8 */
    /* JADX WARN: Type inference failed for: r9v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static boolean evaluateDecoderSanity(@javax.annotation.Nonnull java.lang.String r19, boolean r20, boolean r21, @javax.annotation.Nonnull android.content.Context r22) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 534
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.playback.renderer.mediacodec.MediaCodecSanityEvaluator.evaluateDecoderSanity(java.lang.String, boolean, boolean, android.content.Context):boolean");
    }

    private static class EncodeDecodeSanityTask implements Callable<Void> {
        private final String mMimeType;
        private final boolean mRequiresHdSupport;

        private EncodeDecodeSanityTask(@Nonnull String str, boolean z) {
            this.mMimeType = str;
            this.mRequiresHdSupport = z;
        }

        @Override // java.util.concurrent.Callable
        @Nullable
        public Void call() throws Throwable {
            new EncodeDecodeSanityEvaluator().testEncodeDecodeVideoFromBufferToBuffer(this.mMimeType, this.mRequiresHdSupport);
            return null;
        }
    }
}
