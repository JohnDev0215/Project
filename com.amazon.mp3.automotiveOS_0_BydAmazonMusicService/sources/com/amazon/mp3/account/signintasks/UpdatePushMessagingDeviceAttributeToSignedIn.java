package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.util.Log;
import com.amazon.music.push.PushMessagingClient;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class UpdatePushMessagingDeviceAttributeToSignedIn implements SignInTask {
    private static final String TAG = "UpdatePushMessagingDeviceAttributeToSignedIn";
    private final Context mContext;

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    public UpdatePushMessagingDeviceAttributeToSignedIn(final Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() {
        new PushMessagingClient().setSignedIn(this.mContext, true);
        String str = TAG;
        Log.debug(str, "Executing sign in task " + str);
    }
}
