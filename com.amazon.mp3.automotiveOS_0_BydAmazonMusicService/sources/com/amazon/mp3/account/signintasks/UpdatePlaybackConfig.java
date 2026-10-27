package com.amazon.mp3.account.signintasks;

import android.content.Context;
import com.amazon.mp3.playback.harley.PlaybackInitializationHelper;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class UpdatePlaybackConfig implements SignInTask {
    private static final String TAG = "UpdatePlaybackConfig";
    private Context mContext;

    public UpdatePlaybackConfig(Context context) {
        this.mContext = context;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() throws Exception {
        PlaybackInitializationHelper.updatePlaybackConfigAuthenticationInfo(this.mContext);
    }

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }
}
