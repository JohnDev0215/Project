package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.account.AccountManagerFactory;
import com.amazon.mp3.util.Log;
import com.amazon.music.account.AccountManager;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountServiceConfiguration;
import com.amazon.music.account.MarketplaceProvisionFailedException;
import com.amazon.music.account.MusicIdentityServiceConfiguration;
import com.amazon.music.account.StratusServiceConfiguration;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class BuildUserAccount implements SignInTask {
    private static final String TAG = "BuildUserAccount";
    private Context mContext;

    public BuildUserAccount(final Context context) {
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
        AccountManagerFactory accountManagerFactory = new AccountManagerFactory();
        AccountServiceConfiguration accountServiceConfigurationBuildAccountServiceConfiguration = accountManagerFactory.buildAccountServiceConfiguration(this.mContext);
        StratusServiceConfiguration stratusServiceConfigurationBuildStratusServiceConfiguration = accountManagerFactory.buildStratusServiceConfiguration(this.mContext);
        MusicIdentityServiceConfiguration musicIdentityServiceConfigurationBuildMusicIdentityServiceConfiguration = accountManagerFactory.buildMusicIdentityServiceConfiguration(this.mContext);
        AccountManager accountManager = AccountManagerSingleton.get();
        accountManager.onUserSignedIn(accountServiceConfigurationBuildAccountServiceConfiguration, stratusServiceConfigurationBuildStratusServiceConfiguration, musicIdentityServiceConfigurationBuildMusicIdentityServiceConfiguration);
        try {
            accountManager.updateUserMarketplace();
        } catch (MarketplaceProvisionFailedException unused) {
            throw new MusicUnsupportedMarketplaceException();
        }
    }
}
