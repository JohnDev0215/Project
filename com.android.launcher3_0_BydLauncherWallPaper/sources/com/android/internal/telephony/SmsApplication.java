package com.android.internal.telephony;

import android.Manifest;
import android.app.AppOpsManager;
import android.app.role.RoleManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Process;
import android.os.UserHandle;
import android.provider.Telephony;
import android.telephony.PackageChangeReceiver;
import android.telephony.TelephonyManager;
import android.util.Log;
import com.google.android.mms.ContentType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes4.dex */
public final class SmsApplication {
    public static final String ACTION_DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL = "android.provider.action.DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL";
    public static final String BLUETOOTH_PACKAGE_NAME = "com.android.bluetooth";
    private static final boolean DEBUG = false;
    private static final boolean DEBUG_MULTIUSER = false;
    static final String LOG_TAG = "SmsApplication";
    public static final String MMS_SERVICE_PACKAGE_NAME = "com.android.mms.service";
    public static final String PERMISSION_MONITOR_DEFAULT_SMS_PACKAGE = "android.permission.MONITOR_DEFAULT_SMS_PACKAGE";
    public static final String PHONE_PACKAGE_NAME = "com.android.phone";
    private static final String SCHEME_MMS = "mms";
    private static final String SCHEME_MMSTO = "mmsto";
    private static final String SCHEME_SMS = "sms";
    private static final String SCHEME_SMSTO = "smsto";
    public static final String TELEPHONY_PROVIDER_PACKAGE_NAME = "com.android.providers.telephony";
    private static final String[] DEFAULT_APP_EXCLUSIVE_APPOPS = {AppOpsManager.OPSTR_READ_SMS, AppOpsManager.OPSTR_WRITE_SMS, AppOpsManager.OPSTR_RECEIVE_SMS, AppOpsManager.OPSTR_RECEIVE_WAP_PUSH, AppOpsManager.OPSTR_SEND_SMS, AppOpsManager.OPSTR_READ_CELL_BROADCASTS};
    private static SmsPackageMonitor sSmsPackageMonitor = null;

    public static class SmsApplicationData {
        private String mApplicationName;
        private String mMmsReceiverClass;
        public String mPackageName;
        private String mProviderChangedReceiverClass;
        private String mRespondViaMessageClass;
        private String mSendToClass;
        private String mSimFullReceiverClass;
        private String mSmsAppChangedReceiverClass;
        private String mSmsReceiverClass;
        private int mUid;

        public boolean isComplete() {
            return (this.mSmsReceiverClass == null || this.mMmsReceiverClass == null || this.mRespondViaMessageClass == null || this.mSendToClass == null) ? false : true;
        }

        public SmsApplicationData(String str, int i) {
            this.mPackageName = str;
            this.mUid = i;
        }

        public String getApplicationName(Context context) {
            if (this.mApplicationName == null) {
                PackageManager packageManager = context.getPackageManager();
                try {
                    ApplicationInfo applicationInfoAsUser = packageManager.getApplicationInfoAsUser(this.mPackageName, 0, UserHandle.getUserHandleForUid(this.mUid));
                    if (applicationInfoAsUser != null) {
                        CharSequence applicationLabel = packageManager.getApplicationLabel(applicationInfoAsUser);
                        this.mApplicationName = applicationLabel != null ? applicationLabel.toString() : null;
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                    return null;
                }
            }
            return this.mApplicationName;
        }

        public String toString() {
            return " mPackageName: " + this.mPackageName + " mSmsReceiverClass: " + this.mSmsReceiverClass + " mMmsReceiverClass: " + this.mMmsReceiverClass + " mRespondViaMessageClass: " + this.mRespondViaMessageClass + " mSendToClass: " + this.mSendToClass + " mSmsAppChangedClass: " + this.mSmsAppChangedReceiverClass + " mProviderChangedReceiverClass: " + this.mProviderChangedReceiverClass + " mSimFullReceiverClass: " + this.mSimFullReceiverClass + " mUid: " + this.mUid;
        }
    }

    private static int getIncomingUserId(Context context) {
        int iMyUserId = UserHandle.myUserId();
        int callingUid = Binder.getCallingUid();
        return UserHandle.getAppId(callingUid) < 10000 ? iMyUserId : UserHandle.getUserHandleForUid(callingUid).getIdentifier();
    }

    public static Collection<SmsApplicationData> getApplicationCollection(Context context) {
        return getApplicationCollectionAsUser(context, getIncomingUserId(context));
    }

    public static Collection<SmsApplicationData> getApplicationCollectionAsUser(Context context, int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return getApplicationCollectionInternal(context, i);
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    private static Collection<SmsApplicationData> getApplicationCollectionInternal(Context context, int i) {
        SmsApplicationData smsApplicationData;
        SmsApplicationData smsApplicationData2;
        SmsApplicationData smsApplicationData3;
        SmsApplicationData smsApplicationData4;
        SmsApplicationData smsApplicationData5;
        SmsApplicationData smsApplicationData6;
        PackageManager packageManager = context.getPackageManager();
        UserHandle userHandleOf = UserHandle.of(i);
        List<ResolveInfo> listQueryBroadcastReceiversAsUser = packageManager.queryBroadcastReceiversAsUser(new Intent(Telephony.Sms.Intents.SMS_DELIVER_ACTION), 786432, userHandleOf);
        HashMap map = new HashMap();
        Iterator<ResolveInfo> it = listQueryBroadcastReceiversAsUser.iterator();
        while (it.hasNext()) {
            ActivityInfo activityInfo = it.next().activityInfo;
            if (activityInfo != null && Manifest.permission.BROADCAST_SMS.equals(activityInfo.permission)) {
                String str = activityInfo.packageName;
                if (!map.containsKey(str)) {
                    SmsApplicationData smsApplicationData7 = new SmsApplicationData(str, activityInfo.applicationInfo.uid);
                    smsApplicationData7.mSmsReceiverClass = activityInfo.name;
                    map.put(str, smsApplicationData7);
                }
            }
        }
        Intent intent = new Intent(Telephony.Sms.Intents.WAP_PUSH_DELIVER_ACTION);
        intent.setDataAndType(null, ContentType.MMS_MESSAGE);
        Iterator<ResolveInfo> it2 = packageManager.queryBroadcastReceiversAsUser(intent, 786432, userHandleOf).iterator();
        while (it2.hasNext()) {
            ActivityInfo activityInfo2 = it2.next().activityInfo;
            if (activityInfo2 != null && Manifest.permission.BROADCAST_WAP_PUSH.equals(activityInfo2.permission) && (smsApplicationData6 = (SmsApplicationData) map.get(activityInfo2.packageName)) != null) {
                smsApplicationData6.mMmsReceiverClass = activityInfo2.name;
            }
        }
        Iterator<ResolveInfo> it3 = packageManager.queryIntentServicesAsUser(new Intent(TelephonyManager.ACTION_RESPOND_VIA_MESSAGE, Uri.fromParts(SCHEME_SMSTO, "", null)), 786432, UserHandle.of(i)).iterator();
        while (it3.hasNext()) {
            ServiceInfo serviceInfo = it3.next().serviceInfo;
            if (serviceInfo != null && Manifest.permission.SEND_RESPOND_VIA_MESSAGE.equals(serviceInfo.permission) && (smsApplicationData5 = (SmsApplicationData) map.get(serviceInfo.packageName)) != null) {
                smsApplicationData5.mRespondViaMessageClass = serviceInfo.name;
            }
        }
        Iterator<ResolveInfo> it4 = packageManager.queryIntentActivitiesAsUser(new Intent(Intent.ACTION_SENDTO, Uri.fromParts(SCHEME_SMSTO, "", null)), 786432, userHandleOf).iterator();
        while (it4.hasNext()) {
            ActivityInfo activityInfo3 = it4.next().activityInfo;
            if (activityInfo3 != null && (smsApplicationData4 = (SmsApplicationData) map.get(activityInfo3.packageName)) != null) {
                smsApplicationData4.mSendToClass = activityInfo3.name;
            }
        }
        Iterator<ResolveInfo> it5 = packageManager.queryBroadcastReceiversAsUser(new Intent(Telephony.Sms.Intents.ACTION_DEFAULT_SMS_PACKAGE_CHANGED), 786432, userHandleOf).iterator();
        while (it5.hasNext()) {
            ActivityInfo activityInfo4 = it5.next().activityInfo;
            if (activityInfo4 != null && (smsApplicationData3 = (SmsApplicationData) map.get(activityInfo4.packageName)) != null) {
                smsApplicationData3.mSmsAppChangedReceiverClass = activityInfo4.name;
            }
        }
        Iterator<ResolveInfo> it6 = packageManager.queryBroadcastReceiversAsUser(new Intent(Telephony.Sms.Intents.ACTION_EXTERNAL_PROVIDER_CHANGE), 786432, userHandleOf).iterator();
        while (it6.hasNext()) {
            ActivityInfo activityInfo5 = it6.next().activityInfo;
            if (activityInfo5 != null && (smsApplicationData2 = (SmsApplicationData) map.get(activityInfo5.packageName)) != null) {
                smsApplicationData2.mProviderChangedReceiverClass = activityInfo5.name;
            }
        }
        Iterator<ResolveInfo> it7 = packageManager.queryBroadcastReceiversAsUser(new Intent(Telephony.Sms.Intents.SIM_FULL_ACTION), 786432, userHandleOf).iterator();
        while (it7.hasNext()) {
            ActivityInfo activityInfo6 = it7.next().activityInfo;
            if (activityInfo6 != null && (smsApplicationData = (SmsApplicationData) map.get(activityInfo6.packageName)) != null) {
                smsApplicationData.mSimFullReceiverClass = activityInfo6.name;
            }
        }
        Iterator<ResolveInfo> it8 = listQueryBroadcastReceiversAsUser.iterator();
        while (it8.hasNext()) {
            ActivityInfo activityInfo7 = it8.next().activityInfo;
            if (activityInfo7 != null) {
                String str2 = activityInfo7.packageName;
                SmsApplicationData smsApplicationData8 = (SmsApplicationData) map.get(str2);
                if (smsApplicationData8 != null && !smsApplicationData8.isComplete()) {
                    map.remove(str2);
                }
            }
        }
        return map.values();
    }

    public static SmsApplicationData getApplicationForPackage(Collection<SmsApplicationData> collection, String str) {
        if (str == null) {
            return null;
        }
        for (SmsApplicationData smsApplicationData : collection) {
            if (smsApplicationData.mPackageName.contentEquals(str)) {
                return smsApplicationData;
            }
        }
        return null;
    }

    private static SmsApplicationData getApplication(Context context, boolean z, int i) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        RoleManager roleManager = (RoleManager) context.getSystemService(Context.ROLE_SERVICE);
        if (!telephonyManager.isSmsCapable() && (roleManager == null || !roleManager.isRoleAvailable(RoleManager.ROLE_SMS))) {
            return null;
        }
        Collection<SmsApplicationData> applicationCollectionInternal = getApplicationCollectionInternal(context, i);
        String defaultSmsPackage = getDefaultSmsPackage(context, i);
        SmsApplicationData applicationForPackage = defaultSmsPackage != null ? getApplicationForPackage(applicationCollectionInternal, defaultSmsPackage) : null;
        if (applicationForPackage == null) {
            return applicationForPackage;
        }
        SmsApplicationData smsApplicationData = (!(z || applicationForPackage.mUid == Process.myUid()) || tryFixExclusiveSmsAppops(context, applicationForPackage, z)) ? applicationForPackage : null;
        if (smsApplicationData != null && z) {
            defaultSmsAppChanged(context);
        }
        return smsApplicationData;
    }

    private static String getDefaultSmsPackage(Context context, int i) {
        return ((RoleManager) context.getSystemService(RoleManager.class)).getDefaultSmsPackage(i);
    }

    private static void defaultSmsAppChanged(Context context) {
        PackageManager packageManager = context.getPackageManager();
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        assignExclusiveSmsPermissionsToSystemApp(context, packageManager, appOpsManager, "com.android.phone", true);
        assignExclusiveSmsPermissionsToSystemApp(context, packageManager, appOpsManager, BLUETOOTH_PACKAGE_NAME, true);
        assignExclusiveSmsPermissionsToSystemApp(context, packageManager, appOpsManager, MMS_SERVICE_PACKAGE_NAME, true);
        assignExclusiveSmsPermissionsToSystemApp(context, packageManager, appOpsManager, TELEPHONY_PROVIDER_PACKAGE_NAME, true);
        assignExclusiveSmsPermissionsToSystemApp(context, packageManager, appOpsManager, CellBroadcastUtils.getDefaultCellBroadcastReceiverPackageName(context), false);
        for (String str : DEFAULT_APP_EXCLUSIVE_APPOPS) {
            appOpsManager.setUidMode(str, 1001, 0);
        }
    }

    private static boolean tryFixExclusiveSmsAppops(Context context, SmsApplicationData smsApplicationData, boolean z) {
        AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(AppOpsManager.class);
        for (String str : DEFAULT_APP_EXCLUSIVE_APPOPS) {
            if (appOpsManager.unsafeCheckOp(str, smsApplicationData.mUid, smsApplicationData.mPackageName) != 0) {
                Log.e(LOG_TAG, smsApplicationData.mPackageName + " lost " + str + ": " + (z ? " (fixing)" : " (no permission to fix)"));
                if (!z) {
                    return false;
                }
                appOpsManager.setUidMode(str, smsApplicationData.mUid, 0);
            }
        }
        return true;
    }

    public static void setDefaultApplication(String str, Context context) {
        setDefaultApplicationAsUser(str, context, getIncomingUserId(context));
    }

    public static void setDefaultApplicationAsUser(String str, Context context, int i) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        RoleManager roleManager = (RoleManager) context.getSystemService(Context.ROLE_SERVICE);
        if (telephonyManager.isSmsCapable() || (roleManager != null && roleManager.isRoleAvailable(RoleManager.ROLE_SMS))) {
            long jClearCallingIdentity = Binder.clearCallingIdentity();
            try {
                setDefaultApplicationInternal(str, context, i);
            } finally {
                Binder.restoreCallingIdentity(jClearCallingIdentity);
            }
        }
    }

    private static void setDefaultApplicationInternal(String str, Context context, int i) {
        UserHandle userHandleOf = UserHandle.of(i);
        String defaultSmsPackage = getDefaultSmsPackage(context, i);
        if (str == null || defaultSmsPackage == null || !str.equals(defaultSmsPackage)) {
            PackageManager packageManager = context.createContextAsUser(userHandleOf, 0).getPackageManager();
            Collection<SmsApplicationData> applicationCollectionInternal = getApplicationCollectionInternal(context, i);
            if (defaultSmsPackage != null) {
                getApplicationForPackage(applicationCollectionInternal, defaultSmsPackage);
            }
            SmsApplicationData applicationForPackage = getApplicationForPackage(applicationCollectionInternal, str);
            if (applicationForPackage != null) {
                AppOpsManager appOpsManager = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
                if (defaultSmsPackage != null) {
                    try {
                        setExclusiveAppops(defaultSmsPackage, appOpsManager, packageManager.getPackageInfo(defaultSmsPackage, 0).applicationInfo.uid, 3);
                    } catch (PackageManager.NameNotFoundException unused) {
                        Log.w(LOG_TAG, "Old SMS package not found: " + defaultSmsPackage);
                    }
                }
                final CompletableFuture completableFuture = new CompletableFuture();
                ((RoleManager) context.getSystemService(RoleManager.class)).addRoleHolderAsUser(RoleManager.ROLE_SMS, applicationForPackage.mPackageName, 0, UserHandle.of(i), AsyncTask.THREAD_POOL_EXECUTOR, new Consumer() { // from class: com.android.internal.telephony.-$$Lambda$SmsApplication$NKU6QOxzs9MT_Wn3YrWKCRsNWc4
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        SmsApplication.lambda$setDefaultApplicationInternal$0(completableFuture, (Boolean) obj);
                    }
                });
                try {
                    completableFuture.get(5L, TimeUnit.SECONDS);
                    defaultSmsAppChanged(context);
                } catch (InterruptedException | ExecutionException | TimeoutException e) {
                    Log.e(LOG_TAG, "Exception while adding sms role holder " + applicationForPackage, e);
                }
            }
        }
    }

    static /* synthetic */ void lambda$setDefaultApplicationInternal$0(CompletableFuture completableFuture, Boolean bool) {
        if (bool.booleanValue()) {
            completableFuture.complete(null);
        } else {
            completableFuture.completeExceptionally(new RuntimeException());
        }
    }

    public static void broadcastSmsAppChange(Context context, UserHandle userHandle, String str, String str2) {
        Collection<SmsApplicationData> applicationCollection = getApplicationCollection(context);
        broadcastSmsAppChange(context, userHandle, getApplicationForPackage(applicationCollection, str), getApplicationForPackage(applicationCollection, str2));
    }

    private static void broadcastSmsAppChange(Context context, UserHandle userHandle, SmsApplicationData smsApplicationData, SmsApplicationData smsApplicationData2) {
        if (smsApplicationData != null && smsApplicationData.mSmsAppChangedReceiverClass != null) {
            Intent intent = new Intent(Telephony.Sms.Intents.ACTION_DEFAULT_SMS_PACKAGE_CHANGED);
            intent.setComponent(new ComponentName(smsApplicationData.mPackageName, smsApplicationData.mSmsAppChangedReceiverClass));
            intent.putExtra(Telephony.Sms.Intents.EXTRA_IS_DEFAULT_SMS_APP, false);
            context.sendBroadcastAsUser(intent, userHandle);
        }
        if (smsApplicationData2 != null && smsApplicationData2.mSmsAppChangedReceiverClass != null) {
            Intent intent2 = new Intent(Telephony.Sms.Intents.ACTION_DEFAULT_SMS_PACKAGE_CHANGED);
            intent2.setComponent(new ComponentName(smsApplicationData2.mPackageName, smsApplicationData2.mSmsAppChangedReceiverClass));
            intent2.putExtra(Telephony.Sms.Intents.EXTRA_IS_DEFAULT_SMS_APP, true);
            context.sendBroadcastAsUser(intent2, userHandle);
        }
        context.sendBroadcastAsUser(new Intent(ACTION_DEFAULT_SMS_PACKAGE_CHANGED_INTERNAL), userHandle, "android.permission.MONITOR_DEFAULT_SMS_PACKAGE");
    }

    private static void assignExclusiveSmsPermissionsToSystemApp(Context context, PackageManager packageManager, AppOpsManager appOpsManager, String str, boolean z) {
        if (z && packageManager.checkSignatures(context.getPackageName(), str) != 0) {
            Log.e(LOG_TAG, str + " does not have system signature");
            return;
        }
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 0);
            if (appOpsManager.unsafeCheckOp(AppOpsManager.OPSTR_WRITE_SMS, packageInfo.applicationInfo.uid, str) != 0) {
                Log.w(LOG_TAG, str + " does not have OP_WRITE_SMS:  (fixing)");
                setExclusiveAppops(str, appOpsManager, packageInfo.applicationInfo.uid, 0);
            }
        } catch (PackageManager.NameNotFoundException unused) {
            Log.e(LOG_TAG, "Package not found: " + str);
        }
    }

    private static void setExclusiveAppops(String str, AppOpsManager appOpsManager, int i, int i2) {
        for (String str2 : DEFAULT_APP_EXCLUSIVE_APPOPS) {
            appOpsManager.setUidMode(str2, i, i2);
        }
    }

    private static final class SmsPackageMonitor extends PackageChangeReceiver {
        final Context mContext;

        public SmsPackageMonitor(Context context) {
            this.mContext = context;
        }

        @Override // android.telephony.PackageChangeReceiver
        public void onPackageDisappeared() {
            onPackageChanged();
        }

        @Override // android.telephony.PackageChangeReceiver
        public void onPackageAppeared() {
            onPackageChanged();
        }

        @Override // android.telephony.PackageChangeReceiver
        public void onPackageModified(String str) {
            onPackageChanged();
        }

        private void onPackageChanged() {
            int identifier;
            try {
                identifier = getSendingUser().getIdentifier();
            } catch (NullPointerException unused) {
                identifier = UserHandle.SYSTEM.getIdentifier();
            }
            Context contextCreatePackageContextAsUser = this.mContext;
            if (identifier != UserHandle.SYSTEM.getIdentifier()) {
                try {
                    Context context = this.mContext;
                    contextCreatePackageContextAsUser = context.createPackageContextAsUser(context.getPackageName(), 0, UserHandle.of(identifier));
                } catch (PackageManager.NameNotFoundException unused2) {
                }
            }
            PackageManager packageManager = contextCreatePackageContextAsUser.getPackageManager();
            ComponentName defaultSendToApplication = SmsApplication.getDefaultSendToApplication(contextCreatePackageContextAsUser, true);
            if (defaultSendToApplication != null) {
                SmsApplication.configurePreferredActivity(packageManager, defaultSendToApplication);
            }
        }
    }

    public static void initSmsPackageMonitor(Context context) {
        SmsPackageMonitor smsPackageMonitor = new SmsPackageMonitor(context);
        sSmsPackageMonitor = smsPackageMonitor;
        smsPackageMonitor.register(context, context.getMainLooper(), UserHandle.ALL);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void configurePreferredActivity(PackageManager packageManager, ComponentName componentName) {
        replacePreferredActivity(packageManager, componentName, SCHEME_SMS);
        replacePreferredActivity(packageManager, componentName, SCHEME_SMSTO);
        replacePreferredActivity(packageManager, componentName, "mms");
        replacePreferredActivity(packageManager, componentName, SCHEME_MMSTO);
    }

    private static void replacePreferredActivity(PackageManager packageManager, ComponentName componentName, String str) {
        List<ComponentName> list = (List) packageManager.queryIntentActivities(new Intent(Intent.ACTION_SENDTO, Uri.fromParts(str, "", null)), 65600).stream().map(new Function() { // from class: com.android.internal.telephony.-$$Lambda$SmsApplication$iOKiHGh-aStZYmIAFNMYuIwL0BY
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SmsApplication.lambda$replacePreferredActivity$1((ResolveInfo) obj);
            }
        }).collect(Collectors.toList());
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SENDTO);
        intentFilter.addCategory(Intent.CATEGORY_DEFAULT);
        intentFilter.addDataScheme(str);
        packageManager.replacePreferredActivity(intentFilter, 2129920, list, componentName);
    }

    static /* synthetic */ ComponentName lambda$replacePreferredActivity$1(ResolveInfo resolveInfo) {
        return new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
    }

    public static SmsApplicationData getSmsApplicationData(String str, Context context) {
        return getApplicationForPackage(getApplicationCollection(context), str);
    }

    public static ComponentName getDefaultSmsApplication(Context context, boolean z) {
        return getDefaultSmsApplicationAsUser(context, z, getIncomingUserId(context));
    }

    public static ComponentName getDefaultSmsApplicationAsUser(Context context, boolean z, int i) {
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SmsApplicationData application = getApplication(context, z, i);
            return application != null ? new ComponentName(application.mPackageName, application.mSmsReceiverClass) : null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static ComponentName getDefaultMmsApplication(Context context, boolean z) {
        int incomingUserId = getIncomingUserId(context);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SmsApplicationData application = getApplication(context, z, incomingUserId);
            return application != null ? new ComponentName(application.mPackageName, application.mMmsReceiverClass) : null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static ComponentName getDefaultRespondViaMessageApplication(Context context, boolean z) {
        int incomingUserId = getIncomingUserId(context);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SmsApplicationData application = getApplication(context, z, incomingUserId);
            return application != null ? new ComponentName(application.mPackageName, application.mRespondViaMessageClass) : null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static ComponentName getDefaultSendToApplication(Context context, boolean z) {
        int incomingUserId = getIncomingUserId(context);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        try {
            SmsApplicationData application = getApplication(context, z, incomingUserId);
            return application != null ? new ComponentName(application.mPackageName, application.mSendToClass) : null;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static ComponentName getDefaultExternalTelephonyProviderChangedApplication(Context context, boolean z) {
        int incomingUserId = getIncomingUserId(context);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        ComponentName componentName = null;
        try {
            SmsApplicationData application = getApplication(context, z, incomingUserId);
            if (application != null && application.mProviderChangedReceiverClass != null) {
                componentName = new ComponentName(application.mPackageName, application.mProviderChangedReceiverClass);
            }
            return componentName;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static ComponentName getDefaultSimFullApplication(Context context, boolean z) {
        int incomingUserId = getIncomingUserId(context);
        long jClearCallingIdentity = Binder.clearCallingIdentity();
        ComponentName componentName = null;
        try {
            SmsApplicationData application = getApplication(context, z, incomingUserId);
            if (application != null && application.mSimFullReceiverClass != null) {
                componentName = new ComponentName(application.mPackageName, application.mSimFullReceiverClass);
            }
            return componentName;
        } finally {
            Binder.restoreCallingIdentity(jClearCallingIdentity);
        }
    }

    public static boolean shouldWriteMessageForPackage(String str, Context context) {
        return !isDefaultSmsApplication(context, str);
    }

    public static boolean isDefaultSmsApplication(Context context, String str) {
        if (str == null) {
            return false;
        }
        String defaultSmsApplicationPackageName = getDefaultSmsApplicationPackageName(context);
        return (defaultSmsApplicationPackageName != null && defaultSmsApplicationPackageName.equals(str)) || BLUETOOTH_PACKAGE_NAME.equals(str);
    }

    private static String getDefaultSmsApplicationPackageName(Context context) {
        ComponentName defaultSmsApplication = getDefaultSmsApplication(context, false);
        if (defaultSmsApplication != null) {
            return defaultSmsApplication.getPackageName();
        }
        return null;
    }
}
