package com.android.permissioncontroller.permission.ui.handheld;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.os.UserHandle;
import android.text.method.LinkMovementMethod;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler;
import java.util.List;

/* JADX INFO: loaded from: classes8.dex */
public class GrantPermissionsViewHandlerImpl implements GrantPermissionsViewHandler, View.OnClickListener {
    private static final long ANIMATION_DURATION_MILLIS = 200;
    private static final String ARG_DIALOG_BUTTON_VISIBILITIES = "ARG_DIALOG_BUTTON_VISIBILITIES";
    public static final String ARG_GROUP_COUNT = "ARG_GROUP_COUNT";
    private static final String ARG_GROUP_DETAIL_MESSAGE = "ARG_GROUP_DETAIL_MESSAGE";
    public static final String ARG_GROUP_ICON = "ARG_GROUP_ICON";
    public static final String ARG_GROUP_INDEX = "ARG_GROUP_INDEX";
    public static final String ARG_GROUP_MESSAGE = "ARG_GROUP_MESSAGE";
    public static final String ARG_GROUP_NAME = "ARG_GROUP_NAME";
    private static final SparseArray<Integer> BUTTON_RES_ID_TO_NUM;
    private static final long SWITCH_TIME_MILLIS = 75;
    private final Activity mActivity;
    private final String mAppPackageName;
    private boolean[] mButtonVisibilities;
    private Button[] mButtons;
    private CharSequence mDetailMessage;
    private TextView mDetailMessageView;
    private int mGroupCount;
    private Icon mGroupIcon;
    private int mGroupIndex;
    private CharSequence mGroupMessage;
    private String mGroupName;
    private ImageView mIconView;
    private TextView mMessageView;
    private GrantPermissionsViewHandler.ResultListener mResultListener;
    private ViewGroup mRootView;
    private final UserHandle mUserHandle;

    static {
        SparseArray<Integer> sparseArray = new SparseArray<>();
        BUTTON_RES_ID_TO_NUM = sparseArray;
        sparseArray.put(R.id.permission_allow_button, 0);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_allow_always_button, 1);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_allow_foreground_only_button, 2);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_deny_button, 3);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_deny_and_dont_ask_again_button, 4);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_allow_one_time_button, 5);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_no_upgrade_button, 6);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_no_upgrade_and_dont_ask_again_button, 7);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_no_upgrade_one_time_button, 8);
        BUTTON_RES_ID_TO_NUM.put(R.id.permission_no_upgrade_one_time_and_dont_ask_again_button, 9);
    }

    public GrantPermissionsViewHandlerImpl(Activity activity, String appPackageName, UserHandle userHandle) {
        this.mActivity = activity;
        this.mAppPackageName = appPackageName;
        this.mUserHandle = userHandle;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public GrantPermissionsViewHandlerImpl setResultListener(GrantPermissionsViewHandler.ResultListener listener) {
        this.mResultListener = listener;
        return this;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void saveInstanceState(Bundle arguments) {
        arguments.putString(ARG_GROUP_NAME, this.mGroupName);
        arguments.putInt(ARG_GROUP_COUNT, this.mGroupCount);
        arguments.putInt(ARG_GROUP_INDEX, this.mGroupIndex);
        arguments.putParcelable(ARG_GROUP_ICON, this.mGroupIcon);
        arguments.putCharSequence(ARG_GROUP_MESSAGE, this.mGroupMessage);
        arguments.putCharSequence(ARG_GROUP_DETAIL_MESSAGE, this.mDetailMessage);
        arguments.putBooleanArray(ARG_DIALOG_BUTTON_VISIBILITIES, this.mButtonVisibilities);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void loadInstanceState(Bundle savedInstanceState) {
        this.mGroupName = savedInstanceState.getString(ARG_GROUP_NAME);
        this.mGroupMessage = savedInstanceState.getCharSequence(ARG_GROUP_MESSAGE);
        this.mGroupIcon = (Icon) savedInstanceState.getParcelable(ARG_GROUP_ICON);
        this.mGroupCount = savedInstanceState.getInt(ARG_GROUP_COUNT);
        this.mGroupIndex = savedInstanceState.getInt(ARG_GROUP_INDEX);
        this.mDetailMessage = savedInstanceState.getCharSequence(ARG_GROUP_DETAIL_MESSAGE);
        this.mButtonVisibilities = savedInstanceState.getBooleanArray(ARG_DIALOG_BUTTON_VISIBILITIES);
        updateAll();
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUi(String groupName, int groupCount, int groupIndex, Icon icon, CharSequence message, CharSequence detailMessage, boolean[] buttonVisibilities) {
        if (this.mGroupIndex != groupIndex) {
        }
        this.mGroupName = groupName;
        this.mGroupCount = groupCount;
        this.mGroupIndex = groupIndex;
        this.mGroupIcon = icon;
        this.mGroupMessage = message;
        this.mDetailMessage = detailMessage;
        this.mButtonVisibilities = buttonVisibilities;
        if (this.mIconView != null) {
            updateAll();
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateUiList(List<String> groupName) {
    }

    private void updateAll() {
        updateDescription();
        updateDetailDescription();
        updateButtons();
        ChangeBounds growShrinkToNewContentSize = new ChangeBounds();
        growShrinkToNewContentSize.setDuration(ANIMATION_DURATION_MILLIS);
        growShrinkToNewContentSize.setInterpolator(AnimationUtils.loadInterpolator(this.mActivity, 17563661));
        TransitionManager.beginDelayedTransition(this.mRootView, growShrinkToNewContentSize);
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public View createView() {
        this.mRootView = (ViewGroup) LayoutInflater.from(this.mActivity).inflate(R.layout.grant_permissions, (ViewGroup) null);
        int h = this.mActivity.getResources().getDisplayMetrics().heightPixels;
        this.mRootView.setMinimumHeight(h);
        this.mRootView.findViewById(R.id.grant_singleton).setOnClickListener(this);
        this.mRootView.findViewById(R.id.grant_dialog).setOnClickListener(this);
        this.mMessageView = (TextView) this.mRootView.findViewById(R.id.permission_message);
        TextView textView = (TextView) this.mRootView.findViewById(R.id.detail_message);
        this.mDetailMessageView = textView;
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        this.mIconView = (ImageView) this.mRootView.findViewById(R.id.permission_icon);
        this.mButtons = new Button[11];
        int numButtons = BUTTON_RES_ID_TO_NUM.size();
        for (int i = 0; i < numButtons; i++) {
            Button button = (Button) this.mRootView.findViewById(BUTTON_RES_ID_TO_NUM.keyAt(i));
            button.setOnClickListener(this);
            this.mButtons[BUTTON_RES_ID_TO_NUM.valueAt(i).intValue()] = button;
        }
        if (this.mGroupName != null) {
            updateAll();
        }
        return this.mRootView;
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void updateWindowAttributes(WindowManager.LayoutParams outLayoutParams) {
    }

    private void updateDescription() {
        Icon icon = this.mGroupIcon;
        if (icon != null) {
            this.mIconView.setImageDrawable(icon.loadDrawable(this.mActivity));
        }
        this.mMessageView.setText(this.mGroupMessage);
    }

    private void updateDetailDescription() {
        CharSequence charSequence = this.mDetailMessage;
        if (charSequence == null) {
            this.mDetailMessageView.setVisibility(8);
        } else {
            this.mDetailMessageView.setText(charSequence);
            this.mDetailMessageView.setVisibility(0);
        }
    }

    private void updateButtons() {
        int numButtons = BUTTON_RES_ID_TO_NUM.size();
        for (int i = 0; i < numButtons; i++) {
            int pos = BUTTON_RES_ID_TO_NUM.valueAt(i).intValue();
            this.mButtons[pos].setVisibility(this.mButtonVisibilities[pos] ? 0 : 8);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.grant_singleton) {
            GrantPermissionsViewHandler.ResultListener resultListener = this.mResultListener;
            if (resultListener != null) {
                resultListener.onPermissionGrantResult(this.mGroupName, -1);
                return;
            }
            this.mActivity.finish();
        }
        try {
            int button = BUTTON_RES_ID_TO_NUM.get(id).intValue();
            switch (button) {
                case 0:
                    if (this.mResultListener != null) {
                        view.performAccessibilityAction(128, null);
                        this.mResultListener.onPermissionGrantResult(this.mGroupName, 0);
                    }
                    break;
                case 1:
                    if (this.mResultListener != null) {
                        view.performAccessibilityAction(128, null);
                        this.mResultListener.onPermissionGrantResult(this.mGroupName, 0);
                    }
                    break;
                case 2:
                    if (this.mResultListener != null) {
                        view.performAccessibilityAction(128, null);
                        this.mResultListener.onPermissionGrantResult(this.mGroupName, 1);
                    }
                    break;
                case 3:
                case 6:
                case 8:
                    if (this.mResultListener != null) {
                        view.performAccessibilityAction(128, null);
                        this.mResultListener.onPermissionGrantResult(this.mGroupName, 2);
                    }
                    break;
                case 4:
                case 7:
                case 9:
                    if (this.mResultListener != null) {
                        view.performAccessibilityAction(128, null);
                        this.mResultListener.onPermissionGrantResult(this.mGroupName, 3);
                    }
                    break;
                case 5:
                    if (this.mResultListener != null) {
                        view.performAccessibilityAction(128, null);
                        this.mResultListener.onPermissionGrantResult(this.mGroupName, 4);
                    }
                    break;
            }
        } catch (NullPointerException e) {
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onBackPressed() {
        GrantPermissionsViewHandler.ResultListener resultListener = this.mResultListener;
        if (resultListener != null) {
            resultListener.onPermissionGrantResult(this.mGroupName, -1);
        } else {
            this.mActivity.finish();
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onDestroy() {
    }

    @Override // com.android.permissioncontroller.permission.ui.GrantPermissionsViewHandler
    public void onConfigurationChanged(Configuration config) {
    }
}
