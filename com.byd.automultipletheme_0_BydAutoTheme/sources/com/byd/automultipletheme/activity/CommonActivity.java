package com.byd.automultipletheme.activity;

import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.navigation.ActivityKt;
import androidx.navigation.NavController;
import com.blankj.utilcode.util.BarUtils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.AutoTheme;
import com.byd.automultipletheme.bean.AutoWallpaper;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.ClickEventUtil;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.IntentString;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.databinding.ActivityCommonBinding;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: compiled from: CommonActivity.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000b\u001a\u00020\fH\u0002J\u0012\u0010\r\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\fH\u0014J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0007R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/byd/automultipletheme/activity/CommonActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "autoTheme", "Lcom/byd/automultipletheme/bean/AutoTheme;", "binding", "Lcom/byd/automultipletheme/databinding/ActivityCommonBinding;", "dataType", "", "wallpaper", "Lcom/byd/automultipletheme/bean/AutoWallpaper;", "initViews", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEvent", NotificationCompat.CATEGORY_EVENT, "Lcom/byd/automultipletheme/bean/EventBusBean;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CommonActivity extends AppCompatActivity {
    private AutoTheme autoTheme;
    private ActivityCommonBinding binding;
    private int dataType;
    private AutoWallpaper wallpaper;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCommonBinding activityCommonBindingInflate = ActivityCommonBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCommonBindingInflate, "inflate(layoutInflater)");
        this.binding = activityCommonBindingInflate;
        ActivityCommonBinding activityCommonBinding = null;
        if (activityCommonBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCommonBindingInflate = null;
        }
        setContentView(activityCommonBindingInflate.getRoot());
        if (AutoUtils.INSTANCE.getModelType() == 3) {
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.backgroundColorDenza));
        } else {
            BarUtils.setStatusBarColor(this, ContextCompat.getColor(this, R.color.statusBarColor));
        }
        ActivityCommonBinding activityCommonBinding2 = this.binding;
        if (activityCommonBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCommonBinding = activityCommonBinding2;
        }
        BarUtils.addMarginTopEqualStatusBarHeight(activityCommonBinding.getRoot());
        initViews();
        EventBus.getDefault().register(this);
    }

    private final void initViews() {
        NavController navControllerFindNavController = ActivityKt.findNavController(this, R.id.container_common);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Object obj = extras.get(IntentString.DATA_TYPE);
            Objects.requireNonNull(obj, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue = ((Integer) obj).intValue();
            this.dataType = iIntValue;
            if (iIntValue == 0) {
                this.autoTheme = (AutoTheme) extras.get(IntentString.DATA_STR);
                navControllerFindNavController.navigate(R.id.themeDetailFragment, extras);
            } else {
                this.wallpaper = (AutoWallpaper) extras.get(IntentString.DATA_STR);
                navControllerFindNavController.navigate(R.id.wallpaperDetailFragment, extras);
            }
        }
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
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
