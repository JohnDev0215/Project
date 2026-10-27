package com.android.internal.app;

import android.app.Activity;
import android.app.ActivityTaskManager;
import android.app.ActivityThread;
import android.app.AppGlobals;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.IPackageManager;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.UserInfo;
import android.os.RemoteException;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.util.Slog;
import android.widget.Toast;
import com.android.internal.logging.MetricsLogger;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.function.Supplier;

/* JADX INFO: loaded from: classes4.dex */
public class IntentForwarderActivity extends Activity {
    public static String FORWARD_INTENT_TO_MANAGED_PROFILE = "com.android.internal.app.ForwardIntentToManagedProfile";
    public static String FORWARD_INTENT_TO_PARENT = "com.android.internal.app.ForwardIntentToParent";
    public static String TAG = "IntentForwarderActivity";
    private static final String TEL_SCHEME = "tel";
    protected ExecutorService mExecutorService;
    private Injector mInjector;
    private MetricsLogger mMetricsLogger;
    private static final Set<String> ALLOWED_TEXT_MESSAGE_SCHEMES = new HashSet(Arrays.asList("sms", "smsto", "mms", "mmsto"));
    private static final ComponentName RESOLVER_COMPONENT_NAME = new ComponentName("android", ResolverActivity.class.getName());

    public interface Injector {
        IPackageManager getIPackageManager();

        PackageManager getPackageManager();

        UserManager getUserManager();

        CompletableFuture<ResolveInfo> resolveActivityAsUser(Intent intent, int i, int i2);

        void showToast(int i, int i2);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mExecutorService.shutdown();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0086  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x008a  */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onCreate(android.os.Bundle r11) {
        /*
            Method dump skipped, instruction units count: 269
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.internal.app.IntentForwarderActivity.onCreate(android.os.Bundle):void");
    }

    public /* synthetic */ ResolveInfo lambda$onCreate$0$IntentForwarderActivity(Intent intent, String str, Intent intent2, int i, int i2, ResolveInfo resolveInfo) {
        if (isResolverActivityResolveInfo(resolveInfo)) {
            launchResolverActivityWithCorrectTab(intent, str, intent2, i, i2);
            return resolveInfo;
        }
        startActivityAsCaller(intent2, i2);
        return resolveInfo;
    }

    public /* synthetic */ void lambda$onCreate$1$IntentForwarderActivity(Intent intent, int i, ResolveInfo resolveInfo) {
        maybeShowDisclosure(intent, resolveInfo, i);
        finish();
    }

    private boolean isIntentForwarderResolveInfo(ResolveInfo resolveInfo) {
        ActivityInfo activityInfo;
        if (resolveInfo == null || (activityInfo = resolveInfo.activityInfo) == null || !"android".equals(activityInfo.packageName)) {
            return false;
        }
        return activityInfo.name.equals(FORWARD_INTENT_TO_PARENT) || activityInfo.name.equals(FORWARD_INTENT_TO_MANAGED_PROFILE);
    }

    private boolean isResolverActivityResolveInfo(ResolveInfo resolveInfo) {
        return (resolveInfo == null || resolveInfo.activityInfo == null || !RESOLVER_COMPONENT_NAME.equals(resolveInfo.activityInfo.getComponentName())) ? false : true;
    }

    private void maybeShowDisclosure(Intent intent, ResolveInfo resolveInfo, int i) {
        if (shouldShowDisclosure(resolveInfo, intent)) {
            this.mInjector.showToast(i, 1);
        }
    }

    private void startActivityAsCaller(Intent intent, int i) {
        String launchedFromPackage;
        try {
            startActivityAsCaller(intent, null, null, false, i);
        } catch (RuntimeException e) {
            int launchedFromUid = -1;
            try {
                launchedFromUid = ActivityTaskManager.getService().getLaunchedFromUid(getActivityToken());
                launchedFromPackage = ActivityTaskManager.getService().getLaunchedFromPackage(getActivityToken());
            } catch (RemoteException unused) {
                launchedFromPackage = "?";
            }
            Slog.wtf(TAG, "Unable to launch as UID " + launchedFromUid + " package " + launchedFromPackage + ", while running in " + ActivityThread.currentProcessName(), e);
        }
    }

    private void launchChooserActivityWithCorrectTab(Intent intent, String str) {
        int iFindSelectedProfile = findSelectedProfile(str);
        sanitizeIntent(intent);
        intent.putExtra("com.android.internal.app.ResolverActivity.EXTRA_SELECTED_PROFILE", iFindSelectedProfile);
        Intent intent2 = (Intent) intent.getParcelableExtra(Intent.EXTRA_INTENT);
        if (intent2 == null) {
            Slog.wtf(TAG, "Cannot start a chooser intent with no extra android.intent.extra.INTENT");
            return;
        }
        sanitizeIntent(intent2);
        startActivityAsCaller(intent, null, null, false, getUserId());
        finish();
    }

    private void launchResolverActivityWithCorrectTab(Intent intent, String str, Intent intent2, int i, int i2) {
        int i3 = isIntentForwarderResolveInfo(this.mInjector.resolveActivityAsUser(intent2, 65536, i).join()) ? i2 : i;
        int iFindSelectedProfile = findSelectedProfile(str);
        sanitizeIntent(intent);
        intent.putExtra("com.android.internal.app.ResolverActivity.EXTRA_SELECTED_PROFILE", iFindSelectedProfile);
        intent.putExtra("com.android.internal.app.ResolverActivity.EXTRA_CALLING_USER", UserHandle.of(i));
        startActivityAsCaller(intent, null, null, false, i3);
        finish();
    }

    private int findSelectedProfile(String str) {
        if (str.equals(FORWARD_INTENT_TO_PARENT)) {
            return 0;
        }
        return str.equals(FORWARD_INTENT_TO_MANAGED_PROFILE) ? 1 : -1;
    }

    private boolean shouldShowDisclosure(ResolveInfo resolveInfo, Intent intent) {
        if (!isDeviceProvisioned()) {
            return false;
        }
        if (resolveInfo == null || resolveInfo.activityInfo == null) {
            return true;
        }
        if (resolveInfo.activityInfo.applicationInfo.isSystemApp() && (isDialerIntent(intent) || isTextMessageIntent(intent))) {
            return false;
        }
        return !isTargetResolverOrChooserActivity(resolveInfo.activityInfo);
    }

    private boolean isDeviceProvisioned() {
        return Settings.Global.getInt(getContentResolver(), "device_provisioned", 0) != 0;
    }

    private boolean isTextMessageIntent(Intent intent) {
        return (Intent.ACTION_SENDTO.equals(intent.getAction()) || isViewActionIntent(intent)) && ALLOWED_TEXT_MESSAGE_SCHEMES.contains(intent.getScheme());
    }

    private boolean isDialerIntent(Intent intent) {
        return Intent.ACTION_DIAL.equals(intent.getAction()) || Intent.ACTION_CALL.equals(intent.getAction()) || Intent.ACTION_CALL_PRIVILEGED.equals(intent.getAction()) || Intent.ACTION_CALL_EMERGENCY.equals(intent.getAction()) || (isViewActionIntent(intent) && "tel".equals(intent.getScheme()));
    }

    private boolean isViewActionIntent(Intent intent) {
        return "android.intent.action.VIEW".equals(intent.getAction()) && intent.hasCategory(Intent.CATEGORY_BROWSABLE);
    }

    private boolean isTargetResolverOrChooserActivity(ActivityInfo activityInfo) {
        if ("android".equals(activityInfo.packageName)) {
            return ResolverActivity.class.getName().equals(activityInfo.name) || ChooserActivity.class.getName().equals(activityInfo.name);
        }
        return false;
    }

    static Intent canForward(Intent intent, int i, int i2, IPackageManager iPackageManager, ContentResolver contentResolver) {
        Intent intent2 = new Intent(intent);
        intent2.addFlags(50331648);
        sanitizeIntent(intent2);
        if (Intent.ACTION_CHOOSER.equals(intent2.getAction())) {
            return null;
        }
        Intent selector = intent2.getSelector() != null ? intent2.getSelector() : intent2;
        String strResolveTypeIfNeeded = selector.resolveTypeIfNeeded(contentResolver);
        sanitizeIntent(selector);
        try {
        } catch (RemoteException unused) {
            Slog.e(TAG, "PackageManagerService is dead?");
        }
        if (iPackageManager.canForwardTo(selector, strResolveTypeIfNeeded, i, i2)) {
            return intent2;
        }
        return null;
    }

    private int getManagedProfile() {
        for (UserInfo userInfo : this.mInjector.getUserManager().getProfiles(UserHandle.myUserId())) {
            if (userInfo.isManagedProfile()) {
                return userInfo.id;
            }
        }
        Slog.wtf(TAG, FORWARD_INTENT_TO_MANAGED_PROFILE + " has been called, but there is no managed profile");
        return -10000;
    }

    private int getProfileParent() {
        UserInfo profileParent = this.mInjector.getUserManager().getProfileParent(UserHandle.myUserId());
        if (profileParent == null) {
            Slog.wtf(TAG, FORWARD_INTENT_TO_PARENT + " has been called, but there is no parent");
            return -10000;
        }
        return profileParent.id;
    }

    private static void sanitizeIntent(Intent intent) {
        intent.setPackage(null);
        intent.setComponent(null);
    }

    protected MetricsLogger getMetricsLogger() {
        if (this.mMetricsLogger == null) {
            this.mMetricsLogger = new MetricsLogger();
        }
        return this.mMetricsLogger;
    }

    protected Injector createInjector() {
        return new InjectorImpl();
    }

    /* JADX INFO: Access modifiers changed from: private */
    class InjectorImpl implements Injector {
        private InjectorImpl() {
        }

        @Override // com.android.internal.app.IntentForwarderActivity.Injector
        public IPackageManager getIPackageManager() {
            return AppGlobals.getPackageManager();
        }

        @Override // com.android.internal.app.IntentForwarderActivity.Injector
        public UserManager getUserManager() {
            return (UserManager) IntentForwarderActivity.this.getSystemService(UserManager.class);
        }

        @Override // com.android.internal.app.IntentForwarderActivity.Injector
        public PackageManager getPackageManager() {
            return IntentForwarderActivity.this.getPackageManager();
        }

        @Override // com.android.internal.app.IntentForwarderActivity.Injector
        public CompletableFuture<ResolveInfo> resolveActivityAsUser(final Intent intent, final int i, final int i2) {
            return CompletableFuture.supplyAsync(new Supplier() { // from class: com.android.internal.app.-$$Lambda$IntentForwarderActivity$InjectorImpl$vmWtN6M4HN66enKjPr5VGq1cVO0
                @Override // java.util.function.Supplier
                public final Object get() {
                    return this.f$0.lambda$resolveActivityAsUser$0$IntentForwarderActivity$InjectorImpl(intent, i, i2);
                }
            });
        }

        public /* synthetic */ ResolveInfo lambda$resolveActivityAsUser$0$IntentForwarderActivity$InjectorImpl(Intent intent, int i, int i2) {
            return getPackageManager().resolveActivityAsUser(intent, i, i2);
        }

        @Override // com.android.internal.app.IntentForwarderActivity.Injector
        public void showToast(int i, int i2) {
            IntentForwarderActivity intentForwarderActivity = IntentForwarderActivity.this;
            Toast.makeText(intentForwarderActivity, intentForwarderActivity.getString(i), i2).show();
        }
    }
}
