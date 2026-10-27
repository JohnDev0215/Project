package com.amazon.digitalmusicplayback.internal;

import android.content.Context;
import androidx.annotation.NonNull;
import com.amazon.digitalmusicplayback.internal.LoggingFacade;

/* JADX INFO: loaded from: classes2.dex */
public final class AndroidSystemInformation extends PlatformSystemInformation {
    private final Context mApplicationContext;
    private final LoggingFacade.Logger mLogger;
    private final boolean mRunningOnTV;

    public AndroidSystemInformation(@NonNull LoggingFacade loggingFacade, @NonNull Context context) {
        this.mLogger = loggingFacade.currentSourceFileLog();
        this.mApplicationContext = context;
        this.mRunningOnTV = context.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    @Override // com.amazon.digitalmusicplayback.internal.PlatformSystemInformation
    public boolean isRunningOnTV() {
        return this.mRunningOnTV;
    }
}
