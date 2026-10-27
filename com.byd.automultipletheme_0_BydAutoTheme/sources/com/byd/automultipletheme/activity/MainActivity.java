package com.byd.automultipletheme.activity;

import android.content.IntentFilter;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavGraph;
import androidx.navigation.NavGraphNavigator;
import androidx.navigation.NavigatorProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.activity.navi.FixFragmentNavigator;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.ClickEventUtil;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.LimitedClickUtils;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.WallpaperManagerUtils;
import com.byd.automultipletheme.databinding.ActivityMainBinding;
import com.byd.automultipletheme.fragment.MultipleThemeFragment;
import com.byd.automultipletheme.fragment.WallpaperFragment;
import com.byd.automultipletheme.receiver.ChangeReceiver;
import com.byd.automultipletheme.widget.CommonTabItem;
import com.byd.automultipletheme.widget.TabItemSelectListener;
import java.util.ArrayList;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: MainActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u001a\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\b\u0010\u0013\u001a\u00020\fH\u0002J\u0012\u0010\u0014\u001a\u00020\f2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0014J\b\u0010\u0017\u001a\u00020\fH\u0014J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0007J+\u0010\u001b\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\u001d2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0002\u0010!J\b\u0010\"\u001a\u00020\fH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006#"}, d2 = {"Lcom/byd/automultipletheme/activity/MainActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/byd/automultipletheme/databinding/ActivityMainBinding;", "changeReceiver", "Lcom/byd/automultipletheme/receiver/ChangeReceiver;", "mPermissions", "", "", "[Ljava/lang/String;", "applyRudder", "", "initNavGraph", "Landroidx/navigation/NavGraph;", "provider", "Landroidx/navigation/NavigatorProvider;", "fragmentNavigator", "Lcom/byd/automultipletheme/activity/navi/FixFragmentNavigator;", "initViews", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "onRequestPermissionsResult", "requestCode", "", "permissions", "grantResults", "", "(I[Ljava/lang/String;[I)V", "requestPermission", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private ChangeReceiver changeReceiver;
    private final String[] mPermissions = {BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON, BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_GET, "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON, BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_GET};

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        long jCurrentTimeMillis = System.currentTimeMillis();
        ActivityMainBinding activityMainBindingInflate = ActivityMainBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMainBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMainBindingInflate;
        applyRudder();
        ActivityMainBinding activityMainBinding = this.binding;
        ChangeReceiver changeReceiver = null;
        if (activityMainBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding = null;
        }
        setContentView(activityMainBinding.getRoot());
        MainActivity mainActivity = this;
        MainActivity mainActivity2 = this;
        BarUtils.setStatusBarColor(mainActivity, ContextCompat.getColor(mainActivity2, R.color.statusBarColor));
        ActivityMainBinding activityMainBinding2 = this.binding;
        if (activityMainBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMainBinding2 = null;
        }
        BarUtils.addMarginTopEqualStatusBarHeight(activityMainBinding2.getRoot());
        LogUtils.INSTANCE.i("Time1: ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        requestPermission();
        AutoUtils.INSTANCE.setAutoType();
        LogUtils.INSTANCE.i("AutoType", Integer.valueOf(Const.INSTANCE.getAutoType()));
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding3 = null;
            }
            activityMainBinding3.tabTopLayout.setBackground(null);
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding4 = null;
            }
            activityMainBinding4.getRoot().setBackground(ContextCompat.getDrawable(mainActivity2, R.drawable.img_main_bg_denza));
            BarUtils.setStatusBarColor(mainActivity, ContextCompat.getColor(mainActivity2, R.color.backgroundColorDenza));
        }
        initViews();
        WallpaperManagerUtils.getInstance().addOnWallpaperChangedListener();
        IntentFilter intentFilter = new IntentFilter();
        this.changeReceiver = new ChangeReceiver();
        intentFilter.addAction(Const.ACTION_WALLPAPER_USING_RESULT);
        intentFilter.addAction(Const.ACTION_WALLPAPER_SET_TYPE_SOURCE);
        ChangeReceiver changeReceiver2 = this.changeReceiver;
        if (changeReceiver2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("changeReceiver");
        } else {
            changeReceiver = changeReceiver2;
        }
        registerReceiver(changeReceiver, intentFilter);
        EventBus.getDefault().register(this);
        try {
            if (AutoUtils.INSTANCE.isCarEV(Const.INSTANCE.getAutoType())) {
                BYDAutoGearboxDevice.getInstance(Utils.getApp()).getGear();
            } else {
                BYDAutoGearboxDevice.getInstance(Utils.getApp()).getGearboxAutoModeLevel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final void applyRudder() {
        if (AutoUtils.INSTANCE.isRightRudder() && ScreenUtils.isLandscape()) {
            LogUtils.INSTANCE.i("isRightRudder: ", Boolean.valueOf(AutoUtils.INSTANCE.isRightRudder()), " isLandscape: ", Boolean.valueOf(ScreenUtils.isLandscape()));
            ConstraintSet constraintSet = new ConstraintSet();
            ActivityMainBinding activityMainBinding = this.binding;
            ActivityMainBinding activityMainBinding2 = null;
            if (activityMainBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding = null;
            }
            constraintSet.clone(activityMainBinding.getRoot());
            ActivityMainBinding activityMainBinding3 = this.binding;
            if (activityMainBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding3 = null;
            }
            constraintSet.connect(activityMainBinding3.tabTopLayout.getId(), 2, 0, 2);
            ActivityMainBinding activityMainBinding4 = this.binding;
            if (activityMainBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding4 = null;
            }
            constraintSet.connect(activityMainBinding4.tabTopLayout.getId(), 1, R.id.container_view, 2);
            ActivityMainBinding activityMainBinding5 = this.binding;
            if (activityMainBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding5 = null;
            }
            constraintSet.connect(activityMainBinding5.tabTopLayout.getId(), 3, 0, 3);
            ActivityMainBinding activityMainBinding6 = this.binding;
            if (activityMainBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding6 = null;
            }
            constraintSet.connect(activityMainBinding6.tabTopLayout.getId(), 4, 0, 4);
            constraintSet.connect(R.id.container_view, 3, 0, 3);
            constraintSet.connect(R.id.container_view, 4, 0, 4);
            ActivityMainBinding activityMainBinding7 = this.binding;
            if (activityMainBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMainBinding7 = null;
            }
            constraintSet.connect(R.id.container_view, 2, activityMainBinding7.tabTopLayout.getId(), 1);
            constraintSet.connect(R.id.container_view, 1, 0, 1);
            ActivityMainBinding activityMainBinding8 = this.binding;
            if (activityMainBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityMainBinding2 = activityMainBinding8;
            }
            constraintSet.applyTo(activityMainBinding2.getRoot());
        }
    }

    private final void initViews() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        Fragment fragmentFindFragmentById = getSupportFragmentManager().findFragmentById(R.id.container_view);
        Objects.requireNonNull(fragmentFindFragmentById, "null cannot be cast to non-null type androidx.navigation.fragment.NavHostFragment");
        NavHostFragment navHostFragment = (NavHostFragment) fragmentFindFragmentById;
        final NavController navController = navHostFragment.getNavController();
        Intrinsics.checkNotNullExpressionValue(navController, "navHostFragment.navController");
        NavigatorProvider navigatorProvider = navController.getNavigatorProvider();
        Intrinsics.checkNotNullExpressionValue(navigatorProvider, "navController.navigatorProvider");
        FixFragmentNavigator fixFragmentNavigator = new FixFragmentNavigator(this, navHostFragment.getChildFragmentManager(), navHostFragment.getId());
        navigatorProvider.addNavigator(fixFragmentNavigator);
        NavGraph navGraphInitNavGraph = initNavGraph(navigatorProvider, fixFragmentNavigator);
        Intrinsics.checkNotNull(navGraphInitNavGraph);
        navController.setGraph(navGraphInitNavGraph);
        int index = AutoUtils.INSTANCE.getIndex();
        LogUtils.INSTANCE.i("index:", Integer.valueOf(index), "Time2: ", Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis));
        ActivityMainBinding activityMainBinding = null;
        if (index >= 0) {
            if (AutoUtils.INSTANCE.getIndex() == 0) {
                navController.navigate(R.id.multipleThemeFragment);
                ActivityMainBinding activityMainBinding2 = this.binding;
                if (activityMainBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding2 = null;
                }
                activityMainBinding2.tabLayout.setCurrentTab(0);
            } else {
                navController.navigate(R.id.wallpaperFragment);
                ActivityMainBinding activityMainBinding3 = this.binding;
                if (activityMainBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMainBinding3 = null;
                }
                activityMainBinding3.tabLayout.setCurrentTab(1);
            }
        }
        ActivityMainBinding activityMainBinding4 = this.binding;
        if (activityMainBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMainBinding = activityMainBinding4;
        }
        activityMainBinding.tabLayout.addTabItemSelectListener(new TabItemSelectListener() { // from class: com.byd.automultipletheme.activity.MainActivity.initViews.1
            @Override // com.byd.automultipletheme.widget.TabItemSelectListener
            public void onItemSelected(int position, CommonTabItem tabItem) {
                Intrinsics.checkNotNullParameter(tabItem, "tabItem");
                if (position == 0) {
                    AutoUtils.INSTANCE.setIndex(0);
                    navController.navigate(R.id.multipleThemeFragment);
                } else {
                    if (position != 1) {
                        return;
                    }
                    if (LimitedClickUtils.INSTANCE.isEnoughMem()) {
                        AutoUtils.INSTANCE.setIndex(1);
                        navController.navigate(R.id.wallpaperFragment);
                    } else {
                        ToastUtils.INSTANCE.showShort(R.string.str_storage_full);
                    }
                }
            }
        });
    }

    private final NavGraph initNavGraph(NavigatorProvider provider, FixFragmentNavigator fragmentNavigator) {
        NavGraph navGraph = new NavGraph(new NavGraphNavigator(provider));
        FragmentNavigator.Destination destinationCreateDestination = fragmentNavigator.createDestination();
        Intrinsics.checkNotNullExpressionValue(destinationCreateDestination, "fragmentNavigator.createDestination()");
        destinationCreateDestination.setId(R.id.multipleThemeFragment);
        destinationCreateDestination.setClassName(MultipleThemeFragment.class.getCanonicalName());
        navGraph.addDestination(destinationCreateDestination);
        FragmentNavigator.Destination destinationCreateDestination2 = fragmentNavigator.createDestination();
        Intrinsics.checkNotNullExpressionValue(destinationCreateDestination2, "fragmentNavigator.createDestination()");
        destinationCreateDestination2.setId(R.id.wallpaperFragment);
        destinationCreateDestination2.setClassName(WallpaperFragment.class.getCanonicalName());
        navGraph.addDestination(destinationCreateDestination2);
        navGraph.setStartDestination(destinationCreateDestination.getId());
        return navGraph;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onEvent(EventBusBean event) {
        Intrinsics.checkNotNullParameter(event, "event");
        if (Intrinsics.areEqual(event.getEventType(), EventString.SUCCESS) && event.getEventResult() == 0) {
            long jUptimeMillis = SystemClock.uptimeMillis();
            ClickEventUtil.getInstance().sendEvent(3, 0, 0, jUptimeMillis, jUptimeMillis);
            ClickEventUtil.getInstance().sendEvent(3, 1, 0, jUptimeMillis, jUptimeMillis);
            LogUtils.INSTANCE.i("set success, finish");
            finish();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.INSTANCE.i("MainActivity onDestroy");
        ChangeReceiver changeReceiver = this.changeReceiver;
        if (changeReceiver == null) {
            Intrinsics.throwUninitializedPropertyAccessException("changeReceiver");
            changeReceiver = null;
        }
        unregisterReceiver(changeReceiver);
        WallpaperManagerUtils.getInstance().removeOnWallpaperChangedListener();
        EventBus.getDefault().unregister(this);
    }

    private final void requestPermission() {
        ArrayList arrayList = new ArrayList();
        int length = this.mPermissions.length;
        for (int i = 0; i < length; i++) {
            if (ActivityCompat.checkSelfPermission(this, this.mPermissions[i]) == -1) {
                arrayList.add(this.mPermissions[i]);
            }
        }
        LogUtils.INSTANCE.i("    permissionList ==    " + arrayList);
        if (arrayList.isEmpty()) {
            return;
        }
        Object[] array = arrayList.toArray(new String[0]);
        Objects.requireNonNull(array, "null cannot be cast to non-null type kotlin.Array<T of kotlin.collections.ArraysKt__ArraysJVMKt.toTypedArray>");
        String[] strArr = (String[]) array;
        LogUtils.INSTANCE.i("    needGrantPermissions ==    " + strArr);
        ActivityCompat.requestPermissions(this, strArr, TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        int length = grantResults.length;
        for (int i = 0; i < length; i++) {
            LogUtils.INSTANCE.i(permissions[i] + "    onRequestPermissionsResult   :  " + grantResults[i]);
        }
        if (requestCode == 10000) {
            LogUtils.INSTANCE.i("    requestCode == 10000   ");
        }
    }
}
