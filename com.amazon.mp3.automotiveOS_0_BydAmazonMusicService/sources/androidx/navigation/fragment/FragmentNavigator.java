package androidx.navigation.fragment;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.NavigatorProvider;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
@Navigator.Name("fragment")
public class FragmentNavigator extends Navigator<Destination> {
    private static final String KEY_BACK_STACK_IDS = "androidx-nav-fragment:navigator:backStackIds";
    private static final String TAG = "FragmentNavigator";
    private ArrayDeque<Integer> mBackStack = new ArrayDeque<>();
    private final int mContainerId;
    private final Context mContext;
    private final FragmentManager mFragmentManager;

    public FragmentNavigator(@NonNull Context context, @NonNull FragmentManager fragmentManager, int i) {
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mContainerId = i;
    }

    @Override // androidx.navigation.Navigator
    public boolean popBackStack() {
        if (this.mBackStack.isEmpty()) {
            return false;
        }
        if (this.mFragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring popBackStack() call: FragmentManager has already saved its state");
            return false;
        }
        this.mFragmentManager.popBackStack(generateBackStackName(this.mBackStack.size(), this.mBackStack.peekLast().intValue()), 1);
        this.mBackStack.removeLast();
        return true;
    }

    @Override // androidx.navigation.Navigator
    @NonNull
    public Destination createDestination() {
        return new Destination(this);
    }

    @NonNull
    @Deprecated
    public Fragment instantiateFragment(@NonNull Context context, @NonNull FragmentManager fragmentManager, @NonNull String str, @Nullable Bundle bundle) {
        return fragmentManager.getFragmentFactory().instantiate(context.getClassLoader(), str);
    }

    @Override // androidx.navigation.Navigator
    @Nullable
    public NavDestination navigate(@NonNull Destination destination, @Nullable Bundle bundle, @Nullable NavOptions navOptions, @Nullable Navigator.Extras extras) {
        if (this.mFragmentManager.isStateSaved()) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String className = destination.getClassName();
        boolean z = false;
        if (className.charAt(0) == '.') {
            className = this.mContext.getPackageName() + className;
        }
        Fragment fragmentInstantiateFragment = instantiateFragment(this.mContext, this.mFragmentManager, className, bundle);
        fragmentInstantiateFragment.setArguments(bundle);
        FragmentTransaction fragmentTransactionBeginTransaction = this.mFragmentManager.beginTransaction();
        int enterAnim = navOptions != null ? navOptions.getEnterAnim() : -1;
        int exitAnim = navOptions != null ? navOptions.getExitAnim() : -1;
        int popEnterAnim = navOptions != null ? navOptions.getPopEnterAnim() : -1;
        int popExitAnim = navOptions != null ? navOptions.getPopExitAnim() : -1;
        if (enterAnim != -1 || exitAnim != -1 || popEnterAnim != -1 || popExitAnim != -1) {
            if (enterAnim == -1) {
                enterAnim = 0;
            }
            if (exitAnim == -1) {
                exitAnim = 0;
            }
            if (popEnterAnim == -1) {
                popEnterAnim = 0;
            }
            if (popExitAnim == -1) {
                popExitAnim = 0;
            }
            fragmentTransactionBeginTransaction.setCustomAnimations(enterAnim, exitAnim, popEnterAnim, popExitAnim);
        }
        fragmentTransactionBeginTransaction.replace(this.mContainerId, fragmentInstantiateFragment);
        fragmentTransactionBeginTransaction.setPrimaryNavigationFragment(fragmentInstantiateFragment);
        int id = destination.getId();
        boolean zIsEmpty = this.mBackStack.isEmpty();
        boolean z2 = navOptions != null && !zIsEmpty && navOptions.shouldLaunchSingleTop() && this.mBackStack.peekLast().intValue() == id;
        if (zIsEmpty) {
            z = true;
        } else if (z2) {
            if (this.mBackStack.size() > 1) {
                this.mFragmentManager.popBackStack(generateBackStackName(this.mBackStack.size(), this.mBackStack.peekLast().intValue()), 1);
                fragmentTransactionBeginTransaction.addToBackStack(generateBackStackName(this.mBackStack.size(), id));
            }
        } else {
            fragmentTransactionBeginTransaction.addToBackStack(generateBackStackName(this.mBackStack.size() + 1, id));
            z = true;
        }
        if (extras instanceof Extras) {
            for (Map.Entry<View, String> entry : ((Extras) extras).getSharedElements().entrySet()) {
                fragmentTransactionBeginTransaction.addSharedElement(entry.getKey(), entry.getValue());
            }
        }
        fragmentTransactionBeginTransaction.setReorderingAllowed(true);
        fragmentTransactionBeginTransaction.commit();
        if (!z) {
            return null;
        }
        this.mBackStack.add(Integer.valueOf(id));
        return destination;
    }

    @Override // androidx.navigation.Navigator
    @Nullable
    public Bundle onSaveState() {
        Bundle bundle = new Bundle();
        int[] iArr = new int[this.mBackStack.size()];
        Iterator<Integer> it = this.mBackStack.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = it.next().intValue();
            i++;
        }
        bundle.putIntArray(KEY_BACK_STACK_IDS, iArr);
        return bundle;
    }

    @Override // androidx.navigation.Navigator
    public void onRestoreState(@Nullable Bundle bundle) {
        int[] intArray;
        if (bundle == null || (intArray = bundle.getIntArray(KEY_BACK_STACK_IDS)) == null) {
            return;
        }
        this.mBackStack.clear();
        for (int i : intArray) {
            this.mBackStack.add(Integer.valueOf(i));
        }
    }

    @NonNull
    private String generateBackStackName(int i, int i2) {
        return i + "-" + i2;
    }

    @NavDestination.ClassType(Fragment.class)
    public static class Destination extends NavDestination {
        private String mClassName;

        public Destination(@NonNull NavigatorProvider navigatorProvider) {
            this((Navigator<? extends Destination>) navigatorProvider.getNavigator(FragmentNavigator.class));
        }

        public Destination(@NonNull Navigator<? extends Destination> navigator) {
            super(navigator);
        }

        @Override // androidx.navigation.NavDestination
        @CallSuper
        public void onInflate(@NonNull Context context, @NonNull AttributeSet attributeSet) {
            super.onInflate(context, attributeSet);
            TypedArray typedArrayObtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.FragmentNavigator);
            String string = typedArrayObtainAttributes.getString(R.styleable.FragmentNavigator_android_name);
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
            throw new IllegalStateException("Fragment class was not set");
        }

        @Override // androidx.navigation.NavDestination
        @NonNull
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(super.toString());
            sb.append(" class=");
            String str = this.mClassName;
            if (str == null) {
                sb.append("null");
            } else {
                sb.append(str);
            }
            return sb.toString();
        }
    }

    public static final class Extras implements Navigator.Extras {
        private final LinkedHashMap<View, String> mSharedElements;

        Extras(Map<View, String> map) {
            LinkedHashMap<View, String> linkedHashMap = new LinkedHashMap<>();
            this.mSharedElements = linkedHashMap;
            linkedHashMap.putAll(map);
        }

        @NonNull
        public Map<View, String> getSharedElements() {
            return Collections.unmodifiableMap(this.mSharedElements);
        }

        public static final class Builder {
            private final LinkedHashMap<View, String> mSharedElements = new LinkedHashMap<>();

            @NonNull
            public Builder addSharedElements(@NonNull Map<View, String> map) {
                for (Map.Entry<View, String> entry : map.entrySet()) {
                    View key = entry.getKey();
                    String value = entry.getValue();
                    if (key != null && value != null) {
                        addSharedElement(key, value);
                    }
                }
                return this;
            }

            @NonNull
            public Builder addSharedElement(@NonNull View view, @NonNull String str) {
                this.mSharedElements.put(view, str);
                return this;
            }

            @NonNull
            public Extras build() {
                return new Extras(this.mSharedElements);
            }
        }
    }
}
