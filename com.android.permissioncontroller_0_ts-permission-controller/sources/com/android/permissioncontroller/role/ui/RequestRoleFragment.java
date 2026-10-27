package com.android.permissioncontroller.role.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.role.RoleManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ApplicationInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Process;
import android.os.UserHandle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.android.permissioncontroller.PermissionControllerStatsLog;
import com.android.permissioncontroller.R;
import com.android.permissioncontroller.permission.utils.PackageRemovalMonitor;
import com.android.permissioncontroller.permission.utils.Utils;
import com.android.permissioncontroller.role.model.Role;
import com.android.permissioncontroller.role.model.Roles;
import com.android.permissioncontroller.role.model.UserDeniedManager;
import com.android.permissioncontroller.role.ui.RequestRoleViewModel;
import com.android.permissioncontroller.role.utils.PackageUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes8.dex */
public class RequestRoleFragment extends DialogFragment {
    private static final String LOG_TAG = RequestRoleFragment.class.getSimpleName();
    private static final String STATE_DONT_ASK_AGAIN = RequestRoleFragment.class.getName() + ".state.DONT_ASK_AGAIN";
    private Adapter mAdapter;
    private CheckBox mDontAskAgainCheck;
    private ListView mListView;
    private String mPackageName;
    private PackageRemovalMonitor mPackageRemovalMonitor;
    private Role mRole;
    private String mRoleName;
    private RequestRoleViewModel mViewModel;

    public static RequestRoleFragment newInstance(String roleName, String packageName) {
        RequestRoleFragment fragment = new RequestRoleFragment();
        Bundle arguments = new Bundle();
        arguments.putString("android.intent.extra.ROLE_NAME", roleName);
        arguments.putString("android.intent.extra.PACKAGE_NAME", packageName);
        fragment.setArguments(arguments);
        return fragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        this.mPackageName = arguments.getString("android.intent.extra.PACKAGE_NAME");
        this.mRoleName = arguments.getString("android.intent.extra.ROLE_NAME");
        this.mRole = Roles.get(requireContext()).get(this.mRoleName);
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext(), getTheme());
        Context context = builder.getContext();
        RoleManager roleManager = (RoleManager) context.getSystemService(RoleManager.class);
        List<String> currentPackageNames = roleManager.getRoleHolders(this.mRoleName);
        if (currentPackageNames.contains(this.mPackageName)) {
            Log.i(LOG_TAG, "Application is already a role holder, role: " + this.mRoleName + ", package: " + this.mPackageName);
            reportRequestResult(2, null);
            clearDeniedSetResultOkAndFinish();
            return super.onCreateDialog(savedInstanceState);
        }
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(this.mPackageName, context);
        if (applicationInfo == null) {
            Log.w(LOG_TAG, "Unknown application: " + this.mPackageName);
            reportRequestResult(1, null);
            finish();
            return super.onCreateDialog(savedInstanceState);
        }
        Drawable icon = Utils.getBadgedIcon(context, applicationInfo);
        String applicationLabel = Utils.getAppLabel(applicationInfo, context);
        String title = getString(this.mRole.getRequestTitleResource(), applicationLabel);
        LayoutInflater inflater = LayoutInflater.from(context);
        View titleLayout = inflater.inflate(R.layout.request_role_title, (ViewGroup) null);
        ImageView iconImage = (ImageView) titleLayout.requireViewById(R.id.icon);
        iconImage.setImageDrawable(icon);
        TextView titleText = (TextView) titleLayout.requireViewById(R.id.title);
        titleText.setText(title);
        View viewLayout = inflater.inflate(R.layout.request_role_view, (ViewGroup) null);
        ListView listView = (ListView) viewLayout.requireViewById(R.id.list);
        this.mListView = listView;
        listView.setChoiceMode(1);
        this.mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$6GSlm4rk3CzHPF9ixmhxw4HVR3U
            @Override // android.widget.AdapterView.OnItemClickListener
            public final void onItemClick(AdapterView adapterView, View view, int i, long j) {
                this.f$0.lambda$onCreateDialog$0$RequestRoleFragment(adapterView, view, i, j);
            }
        });
        Adapter adapter = new Adapter(this.mListView, this.mRole);
        this.mAdapter = adapter;
        if (savedInstanceState != null) {
            adapter.onRestoreInstanceState(savedInstanceState);
        }
        this.mListView.setAdapter((ListAdapter) this.mAdapter);
        CheckBox dontAskAgainCheck = (CheckBox) viewLayout.requireViewById(R.id.dont_ask_again);
        boolean isDeniedOnce = UserDeniedManager.getInstance(context).isDeniedOnce(this.mRoleName, this.mPackageName);
        dontAskAgainCheck.setVisibility(isDeniedOnce ? 0 : 8);
        if (isDeniedOnce) {
            this.mDontAskAgainCheck = dontAskAgainCheck;
            dontAskAgainCheck.setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$ALf_uLYIZn8n0YemSiihE6QSoks
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$onCreateDialog$1$RequestRoleFragment(view);
                }
            });
            if (savedInstanceState != null) {
                boolean dontAskAgain = savedInstanceState.getBoolean(STATE_DONT_ASK_AGAIN);
                this.mDontAskAgainCheck.setChecked(dontAskAgain);
                this.mAdapter.setDontAskAgain(dontAskAgain);
            }
        }
        final AlertDialog dialog = builder.setCustomTitle(titleLayout).setView(viewLayout).setPositiveButton(R.string.request_role_set_as_default, (DialogInterface.OnClickListener) null).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$5I6cc_5SVnDJaFmVkyVJEdoqTlk
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        }).create();
        dialog.getWindow().addSystemFlags(524288);
        dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$45zErxSV1wyu3nyvRLMebjnQRAI
            @Override // android.content.DialogInterface.OnShowListener
            public final void onShow(DialogInterface dialogInterface) {
                this.f$0.lambda$onCreateDialog$4$RequestRoleFragment(dialog, dialogInterface);
            }
        });
        return dialog;
    }

    public /* synthetic */ void lambda$onCreateDialog$0$RequestRoleFragment(AdapterView parent, View view, int position, long id) {
        onItemClicked(position);
    }

    public /* synthetic */ void lambda$onCreateDialog$1$RequestRoleFragment(View view) {
        updateUi();
    }

    public /* synthetic */ void lambda$onCreateDialog$4$RequestRoleFragment(AlertDialog dialog, DialogInterface dialog2) {
        dialog.getButton(-1).setOnClickListener(new View.OnClickListener() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$KasbuoTRahQiP5t0-6HnmxDTKhQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onCreateDialog$3$RequestRoleFragment(view);
            }
        });
    }

    public /* synthetic */ void lambda$onCreateDialog$3$RequestRoleFragment(View view) {
        onSetAsDefault();
    }

    @Override // androidx.fragment.app.DialogFragment
    public AlertDialog getDialog() {
        return (AlertDialog) super.getDialog();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        Context context = requireContext();
        if (PackageUtils.getApplicationInfo(this.mPackageName, context) == null) {
            Log.w(LOG_TAG, "Unknown application: " + this.mPackageName);
            reportRequestResult(1, null);
            finish();
            return;
        }
        PackageRemovalMonitor packageRemovalMonitor = new PackageRemovalMonitor(context, this.mPackageName) { // from class: com.android.permissioncontroller.role.ui.RequestRoleFragment.1
            @Override // com.android.permissioncontroller.permission.utils.PackageRemovalMonitor
            protected void onPackageRemoved() {
                Log.w(RequestRoleFragment.LOG_TAG, "Application is uninstalled, role: " + RequestRoleFragment.this.mRoleName + ", package: " + RequestRoleFragment.this.mPackageName);
                RequestRoleFragment.this.reportRequestResult(1, null);
                RequestRoleFragment.this.finish();
            }
        };
        this.mPackageRemovalMonitor = packageRemovalMonitor;
        packageRemovalMonitor.register();
        RequestRoleViewModel requestRoleViewModel = (RequestRoleViewModel) ViewModelProviders.of(this, new RequestRoleViewModel.Factory(this.mRole, requireActivity().getApplication())).get(RequestRoleViewModel.class);
        this.mViewModel = requestRoleViewModel;
        requestRoleViewModel.getRoleLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$Lhe2Qcq01y8XlNexgYb47lNfV9k
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onRoleDataChanged((List) obj);
            }
        });
        this.mViewModel.getManageRoleHolderStateLiveData().observe(this, new Observer() { // from class: com.android.permissioncontroller.role.ui.-$$Lambda$RequestRoleFragment$k18b7Bfo7sA7O5TXyOuyxzSUBfE
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                this.f$0.onManageRoleHolderStateChanged(((Integer) obj).intValue());
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        this.mAdapter.onSaveInstanceState(outState);
        CheckBox checkBox = this.mDontAskAgainCheck;
        if (checkBox != null) {
            outState.putBoolean(STATE_DONT_ASK_AGAIN, checkBox.isChecked());
        }
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        PackageRemovalMonitor packageRemovalMonitor = this.mPackageRemovalMonitor;
        if (packageRemovalMonitor != null) {
            packageRemovalMonitor.unregister();
            this.mPackageRemovalMonitor = null;
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.i(LOG_TAG, "Dialog cancelled, role: " + this.mRoleName + ", package: " + this.mPackageName);
        reportRequestResult(6, null);
        setDeniedOnceAndFinish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRoleDataChanged(List<Pair<ApplicationInfo, Boolean>> qualifyingApplications) {
        this.mAdapter.replace(qualifyingApplications);
        updateUi();
    }

    private void onItemClicked(int position) {
        this.mAdapter.onItemClicked(position);
        updateUi();
    }

    private void onSetAsDefault() {
        CheckBox checkBox = this.mDontAskAgainCheck;
        if (checkBox != null && checkBox.isChecked()) {
            Log.i(LOG_TAG, "Request denied with don't ask again, role: " + this.mRoleName + ", package: " + this.mPackageName);
            reportRequestResult(8, null);
            setDeniedAlwaysAndFinish();
            return;
        }
        setRoleHolder();
    }

    private void setRoleHolder() {
        String checkedPackageName = this.mAdapter.getCheckedPackageName();
        Context contextRequireContext = requireContext();
        UserHandle userHandleMyUserHandle = Process.myUserHandle();
        if (checkedPackageName == null) {
            reportRequestResult(7, null);
            this.mRole.onNoneHolderSelectedAsUser(userHandleMyUserHandle, contextRequireContext);
            this.mViewModel.getManageRoleHolderStateLiveData().clearRoleHoldersAsUser(this.mRoleName, 0, userHandleMyUserHandle, contextRequireContext);
        } else {
            boolean zEquals = Objects.equals(checkedPackageName, this.mPackageName);
            if (zEquals) {
                reportRequestResult(5, null);
            } else {
                reportRequestResult(7, checkedPackageName);
            }
            this.mViewModel.getManageRoleHolderStateLiveData().setRoleHolderAsUser(this.mRoleName, checkedPackageName, true, zEquals ? 1 : 0, userHandleMyUserHandle, contextRequireContext);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onManageRoleHolderStateChanged(int state) {
        if (state == 0 || state == 1) {
            updateUi();
            return;
        }
        if (state != 2) {
            if (state == 3) {
                finish();
                return;
            }
            return;
        }
        ManageRoleHolderStateLiveData liveData = this.mViewModel.getManageRoleHolderStateLiveData();
        String packageName = liveData.getLastPackageName();
        if (packageName != null) {
            this.mRole.onHolderSelectedAsUser(packageName, liveData.getLastUser(), requireContext());
        }
        if (Objects.equals(packageName, this.mPackageName)) {
            Log.i(LOG_TAG, "Application added as a role holder, role: " + this.mRoleName + ", package: " + this.mPackageName);
            clearDeniedSetResultOkAndFinish();
            return;
        }
        Log.i(LOG_TAG, "Request denied with another application added as a role holder, role: " + this.mRoleName + ", package: " + this.mPackageName);
        setDeniedOnceAndFinish();
    }

    private void updateUi() {
        boolean z = true;
        boolean enabled = this.mViewModel.getManageRoleHolderStateLiveData().getValue().intValue() == 0;
        this.mListView.setEnabled(enabled);
        CheckBox checkBox = this.mDontAskAgainCheck;
        boolean dontAskAgain = checkBox != null && checkBox.isChecked();
        this.mAdapter.setDontAskAgain(dontAskAgain);
        AlertDialog dialog = getDialog();
        boolean hasRoleData = this.mViewModel.getRoleLiveData().getValue() != null;
        Button button = dialog.getButton(-1);
        if (!enabled || !hasRoleData || (!dontAskAgain && this.mAdapter.isHolderApplicationChecked())) {
            z = false;
        }
        button.setEnabled(z);
        dialog.getButton(-2).setEnabled(enabled);
    }

    private void clearDeniedSetResultOkAndFinish() {
        UserDeniedManager.getInstance(requireContext()).clearDenied(this.mRoleName, this.mPackageName);
        requireActivity().setResult(-1);
        finish();
    }

    private void setDeniedOnceAndFinish() {
        UserDeniedManager.getInstance(requireContext()).setDeniedOnce(this.mRoleName, this.mPackageName);
        finish();
    }

    private void setDeniedAlwaysAndFinish() {
        UserDeniedManager.getInstance(requireContext()).setDeniedAlways(this.mRoleName, this.mPackageName);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void finish() {
        requireActivity().finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reportRequestResult(int result, String grantedAnotherPackageName) {
        String holderPackageName = getHolderPackageName();
        reportRequestResult(getApplicationUid(this.mPackageName), this.mPackageName, this.mRoleName, getQualifyingApplicationCount(), getQualifyingApplicationUid(holderPackageName), holderPackageName, getQualifyingApplicationUid(grantedAnotherPackageName), grantedAnotherPackageName, result);
    }

    private int getApplicationUid(String packageName) {
        int uid = getQualifyingApplicationUid(packageName);
        if (uid != -1) {
            return uid;
        }
        ApplicationInfo applicationInfo = PackageUtils.getApplicationInfo(packageName, requireActivity());
        if (applicationInfo == null) {
            return -1;
        }
        return applicationInfo.uid;
    }

    private int getQualifyingApplicationUid(String packageName) {
        Adapter adapter;
        if (packageName == null || (adapter = this.mAdapter) == null) {
            return -1;
        }
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = this.mAdapter.getItem(i);
            if (qualifyingApplication != null) {
                ApplicationInfo qualifyingApplicationInfo = (ApplicationInfo) qualifyingApplication.first;
                if (Objects.equals(qualifyingApplicationInfo.packageName, packageName)) {
                    return qualifyingApplicationInfo.uid;
                }
            }
        }
        return -1;
    }

    private int getQualifyingApplicationCount() {
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return -1;
        }
        int count = adapter.getCount();
        if (count > 0 && this.mAdapter.getItem(0) == null) {
            return count - 1;
        }
        return count;
    }

    private String getHolderPackageName() {
        Adapter adapter = this.mAdapter;
        if (adapter == null) {
            return null;
        }
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = this.mAdapter.getItem(i);
            if (qualifyingApplication != null) {
                boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
                if (isHolderApplication) {
                    return ((ApplicationInfo) qualifyingApplication.first).packageName;
                }
            }
        }
        return null;
    }

    static void reportRequestResult(int requestingUid, String requestingPackageName, String roleName, int qualifyingCount, int currentUid, String currentPackageName, int grantedAnotherUid, String grantedAnotherPackageName, int result) {
        Log.v(LOG_TAG, "Role request result requestingUid=" + requestingUid + " requestingPackageName=" + requestingPackageName + " roleName=" + roleName + " qualifyingCount=" + qualifyingCount + " currentUid=" + currentUid + " currentPackageName=" + currentPackageName + " grantedAnotherUid=" + grantedAnotherUid + " grantedAnotherPackageName=" + grantedAnotherPackageName + " result=" + result);
        PermissionControllerStatsLog.write(PermissionControllerStatsLog.ROLE_REQUEST_RESULT_REPORTED, requestingUid, requestingPackageName, roleName, qualifyingCount, currentUid, currentPackageName, grantedAnotherUid, grantedAnotherPackageName, result);
    }

    private static class Adapter extends BaseAdapter {
        private static final int LAYOUT_TRANSITION_DURATION_MILLIS = 150;
        private static final String STATE_USER_CHECKED = Adapter.class.getName() + ".state.USER_CHECKED";
        private static final String STATE_USER_CHECKED_PACKAGE_NAME = Adapter.class.getName() + ".state.USER_CHECKED_PACKAGE_NAME";
        private boolean mDontAskAgain;
        private boolean mHasHolderApplication;
        private final ListView mListView;
        private boolean mPendingUserChecked;
        private String mPendingUserCheckedPackageName;
        private final List<Pair<ApplicationInfo, Boolean>> mQualifyingApplications = new ArrayList();
        private final Role mRole;
        private boolean mUserChecked;

        Adapter(ListView listView, Role role) {
            this.mListView = listView;
            this.mRole = role;
        }

        public void onSaveInstanceState(Bundle outState) {
            outState.putBoolean(STATE_USER_CHECKED, this.mUserChecked);
            if (this.mUserChecked) {
                outState.putString(STATE_USER_CHECKED_PACKAGE_NAME, getCheckedPackageName());
            }
        }

        public void onRestoreInstanceState(Bundle savedInstanceState) {
            boolean z = savedInstanceState.getBoolean(STATE_USER_CHECKED);
            this.mPendingUserChecked = z;
            if (z) {
                this.mPendingUserCheckedPackageName = savedInstanceState.getString(STATE_USER_CHECKED_PACKAGE_NAME);
            }
        }

        public void setDontAskAgain(boolean dontAskAgain) {
            if (this.mDontAskAgain == dontAskAgain) {
                return;
            }
            this.mDontAskAgain = dontAskAgain;
            if (dontAskAgain) {
                this.mUserChecked = false;
                updateItemChecked();
            }
            notifyDataSetChanged();
        }

        public void onItemClicked(int position) {
            this.mUserChecked = true;
            notifyDataSetChanged();
        }

        public void replace(List<Pair<ApplicationInfo, Boolean>> qualifyingApplications) {
            this.mQualifyingApplications.clear();
            if (this.mRole.shouldShowNone()) {
                this.mQualifyingApplications.add(0, null);
            }
            this.mQualifyingApplications.addAll(qualifyingApplications);
            this.mHasHolderApplication = hasHolderApplication(qualifyingApplications);
            notifyDataSetChanged();
            if (this.mPendingUserChecked) {
                restoreItemChecked();
                this.mPendingUserChecked = false;
                this.mPendingUserCheckedPackageName = null;
            }
            if (!this.mUserChecked) {
                updateItemChecked();
            }
        }

        private static boolean hasHolderApplication(List<Pair<ApplicationInfo, Boolean>> qualifyingApplications) {
            int qualifyingApplicationsSize = qualifyingApplications.size();
            for (int i = 0; i < qualifyingApplicationsSize; i++) {
                Pair<ApplicationInfo, Boolean> qualifyingApplication = qualifyingApplications.get(i);
                boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
                if (isHolderApplication) {
                    return true;
                }
            }
            return false;
        }

        private void restoreItemChecked() {
            if (this.mPendingUserCheckedPackageName == null) {
                if (this.mRole.shouldShowNone()) {
                    this.mUserChecked = true;
                    this.mListView.setItemChecked(0, true);
                    return;
                }
                return;
            }
            int count = getCount();
            for (int i = 0; i < count; i++) {
                Pair<ApplicationInfo, Boolean> qualifyingApplication = getItem(i);
                if (qualifyingApplication != null) {
                    String packageName = ((ApplicationInfo) qualifyingApplication.first).packageName;
                    if (Objects.equals(packageName, this.mPendingUserCheckedPackageName)) {
                        this.mUserChecked = true;
                        this.mListView.setItemChecked(i, true);
                        return;
                    }
                }
            }
        }

        private void updateItemChecked() {
            if (!this.mHasHolderApplication) {
                if (this.mRole.shouldShowNone()) {
                    this.mListView.setItemChecked(0, true);
                    return;
                } else {
                    this.mListView.clearChoices();
                    return;
                }
            }
            int count = getCount();
            for (int i = 0; i < count; i++) {
                Pair<ApplicationInfo, Boolean> qualifyingApplication = getItem(i);
                if (qualifyingApplication != null) {
                    boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
                    if (isHolderApplication) {
                        this.mListView.setItemChecked(i, true);
                        return;
                    }
                }
            }
        }

        public Pair<ApplicationInfo, Boolean> getCheckedItem() {
            int position = this.mListView.getCheckedItemPosition();
            if (position != -1) {
                return getItem(position);
            }
            return null;
        }

        public String getCheckedPackageName() {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = getCheckedItem();
            if (qualifyingApplication == null) {
                return null;
            }
            return ((ApplicationInfo) qualifyingApplication.first).packageName;
        }

        public boolean isHolderApplicationChecked() {
            Pair<ApplicationInfo, Boolean> qualifyingApplication = getCheckedItem();
            if (qualifyingApplication == null) {
                return !this.mHasHolderApplication;
            }
            return ((Boolean) qualifyingApplication.second).booleanValue();
        }

        @Override // android.widget.BaseAdapter, android.widget.Adapter
        public boolean hasStableIds() {
            return true;
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            return false;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.mQualifyingApplications.size();
        }

        @Override // android.widget.Adapter
        public Pair<ApplicationInfo, Boolean> getItem(int position) {
            return this.mQualifyingApplications.get(position);
        }

        @Override // android.widget.Adapter
        public long getItemId(int position) {
            if (position >= getCount()) {
                return Long.MIN_VALUE;
            }
            Pair<ApplicationInfo, Boolean> qualifyingApplication = getItem(position);
            if (qualifyingApplication == null) {
                return 0L;
            }
            return ((ApplicationInfo) qualifyingApplication.first).packageName.hashCode();
        }

        @Override // android.widget.BaseAdapter, android.widget.ListAdapter
        public boolean isEnabled(int position) {
            if (!this.mDontAskAgain) {
                return true;
            }
            Pair<ApplicationInfo, Boolean> qualifyingApplication = getItem(position);
            if (qualifyingApplication == null) {
                return true ^ this.mHasHolderApplication;
            }
            boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
            return isHolderApplication;
        }

        @Override // android.widget.Adapter
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            Drawable icon;
            String title;
            Context context = parent.getContext();
            View view = convertView;
            int i = 0;
            if (view == null) {
                view = LayoutInflater.from(context).inflate(R.layout.request_role_item, parent, false);
                holder = new ViewHolder(view);
                view.setTag(holder);
                holder.titleAndSubtitleLayout.getLayoutTransition().setDuration(150L);
            } else {
                holder = (ViewHolder) view.getTag();
            }
            view.setEnabled(isEnabled(position));
            Pair<ApplicationInfo, Boolean> qualifyingApplication = getItem(position);
            String subtitle = null;
            if (qualifyingApplication == null) {
                icon = AppCompatResources.getDrawable(context, R.drawable.ic_remove_circle);
                title = context.getString(R.string.default_app_none);
                if (!this.mHasHolderApplication) {
                    subtitle = context.getString(R.string.request_role_current_default);
                }
            } else {
                ApplicationInfo qualifyingApplicationInfo = (ApplicationInfo) qualifyingApplication.first;
                Drawable icon2 = Utils.getBadgedIcon(context, qualifyingApplicationInfo);
                String title2 = Utils.getAppLabel(qualifyingApplicationInfo, context);
                boolean isHolderApplication = ((Boolean) qualifyingApplication.second).booleanValue();
                if (isHolderApplication) {
                    subtitle = context.getString(R.string.request_role_current_default);
                } else if (this.mListView.isItemChecked(position)) {
                    subtitle = context.getString(this.mRole.getRequestDescriptionResource());
                }
                icon = icon2;
                title = title2;
            }
            holder.iconImage.setImageDrawable(icon);
            holder.titleText.setText(title);
            TextView textView = holder.subtitleText;
            if (TextUtils.isEmpty(subtitle)) {
                i = 8;
            }
            textView.setVisibility(i);
            holder.subtitleText.setText(subtitle);
            return view;
        }

        private static class ViewHolder {
            public final ImageView iconImage;
            public final TextView subtitleText;
            public final ViewGroup titleAndSubtitleLayout;
            public final TextView titleText;

            ViewHolder(View view) {
                this.iconImage = (ImageView) view.requireViewById(R.id.icon);
                this.titleAndSubtitleLayout = (ViewGroup) view.requireViewById(R.id.title_and_subtitle);
                this.titleText = (TextView) view.requireViewById(R.id.title);
                this.subtitleText = (TextView) view.requireViewById(R.id.subtitle);
            }
        }
    }
}
