package com.amazon.mp3.library.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import com.amazon.mp3.activity.settings.SettingsUtil;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.download.service.AutoDownloadPurchaseReceiver;
import com.amazon.mp3.download.service.AutoDownloadThread;
import com.amazon.mp3.library.provider.source.cirrus.CirrusDatabaseUtil;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.util.DialogUtil;

/* JADX INFO: loaded from: classes3.dex */
public class PostPurchaseDialog {
    private Context mContext;
    private AlertDialog mDialog;
    private boolean mIsAlbumPurchase;
    private CheckBox mDoNotRemindMeCheckBox = null;
    private DialogInterface.OnCancelListener mOnCancelListener = new DialogInterface.OnCancelListener() { // from class: com.amazon.mp3.library.dialog.PostPurchaseDialog.1
        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            PostPurchaseDialog.this.updateSettings(false);
        }
    };
    private DialogInterface.OnClickListener mOnButtonClickListener = new DialogInterface.OnClickListener() { // from class: com.amazon.mp3.library.dialog.PostPurchaseDialog.2
        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialog, int which) {
            PostPurchaseDialog.this.updateSettings(which == -1);
            if (which == -2) {
                MetricsLogger.localDownloadRefused();
            }
            PostPurchaseDialog.this.mDialog.dismiss();
        }
    };

    public PostPurchaseDialog(Context context, boolean isAlbumPurchase) {
        this.mContext = context;
        this.mIsAlbumPurchase = isAlbumPurchase;
    }

    public Dialog create() {
        if (this.mDialog != null) {
            throw new IllegalStateException("Dialog already created");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.mContext);
        builder.setOnCancelListener(this.mOnCancelListener);
        builder.setPositiveButton(R.string.dmusic_button_yes, this.mOnButtonClickListener);
        builder.setNegativeButton(R.string.dmusic_button_no, this.mOnButtonClickListener);
        builder.setTitle(R.string.dmusic_library_post_purchase_title);
        View viewInflate = View.inflate(this.mContext, R.layout.library_dialog_checkbox, null);
        builder.setView(viewInflate);
        ((TextView) viewInflate.findViewById(R.id.TextView)).setText(R.string.dmusic_library_post_purchase_msg);
        CheckBox checkBox = (CheckBox) viewInflate.findViewById(R.id.Checkbox);
        this.mDoNotRemindMeCheckBox = checkBox;
        checkBox.setText(R.string.dmusic_library_post_purchase_checkbox);
        AlertDialog alertDialogCreate = builder.create();
        this.mDialog = alertDialogCreate;
        DialogUtil.installDefaultKeyListener(alertDialogCreate);
        return this.mDialog;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSettings(boolean download) {
        boolean z = download || this.mDoNotRemindMeCheckBox.isChecked();
        if (download) {
            long mostRecentDate = CirrusDatabaseUtil.getMostRecentDate(this.mContext, "date_purchased", "cirrus");
            SettingsUtil.setLastAutoDownloadDate(this.mContext, mostRecentDate - 1);
            new AutoDownloadThread(this.mContext, AutoDownloadThread.DownloadRestrictionCriteria.EQUAL_LAST_DATE, mostRecentDate, this.mIsAlbumPurchase).start();
            SettingsUtil.setCirrusDownloadPreference(this.mContext, download);
            AutoDownloadPurchaseReceiver.setComponentEnabled(this.mContext, true);
        }
        SharedPreferences.Editor editorEdit = SettingsUtil.getPrefs(this.mContext).edit();
        editorEdit.putBoolean(this.mContext.getString(R.string.setting_key_suppress_post_purchase_dialog), z);
        editorEdit.apply();
    }
}
