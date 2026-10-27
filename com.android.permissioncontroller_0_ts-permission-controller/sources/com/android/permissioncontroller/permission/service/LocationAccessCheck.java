package com.android.permissioncontroller.permission.service;

import android.app.AppOpsManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobParameters;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.provider.Settings;
import android.service.notification.StatusBarNotification;
import android.util.ArraySet;
import android.util.Log;
import androidx.core.util.Preconditions;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.utils.LocationUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.BooleanSupplier;

/* JADX INFO: loaded from: classes8.dex */
public class LocationAccessCheck {
    private static final boolean DEBUG = false;
    private static final String LOG_TAG = LocationAccessCheck.class.getSimpleName();
    private static final Object sLock = new Object();
    private final AppOpsManager mAppOpsManager;
    private final ContentResolver mContentResolver;
    private final Context mContext;
    private final JobScheduler mJobScheduler;
    private final PackageManager mPackageManager;
    private final Random mRandom = new Random();
    private final SharedPreferences mSharedPrefs;
    private final BooleanSupplier mShouldCancel;
    private final UserManager mUserManager;

    /* JADX INFO: Access modifiers changed from: private */
    public long getPeriodicCheckIntervalMillis() {
        return Settings.Secure.getLong(this.mContentResolver, "location_access_check_interval_millis", TimeUnit.DAYS.toMillis(1L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long getFlexForPeriodicCheckMillis() {
        return getPeriodicCheckIntervalMillis() / 10;
    }

    private long getDelayMillis() {
        return Settings.Secure.getLong(this.mContentResolver, "location_access_check_delay_millis", TimeUnit.DAYS.toMillis(1L));
    }

    private long getInBetweenNotificationsMillis() {
        return getPeriodicCheckIntervalMillis() - ((long) (getFlexForPeriodicCheckMillis() * 2.1d));
    }

    private ArraySet<UserPackage> loadAlreadyNotifiedPackagesLocked() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(this.mContext.openFileInput(Constants.LOCATION_ACCESS_CHECK_ALREADY_NOTIFIED_FILE)));
            try {
                ArraySet<UserPackage> packages = new ArraySet<>();
                while (true) {
                    String line = reader.readLine();
                    if (line != null) {
                        String[] lineComponents = line.split(" ");
                        String pkg = lineComponents[0];
                        UserHandle user = this.mUserManager.getUserForSerialNumber(Long.valueOf(lineComponents[1]).longValue());
                        if (user != null) {
                            packages.add(new UserPackage(this.mContext, pkg, user));
                        } else {
                            Log.i(LOG_TAG, "Not restoring state \"" + line + "\" as user is unknown");
                        }
                    } else {
                        reader.close();
                        return packages;
                    }
                }
            } finally {
            }
        } catch (FileNotFoundException e) {
            return new ArraySet<>();
        } catch (Exception e2) {
            Log.w(LOG_TAG, "Could not read packages_already_notified_location_access", e2);
            return new ArraySet<>();
        }
    }

    private void safeAlreadyNotifiedPackagesLocked(ArraySet<UserPackage> packages) {
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(this.mContext.openFileOutput(Constants.LOCATION_ACCESS_CHECK_ALREADY_NOTIFIED_FILE, 0)));
            try {
                int numPkgs = packages.size();
                for (int i = 0; i < numPkgs; i++) {
                    UserPackage userPkg = packages.valueAt(i);
                    writer.append((CharSequence) userPkg.pkg);
                    writer.append(' ');
                    writer.append((CharSequence) Long.valueOf(this.mUserManager.getSerialNumberForUser(userPkg.user)).toString());
                    writer.newLine();
                }
                writer.close();
            } finally {
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Could not write packages_already_notified_location_access", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void markAsNotified(String pkg, UserHandle user) {
        synchronized (sLock) {
            ArraySet<UserPackage> alreadyNotifiedPackages = loadAlreadyNotifiedPackagesLocked();
            alreadyNotifiedPackages.add(new UserPackage(this.mContext, pkg, user));
            safeAlreadyNotifiedPackagesLocked(alreadyNotifiedPackages);
        }
    }

    private void createPermissionReminderChannel(UserHandle user) {
        NotificationManager notificationManager = (NotificationManager) Utils.getSystemServiceSafe(this.mContext, NotificationManager.class, user);
        NotificationChannel permissionReminderChannel = new NotificationChannel(Constants.PERMISSION_REMINDER_CHANNEL_ID, this.mContext.getString(R.string.permission_reminders), 2);
        notificationManager.createNotificationChannel(permissionReminderChannel);
    }

    private void throwInterruptedExceptionIfTaskIsCanceled() throws InterruptedException {
        BooleanSupplier booleanSupplier = this.mShouldCancel;
        if (booleanSupplier != null && booleanSupplier.getAsBoolean()) {
            throw new InterruptedException();
        }
    }

    public LocationAccessCheck(Context context, BooleanSupplier shouldCancel) {
        Context parentUserContext = Utils.getParentUserContext(context);
        this.mContext = parentUserContext;
        this.mJobScheduler = (JobScheduler) Utils.getSystemServiceSafe(parentUserContext, JobScheduler.class);
        this.mAppOpsManager = (AppOpsManager) Utils.getSystemServiceSafe(this.mContext, AppOpsManager.class);
        this.mPackageManager = this.mContext.getPackageManager();
        this.mUserManager = (UserManager) Utils.getSystemServiceSafe(this.mContext, UserManager.class);
        this.mSharedPrefs = this.mContext.getSharedPreferences(Constants.PREFERENCES_FILE, 0);
        this.mContentResolver = this.mContext.getContentResolver();
        this.mShouldCancel = shouldCancel;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v0 */
    /* JADX WARN: Type inference failed for: r2v10 */
    /* JADX WARN: Type inference failed for: r2v11 */
    /* JADX WARN: Type inference failed for: r2v2, types: [com.android.permissioncontroller.permission.service.LocationAccessCheck$LocationAccessCheckJobService$AddLocationNotificationIfNeededTask] */
    /* JADX WARN: Type inference failed for: r2v6, types: [java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    public void addLocationNotificationIfNeeded(JobParameters jobParameters, LocationAccessCheckJobService locationAccessCheckJobService) {
        if (!checkLocationAccessCheckEnabledAndUpdateEnabledTime()) {
            locationAccessCheckJobService.jobFinished(jobParameters, false);
            return;
        }
        synchronized (sLock) {
            LocationAccessCheckJobService.AddLocationNotificationIfNeededTask addLocationNotificationIfNeededTask = 0;
            addLocationNotificationIfNeededTask = 0;
            try {
                try {
                } catch (Exception e) {
                    Log.e(LOG_TAG, "Could not check for location access", e);
                    locationAccessCheckJobService.jobFinished(jobParameters, true);
                    synchronized (sLock) {
                        locationAccessCheckJobService.mAddLocationNotificationIfNeededTask = addLocationNotificationIfNeededTask;
                    }
                }
                if (System.currentTimeMillis() - this.mSharedPrefs.getLong(Constants.KEY_LAST_LOCATION_ACCESS_NOTIFICATION_SHOWN, 0L) < getInBetweenNotificationsMillis()) {
                    locationAccessCheckJobService.jobFinished(jobParameters, false);
                    synchronized (sLock) {
                        try {
                            locationAccessCheckJobService.mAddLocationNotificationIfNeededTask = null;
                        } catch (Throwable th) {
                            addLocationNotificationIfNeededTask = th;
                        }
                    }
                    return;
                }
                if (getCurrentlyShownNotificationLocked() != null) {
                    locationAccessCheckJobService.jobFinished(jobParameters, false);
                    synchronized (sLock) {
                        try {
                            locationAccessCheckJobService.mAddLocationNotificationIfNeededTask = null;
                        } catch (Throwable th2) {
                            addLocationNotificationIfNeededTask = th2;
                        }
                    }
                    return;
                }
                addLocationNotificationIfNeeded(this.mAppOpsManager.getPackagesForOps(new String[]{"android:fine_location"}));
                locationAccessCheckJobService.jobFinished(jobParameters, false);
                synchronized (sLock) {
                    try {
                        locationAccessCheckJobService.mAddLocationNotificationIfNeededTask = null;
                    } catch (Throwable th3) {
                        addLocationNotificationIfNeededTask = th3;
                        throw addLocationNotificationIfNeededTask;
                    }
                }
                return;
                throw addLocationNotificationIfNeededTask;
            } catch (Throwable th4) {
                synchronized (sLock) {
                    locationAccessCheckJobService.mAddLocationNotificationIfNeededTask = addLocationNotificationIfNeededTask;
                    throw th4;
                }
            }
        }
    }

    private void addLocationNotificationIfNeeded(List<AppOpsManager.PackageOps> ops) throws InterruptedException, PackageManager.NameNotFoundException {
        synchronized (sLock) {
            List<UserPackage> packages = getLocationUsersWithNoNotificationYetLocked(ops);
            PackageInfo pkgInfo = null;
            while (pkgInfo == null) {
                throwInterruptedExceptionIfTaskIsCanceled();
                if (packages.isEmpty()) {
                    return;
                }
                UserPackage packageToNotifyFor = null;
                int numPkgs = packages.size();
                int i = 0;
                while (true) {
                    if (i >= numPkgs) {
                        break;
                    }
                    UserPackage pkg = packages.get(i);
                    LocationManager locationManager = (LocationManager) Utils.getSystemServiceSafe(this.mContext, LocationManager.class, pkg.user);
                    if (!locationManager.isExtraLocationControllerPackageEnabled() || !pkg.pkg.equals(locationManager.getExtraLocationControllerPackage())) {
                        i++;
                    } else {
                        packageToNotifyFor = pkg;
                        break;
                    }
                }
                if (packageToNotifyFor == null) {
                    packageToNotifyFor = packages.get(this.mRandom.nextInt(packages.size()));
                }
                try {
                    pkgInfo = packageToNotifyFor.getPackageInfo();
                } catch (PackageManager.NameNotFoundException e) {
                    packages.remove(packageToNotifyFor);
                }
            }
            createPermissionReminderChannel(UserHandle.getUserHandleForUid(pkgInfo.applicationInfo.uid));
            createNotificationForLocationUser(pkgInfo);
        }
    }

    private List<UserPackage> getLocationUsersWithNoNotificationYetLocked(List<AppOpsManager.PackageOps> allOps) throws InterruptedException {
        List<UserHandle> profiles;
        List<UserPackage> pkgsWithLocationAccess = new ArrayList<>();
        List<UserHandle> profiles2 = this.mUserManager.getUserProfiles();
        LocationManager lm = (LocationManager) this.mContext.getSystemService(LocationManager.class);
        int numPkgs = allOps.size();
        int pkgNum = 0;
        while (pkgNum < numPkgs) {
            AppOpsManager.PackageOps packageOps = allOps.get(pkgNum);
            String pkg = packageOps.getPackageName();
            if (pkg.equals(Utils.OS_PKG)) {
                profiles = profiles2;
            } else if (lm.isProviderPackage(pkg)) {
                profiles = profiles2;
            } else {
                UserHandle user = UserHandle.getUserHandleForUid(packageOps.getUid());
                if (!profiles2.contains(user)) {
                    profiles = profiles2;
                } else {
                    UserPackage userPkg = new UserPackage(this.mContext, pkg, user);
                    AppPermissionGroup bgLocationGroup = userPkg.getBackgroundLocationGroup();
                    if (bgLocationGroup == null) {
                        profiles = profiles2;
                    } else if (!bgLocationGroup.areRuntimePermissionsGranted()) {
                        profiles = profiles2;
                    } else if (!bgLocationGroup.isUserSensitive()) {
                        profiles = profiles2;
                    } else if (userPkg.getLocationGroup().hasGrantedByDefaultPermission() && bgLocationGroup.hasGrantedByDefaultPermission()) {
                        profiles = profiles2;
                    } else {
                        int numOps = packageOps.getOps().size();
                        int opNum = 0;
                        while (true) {
                            if (opNum >= numOps) {
                                profiles = profiles2;
                                break;
                            }
                            AppOpsManager.OpEntry entry = (AppOpsManager.OpEntry) packageOps.getOps().get(opNum);
                            profiles = profiles2;
                            String proxyPackageName = entry.getProxyPackageName();
                            if (proxyPackageName == null || proxyPackageName.equals(Utils.OS_PKG) || lm.isProviderPackage(proxyPackageName)) {
                                long featureEnabledTime = getLocationAccessCheckEnabledTime();
                                if (featureEnabledTime >= 0 && entry.getLastAccessBackgroundTime(13) > featureEnabledTime) {
                                    pkgsWithLocationAccess.add(userPkg);
                                    break;
                                }
                            }
                            opNum++;
                            profiles2 = profiles;
                        }
                    }
                }
            }
            pkgNum++;
            profiles2 = profiles;
        }
        ArraySet<UserPackage> alreadyNotifiedPkgs = loadAlreadyNotifiedPackagesLocked();
        throwInterruptedExceptionIfTaskIsCanceled();
        resetAlreadyNotifiedPackagesWithoutPermissionLocked(alreadyNotifiedPkgs);
        pkgsWithLocationAccess.removeAll(alreadyNotifiedPkgs);
        return pkgsWithLocationAccess;
    }

    private boolean checkLocationAccessCheckEnabledAndUpdateEnabledTime() {
        long enabledTime = getLocationAccessCheckEnabledTime();
        if (Utils.isLocationAccessCheckEnabled()) {
            if (enabledTime <= 0) {
                this.mSharedPrefs.edit().putLong(Constants.KEY_LOCATION_ACCESS_CHECK_ENABLED_TIME, System.currentTimeMillis()).commit();
                return true;
            }
            return true;
        }
        if (enabledTime > 0) {
            this.mSharedPrefs.edit().remove(Constants.KEY_LOCATION_ACCESS_CHECK_ENABLED_TIME).commit();
            return false;
        }
        return false;
    }

    private long getLocationAccessCheckEnabledTime() {
        return this.mSharedPrefs.getLong(Constants.KEY_LOCATION_ACCESS_CHECK_ENABLED_TIME, 0L);
    }

    private void createNotificationForLocationUser(PackageInfo pkg) {
        CharSequence pkgLabel = this.mPackageManager.getApplicationLabel(pkg.applicationInfo);
        Drawable pkgIcon = this.mPackageManager.getApplicationIcon(pkg.applicationInfo);
        Bitmap pkgIconBmp = Bitmap.createBitmap(pkgIcon.getIntrinsicWidth(), pkgIcon.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(pkgIconBmp);
        pkgIcon.setBounds(0, 0, pkgIcon.getIntrinsicWidth(), pkgIcon.getIntrinsicHeight());
        pkgIcon.draw(canvas);
        String pkgName = pkg.packageName;
        UserHandle user = UserHandle.getUserHandleForUid(pkg.applicationInfo.uid);
        NotificationManager notificationManager = (NotificationManager) Utils.getSystemServiceSafe(this.mContext, NotificationManager.class, user);
        long sessionId = 0;
        while (sessionId == 0) {
            sessionId = new Random().nextLong();
        }
        Intent deleteIntent = new Intent(this.mContext, (Class<?>) NotificationDeleteHandler.class);
        deleteIntent.putExtra("android.intent.extra.PACKAGE_NAME", pkgName);
        deleteIntent.putExtra(Constants.EXTRA_SESSION_ID, sessionId);
        deleteIntent.putExtra("android.intent.extra.UID", pkg.applicationInfo.uid);
        deleteIntent.putExtra("android.intent.extra.USER", user);
        deleteIntent.setFlags(268435456);
        Intent clickIntent = new Intent(this.mContext, (Class<?>) NotificationClickHandler.class);
        clickIntent.putExtra("android.intent.extra.PACKAGE_NAME", pkgName);
        clickIntent.putExtra(Constants.EXTRA_SESSION_ID, sessionId);
        clickIntent.putExtra("android.intent.extra.UID", pkg.applicationInfo.uid);
        clickIntent.putExtra("android.intent.extra.USER", user);
        clickIntent.setFlags(268435456);
        CharSequence appName = Utils.getSettingsLabelForNotifications(this.mPackageManager);
        Notification.Builder b = new Notification.Builder(this.mContext, Constants.PERMISSION_REMINDER_CHANNEL_ID).setContentTitle(this.mContext.getString(R.string.background_location_access_reminder_notification_title, pkgLabel)).setContentText(this.mContext.getString(R.string.background_location_access_reminder_notification_content)).setStyle(new Notification.BigTextStyle().bigText(this.mContext.getString(R.string.background_location_access_reminder_notification_content))).setSmallIcon(R.drawable.ic_pin_drop).setLargeIcon(pkgIconBmp).setColor(this.mContext.getColor(android.R.color.system_notification_accent_color)).setAutoCancel(true).setDeleteIntent(PendingIntent.getBroadcast(this.mContext, 0, deleteIntent, 1207959552)).setContentIntent(PendingIntent.getBroadcast(this.mContext, 0, clickIntent, 1207959552));
        if (appName != null) {
            Bundle extras = new Bundle();
            extras.putString("android.substName", appName.toString());
            b.addExtras(extras);
        }
        notificationManager.notify(pkgName, 0, b.build());
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION, sessionId, pkg.applicationInfo.uid, pkgName, 1);
        Log.v(LOG_TAG, "Location access check notification shown with sessionId=" + sessionId + " uid=" + pkg.applicationInfo.uid + " pkgName=" + pkgName);
        this.mSharedPrefs.edit().putLong(Constants.KEY_LAST_LOCATION_ACCESS_NOTIFICATION_SHOWN, System.currentTimeMillis()).apply();
    }

    private StatusBarNotification getCurrentlyShownNotificationLocked() {
        List<UserHandle> profiles = this.mUserManager.getUserProfiles();
        int numProfiles = profiles.size();
        for (int profileNum = 0; profileNum < numProfiles; profileNum++) {
            NotificationManager notificationManager = (NotificationManager) Utils.getSystemServiceSafe(this.mContext, NotificationManager.class, profiles.get(profileNum));
            StatusBarNotification[] notifications = notificationManager.getActiveNotifications();
            for (StatusBarNotification notification : notifications) {
                if (notification.getId() == 0) {
                    return notification;
                }
            }
        }
        return null;
    }

    private void resetAlreadyNotifiedPackagesWithoutPermissionLocked(ArraySet<UserPackage> alreadyNotifiedPkgs) throws InterruptedException {
        ArrayList<UserPackage> packagesToRemove = new ArrayList<>();
        for (UserPackage userPkg : alreadyNotifiedPkgs) {
            throwInterruptedExceptionIfTaskIsCanceled();
            AppPermissionGroup bgLocationGroup = userPkg.getBackgroundLocationGroup();
            if (bgLocationGroup == null || !bgLocationGroup.areRuntimePermissionsGranted()) {
                packagesToRemove.add(userPkg);
            }
        }
        if (!packagesToRemove.isEmpty()) {
            alreadyNotifiedPkgs.removeAll(packagesToRemove);
            safeAlreadyNotifiedPackagesLocked(alreadyNotifiedPkgs);
            throwInterruptedExceptionIfTaskIsCanceled();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forgetAboutPackage(String pkg, UserHandle user) {
        synchronized (sLock) {
            StatusBarNotification notification = getCurrentlyShownNotificationLocked();
            if (notification != null && notification.getUser().equals(user) && notification.getTag().equals(pkg)) {
                ((NotificationManager) Utils.getSystemServiceSafe(this.mContext, NotificationManager.class, user)).cancel(pkg, 0);
            }
            ArraySet<UserPackage> packages = loadAlreadyNotifiedPackagesLocked();
            packages.remove(new UserPackage(this.mContext, pkg, user));
            safeAlreadyNotifiedPackagesLocked(packages);
        }
    }

    public void checkLocationAccessSoon() {
        JobInfo.Builder b = new JobInfo.Builder(1, new ComponentName(this.mContext, (Class<?>) LocationAccessCheckJobService.class)).setMinimumLatency(getDelayMillis());
        int scheduleResult = this.mJobScheduler.schedule(b.build());
        if (scheduleResult != 1) {
            Log.e(LOG_TAG, "Could not schedule location access check " + scheduleResult);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isRunningInParentProfile() {
        UserHandle user = UserHandle.of(UserHandle.myUserId());
        UserHandle parent = this.mUserManager.getProfileParent(user);
        return parent == null || user.equals(parent);
    }

    public static class SetupPeriodicBackgroundLocationAccessCheck extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            LocationAccessCheck locationAccessCheck = new LocationAccessCheck(context, null);
            JobScheduler jobScheduler = (JobScheduler) Utils.getSystemServiceSafe(context, JobScheduler.class);
            if (locationAccessCheck.isRunningInParentProfile() && jobScheduler.getPendingJob(0) == null) {
                JobInfo.Builder b = new JobInfo.Builder(0, new ComponentName(context, (Class<?>) LocationAccessCheckJobService.class)).setPeriodic(locationAccessCheck.getPeriodicCheckIntervalMillis(), locationAccessCheck.getFlexForPeriodicCheckMillis());
                int scheduleResult = jobScheduler.schedule(b.build());
                if (scheduleResult != 1) {
                    Log.e(LocationAccessCheck.LOG_TAG, "Could not schedule periodic location access check " + scheduleResult);
                }
            }
        }
    }

    public static class LocationAccessCheckJobService extends JobService {
        private AddLocationNotificationIfNeededTask mAddLocationNotificationIfNeededTask;
        private LocationAccessCheck mLocationAccessCheck;

        @Override // android.app.Service
        public void onCreate() {
            super.onCreate();
            this.mLocationAccessCheck = new LocationAccessCheck(this, new BooleanSupplier() { // from class: com.android.permissioncontroller.permission.service.-$$Lambda$LocationAccessCheck$LocationAccessCheckJobService$Su2W1mvyE1cr2I-6-cfSfsmm3G0
                @Override // java.util.function.BooleanSupplier
                public final boolean getAsBoolean() {
                    return this.f$0.lambda$onCreate$0$LocationAccessCheck$LocationAccessCheckJobService();
                }
            });
        }

        public /* synthetic */ boolean lambda$onCreate$0$LocationAccessCheck$LocationAccessCheckJobService() {
            boolean z;
            synchronized (LocationAccessCheck.sLock) {
                AddLocationNotificationIfNeededTask task = this.mAddLocationNotificationIfNeededTask;
                z = task != null && task.isCancelled();
            }
            return z;
        }

        @Override // android.app.job.JobService
        public boolean onStartJob(JobParameters params) {
            synchronized (LocationAccessCheck.sLock) {
                if (this.mAddLocationNotificationIfNeededTask != null) {
                    return false;
                }
                AddLocationNotificationIfNeededTask addLocationNotificationIfNeededTask = new AddLocationNotificationIfNeededTask();
                this.mAddLocationNotificationIfNeededTask = addLocationNotificationIfNeededTask;
                addLocationNotificationIfNeededTask.execute(params, this);
                return true;
            }
        }

        @Override // android.app.job.JobService
        public boolean onStopJob(JobParameters params) {
            synchronized (LocationAccessCheck.sLock) {
                if (this.mAddLocationNotificationIfNeededTask == null) {
                    return false;
                }
                AddLocationNotificationIfNeededTask task = this.mAddLocationNotificationIfNeededTask;
                task.cancel(false);
                try {
                    task.get();
                } catch (Exception e) {
                    Log.e(LocationAccessCheck.LOG_TAG, "While waiting for " + task + " to finish", e);
                }
                return false;
            }
        }

        private class AddLocationNotificationIfNeededTask extends AsyncTask<Object, Void, Void> {
            private AddLocationNotificationIfNeededTask() {
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public final Void doInBackground(Object... in) {
                JobParameters params = (JobParameters) in[0];
                LocationAccessCheckJobService service = (LocationAccessCheckJobService) in[1];
                LocationAccessCheckJobService.this.mLocationAccessCheck.addLocationNotificationIfNeeded(params, service);
                return null;
            }
        }
    }

    public static class NotificationDeleteHandler extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String pkg = Utils.getStringExtraSafe(intent, "android.intent.extra.PACKAGE_NAME");
            UserHandle user = (UserHandle) Utils.getParcelableExtraSafe(intent, "android.intent.extra.USER");
            long sessionId = intent.getLongExtra(Constants.EXTRA_SESSION_ID, 0L);
            int uid = intent.getIntExtra("android.intent.extra.UID", 0);
            PermissionControllerStatsLog.write(PermissionControllerStatsLog.LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION, sessionId, uid, pkg, 2);
            Log.v(LocationAccessCheck.LOG_TAG, "Location access check notification declined with sessionId=" + sessionId + " uid=" + uid + " pkgName=" + pkg);
            new LocationAccessCheck(context, null).markAsNotified(pkg, user);
        }
    }

    public static class NotificationClickHandler extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String pkg = Utils.getStringExtraSafe(intent, "android.intent.extra.PACKAGE_NAME");
            UserHandle user = (UserHandle) Utils.getParcelableExtraSafe(intent, "android.intent.extra.USER");
            int uid = intent.getIntExtra("android.intent.extra.UID", 0);
            long sessionId = intent.getLongExtra(Constants.EXTRA_SESSION_ID, 0L);
            new LocationAccessCheck(context, null).markAsNotified(pkg, user);
            PermissionControllerStatsLog.write(PermissionControllerStatsLog.LOCATION_ACCESS_CHECK_NOTIFICATION_ACTION, sessionId, uid, pkg, 3);
            Log.v(LocationAccessCheck.LOG_TAG, "Location access check notification clicked with sessionId=" + sessionId + " uid=" + uid + " pkgName=" + pkg);
            Intent manageAppPermission = new Intent("android.intent.action.MANAGE_APP_PERMISSION");
            manageAppPermission.addFlags(402653184);
            manageAppPermission.putExtra("android.intent.extra.PERMISSION_GROUP_NAME", LocationUtils.LOCATION_PERMISSION);
            manageAppPermission.putExtra("android.intent.extra.PACKAGE_NAME", pkg);
            manageAppPermission.putExtra("android.intent.extra.USER", user);
            manageAppPermission.putExtra(Constants.EXTRA_SESSION_ID, sessionId);
            context.startActivity(manageAppPermission);
        }
    }

    public static class PackageResetHandler extends BroadcastReceiver {
        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (!Objects.equals(action, "android.intent.action.PACKAGE_DATA_CLEARED") && !Objects.equals(action, "android.intent.action.PACKAGE_FULLY_REMOVED")) {
                return;
            }
            Uri data = (Uri) Preconditions.checkNotNull(intent.getData());
            UserHandle user = UserHandle.getUserHandleForUid(intent.getIntExtra("android.intent.extra.UID", 0));
            new LocationAccessCheck(context, null).forgetAboutPackage(data.getSchemeSpecificPart(), user);
        }
    }

    private static final class UserPackage {
        private final Context mContext;
        public final String pkg;
        public final UserHandle user;

        UserPackage(Context context, String pkg, UserHandle user) {
            try {
                this.mContext = context.createPackageContextAsUser(context.getPackageName(), 0, user);
                this.pkg = pkg;
                this.user = user;
            } catch (PackageManager.NameNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }

        PackageInfo getPackageInfo() throws PackageManager.NameNotFoundException {
            return this.mContext.getPackageManager().getPackageInfo(this.pkg, 4096);
        }

        AppPermissionGroup getLocationGroup() {
            try {
                return AppPermissionGroup.create(this.mContext, getPackageInfo(), "android.permission.ACCESS_FINE_LOCATION", false);
            } catch (PackageManager.NameNotFoundException e) {
                return null;
            }
        }

        AppPermissionGroup getBackgroundLocationGroup() {
            AppPermissionGroup locationGroup = getLocationGroup();
            if (locationGroup == null) {
                return null;
            }
            return locationGroup.getBackgroundPermissions();
        }

        public boolean equals(Object o) {
            if (!(o instanceof UserPackage)) {
                return false;
            }
            UserPackage userPackage = (UserPackage) o;
            return this.pkg.equals(userPackage.pkg) && this.user.equals(userPackage.user);
        }

        public int hashCode() {
            return Objects.hash(this.pkg, this.user);
        }
    }
}
