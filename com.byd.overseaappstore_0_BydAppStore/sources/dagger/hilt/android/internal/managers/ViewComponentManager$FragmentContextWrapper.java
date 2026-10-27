package dagger.hilt.android.internal.managers;

import android.content.Context;
import android.content.ContextWrapper;
import android.view.LayoutInflater;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.Objects;

/* JADX INFO: loaded from: classes2.dex */
public final class ViewComponentManager$FragmentContextWrapper extends ContextWrapper {
    public LayoutInflater a;
    public LayoutInflater b;
    public final LifecycleEventObserver c;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ViewComponentManager$FragmentContextWrapper(Context context, Fragment fragment) {
        super(context);
        Objects.requireNonNull(context);
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    ViewComponentManager$FragmentContextWrapper viewComponentManager$FragmentContextWrapper = ViewComponentManager$FragmentContextWrapper.this;
                    Objects.requireNonNull(viewComponentManager$FragmentContextWrapper);
                    viewComponentManager$FragmentContextWrapper.a = null;
                    viewComponentManager$FragmentContextWrapper.b = null;
                }
            }
        };
        this.c = lifecycleEventObserver;
        this.a = null;
        Objects.requireNonNull(fragment);
        fragment.getLifecycle().addObserver(lifecycleEventObserver);
    }

    @Override // android.content.ContextWrapper, android.content.Context
    public final Object getSystemService(String str) {
        if (!"layout_inflater".equals(str)) {
            return getBaseContext().getSystemService(str);
        }
        if (this.b == null) {
            if (this.a == null) {
                this.a = (LayoutInflater) getBaseContext().getSystemService("layout_inflater");
            }
            this.b = this.a.cloneInContext(this);
        }
        return this.b;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ViewComponentManager$FragmentContextWrapper(LayoutInflater layoutInflater, Fragment fragment) {
        Objects.requireNonNull(layoutInflater);
        Context context = layoutInflater.getContext();
        Objects.requireNonNull(context);
        super(context);
        LifecycleEventObserver lifecycleEventObserver = new LifecycleEventObserver() { // from class: dagger.hilt.android.internal.managers.ViewComponentManager$FragmentContextWrapper.1
            @Override // androidx.lifecycle.LifecycleEventObserver
            public final void onStateChanged(LifecycleOwner lifecycleOwner, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_DESTROY) {
                    ViewComponentManager$FragmentContextWrapper viewComponentManager$FragmentContextWrapper = ViewComponentManager$FragmentContextWrapper.this;
                    Objects.requireNonNull(viewComponentManager$FragmentContextWrapper);
                    viewComponentManager$FragmentContextWrapper.a = null;
                    viewComponentManager$FragmentContextWrapper.b = null;
                }
            }
        };
        this.c = lifecycleEventObserver;
        this.a = layoutInflater;
        Objects.requireNonNull(fragment);
        fragment.getLifecycle().addObserver(lifecycleEventObserver);
    }
}
