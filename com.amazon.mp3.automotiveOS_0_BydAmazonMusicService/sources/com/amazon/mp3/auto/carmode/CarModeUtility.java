package com.amazon.mp3.auto.carmode;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.annotation.WorkerThread;
import androidx.fragment.app.FragmentActivity;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.auto.AutoFlexEventUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.activity.CarModeActivity;
import com.amazon.mp3.auto.carmode.detection.CarDetector;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.music.media.playback.config.PlaybackExperienceMode;
import com.amazon.music.media.playback.config.PlaybackExperienceModeProvider;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.comparisons.ComparisonsKt__ComparisonsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeUtility.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000e\n\u0002\b \n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010!\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\bL\u0010MJ\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002J\b\u0010\u0007\u001a\u00020\u0006H\u0016J\u0006\u0010\b\u001a\u00020\u0004J \u0010\u000e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u000b2\b\b\u0002\u0010\r\u001a\u00020\u000bJ\u0006\u0010\u000f\u001a\u00020\u0004J+\u0010\u0013\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\b\b\u0002\u0010\u0010\u001a\u00020\u000b2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u000e\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u000bJ\u0016\u0010\u0018\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u000bJ\u0006\u0010\u0019\u001a\u00020\u000bJ\u000e\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u0011J\u0006\u0010\u001c\u001a\u00020\u0011J\u0006\u0010\u001d\u001a\u00020\u0004J\u000e\u0010\u001e\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010 \u001a\u00020\u001f2\u0006\u0010\n\u001a\u00020\tJ\u0006\u0010!\u001a\u00020\u000bJ\u0006\u0010\"\u001a\u00020\u000bJ\u0010\u0010#\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0007J\u0018\u0010%\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u001fJ\u000e\u0010&\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\tJ\u000e\u0010'\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tJ\u0016\u0010)\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\u0006\u0010(\u001a\u00020\u000bJ\u000e\u0010+\u001a\u00020\u00042\u0006\u0010*\u001a\u00020\u000bJ\b\u0010,\u001a\u00020\u0004H\u0002J\b\u0010-\u001a\u00020\u0004H\u0002J\u0010\u0010.\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0007J\u0010\u0010/\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u00100\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002J!\u00101\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011H\u0002¢\u0006\u0004\b1\u00102J\b\u00103\u001a\u00020\u000bH\u0007J\b\u00104\u001a\u00020\u000bH\u0007R\u0016\u00105\u001a\u00020\u00028\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b9\u00108R\u0016\u0010:\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b:\u00108R\u0016\u0010;\u001a\u00020\u00118\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b;\u0010<R\u001c\u0010>\u001a\n =*\u0004\u0018\u00010\u001f0\u001f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b>\u0010?R\u001a\u0010B\u001a\b\u0012\u0004\u0012\u00020A0@8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010CR\u0017\u0010D\u001a\u00020A8\u0006¢\u0006\f\n\u0004\bD\u0010E\u001a\u0004\bF\u0010GR\u001d\u0010I\u001a\b\u0012\u0004\u0012\u00020A0H8\u0006¢\u0006\f\n\u0004\bI\u0010C\u001a\u0004\bJ\u0010K¨\u0006N"}, d2 = {"Lcom/amazon/mp3/auto/carmode/CarModeUtility;", "Lcom/amazon/music/media/playback/config/PlaybackExperienceModeProvider;", "Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "inCarDetector", "", "setCarDetector", "Lcom/amazon/music/media/playback/config/PlaybackExperienceMode;", "getPlaybackExperienceMode", "resetCarModeData", "Landroid/content/Context;", "context", "", "byUserClick", "isAutoLaunch", "navigateToBrushFragment", "performCarModeLaunchInit", "carDetectorExit", "", "exitMessageId", "onCarModeExit", "(Landroid/content/Context;ZLjava/lang/Integer;)V", "isUserExited", "setUserExitedStatus", "userExited", "navigateToHome", "isCurrentlyInCar", "presetNumber", "setCurrentlyPlayingPresetNumber", "getCurrentPlayingPresetNumber", "destroy", "exitCarModeIfInCarMode", "", "getDisclaimer", "isUserExitedCarMode", "isAutoEnteredCarMode", "clearCarModePreferences", "pageUri", "navigateToCarModeSearchFragment", "shouldStartWelcomePage", "setDisclaimerPreference", "isAccessible", "setOnBoardingPreference", "dismissStatus", "setUserDismissedOnBoardingStatus", "initPlaybackSpeeds", "resetPlaybackSpeeds", "checkIfAutoLaunchCarMode", "navigateToDisclaimerFragment", "navigateToWelcomeFragment", "navigateToCarModeExitFragment", "(Landroid/content/Context;Ljava/lang/Integer;)V", "isCarModeEnabled", "isInCarMode", "carDetector", "Lcom/amazon/mp3/auto/carmode/detection/CarDetector;", "userExitedCarMode", "Z", "userDismissedOnBoarding", "autoLaunchedCarMode", "currentlyPlayingPresetNumber", "I", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "", "Lcom/amazon/podcast/media/playback/PlaybackSpeed;", "DEFAULT_PLAYBACK_SPEEDS", "Ljava/util/List;", "DEFAULT_STANDARD_PLAYBACK_SPEED", "Lcom/amazon/podcast/media/playback/PlaybackSpeed;", "getDEFAULT_STANDARD_PLAYBACK_SPEED", "()Lcom/amazon/podcast/media/playback/PlaybackSpeed;", "", "playbackSpeeds", "getPlaybackSpeeds", "()Ljava/util/List;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeUtility implements PlaybackExperienceModeProvider {
    private static final List<PlaybackSpeed> DEFAULT_PLAYBACK_SPEEDS;
    private static final PlaybackSpeed DEFAULT_STANDARD_PLAYBACK_SPEED;
    private static boolean autoLaunchedCarMode;
    private static CarDetector carDetector;
    private static final List<PlaybackSpeed> playbackSpeeds;
    private static boolean userDismissedOnBoarding;
    private static boolean userExitedCarMode;
    public static final CarModeUtility INSTANCE = new CarModeUtility();
    private static int currentlyPlayingPresetNumber = -1;
    private static final String TAG = CarModeUtility.class.getSimpleName();

    private CarModeUtility() {
    }

    static {
        PlaybackSpeed playbackSpeed = PlaybackSpeed.ONE;
        List<PlaybackSpeed> listListOf = CollectionsKt__CollectionsKt.listOf((Object[]) new PlaybackSpeed[]{PlaybackSpeed.POINT_8, playbackSpeed, PlaybackSpeed.ONE_POINT_TWO});
        DEFAULT_PLAYBACK_SPEEDS = listListOf;
        DEFAULT_STANDARD_PLAYBACK_SPEED = playbackSpeed;
        playbackSpeeds = CollectionsKt___CollectionsKt.toMutableList((Collection) listListOf);
    }

    public final PlaybackSpeed getDEFAULT_STANDARD_PLAYBACK_SPEED() {
        return DEFAULT_STANDARD_PLAYBACK_SPEED;
    }

    public final List<PlaybackSpeed> getPlaybackSpeeds() {
        return playbackSpeeds;
    }

    public final void setCarDetector(CarDetector inCarDetector) {
        Intrinsics.checkNotNullParameter(inCarDetector, "inCarDetector");
        carDetector = inCarDetector;
    }

    @Override // com.amazon.music.media.playback.config.PlaybackExperienceModeProvider
    public PlaybackExperienceMode getPlaybackExperienceMode() {
        return isInCarMode() ? PlaybackExperienceMode.CAR_MODE : PlaybackExperienceMode.STANDARD_MODE;
    }

    private final void initPlaybackSpeeds() {
        PlaybackSpeed playbackSpeed = Podcast.getPlayback().getPlaybackSpeed();
        if (playbackSpeed != null) {
            List<PlaybackSpeed> list = playbackSpeeds;
            if (list.contains(playbackSpeed)) {
                return;
            }
            list.add(playbackSpeed);
            if (list.size() > 1) {
                CollectionsKt__MutableCollectionsJVMKt.sortWith(list, new Comparator() { // from class: com.amazon.mp3.auto.carmode.CarModeUtility$initPlaybackSpeeds$$inlined$sortBy$1
                    /* JADX WARN: Multi-variable type inference failed */
                    @Override // java.util.Comparator
                    public final int compare(T t, T t2) {
                        return ComparisonsKt__ComparisonsKt.compareValues(Float.valueOf(((PlaybackSpeed) t).getValue()), Float.valueOf(((PlaybackSpeed) t2).getValue()));
                    }
                });
            }
        }
    }

    private final void resetPlaybackSpeeds() {
        List<PlaybackSpeed> list = playbackSpeeds;
        list.clear();
        list.addAll(DEFAULT_PLAYBACK_SPEEDS);
    }

    @JvmStatic
    public static final void checkIfAutoLaunchCarMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (isCarModeEnabled() && ConnectivityUtil.hasAnyInternetConnection(context) && !isInCarMode()) {
            Boolean automaticallyLaunchCarModeEnabled = SettingsUtil.getCarModeLaunchAutomaticallyPreference(context);
            CarDetector carDetector2 = carDetector;
            if (carDetector2 != null) {
                if (carDetector2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carDetector");
                    carDetector2 = null;
                }
                if (!carDetector2.isCurrentlyInCar() || userExitedCarMode) {
                    return;
                }
                Intrinsics.checkNotNullExpressionValue(automaticallyLaunchCarModeEnabled, "automaticallyLaunchCarModeEnabled");
                if (automaticallyLaunchCarModeEnabled.booleanValue()) {
                    Log.warning(TAG, "Auto launching car mode");
                    autoLaunchedCarMode = true;
                    INSTANCE.navigateToBrushFragment(context, false, true);
                }
            }
        }
    }

    public final void resetCarModeData() {
        autoLaunchedCarMode = false;
        resetPlaybackSpeeds();
        Podcast.setDisableAlerts(false);
    }

    private final void navigateToDisclaimerFragment(Context context) {
        Intent startIntent = CarModeActivity.INSTANCE.getStartIntent(context);
        startIntent.putExtra("carModeAction", CarModeAction.SHOW_DISCLAIMER_PAGE);
        startIntent.addFlags(268435456);
        Log.warning(TAG, "Launching first time user disclaimer fragment");
        context.startActivity(startIntent);
    }

    private final void navigateToWelcomeFragment(Context context) {
        Intent startIntent = CarModeActivity.INSTANCE.getStartIntent(context);
        startIntent.putExtra("carModeAction", CarModeAction.SHOW_WELCOME_PAGE);
        startIntent.addFlags(268435456);
        Log.debug(TAG, "Launching first time user welcome fragment");
        context.startActivity(startIntent);
    }

    private final void navigateToCarModeExitFragment(Context context, Integer exitMessageId) {
        Intent startIntent = CarModeActivity.INSTANCE.getStartIntent(context);
        startIntent.putExtra("carModeAction", CarModeAction.SHOW_EXIT_PAGE);
        if (exitMessageId != null) {
            startIntent.putExtra("EXIT_STRING_ID_KEY", exitMessageId.intValue());
        }
        startIntent.addFlags(268435456);
        Log.warning(TAG, "Launching first time exit fragment");
        context.startActivity(startIntent);
    }

    public final void navigateToBrushFragment(Context context, boolean byUserClick, boolean isAutoLaunch) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
        if (isCarModeEnabled()) {
            try {
                if (!ConnectivityUtil.hasAnyInternetConnection(context)) {
                    if (!(context instanceof MusicHomeActivity) || ((MusicHomeActivity) context).isFinishing()) {
                        return;
                    }
                    ConnectivityUtil.checkConnectivityAndShowDialog((FragmentActivity) context);
                    return;
                }
                if (isAutoLaunch) {
                    AutoFlexEventUtil autoFlexEventUtil = AutoFlexEventUtil.INSTANCE;
                    boolean zIsApplicationInForeground = AndroidUtils.isApplicationInForeground();
                    Boolean carModeLaunchAutomaticallyPreference = SettingsUtil.getCarModeLaunchAutomaticallyPreference(context);
                    Intrinsics.checkNotNullExpressionValue(carModeLaunchAutomaticallyPreference, "getCarModeLaunchAutomaticallyPreference(context)");
                    boolean zBooleanValue = carModeLaunchAutomaticallyPreference.booleanValue();
                    String showCarModeFABPreference = SettingsUtil.getShowCarModeFABPreference(context);
                    Intrinsics.checkNotNullExpressionValue(showCarModeFABPreference, "getShowCarModeFABPreference(context)");
                    autoFlexEventUtil.emitCarModeAutoLaunchFlexEvent(zIsApplicationInForeground, zBooleanValue, showCarModeFABPreference);
                }
                if (defaultSharedPreferences.getBoolean("CAR_MODE_DISCLAIMER", true) || CarModeConfigs.INSTANCE.alwaysShowDisclaimer()) {
                    navigateToDisclaimerFragment(context);
                    return;
                }
                if (shouldStartWelcomePage(context)) {
                    navigateToWelcomeFragment(context);
                    return;
                }
                Log.warning(TAG, "Launching car mode");
                if (byUserClick) {
                    AutoUiEventUtil.sendUiClickEvent$default(ActionType.CAR_MODE_OPEN, PageType.BROWSE_HOME, ExperienceMode.CAR_MODE, null, 8, null);
                }
                Intent startIntent = CarModeActivity.INSTANCE.getStartIntent(context);
                startIntent.putExtra("carModeAction", CarModeAction.SHOW_HOME_PAGE);
                startIntent.putExtra("carModeLaunch", true);
                startIntent.addFlags(268435456);
                context.startActivity(startIntent);
            } catch (Exception e) {
                Log.error(TAG, "Failed to show connectivity dialog", e);
            }
        }
    }

    public final void performCarModeLaunchInit() {
        if (AmazonApplication.getCapabilities().isCarModeV2Enabled()) {
            MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.CAR_MODE_HOME);
        } else {
            MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.CAR_MODE);
        }
        initPlaybackSpeeds();
        Podcast.setDisableAlerts(true);
    }

    public static /* synthetic */ void onCarModeExit$default(CarModeUtility carModeUtility, Context context, boolean z, Integer num, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            num = null;
        }
        carModeUtility.onCarModeExit(context, z, num);
    }

    public final void onCarModeExit(Context context, boolean carDetectorExit, Integer exitMessageId) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (carDetectorExit) {
            navigateToHome(context, !carDetectorExit);
        } else {
            navigateToCarModeExitFragment(context, exitMessageId);
        }
    }

    public final void setUserExitedStatus(boolean isUserExited) {
        userExitedCarMode = isUserExited;
    }

    public final void navigateToHome(Context context, boolean userExited) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent startIntent = CarModeActivity.INSTANCE.getStartIntent(context);
        startIntent.putExtra("carModeAction", CarModeAction.EXIT_CAR_MODE);
        startIntent.putExtra("carModeUserExit", userExited);
        startIntent.addFlags(268435456);
        context.startActivity(startIntent);
    }

    public final boolean isCurrentlyInCar() {
        CarDetector carDetector2 = carDetector;
        if (carDetector2 == null) {
            return false;
        }
        if (carDetector2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carDetector");
            carDetector2 = null;
        }
        return carDetector2.isCurrentlyInCar();
    }

    public final void setCurrentlyPlayingPresetNumber(int presetNumber) {
        currentlyPlayingPresetNumber = presetNumber;
    }

    public final int getCurrentPlayingPresetNumber() {
        return currentlyPlayingPresetNumber;
    }

    public final void destroy() {
        userExitedCarMode = false;
        userDismissedOnBoarding = false;
        autoLaunchedCarMode = false;
    }

    public final void exitCarModeIfInCarMode(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.warning(TAG, "Exiting car mode if in car mode");
        if (!userExitedCarMode && isInCarMode() && autoLaunchedCarMode) {
            autoLaunchedCarMode = false;
            onCarModeExit$default(this, context, true, null, 4, null);
        }
    }

    public final String getDisclaimer(Context context) {
        String string;
        Intrinsics.checkNotNullParameter(context, "context");
        if (CarModeConfigs.INSTANCE.showAdditionalDisclaimer()) {
            string = context.getString(R.string.dmusic_car_mode_disclaimer_content_with_additional);
        } else {
            string = context.getString(R.string.dmusic_car_mode_disclaimer_content);
        }
        Intrinsics.checkNotNullExpressionValue(string, "if (CarModeConfigs.showA…laimer_content)\n        }");
        return string;
    }

    @JvmStatic
    public static final boolean isCarModeEnabled() {
        return Feature.car_mode.isEnabled();
    }

    @JvmStatic
    public static final boolean isInCarMode() {
        return AndroidUtils.getCurrentActivity() instanceof CarModeActivity;
    }

    public final boolean isUserExitedCarMode() {
        return userExitedCarMode;
    }

    public final boolean isAutoEnteredCarMode() {
        return autoLaunchedCarMode;
    }

    @WorkerThread
    public final void clearCarModePreferences(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PreferenceManager.getDefaultSharedPreferences(context).edit().remove("USER_ADD_PRESETS_PREFERENCE_KEY").remove("USER_DELETE_PRESETS_PREFERENCE_KEY").remove("CAR_MODE_DISCLAIMER").remove("CAR_MODE_WELCOME").commit();
        PresetIdentifierHelper.INSTANCE.clear(context);
    }

    public final void navigateToCarModeSearchFragment(Context context, String pageUri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent startIntent = CarModeActivity.INSTANCE.getStartIntent(context);
        startIntent.putExtra("SEARCH_PAGE_URI_EXTRA", pageUri);
        startIntent.putExtra("carModeAction", CarModeAction.SHOW_SEARCH_PAGE);
        startIntent.setFlags(268435456);
        context.startActivity(startIntent);
    }

    public final boolean shouldStartWelcomePage(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        CarModeVersion carModeVersion = CarModeVersion.INSTANCE;
        if (!carModeVersion.checkUpdate(context)) {
            return PreferenceManager.getDefaultSharedPreferences(context).getBoolean("CAR_MODE_WELCOME", true) && !userDismissedOnBoarding;
        }
        carModeVersion.save(context);
        setOnBoardingPreference(context, true);
        return true;
    }

    public final void setDisclaimerPreference(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("CAR_MODE_DISCLAIMER", false).apply();
    }

    public final void setOnBoardingPreference(Context context, boolean isAccessible) {
        Intrinsics.checkNotNullParameter(context, "context");
        PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("CAR_MODE_WELCOME", isAccessible).apply();
    }

    public final void setUserDismissedOnBoardingStatus(boolean dismissStatus) {
        userDismissedOnBoarding = dismissStatus;
    }
}
