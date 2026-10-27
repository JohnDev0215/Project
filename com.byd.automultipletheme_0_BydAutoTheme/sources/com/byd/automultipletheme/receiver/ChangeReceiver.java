package com.byd.automultipletheme.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.blankj.utilcode.util.SPUtils;
import com.byd.automultipletheme.R;
import com.byd.automultipletheme.bean.EventBusBean;
import com.byd.automultipletheme.common.AutoUtils;
import com.byd.automultipletheme.common.Const;
import com.byd.automultipletheme.common.EventString;
import com.byd.automultipletheme.common.LogUtils;
import com.byd.automultipletheme.common.ToastUtils;
import com.byd.automultipletheme.common.WallpaperManagerUtils;
import com.byd.automultipletheme.common.db.DBUtils;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* JADX INFO: compiled from: ChangeReceiver.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"Lcom/byd/automultipletheme/receiver/ChangeReceiver;", "Landroid/content/BroadcastReceiver;", "()V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ChangeReceiver extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) throws ExecutionException, InterruptedException {
        if (intent != null) {
            if (Intrinsics.areEqual(intent.getAction(), Const.ACTION_WALLPAPER_USING_RESULT)) {
                int intExtra = intent.getIntExtra("setting_result", -1);
                LogUtils.INSTANCE.i("setting_result", Integer.valueOf(intExtra));
                EventBus.getDefault().post(new EventBusBean(EventString.RESULT, intExtra));
                return;
            }
            if (Intrinsics.areEqual(intent.getAction(), WallpaperManagerUtils.WALLPAPER_SAVE_SUCCESS) && Intrinsics.areEqual(SPUtils.getInstance().getString(Const.TYPE_OF_DIRECTION, Const.DIRECTION_DEFAULT), Const.DIRECTION_PORT)) {
                LogUtils.INSTANCE.i("wallpaper save success!");
                EventBus.getDefault().post(new EventBusBean(EventString.SAVE, 0));
                SPUtils.getInstance().put(Const.TYPE_OF_DIRECTION, Const.DIRECTION_DEFAULT);
                return;
            }
            if (Intrinsics.areEqual(intent.getAction(), WallpaperManagerUtils.WALLPAPER_SAVE_SUCCESS) && Intrinsics.areEqual(SPUtils.getInstance().getString(Const.TYPE_OF_DIRECTION, Const.DIRECTION_DEFAULT), Const.DIRECTION_LAND)) {
                LogUtils.INSTANCE.i("land wallpaper save success");
                if (AutoUtils.INSTANCE.getModelType() == 3) {
                    EventBus.getDefault().post(new EventBusBean(EventString.SAVE, 0));
                    SPUtils.getInstance().put(Const.TYPE_OF_DIRECTION, Const.DIRECTION_DEFAULT);
                    return;
                } else {
                    EventBus.getDefault().post(new EventBusBean(EventString.LAND, 0));
                    return;
                }
            }
            if (Intrinsics.areEqual(intent.getAction(), Const.ACTION_WALLPAPER_SET_TYPE_SOURCE)) {
                int intExtra2 = intent.getIntExtra("setting_result", -1);
                LogUtils.INSTANCE.i("ACTION_WALLPAPER_SET_TYPE_SOURCE", Integer.valueOf(intExtra2));
                if (intExtra2 == 1) {
                    DBUtils.INSTANCE.setCurrentWallpaperId(0);
                    return;
                }
                return;
            }
            if (Intrinsics.areEqual(intent.getAction(), Const.ACTION_ERROR_REMINDER)) {
                int intExtra3 = intent.getIntExtra("ErrorCode", -1);
                if (intExtra3 == 0) {
                    ToastUtils.INSTANCE.showShort(R.string.str_cluster_failed_retry);
                } else {
                    if (intExtra3 != 2) {
                        return;
                    }
                    ToastUtils.INSTANCE.showShort(R.string.str_cluster_fifteen_second_unsupported);
                }
            }
        }
    }
}
