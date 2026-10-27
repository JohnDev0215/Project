package me.tatarka.bindingcollectionadapter2;

import android.os.Looper;
import android.view.View;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.LifecycleOwner;

/* JADX INFO: loaded from: classes5.dex */
class Utils {
    private static final String TAG = "BCAdapters";

    Utils() {
    }

    static void throwMissingVariable(ViewDataBinding viewDataBinding, int i, int i2) {
        String resourceName = viewDataBinding.getRoot().getContext().getResources().getResourceName(i2);
        throw new IllegalStateException("Could not bind variable '" + DataBindingUtil.convertBrIdToString(i) + "' in layout '" + resourceName + "'");
    }

    static LifecycleOwner findLifecycleOwner(View view) {
        ViewDataBinding viewDataBindingFindBinding = DataBindingUtil.findBinding(view);
        LifecycleOwner lifecycleOwner = viewDataBindingFindBinding != null ? viewDataBindingFindBinding.getLifecycleOwner() : null;
        Object context = view.getContext();
        return (lifecycleOwner == null && (context instanceof LifecycleOwner)) ? (LifecycleOwner) context : lifecycleOwner;
    }

    static void ensureChangeOnMainThread() {
        if (Thread.currentThread() != Looper.getMainLooper().getThread()) {
            throw new IllegalStateException("You must only modify the ObservableList on the main thread.");
        }
    }
}
