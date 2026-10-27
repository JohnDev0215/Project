package android.media;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class MediaRoute2Info implements Parcelable {
    static {
        throw new NoClassDefFoundError();
    }

    public native /* synthetic */ int getConnectionState();

    @Nullable
    public native /* synthetic */ CharSequence getDescription();

    @Nullable
    public native /* synthetic */ Bundle getExtras();

    @Nullable
    public native /* synthetic */ Uri getIconUri();

    @NonNull
    public native /* synthetic */ String getId();

    @NonNull
    public native /* synthetic */ CharSequence getName();

    public native /* synthetic */ int getVolume();

    public native /* synthetic */ int getVolumeHandling();

    public native /* synthetic */ int getVolumeMax();

    public native /* synthetic */ boolean isSystemRoute();
}
