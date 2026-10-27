package com.amazon.music.curate.skyfire.views;

import android.content.Context;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.music.curate.skyfire.OwnerRegistration;
import com.amazon.music.skyfire.platform.MethodsDispatcher;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateViewContext {
    private Context context;
    private MethodsDispatcher methodsDispatcher;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private FragmentManager parentFragmentManager;
    private LifecycleOwner viewLifecycleOwner;

    public TemplateViewContext(final String ownerId, final Context context, final LifecycleOwner viewLifecycleOwner, final FragmentManager parentFragmentManager, final OwnerRegistration ownerRegistration, final MethodsDispatcher methodsDispatcher) {
        this.ownerId = ownerId;
        this.context = context;
        this.viewLifecycleOwner = viewLifecycleOwner;
        this.parentFragmentManager = parentFragmentManager;
        this.ownerRegistration = ownerRegistration;
        this.methodsDispatcher = methodsDispatcher;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public Context getContext() {
        return this.context;
    }

    public LifecycleOwner getViewLifecycleOwner() {
        return this.viewLifecycleOwner;
    }

    public FragmentManager getParentFragmentManager() {
        return this.parentFragmentManager;
    }

    public OwnerRegistration getOwnerRegistration() {
        return this.ownerRegistration;
    }

    public MethodsDispatcher getMethodsDispatcher() {
        return this.methodsDispatcher;
    }

    public void reset() {
        this.ownerId = null;
        this.context = null;
        this.viewLifecycleOwner = null;
        this.parentFragmentManager = null;
        this.ownerRegistration = null;
        this.methodsDispatcher = null;
    }
}
