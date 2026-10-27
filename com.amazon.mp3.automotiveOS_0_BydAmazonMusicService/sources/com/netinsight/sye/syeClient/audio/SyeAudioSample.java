package com.netinsight.sye.syeClient.audio;

import a.b.a.a.k.e.a;
import com.visualon.OSMPUtils.voMimeTypes;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\t\u0018\u0000 '2\u00020\u0001:\u0001\u0017B7\u0012\u0006\u0010\u000b\u001a\u00020\b\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0015\u001a\u00020\u0010\u0012\u0006\u0010$\u001a\u00020\u001f¢\u0006\u0004\b%\u0010&R\u0019\u0010\u0007\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\u000b\u001a\u00020\b8\u0006@\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000f\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\r\u0010\u0004\u001a\u0004\b\u000e\u0010\u0006R\u0019\u0010\u0015\u001a\u00020\u00108\u0006@\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014R\u0019\u0010\u001b\u001a\u00020\u00168\u0006@\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001e\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0004\u001a\u0004\b\u001d\u0010\u0006R\u0019\u0010$\u001a\u00020\u001f8\u0006@\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#¨\u0006("}, d2 = {"Lcom/netinsight/sye/syeClient/audio/SyeAudioSample;", "", "", "c", "I", "getCodec", "()I", "codec", "", "b", "Z", "isDiscontinuity", "()Z", "e", "getSampleRates", "sampleRates", "", "f", "J", "getLocalTimeMicros", "()J", "localTimeMicros", "La/b/a/a/k/e/a;", "a", "La/b/a/a/k/e/a;", "getAudioCodec", "()La/b/a/a/k/e/a;", "audioCodec", "d", "getNumChannels", "numChannels", "", "g", "[B", "getData", "()[B", "data", "<init>", "(ZIIIJ[B)V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeAudioSample {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final a audioCodec;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final boolean isDiscontinuity;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public final int codec;

    /* JADX INFO: renamed from: d, reason: from kotlin metadata */
    public final int numChannels;

    /* JADX INFO: renamed from: e, reason: from kotlin metadata */
    public final int sampleRates;

    /* JADX INFO: renamed from: f, reason: from kotlin metadata */
    public final long localTimeMicros;

    /* JADX INFO: renamed from: g, reason: from kotlin metadata */
    public final byte[] data;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.audio.SyeAudioSample$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final int a(SyeAudioSample syeAudioSample) {
            int iOrdinal = syeAudioSample.getAudioCodec().ordinal();
            if (iOrdinal != 1) {
                return iOrdinal != 2 ? 1 : 6;
            }
            return 2;
        }

        @JvmStatic
        public final String b(SyeAudioSample syeAudioSample) {
            int iOrdinal = syeAudioSample.getAudioCodec().ordinal();
            if (iOrdinal == 1) {
                return voMimeTypes.VOAUDIO_AAC;
            }
            if (iOrdinal != 2) {
                return null;
            }
            return voMimeTypes.VOAUDIO_RAW;
        }
    }

    public SyeAudioSample(boolean z, int i, int i2, int i3, long j, byte[] bArr) {
        a aVar;
        this.isDiscontinuity = z;
        this.codec = i;
        this.numChannels = i2;
        this.sampleRates = i3;
        this.localTimeMicros = j;
        this.data = bArr;
        if (i == 0) {
            aVar = a.Undefined;
        } else if (i == 1) {
            aVar = a.AAC;
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("No AudioCodec with value " + i);
            }
            aVar = a.EC3;
        }
        this.audioCodec = aVar;
    }

    @JvmStatic
    public static final int getAudioFormat(SyeAudioSample syeAudioSample) {
        return INSTANCE.a(syeAudioSample);
    }

    @JvmStatic
    public static final String getMimeType(SyeAudioSample syeAudioSample) {
        return INSTANCE.b(syeAudioSample);
    }

    public final a getAudioCodec() {
        return this.audioCodec;
    }

    public final int getCodec() {
        return this.codec;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final long getLocalTimeMicros() {
        return this.localTimeMicros;
    }

    public final int getNumChannels() {
        return this.numChannels;
    }

    public final int getSampleRates() {
        return this.sampleRates;
    }

    /* JADX INFO: renamed from: isDiscontinuity, reason: from getter */
    public final boolean getIsDiscontinuity() {
        return this.isDiscontinuity;
    }
}
