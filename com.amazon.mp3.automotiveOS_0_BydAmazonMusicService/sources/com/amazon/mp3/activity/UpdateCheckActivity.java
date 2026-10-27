package com.amazon.mp3.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.config.UpdateCheck;
import com.amazon.mp3.util.BauhausDialogBuilder;
import com.amazon.mp3.util.DialogUtil;
import com.amazon.mp3.util.UniqueCodeUtil;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateCheckActivity extends DialogActivity {
    private static final int MARKET_ERROR_DIALOG_ID = UniqueCodeUtil.nextUniqueCode();
    private static boolean sAlreadyDisplayed = false;
    private static UpdateCheck.Result sLastUpdateCheckResult;
    private boolean mCritical;
    private UpdateCheck.Result mResult;
    private DialogInterface.OnClickListener mYesClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.UpdateCheckActivity.1
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            if (UpdateCheckActivity.this.showUpdateInMarket()) {
                UpdateCheckActivity.this.finish();
            }
        }
    };
    private DialogInterface.OnClickListener mCancelClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.activity.UpdateCheckActivity.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            UpdateCheckActivity.this.finish();
        }
    };
    private DialogInterface.OnCancelListener mCancelListener = new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.activity.UpdateCheckActivity.3
        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            UpdateCheckActivity.this.finish();
        }
    };

    public static void refreshUpdateInformation(Context context) {
        sLastUpdateCheckResult = null;
        UpdateCheck.Result resultCheck = UpdateCheck.check(context);
        if (resultCheck != null) {
            UpdateCheck.Version latestVersion = resultCheck.getLatestVersion();
            UpdateCheck.Version clientVersion = resultCheck.getClientVersion();
            if (latestVersion == null || clientVersion == null || !latestVersion.greaterThan(clientVersion)) {
                return;
            }
            sLastUpdateCheckResult = resultCheck;
        }
    }

    public static void showIfUpdateIsAvailable(Context context) {
        UpdateCheck.Result result = sLastUpdateCheckResult;
        if (result == null) {
            return;
        }
        boolean zUpdateIsCritical = updateIsCritical(result);
        if (zUpdateIsCritical || !sAlreadyDisplayed) {
            Intent intent = new Intent(context, (Class<?>) UpdateCheckActivity.class);
            intent.setFlags(537001984);
            context.startActivity(intent);
            if (zUpdateIsCritical) {
                return;
            }
            sLastUpdateCheckResult = null;
            sAlreadyDisplayed = true;
        }
    }

    @Override // android.app.Activity
    public void finish() {
        super.finish();
        if (this.mCritical) {
            moveTaskToBack(true);
        }
    }

    @Override // com.amazon.mp3.activity.DialogActivity, com.amazon.mp3.service.job.JobSessionActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UpdateCheck.Result resultCheck = UpdateCheck.check(this);
        this.mResult = resultCheck;
        this.mCritical = updateIsCritical(resultCheck);
    }

    @Override // com.amazon.mp3.activity.DialogActivity
    protected Dialog createDialog() {
        String str = String.format(getString(this.mCritical ? R.string.dmusic_update_check_critical_desc : R.string.dmusic_update_check_suggested_desc), this.mResult.getLatestVersion().toString());
        AlertDialog.Builder builderNewAlertDialogBuilder = newAlertDialogBuilder();
        builderNewAlertDialogBuilder.setTitle(R.string.dmusic_update_check_title);
        builderNewAlertDialogBuilder.setMessage(str);
        builderNewAlertDialogBuilder.setNegativeButton(R.string.dmusic_button_no, this.mCancelClickListener);
        builderNewAlertDialogBuilder.setPositiveButton(R.string.dmusic_button_yes, this.mYesClickListener);
        return builderNewAlertDialogBuilder.create();
    }

    protected Dialog createMarketErrorDialog() {
        BauhausDialogBuilder bauhausDialogBuilder = new BauhausDialogBuilder(this);
        bauhausDialogBuilder.setTitle(R.string.dmusic_update_check_market_missing_title);
        bauhausDialogBuilder.setMessage(R.string.dmusic_update_check_market_missing_desc);
        bauhausDialogBuilder.setOnCancelListener(this.mCancelListener);
        bauhausDialogBuilder.setPositiveButton(R.string.dmusic_button_ok, this.mCancelClickListener);
        Dialog dialogCreate = bauhausDialogBuilder.create();
        DialogUtil.installDefaultKeyListener(dialogCreate);
        DialogUtil.applyTheme(dialogCreate);
        return dialogCreate;
    }

    @Override // com.amazon.mp3.activity.DialogActivity, android.app.Activity
    protected Dialog onCreateDialog(int id) {
        if (id == MARKET_ERROR_DIALOG_ID) {
            return createMarketErrorDialog();
        }
        return super.onCreateDialog(id);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean showUpdateInMarket() {
        String marketUrl;
        UpdateCheck.Result result = this.mResult;
        if (result == null || (marketUrl = result.getMarketUrl()) == null || marketUrl.length() <= 0) {
            return true;
        }
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse(marketUrl));
            intent.setFlags(268435456);
            startActivity(intent);
            return true;
        } catch (Exception unused) {
            hide();
            int i = MARKET_ERROR_DIALOG_ID;
            removeDialog(i);
            showDialog(i);
            return false;
        }
    }

    private static boolean updateIsCritical(UpdateCheck.Result result) {
        if (result == null) {
            return false;
        }
        UpdateCheck.Version clientVersion = result.getClientVersion();
        UpdateCheck.Version criticalVersion = result.getCriticalVersion();
        if (criticalVersion != null) {
            return clientVersion.lessThan(criticalVersion);
        }
        return false;
    }
}
