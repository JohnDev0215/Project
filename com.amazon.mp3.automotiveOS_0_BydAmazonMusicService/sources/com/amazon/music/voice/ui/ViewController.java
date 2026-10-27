package com.amazon.music.voice.ui;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.ArrayMap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.appcompat.view.ContextThemeWrapper;
import java.util.Iterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public abstract class ViewController {
    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class.getSimpleName());
    private Bundle arguments;
    private boolean hasAttached;
    private boolean hasCreated;
    private boolean keepViewWhenInBackStack;
    private Bundle pendingViewStateToRestore;
    private Router router;
    private SparseArray<Parcelable> savedViewHierarchyState;
    private int themeResId;
    private View view;
    private final ArrayMap<String, Router> childRouters = new ArrayMap<>();
    private final ArrayMap<String, Bundle> childRouteStateToRestore = new ArrayMap<>();

    protected void onAttach(@NonNull View view) {
    }

    protected void onCreate() {
    }

    @NonNull
    protected abstract View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup);

    protected void onDestroy() {
    }

    protected void onDestroyView(@NonNull View view) {
    }

    protected void onDetach(@NonNull View view) {
    }

    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle bundle) {
    }

    protected void onSaveViewState(@NonNull View view, @NonNull Bundle bundle) {
    }

    public final View getView() {
        return this.view;
    }

    protected final boolean keepViewWhenInBackStack() {
        return this.keepViewWhenInBackStack;
    }

    public final boolean isCreated() {
        return this.hasCreated;
    }

    public boolean isAttached() {
        return this.hasAttached;
    }

    final void attach(@NonNull ViewGroup viewGroup) {
        attach(viewGroup, -1);
    }

    final void attach(@NonNull ViewGroup viewGroup, int i) {
        View view = this.view;
        if (view != null && (view.getParent() == null || this.view.getParent() != viewGroup)) {
            detach();
        }
        if (this.view == null) {
            Logger logger = LOG;
            logger.debug("{}: Create view", getClass().getSimpleName());
            Context context = viewGroup.getContext();
            if (this.themeResId != 0) {
                context = new ContextThemeWrapper(viewGroup.getContext(), this.themeResId);
            }
            this.view = onCreateView(LayoutInflater.from(context), viewGroup);
            if (this.savedViewHierarchyState != null) {
                logger.debug("{}: Restore view hierarchy state", getClass().getSimpleName());
                this.view.restoreHierarchyState(this.savedViewHierarchyState);
                this.savedViewHierarchyState = null;
            }
            if (this.pendingViewStateToRestore != null) {
                logger.debug("{}: Restore view state", getClass().getSimpleName());
                onRestoreViewState(this.view, this.pendingViewStateToRestore);
                this.pendingViewStateToRestore = null;
            }
        }
        if (this.hasAttached) {
            return;
        }
        LOG.debug("{}: Attach", getClass().getSimpleName());
        this.hasAttached = true;
        if (i == -1) {
            viewGroup.addView(this.view);
        } else {
            viewGroup.addView(this.view, i);
        }
        onAttach(this.view);
    }

    final void create(@NonNull Router router) {
        if (this.hasCreated) {
            return;
        }
        this.router = router;
        LOG.debug("{}: Create", getClass().getSimpleName());
        this.hasCreated = true;
        onCreate();
    }

    final void destroy() {
        if (this.hasCreated) {
            destroyView();
            LOG.debug("{}: Destroy", getClass().getSimpleName());
            Iterator<Router> it = this.childRouters.values().iterator();
            while (it.hasNext()) {
                it.next().destroy();
            }
            onDestroy();
            this.hasCreated = false;
        }
    }

    final void destroyView() {
        if (this.view == null) {
            return;
        }
        detach();
        LOG.debug("{}: Destroy view", getClass().getSimpleName());
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.savedViewHierarchyState = sparseArray;
        this.view.saveHierarchyState(sparseArray);
        Iterator<Router> it = this.childRouters.values().iterator();
        while (it.hasNext()) {
            it.next().detach();
        }
        onDestroyView(this.view);
        this.view = null;
    }

    final void detach() {
        if (this.hasAttached) {
            this.hasAttached = false;
            ViewGroup viewGroup = (ViewGroup) this.view.getParent();
            if (viewGroup != null) {
                LOG.debug("{}: Detach", getClass().getSimpleName());
                onDetach(this.view);
                viewGroup.removeView(this.view);
            }
        }
    }

    @NonNull
    public Bundle saveState() {
        LOG.debug("{}: Save state", getClass().getSimpleName());
        Bundle bundle = new Bundle();
        bundle.putBundle("arguments", this.arguments);
        bundle.putBoolean("keepViewWhenInBackStack", this.keepViewWhenInBackStack);
        if (!this.childRouters.isEmpty()) {
            String[] strArr = new String[this.childRouters.size()];
            Bundle[] bundleArr = new Bundle[this.childRouters.size()];
            int size = this.childRouters.size();
            for (int i = 0; i < size; i++) {
                strArr[i] = this.childRouters.keyAt(i);
                bundleArr[i] = new Bundle(getClass().getClassLoader());
                this.childRouters.valueAt(i).saveInstanceState(bundleArr[i]);
            }
            bundle.putStringArray("childRouterKeys", strArr);
            bundle.putParcelableArray("childRouterValues", bundleArr);
        }
        SparseArray<Parcelable> sparseArray = this.savedViewHierarchyState;
        if (this.view != null) {
            Bundle bundle2 = new Bundle(getClass().getClassLoader());
            onSaveViewState(this.view, bundle2);
            bundle.putBundle("view", bundle2);
            sparseArray = new SparseArray<>();
            this.view.saveHierarchyState(sparseArray);
        }
        bundle.putSparseParcelableArray("viewHierarchy", sparseArray);
        return bundle;
    }

    public void restoreState(@NonNull Bundle bundle) {
        LOG.debug("{}: Restore state", getClass().getSimpleName());
        this.arguments = bundle.getBundle("arguments");
        this.keepViewWhenInBackStack = bundle.getBoolean("keepViewWhenInBackStack");
        Bundle bundle2 = bundle.getBundle("view");
        if (bundle2 != null) {
            bundle2.setClassLoader(getClass().getClassLoader());
        }
        String[] stringArray = bundle.getStringArray("childRouterKeys");
        Parcelable[] parcelableArray = bundle.getParcelableArray("childRouterValues");
        if (stringArray != null && parcelableArray != null) {
            int length = stringArray.length;
            for (int i = 0; i < length; i++) {
                String str = stringArray[i];
                Bundle bundle3 = (Bundle) parcelableArray[i];
                bundle3.setClassLoader(getClass().getClassLoader());
                this.childRouteStateToRestore.put(str, bundle3);
                Router router = this.childRouters.get(str);
                if (router != null) {
                    router.restoreInstanceState(bundle3);
                }
            }
        }
        SparseArray<Parcelable> sparseParcelableArray = bundle.getSparseParcelableArray("viewHierarchy");
        View view = this.view;
        if (view == null) {
            this.pendingViewStateToRestore = bundle2;
            this.savedViewHierarchyState = sparseParcelableArray;
        } else {
            view.restoreHierarchyState(sparseParcelableArray);
            if (bundle2 != null) {
                onRestoreViewState(this.view, bundle2);
            }
        }
    }

    public final Component getComponent() {
        Router router = this.router;
        if (router == null) {
            LOG.error("Cannot get component before a controller is created");
            return null;
        }
        return router.getComponent();
    }
}
