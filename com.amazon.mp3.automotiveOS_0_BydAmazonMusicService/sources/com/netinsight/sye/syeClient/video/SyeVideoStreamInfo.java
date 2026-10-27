package com.netinsight.sye.syeClient.video;

import a.b.a.a.k.e.c;
import a.b.a.a.n.a;
import a.b.a.a.o.b;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0016\u0018\u0000 !2\u00020\u0001:\u0001\u0007B\u0011\b\u0002\u0012\u0006\u0010\u001e\u001a\u00020\u0002¢\u0006\u0004\b\u001f\u0010 J\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0002H\u0000¢\u0006\u0004\b\u0003\u0010\u0004R\u001c\u0010\u000b\u001a\u00020\u00068\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\nR\u001c\u0010\u0011\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u001c\u0010\u0014\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0013\u0010\u0010R\u001c\u0010\u0017\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u0010\u000e\u001a\u0004\b\u0016\u0010\u0010R\u001c\u0010\u001a\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0018\u0010\u000e\u001a\u0004\b\u0019\u0010\u0010R\u001c\u0010\u001d\u001a\u00020\f8\u0016@\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010\u000e\u001a\u0004\b\u001c\u0010\u0010¨\u0006\""}, d2 = {"Lcom/netinsight/sye/syeClient/video/SyeVideoStreamInfo;", "Lcom/netinsight/sye/syeClient/video/ISyeVideoStreamInfo;", "", "getAndroidMimeType$syeClient_release", "()Ljava/lang/String;", "getAndroidMimeType", "La/b/a/a/o/b;", "a", "La/b/a/a/o/b;", "getCodec", "()La/b/a/a/o/b;", "codec", "", "b", "I", "getWidth", "()I", "width", "c", "getHeight", "height", "d", "getFramerateNum", "framerateNum", "f", "getBitrate", "bitrate", "e", "getFramerateDen", "framerateDen", "jsonString", "<init>", "(Ljava/lang/String;)V", "Companion", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class SyeVideoStreamInfo implements ISyeVideoStreamInfo {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion();

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final b codec;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public final int width;

    /* JADX INFO: renamed from: c, reason: from kotlin metadata */
    public final int height;

    /* JADX INFO: renamed from: d, reason: from kotlin metadata */
    public final int framerateNum;

    /* JADX INFO: renamed from: e, reason: from kotlin metadata */
    public final int framerateDen;

    /* JADX INFO: renamed from: f, reason: from kotlin metadata */
    public final int bitrate;

    /* JADX INFO: renamed from: com.netinsight.sye.syeClient.video.SyeVideoStreamInfo$a, reason: from kotlin metadata */
    public static final class Companion {
        @JvmStatic
        public final SyeVideoStreamInfo a(String str) {
            try {
                return new SyeVideoStreamInfo(str, null);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public SyeVideoStreamInfo(String str) {
        c cVar;
        JSONObject jSONObject = new JSONObject(str);
        int i = jSONObject.getInt("codec");
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
        this.codec = a.a(cVar);
        this.width = jSONObject.getInt("width");
        this.height = jSONObject.getInt("height");
        this.framerateNum = jSONObject.getInt("framerateNum");
        this.framerateDen = jSONObject.getInt("framerateDen");
        this.bitrate = jSONObject.getInt("bitrate");
    }

    public /* synthetic */ SyeVideoStreamInfo(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    @JvmStatic
    public static final SyeVideoStreamInfo parseJson(String str) {
        return INSTANCE.a(str);
    }

    public final String getAndroidMimeType$syeClient_release() {
        int iOrdinal = getCodec().ordinal();
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

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo
    public int getBitrate() {
        return this.bitrate;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo
    public b getCodec() {
        return this.codec;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo
    public int getFramerateDen() {
        return this.framerateDen;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo
    public int getFramerateNum() {
        return this.framerateNum;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo
    public int getHeight() {
        return this.height;
    }

    @Override // com.netinsight.sye.syeClient.video.ISyeVideoStreamInfo
    public int getWidth() {
        return this.width;
    }
}
