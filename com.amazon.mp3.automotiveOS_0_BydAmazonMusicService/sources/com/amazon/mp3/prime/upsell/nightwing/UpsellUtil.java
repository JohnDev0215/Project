package com.amazon.mp3.prime.upsell.nightwing;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifierType;
import com.amazon.mp3.dialog.fragment.HawkfireAllDevicesUpsellDialogFragment;
import com.amazon.mp3.hawkfire.upsell.UnlimitedUpsellActivity;
import com.amazon.mp3.inappmessaging.DynamicMessagingManager;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.prime.upsell.nightwing.client.UpsellClient;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellReason;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellSourceEntity;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellUIConfig;
import com.amazon.mp3.recentlyplayed.RecentlyPlayedItem;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.upsellweb.SettingsPageWebTargetBuilderFactory;
import com.amazon.mp3.util.DynamicUpsellTranslator;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.mp3.web.webtarget.WebTarget;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.Trigger;
import com.amazon.music.media.playback.MediaCollectionType;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.metrics.mts.event.definition.flex.FlexEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.subscription.UserSubscription;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class UpsellUtil {
    private static final String TAG = StringUtil.class.getSimpleName();

    public static void startStationFromAnything(final Context context, final List<String> asinList, final UpsellSourceEntity sourceEntity, final MediaCollectionType collectionType, final boolean startFullscreen) {
        if (asinList == null || asinList.isEmpty()) {
            Log.error(TAG, "Empty asinList passed to start SFA, ignoring");
            return;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < Math.min(asinList.size(), 100); i++) {
            arrayList.add(new PlayableEntityIdentifier(asinList.get(i), PlayableEntityIdentifierType.ASIN));
        }
        startStation(context, arrayList, getUpsellInformation(arrayList, UpsellReason.PLAY, sourceEntity), collectionType, getPlaybackPageType(sourceEntity), false, startFullscreen);
    }

    private static void startStation(final Context context, final List<PlayableEntityIdentifier> identifiers, final UpsellInformation upsellInformation, final MediaCollectionType collectionType, final PlaybackPageType playbackPageType, final boolean isOnDemandPlayMode, final boolean startFullscreen) {
        StationUtils.startSFA(context, identifiers, playbackPageType, upsellInformation);
    }

    private static PlaybackPageType getPlaybackPageType(@NonNull final UpsellSourceEntity entity) {
        int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity[entity.ordinal()];
        if (i == 1) {
            return PlaybackPageType.ALBUM_DETAIL;
        }
        if (i == 2) {
            return PlaybackPageType.PLAYLIST_DETAIL;
        }
        return PlaybackPageType.SFA;
    }

    public static UpsellInformation getPlaybackUpsellInformation(@NonNull final UpsellSourceEntity entity, @NonNull final Object item) {
        UpsellClient upsellClient = new UpsellClient();
        upsellClient.setSeedName(UpsellManager.getInstance().getSeedName(item));
        upsellClient.setArtistName(UpsellManager.getInstance().getArtistName(item));
        upsellClient.setUpsellReason(UpsellReason.PLAY);
        upsellClient.setUpsellSourceEntity(entity);
        return upsellClient.buildUpsell();
    }

    public static UpsellInformation getPlaybackUpsellInformationForTrack(@NonNull final UpsellSourceEntity entity, @NonNull final Object item, @NonNull final Context context) {
        UpsellClient upsellClient = new UpsellClient();
        String itemUrl = UpsellManager.getInstance().getItemUrl(context, item);
        upsellClient.setSeedName(UpsellManager.getInstance().getSeedName(item));
        upsellClient.setArtistName(UpsellManager.getInstance().getArtistName(item));
        upsellClient.setItemUrl(itemUrl);
        upsellClient.setUpsellReason(UpsellReason.PLAY);
        upsellClient.setUpsellSourceEntity(entity);
        return upsellClient.buildUpsell();
    }

    public static UpsellInformation getUpsellInformation(@Nullable final String asin, @NonNull final UpsellReason reason, @NonNull final UpsellSourceEntity entity) {
        UpsellClient upsellClient = new UpsellClient();
        if (asin != null) {
            upsellClient.setPlayableEntityIdentifierList(Collections.singletonList(new PlayableEntityIdentifier(asin, PlayableEntityIdentifierType.ASIN)));
        }
        upsellClient.setUpsellReason(reason);
        upsellClient.setUpsellSourceEntity(entity);
        return upsellClient.buildUpsell();
    }

    public static UpsellInformation getUpsellInformation(@Nullable final MusicTrack musicTrack, @NonNull final Context context, @NonNull final UpsellReason reason, @NonNull final UpsellSourceEntity entity) {
        UpsellClient upsellClient = new UpsellClient();
        if (musicTrack != null) {
            upsellClient.setPlayableEntityIdentifierList(Collections.singletonList(new PlayableEntityIdentifier(musicTrack.getAsin(), PlayableEntityIdentifierType.ASIN)));
            upsellClient.setItemUrl(UpsellManager.getInstance().getItemUrl(context, musicTrack));
        }
        upsellClient.setUpsellReason(reason);
        upsellClient.setUpsellSourceEntity(entity);
        return upsellClient.buildUpsell();
    }

    public static UpsellInformation getUpsellInformation(@Nullable List<PlayableEntityIdentifier> playableEntityIdentifierList, @NonNull final UpsellReason reason, @NonNull final UpsellSourceEntity entity) {
        UpsellClient upsellClient = new UpsellClient();
        if (playableEntityIdentifierList != null) {
            upsellClient.setPlayableEntityIdentifierList(playableEntityIdentifierList);
        }
        upsellClient.setUpsellReason(reason);
        upsellClient.setUpsellSourceEntity(entity);
        return upsellClient.buildUpsell();
    }

    public static UpsellInformation getUpsellInformation(@Nullable List<PlayableEntityIdentifier> playableEntityIdentifierList, @NonNull final UpsellReason reason, @NonNull final UpsellSourceEntity entity, final String seedName, final String itemUrl) {
        UpsellClient upsellClient = new UpsellClient();
        if (playableEntityIdentifierList != null) {
            upsellClient.setPlayableEntityIdentifierList(playableEntityIdentifierList);
        }
        upsellClient.setUpsellReason(reason);
        upsellClient.setUpsellSourceEntity(entity);
        upsellClient.setSeedName(seedName);
        upsellClient.setItemUrl(itemUrl);
        return upsellClient.buildUpsell();
    }

    public static UpsellInformation getUpsellInformation(@Nullable List<PlayableEntityIdentifier> playableEntityIdentifierList, @Nullable List<String> tags, @NonNull final UpsellReason reason, @NonNull final UpsellSourceEntity entity, final String seedName, final String artistName, final String albumAsin, final String itemUrl, final String trackAsinQueryParameter) {
        UpsellClient upsellClient = new UpsellClient();
        if (playableEntityIdentifierList != null) {
            upsellClient.setPlayableEntityIdentifierList(playableEntityIdentifierList);
        }
        upsellClient.setTags(tags);
        upsellClient.setUpsellReason(reason);
        upsellClient.setUpsellSourceEntity(entity);
        upsellClient.setSeedName(seedName);
        upsellClient.setArtistName(artistName);
        upsellClient.setAlbumAsin(albumAsin);
        upsellClient.setItemUrl(itemUrl);
        upsellClient.setTrackAsinQueryParameter(trackAsinQueryParameter);
        return upsellClient.buildUpsell();
    }

    public static void showNonBlockingUpsell(final FragmentController fragmentController, final FragmentActivity fragmentActivity, @Nullable final String asin, final UpsellReason reason, final UpsellSourceEntity entity) {
        if (UpsellReason.PERSISTENT_UPSELL.equals(reason) || UpsellReason.PERSISTENT_UPGRADE.equals(reason)) {
            UpsellInformation upsellInformation = getUpsellInformation(asin, reason, entity);
            emitEligibilityCheckRequestedMetric(upsellInformation);
            UpsellManager.getInstance().showNonBlockingDynamicMessageOneClick(fragmentController, fragmentActivity, Trigger.PERSISTENT_UPSELL, new DynamicMessageMetricsAttributes(PageType.PERSISTENT_UPSELL_PAGE.getMetricsValue(), upsellInformation.getUpsellReason().getValue()));
            if (UpsellReason.PERSISTENT_UPGRADE.equals(reason)) {
                emitPersistentUpgradePageViewedMetric(true, true);
            } else {
                emitPersistentUpsellPageViewedMetric();
            }
        }
    }

    public static void showBlockingUpsell(final FragmentActivity fragmentActivity, @Nullable final String asin, final UpsellReason reason, final UpsellSourceEntity entity) {
        if (UserSubscriptionUtil.getUserSubscription().isHomeOnly()) {
            showDialog(fragmentActivity, new HawkfireAllDevicesUpsellDialogFragment());
        } else {
            showBlockingUpsell(fragmentActivity, getUpsellInformation(asin, reason, entity));
        }
    }

    public static void showBlockingUpsell(final FragmentActivity fragmentActivity, final UpsellInformation upsellInformation) {
        UpsellReason upsellReason = upsellInformation.getUpsellReason();
        DynamicMessageMetricsAttributes dynamicMessageMetricsAttributes = new DynamicMessageMetricsAttributes(getUpsellPage(upsellReason).getMetricsValue(), upsellReason.getValue());
        if (DynamicMessagingManager.isDynamicMessagingSupported()) {
            emitEligibilityCheckRequestedMetric(upsellInformation);
            UpsellManager.getInstance().showDynamicMessageOneClick(fragmentActivity, DynamicUpsellTranslator.getUpsellTrigger(upsellReason), dynamicMessageMetricsAttributes);
            int i = AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[upsellReason.ordinal()];
            if (i == 1) {
                emitPersistentUpsellPageViewedMetric();
                return;
            } else {
                if (i != 2) {
                    return;
                }
                emitPersistentUpgradePageViewedMetric(true, false);
                return;
            }
        }
        DialogFragment blockingUpsellFragment = UpsellManager.getInstance().getBlockingUpsellFragment(fragmentActivity, upsellInformation, dynamicMessageMetricsAttributes);
        if (blockingUpsellFragment != null) {
            emitEligibilityCheckRequestedMetric(upsellInformation);
            blockingUpsellFragment.show(fragmentActivity.getSupportFragmentManager(), "");
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.prime.upsell.nightwing.UpsellUtil$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity;

        static {
            int[] iArr = new int[UpsellReason.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason = iArr;
            try {
                iArr[UpsellReason.PERSISTENT_UPSELL.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.PERSISTENT_UPGRADE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.PLAY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.TRACK_NEXT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.TRACK_SKIP_LIMIT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.AD_SKIP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.SHUFFLE_ALL.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.DOWNLOAD_CONTENT.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.ADD_TO_LIBRARY.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.ADD_TO_PLAY_QUEUE.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.TRACK_SEEK.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.TRACK_PREVIOUS.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.DEEPLINK_SIGNUP.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[UpsellReason.BROWSE_ALBUM.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            int[] iArr2 = new int[UpsellSourceEntity.values().length];
            $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity = iArr2;
            try {
                iArr2[UpsellSourceEntity.ALBUM_PAGE.ordinal()] = 1;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellSourceEntity[UpsellSourceEntity.PLAYLIST_DETAIL.ordinal()] = 2;
            } catch (NoSuchFieldError unused16) {
            }
        }
    }

    public static void showPlanComparisonUpgradeUpsell(final FragmentActivity fragmentActivity) {
        WebTarget planComparisonPageWebTarget = getPlanComparisonPageWebTarget(fragmentActivity);
        Intent intent = new Intent(fragmentActivity, (Class<?>) UnlimitedUpsellActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("com.amazon.mp3.extra.WebTargetKey.EXTRA_WEB_TARGET", planComparisonPageWebTarget);
        bundle.putSerializable(UnlimitedUpsellActivity.REF_MARKER_KEY, "sdp_to_individual_upgrade");
        intent.putExtras(bundle);
        fragmentActivity.startActivity(intent);
    }

    private static WebTarget getPlanComparisonPageWebTarget(final FragmentActivity fragmentActivity) {
        return new SettingsPageWebTargetBuilderFactory(fragmentActivity.getBaseContext()).newPlanComparisonViewBuilder().build();
    }

    private static void showDialog(final FragmentActivity activity, final DialogFragment dialog) {
        FragmentTransaction fragmentTransactionBeginTransaction = activity.getSupportFragmentManager().beginTransaction();
        fragmentTransactionBeginTransaction.add(dialog, (String) null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    public static void emitEligibilityCheckRequestedMetric(final UpsellInformation upsellInformation) {
        if (upsellInformation != null) {
            String string = upsellInformation.getPlayableEntityIdentifierList() != null ? upsellInformation.getPlayableEntityIdentifierList().get(0).getIdentifier().toString() : "defaultAsin";
            String metricNameFromUpsellReason = getMetricNameFromUpsellReason(upsellInformation.getUpsellReason());
            String value = upsellInformation.getUpsellSourceEntity() != null ? upsellInformation.getUpsellSourceEntity().getValue() : "unknownSource";
            MetricsLogger.eligibilityCheckRequestedEvent(metricNameFromUpsellReason, value, string, value.equals(UpsellSourceEntity.STATION.getValue()) ? "STATION_KEY" : "ASIN", getEligibilityCheckResponseReason());
            return;
        }
        MetricsLogger.eligibilityCheckRequestedEvent("unknownUpsellReason", "unknownSource", "defaultAsin", "unknownEntityType", getEligibilityCheckResponseReason());
    }

    public static void emitPersistentUpsellClickEventMetric() {
        MetricsLogger.emitUiClickMetricForUpsell(ActionType.CLICK_PERSISTENT_UPSELL_INGRESS, ContentName.NIGHTWING_PERSISTENT_UPSELL.getMetricsValue());
    }

    public static void emitPersistentUpgradeClickEventMetric() {
        MetricsLogger.emitUiClickMetricForUpgrade(ActionType.CLICK_PERSISTENT_UPSELL_INGRESS, ContentName.PERSISTENT_UPGRADE.getMetricsValue(), "sdp_to_individual_upgrade");
    }

    public static void emitPersistentUpgradePageViewedMetric(final boolean isDynamicMessage, final boolean isNonBlocking) {
        PageSubType pageSubType;
        if (!isDynamicMessage) {
            pageSubType = PageSubType.PERSISTENT_UPGRADE_PLAN_COMPARISON;
        } else if (isNonBlocking) {
            pageSubType = PageSubType.PERSISTENT_UPGRADE_DM_NON_BLOCKING;
        } else {
            pageSubType = PageSubType.PERSISTENT_UPGRADE_DM_BLOCKING;
        }
        MetricsLogger.uiPageViewed(PageType.PERSISTENT_UPSELL_PAGE.getMetricsValue(), pageSubType.getMetricsValue(), "sdp_to_individual_upgrade");
    }

    public static void emitPersistentUpgradeSuccessPageViewMetric() {
        MetricsLogger.uiPageViewed(PageType.BROWSE_HOME.getMetricsValue(), null, "sdp_to_individual_upgrade");
    }

    public static void emitPersistentUpsellPageViewedMetric() {
        MetricsLogger.uiPageViewed(PageType.PERSISTENT_UPSELL_PAGE.getMetricsValue(), null, null, null, null);
    }

    public static void emitPersistentUpsellTriggerEvent() {
        MetricsLogger.sendEvent(FlexEvent.builder("persistentUpsellTriggerEvent").build());
    }

    private static String getEligibilityCheckResponseReason() {
        UserSubscription userSubscription = UserSubscriptionUtil.getUserSubscription();
        return userSubscription.isNightwingOnly() ? "AMF_NOT_ELIGIBLE" : !userSubscription.isHawkfireAllDevices() ? (userSubscription.isPrimeOnly() || userSubscription.isSonicRush()) ? "PRIME_NOT_ELIGIBLE" : "TIER_NOT_ELIGIBLE" : "TIER_NOT_ELIGIBLE";
    }

    public static String getAsinFromMediaItem(final MediaItem mediaItem) {
        return mediaItem != null ? MediaItemHelper.getAsin(mediaItem) : "defaultAsin";
    }

    private static PageType getUpsellPage(UpsellReason upsellReason) {
        if (AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[upsellReason.ordinal()] == 1) {
            return PageType.PERSISTENT_UPSELL_PAGE;
        }
        return PageType.NOW_PLAYING;
    }

    public static void raiseBlockingUpsell(final Context context, @Nullable final String asin, final UpsellReason upsellReason) {
        UpsellInformation upsellInformation = getUpsellInformation(asin, upsellReason, UpsellSourceEntity.TRACK);
        Intent intent = new Intent("com.amazon.mp3.upsell.skip_limit_upsell");
        intent.putExtra("com.amazon.music.playback.EXTRA_SHOW_UNLIMITED_UPSELL", upsellInformation);
        if (context != null) {
            emitEligibilityCheckRequestedMetric(upsellInformation);
            context.sendBroadcast(intent);
        }
    }

    private static String getMetricNameFromUpsellReason(final UpsellReason upsellReason) {
        if (upsellReason == null) {
            return "unknownUpsellReason";
        }
        switch (AnonymousClass1.$SwitchMap$com$amazon$mp3$prime$upsell$nightwing$model$UpsellReason[upsellReason.ordinal()]) {
            case 3:
                return "contentEligibility";
            case 4:
            case 5:
            case 6:
                return "skip";
            case 7:
                return "shuffle";
            case 8:
                return "download";
            case 9:
                return "addToPlaylist";
            case 10:
                return "addToQueue";
            case 11:
                return "scrub";
            case 12:
                return "previous";
            case 13:
                return "deeplinkUnlimitedSignup";
            case 14:
                return "AlbumUpsellFeature";
            default:
                return "defaultFeature";
        }
    }

    public static AlertDialog.Builder getAlertDialog(Context context, View upsellRootView, UpsellUIConfig upsellUIConfig, View.OnClickListener signupButtonListener, View.OnClickListener onDismissListener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AmazonMP3_Invisible);
        TextView textView = (TextView) upsellRootView.findViewById(R.id.bauhausNowPlayingNonBlockingTitle);
        TextView textView2 = (TextView) upsellRootView.findViewById(R.id.unlimitedIconList);
        TextView textView3 = (TextView) upsellRootView.findViewById(R.id.comfortText);
        Button button = (Button) upsellRootView.findViewById(R.id.signupButton);
        ImageButton imageButton = (ImageButton) upsellRootView.findViewById(R.id.closeButton);
        if (upsellUIConfig.getUpsellDialogTitleId() == 0) {
            textView.setText(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_title);
        } else {
            textView.setText(context.getString(upsellUIConfig.getUpsellDialogTitleId()));
        }
        if (upsellUIConfig.getUpsellDialogBodyId() == 0) {
            textView2.setText(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_message);
        } else {
            textView2.setText(context.getString(upsellUIConfig.getUpsellDialogBodyId()));
        }
        if (upsellUIConfig.getUpsellDialogComfortingTextId() == 0) {
            textView3.setText(R.string.dmusic_hawkfire_account_nightwing_upsell_dialog_comfort_text);
        } else {
            textView3.setText(upsellUIConfig.getUpsellDialogComfortingTextId());
        }
        if (upsellUIConfig.getUpsellPositiveButtonTextId() == 0) {
            button.setText(R.string.dmusic_hawkfire_accoun_nightwing_upsell_dialog_positive_btn);
        } else {
            button.setText(upsellUIConfig.getUpsellPositiveButtonTextId());
        }
        button.setOnClickListener(signupButtonListener);
        imageButton.setOnClickListener(onDismissListener);
        builder.setView(upsellRootView);
        return builder;
    }

    public static UpsellSourceEntity getUpsellSourceEntityFromRecentlyPlayedItem(final RecentlyPlayedItem recentlyPlayedItem) {
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ALBUM) {
            return UpsellSourceEntity.ALBUM;
        }
        if (recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.ARTIST || recentlyPlayedItem.getItemType() == RecentlyPlayedItem.Type.PRIME_ARTIST) {
            return UpsellSourceEntity.ARTIST;
        }
        if (MediaProvider.PrimePlaylists.isPrimePlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.SharedUserPlaylists.isSharedUserPlaylist(recentlyPlayedItem.getContentUri()) || MediaProvider.UdoPlaylists.isUdoPlaylist(recentlyPlayedItem.getContentUri())) {
            return UpsellSourceEntity.PLAYLIST;
        }
        Log.error(TAG, "unexpected type of recentlyPlayedItem for starting SFA for the Nightwing customer");
        return UpsellSourceEntity.TRACK;
    }

    public static boolean containsUpsellTag(final List<String> tags) {
        if (tags == null) {
            return false;
        }
        Iterator<String> it = tags.iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("upsell-tag-")) {
                return true;
            }
        }
        return false;
    }
}
