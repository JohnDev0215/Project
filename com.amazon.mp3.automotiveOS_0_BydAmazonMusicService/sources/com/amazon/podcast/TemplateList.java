package com.amazon.podcast;

import Podcast.Touch.AlertTemplateInterface.v1_0.AlertTemplate;
import Podcast.Touch.AlertTemplateInterface.v1_0.StackedButtonsAlertTemplate;
import Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.CardNowPlayingTemplate;
import Podcast.Touch.ChromeTemplateInterface.v1_0.ChromeTemplate;
import Podcast.Touch.DefaultTemplateInterface.v1_0.DefaultTemplate;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Podcast.Touch.NowPlayingTemplateInterface.v1_0.NowPlayingTemplate;
import Podcast.Touch.PTCTemplateInterface.v1_0.PTCTemplate;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.BindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateAndBindTemplateMethod;
import SOATemplateListInterface.v1_0.CreateTemplateMethod;
import SOATemplateListInterface.v1_0.RemoveTemplateMethod;
import SOATemplateListInterface.v1_0.Template;
import SOATemplateListInterface.v1_0.TemplateLaunchMode;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.podcast.bootstrap.ActionBarListener;
import com.amazon.podcast.bootstrap.ActionBarProvider;
import com.amazon.podcast.bootstrap.FragmentInfoProvider;
import com.amazon.podcast.bootstrap.FragmentManagerListener;
import com.amazon.podcast.cardNowPlaying.CardNowPlayingActivity;
import com.amazon.podcast.deeplinks.PodcastDeeplink;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.mappers.Mappers;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.podcast.nowplaying.NowPlayingActivity;
import com.amazon.podcast.views.TemplateActivity;
import com.amazon.podcast.views.TemplateFragment;
import com.amazon.podcast.views.alertTemplate.AlertTemplateActivity;
import com.amazon.podcast.views.ptcTemplate.PTCTemplateActivity;
import com.amazon.podcast.views.stackedButtonsAlertTemplate.StackedButtonsAlertTemplateActivity;
import com.amazon.soa.core.OnMethodExecutedListener;
import com.amazon.soa.core.OwnerIdGenerator;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
final class TemplateList extends FragmentManager.FragmentLifecycleCallbacks implements FragmentManager.OnBackStackChangedListener, FragmentManagerListener, ActionBarListener, Application.ActivityLifecycleCallbacks {
    private static final Logger logger = LoggerFactory.getLogger("TemplateList");
    private final ActionBarProvider actionBarProvider;
    private final Map<String, WeakReference<TemplateActivity>> activityMap;
    private final Context context;
    private Activity currentActivity;
    private final FragmentInfoProvider fragmentInfoProvider;
    private boolean isLibraryPodcastToggleSupported;
    private final boolean isRestoringPlayback;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final OwnerRegistration ownerRegistration;

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(@NonNull Activity activity) {
    }

    public TemplateList(final String ownerId, final Context context, final ActionBarProvider actionBarProvider, final FragmentInfoProvider fragmentInfoProvider, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration, final boolean isRestoringPlayback) {
        this.ownerId = ownerId;
        this.context = context;
        this.actionBarProvider = actionBarProvider;
        this.fragmentInfoProvider = fragmentInfoProvider;
        fragmentInfoProvider.addFragmentManagerListener(this);
        actionBarProvider.addActionBarListener(this);
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        this.isRestoringPlayback = isRestoringPlayback;
        this.activityMap = new HashMap();
        ((Application) context.getApplicationContext()).registerActivityLifecycleCallbacks(this);
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public final void setIsLibraryPodcastToggleSupported(final boolean isPodcastToggleSupported) {
        this.isLibraryPodcastToggleSupported = isPodcastToggleSupported;
    }

    public void bindTemplateMethod(final String ownerId, final BindTemplateMethod method, final OnMethodExecutedListener listener) {
        bindTemplateMethod(this.fragmentInfoProvider.fragmentManager(), ownerId, method);
        FragmentManager fragmentManagerChildFragmentManager = this.fragmentInfoProvider.childFragmentManager();
        if (fragmentManagerChildFragmentManager != null) {
            bindTemplateMethod(fragmentManagerChildFragmentManager, ownerId, method);
        }
        for (Map.Entry<String, WeakReference<TemplateActivity>> entry : this.activityMap.entrySet()) {
            String key = entry.getKey();
            TemplateActivity templateActivity = entry.getValue().get();
            if (templateActivity != null && ownerId.startsWith(key)) {
                templateActivity.bindTemplate(ownerId, method);
            }
        }
        listener.onMethodExecuted(method);
    }

    public void createTemplateMethod(final CreateTemplateMethod method, final OnMethodExecutedListener listener) {
        createOrCreateAndBind(method, method.getTemplate(), false, listener);
    }

    public void createAndBindTemplateMethod(final CreateAndBindTemplateMethod method, final OnMethodExecutedListener listener) {
        createOrCreateAndBind(method, method.getTemplate(), true, listener);
    }

    public void removeTemplateMethod(final String ownerId, final RemoveTemplateMethod method, final OnMethodExecutedListener listener) {
        listener.onMethodExecuted(method);
        FragmentManager fragmentManager = this.fragmentInfoProvider.fragmentManager();
        FragmentManager fragmentManagerChildFragmentManager = this.fragmentInfoProvider.childFragmentManager();
        removeTemplateMethod(fragmentManager, ownerId);
        if (fragmentManagerChildFragmentManager == null) {
            return;
        }
        removeTemplateMethod(fragmentManagerChildFragmentManager, ownerId);
    }

    public void handleTemplateMethod(final String ownerId, final Method method, final OnMethodExecutedListener listener) {
        FragmentManager fragmentManager = this.fragmentInfoProvider.fragmentManager();
        FragmentManager fragmentManagerChildFragmentManager = this.fragmentInfoProvider.childFragmentManager();
        handleTemplateMethod(fragmentManager, ownerId, method);
        if (fragmentManagerChildFragmentManager != null) {
            handleTemplateMethod(fragmentManagerChildFragmentManager, ownerId, method);
        }
        Activity activity = this.currentActivity;
        if (activity instanceof NowPlayingActivity) {
            ((NowPlayingActivity) activity).handleTemplateMethod(ownerId, this.methodsDispatcher, method);
        } else if (activity instanceof com.amazon.podcast.nowplayingstage.NowPlayingActivity) {
            ((com.amazon.podcast.nowplayingstage.NowPlayingActivity) activity).handleTemplateMethod(ownerId, this.methodsDispatcher, method);
        } else {
            for (Map.Entry<String, WeakReference<TemplateActivity>> entry : this.activityMap.entrySet()) {
                String key = entry.getKey();
                TemplateActivity templateActivity = entry.getValue().get();
                if (templateActivity != null && ownerId.startsWith(key)) {
                    templateActivity.handleTemplateMethod(ownerId, method);
                }
            }
        }
        listener.onMethodExecuted(method);
    }

    @Override // com.amazon.podcast.bootstrap.FragmentManagerListener
    public void onFragmentManagerUpdated(final FragmentManager fragmentManager) {
        logger.debug("onFragmentManagerUpdated");
        fragmentManager.addOnBackStackChangedListener(this);
        fragmentManager.registerFragmentLifecycleCallbacks(this, false);
    }

    @Override // com.amazon.podcast.bootstrap.ActionBarListener
    public void onActionBarUpdated(final ActionBar actionBar) {
        logger.debug("onActionBarUpdated");
        if (this.isRestoringPlayback) {
            return;
        }
        List<Fragment> fragments = this.fragmentInfoProvider.fragmentManager().getFragments();
        if (fragments.isEmpty()) {
            return;
        }
        updateActionBarVisibility(fragments.get(fragments.size() - 1));
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public void onBackStackChanged() {
        logger.debug("onBackStackChanged");
        List<Fragment> fragments = this.fragmentInfoProvider.fragmentManager().getFragments();
        HashSet hashSet = new HashSet();
        for (Fragment fragment : fragments) {
            if (fragment instanceof TemplateFragment) {
                hashSet.add(((TemplateFragment) fragment).getOwnerId());
            }
        }
        TemplateCache.INSTANCE.keep(hashSet);
        if (hashSet.isEmpty()) {
            logger.debug("back stack is empty removing listeners");
            FragmentManager fragmentManager = this.fragmentInfoProvider.fragmentManager();
            fragmentManager.removeOnBackStackChangedListener(this);
            fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            this.fragmentInfoProvider.removeFragmentListener(this);
            this.actionBarProvider.removeActionBarListener(this);
            this.actionBarProvider.actionBar().show();
        }
        if (fragments.isEmpty()) {
            return;
        }
        updateActionBarVisibility(fragments.get(fragments.size() - 1));
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(@NonNull final Activity activity) {
        if (activity instanceof TemplateActivity) {
            TemplateActivity templateActivity = (TemplateActivity) activity;
            String ownerId = templateActivity.getOwnerId();
            if (ownerId.startsWith(this.ownerId)) {
                this.activityMap.put(ownerId, new WeakReference<>(templateActivity));
            }
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(@NonNull final Activity activity) {
        if (activity instanceof TemplateActivity) {
            this.activityMap.remove(((TemplateActivity) activity).getOwnerId());
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(@NonNull Activity activity) {
        this.currentActivity = activity;
    }

    private void popSingleUseTemplateFragment(final FragmentManager fragmentManager) {
        Template template;
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0) {
            return;
        }
        Fragment fragment = fragments.get(fragments.size() - 1);
        if ((fragment instanceof TemplateFragment) && (template = ((TemplateFragment) fragment).getTemplate()) != null && TemplateLaunchMode.singleUse.equals(template.getLaunchMode())) {
            popFragmentBackStackWithDelay(fragmentManager, fragment, 500);
        }
    }

    private void popSingleUseTemplateActivity() {
        Iterator<WeakReference<TemplateActivity>> it = this.activityMap.values().iterator();
        while (it.hasNext()) {
            TemplateActivity templateActivity = it.next().get();
            if (templateActivity != null) {
                Template template = templateActivity.getTemplate();
                if (template == null) {
                    return;
                }
                if (TemplateLaunchMode.singleUse.equals(template.getLaunchMode())) {
                    templateActivity.finish();
                }
            }
        }
    }

    private void popFragmentBackStackWithDelay(final FragmentManager fragmentManager, final Fragment lastFragment, final int delay) {
        Handlers.INSTANCE.getForeground().postDelayed(new Runnable() { // from class: com.amazon.podcast.TemplateList.1
            @Override // java.lang.Runnable
            public void run() {
                fragmentManager.beginTransaction().remove(lastFragment).commitNowAllowingStateLoss();
            }
        }, delay);
    }

    private void createOrCreateAndBind(final Method method, final Template template, final boolean isCreateAndBind, final OnMethodExecutedListener listener) {
        int iFragmentContainerId;
        Intent intent;
        boolean zShouldShowPodcastToggleOnTemplate = shouldShowPodcastToggleOnTemplate(template);
        FragmentManager fragmentManagerForTemplate = getFragmentManagerForTemplate(template);
        popSingleUseTemplateFragment(fragmentManagerForTemplate);
        popSingleUseTemplateActivity();
        if (template instanceof NowPlayingTemplate) {
            boolean zIsEnabled = PodcastFeatureGating.PODCAST_YA_IMMERSE_NPS.isEnabled();
            if (zIsEnabled) {
                intent = new Intent(this.context, (Class<?>) com.amazon.podcast.nowplayingstage.NowPlayingActivity.class);
            } else {
                intent = new Intent(this.context, (Class<?>) NowPlayingActivity.class);
            }
            try {
                String strWriteValueAsString = Mappers.mapper().writeValueAsString(template.getOnViewed());
                if (!zIsEnabled) {
                    intent.putExtra("com.amazon.dmpplaybackservice.playback.ON_VIEWED_METHODS", strWriteValueAsString);
                    intent.putExtra("com.amazon.dmpplaybackservice.playback.ON_VIEWED_OWNER_ID", this.ownerId);
                } else {
                    intent.putExtra("com.amazon.dmpplaybackservice.playback.ON_VIEWED_METHODS", strWriteValueAsString);
                    intent.putExtra("com.amazon.dmpplaybackservice.playback.ON_VIEWED_OWNER_ID", this.ownerId);
                }
            } catch (IOException e) {
                logger.error("Failed serializing on viewed methods" + e.getMessage());
            }
            intent.addFlags(268435456);
            this.context.startActivity(intent);
            this.methodsDispatcher.dispatch(this.ownerId, template.getOnCreated());
            this.methodsDispatcher.dispatch(this.ownerId, template.getOnBound());
            listener.onMethodExecuted(method);
            return;
        }
        if ((template instanceof AlertTemplate) || (template instanceof StackedButtonsAlertTemplate)) {
            showAlertTemplate(template);
            listener.onMethodExecuted(method);
            return;
        }
        if (template instanceof PTCTemplate) {
            showPTCTemplate(template);
            listener.onMethodExecuted(method);
            return;
        }
        if (template instanceof CardNowPlayingTemplate) {
            showCardNowPlayingTemplate(template, isCreateAndBind);
            listener.onMethodExecuted(method);
            return;
        }
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.RENDERING, template != null && template.getLatencyMetricElement() != null ? template.getLatencyMetricElement().getPageType() : null);
        TemplateFragment templateFragment = new TemplateFragment();
        String strForTemplateShard = OwnerIdGenerator.forTemplateShard(this.ownerId);
        if (isCreateAndBind) {
            templateFragment.createAndBindTemplate(strForTemplateShard, template, zShouldShowPodcastToggleOnTemplate | isFacetedNavigation(template), this.actionBarProvider, this.methodsDispatcher, this.ownerRegistration);
        } else {
            templateFragment.createTemplate(strForTemplateShard, template, zShouldShowPodcastToggleOnTemplate | isFacetedNavigation(template), this.actionBarProvider, this.methodsDispatcher, this.ownerRegistration);
        }
        if (zShouldShowPodcastToggleOnTemplate) {
            iFragmentContainerId = this.fragmentInfoProvider.childFragmentContainerId().intValue();
        } else {
            iFragmentContainerId = this.fragmentInfoProvider.fragmentContainerId();
        }
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManagerForTemplate.beginTransaction();
        fragmentTransactionBeginTransaction.add(iFragmentContainerId, templateFragment, strForTemplateShard);
        fragmentTransactionBeginTransaction.addToBackStack(strForTemplateShard);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        if (getFragmentOwnerIds(fragmentManagerForTemplate).isEmpty()) {
            logger.debug("registering for fragment life cycles and back stack change listener");
            fragmentManagerForTemplate.registerFragmentLifecycleCallbacks(this, false);
            fragmentManagerForTemplate.addOnBackStackChangedListener(this);
        }
        listener.onMethodExecuted(method);
    }

    private void showAlertTemplate(final Template template) {
        if (Podcast.isAlertsDisabled()) {
            logger.debug("Showing alert is disabled");
            return;
        }
        String strForTemplateShard = OwnerIdGenerator.forTemplateShard(this.ownerId);
        TemplateCache.INSTANCE.write(strForTemplateShard, new TemplateCacheEntry(template, true, true, true, null, this.methodsDispatcher, this.ownerRegistration));
        Intent intentForAlert = getIntentForAlert(template);
        intentForAlert.putExtra("ownerId", strForTemplateShard);
        intentForAlert.addFlags(268435456);
        this.context.startActivity(intentForAlert);
    }

    private void showPTCTemplate(final Template template) {
        String strForTemplateShard = OwnerIdGenerator.forTemplateShard(this.ownerId);
        TemplateCache.INSTANCE.write(strForTemplateShard, new TemplateCacheEntry(template, true, true, true, null, this.methodsDispatcher, this.ownerRegistration));
        Intent intent = new Intent(this.context, (Class<?>) PTCTemplateActivity.class);
        intent.putExtra("ownerId", strForTemplateShard);
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    private void showCardNowPlayingTemplate(final Template template, final boolean isCreateAndBind) {
        String strForTemplateShard = OwnerIdGenerator.forTemplateShard(this.ownerId);
        TemplateCache.INSTANCE.write(strForTemplateShard, new TemplateCacheEntry(template, true, isCreateAndBind, false, null, this.methodsDispatcher, this.ownerRegistration));
        Intent intent = new Intent(this.context, (Class<?>) CardNowPlayingActivity.class);
        intent.putExtra("ownerId", strForTemplateShard);
        intent.addFlags(268435456);
        this.context.startActivity(intent);
    }

    private Set<String> getFragmentOwnerIds(final FragmentManager fragmentManager) {
        List<Fragment> fragments = fragmentManager.getFragments();
        HashSet hashSet = new HashSet();
        for (Fragment fragment : fragments) {
            if (fragment instanceof TemplateFragment) {
                hashSet.add(((TemplateFragment) fragment).getOwnerId());
            }
        }
        return hashSet;
    }

    private FragmentManager getFragmentManagerForTemplate(final Template template) {
        return shouldShowPodcastToggleOnTemplate(template) ? this.fragmentInfoProvider.childFragmentManager() : this.fragmentInfoProvider.fragmentManager();
    }

    private void bindTemplateMethod(final FragmentManager fragmentManager, final String ownerId, final BindTemplateMethod method) {
        TemplateFragment templateFragment;
        String ownerId2;
        if (ownerId == null) {
            return;
        }
        Template template = method.getTemplate();
        for (Fragment fragment : fragmentManager.getFragments()) {
            if ((fragment instanceof TemplateFragment) && (ownerId2 = (templateFragment = (TemplateFragment) fragment).getOwnerId()) != null && ownerId.startsWith(ownerId2)) {
                if ((template instanceof AlertTemplate) || (template instanceof StackedButtonsAlertTemplate)) {
                    this.fragmentInfoProvider.fragmentManager().popBackStackImmediate();
                    showAlertTemplate(template);
                } else {
                    templateFragment.bindTemplate(ownerId, method);
                }
            }
        }
    }

    private void removeTemplateMethod(final FragmentManager fragmentManager, final String ownerId) {
        if (ownerId == null) {
            return;
        }
        Activity activity = this.currentActivity;
        if ((activity instanceof NowPlayingActivity) || (activity instanceof com.amazon.podcast.nowplayingstage.NowPlayingActivity)) {
            activity.finish();
        }
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0) {
            return;
        }
        Fragment fragment = fragments.get(fragments.size() - 1);
        if ((fragment instanceof TemplateFragment) && ownerId.equals(((TemplateFragment) fragment).getOwnerId())) {
            try {
                fragmentManager.popBackStack();
            } catch (IllegalStateException e) {
                logger.error("Error in remove template method, ", (Throwable) e);
            }
        }
    }

    private void handleTemplateMethod(final FragmentManager fragmentManager, final String ownerId, final Method method) {
        TemplateFragment templateFragment;
        String ownerId2;
        if (ownerId == null) {
            return;
        }
        for (Fragment fragment : fragmentManager.getFragments()) {
            if ((fragment instanceof TemplateFragment) && (ownerId2 = (templateFragment = (TemplateFragment) fragment).getOwnerId()) != null && ownerId.startsWith(ownerId2)) {
                templateFragment.handleTemplateMethod(ownerId, method);
            }
        }
    }

    private boolean shouldShowPodcastToggleOnTemplate(final Template template) {
        return this.isLibraryPodcastToggleSupported && (template instanceof ChromeTemplate) && this.fragmentInfoProvider.childFragmentManager() != null && this.fragmentInfoProvider.childFragmentContainerId() != null;
    }

    private boolean isFacetedNavigation(final Template template) {
        PodcastDeeplink deeplinkInfo = Podcast.getClientState().getDeeplinkInfo();
        if (deeplinkInfo != null && "facetedNavigation".equals(deeplinkInfo.getIngressPage())) {
            return (template instanceof DefaultTemplate) || (template instanceof GalleryTemplate) || FacetedNavigationHelper.isGalleryTemplateV2(template);
        }
        return false;
    }

    private void updateActionBarVisibility(final Fragment fragment) {
        if (fragment instanceof TemplateFragment) {
            TemplateFragment templateFragment = (TemplateFragment) fragment;
            Template template = templateFragment.getTemplate();
            if (isFacetedNavigation(template)) {
                this.actionBarProvider.actionBar().show();
            } else {
                if (shouldShowPodcastToggleOnTemplate(template)) {
                    return;
                }
                this.actionBarProvider.actionBar().hide();
                templateFragment.showActionBar();
            }
        }
    }

    private Intent getIntentForAlert(final Template template) {
        if (template instanceof AlertTemplate) {
            return new Intent(this.context, (Class<?>) AlertTemplateActivity.class);
        }
        if (template instanceof StackedButtonsAlertTemplate) {
            return new Intent(this.context, (Class<?>) StackedButtonsAlertTemplateActivity.class);
        }
        return null;
    }
}
