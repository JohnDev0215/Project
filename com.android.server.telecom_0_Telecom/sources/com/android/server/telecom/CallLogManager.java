package com.android.server.telecom;

import android.content.Context;
import android.content.Intent;
import android.location.Country;
import android.location.CountryDetector;
import android.location.CountryListener;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.provider.CallLog;
import android.telecom.CallerInfo;
import android.telecom.DisconnectCause;
import android.telecom.Log;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.VideoProfile;
import android.telephony.CarrierConfigManager;
import android.telephony.PhoneNumberUtils;
import com.android.internal.annotations.VisibleForTesting;
import com.android.server.telecom.MissedCallNotifier;
import com.android.server.telecom.callfiltering.CallFilteringResult;
import java.util.Arrays;
import java.util.Locale;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Stream;

/* JADX INFO: loaded from: classes.dex */
@VisibleForTesting
public final class CallLogManager extends CallsManagerListenerBase {
    private static final String TAG = "CallLogManager";
    private final CarrierConfigManager mCarrierConfigManager;
    private final Context mContext;
    private String mCurrentCountryIso;
    private Object mLock = new Object();
    private final MissedCallNotifier mMissedCallNotifier;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;

    public interface LogCallCompletedListener {
        void onLogCompleted(Uri uri);
    }

    private static class AddCallArgs {
        public final PhoneAccountHandle accountHandle;
        public final int callBockReason;
        public final CharSequence callScreeningAppName;
        public final String callScreeningComponentName;
        public final int callType;
        public final CallerInfo callerInfo;
        public final Context context;
        public final Long dataUsage;
        public final int durationInSec;
        public final int features;
        public final UserHandle initiatingUser;
        public final boolean isRead;
        public final LogCallCompletedListener logCallCompletedListener;
        public final String number;
        public final String postDialDigits;
        public final int presentation;
        public final long timestamp;
        public final String viaNumber;

        public AddCallArgs(Context context, CallerInfo callerInfo, String str, String str2, String str3, int i, int i2, int i3, PhoneAccountHandle phoneAccountHandle, long j, long j2, Long l, UserHandle userHandle, boolean z, LogCallCompletedListener logCallCompletedListener, int i4, CharSequence charSequence, String str4) {
            this.context = context;
            this.callerInfo = callerInfo;
            this.number = str;
            this.postDialDigits = str2;
            this.viaNumber = str3;
            this.presentation = i;
            this.callType = i2;
            this.features = i3;
            this.accountHandle = phoneAccountHandle;
            this.timestamp = j;
            this.durationInSec = (int) (j2 / 1000);
            this.dataUsage = l;
            this.initiatingUser = userHandle;
            this.isRead = z;
            this.logCallCompletedListener = logCallCompletedListener;
            this.callBockReason = i4;
            this.callScreeningAppName = charSequence;
            this.callScreeningComponentName = str4;
        }
    }

    public CallLogManager(Context context, PhoneAccountRegistrar phoneAccountRegistrar, MissedCallNotifier missedCallNotifier) {
        this.mContext = context;
        this.mCarrierConfigManager = (CarrierConfigManager) context.getSystemService("carrier_config");
        this.mPhoneAccountRegistrar = phoneAccountRegistrar;
        this.mMissedCallNotifier = missedCallNotifier;
    }

    @Override // com.android.server.telecom.CallsManagerListenerBase, com.android.server.telecom.CallsManager.CallsManagerListener
    public void onCallStateChanged(Call call, int i, int i2) {
        int code = call.getDisconnectCause().getCode();
        boolean z = false;
        int i3 = 7;
        boolean z2 = i2 == 7 || i2 == 8;
        if (z2 && code == 4) {
            z = true;
        }
        if (z2 && shouldLogDisconnectedCall(call, i, z)) {
            if (!call.isIncoming()) {
                i3 = 2;
            } else if (code == 5) {
                i3 = 3;
            } else if (code != 11) {
                i3 = code == 6 ? 5 : 1;
            }
            logCall(call, i3, !call.isSelfManaged(), (CallFilteringResult) null);
        }
    }

    @VisibleForTesting
    public boolean shouldLogDisconnectedCall(Call call, int i, boolean z) {
        int subscriptionIdForPhoneAccount;
        PersistableBundle configForSubId;
        boolean z2 = call.isLoggedSelfManaged() && (call.getHandoverState() == 1 || call.getHandoverState() == 5);
        if (i == 2) {
            return false;
        }
        if (call.isConference() && call.hadChildren() && !call.hasProperty(2048)) {
            return false;
        }
        if (call.isConference() && !call.hadChildren() && !call.hasProperty(2048)) {
            return false;
        }
        if (call.getParentCall() != null && call.hasProperty(2048)) {
            return false;
        }
        DisconnectCause disconnectCause = call.getDisconnectCause();
        if (z) {
            return (disconnectCause == null || !"EMULATING_SINGLE_CALL".equals(disconnectCause.getReason())) && (call.getConnectionCapabilities() & 8192) == 8192;
        }
        if (call.isExternalCall()) {
            return false;
        }
        if (disconnectCause != null && "IMS_MERGED_SUCCESSFULLY".equals(disconnectCause.getReason()) && ((subscriptionIdForPhoneAccount = this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(call.getTargetPhoneAccount())) == -1 || (configForSubId = this.mCarrierConfigManager.getConfigForSubId(subscriptionIdForPhoneAccount)) == null || configForSubId.getBoolean("support_ims_conference_event_package_bool", true))) {
            return false;
        }
        return !call.isSelfManaged() || z2;
    }

    void logCall(final Call call, int i, boolean z, CallFilteringResult callFilteringResult) {
        if ((i == 3 || i == 6) && z) {
            logCall(call, i, new LogCallCompletedListener() { // from class: com.android.server.telecom.CallLogManager.1
                @Override // com.android.server.telecom.CallLogManager.LogCallCompletedListener
                public void onLogCompleted(Uri uri) {
                    CallLogManager.this.mMissedCallNotifier.showMissedCallNotification(new MissedCallNotifier.CallInfo(call));
                }
            }, callFilteringResult);
        } else {
            logCall(call, i, (LogCallCompletedListener) null, callFilteringResult);
        }
    }

    void logCall(Call call, int i, LogCallCompletedListener logCallCompletedListener, CallFilteringResult callFilteringResult) {
        long creationTimeMillis;
        if (call.getConnectTimeMillis() != 0 && call.getConnectTimeMillis() < call.getCreationTimeMillis()) {
            creationTimeMillis = call.getConnectTimeMillis();
        } else {
            creationTimeMillis = call.getCreationTimeMillis();
        }
        long j = creationTimeMillis;
        long ageMillis = call.getAgeMillis();
        String logNumber = getLogNumber(call);
        Log.d(TAG, "logNumber set to: %s", new Object[]{Log.pii(logNumber)});
        PhoneAccountHandle accountHandle = TelephonyUtil.getDefaultEmergencyPhoneAccount().getAccountHandle();
        String number = PhoneNumberUtils.formatNumber(call.getViaNumber(), getCountryIso());
        if (number == null) {
            number = call.getViaNumber();
        }
        String str = number;
        PhoneAccountHandle targetPhoneAccount = call.getTargetPhoneAccount();
        PhoneAccountHandle phoneAccountHandle = accountHandle.equals(targetPhoneAccount) ? null : targetPhoneAccount;
        Long lValueOf = call.getCallDataUsage() == -1 ? null : Long.valueOf(call.getCallDataUsage());
        int callFeatures = getCallFeatures(call.getVideoStateHistory(), call.getDisconnectCause().getCode() == 12, call.wasHighDefAudio(), call.wasWifi(), (call.getConnectionProperties() & 512) == 512, call.wasEverRttCall(), call.wasVolte());
        if (i == 6) {
            logCall(call.getCallerInfo(), logNumber, call.getPostDialDigits(), str, call.getHandlePresentation(), i, callFeatures, phoneAccountHandle, j, ageMillis, lValueOf, call.isEmergencyCall(), call.getInitiatingUser(), call.isSelfManaged(), logCallCompletedListener, callFilteringResult.mCallBlockReason, callFilteringResult.mCallScreeningAppName, callFilteringResult.mCallScreeningComponentName);
        } else {
            logCall(call.getCallerInfo(), logNumber, call.getPostDialDigits(), str, call.getHandlePresentation(), i, callFeatures, phoneAccountHandle, j, ageMillis, lValueOf, call.isEmergencyCall(), call.getInitiatingUser(), call.isSelfManaged(), logCallCompletedListener, 0, null, null);
        }
    }

    private void logCall(CallerInfo callerInfo, String str, String str2, String str3, int i, int i2, int i3, PhoneAccountHandle phoneAccountHandle, long j, long j2, Long l, boolean z, UserHandle userHandle, boolean z2, LogCallCompletedListener logCallCompletedListener, int i4, CharSequence charSequence, String str4) {
        PersistableBundle configForSubId = ((CarrierConfigManager) this.mContext.getSystemService("carrier_config")).getConfigForSubId(this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle));
        boolean z3 = (!z || (configForSubId != null ? configForSubId.getBoolean("allow_emergency_numbers_in_call_log_bool") : false)) && !isUnloggableNumber(str, configForSubId);
        sendAddCallBroadcast(i2, j2);
        if (z3) {
            Log.d(TAG, "Logging Call log entry: " + callerInfo + ", " + Log.pii(str) + "," + i + ", " + i2 + ", " + j + ", " + j2, new Object[0]);
            logCallAsync(new AddCallArgs(this.mContext, callerInfo, str, str2, str3, i, i2, i3, phoneAccountHandle, j, j2, l, userHandle, z2, logCallCompletedListener, i4, charSequence, str4));
            return;
        }
        Log.d(TAG, "Not adding emergency call to call log.", new Object[0]);
    }

    private boolean isUnloggableNumber(String str, PersistableBundle persistableBundle) {
        final String strNormalizeNumber = PhoneNumberUtils.normalizeNumber(str);
        String[] stringArray = persistableBundle == null ? null : persistableBundle.getStringArray("unloggable_numbers_string_array");
        String[] stringArray2 = this.mContext.getResources().getStringArray(android.R.array.config_oemUsbModeOverride);
        return Stream.concat(stringArray == null ? Stream.empty() : Arrays.stream(stringArray), stringArray2 == null ? Stream.empty() : Arrays.stream(stringArray2)).anyMatch(new Predicate() { // from class: com.android.server.telecom.-$$Lambda$CallLogManager$lVK9xiwv_lxy1SnWjFDD1dDThyo
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return Objects.equals((String) obj, strNormalizeNumber);
            }
        });
    }

    private static int getCallFeatures(int i, boolean z, boolean z2, boolean z3, boolean z4, boolean z5, boolean z6) {
        int i2 = VideoProfile.isVideo(i) ? 1 : 0;
        if (z) {
            i2 |= 2;
        }
        if (z2) {
            i2 |= 4;
        }
        if (z3) {
            i2 |= 8;
        }
        if (z4) {
            i2 |= 16;
        }
        if (z5) {
            i2 |= 32;
        }
        return z6 ? i2 | 64 : i2;
    }

    private String getLogNumber(Call call) {
        Uri originalHandle = call.getOriginalHandle();
        if (originalHandle == null) {
            return null;
        }
        String schemeSpecificPart = originalHandle.getSchemeSpecificPart();
        return !PhoneNumberUtils.isUriNumber(schemeSpecificPart) ? PhoneNumberUtils.stripSeparators(schemeSpecificPart) : schemeSpecificPart;
    }

    public AsyncTask<AddCallArgs, Void, Uri[]> logCallAsync(AddCallArgs addCallArgs) {
        return new LogCallAsyncTask().execute(addCallArgs);
    }

    private class LogCallAsyncTask extends AsyncTask<AddCallArgs, Void, Uri[]> {
        private LogCallCompletedListener[] mListeners;

        private LogCallAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public Uri[] doInBackground(AddCallArgs... addCallArgsArr) {
            int length = addCallArgsArr.length;
            Uri[] uriArr = new Uri[length];
            this.mListeners = new LogCallCompletedListener[length];
            for (int i = 0; i < length; i++) {
                AddCallArgs addCallArgs = addCallArgsArr[i];
                this.mListeners[i] = addCallArgs.logCallCompletedListener;
                try {
                    uriArr[i] = addCall(addCallArgs);
                } catch (Exception e) {
                    Log.e(CallLogManager.TAG, e, "Exception raised during adding CallLog entry.", new Object[0]);
                    uriArr[i] = null;
                }
            }
            return uriArr;
        }

        private Uri addCall(AddCallArgs addCallArgs) {
            PhoneAccount phoneAccountUnchecked = CallLogManager.this.mPhoneAccountRegistrar.getPhoneAccountUnchecked(addCallArgs.accountHandle);
            if (phoneAccountUnchecked != null && phoneAccountUnchecked.hasCapabilities(32)) {
                if (addCallArgs.initiatingUser != null && UserUtil.isManagedProfile(CallLogManager.this.mContext, addCallArgs.initiatingUser)) {
                    return addCall(addCallArgs, addCallArgs.initiatingUser);
                }
                return addCall(addCallArgs, null);
            }
            PhoneAccountHandle phoneAccountHandle = addCallArgs.accountHandle;
            return addCall(addCallArgs, phoneAccountHandle != null ? phoneAccountHandle.getUserHandle() : null);
        }

        private Uri addCall(AddCallArgs addCallArgs, UserHandle userHandle) {
            return CallLog.Calls.addCall(addCallArgs.callerInfo, addCallArgs.context, addCallArgs.number, addCallArgs.postDialDigits, addCallArgs.viaNumber, addCallArgs.presentation, addCallArgs.callType, addCallArgs.features, addCallArgs.accountHandle, addCallArgs.timestamp, addCallArgs.durationInSec, addCallArgs.dataUsage, userHandle == null, userHandle, addCallArgs.isRead, addCallArgs.callBockReason, addCallArgs.callScreeningAppName, addCallArgs.callScreeningComponentName);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Uri[] uriArr) {
            for (int i = 0; i < uriArr.length; i++) {
                Uri uri = uriArr[i];
                if (uri == null) {
                    Log.w(CallLogManager.TAG, "Failed to write call to the log.", new Object[0]);
                }
                LogCallCompletedListener[] logCallCompletedListenerArr = this.mListeners;
                if (logCallCompletedListenerArr[i] != null) {
                    logCallCompletedListenerArr[i].onLogCompleted(uri);
                }
            }
        }
    }

    private void sendAddCallBroadcast(int i, long j) {
        Intent intent = new Intent("com.android.server.telecom.intent.action.CALLS_ADD_ENTRY");
        intent.putExtra("callType", i);
        intent.putExtra("duration", j);
        this.mContext.sendBroadcast(intent, "android.permission.PROCESS_CALLLOG_INFO");
    }

    private String getCountryIsoFromCountry(Country country) {
        if (country == null) {
            Log.w(TAG, "Value for country was null. Falling back to Locale.", new Object[0]);
            return Locale.getDefault().getCountry();
        }
        return country.getCountryIso();
    }

    public String getCountryIso() {
        String str;
        synchronized (this.mLock) {
            if (this.mCurrentCountryIso == null) {
                Log.i(TAG, "Country cache is null. Detecting Country and Setting Cache...", new Object[0]);
                CountryDetector countryDetector = (CountryDetector) this.mContext.getSystemService("country_detector");
                Country countryDetectCountry = null;
                if (countryDetector != null) {
                    countryDetectCountry = countryDetector.detectCountry();
                    countryDetector.addCountryListener(new CountryListener() { // from class: com.android.server.telecom.-$$Lambda$CallLogManager$tZVxOXiDALD7LuYlzVni-kYnrHA
                        public final void onCountryDetected(Country country) {
                            this.f$0.lambda$getCountryIso$1$CallLogManager(country);
                        }
                    }, Looper.getMainLooper());
                }
                this.mCurrentCountryIso = getCountryIsoFromCountry(countryDetectCountry);
            }
            str = this.mCurrentCountryIso;
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$getCountryIso$1, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$getCountryIso$1$CallLogManager(Country country) {
        Log.startSession("CLM.oCD");
        try {
            synchronized (this.mLock) {
                Log.i(TAG, "Country ISO changed. Retrieving new ISO...", new Object[0]);
                this.mCurrentCountryIso = getCountryIsoFromCountry(country);
            }
        } finally {
            Log.endSession();
        }
    }
}
