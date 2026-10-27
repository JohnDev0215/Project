package com.byd.automultipletheme.activity.navi;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavDestination;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigator;
import androidx.navigation.fragment.FragmentNavigator;
import java.lang.reflect.Field;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@Navigator.Name("fixFragment")
public class FixFragmentNavigator extends FragmentNavigator {
    private final String TAG;
    private final int mContainerId;
    private final Context mContext;
    private final FragmentManager mFragmentManager;

    public FixFragmentNavigator(Context context, FragmentManager fragmentManager, int i) {
        super(context, fragmentManager, i);
        this.TAG = "ReLoadFragmentNavictor";
        this.mContext = context;
        this.mFragmentManager = fragmentManager;
        this.mContainerId = i;
    }

    @Override // androidx.navigation.fragment.FragmentNavigator, androidx.navigation.Navigator
    public NavDestination navigate(FragmentNavigator.Destination destination, Bundle bundle, NavOptions navOptions, Navigator.Extras extras) {
        if (this.mFragmentManager.isStateSaved()) {
            Log.i("ReLoadFragmentNavictor", "Ignoring navigate() call: FragmentManager has already saved its state");
            return null;
        }
        String className = destination.getClassName();
        boolean z = false;
        if (className.charAt(0) == '.') {
            className = this.mContext.getPackageName() + className;
        }
        Fragment fragmentFindFragmentByTag = this.mFragmentManager.findFragmentByTag(className);
        if (fragmentFindFragmentByTag == null) {
            fragmentFindFragmentByTag = instantiateFragment(this.mContext, this.mFragmentManager, className, bundle);
        }
        fragmentFindFragmentByTag.setArguments(bundle);
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
        Iterator<Fragment> it = this.mFragmentManager.getFragments().iterator();
        while (it.hasNext()) {
            fragmentTransactionBeginTransaction.hide(it.next());
        }
        if (!fragmentFindFragmentByTag.isAdded()) {
            fragmentTransactionBeginTransaction.add(this.mContainerId, fragmentFindFragmentByTag, className);
        }
        fragmentTransactionBeginTransaction.show(fragmentFindFragmentByTag);
        fragmentTransactionBeginTransaction.setPrimaryNavigationFragment(fragmentFindFragmentByTag);
        int id = destination.getId();
        try {
            Field declaredField = FragmentNavigator.class.getDeclaredField("mBackStack");
            declaredField.setAccessible(true);
            ArrayDeque arrayDeque = (ArrayDeque) declaredField.get(this);
            boolean zIsEmpty = arrayDeque.isEmpty();
            boolean z2 = navOptions != null && !zIsEmpty && navOptions.shouldLaunchSingleTop() && ((Integer) arrayDeque.peekLast()).intValue() == id;
            if (zIsEmpty) {
                z = true;
            } else if (z2) {
                if (arrayDeque.size() > 1) {
                    this.mFragmentManager.popBackStack(generateBackStackName(arrayDeque.size(), ((Integer) arrayDeque.peekLast()).intValue()), 1);
                    fragmentTransactionBeginTransaction.addToBackStack(generateBackStackName(arrayDeque.size(), id));
                }
            } else {
                fragmentTransactionBeginTransaction.addToBackStack(generateBackStackName(arrayDeque.size() + 1, id));
                z = true;
            }
            if (extras instanceof FragmentNavigator.Extras) {
                for (Map.Entry<View, String> entry : ((FragmentNavigator.Extras) extras).getSharedElements().entrySet()) {
                    fragmentTransactionBeginTransaction.addSharedElement(entry.getKey(), entry.getValue());
                }
            }
            fragmentTransactionBeginTransaction.setReorderingAllowed(true);
            fragmentTransactionBeginTransaction.commit();
            if (!z) {
                return null;
            }
            arrayDeque.add(Integer.valueOf(id));
            return destination;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String generateBackStackName(int i, int i2) {
        return i + "-" + i2;
    }
}
