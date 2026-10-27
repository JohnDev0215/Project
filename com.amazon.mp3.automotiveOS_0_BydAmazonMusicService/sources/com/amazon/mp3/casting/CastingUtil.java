package com.amazon.mp3.casting;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.generator.LocalLuidGenerator;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.recentlyplayed.RecentTrack;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.service.ServiceUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.casting.CastingCategory;
import com.amazon.music.casting.CastingDevice;
import com.amazon.music.casting.session.CastingSessionManager;
import com.amazon.music.find.model.search.SearchItem;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.MediaItemId;
import com.amazon.music.metrics.mts.event.types.TerminationReason;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.List;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes3.dex */
public class CastingUtil {
    public static String QUEUE_ID = "queueId";
    public static String SINGLE_ACTIVE_STREAM = "getSingleActiveStream";
    public static String SKILL_TYPE = "skillType";
    public static String TRANSFER_PLAYBACK = "transferPlayback";

    public static boolean isSupportedContent(final CatalogContent catalogContent) {
        if (catalogContent instanceof AbstractItem) {
            AbstractItem abstractItem = (AbstractItem) catalogContent;
            return isSupportedContent(abstractItem.getAsin(), abstractItem.getSource(), !catalogContent.isAllOwned());
        }
        if (catalogContent instanceof SearchItem) {
            SearchItem searchItem = (SearchItem) catalogContent;
            return isSupportedContent(searchItem.getAsin(), searchItem.getSource(), catalogContent.isAllOwned());
        }
        if (catalogContent instanceof RecentlyPlayedItem) {
            RecentlyPlayedItem recentlyPlayedItem = (RecentlyPlayedItem) catalogContent;
            if (recentlyPlayedItem.getTrackAsin() != null || (recentlyPlayedItem.getTrackLuid() != null && !LocalLuidGenerator.isLocalLuid(recentlyPlayedItem.getTrackLuid()))) {
                return true;
            }
        }
        if (!(catalogContent instanceof RecentTrack)) {
            return false;
        }
        RecentTrack recentTrack = (RecentTrack) catalogContent;
        return (recentTrack.getAsin() == null && (recentTrack.getLuid() == null || LocalLuidGenerator.isLocalLuid(recentTrack.getLuid()))) ? false : true;
    }

    public static boolean isSupportedContent(final String asin, final String source, final boolean isDrm) {
        return ("cirrus-local".equals(source) && !isDrm && asin == null) ? false : true;
    }

    public static boolean isSupportedContent(final MediaItem mediaItem) {
        return mediaItem.getMediaItemId(MediaItemId.Type.PID) == null;
    }

    public static boolean isSupportedContent(final List<ContentMetadata> contentMetadataList) {
        ContentMetadata contentMetadata;
        return contentMetadataList == null || (contentMetadata = contentMetadataList.get(0)) == null || !(contentMetadata instanceof TrackMetadata) || !"VIDEO".equals(((TrackMetadata) contentMetadata).getAssetType());
    }

    public static boolean isPlayQueueSupported() {
        return !isCasting() || CastingCategory.CHROMECAST.equals(getDeviceCategory());
    }

    public static boolean isCasting() {
        CastingSessionManager castingSessionManager = CastingSessionManager.getInstance();
        return castingSessionManager.isInitialized() && castingSessionManager.isConnected() && castingSessionManager.getCastingDevice() != null;
    }

    public static boolean isCastingToAlexa() {
        return isCasting() && !CastingCategory.CHROMECAST.equals(getDeviceCategory());
    }

    public static boolean isCastingToChromecast() {
        return isCasting() && CastingCategory.CHROMECAST.equals(getDeviceCategory());
    }

    public static boolean isCastingAvailable() {
        return (UserSubscriptionUtil.isNightwingOnly() || !AmazonApplication.getCapabilities().isCastingEnabled() || NowPlayingUtil.isAlexaPlaying()) ? false : true;
    }

    public static boolean isAlexaCastingEnabled() {
        return AmazonApplication.getCapabilities().canAccessCasting() && !UserSubscriptionUtil.isNightwingOnly();
    }

    public static boolean isAoMAllowed() {
        return !CastingSessionManager.getInstance().isConnected() || CastingCategory.CHROMECAST.equals(getDeviceCategory());
    }

    public static CastingCategory getDeviceCategory() {
        CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
        return (castingDevice == null || castingDevice.getCategory() == null) ? CastingCategory.UNDEFINED : castingDevice.getCategory();
    }

    public static String getCastingDeviceName(final Context context) {
        return getDeviceName(context, CastingSessionManager.getInstance().getCastingDevice());
    }

    public static String getLastCastingDeviceName(final Context context) {
        return getDeviceName(context, CastingSessionManager.getInstance().getLastDevice());
    }

    public static String getDeviceName(final Context context, final CastingDevice device) {
        if (device != null && !TextUtils.isEmpty(device.getName())) {
            return device.getName();
        }
        if (context == null || !isCasting()) {
            return "";
        }
        if (isCastingToAlexa()) {
            return context.getString(R.string.casting_alexa_device_text);
        }
        return context.getString(R.string.casting_chromecast_device_text);
    }

    public static String getCastingDeviceTypeId() {
        CastingDevice castingDevice = CastingSessionManager.getInstance().getCastingDevice();
        if (castingDevice != null) {
            return castingDevice.getDeviceTypeId();
        }
        return null;
    }

    public static void clearSession() {
        CastingSessionManager.getInstance().disconnect(TerminationReason.USER_DISCONNECT);
        CastingSessionManager.getInstance().clearSession();
    }

    public static void showCastingContentUnavailable(final Context context) {
        context.startActivity(CastingAlertDialogActivity.getCastingAlertIntentForContentUnavailable(context));
    }

    public static void setForegroundAutoConnectInvoke(final Context context) {
        AmazonApplication.subscribeForegroundObserver(new Action1() { // from class: com.amazon.mp3.casting.CastingUtil$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CastingUtil.lambda$setForegroundAutoConnectInvoke$0(context, (Void) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.casting.CastingUtil$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CastingUtil.lambda$setForegroundAutoConnectInvoke$1((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setForegroundAutoConnectInvoke$0(Context context, Void r3) {
        if (!PlaybackControllerProvider.getController(ControlSource.APP_UI).getPlayStatus().shouldBePlaying()) {
            Intent intent = new Intent(context, (Class<?>) CastingSkillService.class);
            intent.putExtra(SKILL_TYPE, SINGLE_ACTIVE_STREAM);
            ServiceUtil.startService(context, intent);
        }
        setBackgroundAutoConnectInvoke(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setForegroundAutoConnectInvoke$1(Throwable th) {
        Log.error(CastingUtil.class.getSimpleName(), "Error:" + th);
    }

    public static void setBackgroundAutoConnectInvoke(final Context context) {
        AmazonApplication.subscribeBackgroundObserver(new Action1() { // from class: com.amazon.mp3.casting.CastingUtil$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CastingUtil.setForegroundAutoConnectInvoke(context);
            }
        }, new Action1() { // from class: com.amazon.mp3.casting.CastingUtil$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                CastingUtil.lambda$setBackgroundAutoConnectInvoke$3((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$setBackgroundAutoConnectInvoke$3(Throwable th) {
        Log.error(CastingUtil.class.getSimpleName(), "Error:" + th);
    }

    public static String getLastCastingDeviceTargetId() {
        return CastingSessionManager.getInstance().getCastingDevice().getTargetId();
    }
}
