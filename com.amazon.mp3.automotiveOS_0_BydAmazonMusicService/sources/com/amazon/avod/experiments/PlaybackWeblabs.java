package com.amazon.avod.experiments;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.collect.UnmodifiableIterator;
import java.util.Map;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackWeblabs {
    private static final ImmutableSet<MobileWeblab> PLAYBACK_WEBLABS;
    private static final ImmutableMap<String, MobileWeblab> PLAYBACK_WEBLABS_MAP;
    private static final ImmutableMap<String, String> PLAYBACK_WEBLAB_TO_FEATURE_NAME_MAP_FOR_QOS_TAGGING;

    static {
        ImmutableSet<MobileWeblab> immutableSetOf = ImmutableSet.of(new MobileWeblab("AIVPLAYERS_MEDIACODEC_SOFTWAREPLAYREADY_331879"), new MobileWeblab("AV_ANDROID_ALOYSIUS_287513"), new MobileWeblab("AIVPLAYERS_3P_AVIATOR_HFR_223619"), new MobileWeblab("AIVPLAYERS_243004"), new MobileWeblab("AIVPLAYERS_LIVE_SLIDING_WINDOW_POLICY_245364"), new MobileWeblab("AIVPLAYERS_250630"), new MobileWeblab("AIVPLAYERS_HEURISTICS_CALLBACKS_CHANGE_255240"), new MobileWeblab("AIVPLAYERS_SINGLE_INSTANCE_272360"), new MobileWeblab("AIVPLAYERS_ANDROID_VIDEO_PLAYER_V2_276692"), new MobileWeblab("AIVPLAYERS_12263_177208"), new MobileWeblab("AIVPLAYERS_FIRETV_MIRO_CAROUSELS_203132"), new MobileWeblab("AIVPLAYERS_290492"), new MobileWeblab("AIVPLAYERS_OK_HTTP_FOR_DOWNLOAD_SERVICE_320669"), new MobileWeblab("ATVANDROID_COMBINED_RESOURCE_CALL_FOR_DOWNLOADS_322985"), new MobileWeblab("AIVPLAYERS_SSAI_SINGLE_PERIOD_DOWNLOADS_380281"), new MobileWeblab("AIVPLAYERS_SSAI_MULTI_PERIOD_MANIFEST_352930"), new MobileWeblab("AIVPLAYERS_331571"), new MobileWeblab("GESTURE_SEEKING_334404"), new MobileWeblab("AIVPLAYERS_334646"), new MobileWeblab("SSAI_UPLOAD_AD_MANIFEST_TO_S3_343261"), new MobileWeblab("AIVPLAYERS_ROWC_406241"), new MobileWeblab("AIVPLAYERS_PV_ON_ROTHKO_VOD_384587"), new MobileWeblab("AIVPLAYERS_PV_ON_ROTHKO_LIVE_384629"), new MobileWeblab("DAI_TRICKPLAY_389914"), new MobileWeblab("PRSV2_CR_405603"));
        PLAYBACK_WEBLABS = immutableSetOf;
        PLAYBACK_WEBLABS_MAP = Maps.uniqueIndex(immutableSetOf, new Function() { // from class: com.amazon.avod.experiments.PlaybackWeblabs$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Function
            public final Object apply(Object obj) {
                return ((MobileWeblab) obj).getName();
            }
        });
        PLAYBACK_WEBLAB_TO_FEATURE_NAME_MAP_FOR_QOS_TAGGING = ImmutableMap.builder().put("AIVPLAYERS_243004", "PManifestBuffer").put("AIVPLAYERS_LIVE_SLIDING_WINDOW_POLICY_245364", "LiveWindow").put("AIVPLAYERS_HEURISTICS_CALLBACKS_CHANGE_255240", "HeurCallback").put("AIVPLAYERS_250630", "PManifestFatal").put("AIVPLAYERS_12263_177208", "Sye").put("AIVPLAYERS_FIRETV_MIRO_CAROUSELS_203132", "FtvMiro").put("AIVPLAYERS_MEDIACODEC_SOFTWAREPLAYREADY_331879", "MCPR").put("AIVPLAYERS_SINGLE_INSTANCE_272360", "SingleInstance").put("AIVPLAYERS_ANDROID_VIDEO_PLAYER_V2_276692", "ExoForAds").put("AIVPLAYERS_290492", "contentDes").put("AIVPLAYERS_OK_HTTP_FOR_DOWNLOAD_SERVICE_320669", "OkHttpDlS").put("ATVANDROID_COMBINED_RESOURCE_CALL_FOR_DOWNLOADS_322985", "SSAICombinedCallDownloads").put("AIVPLAYERS_SSAI_SINGLE_PERIOD_DOWNLOADS_380281", "SSAISinglePeriodDownloadsFeature").put("AIVPLAYERS_SSAI_MULTI_PERIOD_MANIFEST_352930", "SegmentListManifestSupport").put("AIVPLAYERS_327614", "HeapExperiment").put("AIVPLAYERS_331571", "DAG").put("GESTURE_SEEKING_334404", "Gestureseeking").put("AIVPLAYERS_334646", "RatingBadge").put("AIVPLAYERS_ROWC_406241", "PlayerSdkCaching").put("AIVPLAYERS_PV_ON_ROTHKO_VOD_384587", "RothkoVOD").put("AIVPLAYERS_PV_ON_ROTHKO_LIVE_384629", "RothkoLive").put("DAI_TRICKPLAY_389914", "DAITrickplay").put("PRSV2_CR_405603", "PrsV2ClientResources").build();
    }

    @Nonnull
    public static ImmutableMap<String, MobileWeblab> getPlaybackWeblabs() {
        return PLAYBACK_WEBLABS_MAP;
    }

    private static ImmutableMap<String, String> getPlaybackWeblabsToReportToQos() {
        return PLAYBACK_WEBLAB_TO_FEATURE_NAME_MAP_FOR_QOS_TAGGING;
    }

    public static String getRunningExperimentsString() {
        ImmutableMap<String, String> playbackWeblabsToReportToQos = getPlaybackWeblabsToReportToQos();
        StringBuilder sb = new StringBuilder();
        UnmodifiableIterator<Map.Entry<String, String>> it = playbackWeblabsToReportToQos.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> next = it.next();
            String key = next.getKey();
            String value = next.getValue();
            MobileWeblab mobileWeblab = getPlaybackWeblabs().get(key);
            if (mobileWeblab != null && value != null) {
                sb.append(value);
                sb.append(":");
                sb.append(mobileWeblab.getCurrentTreatment().toReportableString());
                sb.append(",");
            }
        }
        return sb.toString();
    }
}
