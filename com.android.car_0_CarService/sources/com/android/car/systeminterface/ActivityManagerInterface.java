package com.android.car.systeminterface;

import android.content.Context;
import android.content.Intent;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes3.dex */
public interface ActivityManagerInterface {
    void sendBroadcastAsUser(Intent intent, UserHandle userHandle);

    public static class DefaultImpl implements ActivityManagerInterface {
        private final Context mContext;

        DefaultImpl(Context context) {
            this.mContext = context;
        }

        @Override // com.android.car.systeminterface.ActivityManagerInterface
        public void sendBroadcastAsUser(Intent intent, UserHandle user) {
            this.mContext.sendBroadcastAsUser(intent, user);
        }
    }
}
