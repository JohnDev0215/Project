package com.amazon.mp3.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeAlexaActionsHandler;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.auto.carmode.activity.CarModeActivity;
import com.amazon.mp3.navigation.ContextAwareDeeplinkManager;
import com.amazon.mp3.navigation.LauncherLinkStatusHandler;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.router.DestinationHandler;
import com.amazon.music.skyfire.ui.skyfire.Contexts;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import main.java.com.amazon.music.destination.CarModeDestination;

/* JADX INFO: compiled from: CarModeDestinationHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0005¢\u0006\u0002\u0010\u0004J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0002J \u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0002J\u0018\u0010\u000f\u001a\u00020\r2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/navigation/CarModeDestinationHandler;", "Lcom/amazon/music/router/DestinationHandler;", "Lmain/java/com/amazon/music/destination/CarModeDestination;", "Lcom/amazon/mp3/navigation/LauncherLinkStatusHandler;", "()V", "getCarModeAlexaActionsHandler", "Lcom/amazon/mp3/auto/carmode/CarModeAlexaActionsHandler;", "getLaunchCarModeIntent", "Landroid/content/Intent;", "context", "Landroid/content/Context;", "destination", "handleActiveCarModeState", "", "carModeActionsHandler", "navigateTo", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarModeDestinationHandler implements DestinationHandler<CarModeDestination>, LauncherLinkStatusHandler {
    public void broadcastFailedToHandleLauncherLink(Context context, String str, Uri uri) {
        LauncherLinkStatusHandler.DefaultImpls.broadcastFailedToHandleLauncherLink(this, context, str, uri);
    }

    @Override // com.amazon.mp3.navigation.LauncherLinkStatusHandler
    public void broadcastSuccessfullyHandledLauncherLink(Context context, String str, Uri uri) {
        LauncherLinkStatusHandler.DefaultImpls.broadcastSuccessfullyHandledLauncherLink(this, context, str, uri);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @Override // com.amazon.music.router.DestinationHandler
    public void navigateTo(Context context, CarModeDestination destination) {
        Integer presetNumber;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        CarModeAlexaActionsHandler carModeAlexaActionsHandler = getCarModeAlexaActionsHandler();
        if (carModeAlexaActionsHandler != null) {
            handleActiveCarModeState(carModeAlexaActionsHandler, destination, context);
            return;
        }
        Intent launchCarModeIntent = getLaunchCarModeIntent(context, destination);
        boolean zIsInCarMode = CarModeUtility.isInCarMode();
        String carModeDestinationType = destination.getCarModeDestinationType();
        if (carModeDestinationType != null) {
            switch (carModeDestinationType.hashCode()) {
                case -1432962864:
                    if (carModeDestinationType.equals("ENTER_CARMODE")) {
                        if (zIsInCarMode) {
                            Uri uri = destination.getUri();
                            Intrinsics.checkNotNullExpressionValue(uri, "destination.uri");
                            broadcastFailedToHandleLauncherLink(context, "User already in CarMode", uri);
                            return;
                        } else {
                            Uri uri2 = destination.getUri();
                            Intrinsics.checkNotNullExpressionValue(uri2, "destination.uri");
                            LauncherLinkStatusHandler.DefaultImpls.broadcastSuccessfullyHandledLauncherLink$default(this, context, null, uri2, 2, null);
                            CarModeUtility.INSTANCE.navigateToBrushFragment(context, false, false);
                            return;
                        }
                    }
                    break;
                case -83584635:
                    if (carModeDestinationType.equals("PLAYBACK_PRESET_NUMBER") && (presetNumber = destination.getPresetNumber()) != null) {
                        presetNumber.intValue();
                        Integer presetNumber2 = destination.getPresetNumber();
                        Intrinsics.checkNotNullExpressionValue(presetNumber2, "destination.presetNumber");
                        launchCarModeIntent.putExtra("position", presetNumber2.intValue());
                        launchCarModeIntent.putExtra(Contexts.OpenSeeMore.uri, destination.getUri().toString());
                        launchCarModeIntent.putExtra("action", "play-preset-number");
                    }
                    break;
                case 1766364470:
                    if (carModeDestinationType.equals("EXIT_CARMODE")) {
                        if (!zIsInCarMode) {
                            Uri uri3 = destination.getUri();
                            Intrinsics.checkNotNullExpressionValue(uri3, "destination.uri");
                            broadcastFailedToHandleLauncherLink(context, "CarMode is already inactive", uri3);
                            return;
                        } else {
                            CarModeUtility.INSTANCE.navigateToHome(context, true);
                            Uri uri4 = destination.getUri();
                            Intrinsics.checkNotNullExpressionValue(uri4, "destination.uri");
                            LauncherLinkStatusHandler.DefaultImpls.broadcastSuccessfullyHandledLauncherLink$default(this, context, null, uri4, 2, null);
                            return;
                        }
                    }
                    break;
                case 2015538223:
                    if (carModeDestinationType.equals("PLAYBACK_PRESET_NEXT")) {
                        launchCarModeIntent.putExtra("action", "next");
                        launchCarModeIntent.putExtra(Contexts.OpenSeeMore.uri, destination.getUri().toString());
                    }
                    break;
                case 2015609711:
                    if (carModeDestinationType.equals("PLAYBACK_PRESET_PREV")) {
                        launchCarModeIntent.putExtra("action", "prev");
                        launchCarModeIntent.putExtra(Contexts.OpenSeeMore.uri, destination.getUri().toString());
                    }
                    break;
            }
        }
        if (CarModeUtility.isInCarMode()) {
            AndroidUtils.getCurrentActivity().startActivity(launchCarModeIntent);
        } else {
            context.startActivity(launchCarModeIntent);
        }
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final void handleActiveCarModeState(CarModeAlexaActionsHandler carModeActionsHandler, final CarModeDestination destination, final Context context) {
        Function2<ContextAwareDeeplinkManager.Companion.Status, String, Unit> function2 = new Function2<ContextAwareDeeplinkManager.Companion.Status, String, Unit>() { // from class: com.amazon.mp3.navigation.CarModeDestinationHandler$handleActiveCarModeState$onCompleteHandler$1

            /* JADX INFO: compiled from: CarModeDestinationHandler.kt */
            @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
            public /* synthetic */ class WhenMappings {
                public static final /* synthetic */ int[] $EnumSwitchMapping$0;

                static {
                    int[] iArr = new int[ContextAwareDeeplinkManager.Companion.Status.values().length];
                    iArr[ContextAwareDeeplinkManager.Companion.Status.SUCCESS.ordinal()] = 1;
                    iArr[ContextAwareDeeplinkManager.Companion.Status.FAILURE.ordinal()] = 2;
                    $EnumSwitchMapping$0 = iArr;
                }
            }

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke */
            public /* bridge */ /* synthetic */ Unit mo4invoke(ContextAwareDeeplinkManager.Companion.Status status, String str) {
                invoke2(status, str);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ContextAwareDeeplinkManager.Companion.Status status, String str) {
                Intrinsics.checkNotNullParameter(status, "status");
                int i = WhenMappings.$EnumSwitchMapping$0[status.ordinal()];
                if (i == 1) {
                    CarModeDestinationHandler carModeDestinationHandler = this.this$0;
                    Context context2 = context;
                    Uri uri = destination.getUri();
                    Intrinsics.checkNotNullExpressionValue(uri, "destination.uri");
                    carModeDestinationHandler.broadcastSuccessfullyHandledLauncherLink(context2, str, uri);
                    return;
                }
                if (i != 2) {
                    return;
                }
                CarModeDestinationHandler carModeDestinationHandler2 = this.this$0;
                Context context3 = context;
                Uri uri2 = destination.getUri();
                Intrinsics.checkNotNullExpressionValue(uri2, "destination.uri");
                carModeDestinationHandler2.broadcastFailedToHandleLauncherLink(context3, str, uri2);
            }
        };
        String carModeDestinationType = destination.getCarModeDestinationType();
        if (carModeDestinationType != null) {
            switch (carModeDestinationType.hashCode()) {
                case -83584635:
                    if (carModeDestinationType.equals("PLAYBACK_PRESET_NUMBER")) {
                        Integer presetNumber = destination.getPresetNumber();
                        Intrinsics.checkNotNullExpressionValue(presetNumber, "destination.presetNumber");
                        carModeActionsHandler.handlePresetPlayback(presetNumber.intValue(), function2);
                        return;
                    }
                    break;
                case 1766364470:
                    if (carModeDestinationType.equals("EXIT_CARMODE")) {
                        carModeActionsHandler.handleExitCarMode(function2);
                        return;
                    }
                    break;
                case 2015538223:
                    if (carModeDestinationType.equals("PLAYBACK_PRESET_NEXT")) {
                        carModeActionsHandler.handlePresetsPlaybackNext(function2);
                        return;
                    }
                    break;
                case 2015609711:
                    if (carModeDestinationType.equals("PLAYBACK_PRESET_PREV")) {
                        carModeActionsHandler.handlePresetsPlaybackPrevious(function2);
                        return;
                    }
                    break;
            }
        }
        Uri uri = destination.getUri();
        Intrinsics.checkNotNullExpressionValue(uri, "destination.uri");
        broadcastFailedToHandleLauncherLink(context, null, uri);
    }

    private final CarModeAlexaActionsHandler getCarModeAlexaActionsHandler() {
        if (!CarModeUtility.isInCarMode()) {
            return null;
        }
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        CarModeActivity carModeActivity = currentActivity instanceof CarModeActivity ? (CarModeActivity) currentActivity : null;
        Fragment currentFragment = carModeActivity == null ? null : carModeActivity.getCurrentFragment();
        if (currentFragment instanceof CarModeAlexaActionsHandler) {
            return (CarModeAlexaActionsHandler) currentFragment;
        }
        return null;
    }

    private final Intent getLaunchCarModeIntent(Context context, CarModeDestination destination) {
        Intent intentPutExtra = CarModeActivity.INSTANCE.getStartIntent(context).putExtra("carModeAction", CarModeAction.SHOW_HOME_PAGE).putExtra("carModeLaunch", true).putExtra(DeeplinkIntentKey.KEY_ASSOCIATE_TAG.toString(), destination.getTag()).putExtra(DeeplinkIntentKey.KEY_REF_MARKER.toString(), destination.getRef());
        Intrinsics.checkNotNullExpressionValue(intentPutExtra, "CarModeActivity.getStart…tring(), destination.ref)");
        if (CarModeUtility.isInCarMode()) {
            intentPutExtra.setFlags(131072);
        } else {
            intentPutExtra.addFlags(268435456);
        }
        return intentPutExtra;
    }
}
