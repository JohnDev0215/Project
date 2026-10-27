package com.android.car.developeroptions.users;

import android.content.ClipData;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.os.UserHandle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListPopupWindow;
import android.widget.TextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import com.android.car.developeroptions.R;
import com.android.settingslib.RestrictedLockUtils;
import com.android.settingslib.RestrictedLockUtilsInternal;
import com.android.settingslib.drawable.CircleFramedDrawable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import libcore.io.Streams;

/* JADX INFO: loaded from: classes.dex */
public class EditUserPhotoController {
    private final Context mContext;
    private final Uri mCropPictureUri;
    private final Fragment mFragment;
    private final ImageView mImageView;
    private Bitmap mNewUserPhotoBitmap;
    private Drawable mNewUserPhotoDrawable;
    private final int mPhotoSize;
    private final Uri mTakePictureUri;

    public EditUserPhotoController(Fragment fragment, ImageView imageView, Bitmap bitmap, Drawable drawable, boolean z) {
        Context context = imageView.getContext();
        this.mContext = context;
        this.mFragment = fragment;
        this.mImageView = imageView;
        this.mCropPictureUri = createTempImageUri(context, "CropEditUserPhoto.jpg", !z);
        this.mTakePictureUri = createTempImageUri(this.mContext, "TakeEditUserPhoto2.jpg", !z);
        this.mPhotoSize = getPhotoSize(this.mContext);
        this.mImageView.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.developeroptions.users.EditUserPhotoController.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                EditUserPhotoController.this.showUpdatePhotoPopup();
            }
        });
        this.mNewUserPhotoBitmap = bitmap;
        this.mNewUserPhotoDrawable = drawable;
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        if (i2 != -1) {
            return false;
        }
        Uri data = (intent == null || intent.getData() == null) ? this.mTakePictureUri : intent.getData();
        switch (i) {
            case 1001:
            case 1002:
                if (this.mTakePictureUri.equals(data)) {
                    cropPhoto();
                } else {
                    copyAndCropPhoto(data);
                }
                break;
            case 1003:
                onPhotoCropped(data, true);
                break;
        }
        return false;
    }

    public Bitmap getNewUserPhotoBitmap() {
        return this.mNewUserPhotoBitmap;
    }

    public Drawable getNewUserPhotoDrawable() {
        return this.mNewUserPhotoDrawable;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUpdatePhotoPopup() {
        boolean zCanTakePhoto = canTakePhoto();
        boolean zCanChoosePhoto = canChoosePhoto();
        if (zCanTakePhoto || zCanChoosePhoto) {
            Context context = this.mImageView.getContext();
            ArrayList arrayList = new ArrayList();
            if (zCanTakePhoto) {
                arrayList.add(new RestrictedMenuItem(context, context.getString(R.string.user_image_take_photo), "no_set_user_icon", new Runnable() { // from class: com.android.car.developeroptions.users.EditUserPhotoController.2
                    @Override // java.lang.Runnable
                    public void run() {
                        EditUserPhotoController.this.takePhoto();
                    }
                }));
            }
            if (zCanChoosePhoto) {
                arrayList.add(new RestrictedMenuItem(context, context.getString(R.string.user_image_choose_photo), "no_set_user_icon", new Runnable() { // from class: com.android.car.developeroptions.users.EditUserPhotoController.3
                    @Override // java.lang.Runnable
                    public void run() {
                        EditUserPhotoController.this.choosePhoto();
                    }
                }));
            }
            final ListPopupWindow listPopupWindow = new ListPopupWindow(context);
            listPopupWindow.setAnchorView(this.mImageView);
            listPopupWindow.setModal(true);
            listPopupWindow.setInputMethodMode(2);
            listPopupWindow.setAdapter(new RestrictedPopupMenuAdapter(context, arrayList));
            listPopupWindow.setWidth(Math.max(this.mImageView.getWidth(), context.getResources().getDimensionPixelSize(R.dimen.update_user_photo_popup_min_width)));
            listPopupWindow.setDropDownGravity(8388611);
            listPopupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener(this) { // from class: com.android.car.developeroptions.users.EditUserPhotoController.4
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    listPopupWindow.dismiss();
                    ((RestrictedMenuItem) adapterView.getAdapter().getItem(i)).doAction();
                }
            });
            listPopupWindow.show();
        }
    }

    private boolean canTakePhoto() {
        return this.mImageView.getContext().getPackageManager().queryIntentActivities(new Intent("android.media.action.IMAGE_CAPTURE"), 65536).size() > 0;
    }

    private boolean canChoosePhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        return this.mImageView.getContext().getPackageManager().queryIntentActivities(intent, 0).size() > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void takePhoto() {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        appendOutputExtra(intent, this.mTakePictureUri);
        this.mFragment.startActivityForResult(intent, 1002);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void choosePhoto() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT", (Uri) null);
        intent.setType("image/*");
        appendOutputExtra(intent, this.mTakePictureUri);
        this.mFragment.startActivityForResult(intent, 1001);
    }

    private void copyAndCropPhoto(final Uri uri) {
        new AsyncTask<Void, Void, Void>() { // from class: com.android.car.developeroptions.users.EditUserPhotoController.5
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public Void doInBackground(Void... voidArr) {
                ContentResolver contentResolver = EditUserPhotoController.this.mContext.getContentResolver();
                try {
                    InputStream inputStreamOpenInputStream = contentResolver.openInputStream(uri);
                    try {
                        OutputStream outputStreamOpenOutputStream = contentResolver.openOutputStream(EditUserPhotoController.this.mTakePictureUri);
                        try {
                            Streams.copy(inputStreamOpenInputStream, outputStreamOpenOutputStream);
                            if (outputStreamOpenOutputStream != null) {
                                outputStreamOpenOutputStream.close();
                            }
                            if (inputStreamOpenInputStream == null) {
                                return null;
                            }
                            inputStreamOpenInputStream.close();
                            return null;
                        } finally {
                        }
                    } finally {
                    }
                } catch (IOException e) {
                    Log.w("EditUserPhotoController", "Failed to copy photo", e);
                    return null;
                }
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Void r1) {
                if (EditUserPhotoController.this.mFragment.isAdded()) {
                    EditUserPhotoController.this.cropPhoto();
                }
            }
        }.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cropPhoto() {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(this.mTakePictureUri, "image/*");
        appendOutputExtra(intent, this.mCropPictureUri);
        appendCropExtras(intent);
        if (intent.resolveActivity(this.mContext.getPackageManager()) != null) {
            try {
                StrictMode.disableDeathOnFileUriExposure();
                this.mFragment.startActivityForResult(intent, 1003);
                return;
            } finally {
                StrictMode.enableDeathOnFileUriExposure();
            }
        }
        onPhotoCropped(this.mTakePictureUri, false);
    }

    private void appendOutputExtra(Intent intent, Uri uri) {
        intent.putExtra("output", uri);
        intent.addFlags(3);
        intent.setClipData(ClipData.newRawUri("output", uri));
    }

    private void appendCropExtras(Intent intent) {
        intent.putExtra("crop", "true");
        intent.putExtra("scale", true);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        intent.putExtra("outputX", this.mPhotoSize);
        intent.putExtra("outputY", this.mPhotoSize);
    }

    private void onPhotoCropped(final Uri uri, final boolean z) {
        new AsyncTask<Void, Void, Bitmap>() { // from class: com.android.car.developeroptions.users.EditUserPhotoController.6
            /* JADX INFO: Access modifiers changed from: protected */
            /* JADX WARN: Removed duplicated region for block: B:44:0x0042 A[EXC_TOP_SPLITTER, SYNTHETIC] */
            @Override // android.os.AsyncTask
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public android.graphics.Bitmap doInBackground(java.lang.Void... r8) throws java.lang.Throwable {
                /*
                    r7 = this;
                    java.lang.String r8 = "Cannot close image stream"
                    java.lang.String r0 = "EditUserPhotoController"
                    boolean r1 = r2
                    r2 = 0
                    if (r1 == 0) goto L4b
                    com.android.car.developeroptions.users.EditUserPhotoController r1 = com.android.car.developeroptions.users.EditUserPhotoController.this     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                    android.content.Context r1 = com.android.car.developeroptions.users.EditUserPhotoController.access$300(r1)     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                    android.net.Uri r7 = r3     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                    java.io.InputStream r7 = r1.openInputStream(r7)     // Catch: java.lang.Throwable -> L2a java.io.FileNotFoundException -> L2c
                    android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r7)     // Catch: java.io.FileNotFoundException -> L28 java.lang.Throwable -> L3e
                    if (r7 == 0) goto L27
                    r7.close()     // Catch: java.io.IOException -> L23
                    goto L27
                L23:
                    r7 = move-exception
                    android.util.Log.w(r0, r8, r7)
                L27:
                    return r1
                L28:
                    r1 = move-exception
                    goto L2e
                L2a:
                    r1 = move-exception
                    goto L40
                L2c:
                    r1 = move-exception
                    r7 = r2
                L2e:
                    java.lang.String r3 = "Cannot find image file"
                    android.util.Log.w(r0, r3, r1)     // Catch: java.lang.Throwable -> L3e
                    if (r7 == 0) goto L3d
                    r7.close()     // Catch: java.io.IOException -> L39
                    goto L3d
                L39:
                    r7 = move-exception
                    android.util.Log.w(r0, r8, r7)
                L3d:
                    return r2
                L3e:
                    r1 = move-exception
                    r2 = r7
                L40:
                    if (r2 == 0) goto L4a
                    r2.close()     // Catch: java.io.IOException -> L46
                    goto L4a
                L46:
                    r7 = move-exception
                    android.util.Log.w(r0, r8, r7)
                L4a:
                    throw r1
                L4b:
                    com.android.car.developeroptions.users.EditUserPhotoController r8 = com.android.car.developeroptions.users.EditUserPhotoController.this
                    int r8 = com.android.car.developeroptions.users.EditUserPhotoController.access$700(r8)
                    com.android.car.developeroptions.users.EditUserPhotoController r0 = com.android.car.developeroptions.users.EditUserPhotoController.this
                    int r0 = com.android.car.developeroptions.users.EditUserPhotoController.access$700(r0)
                    android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
                    android.graphics.Bitmap r8 = android.graphics.Bitmap.createBitmap(r8, r0, r1)
                    android.graphics.Canvas r0 = new android.graphics.Canvas
                    r0.<init>(r8)
                    com.android.car.developeroptions.users.EditUserPhotoController r1 = com.android.car.developeroptions.users.EditUserPhotoController.this     // Catch: java.io.FileNotFoundException -> Lb5
                    android.content.Context r1 = com.android.car.developeroptions.users.EditUserPhotoController.access$300(r1)     // Catch: java.io.FileNotFoundException -> Lb5
                    android.content.ContentResolver r1 = r1.getContentResolver()     // Catch: java.io.FileNotFoundException -> Lb5
                    android.net.Uri r3 = r3     // Catch: java.io.FileNotFoundException -> Lb5
                    java.io.InputStream r1 = r1.openInputStream(r3)     // Catch: java.io.FileNotFoundException -> Lb5
                    android.graphics.Bitmap r1 = android.graphics.BitmapFactory.decodeStream(r1)     // Catch: java.io.FileNotFoundException -> Lb5
                    if (r1 == 0) goto Lb5
                    int r2 = r1.getWidth()
                    int r3 = r1.getHeight()
                    int r2 = java.lang.Math.min(r2, r3)
                    int r3 = r1.getWidth()
                    int r3 = r3 - r2
                    int r3 = r3 / 2
                    int r4 = r1.getHeight()
                    int r4 = r4 - r2
                    int r4 = r4 / 2
                    android.graphics.Rect r5 = new android.graphics.Rect
                    int r6 = r3 + r2
                    int r2 = r2 + r4
                    r5.<init>(r3, r4, r6, r2)
                    android.graphics.Rect r2 = new android.graphics.Rect
                    com.android.car.developeroptions.users.EditUserPhotoController r3 = com.android.car.developeroptions.users.EditUserPhotoController.this
                    int r3 = com.android.car.developeroptions.users.EditUserPhotoController.access$700(r3)
                    com.android.car.developeroptions.users.EditUserPhotoController r7 = com.android.car.developeroptions.users.EditUserPhotoController.this
                    int r7 = com.android.car.developeroptions.users.EditUserPhotoController.access$700(r7)
                    r4 = 0
                    r2.<init>(r4, r4, r3, r7)
                    android.graphics.Paint r7 = new android.graphics.Paint
                    r7.<init>()
                    r0.drawBitmap(r1, r5, r2, r7)
                    return r8
                Lb5:
                    return r2
                */
                throw new UnsupportedOperationException("Method not decompiled: com.android.car.developeroptions.users.EditUserPhotoController.AnonymousClass6.doInBackground(java.lang.Void[]):android.graphics.Bitmap");
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // android.os.AsyncTask
            public void onPostExecute(Bitmap bitmap) {
                if (bitmap != null) {
                    EditUserPhotoController.this.mNewUserPhotoBitmap = bitmap;
                    EditUserPhotoController editUserPhotoController = EditUserPhotoController.this;
                    editUserPhotoController.mNewUserPhotoDrawable = CircleFramedDrawable.getInstance(editUserPhotoController.mImageView.getContext(), EditUserPhotoController.this.mNewUserPhotoBitmap);
                    EditUserPhotoController.this.mImageView.setImageDrawable(EditUserPhotoController.this.mNewUserPhotoDrawable);
                }
                new File(EditUserPhotoController.this.mContext.getCacheDir(), "TakeEditUserPhoto2.jpg").delete();
                new File(EditUserPhotoController.this.mContext.getCacheDir(), "CropEditUserPhoto.jpg").delete();
            }
        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
    }

    private static int getPhotoSize(Context context) {
        Cursor cursorQuery = context.getContentResolver().query(ContactsContract.DisplayPhoto.CONTENT_MAX_DIMENSIONS_URI, new String[]{"display_max_dim"}, null, null, null);
        try {
            cursorQuery.moveToFirst();
            return cursorQuery.getInt(0);
        } finally {
            cursorQuery.close();
        }
    }

    private Uri createTempImageUri(Context context, String str, boolean z) {
        File cacheDir = context.getCacheDir();
        cacheDir.mkdirs();
        File file = new File(cacheDir, str);
        if (z) {
            file.delete();
        }
        return FileProvider.getUriForFile(context, "com.android.car.developeroptions.files", file);
    }

    File saveNewUserPhotoBitmap() {
        if (this.mNewUserPhotoBitmap == null) {
            return null;
        }
        try {
            File file = new File(this.mContext.getCacheDir(), "NewUserPhoto.png");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            this.mNewUserPhotoBitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
            return file;
        } catch (IOException e) {
            Log.e("EditUserPhotoController", "Cannot create temp file", e);
            return null;
        }
    }

    static Bitmap loadNewUserPhotoBitmap(File file) {
        return BitmapFactory.decodeFile(file.getAbsolutePath());
    }

    void removeNewUserPhotoBitmapFile() {
        new File(this.mContext.getCacheDir(), "NewUserPhoto.png").delete();
    }

    private static final class RestrictedMenuItem {
        private final Runnable mAction;
        private final RestrictedLockUtils.EnforcedAdmin mAdmin;
        private final Context mContext;
        private final boolean mIsRestrictedByBase;
        private final String mTitle;

        public RestrictedMenuItem(Context context, String str, String str2, Runnable runnable) {
            this.mContext = context;
            this.mTitle = str;
            this.mAction = runnable;
            int iMyUserId = UserHandle.myUserId();
            this.mAdmin = RestrictedLockUtilsInternal.checkIfRestrictionEnforced(context, str2, iMyUserId);
            this.mIsRestrictedByBase = RestrictedLockUtilsInternal.hasBaseUserRestriction(this.mContext, str2, iMyUserId);
        }

        public String toString() {
            return this.mTitle;
        }

        final void doAction() {
            if (isRestrictedByBase()) {
                return;
            }
            if (isRestrictedByAdmin()) {
                RestrictedLockUtils.sendShowAdminSupportDetailsIntent(this.mContext, this.mAdmin);
            } else {
                this.mAction.run();
            }
        }

        final boolean isRestrictedByAdmin() {
            return this.mAdmin != null;
        }

        final boolean isRestrictedByBase() {
            return this.mIsRestrictedByBase;
        }
    }

    private static final class RestrictedPopupMenuAdapter extends ArrayAdapter<RestrictedMenuItem> {
        public RestrictedPopupMenuAdapter(Context context, List<RestrictedMenuItem> list) {
            super(context, R.layout.restricted_popup_menu_item, R.id.text, list);
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view2 = super.getView(i, view, viewGroup);
            RestrictedMenuItem item = getItem(i);
            TextView textView = (TextView) view2.findViewById(R.id.text);
            ImageView imageView = (ImageView) view2.findViewById(R.id.restricted_icon);
            textView.setEnabled((item.isRestrictedByAdmin() || item.isRestrictedByBase()) ? false : true);
            imageView.setVisibility((!item.isRestrictedByAdmin() || item.isRestrictedByBase()) ? 8 : 0);
            return view2;
        }
    }
}
