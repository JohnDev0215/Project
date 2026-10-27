package com.google.android.gms.cast.framework.media.internal;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzi extends IInterface {
    @Nullable
    Bitmap zze(Uri uri) throws RemoteException;
}
