package com.android.car.settings.quicksettings;

import android.app.Activity;
import android.app.ActivityManager;
import android.car.drivingstate.CarUxRestrictions;
import android.content.Context;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemProperties;
import android.os.UserManager;
import android.view.View;
import android.widget.TextView;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.settings.R;
import com.android.car.settings.common.BaseFragment;
import com.android.car.settings.common.CarSettingActivities;
import com.android.car.settings.users.UserIconProvider;
import com.android.car.settings.users.UserSwitcherActivity;
import com.android.car.ui.toolbar.MenuItem;
import com.android.car.ui.toolbar.Toolbar;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class QuickSettingFragment extends BaseFragment {
    private static final long BUILD_INFO_REFRESH_TIME_MS = TimeUnit.SECONDS.toMillis(5);
    private TextView mBuildInfo;
    private MenuItem mFullSettingsBtn;
    private QuickSettingGridAdapter mGridAdapter;
    private RecyclerView mListView;
    private ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback() { // from class: com.android.car.settings.quicksettings.-$$Lambda$QuickSettingFragment$S6kuKKYz42yfV-oQ8xVsLVD2HCY
        @Override // androidx.activity.result.ActivityResultCallback
        public final void onActivityResult(Object obj) {
            this.f$0.lambda$new$0$QuickSettingFragment((ActivityResult) obj);
        }
    });
    private UserIconProvider mUserIconProvider;
    private UserManager mUserManager;
    private MenuItem mUserSwitcherBtn;

    public /* synthetic */ void lambda$new$0$QuickSettingFragment(ActivityResult result) {
        if (result.getResultCode() == 2) {
            getActivity().finish();
        }
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected int getLayoutId() {
        return R.layout.quick_settings;
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.mUserManager = UserManager.get(getContext());
        Activity activity = requireActivity();
        this.mUserIconProvider = new UserIconProvider();
        this.mListView = (RecyclerView) activity.findViewById(R.id.list);
        QuickSettingGridAdapter quickSettingGridAdapter = new QuickSettingGridAdapter(activity);
        this.mGridAdapter = quickSettingGridAdapter;
        this.mListView.setLayoutManager(quickSettingGridAdapter.getGridLayoutManager());
        setupUserButton(activity);
        QuickSettingGridAdapter quickSettingGridAdapter2 = this.mGridAdapter;
        quickSettingGridAdapter2.addTile(new WifiTile(activity, quickSettingGridAdapter2, getFragmentHost())).addTile(new BluetoothTile(activity, this.mGridAdapter, getFragmentHost())).addTile(new DayNightTile(activity, this.mGridAdapter, getFragmentHost())).addTile(new CelluarTile(activity, this.mGridAdapter, getFragmentHost())).addSeekbarTile(new BrightnessTile(activity));
        this.mListView.setAdapter(this.mGridAdapter);
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected Toolbar.NavButtonMode getToolbarNavButtonStyle() {
        return Toolbar.NavButtonMode.CLOSE;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected Toolbar.State getToolbarState() {
        if (getContext().getResources().getBoolean(R.bool.config_is_quick_settings_root) && !getContext().getResources().getBoolean(R.bool.config_show_settings_root_exit_icon)) {
            return Toolbar.State.HOME;
        }
        return Toolbar.State.SUBPAGE;
    }

    @Override // com.android.car.settings.common.BaseFragment
    public List<MenuItem> getToolbarMenuItems() {
        return Arrays.asList(this.mUserSwitcherBtn, this.mFullSettingsBtn);
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.mUserSwitcherBtn = new MenuItem.Builder(getContext()).setTitle(getString(R.string.user_switch)).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.quicksettings.-$$Lambda$QuickSettingFragment$h_LqC1OePrb0o8Y9s2fz2JQ96JA
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$1$QuickSettingFragment(menuItem);
            }
        }).setIcon(R.drawable.ic_user).setShowIconAndTitle(true).setVisible(showUserSwitcher()).setTinted(false).build();
        this.mFullSettingsBtn = new MenuItem.Builder(getContext()).setTitle(getString(R.string.more_settings_label)).setOnClickListener(new MenuItem.OnClickListener() { // from class: com.android.car.settings.quicksettings.-$$Lambda$QuickSettingFragment$eqsoDJF1a91ctC1EMfBi76osqzA
            @Override // com.android.car.ui.toolbar.MenuItem.OnClickListener
            public final void onClick(MenuItem menuItem) {
                this.f$0.lambda$onCreate$2$QuickSettingFragment(menuItem);
            }
        }).setIcon(R.drawable.ic_settings_gear).setShowIconAndTitle(true).setUxRestrictions(64).build();
    }

    public /* synthetic */ void lambda$onCreate$1$QuickSettingFragment(MenuItem i) {
        this.mStartForResult.launch(new Intent(getContext(), (Class<?>) UserSwitcherActivity.class));
    }

    public /* synthetic */ void lambda$onCreate$2$QuickSettingFragment(MenuItem i) {
        launchFullSettings();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.mBuildInfo = (TextView) view.requireViewById(R.id.build_info);
    }

    @Override // com.android.car.settings.common.BaseFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        this.mGridAdapter.start();
        if (!Build.IS_USER) {
            refreshBuildInfo();
        }
    }

    private void launchFullSettings() {
        startActivity(new Intent(getContext(), (Class<?>) CarSettingActivities.HomepageActivity.class));
        if (!getContext().getResources().getBoolean(R.bool.config_is_quick_settings_root)) {
            getActivity().finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshBuildInfo() {
        if (!isAdded()) {
            return;
        }
        long buildTimeDiffDays = TimeUnit.MILLISECONDS.toDays(System.currentTimeMillis() - Build.TIME);
        if (buildTimeDiffDays < 0) {
            this.mBuildInfo.postDelayed(new Runnable() { // from class: com.android.car.settings.quicksettings.-$$Lambda$QuickSettingFragment$COjvCsP5coZALcJPAFWcsvM3Tc8
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.refreshBuildInfo();
                }
            }, BUILD_INFO_REFRESH_TIME_MS);
        }
        String string = getResources().getString(R.string.build_info_fmt);
        Object[] objArr = new Object[3];
        objArr[0] = Build.FINGERPRINT;
        objArr[1] = SystemProperties.get("ro.build.date", "<unknown>");
        objArr[2] = buildTimeDiffDays < 0 ? "--" : Long.toString(buildTimeDiffDays);
        String str = String.format(string, objArr);
        this.mBuildInfo.setVisibility(0);
        this.mBuildInfo.setText(str);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        this.mGridAdapter.stop();
    }

    private void setupUserButton(Context context) {
        UserInfo currentUserInfo = this.mUserManager.getUserInfo(ActivityManager.getCurrentUser());
        Drawable userIcon = this.mUserIconProvider.getRoundedUserIcon(currentUserInfo, context);
        this.mUserSwitcherBtn.setIcon(userIcon);
        this.mUserSwitcherBtn.setTitle(currentUserInfo.name);
    }

    private boolean showUserSwitcher() {
        return (UserManager.isDeviceInDemoMode(getContext()) || !UserManager.supportsMultipleUsers() || UserManager.get(getContext()).hasUserRestriction("no_user_switch")) ? false : true;
    }

    @Override // com.android.car.settings.common.BaseFragment
    protected boolean canBeShown(CarUxRestrictions carUxRestrictions) {
        return true;
    }
}
