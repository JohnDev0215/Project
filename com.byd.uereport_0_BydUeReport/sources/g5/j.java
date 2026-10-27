package g5;

import android.annotation.SuppressLint;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.net.Uri;
import android.os.Build;
import com.byd.uereport.R;
import d0.e;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: compiled from: NotificationUtil.java */
/* JADX INFO: loaded from: classes2.dex */
public class j {
    public static void a(Context context) {
        try {
            int[] malfunctionInfos = BYDAutoInstrumentDevice.getInstance(context).getMalfunctionInfos();
            String[] stringArray = context.getResources().getStringArray(R.array.malfunction_info_area_array);
            if (malfunctionInfos == null) {
                return;
            }
            a.i("initNotificationMsg faultArea values : " + Arrays.toString(malfunctionInfos));
            if (malfunctionInfos.length != stringArray.length) {
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int i8 = 1;
            for (int i9 = 0; i9 < malfunctionInfos.length; i9++) {
                if (i9 != 5 && i9 != 15 && i9 != 16 && i9 != 29 && i9 != 30 && i9 != 44 && i9 != 45 && i9 != 56 && malfunctionInfos[i9] == 1) {
                    stringBuffer.append(i8 + "." + stringArray[i9] + " ");
                    i8++;
                }
            }
            if (stringBuffer.toString().length() == 0) {
                ((NotificationManager) context.getSystemService("notification")).cancel("bydCustomNtf", 100);
                return;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                NotificationChannel notificationChannel = new NotificationChannel("100", CarSettings.TAG, 3);
                notificationChannel.setDescription(CarSettings.TAG);
                notificationChannel.enableLights(true);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=carhealthy_message"));
                intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
                notificationManager.notify("bydCustomNtf", 100, new e.c(context.getApplicationContext(), "100").k(R.mipmap.app_icon).g(context.getResources().getString(R.string.carhealthy_oil_health_msg)).f(stringBuffer.toString()).e(PendingIntent.getService(context.getApplicationContext(), 1, intent, 134217728)).d(false).a());
            }
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }

    @SuppressLint({"StringFormatInvalid"})
    public static void b(Context context, List<a5.a> list) {
        try {
            if (list == null) {
                ((NotificationManager) context.getSystemService("notification")).cancel("bydCustomNtf", 101);
                return;
            }
            StringBuffer stringBuffer = new StringBuffer();
            int i8 = 0;
            while (i8 < list.size()) {
                StringBuilder sb = new StringBuilder();
                int i9 = i8 + 1;
                sb.append(i9);
                sb.append(".");
                sb.append(list.get(i8).b());
                sb.append(String.format(context.getResources().getString(R.string.carhealthy_oil_life_remain), Integer.valueOf(list.get(i8).d())));
                sb.append(" ");
                stringBuffer.append(sb.toString());
                i8 = i9;
            }
            if (stringBuffer.toString().length() == 0) {
                ((NotificationManager) context.getSystemService("notification")).cancel("bydCustomNtf", 101);
                return;
            }
            if (Build.VERSION.SDK_INT >= 26) {
                NotificationManager notificationManager = (NotificationManager) context.getSystemService("notification");
                NotificationChannel notificationChannel = new NotificationChannel("100", CarSettings.TAG, 3);
                notificationChannel.setDescription(CarSettings.TAG);
                notificationChannel.enableLights(true);
                notificationChannel.enableVibration(true);
                notificationManager.createNotificationChannel(notificationChannel);
                Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("byd_pad://byd_car_settings:2933/launcher?action=maintenance_oil"));
                intent.setComponent(new ComponentName("com.byd.carsettings", "com.byd.dialogservice.DialogService"));
                notificationManager.notify("bydCustomNtf", 101, new e.c(context.getApplicationContext(), "100").k(R.mipmap.app_icon).g(context.getResources().getString(R.string.carhealthy_oil_replace)).f(stringBuffer.toString()).e(PendingIntent.getService(context.getApplicationContext(), 1, intent, 134217728)).d(false).a());
            }
        } catch (Exception e8) {
            e8.printStackTrace();
        }
    }
}
