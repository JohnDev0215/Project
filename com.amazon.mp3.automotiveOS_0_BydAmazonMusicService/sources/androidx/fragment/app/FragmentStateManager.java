package androidx.fragment.app;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.os.EnvironmentCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.R;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelStoreOwner;

/* JADX INFO: loaded from: classes.dex */
class FragmentStateManager {
    private static final String TAG = "FragmentManager";
    private static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
    private static final String TARGET_STATE_TAG = "android:target_state";
    private static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
    private static final String VIEW_STATE_TAG = "android:view_state";
    private final FragmentLifecycleCallbacksDispatcher mDispatcher;

    @NonNull
    private final Fragment mFragment;
    private int mFragmentManagerState = -1;

    FragmentStateManager(@NonNull FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, @NonNull Fragment fragment) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragment = fragment;
    }

    FragmentStateManager(@NonNull FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, @NonNull ClassLoader classLoader, @NonNull FragmentFactory fragmentFactory, @NonNull FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        Fragment fragmentInstantiate = fragmentFactory.instantiate(classLoader, fragmentState.mClassName);
        this.mFragment = fragmentInstantiate;
        Bundle bundle = fragmentState.mArguments;
        if (bundle != null) {
            bundle.setClassLoader(classLoader);
        }
        fragmentInstantiate.setArguments(fragmentState.mArguments);
        fragmentInstantiate.mWho = fragmentState.mWho;
        fragmentInstantiate.mFromLayout = fragmentState.mFromLayout;
        fragmentInstantiate.mRestored = true;
        fragmentInstantiate.mFragmentId = fragmentState.mFragmentId;
        fragmentInstantiate.mContainerId = fragmentState.mContainerId;
        fragmentInstantiate.mTag = fragmentState.mTag;
        fragmentInstantiate.mRetainInstance = fragmentState.mRetainInstance;
        fragmentInstantiate.mRemoving = fragmentState.mRemoving;
        fragmentInstantiate.mDetached = fragmentState.mDetached;
        fragmentInstantiate.mHidden = fragmentState.mHidden;
        fragmentInstantiate.mMaxState = Lifecycle.State.values()[fragmentState.mMaxLifecycleState];
        Bundle bundle2 = fragmentState.mSavedFragmentState;
        if (bundle2 != null) {
            fragmentInstantiate.mSavedFragmentState = bundle2;
        } else {
            fragmentInstantiate.mSavedFragmentState = new Bundle();
        }
        if (FragmentManager.isLoggingEnabled(2)) {
            Log.v(TAG, "Instantiated fragment " + fragmentInstantiate);
        }
    }

    FragmentStateManager(@NonNull FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher, @NonNull Fragment fragment, @NonNull FragmentState fragmentState) {
        this.mDispatcher = fragmentLifecycleCallbacksDispatcher;
        this.mFragment = fragment;
        fragment.mSavedViewState = null;
        fragment.mBackStackNesting = 0;
        fragment.mInLayout = false;
        fragment.mAdded = false;
        Fragment fragment2 = fragment.mTarget;
        fragment.mTargetWho = fragment2 != null ? fragment2.mWho : null;
        fragment.mTarget = null;
        Bundle bundle = fragmentState.mSavedFragmentState;
        if (bundle != null) {
            fragment.mSavedFragmentState = bundle;
        } else {
            fragment.mSavedFragmentState = new Bundle();
        }
    }

    @NonNull
    Fragment getFragment() {
        return this.mFragment;
    }

    void setFragmentManagerState(int i) {
        this.mFragmentManagerState = i;
    }

    int computeMaxState() {
        int iMin = this.mFragmentManagerState;
        Fragment fragment = this.mFragment;
        if (fragment.mFromLayout) {
            if (fragment.mInLayout) {
                iMin = Math.max(iMin, 1);
            } else if (iMin < 2) {
                iMin = Math.min(iMin, fragment.mState);
            } else {
                iMin = Math.min(iMin, 1);
            }
        }
        if (!this.mFragment.mAdded) {
            iMin = Math.min(iMin, 1);
        }
        Fragment fragment2 = this.mFragment;
        if (fragment2.mRemoving) {
            if (fragment2.isInBackStack()) {
                iMin = Math.min(iMin, 1);
            } else {
                iMin = Math.min(iMin, -1);
            }
        }
        Fragment fragment3 = this.mFragment;
        if (fragment3.mDeferStart && fragment3.mState < 3) {
            iMin = Math.min(iMin, 2);
        }
        int i = AnonymousClass1.$SwitchMap$androidx$lifecycle$Lifecycle$State[this.mFragment.mMaxState.ordinal()];
        if (i == 1) {
            return iMin;
        }
        if (i == 2) {
            return Math.min(iMin, 3);
        }
        if (i == 3) {
            return Math.min(iMin, 1);
        }
        return Math.min(iMin, -1);
    }

    /* JADX INFO: renamed from: androidx.fragment.app.FragmentStateManager$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$lifecycle$Lifecycle$State;

        static {
            int[] iArr = new int[Lifecycle.State.values().length];
            $SwitchMap$androidx$lifecycle$Lifecycle$State = iArr;
            try {
                iArr[Lifecycle.State.RESUMED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.STARTED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$androidx$lifecycle$Lifecycle$State[Lifecycle.State.CREATED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    void ensureInflatedView() {
        Fragment fragment = this.mFragment;
        if (fragment.mFromLayout && fragment.mInLayout && !fragment.mPerformedCreateView) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d(TAG, "moveto CREATE_VIEW: " + this.mFragment);
            }
            Fragment fragment2 = this.mFragment;
            fragment2.performCreateView(fragment2.performGetLayoutInflater(fragment2.mSavedFragmentState), null, this.mFragment.mSavedFragmentState);
            View view = this.mFragment.mView;
            if (view != null) {
                view.setSaveFromParentEnabled(false);
                Fragment fragment3 = this.mFragment;
                fragment3.mView.setTag(R.id.fragment_container_view_tag, fragment3);
                Fragment fragment4 = this.mFragment;
                if (fragment4.mHidden) {
                    fragment4.mView.setVisibility(8);
                }
                Fragment fragment5 = this.mFragment;
                fragment5.onViewCreated(fragment5.mView, fragment5.mSavedFragmentState);
                FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
                Fragment fragment6 = this.mFragment;
                fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment6, fragment6.mView, fragment6.mSavedFragmentState, false);
            }
        }
    }

    void restoreState(@NonNull ClassLoader classLoader) {
        Bundle bundle = this.mFragment.mSavedFragmentState;
        if (bundle == null) {
            return;
        }
        bundle.setClassLoader(classLoader);
        Fragment fragment = this.mFragment;
        fragment.mSavedViewState = fragment.mSavedFragmentState.getSparseParcelableArray(VIEW_STATE_TAG);
        Fragment fragment2 = this.mFragment;
        fragment2.mTargetWho = fragment2.mSavedFragmentState.getString(TARGET_STATE_TAG);
        Fragment fragment3 = this.mFragment;
        if (fragment3.mTargetWho != null) {
            fragment3.mTargetRequestCode = fragment3.mSavedFragmentState.getInt(TARGET_REQUEST_CODE_STATE_TAG, 0);
        }
        Fragment fragment4 = this.mFragment;
        Boolean bool = fragment4.mSavedUserVisibleHint;
        if (bool != null) {
            fragment4.mUserVisibleHint = bool.booleanValue();
            this.mFragment.mSavedUserVisibleHint = null;
        } else {
            fragment4.mUserVisibleHint = fragment4.mSavedFragmentState.getBoolean(USER_VISIBLE_HINT_TAG, true);
        }
        Fragment fragment5 = this.mFragment;
        if (fragment5.mUserVisibleHint) {
            return;
        }
        fragment5.mDeferStart = true;
    }

    void attach(@NonNull FragmentHostCallback<?> fragmentHostCallback, @NonNull FragmentManager fragmentManager, @Nullable Fragment fragment) {
        Fragment fragment2 = this.mFragment;
        fragment2.mHost = fragmentHostCallback;
        fragment2.mParentFragment = fragment;
        fragment2.mFragmentManager = fragmentManager;
        this.mDispatcher.dispatchOnFragmentPreAttached(fragment2, fragmentHostCallback.getContext(), false);
        this.mFragment.performAttach();
        Fragment fragment3 = this.mFragment;
        Fragment fragment4 = fragment3.mParentFragment;
        if (fragment4 == null) {
            fragmentHostCallback.onAttachFragment(fragment3);
        } else {
            fragment4.onAttachFragment(fragment3);
        }
        this.mDispatcher.dispatchOnFragmentAttached(this.mFragment, fragmentHostCallback.getContext(), false);
    }

    void create() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto CREATED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        if (!fragment.mIsCreated) {
            this.mDispatcher.dispatchOnFragmentPreCreated(fragment, fragment.mSavedFragmentState, false);
            Fragment fragment2 = this.mFragment;
            fragment2.performCreate(fragment2.mSavedFragmentState);
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
            Fragment fragment3 = this.mFragment;
            fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentCreated(fragment3, fragment3.mSavedFragmentState, false);
            return;
        }
        fragment.restoreChildFragmentState(fragment.mSavedFragmentState);
        this.mFragment.mState = 1;
    }

    void createView(@NonNull FragmentContainer fragmentContainer) {
        String resourceName;
        if (this.mFragment.mFromLayout) {
            return;
        }
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto CREATE_VIEW: " + this.mFragment);
        }
        ViewGroup viewGroup = null;
        Fragment fragment = this.mFragment;
        ViewGroup viewGroup2 = fragment.mContainer;
        if (viewGroup2 != null) {
            viewGroup = viewGroup2;
        } else {
            int i = fragment.mContainerId;
            if (i != 0) {
                if (i == -1) {
                    throw new IllegalArgumentException("Cannot create fragment " + this.mFragment + " for a container view with no id");
                }
                viewGroup = (ViewGroup) fragmentContainer.onFindViewById(i);
                if (viewGroup == null) {
                    Fragment fragment2 = this.mFragment;
                    if (!fragment2.mRestored) {
                        try {
                            resourceName = fragment2.getResources().getResourceName(this.mFragment.mContainerId);
                        } catch (Resources.NotFoundException unused) {
                            resourceName = EnvironmentCompat.MEDIA_UNKNOWN;
                        }
                        throw new IllegalArgumentException("No view found for id 0x" + Integer.toHexString(this.mFragment.mContainerId) + " (" + resourceName + ") for fragment " + this.mFragment);
                    }
                }
            }
        }
        Fragment fragment3 = this.mFragment;
        fragment3.mContainer = viewGroup;
        fragment3.performCreateView(fragment3.performGetLayoutInflater(fragment3.mSavedFragmentState), viewGroup, this.mFragment.mSavedFragmentState);
        View view = this.mFragment.mView;
        if (view != null) {
            boolean z = false;
            view.setSaveFromParentEnabled(false);
            Fragment fragment4 = this.mFragment;
            fragment4.mView.setTag(R.id.fragment_container_view_tag, fragment4);
            if (viewGroup != null) {
                viewGroup.addView(this.mFragment.mView);
            }
            Fragment fragment5 = this.mFragment;
            if (fragment5.mHidden) {
                fragment5.mView.setVisibility(8);
            }
            ViewCompat.requestApplyInsets(this.mFragment.mView);
            Fragment fragment6 = this.mFragment;
            fragment6.onViewCreated(fragment6.mView, fragment6.mSavedFragmentState);
            FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
            Fragment fragment7 = this.mFragment;
            fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentViewCreated(fragment7, fragment7.mView, fragment7.mSavedFragmentState, false);
            Fragment fragment8 = this.mFragment;
            if (fragment8.mView.getVisibility() == 0 && this.mFragment.mContainer != null) {
                z = true;
            }
            fragment8.mIsNewlyAdded = z;
        }
    }

    void activityCreated() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto ACTIVITY_CREATED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        fragment.performActivityCreated(fragment.mSavedFragmentState);
        FragmentLifecycleCallbacksDispatcher fragmentLifecycleCallbacksDispatcher = this.mDispatcher;
        Fragment fragment2 = this.mFragment;
        fragmentLifecycleCallbacksDispatcher.dispatchOnFragmentActivityCreated(fragment2, fragment2.mSavedFragmentState, false);
    }

    void restoreViewState() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto RESTORE_VIEW_STATE: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        if (fragment.mView != null) {
            fragment.restoreViewState(fragment.mSavedFragmentState);
        }
        this.mFragment.mSavedFragmentState = null;
    }

    void start() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto STARTED: " + this.mFragment);
        }
        this.mFragment.performStart();
        this.mDispatcher.dispatchOnFragmentStarted(this.mFragment, false);
    }

    void resume() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "moveto RESUMED: " + this.mFragment);
        }
        this.mFragment.performResume();
        this.mDispatcher.dispatchOnFragmentResumed(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mSavedFragmentState = null;
        fragment.mSavedViewState = null;
    }

    void pause() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom RESUMED: " + this.mFragment);
        }
        this.mFragment.performPause();
        this.mDispatcher.dispatchOnFragmentPaused(this.mFragment, false);
    }

    void stop() {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom STARTED: " + this.mFragment);
        }
        this.mFragment.performStop();
        this.mDispatcher.dispatchOnFragmentStopped(this.mFragment, false);
    }

    @NonNull
    FragmentState saveState() {
        FragmentState fragmentState = new FragmentState(this.mFragment);
        Fragment fragment = this.mFragment;
        if (fragment.mState > -1 && fragmentState.mSavedFragmentState == null) {
            Bundle bundleSaveBasicState = saveBasicState();
            fragmentState.mSavedFragmentState = bundleSaveBasicState;
            if (this.mFragment.mTargetWho != null) {
                if (bundleSaveBasicState == null) {
                    fragmentState.mSavedFragmentState = new Bundle();
                }
                fragmentState.mSavedFragmentState.putString(TARGET_STATE_TAG, this.mFragment.mTargetWho);
                int i = this.mFragment.mTargetRequestCode;
                if (i != 0) {
                    fragmentState.mSavedFragmentState.putInt(TARGET_REQUEST_CODE_STATE_TAG, i);
                }
            }
        } else {
            fragmentState.mSavedFragmentState = fragment.mSavedFragmentState;
        }
        return fragmentState;
    }

    @Nullable
    Fragment.SavedState saveInstanceState() {
        Bundle bundleSaveBasicState;
        if (this.mFragment.mState <= -1 || (bundleSaveBasicState = saveBasicState()) == null) {
            return null;
        }
        return new Fragment.SavedState(bundleSaveBasicState);
    }

    private Bundle saveBasicState() {
        Bundle bundle = new Bundle();
        this.mFragment.performSaveInstanceState(bundle);
        this.mDispatcher.dispatchOnFragmentSaveInstanceState(this.mFragment, bundle, false);
        if (bundle.isEmpty()) {
            bundle = null;
        }
        if (this.mFragment.mView != null) {
            saveViewState();
        }
        if (this.mFragment.mSavedViewState != null) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putSparseParcelableArray(VIEW_STATE_TAG, this.mFragment.mSavedViewState);
        }
        if (!this.mFragment.mUserVisibleHint) {
            if (bundle == null) {
                bundle = new Bundle();
            }
            bundle.putBoolean(USER_VISIBLE_HINT_TAG, this.mFragment.mUserVisibleHint);
        }
        return bundle;
    }

    void saveViewState() {
        if (this.mFragment.mView == null) {
            return;
        }
        SparseArray<Parcelable> sparseArray = new SparseArray<>();
        this.mFragment.mView.saveHierarchyState(sparseArray);
        if (sparseArray.size() > 0) {
            this.mFragment.mSavedViewState = sparseArray;
        }
    }

    void destroy(@NonNull FragmentHostCallback<?> fragmentHostCallback, @NonNull FragmentManagerViewModel fragmentManagerViewModel) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom CREATED: " + this.mFragment);
        }
        Fragment fragment = this.mFragment;
        boolean zIsChangingConfigurations = true;
        boolean z = fragment.mRemoving && !fragment.isInBackStack();
        if (z || fragmentManagerViewModel.shouldDestroy(this.mFragment)) {
            if (fragmentHostCallback instanceof ViewModelStoreOwner) {
                zIsChangingConfigurations = fragmentManagerViewModel.isCleared();
            } else if (fragmentHostCallback.getContext() instanceof Activity) {
                zIsChangingConfigurations = true ^ ((Activity) fragmentHostCallback.getContext()).isChangingConfigurations();
            }
            if (z || zIsChangingConfigurations) {
                fragmentManagerViewModel.clearNonConfigState(this.mFragment);
            }
            this.mFragment.performDestroy();
            this.mDispatcher.dispatchOnFragmentDestroyed(this.mFragment, false);
            return;
        }
        this.mFragment.mState = 0;
    }

    void detach(@NonNull FragmentManagerViewModel fragmentManagerViewModel) {
        if (FragmentManager.isLoggingEnabled(3)) {
            Log.d(TAG, "movefrom ATTACHED: " + this.mFragment);
        }
        this.mFragment.performDetach();
        boolean z = false;
        this.mDispatcher.dispatchOnFragmentDetached(this.mFragment, false);
        Fragment fragment = this.mFragment;
        fragment.mState = -1;
        fragment.mHost = null;
        fragment.mParentFragment = null;
        fragment.mFragmentManager = null;
        if (fragment.mRemoving && !fragment.isInBackStack()) {
            z = true;
        }
        if (z || fragmentManagerViewModel.shouldDestroy(this.mFragment)) {
            if (FragmentManager.isLoggingEnabled(3)) {
                Log.d(TAG, "initState called for fragment: " + this.mFragment);
            }
            this.mFragment.initState();
        }
    }
}
