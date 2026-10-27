package com.amazon.mp3.reactnative;

import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.language.preference.AppDisplayLanguageSetting;
import com.amazon.mp3.language.preference.locale.AppLocales;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.reactnative.GenericReactFragment;
import com.amazon.music.destination.ReactNativeDestination;
import com.amazon.music.inappmessaging.internal.model.Splash;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: GenericReactExperienceInitializer.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J0\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J(\u0010\u0012\u001a\u00020\u00132\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0016\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\fR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/reactnative/GenericReactExperienceInitializer;", "", "()V", "INITIAL_PROP_LOCALE", "", "INITIAL_PROP_NATIVE_NAVIGATION_START_TIME", "INITIAL_PROP_PERF_PROFILING_ENABLED", "INITIAL_PROP_ROUTE", "createRNActivity", "Landroid/content/Intent;", "path", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", Splash.PARAMS_LOCALE, "performanceProfilingEnabled", "", "currentTimeInMilliseconds", "", "createRNFragment", "Lcom/amazon/mp3/reactnative/GenericReactFragment;", "startExperience", "", "destination", "Lcom/amazon/music/destination/ReactNativeDestination;", "hostActivity", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class GenericReactExperienceInitializer {
    private final String INITIAL_PROP_LOCALE = Splash.PARAMS_LOCALE;
    private final String INITIAL_PROP_ROUTE = "route";
    private final String INITIAL_PROP_PERF_PROFILING_ENABLED = "performanceProfilingEnabled";
    private final String INITIAL_PROP_NATIVE_NAVIGATION_START_TIME = "nativeNavigationStartTime";

    public final void startExperience(ReactNativeDestination destination, FragmentActivity hostActivity) {
        Intrinsics.checkNotNullParameter(destination, "destination");
        Intrinsics.checkNotNullParameter(hostActivity, "hostActivity");
        Trace.beginSection("AMA.GenericReactExperienceInitializer.startExperience");
        AppLocales appLocalesFromValue = AppLocales.INSTANCE.fromValue(AppDisplayLanguageSetting.INSTANCE.getDisplayLanguage(hostActivity));
        if (appLocalesFromValue == null) {
            appLocalesFromValue = AppLocales.EN_US_LOCALE;
        }
        String locale = appLocalesFromValue.getEntryValue().toLanguageTag();
        boolean zIsEnabled = Feature.react_native_performance_profiling.isEnabled();
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Intrinsics.areEqual(destination.getPresentationStyle(), "modal")) {
            String path = destination.getPath();
            Intrinsics.checkNotNullExpressionValue(locale, "locale");
            hostActivity.startActivity(createRNActivity(path, hostActivity, locale, zIsEnabled, jCurrentTimeMillis));
        } else {
            String path2 = destination.getPath();
            Intrinsics.checkNotNullExpressionValue(locale, "locale");
            GenericReactFragment genericReactFragmentCreateRNFragment = createRNFragment(path2, locale, zIsEnabled, jCurrentTimeMillis);
            if (hostActivity instanceof MusicHomeActivity) {
                ((MusicHomeActivity) hostActivity).changeScreenFragment(genericReactFragmentCreateRNFragment, true, true, true);
            } else if (hostActivity instanceof NowPlayingFragmentActivity) {
                ((NowPlayingFragmentActivity) hostActivity).changeScreenFragment(genericReactFragmentCreateRNFragment, true, true, true);
            }
        }
        Trace.endSection();
    }

    private final Intent createRNActivity(String path, FragmentActivity activity, String locale, boolean performanceProfilingEnabled, long currentTimeInMilliseconds) {
        Trace.beginSection("AMA.GenericReactExperienceInitializer.createRNActivity");
        Intent intent = new Intent(activity, (Class<?>) GenericReactActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(this.INITIAL_PROP_ROUTE, path);
        bundle.putString(this.INITIAL_PROP_LOCALE, locale);
        bundle.putString(this.INITIAL_PROP_PERF_PROFILING_ENABLED, String.valueOf(performanceProfilingEnabled));
        bundle.putString(this.INITIAL_PROP_NATIVE_NAVIGATION_START_TIME, String.valueOf(currentTimeInMilliseconds));
        intent.putExtras(bundle);
        Trace.endSection();
        return intent;
    }

    private final GenericReactFragment createRNFragment(String path, String locale, boolean performanceProfilingEnabled, long currentTimeInMilliseconds) {
        Bundle bundle = new Bundle();
        bundle.putString(this.INITIAL_PROP_ROUTE, path);
        bundle.putString(this.INITIAL_PROP_LOCALE, locale);
        bundle.putString(this.INITIAL_PROP_PERF_PROFILING_ENABLED, String.valueOf(performanceProfilingEnabled));
        bundle.putString(this.INITIAL_PROP_NATIVE_NAVIGATION_START_TIME, String.valueOf(currentTimeInMilliseconds));
        GenericReactFragment genericReactFragmentBuild = new GenericReactFragment.Builder().setComponentName("DragonFly").setLaunchOptions(bundle).build();
        Trace.endSection();
        return genericReactFragmentBuild;
    }
}
