package com.netinsight.sye.syeClient.video;

import a.b.a.a.k.e.c;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\u0012\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u000f\u0018\u0000 H2\u00020\u0001:\u0001)Bw\u0012\u0006\u0010\u0013\u001a\u00020\r\u0012\u0006\u00109\u001a\u00020\u0002\u0012\u0006\u0010\u001b\u001a\u00020\u0002\u0012\u0006\u00103\u001a\u00020\u0002\u0012\u0006\u00100\u001a\u00020\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002\u0012\u0006\u0010\u0010\u001a\u00020\r\u0012\u0006\u0010?\u001a\u00020:\u0012\u0006\u0010E\u001a\u00020\u001f\u0012\u0006\u00106\u001a\u00020\u001f\u0012\u0006\u0010'\u001a\u00020\u001f\u0012\u0006\u0010$\u001a\u00020\u001f\u0012\u0006\u0010B\u001a\u00020\r\u0012\u0006\u0010\u0016\u001a\u00020\r¢\u0006\u0004\bF\u0010GJ\u001f\u0010\b\u001a\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\f\u001a\u0004\u0018\u00010\tH\u0000¢\u0006\u0004\b\n\u0010\u000bR\u0019\u0010\u0010\u001a\u00020\r8\u0006@\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0019\u0010\u0013\u001a\u00020\r8\u0006@\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u000f\u001a\u0004\b\u0013\u0010\u0011R\u0019\u0010\u0016\u001a\u00020\r8\u0006@\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u000f\u001a\u0004\b\u0015\u0010\u0011R\u0019\u0010\u001b\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0019\u0010\u001e\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u0018\u001a\u0004\b\u001d\u0010\u001aR\u0019\u0010$\u001a\u00020\u001f8\u0006@\u0006¢\u0006\f\n\u0004\b \u0010!\u001a\u0004\b\"\u0010#R\u0019\u0010'\u001a\u00020\u001f8\u0006@\u0006¢\u0006\f\n\u0004\b%\u0010!\u001a\u0004\b&\u0010#R\u0019\u0010-\u001a\u00020(8\u0006@\u0006¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\u0019\u00100\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b.\u0010\u0018\u001a\u0004\b/\u0010\u001aR\u0019\u00103\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b1\u0010\u0018\u001a\u0004\b2\u0010\u001aR\u0019\u00106\u001a\u00020\u001f8\u0006@\u0006¢\u0006\f\n\u0004\b4\u0010!\u001a\u0004\b5\u0010#R\u0019\u00109\u001a\u00020\u00028\u0006@\u0006¢\u0006\f\n\u0004\b7\u0010\u0018\u001a\u0004\b8\u0010\u001aR\u0019\u0010?\u001a\u00020:8\u0006@\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u0019\u0010B\u001a\u00020\r8\u0006@\u0006¢\u0006\f\n\u0004\b@\u0010\u000f\u001a\u0004\bA\u0010\u0011R\u0019\u0010E\u001a\u00020\u001f8\u0006@\u0006¢\u0006\f\n\u0004\bC\u0010!\u001a\u0004\bD\u0010#¨\u0006I"}, d2 = {"Lcom/netinsight/sye/syeClient/video/SyeVideoSample;", "", "", "expectedMaxWidth", "expectedMaxHeight", "La/b/a/a/o/f/a;", "createMediaFormatWithExpectedMaxResolution$syeClient_release", "(II)La/b/a/a/o/f/a;", "createMediaFormatWithExpectedMaxResolution", "", "getAndroidMimeType$syeClient_release", "()Ljava/lang/String;", "getAndroidMimeType", "", "h", "Z", "isIDRFrame", "()Z", "b", "isDiscontinuity", "o", "getDisplayImmediately", "displayImmediately", "d", "I", "getWidth", "()I", "width", "g", "getFramerateDen", "framerateDen", "", "m", "[B", "getPps", "()[B", "pps", "l", "getSps", "sps", "La/b/a/a/k/e/c;", "a", "La/b/a/a/k/e/c;", "getVideoCodec", "()La/b/a/a/k/e/c;", "videoCodec", "f", "getFramerateNum", "framerateNum", "e", "getHeight", "height", "k", "getVps", "vps", "c", "getCodec", "codec", "", "i", "J", "getLocalTimeMicros", "()J", "localTimeMicros", "n", "getDecodeOnly", "decodeOnly", "j", "getData", "data", "<init>", "(ZIIIIIZJ[B[B[B[BZZ)V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeVideoSample {
    public static final String ANDROID_MIME_TYPE_H264 = "video/avc";
    public static final String ANDROID_MIME_TYPE_HEVC = "video/hevc";

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final c videoCodec;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final boolean isDiscontinuity;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public final int codec;

    /* JADX INFO: renamed from: d, reason: from kotlin metadata */
    public final int width;

    /* JADX INFO: renamed from: e, reason: from kotlin metadata */
    public final int height;

    /* JADX INFO: renamed from: f, reason: from kotlin metadata */
    public final int framerateNum;

    /* JADX INFO: renamed from: g, reason: from kotlin metadata */
    public final int framerateDen;

    /* JADX INFO: renamed from: h, reason: from kotlin metadata */
    public final boolean isIDRFrame;

    /* JADX INFO: renamed from: i, reason: from kotlin metadata */
    public final long localTimeMicros;

    /* JADX INFO: renamed from: j, reason: from kotlin metadata */
    public final byte[] data;

    /* JADX INFO: renamed from: k, reason: from kotlin metadata */
    public final byte[] vps;

    /* JADX INFO: renamed from: l, reason: from kotlin metadata */
    public final byte[] sps;

    /* JADX INFO: renamed from: m, reason: from kotlin metadata */
    public final byte[] pps;

    /* JADX INFO: renamed from: n, reason: from kotlin metadata */
    public final boolean decodeOnly;

    /* JADX INFO: renamed from: o, reason: from kotlin metadata */
    public final boolean displayImmediately;

    public SyeVideoSample(boolean z, int i, int i2, int i3, int i4, int i5, boolean z2, long j, byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, boolean z3, boolean z4) {
        c cVar;
        this.isDiscontinuity = z;
        this.codec = i;
        this.width = i2;
        this.height = i3;
        this.framerateNum = i4;
        this.framerateDen = i5;
        this.isIDRFrame = z2;
        this.localTimeMicros = j;
        this.data = bArr;
        this.vps = bArr2;
        this.sps = bArr3;
        this.pps = bArr4;
        this.decodeOnly = z3;
        this.displayImmediately = z4;
        if (i == 0) {
            cVar = c.Undefined;
        } else if (i == 1) {
            cVar = c.AVC;
        } else {
            if (i != 2) {
                throw new IllegalArgumentException("No VideoCodec with value " + i);
            }
            cVar = c.HEVC;
        }
        this.videoCodec = cVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final a.b.a.a.o.f.a createMediaFormatWithExpectedMaxResolution$syeClient_release(int r12, int r13) {
        /*
            r11 = this;
            java.lang.String r1 = r11.getAndroidMimeType$syeClient_release()
            if (r1 == 0) goto L39
            a.b.a.a.o.f.a r8 = new a.b.a.a.o.f.a
            int r2 = r11.width
            int r3 = r11.height
            int r0 = r11.framerateDen
            int r4 = r11.framerateNum
            r5 = 1
            if (r0 >= r5) goto L14
            goto L21
        L14:
            float r4 = (float) r4
            float r0 = (float) r0
            r5 = 1065353216(0x3f800000, float:1.0)
            float r0 = r0 * r5
            float r4 = r4 / r0
            double r5 = (double) r4
            r9 = 4607182418800017408(0x3ff0000000000000, double:1.0)
            int r0 = (r5 > r9 ? 1 : (r5 == r9 ? 0 : -1))
            if (r0 >= 0) goto L23
        L21:
            r0 = 0
            goto L27
        L23:
            java.lang.Float r0 = java.lang.Float.valueOf(r4)
        L27:
            if (r0 == 0) goto L2e
            float r0 = r0.floatValue()
            goto L30
        L2e:
            r0 = 1103626240(0x41c80000, float:25.0)
        L30:
            r4 = r0
            r7 = 0
            r0 = r8
            r5 = r12
            r6 = r13
            r0.<init>(r1, r2, r3, r4, r5, r6, r7)
            return r8
        L39:
            java.lang.IllegalArgumentException r12 = new java.lang.IllegalArgumentException
            java.lang.String r13 = "Required value was null."
            java.lang.String r13 = r13.toString()
            r12.<init>(r13)
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.netinsight.sye.syeClient.video.SyeVideoSample.createMediaFormatWithExpectedMaxResolution$syeClient_release(int, int):a.b.a.a.o.f.a");
    }

    public final String getAndroidMimeType$syeClient_release() {
        int iOrdinal = this.videoCodec.ordinal();
        if (iOrdinal == 0) {
            return null;
        }
        if (iOrdinal == 1) {
            return "video/avc";
        }
        if (iOrdinal == 2) {
            return "video/hevc";
        }
        throw new NoWhenBranchMatchedException();
    }

    public final int getCodec() {
        return this.codec;
    }

    public final byte[] getData() {
        return this.data;
    }

    public final boolean getDecodeOnly() {
        return this.decodeOnly;
    }

    public final boolean getDisplayImmediately() {
        return this.displayImmediately;
    }

    public final int getFramerateDen() {
        return this.framerateDen;
    }

    public final int getFramerateNum() {
        return this.framerateNum;
    }

    public final int getHeight() {
        return this.height;
    }

    public final long getLocalTimeMicros() {
        return this.localTimeMicros;
    }

    public final byte[] getPps() {
        return this.pps;
    }

    public final byte[] getSps() {
        return this.sps;
    }

    public final c getVideoCodec() {
        return this.videoCodec;
    }

    public final byte[] getVps() {
        return this.vps;
    }

    public final int getWidth() {
        return this.width;
    }

    /* JADX INFO: renamed from: isDiscontinuity, reason: from getter */
    public final boolean getIsDiscontinuity() {
        return this.isDiscontinuity;
    }

    /* JADX INFO: renamed from: isIDRFrame, reason: from getter */
    public final boolean getIsIDRFrame() {
        return this.isIDRFrame;
    }
}
