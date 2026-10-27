package com.android.packageinstaller;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PackageParser;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import com.android.internal.app.AlertActivity;
import com.android.internal.content.PackageHelper;
import com.android.packageinstaller.EventResultPersister;
import com.android.packageinstaller.PackageUtil;
import com.ts.permission.CarPermissionManager;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes2.dex */
public class InstallInstalling extends AlertActivity {
    private static final String BROADCAST_ACTION = "com.android.packageinstaller.ACTION_INSTALL_COMMIT";
    private static final String INSTALL_ID = "com.android.packageinstaller.INSTALL_ID";
    private static final String LOG_TAG = InstallInstalling.class.getSimpleName();
    private static final String SESSION_ID = "com.android.packageinstaller.SESSION_ID";
    private Button mCancelButton;
    private int mInstallId;
    private InstallingAsyncTask mInstallingTask;
    private Uri mPackageURI;
    private PackageInstaller.SessionCallback mSessionCallback;
    private int mSessionId;

    /* JADX WARN: Multi-variable type inference failed */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApplicationInfo appInfo = (ApplicationInfo) getIntent().getParcelableExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO);
        this.mPackageURI = getIntent().getData();
        String installerPackageName = getIntent().getStringExtra("android.intent.extra.INSTALLER_PACKAGE_NAME");
        if (appInfo != null) {
            CarPermissionManager.getPermissionManager(this).installPackageByPi(appInfo.packageName, installerPackageName);
        }
        if ("package".equals(this.mPackageURI.getScheme())) {
            try {
                getPackageManager().installExistingPackage(appInfo.packageName);
                launchSuccess();
                return;
            } catch (PackageManager.NameNotFoundException e) {
                launchFailure(-110, null);
                return;
            }
        }
        File sourceFile = new File(this.mPackageURI.getPath());
        PackageUtil.AppSnippet as = PackageUtil.getAppSnippet(this, appInfo, sourceFile);
        this.mAlert.setIcon(as.icon);
        this.mAlert.setTitle(as.label);
        this.mAlert.setView(R.layout.install_content_view);
        this.mAlert.setButton(-2, getString(R.string.cancel), new DialogInterface.OnClickListener() { // from class: com.android.packageinstaller.-$$Lambda$InstallInstalling$znOyJ0CDR9gFSSyLvwV7HaV9tJY
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$onCreate$0$InstallInstalling(dialogInterface, i);
            }
        }, (Message) null);
        setupAlert();
        requireViewById(R.id.installing).setVisibility(0);
        if (savedInstanceState != null) {
            this.mSessionId = savedInstanceState.getInt(SESSION_ID);
            int i = savedInstanceState.getInt(INSTALL_ID);
            this.mInstallId = i;
            try {
                InstallEventReceiver.addObserver(this, i, new EventResultPersister.EventResultObserver() { // from class: com.android.packageinstaller.-$$Lambda$InstallInstalling$Fm5TSD2hD23Z_s5R1z4FKuv9cT0
                    @Override // com.android.packageinstaller.EventResultPersister.EventResultObserver
                    public final void onResult(int i2, int i3, String str) {
                        this.f$0.launchFinishBasedOnResult(i2, i3, str);
                    }
                });
            } catch (EventResultPersister.OutOfIdsException e2) {
            }
        } else {
            PackageInstaller.SessionParams params = new PackageInstaller.SessionParams(1);
            params.setInstallAsInstantApp(false);
            params.setReferrerUri((Uri) getIntent().getParcelableExtra("android.intent.extra.REFERRER"));
            params.setOriginatingUri((Uri) getIntent().getParcelableExtra("android.intent.extra.ORIGINATING_URI"));
            params.setOriginatingUid(getIntent().getIntExtra("android.intent.extra.ORIGINATING_UID", -1));
            params.setInstallerPackageName(getIntent().getStringExtra("android.intent.extra.INSTALLER_PACKAGE_NAME"));
            params.setInstallReason(4);
            File file = new File(this.mPackageURI.getPath());
            try {
                PackageParser.PackageLite pkg = PackageParser.parsePackageLite(file, 0);
                params.setAppPackageName(pkg.packageName);
                params.setInstallLocation(pkg.installLocation);
                params.setSize(PackageHelper.calculateInstalledSize(pkg, false, params.abiOverride));
            } catch (PackageParser.PackageParserException e3) {
                Log.e(LOG_TAG, "Cannot parse package " + file + ". Assuming defaults.");
                Log.e(LOG_TAG, "Cannot calculate installed size " + file + ". Try only apk size.");
                params.setSize(file.length());
            } catch (IOException e4) {
                Log.e(LOG_TAG, "Cannot calculate installed size " + file + ". Try only apk size.");
                params.setSize(file.length());
            }
            try {
                this.mInstallId = InstallEventReceiver.addObserver(this, Integer.MIN_VALUE, new EventResultPersister.EventResultObserver() { // from class: com.android.packageinstaller.-$$Lambda$InstallInstalling$Fm5TSD2hD23Z_s5R1z4FKuv9cT0
                    @Override // com.android.packageinstaller.EventResultPersister.EventResultObserver
                    public final void onResult(int i2, int i3, String str) {
                        this.f$0.launchFinishBasedOnResult(i2, i3, str);
                    }
                });
            } catch (EventResultPersister.OutOfIdsException e5) {
                launchFailure(-110, null);
            }
            try {
                this.mSessionId = getPackageManager().getPackageInstaller().createSession(params);
            } catch (IOException e6) {
                launchFailure(-110, null);
            }
        }
        this.mCancelButton = this.mAlert.getButton(-2);
        this.mSessionCallback = new InstallSessionCallback();
    }

    public /* synthetic */ void lambda$onCreate$0$InstallInstalling(DialogInterface ignored, int ignored2) {
        InstallingAsyncTask installingAsyncTask = this.mInstallingTask;
        if (installingAsyncTask != null) {
            installingAsyncTask.cancel(true);
        }
        if (this.mSessionId > 0) {
            getPackageManager().getPackageInstaller().abandonSession(this.mSessionId);
            this.mSessionId = 0;
        }
        setResult(0);
        finish();
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void launchSuccess() {
        Intent successIntent = new Intent(getIntent());
        successIntent.setClass(this, InstallSuccess.class);
        successIntent.addFlags(33554432);
        startActivity(successIntent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void launchFailure(int legacyStatus, String statusMessage) {
        Intent failureIntent = new Intent(getIntent());
        failureIntent.setClass(this, InstallFailed.class);
        failureIntent.addFlags(33554432);
        failureIntent.putExtra("android.content.pm.extra.LEGACY_STATUS", legacyStatus);
        failureIntent.putExtra("android.content.pm.extra.STATUS_MESSAGE", statusMessage);
        startActivity(failureIntent);
        finish();
    }

    protected void onStart() {
        super.onStart();
        getPackageManager().getPackageInstaller().registerSessionCallback(this.mSessionCallback);
    }

    protected void onResume() {
        super.onResume();
        if (this.mInstallingTask == null) {
            PackageInstaller installer = getPackageManager().getPackageInstaller();
            PackageInstaller.SessionInfo sessionInfo = installer.getSessionInfo(this.mSessionId);
            if (sessionInfo == null || sessionInfo.isActive()) {
                this.mCancelButton.setEnabled(false);
                setFinishOnTouchOutside(false);
            } else {
                InstallingAsyncTask installingAsyncTask = new InstallingAsyncTask();
                this.mInstallingTask = installingAsyncTask;
                installingAsyncTask.execute(new Void[0]);
            }
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(SESSION_ID, this.mSessionId);
        outState.putInt(INSTALL_ID, this.mInstallId);
    }

    public void onBackPressed() {
        if (this.mCancelButton.isEnabled()) {
            super.onBackPressed();
        }
    }

    protected void onStop() {
        super.onStop();
        getPackageManager().getPackageInstaller().unregisterSessionCallback(this.mSessionCallback);
    }

    /* JADX WARN: Multi-variable type inference failed */
    protected void onDestroy() {
        InstallingAsyncTask installingAsyncTask = this.mInstallingTask;
        if (installingAsyncTask != null) {
            installingAsyncTask.cancel(true);
            synchronized (this.mInstallingTask) {
                while (!this.mInstallingTask.isDone) {
                    try {
                        this.mInstallingTask.wait();
                    } catch (InterruptedException e) {
                        Log.i(LOG_TAG, "Interrupted while waiting for installing task to cancel", e);
                    }
                }
            }
        }
        InstallEventReceiver.removeObserver(this, this.mInstallId);
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchFinishBasedOnResult(int statusCode, int legacyStatus, String statusMessage) {
        if (statusCode == 0) {
            launchSuccess();
        } else {
            launchFailure(legacyStatus, statusMessage);
        }
    }

    private class InstallSessionCallback extends PackageInstaller.SessionCallback {
        private InstallSessionCallback() {
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onCreated(int sessionId) {
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onBadgingChanged(int sessionId) {
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onActiveChanged(int sessionId, boolean active) {
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onProgressChanged(int sessionId, float progress) {
            if (sessionId == InstallInstalling.this.mSessionId) {
                ProgressBar progressBar = (ProgressBar) InstallInstalling.this.requireViewById(R.id.progress);
                progressBar.setMax(Integer.MAX_VALUE);
                progressBar.setProgress((int) (2.1474836E9f * progress));
            }
        }

        @Override // android.content.pm.PackageInstaller.SessionCallback
        public void onFinished(int sessionId, boolean success) {
        }
    }

    private final class InstallingAsyncTask extends AsyncTask<Void, Void, PackageInstaller.Session> {
        volatile boolean isDone;

        private InstallingAsyncTask() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public PackageInstaller.Session doInBackground(Void... params) {
            try {
                PackageInstaller.Session session = InstallInstalling.this.getPackageManager().getPackageInstaller().openSession(InstallInstalling.this.mSessionId);
                session.setStagingProgress(0.0f);
                try {
                    try {
                        File file = new File(InstallInstalling.this.mPackageURI.getPath());
                        InputStream in = new FileInputStream(file);
                        try {
                            long sizeBytes = file.length();
                            OutputStream out = session.openWrite("PackageInstaller", 0L, sizeBytes);
                            try {
                                byte[] buffer = new byte[1048576];
                                while (true) {
                                    int numRead = in.read(buffer);
                                    if (numRead == -1) {
                                        session.fsync(out);
                                        break;
                                    }
                                    if (isCancelled()) {
                                        session.close();
                                        break;
                                    }
                                    out.write(buffer, 0, numRead);
                                    if (sizeBytes > 0) {
                                        float fraction = numRead / sizeBytes;
                                        session.addProgress(fraction);
                                    }
                                }
                                if (out != null) {
                                    out.close();
                                }
                                in.close();
                                synchronized (this) {
                                    this.isDone = true;
                                    notifyAll();
                                }
                                return session;
                            } finally {
                            }
                        } catch (Throwable th) {
                            try {
                                in.close();
                            } catch (Throwable th2) {
                                th.addSuppressed(th2);
                            }
                            throw th;
                        }
                    } catch (IOException | SecurityException e) {
                        Log.e(InstallInstalling.LOG_TAG, "Could not write package", e);
                        session.close();
                        synchronized (this) {
                            this.isDone = true;
                            notifyAll();
                            return null;
                        }
                    }
                } catch (Throwable th3) {
                    synchronized (this) {
                        this.isDone = true;
                        notifyAll();
                        throw th3;
                    }
                }
            } catch (IOException e2) {
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Type inference failed for: r1v9, types: [android.content.Context, com.android.packageinstaller.InstallInstalling] */
        @Override // android.os.AsyncTask
        public void onPostExecute(PackageInstaller.Session session) {
            if (session == null) {
                InstallInstalling.this.getPackageManager().getPackageInstaller().abandonSession(InstallInstalling.this.mSessionId);
                if (!isCancelled()) {
                    InstallInstalling.this.launchFailure(-2, null);
                    return;
                }
                return;
            }
            Intent broadcastIntent = new Intent(InstallInstalling.BROADCAST_ACTION);
            broadcastIntent.setFlags(268435456);
            broadcastIntent.setPackage(InstallInstalling.this.getPackageName());
            broadcastIntent.putExtra("EventResultPersister.EXTRA_ID", InstallInstalling.this.mInstallId);
            ?? r1 = InstallInstalling.this;
            PendingIntent pendingIntent = PendingIntent.getBroadcast(r1, ((InstallInstalling) r1).mInstallId, broadcastIntent, 134217728);
            session.commit(pendingIntent.getIntentSender());
            InstallInstalling.this.mCancelButton.setEnabled(false);
            InstallInstalling.this.setFinishOnTouchOutside(false);
        }
    }
}
