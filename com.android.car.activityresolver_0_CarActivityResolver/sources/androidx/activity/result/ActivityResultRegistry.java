package androidx.activity.result;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.internal.view.SupportMenu;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public abstract class ActivityResultRegistry {
    private static final String KEY_COMPONENT_ACTIVITY_PENDING_RESULTS = "KEY_COMPONENT_ACTIVITY_PENDING_RESULT";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS = "KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS";
    private static final String KEY_COMPONENT_ACTIVITY_REGISTERED_RCS = "KEY_COMPONENT_ACTIVITY_REGISTERED_RCS";
    private static final String LOG_TAG = "ActivityResultRegistry";
    private final AtomicInteger mNextRc = new AtomicInteger(SupportMenu.USER_MASK);
    private final Map<Integer, String> mRcToKey = new HashMap();
    private final Map<String, Integer> mKeyToRc = new HashMap();
    private final transient Map<String, CallbackAndContract<?>> mKeyToCallback = new HashMap();
    private final Bundle mPendingResults = new Bundle();

    public abstract <I, O> void onLaunch(int i, ActivityResultContract<I, O> activityResultContract, I i2, ActivityOptionsCompat activityOptionsCompat);

    public final <I, O> ActivityResultLauncher<I> register(final String key, LifecycleOwner lifecycleOwner, final ActivityResultContract<I, O> contract, final ActivityResultCallback<O> callback) {
        final int requestCode = registerKey(key);
        this.mKeyToCallback.put(key, new CallbackAndContract<>(callback, contract));
        Lifecycle lifecycle = lifecycleOwner.getLifecycle();
        final ActivityResult pendingResult = (ActivityResult) this.mPendingResults.getParcelable(key);
        if (pendingResult != null) {
            this.mPendingResults.remove(key);
            if (lifecycle.getCurrentState().isAtLeast(Lifecycle.State.STARTED)) {
                callback.onActivityResult(contract.parseResult(pendingResult.getResultCode(), pendingResult.getData()));
            } else {
                lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.1
                    @Override // androidx.lifecycle.LifecycleEventObserver
                    public void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                        if (Lifecycle.Event.ON_START.equals(event)) {
                            callback.onActivityResult(contract.parseResult(pendingResult.getResultCode(), pendingResult.getData()));
                        }
                    }
                });
            }
        }
        lifecycle.addObserver(new LifecycleEventObserver() { // from class: androidx.activity.result.ActivityResultRegistry.2
            @Override // androidx.lifecycle.LifecycleEventObserver
            public void onStateChanged(LifecycleOwner lifecycleOwner2, Lifecycle.Event event) {
                if (Lifecycle.Event.ON_DESTROY.equals(event)) {
                    ActivityResultRegistry.this.unregister(key);
                }
            }
        });
        return new ActivityResultLauncher<I>() { // from class: androidx.activity.result.ActivityResultRegistry.3
            @Override // androidx.activity.result.ActivityResultLauncher
            public void launch(I input, ActivityOptionsCompat options) {
                ActivityResultRegistry.this.onLaunch(requestCode, contract, input, options);
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.unregister(key);
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public ActivityResultContract<I, ?> getContract() {
                return contract;
            }
        };
    }

    public final <I, O> ActivityResultLauncher<I> register(final String key, final ActivityResultContract<I, O> contract, ActivityResultCallback<O> callback) {
        final int requestCode = registerKey(key);
        this.mKeyToCallback.put(key, new CallbackAndContract<>(callback, contract));
        ActivityResult pendingResult = (ActivityResult) this.mPendingResults.getParcelable(key);
        if (pendingResult != null) {
            this.mPendingResults.remove(key);
            callback.onActivityResult(contract.parseResult(pendingResult.getResultCode(), pendingResult.getData()));
        }
        return new ActivityResultLauncher<I>() { // from class: androidx.activity.result.ActivityResultRegistry.4
            @Override // androidx.activity.result.ActivityResultLauncher
            public void launch(I input, ActivityOptionsCompat options) {
                ActivityResultRegistry.this.onLaunch(requestCode, contract, input, options);
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public void unregister() {
                ActivityResultRegistry.this.unregister(key);
            }

            @Override // androidx.activity.result.ActivityResultLauncher
            public ActivityResultContract<I, ?> getContract() {
                return contract;
            }
        };
    }

    final void unregister(String key) {
        Integer rc = this.mKeyToRc.remove(key);
        if (rc != null) {
            this.mRcToKey.remove(rc);
        }
        this.mKeyToCallback.remove(key);
        if (this.mPendingResults.containsKey(key)) {
            Log.w(LOG_TAG, "Dropping pending result for request " + key + ": " + this.mPendingResults.getParcelable(key));
            this.mPendingResults.remove(key);
        }
    }

    public final void onSaveInstanceState(Bundle outState) {
        outState.putIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS, new ArrayList<>(this.mRcToKey.keySet()));
        outState.putStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS, new ArrayList<>(this.mRcToKey.values()));
        outState.putBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS, this.mPendingResults);
    }

    public final void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState == null) {
            return;
        }
        ArrayList<Integer> rcs = savedInstanceState.getIntegerArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_RCS);
        ArrayList<String> keys = savedInstanceState.getStringArrayList(KEY_COMPONENT_ACTIVITY_REGISTERED_KEYS);
        if (keys == null || rcs == null) {
            return;
        }
        int numKeys = keys.size();
        for (int i = 0; i < numKeys; i++) {
            bindRcKey(rcs.get(i).intValue(), keys.get(i));
        }
        this.mNextRc.set(numKeys);
        this.mPendingResults.putAll(savedInstanceState.getBundle(KEY_COMPONENT_ACTIVITY_PENDING_RESULTS));
    }

    public final boolean dispatchResult(int requestCode, int resultCode, Intent data) {
        String key = this.mRcToKey.get(Integer.valueOf(requestCode));
        if (key == null) {
            return false;
        }
        doDispatch(key, resultCode, data, this.mKeyToCallback.get(key));
        return true;
    }

    public final <O> boolean dispatchResult(int requestCode, O result) {
        CallbackAndContract<?> callbackAndContract;
        String key = this.mRcToKey.get(Integer.valueOf(requestCode));
        if (key == null || (callbackAndContract = this.mKeyToCallback.get(key)) == null || callbackAndContract.mCallback == null) {
            return false;
        }
        callbackAndContract.mCallback.onActivityResult(result);
        return true;
    }

    private <O> void doDispatch(String key, int resultCode, Intent data, CallbackAndContract<O> callbackAndContract) {
        if (callbackAndContract != null && callbackAndContract.mCallback != null) {
            ActivityResultCallback<O> callback = callbackAndContract.mCallback;
            ActivityResultContract<?, O> contract = callbackAndContract.mContract;
            callback.onActivityResult(contract.parseResult(resultCode, data));
            return;
        }
        this.mPendingResults.putParcelable(key, new ActivityResult(resultCode, data));
    }

    private int registerKey(String key) {
        Integer existing = this.mKeyToRc.get(key);
        if (existing != null) {
            return existing.intValue();
        }
        int rc = this.mNextRc.getAndIncrement();
        bindRcKey(rc, key);
        return rc;
    }

    private void bindRcKey(int rc, String key) {
        this.mRcToKey.put(Integer.valueOf(rc), key);
        this.mKeyToRc.put(key, Integer.valueOf(rc));
    }

    private static class CallbackAndContract<O> {
        final ActivityResultCallback<O> mCallback;
        final ActivityResultContract<?, O> mContract;

        CallbackAndContract(ActivityResultCallback<O> callback, ActivityResultContract<?, O> contract) {
            this.mCallback = callback;
            this.mContract = contract;
        }
    }
}
