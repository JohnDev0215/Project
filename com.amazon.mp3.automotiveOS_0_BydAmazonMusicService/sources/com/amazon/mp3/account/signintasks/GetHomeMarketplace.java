package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.account.AccountRequestUtil;
import com.amazon.mp3.account.CustomerHomeDetails;
import com.amazon.mp3.account.credentials.AccountCredentialUtil;
import com.amazon.mp3.prime.DeviceAuthorizationManager;
import com.amazon.mp3.util.Log;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class GetHomeMarketplace implements SignInTask {
    public static final String TAG = "GetHomeMarketplace";
    private Context mContext;

    public GetHomeMarketplace(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        String str = TAG;
        Log.debug(str, "Executing sign in task " + str);
        if (AccountCredentialUtil.get().isSignedOut()) {
            throw new UserNotSignedInException();
        }
        if (AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
            CustomerHomeDetails customerHome = AccountRequestUtil.getCustomerHome(this.mContext);
            if (!AccountRequestUtil.isAccountValid(this.mContext)) {
                throw new AccountStatusException();
            }
            if (customerHome.getCloudPlayerAccountExists() && AmazonApplication.getCapabilities().shouldCloudBeSupported()) {
                if (AccountCredentialUtil.get().isSignedOut()) {
                    throw new UserNotSignedInException();
                }
                DeviceAuthorizationManager.getInstance().refresh();
                DeviceAuthorizationManager.getInstance().autoAuthorize(this.mContext);
                AccountRequestUtil.refreshAndStoreAccountDetailsForCurrentUser(this.mContext);
            }
        }
    }

    public class UserNotSignedInException extends RuntimeException {
        public UserNotSignedInException() {
        }
    }

    public class AccountStatusException extends RuntimeException {
        public AccountStatusException() {
        }
    }
}
