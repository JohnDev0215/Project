package com.byd.automultipletheme.common;

import android.os.Environment;
import android.os.StatFs;
import kotlin.Metadata;

/* JADX INFO: compiled from: LimitedClickUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/byd/automultipletheme/common/LimitedClickUtils;", "", "()V", "MIN_CLICK_DELAY_TIME", "", "lastClickTime", "", "canClick", "", "isEnoughMem", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LimitedClickUtils {
    public static final LimitedClickUtils INSTANCE = new LimitedClickUtils();
    private static final int MIN_CLICK_DELAY_TIME = 800;
    private static long lastClickTime;

    private LimitedClickUtils() {
    }

    public final boolean canClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - lastClickTime) <= 800) {
            return false;
        }
        lastClickTime = jCurrentTimeMillis;
        return true;
    }

    public final boolean isEnoughMem() {
        StatFs statFs = new StatFs(Environment.getDataDirectory().getPath());
        return statFs.getAvailableBlocksLong() * statFs.getBlockSizeLong() >= ((long) 1048576000);
    }
}
