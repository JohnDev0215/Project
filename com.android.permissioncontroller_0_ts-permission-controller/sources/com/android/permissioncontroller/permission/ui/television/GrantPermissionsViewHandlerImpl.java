package com.android.permissioncontroller.permission.ui.television;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public final class GrantPermissionsViewHandlerImpl implements GrantPermissionsViewHandler, View.OnClickListener {
    private static final String ARG_GROUP_NAME = "ARG_GROUP_NAME";
    private static final String TAG = GrantPermissionsViewHandlerImpl.class.getSimpleName();
    private Button mAllowAlwaysButton;
    private Button mAllowButton;
    private Button mAllowForegroundOnlyButton;
    private Button mAllowOneTimeButton;
    private final Context mContext;
    private TextView mCurrentGroupView;
    private String mGroupName;
    private Button mHardDenyButton;
    private ImageView mIconView;
    private TextView mMessageView;
    private GrantPermissionsViewHandler.ResultListener mResultListener;
    private LinearLayout mRootView;
    private Button mSoftDenyButton;

    public GrantPermissionsViewHandlerImpl(Context context, String appPackageName) {
        this.mContext = context;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public GrantPermissionsViewHandlerImpl setResultListener(GrantPermissionsViewHandler.ResultListener listener) {
        this.mResultListener = listener;
        return this;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public View createView() {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.mContext).inflate(R.layout.grant_permissions, (ViewGroup) null);
        this.mRootView = linearLayout;
        this.mMessageView = (TextView) linearLayout.findViewById(R.id.permission_message);
        this.mIconView = (ImageView) this.mRootView.findViewById(R.id.permission_icon);
        this.mCurrentGroupView = (TextView) this.mRootView.findViewById(R.id.current_page_text);
        this.mAllowButton = (Button) this.mRootView.findViewById(R.id.permission_allow_button);
        this.mAllowAlwaysButton = (Button) this.mRootView.findViewById(R.id.permission_allow_always_button);
        this.mAllowForegroundOnlyButton = (Button) this.mRootView.findViewById(R.id.permission_allow_foreground_only_button);
        this.mAllowOneTimeButton = (Button) this.mRootView.findViewById(R.id.permission_allow_one_time_button);
        this.mSoftDenyButton = (Button) this.mRootView.findViewById(R.id.permission_deny_button);
        this.mHardDenyButton = (Button) this.mRootView.findViewById(R.id.permission_deny_dont_ask_again_button);
        this.mAllowButton.setOnClickListener(this);
        this.mAllowAlwaysButton.setOnClickListener(this);
        this.mAllowForegroundOnlyButton.setOnClickListener(this);
        this.mAllowOneTimeButton.setOnClickListener(this);
        this.mSoftDenyButton.setOnClickListener(this);
        this.mHardDenyButton.setOnClickListener(this);
        return this.mRootView;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateWindowAttributes(WindowManager.LayoutParams outLayoutParams) {
        outLayoutParams.width = -1;
        outLayoutParams.height = -2;
        outLayoutParams.format = -1;
        outLayoutParams.gravity = 80;
        outLayoutParams.type = 2008;
        outLayoutParams.flags |= 128;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUi(String groupName, int groupCount, int groupIndex, Icon icon, CharSequence message, CharSequence detailMessage, boolean[] buttonVisibilities) {
        this.mGroupName = groupName;
        this.mMessageView.setText(message);
        if (icon != null) {
            this.mIconView.setImageIcon(icon);
        }
        this.mAllowButton.setVisibility(buttonVisibilities[0] ? 0 : 8);
        this.mAllowAlwaysButton.setVisibility(buttonVisibilities[1] ? 0 : 8);
        this.mAllowForegroundOnlyButton.setVisibility(buttonVisibilities[2] ? 0 : 8);
        this.mAllowOneTimeButton.setVisibility(buttonVisibilities[5] ? 0 : 8);
        this.mHardDenyButton.setVisibility(buttonVisibilities[4] ? 0 : 8);
        if (groupCount > 1) {
            this.mCurrentGroupView.setVisibility(0);
            this.mCurrentGroupView.setText(this.mContext.getString(R.string.current_permission_template, Integer.valueOf(groupIndex + 1), Integer.valueOf(groupCount)));
        } else {
            this.mCurrentGroupView.setVisibility(4);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUiList(List<String> groupName) {
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void saveInstanceState(Bundle outState) {
        outState.putString("ARG_GROUP_NAME", this.mGroupName);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void loadInstanceState(Bundle savedInstanceState) {
        this.mGroupName = savedInstanceState.getString("ARG_GROUP_NAME");
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.permission_allow_always_button /* 2131362353 */:
                this.mResultListener.onPermissionGrantResult(this.mGroupName, 0);
                break;
            case R.id.permission_allow_button /* 2131362354 */:
                this.mResultListener.onPermissionGrantResult(this.mGroupName, 0);
                break;
            case R.id.permission_allow_foreground_only_button /* 2131362355 */:
                this.mResultListener.onPermissionGrantResult(this.mGroupName, 1);
                break;
            case R.id.permission_allow_one_time_button /* 2131362356 */:
                this.mResultListener.onPermissionGrantResult(this.mGroupName, 4);
                break;
            case R.id.permission_deny_button /* 2131362359 */:
                this.mResultListener.onPermissionGrantResult(this.mGroupName, 2);
                break;
            case R.id.permission_deny_dont_ask_again_button /* 2131362360 */:
                this.mResultListener.onPermissionGrantResult(this.mGroupName, 3);
                break;
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onBackPressed() {
        GrantPermissionsViewHandler.ResultListener resultListener = this.mResultListener;
        if (resultListener != null) {
            resultListener.onPermissionGrantResult(this.mGroupName, 2);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onDestroy() {
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onConfigurationChanged(Configuration config) {
    }
}
