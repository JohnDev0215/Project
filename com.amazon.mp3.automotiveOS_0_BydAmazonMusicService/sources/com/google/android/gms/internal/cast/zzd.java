package com.google.android.gms.internal.cast;

import android.content.SharedPreferences;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import com.google.android.datatransport.Event;
import com.google.android.datatransport.Transport;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ShowFirstParty;
import java.util.UUID;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@ShowFirstParty
@MainThread
public final class zzd {
    private final Transport zza;
    private final String zzb;
    private final int zzc;

    private zzd(@NonNull SharedPreferences sharedPreferences, @NonNull Transport transport, long j) {
        this.zza = transport;
        String string = sharedPreferences.getString("client_sender_id", null);
        if (string == null) {
            string = UUID.randomUUID().toString();
            sharedPreferences.edit().putString("client_sender_id", string).apply();
        }
        this.zzb = string;
        this.zzc = j == 0 ? 1 : 2;
    }

    public static zzd zza(@NonNull SharedPreferences sharedPreferences, @NonNull Transport transport, long j) {
        return new zzd(sharedPreferences, transport, j);
    }

    public final void zzb(zzlk zzlkVar, int i) {
        zzlj zzljVarZzd = zzlk.zzd(zzlkVar);
        zzljVarZzd.zzi(this.zzb);
        zzlk zzlkVar2 = (zzlk) zzljVarZzd.zzp();
        Event eventOfData = this.zzc + (-1) != 0 ? Event.ofData(i - 1, zzlkVar2) : Event.ofTelemetry(i - 1, zzlkVar2);
        Preconditions.checkNotNull(eventOfData);
        this.zza.send(eventOfData);
    }
}
