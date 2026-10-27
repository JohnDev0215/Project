package com.android.permissioncontroller.permission.ui.wear;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Space;
import androidx.wear.ble.view.AcceptDenyDialog;
import androidx.wear.ble.view.WearableDialogHelper;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class GrantPermissionsWearViewHandler implements GrantPermissionsViewHandler, DialogInterface.OnClickListener {
    private static final String CURRENT_PAGE_TEXT = "current_page_text";
    private static final String DIALOG_BUNDLE = "dialog_bundle";
    private static final String GROUP_NAME = "group_name";
    private static final String ICON = "icon";
    private static final String MESSAGE = "message";
    private static final String SHOW_DO_NOT_ASK = "show_do_not_ask";
    private static final String TAG = "GrantPermsWatchViewH";
    private static final String WATCH_HANDLER_BUNDLE = "watch_handler_bundle";
    private final Context mContext;
    private String mCurrentPageText;
    private Dialog mDialog;
    private String mGroupName;
    private Icon mIcon;
    private CharSequence mMessage;
    private GrantPermissionsViewHandler.ResultListener mResultListener;
    private boolean mShowDoNotAsk;

    public GrantPermissionsWearViewHandler(Context context) {
        this.mContext = context;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public GrantPermissionsWearViewHandler setResultListener(GrantPermissionsViewHandler.ResultListener listener) {
        this.mResultListener = listener;
        return this;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public View createView() {
        return new Space(this.mContext);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateWindowAttributes(WindowManager.LayoutParams outLayoutParams) {
        outLayoutParams.width = -1;
        outLayoutParams.height = -1;
        outLayoutParams.format = -1;
        outLayoutParams.type = 2008;
        outLayoutParams.flags |= 128;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUi(String groupName, int groupCount, int groupIndex, Icon icon, CharSequence message, CharSequence detailMessage, boolean[] buttonVisibilities) {
        String string;
        boolean showDoNotAsk = buttonVisibilities[4];
        if (Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "updateUi() - groupName: " + groupName + ", groupCount: " + groupCount + ", groupIndex: " + groupIndex + ", icon: " + icon + ", message: " + ((Object) message) + ", showDoNotAsk: " + showDoNotAsk);
        }
        this.mGroupName = groupName;
        this.mShowDoNotAsk = showDoNotAsk;
        this.mMessage = message;
        this.mIcon = icon;
        if (groupCount > 1) {
            string = this.mContext.getString(R.string.current_permission_template, Integer.valueOf(groupIndex + 1), Integer.valueOf(groupCount));
        } else {
            string = null;
        }
        this.mCurrentPageText = string;
        showDialog(null);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUiList(List<String> groupName) {
    }

    private void showDialog(Bundle savedInstanceState) {
        TypedArray a = this.mContext.obtainStyledAttributes(new int[]{android.R.attr.textColorPrimary});
        int color = a.getColor(0, this.mContext.getColor(android.R.color.white));
        a.recycle();
        Icon icon = this.mIcon;
        Drawable drawable = icon == null ? null : icon.setTint(color).loadDrawable(this.mContext);
        SpannableStringBuilder ssb = new SpannableStringBuilder();
        if (!TextUtils.isEmpty(this.mCurrentPageText)) {
            ssb.append(this.mCurrentPageText, new TextAppearanceSpan(this.mContext, R.style.BreadcrumbText), 33);
            ssb.append('\n');
        }
        if (!TextUtils.isEmpty(this.mMessage)) {
            ssb.append(this.mMessage, new TextAppearanceSpan(this.mContext, R.style.TitleText), 33);
        }
        Dialog dialog = this.mDialog;
        if (dialog != null) {
            dialog.dismiss();
            this.mDialog = null;
        }
        if (this.mShowDoNotAsk) {
            AlertDialog alertDialog = new WearableDialogHelper.DialogBuilder(this.mContext).setPositiveIcon(R.drawable.confirm_button).setNeutralIcon(R.drawable.cancel_button).setNegativeIcon(R.drawable.deny_button).setTitle(ssb).setIcon(drawable).setPositiveButton(R.string.grant_dialog_button_allow, this).setNeutralButton(R.string.grant_dialog_button_deny, this).setNegativeButton(R.string.grant_dialog_button_deny_dont_ask_again, this).show();
            alertDialog.getButton(-1).setId(R.id.permission_allow_button);
            alertDialog.getButton(-3).setId(R.id.permission_deny_button);
            alertDialog.getButton(-2).setId(R.id.permission_deny_dont_ask_again_button);
            this.mDialog = alertDialog;
        } else {
            AcceptDenyDialog acceptDenyDialog = new AcceptDenyDialog(this.mContext);
            acceptDenyDialog.setTitle(ssb);
            acceptDenyDialog.setIcon(drawable);
            acceptDenyDialog.setPositiveButton(this);
            acceptDenyDialog.setNegativeButton(this);
            acceptDenyDialog.show();
            acceptDenyDialog.getButton(-1).setId(R.id.permission_allow_button);
            acceptDenyDialog.getButton(-2).setId(R.id.permission_deny_button);
            this.mDialog = acceptDenyDialog;
        }
        this.mDialog.setCancelable(false);
        if (savedInstanceState != null) {
            this.mDialog.onRestoreInstanceState(savedInstanceState);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void saveInstanceState(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        bundle2.putByte(SHOW_DO_NOT_ASK, this.mShowDoNotAsk ? (byte) 1 : (byte) 0);
        bundle2.putString(GROUP_NAME, this.mGroupName);
        bundle2.putBundle(DIALOG_BUNDLE, this.mDialog.onSaveInstanceState());
        bundle.putBundle(WATCH_HANDLER_BUNDLE, bundle2);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void loadInstanceState(Bundle savedInstanceState) {
        Bundle b = savedInstanceState.getBundle(WATCH_HANDLER_BUNDLE);
        this.mShowDoNotAsk = b.getByte(SHOW_DO_NOT_ASK) == 1;
        this.mGroupName = b.getString(GROUP_NAME);
        showDialog(b.getBundle(DIALOG_BUNDLE));
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onBackPressed() {
        notifyListener(2);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onDestroy() {
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onConfigurationChanged(Configuration config) {
    }

    @Override // android.content.DialogInterface.OnClickListener
    public void onClick(DialogInterface dialog, int which) {
        if (which == -3) {
            notifyListener(2);
            return;
        }
        if (which != -2) {
            if (which == -1) {
                notifyListener(0);
            }
        } else if (dialog instanceof AlertDialog) {
            notifyListener(3);
        } else {
            notifyListener(2);
        }
    }

    private void notifyListener(int result) {
        GrantPermissionsViewHandler.ResultListener resultListener = this.mResultListener;
        if (resultListener != null) {
            resultListener.onPermissionGrantResult(this.mGroupName, result);
        }
    }
}
