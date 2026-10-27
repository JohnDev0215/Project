package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dialog.ErrorDialogButton;
import com.amazon.mp3.dialog.OneButtonErrorDialog;
import com.amazon.mp3.find.util.AlexaUtil;
import com.amazon.mp3.find.view.SearchBrushFragment;
import com.amazon.mp3.find.view.SearchTabFragmentHost;
import com.amazon.mp3.navigation.LauncherLinkStatusHandler;
import com.amazon.music.destination.FindDestination;
import com.amazon.music.find.utils.EntityTypeUtils;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.platform.find.common.EntityType;
import com.amazon.music.router.DestinationHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: FindDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u0000 \u00152\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0002J\n\u0010\u000e\u001a\u0004\u0018\u00010\nH\u0002J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u0002H\u0016J\"\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u0002H\u0002J\"\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\r\u001a\u00020\u0002H\u0002J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002¨\u0006\u0016"}, d2 = {"Lcom/amazon/mp3/navigation/FindDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lcom/amazon/music/destination/FindDestination;", "Lcom/amazon/mp3/navigation/LauncherLinkStatusHandler;", "()V", "attemptToPresentCarModeFeatureUnavailableDialog", "", "context", "Landroid/content/Context;", "currentlyVisibleFragment", "Landroidx/fragment/app/Fragment;", "getIntent", "Landroid/content/Intent;", "findDestination", "getVisibleFragment", "navigateTo", "navigateToCarMode", "navigateToFindOrSearch", "shouldNavigateToSearch", "", "showFeatureUnavailableDialogForCarMode", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindDestinationHandler implements DestinationHandler<FindDestination>, LauncherLinkStatusHandler {
    private static final Logger logger = LoggerFactory.getLogger(FindDestinationHandler.class.getName());

    public void broadcastFailedToHandleLauncherLink(Context context, String str, Uri uri) {
        LauncherLinkStatusHandler.DefaultImpls.broadcastFailedToHandleLauncherLink(this, context, str, uri);
    }

    @Override // com.amazon.mp3.navigation.LauncherLinkStatusHandler
    public void broadcastSuccessfullyHandledLauncherLink(Context context, String str, Uri uri) {
        LauncherLinkStatusHandler.DefaultImpls.broadcastSuccessfullyHandledLauncherLink(this, context, str, uri);
    }

    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, FindDestination findDestination) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(findDestination, "findDestination");
        Fragment visibleFragment = getVisibleFragment();
        if (CarModeUtility.isInCarMode()) {
            navigateToCarMode(context, visibleFragment, findDestination);
        } else {
            navigateToFindOrSearch(context, visibleFragment, findDestination);
        }
    }

    private final Fragment getVisibleFragment() {
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        Activity previousActivity = AndroidUtils.getPreviousActivity();
        if (currentActivity instanceof DeeplinkHandlerActivity) {
            currentActivity = previousActivity;
        }
        return AndroidUtils.getVisibleFragment(currentActivity);
    }

    private final Intent getIntent(Context context, FindDestination findDestination) {
        Intent findIntent;
        String strConstructPageUri = PageUriUtils.constructPageUri(findDestination);
        if (shouldNavigateToSearch(findDestination)) {
            findIntent = SearchTabFragmentHost.getSearchIntent(context, strConstructPageUri);
        } else {
            findIntent = SearchTabFragmentHost.getFindIntent(context, strConstructPageUri);
        }
        findIntent.setFlags(268435456);
        return findIntent;
    }

    private final void navigateToFindOrSearch(Context context, Fragment currentlyVisibleFragment, FindDestination findDestination) {
        if ((currentlyVisibleFragment instanceof SearchBrushFragment) && shouldNavigateToSearch(findDestination)) {
            ((SearchBrushFragment) currentlyVisibleFragment).getViewModel().handleDestination(findDestination);
        } else {
            context.startActivity(getIntent(context, findDestination));
        }
        if (Intrinsics.areEqual(findDestination.getSource(), PageUriUtils.SOURCE_QUERY_VOICE_VALUE)) {
            Uri uri = findDestination.getUri();
            Intrinsics.checkNotNullExpressionValue(uri, "findDestination.uri");
            broadcastSuccessfullyHandledLauncherLink(context, "Successfully handled search request.", uri);
        }
    }

    private final boolean shouldNavigateToSearch(FindDestination findDestination) {
        boolean z = !findDestination.isFindHome();
        boolean z2 = findDestination.isFindHome() && Intrinsics.areEqual(findDestination.getSource(), "keyCodeSearch");
        if (z && AlexaUtil.isAlexaSearchFindBrowseEnabled()) {
            return true;
        }
        return (z2 && AmazonApplication.getCapabilities().isBluetoothSearchEnabled()) || findDestination.isSearchHistory();
    }

    private final void navigateToCarMode(Context context, Fragment currentlyVisibleFragment, FindDestination findDestination) {
        Uri destinationUri = findDestination.getUri();
        boolean zAreEqual = Intrinsics.areEqual(findDestination.getSource(), PageUriUtils.SOURCE_QUERY_VOICE_VALUE);
        boolean zIsAlexaSearchFindBrowseEnabled = AlexaUtil.isAlexaSearchFindBrowseEnabled();
        boolean zIsAlexaSearchFindBrowseInCarModeEnabled = AlexaUtil.isAlexaSearchFindBrowseInCarModeEnabled();
        String str = "Failed to show car mode search results due to feature eligibility";
        if (!zIsAlexaSearchFindBrowseEnabled && !zIsAlexaSearchFindBrowseInCarModeEnabled) {
            CarModeUtility.INSTANCE.navigateToBrushFragment(context, false, false);
        } else if (!zIsAlexaSearchFindBrowseEnabled || zIsAlexaSearchFindBrowseInCarModeEnabled) {
            if (zIsAlexaSearchFindBrowseInCarModeEnabled) {
                if (EntityTypeUtils.INSTANCE.convertFromString(findDestination.getSeeMoreEntityType()) == EntityType.VIDEO && AmazonApplication.getCapabilities().isMusicVideosEnabled()) {
                    CarModeUtility.INSTANCE.onCarModeExit(context, false, Integer.valueOf(R.string.dmusic_car_mode_exit_for_videos));
                    return;
                } else {
                    CarModeUtility.INSTANCE.navigateToCarModeSearchFragment(context, PageUriUtils.constructPageUri(findDestination));
                }
            }
            str = null;
        } else {
            attemptToPresentCarModeFeatureUnavailableDialog(context, currentlyVisibleFragment);
        }
        if (zAreEqual) {
            if (str == null) {
                Intrinsics.checkNotNullExpressionValue(destinationUri, "destinationUri");
                broadcastSuccessfullyHandledLauncherLink(context, "Successfully handled search request.", destinationUri);
            } else {
                Intrinsics.checkNotNullExpressionValue(destinationUri, "destinationUri");
                broadcastFailedToHandleLauncherLink(context, str, destinationUri);
            }
        }
    }

    private final void attemptToPresentCarModeFeatureUnavailableDialog(Context context, Fragment currentlyVisibleFragment) {
        try {
            if (currentlyVisibleFragment == null) {
                CarModeUtility.INSTANCE.navigateToBrushFragment(context, false, false);
            } else {
                Context contextRequireContext = currentlyVisibleFragment.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "currentlyVisibleFragment.requireContext()");
                showFeatureUnavailableDialogForCarMode(contextRequireContext);
            }
        } catch (Exception unused) {
            logger.error("Error occurred while trying to present dialog.");
        }
    }

    private final void showFeatureUnavailableDialogForCarMode(Context context) {
        new OneButtonErrorDialog(context, context.getString(R.string.dmusic_find_car_mode_simplifier_title), context.getString(R.string.dmusic_find_car_mode_simplifier_message), new ErrorDialogButton(context.getString(R.string.dmusic_button_ok))).show();
    }
}
