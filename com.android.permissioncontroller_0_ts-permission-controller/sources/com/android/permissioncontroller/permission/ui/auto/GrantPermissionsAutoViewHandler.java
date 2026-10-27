package com.android.permissioncontroller.permission.ui.auto;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import com.android.car.ui.AlertDialogBuilder;
import com.android.car.ui.recyclerview.CarUiContentListItem;
import com.android.car.ui.recyclerview.CarUiListItem;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler;
import java.util.ArrayList;
import java.util.List;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.stream.IntStream;

/* JADX INFO: loaded from: classes8.dex */
public class GrantPermissionsAutoViewHandler implements GrantPermissionsViewHandler {
    private static final String ARG_BUTTON_VISIBILITIES = "ARG_BUTTON_VISIBILITIES";
    private static final String ARG_GROUP_COUNT = "ARG_GROUP_COUNT";
    private static final String ARG_GROUP_DETAIL_MESSAGE = "ARG_GROUP_DETAIL_MESSAGE";
    private static final String ARG_GROUP_ICON = "ARG_GROUP_ICON";
    private static final String ARG_GROUP_INDEX = "ARG_GROUP_INDEX";
    private static final String ARG_GROUP_MESSAGE = "ARG_GROUP_MESSAGE";
    private static final String ARG_GROUP_NAME = "ARG_GROUP_NAME";
    private static final String TAG = GrantPermissionsAutoViewHandler.class.getSimpleName();
    public static List<String> mGroupNameList = new ArrayList();
    private boolean[] mButtonVisibilities;
    private final Context mContext;
    private CharSequence mDetailMessage;
    private AlertDialog mDialog;
    private Boolean mDialogShowStatus = false;
    private int mGroupCount;
    private Icon mGroupIcon;
    private int mGroupIndex;
    private CharSequence mGroupMessage;
    private String mGroupName;
    private PermissionDialog mPermissionDialog;
    private GrantPermissionsViewHandler.ResultListener mResultListener;

    public GrantPermissionsAutoViewHandler(Context context, String appPackageName) {
        this.mContext = context;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public GrantPermissionsViewHandler setResultListener(GrantPermissionsViewHandler.ResultListener listener) {
        this.mResultListener = listener;
        this.mPermissionDialog = new PermissionDialog(this.mContext, this.mResultListener);
        return this;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public View createView() {
        return new View(this.mContext);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateWindowAttributes(WindowManager.LayoutParams outLayoutParams) {
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUi(String groupName, int groupCount, int groupIndex, Icon icon, CharSequence message, CharSequence detailMessage, boolean[] buttonVisibilities) {
        this.mGroupName = groupName;
        this.mGroupCount = groupCount;
        this.mGroupIndex = groupIndex;
        this.mGroupIcon = icon;
        this.mGroupMessage = message;
        this.mDetailMessage = detailMessage;
        this.mButtonVisibilities = buttonVisibilities;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUiList(List<String> groupName) {
        mGroupNameList = groupName;
        for (String s : groupName) {
            Log.d(TAG, "updateUiList: all groupName is " + s);
        }
        Log.i(TAG, "updateUiList: mDialogShowStatus is " + this.mDialogShowStatus);
        if (this.mDialogShowStatus.booleanValue()) {
            return;
        }
        PermissionDialog permissionDialog = this.mPermissionDialog;
        if (permissionDialog != null) {
            permissionDialog.showDialog();
        }
        this.mDialogShowStatus = true;
    }

    private void update() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        AlertDialogBuilder builder = new AlertDialogBuilder(this.mContext).setTitle(this.mGroupMessage).setSubtitle(this.mDetailMessage).setNegativeButton(R.string.grant_dialog_button_dismiss, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$GrantPermissionsAutoViewHandler$ysaVYd92KU7Tf1RjqVt-9SqiXkk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$GrantPermissionsAutoViewHandler$dl2S9v0meaxdhOaEO7KObu1bC8A
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.lambda$update$1$GrantPermissionsAutoViewHandler(dialogInterface);
            }
        });
        Icon icon = this.mGroupIcon;
        if (icon != null) {
            builder.setIcon(icon.loadDrawable(this.mContext));
        }
        List<CarUiListItem> itemList = new ArrayList<>();
        createListItem(itemList, R.string.grant_dialog_button_allow, 0, 0, 5);
        createListItem(itemList, R.string.grant_dialog_button_allow_always, 0, 1);
        createListItem(itemList, R.string.grant_dialog_button_allow_foreground, 1, 2);
        createListItem(itemList, R.string.grant_dialog_button_deny, 2, 3);
        createListItem(itemList, R.string.grant_dialog_button_deny_and_dont_ask_again, 3, 4);
        createListItem(itemList, R.string.grant_dialog_button_no_upgrade, 2, 6, 8);
        createListItem(itemList, R.string.grant_dialog_button_no_upgrade, 3, 7, 9);
        builder.setAdapter(new CarUiListItemAdapter(itemList));
        AlertDialog alertDialogCreate = builder.create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.show();
    }

    public /* synthetic */ void lambda$update$1$GrantPermissionsAutoViewHandler(DialogInterface dialog) {
        this.mDialog = null;
        this.mResultListener.onPermissionGrantResult(this.mGroupName, 2);
    }

    private void createListItem(List<CarUiListItem> list, int stringId, final int result, int... indices) {
        if (IntStream.of(indices).mapToObj(new IntFunction() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$GrantPermissionsAutoViewHandler$UL526C5At-dSb52W3F_rDpb2Ss4
            @Override // java.util.function.IntFunction
            public final Object apply(int i) {
                return this.f$0.lambda$createListItem$2$GrantPermissionsAutoViewHandler(i);
            }
        }).noneMatch(new Predicate() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$GrantPermissionsAutoViewHandler$ELIhZDxynw1kMnB9BW-ffBf8QWA
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((Boolean) obj).booleanValue();
            }
        })) {
            return;
        }
        CarUiContentListItem item = new CarUiContentListItem(CarUiContentListItem.Action.NONE);
        item.setTitle(this.mContext.getString(stringId));
        item.setOnItemClickedListener(new CarUiContentListItem.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.-$$Lambda$GrantPermissionsAutoViewHandler$0tmgGtEQJkOr96DL7cEpMDvvoIs
            @Override // com.android.car.ui.recyclerview.CarUiContentListItem.OnClickListener
            public final void onClick(CarUiContentListItem carUiContentListItem) {
                this.f$0.lambda$createListItem$4$GrantPermissionsAutoViewHandler(result, carUiContentListItem);
            }
        });
        list.add(item);
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x000d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public /* synthetic */ java.lang.Boolean lambda$createListItem$2$GrantPermissionsAutoViewHandler(int r3) {
        /*
            r2 = this;
            if (r3 < 0) goto Ld
            boolean[] r0 = r2.mButtonVisibilities
            int r1 = r0.length
            if (r3 >= r1) goto Ld
            boolean r0 = r0[r3]
            if (r0 == 0) goto Ld
            r0 = 1
            goto Le
        Ld:
            r0 = 0
        Le:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.permissioncontroller.permission.ui.auto.GrantPermissionsAutoViewHandler.lambda$createListItem$2$GrantPermissionsAutoViewHandler(int):java.lang.Boolean");
    }

    public /* synthetic */ void lambda$createListItem$4$GrantPermissionsAutoViewHandler(int result, CarUiContentListItem i) {
        this.mDialog.setOnDismissListener(null);
        this.mDialog.dismiss();
        this.mDialog = null;
        this.mResultListener.onPermissionGrantResult(this.mGroupName, result);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void saveInstanceState(Bundle arguments) {
        arguments.putString("ARG_GROUP_NAME", this.mGroupName);
        arguments.putInt("ARG_GROUP_COUNT", this.mGroupCount);
        arguments.putInt("ARG_GROUP_INDEX", this.mGroupIndex);
        arguments.putParcelable("ARG_GROUP_ICON", this.mGroupIcon);
        arguments.putCharSequence("ARG_GROUP_MESSAGE", this.mGroupMessage);
        arguments.putCharSequence(ARG_GROUP_DETAIL_MESSAGE, this.mDetailMessage);
        arguments.putBooleanArray(ARG_BUTTON_VISIBILITIES, this.mButtonVisibilities);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void loadInstanceState(Bundle savedInstanceState) {
        this.mGroupName = savedInstanceState.getString("ARG_GROUP_NAME");
        this.mGroupMessage = savedInstanceState.getCharSequence("ARG_GROUP_MESSAGE");
        this.mGroupIcon = (Icon) savedInstanceState.getParcelable("ARG_GROUP_ICON");
        this.mGroupCount = savedInstanceState.getInt("ARG_GROUP_COUNT");
        this.mGroupIndex = savedInstanceState.getInt("ARG_GROUP_INDEX");
        this.mDetailMessage = savedInstanceState.getCharSequence(ARG_GROUP_DETAIL_MESSAGE);
        this.mButtonVisibilities = savedInstanceState.getBooleanArray(ARG_BUTTON_VISIBILITIES);
        update();
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onBackPressed() {
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.dismiss();
            return;
        }
        GrantPermissionsViewHandler.ResultListener resultListener = this.mResultListener;
        if (resultListener != null) {
            resultListener.onPermissionGrantResult(this.mGroupName, 2);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onDestroy() {
        PermissionDialog permissionDialog = this.mPermissionDialog;
        if (permissionDialog != null) {
            permissionDialog.onDestroy();
        }
        if (this.mResultListener != null) {
            this.mResultListener = null;
        }
    }

    public static List<String> getGroupNameList() {
        return mGroupNameList;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onConfigurationChanged(Configuration newConfig) {
        Log.i(TAG, "onConfigurationChanged()");
        PermissionDialog permissionDialog = this.mPermissionDialog;
        if (permissionDialog != null) {
            permissionDialog.onConfigurationChanged(newConfig);
        }
    }
}
