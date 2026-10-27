package a.b.a.a.o.f;

import android.media.MediaCodecInfo;
import android.media.MediaFormat;
import android.os.Build;
import java.nio.ByteBuffer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ByteBuffer f140a;
    public final ByteBuffer b;
    public final int c;
    public final String d;
    public final int e;
    public final int f;
    public final float g;
    public final int h;
    public final int i;
    public final MediaCodecInfo.CodecProfileLevel j = null;

    public a(String str, int i, int i2, float f, int i3, int i4, MediaCodecInfo.CodecProfileLevel codecProfileLevel) {
        this.d = str;
        this.e = i;
        this.f = i2;
        this.g = f;
        this.h = i3;
        this.i = i4;
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(0);
        Intrinsics.checkExpressionValueIsNotNull(byteBufferAllocate, "ByteBuffer.allocate(0)");
        this.f140a = byteBufferAllocate;
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(0);
        Intrinsics.checkExpressionValueIsNotNull(byteBufferAllocate2, "ByteBuffer.allocate(0)");
        this.b = byteBufferAllocate2;
        this.c = a(str, false, i3, i4);
    }

    public final int a(String str, boolean z, int i, int i2) {
        int i3;
        int i4;
        if (i > 0 && i2 > 0) {
            int iHashCode = str.hashCode();
            if (iHashCode != -1662541442) {
                if (iHashCode == 1331836730 && str.equals("video/avc")) {
                    String str2 = Build.MODEL;
                    if (StringsKt__StringsJVMKt.equals("BRAVIA 4K 2015", str2, true) || (StringsKt__StringsJVMKt.equals("Amazon", Build.MANUFACTURER, true) && (StringsKt__StringsJVMKt.equals("KFSOWI", str2, true) || (StringsKt__StringsJVMKt.equals("AFTS", str2, true) && z)))) {
                        return 0;
                    }
                    i3 = (((i + 16) - 1) / 16) * (((i2 + 16) - 1) / 16) * 16 * 16;
                    i4 = 2;
                    return (i3 * 3) / (i4 * 2);
                }
            } else if (str.equals("video/hevc")) {
                i3 = i * i2;
                i4 = 4;
                return (i3 * 3) / (i4 * 2);
            }
        }
        return 0;
    }

    public final MediaFormat a() {
        MediaFormat mediaFormatCreateVideoFormat = MediaFormat.createVideoFormat(this.d, this.e, this.f);
        int i = this.c;
        if (i > 0) {
            mediaFormatCreateVideoFormat.setInteger("max-input-size", i);
        }
        mediaFormatCreateVideoFormat.setInteger("priority", 0);
        mediaFormatCreateVideoFormat.setFloat("operating-rate", this.g);
        mediaFormatCreateVideoFormat.setFloat("frame-rate", this.g);
        mediaFormatCreateVideoFormat.setFeatureEnabled("secure-playback", false);
        mediaFormatCreateVideoFormat.setFeatureEnabled("adaptive-playback", true);
        int i2 = this.h;
        if (i2 > 0) {
            mediaFormatCreateVideoFormat.setInteger("max-width", i2);
        }
        int i3 = this.i;
        if (i3 > 0) {
            mediaFormatCreateVideoFormat.setInteger("max-height", i3);
        }
        Intrinsics.checkExpressionValueIsNotNull(mediaFormatCreateVideoFormat, "MediaFormat.createVideoF…}\n            }\n        }");
        return mediaFormatCreateVideoFormat;
    }

    public String toString() {
        String strValueOf;
        String strValueOf2;
        StringBuilder sb = new StringBuilder();
        sb.append(" codec: '");
        sb.append(this.d);
        sb.append("'\n");
        sb.append(" profile: '");
        MediaCodecInfo.CodecProfileLevel codecProfileLevel = this.j;
        String str = "null";
        if (codecProfileLevel == null || (strValueOf = String.valueOf(codecProfileLevel.profile)) == null) {
            strValueOf = "null";
        }
        sb.append(strValueOf);
        sb.append("'\n");
        sb.append(" level: '");
        MediaCodecInfo.CodecProfileLevel codecProfileLevel2 = this.j;
        if (codecProfileLevel2 != null && (strValueOf2 = String.valueOf(codecProfileLevel2.level)) != null) {
            str = strValueOf2;
        }
        sb.append(str);
        sb.append("'\n");
        sb.append(" w: '");
        sb.append(this.e);
        sb.append("'\n");
        sb.append(" h: '");
        sb.append(this.f);
        sb.append("'\n");
        sb.append(" fps: '");
        sb.append(this.g);
        sb.append("'\n");
        sb.append(" max w: '");
        sb.append(this.h);
        sb.append("'\n");
        sb.append(" max h: '");
        sb.append(this.i);
        sb.append("'\n");
        sb.append(" max input size: '");
        sb.append(this.c);
        sb.append('\'');
        return sb.toString();
    }
}
