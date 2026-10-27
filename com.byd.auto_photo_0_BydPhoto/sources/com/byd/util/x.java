package com.byd.util;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.car.VehicleAreaDoor;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.os.Process;
import android.provider.MediaStore;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Window;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import com.byd.auto_photo.ImageShowBaseActivity;
import com.byd.auto_photo.MainActivity;
import com.byd.auto_photo.PhotoBaseApp;
import com.byd.auto_photo.R;
import com.byd.bean.AlbumItem;
import com.byd.util.j;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes2.dex */
public class x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f3014a = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f3015b = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/Screenshots";

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f3016c = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Mirror";

    private static int a(BitmapFactory.Options options, int i, int i2) {
        int iMin;
        double d = options.outWidth;
        double d2 = options.outHeight;
        int iCeil = i2 == -1 ? 1 : (int) Math.ceil(Math.sqrt((d * d2) / ((double) i2)));
        if (i == -1) {
            iMin = 128;
        } else {
            double d3 = i;
            iMin = (int) Math.min(Math.floor(d / d3), Math.floor(d2 / d3));
        }
        if (iMin < iCeil) {
            return iCeil;
        }
        if (i2 == -1 && i == -1) {
            return 1;
        }
        return i == -1 ? iCeil : iMin;
    }

    public static int b(BitmapFactory.Options options, int i, int i2) {
        int iA = a(options, i, i2);
        if (iA > 8) {
            return 8 * ((iA + 7) / 8);
        }
        int i3 = 1;
        while (i3 < iA) {
            i3 <<= 1;
        }
        return i3;
    }

    public static Notification c(Context context, Class<?> cls, int i, NotificationManager notificationManager, int i2) {
        PendingIntent service;
        NotificationCompat.Builder builder;
        if (context.getResources().getInteger(R.integer.activity_main_enable) == 1) {
            Intent intent = new Intent(context, (Class<?>) MainActivity.class);
            intent.putExtra("index", 1);
            intent.addFlags(VehicleAreaDoor.DOOR_HOOD);
            intent.addFlags(4);
            service = PendingIntent.getActivity(context, i, intent, Build.VERSION.SDK_INT < 31 ? 134217728 : 67108864);
        } else {
            service = PendingIntent.getService(context, i, new Intent(context, cls), Build.VERSION.SDK_INT < 31 ? 134217728 : 67108864);
        }
        if (Build.VERSION.SDK_INT >= 26) {
            notificationManager.createNotificationChannel(new NotificationChannel("com.byd.auto_photo", "photo", 1));
            builder = new NotificationCompat.Builder(context, "com.byd.auto_photo");
        } else {
            builder = new NotificationCompat.Builder(context);
        }
        String string = context.getResources().getString(i2);
        builder.setTicker(string).setOngoing(true).setSmallIcon(R.drawable.icon_photo).setWhen(System.currentTimeMillis()).setContentTitle(string).setContentText(string).setContentIntent(service).setGroupSummary(false).setGroup("photo");
        Notification notificationBuild = builder.build();
        notificationBuild.flags = 64;
        return notificationBuild;
    }

    public static String d(Context context, AlbumItem albumItem, String str) {
        j.a aVarI;
        if (albumItem.getAlbumType() == 2) {
            albumItem.setCanReName(true);
            return albumItem.getName();
        }
        if (MediaStore.Images.Media.EXTERNAL_CONTENT_URI.equals(albumItem.getUri())) {
            return context.getResources().getString(R.string.photos);
        }
        if (MediaStore.Video.Media.EXTERNAL_CONTENT_URI.equals(albumItem.getUri())) {
            return context.getResources().getString(R.string.videos);
        }
        if (b.a.k.c.f531b.equals(albumItem.getUri())) {
            return context.getResources().getString(R.string.collect);
        }
        if (albumItem.getUri() == null && albumItem.getNum() == -1) {
            return context.getResources().getString(R.string.new_album_fun);
        }
        if (str.equals(albumItem.getUri().toString())) {
            return context.getResources().getString(R.string.root_path);
        }
        if (f3016c.equals(albumItem.getUri().toString())) {
            albumItem.setCanReName(false);
            return context.getResources().getString(R.string.mirror_app);
        }
        if (j.e(context).q() > 0 && (aVarI = j.e(context).i(albumItem.getUri().getPath())) != null) {
            String string = context.getResources().getString(aVarI.g ? R.string.sdcard : R.string.udisk);
            int i = aVarI.i;
            if (i <= 0) {
                return string;
            }
            return string + " " + i;
        }
        String name = albumItem.getName();
        String string2 = albumItem.getUri().toString();
        if (f3015b.equals(string2)) {
            albumItem.setCanReName(false);
            return context.getResources().getString(R.string.screen_shot);
        }
        if (f3014a.equals(string2)) {
            albumItem.setCanReName(false);
            return context.getResources().getString(R.string.download);
        }
        if (string2.startsWith("/storage/FFFF-FFF")) {
            if ("FFFF-FFFC".equals(name)) {
                albumItem.setCanReName(false);
                return context.getResources().getString(R.string.name_fse);
            }
            if ("FFFF-FFFE".equals(name)) {
                albumItem.setCanReName(false);
                return context.getResources().getString(R.string.name_left_rse);
            }
            if ("FFFF-FFFD".equals(name)) {
                albumItem.setCanReName(false);
                return context.getResources().getString(R.string.name_right_rse);
            }
            if ("FFFF-FFFF".equals(name)) {
                albumItem.setCanReName(false);
                return context.getResources().getString(R.string.name_ivi);
            }
        }
        albumItem.setCanReName(true);
        return name;
    }

    public static String[] e(PhotoBaseApp photoBaseApp) {
        return e.q(photoBaseApp.c());
    }

    public static String[] f(Context context) {
        LinkedList linkedList = new LinkedList(Arrays.asList(e.q(((PhotoBaseApp) context.getApplicationContext()).c())));
        boolean zG = e.G(context);
        if (context.getResources().getInteger(R.integer.support_event_upload) == 1 && !zG) {
            linkedList.add(BYDAutoBodyworkDevice.PERMISSION_BYDAUTO_BODYWORK_COMMON);
        }
        if (zG) {
            linkedList.add(BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON);
        }
        if ((context instanceof ImageShowBaseActivity) && Build.VERSION.SDK_INT >= 29 && !zG && context.getResources().getBoolean(R.bool.support_location)) {
            linkedList.add("android.permission.ACCESS_MEDIA_LOCATION");
        }
        String[] strArr = new String[linkedList.size()];
        linkedList.toArray(strArr);
        return strArr;
    }

    public static String g(Context context) {
        int iMyPid = Process.myPid();
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == iMyPid) {
                String str = runningAppProcessInfo.processName;
                w.c("process : " + str + " " + iMyPid);
                return str;
            }
        }
        return null;
    }

    public static String h(Context context, int i, int i2) {
        Resources resources;
        int i3;
        int i4 = i != 1 ? i != 2 ? i != 4 ? i != 8 ? i != 16 ? 0 : R.string.name_tv : R.string.name_right_rse : R.string.name_left_rse : R.string.name_fse : R.string.name_ivi;
        if (i2 == 2) {
            resources = context.getResources();
            i3 = R.string.video;
        } else {
            resources = context.getResources();
            i3 = R.string.image;
        }
        return String.format(context.getResources().getString(R.string.share_receiv_msg), context.getResources().getString(i4), resources.getString(i3));
    }

    public static boolean i(String str) {
        if (!str.startsWith("/storage/FFFF-FFF")) {
            return Pattern.compile("^(/storage/(([\\w]{4}-[\\w]{4})|[\\w]{16})).*").matcher(str).matches();
        }
        w.c(str + " is not external sd or udisk path");
        return false;
    }

    public static File j(String str) {
        if (str.startsWith("/storage/FFFF-")) {
            return null;
        }
        File file = new File(str);
        if (file.getName().startsWith(".") || !file.exists() || file.length() == 0) {
            return null;
        }
        return file;
    }

    public static boolean k(Context context, File file, int i) {
        int count;
        Cursor cursorQuery = context.getContentResolver().query(b.a.k.i.d, null, "parent=" + i + " and (media_type=1 or media_type=3 or mime_type like 'video/%')", null, null);
        if (cursorQuery != null) {
            count = cursorQuery.getCount();
            cursorQuery.close();
        } else {
            count = 0;
        }
        File[] fileArrListFiles = file.listFiles();
        if (fileArrListFiles == null) {
            return true;
        }
        w.c("content count : " + count + " but folder have children : " + fileArrListFiles.length);
        return fileArrListFiles.length > 0 && count > 0;
    }

    public static boolean l(Activity activity) {
        return m(activity, false);
    }

    public static boolean m(Activity activity, boolean z) {
        if (activity == null) {
            return false;
        }
        Window window = activity.getWindow();
        int height = window.getDecorView().getHeight();
        Rect rect = new Rect();
        window.getDecorView().getWindowVisibleDisplayFrame(rect);
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        defaultDisplay.getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (z) {
            height = i2;
        }
        int i3 = i2 > i ? i2 - i : 0;
        int i4 = (height - rect.bottom) - i3;
        w.c("check soft show : " + height + " " + rect.bottom + " " + i3 + " " + i4);
        return i4 > 0;
    }

    public static boolean n(Activity activity, Point point) {
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int i = displayMetrics.heightPixels;
        defaultDisplay.getRealMetrics(displayMetrics);
        int i2 = displayMetrics.heightPixels;
        if (point.y <= 0) {
            point.y = i2;
        }
        int i3 = i2 > i ? i2 - i : 0;
        int i4 = (point.y - rect.bottom) - i3;
        w.c("check soft show2 : " + point.y + " " + rect.bottom + " " + i3 + " " + i4);
        return i4 > 0;
    }

    public static boolean o(String str) {
        return Pattern.compile("[#%\\?<>:\\*\\|/\\\\]").matcher(str).find();
    }

    /* JADX WARN: Removed duplicated region for block: B:40:0x006d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap p(int r4, int r5, android.net.Uri r6, android.content.ContentResolver r7) throws java.lang.Throwable {
        /*
            r0 = 0
            android.os.ParcelFileDescriptor r6 = q(r6, r7)     // Catch: java.lang.Throwable -> L47 java.lang.OutOfMemoryError -> L49
            if (r6 != 0) goto Ld
            if (r6 == 0) goto Lc
            r6.close()     // Catch: java.lang.Exception -> Lc
        Lc:
            return r0
        Ld:
            android.graphics.BitmapFactory$Options r7 = new android.graphics.BitmapFactory$Options     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r7.<init>()     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            java.io.FileDescriptor r1 = r6.getFileDescriptor()     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r2 = 1
            r7.inJustDecodeBounds = r2     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            android.graphics.BitmapFactory.decodeFileDescriptor(r1, r0, r7)     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            boolean r2 = r7.mCancel     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            if (r2 != 0) goto L41
            int r2 = r7.outWidth     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r3 = -1
            if (r2 == r3) goto L41
            int r2 = r7.outHeight     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            if (r2 != r3) goto L2a
            goto L41
        L2a:
            int r4 = b(r7, r4, r5)     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r7.inSampleSize = r4     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r4 = 0
            r7.inJustDecodeBounds = r4     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r7.inDither = r4     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            android.graphics.Bitmap$Config r4 = android.graphics.Bitmap.Config.ARGB_8888     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r7.inPreferredConfig = r4     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            android.graphics.Bitmap r4 = android.graphics.BitmapFactory.decodeFileDescriptor(r1, r0, r7)     // Catch: java.lang.OutOfMemoryError -> L45 java.lang.Throwable -> L69
            r6.close()     // Catch: java.lang.Exception -> L40
        L40:
            return r4
        L41:
            r6.close()     // Catch: java.lang.Exception -> L44
        L44:
            return r0
        L45:
            r4 = move-exception
            goto L4b
        L47:
            r4 = move-exception
            goto L6b
        L49:
            r4 = move-exception
            r6 = r0
        L4b:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L69
            r5.<init>()     // Catch: java.lang.Throwable -> L69
            java.lang.String r7 = "Got oom exception "
            r5.append(r7)     // Catch: java.lang.Throwable -> L69
            java.lang.String r4 = r4.getMessage()     // Catch: java.lang.Throwable -> L69
            r5.append(r4)     // Catch: java.lang.Throwable -> L69
            java.lang.String r4 = r5.toString()     // Catch: java.lang.Throwable -> L69
            com.byd.util.w.c(r4)     // Catch: java.lang.Throwable -> L69
            if (r6 == 0) goto L68
            r6.close()     // Catch: java.lang.Exception -> L68
        L68:
            return r0
        L69:
            r4 = move-exception
            r0 = r6
        L6b:
            if (r0 == 0) goto L70
            r0.close()     // Catch: java.lang.Exception -> L70
        L70:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.util.x.p(int, int, android.net.Uri, android.content.ContentResolver):android.graphics.Bitmap");
    }

    private static ParcelFileDescriptor q(Uri uri, ContentResolver contentResolver) {
        try {
            return contentResolver.openFileDescriptor(uri, "r");
        } catch (IOException unused) {
            return null;
        }
    }

    public static File r(File[] fileArr) {
        int length = fileArr.length;
        File file = fileArr[0];
        if (length < 2) {
            return file;
        }
        for (int i = 1; i < fileArr.length; i++) {
            if (fileArr[i].lastModified() - file.lastModified() > 0) {
                file = fileArr[i];
            }
        }
        return file;
    }

    public static Drawable s(Context context, int i) {
        return t(context, context.getResources().getDrawable(i));
    }

    public static Drawable t(Context context, Drawable drawable) {
        return h.c(drawable, new ColorStateList(new int[][]{new int[]{android.R.attr.state_pressed, android.R.attr.state_enabled}, new int[]{android.R.attr.state_enabled}, new int[0]}, new int[]{e.l(context, R.attr.text_color_press), e.l(context, R.attr.text_color), e.l(context, R.attr.text_color_no_enable)}));
    }

    public static void u(Context context, String str, int i) {
        context.getContentResolver().notifyChange(Uri.parse("content://" + context.getApplicationInfo().packageName + "/" + str + "?result=" + i), null);
    }

    public static void v(Context context, int i, int i2, TextView... textViewArr) {
        if (context.getResources().getConfiguration().getLayoutDirection() == 1) {
            i2 = i2 == 3 ? 5 : 3;
        }
        if (i != 0) {
            i2 |= i;
        }
        for (TextView textView : textViewArr) {
            textView.setGravity(i2);
        }
    }

    public static void w(Context context, int i, TextView... textViewArr) {
        v(context, i, 3, textViewArr);
    }
}
