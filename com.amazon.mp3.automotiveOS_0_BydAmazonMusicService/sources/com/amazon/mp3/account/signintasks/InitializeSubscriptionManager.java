package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.hawkfire.upsell.SubscriptionManagerSingleton;
import com.amazon.mp3.util.Log;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class InitializeSubscriptionManager implements SignInTask {
    private static final String TAG = "InitializeSubscriptionManager";
    private Context mContext;

    public InitializeSubscriptionManager(final Context context) {
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
        SubscriptionManagerSingleton.initialize(this.mContext);
    }
}
