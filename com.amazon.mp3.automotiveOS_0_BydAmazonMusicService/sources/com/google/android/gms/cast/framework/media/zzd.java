package com.google.android.gms.cast.framework.media;

import android.os.IInterface;
import android.os.RemoteException;
import androidx.annotation.Nullable;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.dynamic.IObjectWrapper;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface zzd extends IInterface {
    @Nullable
    WebImage zze(MediaMetadata mediaMetadata, int i) throws RemoteException;

    @Nullable
    WebImage zzf(MediaMetadata mediaMetadata, ImageHints imageHints) throws RemoteException;

    IObjectWrapper zzg() throws RemoteException;
}
