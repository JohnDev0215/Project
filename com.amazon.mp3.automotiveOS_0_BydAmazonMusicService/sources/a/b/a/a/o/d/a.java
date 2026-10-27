package a.b.a.a.o.d;

import a.b.a.a.i.b;
import a.b.a.a.n.b;
import a.b.a.a.o.d.b.k;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.util.Range;
import androidx.annotation.RequiresApi;
import java.io.IOException;
import java.util.ArrayList;
import kotlin.collections.CollectionsKt__IterablesKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static final C0019a b = new C0019a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final b f97a = b.d.a("VideoCodecFactory", b.a.Video);

    /* JADX INFO: renamed from: a.b.a.a.o.d.a$a, reason: collision with other inner class name */
    public static final class C0019a {
        @JvmStatic
        @RequiresApi(21)
        public final MediaCodec a(a.b.a.a.o.f.a aVar) {
            MediaCodecInfo.VideoCapabilities videoCapabilities;
            Range<Integer> bitrateRange;
            MediaCodecInfo.VideoCapabilities videoCapabilities2;
            Range<Integer> supportedHeights;
            MediaCodecInfo.VideoCapabilities videoCapabilities3;
            Range<Integer> supportedWidths;
            a.f97a.a("VideoCodecFactory", "createDecoder: Attempting to create decoder for format:\n" + aVar);
            MediaCodecInfo[] codecInfos = k.b.a(false, false).getCodecInfos();
            Intrinsics.checkExpressionValueIsNotNull(codecInfos, "VideoDecoderUtil\n       …              .codecInfos");
            ArrayList<MediaCodecInfo> arrayList = new ArrayList();
            for (MediaCodecInfo it : codecInfos) {
                k kVar = k.b;
                Intrinsics.checkExpressionValueIsNotNull(it, "it");
                if (kVar.a(it, aVar)) {
                    arrayList.add(it);
                }
            }
            ArrayList<String> arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(arrayList, 10));
            for (MediaCodecInfo it2 : arrayList) {
                Intrinsics.checkExpressionValueIsNotNull(it2, "it");
                arrayList2.add(it2.getName());
            }
            a.f97a.a("VideoCodecFactory", "Found " + arrayList2.size() + " eligible decoders: '" + CollectionsKt___CollectionsKt.joinToString$default(arrayList2, null, null, null, 0, null, null, 63, null) + '\'');
            if (arrayList2.isEmpty()) {
                throw new IllegalArgumentException("No decoder on device supports format: " + aVar);
            }
            for (String str : arrayList2) {
                try {
                    a.f97a.a("VideoCodecFactory", "createDecoder: Attempting to create decoder " + str);
                    MediaCodec mediaCodecCreateByCodecName = MediaCodec.createByCodecName(str);
                    Intrinsics.checkExpressionValueIsNotNull(mediaCodecCreateByCodecName, "MediaCodec.createByCodecName(decoderName)");
                    k kVar2 = k.b;
                    MediaCodecInfo codecInfo = mediaCodecCreateByCodecName.getCodecInfo();
                    Intrinsics.checkExpressionValueIsNotNull(codecInfo, "decoder.codecInfo");
                    MediaCodecInfo.CodecCapabilities codecCapabilitiesA = kVar2.a(codecInfo, aVar.d);
                    MediaCodecInfo codecInfo2 = mediaCodecCreateByCodecName.getCodecInfo();
                    Intrinsics.checkExpressionValueIsNotNull(codecInfo2, "decoder.codecInfo");
                    String name = codecInfo2.getName();
                    Intrinsics.checkExpressionValueIsNotNull(name, "codecInfo.name");
                    StringsKt__StringsJVMKt.endsWith(name, ".secure", true);
                    if (codecCapabilitiesA != null) {
                        codecCapabilitiesA.isFeatureSupported("adaptive-playback");
                    }
                    if (codecCapabilitiesA != null && (videoCapabilities3 = codecCapabilitiesA.getVideoCapabilities()) != null && (supportedWidths = videoCapabilities3.getSupportedWidths()) != null) {
                    }
                    if (codecCapabilitiesA != null && (videoCapabilities2 = codecCapabilitiesA.getVideoCapabilities()) != null && (supportedHeights = videoCapabilities2.getSupportedHeights()) != null) {
                    }
                    if (codecCapabilitiesA != null && (videoCapabilities = codecCapabilitiesA.getVideoCapabilities()) != null && (bitrateRange = videoCapabilities.getBitrateRange()) != null) {
                    }
                    return mediaCodecCreateByCodecName;
                } catch (IOException e) {
                    a.f97a.b("VideoCodecFactory", "createNewDecoder: codec cannot be created: " + e.getMessage());
                } catch (IllegalArgumentException e2) {
                    a.f97a.b("VideoCodecFactory", "createNewDecoder: invalid codec name:" + e2.getMessage());
                } catch (NullPointerException e3) {
                    a.f97a.b("VideoCodecFactory", "createNewDecoder: null codec name: " + e3.getMessage());
                }
            }
            throw new a.b.a.a.j.a("Unable to create decoder");
        }
    }
}
