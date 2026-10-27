package org.xwalk.core;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.webkit.ValueCallback;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class XWalkFileChooser {
    private static final String ALL_AUDIO_TYPES = "audio/*";
    private static final String ALL_IMAGE_TYPES = "image/*";
    private static final String ALL_VIDEO_TYPES = "video/*";
    private static final String ANY_TYPES = "*/*";
    private static final String AUDIO_TYPE = "audio/";
    private static final String IMAGE_TYPE = "image/";
    public static final int INPUT_FILE_REQUEST_CODE = 1;
    private static final String PATH_PREFIX = "file:";
    private static final String SPLIT_EXPRESSION = ",";
    private static final String TAG = "XWalkFileChooser";
    private static final String VIDEO_TYPE = "video/";
    private static final String WRITE_EXTERNAL_STORAGE = "android.permission.WRITE_EXTERNAL_STORAGE";
    private Activity mActivity;
    private String mCameraPhotoPath;
    private ValueCallback<Uri> mFilePathCallback;

    public XWalkFileChooser(Activity activity) {
        this.mActivity = activity;
    }

    public boolean showFileChooser(ValueCallback<Uri> valueCallback, String str, String str2) {
        this.mFilePathCallback = valueCallback;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (intent.resolveActivity(this.mActivity.getPackageManager()) != null) {
            File fileCreateImageFile = createImageFile();
            if (fileCreateImageFile != null) {
                String str3 = PATH_PREFIX + fileCreateImageFile.getAbsolutePath();
                this.mCameraPhotoPath = str3;
                intent.putExtra("PhotoPath", str3);
                intent.putExtra("output", Uri.fromFile(fileCreateImageFile));
            } else {
                intent = null;
            }
        }
        Intent intent2 = new Intent("android.media.action.VIDEO_CAPTURE");
        Intent intent3 = new Intent("android.provider.MediaStore.RECORD_SOUND");
        Intent intent4 = new Intent("android.intent.action.GET_CONTENT");
        intent4.addCategory("android.intent.category.OPENABLE");
        ArrayList arrayList = new ArrayList();
        if (!str.contains(",") && !str.contains(ANY_TYPES)) {
            if (str2.equals("true")) {
                if (str.startsWith(IMAGE_TYPE)) {
                    if (intent != null) {
                        this.mActivity.startActivityForResult(intent, 1);
                        Log.d(TAG, "Started taking picture");
                        return true;
                    }
                } else {
                    if (str.startsWith(VIDEO_TYPE)) {
                        this.mActivity.startActivityForResult(intent2, 1);
                        Log.d(TAG, "Started camcorder");
                        return true;
                    }
                    if (str.startsWith(AUDIO_TYPE)) {
                        this.mActivity.startActivityForResult(intent3, 1);
                        Log.d(TAG, "Started sound recorder");
                        return true;
                    }
                }
            } else if (str.startsWith(IMAGE_TYPE)) {
                if (intent != null) {
                    arrayList.add(intent);
                }
                intent4.setType(ALL_IMAGE_TYPES);
            } else if (str.startsWith(VIDEO_TYPE)) {
                arrayList.add(intent2);
                intent4.setType(ALL_VIDEO_TYPES);
            } else if (str.startsWith(AUDIO_TYPE)) {
                arrayList.add(intent3);
                intent4.setType(ALL_AUDIO_TYPES);
            }
        }
        if (arrayList.isEmpty() && canWriteExternalStorage()) {
            if (intent != null) {
                arrayList.add(intent);
            }
            arrayList.add(intent2);
            arrayList.add(intent3);
            intent4.setType(ANY_TYPES);
        }
        Intent intent5 = new Intent("android.intent.action.CHOOSER");
        intent5.putExtra("android.intent.extra.INTENT", intent4);
        if (!arrayList.isEmpty()) {
            intent5.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Intent[0]));
        }
        this.mActivity.startActivityForResult(intent5, 1);
        Log.d(TAG, "Started chooser");
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onActivityResult(int r3, int r4, android.content.Intent r5) {
        /*
            r2 = this;
            r0 = 1
            if (r3 != r0) goto L6c
            android.webkit.ValueCallback<android.net.Uri> r3 = r2.mFilePathCallback
            if (r3 == 0) goto L6c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r0 = "Activity result: "
            r3.<init>(r0)
            java.lang.StringBuilder r3 = r3.append(r4)
            java.lang.String r3 = r3.toString()
            java.lang.String r0 = "XWalkFileChooser"
            android.util.Log.d(r0, r3)
            r3 = -1
            r1 = 0
            if (r3 != r4) goto L47
            if (r5 == 0) goto L3e
            java.lang.String r3 = r5.getAction()
            if (r3 != 0) goto L2e
            android.net.Uri r3 = r5.getData()
            if (r3 != 0) goto L2e
            goto L3e
        L2e:
            java.lang.String r3 = r5.getDataString()
            if (r3 == 0) goto L39
            android.net.Uri r3 = android.net.Uri.parse(r3)
            goto L3a
        L39:
            r3 = r1
        L3a:
            r2.deleteImageFile()
            goto L4d
        L3e:
            java.lang.String r3 = r2.mCameraPhotoPath
            if (r3 == 0) goto L4c
            android.net.Uri r3 = android.net.Uri.parse(r3)
            goto L4d
        L47:
            if (r4 != 0) goto L4c
            r2.deleteImageFile()
        L4c:
            r3 = r1
        L4d:
            if (r3 == 0) goto L65
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Received file: "
            r4.<init>(r5)
            java.lang.String r5 = r3.toString()
            java.lang.StringBuilder r4 = r4.append(r5)
            java.lang.String r4 = r4.toString()
            android.util.Log.d(r0, r4)
        L65:
            android.webkit.ValueCallback<android.net.Uri> r4 = r2.mFilePathCallback
            r4.onReceiveValue(r3)
            r2.mFilePathCallback = r1
        L6c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xwalk.core.XWalkFileChooser.onActivityResult(int, int, android.content.Intent):void");
    }

    private boolean canWriteExternalStorage() {
        try {
            return Arrays.asList(this.mActivity.getPackageManager().getPackageInfo(this.mActivity.getPackageName(), 4096).requestedPermissions).contains(WRITE_EXTERNAL_STORAGE);
        } catch (PackageManager.NameNotFoundException | NullPointerException unused) {
            return false;
        }
    }

    private File createImageFile() {
        if (!Environment.getExternalStorageState().equals("mounted")) {
            Log.e(TAG, "External storage is not mounted.");
            return null;
        }
        String str = "JPEG_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date()) + "_";
        File externalStoragePublicDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        if (!externalStoragePublicDirectory.exists()) {
            externalStoragePublicDirectory.mkdirs();
        }
        try {
            File fileCreateTempFile = File.createTempFile(str, ".jpg", externalStoragePublicDirectory);
            Log.d(TAG, "Created image file: " + fileCreateTempFile.getAbsolutePath());
            return fileCreateTempFile;
        } catch (IOException unused) {
            Log.e(TAG, "Unable to create Image File, please make sure permission 'WRITE_EXTERNAL_STORAGE' was added.");
            return null;
        }
    }

    private boolean deleteImageFile() {
        String str = this.mCameraPhotoPath;
        if (str == null || !str.contains(PATH_PREFIX)) {
            return false;
        }
        String str2 = this.mCameraPhotoPath.split(PATH_PREFIX)[1];
        boolean zDelete = new File(str2).delete();
        Log.d(TAG, "Delete image file: " + str2 + " result: " + zDelete);
        return zDelete;
    }
}
