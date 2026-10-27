package com.amazon.video.sdk.player.error;

import android.content.Context;
import androidx.compose.runtime.ComposeVersion;
import androidx.core.view.PointerIconCompat;
import com.amazon.avod.media.download.error.DownloadErrorCode;
import com.amazon.avod.media.download.error.DownloadExecutionErrorCode;
import com.amazon.avod.media.error.DrmErrorCode;
import com.amazon.avod.media.error.MediaErrorCode;
import com.amazon.avod.media.error.ServiceErrorCode;
import com.amazon.avod.media.error.StandardErrorCode;
import com.amazon.avod.util.Preconditions2;
import com.amazon.video.player.R$string;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureImpl;
import com.google.android.gms.wearable.WearableStatusCodes;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.visualon.OSMPUtils.voOSTypePrivate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt__MapsJVMKt;
import kotlin.collections.MapsKt__MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;

/* JADX INFO: renamed from: com.amazon.video.sdk.player.error.PlaybackErrorImpl, reason: from toString */
/* JADX INFO: compiled from: PlaybackErrorImpl.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000  2\u00020\u0001:\u0001 B\u0017\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\"\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\r2\u0006\u0010\u0007\u001a\u00020\bH\u0002J\b\u0010\u001f\u001a\u00020\u0013H\u0016R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nRN\u0010\u000b\u001aB\u0012\u0004\u0012\u00020\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\r0\fj \u0012\u0004\u0012\u00020\b\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\r`\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\nR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0017\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\nR\u000e\u0010\u0019\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001d¨\u0006!"}, d2 = {"Lcom/amazon/video/sdk/player/error/PlaybackErrorImpl;", "Lcom/amazon/video/sdk/player/error/PlaybackError;", "mediaErrorCode", "Lcom/amazon/avod/media/error/MediaErrorCode;", "context", "Landroid/content/Context;", "(Lcom/amazon/avod/media/error/MediaErrorCode;Landroid/content/Context;)V", "errorCode", "", "getErrorCode", "()I", "errorCodeToErrorCodeInfoMap", "Ljava/util/HashMap;", "Lkotlin/Triple;", "Lcom/amazon/video/sdk/player/error/ErrorDomain;", "Lkotlin/collections/HashMap;", "errorDescription", "getErrorDescription", "errorDescriptionText", "", "errorDomain", "getErrorDomain", "()Lcom/amazon/video/sdk/player/error/ErrorDomain;", "errorMessage", "getErrorMessage", "errorMessageText", "errorType", "Lcom/amazon/video/sdk/player/error/ErrorType;", "getErrorType", "()Lcom/amazon/video/sdk/player/error/ErrorType;", "getErrorCodeInfo", "toString", "Companion", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlaybackError implements com.amazon.video.sdk.player.error.PlaybackError {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final HashMap<DownloadErrorCode, Integer> downloadErrorCodeToErrorCodeMap;
    private static final HashMap<DownloadErrorCode, ErrorType> downloadErrorCodeToErrorTypeMap;
    private static final HashMap<DownloadExecutionErrorCode, Integer> downloadExecutionErrorCodeToErrorCodeMap;
    private static final HashMap<DownloadExecutionErrorCode, ErrorType> downloadExecutionErrorCodeToErrorTypeMap;
    private static final HashMap<DrmErrorCode, Integer> drmErrorCodeToErrorCodeMap;
    private static final HashMap<DrmErrorCode, ErrorType> drmErrorCodeToErrorTypeMap;
    private static final ImmutableMap<MediaErrorCode, ErrorType> errorToErrorTypeMap;
    private static final ImmutableMap<MediaErrorCode, Integer> mediaErrorCodeToErrorCodeMap;
    private static final Map<PlaylistFeatureImpl.PlaylistErrorCode, Integer> playlistErrorToErrorCodeMap;
    private static final Map<PlaylistFeatureImpl.PlaylistErrorCode, ErrorType> playlistErrorToErrorTypeMap;
    private static final HashMap<ServiceErrorCode, Integer> serviceErrorCodeToErrorCodeMap;
    private static final HashMap<ServiceErrorCode, ErrorType> serviceErrorCodeToErrorTypeMap;
    private static final HashMap<StandardErrorCode, Integer> standardErrorCodeToErrorCodeMap;
    private static final HashMap<StandardErrorCode, ErrorType> standardErrorCodeToErrorTypeMap;
    private final int errorCode;
    private final HashMap<Integer, Triple<ErrorDomain, Integer, Integer>> errorCodeToErrorCodeInfoMap;
    private final int errorDescription;
    private final String errorDescriptionText;
    private final ErrorDomain errorDomain;
    private final int errorMessage;
    private final String errorMessageText;
    private final ErrorType errorType;

    public PlaybackError(MediaErrorCode mediaErrorCode, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Integer numValueOf = Integer.valueOf(PointerIconCompat.TYPE_CONTEXT_MENU);
        ErrorDomain errorDomain = ErrorDomain.Network;
        Integer numValueOf2 = Integer.valueOf(PointerIconCompat.TYPE_HAND);
        int i = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_1002;
        Integer numValueOf3 = Integer.valueOf(i);
        int i2 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_1002;
        ErrorDomain errorDomain2 = ErrorDomain.ContentProtection;
        int i3 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_3001;
        Integer numValueOf4 = Integer.valueOf(i3);
        int i4 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_3001;
        int i5 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_3002;
        Integer numValueOf5 = Integer.valueOf(i5);
        int i6 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_3002;
        Integer numValueOf6 = Integer.valueOf(WearableStatusCodes.DUPLICATE_LISTENER);
        ErrorDomain errorDomain3 = ErrorDomain.System;
        int i7 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_4001;
        Integer numValueOf7 = Integer.valueOf(i7);
        int i8 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_4001;
        Integer numValueOf8 = Integer.valueOf(WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED);
        int i9 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_4008;
        Integer numValueOf9 = Integer.valueOf(i9);
        int i10 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_4008;
        Integer numValueOf10 = Integer.valueOf(voOSTypePrivate.VOOSMP_SRC_ADAPTIVE_STREAMING_INFO_EVENT_INTERNAL_SEEK_POS);
        ErrorDomain errorDomain4 = ErrorDomain.Generic;
        int i11 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_5001;
        Integer numValueOf11 = Integer.valueOf(i11);
        int i12 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_5001;
        int i13 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_5002;
        Integer numValueOf12 = Integer.valueOf(i13);
        int i14 = R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_5002;
        ErrorDomain errorDomain5 = ErrorDomain.Playlist;
        this.errorCodeToErrorCodeInfoMap = MapsKt__MapsKt.hashMapOf(TuplesKt.to(numValueOf, new Triple(errorDomain, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_1001), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_1001))), TuplesKt.to(numValueOf2, new Triple(errorDomain, numValueOf3, Integer.valueOf(i2))), TuplesKt.to(Integer.valueOf(PointerIconCompat.TYPE_HELP), new Triple(errorDomain, Integer.valueOf(i), Integer.valueOf(i2))), TuplesKt.to(Integer.valueOf(PointerIconCompat.TYPE_WAIT), new Triple(errorDomain, Integer.valueOf(i), Integer.valueOf(i2))), TuplesKt.to(3001, new Triple(errorDomain2, numValueOf4, Integer.valueOf(i4))), TuplesKt.to(3002, new Triple(errorDomain2, numValueOf5, Integer.valueOf(i6))), TuplesKt.to(3003, new Triple(errorDomain2, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_3003), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_3003))), TuplesKt.to(3004, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3005, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3006, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3007, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3008, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3009, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3010, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3011, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3012, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3013, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3014, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3015, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3016, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3017, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3018, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3019, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3020, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3021, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3022, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3023, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3024, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3025, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3026, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3027, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3028, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3029, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3030, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3031, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3032, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3033, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3034, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3035, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3036, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3037, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3038, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3039, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3040, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3041, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3042, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3043, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3044, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3045, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3046, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3047, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3048, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3049, new Triple(errorDomain2, Integer.valueOf(i3), Integer.valueOf(i4))), TuplesKt.to(3050, new Triple(errorDomain2, Integer.valueOf(i5), Integer.valueOf(i6))), TuplesKt.to(3051, new Triple(errorDomain2, Integer.valueOf(i5), Integer.valueOf(i6))), TuplesKt.to(3052, new Triple(errorDomain2, Integer.valueOf(i5), Integer.valueOf(i6))), TuplesKt.to(3053, new Triple(errorDomain2, Integer.valueOf(i5), Integer.valueOf(i6))), TuplesKt.to(numValueOf6, new Triple(errorDomain3, numValueOf7, Integer.valueOf(i8))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.UNKNOWN_LISTENER), new Triple(errorDomain3, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_4002), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_4002))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.DATA_ITEM_TOO_LARGE), new Triple(errorDomain3, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_4003), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_4003))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.DUPLICATE_CAPABILITY), new Triple(errorDomain3, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_4006), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_4006))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.UNKNOWN_CAPABILITY), new Triple(errorDomain3, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_4007), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_4007))), TuplesKt.to(numValueOf8, new Triple(errorDomain3, numValueOf9, Integer.valueOf(i10))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.UNSUPPORTED_BY_TARGET_NODE), new Triple(errorDomain3, Integer.valueOf(i9), Integer.valueOf(i10))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.ACCOUNT_KEY_CREATION_FAILED), new Triple(errorDomain3, Integer.valueOf(i9), Integer.valueOf(i10))), TuplesKt.to(Integer.valueOf(WearableStatusCodes.MODEL_ID_UNAVAILABLE), new Triple(errorDomain3, Integer.valueOf(i9), Integer.valueOf(i10))), TuplesKt.to(4012, new Triple(errorDomain3, Integer.valueOf(i9), Integer.valueOf(i10))), TuplesKt.to(4013, new Triple(errorDomain3, Integer.valueOf(i9), Integer.valueOf(i10))), TuplesKt.to(4014, new Triple(errorDomain3, Integer.valueOf(i9), Integer.valueOf(i10))), TuplesKt.to(4015, new Triple(errorDomain3, Integer.valueOf(i7), Integer.valueOf(i8))), TuplesKt.to(4016, new Triple(errorDomain3, Integer.valueOf(i7), Integer.valueOf(i8))), TuplesKt.to(4017, new Triple(errorDomain3, Integer.valueOf(i7), Integer.valueOf(i8))), TuplesKt.to(numValueOf10, new Triple(errorDomain4, numValueOf11, Integer.valueOf(i12))), TuplesKt.to(5002, new Triple(errorDomain4, numValueOf12, Integer.valueOf(i14))), TuplesKt.to(Integer.valueOf(ComposeVersion.version), new Triple(errorDomain4, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_5003), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_5003))), TuplesKt.to(5004, new Triple(errorDomain4, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_5004), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_5004))), TuplesKt.to(5005, new Triple(errorDomain4, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_5005), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_5005))), TuplesKt.to(5006, new Triple(errorDomain4, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_5006), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_5006))), TuplesKt.to(5007, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5008, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5009, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5010, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5011, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5012, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5013, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5014, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5015, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5016, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5017, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5018, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5019, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5020, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5021, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5022, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5023, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5024, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5025, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5026, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5027, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5028, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5029, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5030, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5031, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5032, new Triple(errorDomain4, Integer.valueOf(i11), Integer.valueOf(i12))), TuplesKt.to(5033, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5034, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5035, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5036, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5037, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5038, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5039, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5040, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5041, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5042, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5043, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5044, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5045, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5046, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5047, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5048, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(5049, new Triple(errorDomain4, Integer.valueOf(i13), Integer.valueOf(i14))), TuplesKt.to(6001, new Triple(errorDomain5, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_6001), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_6001))), TuplesKt.to(6002, new Triple(errorDomain5, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_6002), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_6002))), TuplesKt.to(6003, new Triple(errorDomain5, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_6003), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_6003))), TuplesKt.to(6004, new Triple(errorDomain5, Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_DESCRIPTION_6004), Integer.valueOf(R$string.AV_MOBILE_ANDROID_PLAYER_ERROR_MESSAGE_6004))));
        MediaErrorCode mediaErrorCode2 = INSTANCE.getMediaErrorCode(mediaErrorCode);
        ImmutableMap<MediaErrorCode, ErrorType> immutableMap = errorToErrorTypeMap;
        Preconditions.checkArgument(immutableMap.containsKey(mediaErrorCode2));
        Integer num = mediaErrorCodeToErrorCodeMap.get(mediaErrorCode2);
        this.errorCode = (num != null ? num : numValueOf10).intValue();
        Triple<ErrorDomain, Integer, Integer> errorCodeInfo = getErrorCodeInfo(getErrorCode());
        this.errorDomain = errorCodeInfo.getFirst();
        this.errorDescription = errorCodeInfo.getSecond().intValue();
        this.errorMessage = errorCodeInfo.getThird().intValue();
        ErrorType errorType = immutableMap.get(mediaErrorCode2);
        this.errorType = errorType == null ? ErrorType.PlayerError : errorType;
        String string = context.getString(getErrorDescription());
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(errorDescription)");
        this.errorDescriptionText = string;
        String string2 = context.getString(getErrorMessage());
        Intrinsics.checkNotNullExpressionValue(string2, "context.getString(errorMessage)");
        this.errorMessageText = string2;
    }

    @Override // com.amazon.video.sdk.player.error.PlaybackError
    public int getErrorCode() {
        return this.errorCode;
    }

    @Override // com.amazon.video.sdk.player.error.PlaybackError
    public ErrorDomain getErrorDomain() {
        return this.errorDomain;
    }

    @Override // com.amazon.video.sdk.player.error.PlaybackError
    public int getErrorDescription() {
        return this.errorDescription;
    }

    @Override // com.amazon.video.sdk.player.error.PlaybackError
    public int getErrorMessage() {
        return this.errorMessage;
    }

    public final ErrorType getErrorType() {
        return this.errorType;
    }

    /* JADX INFO: renamed from: com.amazon.video.sdk.player.error.PlaybackErrorImpl$Companion, reason: from kotlin metadata */
    /* JADX INFO: compiled from: PlaybackErrorImpl.kt */
    @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u001e\u001a\u00020\u00122\b\u0010\u001f\u001a\u0004\u0018\u00010\u0012H\u0002J\u0010\u0010 \u001a\u00020!2\b\u0010\u001f\u001a\u0004\u0018\u00010\u0012R\"\u0010\u0003\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\b\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\n\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000b0\u000b\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\f\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000b0\u000b\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\r\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000f\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u000e0\u000e\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\t0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00070\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u00070\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\t0\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0018\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00190\u0019\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001a\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00190\u0019\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001b\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u001c0\u001c\u0012\u0004\u0012\u00020\u00070\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u0016\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u001c0\u001c\u0012\u0004\u0012\u00020\t0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\""}, d2 = {"Lcom/amazon/video/sdk/player/error/PlaybackErrorImpl$Companion;", "", "()V", "downloadErrorCodeToErrorCodeMap", "Ljava/util/HashMap;", "Lcom/amazon/avod/media/download/error/DownloadErrorCode;", "kotlin.jvm.PlatformType", "", "downloadErrorCodeToErrorTypeMap", "Lcom/amazon/video/sdk/player/error/ErrorType;", "downloadExecutionErrorCodeToErrorCodeMap", "Lcom/amazon/avod/media/download/error/DownloadExecutionErrorCode;", "downloadExecutionErrorCodeToErrorTypeMap", "drmErrorCodeToErrorCodeMap", "Lcom/amazon/avod/media/error/DrmErrorCode;", "drmErrorCodeToErrorTypeMap", "errorToErrorTypeMap", "Lcom/google/common/collect/ImmutableMap;", "Lcom/amazon/avod/media/error/MediaErrorCode;", "mediaErrorCodeToErrorCodeMap", "playlistErrorToErrorCodeMap", "", "Lcom/amazon/video/sdk/player/playlist/PlaylistFeatureImpl$PlaylistErrorCode;", "playlistErrorToErrorTypeMap", "serviceErrorCodeToErrorCodeMap", "Lcom/amazon/avod/media/error/ServiceErrorCode;", "serviceErrorCodeToErrorTypeMap", "standardErrorCodeToErrorCodeMap", "Lcom/amazon/avod/media/error/StandardErrorCode;", "standardErrorCodeToErrorTypeMap", "getMediaErrorCode", "mediaErrorCode", "isErrorCodeFatal", "", "AmazonAndroidVideoPlayer_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final boolean isErrorCodeFatal(MediaErrorCode mediaErrorCode) {
            return ((ImmutableSet) PlaybackError.errorToErrorTypeMap.keySet()).contains(getMediaErrorCode(mediaErrorCode));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final MediaErrorCode getMediaErrorCode(MediaErrorCode mediaErrorCode) {
            return mediaErrorCode == null ? StandardErrorCode.PLAYBACK_RENDERER_ERROR : mediaErrorCode;
        }
    }

    static {
        DownloadExecutionErrorCode downloadExecutionErrorCode = DownloadExecutionErrorCode.BAD_DRM_RECORD;
        ErrorType errorType = ErrorType.ContentError;
        DownloadExecutionErrorCode downloadExecutionErrorCode2 = DownloadExecutionErrorCode.MISSING_ASSET_ID;
        DownloadExecutionErrorCode downloadExecutionErrorCode3 = DownloadExecutionErrorCode.MISSING_DASH_METADATA;
        DownloadExecutionErrorCode downloadExecutionErrorCode4 = DownloadExecutionErrorCode.MISSING_DRM_RECORD;
        DownloadExecutionErrorCode downloadExecutionErrorCode5 = DownloadExecutionErrorCode.NOT_READY_TO_WATCH;
        Map mapCheckFullKeyMapping = Preconditions2.checkFullKeyMapping(DownloadExecutionErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(downloadExecutionErrorCode, errorType), TuplesKt.to(downloadExecutionErrorCode2, errorType), TuplesKt.to(downloadExecutionErrorCode3, errorType), TuplesKt.to(downloadExecutionErrorCode4, errorType), TuplesKt.to(downloadExecutionErrorCode5, errorType)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping, "checkFullKeyMapping(\n   …          )\n            )");
        downloadExecutionErrorCodeToErrorTypeMap = (HashMap) mapCheckFullKeyMapping;
        Map mapCheckFullKeyMapping2 = Preconditions2.checkFullKeyMapping(DownloadExecutionErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(downloadExecutionErrorCode, 5007), TuplesKt.to(downloadExecutionErrorCode2, 5008), TuplesKt.to(downloadExecutionErrorCode3, 5009), TuplesKt.to(downloadExecutionErrorCode4, 5010), TuplesKt.to(downloadExecutionErrorCode5, 5011)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping2, "checkFullKeyMapping(\n   …          )\n            )");
        downloadExecutionErrorCodeToErrorCodeMap = (HashMap) mapCheckFullKeyMapping2;
        DownloadErrorCode downloadErrorCode = DownloadErrorCode.AV_MARKETPLACE_CHANGE;
        ErrorType errorType2 = ErrorType.PlayerError;
        DownloadErrorCode downloadErrorCode2 = DownloadErrorCode.DISK_FULL;
        DownloadErrorCode downloadErrorCode3 = DownloadErrorCode.EXTERNAL_DISK_FULL;
        DownloadErrorCode downloadErrorCode4 = DownloadErrorCode.EXTERNAL_STORAGE_NOT_WRITABLE;
        DownloadErrorCode downloadErrorCode5 = DownloadErrorCode.INTERNAL_DISK_FULL;
        DownloadErrorCode downloadErrorCode6 = DownloadErrorCode.INTERNAL_STORAGE_NOT_WRITABLE;
        DownloadErrorCode downloadErrorCode7 = DownloadErrorCode.NO_SERVER_ENTITLEMENTS;
        Map mapCheckFullKeyMapping3 = Preconditions2.checkFullKeyMapping(DownloadErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(downloadErrorCode, errorType2), TuplesKt.to(downloadErrorCode2, errorType2), TuplesKt.to(downloadErrorCode3, errorType2), TuplesKt.to(downloadErrorCode4, errorType2), TuplesKt.to(downloadErrorCode5, errorType2), TuplesKt.to(downloadErrorCode6, errorType2), TuplesKt.to(downloadErrorCode7, errorType2)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping3, "checkFullKeyMapping(\n   …          )\n            )");
        downloadErrorCodeToErrorTypeMap = (HashMap) mapCheckFullKeyMapping3;
        Map mapCheckFullKeyMapping4 = Preconditions2.checkFullKeyMapping(DownloadErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(downloadErrorCode, 5033), TuplesKt.to(downloadErrorCode2, Integer.valueOf(WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED)), TuplesKt.to(downloadErrorCode3, Integer.valueOf(WearableStatusCodes.UNSUPPORTED_BY_TARGET_NODE)), TuplesKt.to(downloadErrorCode4, Integer.valueOf(WearableStatusCodes.ACCOUNT_KEY_CREATION_FAILED)), TuplesKt.to(downloadErrorCode5, Integer.valueOf(WearableStatusCodes.MODEL_ID_UNAVAILABLE)), TuplesKt.to(downloadErrorCode6, 4012), TuplesKt.to(downloadErrorCode7, 5034)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping4, "checkFullKeyMapping(\n   …          )\n            )");
        downloadErrorCodeToErrorCodeMap = (HashMap) mapCheckFullKeyMapping4;
        ServiceErrorCode serviceErrorCode = ServiceErrorCode.CONCURRENCY_OFFLINE_ERROR;
        ServiceErrorCode serviceErrorCode2 = ServiceErrorCode.DOWNLOAD_NOT_OWNED;
        ServiceErrorCode serviceErrorCode3 = ServiceErrorCode.DOWNLOAD_NOT_OWNED_NON_TVOD;
        ServiceErrorCode serviceErrorCode4 = ServiceErrorCode.DOWNLOAD_NOT_OWNED_PURCHASE;
        ServiceErrorCode serviceErrorCode5 = ServiceErrorCode.DOWNLOAD_NOT_OWNED_RENTAL;
        ServiceErrorCode serviceErrorCode6 = ServiceErrorCode.GAME_BLACKOUT;
        ServiceErrorCode serviceErrorCode7 = ServiceErrorCode.HTTP_PROXY_ERROR;
        ServiceErrorCode serviceErrorCode8 = ServiceErrorCode.INVALID_GEO_IP;
        ServiceErrorCode serviceErrorCode9 = ServiceErrorCode.LOWER_TIER_CONCURRENCY_LIMIT;
        ServiceErrorCode serviceErrorCode10 = ServiceErrorCode.LOWER_TIER_PRIME_DEVICE_NOT_AUTHORIZED;
        ServiceErrorCode serviceErrorCode11 = ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS;
        ServiceErrorCode serviceErrorCode12 = ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS_PRIME;
        ServiceErrorCode serviceErrorCode13 = ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS_PURCHASE;
        ServiceErrorCode serviceErrorCode14 = ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS_RENTAL;
        ServiceErrorCode serviceErrorCode15 = ServiceErrorCode.NO_AVAILABLE_DOWNLOAD_RIGHTS_SUBSCRIPTION;
        ServiceErrorCode serviceErrorCode16 = ServiceErrorCode.NO_AVAILABLE_ONLINE_STREAMS;
        ServiceErrorCode serviceErrorCode17 = ServiceErrorCode.SERVICE_ERROR;
        ServiceErrorCode serviceErrorCode18 = ServiceErrorCode.TEMPORARILY_UNAVAILABLE;
        ServiceErrorCode serviceErrorCode19 = ServiceErrorCode.URL_ERROR;
        ServiceErrorCode serviceErrorCode20 = ServiceErrorCode.MISSING_PLAYBACK_ENVELOPE;
        ServiceErrorCode serviceErrorCode21 = ServiceErrorCode.EXPIRED_PLAYBACK_ENVELOPE;
        ServiceErrorCode serviceErrorCode22 = ServiceErrorCode.INVALID_PLAYBACK_ENVELOPE;
        Map mapCheckFullKeyMapping5 = Preconditions2.checkFullKeyMapping(ServiceErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(serviceErrorCode, errorType), TuplesKt.to(serviceErrorCode2, errorType), TuplesKt.to(serviceErrorCode3, errorType), TuplesKt.to(serviceErrorCode4, errorType), TuplesKt.to(serviceErrorCode5, errorType), TuplesKt.to(serviceErrorCode6, errorType), TuplesKt.to(serviceErrorCode7, errorType), TuplesKt.to(serviceErrorCode8, errorType2), TuplesKt.to(serviceErrorCode9, errorType2), TuplesKt.to(serviceErrorCode10, errorType2), TuplesKt.to(serviceErrorCode11, errorType), TuplesKt.to(serviceErrorCode12, errorType), TuplesKt.to(serviceErrorCode13, errorType), TuplesKt.to(serviceErrorCode14, errorType), TuplesKt.to(serviceErrorCode15, errorType), TuplesKt.to(serviceErrorCode16, errorType), TuplesKt.to(serviceErrorCode17, errorType), TuplesKt.to(serviceErrorCode18, errorType), TuplesKt.to(serviceErrorCode19, errorType), TuplesKt.to(serviceErrorCode20, errorType), TuplesKt.to(serviceErrorCode21, errorType), TuplesKt.to(serviceErrorCode22, errorType)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping5, "checkFullKeyMapping(\n   …          )\n            )");
        serviceErrorCodeToErrorTypeMap = (HashMap) mapCheckFullKeyMapping5;
        Map mapCheckFullKeyMapping6 = Preconditions2.checkFullKeyMapping(ServiceErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(serviceErrorCode, 5012), TuplesKt.to(serviceErrorCode2, 5035), TuplesKt.to(serviceErrorCode3, 5036), TuplesKt.to(serviceErrorCode4, 5037), TuplesKt.to(serviceErrorCode5, 5038), TuplesKt.to(serviceErrorCode6, 5039), TuplesKt.to(serviceErrorCode7, Integer.valueOf(WearableStatusCodes.DUPLICATE_CAPABILITY)), TuplesKt.to(serviceErrorCode8, 5006), TuplesKt.to(serviceErrorCode9, 5040), TuplesKt.to(serviceErrorCode10, 5041), TuplesKt.to(serviceErrorCode11, 5042), TuplesKt.to(serviceErrorCode12, 5043), TuplesKt.to(serviceErrorCode13, 5044), TuplesKt.to(serviceErrorCode14, 5045), TuplesKt.to(serviceErrorCode15, 5046), TuplesKt.to(serviceErrorCode16, 5047), TuplesKt.to(serviceErrorCode17, 5014), TuplesKt.to(serviceErrorCode18, 5048), TuplesKt.to(serviceErrorCode19, 5013), TuplesKt.to(serviceErrorCode20, 5015), TuplesKt.to(serviceErrorCode21, 5016), TuplesKt.to(serviceErrorCode22, 5017)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping6, "checkFullKeyMapping(\n   …          )\n            )");
        serviceErrorCodeToErrorCodeMap = (HashMap) mapCheckFullKeyMapping6;
        DrmErrorCode drmErrorCode = DrmErrorCode.DRM_AUTO_RESET;
        DrmErrorCode drmErrorCode2 = DrmErrorCode.DRM_FIELD_PROVISIONING_FAILURE;
        DrmErrorCode drmErrorCode3 = DrmErrorCode.DRM_FRAMEWORK_BUSY;
        DrmErrorCode drmErrorCode4 = DrmErrorCode.DRM_SCHEME_NOT_SUPPORTED;
        DrmErrorCode drmErrorCode5 = DrmErrorCode.DRM_SYSTEM_NEEDS_RESET;
        DrmErrorCode drmErrorCode6 = DrmErrorCode.LICENSE_DELETE_FAILURE;
        DrmErrorCode drmErrorCode7 = DrmErrorCode.LICENSE_ERROR;
        DrmErrorCode drmErrorCode8 = DrmErrorCode.LICENSE_EXPIRED;
        DrmErrorCode drmErrorCode9 = DrmErrorCode.LICENSE_EXPIRED_LICENSE_CLOCK;
        DrmErrorCode drmErrorCode10 = DrmErrorCode.LICENSE_EXPIRED_PLAYBACK_CLOCK;
        DrmErrorCode drmErrorCode11 = DrmErrorCode.LICENSE_EXPIRED_RENTAL;
        DrmErrorCode drmErrorCode12 = DrmErrorCode.LICENSE_EXPIRED_RENTAL_LICENSE_CLOCK;
        DrmErrorCode drmErrorCode13 = DrmErrorCode.LICENSE_EXPIRED_RENTAL_PLAYBACK_CLOCK;
        DrmErrorCode drmErrorCode14 = DrmErrorCode.LICENSE_GENERATE_CHALLENGE_FAILURE;
        DrmErrorCode drmErrorCode15 = DrmErrorCode.LICENSE_GET_RIGHTS_NULL;
        DrmErrorCode drmErrorCode16 = DrmErrorCode.LICENSE_INVALID_HEADER;
        DrmErrorCode drmErrorCode17 = DrmErrorCode.LICENSE_INVALID_KEY_COUNT;
        DrmErrorCode drmErrorCode18 = DrmErrorCode.LICENSE_INVALID_TYPE;
        DrmErrorCode drmErrorCode19 = DrmErrorCode.LICENSE_MALFORMED_RIGHTS_MAP;
        DrmErrorCode drmErrorCode20 = DrmErrorCode.LICENSE_MISMATCH_IN_SERVER_RESPONSE;
        DrmErrorCode drmErrorCode21 = DrmErrorCode.LICENSE_MISSING_USER_ID;
        DrmErrorCode drmErrorCode22 = DrmErrorCode.LICENSE_OFFER_UNAVAILABLE;
        DrmErrorCode drmErrorCode23 = DrmErrorCode.LICENSE_OPEN_SESSION_FAILURE;
        DrmErrorCode drmErrorCode24 = DrmErrorCode.LICENSE_PARSING_FAILURE;
        DrmErrorCode drmErrorCode25 = DrmErrorCode.LICENSE_PLAYBACK_NOT_ALLOWED;
        DrmErrorCode drmErrorCode26 = DrmErrorCode.LICENSE_PROCESS_RESPONSE_FAILURE;
        DrmErrorCode drmErrorCode27 = DrmErrorCode.LICENSE_PROCESS_RESPONSE_TIMEOUT;
        DrmErrorCode drmErrorCode28 = DrmErrorCode.LICENSE_QUERY_RIGHTS_FAILURE;
        DrmErrorCode drmErrorCode29 = DrmErrorCode.LICENSE_SERVICE_CALL_DEGRADED;
        DrmErrorCode drmErrorCode30 = DrmErrorCode.LICENSE_SERVICE_CALL_DENIED;
        DrmErrorCode drmErrorCode31 = DrmErrorCode.LICENSE_SERVICE_CALL_ERROR;
        DrmErrorCode drmErrorCode32 = DrmErrorCode.LICENSE_SERVICE_CALL_FAILURE;
        DrmErrorCode drmErrorCode33 = DrmErrorCode.LICENSE_SERVICE_CALL_MALFORMED_RESPONSE;
        DrmErrorCode drmErrorCode34 = DrmErrorCode.LICENSE_SERVICE_CALL_STREAMING_NOT_OWNED;
        DrmErrorCode drmErrorCode35 = DrmErrorCode.MISSING_OFFLINE_KEY_ID;
        DrmErrorCode drmErrorCode36 = DrmErrorCode.NO_LICENSE_AVAILABLE;
        DrmErrorCode drmErrorCode37 = DrmErrorCode.OFFLINE_LICENSE_MISSING;
        DrmErrorCode drmErrorCode38 = DrmErrorCode.ONLINE_LICENSE_MISSING;
        DrmErrorCode drmErrorCode39 = DrmErrorCode.PLAYREADY_CIPHERNOTINITIALIZED;
        DrmErrorCode drmErrorCode40 = DrmErrorCode.PLAYREADY_DRMNOTINIT;
        DrmErrorCode drmErrorCode41 = DrmErrorCode.PLAYREADY_HDCPFAIL;
        DrmErrorCode drmErrorCode42 = DrmErrorCode.PLAYREADY_INVALIDARG;
        DrmErrorCode drmErrorCode43 = DrmErrorCode.PLAYREADY_LICENSENOTFOUND;
        DrmErrorCode drmErrorCode44 = DrmErrorCode.SOFTWARE_PLAYREADY_INIT_FAILURE;
        DrmErrorCode drmErrorCode45 = DrmErrorCode.SOFTWARE_PLAYREADY_NATIVE_BUFFER_ALLOCATION_FAILURE;
        DrmErrorCode drmErrorCode46 = DrmErrorCode.SOFTWARE_PLAYREADY_NATIVE_BUFFER_OVERFLOW;
        DrmErrorCode drmErrorCode47 = DrmErrorCode.SOFTWARE_PLAYREADY_NATIVE_LIB_TIMEOUT;
        DrmErrorCode drmErrorCode48 = DrmErrorCode.SOFTWARE_PLAYREADY_SESSION_CREATE_FAILURE;
        DrmErrorCode drmErrorCode49 = DrmErrorCode.SYSTEM_CLOCK_ERROR;
        DrmErrorCode drmErrorCode50 = DrmErrorCode.UNKNOWN_PLAYREADY_ERROR;
        Map mapCheckFullKeyMapping7 = Preconditions2.checkFullKeyMapping(DrmErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(drmErrorCode, errorType2), TuplesKt.to(drmErrorCode2, errorType2), TuplesKt.to(drmErrorCode3, errorType2), TuplesKt.to(drmErrorCode4, errorType), TuplesKt.to(drmErrorCode5, errorType2), TuplesKt.to(drmErrorCode6, errorType2), TuplesKt.to(drmErrorCode7, errorType), TuplesKt.to(drmErrorCode8, errorType), TuplesKt.to(drmErrorCode9, errorType), TuplesKt.to(drmErrorCode10, errorType), TuplesKt.to(drmErrorCode11, errorType), TuplesKt.to(drmErrorCode12, errorType), TuplesKt.to(drmErrorCode13, errorType), TuplesKt.to(drmErrorCode14, errorType2), TuplesKt.to(drmErrorCode15, errorType), TuplesKt.to(drmErrorCode16, errorType), TuplesKt.to(drmErrorCode17, errorType), TuplesKt.to(drmErrorCode18, errorType), TuplesKt.to(drmErrorCode19, errorType), TuplesKt.to(drmErrorCode20, errorType), TuplesKt.to(drmErrorCode21, errorType), TuplesKt.to(drmErrorCode22, errorType), TuplesKt.to(drmErrorCode23, errorType2), TuplesKt.to(drmErrorCode24, errorType), TuplesKt.to(drmErrorCode25, errorType), TuplesKt.to(drmErrorCode26, errorType2), TuplesKt.to(drmErrorCode27, errorType2), TuplesKt.to(drmErrorCode28, errorType2), TuplesKt.to(drmErrorCode29, errorType2), TuplesKt.to(drmErrorCode30, errorType2), TuplesKt.to(drmErrorCode31, errorType2), TuplesKt.to(drmErrorCode32, errorType2), TuplesKt.to(drmErrorCode33, errorType2), TuplesKt.to(drmErrorCode34, errorType2), TuplesKt.to(drmErrorCode35, errorType), TuplesKt.to(drmErrorCode36, errorType), TuplesKt.to(drmErrorCode37, errorType), TuplesKt.to(drmErrorCode38, errorType), TuplesKt.to(drmErrorCode39, errorType2), TuplesKt.to(drmErrorCode40, errorType), TuplesKt.to(drmErrorCode41, errorType2), TuplesKt.to(drmErrorCode42, errorType2), TuplesKt.to(drmErrorCode43, errorType), TuplesKt.to(drmErrorCode44, errorType2), TuplesKt.to(drmErrorCode45, errorType2), TuplesKt.to(drmErrorCode46, errorType2), TuplesKt.to(drmErrorCode47, errorType2), TuplesKt.to(drmErrorCode48, errorType2), TuplesKt.to(drmErrorCode49, errorType2), TuplesKt.to(drmErrorCode50, errorType2)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping7, "checkFullKeyMapping(\n   …          )\n            )");
        drmErrorCodeToErrorTypeMap = (HashMap) mapCheckFullKeyMapping7;
        Map mapCheckFullKeyMapping8 = Preconditions2.checkFullKeyMapping(DrmErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(drmErrorCode, 3001), TuplesKt.to(drmErrorCode2, 3004), TuplesKt.to(drmErrorCode3, 3005), TuplesKt.to(drmErrorCode4, 3006), TuplesKt.to(drmErrorCode5, 3007), TuplesKt.to(drmErrorCode6, 3008), TuplesKt.to(drmErrorCode7, 3009), TuplesKt.to(drmErrorCode8, 3010), TuplesKt.to(drmErrorCode9, 3011), TuplesKt.to(drmErrorCode10, 3012), TuplesKt.to(drmErrorCode11, 3013), TuplesKt.to(drmErrorCode12, 3014), TuplesKt.to(drmErrorCode13, 3015), TuplesKt.to(drmErrorCode14, 3016), TuplesKt.to(drmErrorCode15, 3017), TuplesKt.to(drmErrorCode16, 3018), TuplesKt.to(drmErrorCode17, 3019), TuplesKt.to(drmErrorCode18, 3020), TuplesKt.to(drmErrorCode19, 3021), TuplesKt.to(drmErrorCode20, 3002), TuplesKt.to(drmErrorCode21, 3022), TuplesKt.to(drmErrorCode22, 3023), TuplesKt.to(drmErrorCode23, 3024), TuplesKt.to(drmErrorCode24, 3025), TuplesKt.to(drmErrorCode25, 3026), TuplesKt.to(drmErrorCode26, 3027), TuplesKt.to(drmErrorCode27, 3028), TuplesKt.to(drmErrorCode28, 3029), TuplesKt.to(drmErrorCode29, 3030), TuplesKt.to(drmErrorCode30, 3031), TuplesKt.to(drmErrorCode31, 3032), TuplesKt.to(drmErrorCode32, 3033), TuplesKt.to(drmErrorCode33, 3034), TuplesKt.to(drmErrorCode34, 3035), TuplesKt.to(drmErrorCode35, 5049), TuplesKt.to(drmErrorCode36, 3036), TuplesKt.to(drmErrorCode37, 3037), TuplesKt.to(drmErrorCode38, 3038), TuplesKt.to(drmErrorCode39, 3039), TuplesKt.to(drmErrorCode40, 3050), TuplesKt.to(drmErrorCode41, 3003), TuplesKt.to(drmErrorCode42, 3040), TuplesKt.to(drmErrorCode43, 3041), TuplesKt.to(drmErrorCode44, 3051), TuplesKt.to(drmErrorCode45, 3042), TuplesKt.to(drmErrorCode46, 3043), TuplesKt.to(drmErrorCode47, 3044), TuplesKt.to(drmErrorCode48, 3045), TuplesKt.to(drmErrorCode49, Integer.valueOf(WearableStatusCodes.DUPLICATE_LISTENER)), TuplesKt.to(drmErrorCode50, 3046)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping8, "checkFullKeyMapping(\n   …          )\n            )");
        drmErrorCodeToErrorCodeMap = (HashMap) mapCheckFullKeyMapping8;
        StandardErrorCode standardErrorCode = StandardErrorCode.AD_ERROR;
        StandardErrorCode standardErrorCode2 = StandardErrorCode.ANDROID_MEDIAPLAYER_ERROR;
        StandardErrorCode standardErrorCode3 = StandardErrorCode.AUTO_EVAL_CANARY_FAILURE;
        StandardErrorCode standardErrorCode4 = StandardErrorCode.AUTO_EVAL_POOR_PLAYER_PERFORMANCE;
        StandardErrorCode standardErrorCode5 = StandardErrorCode.CDN_ERROR;
        StandardErrorCode standardErrorCode6 = StandardErrorCode.CONTENT_ERROR;
        StandardErrorCode standardErrorCode7 = StandardErrorCode.DATA_CONNECTION_UNAVAILABLE;
        StandardErrorCode standardErrorCode8 = StandardErrorCode.DECRYPTION_FAILURE;
        StandardErrorCode standardErrorCode9 = StandardErrorCode.DECRYPTION_FAILURE_EXPIRED_KEY;
        StandardErrorCode standardErrorCode10 = StandardErrorCode.DECRYPTION_FAILURE_MISSING_KEY;
        StandardErrorCode standardErrorCode11 = StandardErrorCode.DEVICE_REBOOT_REQUIRED;
        StandardErrorCode standardErrorCode12 = StandardErrorCode.DISK_FULL;
        StandardErrorCode standardErrorCode13 = StandardErrorCode.DISK_IO_ERROR;
        StandardErrorCode standardErrorCode14 = StandardErrorCode.DOWNLOAD_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT;
        StandardErrorCode standardErrorCode15 = StandardErrorCode.DOWNLOAD_UNKNOWN_ERROR;
        StandardErrorCode standardErrorCode16 = StandardErrorCode.FILE_MISSING;
        StandardErrorCode standardErrorCode17 = StandardErrorCode.IMAGE_VIDEO_PLAYER_ERROR;
        StandardErrorCode standardErrorCode18 = StandardErrorCode.INSUFFICIENT_DISK_SPACE;
        StandardErrorCode standardErrorCode19 = StandardErrorCode.LOW_MEMORY_ERROR;
        StandardErrorCode standardErrorCode20 = StandardErrorCode.MANIFEST_ERROR;
        StandardErrorCode standardErrorCode21 = StandardErrorCode.MEDIA_EJECTED;
        StandardErrorCode standardErrorCode22 = StandardErrorCode.MEDIA_EXCEPTION;
        StandardErrorCode standardErrorCode23 = StandardErrorCode.MEMORY_ACCESS_ERROR;
        StandardErrorCode standardErrorCode24 = StandardErrorCode.NATIVE_PLAYBACK_ERROR;
        StandardErrorCode standardErrorCode25 = StandardErrorCode.NETWORK_ERROR;
        StandardErrorCode standardErrorCode26 = StandardErrorCode.NOT_ENTITLED;
        StandardErrorCode standardErrorCode27 = StandardErrorCode.OVERLAPPING_FRAGMENT;
        StandardErrorCode standardErrorCode28 = StandardErrorCode.PLAYBACK_RENDERER_ERROR;
        StandardErrorCode standardErrorCode29 = StandardErrorCode.PREPARE_FAILED_CACHE_FULL;
        StandardErrorCode standardErrorCode30 = StandardErrorCode.PREPARE_FAILED_LOCK_NOT_HELD;
        StandardErrorCode standardErrorCode31 = StandardErrorCode.SAMPLE_ERROR;
        StandardErrorCode standardErrorCode32 = StandardErrorCode.STREAMING_PLAYBACK_RESTRICTED_TO_BUFFERED_CONTENT;
        StandardErrorCode standardErrorCode33 = StandardErrorCode.UNKNOWN_ERROR;
        Map mapCheckFullKeyMapping9 = Preconditions2.checkFullKeyMapping(StandardErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(standardErrorCode, errorType2), TuplesKt.to(standardErrorCode2, errorType2), TuplesKt.to(standardErrorCode3, errorType2), TuplesKt.to(standardErrorCode4, errorType2), TuplesKt.to(standardErrorCode5, errorType), TuplesKt.to(standardErrorCode6, errorType), TuplesKt.to(standardErrorCode7, errorType2), TuplesKt.to(standardErrorCode8, errorType), TuplesKt.to(standardErrorCode9, errorType), TuplesKt.to(standardErrorCode10, errorType), TuplesKt.to(standardErrorCode11, errorType2), TuplesKt.to(standardErrorCode12, errorType2), TuplesKt.to(standardErrorCode13, errorType2), TuplesKt.to(standardErrorCode14, errorType2), TuplesKt.to(standardErrorCode15, errorType2), TuplesKt.to(standardErrorCode16, errorType), TuplesKt.to(standardErrorCode17, errorType2), TuplesKt.to(standardErrorCode18, errorType2), TuplesKt.to(standardErrorCode19, errorType2), TuplesKt.to(standardErrorCode20, errorType), TuplesKt.to(standardErrorCode21, errorType2), TuplesKt.to(standardErrorCode22, errorType2), TuplesKt.to(standardErrorCode23, errorType2), TuplesKt.to(standardErrorCode24, errorType2), TuplesKt.to(standardErrorCode25, errorType), TuplesKt.to(standardErrorCode26, errorType), TuplesKt.to(standardErrorCode27, errorType), TuplesKt.to(standardErrorCode28, errorType2), TuplesKt.to(standardErrorCode29, errorType2), TuplesKt.to(standardErrorCode30, errorType2), TuplesKt.to(standardErrorCode31, errorType), TuplesKt.to(standardErrorCode32, errorType2), TuplesKt.to(standardErrorCode33, errorType2)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping9, "checkFullKeyMapping(\n   …          )\n            )");
        standardErrorCodeToErrorTypeMap = (HashMap) mapCheckFullKeyMapping9;
        Map mapCheckFullKeyMapping10 = Preconditions2.checkFullKeyMapping(StandardErrorCode.class, MapsKt__MapsKt.hashMapOf(TuplesKt.to(standardErrorCode, 5018), TuplesKt.to(standardErrorCode2, Integer.valueOf(ComposeVersion.version)), TuplesKt.to(standardErrorCode3, 4015), TuplesKt.to(standardErrorCode4, 4016), TuplesKt.to(standardErrorCode5, Integer.valueOf(PointerIconCompat.TYPE_HAND)), TuplesKt.to(standardErrorCode6, 5019), TuplesKt.to(standardErrorCode7, Integer.valueOf(PointerIconCompat.TYPE_HELP)), TuplesKt.to(standardErrorCode8, 3047), TuplesKt.to(standardErrorCode9, 3048), TuplesKt.to(standardErrorCode10, 3049), TuplesKt.to(standardErrorCode11, 3052), TuplesKt.to(standardErrorCode12, Integer.valueOf(WearableStatusCodes.WIFI_CREDENTIAL_SYNC_NO_CREDENTIAL_FETCHED)), TuplesKt.to(standardErrorCode13, 4017), TuplesKt.to(standardErrorCode14, 5020), TuplesKt.to(standardErrorCode15, 4013), TuplesKt.to(standardErrorCode16, 5004), TuplesKt.to(standardErrorCode17, 5021), TuplesKt.to(standardErrorCode18, 4014), TuplesKt.to(standardErrorCode19, 3053), TuplesKt.to(standardErrorCode20, 5022), TuplesKt.to(standardErrorCode21, Integer.valueOf(WearableStatusCodes.UNKNOWN_CAPABILITY)), TuplesKt.to(standardErrorCode22, 5023), TuplesKt.to(standardErrorCode23, 5024), TuplesKt.to(standardErrorCode24, 5025), TuplesKt.to(standardErrorCode25, Integer.valueOf(PointerIconCompat.TYPE_WAIT)), TuplesKt.to(standardErrorCode26, 5002), TuplesKt.to(standardErrorCode27, 5026), TuplesKt.to(standardErrorCode28, 5027), TuplesKt.to(standardErrorCode29, 5028), TuplesKt.to(standardErrorCode30, 5029), TuplesKt.to(standardErrorCode31, 5030), TuplesKt.to(standardErrorCode32, 5031), TuplesKt.to(standardErrorCode33, 5032)));
        Intrinsics.checkNotNullExpressionValue(mapCheckFullKeyMapping10, "checkFullKeyMapping(\n   …          )\n            )");
        standardErrorCodeToErrorCodeMap = (HashMap) mapCheckFullKeyMapping10;
        PlaylistFeatureImpl.PlaylistErrorCode[] playlistErrorCodeArrValues = PlaylistFeatureImpl.PlaylistErrorCode.values();
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(playlistErrorCodeArrValues.length), 16));
        int length = playlistErrorCodeArrValues.length;
        int i = 0;
        while (i < length) {
            PlaylistFeatureImpl.PlaylistErrorCode playlistErrorCode = playlistErrorCodeArrValues[i];
            i++;
            Pair pair = TuplesKt.to(playlistErrorCode, ErrorType.PlaylistError);
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        playlistErrorToErrorTypeMap = linkedHashMap;
        PlaylistFeatureImpl.PlaylistErrorCode[] playlistErrorCodeArrValues2 = PlaylistFeatureImpl.PlaylistErrorCode.values();
        LinkedHashMap linkedHashMap2 = new LinkedHashMap(RangesKt___RangesKt.coerceAtLeast(MapsKt__MapsJVMKt.mapCapacity(playlistErrorCodeArrValues2.length), 16));
        int length2 = playlistErrorCodeArrValues2.length;
        int i2 = 0;
        while (i2 < length2) {
            PlaylistFeatureImpl.PlaylistErrorCode playlistErrorCode2 = playlistErrorCodeArrValues2[i2];
            i2++;
            Pair pair2 = TuplesKt.to(playlistErrorCode2, Integer.valueOf(playlistErrorCode2.getNumberCode()));
            linkedHashMap2.put(pair2.getFirst(), pair2.getSecond());
        }
        playlistErrorToErrorCodeMap = linkedHashMap2;
        ImmutableMap<MediaErrorCode, ErrorType> immutableMapBuild = ImmutableMap.builder().putAll(downloadExecutionErrorCodeToErrorTypeMap).putAll(downloadErrorCodeToErrorTypeMap).putAll(serviceErrorCodeToErrorTypeMap).putAll(drmErrorCodeToErrorTypeMap).putAll(standardErrorCodeToErrorTypeMap).putAll(playlistErrorToErrorTypeMap).build();
        Intrinsics.checkNotNullExpressionValue(immutableMapBuild, "builder<MediaErrorCode, …\n                .build()");
        errorToErrorTypeMap = immutableMapBuild;
        ImmutableMap<MediaErrorCode, Integer> immutableMapBuild2 = ImmutableMap.builder().putAll(downloadExecutionErrorCodeToErrorCodeMap).putAll(downloadErrorCodeToErrorCodeMap).putAll(serviceErrorCodeToErrorCodeMap).putAll(drmErrorCodeToErrorCodeMap).putAll(standardErrorCodeToErrorCodeMap).putAll(linkedHashMap2).build();
        Intrinsics.checkNotNullExpressionValue(immutableMapBuild2, "builder<MediaErrorCode, …\n                .build()");
        mediaErrorCodeToErrorCodeMap = immutableMapBuild2;
    }

    private final Triple<ErrorDomain, Integer, Integer> getErrorCodeInfo(int errorCode) {
        Preconditions.checkArgument(this.errorCodeToErrorCodeInfoMap.containsKey(Integer.valueOf(errorCode)));
        Triple<ErrorDomain, Integer, Integer> triple = this.errorCodeToErrorCodeInfoMap.get(Integer.valueOf(errorCode));
        Intrinsics.checkNotNull(triple);
        Intrinsics.checkNotNullExpressionValue(triple, "errorCodeToErrorCodeInfoMap[errorCode]!!");
        return triple;
    }

    public String toString() {
        return "PlaybackError(errorCode=" + getErrorCode() + ", errorDomain=" + getErrorDomain() + ", errorDescriptionText='" + this.errorDescriptionText + "', errorMessageText='" + this.errorMessageText + "')";
    }
}
