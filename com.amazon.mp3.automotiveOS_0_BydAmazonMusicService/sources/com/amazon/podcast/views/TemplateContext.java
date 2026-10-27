package com.amazon.podcast.views;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.OwnerRegistration;
import com.amazon.podcast.bootstrap.ActionBarProvider;

/* JADX INFO: loaded from: classes5.dex */
public final class TemplateContext {
    private final ActionBarProvider actionBarProvider;
    private final FragmentManager childFragmentManager;
    private final Context context;
    private final FragmentManager fragmentManager;
    private final LifecycleOwner lifecycleOwner;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private final OwnerRegistration ownerRegistration;

    public TemplateContext(final String ownerId, final Context context, final LifecycleOwner lifecycleOwner, final FragmentManager fragmentManager, final FragmentManager childFragmentManager, final OwnerRegistration ownerRegistration, final MethodsDispatcher methodsDispatcher, final ActionBarProvider actionBarProvider) {
        this.ownerId = ownerId;
        this.context = context;
        this.lifecycleOwner = lifecycleOwner;
        this.fragmentManager = fragmentManager;
        this.ownerRegistration = ownerRegistration;
        this.childFragmentManager = childFragmentManager;
        this.methodsDispatcher = methodsDispatcher;
        this.actionBarProvider = actionBarProvider;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public Context getContext() {
        return this.context;
    }

    public LifecycleOwner getLifecycleOwner() {
        return this.lifecycleOwner;
    }

    public FragmentManager getFragmentManager() {
        return this.fragmentManager;
    }

    public OwnerRegistration getOwnerRegistration() {
        return this.ownerRegistration;
    }

    public FragmentManager getChildFragmentManager() {
        return this.childFragmentManager;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }

    public ActionBarProvider getActionBarProvider() {
        return this.actionBarProvider;
    }
}
