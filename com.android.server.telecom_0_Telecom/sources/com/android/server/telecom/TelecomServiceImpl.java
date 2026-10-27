package com.android.server.telecom;

import android.app.ActivityManager;
import android.app.AppOpsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Binder;
import android.os.Build;
import android.os.Bundle;
import android.os.UserHandle;
import android.provider.BlockedNumberContract;
import android.telecom.Log;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomAnalytics;
import android.telecom.VideoProfile;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.EventLog;
import com.android.internal.telecom.ITelecomService;
import com.android.internal.util.FunctionalUtils;
import com.android.internal.util.IndentingPrintWriter;
import com.android.server.telecom.CallIntentProcessor;
import com.android.server.telecom.TelecomSystem;
import com.android.server.telecom.components.UserCallIntentProcessor;
import com.android.server.telecom.components.UserCallIntentProcessorFactory;
import com.android.server.telecom.settings.BlockedNumbersActivity;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.function.IntConsumer;

/* JADX INFO: loaded from: classes.dex */
public class TelecomServiceImpl {
    private AppOpsManager mAppOpsManager;
    private final ITelecomService.Stub mBinderImpl = new AnonymousClass1();
    private final CallIntentProcessor.Adapter mCallIntentProcessorAdapter;
    private CallsManager mCallsManager;
    private Context mContext;
    private final DefaultDialerCache mDefaultDialerCache;
    private final TelecomSystem.SyncRoot mLock;
    private PackageManager mPackageManager;
    private final PhoneAccountRegistrar mPhoneAccountRegistrar;
    private final SubscriptionManagerAdapter mSubscriptionManagerAdapter;
    private final UserCallIntentProcessorFactory mUserCallIntentProcessorFactory;

    public interface SettingsSecureAdapter {
    }

    public interface SubscriptionManagerAdapter {
        int getDefaultVoiceSubId();
    }

    private boolean isValidAcceptVideoState(int i) {
        return ((i & (-2)) & (-3)) == 0;
    }

    static class SubscriptionManagerAdapterImpl implements SubscriptionManagerAdapter {
        SubscriptionManagerAdapterImpl() {
        }

        @Override // com.android.server.telecom.TelecomServiceImpl.SubscriptionManagerAdapter
        public int getDefaultVoiceSubId() {
            return SubscriptionManager.getDefaultVoiceSubscriptionId();
        }
    }

    static class SettingsSecureAdapterImpl implements SettingsSecureAdapter {
        SettingsSecureAdapterImpl() {
        }
    }

    /* JADX INFO: renamed from: com.android.server.telecom.TelecomServiceImpl$1, reason: invalid class name */
    class AnonymousClass1 extends ITelecomService.Stub {
        AnonymousClass1() {
        }

        public PhoneAccountHandle getDefaultOutgoingPhoneAccount(String str, String str2, String str3) {
            try {
                Log.startSession("TSI.gDOPA");
                synchronized (TelecomServiceImpl.this.mLock) {
                    UserHandle callingUserHandle = Binder.getCallingUserHandle();
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            PhoneAccountHandle outgoingPhoneAccountForScheme = TelecomServiceImpl.this.mPhoneAccountRegistrar.getOutgoingPhoneAccountForScheme(str, callingUserHandle);
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            if (!TelecomServiceImpl.this.isCallerSimCallManager(outgoingPhoneAccountForScheme) && !TelecomServiceImpl.this.canReadPhoneState(str2, str3, "getDefaultOutgoingPhoneAccount")) {
                                return null;
                            }
                            return outgoingPhoneAccountForScheme;
                        } catch (Exception e) {
                            Log.e(this, e, "getDefaultOutgoingPhoneAccount", new Object[0]);
                            throw e;
                        }
                    } catch (Throwable th) {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        throw th;
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public PhoneAccountHandle getUserSelectedOutgoingPhoneAccount(String str) {
            PhoneAccountHandle userSelectedOutgoingPhoneAccount;
            synchronized (TelecomServiceImpl.this.mLock) {
                try {
                    try {
                        Log.startSession("TSI.gUSOPA");
                        if (!TelecomServiceImpl.this.isDialerOrPrivileged(str, "getDefaultOutgoingPhoneAccount")) {
                            throw new SecurityException("Only the default dialer, or caller with READ_PRIVILEGED_PHONE_STATE can call this method.");
                        }
                        userSelectedOutgoingPhoneAccount = TelecomServiceImpl.this.mPhoneAccountRegistrar.getUserSelectedOutgoingPhoneAccount(Binder.getCallingUserHandle());
                    } catch (Exception e) {
                        Log.e(this, e, "getUserSelectedOutgoingPhoneAccount", new Object[0]);
                        throw e;
                    }
                } finally {
                    Log.endSession();
                }
            }
            return userSelectedOutgoingPhoneAccount;
        }

        public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
            try {
                Log.startSession("TSI.sUSOPA");
                synchronized (TelecomServiceImpl.this.mLock) {
                    TelecomServiceImpl.this.enforceModifyPermission();
                    UserHandle callingUserHandle = Binder.getCallingUserHandle();
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            TelecomServiceImpl.this.mPhoneAccountRegistrar.setUserSelectedOutgoingPhoneAccount(phoneAccountHandle, callingUserHandle);
                        } catch (Exception e) {
                            Log.e(this, e, "setUserSelectedOutgoingPhoneAccount", new Object[0]);
                            throw e;
                        }
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public List<PhoneAccountHandle> getCallCapablePhoneAccounts(boolean z, String str, String str2) {
            List<PhoneAccountHandle> callCapablePhoneAccounts;
            try {
                Log.startSession("TSI.gCCPA");
                if (z && !TelecomServiceImpl.this.canReadPrivilegedPhoneState(str, "getCallCapablePhoneAccounts")) {
                    return Collections.emptyList();
                }
                if (!TelecomServiceImpl.this.canReadPhoneState(str, str2, "getCallCapablePhoneAccounts")) {
                    return Collections.emptyList();
                }
                synchronized (TelecomServiceImpl.this.mLock) {
                    UserHandle callingUserHandle = Binder.getCallingUserHandle();
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            callCapablePhoneAccounts = TelecomServiceImpl.this.mPhoneAccountRegistrar.getCallCapablePhoneAccounts(null, z, callingUserHandle);
                        } catch (Exception e) {
                            Log.e(this, e, "getCallCapablePhoneAccounts", new Object[0]);
                            throw e;
                        }
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return callCapablePhoneAccounts;
            } finally {
                Log.endSession();
            }
        }

        public List<PhoneAccountHandle> getSelfManagedPhoneAccounts(String str, String str2) {
            List<PhoneAccountHandle> selfManagedPhoneAccounts;
            try {
                Log.startSession("TSI.gSMPA");
                if (TelecomServiceImpl.this.canReadPhoneState(str, str2, "Requires READ_PHONE_STATE permission.")) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        UserHandle callingUserHandle = Binder.getCallingUserHandle();
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            try {
                                selfManagedPhoneAccounts = TelecomServiceImpl.this.mPhoneAccountRegistrar.getSelfManagedPhoneAccounts(callingUserHandle);
                            } catch (Exception e) {
                                Log.e(this, e, "getSelfManagedPhoneAccounts", new Object[0]);
                                throw e;
                            }
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                    return selfManagedPhoneAccounts;
                }
                throw new SecurityException("Requires READ_PHONE_STATE permission.");
            } finally {
                Log.endSession();
            }
        }

        public List<PhoneAccountHandle> getPhoneAccountsSupportingScheme(String str, String str2) {
            List<PhoneAccountHandle> callCapablePhoneAccounts;
            try {
                Log.startSession("TSI.gPASS");
                TelecomServiceImpl.this.enforceModifyPermission("getPhoneAccountsSupportingScheme requires MODIFY_PHONE_STATE");
                synchronized (TelecomServiceImpl.this.mLock) {
                    UserHandle callingUserHandle = Binder.getCallingUserHandle();
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            callCapablePhoneAccounts = TelecomServiceImpl.this.mPhoneAccountRegistrar.getCallCapablePhoneAccounts(str, false, callingUserHandle);
                        } catch (Exception e) {
                            Log.e(this, e, "getPhoneAccountsSupportingScheme %s", new Object[]{str});
                            throw e;
                        }
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return callCapablePhoneAccounts;
            } catch (SecurityException unused) {
                EventLog.writeEvent(1397638484, "62347125", Integer.valueOf(Binder.getCallingUid()), "getPhoneAccountsSupportingScheme: " + str2);
                return Collections.emptyList();
            } finally {
                Log.endSession();
            }
        }

        public List<PhoneAccountHandle> getPhoneAccountsForPackage(String str) {
            List<PhoneAccountHandle> phoneAccountsForPackage;
            try {
                TelecomServiceImpl.this.enforceCallingPackage(str);
                try {
                    TelecomServiceImpl.this.enforcePermission("android.permission.READ_PRIVILEGED_PHONE_STATE");
                    synchronized (TelecomServiceImpl.this.mLock) {
                        UserHandle callingUserHandle = Binder.getCallingUserHandle();
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            try {
                                Log.startSession("TSI.gPAFP");
                                phoneAccountsForPackage = TelecomServiceImpl.this.mPhoneAccountRegistrar.getPhoneAccountsForPackage(str, callingUserHandle);
                            } finally {
                                Binder.restoreCallingIdentity(jClearCallingIdentity);
                                Log.endSession();
                            }
                        } catch (Exception e) {
                            Log.e(this, e, "getPhoneAccountsForPackage %s", new Object[]{str});
                            throw e;
                        }
                    }
                    return phoneAccountsForPackage;
                } catch (SecurityException e2) {
                    EventLog.writeEvent(1397638484, "153995334", Integer.valueOf(Binder.getCallingUid()), "getPhoneAccountsForPackage: no permission");
                    throw e2;
                }
            } catch (SecurityException e3) {
                EventLog.writeEvent(1397638484, "153995334", Integer.valueOf(Binder.getCallingUid()), "getPhoneAccountsForPackage: invalid calling package");
                throw e3;
            }
        }

        public PhoneAccount getPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
            PhoneAccount phoneAccount;
            synchronized (TelecomServiceImpl.this.mLock) {
                UserHandle callingUserHandle = Binder.getCallingUserHandle();
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    try {
                        Log.startSession("TSI.gPA");
                        phoneAccount = TelecomServiceImpl.this.mPhoneAccountRegistrar.getPhoneAccount(phoneAccountHandle, callingUserHandle, true);
                    } catch (Exception e) {
                        Log.e(this, e, "getPhoneAccount %s", new Object[]{phoneAccountHandle});
                        throw e;
                    }
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                    Log.endSession();
                }
            }
            return phoneAccount;
        }

        public int getAllPhoneAccountsCount() {
            int size;
            try {
                Log.startSession("TSI.gAPAC");
                try {
                    TelecomServiceImpl.this.enforceModifyPermission("getAllPhoneAccountsCount requires MODIFY_PHONE_STATE permission.");
                    synchronized (TelecomServiceImpl.this.mLock) {
                        try {
                            size = getAllPhoneAccounts().size();
                        } catch (Exception e) {
                            Log.e(this, e, "getAllPhoneAccountsCount", new Object[0]);
                            throw e;
                        }
                    }
                    return size;
                } catch (SecurityException e2) {
                    EventLog.writeEvent(1397638484, "62347125", Integer.valueOf(Binder.getCallingUid()), "getAllPhoneAccountsCount");
                    throw e2;
                }
            } finally {
                Log.endSession();
            }
        }

        public List<PhoneAccount> getAllPhoneAccounts() {
            List<PhoneAccount> allPhoneAccounts;
            synchronized (TelecomServiceImpl.this.mLock) {
                try {
                    Log.startSession("TSI.gAPA");
                    try {
                        TelecomServiceImpl.this.enforceModifyPermission("getAllPhoneAccounts requires MODIFY_PHONE_STATE permission.");
                        UserHandle callingUserHandle = Binder.getCallingUserHandle();
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            try {
                                allPhoneAccounts = TelecomServiceImpl.this.mPhoneAccountRegistrar.getAllPhoneAccounts(callingUserHandle);
                            } finally {
                                Binder.restoreCallingIdentity(jClearCallingIdentity);
                            }
                        } catch (Exception e) {
                            Log.e(this, e, "getAllPhoneAccounts", new Object[0]);
                            throw e;
                        }
                    } catch (SecurityException e2) {
                        EventLog.writeEvent(1397638484, "62347125", Integer.valueOf(Binder.getCallingUid()), "getAllPhoneAccounts");
                        throw e2;
                    }
                } finally {
                    Log.endSession();
                }
            }
            return allPhoneAccounts;
        }

        public List<PhoneAccountHandle> getAllPhoneAccountHandles() {
            List<PhoneAccountHandle> allPhoneAccountHandles;
            try {
                Log.startSession("TSI.gAPAH");
                try {
                    TelecomServiceImpl.this.enforceModifyPermission("getAllPhoneAccountHandles requires MODIFY_PHONE_STATE permission.");
                    synchronized (TelecomServiceImpl.this.mLock) {
                        UserHandle callingUserHandle = Binder.getCallingUserHandle();
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            try {
                                allPhoneAccountHandles = TelecomServiceImpl.this.mPhoneAccountRegistrar.getAllPhoneAccountHandles(callingUserHandle);
                            } catch (Exception e) {
                                Log.e(this, e, "getAllPhoneAccounts", new Object[0]);
                                throw e;
                            }
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                    return allPhoneAccountHandles;
                } catch (SecurityException e2) {
                    EventLog.writeEvent(1397638484, "62347125", Integer.valueOf(Binder.getCallingUid()), "getAllPhoneAccountHandles");
                    throw e2;
                }
            } finally {
                Log.endSession();
            }
        }

        public PhoneAccountHandle getSimCallManager(int i) {
            PhoneAccountHandle simCallManager;
            synchronized (TelecomServiceImpl.this.mLock) {
                try {
                    try {
                        Log.startSession("TSI.gSCM");
                        int callingUid = Binder.getCallingUid();
                        int userId = UserHandle.getUserId(callingUid);
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            if (userId != ActivityManager.getCurrentUser()) {
                                TelecomServiceImpl.this.enforceCrossUserPermission(callingUid);
                            }
                            simCallManager = TelecomServiceImpl.this.mPhoneAccountRegistrar.getSimCallManager(i, UserHandle.of(userId));
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    } finally {
                        Log.endSession();
                    }
                } catch (Exception e) {
                    Log.e(this, e, "getSimCallManager", new Object[0]);
                    throw e;
                }
            }
            return simCallManager;
        }

        public PhoneAccountHandle getSimCallManagerForUser(int i) {
            PhoneAccountHandle simCallManager;
            synchronized (TelecomServiceImpl.this.mLock) {
                try {
                    try {
                        Log.startSession("TSI.gSCMFU");
                        int callingUid = Binder.getCallingUid();
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            if (i != ActivityManager.getCurrentUser()) {
                                TelecomServiceImpl.this.enforceCrossUserPermission(callingUid);
                            }
                            simCallManager = TelecomServiceImpl.this.mPhoneAccountRegistrar.getSimCallManager(UserHandle.of(i));
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    } finally {
                        Log.endSession();
                    }
                } catch (Exception e) {
                    Log.e(this, e, "getSimCallManager", new Object[0]);
                    throw e;
                }
            }
            return simCallManager;
        }

        public void registerPhoneAccount(PhoneAccount phoneAccount) {
            try {
                Log.startSession("TSI.rPA");
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (((TelephonyManager) TelecomServiceImpl.this.mContext.getSystemService("phone")).isVoiceCapable()) {
                        try {
                            TelecomServiceImpl.this.enforcePhoneAccountModificationForPackage(phoneAccount.getAccountHandle().getComponentName().getPackageName());
                            if (phoneAccount.hasCapabilities(2048)) {
                                TelecomServiceImpl.this.enforceRegisterSelfManaged();
                                if (phoneAccount.hasCapabilities(2) || phoneAccount.hasCapabilities(1) || phoneAccount.hasCapabilities(4)) {
                                    throw new SecurityException("Self-managed ConnectionServices cannot also be call capable, connection managers, or SIM accounts.");
                                }
                            }
                            if (phoneAccount.hasCapabilities(4)) {
                                TelecomServiceImpl.this.enforceRegisterSimSubscriptionPermission();
                            }
                            if (phoneAccount.hasCapabilities(32)) {
                                TelecomServiceImpl.this.enforceRegisterMultiUser();
                            }
                            Bundle extras = phoneAccount.getExtras();
                            if (extras != null && extras.getBoolean("android.telecom.extra.SKIP_CALL_FILTERING")) {
                                TelecomServiceImpl.this.enforceRegisterSkipCallFiltering();
                            }
                            if (Binder.getCallingUid() != 2000) {
                                TelecomServiceImpl.this.enforceUserHandleMatchesCaller(phoneAccount.getAccountHandle());
                            }
                            if (TextUtils.isEmpty(phoneAccount.getGroupId()) && TelecomServiceImpl.this.mContext.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
                                Log.w(this, "registerPhoneAccount - attempt to set a group from a non-system caller.", new Object[0]);
                                phoneAccount = new PhoneAccount.Builder(phoneAccount).setGroupId(null).build();
                            }
                            long jClearCallingIdentity = Binder.clearCallingIdentity();
                            try {
                                TelecomServiceImpl.this.mPhoneAccountRegistrar.registerPhoneAccount(phoneAccount);
                                return;
                            } finally {
                                Binder.restoreCallingIdentity(jClearCallingIdentity);
                            }
                        } catch (Exception e) {
                            Log.e(this, e, "registerPhoneAccount %s", new Object[]{phoneAccount});
                            throw e;
                        }
                    }
                    Log.w(this, "registerPhoneAccount not allowed on non-voice capable device.", new Object[0]);
                }
            } finally {
                Log.endSession();
            }
        }

        public void unregisterPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
            synchronized (TelecomServiceImpl.this.mLock) {
                try {
                    try {
                        Log.startSession("TSI.uPA");
                        TelecomServiceImpl.this.enforcePhoneAccountModificationForPackage(phoneAccountHandle.getComponentName().getPackageName());
                        TelecomServiceImpl.this.enforceUserHandleMatchesCaller(phoneAccountHandle);
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.mPhoneAccountRegistrar.unregisterPhoneAccount(phoneAccountHandle);
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    } catch (Exception e) {
                        Log.e(this, e, "unregisterPhoneAccount %s", new Object[]{phoneAccountHandle});
                        throw e;
                    }
                } finally {
                    Log.endSession();
                }
            }
        }

        public void clearAccounts(String str) {
            synchronized (TelecomServiceImpl.this.mLock) {
                try {
                    try {
                        Log.startSession("TSI.cA");
                        TelecomServiceImpl.this.enforcePhoneAccountModificationForPackage(str);
                        TelecomServiceImpl.this.mPhoneAccountRegistrar.clearAccounts(str, Binder.getCallingUserHandle());
                    } catch (Exception e) {
                        Log.e(this, e, "clearAccounts %s", new Object[]{str});
                        throw e;
                    }
                } finally {
                    Log.endSession();
                }
            }
        }

        public boolean isVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2, String str3) {
            try {
                Log.startSession("TSI.iVMN");
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (!TelecomServiceImpl.this.canReadPhoneState(str2, str3, "isVoiceMailNumber")) {
                        return false;
                    }
                    if (!TelecomServiceImpl.this.isPhoneAccountHandleVisibleToCallingUser(phoneAccountHandle, Binder.getCallingUserHandle())) {
                        Log.d(this, "%s is not visible for the calling user [iVMN]", new Object[]{phoneAccountHandle});
                        return false;
                    }
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        try {
                            return TelecomServiceImpl.this.mPhoneAccountRegistrar.isVoiceMailNumber(phoneAccountHandle, str);
                        } catch (Exception e) {
                            Log.e(this, e, "getSubscriptionIdForPhoneAccount", new Object[0]);
                            throw e;
                        }
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public String getVoiceMailNumber(PhoneAccountHandle phoneAccountHandle, String str, String str2) {
            try {
                Log.startSession("TSI.gVMN");
                if (!TelecomServiceImpl.this.canReadPhoneState(str, str2, "getVoiceMailNumber")) {
                    return null;
                }
                try {
                    if (!TelecomServiceImpl.this.isPhoneAccountHandleVisibleToCallingUser(phoneAccountHandle, Binder.getCallingUserHandle())) {
                        Log.d(this, "%s is not visible for the calling user [gVMN]", new Object[]{phoneAccountHandle});
                        return null;
                    }
                    int defaultVoiceSubId = TelecomServiceImpl.this.mSubscriptionManagerAdapter.getDefaultVoiceSubId();
                    synchronized (TelecomServiceImpl.this.mLock) {
                        if (phoneAccountHandle != null) {
                            defaultVoiceSubId = TelecomServiceImpl.this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle);
                        }
                    }
                    return TelecomServiceImpl.this.getTelephonyManager(defaultVoiceSubId).getVoiceMailNumber();
                } catch (Exception e) {
                    Log.e(this, e, "getSubscriptionIdForPhoneAccount", new Object[0]);
                    throw e;
                }
            } finally {
                Log.endSession();
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r5v3, types: [boolean] */
        /* JADX WARN: Type inference failed for: r5v5, types: [long] */
        public String getLine1Number(PhoneAccountHandle phoneAccountHandle, String str, String str2) {
            int subscriptionIdForPhoneAccount;
            try {
                Log.startSession("getL1N");
                if (!TelecomServiceImpl.this.canReadPhoneNumbers(str, str2, "getLine1Number")) {
                    return null;
                }
                long jIsPhoneAccountHandleVisibleToCallingUser = TelecomServiceImpl.this.isPhoneAccountHandleVisibleToCallingUser(phoneAccountHandle, Binder.getCallingUserHandle());
                if (jIsPhoneAccountHandleVisibleToCallingUser == 0) {
                    Log.d(this, "%s is not visible for the calling user [gL1N]", new Object[]{phoneAccountHandle});
                    return null;
                }
                try {
                    jIsPhoneAccountHandleVisibleToCallingUser = Binder.clearCallingIdentity();
                    try {
                        synchronized (TelecomServiceImpl.this.mLock) {
                            subscriptionIdForPhoneAccount = TelecomServiceImpl.this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle);
                        }
                        return TelecomServiceImpl.this.getTelephonyManager(subscriptionIdForPhoneAccount).getLine1Number();
                    } catch (Exception e) {
                        Log.e(this, e, "getSubscriptionIdForPhoneAccount", new Object[0]);
                        throw e;
                    }
                } finally {
                    Binder.restoreCallingIdentity(jIsPhoneAccountHandleVisibleToCallingUser);
                }
            } finally {
                Log.endSession();
            }
        }

        public void silenceRinger(String str) {
            try {
                Log.startSession("TSI.sR");
                synchronized (TelecomServiceImpl.this.mLock) {
                    TelecomServiceImpl.this.enforcePermissionOrPrivilegedDialer("android.permission.MODIFY_PHONE_STATE", str);
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        Log.i(this, "Silence Ringer requested by %s", new Object[]{str});
                        TelecomServiceImpl.this.mCallsManager.getCallAudioManager().silenceRingers();
                        TelecomServiceImpl.this.mCallsManager.getInCallController().silenceRinger();
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public ComponentName getDefaultPhoneApp() {
            try {
                Log.startSession("TSI.gDPA");
                return TelecomServiceImpl.this.mDefaultDialerCache.getSystemDialerComponent();
            } finally {
                Log.endSession();
            }
        }

        public String getDefaultDialerPackage() {
            try {
                Log.startSession("TSI.gDDP");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    return TelecomServiceImpl.this.mDefaultDialerCache.getDefaultDialerApplication(ActivityManager.getCurrentUser());
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } finally {
                Log.endSession();
            }
        }

        public String getDefaultDialerPackageForUser(int i) {
            try {
                Log.startSession("TSI.gDDPU");
                TelecomServiceImpl.this.mContext.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", "READ_PRIVILEGED_PHONE_STATE permission required.");
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    return TelecomServiceImpl.this.mDefaultDialerCache.getDefaultDialerApplication(i);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } finally {
                Log.endSession();
            }
        }

        public String getSystemDialerPackage() {
            try {
                Log.startSession("TSI.gSDP");
                return TelecomServiceImpl.this.mDefaultDialerCache.getSystemDialerApplication();
            } finally {
                Log.endSession();
            }
        }

        public void setSystemDialer(ComponentName componentName) {
            try {
                Log.startSession("TSI.sSD");
                TelecomServiceImpl.this.enforceModifyPermission();
                TelecomServiceImpl.this.enforceShellOnly(Binder.getCallingUid(), "setSystemDialer");
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        TelecomServiceImpl.this.mDefaultDialerCache.setSystemDialerComponentName(componentName);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public boolean isInCall(String str, String str2) {
            boolean zHasOngoingCalls;
            try {
                Log.startSession("TSI.iIC");
                if (TelecomServiceImpl.this.canReadPhoneState(str, str2, "isInCall")) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        zHasOngoingCalls = TelecomServiceImpl.this.mCallsManager.hasOngoingCalls();
                    }
                    return zHasOngoingCalls;
                }
                return false;
            } finally {
                Log.endSession();
            }
        }

        public boolean isInManagedCall(String str, String str2) {
            boolean zHasOngoingManagedCalls;
            try {
                Log.startSession("TSI.iIMC");
                if (TelecomServiceImpl.this.canReadPhoneState(str, str2, "isInManagedCall")) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        zHasOngoingManagedCalls = TelecomServiceImpl.this.mCallsManager.hasOngoingManagedCalls();
                    }
                    return zHasOngoingManagedCalls;
                }
                throw new SecurityException("Only the default dialer or caller with READ_PHONE_STATE permission can use this method.");
            } finally {
                Log.endSession();
            }
        }

        public boolean isRinging(String str) {
            boolean zHasRingingOrSimulatedRingingCall;
            try {
                Log.startSession("TSI.iR");
                if (!TelecomServiceImpl.this.isPrivilegedDialerCalling(str)) {
                    try {
                        TelecomServiceImpl.this.enforceModifyPermission("isRinging requires MODIFY_PHONE_STATE permission.");
                    } catch (SecurityException e) {
                        EventLog.writeEvent(1397638484, "62347125", "isRinging: " + str);
                        throw e;
                    }
                }
                synchronized (TelecomServiceImpl.this.mLock) {
                    zHasRingingOrSimulatedRingingCall = TelecomServiceImpl.this.mCallsManager.hasRingingOrSimulatedRingingCall();
                }
                return zHasRingingOrSimulatedRingingCall;
            } finally {
                Log.endSession();
            }
        }

        public int getCallState() {
            int callState;
            try {
                Log.startSession("TSI.getCallState");
                synchronized (TelecomServiceImpl.this.mLock) {
                    callState = TelecomServiceImpl.this.mCallsManager.getCallState();
                }
                return callState;
            } finally {
                Log.endSession();
            }
        }

        public boolean endCall(String str) {
            boolean zEndCallInternal;
            try {
                Log.startSession("TSI.eC");
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (!TelecomServiceImpl.this.enforceAnswerCallPermission(str, Binder.getCallingUid())) {
                        throw new SecurityException("requires ANSWER_PHONE_CALLS permission");
                    }
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        zEndCallInternal = TelecomServiceImpl.this.endCallInternal(str);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return zEndCallInternal;
            } finally {
                Log.endSession();
            }
        }

        public void acceptRingingCall(String str) {
            try {
                Log.startSession("TSI.aRC");
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (TelecomServiceImpl.this.enforceAnswerCallPermission(str, Binder.getCallingUid())) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.acceptRingingCallInternal(-1);
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void acceptRingingCallWithVideoState(String str, int i) {
            try {
                Log.startSession("TSI.aRCWVS");
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (TelecomServiceImpl.this.enforceAnswerCallPermission(str, Binder.getCallingUid())) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.acceptRingingCallInternal(i);
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void showInCallScreen(boolean z, String str, String str2) {
            try {
                Log.startSession("TSI.sICS");
                if (TelecomServiceImpl.this.canReadPhoneState(str, str2, "showInCallScreen")) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.mCallsManager.getInCallController().bringToForeground(z);
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void cancelMissedCallsNotification(String str) {
            try {
                Log.startSession("TSI.cMCN");
                synchronized (TelecomServiceImpl.this.mLock) {
                    TelecomServiceImpl.this.enforcePermissionOrPrivilegedDialer("android.permission.MODIFY_PHONE_STATE", str);
                    UserHandle callingUserHandle = Binder.getCallingUserHandle();
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        TelecomServiceImpl.this.mCallsManager.getMissedCallNotifier().clearMissedCalls(callingUserHandle);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public boolean handlePinMmi(String str, String str2) {
            try {
                Log.startSession("TSI.hPM");
                TelecomServiceImpl.this.enforcePermissionOrPrivilegedDialer("android.permission.MODIFY_PHONE_STATE", str2);
                long jClearCallingIdentity = Binder.clearCallingIdentity();
                try {
                    return TelecomServiceImpl.this.getTelephonyManager(SubscriptionManager.getDefaultVoiceSubscriptionId()).handlePinMmi(str);
                } finally {
                    Binder.restoreCallingIdentity(jClearCallingIdentity);
                }
            } finally {
                Log.endSession();
            }
        }

        public boolean handlePinMmiForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str, String str2) {
            int subscriptionIdForPhoneAccount;
            try {
                Log.startSession("TSI.hPMFPA");
                TelecomServiceImpl.this.enforcePermissionOrPrivilegedDialer("android.permission.MODIFY_PHONE_STATE", str2);
                UserHandle callingUserHandle = Binder.getCallingUserHandle();
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (!TelecomServiceImpl.this.isPhoneAccountHandleVisibleToCallingUser(phoneAccountHandle, callingUserHandle)) {
                        Log.d(this, "%s is not visible for the calling user [hMMI]", new Object[]{phoneAccountHandle});
                        return false;
                    }
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        synchronized (TelecomServiceImpl.this.mLock) {
                            subscriptionIdForPhoneAccount = TelecomServiceImpl.this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle);
                        }
                        return TelecomServiceImpl.this.getTelephonyManager(subscriptionIdForPhoneAccount).handlePinMmiForSubscriber(subscriptionIdForPhoneAccount, str);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public Uri getAdnUriForPhoneAccount(PhoneAccountHandle phoneAccountHandle, String str) {
            try {
                Log.startSession("TSI.aAUFPA");
                TelecomServiceImpl.this.enforcePermissionOrPrivilegedDialer("android.permission.MODIFY_PHONE_STATE", str);
                synchronized (TelecomServiceImpl.this.mLock) {
                    if (!TelecomServiceImpl.this.isPhoneAccountHandleVisibleToCallingUser(phoneAccountHandle, Binder.getCallingUserHandle())) {
                        Log.d(this, "%s is not visible for the calling user [gA4PA]", new Object[]{phoneAccountHandle});
                        return null;
                    }
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        String str2 = "content://icc/adn/subId/" + TelecomServiceImpl.this.mPhoneAccountRegistrar.getSubscriptionIdForPhoneAccount(phoneAccountHandle);
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        return Uri.parse(str2);
                    } catch (Throwable th) {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                        throw th;
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public boolean isTtySupported(String str, String str2) {
            boolean zIsTtySupported;
            try {
                Log.startSession("TSI.iTS");
                if (TelecomServiceImpl.this.canReadPhoneState(str, str2, "isTtySupported")) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        zIsTtySupported = TelecomServiceImpl.this.mCallsManager.isTtySupported();
                    }
                    return zIsTtySupported;
                }
                throw new SecurityException("Only default dialer or an app withREAD_PRIVILEGED_PHONE_STATE or READ_PHONE_STATE can call this api");
            } finally {
                Log.endSession();
            }
        }

        public int getCurrentTtyMode(String str, String str2) {
            int currentTtyMode;
            try {
                Log.startSession("TSI.gCTM");
                if (TelecomServiceImpl.this.canReadPhoneState(str, str2, "getCurrentTtyMode")) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        currentTtyMode = TelecomServiceImpl.this.mCallsManager.getCurrentTtyMode();
                    }
                    return currentTtyMode;
                }
                return 0;
            } finally {
                Log.endSession();
            }
        }

        public void addNewIncomingCall(PhoneAccountHandle phoneAccountHandle, Bundle bundle) {
            try {
                Log.startSession("TSI.aNIC");
                synchronized (TelecomServiceImpl.this.mLock) {
                    Log.i(this, "Adding new incoming call with phoneAccountHandle %s", new Object[]{phoneAccountHandle});
                    if (phoneAccountHandle != null && phoneAccountHandle.getComponentName() != null) {
                        if (TelecomServiceImpl.this.isCallerSimCallManager(phoneAccountHandle) && TelephonyUtil.isPstnComponentName(phoneAccountHandle.getComponentName())) {
                            Log.v(this, "Allowing call manager to add incoming call with PSTN handle", new Object[0]);
                        } else {
                            TelecomServiceImpl.this.mAppOpsManager.checkPackage(Binder.getCallingUid(), phoneAccountHandle.getComponentName().getPackageName());
                            TelecomServiceImpl.this.enforceUserHandleMatchesCaller(phoneAccountHandle);
                            TelecomServiceImpl.this.enforcePhoneAccountIsRegisteredEnabled(phoneAccountHandle, Binder.getCallingUserHandle());
                            if (TelecomServiceImpl.this.isSelfManagedConnectionService(phoneAccountHandle)) {
                                TelecomServiceImpl.this.mContext.enforceCallingOrSelfPermission("android.permission.MANAGE_OWN_CALLS", "Self-managed phone accounts must have MANAGE_OWN_CALLS permission.");
                            }
                        }
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            Intent intent = new Intent("android.telecom.action.INCOMING_CALL");
                            intent.putExtra("android.telecom.extra.PHONE_ACCOUNT_HANDLE", phoneAccountHandle);
                            intent.putExtra("is_incoming_call", true);
                            if (bundle != null) {
                                bundle.setDefusable(true);
                                intent.putExtra("android.telecom.extra.INCOMING_CALL_EXTRAS", bundle);
                            }
                            TelecomServiceImpl.this.mCallIntentProcessorAdapter.processIncomingCallIntent(TelecomServiceImpl.this.mCallsManager, intent);
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        } catch (Throwable th) {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            throw th;
                        }
                    } else {
                        Log.w(this, "Null phoneAccountHandle. Ignoring request to add new incoming call", new Object[0]);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void addNewIncomingConference(PhoneAccountHandle phoneAccountHandle, Bundle bundle) {
            try {
                Log.startSession("TSI.aNIC");
                synchronized (TelecomServiceImpl.this.mLock) {
                    Log.i(this, "Adding new incoming conference with phoneAccountHandle %s", new Object[]{phoneAccountHandle});
                    if (phoneAccountHandle != null && phoneAccountHandle.getComponentName() != null) {
                        if (TelecomServiceImpl.this.isCallerSimCallManager(phoneAccountHandle) && TelephonyUtil.isPstnComponentName(phoneAccountHandle.getComponentName())) {
                            Log.v(this, "Allowing call manager to add incoming conference with PSTN handle", new Object[0]);
                        } else {
                            TelecomServiceImpl.this.mAppOpsManager.checkPackage(Binder.getCallingUid(), phoneAccountHandle.getComponentName().getPackageName());
                            TelecomServiceImpl.this.enforceUserHandleMatchesCaller(phoneAccountHandle);
                            TelecomServiceImpl.this.enforcePhoneAccountIsRegisteredEnabled(phoneAccountHandle, Binder.getCallingUserHandle());
                            if (TelecomServiceImpl.this.isSelfManagedConnectionService(phoneAccountHandle)) {
                                throw new SecurityException("Self-Managed ConnectionServices cannot add adhoc conference calls");
                            }
                        }
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.mCallsManager.processIncomingConference(phoneAccountHandle, bundle);
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        } catch (Throwable th) {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            throw th;
                        }
                    } else {
                        Log.w(this, "Null phoneAccountHandle. Ignoring request to add new incoming conference", new Object[0]);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void acceptHandover(Uri uri, int i, PhoneAccountHandle phoneAccountHandle) {
            try {
                Log.startSession("TSI.aHO");
                synchronized (TelecomServiceImpl.this.mLock) {
                    Log.i(this, "acceptHandover; srcAddr=%s, videoState=%s, dest=%s", new Object[]{Log.pii(uri), VideoProfile.videoStateToString(i), phoneAccountHandle});
                    if (phoneAccountHandle != null && phoneAccountHandle.getComponentName() != null) {
                        TelecomServiceImpl.this.mAppOpsManager.checkPackage(Binder.getCallingUid(), phoneAccountHandle.getComponentName().getPackageName());
                        TelecomServiceImpl.this.enforceUserHandleMatchesCaller(phoneAccountHandle);
                        TelecomServiceImpl.this.enforcePhoneAccountIsRegisteredEnabled(phoneAccountHandle, Binder.getCallingUserHandle());
                        if (TelecomServiceImpl.this.isSelfManagedConnectionService(phoneAccountHandle)) {
                            TelecomServiceImpl.this.mContext.enforceCallingOrSelfPermission("android.permission.MANAGE_OWN_CALLS", "Self-managed phone accounts must have MANAGE_OWN_CALLS permission.");
                        }
                        if (!TelecomServiceImpl.this.enforceAcceptHandoverPermission(phoneAccountHandle.getComponentName().getPackageName(), Binder.getCallingUid())) {
                            throw new SecurityException("App must be granted runtime ACCEPT_HANDOVER permission.");
                        }
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.mCallsManager.acceptHandover(uri, i, phoneAccountHandle);
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        } catch (Throwable th) {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                            throw th;
                        }
                    } else {
                        Log.w(this, "Null phoneAccountHandle. Ignoring request to handover the call", new Object[0]);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        /* JADX WARN: Removed duplicated region for block: B:19:0x0075 A[Catch: all -> 0x0081, TryCatch #1 {, blocks: (B:8:0x0017, B:10:0x001d, B:15:0x006c, B:20:0x007c, B:17:0x0071, B:18:0x0074, B:19:0x0075, B:11:0x0044, B:13:0x004d, B:14:0x0053), top: B:34:0x0017, outer: #2, inners: #0 }] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void addNewUnknownCall(android.telecom.PhoneAccountHandle r7, android.os.Bundle r8) {
            /*
                r6 = this;
                java.lang.String r0 = "TSI.aNUC"
                android.telecom.Log.startSession(r0)     // Catch: java.lang.Throwable -> La2
                r0 = 0
                r1 = 1
                com.android.server.telecom.TelecomServiceImpl r2 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.SecurityException -> L84 java.lang.Throwable -> La2
                java.lang.String r3 = "addNewUnknownCall requires MODIFY_PHONE_STATE permission."
                com.android.server.telecom.TelecomServiceImpl.access$700(r2, r3)     // Catch: java.lang.SecurityException -> L84 java.lang.Throwable -> La2
                com.android.server.telecom.TelecomServiceImpl r2 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.Throwable -> La2
                com.android.server.telecom.TelecomSystem$SyncRoot r2 = com.android.server.telecom.TelecomServiceImpl.access$000(r2)     // Catch: java.lang.Throwable -> La2
                monitor-enter(r2)     // Catch: java.lang.Throwable -> La2
                if (r7 == 0) goto L75
                android.content.ComponentName r3 = r7.getComponentName()     // Catch: java.lang.Throwable -> L81
                if (r3 == 0) goto L75
                com.android.server.telecom.TelecomServiceImpl r0 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.Throwable -> L81
                android.app.AppOpsManager r0 = com.android.server.telecom.TelecomServiceImpl.access$3000(r0)     // Catch: java.lang.Throwable -> L81
                int r3 = android.os.Binder.getCallingUid()     // Catch: java.lang.Throwable -> L81
                android.content.ComponentName r4 = r7.getComponentName()     // Catch: java.lang.Throwable -> L81
                java.lang.String r4 = r4.getPackageName()     // Catch: java.lang.Throwable -> L81
                r0.checkPackage(r3, r4)     // Catch: java.lang.Throwable -> L81
                com.android.server.telecom.TelecomServiceImpl r0 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.Throwable -> L81
                com.android.server.telecom.TelecomServiceImpl.access$1700(r0, r7)     // Catch: java.lang.Throwable -> L81
                com.android.server.telecom.TelecomServiceImpl r0 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.Throwable -> L81
                android.os.UserHandle r3 = android.os.Binder.getCallingUserHandle()     // Catch: java.lang.Throwable -> L81
                com.android.server.telecom.TelecomServiceImpl.access$3100(r0, r7, r3)     // Catch: java.lang.Throwable -> L81
                long r3 = android.os.Binder.clearCallingIdentity()     // Catch: java.lang.Throwable -> L81
                android.content.Intent r0 = new android.content.Intent     // Catch: java.lang.Throwable -> L70
                java.lang.String r5 = "android.telecom.action.NEW_UNKNOWN_CALL"
                r0.<init>(r5)     // Catch: java.lang.Throwable -> L70
                if (r8 == 0) goto L53
                r8.setDefusable(r1)     // Catch: java.lang.Throwable -> L70
                r0.putExtras(r8)     // Catch: java.lang.Throwable -> L70
            L53:
                java.lang.String r8 = "is_unknown_call"
                r0.putExtra(r8, r1)     // Catch: java.lang.Throwable -> L70
                java.lang.String r8 = "android.telecom.extra.PHONE_ACCOUNT_HANDLE"
                r0.putExtra(r8, r7)     // Catch: java.lang.Throwable -> L70
                com.android.server.telecom.TelecomServiceImpl r7 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.Throwable -> L70
                com.android.server.telecom.CallIntentProcessor$Adapter r7 = com.android.server.telecom.TelecomServiceImpl.access$3300(r7)     // Catch: java.lang.Throwable -> L70
                com.android.server.telecom.TelecomServiceImpl r6 = com.android.server.telecom.TelecomServiceImpl.this     // Catch: java.lang.Throwable -> L70
                com.android.server.telecom.CallsManager r6 = com.android.server.telecom.TelecomServiceImpl.access$2300(r6)     // Catch: java.lang.Throwable -> L70
                r7.processUnknownCallIntent(r6, r0)     // Catch: java.lang.Throwable -> L70
                android.os.Binder.restoreCallingIdentity(r3)     // Catch: java.lang.Throwable -> L81
                goto L7c
            L70:
                r6 = move-exception
                android.os.Binder.restoreCallingIdentity(r3)     // Catch: java.lang.Throwable -> L81
                throw r6     // Catch: java.lang.Throwable -> L81
            L75:
                java.lang.String r7 = "Null phoneAccountHandle or not initiated by Telephony. Ignoring request to add new unknown call."
                java.lang.Object[] r8 = new java.lang.Object[r0]     // Catch: java.lang.Throwable -> L81
                android.telecom.Log.i(r6, r7, r8)     // Catch: java.lang.Throwable -> L81
            L7c:
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L81
                android.telecom.Log.endSession()
                return
            L81:
                r6 = move-exception
                monitor-exit(r2)     // Catch: java.lang.Throwable -> L81
                throw r6     // Catch: java.lang.Throwable -> La2
            L84:
                r6 = move-exception
                r7 = 1397638484(0x534e4554, float:8.859264E11)
                r8 = 3
                java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch: java.lang.Throwable -> La2
                java.lang.String r2 = "62347125"
                r8[r0] = r2     // Catch: java.lang.Throwable -> La2
                int r0 = android.os.Binder.getCallingUid()     // Catch: java.lang.Throwable -> La2
                java.lang.Integer r0 = java.lang.Integer.valueOf(r0)     // Catch: java.lang.Throwable -> La2
                r8[r1] = r0     // Catch: java.lang.Throwable -> La2
                r0 = 2
                java.lang.String r1 = "addNewUnknownCall"
                r8[r0] = r1     // Catch: java.lang.Throwable -> La2
                android.util.EventLog.writeEvent(r7, r8)     // Catch: java.lang.Throwable -> La2
                throw r6     // Catch: java.lang.Throwable -> La2
            La2:
                r6 = move-exception
                android.telecom.Log.endSession()
                throw r6
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.server.telecom.TelecomServiceImpl.AnonymousClass1.addNewUnknownCall(android.telecom.PhoneAccountHandle, android.os.Bundle):void");
        }

        public void startConference(List<Uri> list, Bundle bundle, String str) {
            try {
                Log.startSession("TSI.sC");
                if (!TelecomServiceImpl.this.canCallPhone(str, "startConference")) {
                    throw new SecurityException("Package " + str + " is not allowed to start conference call");
                }
                TelecomServiceImpl.this.mCallsManager.startConference(list, bundle, str, Binder.getCallingUserHandle());
            } finally {
                Log.endSession();
            }
        }

        public void placeCall(Uri uri, Bundle bundle, String str, String str2) {
            try {
                Log.startSession("TSI.pC");
                TelecomServiceImpl.this.enforceCallingPackage(str);
                PhoneAccountHandle phoneAccountHandle = null;
                if (bundle != null) {
                    phoneAccountHandle = (PhoneAccountHandle) bundle.getParcelable("android.telecom.extra.PHONE_ACCOUNT_HANDLE");
                    if (bundle.containsKey("android.telecom.extra.IS_HANDOVER")) {
                        bundle.remove("android.telecom.extra.IS_HANDOVER");
                    }
                }
                boolean z = false;
                boolean z2 = phoneAccountHandle != null && TelecomServiceImpl.this.isSelfManagedConnectionService(phoneAccountHandle);
                if (z2) {
                    TelecomServiceImpl.this.mContext.enforceCallingOrSelfPermission("android.permission.MANAGE_OWN_CALLS", "Self-managed ConnectionServices require MANAGE_OWN_CALLS permission.");
                    if (!str.equals(phoneAccountHandle.getComponentName().getPackageName()) && !TelecomServiceImpl.this.canCallPhone(str, str2, "CALL_PHONE permission required to place calls.")) {
                        throw new SecurityException("Self-managed ConnectionServices can only place calls through their own ConnectionService.");
                    }
                } else if (!TelecomServiceImpl.this.canCallPhone(str, str2, "placeCall")) {
                    throw new SecurityException("Package " + str + " is not allowed to place phone calls");
                }
                boolean z3 = TelecomServiceImpl.this.mAppOpsManager.noteOp(13, Binder.getCallingUid(), str, str2, (String) null) == 0;
                boolean z4 = TelecomServiceImpl.this.mContext.checkCallingPermission("android.permission.CALL_PHONE") == 0;
                boolean z5 = TelecomServiceImpl.this.mContext.checkCallingPermission("android.permission.CALL_PRIVILEGED") == 0;
                synchronized (TelecomServiceImpl.this.mLock) {
                    UserHandle callingUserHandle = Binder.getCallingUserHandle();
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        Intent intent = new Intent(z5 ? "android.intent.action.CALL_PRIVILEGED" : "android.intent.action.CALL", uri);
                        if (bundle != null) {
                            bundle.setDefusable(true);
                            intent.putExtras(bundle);
                        }
                        UserCallIntentProcessor userCallIntentProcessorCreate = TelecomServiceImpl.this.mUserCallIntentProcessorFactory.create(TelecomServiceImpl.this.mContext, callingUserHandle);
                        if (z2 || (z3 && z4)) {
                            z = true;
                        }
                        userCallIntentProcessorCreate.processIntent(intent, str, z, true);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public boolean enablePhoneAccount(PhoneAccountHandle phoneAccountHandle, boolean z) {
            boolean zEnablePhoneAccount;
            try {
                Log.startSession("TSI.ePA");
                TelecomServiceImpl.this.enforceModifyPermission();
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        zEnablePhoneAccount = TelecomServiceImpl.this.mPhoneAccountRegistrar.enablePhoneAccount(phoneAccountHandle, z);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return zEnablePhoneAccount;
            } finally {
                Log.endSession();
            }
        }

        public boolean setDefaultDialer(String str) {
            boolean defaultDialer;
            try {
                Log.startSession("TSI.sDD");
                TelecomServiceImpl.this.enforcePermission("android.permission.MODIFY_PHONE_STATE");
                TelecomServiceImpl.this.enforcePermission("android.permission.WRITE_SECURE_SETTINGS");
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        defaultDialer = TelecomServiceImpl.this.mDefaultDialerCache.setDefaultDialer(str, ActivityManager.getCurrentUser());
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return defaultDialer;
            } finally {
                Log.endSession();
            }
        }

        public void stopBlockSuppression() {
            try {
                Log.startSession("TSI.sBS");
                TelecomServiceImpl.this.enforceModifyPermission();
                if (Binder.getCallingUid() != 2000 && Binder.getCallingUid() != 0) {
                    throw new SecurityException("Shell-only API.");
                }
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        BlockedNumberContract.SystemContract.endBlockSuppression(TelecomServiceImpl.this.mContext);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public TelecomAnalytics dumpCallAnalytics() {
            try {
                Log.startSession("TSI.dCA");
                TelecomServiceImpl.this.enforcePermission("android.permission.DUMP");
                return Analytics.dumpToParcelableAnalytics();
            } finally {
                Log.endSession();
            }
        }

        protected void dump(FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] strArr) {
            if (TelecomServiceImpl.this.mContext.checkCallingOrSelfPermission("android.permission.DUMP") != 0) {
                printWriter.println("Permission Denial: can't dump TelecomService from from pid=" + Binder.getCallingPid() + ", uid=" + Binder.getCallingUid());
                return;
            }
            boolean z = false;
            if (strArr.length > 0 && "analytics".equals(strArr[0])) {
                Binder.withCleanCallingIdentity(new FunctionalUtils.ThrowingRunnable() { // from class: com.android.server.telecom.-$$Lambda$TelecomServiceImpl$1$l2AWpPrUY38Uzszoq6QKmy8ZhEU
                    public final void runOrThrow() throws Exception {
                        this.f$0.lambda$dump$0$TelecomServiceImpl$1(printWriter, strArr);
                    }
                });
                return;
            }
            if (strArr.length > 0 && "timeline".equalsIgnoreCase(strArr[0])) {
                z = true;
            }
            IndentingPrintWriter indentingPrintWriter = new IndentingPrintWriter(printWriter, "  ");
            if (TelecomServiceImpl.this.mCallsManager != null) {
                indentingPrintWriter.println("CallsManager: ");
                indentingPrintWriter.increaseIndent();
                TelecomServiceImpl.this.mCallsManager.dump(indentingPrintWriter);
                indentingPrintWriter.decreaseIndent();
                indentingPrintWriter.println("PhoneAccountRegistrar: ");
                indentingPrintWriter.increaseIndent();
                TelecomServiceImpl.this.mPhoneAccountRegistrar.dump(indentingPrintWriter);
                indentingPrintWriter.decreaseIndent();
                indentingPrintWriter.println("Analytics:");
                indentingPrintWriter.increaseIndent();
                Analytics.dump(indentingPrintWriter);
                indentingPrintWriter.decreaseIndent();
            }
            if (z) {
                Log.dumpEventsTimeline(indentingPrintWriter);
            } else {
                Log.dumpEvents(indentingPrintWriter);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX INFO: renamed from: lambda$dump$0, reason: merged with bridge method [inline-methods] */
        public /* synthetic */ void lambda$dump$0$TelecomServiceImpl$1(PrintWriter printWriter, String[] strArr) throws Exception {
            Analytics.dumpToEncodedProto(TelecomServiceImpl.this.mContext, printWriter, strArr);
        }

        public Intent createManageBlockedNumbersIntent() {
            return BlockedNumbersActivity.getIntentForStartingActivity();
        }

        public Intent createLaunchEmergencyDialerIntent(String str) {
            Intent intent = new Intent("android.intent.action.DIAL_EMERGENCY").setPackage(TelecomServiceImpl.this.mContext.getApplicationContext().getString(android.R.string.config_cameraShutterSound));
            if (TelecomServiceImpl.this.mPackageManager.resolveActivity(intent, 0) == null) {
                intent.setPackage(null);
            }
            if (!TextUtils.isEmpty(str) && TextUtils.isDigitsOnly(str)) {
                intent.setData(Uri.parse("tel:" + str));
            }
            return intent;
        }

        public boolean isIncomingCallPermitted(PhoneAccountHandle phoneAccountHandle) {
            boolean zIsIncomingCallPermitted;
            try {
                Log.startSession("TSI.iICP");
                TelecomServiceImpl.this.enforcePermission("android.permission.MANAGE_OWN_CALLS");
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        zIsIncomingCallPermitted = TelecomServiceImpl.this.mCallsManager.isIncomingCallPermitted(phoneAccountHandle);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return zIsIncomingCallPermitted;
            } finally {
                Log.endSession();
            }
        }

        public boolean isOutgoingCallPermitted(PhoneAccountHandle phoneAccountHandle) {
            boolean zIsOutgoingCallPermitted;
            try {
                Log.startSession("TSI.iOCP");
                TelecomServiceImpl.this.enforcePermission("android.permission.MANAGE_OWN_CALLS");
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        zIsOutgoingCallPermitted = TelecomServiceImpl.this.mCallsManager.isOutgoingCallPermitted(phoneAccountHandle);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return zIsOutgoingCallPermitted;
            } finally {
                Log.endSession();
            }
        }

        public void waitOnHandlers() {
            try {
                Log.startSession("TSI.wOH");
                TelecomServiceImpl.this.enforceModifyPermission();
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        Log.i(this, "waitOnHandlers", new Object[0]);
                        TelecomServiceImpl.this.mCallsManager.waitOnHandlers();
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void setTestEmergencyPhoneAccountPackageNameFilter(String str) {
            try {
                Log.startSession("TSI.sTPAPNF");
                TelecomServiceImpl.this.enforceModifyPermission();
                TelecomServiceImpl.this.enforceShellOnly(Binder.getCallingUid(), "setTestEmergencyPhoneAccountPackageNameFilter");
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        TelecomServiceImpl.this.mPhoneAccountRegistrar.setTestPhoneAccountPackageNameFilter(str);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public boolean isInEmergencyCall() {
            boolean zIsInEmergencyCall;
            try {
                Log.startSession("TSI.iIEC");
                TelecomServiceImpl.this.enforceModifyPermission();
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        zIsInEmergencyCall = TelecomServiceImpl.this.mCallsManager.isInEmergencyCall();
                        Log.i(this, "isInEmergencyCall: %b", new Object[]{Boolean.valueOf(zIsInEmergencyCall)});
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
                return zIsInEmergencyCall;
            } finally {
                Log.endSession();
            }
        }

        public void handleCallIntent(Intent intent, String str) {
            try {
                Log.startSession("TSI.hCI");
                synchronized (TelecomServiceImpl.this.mLock) {
                    TelecomServiceImpl.this.mContext.enforceCallingOrSelfPermission("android.permission.HANDLE_CALL_INTENT", "handleCallIntent is for internal use only.");
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        Log.i(this, "handleCallIntent: handling call intent", new Object[0]);
                        TelecomServiceImpl.this.mCallIntentProcessorAdapter.processOutgoingCallIntent(TelecomServiceImpl.this.mContext, TelecomServiceImpl.this.mCallsManager, intent, str);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void setTestDefaultCallRedirectionApp(String str) {
            try {
                Log.startSession("TSI.sTDCRA");
                TelecomServiceImpl.this.enforceModifyPermission();
                if (Build.IS_USERDEBUG) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.mCallsManager.getRoleManagerAdapter().setTestDefaultCallRedirectionApp(str);
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                    return;
                }
                throw new SecurityException("Test-only API.");
            } finally {
                Log.endSession();
            }
        }

        public void setTestDefaultCallScreeningApp(String str) {
            try {
                Log.startSession("TSI.sTDCSA");
                TelecomServiceImpl.this.enforceModifyPermission();
                if (Build.IS_USERDEBUG) {
                    synchronized (TelecomServiceImpl.this.mLock) {
                        long jClearCallingIdentity = Binder.clearCallingIdentity();
                        try {
                            TelecomServiceImpl.this.mCallsManager.getRoleManagerAdapter().setTestDefaultCallScreeningApp(str);
                        } finally {
                            Binder.restoreCallingIdentity(jClearCallingIdentity);
                        }
                    }
                    return;
                }
                throw new SecurityException("Test-only API.");
            } finally {
                Log.endSession();
            }
        }

        public void addOrRemoveTestCallCompanionApp(String str, boolean z) {
            try {
                Log.startSession("TSI.aORTCCA");
                TelecomServiceImpl.this.enforceModifyPermission();
                TelecomServiceImpl.this.enforceShellOnly(Binder.getCallingUid(), "addOrRemoveTestCallCompanionApp");
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        TelecomServiceImpl.this.mCallsManager.getRoleManagerAdapter().addOrRemoveTestCallCompanionApp(str, z);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }

        public void setTestPhoneAcctSuggestionComponent(String str) {
            try {
                Log.startSession("TSI.sPASA");
                TelecomServiceImpl.this.enforceModifyPermission();
                if (Binder.getCallingUid() != 2000 && Binder.getCallingUid() != 0) {
                    throw new SecurityException("Shell-only API.");
                }
                synchronized (TelecomServiceImpl.this.mLock) {
                    PhoneAccountSuggestionHelper.setOverrideServiceName(str);
                }
            } finally {
                Log.endSession();
            }
        }

        public void setTestDefaultDialer(String str) {
            try {
                Log.startSession("TSI.sTDD");
                TelecomServiceImpl.this.enforceModifyPermission();
                if (Binder.getCallingUid() != 2000 && Binder.getCallingUid() != 0) {
                    throw new SecurityException("Shell-only API.");
                }
                synchronized (TelecomServiceImpl.this.mLock) {
                    long jClearCallingIdentity = Binder.clearCallingIdentity();
                    try {
                        TelecomServiceImpl.this.mCallsManager.getRoleManagerAdapter().setTestDefaultDialer(str);
                    } finally {
                        Binder.restoreCallingIdentity(jClearCallingIdentity);
                    }
                }
            } finally {
                Log.endSession();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean enforceAnswerCallPermission(String str, int i) {
        try {
            enforceModifyPermission();
            return true;
        } catch (SecurityException unused) {
            enforcePermission("android.permission.ANSWER_PHONE_CALLS");
            int iPermissionToOpCode = AppOpsManager.permissionToOpCode("android.permission.ANSWER_PHONE_CALLS");
            return iPermissionToOpCode == -1 || this.mAppOpsManager.checkOp(iPermissionToOpCode, i, str) == 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean enforceAcceptHandoverPermission(String str, int i) {
        this.mContext.enforceCallingOrSelfPermission("android.permission.ACCEPT_HANDOVER", "App requires ACCEPT_HANDOVER permission to accept handovers.");
        int iPermissionToOpCode = AppOpsManager.permissionToOpCode("android.permission.ACCEPT_HANDOVER");
        return iPermissionToOpCode == 74 && this.mAppOpsManager.checkOp(iPermissionToOpCode, i, str) == 0;
    }

    public TelecomServiceImpl(Context context, CallsManager callsManager, PhoneAccountRegistrar phoneAccountRegistrar, CallIntentProcessor.Adapter adapter, UserCallIntentProcessorFactory userCallIntentProcessorFactory, DefaultDialerCache defaultDialerCache, SubscriptionManagerAdapter subscriptionManagerAdapter, SettingsSecureAdapter settingsSecureAdapter, TelecomSystem.SyncRoot syncRoot) {
        this.mContext = context;
        this.mAppOpsManager = (AppOpsManager) context.getSystemService("appops");
        this.mPackageManager = this.mContext.getPackageManager();
        this.mCallsManager = callsManager;
        this.mLock = syncRoot;
        this.mPhoneAccountRegistrar = phoneAccountRegistrar;
        this.mUserCallIntentProcessorFactory = userCallIntentProcessorFactory;
        this.mDefaultDialerCache = defaultDialerCache;
        this.mCallIntentProcessorAdapter = adapter;
        this.mSubscriptionManagerAdapter = subscriptionManagerAdapter;
        defaultDialerCache.observeDefaultDialerApplication(this.mContext.getMainExecutor(), new IntConsumer() { // from class: com.android.server.telecom.-$$Lambda$TelecomServiceImpl$gr-kBPk5T_WsPPpecOEyahJ1zig
            @Override // java.util.function.IntConsumer
            public final void accept(int i) {
                this.f$0.lambda$new$0$TelecomServiceImpl(i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: lambda$new$0, reason: merged with bridge method [inline-methods] */
    public /* synthetic */ void lambda$new$0$TelecomServiceImpl(int i) {
        String defaultDialerApplication = this.mDefaultDialerCache.getDefaultDialerApplication(i);
        if (defaultDialerApplication == null) {
            return;
        }
        this.mContext.sendBroadcastAsUser(new Intent("android.telecom.action.DEFAULT_DIALER_CHANGED").putExtra("android.telecom.extra.CHANGE_DEFAULT_DIALER_PACKAGE_NAME", defaultDialerApplication), UserHandle.of(i));
    }

    public ITelecomService.Stub getBinder() {
        return this.mBinderImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPhoneAccountHandleVisibleToCallingUser(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mPhoneAccountRegistrar.getPhoneAccount(phoneAccountHandle, userHandle) != null;
        }
        return z;
    }

    private boolean isCallerSystemApp() {
        for (String str : this.mPackageManager.getPackagesForUid(Binder.getCallingUid())) {
            if (isPackageSystemApp(str)) {
                return true;
            }
        }
        return false;
    }

    private boolean isPackageSystemApp(String str) {
        try {
            return (this.mPackageManager.getApplicationInfo(str, 128).flags & 1) != 0;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void acceptRingingCallInternal(int i) {
        Call firstCallWithState = this.mCallsManager.getFirstCallWithState(4, 13);
        if (firstCallWithState != null) {
            if (i == -1 || !isValidAcceptVideoState(i)) {
                i = firstCallWithState.getVideoState();
            }
            this.mCallsManager.answerCall(firstCallWithState, i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean endCallInternal(String str) {
        Call foregroundCall = this.mCallsManager.getForegroundCall();
        if (foregroundCall == null) {
            foregroundCall = this.mCallsManager.getFirstCallWithState(5, 3, 10, 4, 13, 6);
        }
        if (foregroundCall == null) {
            return false;
        }
        if (foregroundCall.isEmergencyCall()) {
            EventLog.writeEvent(1397638484, "132438333", -1, "");
            return false;
        }
        if (foregroundCall.getState() == 4 || foregroundCall.getState() == 13) {
            this.mCallsManager.rejectCall(foregroundCall, false, null);
        } else {
            this.mCallsManager.lambda$disconnectOtherCalls$17(foregroundCall);
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforcePhoneAccountIsRegisteredEnabled(PhoneAccountHandle phoneAccountHandle, UserHandle userHandle) {
        PhoneAccount phoneAccount = this.mPhoneAccountRegistrar.getPhoneAccount(phoneAccountHandle, userHandle);
        if (phoneAccount == null) {
            EventLog.writeEvent(1397638484, "26864502", Integer.valueOf(Binder.getCallingUid()), "R");
            throw new SecurityException("This PhoneAccountHandle is not registered for this user!");
        }
        if (phoneAccount.isEnabled()) {
            return;
        }
        EventLog.writeEvent(1397638484, "26864502", Integer.valueOf(Binder.getCallingUid()), "E");
        throw new SecurityException("This PhoneAccountHandle is not enabled for this user!");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforcePhoneAccountModificationForPackage(String str) {
        if (this.mContext.checkCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE") != 0) {
            enforceConnectionServiceFeature();
            enforceCallingPackage(str);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforcePermissionOrPrivilegedDialer(String str, String str2) {
        if (isPrivilegedDialerCalling(str2)) {
            return;
        }
        try {
            enforcePermission(str);
        } catch (SecurityException e) {
            Log.e(this, e, "Caller must be the default or system dialer, or have the permission %s to perform this operation.", new Object[]{str});
            throw e;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceCallingPackage(String str) {
        this.mAppOpsManager.checkPackage(Binder.getCallingUid(), str);
    }

    private void enforceConnectionServiceFeature() {
        enforceFeature("android.software.connectionservice");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceRegisterSimSubscriptionPermission() {
        enforcePermission("android.permission.REGISTER_SIM_SUBSCRIPTION");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceModifyPermission() {
        enforcePermission("android.permission.MODIFY_PHONE_STATE");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceModifyPermission(String str) {
        this.mContext.enforceCallingOrSelfPermission("android.permission.MODIFY_PHONE_STATE", str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforcePermission(String str) {
        this.mContext.enforceCallingOrSelfPermission(str, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceRegisterSelfManaged() {
        this.mContext.enforceCallingPermission("android.permission.MANAGE_OWN_CALLS", null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceRegisterMultiUser() {
        if (!isCallerSystemApp()) {
            throw new SecurityException("CAPABILITY_MULTI_USER is only available to system apps.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceRegisterSkipCallFiltering() {
        if (!isCallerSystemApp()) {
            throw new SecurityException("EXTRA_SKIP_CALL_FILTERING is only available to system apps.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceUserHandleMatchesCaller(PhoneAccountHandle phoneAccountHandle) {
        if (!Binder.getCallingUserHandle().equals(phoneAccountHandle.getUserHandle())) {
            throw new SecurityException("Calling UserHandle does not match PhoneAccountHandle's");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceCrossUserPermission(int i) {
        if (i == 1000 || i == 0) {
            return;
        }
        this.mContext.enforceCallingOrSelfPermission("android.permission.INTERACT_ACROSS_USERS_FULL", "Must be system or have INTERACT_ACROSS_USERS_FULL permission");
    }

    private void enforceFeature(String str) {
        if (this.mContext.getPackageManager().hasSystemFeature(str)) {
            return;
        }
        throw new UnsupportedOperationException("System does not support feature " + str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void enforceShellOnly(int i, String str) {
        if (i == 2000 || i == 0) {
            return;
        }
        throw new SecurityException(str + ": Only shell user can call it");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canReadPhoneState(String str, String str2, String str3) {
        if (isPrivilegedDialerCalling(str)) {
            return true;
        }
        try {
            this.mContext.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", str3);
            return true;
        } catch (SecurityException unused) {
            this.mContext.enforceCallingOrSelfPermission("android.permission.READ_PHONE_STATE", str3);
            return this.mAppOpsManager.noteOp(51, Binder.getCallingUid(), str, str2, str3) == 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canReadPhoneNumbers(String str, String str2, String str3) {
        int callingUid = Binder.getCallingUid();
        boolean z = false;
        try {
            ApplicationInfo applicationInfoAsUser = this.mPackageManager.getApplicationInfoAsUser(str, 0, UserHandle.getUserHandleForUid(Binder.getCallingUid()));
            if (applicationInfoAsUser != null) {
                if (applicationInfoAsUser.targetSdkVersion < 30) {
                    z = true;
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        if (z) {
            try {
                return canReadPhoneState(str, str2, str3);
            } catch (SecurityException unused2) {
            }
        } else if (isPrivilegedDialerCalling(str) || this.mContext.checkCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE") == 0) {
            return true;
        }
        if (this.mContext.checkCallingOrSelfPermission("android.permission.READ_PHONE_NUMBERS") == 0 && this.mAppOpsManager.noteOpNoThrow("android:read_phone_numbers", callingUid, str, str2, str3) == 0) {
            return true;
        }
        if ((this.mContext.checkCallingOrSelfPermission("android.permission.READ_SMS") == 0 && this.mAppOpsManager.noteOpNoThrow("android:read_sms", callingUid, str, str2, str3) == 0) || this.mAppOpsManager.noteOpNoThrow("android:write_sms", callingUid, str, str2, str3) == 0) {
            return true;
        }
        throw new SecurityException("Package " + str + " does not meet the requirements to access the phone number");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canReadPrivilegedPhoneState(String str, String str2) {
        if (isPrivilegedDialerCalling(str)) {
            return true;
        }
        this.mContext.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", str2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isDialerOrPrivileged(String str, String str2) {
        if (isPrivilegedDialerCalling(str)) {
            return true;
        }
        this.mContext.enforceCallingOrSelfPermission("android.permission.READ_PRIVILEGED_PHONE_STATE", str2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isSelfManagedConnectionService(PhoneAccountHandle phoneAccountHandle) {
        PhoneAccount phoneAccountUnchecked;
        return (phoneAccountHandle == null || (phoneAccountUnchecked = this.mPhoneAccountRegistrar.getPhoneAccountUnchecked(phoneAccountHandle)) == null || !phoneAccountUnchecked.isSelfManaged()) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canCallPhone(String str, String str2) {
        return canCallPhone(str, null, str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean canCallPhone(String str, String str2, String str3) {
        if (isPrivilegedDialerCalling(str)) {
            return true;
        }
        this.mContext.enforceCallingOrSelfPermission("android.permission.CALL_PHONE", str3);
        return this.mAppOpsManager.noteOp(13, Binder.getCallingUid(), str, str2, str3) == 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isCallerSimCallManager(PhoneAccountHandle phoneAccountHandle) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            PhoneAccountHandle simCallManagerFromHandle = this.mPhoneAccountRegistrar.getSimCallManagerFromHandle(phoneAccountHandle, this.mCallsManager.getCurrentUserHandle());
            if (simCallManagerFromHandle == null) {
                return false;
            }
            try {
                this.mAppOpsManager.checkPackage(Binder.getCallingUid(), simCallManagerFromHandle.getComponentName().getPackageName());
                return true;
            } catch (SecurityException unused) {
                return false;
            }
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isPrivilegedDialerCalling(String str) {
        this.mAppOpsManager.checkPackage(Binder.getCallingUid(), str);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.mDefaultDialerCache.isDefaultOrSystemDialer(str, Binder.getCallingUserHandle().getIdentifier());
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public TelephonyManager getTelephonyManager(int i) {
        return ((TelephonyManager) this.mContext.getSystemService("phone")).createForSubscriptionId(i);
    }
}
