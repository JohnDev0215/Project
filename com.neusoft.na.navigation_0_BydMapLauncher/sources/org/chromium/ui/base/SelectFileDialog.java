package org.chromium.ui.base;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.ContentUriUtils;
import org.chromium.base.annotations.JNINamespace;
import org.chromium.ui.R;
import org.chromium.ui.UiUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes4.dex */
@JNINamespace("ui")
public class SelectFileDialog implements WindowAndroid.IntentCallback, WindowAndroid.PermissionCallback {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String ALL_AUDIO_TYPES = "audio/*";
    private static final String ALL_IMAGE_TYPES = "image/*";
    private static final String ALL_VIDEO_TYPES = "video/*";
    private static final String ANY_TYPES = "*/*";
    private static final String AUDIO_TYPE = "audio/";
    private static final String IMAGE_TYPE = "image/";
    private static final String TAG = "SelectFileDialog";
    private static final String VIDEO_TYPE = "video/";
    private static WindowAndroid sOverrideWindowAndroid;
    private boolean mAllowMultiple;
    private Uri mCameraOutputUri;
    private boolean mCapture;
    private List<String> mFileTypes;
    private final long mNativeSelectFileDialog;
    private boolean mSupportsAudioCapture;
    private boolean mSupportsImageCapture;
    private boolean mSupportsVideoCapture;
    private WindowAndroid mWindowAndroid;

    private native void nativeOnFileNotSelected(long j);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnFileSelected(long j, String str, String str2);

    /* JADX INFO: Access modifiers changed from: private */
    public native void nativeOnMultipleFilesSelected(long j, String[] strArr, String[] strArr2);

    private SelectFileDialog(long j) {
        this.mNativeSelectFileDialog = j;
    }

    public static void setWindowAndroidForTests(WindowAndroid windowAndroid) {
        sOverrideWindowAndroid = windowAndroid;
    }

    private void selectFile(String[] strArr, boolean z, boolean z2, WindowAndroid windowAndroid) {
        this.mFileTypes = new ArrayList(Arrays.asList(strArr));
        this.mCapture = z;
        this.mAllowMultiple = z2;
        WindowAndroid windowAndroid2 = sOverrideWindowAndroid;
        if (windowAndroid2 == null) {
            windowAndroid2 = windowAndroid;
        }
        this.mWindowAndroid = windowAndroid2;
        this.mSupportsImageCapture = windowAndroid2.canResolveActivity(new Intent("android.media.action.IMAGE_CAPTURE"));
        this.mSupportsVideoCapture = this.mWindowAndroid.canResolveActivity(new Intent("android.media.action.VIDEO_CAPTURE"));
        this.mSupportsAudioCapture = this.mWindowAndroid.canResolveActivity(new Intent("android.provider.MediaStore.RECORD_SOUND"));
        ArrayList arrayList = new ArrayList();
        if (((this.mSupportsImageCapture && shouldShowImageTypes()) || (this.mSupportsVideoCapture && shouldShowVideoTypes())) && !windowAndroid.hasPermission("android.permission.CAMERA")) {
            arrayList.add("android.permission.CAMERA");
        }
        if (this.mSupportsAudioCapture && shouldShowAudioTypes() && !windowAndroid.hasPermission("android.permission.RECORD_AUDIO")) {
            arrayList.add("android.permission.RECORD_AUDIO");
        }
        if (arrayList.isEmpty()) {
            launchSelectFileIntent();
        } else {
            windowAndroid.requestPermissions((String[]) arrayList.toArray(new String[arrayList.size()]), this);
        }
    }

    private void launchSelectFileIntent() {
        boolean zHasPermission = this.mWindowAndroid.hasPermission("android.permission.CAMERA");
        if (this.mSupportsImageCapture && zHasPermission) {
            new GetCameraIntentTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, new Void[0]);
        } else {
            launchSelectFileWithCameraIntent(zHasPermission, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void launchSelectFileWithCameraIntent(boolean z, Intent intent) {
        Intent intent2 = null;
        Intent intent3 = (this.mSupportsVideoCapture && z) ? new Intent("android.media.action.VIDEO_CAPTURE") : null;
        boolean zHasPermission = this.mWindowAndroid.hasPermission("android.permission.RECORD_AUDIO");
        if (this.mSupportsAudioCapture && zHasPermission) {
            intent2 = new Intent("android.provider.MediaStore.RECORD_SOUND");
        }
        if (captureCamera() && intent != null) {
            if (this.mWindowAndroid.showIntent(intent, this, Integer.valueOf(R.string.low_memory_error))) {
                return;
            }
        } else if (captureCamcorder() && intent3 != null) {
            if (this.mWindowAndroid.showIntent(intent3, this, Integer.valueOf(R.string.low_memory_error))) {
                return;
            }
        } else if (captureMicrophone() && intent2 != null && this.mWindowAndroid.showIntent(intent2, this, Integer.valueOf(R.string.low_memory_error))) {
            return;
        }
        Intent intent4 = new Intent("android.intent.action.GET_CONTENT");
        intent4.addCategory("android.intent.category.OPENABLE");
        if (this.mAllowMultiple) {
            intent4.putExtra("android.intent.extra.ALLOW_MULTIPLE", true);
        }
        ArrayList arrayList = new ArrayList();
        if (!noSpecificType()) {
            if (shouldShowImageTypes()) {
                if (intent != null) {
                    arrayList.add(intent);
                }
                intent4.setType(ALL_IMAGE_TYPES);
            } else if (shouldShowVideoTypes()) {
                if (intent3 != null) {
                    arrayList.add(intent3);
                }
                intent4.setType(ALL_VIDEO_TYPES);
            } else if (shouldShowAudioTypes()) {
                if (intent2 != null) {
                    arrayList.add(intent2);
                }
                intent4.setType(ALL_AUDIO_TYPES);
            }
        }
        if (arrayList.isEmpty()) {
            intent4.setType(ANY_TYPES);
            if (intent != null) {
                arrayList.add(intent);
            }
            if (intent3 != null) {
                arrayList.add(intent3);
            }
            if (intent2 != null) {
                arrayList.add(intent2);
            }
        }
        Intent intent5 = new Intent("android.intent.action.CHOOSER");
        if (!arrayList.isEmpty()) {
            intent5.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Intent[0]));
        }
        intent5.putExtra("android.intent.extra.INTENT", intent4);
        if (this.mWindowAndroid.showIntent(intent5, this, Integer.valueOf(R.string.low_memory_error))) {
            return;
        }
        onFileNotSelected();
    }

    private class GetCameraIntentTask extends AsyncTask<Void, Void, Uri> {
        private GetCameraIntentTask() {
        }

        @Override // android.os.AsyncTask
        public Uri doInBackground(Void... voidArr) {
            try {
                Context applicationContext = SelectFileDialog.this.mWindowAndroid.getApplicationContext();
                return UiUtils.getUriForImageCaptureFile(applicationContext, SelectFileDialog.this.getFileForImageCapture(applicationContext));
            } catch (IOException e) {
                Log.e(SelectFileDialog.TAG, "Cannot retrieve content uri from file", e);
                return null;
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(Uri uri) {
            SelectFileDialog.this.mCameraOutputUri = uri;
            if (SelectFileDialog.this.mCameraOutputUri == null && SelectFileDialog.this.captureCamera()) {
                SelectFileDialog.this.onFileNotSelected();
                return;
            }
            Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
            intent.setFlags(3);
            intent.putExtra("output", SelectFileDialog.this.mCameraOutputUri);
            intent.setClipData(ClipData.newUri(SelectFileDialog.this.mWindowAndroid.getApplicationContext().getContentResolver(), UiUtils.IMAGE_FILE_PATH, SelectFileDialog.this.mCameraOutputUri));
            SelectFileDialog.this.launchSelectFileWithCameraIntent(true, intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public File getFileForImageCapture(Context context) throws IOException {
        return File.createTempFile(String.valueOf(System.currentTimeMillis()), ".jpg", UiUtils.getDirectoryForImageCapture(context));
    }

    @Override // org.chromium.ui.base.WindowAndroid.IntentCallback
    public void onIntentCompleted(WindowAndroid windowAndroid, int i, ContentResolver contentResolver, Intent intent) {
        if (i != -1) {
            onFileNotSelected();
            return;
        }
        if (intent == null || (intent.getData() == null && intent.getClipData() == null)) {
            nativeOnFileSelected(this.mNativeSelectFileDialog, "file".equals(this.mCameraOutputUri.getScheme()) ? this.mCameraOutputUri.getPath() : this.mCameraOutputUri.toString(), this.mCameraOutputUri.getLastPathSegment());
            windowAndroid.sendBroadcast(new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE", this.mCameraOutputUri));
            return;
        }
        if (intent.getData() == null && intent.getClipData() != null) {
            ClipData clipData = intent.getClipData();
            int itemCount = clipData.getItemCount();
            if (itemCount == 0) {
                onFileNotSelected();
                return;
            }
            Uri[] uriArr = new Uri[itemCount];
            for (int i2 = 0; i2 < itemCount; i2++) {
                uriArr[i2] = clipData.getItemAt(i2).getUri();
            }
            new GetDisplayNameTask(contentResolver, true).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, uriArr);
            return;
        }
        if ("file".equals(intent.getData().getScheme())) {
            nativeOnFileSelected(this.mNativeSelectFileDialog, intent.getData().getSchemeSpecificPart(), "");
        } else if ("content".equals(intent.getScheme())) {
            new GetDisplayNameTask(contentResolver, false).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, intent.getData());
        } else {
            onFileNotSelected();
            windowAndroid.showError(R.string.opening_file_error);
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid.PermissionCallback
    public void onRequestPermissionsResult(String[] strArr, int[] iArr) {
        for (int i : iArr) {
            if (i == -1 && this.mCapture) {
                onFileNotSelected();
                return;
            }
        }
        launchSelectFileIntent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onFileNotSelected() {
        nativeOnFileNotSelected(this.mNativeSelectFileDialog);
    }

    private boolean noSpecificType() {
        return this.mFileTypes.size() != 1 || this.mFileTypes.contains(ANY_TYPES);
    }

    private boolean shouldShowTypes(String str, String str2) {
        if (noSpecificType() || this.mFileTypes.contains(str)) {
            return true;
        }
        return acceptSpecificType(str2);
    }

    private boolean shouldShowImageTypes() {
        return shouldShowTypes(ALL_IMAGE_TYPES, IMAGE_TYPE);
    }

    private boolean shouldShowVideoTypes() {
        return shouldShowTypes(ALL_VIDEO_TYPES, VIDEO_TYPE);
    }

    private boolean shouldShowAudioTypes() {
        return shouldShowTypes(ALL_AUDIO_TYPES, AUDIO_TYPE);
    }

    private boolean acceptsSpecificType(String str) {
        return this.mFileTypes.size() == 1 && TextUtils.equals(this.mFileTypes.get(0), str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean captureCamera() {
        return this.mCapture && acceptsSpecificType(ALL_IMAGE_TYPES);
    }

    private boolean captureCamcorder() {
        return this.mCapture && acceptsSpecificType(ALL_VIDEO_TYPES);
    }

    private boolean captureMicrophone() {
        return this.mCapture && acceptsSpecificType(ALL_AUDIO_TYPES);
    }

    private boolean acceptSpecificType(String str) {
        Iterator<String> it = this.mFileTypes.iterator();
        while (it.hasNext()) {
            if (it.next().startsWith(str)) {
                return true;
            }
        }
        return false;
    }

    private class GetDisplayNameTask extends AsyncTask<Uri, Void, String[]> {
        final ContentResolver mContentResolver;
        String[] mFilePaths;
        final boolean mIsMultiple;

        public GetDisplayNameTask(ContentResolver contentResolver, boolean z) {
            this.mContentResolver = contentResolver;
            this.mIsMultiple = z;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public String[] doInBackground(Uri... uriArr) {
            this.mFilePaths = new String[uriArr.length];
            String[] strArr = new String[uriArr.length];
            for (int i = 0; i < uriArr.length; i++) {
                try {
                    this.mFilePaths[i] = uriArr[i].toString();
                    strArr[i] = ContentUriUtils.getDisplayName(uriArr[i], this.mContentResolver, "_display_name");
                } catch (SecurityException unused) {
                    Log.w(SelectFileDialog.TAG, "Unable to extract results from the content provider");
                    return null;
                }
            }
            return strArr;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // android.os.AsyncTask
        public void onPostExecute(String[] strArr) {
            if (strArr == null) {
                SelectFileDialog.this.onFileNotSelected();
            } else if (this.mIsMultiple) {
                SelectFileDialog selectFileDialog = SelectFileDialog.this;
                selectFileDialog.nativeOnMultipleFilesSelected(selectFileDialog.mNativeSelectFileDialog, this.mFilePaths, strArr);
            } else {
                SelectFileDialog selectFileDialog2 = SelectFileDialog.this;
                selectFileDialog2.nativeOnFileSelected(selectFileDialog2.mNativeSelectFileDialog, this.mFilePaths[0], strArr[0]);
            }
        }
    }

    private static SelectFileDialog create(long j) {
        return new SelectFileDialog(j);
    }
}
