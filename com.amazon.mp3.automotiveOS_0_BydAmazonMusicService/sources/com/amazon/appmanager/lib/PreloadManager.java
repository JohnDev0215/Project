package com.amazon.appmanager.lib;

import android.content.Context;
import android.os.RemoteException;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;

/* JADX INFO: loaded from: classes2.dex */
public abstract class PreloadManager {
    private static PreloadManager defaultPreloadManager;

    @Nullable
    public abstract String getPreloadAssociateTagWithoutRegionId_Minerva(@NonNull Context context) throws RemoteException, SecurityException, IOException, IllegalArgumentException;

    public static synchronized PreloadManager getDefault() {
        if (defaultPreloadManager == null) {
            defaultPreloadManager = new DefaultPreloadManager();
        }
        return defaultPreloadManager;
    }
}
