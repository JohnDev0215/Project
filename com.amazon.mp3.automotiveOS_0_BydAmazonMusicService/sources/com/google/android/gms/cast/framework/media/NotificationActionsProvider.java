package com.google.android.gms.cast.framework.media;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public abstract class NotificationActionsProvider {
    private final Context zza;
    private final zzg zzb = new zzz(this, null);

    public NotificationActionsProvider(@NonNull Context context) {
        this.zza = context.getApplicationContext();
    }

    @NonNull
    public Context getApplicationContext() {
        return this.zza;
    }

    @NonNull
    public abstract int[] getCompactViewActionIndices();

    @NonNull
    public abstract List<NotificationAction> getNotificationActions();

    public final zzg zza() {
        return this.zzb;
    }
}
