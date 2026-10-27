package com.neusoft.na.common.util;

import android.content.Context;
import android.util.Log;
import com.neusoft.na.ocservice.Broadcast.MotsBroadcastReceiver;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: SystemUtils.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/neusoft/na/common/util/SystemUtils;", "", "()V", "Companion", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class SystemUtils {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = "SystemUtils";

    /* JADX INFO: compiled from: SystemUtils.kt */
    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0004J\u0018\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0004H\u0007J \u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0004H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/neusoft/na/common/util/SystemUtils$Companion;", "", "()V", "TAG", "", "getByd8155CountryCode", "context", "Landroid/content/Context;", "getSystemPropertiesIntValue", "", "key", "getSystemPropertiesValue", "setSystemProperties", "", MotsBroadcastReceiver.ACTION_PRESENTATION_VALUE, "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getSystemPropertiesIntValue(Context context, String key) throws IllegalAccessException, ClassNotFoundException, InvocationTargetException {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(key, "key");
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object objInvoke = clsLoadClass.getMethod("getInt", String.class, Integer.TYPE).invoke(clsLoadClass, key, -10086);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.Int");
            int iIntValue = ((Integer) objInvoke).intValue();
            Log.i(SystemUtils.TAG, "[getSystemPropertiesIntValue] --> key == " + key + ", value == " + iIntValue);
            return iIntValue;
        }

        public final String getSystemPropertiesValue(Context context, String key) {
            String str;
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(key, "key");
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
                Object objInvoke = clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, key);
                Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.String");
                str = (String) objInvoke;
            } catch (Exception e) {
                Log.e(SystemUtils.TAG, "get: " + e.getMessage());
                str = "";
            }
            Log.i(SystemUtils.TAG, "[getSystemProperties] --> key == " + key + ", value == " + str);
            return str;
        }

        public final void setSystemProperties(Context context, String key, String value) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(key, "key");
            Intrinsics.checkNotNullParameter(value, "value");
            Log.i(SystemUtils.TAG, "[setSystemProperties] --> key == " + key + ", value == " + value);
            try {
                Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
                Method method = clsLoadClass.getMethod("set", String.class, String.class);
                method.setAccessible(true);
                method.invoke(clsLoadClass, key, value);
            } catch (Exception e) {
                Log.e(SystemUtils.TAG, "[setSystemProperties] Exception: " + e.getMessage());
            }
        }

        public final String getByd8155CountryCode(Context context) {
            String str;
            IOException e;
            JSONArray jSONArray;
            int length;
            int i;
            Intrinsics.checkNotNullParameter(context, "context");
            try {
                InputStream inputStreamOpen = context.getResources().getAssets().open("countrycode_us.json");
                Intrinsics.checkNotNullExpressionValue(inputStreamOpen, "context.resources.assets…en(\"countrycode_us.json\")");
                byte[] bArr = new byte[inputStreamOpen.available()];
                inputStreamOpen.read(bArr);
                str = new String(bArr, Charsets.UTF_8);
                try {
                    inputStreamOpen.close();
                } catch (IOException e2) {
                    e = e2;
                    e.printStackTrace();
                }
            } catch (IOException e3) {
                str = null;
                e = e3;
            }
            if (str == null) {
                Log.i(SystemUtils.TAG, "content is null ");
                return "";
            }
            String systemPropertiesValue = getSystemPropertiesValue(context, "persist.vendor.byd.cfg.huc.config.area");
            Log.i(SystemUtils.TAG, "parseJson codeNum =" + systemPropertiesValue);
            try {
                jSONArray = new JSONArray(str);
                length = jSONArray.length();
            } catch (JSONException e4) {
                e4.printStackTrace();
            }
            for (i = 0; i < length; i++) {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                String countryCode = jSONObject.getString("countrycode");
                String number = jSONObject.getString("number");
                Intrinsics.checkNotNullExpressionValue(number, "number");
                if ((number.length() > 0) && Intrinsics.areEqual(number, systemPropertiesValue)) {
                    Log.i(SystemUtils.TAG, "parseJson number =" + number + ",CountryCode =" + countryCode);
                    Intrinsics.checkNotNullExpressionValue(countryCode, "countryCode");
                    return countryCode;
                }
                return "";
            }
            return "";
        }
    }
}
