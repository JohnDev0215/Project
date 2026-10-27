package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.navigation.FloatingWindow;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import java.util.HashSet;

/* JADX INFO: loaded from: classes2.dex */
@Navigator.Name("dialog")
public final class DialogFragmentNavigator extends Navigator<Destination> {
    private static final String DIALOG_TAG = "androidx-nav-fragment:navigator:dialog:";
    private static final String KEY_DIALOG_COUNT = "androidx-nav-dialogfragment:navigator:count";
    private static final String TAG = "DialogFragmentNavigator";
    private final Context mContext;
    private final FragmentManager mFragmentManager;
    private int mDialogCount = 0;
    private final HashSet<String> mRestoredTagsAwaitingAttach = new HashSet<>();
    private LifecycleEventObserver mObserver = new LifecycleEventObserver() { // from class: androidx.navigation.fragment.DialogFragmentNavigator.1
        @Override // androidx.lifecycle.LifecycleEventObserver
        public void onStateChanged(@NonNull LifecycleOwner lifecycleOwner, @NonNull Lifecycle.Event event) {
            if (event == Lifecycle.Event.ON_STOP) {
                DialogFragment dialogFragment = (DialogFragment) lifecycleOwner;
                if (dialogFragment.requireDialog().isShowing()) {
                    return;
                }
                NavHostFragment.findNavController(dialogFragment).popBackStack();
            }
        }
    };

    public DialogFragmentNavigator(@NonNull Context context, @NonNull FragmentManager fragmentManager) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
    }

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        if (this.mDialogCount == 0) {
            return false;
        }
        if (this.mFragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        FragmentManager fragmentManager = this.mFragmentManager;
        StringBuilder sb = new StringBuilder();
        sb.append(DIALOG_TAG);
        int i = this.mDialogCount - 1;
        this.mDialogCount = i;
        sb.append(i);
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(sb.toString());
        if (fragmentFindFragmentByTag != null) {
            fragmentFindFragmentByTag.getLifecycle().removeObserver(this.mObserver);
            ((DialogFragment) fragmentFindFragmentByTag).dismiss();
        }
        return true;
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public Destination createDestination() {
        return new Destination(this);
    }

    @Override // androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        if (this.mFragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String className = destination.getClassName();
        if (className.charAt(0) == '.') {
            className = this.mContext.getPackageName() + className;
        }
        Fragment fragmentInstantiate = this.mFragmentManager.getFragmentFactory().instantiate(this.mContext.getClassLoader(), className);
        if (!DialogFragment.class.isAssignableFrom(fragmentInstantiate.getClass())) {
            throw new IllegalArgumentException("Dialog destination " + destination.getClassName() + " is not an instance of DialogFragment");
        }
        DialogFragment dialogFragment = (DialogFragment) fragmentInstantiate;
        dialogFragment.setArguments(bundle);
        dialogFragment.getLifecycle().addObserver(this.mObserver);
        FragmentManager fragmentManager = this.mFragmentManager;
        StringBuilder sb = new StringBuilder();
        sb.append(DIALOG_TAG);
        int i = this.mDialogCount;
        this.mDialogCount = i + 1;
        sb.append(i);
        dialogFragment.show(fragmentManager, sb.toString());
        return destination;
    }

    @Override // androidx.navigation.Navigator
    @Nullable
    public Bundle onSaveState() {
        if (this.mDialogCount == 0) {
            return null;
        }
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_DIALOG_COUNT, this.mDialogCount);
        return bundle;
    }

    @Override // androidx.navigation.Navigator
    public void onRestoreState(@Nullable Bundle bundle) {
        if (bundle != null) {
            this.mDialogCount = bundle.getInt(KEY_DIALOG_COUNT, 0);
            for (int i = 0; i < this.mDialogCount; i++) {
                DialogFragment dialogFragment = (DialogFragment) this.mFragmentManager.findFragmentByTag(DIALOG_TAG + i);
                if (dialogFragment != null) {
                    dialogFragment.getLifecycle().addObserver(this.mObserver);
                } else {
                    this.mRestoredTagsAwaitingAttach.add(DIALOG_TAG + i);
                }
            }
        }
    }

    void onAttachFragment(@NonNull Fragment fragment) {
        if (this.mRestoredTagsAwaitingAttach.remove(fragment.getTag())) {
            fragment.getLifecycle().addObserver(this.mObserver);
        }
    }

    @NavDestination.ClassType(DialogFragment.class)
    public static class Destination extends NavDestination implements FloatingWindow {
        private String mClassName;

        public Destination(@NonNull NavigatorProvider navigatorProvider) {
            this((Navigator<? extends Destination>) navigatorProvider.getNavigator(DialogFragmentNavigator.class));
        }

        public Destination(@NonNull Navigator<? extends Destination> navigator) {
            super(navigator);
        }

        @Override // androidx.navigation.NavDestination
        @CallSuper
        public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
            super.onInflate(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.DialogFragmentNavigator);
            String string = typedArrayObtainAttributes.getString(R.styleable.DialogFragmentNavigator_android_name);
            if (string != null) {
                setClassName(string);
            }
            typedArrayObtainAttributes.recycle();
        }

        @NonNull
        public final Destination setClassName(@NonNull String str) {
            this.mClassName = str;
            return this;
        }

        @NonNull
        public final String getClassName() {
            String str = this.mClassName;
            if (str != null) {
                return str;
            }
            throw new IllegalStateException("DialogFragment class was not set");
        }
    }
}
