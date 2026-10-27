package com.android.permissioncontroller.permission.ui.television;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.text.BidiFormatter;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.preference.Preference;
import androidx.preference.PreferenceScreen;
import androidx.preference.PreferenceViewHolder;
import com.android.permissioncontroller.Constants;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.model.AppPermissionGroup;
import com.android.permissioncontroller.permission.model.AppPermissions;
import com.android.permissioncontroller.permission.ui.handheld.UtilsKt;
import com.android.permissioncontroller.permission.ui.model.AppPermissionViewModel;
import com.android.permissioncontroller.permission.ui.model.AppPermissionViewModelFactory;
import com.android.permissioncontroller.permission.utils.KotlinUtils;
import com.android.permissioncontroller.permission.utils.Utils;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class AppPermissionFragment extends SettingsWithHeader implements AppPermissionViewModel.ConfirmDialogShowingFragment {
    static final String GRANT_CATEGORY = "grant_category";
    private static final String LOG_TAG = "AppPermissionFragment";
    private static final long POST_DELAY_MS = 20;
    private RadioButtonPreference mAllowAlwaysButton;
    private RadioButtonPreference mAllowButton;
    private RadioButtonPreference mAllowForegroundButton;
    private RadioButtonPreference mAskButton;
    private RadioButtonPreference mAskOneTimeButton;
    private RadioButtonPreference mDenyButton;
    private RadioButtonPreference mDenyForegroundButton;
    private Utils.ForegroundCapableType mForegroundCapableType;
    private boolean mIsInitialLoad;
    private boolean mIsStorageGroup;
    private Drawable mPackageIcon;
    private String mPackageLabel;
    private String mPackageName;
    private String mPermGroupLabel;
    private String mPermGroupName;
    private long mSessionId;
    private UserHandle mUser;
    private AppPermissionViewModel mViewModel;

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
        String str;
        super.onCreate(savedInstanceState);
        this.mPackageName = getArguments().getString("android.intent.extra.PACKAGE_NAME");
        String string = getArguments().getString("android.intent.extra.PERMISSION_GROUP_NAME");
        this.mPermGroupName = string;
        if (string == null) {
            this.mPermGroupName = getArguments().getString("android.intent.extra.PERMISSION_NAME");
        }
        if (this.mPackageName == null || (str = this.mPermGroupName) == null) {
            if (this.mPackageName == null) {
                Log.e(LOG_TAG, "Package name is null: android.intent.extra.PACKAGE_NAME");
            }
            if (this.mPermGroupName == null) {
                Log.e(LOG_TAG, "Permission group is null: android.intent.extra.PERMISSION_GROUP_NAME");
            }
            Activity activity = getActivity();
            Toast.makeText(activity, R.string.app_not_found_dlg_title, 1).show();
            activity.finish();
            return;
        }
        this.mIsStorageGroup = Objects.equals(str, "android.permission-group.STORAGE");
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
        this.mViewModel.getButtonStateLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$Is1kgujRMZDFJHHlDJbcLExQAZI
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.lambda$onCreate$1$AppPermissionFragment(delayHandler, (Map) obj);
            }
        });
    }

    public /* synthetic */ void lambda$onCreate$1$AppPermissionFragment(Handler delayHandler, final Map buttonState) {
        if (this.mIsInitialLoad) {
            lambda$onCreate$0$AppPermissionFragment(buttonState);
        } else {
            delayHandler.removeCallbacksAndMessages(null);
            delayHandler.postDelayed(new Runnable() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$zNGtEQpHOvSlEjentPx2WTObcac
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCreate$0$AppPermissionFragment(buttonState);
                }
            }, POST_DELAY_MS);
        }
    }

    @Override // androidx.preference.PreferenceFragmentCompat, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mIsInitialLoad = true;
        setHeader(this.mPackageIcon, this.mPackageLabel, null, getString(R.string.app_permissions_decor_title));
        createPreferences();
        updatePreferences();
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        updatePreferences();
    }

    public void createPreferences() {
        PreferenceScreen screen = getPreferenceScreen();
        Context context = getContext();
        screen.removeAll();
        PackageInfo packageInfo = getPackageInfo(getActivity(), this.mPackageName);
        AppPermissions appPermissions = new AppPermissions(getActivity(), packageInfo, true, new Runnable() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$VT0oSBf6OzO7yHzBiQE0JFywoaQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createPreferences$2$AppPermissionFragment();
            }
        });
        AppPermissionGroup group = appPermissions.getPermissionGroup(this.mPermGroupName);
        Drawable icon = Utils.loadDrawable(context.getPackageManager(), group.getIconPkg(), group.getIconResId());
        screen.addPreference(createHeaderLineTwoPreference(context));
        Preference permHeader = new Preference(context);
        permHeader.setTitle(this.mPermGroupLabel);
        permHeader.setSummary(context.getString(R.string.app_permission_header, this.mPermGroupLabel));
        permHeader.setSelectable(false);
        permHeader.setIcon(Utils.applyTint(getContext(), icon, android.R.attr.colorControlNormal));
        screen.addPreference(permHeader);
        this.mAllowButton = new RadioButtonPreference(context, R.string.app_permission_button_allow);
        this.mAllowAlwaysButton = new RadioButtonPreference(context, R.string.app_permission_button_allow_always);
        this.mAllowForegroundButton = new RadioButtonPreference(context, R.string.app_permission_button_allow_foreground);
        this.mAskOneTimeButton = new RadioButtonPreference(context, R.string.app_permission_button_ask);
        this.mAskButton = new RadioButtonPreference(context, R.string.app_permission_button_ask);
        this.mDenyButton = new RadioButtonPreference(context, R.string.app_permission_button_deny);
        RadioButtonPreference radioButtonPreference = new RadioButtonPreference(context, R.string.app_permission_button_deny);
        this.mDenyForegroundButton = radioButtonPreference;
        Preference[] preferenceArr = {this.mAllowButton, this.mAllowAlwaysButton, this.mAllowForegroundButton, this.mAskOneTimeButton, this.mAskButton, this.mDenyButton, radioButtonPreference};
        for (int i = 0; i < 7; i++) {
            Preference preference = preferenceArr[i];
            preference.setVisible(false);
            preference.setIcon(android.R.color.transparent);
            screen.addPreference(preference);
        }
    }

    public /* synthetic */ void lambda$createPreferences$2$AppPermissionFragment() {
        getActivity().finish();
    }

    public void updatePreferences() {
        if (this.mViewModel.getButtonStateLiveData().getValue() != null) {
            lambda$onCreate$0$AppPermissionFragment(this.mViewModel.getButtonStateLiveData().getValue());
        }
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
        this.mAllowButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$0veSLroxNr57XnCZeEJdWqv4YFc
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setRadioButtonsState$3$AppPermissionFragment(preference);
            }
        });
        this.mAllowAlwaysButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$Vkj8LKwUrIHLgFhKuCgnFHnJtCQ
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setRadioButtonsState$4$AppPermissionFragment(preference);
            }
        });
        this.mAllowForegroundButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$cE6YwslGeR8fSTfTIat2kBWZtoc
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setRadioButtonsState$5$AppPermissionFragment(preference);
            }
        });
        this.mAskButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$LQu1WCS1mMz50P5RXiRPxcsiqwY
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setRadioButtonsState$6$AppPermissionFragment(preference);
            }
        });
        this.mDenyButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$xTLIYJG3It_awXe_oYoh9jLBVHE
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setRadioButtonsState$7$AppPermissionFragment(preference);
            }
        });
        this.mDenyForegroundButton.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$zDQVT4bqOd7i8SZUkQKeS7lYXVE
            @Override // androidx.preference.Preference.OnPreferenceClickListener
            public final boolean onPreferenceClick(Preference preference) {
                return this.f$0.lambda$setRadioButtonsState$8$AppPermissionFragment(preference);
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
    }

    public /* synthetic */ boolean lambda$setRadioButtonsState$3$AppPermissionFragment(Preference v) {
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_FOREGROUND, 1);
        setResult(0);
        return false;
    }

    public /* synthetic */ boolean lambda$setRadioButtonsState$4$AppPermissionFragment(Preference v) {
        if (this.mIsStorageGroup) {
            showConfirmDialog(AppPermissionViewModel.ChangeRequest.GRANT_All_FILE_ACCESS, R.string.special_file_access_dialog, -1, false);
        } else {
            this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_BOTH, 4);
        }
        setResult(0);
        return false;
    }

    public /* synthetic */ boolean lambda$setRadioButtonsState$5$AppPermissionFragment(Preference v) {
        if (this.mIsStorageGroup) {
            this.mViewModel.setAllFilesAccess(false);
            this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_BOTH, 1);
            setResult(0);
            return false;
        }
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.GRANT_FOREGROUND_ONLY, 5);
        setResult(1);
        return false;
    }

    public /* synthetic */ boolean lambda$setRadioButtonsState$6$AppPermissionFragment(Preference v) {
        this.mViewModel.requestChange(true, this, this, AppPermissionViewModel.ChangeRequest.REVOKE_BOTH, 3);
        setResult(2);
        return false;
    }

    public /* synthetic */ boolean lambda$setRadioButtonsState$7$AppPermissionFragment(Preference v) {
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.REVOKE_BOTH, 2);
        setResult(3);
        return false;
    }

    public /* synthetic */ boolean lambda$setRadioButtonsState$8$AppPermissionFragment(Preference v) {
        this.mViewModel.requestChange(false, this, this, AppPermissionViewModel.ChangeRequest.REVOKE_FOREGROUND, 6);
        setResult(3);
        return false;
    }

    private void setButtonState(RadioButtonPreference button, AppPermissionViewModel.ButtonState state) {
        button.setVisible(state.isShown());
        if (state.isShown()) {
            button.setChecked(state.isChecked());
            button.setEnabled(state.isEnabled());
        }
        if (state.isShown() && state.isChecked()) {
            scrollToPreference(button);
        }
    }

    private Preference createHeaderLineTwoPreference(Context context) {
        Preference headerLineTwo = new Preference(context) { // from class: com.android.permissioncontroller.permission.ui.television.AppPermissionFragment.1
            @Override // androidx.preference.Preference
            public void onBindViewHolder(PreferenceViewHolder holder) {
                super.onBindViewHolder(holder);
                holder.itemView.setBackgroundColor(AppPermissionFragment.this.getResources().getColor(R.color.lb_header_banner_color));
            }
        };
        headerLineTwo.setKey("HeaderPreferenceKey");
        headerLineTwo.setSelectable(false);
        headerLineTwo.setTitle(this.mPackageLabel);
        headerLineTwo.setIcon(this.mPackageIcon);
        return headerLineTwo;
    }

    private static PackageInfo getPackageInfo(Activity activity, String packageName) {
        try {
            return activity.getPackageManager().getPackageInfo(packageName, 4096);
        } catch (PackageManager.NameNotFoundException e) {
            Log.i(LOG_TAG, "No package:" + activity.getCallingPackage(), e);
            return null;
        }
    }

    private void setResult(int result) {
        Intent intent = new Intent().putExtra("com.android.permissioncontroller.extra.RESULT_PERMISSION_INTERACTED", this.mPermGroupName).putExtra("com.android.permissioncontroller.extra.PERMISSION_RESULT", result);
        getActivity().setResult(-1, intent);
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
        defaultDenyDialog.setTargetFragment(this, 0);
        defaultDenyDialog.show(getFragmentManager(), ConfirmDialog.class.getName());
    }

    public static class ConfirmDialog extends DialogFragment {
        static final String MSG = ConfirmDialog.class.getName() + ".arg.msg";
        static final String CHANGE_REQUEST = ConfirmDialog.class.getName() + ".arg.changeRequest";
        private static final String KEY = ConfirmDialog.class.getName() + ".arg.key";
        private static final String BUTTON = ConfirmDialog.class.getName() + ".arg.button";
        private static final String ONE_TIME = ConfirmDialog.class.getName() + ".arg.onetime";

        @Override // androidx.fragment.app.DialogFragment
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            final AppPermissionFragment fragment = (AppPermissionFragment) getTargetFragment();
            final boolean isGrantFileAccess = getArguments().getSerializable(CHANGE_REQUEST) == AppPermissionViewModel.ChangeRequest.GRANT_All_FILE_ACCESS;
            int positiveButtonStringResId = R.string.grant_dialog_button_deny_anyway;
            if (isGrantFileAccess) {
                positiveButtonStringResId = R.string.grant_dialog_button_allow;
            }
            AlertDialog.Builder b = new AlertDialog.Builder(getContext()).setMessage(getArguments().getInt(MSG)).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$ConfirmDialog$hDB6OwUxJpktyZYJHw2XQwoI7LU
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            }).setPositiveButton(positiveButtonStringResId, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.permission.ui.television.-$$Lambda$AppPermissionFragment$ConfirmDialog$wMh5FKbn68-bzeVepNiw7QiBHjk
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
            } else {
                fragment.mViewModel.onDenyAnyWay((AppPermissionViewModel.ChangeRequest) getArguments().getSerializable(CHANGE_REQUEST), getArguments().getInt(BUTTON), getArguments().getBoolean(ONE_TIME));
            }
        }

        @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialog) {
            AppPermissionFragment fragment = (AppPermissionFragment) getTargetFragment();
            fragment.lambda$onCreate$0$AppPermissionFragment(fragment.mViewModel.getButtonStateLiveData().getValue());
        }
    }
}
