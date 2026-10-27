package com.amazon.music.curate.skyfire.views;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import TemplateContainerInterface.v1_0.BindManagedContainerMethod;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateListInterface.v1_0.RemoveTemplateMethod;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import com.amazon.music.curate.R;
import com.amazon.music.curate.skyfire.OwnerRegistration;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.views.defaultTemplate.DefaultTemplateView;
import com.amazon.music.curate.skyfire.views.galleryTemplate.GalleryTemplateView;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public class TemplateFragment extends Fragment {
    private static final String OWNER_ID;
    private static final String TAG;
    private static final Logger logger;
    private View childView;
    private CurateBootstrapProviders curateBootstrapProviders;
    private boolean isCreatedAndBind;
    private MethodsDispatcher<Method> methodsDispatcher;
    private String ownerId;
    private OwnerRegistration ownerRegistration;
    private Template template;
    private View templateView;
    private TemplateViewContext templateViewContext;
    private ConstraintLayout viewContainer;

    static {
        String simpleName = TemplateFragment.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
        OWNER_ID = TemplateFragment.class.getSimpleName();
    }

    public static TemplateFragment newInstance() {
        Bundle bundle = new Bundle();
        TemplateFragment templateFragment = new TemplateFragment();
        templateFragment.setArguments(bundle);
        return templateFragment;
    }

    public void setCurateBootstrapProviders(final CurateBootstrapProviders bootstrapProviders) {
        this.curateBootstrapProviders = bootstrapProviders;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            this.ownerRegistration.registerOwner(this.ownerId);
        }
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View viewInflate = inflater.inflate(R.layout.curate_template_fragment, container, false);
        this.templateView = viewInflate;
        if (this.methodsDispatcher == null) {
            return viewInflate;
        }
        this.viewContainer = (ConstraintLayout) viewInflate.findViewById(R.id.template_container);
        if (this.template != null) {
            removeSpinner();
            TemplateViewContext templateViewContextCreateTemplateViewContext = createTemplateViewContext();
            this.templateViewContext = templateViewContextCreateTemplateViewContext;
            View viewFromTemplate = TemplateFactory.fromTemplate(this.template, templateViewContextCreateTemplateViewContext, this.curateBootstrapProviders);
            this.childView = viewFromTemplate;
            bindView(viewFromTemplate, this.template);
            this.methodsDispatcher.dispatchMethods(this.ownerId, this.template.onCreated());
            if (this.isCreatedAndBind) {
                this.methodsDispatcher.dispatchMethods(this.ownerId, this.template.onBound());
            }
            this.viewContainer.addView(this.childView);
        }
        return this.viewContainer;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        OwnerRegistration ownerRegistration = this.ownerRegistration;
        if (ownerRegistration != null) {
            ownerRegistration.deregisterOwner(this.ownerId);
            this.ownerRegistration = null;
        }
        this.methodsDispatcher = null;
        CurateBootstrapProviders curateBootstrapProviders = this.curateBootstrapProviders;
        if (curateBootstrapProviders != null) {
            curateBootstrapProviders.reset();
            this.curateBootstrapProviders = null;
        }
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(final Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ConstraintLayout constraintLayout = this.viewContainer;
        if (constraintLayout == null) {
            return;
        }
        ((TemplateViewBinder) constraintLayout.getChildAt(0)).onConfigurationChanged();
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void createTemplate(final String ownerId, final Template template, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.template = template;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        this.isCreatedAndBind = false;
    }

    public void createAndBindTemplate(final String ownerId, final Template template, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.template = template;
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
        this.isCreatedAndBind = true;
    }

    public void bindTemplate(final String sourceOwnerID, final Method method) {
        Template template;
        if (!sourceOwnerID.equals(this.ownerId) && sourceOwnerID.startsWith(this.ownerId)) {
            ((TemplateViewBinder) this.viewContainer.getChildAt(0)).handleTemplateMethod(sourceOwnerID, method);
            return;
        }
        if (method instanceof BindManagedContainerMethod) {
            template = ((BindManagedContainerMethod) method).template();
        } else {
            template = ((BindStandaloneContainerMethod) method).template();
        }
        if (template.getClass() != this.template.getClass()) {
            this.viewContainer.removeAllViews();
            TemplateViewContext templateViewContextCreateTemplateViewContext = createTemplateViewContext();
            this.templateViewContext = templateViewContextCreateTemplateViewContext;
            View viewFromTemplate = TemplateFactory.fromTemplate(template, templateViewContextCreateTemplateViewContext, this.curateBootstrapProviders);
            this.viewContainer.addView(viewFromTemplate);
            bindView(viewFromTemplate, template);
        } else {
            bindView(this.viewContainer.getChildAt(0), template);
        }
        this.methodsDispatcher.dispatchMethods(this.ownerId, template.onBound());
        this.template = template;
    }

    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof TriggerExternalActionMethod) {
            new ExternalActionBinder(getActivity(), this.curateBootstrapProviders).handleExternalAction((TriggerExternalActionMethod) method);
        } else {
            ((TemplateViewBinder) this.viewContainer.getChildAt(0)).handleTemplateMethod(ownerId, method);
        }
    }

    public Template getTemplate() {
        return this.template;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void bindView(final View view, final Template template) {
        ((TemplateViewBinder) view).bind(template);
    }

    private TemplateViewContext createTemplateViewContext() {
        return new TemplateViewContext(this.ownerId, getContext(), getViewLifecycleOwner(), getParentFragmentManager(), this.ownerRegistration, this.methodsDispatcher);
    }

    public void removeTemplate(final String sourceOwnerID, final Method method) {
        if (!sourceOwnerID.equals(this.ownerId) && sourceOwnerID.startsWith(this.ownerId) && (method instanceof RemoveTemplateMethod)) {
            removeSpinner();
        }
    }

    private void removeSpinner() {
        ConstraintLayout constraintLayout = this.viewContainer;
        if (constraintLayout != null) {
            int childCount = constraintLayout.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View childAt = this.viewContainer.getChildAt(i);
                if (childAt instanceof DefaultTemplateView) {
                    this.viewContainer.removeView(childAt);
                    return;
                }
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        View view = this.childView;
        if (view instanceof GalleryTemplateView) {
            ((GalleryTemplateView) view).clearAdapter();
            ((GalleryTemplateView) this.childView).removeListeners();
        }
        ConstraintLayout constraintLayout = this.viewContainer;
        if (constraintLayout != null) {
            constraintLayout.removeAllViews();
            this.viewContainer = null;
        }
        this.templateView = null;
        this.childView = null;
        this.template = null;
        this.methodsDispatcher = null;
        TemplateViewContext templateViewContext = this.templateViewContext;
        if (templateViewContext != null) {
            templateViewContext.reset();
            this.templateViewContext = null;
        }
    }
}
