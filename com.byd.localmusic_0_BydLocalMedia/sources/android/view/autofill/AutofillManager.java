package android.view.autofill;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.annotation.SystemApi;
import android.content.AutofillOptions;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.graphics.Rect;
import android.metrics.LogMaker;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcelable;
import android.os.RemoteException;
import android.os.SystemClock;
import android.provider.SettingsStringUtil;
import android.service.autofill.FillEventHistory;
import android.service.autofill.UserData;
import android.text.TextUtils;
import android.util.ArrayMap;
import android.util.ArraySet;
import android.util.DebugUtils;
import android.util.Log;
import android.util.Slog;
import android.util.SparseArray;
import android.view.Choreographer;
import android.view.KeyEvent;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.accessibility.AccessibilityNodeProvider;
import android.view.autofill.AutofillManager;
import android.view.autofill.IAugmentedAutofillManagerClient;
import android.view.autofill.IAutoFillManagerClient;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import com.android.internal.logging.MetricsLogger;
import com.android.internal.os.IResultReceiver;
import com.android.internal.util.ArrayUtils;
import com.android.internal.util.Preconditions;
import com.android.internal.util.SyncResultReceiver;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.xmlpull.v1.XmlPullParserException;
import sun.misc.Cleaner;

/* JADX INFO: loaded from: classes3.dex */
public final class AutofillManager {
    public static final int ACTION_RESPONSE_EXPIRED = 5;
    public static final int ACTION_START_SESSION = 1;
    public static final int ACTION_VALUE_CHANGED = 4;
    public static final int ACTION_VIEW_ENTERED = 2;
    public static final int ACTION_VIEW_EXITED = 3;
    private static final int AUTHENTICATION_ID_DATASET_ID_MASK = 65535;
    private static final int AUTHENTICATION_ID_DATASET_ID_SHIFT = 16;
    public static final int AUTHENTICATION_ID_DATASET_ID_UNDEFINED = 65535;
    public static final int DEFAULT_LOGGING_LEVEL;
    public static final int DEFAULT_MAX_PARTITIONS_SIZE = 10;
    public static final String DEVICE_CONFIG_AUGMENTED_SERVICE_IDLE_UNBIND_TIMEOUT = "augmented_service_idle_unbind_timeout";
    public static final String DEVICE_CONFIG_AUGMENTED_SERVICE_REQUEST_TIMEOUT = "augmented_service_request_timeout";
    public static final String DEVICE_CONFIG_AUTOFILL_SMART_SUGGESTION_SUPPORTED_MODES = "smart_suggestion_supported_modes";
    public static final String EXTRA_ASSIST_STRUCTURE = "android.view.autofill.extra.ASSIST_STRUCTURE";
    public static final String EXTRA_AUGMENTED_AUTOFILL_CLIENT = "android.view.autofill.extra.AUGMENTED_AUTOFILL_CLIENT";
    public static final String EXTRA_AUTHENTICATION_RESULT = "android.view.autofill.extra.AUTHENTICATION_RESULT";
    public static final String EXTRA_CLIENT_STATE = "android.view.autofill.extra.CLIENT_STATE";
    public static final String EXTRA_RESTORE_CROSS_ACTIVITY = "android.view.autofill.extra.RESTORE_CROSS_ACTIVITY";
    public static final String EXTRA_RESTORE_SESSION_TOKEN = "android.view.autofill.extra.RESTORE_SESSION_TOKEN";
    public static final int FC_SERVICE_TIMEOUT = 5000;
    public static final int FLAG_ADD_CLIENT_DEBUG = 2;
    public static final int FLAG_ADD_CLIENT_ENABLED = 1;
    public static final int FLAG_ADD_CLIENT_ENABLED_FOR_AUGMENTED_AUTOFILL_ONLY = 8;
    public static final int FLAG_ADD_CLIENT_VERBOSE = 4;
    public static final int FLAG_SMART_SUGGESTION_OFF = 0;
    public static final int FLAG_SMART_SUGGESTION_SYSTEM = 1;
    private static final String LAST_AUTOFILLED_DATA_TAG = "android:lastAutoFilledData";
    public static final int MAX_TEMP_AUGMENTED_SERVICE_DURATION_MS = 120000;
    public static final int NO_LOGGING = 0;
    public static final int NO_SESSION = Integer.MAX_VALUE;
    public static final int PENDING_UI_OPERATION_CANCEL = 1;
    public static final int PENDING_UI_OPERATION_RESTORE = 2;
    public static final int RECEIVER_FLAG_SESSION_FOR_AUGMENTED_AUTOFILL_ONLY = 1;
    public static final int RESULT_CODE_NOT_SERVICE = -1;
    public static final int RESULT_OK = 0;
    private static final String SESSION_ID_TAG = "android:sessionId";
    public static final int SET_STATE_FLAG_DEBUG = 8;
    public static final int SET_STATE_FLAG_ENABLED = 1;
    public static final int SET_STATE_FLAG_FOR_AUTOFILL_ONLY = 32;
    public static final int SET_STATE_FLAG_RESET_CLIENT = 4;
    public static final int SET_STATE_FLAG_RESET_SESSION = 2;
    public static final int SET_STATE_FLAG_VERBOSE = 16;
    public static final int STATE_ACTIVE = 1;
    public static final int STATE_DISABLED_BY_SERVICE = 4;
    public static final int STATE_FINISHED = 2;
    public static final int STATE_SHOWING_SAVE_UI = 3;
    private static final String STATE_TAG = "android:state";
    public static final int STATE_UNKNOWN = 0;
    public static final int STATE_UNKNOWN_COMPAT_MODE = 5;
    public static final int STATE_UNKNOWN_FAILED = 6;
    private static final int SYNC_CALLS_TIMEOUT_MS = 5000;
    private static final String TAG = "AutofillManager";
    private IAugmentedAutofillManagerClient mAugmentedAutofillServiceClient;
    private AutofillCallback mCallback;
    private CompatibilityBridge mCompatibilityBridge;
    private final Context mContext;
    private boolean mEnabled;
    private boolean mEnabledForAugmentedAutofillOnly;
    private Set<AutofillId> mEnteredForAugmentedAutofillIds;
    private ArraySet<AutofillId> mEnteredIds;
    private ArraySet<AutofillId> mFillableIds;
    private boolean mForAugmentedAutofillOnly;
    private AutofillId mIdShownFillUi;
    private ParcelableMap mLastAutofilledData;
    private boolean mOnInvisibleCalled;
    private final AutofillOptions mOptions;
    private boolean mSaveOnFinish;
    private AutofillId mSaveTriggerId;
    private final IAutoFillManager mService;
    private IAutoFillManagerClient mServiceClient;
    private Cleaner mServiceClientCleaner;
    private TrackedViews mTrackedViews;
    private final MetricsLogger mMetricsLogger = new MetricsLogger();
    private final Object mLock = new Object();
    private int mSessionId = Integer.MAX_VALUE;
    private int mState = 0;

    public static abstract class AutofillCallback {
        public static final int EVENT_INPUT_HIDDEN = 2;
        public static final int EVENT_INPUT_SHOWN = 1;
        public static final int EVENT_INPUT_UNAVAILABLE = 3;

        @Retention(RetentionPolicy.SOURCE)
        public @interface AutofillEventType {
        }

        public void onAutofillEvent(View view, int i) {
        }

        public void onAutofillEvent(View view, int i, int i2) {
        }
    }

    public interface AutofillClient {
        void autofillClientAuthenticate(int i, IntentSender intentSender, Intent intent, boolean z);

        void autofillClientDispatchUnhandledKey(View view, KeyEvent keyEvent);

        View autofillClientFindViewByAccessibilityIdTraversal(int i, int i2);

        View autofillClientFindViewByAutofillIdTraversal(AutofillId autofillId);

        View[] autofillClientFindViewsByAutofillIdTraversal(AutofillId[] autofillIdArr);

        IBinder autofillClientGetActivityToken();

        ComponentName autofillClientGetComponentName();

        AutofillId autofillClientGetNextAutofillId();

        boolean[] autofillClientGetViewVisibility(AutofillId[] autofillIdArr);

        boolean autofillClientIsCompatibilityModeEnabled();

        boolean autofillClientIsFillUiShowing();

        boolean autofillClientIsVisibleForAutofill();

        boolean autofillClientRequestHideFillUi();

        boolean autofillClientRequestShowFillUi(View view, int i, int i2, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter);

        void autofillClientResetableStateAvailable();

        void autofillClientRunOnUiThread(Runnable runnable);

        boolean isDisablingEnterExitEventForAutofill();
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface SmartSuggestionMode {
    }

    public static int getDatasetIdFromAuthenticationId(int i) {
        return i & 65535;
    }

    public static int getRequestIdFromAuthenticationId(int i) {
        return i >> 16;
    }

    public static int makeAuthenticationId(int i, int i2) {
        return (i << 16) | (i2 & 65535);
    }

    static {
        DEFAULT_LOGGING_LEVEL = Build.IS_DEBUGGABLE ? 2 : 0;
    }

    public AutofillManager(Context context, IAutoFillManager iAutoFillManager) {
        this.mContext = (Context) Preconditions.checkNotNull(context, "context cannot be null");
        this.mService = iAutoFillManager;
        AutofillOptions autofillOptions = context.getAutofillOptions();
        this.mOptions = autofillOptions;
        if (autofillOptions != null) {
            Helper.sDebug = (autofillOptions.loggingLevel & 2) != 0;
            Helper.sVerbose = (this.mOptions.loggingLevel & 4) != 0;
        }
    }

    public void enableCompatibilityMode() {
        synchronized (this.mLock) {
            if (Helper.sDebug) {
                Slog.d(TAG, "creating CompatibilityBridge for " + this.mContext);
            }
            this.mCompatibilityBridge = new CompatibilityBridge();
        }
    }

    public void onCreate(Bundle bundle) {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                this.mLastAutofilledData = (ParcelableMap) bundle.getParcelable(LAST_AUTOFILLED_DATA_TAG);
                if (isActiveLocked()) {
                    Log.w(TAG, "New session was started before onCreate()");
                    return;
                }
                this.mSessionId = bundle.getInt(SESSION_ID_TAG, Integer.MAX_VALUE);
                this.mState = bundle.getInt(STATE_TAG, 0);
                if (this.mSessionId != Integer.MAX_VALUE) {
                    ensureServiceClientAddedIfNeededLocked();
                    AutofillClient client = getClient();
                    if (client != null) {
                        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
                        try {
                            this.mService.restoreSession(this.mSessionId, client.autofillClientGetActivityToken(), this.mServiceClient.asBinder(), syncResultReceiver);
                            boolean z = true;
                            if (syncResultReceiver.getIntResult() != 1) {
                                z = false;
                            }
                            if (!z) {
                                Log.w(TAG, "Session " + this.mSessionId + " could not be restored");
                                this.mSessionId = Integer.MAX_VALUE;
                                this.mState = 0;
                            } else {
                                if (Helper.sDebug) {
                                    Log.d(TAG, "session " + this.mSessionId + " was restored");
                                }
                                client.autofillClientResetableStateAvailable();
                            }
                        } catch (RemoteException e) {
                            Log.e(TAG, "Could not figure out if there was an autofill session", e);
                        } catch (SyncResultReceiver.TimeoutException e2) {
                            Log.e(TAG, "Fail to get session restore status: " + e2);
                        }
                    }
                }
            }
        }
    }

    public void onVisibleForAutofill() {
        Choreographer.getInstance().postCallback(4, new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$YfpJNFodEuj5lbXfPlc77fsEvC8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onVisibleForAutofill$0$AutofillManager();
            }
        }, null);
    }

    public /* synthetic */ void lambda$onVisibleForAutofill$0$AutofillManager() {
        synchronized (this.mLock) {
            if (this.mEnabled && isActiveLocked() && this.mTrackedViews != null) {
                this.mTrackedViews.onVisibleForAutofillChangedLocked();
            }
        }
    }

    public void onInvisibleForAutofill(boolean z) {
        synchronized (this.mLock) {
            this.mOnInvisibleCalled = true;
            if (z) {
                updateSessionLocked(null, null, null, 5, 0);
            }
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                if (this.mSessionId != Integer.MAX_VALUE) {
                    bundle.putInt(SESSION_ID_TAG, this.mSessionId);
                }
                if (this.mState != 0) {
                    bundle.putInt(STATE_TAG, this.mState);
                }
                if (this.mLastAutofilledData != null) {
                    bundle.putParcelable(LAST_AUTOFILLED_DATA_TAG, this.mLastAutofilledData);
                }
            }
        }
    }

    public boolean isCompatibilityModeEnabledLocked() {
        return this.mCompatibilityBridge != null;
    }

    public boolean isEnabled() {
        if (!hasAutofillFeature()) {
            return false;
        }
        synchronized (this.mLock) {
            if (isDisabledByServiceLocked()) {
                return false;
            }
            ensureServiceClientAddedIfNeededLocked();
            return this.mEnabled;
        }
    }

    public FillEventHistory getFillEventHistory() {
        try {
            SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
            this.mService.getFillEventHistory(syncResultReceiver);
            return (FillEventHistory) syncResultReceiver.getParcelableResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException e2) {
            Log.e(TAG, "Fail to get fill event history: " + e2);
            return null;
        }
    }

    public void requestAutofill(View view) {
        notifyViewEntered(view, !view.isFocused() ? 17 : 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void requestAutofillFromNewSession(View view) {
        cancel();
        notifyViewEntered(view);
    }

    public void requestAutofill(View view, int i, Rect rect) {
        notifyViewEntered(view, i, rect, !view.isFocused() ? 17 : 1);
    }

    public void notifyViewEntered(View view) {
        notifyViewEntered(view, 0);
    }

    private boolean shouldIgnoreViewEnteredLocked(AutofillId autofillId, int i) {
        ArraySet<AutofillId> arraySet;
        if (isDisabledByServiceLocked()) {
            if (Helper.sVerbose) {
                Log.v(TAG, "ignoring notifyViewEntered(flags=" + i + ", view=" + autofillId + ") on state " + getStateAsStringLocked() + " because disabled by svc");
            }
            return true;
        }
        if (!isFinishedLocked() || (i & 1) != 0 || (arraySet = this.mEnteredIds) == null || !arraySet.contains(autofillId)) {
            return false;
        }
        if (Helper.sVerbose) {
            Log.v(TAG, "ignoring notifyViewEntered(flags=" + i + ", view=" + autofillId + ") on state " + getStateAsStringLocked() + " because view was already entered: " + this.mEnteredIds);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isClientVisibleForAutofillLocked() {
        AutofillClient client = getClient();
        return client != null && client.autofillClientIsVisibleForAutofill();
    }

    private boolean isClientDisablingEnterExitEvent() {
        AutofillClient client = getClient();
        return client != null && client.isDisablingEnterExitEventForAutofill();
    }

    private void notifyViewEntered(View view, int i) {
        AutofillCallback autofillCallbackNotifyViewEnteredLocked;
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                autofillCallbackNotifyViewEnteredLocked = notifyViewEnteredLocked(view, i);
            }
            if (autofillCallbackNotifyViewEnteredLocked != null) {
                this.mCallback.onAutofillEvent(view, 3);
            }
        }
    }

    private AutofillCallback notifyViewEnteredLocked(View view, int i) {
        AutofillId autofillId = view.getAutofillId();
        if (shouldIgnoreViewEnteredLocked(autofillId, i)) {
            return null;
        }
        ensureServiceClientAddedIfNeededLocked();
        if (!this.mEnabled && !this.mEnabledForAugmentedAutofillOnly) {
            if (Helper.sVerbose) {
                Log.v(TAG, "ignoring notifyViewEntered(" + autofillId + "): disabled");
            }
            AutofillCallback autofillCallback = this.mCallback;
            if (autofillCallback != null) {
                return autofillCallback;
            }
            return null;
        }
        if (isClientDisablingEnterExitEvent()) {
            return null;
        }
        AutofillValue autofillValue = view.getAutofillValue();
        if ((view instanceof TextView) && ((TextView) view).isAnyPasswordInputType()) {
            i |= 4;
        }
        int i2 = i;
        if (!isActiveLocked()) {
            startSessionLocked(autofillId, null, autofillValue, i2);
        } else {
            if (this.mForAugmentedAutofillOnly && (i2 & 1) != 0) {
                if (Helper.sDebug) {
                    Log.d(TAG, "notifyViewEntered(" + autofillId + "): resetting mForAugmentedAutofillOnly on manual request");
                }
                this.mForAugmentedAutofillOnly = false;
            }
            updateSessionLocked(autofillId, null, autofillValue, 2, i2);
        }
        addEnteredIdLocked(autofillId);
        return null;
    }

    public void notifyViewExited(View view) {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                notifyViewExitedLocked(view);
            }
        }
    }

    void notifyViewExitedLocked(View view) {
        ensureServiceClientAddedIfNeededLocked();
        if ((this.mEnabled || this.mEnabledForAugmentedAutofillOnly) && isActiveLocked() && !isClientDisablingEnterExitEvent()) {
            updateSessionLocked(view.getAutofillId(), null, null, 3, 0);
        }
    }

    public void notifyViewVisibilityChanged(View view, boolean z) {
        notifyViewVisibilityChangedInternal(view, 0, z, false);
    }

    public void notifyViewVisibilityChanged(View view, int i, boolean z) {
        notifyViewVisibilityChangedInternal(view, i, z, true);
    }

    private void notifyViewVisibilityChangedInternal(View view, int i, boolean z, boolean z2) {
        AutofillId autofillId;
        synchronized (this.mLock) {
            if (this.mForAugmentedAutofillOnly) {
                if (Helper.sVerbose) {
                    Log.v(TAG, "notifyViewVisibilityChanged(): ignoring on augmented only mode");
                }
                return;
            }
            if (this.mEnabled && isActiveLocked()) {
                if (z2) {
                    autofillId = getAutofillId(view, i);
                } else {
                    autofillId = view.getAutofillId();
                }
                if (Helper.sVerbose) {
                    Log.v(TAG, "visibility changed for " + autofillId + ": " + z);
                }
                if (!z && this.mFillableIds != null && this.mFillableIds.contains(autofillId)) {
                    if (Helper.sDebug) {
                        Log.d(TAG, "Hidding UI when view " + autofillId + " became invisible");
                    }
                    requestHideFillUi(autofillId, view);
                }
                if (this.mTrackedViews != null) {
                    this.mTrackedViews.notifyViewVisibilityChangedLocked(autofillId, z);
                } else if (Helper.sVerbose) {
                    Log.v(TAG, "Ignoring visibility change on " + autofillId + ": no tracked views");
                }
            } else if (!z2 && z) {
                startAutofillIfNeededLocked(view);
            }
        }
    }

    public void notifyViewEntered(View view, int i, Rect rect) {
        notifyViewEntered(view, i, rect, 0);
    }

    private void notifyViewEntered(View view, int i, Rect rect, int i2) {
        AutofillCallback autofillCallbackNotifyViewEnteredLocked;
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                autofillCallbackNotifyViewEnteredLocked = notifyViewEnteredLocked(view, i, rect, i2);
            }
            if (autofillCallbackNotifyViewEnteredLocked != null) {
                autofillCallbackNotifyViewEnteredLocked.onAutofillEvent(view, i, 3);
            }
        }
    }

    private AutofillCallback notifyViewEnteredLocked(View view, int i, Rect rect, int i2) {
        AutofillId autofillId = getAutofillId(view, i);
        if (shouldIgnoreViewEnteredLocked(autofillId, i2)) {
            return null;
        }
        ensureServiceClientAddedIfNeededLocked();
        if (!this.mEnabled && !this.mEnabledForAugmentedAutofillOnly) {
            if (Helper.sVerbose) {
                Log.v(TAG, "ignoring notifyViewEntered(" + autofillId + "): disabled");
            }
            AutofillCallback autofillCallback = this.mCallback;
            if (autofillCallback != null) {
                return autofillCallback;
            }
            return null;
        }
        if (isClientDisablingEnterExitEvent()) {
            return null;
        }
        if ((view instanceof TextView) && ((TextView) view).isAnyPasswordInputType()) {
            i2 |= 4;
        }
        int i3 = i2;
        if (!isActiveLocked()) {
            startSessionLocked(autofillId, rect, null, i3);
        } else {
            if (this.mForAugmentedAutofillOnly && (i3 & 1) != 0) {
                if (Helper.sDebug) {
                    Log.d(TAG, "notifyViewEntered(" + autofillId + "): resetting mForAugmentedAutofillOnly on manual request");
                }
                this.mForAugmentedAutofillOnly = false;
            }
            updateSessionLocked(autofillId, rect, null, 2, i3);
        }
        addEnteredIdLocked(autofillId);
        return null;
    }

    private void addEnteredIdLocked(AutofillId autofillId) {
        if (this.mEnteredIds == null) {
            this.mEnteredIds = new ArraySet<>(1);
        }
        autofillId.resetSessionId();
        this.mEnteredIds.add(autofillId);
    }

    public void notifyViewExited(View view, int i) {
        if (Helper.sVerbose) {
            Log.v(TAG, "notifyViewExited(" + view.getAutofillId() + ", " + i);
        }
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                notifyViewExitedLocked(view, i);
            }
        }
    }

    private void notifyViewExitedLocked(View view, int i) {
        ensureServiceClientAddedIfNeededLocked();
        if ((this.mEnabled || this.mEnabledForAugmentedAutofillOnly) && isActiveLocked() && !isClientDisablingEnterExitEvent()) {
            updateSessionLocked(getAutofillId(view, i), null, null, 3, 0);
        }
    }

    public void notifyValueChanged(View view) {
        AutofillValue autofillValue;
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                AutofillValue autofillValue2 = null;
                AutofillId autofillId = null;
                boolean z = true;
                if (this.mLastAutofilledData == null) {
                    view.setAutofilled(false, false);
                    autofillValue = null;
                    z = false;
                } else {
                    AutofillId autofillId2 = view.getAutofillId();
                    if (this.mLastAutofilledData.containsKey(autofillId2)) {
                        autofillValue2 = view.getAutofillValue();
                        boolean z2 = this.mLastAutofilledData.keySet().size() == 1;
                        if (Objects.equals(this.mLastAutofilledData.get(autofillId2), autofillValue2)) {
                            view.setAutofilled(true, z2);
                        } else {
                            view.setAutofilled(false, false);
                            this.mLastAutofilledData.remove(autofillId2);
                        }
                    } else {
                        view.setAutofilled(false, false);
                        z = false;
                    }
                    AutofillValue autofillValue3 = autofillValue2;
                    autofillId = autofillId2;
                    autofillValue = autofillValue3;
                }
                if (this.mEnabled && isActiveLocked()) {
                    if (autofillId == null) {
                        autofillId = view.getAutofillId();
                    }
                    if (!z) {
                        autofillValue = view.getAutofillValue();
                    }
                    updateSessionLocked(autofillId, null, autofillValue, 4, 0);
                    return;
                }
                if (!startAutofillIfNeededLocked(view) && Helper.sVerbose) {
                    Log.v(TAG, "notifyValueChanged(" + view.getAutofillId() + "): ignoring on state " + getStateAsStringLocked());
                }
            }
        }
    }

    public void notifyValueChanged(View view, int i, AutofillValue autofillValue) {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                if (this.mEnabled && isActiveLocked()) {
                    updateSessionLocked(getAutofillId(view, i), null, autofillValue, 4, 0);
                    return;
                }
                if (Helper.sVerbose) {
                    Log.v(TAG, "notifyValueChanged(" + view.getAutofillId() + SettingsStringUtil.DELIMITER + i + "): ignoring on state " + getStateAsStringLocked());
                }
            }
        }
    }

    public void notifyViewClicked(View view) {
        notifyViewClicked(view.getAutofillId());
    }

    public void notifyViewClicked(View view, int i) {
        notifyViewClicked(getAutofillId(view, i));
    }

    private void notifyViewClicked(AutofillId autofillId) {
        if (hasAutofillFeature()) {
            if (Helper.sVerbose) {
                Log.v(TAG, "notifyViewClicked(): id=" + autofillId + ", trigger=" + this.mSaveTriggerId);
            }
            synchronized (this.mLock) {
                if (this.mEnabled && isActiveLocked()) {
                    if (this.mSaveTriggerId != null && this.mSaveTriggerId.equals(autofillId)) {
                        if (Helper.sDebug) {
                            Log.d(TAG, "triggering commit by click of " + autofillId);
                        }
                        commitLocked();
                        this.mMetricsLogger.write(newLog(1229));
                    }
                }
            }
        }
    }

    public void onActivityFinishing() {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                if (this.mSaveOnFinish) {
                    if (Helper.sDebug) {
                        Log.d(TAG, "onActivityFinishing(): calling commitLocked()");
                    }
                    commitLocked();
                } else {
                    if (Helper.sDebug) {
                        Log.d(TAG, "onActivityFinishing(): calling cancelLocked()");
                    }
                    cancelLocked();
                }
            }
        }
    }

    public void commit() {
        if (hasAutofillFeature()) {
            if (Helper.sVerbose) {
                Log.v(TAG, "commit() called by app");
            }
            synchronized (this.mLock) {
                commitLocked();
            }
        }
    }

    private void commitLocked() {
        if (this.mEnabled || isActiveLocked()) {
            finishSessionLocked();
        }
    }

    public void cancel() {
        if (Helper.sVerbose) {
            Log.v(TAG, "cancel() called by app or augmented autofill service");
        }
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                cancelLocked();
            }
        }
    }

    private void cancelLocked() {
        if (this.mEnabled || isActiveLocked()) {
            cancelSessionLocked();
        }
    }

    public void disableOwnedAutofillServices() {
        disableAutofillServices();
    }

    public void disableAutofillServices() {
        if (hasAutofillFeature()) {
            try {
                this.mService.disableOwnedAutofillServices(this.mContext.getUserId());
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    public boolean hasEnabledAutofillServices() {
        if (this.mService == null) {
            return false;
        }
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.isServiceEnabled(this.mContext.getUserId(), this.mContext.getPackageName(), syncResultReceiver);
            return syncResultReceiver.getIntResult() == 1;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get enabled autofill services status.");
        }
    }

    public ComponentName getAutofillServiceComponentName() {
        if (this.mService == null) {
            return null;
        }
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.getAutofillServiceComponentName(syncResultReceiver);
            return (ComponentName) syncResultReceiver.getParcelableResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get autofill services component name.");
        }
    }

    public String getUserDataId() {
        try {
            SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
            this.mService.getUserDataId(syncResultReceiver);
            return syncResultReceiver.getStringResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get user data id for field classification.");
        }
    }

    public UserData getUserData() {
        try {
            SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
            this.mService.getUserData(syncResultReceiver);
            return (UserData) syncResultReceiver.getParcelableResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get user data for field classification.");
        }
    }

    public void setUserData(UserData userData) {
        try {
            this.mService.setUserData(userData);
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    public boolean isFieldClassificationEnabled() {
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.isFieldClassificationEnabled(syncResultReceiver);
            return syncResultReceiver.getIntResult() == 1;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get field classification enabled status.");
        }
    }

    public String getDefaultFieldClassificationAlgorithm() {
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.getDefaultFieldClassificationAlgorithm(syncResultReceiver);
            return syncResultReceiver.getStringResult();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get default field classification algorithm.");
        }
    }

    public List<String> getAvailableFieldClassificationAlgorithms() {
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.getAvailableFieldClassificationAlgorithms(syncResultReceiver);
            String[] stringArrayResult = syncResultReceiver.getStringArrayResult();
            return stringArrayResult != null ? Arrays.asList(stringArrayResult) : Collections.emptyList();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get available field classification algorithms.");
        }
    }

    public boolean isAutofillSupported() {
        if (this.mService == null) {
            return false;
        }
        SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
        try {
            this.mService.isServiceSupported(this.mContext.getUserId(), syncResultReceiver);
            return syncResultReceiver.getIntResult() == 1;
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException unused) {
            throw new RuntimeException("Fail to get autofill supported status.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AutofillClient getClient() {
        AutofillClient autofillClient = this.mContext.getAutofillClient();
        if (autofillClient == null && Helper.sVerbose) {
            Log.v(TAG, "No AutofillClient for " + this.mContext.getPackageName() + " on context " + this.mContext);
        }
        return autofillClient;
    }

    public boolean isAutofillUiShowing() {
        AutofillClient autofillClient = this.mContext.getAutofillClient();
        return autofillClient != null && autofillClient.autofillClientIsFillUiShowing();
    }

    public void onAuthenticationResult(int i, Intent intent, View view) {
        if (hasAutofillFeature()) {
            if (Helper.sDebug) {
                Log.d(TAG, "onAuthenticationResult(): id= " + i + ", data=" + intent);
            }
            synchronized (this.mLock) {
                if (isActiveLocked()) {
                    if (!this.mOnInvisibleCalled && view != null && view.canNotifyAutofillEnterExitEvent()) {
                        notifyViewExitedLocked(view);
                        notifyViewEnteredLocked(view, 0);
                    }
                    if (intent == null) {
                        return;
                    }
                    Parcelable parcelableExtra = intent.getParcelableExtra(EXTRA_AUTHENTICATION_RESULT);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable(EXTRA_AUTHENTICATION_RESULT, parcelableExtra);
                    Bundle bundleExtra = intent.getBundleExtra(EXTRA_CLIENT_STATE);
                    if (bundleExtra != null) {
                        bundle.putBundle(EXTRA_CLIENT_STATE, bundleExtra);
                    }
                    try {
                        this.mService.setAuthenticationResult(bundle, this.mSessionId, i, this.mContext.getUserId());
                    } catch (RemoteException e) {
                        Log.e(TAG, "Error delivering authentication result", e);
                    }
                }
            }
        }
    }

    public AutofillId getNextAutofillId() {
        AutofillClient client = getClient();
        if (client == null) {
            return null;
        }
        AutofillId autofillIdAutofillClientGetNextAutofillId = client.autofillClientGetNextAutofillId();
        if (autofillIdAutofillClientGetNextAutofillId == null && Helper.sDebug) {
            Log.d(TAG, "getNextAutofillId(): client " + client + " returned null");
        }
        return autofillIdAutofillClientGetNextAutofillId;
    }

    private static AutofillId getAutofillId(View view, int i) {
        return new AutofillId(view.getAutofillViewId(), i);
    }

    private void startSessionLocked(AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i) {
        int i2;
        Set<AutofillId> set = this.mEnteredForAugmentedAutofillIds;
        if ((set == null || !set.contains(autofillId)) && !this.mEnabledForAugmentedAutofillOnly) {
            i2 = i;
        } else {
            if (Helper.sVerbose) {
                Log.v(TAG, "Starting session for augmented autofill on " + autofillId);
            }
            i2 = i | 8;
        }
        if (Helper.sVerbose) {
            Log.v(TAG, "startSessionLocked(): id=" + autofillId + ", bounds=" + rect + ", value=" + autofillValue + ", flags=" + i2 + ", state=" + getStateAsStringLocked() + ", compatMode=" + isCompatibilityModeEnabledLocked() + ", augmentedOnly=" + this.mForAugmentedAutofillOnly + ", enabledAugmentedOnly=" + this.mEnabledForAugmentedAutofillOnly + ", enteredIds=" + this.mEnteredIds);
        }
        if (this.mForAugmentedAutofillOnly && !this.mEnabledForAugmentedAutofillOnly && (i2 & 1) != 0) {
            if (Helper.sVerbose) {
                Log.v(TAG, "resetting mForAugmentedAutofillOnly on manual autofill request");
            }
            this.mForAugmentedAutofillOnly = false;
        }
        if (this.mState != 0 && !isFinishedLocked() && (i2 & 1) == 0) {
            if (Helper.sVerbose) {
                Log.v(TAG, "not automatically starting session for " + autofillId + " on state " + getStateAsStringLocked() + " and flags " + i2);
                return;
            }
            return;
        }
        try {
            AutofillClient client = getClient();
            if (client == null) {
                return;
            }
            SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
            ComponentName componentNameAutofillClientGetComponentName = client.autofillClientGetComponentName();
            if (!this.mEnabledForAugmentedAutofillOnly && this.mOptions != null && this.mOptions.isAutofillDisabledLocked(componentNameAutofillClientGetComponentName)) {
                if (this.mOptions.isAugmentedAutofillEnabled(this.mContext)) {
                    if (Helper.sDebug) {
                        Log.d(TAG, "startSession(" + componentNameAutofillClientGetComponentName + "): disabled by service but whitelisted for augmented autofill");
                        i2 |= 8;
                    }
                } else {
                    if (Helper.sDebug) {
                        Log.d(TAG, "startSession(" + componentNameAutofillClientGetComponentName + "): ignored because disabled by service and not whitelisted for augmented autofill");
                    }
                    setSessionFinished(4, null);
                    client.autofillClientResetableStateAvailable();
                    return;
                }
            }
            this.mService.startSession(client.autofillClientGetActivityToken(), this.mServiceClient.asBinder(), autofillId, rect, autofillValue, this.mContext.getUserId(), this.mCallback != null, i2, componentNameAutofillClientGetComponentName, isCompatibilityModeEnabledLocked(), syncResultReceiver);
            int intResult = syncResultReceiver.getIntResult();
            this.mSessionId = intResult;
            if (intResult != Integer.MAX_VALUE) {
                this.mState = 1;
            }
            if ((syncResultReceiver.getOptionalExtraIntResult(0) & 1) != 0) {
                if (Helper.sDebug) {
                    Log.d(TAG, "startSession(" + componentNameAutofillClientGetComponentName + "): for augmented only");
                }
                this.mForAugmentedAutofillOnly = true;
            }
            client.autofillClientResetableStateAvailable();
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        } catch (SyncResultReceiver.TimeoutException e2) {
            Log.w(TAG, "Exception getting result from SyncResultReceiver: " + e2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finishSessionLocked() {
        if (Helper.sVerbose) {
            Log.v(TAG, "finishSessionLocked(): " + getStateAsStringLocked());
        }
        if (isActiveLocked()) {
            try {
                this.mService.finishSession(this.mSessionId, this.mContext.getUserId());
                resetSessionLocked(true);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    private void cancelSessionLocked() {
        if (Helper.sVerbose) {
            Log.v(TAG, "cancelSessionLocked(): " + getStateAsStringLocked());
        }
        if (isActiveLocked()) {
            try {
                this.mService.cancelSession(this.mSessionId, this.mContext.getUserId());
                resetSessionLocked(true);
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            }
        }
    }

    private void resetSessionLocked(boolean z) {
        this.mSessionId = Integer.MAX_VALUE;
        this.mState = 0;
        this.mTrackedViews = null;
        this.mFillableIds = null;
        this.mSaveTriggerId = null;
        this.mIdShownFillUi = null;
        if (z) {
            this.mEnteredIds = null;
        }
    }

    private void updateSessionLocked(AutofillId autofillId, Rect rect, AutofillValue autofillValue, int i, int i2) {
        if (Helper.sVerbose) {
            Log.v(TAG, "updateSessionLocked(): id=" + autofillId + ", bounds=" + rect + ", value=" + autofillValue + ", action=" + i + ", flags=" + i2);
        }
        try {
            this.mService.updateSession(this.mSessionId, autofillId, rect, autofillValue, i, i2, this.mContext.getUserId());
        } catch (RemoteException e) {
            throw e.rethrowFromSystemServer();
        }
    }

    private void ensureServiceClientAddedIfNeededLocked() {
        AutofillClient client = getClient();
        if (client != null && this.mServiceClient == null) {
            this.mServiceClient = new AutofillManagerClient();
            try {
                final int userId = this.mContext.getUserId();
                SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
                this.mService.addClient(this.mServiceClient, client.autofillClientGetComponentName(), userId, syncResultReceiver);
                try {
                    int intResult = syncResultReceiver.getIntResult();
                    this.mEnabled = (intResult & 1) != 0;
                    Helper.sDebug = (intResult & 2) != 0;
                    Helper.sVerbose = (intResult & 4) != 0;
                    this.mEnabledForAugmentedAutofillOnly = (intResult & 8) != 0;
                    if (Helper.sVerbose) {
                        Log.v(TAG, "receiver results: flags=" + intResult + " enabled=" + this.mEnabled + ", enabledForAugmentedOnly: " + this.mEnabledForAugmentedAutofillOnly);
                    }
                    final IAutoFillManager iAutoFillManager = this.mService;
                    final IAutoFillManagerClient iAutoFillManagerClient = this.mServiceClient;
                    this.mServiceClientCleaner = Cleaner.create(this, new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$V76JiQu509LCUz3-ckpb-nB3JhA
                        @Override // java.lang.Runnable
                        public final void run() throws RemoteException {
                            iAutoFillManager.removeClient(iAutoFillManagerClient, userId);
                        }
                    });
                } catch (SyncResultReceiver.TimeoutException e) {
                    Log.w(TAG, "Failed to initialize autofill: " + e);
                }
            } catch (RemoteException e2) {
                throw e2.rethrowFromSystemServer();
            }
        }
    }

    private boolean startAutofillIfNeededLocked(View view) {
        if (this.mState == 0 && this.mSessionId == Integer.MAX_VALUE && (view instanceof EditText) && !TextUtils.isEmpty(((EditText) view).getText()) && !view.isFocused() && view.isImportantForAutofill() && view.isLaidOut() && view.isVisibleToUser()) {
            ensureServiceClientAddedIfNeededLocked();
            if (Helper.sVerbose) {
                Log.v(TAG, "startAutofillIfNeededLocked(): enabled=" + this.mEnabled);
            }
            if (this.mEnabled && !isClientDisablingEnterExitEvent()) {
                AutofillId autofillId = view.getAutofillId();
                AutofillValue autofillValue = view.getAutofillValue();
                startSessionLocked(autofillId, null, null, 0);
                updateSessionLocked(autofillId, null, autofillValue, 4, 0);
                addEnteredIdLocked(autofillId);
                return true;
            }
        }
        return false;
    }

    public void registerCallback(AutofillCallback autofillCallback) {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                if (autofillCallback == null) {
                    return;
                }
                boolean z = this.mCallback != null;
                this.mCallback = autofillCallback;
                if (!z) {
                    try {
                        this.mService.setHasCallback(this.mSessionId, this.mContext.getUserId(), true);
                    } catch (RemoteException e) {
                        throw e.rethrowFromSystemServer();
                    }
                }
            }
        }
    }

    public void unregisterCallback(AutofillCallback autofillCallback) {
        if (hasAutofillFeature()) {
            synchronized (this.mLock) {
                if (autofillCallback != null) {
                    if (this.mCallback != null && autofillCallback == this.mCallback) {
                        this.mCallback = null;
                        try {
                            this.mService.setHasCallback(this.mSessionId, this.mContext.getUserId(), false);
                        } catch (RemoteException e) {
                            throw e.rethrowFromSystemServer();
                        }
                    }
                }
            }
        }
    }

    @SystemApi
    public void setAugmentedAutofillWhitelist(Set<String> set, Set<ComponentName> set2) {
        if (hasAutofillFeature()) {
            SyncResultReceiver syncResultReceiver = new SyncResultReceiver(5000);
            try {
                this.mService.setAugmentedAutofillWhitelist(Helper.toList(set), Helper.toList(set2), syncResultReceiver);
                int intResult = syncResultReceiver.getIntResult();
                if (intResult == -1) {
                    throw new SecurityException("caller is not user's Augmented Autofill Service");
                }
                if (intResult != 0) {
                    Log.wtf(TAG, "setAugmentedAutofillWhitelist(): received invalid result: " + intResult);
                }
            } catch (RemoteException e) {
                throw e.rethrowFromSystemServer();
            } catch (SyncResultReceiver.TimeoutException e2) {
                Log.e(TAG, "Fail to get the result of set AugmentedAutofill whitelist. " + e2);
            }
        }
    }

    public void notifyViewEnteredForAugmentedAutofill(View view) {
        AutofillId autofillId = view.getAutofillId();
        synchronized (this.mLock) {
            if (this.mEnteredForAugmentedAutofillIds == null) {
                this.mEnteredForAugmentedAutofillIds = new ArraySet(1);
            }
            this.mEnteredForAugmentedAutofillIds.add(autofillId);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestShowFillUi(int i, AutofillId autofillId, int i2, int i3, Rect rect, IAutofillWindowPresenter iAutofillWindowPresenter) {
        AutofillClient client;
        View viewFindView = findView(autofillId);
        if (viewFindView == null) {
            return;
        }
        AutofillCallback autofillCallback = null;
        synchronized (this.mLock) {
            if (this.mSessionId == i && (client = getClient()) != null && client.autofillClientRequestShowFillUi(viewFindView, i2, i3, rect, iAutofillWindowPresenter)) {
                autofillCallback = this.mCallback;
                this.mIdShownFillUi = autofillId;
            }
        }
        if (autofillCallback != null) {
            if (autofillId.isVirtualInt()) {
                autofillCallback.onAutofillEvent(viewFindView, autofillId.getVirtualChildIntId(), 1);
            } else {
                autofillCallback.onAutofillEvent(viewFindView, 1);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void authenticate(int i, int i2, IntentSender intentSender, Intent intent, boolean z) {
        AutofillClient client;
        synchronized (this.mLock) {
            if (i == this.mSessionId && (client = getClient()) != null) {
                this.mOnInvisibleCalled = false;
                client.autofillClientAuthenticate(i2, intentSender, intent, z);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchUnhandledKey(int i, AutofillId autofillId, KeyEvent keyEvent) {
        AutofillClient client;
        View viewFindView = findView(autofillId);
        if (viewFindView == null) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mSessionId == i && (client = getClient()) != null) {
                client.autofillClientDispatchUnhandledKey(viewFindView, keyEvent);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int i) {
        if (Helper.sVerbose) {
            Log.v(TAG, "setState(" + i + ": " + DebugUtils.flagsToString(AutofillManager.class, "SET_STATE_FLAG_", i) + ")");
        }
        synchronized (this.mLock) {
            try {
                if ((i & 32) != 0) {
                    this.mForAugmentedAutofillOnly = true;
                    return;
                }
                boolean z = (i & 1) != 0;
                this.mEnabled = z;
                if (!z || (i & 2) != 0) {
                    resetSessionLocked(true);
                }
                if ((i & 4) != 0) {
                    this.mServiceClient = null;
                    this.mAugmentedAutofillServiceClient = null;
                    if (this.mServiceClientCleaner != null) {
                        this.mServiceClientCleaner.clean();
                        this.mServiceClientCleaner = null;
                    }
                    notifyReenableAutofill();
                }
                Helper.sDebug = (i & 8) != 0;
                Helper.sVerbose = (i & 16) != 0;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    private void setAutofilledIfValuesIs(View view, AutofillValue autofillValue, boolean z) {
        if (Objects.equals(view.getAutofillValue(), autofillValue)) {
            synchronized (this.mLock) {
                if (this.mLastAutofilledData == null) {
                    this.mLastAutofilledData = new ParcelableMap(1);
                }
                this.mLastAutofilledData.put(view.getAutofillId(), autofillValue);
            }
            view.setAutofilled(true, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autofill(int i, List<AutofillId> list, List<AutofillValue> list2, boolean z) {
        synchronized (this.mLock) {
            if (i != this.mSessionId) {
                return;
            }
            AutofillClient client = getClient();
            if (client == null) {
                return;
            }
            int size = list.size();
            View[] viewArrAutofillClientFindViewsByAutofillIdTraversal = client.autofillClientFindViewsByAutofillIdTraversal(Helper.toArray(list));
            ArrayList arrayList = null;
            ArrayMap arrayMap = null;
            int size2 = 0;
            for (int i2 = 0; i2 < size; i2++) {
                AutofillId autofillId = list.get(i2);
                AutofillValue autofillValue = list2.get(i2);
                View view = viewArrAutofillClientFindViewsByAutofillIdTraversal[i2];
                if (view == null) {
                    Log.d(TAG, "autofill(): no View with id " + autofillId);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(autofillId);
                } else if (autofillId.isVirtualInt()) {
                    if (arrayMap == null) {
                        arrayMap = new ArrayMap(1);
                    }
                    SparseArray sparseArray = (SparseArray) arrayMap.get(view);
                    if (sparseArray == null) {
                        sparseArray = new SparseArray(5);
                        arrayMap.put(view, sparseArray);
                    }
                    sparseArray.put(autofillId.getVirtualChildIntId(), autofillValue);
                } else {
                    if (this.mLastAutofilledData == null) {
                        this.mLastAutofilledData = new ParcelableMap(size - i2);
                    }
                    this.mLastAutofilledData.put(autofillId, autofillValue);
                    view.autofill(autofillValue);
                    setAutofilledIfValuesIs(view, autofillValue, z);
                    size2++;
                }
            }
            if (arrayList != null) {
                if (Helper.sVerbose) {
                    Log.v(TAG, "autofill(): total failed views: " + arrayList);
                }
                try {
                    this.mService.setAutofillFailure(this.mSessionId, arrayList, this.mContext.getUserId());
                } catch (RemoteException e) {
                    throw e.rethrowFromSystemServer();
                }
            }
            if (arrayMap != null) {
                for (int i3 = 0; i3 < arrayMap.size(); i3++) {
                    View view2 = (View) arrayMap.keyAt(i3);
                    SparseArray<AutofillValue> sparseArray2 = (SparseArray) arrayMap.valueAt(i3);
                    view2.autofill(sparseArray2);
                    size2 += sparseArray2.size();
                }
            }
            this.mMetricsLogger.write(newLog(913).addTaggedData(914, Integer.valueOf(size)).addTaggedData(915, Integer.valueOf(size2)));
        }
    }

    private LogMaker newLog(int i) {
        LogMaker logMakerAddTaggedData = new LogMaker(i).addTaggedData(1456, Integer.valueOf(this.mSessionId));
        if (isCompatibilityModeEnabledLocked()) {
            logMakerAddTaggedData.addTaggedData(1414, 1);
        }
        AutofillClient client = getClient();
        if (client == null) {
            logMakerAddTaggedData.setPackageName(this.mContext.getPackageName());
        } else {
            logMakerAddTaggedData.setComponentName(client.autofillClientGetComponentName());
        }
        return logMakerAddTaggedData;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrackedViews(int i, AutofillId[] autofillIdArr, boolean z, boolean z2, AutofillId[] autofillIdArr2, AutofillId autofillId) {
        if (autofillId != null) {
            autofillId.resetSessionId();
        }
        synchronized (this.mLock) {
            if (Helper.sVerbose) {
                Log.v(TAG, "setTrackedViews(): sessionId=" + i + ", trackedIds=" + Arrays.toString(autofillIdArr) + ", saveOnAllViewsInvisible=" + z + ", saveOnFinish=" + z2 + ", fillableIds=" + Arrays.toString(autofillIdArr2) + ", saveTrigerId=" + autofillId + ", mFillableIds=" + this.mFillableIds + ", mEnabled=" + this.mEnabled + ", mSessionId=" + this.mSessionId);
            }
            if (this.mEnabled && this.mSessionId == i) {
                if (z) {
                    this.mTrackedViews = new TrackedViews(autofillIdArr);
                } else {
                    this.mTrackedViews = null;
                }
                this.mSaveOnFinish = z2;
                if (autofillIdArr2 != null) {
                    if (this.mFillableIds == null) {
                        this.mFillableIds = new ArraySet<>(autofillIdArr2.length);
                    }
                    for (AutofillId autofillId2 : autofillIdArr2) {
                        autofillId2.resetSessionId();
                        this.mFillableIds.add(autofillId2);
                    }
                }
                if (this.mSaveTriggerId != null && !this.mSaveTriggerId.equals(autofillId)) {
                    setNotifyOnClickLocked(this.mSaveTriggerId, false);
                }
                if (autofillId != null && !autofillId.equals(this.mSaveTriggerId)) {
                    this.mSaveTriggerId = autofillId;
                    setNotifyOnClickLocked(autofillId, true);
                }
            }
        }
    }

    private void setNotifyOnClickLocked(AutofillId autofillId, boolean z) {
        View viewFindView = findView(autofillId);
        if (viewFindView == null) {
            Log.w(TAG, "setNotifyOnClick(): invalid id: " + autofillId);
            return;
        }
        viewFindView.setNotifyAutofillManagerOnClick(z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSaveUiState(int i, boolean z) {
        if (Helper.sDebug) {
            Log.d(TAG, "setSaveUiState(" + i + "): " + z);
        }
        synchronized (this.mLock) {
            if (this.mSessionId != Integer.MAX_VALUE) {
                Log.w(TAG, "setSaveUiState(" + i + ", " + z + ") called on existing session " + this.mSessionId + "; cancelling it");
                cancelSessionLocked();
            }
            if (z) {
                this.mSessionId = i;
                this.mState = 3;
            } else {
                this.mSessionId = Integer.MAX_VALUE;
                this.mState = 0;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSessionFinished(int i, List<AutofillId> list) {
        if (list != null) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                list.get(i2).resetSessionId();
            }
        }
        synchronized (this.mLock) {
            if (Helper.sVerbose) {
                Log.v(TAG, "setSessionFinished(): from " + getStateAsStringLocked() + " to " + getStateAsString(i) + "; autofillableIds=" + list);
            }
            if (list != null) {
                this.mEnteredIds = new ArraySet<>(list);
            }
            if (i == 5 || i == 6) {
                resetSessionLocked(true);
                this.mState = 0;
            } else {
                resetSessionLocked(false);
                this.mState = i;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAugmentedAutofillClient(IResultReceiver iResultReceiver) {
        synchronized (this.mLock) {
            if (this.mAugmentedAutofillServiceClient == null) {
                this.mAugmentedAutofillServiceClient = new AugmentedAutofillManagerClient();
            }
            Bundle bundle = new Bundle();
            bundle.putBinder(EXTRA_AUGMENTED_AUTOFILL_CLIENT, this.mAugmentedAutofillServiceClient.asBinder());
            try {
                iResultReceiver.send(0, bundle);
            } catch (RemoteException e) {
                Log.w(TAG, "Could not send AugmentedAutofillClient back: " + e);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestShowSoftInput(AutofillId autofillId) {
        if (Helper.sVerbose) {
            Log.v(TAG, "requestShowSoftInput(" + autofillId + ")");
        }
        AutofillClient client = getClient();
        if (client == null) {
            return;
        }
        final View viewAutofillClientFindViewByAutofillIdTraversal = client.autofillClientFindViewByAutofillIdTraversal(autofillId);
        if (viewAutofillClientFindViewByAutofillIdTraversal == null) {
            if (Helper.sVerbose) {
                Log.v(TAG, "View is not found");
                return;
            }
            return;
        }
        Handler handler = viewAutofillClientFindViewByAutofillIdTraversal.getHandler();
        if (handler == null) {
            if (Helper.sVerbose) {
                Log.v(TAG, "Ignoring requestShowSoftInput due to no handler in view");
            }
        } else {
            if (handler.getLooper() != Looper.myLooper()) {
                if (Helper.sVerbose) {
                    Log.v(TAG, "Scheduling showSoftInput() on the view UI thread");
                }
                handler.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$yUWPXQETqEjLvLaUNJn6ewweoC8
                    @Override // java.lang.Runnable
                    public final void run() {
                        AutofillManager.requestShowSoftInputInViewThread(viewAutofillClientFindViewByAutofillIdTraversal);
                    }
                });
                return;
            }
            requestShowSoftInputInViewThread(viewAutofillClientFindViewByAutofillIdTraversal);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void requestShowSoftInputInViewThread(View view) {
        if (!view.isFocused()) {
            Log.w(TAG, "Ignoring requestShowSoftInput() due to non-focused view");
            return;
        }
        boolean zShowSoftInput = ((InputMethodManager) view.getContext().getSystemService(InputMethodManager.class)).showSoftInput(view, 0);
        if (Helper.sVerbose) {
            Log.v(TAG, " InputMethodManager.showSoftInput returns " + zShowSoftInput);
        }
    }

    public void requestHideFillUi() {
        requestHideFillUi(this.mIdShownFillUi, true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestHideFillUi(AutofillId autofillId, boolean z) {
        AutofillClient client;
        View viewFindView = autofillId == null ? null : findView(autofillId);
        if (Helper.sVerbose) {
            Log.v(TAG, "requestHideFillUi(" + autofillId + "): anchor = " + viewFindView);
        }
        if (viewFindView != null) {
            requestHideFillUi(autofillId, viewFindView);
        } else {
            if (!z || (client = getClient()) == null) {
                return;
            }
            client.autofillClientRequestHideFillUi();
        }
    }

    private void requestHideFillUi(AutofillId autofillId, View view) {
        AutofillCallback autofillCallback;
        synchronized (this.mLock) {
            AutofillClient client = getClient();
            autofillCallback = null;
            if (client != null && client.autofillClientRequestHideFillUi()) {
                this.mIdShownFillUi = null;
                autofillCallback = this.mCallback;
            }
        }
        if (autofillCallback != null) {
            if (autofillId.isVirtualInt()) {
                autofillCallback.onAutofillEvent(view, autofillId.getVirtualChildIntId(), 2);
            } else {
                autofillCallback.onAutofillEvent(view, 2);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDisableAutofill(long j, ComponentName componentName) {
        synchronized (this.mLock) {
            if (this.mOptions == null) {
                return;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime() + j;
            if (jElapsedRealtime < 0) {
                jElapsedRealtime = Long.MAX_VALUE;
            }
            if (componentName != null) {
                if (this.mOptions.disabledActivities == null) {
                    this.mOptions.disabledActivities = new ArrayMap<>();
                }
                this.mOptions.disabledActivities.put(componentName.flattenToString(), Long.valueOf(jElapsedRealtime));
            } else {
                this.mOptions.appDisabledExpiration = jElapsedRealtime;
            }
        }
    }

    void notifyReenableAutofill() {
        synchronized (this.mLock) {
            if (this.mOptions == null) {
                return;
            }
            this.mOptions.appDisabledExpiration = 0L;
            this.mOptions.disabledActivities = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyNoFillUi(int i, AutofillId autofillId, int i2) {
        if (Helper.sVerbose) {
            Log.v(TAG, "notifyNoFillUi(): sessionFinishedState=" + i2);
        }
        if (findView(autofillId) == null) {
            return;
        }
        notifyCallback(i, autofillId, 3);
        if (i2 != 0) {
            setSessionFinished(i2, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCallback(int i, AutofillId autofillId, int i2) {
        if (Helper.sVerbose) {
            Log.v(TAG, "notifyCallback(): sessionId=" + i + ", autofillId=" + autofillId + ", event=" + i2);
        }
        View viewFindView = findView(autofillId);
        if (viewFindView == null) {
            return;
        }
        AutofillCallback autofillCallback = null;
        synchronized (this.mLock) {
            if (this.mSessionId == i && getClient() != null) {
                autofillCallback = this.mCallback;
            }
        }
        if (autofillCallback != null) {
            if (autofillId.isVirtualInt()) {
                autofillCallback.onAutofillEvent(viewFindView, autofillId.getVirtualChildIntId(), i2);
            } else {
                autofillCallback.onAutofillEvent(viewFindView, i2);
            }
        }
    }

    private View findView(AutofillId autofillId) {
        AutofillClient client = getClient();
        if (client != null) {
            return client.autofillClientFindViewByAutofillIdTraversal(autofillId);
        }
        return null;
    }

    public boolean hasAutofillFeature() {
        return this.mService != null;
    }

    public void onPendingSaveUi(int i, IBinder iBinder) {
        if (Helper.sVerbose) {
            Log.v(TAG, "onPendingSaveUi(" + i + "): " + iBinder);
        }
        synchronized (this.mLock) {
            try {
                this.mService.onPendingSaveUi(i, iBinder);
            } catch (RemoteException e) {
                Log.e(TAG, "Error in onPendingSaveUi: ", e);
            }
        }
    }

    public void dump(String str, PrintWriter printWriter) {
        printWriter.print(str);
        printWriter.println("AutofillManager:");
        String str2 = str + "  ";
        printWriter.print(str2);
        printWriter.print("sessionId: ");
        printWriter.println(this.mSessionId);
        printWriter.print(str2);
        printWriter.print("state: ");
        printWriter.println(getStateAsStringLocked());
        printWriter.print(str2);
        printWriter.print("context: ");
        printWriter.println(this.mContext);
        AutofillClient client = getClient();
        if (client != null) {
            printWriter.print(str2);
            printWriter.print("client: ");
            printWriter.print(client);
            printWriter.print(" (");
            printWriter.print(client.autofillClientGetActivityToken());
            printWriter.println(')');
        }
        printWriter.print(str2);
        printWriter.print("enabled: ");
        printWriter.println(this.mEnabled);
        printWriter.print(str2);
        printWriter.print("enabledAugmentedOnly: ");
        printWriter.println(this.mForAugmentedAutofillOnly);
        printWriter.print(str2);
        printWriter.print("hasService: ");
        printWriter.println(this.mService != null);
        printWriter.print(str2);
        printWriter.print("hasCallback: ");
        printWriter.println(this.mCallback != null);
        printWriter.print(str2);
        printWriter.print("onInvisibleCalled ");
        printWriter.println(this.mOnInvisibleCalled);
        printWriter.print(str2);
        printWriter.print("last autofilled data: ");
        printWriter.println(this.mLastAutofilledData);
        printWriter.print(str2);
        printWriter.print("id of last fill UI shown: ");
        printWriter.println(this.mIdShownFillUi);
        printWriter.print(str2);
        printWriter.print("tracked views: ");
        if (this.mTrackedViews == null) {
            printWriter.println("null");
        } else {
            String str3 = str2 + "  ";
            printWriter.println();
            printWriter.print(str3);
            printWriter.print("visible:");
            printWriter.println(this.mTrackedViews.mVisibleTrackedIds);
            printWriter.print(str3);
            printWriter.print("invisible:");
            printWriter.println(this.mTrackedViews.mInvisibleTrackedIds);
        }
        printWriter.print(str2);
        printWriter.print("fillable ids: ");
        printWriter.println(this.mFillableIds);
        printWriter.print(str2);
        printWriter.print("entered ids: ");
        printWriter.println(this.mEnteredIds);
        if (this.mEnteredForAugmentedAutofillIds != null) {
            printWriter.print(str2);
            printWriter.print("entered ids for augmented autofill: ");
            printWriter.println(this.mEnteredForAugmentedAutofillIds);
        }
        if (this.mForAugmentedAutofillOnly) {
            printWriter.print(str2);
            printWriter.println("For Augmented Autofill Only");
        }
        printWriter.print(str2);
        printWriter.print("save trigger id: ");
        printWriter.println(this.mSaveTriggerId);
        printWriter.print(str2);
        printWriter.print("save on finish(): ");
        printWriter.println(this.mSaveOnFinish);
        if (this.mOptions != null) {
            printWriter.print(str2);
            printWriter.print("options: ");
            this.mOptions.dumpShort(printWriter);
            printWriter.println();
        }
        printWriter.print(str2);
        printWriter.print("compat mode enabled: ");
        synchronized (this.mLock) {
            if (this.mCompatibilityBridge != null) {
                String str4 = str2 + "  ";
                printWriter.println("true");
                printWriter.print(str4);
                printWriter.print("windowId: ");
                printWriter.println(this.mCompatibilityBridge.mFocusedWindowId);
                printWriter.print(str4);
                printWriter.print("nodeId: ");
                printWriter.println(this.mCompatibilityBridge.mFocusedNodeId);
                printWriter.print(str4);
                printWriter.print("virtualId: ");
                printWriter.println(AccessibilityNodeInfo.getVirtualDescendantId(this.mCompatibilityBridge.mFocusedNodeId));
                printWriter.print(str4);
                printWriter.print("focusedBounds: ");
                printWriter.println(this.mCompatibilityBridge.mFocusedBounds);
            } else {
                printWriter.println("false");
            }
        }
        printWriter.print(str2);
        printWriter.print("debug: ");
        printWriter.print(Helper.sDebug);
        printWriter.print(" verbose: ");
        printWriter.println(Helper.sVerbose);
    }

    private String getStateAsStringLocked() {
        return getStateAsString(this.mState);
    }

    private static String getStateAsString(int i) {
        switch (i) {
            case 0:
                return "UNKNOWN";
            case 1:
                return "ACTIVE";
            case 2:
                return "FINISHED";
            case 3:
                return "SHOWING_SAVE_UI";
            case 4:
                return "DISABLED_BY_SERVICE";
            case 5:
                return "UNKNOWN_COMPAT_MODE";
            case 6:
                return "UNKNOWN_FAILED";
            default:
                return "INVALID:" + i;
        }
    }

    public static String getSmartSuggestionModeToString(int i) {
        if (i == 0) {
            return "OFF";
        }
        if (i == 1) {
            return "SYSTEM";
        }
        return "INVALID:" + i;
    }

    private boolean isActiveLocked() {
        return this.mState == 1;
    }

    private boolean isDisabledByServiceLocked() {
        return this.mState == 4;
    }

    private boolean isFinishedLocked() {
        return this.mState == 2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void post(Runnable runnable) {
        AutofillClient client = getClient();
        if (client == null) {
            if (Helper.sVerbose) {
                Log.v(TAG, "ignoring post() because client is null");
                return;
            }
            return;
        }
        client.autofillClientRunOnUiThread(runnable);
    }

    private final class CompatibilityBridge implements AccessibilityManager.AccessibilityPolicy {
        AccessibilityServiceInfo mCompatServiceInfo;
        private final Rect mFocusedBounds = new Rect();
        private final Rect mTempBounds = new Rect();
        private int mFocusedWindowId = -1;
        private long mFocusedNodeId = AccessibilityNodeInfo.UNDEFINED_NODE_ID;

        private boolean isVirtualNode(int i) {
            return (i == -1 || i == Integer.MAX_VALUE) ? false : true;
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityPolicy
        public int getRelevantEventTypes(int i) {
            return i | 8 | 16 | 1 | 2048;
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityPolicy
        public boolean isEnabled(boolean z) {
            return true;
        }

        CompatibilityBridge() {
            AccessibilityManager.getInstance(AutofillManager.this.mContext).setAccessibilityPolicy(this);
        }

        private AccessibilityServiceInfo getCompatServiceInfo() {
            synchronized (AutofillManager.this.mLock) {
                if (this.mCompatServiceInfo != null) {
                    return this.mCompatServiceInfo;
                }
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("android", "com.android.server.autofill.AutofillCompatAccessibilityService"));
                try {
                    AccessibilityServiceInfo accessibilityServiceInfo = new AccessibilityServiceInfo(AutofillManager.this.mContext.getPackageManager().resolveService(intent, 1048704), AutofillManager.this.mContext);
                    this.mCompatServiceInfo = accessibilityServiceInfo;
                    return accessibilityServiceInfo;
                } catch (IOException | XmlPullParserException unused) {
                    Log.e(AutofillManager.TAG, "Cannot find compat autofill service:" + intent);
                    throw new IllegalStateException("Cannot find compat autofill service");
                }
            }
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityPolicy
        public List<AccessibilityServiceInfo> getInstalledAccessibilityServiceList(List<AccessibilityServiceInfo> list) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(getCompatServiceInfo());
            return list;
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityPolicy
        public List<AccessibilityServiceInfo> getEnabledAccessibilityServiceList(int i, List<AccessibilityServiceInfo> list) {
            if (list == null) {
                list = new ArrayList<>();
            }
            list.add(getCompatServiceInfo());
            return list;
        }

        @Override // android.view.accessibility.AccessibilityManager.AccessibilityPolicy
        public AccessibilityEvent onAccessibilityEvent(AccessibilityEvent accessibilityEvent, boolean z, int i) {
            AutofillClient client;
            int eventType = accessibilityEvent.getEventType();
            if (Helper.sVerbose) {
                Log.v(AutofillManager.TAG, "onAccessibilityEvent(" + AccessibilityEvent.eventTypeToString(eventType) + "): virtualId=" + AccessibilityNodeInfo.getVirtualDescendantId(accessibilityEvent.getSourceNodeId()) + ", client=" + AutofillManager.this.getClient());
            }
            if (eventType == 1) {
                synchronized (AutofillManager.this.mLock) {
                    notifyViewClicked(accessibilityEvent.getWindowId(), accessibilityEvent.getSourceNodeId());
                }
            } else if (eventType == 8) {
                synchronized (AutofillManager.this.mLock) {
                    if (this.mFocusedWindowId == accessibilityEvent.getWindowId() && this.mFocusedNodeId == accessibilityEvent.getSourceNodeId()) {
                        return accessibilityEvent;
                    }
                    if (this.mFocusedWindowId != -1 && this.mFocusedNodeId != AccessibilityNodeInfo.UNDEFINED_NODE_ID) {
                        notifyViewExited(this.mFocusedWindowId, this.mFocusedNodeId);
                        this.mFocusedWindowId = -1;
                        this.mFocusedNodeId = AccessibilityNodeInfo.UNDEFINED_NODE_ID;
                        this.mFocusedBounds.set(0, 0, 0, 0);
                    }
                    int windowId = accessibilityEvent.getWindowId();
                    long sourceNodeId = accessibilityEvent.getSourceNodeId();
                    if (notifyViewEntered(windowId, sourceNodeId, this.mFocusedBounds)) {
                        this.mFocusedWindowId = windowId;
                        this.mFocusedNodeId = sourceNodeId;
                    }
                }
            } else if (eventType == 16) {
                synchronized (AutofillManager.this.mLock) {
                    if (this.mFocusedWindowId == accessibilityEvent.getWindowId() && this.mFocusedNodeId == accessibilityEvent.getSourceNodeId()) {
                        notifyValueChanged(accessibilityEvent.getWindowId(), accessibilityEvent.getSourceNodeId());
                    }
                }
            } else if (eventType == 2048 && (client = AutofillManager.this.getClient()) != null) {
                synchronized (AutofillManager.this.mLock) {
                    if (client.autofillClientIsFillUiShowing()) {
                        notifyViewEntered(this.mFocusedWindowId, this.mFocusedNodeId, this.mFocusedBounds);
                    }
                    updateTrackedViewsLocked();
                }
            }
            if (z) {
                return accessibilityEvent;
            }
            return null;
        }

        private boolean notifyViewEntered(int i, long j, Rect rect) {
            View viewFindViewByAccessibilityId;
            AccessibilityNodeInfo accessibilityNodeInfoFindVirtualNodeByAccessibilityId;
            int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(j);
            if (!isVirtualNode(virtualDescendantId) || (viewFindViewByAccessibilityId = findViewByAccessibilityId(i, j)) == null || (accessibilityNodeInfoFindVirtualNodeByAccessibilityId = findVirtualNodeByAccessibilityId(viewFindViewByAccessibilityId, virtualDescendantId)) == null || !accessibilityNodeInfoFindVirtualNodeByAccessibilityId.isEditable()) {
                return false;
            }
            Rect rect2 = this.mTempBounds;
            accessibilityNodeInfoFindVirtualNodeByAccessibilityId.getBoundsInScreen(rect2);
            if (rect2.equals(rect)) {
                return false;
            }
            rect.set(rect2);
            AutofillManager.this.notifyViewEntered(viewFindViewByAccessibilityId, virtualDescendantId, rect2);
            return true;
        }

        private void notifyViewExited(int i, long j) {
            View viewFindViewByAccessibilityId;
            int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(j);
            if (isVirtualNode(virtualDescendantId) && (viewFindViewByAccessibilityId = findViewByAccessibilityId(i, j)) != null) {
                AutofillManager.this.notifyViewExited(viewFindViewByAccessibilityId, virtualDescendantId);
            }
        }

        private void notifyValueChanged(int i, long j) {
            View viewFindViewByAccessibilityId;
            AccessibilityNodeInfo accessibilityNodeInfoFindVirtualNodeByAccessibilityId;
            int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(j);
            if (!isVirtualNode(virtualDescendantId) || (viewFindViewByAccessibilityId = findViewByAccessibilityId(i, j)) == null || (accessibilityNodeInfoFindVirtualNodeByAccessibilityId = findVirtualNodeByAccessibilityId(viewFindViewByAccessibilityId, virtualDescendantId)) == null) {
                return;
            }
            AutofillManager.this.notifyValueChanged(viewFindViewByAccessibilityId, virtualDescendantId, AutofillValue.forText(accessibilityNodeInfoFindVirtualNodeByAccessibilityId.getText()));
        }

        private void notifyViewClicked(int i, long j) {
            View viewFindViewByAccessibilityId;
            int virtualDescendantId = AccessibilityNodeInfo.getVirtualDescendantId(j);
            if (!isVirtualNode(virtualDescendantId) || (viewFindViewByAccessibilityId = findViewByAccessibilityId(i, j)) == null || findVirtualNodeByAccessibilityId(viewFindViewByAccessibilityId, virtualDescendantId) == null) {
                return;
            }
            AutofillManager.this.notifyViewClicked(viewFindViewByAccessibilityId, virtualDescendantId);
        }

        private void updateTrackedViewsLocked() {
            if (AutofillManager.this.mTrackedViews != null) {
                AutofillManager.this.mTrackedViews.onVisibleForAutofillChangedLocked();
            }
        }

        private View findViewByAccessibilityId(int i, long j) {
            AutofillClient client = AutofillManager.this.getClient();
            if (client == null) {
                return null;
            }
            return client.autofillClientFindViewByAccessibilityIdTraversal(AccessibilityNodeInfo.getAccessibilityViewId(j), i);
        }

        private AccessibilityNodeInfo findVirtualNodeByAccessibilityId(View view, int i) {
            AccessibilityNodeProvider accessibilityNodeProvider = view.getAccessibilityNodeProvider();
            if (accessibilityNodeProvider == null) {
                return null;
            }
            return accessibilityNodeProvider.createAccessibilityNodeInfo(i);
        }
    }

    private class TrackedViews {
        private ArraySet<AutofillId> mInvisibleTrackedIds;
        private ArraySet<AutofillId> mVisibleTrackedIds;

        private <T> boolean isInSet(ArraySet<T> arraySet, T t) {
            return arraySet != null && arraySet.contains(t);
        }

        private <T> ArraySet<T> addToSet(ArraySet<T> arraySet, T t) {
            if (arraySet == null) {
                arraySet = new ArraySet<>(1);
            }
            arraySet.add(t);
            return arraySet;
        }

        private <T> ArraySet<T> removeFromSet(ArraySet<T> arraySet, T t) {
            if (arraySet == null) {
                return null;
            }
            arraySet.remove(t);
            if (arraySet.isEmpty()) {
                return null;
            }
            return arraySet;
        }

        TrackedViews(AutofillId[] autofillIdArr) {
            boolean[] zArrAutofillClientGetViewVisibility;
            AutofillClient client = AutofillManager.this.getClient();
            if (!ArrayUtils.isEmpty(autofillIdArr) && client != null) {
                if (client.autofillClientIsVisibleForAutofill()) {
                    if (Helper.sVerbose) {
                        Log.v(AutofillManager.TAG, "client is visible, check tracked ids");
                    }
                    zArrAutofillClientGetViewVisibility = client.autofillClientGetViewVisibility(autofillIdArr);
                } else {
                    zArrAutofillClientGetViewVisibility = new boolean[autofillIdArr.length];
                }
                int length = autofillIdArr.length;
                for (int i = 0; i < length; i++) {
                    AutofillId autofillId = autofillIdArr[i];
                    autofillId.resetSessionId();
                    if (zArrAutofillClientGetViewVisibility[i]) {
                        this.mVisibleTrackedIds = addToSet(this.mVisibleTrackedIds, autofillId);
                    } else {
                        this.mInvisibleTrackedIds = addToSet(this.mInvisibleTrackedIds, autofillId);
                    }
                }
            }
            if (Helper.sVerbose) {
                Log.v(AutofillManager.TAG, "TrackedViews(trackedIds=" + Arrays.toString(autofillIdArr) + "):  mVisibleTrackedIds=" + this.mVisibleTrackedIds + " mInvisibleTrackedIds=" + this.mInvisibleTrackedIds);
            }
            if (this.mVisibleTrackedIds == null) {
                AutofillManager.this.finishSessionLocked();
            }
        }

        void notifyViewVisibilityChangedLocked(AutofillId autofillId, boolean z) {
            if (Helper.sDebug) {
                Log.d(AutofillManager.TAG, "notifyViewVisibilityChangedLocked(): id=" + autofillId + " isVisible=" + z);
            }
            if (AutofillManager.this.isClientVisibleForAutofillLocked()) {
                if (z) {
                    if (isInSet(this.mInvisibleTrackedIds, autofillId)) {
                        this.mInvisibleTrackedIds = removeFromSet(this.mInvisibleTrackedIds, autofillId);
                        this.mVisibleTrackedIds = addToSet(this.mVisibleTrackedIds, autofillId);
                    }
                } else if (isInSet(this.mVisibleTrackedIds, autofillId)) {
                    this.mVisibleTrackedIds = removeFromSet(this.mVisibleTrackedIds, autofillId);
                    this.mInvisibleTrackedIds = addToSet(this.mInvisibleTrackedIds, autofillId);
                }
            }
            if (this.mVisibleTrackedIds == null) {
                if (Helper.sVerbose) {
                    Log.v(AutofillManager.TAG, "No more visible ids. Invisibile = " + this.mInvisibleTrackedIds);
                }
                AutofillManager.this.finishSessionLocked();
            }
        }

        void onVisibleForAutofillChangedLocked() {
            ArraySet<AutofillId> arraySetAddToSet;
            AutofillClient client = AutofillManager.this.getClient();
            if (client != null) {
                if (Helper.sVerbose) {
                    Log.v(AutofillManager.TAG, "onVisibleForAutofillChangedLocked(): inv= " + this.mInvisibleTrackedIds + " vis=" + this.mVisibleTrackedIds);
                }
                ArraySet<AutofillId> arraySetAddToSet2 = null;
                if (this.mInvisibleTrackedIds != null) {
                    ArrayList arrayList = new ArrayList(this.mInvisibleTrackedIds);
                    boolean[] zArrAutofillClientGetViewVisibility = client.autofillClientGetViewVisibility(Helper.toArray(arrayList));
                    int size = arrayList.size();
                    arraySetAddToSet = null;
                    for (int i = 0; i < size; i++) {
                        AutofillId autofillId = (AutofillId) arrayList.get(i);
                        if (zArrAutofillClientGetViewVisibility[i]) {
                            arraySetAddToSet2 = addToSet(arraySetAddToSet2, autofillId);
                            if (Helper.sDebug) {
                                Log.d(AutofillManager.TAG, "onVisibleForAutofill() " + autofillId + " became visible");
                            }
                        } else {
                            arraySetAddToSet = addToSet(arraySetAddToSet, autofillId);
                        }
                    }
                } else {
                    arraySetAddToSet = null;
                }
                if (this.mVisibleTrackedIds != null) {
                    ArrayList arrayList2 = new ArrayList(this.mVisibleTrackedIds);
                    boolean[] zArrAutofillClientGetViewVisibility2 = client.autofillClientGetViewVisibility(Helper.toArray(arrayList2));
                    int size2 = arrayList2.size();
                    for (int i2 = 0; i2 < size2; i2++) {
                        AutofillId autofillId2 = (AutofillId) arrayList2.get(i2);
                        if (zArrAutofillClientGetViewVisibility2[i2]) {
                            arraySetAddToSet2 = addToSet(arraySetAddToSet2, autofillId2);
                        } else {
                            arraySetAddToSet = addToSet(arraySetAddToSet, autofillId2);
                            if (Helper.sDebug) {
                                Log.d(AutofillManager.TAG, "onVisibleForAutofill() " + autofillId2 + " became invisible");
                            }
                        }
                    }
                }
                this.mInvisibleTrackedIds = arraySetAddToSet;
                this.mVisibleTrackedIds = arraySetAddToSet2;
            }
            if (this.mVisibleTrackedIds == null) {
                if (Helper.sVerbose) {
                    Log.v(AutofillManager.TAG, "onVisibleForAutofillChangedLocked(): no more visible ids");
                }
                AutofillManager.this.finishSessionLocked();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class AutofillManagerClient extends IAutoFillManagerClient.Stub {
        private final WeakReference<AutofillManager> mAfm;

        private AutofillManagerClient(AutofillManager autofillManager) {
            this.mAfm = new WeakReference<>(autofillManager);
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setState(final int i) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$qH36EJk2Hkdja9ZZmTxqYPyr0YA
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.setState(i);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void autofill(final int i, final List<AutofillId> list, final List<AutofillValue> list2, final boolean z) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$M5cIHWp4VjSrFSjWPhPkW66WcgE
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.autofill(i, list, list2, z);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void authenticate(final int i, final int i2, final IntentSender intentSender, final Intent intent, final boolean z) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$24XrRrXer3uVV1bTpl9qUIIHmJM
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.authenticate(i, i2, intentSender, intent, z);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void requestShowFillUi(final int i, final AutofillId autofillId, final int i2, final int i3, final Rect rect, final IAutofillWindowPresenter iAutofillWindowPresenter) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$kRL9XILLc2XNr90gxVDACLzcyqc
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.requestShowFillUi(i, autofillId, i2, i3, rect, iAutofillWindowPresenter);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void requestHideFillUi(int i, final AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$dCTetwfU0gT1ZrSzZGZiGStXlOY
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.requestHideFillUi(autofillId, false);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyNoFillUi(final int i, final AutofillId autofillId, final int i2) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$K79QnIPRaZuikYDQdsLcIUBhqiI
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.notifyNoFillUi(i, autofillId, i2);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyFillUiShown(final int i, final AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$C_N0TMarq3R8L6VkaEm7H_n1T7k
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.notifyCallback(i, autofillId, 1);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyFillUiHidden(final int i, final AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$tXABDazS-gnl4cSTqRq7xAnrZwo
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.notifyCallback(i, autofillId, 2);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void notifyDisableAutofill(final long j, final ComponentName componentName) throws RemoteException {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$nGHoEL-C5tEDrLAbg_3JSwDnikk
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.notifyDisableAutofill(j, componentName);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void dispatchUnhandledKey(final int i, final AutofillId autofillId, final KeyEvent keyEvent) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$diRhCbPquG9lbMilczZW3FIsRVQ
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.dispatchUnhandledKey(i, autofillId, keyEvent);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void startIntentSender(final IntentSender intentSender, final Intent intent) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$5gqq5d05q4z8TAod0qB3s7DbGQ0
                    @Override // java.lang.Runnable
                    public final void run() {
                        AutofillManager.AutofillManagerClient.lambda$startIntentSender$10(autofillManager, intentSender, intent);
                    }
                });
            }
        }

        static /* synthetic */ void lambda$startIntentSender$10(AutofillManager autofillManager, IntentSender intentSender, Intent intent) {
            try {
                autofillManager.mContext.startIntentSender(intentSender, intent, 0, 0, 0);
            } catch (IntentSender.SendIntentException e) {
                Log.e(AutofillManager.TAG, "startIntentSender() failed for intent:" + intentSender, e);
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setTrackedViews(final int i, final AutofillId[] autofillIdArr, final boolean z, final boolean z2, final AutofillId[] autofillIdArr2, final AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$h3jwxJ8O0NJVjgcEdK-Tct9rSeg
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.setTrackedViews(i, autofillIdArr, z, z2, autofillIdArr2, autofillId);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setSaveUiState(final int i, final boolean z) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$APk49Wn0CZsFOdF9dTuuaJaRhy8
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.setSaveUiState(i, z);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void setSessionFinished(final int i, final List<AutofillId> list) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$6YlLUns4Jn3OXEyrRSzxW7pT0cE
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.setSessionFinished(i, list);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void getAugmentedAutofillClient(final IResultReceiver iResultReceiver) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$hCkZWIIgnjguHBxIc5d7zi3IzQY
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.getAugmentedAutofillClient(iResultReceiver);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAutoFillManagerClient
        public void requestShowSoftInput(final AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AutofillManagerClient$5AR3XMrrRE8rxyNGRepInd-vCsE
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.requestShowSoftInput(autofillId);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static final class AugmentedAutofillManagerClient extends IAugmentedAutofillManagerClient.Stub {
        private final WeakReference<AutofillManager> mAfm;

        private AugmentedAutofillManagerClient(AutofillManager autofillManager) {
            this.mAfm = new WeakReference<>(autofillManager);
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public Rect getViewCoordinates(AutofillId autofillId) {
            View view;
            AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager == null || (view = getView(autofillManager, autofillId)) == null) {
                return null;
            }
            Rect rect = new Rect();
            view.getWindowVisibleDisplayFrame(rect);
            int[] iArr = new int[2];
            view.getLocationOnScreen(iArr);
            Rect rect2 = new Rect(iArr[0], iArr[1] - rect.top, iArr[0] + view.getWidth(), (iArr[1] - rect.top) + view.getHeight());
            if (Helper.sVerbose) {
                Log.v(AutofillManager.TAG, "Coordinates for " + autofillId + ": " + rect2);
            }
            return rect2;
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void autofill(final int i, final List<AutofillId> list, final List<AutofillValue> list2, final boolean z) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AugmentedAutofillManagerClient$4xiNVTyrzh8FT7hUgeGwxhOR4TY
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.autofill(i, list, list2, z);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void requestShowFillUi(final int i, final AutofillId autofillId, final int i2, final int i3, final Rect rect, final IAutofillWindowPresenter iAutofillWindowPresenter) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AugmentedAutofillManagerClient$OrAY5q15e0VwuCSYnsGgs6GcY1U
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.requestShowFillUi(i, autofillId, i2, i3, rect, iAutofillWindowPresenter);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public void requestHideFillUi(int i, final AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager != null) {
                autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AugmentedAutofillManagerClient$tbNtqpHgXnRdc3JO5HaBlxclFg0
                    @Override // java.lang.Runnable
                    public final void run() {
                        autofillManager.requestHideFillUi(autofillId, false);
                    }
                });
            }
        }

        @Override // android.view.autofill.IAugmentedAutofillManagerClient
        public boolean requestAutofill(int i, AutofillId autofillId) {
            final AutofillManager autofillManager = this.mAfm.get();
            if (autofillManager == null || autofillManager.mSessionId != i) {
                if (Helper.sDebug) {
                    Slog.d(AutofillManager.TAG, "Autofill not available or sessionId doesn't match");
                }
                return false;
            }
            final View view = getView(autofillManager, autofillId);
            if (view == null || !view.isFocused()) {
                if (Helper.sDebug) {
                    Slog.d(AutofillManager.TAG, "View not available or is not on focus");
                }
                return false;
            }
            if (Helper.sVerbose) {
                Log.v(AutofillManager.TAG, "requestAutofill() by AugmentedAutofillService.");
            }
            autofillManager.post(new Runnable() { // from class: android.view.autofill.-$$Lambda$AutofillManager$AugmentedAutofillManagerClient$IV7bcpTcFDYfdRsdqPS_5PQAkX0
                @Override // java.lang.Runnable
                public final void run() {
                    autofillManager.requestAutofillFromNewSession(view);
                }
            });
            return true;
        }

        private View getView(AutofillManager autofillManager, AutofillId autofillId) {
            AutofillClient client = autofillManager.getClient();
            if (client == null) {
                Log.w(AutofillManager.TAG, "getView(" + autofillId + "): no autofill client");
                return null;
            }
            View viewAutofillClientFindViewByAutofillIdTraversal = client.autofillClientFindViewByAutofillIdTraversal(autofillId);
            if (viewAutofillClientFindViewByAutofillIdTraversal == null) {
                Log.w(AutofillManager.TAG, "getView(" + autofillId + "): could not find view");
            }
            return viewAutofillClientFindViewByAutofillIdTraversal;
        }
    }
}
