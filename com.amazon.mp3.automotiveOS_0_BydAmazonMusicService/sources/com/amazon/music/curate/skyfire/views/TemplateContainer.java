package com.amazon.music.curate.skyfire.views;

import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Template;
import CoreInterface.v1_0.TemplateLaunchMode;
import TemplateContainerInterface.v1_0.BindManagedContainerMethod;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod;
import TemplateListInterface.v1_0.RemoveTemplateMethod;
import Touch.WidgetsTemplateInterface.v1_0.WidgetsTemplate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.music.curate.Handlers;
import com.amazon.music.curate.provider.FragmentsStackProvider;
import com.amazon.music.curate.skyfire.OwnerRegistration;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.core.OwnerIdGenerator;
import com.amazon.music.curate.skyfire.util.WidgetsHelper;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateContainer extends FragmentManager.FragmentLifecycleCallbacks implements FragmentManager.OnBackStackChangedListener, FragmentsStackProvider.FragmentManagerListener {
    private static final int POP_BACK_STACK_DELAY_MS = 500;
    private static final String TAG = "TemplateContainer";
    private static final Logger logger = LoggerFactory.getLogger(TemplateContainer.class.getSimpleName());
    private CurateBootstrapProviders curateBootstrapProviders;
    private FragmentsStackProvider fragmentsStackProvider;
    private MethodsDispatcher methodsDispatcher;
    private final String ownerId;
    private OwnerRegistration ownerRegistration;

    private void createOrCreateAndBindStandaloneContainer(final Method method, final Template template, final boolean isCreateAndBind, final OnMethodExecutedListener listener) {
    }

    public TemplateContainer(final String ownerId, final CurateBootstrapProviders curateBootstrapProviders, final MethodsDispatcher methodsDispatcher, final OwnerRegistration ownerRegistration) {
        this.ownerId = ownerId;
        this.curateBootstrapProviders = curateBootstrapProviders;
        this.fragmentsStackProvider = curateBootstrapProviders.getFragmentsStackProvider();
        this.methodsDispatcher = methodsDispatcher;
        this.ownerRegistration = ownerRegistration;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void createManagedContainerMethod(final CreateManagedContainerMethod method, final OnMethodExecutedListener listener) {
        createOrCreateAndBindManagedContainer(method, method.template(), false, listener);
    }

    public void createAndBindManagedContainerMethod(final CreateAndBindManagedContainerMethod method, final OnMethodExecutedListener listener) {
        if (this.curateBootstrapProviders == null) {
            return;
        }
        if (method.template() instanceof WidgetsTemplate) {
            WidgetsTemplate widgetsTemplate = (WidgetsTemplate) method.template();
            this.curateBootstrapProviders.getPlaylistProvider().setSuggestionData(WidgetsHelper.convertToModels(widgetsTemplate), WidgetsHelper.getMaximumItems(widgetsTemplate), WidgetsHelper.getSuggestionsHeader(widgetsTemplate));
            listener.onMethodExecuted(method);
            return;
        }
        createOrCreateAndBindManagedContainer(method, method.template(), true, listener);
    }

    public void createStandaloneContainerMethod(final CreateStandaloneContainerMethod method, final OnMethodExecutedListener listener) {
        createOrCreateAndBindStandaloneContainer(method, method.template(), false, listener);
    }

    public CurateBootstrapProviders getCurateBootstrapProviders() {
        return this.curateBootstrapProviders;
    }

    public void createAndBindStandaloneContainerMethod(final CreateAndBindStandaloneContainerMethod method, final OnMethodExecutedListener listener) {
        createOrCreateAndBindStandaloneContainer(method, method.template(), true, listener);
    }

    public void bindManagedContainerMethod(final String ownerId, final BindManagedContainerMethod method, final OnMethodExecutedListener listener) {
        bindTemplateMethod(ownerId, method, listener);
    }

    public void bindStandaloneContainerMethod(final String ownerId, final BindStandaloneContainerMethod method, final OnMethodExecutedListener listener) {
        bindTemplateMethod(ownerId, method, listener);
    }

    public void removeTemplateMethod(final String ownerId, final RemoveTemplateMethod method, final OnMethodExecutedListener listener) {
        listener.onMethodExecuted(method);
        FragmentManager fragmentManager = this.fragmentsStackProvider.fragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0) {
            return;
        }
        for (Fragment fragment : fragments) {
            if (fragment instanceof TemplateFragment) {
                TemplateFragment templateFragment = (TemplateFragment) fragment;
                String ownerId2 = templateFragment.getOwnerId();
                if (StringUtils.isNotBlank(ownerId) && StringUtils.isNotBlank(ownerId2) && ownerId.startsWith(ownerId2)) {
                    templateFragment.removeTemplate(ownerId, method);
                    try {
                        fragmentManager.popBackStack();
                        return;
                    } catch (IllegalStateException e) {
                        logger.error("Error in remove template method, ", (Throwable) e);
                        return;
                    }
                }
            }
        }
    }

    public void handleTemplateMethod(final String ownerId, final Method method, final OnMethodExecutedListener listener) {
        FragmentsStackProvider fragmentsStackProvider = this.fragmentsStackProvider;
        if (fragmentsStackProvider == null) {
            return;
        }
        for (Fragment fragment : fragmentsStackProvider.fragmentManager().getFragments()) {
            if (fragment instanceof TemplateFragment) {
                TemplateFragment templateFragment = (TemplateFragment) fragment;
                String ownerId2 = templateFragment.getOwnerId();
                if (ownerId != null && ownerId.startsWith(ownerId2)) {
                    templateFragment.handleTemplateMethod(ownerId, method);
                }
            }
        }
        listener.onMethodExecuted(method);
    }

    @Override // com.amazon.music.curate.provider.FragmentsStackProvider.FragmentManagerListener
    public void onFragmentManagerUpdated(final FragmentManager fragmentManager) {
        logger.debug("onFragmentManagerUpdated");
        fragmentManager.addOnBackStackChangedListener(this);
        fragmentManager.registerFragmentLifecycleCallbacks(this, false);
    }

    @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
    public void onBackStackChanged() {
        logger.debug("onBackStackChanged");
        if (this.curateBootstrapProviders == null || this.methodsDispatcher == null || this.ownerRegistration == null) {
            return;
        }
        List<Fragment> fragments = this.fragmentsStackProvider.fragmentManager().getFragments();
        HashSet hashSet = new HashSet();
        for (Fragment fragment : fragments) {
            if (fragment instanceof TemplateFragment) {
                hashSet.add(((TemplateFragment) fragment).getOwnerId());
            }
        }
        if (hashSet.isEmpty()) {
            FragmentManager fragmentManager = this.fragmentsStackProvider.fragmentManager();
            fragmentManager.removeOnBackStackChangedListener(this);
            fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            this.fragmentsStackProvider.removeFragmentListener(this);
        }
    }

    private void createOrCreateAndBindManagedContainer(final Method method, final Template template, final boolean isCreateAndBind, final OnMethodExecutedListener listener) {
        if (this.curateBootstrapProviders == null || this.methodsDispatcher == null || this.ownerRegistration == null) {
            logger.debug("Curate library loading has been reset");
            return;
        }
        removeSingleUseTemplate();
        String strForTemplate = OwnerIdGenerator.forTemplate(this.ownerId);
        TemplateFragment templateFragmentNewInstance = TemplateFragment.newInstance();
        templateFragmentNewInstance.setCurateBootstrapProviders(this.curateBootstrapProviders);
        if (isCreateAndBind) {
            templateFragmentNewInstance.createAndBindTemplate(strForTemplate, template, this.methodsDispatcher, this.ownerRegistration);
        } else {
            templateFragmentNewInstance.createTemplate(strForTemplate, template, this.methodsDispatcher, this.ownerRegistration);
        }
        FragmentManager fragmentManager = this.fragmentsStackProvider.fragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        fragmentTransactionBeginTransaction.replace(this.fragmentsStackProvider.fragmentContainerId(), templateFragmentNewInstance, strForTemplate);
        fragmentTransactionBeginTransaction.addToBackStack(null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        if (getFragmentOwnerIds().isEmpty()) {
            logger.debug("registering for fragment life cycles and back stack change listener");
            fragmentManager.registerFragmentLifecycleCallbacks(this, false);
            fragmentManager.addOnBackStackChangedListener(this);
        }
        listener.onMethodExecuted(method);
    }

    private Set<String> getFragmentOwnerIds() {
        List<Fragment> fragments = this.fragmentsStackProvider.fragmentManager().getFragments();
        HashSet hashSet = new HashSet();
        for (Fragment fragment : fragments) {
            if (fragment instanceof TemplateFragment) {
                hashSet.add(((TemplateFragment) fragment).getOwnerId());
            }
        }
        return hashSet;
    }

    private void bindTemplateMethod(final String ownerId, final Method method, final OnMethodExecutedListener listener) {
        for (Fragment fragment : this.fragmentsStackProvider.fragmentManager().getFragments()) {
            if (fragment instanceof TemplateFragment) {
                TemplateFragment templateFragment = (TemplateFragment) fragment;
                if (ownerId.startsWith(templateFragment.getOwnerId())) {
                    templateFragment.bindTemplate(ownerId, method);
                }
            }
        }
        listener.onMethodExecuted(method);
    }

    private void removeSingleUseTemplate() {
        Template template;
        FragmentManager fragmentManager = this.fragmentsStackProvider.fragmentManager();
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null || fragments.size() == 0) {
            return;
        }
        Fragment fragment = fragments.get(fragments.size() - 1);
        if ((fragment instanceof TemplateFragment) && (template = ((TemplateFragment) fragment).getTemplate()) != null && TemplateLaunchMode.singleUse.equals(template.launchMode())) {
            removeFragmentFromBackStackWithDelay(fragmentManager, fragment, 500);
        }
    }

    private void removeFragmentFromBackStackWithDelay(final FragmentManager fragmentManager, final Fragment fragment, final int delay) {
        Handlers.INSTANCE.getForeground().postDelayed(new Runnable() { // from class: com.amazon.music.curate.skyfire.views.TemplateContainer.1
            @Override // java.lang.Runnable
            public void run() {
                fragmentManager.beginTransaction().remove(fragment).commitNow();
            }
        }, delay);
    }

    public void reset() {
        logger.debug("Reset TemplateContainer");
        this.ownerRegistration = null;
        this.methodsDispatcher = null;
        FragmentsStackProvider fragmentsStackProvider = this.fragmentsStackProvider;
        if (fragmentsStackProvider != null) {
            FragmentManager fragmentManager = fragmentsStackProvider.fragmentManager();
            fragmentManager.removeOnBackStackChangedListener(this);
            fragmentManager.unregisterFragmentLifecycleCallbacks(this);
            this.fragmentsStackProvider.removeFragmentListener(this);
            this.fragmentsStackProvider = null;
        }
        CurateBootstrapProviders curateBootstrapProviders = this.curateBootstrapProviders;
        if (curateBootstrapProviders != null) {
            curateBootstrapProviders.reset();
            this.curateBootstrapProviders = null;
        }
    }
}
