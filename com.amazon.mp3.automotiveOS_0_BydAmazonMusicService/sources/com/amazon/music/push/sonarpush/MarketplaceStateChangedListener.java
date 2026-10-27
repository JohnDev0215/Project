package com.amazon.music.push.sonarpush;

import android.content.Context;
import android.util.Log;
import com.amazon.music.account.AccountManagerSingleton;
import com.amazon.music.account.AccountStateChangeListener;
import com.amazon.music.account.AccountStateField;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class MarketplaceStateChangedListener {
    private static final String TAG = "MarketplaceStateChangedListener";
    private final AccountStateChangeListener accountStateChangeListener = new AccountStateChangeListener() { // from class: com.amazon.music.push.sonarpush.MarketplaceStateChangedListener.1
        @Override // com.amazon.music.account.AccountStateChangeListener
        public void onAccountStateChange(Set<AccountStateField> set) {
            Log.d(MarketplaceStateChangedListener.TAG, "Account state change detected");
            if (set.contains(AccountStateField.USER_MUSIC_TERRITORY_OF_RESIDENCE) || set.contains(AccountStateField.USER_LIBRARY_HOME_MARKETPLACE) || set.contains(AccountStateField.ACCOUNT_MIGRATED)) {
                PushNotificationManager.getInstance().updateApplicationMarketPlaceChanged(MarketplaceStateChangedListener.this.mContext);
            }
        }
    };
    private final Context mContext;

    public MarketplaceStateChangedListener(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public void registerListener() {
        Log.d(TAG, "Registering AccountStateChangeListener");
        AccountManagerSingleton.get().registerListener(this.accountStateChangeListener);
    }
}
