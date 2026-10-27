package com.amazon.mp3.library.util;

import android.content.Context;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.auto.carmode.CarModeUserPresetHandlerImpl;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.util.PlatformUtil;
import com.amazon.mp3.weblab.Weblab;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.providers.ExperimentFeatureGateProvider;
import com.amazon.music.platform.providers.FeatureGateProviderImpl;
import com.amazon.music.views.library.binders.MultiToggleViewBinder;
import com.amazon.music.views.library.binders.ToggleButtonBinder;
import com.amazon.music.views.library.models.MultiToggleViewModel;
import com.amazon.music.views.library.models.ToggleButtonModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.views.MultiToggleView;
import com.amazon.podcast.ActionBarInfo;
import com.amazon.podcast.AdvertisingPreferencesProviderImpl;
import com.amazon.podcast.DownloadSettings;
import com.amazon.podcast.FragmentInfo;
import com.amazon.podcast.NetworkSettings;
import com.amazon.podcast.PodcastDynamicMessaging;
import com.amazon.podcast.PodcastSharingProvider;
import com.amazon.podcast.UserInfo;
import com.amazon.podcast.bootstrap.Bootstrap;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MyLibraryPodcastsToggleHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J-\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\rJ9\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f¢\u0006\u0002\u0010\u0015J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u001dJ\u0006\u0010\u001e\u001a\u00020\u001dR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/amazon/mp3/library/util/MyLibraryPodcastsToggleHelper;", "", "()V", "buttons", "", "Lcom/amazon/music/views/library/models/ToggleButtonModel;", "createToggleButtonModel", "context", "Landroid/content/Context;", "buttonSizeKey", "Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;", "maxButtonWidth", "", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/keys/ButtonSizeKey;Ljava/lang/Integer;)Ljava/util/List;", "createToggleWidget", "Lcom/amazon/music/views/library/views/MultiToggleView;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;", "defaultActiveButton", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Lcom/amazon/music/views/library/views/MultiToggleView$OnValueChangedListener;ILjava/lang/Integer;)Lcom/amazon/music/views/library/views/MultiToggleView;", "getPodcastBootstrap", "Lcom/amazon/podcast/bootstrap/Bootstrap;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "deeplinkPath", "", "isMyLibraryV2Supported", "", "isPodcastsToggleSupported", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MyLibraryPodcastsToggleHelper {
    public static final MyLibraryPodcastsToggleHelper INSTANCE = new MyLibraryPodcastsToggleHelper();
    private static List<ToggleButtonModel> buttons;

    private MyLibraryPodcastsToggleHelper() {
    }

    public final MultiToggleView createToggleWidget(Context context, StyleSheet styleSheet, MultiToggleView.OnValueChangedListener listener, int defaultActiveButton, Integer maxButtonWidth) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        MultiToggleViewBinder multiToggleViewBinder = new MultiToggleViewBinder(context, styleSheet, new ToggleButtonBinder(context, styleSheet), listener);
        List<ToggleButtonModel> listCreateToggleButtonModel = createToggleButtonModel(context, ButtonSizeKey.SMALL, maxButtonWidth);
        buttons = listCreateToggleButtonModel;
        if (listCreateToggleButtonModel == null) {
            Intrinsics.throwUninitializedPropertyAccessException("buttons");
            listCreateToggleButtonModel = null;
        }
        MultiToggleViewModel multiToggleViewModel = new MultiToggleViewModel(null, false, defaultActiveButton, -2, listCreateToggleButtonModel, 1, null);
        MultiToggleView multiToggleViewCreateView = multiToggleViewBinder.createView(context);
        multiToggleViewBinder.bind(multiToggleViewCreateView, multiToggleViewModel);
        return multiToggleViewCreateView;
    }

    public final boolean isPodcastsToggleSupported() {
        return PodcastFeatureGating.PODCAST.isEnabled();
    }

    public final boolean isMyLibraryV2Supported() {
        WeblabTreatment weblabTreatmentEvaluateFeatureAndGetTreatment;
        ExperimentFeatureGateProvider experimentFeatureGateProvider = (ExperimentFeatureGateProvider) Providers.INSTANCE.get(ExperimentFeatureGateProvider.class);
        if (experimentFeatureGateProvider == null) {
            return false;
        }
        if (PlatformUtil.isFireOS()) {
            Feature feature = Feature.my_library_v2;
            String string = Weblab.DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AB.toString();
            Intrinsics.checkNotNullExpressionValue(string, "DM_CURATE_MY_LIBRARY_V2_…FIRE_TABLET_AB.toString()");
            weblabTreatmentEvaluateFeatureAndGetTreatment = experimentFeatureGateProvider.evaluateFeatureAndGetTreatment(feature, string, Weblab.DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_FIRE_TABLET_AA.toString());
        } else {
            Feature feature2 = Feature.my_library_v2;
            String string2 = Weblab.DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AB.toString();
            Intrinsics.checkNotNullExpressionValue(string2, "DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AB.toString()");
            weblabTreatmentEvaluateFeatureAndGetTreatment = experimentFeatureGateProvider.evaluateFeatureAndGetTreatment(feature2, string2, Weblab.DM_CURATE_MY_LIBRARY_V2_FRONT_PAGE_AA.toString());
        }
        return WeblabTreatment.T1 == weblabTreatmentEvaluateFeatureAndGetTreatment;
    }

    public final Bootstrap getPodcastBootstrap(FragmentActivity activity, String deeplinkPath) {
        Intrinsics.checkNotNullParameter(deeplinkPath, "deeplinkPath");
        if (activity == null) {
            return null;
        }
        FragmentInfo fragmentInfo = FragmentInfo.INSTANCE;
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "activity.applicationContext");
        return new Bootstrap(new PodcastDeeplink(deeplinkPath, false), applicationContext, new UserInfo(), ActionBarInfo.INSTANCE, fragmentInfo, new NetworkSettings(), new DownloadSettings(activity), new PodcastSharingProvider(), new FeatureGateProviderImpl(), false, new PodcastDynamicMessaging(activity), new CarModeUserPresetHandlerImpl(), new AdvertisingPreferencesProviderImpl());
    }

    private final List<ToggleButtonModel> createToggleButtonModel(Context context, ButtonSizeKey buttonSizeKey, Integer maxButtonWidth) {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new ToggleButtonModel[]{new ToggleButtonModel(null, Integer.valueOf(R.id.library_toggle_music_button), null, context.getString(R.string.dmusic_library_toggle_music), null, buttonSizeKey, null, maxButtonWidth, false, 341, null), new ToggleButtonModel(null, Integer.valueOf(R.id.library_toggle_podcasts_button), null, context.getString(R.string.dmusic_library_toggle_podcasts), null, buttonSizeKey, null, maxButtonWidth, false, 341, null)});
    }
}
