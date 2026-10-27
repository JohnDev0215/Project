package com.android.car.settings.users;

import android.car.user.CarUserManager;
import android.car.user.UserCreationResult;
import android.content.Context;
import android.content.pm.UserInfo;
import android.os.AsyncTask;
import com.android.car.settings.common.Logger;
import com.android.internal.infra.AndroidFuture;
import java.util.concurrent.ExecutionException;

/* JADX INFO: loaded from: classes5.dex */
public class AddNewUserTask extends AsyncTask<String, Void, UserInfo> {
    private static final Logger LOG = new Logger((Class<?>) AddNewUserTask.class);
    private final AddNewUserListener mAddNewUserListener;
    private final CarUserManager mCarUserManager;
    private final Context mContext;

    public interface AddNewUserListener {
        void onUserAddedFailure();

        void onUserAddedSuccess();
    }

    public AddNewUserTask(Context context, CarUserManager carUserManager, AddNewUserListener addNewUserListener) {
        this.mContext = context;
        this.mCarUserManager = carUserManager;
        this.mAddNewUserListener = addNewUserListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public UserInfo doInBackground(String... userNames) {
        AndroidFuture<UserCreationResult> future = this.mCarUserManager.createUser(userNames[0], 0);
        try {
            UserCreationResult result = (UserCreationResult) future.get();
            if (result.isSuccess()) {
                UserInfo user = result.getUser();
                if (user != null) {
                    android.car.userlib.UserHelper.setDefaultNonAdminRestrictions(this.mContext, user, true);
                    android.car.userlib.UserHelper.assignDefaultIcon(this.mContext, user);
                } else {
                    LOG.wtf("Inconsistent state: successful future with null user - " + result.toString());
                }
                return user;
            }
            return null;
        } catch (InterruptedException | ExecutionException e) {
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
            LOG.e("Error creating new user: ", e);
            return null;
        }
    }

    @Override // android.os.AsyncTask
    protected void onPreExecute() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(UserInfo user) {
        if (user != null) {
            this.mAddNewUserListener.onUserAddedSuccess();
            this.mCarUserManager.switchUser(user.id);
        } else {
            this.mAddNewUserListener.onUserAddedFailure();
        }
    }
}
