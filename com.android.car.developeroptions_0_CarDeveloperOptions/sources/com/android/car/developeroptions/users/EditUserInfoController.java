package com.android.car.developeroptions.users;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.UserInfo;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.UserHandle;
import android.os.UserManager;
import android.text.Editable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import com.android.car.developeroptions.R;
import com.android.settingslib.Utils;
import com.android.settingslib.drawable.CircleFramedDrawable;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class EditUserInfoController {
    private Dialog mEditUserInfoDialog;
    private EditUserPhotoController mEditUserPhotoController;
    private Bitmap mSavedPhoto;
    private UserHandle mUser;
    private UserManager mUserManager;
    private boolean mWaitingForActivityResult = false;

    public interface OnContentChangedCallback {
        void onLabelChanged(CharSequence charSequence);

        void onPhotoChanged(Drawable drawable);
    }

    public void clear() {
        this.mEditUserPhotoController.removeNewUserPhotoBitmapFile();
        this.mEditUserInfoDialog = null;
        this.mSavedPhoto = null;
    }

    public void onRestoreInstanceState(Bundle bundle) {
        String string = bundle.getString("pending_photo");
        if (string != null) {
            this.mSavedPhoto = EditUserPhotoController.loadNewUserPhotoBitmap(new File(string));
        }
        this.mWaitingForActivityResult = bundle.getBoolean("awaiting_result", false);
    }

    public void onSaveInstanceState(Bundle bundle) {
        EditUserPhotoController editUserPhotoController;
        File fileSaveNewUserPhotoBitmap;
        Dialog dialog = this.mEditUserInfoDialog;
        if (dialog != null && dialog.isShowing() && (editUserPhotoController = this.mEditUserPhotoController) != null && (fileSaveNewUserPhotoBitmap = editUserPhotoController.saveNewUserPhotoBitmap()) != null) {
            bundle.putString("pending_photo", fileSaveNewUserPhotoBitmap.getPath());
        }
        boolean z = this.mWaitingForActivityResult;
        if (z) {
            bundle.putBoolean("awaiting_result", z);
        }
    }

    public void startingActivityForResult() {
        this.mWaitingForActivityResult = true;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        this.mWaitingForActivityResult = false;
        if (this.mEditUserInfoDialog != null) {
            this.mEditUserPhotoController.onActivityResult(i, i2, intent);
        }
    }

    public Dialog createDialog(final Fragment fragment, final Drawable drawable, final CharSequence charSequence, int i, final OnContentChangedCallback onContentChangedCallback, UserHandle userHandle) {
        Drawable userIcon;
        FragmentActivity activity = fragment.getActivity();
        this.mUser = userHandle;
        if (this.mUserManager == null) {
            this.mUserManager = (UserManager) activity.getSystemService(UserManager.class);
        }
        View viewInflate = activity.getLayoutInflater().inflate(R.layout.edit_user_info_dialog_content, (ViewGroup) null);
        UserInfo userInfo = this.mUserManager.getUserInfo(this.mUser.getIdentifier());
        final EditText editText = (EditText) viewInflate.findViewById(R.id.user_name);
        editText.setText(userInfo.name);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.user_photo);
        Bitmap bitmap = this.mSavedPhoto;
        if (bitmap != null) {
            userIcon = CircleFramedDrawable.getInstance(activity, bitmap);
        } else {
            userIcon = drawable == null ? Utils.getUserIcon(activity, this.mUserManager, userInfo) : drawable;
        }
        imageView.setImageDrawable(userIcon);
        this.mEditUserPhotoController = createEditUserPhotoController(fragment, imageView, userIcon);
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.profile_info_settings_title);
        builder.setView(viewInflate);
        builder.setCancelable(true);
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.EditUserInfoController.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                if (i2 == -1) {
                    Editable text = editText.getText();
                    if (!TextUtils.isEmpty(text) && (charSequence == null || !text.toString().equals(charSequence.toString()))) {
                        OnContentChangedCallback onContentChangedCallback2 = onContentChangedCallback;
                        if (onContentChangedCallback2 != null) {
                            onContentChangedCallback2.onLabelChanged(text.toString());
                        }
                        EditUserInfoController.this.mUserManager.setUserName(EditUserInfoController.this.mUser.getIdentifier(), text.toString());
                    }
                    Drawable newUserPhotoDrawable = EditUserInfoController.this.mEditUserPhotoController.getNewUserPhotoDrawable();
                    Bitmap newUserPhotoBitmap = EditUserInfoController.this.mEditUserPhotoController.getNewUserPhotoBitmap();
                    if (newUserPhotoDrawable != null && newUserPhotoBitmap != null && !newUserPhotoDrawable.equals(drawable)) {
                        OnContentChangedCallback onContentChangedCallback3 = onContentChangedCallback;
                        if (onContentChangedCallback3 != null) {
                            onContentChangedCallback3.onPhotoChanged(newUserPhotoDrawable);
                        }
                        new AsyncTask<Void, Void, Void>() { // from class: com.android.car.developeroptions.users.EditUserInfoController.2.1
                            /* JADX INFO: Access modifiers changed from: protected */
                            @Override // android.os.AsyncTask
                            public Void doInBackground(Void... voidArr) {
                                EditUserInfoController.this.mUserManager.setUserIcon(EditUserInfoController.this.mUser.getIdentifier(), EditUserInfoController.this.mEditUserPhotoController.getNewUserPhotoBitmap());
                                return null;
                            }
                        }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, null);
                    }
                    fragment.getActivity().removeDialog(1);
                }
                EditUserInfoController.this.clear();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.users.EditUserInfoController.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                EditUserInfoController.this.clear();
            }
        });
        AlertDialog alertDialogCreate = builder.create();
        this.mEditUserInfoDialog = alertDialogCreate;
        alertDialogCreate.getWindow().setSoftInputMode(4);
        return this.mEditUserInfoDialog;
    }

    EditUserPhotoController createEditUserPhotoController(Fragment fragment, ImageView imageView, Drawable drawable) {
        return new EditUserPhotoController(fragment, imageView, this.mSavedPhoto, drawable, this.mWaitingForActivityResult);
    }
}
