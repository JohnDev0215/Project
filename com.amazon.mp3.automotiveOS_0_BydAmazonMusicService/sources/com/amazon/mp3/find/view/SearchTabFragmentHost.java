package com.amazon.mp3.find.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Trace;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.activity.settings.LastViewedScreenUtil;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.bottomnavbar.BottomBarTab;
import com.amazon.mp3.bottomnavbar.BottomNavigationBarContainer;
import com.amazon.mp3.catalog.fragment.FindBrushFragment;
import com.amazon.mp3.featuregate.Feature;
import com.amazon.mp3.find.skyfire.FindSkyfireFactory;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.reactnative.ReactExperienceNavigationRouteHelper;
import com.amazon.music.find.featuregating.FindFeatureGating;
import com.amazon.music.find.fragment.FindSkyfireFragment;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.find.viewmodels.SearchExperienceMode;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.FeatureGateProvider;
import com.amazon.music.platform.providers.InterfaceProvider;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SearchTabFragmentHost.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\r\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b=\u0010>J$\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0007J,\u0010\u000f\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0007J6\u0010\u0012\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0007J\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0007J\u0012\u0010\u0016\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010\u0017\u001a\u00020\f2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010\u0019\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u001a\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0002J\u0012\u0010\u0015\u001a\u00020\f2\b\u0010\u0018\u001a\u0004\u0018\u00010\u0004H\u0007J<\u0010\u001c\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0003J*\u0010\u001d\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0003J\u0010\u0010 \u001a\u00020\b2\u0006\u0010\u001f\u001a\u00020\u001eH\u0002J2\u0010!\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u001b\u001a\u00020\f2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\fH\u0003J\b\u0010\"\u001a\u00020\u0013H\u0007J\b\u0010#\u001a\u00020\bH\u0007J\n\u0010%\u001a\u0004\u0018\u00010$H\u0003J\u001a\u0010*\u001a\u00020)2\u0006\u0010'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u0013H\u0007J\u001a\u0010+\u001a\u00020)2\u0006\u0010'\u001a\u00020&2\b\u0010(\u001a\u0004\u0018\u00010\u0013H\u0007J\u0018\u0010-\u001a\u00020)2\u0006\u0010'\u001a\u00020&2\u0006\u0010,\u001a\u00020\u0013H\u0003R\u001c\u00100\u001a\n /*\u0004\u0018\u00010.0.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u0018\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0014\u00107\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b5\u00106R\u0014\u00108\u001a\u00020\f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b8\u00106R\u0011\u0010:\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b9\u00106R\u0011\u0010<\u001a\u00020\f8F¢\u0006\u0006\u001a\u0004\b;\u00106¨\u0006?"}, d2 = {"Lcom/amazon/mp3/find/view/SearchTabFragmentHost;", "", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "fragmentController", "Landroidx/fragment/app/Fragment;", "currentFragment", "Landroid/os/Bundle;", "extras", "", "selectTab", "Lcom/amazon/mp3/activity/MusicHomeActivity;", "musicHomeActivity", "", "addToBackStack", "forceReplace", "selectFindTab", "Lcom/amazon/music/find/viewmodels/SearchExperienceMode;", "mode", "selectSearchTab", "", "className", "isNativeFindOrSearchFragment", "isSearchFragmentClassName", "isFindFragmentClassName", "fragment", "isSearchFragment", "isFindFragment", "isInCarMode", "goToSearch", "goToFind", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "goToReactNativeFind", "goToLastActiveTab", "getFindFragmentName", "setBottomBarTab", "Lcom/amazon/mp3/bottomnavbar/BottomBarTab;", "getPreviouslySelectedTab", "Landroid/content/Context;", "context", "uri", "Landroid/content/Intent;", "getFindIntent", "getSearchIntent", "fragmentName", "getStartIntent", "Lorg/slf4j/Logger;", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "Lcom/amazon/mp3/find/view/SearchTab;", "lastActiveTab", "Lcom/amazon/mp3/find/view/SearchTab;", "getShowSkyfireLandingPage", "()Z", "showSkyfireLandingPage", "isSkyfireSearchEnabled", "getUseFindSkyfireFragment", "useFindSkyfireFragment", "getUseFindReactNativeFragment", "useFindReactNativeFragment", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class SearchTabFragmentHost {
    private static SearchTab lastActiveTab;
    public static final SearchTabFragmentHost INSTANCE = new SearchTabFragmentHost();
    private static final Logger logger = LoggerFactory.getLogger(SearchTabFragmentHost.class.getName());

    private SearchTabFragmentHost() {
    }

    private final boolean getShowSkyfireLandingPage() {
        return AmazonApplication.getCapabilities().isSkyfireFindLandingPageGroupingsAndCategoriesEnabled();
    }

    private final boolean isSkyfireSearchEnabled() {
        return AmazonApplication.getCapabilities().isSkyfireSearchFragmentEnabled();
    }

    public final boolean getUseFindSkyfireFragment() {
        return AmazonApplication.getCapabilities().isSkyfireFindLandingFragmentEnabled();
    }

    public final boolean getUseFindReactNativeFragment() {
        if (Feature.dragonfly_enabled.isEnabled()) {
            FindFeatureGating findFeatureGating = FindFeatureGating.FIND_LANDING_REACT_NATIVE;
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(FeatureGateProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            if (!(interfaceProvider instanceof FeatureGateProvider)) {
                interfaceProvider = null;
            }
            if (findFeatureGating.isEnabled((FeatureGateProvider) interfaceProvider)) {
                return true;
            }
        }
        return false;
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0018  */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void selectTab(com.amazon.mp3.activity.navigation.FragmentController r6, androidx.fragment.app.Fragment r7, android.os.Bundle r8) {
        /*
            java.lang.String r7 = "fragmentController"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r7)
            boolean r2 = com.amazon.mp3.auto.carmode.CarModeUtility.isInCarMode()
            java.lang.String r7 = "SEARCH_PAGE_URI_EXTRA"
            r0 = 0
            if (r2 == 0) goto L16
            if (r8 != 0) goto L11
            goto L18
        L11:
            java.lang.String r7 = r8.getString(r7)
            goto L1e
        L16:
            if (r8 != 0) goto L1a
        L18:
            r7 = r0
            goto L1e
        L1a:
            java.lang.String r7 = r8.getString(r7)
        L1e:
            if (r7 != 0) goto L58
            if (r8 != 0) goto L23
            goto L29
        L23:
            java.lang.String r7 = "FIND_PAGE_URI_EXTRA"
            java.lang.Object r0 = r8.get(r7)
        L29:
            r7 = 1
            r1 = 0
            if (r0 != 0) goto L54
            com.amazon.mp3.bottomnavbar.BottomBarTab r0 = getPreviouslySelectedTab()
            com.amazon.mp3.bottomnavbar.BottomBarTab r3 = com.amazon.mp3.bottomnavbar.BottomBarTab.SEARCH
            if (r0 != r3) goto L50
            com.amazon.mp3.find.view.SearchTab r0 = com.amazon.mp3.find.view.SearchTabFragmentHost.lastActiveTab
            if (r0 != 0) goto L3a
            goto L50
        L3a:
            com.amazon.mp3.find.view.SearchTab r3 = com.amazon.mp3.find.view.SearchTab.Find
            if (r0 != r3) goto L48
            com.amazon.music.find.viewmodels.SearchExperienceMode r1 = com.amazon.music.find.viewmodels.SearchExperienceMode.SEARCH_HISTORY
            r4 = 0
            r5 = 1
            r0 = r6
            r3 = r8
            goToSearch(r0, r1, r2, r3, r4, r5)
            return
        L48:
            com.amazon.mp3.find.view.SearchTab r3 = com.amazon.mp3.find.view.SearchTab.Search
            if (r0 != r3) goto L50
            goToFind(r6, r8, r1, r7)
            return
        L50:
            goToLastActiveTab(r6, r2, r8, r1, r7)
            return
        L54:
            goToFind(r6, r8, r1, r7)
            return
        L58:
            com.amazon.music.find.viewmodels.SearchExperienceMode r1 = com.amazon.music.find.utils.PageUriUtils.getExperienceMode(r7)
            r4 = 0
            r5 = 1
            r0 = r6
            r3 = r8
            goToSearch(r0, r1, r2, r3, r4, r5)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.find.view.SearchTabFragmentHost.selectTab(com.amazon.mp3.activity.navigation.FragmentController, androidx.fragment.app.Fragment, android.os.Bundle):void");
    }

    @JvmStatic
    public static final void selectFindTab(MusicHomeActivity musicHomeActivity, Bundle extras, boolean addToBackStack, boolean forceReplace) {
        Trace.beginSection("AMA.SearchTabFragmentHost.selectFindTab");
        SearchTabFragmentHost searchTabFragmentHost = INSTANCE;
        if (searchTabFragmentHost.getUseFindReactNativeFragment()) {
            MusicHomeActivity musicHomeActivity2 = musicHomeActivity instanceof FragmentActivity ? musicHomeActivity : null;
            if (musicHomeActivity2 != null) {
                searchTabFragmentHost.goToReactNativeFind(musicHomeActivity2);
                Trace.endSection();
                return;
            }
        }
        Fragment fragmentCreateFindLandingPage = searchTabFragmentHost.getShowSkyfireLandingPage() ? FindSkyfireFactory.INSTANCE.createFindLandingPage(extras) : new BrushFragmentNavigation.Builder("find").withBundle(extras).createFragment();
        if (musicHomeActivity != null) {
            musicHomeActivity.changeScreenFragment(fragmentCreateFindLandingPage, addToBackStack, forceReplace, false);
        }
        lastActiveTab = SearchTab.Find;
        Trace.endSection();
    }

    @JvmStatic
    public static final void selectSearchTab(MusicHomeActivity musicHomeActivity, SearchExperienceMode mode, Bundle extras, boolean addToBackStack, boolean forceReplace) {
        Fragment fragmentNewInstance;
        if (CarModeUtility.isInCarMode()) {
            fragmentNewInstance = CarModeSearchBrushFragment.INSTANCE.newInstance(extras);
        } else {
            fragmentNewInstance = INSTANCE.isSkyfireSearchEnabled() ? SearchHostFragment.INSTANCE.newInstance(mode, extras) : SearchBrushFragment.INSTANCE.newInstance(mode, extras);
        }
        if (musicHomeActivity != null) {
            musicHomeActivity.changeScreenFragment(fragmentNewInstance, addToBackStack, forceReplace, false);
        }
        lastActiveTab = SearchTab.Search;
    }

    @JvmStatic
    public static final boolean isNativeFindOrSearchFragment(String className) {
        SearchTabFragmentHost searchTabFragmentHost = INSTANCE;
        return searchTabFragmentHost.isSearchFragmentClassName(className) || searchTabFragmentHost.isFindFragmentClassName(className);
    }

    private final boolean isSearchFragmentClassName(String className) {
        return className != null && (SearchBrushFragment.class.getSimpleName().equals(className) || SearchHostFragment.class.getSimpleName().equals(className) || CarModeSearchBrushFragment.class.getSimpleName().equals(className));
    }

    private final boolean isFindFragmentClassName(String className) {
        boolean showSkyfireLandingPage = getShowSkyfireLandingPage();
        return className != null && ((showSkyfireLandingPage && getUseFindSkyfireFragment() && FindSkyfireFragment.class.getSimpleName().equals(className)) || ((showSkyfireLandingPage && FindLandingFragment.class.getSimpleName().equals(className)) || (!showSkyfireLandingPage && FindBrushFragment.class.getSimpleName().equals(className))));
    }

    private final boolean isSearchFragment(Fragment fragment) {
        return fragment != null && ((fragment instanceof SearchBrushFragment) || (fragment instanceof SearchHostFragment) || (fragment instanceof CarModeSearchBrushFragment));
    }

    private final boolean isFindFragment(Fragment fragment) {
        boolean showSkyfireLandingPage = getShowSkyfireLandingPage();
        return fragment != null && ((showSkyfireLandingPage && getUseFindSkyfireFragment() && (fragment instanceof FindSkyfireFragment)) || ((showSkyfireLandingPage && (fragment instanceof FindLandingFragment)) || (!showSkyfireLandingPage && (fragment instanceof FindBrushFragment))));
    }

    @JvmStatic
    public static final boolean isNativeFindOrSearchFragment(Fragment fragment) {
        SearchTabFragmentHost searchTabFragmentHost = INSTANCE;
        return searchTabFragmentHost.isSearchFragment(fragment) || searchTabFragmentHost.isFindFragment(fragment);
    }

    @JvmStatic
    private static final void goToSearch(FragmentController fragmentController, SearchExperienceMode mode, boolean isInCarMode, Bundle extras, boolean addToBackStack, boolean forceReplace) {
        Fragment fragmentNewInstance;
        if (isInCarMode) {
            fragmentNewInstance = CarModeSearchBrushFragment.INSTANCE.newInstance(extras);
        } else {
            fragmentNewInstance = INSTANCE.isSkyfireSearchEnabled() ? SearchHostFragment.INSTANCE.newInstance(mode, extras) : SearchBrushFragment.INSTANCE.newInstance(mode, extras);
        }
        fragmentController.changeScreenFragment(fragmentNewInstance, addToBackStack, forceReplace);
        lastActiveTab = SearchTab.Search;
    }

    @JvmStatic
    private static final void goToFind(FragmentController fragmentController, Bundle extras, boolean addToBackStack, boolean forceReplace) {
        Trace.beginSection("AMA.SearchTabFragmentHost.goToFind");
        SearchTabFragmentHost searchTabFragmentHost = INSTANCE;
        if (searchTabFragmentHost.getUseFindReactNativeFragment()) {
            FragmentActivity fragmentActivity = fragmentController.getFragmentActivity();
            Intrinsics.checkNotNullExpressionValue(fragmentActivity, "fragmentController.getFragmentActivity()");
            searchTabFragmentHost.goToReactNativeFind(fragmentActivity);
            Trace.endSection();
            return;
        }
        fragmentController.changeScreenFragment(searchTabFragmentHost.getShowSkyfireLandingPage() ? FindSkyfireFactory.INSTANCE.createFindLandingPage(extras) : new BrushFragmentNavigation.Builder("find").withBundle(extras).createFragment(), addToBackStack, forceReplace);
        lastActiveTab = SearchTab.Find;
        Trace.endSection();
    }

    private final void goToReactNativeFind(FragmentActivity activity) {
        Trace.beginSection("AMA.SearchTabFragmentHost.goToReactNativeFind");
        ReactExperienceNavigationRouteHelper.INSTANCE.navigateToFindLandingPageRoute(activity);
        setBottomBarTab();
        lastActiveTab = SearchTab.Find;
        Trace.endSection();
    }

    @JvmStatic
    private static final void goToLastActiveTab(FragmentController fragmentController, boolean isInCarMode, Bundle extras, boolean addToBackStack, boolean forceReplace) {
        if (lastActiveTab == SearchTab.Search) {
            goToSearch(fragmentController, null, isInCarMode, extras, addToBackStack, forceReplace);
        } else {
            goToFind(fragmentController, extras, addToBackStack, forceReplace);
        }
    }

    @JvmStatic
    public static final String getFindFragmentName() {
        SearchTabFragmentHost searchTabFragmentHost = INSTANCE;
        boolean showSkyfireLandingPage = searchTabFragmentHost.getShowSkyfireLandingPage();
        boolean useFindSkyfireFragment = searchTabFragmentHost.getUseFindSkyfireFragment();
        if (showSkyfireLandingPage && useFindSkyfireFragment) {
            String simpleName = FindSkyfireFragment.class.getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName, "{\n            FindSkyfir…java.simpleName\n        }");
            return simpleName;
        }
        if (showSkyfireLandingPage) {
            String simpleName2 = FindLandingFragment.class.getSimpleName();
            Intrinsics.checkNotNullExpressionValue(simpleName2, "{\n            FindLandin…java.simpleName\n        }");
            return simpleName2;
        }
        String simpleName3 = FindBrushFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName3, "{\n            FindBrushF…java.simpleName\n        }");
        return simpleName3;
    }

    @JvmStatic
    public static final void setBottomBarTab() {
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            BottomNavigationBarContainer.getInstance().setBottomBarTab(LastViewedScreenUtil.LastViewedScreen.SEARCH);
        }
    }

    @JvmStatic
    private static final BottomBarTab getPreviouslySelectedTab() {
        if (BottomNavigationBarContainer.getInstance().isEnabled()) {
            return BottomNavigationBarContainer.getInstance().getCurrentlySelectedBottomBarTab();
        }
        return null;
    }

    @JvmStatic
    public static final Intent getFindIntent(Context context, String uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intent startIntent = getStartIntent(context, getFindFragmentName());
        if (uri != null) {
            startIntent.putExtra("FIND_PAGE_URI_EXTRA", uri);
        }
        return startIntent;
    }

    @JvmStatic
    public static final Intent getSearchIntent(Context context, String uri) {
        Intrinsics.checkNotNullParameter(context, "context");
        String simpleName = SearchBrushFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "SearchBrushFragment::class.java.simpleName");
        Intent startIntent = getStartIntent(context, simpleName);
        if (uri != null) {
            startIntent.putExtra("SEARCH_PAGE_URI_EXTRA", uri);
            startIntent.putExtra("SEARCH_EXPERIENCE_MODE_EXTRA", PageUriUtils.getExperienceMode(uri));
        }
        return startIntent;
    }

    @JvmStatic
    private static final Intent getStartIntent(Context context, String fragmentName) {
        Intent intent = MusicHomeActivity.getStartIntent(context);
        intent.putExtra("com.amazon.mp3.fragment.extra", fragmentName);
        intent.putExtra("com.amazon.mp3.STANDALONE_FRAGMENT_KEY", true);
        Intrinsics.checkNotNullExpressionValue(intent, "intent");
        return intent;
    }
}
