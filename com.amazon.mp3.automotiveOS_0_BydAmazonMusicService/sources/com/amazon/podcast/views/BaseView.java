package com.amazon.podcast.views;

import SOATemplateListInterface.v1_0.Template;
import android.widget.LinearLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;

/* JADX INFO: loaded from: classes5.dex */
public abstract class BaseView<T extends Template> extends LinearLayout implements ViewBinder<T>, UiMetrics {
    private final TemplateContext templateContext;

    public BaseView(final TemplateContext templateContext) {
        super(templateContext.getContext());
        this.templateContext = templateContext;
        setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
    }

    public String getOwnerId() {
        return this.templateContext.getOwnerId();
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.templateContext.getMethodsDispatcher();
    }

    public LifecycleOwner getLifecycleOwner() {
        return this.templateContext.getLifecycleOwner();
    }

    public void handleBackPressed() {
        handleOnViewedForBackPress();
        this.templateContext.getFragmentManager().popBackStack();
    }

    public FragmentManager getChildFragmentManager() {
        return this.templateContext.getChildFragmentManager();
    }

    public OwnerRegistration getOwnerRegistration() {
        return this.templateContext.getOwnerRegistration();
    }

    public TemplateContext getTemplateContext() {
        return this.templateContext;
    }

    private void handleOnViewedForBackPress() {
        Fragment priorFragment = getPriorFragment();
        if (priorFragment instanceof TemplateFragment) {
            ((TemplateFragment) priorFragment).handleOnViewed();
        }
    }

    private Fragment getPriorFragment() {
        FragmentManager fragmentManager = this.templateContext.getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() < 2) {
            return null;
        }
        return fragmentManager.findFragmentByTag(fragmentManager.getBackStackEntryAt(fragmentManager.getBackStackEntryCount() - 2).getName());
    }
}
