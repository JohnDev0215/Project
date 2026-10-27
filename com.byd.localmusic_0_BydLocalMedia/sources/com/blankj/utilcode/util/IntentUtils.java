package com.blankj.utilcode.util;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.webkit.WebView;
import androidx.core.content.FileProvider;
import com.google.android.mms.ContentType;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public final class IntentUtils {
    private IntentUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    public static Intent getInstallAppIntent(String str) {
        return getInstallAppIntent(getFileByPath(str), false);
    }

    public static Intent getInstallAppIntent(File file) {
        return getInstallAppIntent(file, false);
    }

    public static Intent getInstallAppIntent(String str, boolean z) {
        return getInstallAppIntent(getFileByPath(str), z);
    }

    public static Intent getInstallAppIntent(File file, boolean z) {
        Uri uriForFile;
        if (file == null) {
            return null;
        }
        Intent intent = new Intent("android.intent.action.VIEW");
        if (Build.VERSION.SDK_INT < 24) {
            uriForFile = Uri.fromFile(file);
        } else {
            intent.setFlags(1);
            uriForFile = FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
        }
        intent.setDataAndType(uriForFile, "application/vnd.android.package-archive");
        return getIntent(intent, z);
    }

    public static Intent getUninstallAppIntent(String str) {
        return getUninstallAppIntent(str, false);
    }

    public static Intent getUninstallAppIntent(String str, boolean z) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + str));
        return getIntent(intent, z);
    }

    public static Intent getLaunchAppIntent(String str) {
        return getLaunchAppIntent(str, false);
    }

    public static Intent getLaunchAppIntent(String str, boolean z) {
        Intent launchIntentForPackage = Utils.getApp().getPackageManager().getLaunchIntentForPackage(str);
        if (launchIntentForPackage == null) {
            return null;
        }
        return getIntent(launchIntentForPackage, z);
    }

    public static Intent getLaunchAppDetailsSettingsIntent(String str) {
        return getLaunchAppDetailsSettingsIntent(str, false);
    }

    public static Intent getLaunchAppDetailsSettingsIntent(String str, boolean z) {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + str));
        return getIntent(intent, z);
    }

    public static Intent getShareTextIntent(String str) {
        return getShareTextIntent(str, false);
    }

    public static Intent getShareTextIntent(String str, boolean z) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, str);
        return getIntent(intent, z);
    }

    public static Intent getShareImageIntent(String str, String str2) {
        return getShareImageIntent(str, str2, false);
    }

    public static Intent getShareImageIntent(String str, String str2, boolean z) {
        if (str2 == null || str2.length() == 0) {
            return null;
        }
        return getShareImageIntent(str, new File(str2), z);
    }

    public static Intent getShareImageIntent(String str, File file) {
        return getShareImageIntent(str, file, false);
    }

    public static Intent getShareImageIntent(String str, File file, boolean z) {
        if (file == null || !file.isFile()) {
            return null;
        }
        return getShareImageIntent(str, file2Uri(file), z);
    }

    public static Intent getShareImageIntent(String str, Uri uri) {
        return getShareImageIntent(str, uri, false);
    }

    public static Intent getShareImageIntent(String str, Uri uri, boolean z) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, str);
        intent.putExtra(Intent.EXTRA_STREAM, uri);
        intent.setType(ContentType.IMAGE_UNSPECIFIED);
        return getIntent(intent, z);
    }

    public static Intent getShareImageIntent(String str, LinkedList<String> linkedList) {
        return getShareImageIntent(str, linkedList, false);
    }

    public static Intent getShareImageIntent(String str, LinkedList<String> linkedList, boolean z) {
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        Iterator<String> it = linkedList.iterator();
        while (it.hasNext()) {
            arrayList.add(new File(it.next()));
        }
        return getShareImageIntent(str, (List<File>) arrayList, z);
    }

    public static Intent getShareImageIntent(String str, List<File> list) {
        return getShareImageIntent(str, list, false);
    }

    public static Intent getShareImageIntent(String str, List<File> list, boolean z) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (File file : list) {
            if (file.isFile()) {
                arrayList.add(file2Uri(file));
            }
        }
        return getShareImageIntent(str, (ArrayList<Uri>) arrayList, z);
    }

    public static Intent getShareImageIntent(String str, ArrayList<Uri> arrayList) {
        return getShareImageIntent(str, arrayList, false);
    }

    public static Intent getShareImageIntent(String str, ArrayList<Uri> arrayList, boolean z) {
        Intent intent = new Intent(Intent.ACTION_SEND_MULTIPLE);
        intent.putExtra(Intent.EXTRA_TEXT, str);
        intent.putParcelableArrayListExtra(Intent.EXTRA_STREAM, arrayList);
        intent.setType(ContentType.IMAGE_UNSPECIFIED);
        return getIntent(intent, z);
    }

    public static Intent getComponentIntent(String str, String str2) {
        return getComponentIntent(str, str2, null, false);
    }

    public static Intent getComponentIntent(String str, String str2, boolean z) {
        return getComponentIntent(str, str2, null, z);
    }

    public static Intent getComponentIntent(String str, String str2, Bundle bundle) {
        return getComponentIntent(str, str2, bundle, false);
    }

    public static Intent getComponentIntent(String str, String str2, Bundle bundle, boolean z) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setComponent(new ComponentName(str, str2));
        return getIntent(intent, z);
    }

    public static Intent getShutdownIntent() {
        return getShutdownIntent(false);
    }

    public static Intent getShutdownIntent(boolean z) {
        Intent intent = new Intent("android.intent.action.ACTION_REQUEST_SHUTDOWN");
        intent.putExtra(Intent.EXTRA_KEY_CONFIRM, false);
        return getIntent(intent, z);
    }

    public static Intent getDialIntent(String str) {
        return getDialIntent(str, false);
    }

    public static Intent getDialIntent(String str, boolean z) {
        return getIntent(new Intent(Intent.ACTION_DIAL, Uri.parse(WebView.SCHEME_TEL + str)), z);
    }

    public static Intent getCallIntent(String str) {
        return getCallIntent(str, false);
    }

    public static Intent getCallIntent(String str, boolean z) {
        return getIntent(new Intent(Intent.ACTION_CALL, Uri.parse(WebView.SCHEME_TEL + str)), z);
    }

    public static Intent getSendSmsIntent(String str, String str2) {
        return getSendSmsIntent(str, str2, false);
    }

    public static Intent getSendSmsIntent(String str, String str2, boolean z) {
        Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:" + str));
        intent.putExtra("sms_body", str2);
        return getIntent(intent, z);
    }

    public static Intent getCaptureIntent(Uri uri) {
        return getCaptureIntent(uri, false);
    }

    public static Intent getCaptureIntent(Uri uri, boolean z) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("output", uri);
        intent.addFlags(1);
        return getIntent(intent, z);
    }

    private static Intent getIntent(Intent intent, boolean z) {
        return z ? intent.addFlags(268435456) : intent;
    }

    private static File getFileByPath(String str) {
        if (isSpace(str)) {
            return null;
        }
        return new File(str);
    }

    private static boolean isSpace(String str) {
        if (str == null) {
            return true;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    private static Uri file2Uri(File file) {
        if (file == null) {
            return null;
        }
        if (Build.VERSION.SDK_INT >= 24) {
            return FileProvider.getUriForFile(Utils.getApp(), Utils.getApp().getPackageName() + ".utilcode.provider", file);
        }
        return Uri.fromFile(file);
    }
}
