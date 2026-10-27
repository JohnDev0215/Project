package com.neusoft.na.mots;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.util.Log;
import com.neusoft.na.common.FileUtils;
import com.neusoft.na.common.util.SystemUtils;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.io.ByteStreamsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: compiled from: DbCheckUtil.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0002J\u001c\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\n\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/neusoft/na/mots/DbCheckUtil;", "", "()V", "TAG", "", "check", "", "activity", "Landroid/app/Activity;", "checkDB", "context", "checkDB8155", "getSystemProperties", "Landroid/content/Context;", "key", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class DbCheckUtil {
    public static final DbCheckUtil INSTANCE = new DbCheckUtil();
    private static final String TAG = "XJDbCheckUtil";

    private DbCheckUtil() {
    }

    public final void check(Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        if (CheckBoardKt.isByd()) {
            checkDB(activity);
        } else if (CheckBoardKt.isByd8155f()) {
            checkDB8155(activity);
        }
    }

    private final void checkDB(Activity context) throws IOException {
        Activity activity = context;
        String systemProperties = getSystemProperties(activity, "debug.factory.map.check");
        Log.i(TAG, "checkDB: " + systemProperties);
        if (Intrinsics.areEqual("1", systemProperties)) {
            Log.i(TAG, "checkDB start");
            ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf("1");
            ArrayList arrayListArrayListOf2 = CollectionsKt.arrayListOf("966", "971", "974", "973", "968", "965", "961", "962");
            ArrayList arrayListArrayListOf3 = CollectionsKt.arrayListOf("20", "212", "238", "216");
            ArrayList arrayListArrayListOf4 = CollectionsKt.arrayListOf("27", "262", "263", "260", "230", "261", "244", "267");
            HashMap map = new HashMap(4);
            HashMap map2 = map;
            map2.put("A3", arrayListArrayListOf);
            map2.put("B4", arrayListArrayListOf2);
            map2.put("B5", arrayListArrayListOf3);
            map2.put("B6", arrayListArrayListOf4);
            File file = new File(FileUtils.getInstance(activity).getAreaPath());
            boolean zExists = file.exists();
            String str = FileUtils.DEFAULT_VERSION;
            if (zExists) {
                FileInputStream fileInputStream = new FileInputStream(file);
                String strReplace$default = StringsKt.replace$default(new String(ByteStreamsKt.readBytes(fileInputStream), Charsets.UTF_8), StringUtils.SPACE, "", false, 4, (Object) null);
                fileInputStream.close();
                String systemProperties2 = getSystemProperties(activity, BYDAutoSysPropertiesDevice.COUNTRY_CODE);
                Log.i(TAG, "checkDB: " + strReplace$default + ',' + systemProperties2);
                ArrayList arrayList = (ArrayList) map.get(strReplace$default);
                boolean z = false;
                if (arrayList != null && true == CollectionsKt.contains(arrayList, systemProperties2)) {
                    z = true;
                }
                String str2 = z ? "1" : FileUtils.DEFAULT_VERSION;
                Log.i(TAG, "checkDB: ".concat(str2));
                str = str2;
            } else {
                Log.i(TAG, "checkDB file is not exists");
            }
            Intent intent = new Intent("map.action.byd.MAP_CHECKOUT_RESULT");
            intent.putExtra("map_checkout_result", str);
            intent.setPackage("com.byd.byddatachecktool");
            context.sendBroadcast(intent);
            context.finish();
            Log.i(TAG, "checkDB over");
        }
    }

    private final void checkDB8155(Activity context) throws IOException {
        Activity activity = context;
        String systemProperties = getSystemProperties(activity, "debug.factory.map.check");
        Log.d(TAG, "checkDB: " + systemProperties);
        if (Intrinsics.areEqual("1", systemProperties)) {
            Log.d(TAG, "checkDB start");
            ArrayList arrayListArrayListOf = CollectionsKt.arrayListOf("47", "31", "44", "45", "351", "32", "46", "41", "43", "39", "34", "358", "353", "30", "49", "33", "359", "357", "385", "420", "36", "372", "371", "370", "352", "356", "48", "40", "421", "386", "354", "423", "377", "379", "382", "355", "381", "90");
            ArrayList arrayListArrayListOf2 = CollectionsKt.arrayListOf("997", "375", "7");
            ArrayList arrayListArrayListOf3 = CollectionsKt.arrayListOf("61", "64");
            ArrayList arrayListArrayListOf4 = CollectionsKt.arrayListOf("65", "66", "62", "60", "84", "63");
            ArrayList arrayListArrayListOf5 = CollectionsKt.arrayListOf("977", "92");
            ArrayList arrayListArrayListOf6 = CollectionsKt.arrayListOf("1");
            ArrayList arrayListArrayListOf7 = CollectionsKt.arrayListOf("52", "502", "507", "1876", "1242", "506");
            ArrayList arrayListArrayListOf8 = CollectionsKt.arrayListOf("57", "593", "51", "591", "55", "595", "598", "56");
            ArrayList arrayListArrayListOf9 = CollectionsKt.arrayListOf("966", "971", "974", "965", "968", "973", "962", "961");
            ArrayList arrayListArrayListOf10 = CollectionsKt.arrayListOf("20", "212", "216", "238");
            ArrayList arrayListArrayListOf11 = CollectionsKt.arrayListOf("27", "267", "262", "263", "260", "230", "261", "244");
            HashMap map = new HashMap(9);
            HashMap map2 = map;
            map2.put("A2", arrayListArrayListOf);
            map2.put("A9", arrayListArrayListOf2);
            map2.put("A5", arrayListArrayListOf3);
            map2.put("A7", arrayListArrayListOf4);
            map2.put("J1", arrayListArrayListOf5);
            map2.put("A3", arrayListArrayListOf6);
            map2.put("AA", arrayListArrayListOf7);
            map2.put("A4", arrayListArrayListOf8);
            map2.put("B4", arrayListArrayListOf9);
            map2.put("B5", arrayListArrayListOf10);
            map2.put("B6", arrayListArrayListOf11);
            File file = new File(FileUtils.getInstance(activity).getAreaPath());
            boolean zExists = file.exists();
            String str = FileUtils.DEFAULT_VERSION;
            if (zExists) {
                FileInputStream fileInputStream = new FileInputStream(file);
                String strReplace$default = StringsKt.replace$default(new String(ByteStreamsKt.readBytes(fileInputStream), Charsets.UTF_8), StringUtils.SPACE, "", false, 4, (Object) null);
                fileInputStream.close();
                String byd8155CountryCode = SystemUtils.INSTANCE.getByd8155CountryCode(activity);
                Log.d(TAG, "checkDB: " + strReplace$default + ',' + byd8155CountryCode);
                ArrayList arrayList = (ArrayList) map.get(strReplace$default);
                boolean z = false;
                if (arrayList != null && true == arrayList.contains(byd8155CountryCode)) {
                    z = true;
                }
                String str2 = z ? "1" : FileUtils.DEFAULT_VERSION;
                Log.d(TAG, "checkDB: ".concat(str2));
                str = str2;
            } else {
                Log.i(TAG, "checkDB file is not exists");
            }
            Intent intent = new Intent("map.action.byd.MAP_CHECKOUT_RESULT");
            intent.putExtra("map_checkout_result", str);
            intent.setPackage("com.byd.byddatachecktool");
            context.sendBroadcast(intent);
            context.finish();
            Log.d(TAG, "checkDB over");
        }
    }

    private final String getSystemProperties(Context context, String key) {
        try {
            Class<?> clsLoadClass = context.getClassLoader().loadClass("android.os.SystemProperties");
            Object objInvoke = clsLoadClass.getMethod("get", String.class).invoke(clsLoadClass, key);
            Intrinsics.checkNotNull(objInvoke, "null cannot be cast to non-null type kotlin.String");
            return (String) objInvoke;
        } catch (Exception e) {
            Log.e(TAG, "get: " + e.getMessage());
            return "";
        }
    }
}
