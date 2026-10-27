package com.android.permissioncontroller.permission.ui.auto;

import android.app.AlertDialog;
import android.app.UiModeManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.res.Configuration;
import android.os.SystemProperties;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.GrantPermissionsActivity;
import com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes8.dex */
public class PermissionDialog {
    private static final int DIALOG_HEIGHT = 514;
    private static final int DIALOG_WIDTH = 560;
    private static final String TAG = PermissionDialog.class.getSimpleName();
    private static final String WHEEL_POSITION = "persist.vendor.byd.cfg.huc.steering.wheel.position";
    private String dialogTitle;
    SharedPreferences.Editor editor;
    private ListViewAdapter mAdapter;
    private Button mBtnCancel;
    private Button mBtnOk;
    private final Context mContext;
    private int mCurrentDayNightMode;
    private AlertDialog mDialog;
    private AlertDialog.Builder mDialogBuilder;
    private ListView mDialogListView;
    private TextView mDialogTitle;
    private View mDialogView;
    private LayoutInflater mInflater;
    private PermissionItem mPermissionItem;
    private GrantPermissionsViewHandler.ResultListener mResultListener;
    SharedPreferences mSp;
    private UiModeManager mUiModeManager;
    private List<PermissionItem> mPermissionItemList = new ArrayList();
    private List<String> mAgreePermissionList = new ArrayList();
    private List<String> mDisagreePermissionList = new ArrayList();
    private boolean mIsOffAskAgain = true;
    private boolean mIsNotifyOff = false;
    private HashMap<String, ArrayList<String>> mPermissionItems = new HashMap<>();
    private List<String> mGroupNameList = new ArrayList();
    boolean mSwitchStatus = true;
    private boolean mUiModeChanged = false;
    private List<String> mNameToRightList = new ArrayList();

    public PermissionDialog(Context context, GrantPermissionsViewHandler.ResultListener listener) {
        this.mContext = context;
        UiModeManager uiModeManager = (UiModeManager) context.getSystemService("uimode");
        this.mUiModeManager = uiModeManager;
        this.mCurrentDayNightMode = uiModeManager.getNightMode();
        Log.e(TAG, "currentUiMode: " + this.mCurrentDayNightMode);
        this.mResultListener = listener;
        SharedPreferences sharedPreferences = this.mContext.getSharedPreferences(GrantPermissionsActivity.APP_NAME, 0);
        this.mSp = sharedPreferences;
        SharedPreferences.Editor editorEdit = sharedPreferences.edit();
        this.editor = editorEdit;
        editorEdit.clear();
        this.editor.apply();
        initLanguageList();
    }

    private void initLanguageList() {
        this.mNameToRightList.add("pt");
        this.mNameToRightList.add("ru");
        this.mNameToRightList.add("it");
        this.mNameToRightList.add("in");
    }

    public void showDialog() {
        if (this.mDialog != null) {
            Log.d(TAG, "showDialog mDialog ! = null");
            this.mDialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        initList();
        this.mDialogBuilder = new AlertDialog.Builder(this.mContext);
        this.mInflater = LayoutInflater.from(this.mContext);
        String wheelPosition = SystemProperties.get(WHEEL_POSITION, (String) null);
        Log.e(TAG, "showDialog: wheelPosition " + wheelPosition);
        if (wheelPosition.equals("0")) {
            Log.e(TAG, "showDialog: left position");
            this.mAdapter = new ListViewAdapter(this.mContext, R.layout.permission_dialog_listview_item_left, this.mPermissionItemList);
            this.mDialogView = this.mInflater.inflate(R.layout.permission_dialog_left, (ViewGroup) null);
        } else {
            Log.e(TAG, "showDialog: right position");
            this.mAdapter = new ListViewAdapter(this.mContext, R.layout.permission_dialog_listview_item, this.mPermissionItemList);
            this.mDialogView = this.mInflater.inflate(R.layout.permission_dialog, (ViewGroup) null);
        }
        this.mDialogTitle = (TextView) this.mDialogView.findViewById(R.id.permission_dialog_title);
        this.mDialogListView = (ListView) this.mDialogView.findViewById(R.id.permission_dialog_content);
        this.mBtnOk = (Button) this.mDialogView.findViewById(R.id.btn_ok);
        this.mBtnCancel = (Button) this.mDialogView.findViewById(R.id.btn_cancel);
        this.mDialogListView.setAdapter((ListAdapter) this.mAdapter);
        this.mDialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.PermissionDialog.1
            @Override // android.widget.AdapterView.OnItemClickListener
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                PermissionItem permissionItem = (PermissionItem) PermissionDialog.this.mPermissionItemList.get(position);
                int index = PermissionDialog.this.mPermissionItemList.indexOf(permissionItem);
                Log.i(PermissionDialog.TAG, "index: " + index + ", item: " + permissionItem.getName() + ", click_before_status: " + permissionItem.getCheckStatus());
                if (permissionItem.getCheckStatus()) {
                    PermissionItem newItem = new PermissionItem(permissionItem.getName(), PermissionDialog.this.getPermissionName(permissionItem.getName()), PermissionDialog.this.getDetailMessage(permissionItem.getName()), R.drawable.img_switch_bg_normal, false);
                    PermissionDialog.this.mPermissionItemList.set(index, newItem);
                    PermissionDialog permissionDialog = PermissionDialog.this;
                    permissionDialog.notifyDataSetChanged(position, permissionDialog.mDialogListView);
                    PermissionDialog.this.mAgreePermissionList.remove(permissionItem.getName());
                    PermissionDialog.this.editor.putBoolean(permissionItem.getName(), false);
                    Log.e(PermissionDialog.TAG, "onItemClick:  " + permissionItem.getName() + " true -> false");
                } else {
                    PermissionItem newItem2 = new PermissionItem(permissionItem.getName(), PermissionDialog.this.getPermissionName(permissionItem.getName()), PermissionDialog.this.getDetailMessage(permissionItem.getName()), R.drawable.img_switch_bg_select, true);
                    PermissionDialog.this.mPermissionItemList.set(index, newItem2);
                    PermissionDialog permissionDialog2 = PermissionDialog.this;
                    permissionDialog2.notifyDataSetChanged(position, permissionDialog2.mDialogListView);
                    PermissionDialog.this.mAgreePermissionList.add(permissionItem.getName());
                    PermissionDialog.this.editor.putBoolean(permissionItem.getName(), true);
                    Log.e(PermissionDialog.TAG, "onItemClick:  " + permissionItem.getName() + "false -> true");
                }
                PermissionDialog.this.editor.apply();
            }
        });
        this.mBtnOk.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.PermissionDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.d(PermissionDialog.TAG, "onClick: OK");
                for (PermissionItem s : PermissionDialog.this.mPermissionItemList) {
                    if (s.getCheckStatus()) {
                        PermissionDialog.this.mAgreePermissionList.add(s.getName());
                    } else {
                        PermissionDialog.this.mDisagreePermissionList.add(s.getName());
                    }
                }
                if (PermissionDialog.this.mDialog != null) {
                    Log.d(PermissionDialog.TAG, "ok mDialog ! = null");
                    PermissionDialog.this.mDialog.setOnDismissListener(null);
                    PermissionDialog.this.mDialog.dismiss();
                    PermissionDialog.this.mDialog = null;
                }
                PermissionDialog permissionDialog = PermissionDialog.this;
                permissionDialog.requestPermission(permissionDialog.mAgreePermissionList, 0);
                if (PermissionDialog.this.mIsOffAskAgain) {
                    Log.d(PermissionDialog.TAG, "ok is onClick: mIsOffAskAgain is true ,can be ask again");
                    PermissionDialog permissionDialog2 = PermissionDialog.this;
                    permissionDialog2.requestPermission(permissionDialog2.mDisagreePermissionList, 2);
                } else {
                    Log.d(PermissionDialog.TAG, "ok is onClick: mIsOffAskAgain is false ,can not be ask again");
                    PermissionDialog permissionDialog3 = PermissionDialog.this;
                    permissionDialog3.requestPermission(permissionDialog3.mDisagreePermissionList, 3);
                }
            }
        });
        this.mBtnCancel.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.auto.PermissionDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Log.i(PermissionDialog.TAG, "onClick: CANCEL");
                List<String> nextShowList = new ArrayList<>();
                if (PermissionDialog.this.mDialog != null) {
                    Log.i(PermissionDialog.TAG, "Cancel mDialog ! = null");
                    PermissionDialog.this.mDialog.setOnDismissListener(null);
                    PermissionDialog.this.mDialog.dismiss();
                    PermissionDialog.this.mDialog = null;
                }
                for (PermissionItem s : PermissionDialog.this.mPermissionItemList) {
                    nextShowList.add(s.getName());
                    PermissionDialog.this.requestPermission(nextShowList, -1);
                }
            }
        });
        if (!this.mUiModeChanged) {
            this.dialogTitle = setDialogTitle(Locale.getDefault().getLanguage());
        }
        Log.i(TAG, "dialogTitle: " + this.dialogTitle);
        this.mDialogTitle.setText(this.dialogTitle);
        AlertDialog alertDialogCreate = this.mDialogBuilder.create();
        this.mDialog = alertDialogCreate;
        alertDialogCreate.setCanceledOnTouchOutside(false);
        this.mDialog.setCancelable(false);
        this.mDialog.show();
        this.mDialog.getWindow().setLayout(dip2px(this.mContext, 560.0f), dip2px(this.mContext, 514.0f));
        this.mDialog.getWindow().setContentView(this.mDialogView);
        this.mDialog.getWindow().setGravity(17);
        this.mDialog.getWindow().clearFlags(131072);
        this.mUiModeChanged = false;
    }

    private void initList() {
        int imgId;
        List<PermissionItem> list = this.mPermissionItemList;
        if (list != null) {
            list.clear();
        }
        Log.i(TAG, "initList: mUiModeChanged  " + this.mUiModeChanged);
        if (this.mUiModeChanged) {
            List<String> list2 = this.mGroupNameList;
            if (list2 == null) {
                Log.i(TAG, "initList: mGroupNameList is null ");
                return;
            }
            for (String groupName : list2) {
                Log.i(TAG, "initList(config changed): name: " + groupName);
                boolean z = this.mSp.getBoolean(groupName, true);
                this.mSwitchStatus = z;
                if (z) {
                    imgId = R.drawable.img_switch_bg_select;
                } else {
                    imgId = R.drawable.img_switch_bg_normal;
                }
                PermissionItem permissionItem = new PermissionItem(groupName, getPermissionName(groupName), getDetailMessage(groupName), imgId, this.mSwitchStatus);
                this.mPermissionItem = permissionItem;
                this.mPermissionItemList.add(permissionItem);
            }
            return;
        }
        List<String> groupNameList = GrantPermissionsAutoViewHandler.getGroupNameList();
        this.mGroupNameList = groupNameList;
        for (String groupName2 : groupNameList) {
            Log.i(TAG, "initList: name: " + groupName2);
            PermissionItem permissionItem2 = new PermissionItem(groupName2, getPermissionName(groupName2), getDetailMessage(groupName2), R.drawable.img_switch_bg_select, true);
            this.mPermissionItem = permissionItem2;
            this.mPermissionItemList.add(permissionItem2);
        }
    }

    public String getPermissionName(String name) {
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(name, 0);
            String permissionName = permissionGroupInfo.loadLabel(packageManager).toString();
            return permissionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getDetailMessage(String name) {
        String detailMessage = null;
        PackageManager packageManager = this.mContext.getPackageManager();
        try {
            PermissionGroupInfo permissionGroupInfo = packageManager.getPermissionGroupInfo(name, 0);
            detailMessage = permissionGroupInfo.loadDescription(packageManager).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "getDetailMessage: " + detailMessage);
        return detailMessage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChanged(int position, ListView listView) {
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        int lastVisiblePosition = listView.getLastVisiblePosition();
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            View view = listView.getChildAt(position - firstVisiblePosition);
            this.mAdapter.getView(position, view, listView);
        }
    }

    public void onBackPressed() {
        if (this.mDialog != null) {
            Log.d(TAG, "onBackPressed mDialog ! = null");
            this.mDialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            this.mDialog = null;
        }
    }

    public void onDestroy() {
        Log.d(TAG, "onDestroy()");
        AlertDialog alertDialog = this.mDialog;
        if (alertDialog != null) {
            alertDialog.setOnDismissListener(null);
            this.mDialog.dismiss();
            this.mDialog = null;
        }
        if (this.mResultListener != null) {
            this.mResultListener = null;
        }
    }

    public void requestPermission(List<String> list, int result) {
        for (String groupName : list) {
            GrantPermissionsViewHandler.ResultListener resultListener = this.mResultListener;
            if (resultListener != null) {
                resultListener.onPermissionGrantResult(groupName, result);
                Log.d(TAG, "createListItem setOnItemClickedListener groupName : " + groupName + " result : " + result);
            }
        }
    }

    public static int dip2px(Context context, float dpValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) ((dpValue * scale) + 0.5f);
    }

    public void onConfigurationChanged(Configuration newConfig) {
        Log.d(TAG, "onConfigurationChanged()");
        int dayNightMode = this.mUiModeManager.getNightMode();
        if (this.mCurrentDayNightMode != dayNightMode) {
            this.mUiModeChanged = true;
            showDialog();
            this.mCurrentDayNightMode = dayNightMode;
        }
    }

    public String setDialogTitle(String language) {
        Log.e(TAG, "current language " + language);
        if (!this.mNameToRightList.contains(language)) {
            return GrantPermissionsActivity.APP_NAME + "\r" + this.mContext.getResources().getString(R.string.perm_management);
        }
        return this.mContext.getResources().getString(R.string.perm_management) + "\r" + GrantPermissionsActivity.APP_NAME;
    }
}
