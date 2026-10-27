package com.amazon.video.sdk.stream.util;

import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.avod.util.DLog;
import com.amazon.video.sdk.stream.AudioStream;
import com.amazon.video.sdk.stream.AudioStreamData;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import com.amazon.video.sdk.stream.AudioType;
import com.amazon.video.sdk.stream.util.MultiTrackAudioUtils;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MultiTrackAudioUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00040\u0003¨\u0006\u0005"}, d2 = {"generateAudioStreamsList", "", "Lcom/amazon/video/sdk/stream/AudioStream;", "Lcom/google/common/collect/ImmutableList;", "Lcom/amazon/avod/content/urlvending/AudioTrackMetadata;", "AmazonAndroidVideoPlayer_release"}, k = 2, mv = {1, 6, 0}, xi = 48)
public final class MultiTrackAudioUtilsKt {
    public static final List<AudioStream> generateAudioStreamsList(ImmutableList<AudioTrackMetadata> immutableList) {
        Intrinsics.checkNotNullParameter(immutableList, "<this>");
        ArrayList arrayList = new ArrayList();
        for (AudioTrackMetadata it : immutableList) {
            String languageCode = it.getLanguageCode();
            Intrinsics.checkNotNullExpressionValue(languageCode, "it.languageCode");
            AudioType audioTypeFrom = AudioType.INSTANCE.from(it.getSubtype());
            MultiTrackAudioUtils.Companion companion = MultiTrackAudioUtils.INSTANCE;
            Intrinsics.checkNotNullExpressionValue(it, "it");
            AudioStreamData audioStreamData = new AudioStreamData(companion.getAudioTrackId(it, immutableList), languageCode, audioTypeFrom, new AudioStreamMatcher(languageCode, audioTypeFrom), it.getDisplayName(), null, null, 96, null);
            DLog.logf("Found audio language %s and type %s", audioStreamData.getLanguage(), audioStreamData.getType());
            arrayList.add(audioStreamData);
        }
        ImmutableList immutableListCopyOf = ImmutableList.copyOf((Collection) arrayList);
        Intrinsics.checkNotNullExpressionValue(immutableListCopyOf, "copyOf(audioStreams)");
        return immutableListCopyOf;
    }
}
