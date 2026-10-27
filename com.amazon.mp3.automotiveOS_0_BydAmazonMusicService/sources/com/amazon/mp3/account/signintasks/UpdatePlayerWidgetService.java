package com.amazon.mp3.account.signintasks;

import com.amazon.mp3.util.Log;
import com.amazon.mp3.widget.PlayerWidgetController;
import com.amazon.music.signin.SignInTask;

/* JADX INFO: loaded from: classes3.dex */
public class UpdatePlayerWidgetService implements SignInTask {
    private static final String TAG = "UpdatePlayerWidgetService";

    @Override // com.amazon.music.signin.SignInTask
    public String getName() {
        return TAG;
    }

    @Override // com.amazon.music.signin.SignInTask
    public void execute() {
        String str = TAG;
        Log.debug(str, "Executing sign in task " + str);
        if (PlayerWidgetController.isSupported()) {
            PlayerWidgetController.getInstance().updateWidget();
        }
    }
}
