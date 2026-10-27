package com.android.packageinstaller;

import android.app.Activity;
import android.app.ActivityThread;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.IPackageDeleteObserver2;
import android.content.pm.VersionedPackage;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.UserHandle;
import android.util.Log;
import android.widget.Toast;
import com.android.packageinstaller.EventResultPersister;

/* JADX INFO: loaded from: classes2.dex */
public class UninstallUninstalling extends Activity implements EventResultPersister.EventResultObserver {
    private static final String BROADCAST_ACTION = "com.android.packageinstaller.ACTION_UNINSTALL_COMMIT";
    static final String EXTRA_APP_LABEL = "com.android.packageinstaller.extra.APP_LABEL";
    static final String EXTRA_KEEP_DATA = "com.android.packageinstaller.extra.KEEP_DATA";
    private static final String LOG_TAG = UninstallUninstalling.class.getSimpleName();
    private static final String UNINSTALL_ID = "com.android.packageinstaller.UNINSTALL_ID";
    private ApplicationInfo mAppInfo;
    private IBinder mCallback;
    private String mLabel;
    private boolean mReturnResult;
    private int mUninstallId;

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFinishOnTouchOutside(false);
        this.mAppInfo = (ApplicationInfo) getIntent().getParcelableExtra(PackageUtil.INTENT_ATTR_APPLICATION_INFO);
        this.mCallback = getIntent().getIBinderExtra("android.content.pm.extra.CALLBACK");
        this.mReturnResult = getIntent().getBooleanExtra("android.intent.extra.RETURN_RESULT", false);
        this.mLabel = getIntent().getStringExtra(EXTRA_APP_LABEL);
        try {
            if (savedInstanceState == null) {
                boolean allUsers = getIntent().getBooleanExtra("android.intent.extra.UNINSTALL_ALL_USERS", false);
                boolean keepData = getIntent().getBooleanExtra(EXTRA_KEEP_DATA, false);
                UserHandle user = (UserHandle) getIntent().getParcelableExtra("android.intent.extra.USER");
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                Fragment prev = getFragmentManager().findFragmentByTag("dialog");
                if (prev != null) {
                    transaction.remove(prev);
                }
                DialogFragment dialog = new UninstallUninstallingFragment();
                dialog.setCancelable(false);
                dialog.show(transaction, "dialog");
                this.mUninstallId = UninstallEventReceiver.addObserver(this, Integer.MIN_VALUE, this);
                Intent broadcastIntent = new Intent(BROADCAST_ACTION);
                broadcastIntent.setFlags(268435456);
                broadcastIntent.putExtra("EventResultPersister.EXTRA_ID", this.mUninstallId);
                broadcastIntent.setPackage(getPackageName());
                PendingIntent pendingIntent = PendingIntent.getBroadcast(this, this.mUninstallId, broadcastIntent, 134217728);
                int flags = allUsers ? 2 : 0;
                try {
                    ActivityThread.getPackageManager().getPackageInstaller().uninstall(new VersionedPackage(this.mAppInfo.packageName, -1), getPackageName(), (keepData ? 1 : 0) | flags, pendingIntent.getIntentSender(), user.getIdentifier());
                } catch (RemoteException e) {
                    e.rethrowFromSystemServer();
                }
                return;
            }
            int i = savedInstanceState.getInt(UNINSTALL_ID);
            this.mUninstallId = i;
            UninstallEventReceiver.addObserver(this, i, this);
        } catch (EventResultPersister.OutOfIdsException | IllegalArgumentException e2) {
            Log.e(LOG_TAG, "Fails to start uninstall", e2);
            onResult(1, -1, null);
        }
    }

    @Override // android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(UNINSTALL_ID, this.mUninstallId);
    }

    @Override // android.app.Activity
    public void onBackPressed() {
    }

    @Override // com.android.packageinstaller.EventResultPersister.EventResultObserver
    public void onResult(int status, int legacyStatus, String message) {
        IBinder iBinder = this.mCallback;
        if (iBinder != null) {
            IPackageDeleteObserver2 observer = IPackageDeleteObserver2.Stub.asInterface(iBinder);
            try {
                observer.onPackageDeleted(this.mAppInfo.packageName, legacyStatus, message);
            } catch (RemoteException e) {
            }
        } else {
            if (this.mReturnResult) {
                Intent result = new Intent();
                result.putExtra("android.intent.extra.INSTALL_RESULT", legacyStatus);
                setResult(status == 0 ? -1 : 1, result);
            } else if (status != 0) {
                Toast.makeText(this, getString(R.string.uninstall_failed_app, new Object[]{this.mLabel}), 1).show();
            }
        }
        finish();
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        UninstallEventReceiver.removeObserver(this, this.mUninstallId);
        super.onDestroy();
    }

    public static class UninstallUninstallingFragment extends DialogFragment {
        @Override // android.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
            dialogBuilder.setCancelable(false);
            dialogBuilder.setMessage(getActivity().getString(R.string.uninstalling_app, new Object[]{((UninstallUninstalling) getActivity()).mLabel}));
            Dialog dialog = dialogBuilder.create();
            dialog.setCanceledOnTouchOutside(false);
            return dialog;
        }
    }
}
