package com.byd.automultipletheme.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.Utils;
import com.byd.automultipletheme.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ToastUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0007\u001a\u00020\bH\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\t\u001a\u00020\n2\b\b\u0001\u0010\r\u001a\u00020\u0004J\u000e\u0010\u000e\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0010\u0010\u000e\u001a\u00020\n2\b\b\u0001\u0010\r\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/byd/automultipletheme/common/ToastUtils;", "", "()V", "MIN_CLICK_DELAY_TIME", "", "lastClickTime", "", "fastClick", "", "showLong", "", "text", "", "idRes", "showShort", "app_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ToastUtils {
    public static final ToastUtils INSTANCE = new ToastUtils();
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;

    private ToastUtils() {
    }

    private final boolean fastClick() {
        long jCurrentTimeMillis = System.currentTimeMillis();
        if (Math.abs(jCurrentTimeMillis - lastClickTime) <= 2000) {
            return false;
        }
        lastClickTime = jCurrentTimeMillis;
        return true;
    }

    public final void showShort(CharSequence text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Toast toastMakeText = Toast.makeText(Utils.getApp(), text, 0);
        toastMakeText.setGravity(48, 0, Utils.getApp().getResources().getDimensionPixelSize(R.dimen.dp_128));
        toastMakeText.show();
    }

    public final void showShort(int idRes) {
        try {
            if (fastClick()) {
                View viewInflate = LayoutInflater.from(Utils.getApp()).inflate(R.layout.view_custom_toast, (ViewGroup) null);
                ((TextView) viewInflate.findViewById(R.id.tv_toast)).setText(Utils.getApp().getText(idRes));
                Toast toast = new Toast(Utils.getApp());
                toast.setView(viewInflate);
                toast.setDuration(0);
                if (ScreenUtils.isLandscape()) {
                    toast.setGravity(48, 0, 106);
                } else {
                    toast.setGravity(48, 0, 326);
                }
                toast.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void showLong(CharSequence text) {
        Intrinsics.checkNotNullParameter(text, "text");
        Toast toastMakeText = Toast.makeText(Utils.getApp(), text, 1);
        toastMakeText.setGravity(48, 0, Utils.getApp().getResources().getDimensionPixelSize(R.dimen.dp_128));
        toastMakeText.show();
    }

    public final void showLong(int idRes) {
        Toast toastMakeText = Toast.makeText(Utils.getApp(), Utils.getApp().getText(idRes), 1);
        toastMakeText.setGravity(48, 0, Utils.getApp().getResources().getDimensionPixelSize(R.dimen.dp_128));
        toastMakeText.show();
    }
}
