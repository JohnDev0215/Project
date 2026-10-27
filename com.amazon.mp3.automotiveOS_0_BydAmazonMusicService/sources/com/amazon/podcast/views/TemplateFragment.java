package com.amazon.podcast.views;

import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.BindTemplateMethod;
import SOATemplateListInterface.v1_0.Template;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.TemplateCache;
import com.amazon.podcast.TemplateCacheEntry;
import com.amazon.podcast.bootstrap.ActionBarProvider;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.podcast.views.contextMenu.ContextMenus;
import com.amazon.podcast.views.galleryTemplate.GalleryTemplateView;
import com.amazon.podcast.views.ptcPodcastsTemplate.PTCBottomSheet;
import com.amazon.podcast.views.refinements.CheckBoxRefinementOptions;
import java.lang.ref.SoftReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public class TemplateFragment extends Fragment {
    private static final Logger logger = LoggerFactory.getLogger("TemplateFragment");
    private ActionBarProvider actionBarProvider;
    private boolean hidePodcastActionBar;
    private boolean isCreatedAndBind;
    private FragmentManager mChildFragmentManager;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private Template template;
    private SoftReference<RelativeLayout> viewContainerReference;

    public String getOwnerId() {
        return this.ownerId;
    }

    public void createTemplate(final String ownerId, final Template template, final boolean hidePodcastActionBar, final ActionBarProvider actionBarProvider, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.template = template;
        this.hidePodcastActionBar = hidePodcastActionBar;
        this.actionBarProvider = actionBarProvider;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        this.isCreatedAndBind = false;
    }

    public void createAndBindTemplate(final String ownerId, final Template template, final boolean hidePodcastActionBar, final ActionBarProvider actionBarProvider, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.template = template;
        this.hidePodcastActionBar = hidePodcastActionBar;
        this.actionBarProvider = actionBarProvider;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        this.isCreatedAndBind = true;
    }

    public Template getTemplate() {
        return this.template;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.ownerRegistration.registerOwner(this.ownerId);
            ActionBarProvider actionBarProvider = this.actionBarProvider;
            if (actionBarProvider == null || actionBarProvider.actionBar() == null || this.hidePodcastActionBar) {
                return;
            }
            this.actionBarProvider.actionBar().hide();
            return;
        }
        String string = savedInstanceState.getString("ownerId");
        TemplateCacheEntry templateCacheEntry = TemplateCache.INSTANCE.read(string);
        if (templateCacheEntry == null) {
            logger.debug("cache is null");
            return;
        }
        logger.debug("cache restored for template {}", templateCacheEntry.getTemplate().getClass().getSimpleName());
        boolean zIsCreateAndBind = templateCacheEntry.isCreateAndBind();
        boolean zIsHideActionBar = templateCacheEntry.isHideActionBar();
        if (zIsCreateAndBind) {
            createAndBindTemplate(string, templateCacheEntry.getTemplate(), zIsHideActionBar, templateCacheEntry.getActionBarProvider(), templateCacheEntry.getMethodsDispatcher(), templateCacheEntry.getOwnerRegistration());
        } else {
            createTemplate(string, templateCacheEntry.getTemplate(), zIsHideActionBar, templateCacheEntry.getActionBarProvider(), templateCacheEntry.getMethodsDispatcher(), templateCacheEntry.getOwnerRegistration());
        }
        ActionBarProvider actionBarProvider2 = this.actionBarProvider;
        if (actionBarProvider2 != null && actionBarProvider2.actionBar() != null && !zIsHideActionBar) {
            this.actionBarProvider.actionBar().hide();
        }
        this.ownerRegistration.registerOwner(string);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R$layout.podcast_template_fragment, container, false);
        if (this.methodsDispatcher == null) {
            return viewInflate;
        }
        this.viewContainerReference = new SoftReference<>((RelativeLayout) viewInflate.findViewById(R$id.view_container));
        if (this.template != null) {
            this.mChildFragmentManager = getChildFragmentManager();
            View viewFromTemplate = TemplateFactory.fromTemplate(this.template, getTemplateContext());
            bindView(viewFromTemplate, this.template);
            this.methodsDispatcher.dispatch(this.ownerId, this.template.getOnCreated());
            if (this.isCreatedAndBind) {
                this.methodsDispatcher.dispatch(this.ownerId, this.template.getOnBound());
            }
            LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, this.template.getLatencyMetricElement() != null ? this.template.getLatencyMetricElement().getPageType() : null);
            SoftReference<RelativeLayout> softReference = this.viewContainerReference;
            if (softReference != null && softReference.get() != null) {
                this.viewContainerReference.get().addView(viewFromTemplate);
            }
        }
        return this.viewContainerReference.get();
    }

    @Override // androidx.fragment.app.Fragment
    public void onSaveInstanceState(@NonNull final Bundle outState) {
        super.onSaveInstanceState(outState);
        Template template = this.template;
        if (template == null) {
            logger.debug("onSaveInstanceState, template is null");
            return;
        }
        TemplateCache.INSTANCE.write(this.ownerId, new TemplateCacheEntry(template, this.hidePodcastActionBar, this.isCreatedAndBind, false, this.actionBarProvider, this.methodsDispatcher, this.ownerRegistration));
        logger.debug("onSaveInstanceState called. Saving template {}", this.template.getClass().getSimpleName());
        outState.putString("ownerId", this.ownerId);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        ContextMenus.dismissContextMenuPopUp();
        CheckBoxRefinementOptions.dismissRefinementOptionsPopUp();
        PTCBottomSheet.dismissPTCBottomSheetPopup();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        SoftReference<RelativeLayout> softReference = this.viewContainerReference;
        if (softReference == null || softReference.get() == null) {
            return;
        }
        this.viewContainerReference.get().removeAllViews();
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        SoftReference<RelativeLayout> softReference = this.viewContainerReference;
        if (softReference != null) {
            softReference.clear();
        }
        this.viewContainerReference = null;
        OwnerRegistration ownerRegistration = this.ownerRegistration;
        if (ownerRegistration == null) {
            return;
        }
        ownerRegistration.deregisterOwner(this.ownerId);
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        SoftReference<RelativeLayout> softReference = this.viewContainerReference;
        if (softReference == null || softReference.get() == null) {
            return;
        }
        ((ViewBinder) this.viewContainerReference.get().getChildAt(0)).onConfigurationChanged();
    }

    public void handleTemplateMethod(final String ownerId, final Method method) {
        SoftReference<RelativeLayout> softReference = this.viewContainerReference;
        if (softReference == null || softReference.get() == null) {
            return;
        }
        ((ViewBinder) this.viewContainerReference.get().getChildAt(0)).handleTemplateMethod(ownerId, method);
    }

    public void handleOnViewed() {
        SoftReference<RelativeLayout> softReference = this.viewContainerReference;
        if (softReference == null || softReference.get() == null) {
            return;
        }
        ((BaseView) this.viewContainerReference.get().getChildAt(0)).handleOnViewed();
    }

    public void showActionBar() {
        SoftReference<RelativeLayout> softReference = this.viewContainerReference;
        if (softReference == null || softReference.get() == null) {
            return;
        }
        View childAt = this.viewContainerReference.get().getChildAt(0);
        if (childAt instanceof GalleryTemplateView) {
            ((GalleryTemplateView) childAt).showActionBar();
        }
    }

    public void bindTemplate(final String sourceOwnerID, final BindTemplateMethod method) {
        SoftReference<RelativeLayout> softReference;
        KeyEvent.Callback childAt;
        Template template = method.getTemplate();
        String pageType = template != null && template.getLatencyMetricElement() != null ? template.getLatencyMetricElement().getPageType() : null;
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.RENDERING, pageType);
        if (!sourceOwnerID.equals(this.ownerId) && sourceOwnerID.startsWith(this.ownerId)) {
            SoftReference<RelativeLayout> softReference2 = this.viewContainerReference;
            if (softReference2 == null || softReference2.get() == null || (childAt = this.viewContainerReference.get().getChildAt(0)) == null) {
                return;
            }
            ((ViewBinder) childAt).handleTemplateMethod(sourceOwnerID, method);
            return;
        }
        if (template.getClass() != this.template.getClass() && (softReference = this.viewContainerReference) != null && softReference.get() != null) {
            this.viewContainerReference.get().removeAllViews();
            View viewFromTemplate = TemplateFactory.fromTemplate(template, getTemplateContext());
            this.viewContainerReference.get().addView(viewFromTemplate);
            bindView(viewFromTemplate, template);
        } else {
            SoftReference<RelativeLayout> softReference3 = this.viewContainerReference;
            if (softReference3 == null || softReference3.get() == null) {
                return;
            } else {
                bindView(this.viewContainerReference.get().getChildAt(0), template);
            }
        }
        this.methodsDispatcher.dispatch(this.ownerId, template.getOnBound());
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, pageType);
        this.template = template;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void bindView(final View view, final Template template) {
        if (view == 0) {
            return;
        }
        ((ViewBinder) view).bind(template, this.hidePodcastActionBar);
    }

    private TemplateContext getTemplateContext() {
        return new TemplateContext(this.ownerId, getContext(), getViewLifecycleOwner(), getFragmentManager(), this.mChildFragmentManager, this.ownerRegistration, this.methodsDispatcher, this.actionBarProvider);
    }
}
