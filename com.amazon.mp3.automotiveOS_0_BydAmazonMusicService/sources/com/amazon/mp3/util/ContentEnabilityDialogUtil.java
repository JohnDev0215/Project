package com.amazon.mp3.util;

import android.content.Context;
import android.content.Intent;
import androidx.fragment.app.DialogFragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.activity.AccountWebViewActivity;
import com.amazon.mp3.activity.WideDialogActivity;
import com.amazon.mp3.activity.WideDialogActivityPurpose;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.fragment.ContentInaccessibleDialogFragment;
import com.amazon.mp3.dialog.fragment.ContentNotCastableDialogFragment;
import com.amazon.mp3.dialog.fragment.ContentUnavailableDialogFragment;
import com.amazon.mp3.dialog.fragment.DownloadedContentInaccessibleDialogFragment;
import com.amazon.mp3.dialog.fragment.ExplicitContentUnavailableDialogFragment;
import com.amazon.mp3.dialog.fragment.MarketplaceUnsupportedDialog;
import com.amazon.mp3.dialog.fragment.PrimeContentExpiredDialogFragment;
import com.amazon.mp3.library.dialog.DownloadedFileNotFoundDialog;
import com.amazon.mp3.library.fragment.DeleteContentHandler;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.CatalogContent;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.item.RedownloadableTrack;
import com.amazon.mp3.prime.ContentUnavailableReason;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.upsell.PrimeUpsellActivity;
import com.amazon.mp3.prime.upsell.nightwing.UpsellManager;
import com.amazon.mp3.upsellweb.PrimeOfferPageWebTargetBuilderFactory;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;

/* JADX INFO: loaded from: classes4.dex */
public class ContentEnabilityDialogUtil {
    private static final String TAG = "ContentEnabilityDialogUtil";

    public static DialogFragment getDialogForDisabledReason(final Context context, final ContentUnavailableReason reason, final DeleteContentHandler deleteContentHandler, final String itemName) {
        return getDialogForDisabledReason(context, reason, deleteContentHandler, itemName, null, null);
    }

    public static DialogFragment getDialogForDisabledReason(final Context context, final ContentUnavailableReason reason, final DeleteContentHandler deleteContentHandler, final String itemName, final Object item, final ContentAccessUtil.ContentAccessOperation contentAccessOperation) {
        RedownloadableTrack redownloadableTrack;
        String str = TAG;
        Log.warning(str, "Called getDialogForDisabledReason() with reason: " + reason + " item: " + itemName);
        if (reason == null) {
            return null;
        }
        switch (AnonymousClass2.$SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[reason.ordinal()]) {
            case 1:
                return new PrimeContentExpiredDialogFragment();
            case 2:
                if (context != null) {
                    context.startActivity(PrimeUpsellActivity.getDialogBeforeWebIntent(context, new PrimeOfferPageWebTargetBuilderFactory(context).newBuilder().withReportingParameters("dmusic_browse", "upbox").build()));
                }
                return null;
            case 3:
                if (UserSubscriptionUtil.getUserSubscription().isInSubscriptionMarketplace()) {
                    return ContentInaccessibleDialogFragment.newInstance(itemName);
                }
                return new MarketplaceUnsupportedDialog();
            case 4:
                if (UserSubscriptionUtil.isNightwingOnly() && AmazonApplication.getCapabilities().isFreeTierUserPlaylistEditingEnabled()) {
                    return getExplicitFilterDialogOrUpsellDialog(context, deleteContentHandler, itemName, item, contentAccessOperation);
                }
                return ContentUnavailableDialogFragment.newInstance(deleteContentHandler, itemName);
            case 5:
                return ContentUnavailableDialogFragment.newInstance(deleteContentHandler, itemName);
            case 6:
                return getExplicitFilterDialogOrUpsellDialog(context, deleteContentHandler, itemName, item, contentAccessOperation);
            case 7:
            case 8:
                return getExplicitFilterDialogOrUpsellDialog(context, deleteContentHandler, itemName, item, contentAccessOperation);
            case 9:
                Log.warning(str, "Called getDialogForDisabledReason() with DEVICE_NOT_AUTHORIZED.  Attempting to authorize.");
                AmazonApplication.getBackgroundHandler().postAtFrontOfQueue(new Runnable() { // from class: com.amazon.mp3.util.ContentEnabilityDialogUtil.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Intent deviceAuthorizationIntent = WebViewUtil.getDeviceAuthorizationIntent(context);
                        if (deviceAuthorizationIntent != null) {
                            context.startActivity(deviceAuthorizationIntent);
                        }
                    }
                });
                return null;
            case 10:
                Log.warning(str, "Called getDialogForDisabledReason() with ACCOUNT_INVALID.  Launching AccountWebView.");
                context.startActivity(AccountWebViewActivity.getIntent(context));
                return null;
            case 11:
                return ContentNotCastableDialogFragment.newInstance(itemName);
            case 12:
                if (isVideoTrack(item)) {
                    return ExplicitContentUnavailableDialogFragment.newInstance(ExplicitContentUnavailableDialogFragment.ExplicitContentType.VIDEO);
                }
                return ExplicitContentUnavailableDialogFragment.newInstance(ExplicitContentUnavailableDialogFragment.ExplicitContentType.AUDIO);
            case 13:
                if (item instanceof AbstractItem) {
                    return DownloadedFileNotFoundDialog.newInstance((AbstractItem) item);
                }
                return null;
            case 14:
                WideDialogActivity.showActivity(context, WideDialogActivityPurpose.SHOW_REDOWNLOAD_DIALOG);
                return null;
            case 15:
                if (item instanceof MusicTrack) {
                    MusicTrack musicTrack = (MusicTrack) item;
                    redownloadableTrack = new RedownloadableTrack(musicTrack.getAsin(), musicTrack.getType(), musicTrack.getTitle());
                } else {
                    redownloadableTrack = null;
                }
                WideDialogActivity.showActivity(context, WideDialogActivityPurpose.SHOW_UNAVAILABLE_EXPIRED_KATANA_SUBSCRIPTION, R.style.Theme_AmazonMP3_WideDialog, redownloadableTrack);
                return null;
            case 16:
                ConnectivityUtil.checkConnectivityAndShowDialog(context);
                return null;
            case 17:
                if (!UserSubscriptionUtil.isHawkfire() && UserSubscriptionUtil.getUserSubscription().isSonicRush()) {
                    return DownloadedContentInaccessibleDialogFragment.newInstance(itemName);
                }
                return ContentInaccessibleDialogFragment.newInstance(itemName);
            default:
                Log.warning(str, "Unhandled reason: " + reason);
                return null;
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.util.ContentEnabilityDialogUtil$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason;

        static {
            int[] iArr = new int[ContentUnavailableReason.values().length];
            $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason = iArr;
            try {
                iArr[ContentUnavailableReason.TOKEN_EXPIRED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.CUSTOMER_NOT_PRIME.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.UNSUPPORTED_MARKETPLACE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.TRACK_NO_LONGER_PRIME.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.TRACK_NO_LONGER_AVAILABLE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.CUSTOMER_HAS_HAWKFIRE_HOME_SUBSCRIPTION.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.CUSTOMER_HAS_NO_HAWKFIRE_SUBSCRIPTION.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.DISABLE_CONTENT_FOR_UPSELL.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.DEVICE_NOT_AUTHORIZED.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.ACCOUNT_INVALID.ordinal()] = 10;
            } catch (NoSuchFieldError unused10) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.CONTENT_TYPE_NOT_CASTABLE.ordinal()] = 11;
            } catch (NoSuchFieldError unused11) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED.ordinal()] = 12;
            } catch (NoSuchFieldError unused12) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.FILE_DOES_NOT_EXIST_ON_DISK.ordinal()] = 13;
            } catch (NoSuchFieldError unused13) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.ASSET_IS_PRE_WIDEVINE.ordinal()] = 14;
            } catch (NoSuchFieldError unused14) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.CUSTOMER_HAS_NO_KATANA_SUBSCRIPTION.ordinal()] = 15;
            } catch (NoSuchFieldError unused15) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.NO_NETWORK.ordinal()] = 16;
            } catch (NoSuchFieldError unused16) {
            }
            try {
                $SwitchMap$com$amazon$mp3$prime$ContentUnavailableReason[ContentUnavailableReason.NOT_AVAILABLE_OFFLINE.ordinal()] = 17;
            } catch (NoSuchFieldError unused17) {
            }
        }
    }

    private static boolean isVideoTrack(Object item) {
        if (item instanceof PrimeTrack) {
            return ((PrimeTrack) item).isVideo();
        }
        if (item instanceof TrackMetadata) {
            return PrimePlaylistTracksTable.AssetType.VIDEO.toString().equals(((TrackMetadata) item).getAssetType());
        }
        if (item instanceof MediaItem) {
            return MediaItemExtensions.isVideo((MediaItem) item);
        }
        if (item instanceof MusicTrack) {
            return ((MusicTrack) item).getAssetType() == PrimePlaylistTracksTable.AssetType.VIDEO;
        }
        if (item instanceof PlaylistMetadata) {
            return ((PlaylistMetadata) item).getContentTypes().contains(PrimePlaylistTracksTable.AssetType.VIDEO.toString());
        }
        return false;
    }

    private static DialogFragment getExplicitFilterDialogOrUpsellDialog(final Context context, final DeleteContentHandler deleteContentHandler, final String itemName, final Object item, final ContentAccessUtil.ContentAccessOperation contentAccessOperation) {
        if ((item instanceof CatalogContent) && contentAccessOperation != ContentAccessUtil.ContentAccessOperation.STREAM_UPSELL && ContentAccessUtil.getExplicitFilterUnavailableReason((CatalogContent) item, ContentAccessUtil.ContentAccessOperation.STREAM) != null) {
            return getDialogForDisabledReason(context, ContentUnavailableReason.EXPLICIT_CONTENT_FILTERED, deleteContentHandler, itemName);
        }
        return UpsellManager.getInstance().getUpsellDialog(context, item, itemName, contentAccessOperation);
    }
}
