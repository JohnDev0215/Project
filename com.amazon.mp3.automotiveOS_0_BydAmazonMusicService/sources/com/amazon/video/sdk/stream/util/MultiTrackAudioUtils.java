package com.amazon.video.sdk.stream.util;

import com.amazon.avod.content.urlvending.AudioTrackMetadata;
import com.amazon.video.sdk.player.ContentConfig;
import com.amazon.video.sdk.stream.AudioStreamMatcher;
import com.amazon.video.sdk.stream.AudioType;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;

/* JADX INFO: compiled from: MultiTrackAudioUtils.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0016\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0007¢\u0006\u0004\b\f\u0010\rJ\u001c\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0016J \u0010\u000b\u001a\u0004\u0018\u00010\u00062\u0006\u0010\t\u001a\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0016¨\u0006\u000f"}, d2 = {"Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils;", "", "", "Lcom/amazon/video/sdk/stream/AudioStreamMatcher;", "audioStreamMatchers", "Lcom/google/common/collect/ImmutableList;", "", "generateAudioTrackIdsFromStreamMatchers", "Lcom/amazon/video/sdk/player/ContentConfig;", "content", "preferredAudioTrackIds", "getInitialAudioTrackId", "<init>", "()V", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0})
public class MultiTrackAudioUtils {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);

    /* JADX INFO: compiled from: MultiTrackAudioUtils.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tH\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/amazon/video/sdk/stream/util/MultiTrackAudioUtils$Companion;", "", "()V", "AUDIO_STREAM_DEFAULT_INDEX", "", "getAudioTrackId", "audioTrackMetadata", "Lcom/amazon/avod/content/urlvending/AudioTrackMetadata;", "audioTrackMetadataList", "Lcom/google/common/collect/ImmutableList;", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final String getAudioTrackId(AudioTrackMetadata audioTrackMetadata, ImmutableList<AudioTrackMetadata> audioTrackMetadataList) {
            Intrinsics.checkNotNullParameter(audioTrackMetadata, "audioTrackMetadata");
            Intrinsics.checkNotNullParameter(audioTrackMetadataList, "audioTrackMetadataList");
            String audioTrackId = audioTrackMetadata.getAudioTrackId();
            if (audioTrackId.length() == 0) {
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                audioTrackId = String.format("%s_%s_%s", Arrays.copyOf(new Object[]{audioTrackMetadata.getLanguageCode(), audioTrackMetadata.getSubtype(), Integer.valueOf(audioTrackMetadataList.indexOf(audioTrackMetadata))}, 3));
                Intrinsics.checkNotNullExpressionValue(audioTrackId, "format(format, *args)");
            }
            Intrinsics.checkNotNullExpressionValue(audioTrackId, "audioTrackMetadata.let {…          }\n            }");
            return audioTrackId;
        }
    }

    public ImmutableList<String> generateAudioTrackIdsFromStreamMatchers(List<AudioStreamMatcher> audioStreamMatchers) {
        Intrinsics.checkNotNullParameter(audioStreamMatchers, "audioStreamMatchers");
        ArrayList arrayList = new ArrayList();
        for (AudioStreamMatcher audioStreamMatcher : audioStreamMatchers) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String str = String.format("%s_%s_%s", Arrays.copyOf(new Object[]{audioStreamMatcher.getLanguage(), AudioType.INSTANCE.to(audioStreamMatcher.getSubType()), "0"}, 3));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            arrayList.add(str);
        }
        ImmutableList<String> immutableListCopyOf = ImmutableList.copyOf((Collection) arrayList);
        Intrinsics.checkNotNullExpressionValue(immutableListCopyOf, "copyOf(audioTrackIds)");
        return immutableListCopyOf;
    }

    public String getInitialAudioTrackId(ContentConfig content, ImmutableList<String> preferredAudioTrackIds) {
        Intrinsics.checkNotNullParameter(content, "content");
        Intrinsics.checkNotNullParameter(preferredAudioTrackIds, "preferredAudioTrackIds");
        if (content.getVideoType().isLive$AmazonAndroidVideoPlayer_release() || preferredAudioTrackIds.isEmpty()) {
            return null;
        }
        return preferredAudioTrackIds.get(0);
    }
}
