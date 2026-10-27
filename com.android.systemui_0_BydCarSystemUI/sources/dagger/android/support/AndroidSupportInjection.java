package dagger.android.support;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import dagger.android.AndroidInjector;
import dagger.android.HasAndroidInjector;
import dagger.internal.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidSupportInjection {
    private static final String TAG = "dagger.android.support";

    public static void inject(Fragment fragment) {
        Preconditions.checkNotNull(fragment, "fragment");
        HasAndroidInjector hasAndroidInjectorFindHasAndroidInjectorForFragment = findHasAndroidInjectorForFragment(fragment);
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, String.format("An injector for %s was found in %s", fragment.getClass().getCanonicalName(), hasAndroidInjectorFindHasAndroidInjectorForFragment.getClass().getCanonicalName()));
        }
        inject(fragment, hasAndroidInjectorFindHasAndroidInjectorForFragment);
    }

    private static void inject(Object obj, HasAndroidInjector hasAndroidInjector) {
        AndroidInjector<Object> androidInjector = hasAndroidInjector.androidInjector();
        Preconditions.checkNotNull(androidInjector, "%s.androidInjector() returned null", hasAndroidInjector.getClass());
        androidInjector.inject(obj);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static HasAndroidInjector findHasAndroidInjectorForFragment(Fragment fragment) {
        Fragment parentFragment = fragment;
        do {
            parentFragment = parentFragment.getParentFragment();
            if (parentFragment == 0) {
                FragmentActivity activity = fragment.getActivity();
                if (activity instanceof HasAndroidInjector) {
                    return (HasAndroidInjector) activity;
                }
                if (activity.getApplication() instanceof HasAndroidInjector) {
                    return (HasAndroidInjector) activity.getApplication();
                }
                throw new IllegalArgumentException(String.format("No injector was found for %s", fragment.getClass().getCanonicalName()));
            }
        } while (!(parentFragment instanceof HasAndroidInjector));
        return (HasAndroidInjector) parentFragment;
    }

    private AndroidSupportInjection() {
    }
}
