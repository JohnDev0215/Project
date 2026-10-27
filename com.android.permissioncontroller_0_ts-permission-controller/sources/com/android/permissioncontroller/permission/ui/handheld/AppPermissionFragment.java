package com.android.permissioncontroller.permission.ui.handheld;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.data.FullStoragePermissionAppsLiveData;
import com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel;
import com.android.permissioncontroller.permission.ui.model.AppPermissionViewModelFactory;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.widget.ActionBarShadowController;
import java.util.Map;
import java.util.Objects;
import kotlin.Pair;

/* JADX INFO: loaded from: classes8.dex */
public class AppPermissionFragment extends SettingsWithLargeHeader implements AppPermissionViewModel.ConfirmDialogShowingFragment {
    static final String GRANT_CATEGORY = "grant_category";
    private static final String LOG_TAG = "AppPermissionFragment";
    private static final long POST_DELAY_MS = 20;
    private RadioButton mAllowAlwaysButton;
    private RadioButton mAllowButton;
    private RadioButton mAllowForegroundButton;
    private RadioButton mAskButton;
    private RadioButton mAskOneTimeButton;
    private RadioButton mDenyButton;
    private RadioButton mDenyForegroundButton;
    private View mDivider;
    private Utils.ForegroundCapableType mForegroundCapableType;
    private boolean mIsInitialLoad;
    private boolean mIsStorageGroup;
    private NestedScrollView mNestedScrollView;
    private Drawable mPackageIcon;
    private String mPackageLabel;
    private String mPackageName;
    private String mPermGroupLabel;
    private String mPermGroupName;
    private TextView mPermissionDetails;
    private long mSessionId;
    private UserHandle mUser;
    private AppPermissionViewModel mViewModel;
    private ViewGroup mWidgetFrame;

    public static Bundle createArgs(String packageName, String permName, String groupName, UserHandle userHandle, String caller, long sessionId, String grantCategory) {
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        if (groupName == null) {
            arguments.putString("android.intent.extra.PERMISSION_NAME", permName);
        } else {
            arguments.putString("android.intent.extra.PERMISSION_GROUP_NAME", groupName);
        }
        arguments.putParcelable("android.intent.extra.USER", userHandle);
        arguments.putString("com.android.permissioncontroller.extra.CALLER_NAME", caller);
        arguments.putLong(Constants.EXTRA_SESSION_ID, sessionId);
        arguments.putString(GRANT_CATEGORY, grantCategory);
        return arguments;
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        this.mPackageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        String string = getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME");
        this.mPermGroupName = string;
        if (string == null) {
            this.mPermGroupName = getArguments().getString("android.intent.extra.PERMISSION_NAME");
        }
        this.mIsStorageGroup = Objects.equals(this.mPermGroupName, "android.permission-group.STORAGE");
        this.mUser = (UserHandle) getArguments().getParcelable("android.intent.extra.USER");
        this.mPackageLabel = BidiFormatter.getInstance().unicodeWrap(KotlinUtils.INSTANCE.getPackageLabel(getActivity().getApplication(), this.mPackageName, this.mUser));
        this.mPermGroupLabel = KotlinUtils.INSTANCE.getPermGroupLabel(getContext(), this.mPermGroupName).toString();
        this.mPackageIcon = KotlinUtils.INSTANCE.getBadgedPackageIcon(getActivity().getApplication(), this.mPackageName, this.mUser);
        try {
            this.mForegroundCapableType = Utils.getForegroundCapableType(getContext(), this.mPackageName);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(LOG_TAG, "Package " + this.mPackageName + " not found", e);
        }
        this.mSessionId = getArguments().getLong(Constants.EXTRA_SESSION_ID, 0L);
        AppPermissionViewModelFactory factory = new AppPermissionViewModelFactory(getActivity().getApplication(), this.mPackageName, this.mPermGroupName, this.mUser, this.mSessionId, this.mForegroundCapableType);
        this.mViewModel = (AppPermissionViewModel) new ViewModelProvider(this, factory).get(AppPermissionViewModel.class);
        final Handler delayHandler = new Handler(Looper.getMainLooper());
        this.mViewModel.getButtonStateLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$psxp5Y-k_ivk9_Nz-_EyO6M5wvs
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$1$AppPermissionFragment(delayHandler, (Map) obj);
            }
        });
        this.mViewModel.getDetailResIdLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$SasgKE9vUphp3srd4o2eGG9s3_g
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setDetail((Pair) obj);
            }
        });
        this.mViewModel.getShowAdminSupportLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$vUih1eohoZueUrAVKBpsp_46qgo
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.setAdminSupportDetail((RestrictedLockUtils.EnforcedAdmin) obj);
            }
        });
        if (this.mIsStorageGroup) {
            this.mViewModel.getFullStorageStateLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$lHnGWW_GoChcBfPPPRDhQOXPpn0
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    this.f$0.setSpecialStorageState((FullStoragePermissionAppsLiveData.FullStoragePackageState) obj);
                }
            });
        }
    }

    public /* synthetic */ void lambda$onCreate$1$AppPermissionFragment(Handler delayHandler, final Map buttonState) {
        if (this.mIsInitialLoad) {
            lambda$onCreate$0$AppPermissionFragment(buttonState);
        } else {
            delayHandler.removeCallbacksAndMessages(null);
            delayHandler.postDelayed(new Runnable() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$ZV5RD3tKxluJwBuhh5THXB2rJxo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreate$0$AppPermissionFragment(buttonState);
                }
            }, POST_DELAY_MS);
        }
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.SettingsWithLargeHeader, com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.app_permission, container, false);
        this.mIsInitialLoad = true;
        setHeader(this.mPackageIcon, this.mPackageLabel, null, null, false);
        updateHeader(root.requireViewById(R.id.large_header));
        ((TextView) root.requireViewById(R.id.permission_message)).setText(context.getString(R.string.app_permission_header, this.mPermGroupLabel));
        String caller = getArguments().getString("com.android.permissioncontroller.extra.CALLER_NAME");
        TextView footer1Link = (TextView) root.requireViewById(R.id.footer_link_1);
        footer1Link.setText(context.getString(R.string.app_permission_footer_app_permissions_link, this.mPackageLabel));
        setBottomLinkState(footer1Link, caller, "android.intent.action.MANAGE_APP_PERMISSIONS");
        TextView footer2Link = (TextView) root.requireViewById(R.id.footer_link_2);
        footer2Link.setText(context.getString(R.string.app_permission_footer_permission_apps_link));
        setBottomLinkState(footer2Link, caller, "android.intent.action.MANAGE_PERMISSION_APPS");
        this.mAllowButton = (RadioButton) root.requireViewById(R.id.allow_radio_button);
        this.mAllowAlwaysButton = (RadioButton) root.requireViewById(R.id.allow_always_radio_button);
        this.mAllowForegroundButton = (RadioButton) root.requireViewById(R.id.allow_foreground_only_radio_button);
        this.mAskOneTimeButton = (RadioButton) root.requireViewById(R.id.ask_one_time_radio_button);
        this.mAskButton = (RadioButton) root.requireViewById(R.id.ask_radio_button);
        this.mDenyButton = (RadioButton) root.requireViewById(R.id.deny_radio_button);
        this.mDenyForegroundButton = (RadioButton) root.requireViewById(R.id.deny_foreground_radio_button);
        this.mDivider = root.requireViewById(R.id.two_target_divider);
        this.mWidgetFrame = (ViewGroup) root.requireViewById(R.id.widget_frame);
        this.mPermissionDetails = (TextView) root.requireViewById(R.id.permission_details);
        this.mNestedScrollView = (NestedScrollView) root.requireViewById(R.id.nested_scroll_view);
        if (this.mViewModel.getButtonStateLiveData().getValue() != null) {
            lambda$onCreate$0$AppPermissionFragment(this.mViewModel.getButtonStateLiveData().getValue());
        } else {
            this.mAllowButton.setVisibility(8);
            this.mAllowAlwaysButton.setVisibility(8);
            this.mAllowForegroundButton.setVisibility(8);
            this.mAskOneTimeButton.setVisibility(8);
            this.mAskButton.setVisibility(8);
            this.mDenyButton.setVisibility(8);
            this.mDenyForegroundButton.setVisibility(8);
        }
        if (this.mViewModel.getFullStorageStateLiveData().getIsInitialized() && this.mIsStorageGroup) {
            setSpecialStorageState(this.mViewModel.getFullStorageStateLiveData().getValue(), root);
        } else {
            TextView storageFooter = (TextView) root.requireViewById(R.id.footer_storage_special_app_access);
            storageFooter.setVisibility(8);
        }
        getActivity().setTitle(getPreferenceManager().getContext().getString(R.string.app_permission_title, this.mPermGroupLabel));
        return root;
    }

    private void setBottomLinkState(TextView view, String caller, final String action) {
        if ((Objects.equals(caller, AppPermissionGroupsFragment.class.getName()) && action.equals("android.intent.action.MANAGE_APP_PERMISSIONS")) || (Objects.equals(caller, PermissionAppsFragment.class.getName()) && action.equals("android.intent.action.MANAGE_PERMISSION_APPS"))) {
            view.setVisibility(8);
        } else {
            view.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$27ArS9j6Y5t9emQb5tzM5XQgRto
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    this.f$0.lambda$setBottomLinkState$2$AppPermissionFragment(action, view2);
                }
            });
        }
    }

    public /* synthetic */ void lambda$setBottomLinkState$2$AppPermissionFragment(String action, View v) {
        Bundle args;
        if (action.equals("android.intent.action.MANAGE_APP_PERMISSIONS")) {
            args = AppPermissionGroupsFragment.createArgs(this.mPackageName, this.mUser, this.mSessionId, true);
        } else {
            args = PermissionAppsFragment.createArgs(this.mPermGroupName, this.mSessionId);
        }
        this.mViewModel.showBottomLinkPage(this, action, args);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setSpecialStorageState(FullStoragePermissionAppsLiveData.FullStoragePackageState storageState) {
        setSpecialStorageState(storageState, getView());
    }

    @Override // com.android.permissioncontroller.permission.ui.handheld.PermissionsFrameFragment, androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        ActionBar ab = getActivity().getActionBar();
        if (ab != null) {
            ab.setElevation(0.0f);
        }
        ActionBarShadowController.attachToView(getActivity(), getLifecycle(), this.mNestedScrollView);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == 16908332) {
            UtilsKt.pressBack(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setRadioButtonsState, reason: merged with bridge method [inline-methods] */
    public void lambda$onCreate$0$AppPermissionFragment(Map<AppPermissionViewModel.ButtonType, AppPermissionViewModel.ButtonState> states) {
        if (states == null && this.mViewModel.getButtonStateLiveData().getIsInitialized()) {
            UtilsKt.pressBack(this);
            Log.w(LOG_TAG, "invalid package " + this.mPackageName + " or perm group " + this.mPermGroupName);
            Toast.makeText(getActivity(), R.string.app_not_found_dlg_title, 1).show();
            return;
        }
        if (states == null) {
            return;
        }
        this.mAllowButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$ZiQb2FKS84v7Mk9khdJuaWZaAAo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRadioButtonsState$3$AppPermissionFragment(view);
            }
        });
        this.mAllowAlwaysButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$B9X5MjABc5NuWod2_90qpGLP0n4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRadioButtonsState$4$AppPermissionFragment(view);
            }
        });
        this.mAllowForegroundButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$_2HC1VI-MtCHvyll4DjR-uoWpls
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRadioButtonsState$5$AppPermissionFragment(view);
            }
        });
        this.mAskButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$1Cozfhex-V_vAeOJKDfLjp64O88
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRadioButtonsState$6$AppPermissionFragment(view);
            }
        });
        this.mDenyButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$oxTNIcZYipU2FQwzLnJTPq7ZJls
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRadioButtonsState$7$AppPermissionFragment(view);
            }
        });
        this.mDenyForegroundButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$XCUAIu0XlwdAbRAyYrT3KzZlYv8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setRadioButtonsState$8$AppPermissionFragment(view);
            }
        });
        setButtonState(this.mAllowButton, states.get(AppPermissionViewModel.ButtonType.ALLOW));
        setButtonState(this.mAllowAlwaysButton, states.get(AppPermissionViewModel.ButtonType.ALLOW_ALWAYS));
        setButtonState(this.mAllowForegroundButton, states.get(AppPermissionViewModel.ButtonType.ALLOW_FOREGROUND));
        setButtonState(this.mAskOneTimeButton, states.get(AppPermissionViewModel.ButtonType.ASK_ONCE));
        setButtonState(this.mAskButton, states.get(AppPermissionViewModel.ButtonType.ASK));
        setButtonState(this.mDenyButton, states.get(AppPermissionViewModel.ButtonType.DENY));
        setButtonState(this.mDenyForegroundButton, states.get(AppPermissionViewModel.ButtonType.DENY_FOREGROUND));
        this.mIsInitialLoad = false;
        if (this.mViewModel.getFullStorageStateLiveData().getIsInitialized()) {
            setSpecialStorageState(this.mViewModel.getFullStorageStateLiveData().getValue());
        }
    }

    public /* synthetic */ void lambda$setRadioButtonsState$3$AppPermissionFragment(View v) {
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_FOREGROUND, 1);
        setResult(0);
    }

    public /* synthetic */ void lambda$setRadioButtonsState$4$AppPermissionFragment(View v) {
        if (this.mIsStorageGroup) {
            showConfirmDialog(AppPermissionViewModel.ChangeRequest.GRANT_All_FILE_ACCESS, R.string.special_file_access_dialog, -1, false);
        } else {
            this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_BOTH, 4);
        }
        setResult(0);
    }

    public /* synthetic */ void lambda$setRadioButtonsState$5$AppPermissionFragment(View v) {
        if (this.mIsStorageGroup) {
            this.mViewModel.setAllFilesAccess(false);
            this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_BOTH, 1);
            setResult(0);
        } else {
            this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_FOREGROUND_ONLY, 5);
            setResult(1);
        }
    }

    public /* synthetic */ void lambda$setRadioButtonsState$6$AppPermissionFragment(View v) {
        this.mViewModel.requestChange(true, this, this, AppPermissionViewModel.ChangeRequest.REVOKE_BOTH, 3);
        setResult(2);
    }

    public /* synthetic */ void lambda$setRadioButtonsState$7$AppPermissionFragment(View v) {
        if (this.mViewModel.getFullStorageStateLiveData().getValue() != null && !this.mViewModel.getFullStorageStateLiveData().getValue().isLegacy()) {
            this.mViewModel.setAllFilesAccess(false);
        }
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.REVOKE_BOTH, 2);
        setResult(3);
    }

    public /* synthetic */ void lambda$setRadioButtonsState$8$AppPermissionFragment(View v) {
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.REVOKE_FOREGROUND, 6);
        setResult(3);
    }

    private void setButtonState(RadioButton button, AppPermissionViewModel.ButtonState state) {
        int visible = state.isShown() ? 0 : 8;
        button.setVisibility(visible);
        if (state.isShown()) {
            button.setChecked(state.isChecked());
            button.setEnabled(state.isEnabled());
        }
        if (this.mIsInitialLoad) {
            button.jumpDrawablesToCurrentState();
        }
    }

    private void setSpecialStorageState(FullStoragePermissionAppsLiveData.FullStoragePackageState storageState, View v) {
        if (v == null) {
            return;
        }
        TextView textView = (TextView) v.requireViewById(R.id.footer_storage_special_app_access);
        if (this.mAllowButton == null || !this.mIsStorageGroup) {
            textView.setVisibility(8);
            return;
        }
        this.mAllowAlwaysButton.setText(R.string.app_permission_button_allow_all_files);
        this.mAllowForegroundButton.setText(R.string.app_permission_button_allow_media_only);
        if (storageState == null) {
            textView.setVisibility(8);
        } else if (storageState.isLegacy()) {
            this.mAllowButton.setText(R.string.app_permission_button_allow_all_files);
            textView.setVisibility(8);
        } else {
            textView.setText(R.string.app_permission_footer_special_file_access);
            textView.setVisibility(0);
        }
    }

    private void setResult(int result) {
        Intent intent = new Intent().putExtra("com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED", this.mPermGroupName).putExtra("com.android.permissioncontroller.extra.PERMISSION_RESULT", result);
        getActivity().setResult(-1, intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setDetail(Pair<Integer, Integer> detailResIds) {
        if (detailResIds == null) {
            this.mWidgetFrame.setVisibility(8);
            this.mDivider.setVisibility(8);
            return;
        }
        this.mWidgetFrame.setVisibility(0);
        if (detailResIds.getSecond() != null) {
            this.mDivider.setVisibility(0);
            showRightIcon(R.drawable.ic_settings);
            final Bundle args = AllAppPermissionsFragment.createArgs(this.mPackageName, this.mPermGroupName, this.mUser);
            this.mWidgetFrame.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$v6INr7XVW94fG-TtA7sP1T-ocAU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setDetail$9$AppPermissionFragment(args, view);
                }
            });
            this.mPermissionDetails.setText(getPreferenceManager().getContext().getString(detailResIds.getFirst().intValue(), detailResIds.getSecond()));
        } else {
            this.mPermissionDetails.setText(getPreferenceManager().getContext().getString(detailResIds.getFirst().intValue()));
        }
        this.mPermissionDetails.setVisibility(0);
    }

    public /* synthetic */ void lambda$setDetail$9$AppPermissionFragment(Bundle args, View v) {
        this.mViewModel.showAllPermissions(this, args);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setAdminSupportDetail(final RestrictedLockUtils.EnforcedAdmin admin) {
        if (admin != null) {
            showRightIcon(R.drawable.ic_info);
            this.mWidgetFrame.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$2vau_tcFkqjJuI5Z3RwgxhAl-Yo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setAdminSupportDetail$10$AppPermissionFragment(admin, view);
                }
            });
        } else {
            this.mWidgetFrame.removeAllViews();
        }
    }

    public /* synthetic */ void lambda$setAdminSupportDetail$10$AppPermissionFragment(RestrictedLockUtils.EnforcedAdmin admin, View v) {
        RestrictedLockUtils.sendShowAdminSupportDetailsIntent(getContext(), admin);
    }

    private void showRightIcon(int iconId) {
        this.mWidgetFrame.removeAllViews();
        ImageView imageView = new ImageView(getPreferenceManager().getContext());
        imageView.setImageResource(iconId);
        this.mWidgetFrame.addView(imageView);
        this.mWidgetFrame.setVisibility(0);
    }

    @Override // com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel.ConfirmDialogShowingFragment
    public void showConfirmDialog(AppPermissionViewModel.ChangeRequest changeRequest, int messageId, int buttonPressed, boolean oneTime) {
        Bundle args = getArguments().deepCopy();
        args.putInt(ConfirmDialog.MSG, messageId);
        args.putSerializable(ConfirmDialog.CHANGE_REQUEST, changeRequest);
        args.putInt(ConfirmDialog.BUTTON, buttonPressed);
        args.putBoolean(ConfirmDialog.ONE_TIME, oneTime);
        ConfirmDialog defaultDenyDialog = new ConfirmDialog();
        defaultDenyDialog.setCancelable(true);
        defaultDenyDialog.setArguments(args);
        defaultDenyDialog.show(getChildFragmentManager().beginTransaction(), ConfirmDialog.class.getName());
    }

    public static class ConfirmDialog extends DialogFragment {
        static final String MSG = ConfirmDialog.class.getName() + ".arg.msg";
        static final String CHANGE_REQUEST = ConfirmDialog.class.getName() + ".arg.changeRequest";
        private static final String KEY = ConfirmDialog.class.getName() + ".arg.key";
        private static final String BUTTON = ConfirmDialog.class.getName() + ".arg.button";
        private static final String ONE_TIME = ConfirmDialog.class.getName() + ".arg.onetime";
        private static int sCode = 1;

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final AppPermissionFragment fragment = (AppPermissionFragment) getParentFragment();
            final boolean isGrantFileAccess = getArguments().getSerializable(CHANGE_REQUEST) == AppPermissionViewModel.ChangeRequest.GRANT_All_FILE_ACCESS;
            int positiveButtonStringResId = R.string.grant_dialog_button_deny_anyway;
            if (isGrantFileAccess) {
                positiveButtonStringResId = R.string.grant_dialog_button_allow;
            }
            AlertDialog.Builder b = new AlertDialog.Builder(getContext()).setMessage(getArguments().getInt(MSG)).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$ConfirmDialog$7iQoGiNjVcLCKDB-UwzIVh7DG4g
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).setPositiveButton(positiveButtonStringResId, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.handheld.-$$Lambda$AppPermissionFragment$ConfirmDialog$3RydX91XFMP8chIaJYj1YiDfQLg
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    this.f$0.lambda$onCreateDialog$1$AppPermissionFragment$ConfirmDialog(isGrantFileAccess, fragment, dialogInterface, i);
                }
            });
            Dialog d = b.create();
            d.setCanceledOnTouchOutside(true);
            return d;
        }

        public /* synthetic */ void lambda$onCreateDialog$1$AppPermissionFragment$ConfirmDialog(boolean isGrantFileAccess, AppPermissionFragment fragment, DialogInterface dialog, int which) {
            if (isGrantFileAccess) {
                fragment.mViewModel.setAllFilesAccess(true);
                fragment.mViewModel.requestChange(false, fragment, fragment, AppPermissionViewModel.ChangeRequest.GRANT_BOTH, sCode);
            } else {
                fragment.mViewModel.onDenyAnyWay((AppPermissionViewModel.ChangeRequest) getArguments().getSerializable(CHANGE_REQUEST), getArguments().getInt(BUTTON), getArguments().getBoolean(ONE_TIME));
            }
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            AppPermissionFragment fragment = (AppPermissionFragment) getParentFragment();
            fragment.lambda$onCreate$0$AppPermissionFragment(fragment.mViewModel.getButtonStateLiveData().getValue());
        }
    }
}
