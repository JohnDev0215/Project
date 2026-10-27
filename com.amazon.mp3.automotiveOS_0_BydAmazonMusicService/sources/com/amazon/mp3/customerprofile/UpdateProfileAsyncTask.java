package com.amazon.mp3.customerprofile;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import com.amazon.mp3.customerprofile.CustomerProfileService;

/* JADX INFO: loaded from: classes3.dex */
public class UpdateProfileAsyncTask extends AsyncTask<Void, Void, Void> {
    private CustomerProfileService.Callback callback;
    private Context context;
    private int imageRotation;
    private Uri profileAvatarUri;
    private String profileHandle;
    private String profileName;
    private String profileStatus;
    private String shareListeningHistory;

    public UpdateProfileAsyncTask(final Context context, final Uri profileAvatarUri, final String profileName, final String profileHandle, final String profileStatus, final String shareListeningHistory, final CustomerProfileService.Callback callback, final int imageRotation) {
        this.context = context;
        this.profileAvatarUri = profileAvatarUri;
        this.profileName = profileName;
        this.profileHandle = profileHandle;
        this.profileStatus = profileStatus;
        this.callback = callback;
        this.imageRotation = imageRotation;
        this.shareListeningHistory = shareListeningHistory;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Void doInBackground(final Void... params) {
        if (this.callback == null) {
            return null;
        }
        new CustomerProfileService(this.context).update(this.profileAvatarUri, this.profileName, this.profileHandle, this.profileStatus, this.callback, this.imageRotation, this.shareListeningHistory);
        return null;
    }

    public void clear() {
        this.context = null;
        this.profileAvatarUri = null;
        this.profileName = null;
        this.profileHandle = null;
        this.profileStatus = null;
        this.callback = null;
        this.imageRotation = 0;
    }
}
