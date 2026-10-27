package com.amazon.mp3.activity.navigation;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.PlayQueueFragment;
import com.amazon.mp3.util.Log;

/* JADX INFO: loaded from: classes3.dex */
public class FragmentController {
    private static final String TAG = "FragmentController";
    private FragmentChangedCallback mChangedCallback;
    private final FragmentActivity mFragmentActivity;
    private final FragmentLoader[] mFragmentLoader;
    private final int mPageContainerId;

    public interface FragmentChangedCallback {
        void onChange();
    }

    public FragmentController(final FragmentActivity fragmentActivity, final int pageContainerId) {
        this.mPageContainerId = pageContainerId;
        this.mFragmentActivity = fragmentActivity;
        this.mFragmentLoader = new FragmentLoader[]{new LibraryFragmentLoader(fragmentActivity, this), new StoreFragmentLoader(fragmentActivity, this), new MoreMenuFragmentLoader(fragmentActivity, this), new PrimeFragmentLoader(fragmentActivity, this), new NowPlayingFragmentLoader(fragmentActivity, this), new ExploreFragmentLoader(fragmentActivity, this), new ALPSFragmentLoader(fragmentActivity, this)};
    }

    public void showDefaultLibraryScreen() {
        ((LibraryFragmentLoader) this.mFragmentLoader[0]).showMusicOrDefaultScreen();
    }

    public final boolean navigateFromMenu(final int menuId) {
        for (FragmentLoader fragmentLoader : this.mFragmentLoader) {
            if (fragmentLoader.navigateFromMenu(menuId)) {
                return true;
            }
        }
        return false;
    }

    public final boolean navigateFromIntent(final Intent intent) {
        for (FragmentLoader fragmentLoader : this.mFragmentLoader) {
            if (fragmentLoader.navigateFromIntent(intent)) {
                return true;
            }
        }
        return false;
    }

    public void setFragmentChangedCallback(FragmentChangedCallback callback) {
        this.mChangedCallback = callback;
    }

    public FragmentActivity getFragmentActivity() {
        return this.mFragmentActivity;
    }

    public Fragment getCurrentFragment() {
        return this.mFragmentActivity.getSupportFragmentManager().findFragmentById(this.mPageContainerId);
    }

    public void showDialogFragment(final DialogFragment dialogFragment) {
        showDialogFragment(dialogFragment, null);
    }

    public void showDialogFragment(final DialogFragment dialogFragment, final String tag) {
        dialogFragment.show(this.mFragmentActivity.getSupportFragmentManager(), tag);
    }

    public String getCurrentFragmentName() {
        Fragment currentFragment = getCurrentFragment();
        return currentFragment == null ? "" : currentFragment.getClass().getSimpleName();
    }

    public void changeScreenFragment(Fragment fragment, boolean addToBackStack, boolean forceReplace) {
        changeScreenFragment(fragment, addToBackStack, forceReplace, false);
    }

    public void changeScreenFragment(Fragment fragment, boolean addToBackStack, boolean forceReplace, boolean animate) {
        changeScreenFragment(fragment, addToBackStack, forceReplace, animate, R.anim.zoom_in, R.anim.fragment_fade_out);
    }

    public void changeScreenFragment(Fragment fragment, boolean addToBackStack, boolean forceReplace, boolean animate, int... customAnimationIds) {
        String simpleName = fragment.getClass().getSimpleName();
        if ((fragment instanceof PlayQueueFragment) && getCurrentFragmentName().equals(simpleName)) {
            return;
        }
        if (!forceReplace && getCurrentFragmentName().equals(simpleName) && !this.mFragmentActivity.getIntent().getBooleanExtra("com.amazon.mp3.launch.EXTERNAL", false)) {
            Bundle arguments = getCurrentFragment().getArguments();
            if (arguments != null) {
                arguments.putAll(fragment.getArguments());
                return;
            }
            return;
        }
        FragmentManager supportFragmentManager = this.mFragmentActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransactionBeginTransaction = supportFragmentManager.beginTransaction();
        if (animate) {
            if (customAnimationIds.length == 4) {
                fragmentTransactionBeginTransaction.setCustomAnimations(customAnimationIds[0], customAnimationIds[1], customAnimationIds[2], customAnimationIds[3]);
            } else if (customAnimationIds.length == 2) {
                fragmentTransactionBeginTransaction.setCustomAnimations(customAnimationIds[0], customAnimationIds[1]);
            } else {
                Log.error(TAG, "Either 2 or 4 custom animation Ids must be provided");
            }
        }
        if (supportFragmentManager.getBackStackEntryCount() > 0) {
            addToBackStack = true;
        }
        fragmentTransactionBeginTransaction.replace(this.mPageContainerId, fragment);
        if (addToBackStack && !TextUtils.isEmpty(getCurrentFragmentName())) {
            fragmentTransactionBeginTransaction.addToBackStack(simpleName);
        }
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        FragmentChangedCallback fragmentChangedCallback = this.mChangedCallback;
        if (fragmentChangedCallback != null) {
            fragmentChangedCallback.onChange();
        }
    }

    public void popBackStack() {
        try {
            this.mFragmentActivity.getSupportFragmentManager().popBackStack();
        } catch (IllegalStateException e) {
            Log.error(TAG, "FragmentManager popBackStackImmediate() failed, No-op", e);
        }
    }

    public void clearBackStack(final Fragment fragment) {
        try {
            this.mFragmentActivity.getSupportFragmentManager().popBackStackImmediate(fragment.getClass().getSimpleName(), 0);
        } catch (IllegalStateException e) {
            Log.error(TAG, "FragmentManager popBackStackImmediate() failed, No-op", e);
        }
    }

    public void clearBackStack() {
        try {
            this.mFragmentActivity.getSupportFragmentManager().popBackStackImmediate((String) null, 1);
        } catch (IllegalStateException e) {
            Log.error(TAG, "FragmentManager popBackStackImmediate() failed, No-op", e);
        }
    }

    public void removeFragment(final Fragment fragment) {
        this.mFragmentActivity.getSupportFragmentManager().beginTransaction().remove(fragment).commitAllowingStateLoss();
    }
}
