package com.neusoft.na.byd.util;

import android.content.Context;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.bydnaviapi.BydAutoNaviDevice;
import com.neusoft.na.byd.R;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BydUtils.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\b"}, d2 = {"Lcom/neusoft/na/byd/util/BydUtils;", "", "()V", "getToast", "Landroid/widget/Toast;", "context", "Landroid/content/Context;", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class BydUtils {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "BydUtils";

    /* JADX INFO: compiled from: BydUtils.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ \u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u0004H\u0003R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/neusoft/na/byd/util/BydUtils$Companion;", "", "()V", "TAG", "", "sendMapActivate", "", "context", "Landroid/content/Context;", "bydAutoNaviDevice", "Lcom/byd/bydnaviapi/BydAutoNaviDevice;", "state", "", "sendPrivacyPolicy", "setSystemProperties", "key", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void sendMapActivate(Context context, BydAutoNaviDevice bydAutoNaviDevice, boolean state) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(bydAutoNaviDevice, "bydAutoNaviDevice");
            Log.i(BydUtils.TAG, "[sendMapActivate] --> state == " + state);
            if (!state) {
                int focusMapType = bydAutoNaviDevice.getFocusMapType();
                int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
                Log.i(BydUtils.TAG, "mapType == " + focusMapType + ", initNavDisplayMode == " + insNavDisplayMode);
                if ((focusMapType == 0 || focusMapType == 1) && insNavDisplayMode != 1) {
                    Log.i(BydUtils.TAG, "[sendMapActivate] --> sendNaviPresentationState MAP_STATE_NOT_ACTIVATION, value == 8, code == " + bydAutoNaviDevice.sendNaviPresentationState(1, 8));
                }
            }
            setSystemProperties(context, "byd_map_activated", String.valueOf(state ? 1 : 0));
            Log.i(BydUtils.TAG, "[sendMapActivate] --> setSystemProperties value == " + (state ? 1 : 0));
        }

        public final void sendPrivacyPolicy(Context context, BydAutoNaviDevice bydAutoNaviDevice, boolean state) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(bydAutoNaviDevice, "bydAutoNaviDevice");
            Log.i(BydUtils.TAG, "[sendPrivacyPolicy] --> state == " + state);
            if (!state) {
                int focusMapType = bydAutoNaviDevice.getFocusMapType();
                int insNavDisplayMode = bydAutoNaviDevice.getInsNavDisplayMode();
                Log.i(BydUtils.TAG, "mapType == " + focusMapType + ", initNavDisplayMode == " + insNavDisplayMode);
                if ((focusMapType == 0 || focusMapType == 1) && insNavDisplayMode != 1) {
                    Log.i(BydUtils.TAG, "[sendPrivacyPolicy] --> sendNaviPresentationState MAP_STATE_NOT_AGREE_AGREEMENT, value == 9, code == " + bydAutoNaviDevice.sendNaviPresentationState(1, 9));
                }
            }
            setSystemProperties(context, "byd_map_agreement", String.valueOf(state ? 1 : 0));
            Log.i(BydUtils.TAG, "[sendPrivacyPolicy] --> setSystemProperties value == " + (state ? 1 : 0));
        }

        private final void setSystemProperties(Context context, String key, String value) {
            Log.i(BydUtils.TAG, "[setSystemProperties] --> key == " + key + ", value == " + value);
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
                Method method = clsLoadClass.getMethod("set", String.class, String.class);
                method.setAccessible(true);
                method.invoke(clsLoadClass, key, value);
            } catch (Exception e) {
                Log.e(BydUtils.TAG, "[setSystemProperties] Exception: " + e.getMessage());
            }
        }
    }

    public final Toast getToast(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        Log.i(TAG, "getToast");
        Toast toast = Toast.makeText(context, R.string.launcher_change, 0);
        FrameLayout frameLayout = new FrameLayout(context);
        TextView textView = new TextView(context);
        textView.setGravity(17);
        textView.setText(R.string.launcher_change);
        textView.setTextColor(-1);
        textView.setBackgroundResource(R.drawable.toast_bg);
        textView.setTextSize(context.getResources().getDimension(R.dimen.text_size));
        int dimension = (int) context.getResources().getDimension(R.dimen.padding_top);
        int dimension2 = (int) context.getResources().getDimension(R.dimen.padding_left);
        textView.setPadding(dimension2, dimension, dimension2, dimension);
        frameLayout.setPadding(0, (int) context.getResources().getDimension(R.dimen.margin_top), 0, 0);
        frameLayout.addView(textView);
        toast.setView(frameLayout);
        toast.setGravity(48, 0, 0);
        Intrinsics.checkNotNullExpressionValue(toast, "toast");
        return toast;
    }
}
