package com.amazon.avod.media.playback.render;

import android.media.AudioTrack;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class AudioTrackFactory {
    private static final AudioTrackFactory INSTANCE = new AudioTrackFactory();
    private final long mAudioTrackBufferMaxSizeUs;
    private final long mAudioTrackBufferMinSizeUs;
    private final int mAudioTrackBufferMultiplicationFactor;
    private final long mPassThroughAudioTrackBufferSizeUs;
    private final long mTunnelModeAudioTrackBufferSizeUs;
    private final float mTunnelModeAudioTrackSizeScaleFactor;

    @Nonnull
    public static AudioTrackFactory getInstance() {
        return INSTANCE;
    }

    private AudioTrackFactory() {
        MediaDefaultConfiguration mediaDefaultConfiguration = MediaDefaultConfiguration.getInstance();
        this.mAudioTrackBufferMultiplicationFactor = mediaDefaultConfiguration.getAudioTrackBufferMultiplicationFactor();
        this.mPassThroughAudioTrackBufferSizeUs = mediaDefaultConfiguration.getPassThroughAudioTrackBufferSizeUs();
        this.mAudioTrackBufferMinSizeUs = mediaDefaultConfiguration.getAudioTrackBufferMinSizeUs();
        this.mAudioTrackBufferMaxSizeUs = mediaDefaultConfiguration.getAudioTrackBufferMaxSizeUs();
        this.mTunnelModeAudioTrackBufferSizeUs = mediaDefaultConfiguration.getTunnelModeAudioTrackBufferSizeUs();
        this.mTunnelModeAudioTrackSizeScaleFactor = mediaDefaultConfiguration.getTunnelModeAudioTrackSizeScaleFactor();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0041  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0043  */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0052  */
    /* JADX WARN: Removed duplicated region for block: B:24:0x0054  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0087  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ae  */
    @javax.annotation.Nonnull
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.amazon.avod.media.playback.render.AudioTrackWrapper newAudioTrackWrapper(@javax.annotation.Nonnull android.media.MediaFormat r23, boolean r24, @javax.annotation.Nullable java.lang.Integer r25) {
        /*
            r22 = this;
            r0 = r23
            r1 = r25
            java.lang.String r2 = "mime"
            java.lang.String r4 = r0.getString(r2)
            java.lang.String r2 = "sample-rate"
            int r2 = r0.getInteger(r2)
            java.lang.String r3 = "channel-count"
            int r0 = r0.getInteger(r3)
            r3 = 12
            r5 = 6
            r6 = 2
            r12 = 1
            if (r0 == r12) goto L27
            if (r0 == r6) goto L28
            if (r0 == r5) goto L24
            goto L28
        L24:
            r3 = 252(0xfc, float:3.53E-43)
            goto L28
        L27:
            r3 = 4
        L28:
            r13 = r3
            java.lang.String r3 = "audio/ac3"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L34
            r5 = 5
        L32:
            r14 = r5
            goto L3e
        L34:
            java.lang.String r3 = "audio/eac3"
            boolean r3 = r4.equals(r3)
            if (r3 == 0) goto L3d
            goto L32
        L3d:
            r14 = r6
        L3e:
            r15 = 0
            if (r1 == 0) goto L43
            r11 = r12
            goto L44
        L43:
            r11 = r15
        L44:
            r5 = r22
            r6 = r0
            r7 = r2
            r8 = r13
            r9 = r14
            r10 = r24
            int r11 = r5.computeAudioTrackBufferSizeBytes(r6, r7, r8, r9, r10, r11)
            if (r1 == 0) goto L54
            r10 = r12
            goto L55
        L54:
            r10 = r15
        L55:
            r5 = r22
            r6 = r11
            r7 = r0
            r8 = r2
            r9 = r24
            long r18 = r5.computeAudioTrackBufferSizeUs(r6, r7, r8, r9, r10)
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)
            java.lang.Integer r6 = java.lang.Integer.valueOf(r13)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r14)
            java.lang.Integer r8 = java.lang.Integer.valueOf(r11)
            java.lang.Long r9 = java.lang.Long.valueOf(r18)
            java.lang.String r3 = "Creating AudioTrack for %s with channelCount=%d, channelConfig=%d, audioFormat=%d, bufferSizeBytes=%d, bufferSizeUs=%d"
            com.amazon.avod.util.DLog.logf(r3, r4, r5, r6, r7, r8, r9)
            android.media.AudioAttributes$Builder r0 = new android.media.AudioAttributes$Builder
            r0.<init>()
            r0.setUsage(r12)
            r3 = 3
            r0.setContentType(r3)
            if (r1 == 0) goto L91
            java.lang.String r3 = "TunnelMode: setting FLAG_HW_AV_SYNC during audio track creation, audioSessionId %d"
            com.amazon.avod.util.DLog.logf(r3, r1)
            r3 = 16
            r0.setFlags(r3)
        L91:
            android.media.AudioFormat$Builder r3 = new android.media.AudioFormat$Builder
            r3.<init>()
            r3.setEncoding(r14)
            r3.setChannelMask(r13)
            r3.setSampleRate(r2)
            com.amazon.avod.media.playback.render.AudioTrackWrapper r2 = new com.amazon.avod.media.playback.render.AudioTrackWrapper
            android.media.AudioTrack r17 = new android.media.AudioTrack
            android.media.AudioAttributes r6 = r0.build()
            android.media.AudioFormat r7 = r3.build()
            r9 = 1
            if (r1 == 0) goto Lb2
            int r15 = r25.intValue()
        Lb2:
            r10 = r15
            r5 = r17
            r8 = r11
            r5.<init>(r6, r7, r8, r9, r10)
            long r0 = (long) r11
            r16 = r2
            r20 = r0
            r16.<init>(r17, r18, r20)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.media.playback.render.AudioTrackFactory.newAudioTrackWrapper(android.media.MediaFormat, boolean, java.lang.Integer):com.amazon.avod.media.playback.render.AudioTrackWrapper");
    }

    int computeAudioTrackBufferSizeBytes(int i, int i2, int i3, int i4, boolean z, boolean z2) {
        long jDurationUsToBytes;
        long micros;
        if (z) {
            if (z2) {
                micros = ((this.mTunnelModeAudioTrackBufferSizeUs * 80) * 1024) / TimeUnit.SECONDS.toMicros(1L);
            } else {
                micros = ((this.mPassThroughAudioTrackBufferSizeUs * 80) * 1024) / TimeUnit.SECONDS.toMicros(1L);
            }
            return (int) micros;
        }
        int minBufferSize = AudioTrack.getMinBufferSize(i2, i3, i4);
        Preconditions.checkState(minBufferSize != -2);
        int i5 = (z2 ? (int) this.mTunnelModeAudioTrackSizeScaleFactor : this.mAudioTrackBufferMultiplicationFactor) * minBufferSize;
        int i6 = i * 2;
        if (z2) {
            jDurationUsToBytes = durationUsToBytes(this.mTunnelModeAudioTrackBufferSizeUs, i2, i6);
        } else {
            jDurationUsToBytes = durationUsToBytes(this.mAudioTrackBufferMinSizeUs, i2, i6);
        }
        int i7 = (int) jDurationUsToBytes;
        int iMax = (int) Math.max(minBufferSize, durationUsToBytes(this.mAudioTrackBufferMaxSizeUs, i2, i6));
        return i5 < i7 ? i7 : i5 > iMax ? iMax : i5;
    }

    private long durationUsToBytes(long j, int i, int i2) {
        return ((j * ((long) i)) / TimeUnit.SECONDS.toMicros(1L)) * ((long) i2);
    }

    long computeAudioTrackBufferSizeUs(int i, int i2, int i3, boolean z, boolean z2) {
        if (z) {
            return z2 ? this.mTunnelModeAudioTrackBufferSizeUs : this.mPassThroughAudioTrackBufferSizeUs;
        }
        return (((long) (i / (i2 * 2))) * TimeUnit.SECONDS.toMicros(1L)) / ((long) i3);
    }
}
