package com.android.server.telecom.components;

import android.content.Context;
import android.os.UserHandle;

/* JADX INFO: loaded from: classes.dex */
public interface UserCallIntentProcessorFactory {
    UserCallIntentProcessor create(Context context, UserHandle userHandle);
}
