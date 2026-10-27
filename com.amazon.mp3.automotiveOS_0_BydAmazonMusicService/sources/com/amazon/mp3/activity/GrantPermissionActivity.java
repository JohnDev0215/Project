package com.amazon.mp3.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import com.amazon.mp3.util.extensions.ContextKt;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes3.dex */
public class GrantPermissionActivity extends AppCompatActivity {
    protected static final Bundle ALL_GRANTED_SUCCESS_BUNDLE;
    public static final String INTENT_EXTRA_PERMISSIONS_TO_GRANT;
    public static final String INTENT_EXTRA_RESULT_CALLBACK;
    protected static final String SHARED_PREF_NAME;
    protected boolean mLaunchedExternalSettings = false;
    protected PermissionResultReceiver mPermissionResultCallback;

    static {
        String simpleName = GrantPermissionActivity.class.getSimpleName();
        SHARED_PREF_NAME = "com.amazon.mp3_" + GrantPermissionActivity.class.getSimpleName();
        INTENT_EXTRA_PERMISSIONS_TO_GRANT = simpleName + ".PERMISSIONS_TO_GRANT";
        INTENT_EXTRA_RESULT_CALLBACK = simpleName + ".RESULT_CALLBACK";
        Bundle bundle = new Bundle(1);
        ALL_GRANTED_SUCCESS_BUNDLE = bundle;
        bundle.putStringArray("DENIED_PERMISSIONS", new String[0]);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String[] stringArrayExtra = intent.getStringArrayExtra(INTENT_EXTRA_PERMISSIONS_TO_GRANT);
        this.mPermissionResultCallback = (PermissionResultReceiver) intent.getParcelableExtra(INTENT_EXTRA_RESULT_CALLBACK);
        requestPermissions(savedInstanceState, stringArrayExtra);
    }

    protected void requestPermissions(final Bundle savedInstanceState, final String[] permissions) {
        if (savedInstanceState == null) {
            ActivityCompat.requestPermissions(this, permissions, 0);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(final int requestCode, final String[] permissions, final int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (this.mPermissionResultCallback != null) {
            ArrayList arrayList = new ArrayList(permissions.length);
            boolean z = false;
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == -1) {
                    arrayList.add(permissions[i]);
                }
            }
            Bundle bundle = new Bundle();
            bundle.putStringArray("DENIED_PERMISSIONS", (String[]) arrayList.toArray(new String[arrayList.size()]));
            if (permissions.length != 0 && arrayList.isEmpty()) {
                z = true;
            }
            this.mPermissionResultCallback.send(z ? 1 : -2, bundle);
        }
        finish();
    }

    public static boolean areAllGranted(final Context context, final String[] permissions) {
        for (String str : permissions) {
            if (!ContextKt.isPermissionGranted(context, str)) {
                return false;
            }
        }
        return true;
    }

    public static void requestIfNotGranted(final Context context, final String permission, final PermissionResultReceiver resultReceiver) {
        requestIfNotGranted(context, new String[]{permission}, resultReceiver);
    }

    public static void requestIfNotGranted(final Context context, final String[] permissions, final PermissionResultReceiver resultReceiver) {
        ArrayList<String> permissionsToRequest = getPermissionsToRequest(context, permissions);
        if (!permissionsToRequest.isEmpty()) {
            startGrantPermissionsActivity(context, GrantPermissionActivity.class, permissionsToRequest, resultReceiver);
        } else if (resultReceiver != null) {
            resultReceiver.send(1, ALL_GRANTED_SUCCESS_BUNDLE);
        }
    }

    protected void openSettings(final Context context) {
        Intent intent = new Intent();
        Uri uri = Uri.parse("package:" + context.getPackageName());
        intent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.addCategory("android.intent.category.DEFAULT");
        intent.setData(uri);
        intent.addFlags(268435456);
        intent.addFlags(1073741824);
        intent.addFlags(8388608);
        context.startActivity(intent);
        this.mLaunchedExternalSettings = true;
    }

    protected void showPermissionsDialog(final boolean launchExternalSettings, final String[] permissions, final int dialogMessageId, final int dialogPositiveButtonId, final String permission) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.activity.GrantPermissionActivity$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnCancelListener
            public final void onCancel(DialogInterface dialogInterface) {
                this.f$0.lambda$showPermissionsDialog$0(permission, dialogInterface);
            }
        });
        builder.setMessage(dialogMessageId);
        builder.setPositiveButton(dialogPositiveButtonId, new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.GrantPermissionActivity$$ExternalSyntheticLambda1
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                this.f$0.lambda$showPermissionsDialog$1(launchExternalSettings, permissions, dialogInterface, i);
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPermissionsDialog$0(String str, DialogInterface dialogInterface) {
        if (this.mPermissionResultCallback != null) {
            Bundle bundle = new Bundle();
            bundle.putStringArray("DENIED_PERMISSIONS", new String[]{str});
            this.mPermissionResultCallback.send(0, bundle);
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$showPermissionsDialog$1(boolean z, String[] strArr, DialogInterface dialogInterface, int i) {
        if (z) {
            openSettings(this);
        } else {
            ActivityCompat.requestPermissions(this, strArr, 0);
        }
    }

    protected void markPermissionRequested(final String key) {
        SharedPreferences.Editor editorEdit = getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editorEdit.putBoolean(key, false);
        editorEdit.commit();
    }

    protected boolean isFirstTimeRequestingPermission(final String key) {
        return getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean(key, true);
    }

    protected static ArrayList<String> getPermissionsToRequest(final Context context, final String[] permissions) {
        ArrayList<String> arrayList = new ArrayList<>(permissions.length);
        for (String str : permissions) {
            if (!ContextKt.isPermissionGranted(context, str)) {
                arrayList.add(str);
            }
        }
        return arrayList;
    }

    protected static void startGrantPermissionsActivity(final Context context, final Class activity, final ArrayList<String> permissionsToRequest, final PermissionResultReceiver resultReceiver) {
        Intent intent = new Intent(context, (Class<?>) activity);
        intent.putExtra(INTENT_EXTRA_PERMISSIONS_TO_GRANT, (String[]) permissionsToRequest.toArray(new String[permissionsToRequest.size()]));
        if (!(context instanceof Activity)) {
            intent.addFlags(268435456);
        }
        if (resultReceiver != null) {
            intent.putExtra(INTENT_EXTRA_RESULT_CALLBACK, resultReceiver);
        }
        context.startActivity(intent);
    }

    protected void checkIfPermissionGrantedAndFinish(final String permission) {
        if (this.mLaunchedExternalSettings) {
            if (this.mPermissionResultCallback != null) {
                Bundle bundle = new Bundle();
                bundle.putStringArray("DENIED_PERMISSIONS", new String[]{permission});
                this.mPermissionResultCallback.send(ContextKt.isPermissionGranted(this, permission) ? 1 : -2, bundle);
            }
            finish();
        }
    }

    public static class PermissionResultReceiver implements Parcelable {
        public static final Parcelable.Creator<PermissionResultReceiver> CREATOR = new Parcelable.Creator<PermissionResultReceiver>() { // from class: com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PermissionResultReceiver createFromParcel(final Parcel in) {
                return new PermissionResultReceiver((ResultReceiver) ResultReceiver.CREATOR.createFromParcel(in));
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public PermissionResultReceiver[] newArray(final int size) {
                return new PermissionResultReceiver[size];
            }
        };
        protected final ResultReceiver mResultReceiver;

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        protected void onDenied(final String[] permissions) {
        }

        protected void onGranted() {
        }

        protected PermissionResultReceiver() {
            this(new Handler(Looper.getMainLooper()));
        }

        protected PermissionResultReceiver(Handler handler) {
            this.mResultReceiver = new ResultReceiver(handler) { // from class: com.amazon.mp3.activity.GrantPermissionActivity.PermissionResultReceiver.1
                @Override // android.os.ResultReceiver
                protected void onReceiveResult(final int resultCode, final Bundle resultData) {
                    super.onReceiveResult(resultCode, resultData);
                    if (resultCode == 1) {
                        PermissionResultReceiver.this.onGranted();
                    } else {
                        PermissionResultReceiver.this.onDenied(resultData.getStringArray("DENIED_PERMISSIONS"));
                    }
                }
            };
        }

        public final void send(final int resultCode, final Bundle resultData) {
            this.mResultReceiver.send(resultCode, resultData);
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(final Parcel dest, final int flags) {
            this.mResultReceiver.writeToParcel(dest, flags);
        }

        protected PermissionResultReceiver(ResultReceiver resultReceiver) {
            this.mResultReceiver = resultReceiver;
        }
    }
}
