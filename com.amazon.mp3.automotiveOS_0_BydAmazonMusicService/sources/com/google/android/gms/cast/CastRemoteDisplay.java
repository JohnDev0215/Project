package com.google.android.gms.cast;

import android.content.Context;
import android.view.Display;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Preconditions;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
@Deprecated
public final class CastRemoteDisplay {

    @NonNull
    @Deprecated
    public static final Api<CastRemoteDisplayOptions> API;
    public static final int CONFIGURATION_INTERACTIVE_NONREALTIME = 2;
    public static final int CONFIGURATION_INTERACTIVE_REALTIME = 1;
    public static final int CONFIGURATION_NONINTERACTIVE = 3;

    @NonNull
    @Deprecated
    public static final CastRemoteDisplayApi CastRemoteDisplayApi;

    @NonNull
    public static final String EXTRA_INT_SESSION_ENDED_STATUS_CODE = "extra_int_session_ended_status_code";
    private static final Api.AbstractClientBuilder zza;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public static final class CastRemoteDisplayOptions implements Api.ApiOptions.HasOptions {
        final CastDevice zza;
        final CastRemoteDisplaySessionCallbacks zzb;
        final int zzc;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
        @Deprecated
        public static final class Builder {
            CastDevice zza;
            CastRemoteDisplaySessionCallbacks zzb;
            int zzc;

            public Builder(@NonNull CastDevice castDevice, @NonNull CastRemoteDisplaySessionCallbacks castRemoteDisplaySessionCallbacks) {
                Preconditions.checkNotNull(castDevice, "CastDevice parameter cannot be null");
                this.zza = castDevice;
                this.zzb = castRemoteDisplaySessionCallbacks;
                this.zzc = 2;
            }

            @NonNull
            public CastRemoteDisplayOptions build() {
                return new CastRemoteDisplayOptions(this, null);
            }

            @NonNull
            public Builder setConfigPreset(@Configuration int i) {
                this.zzc = i;
                return this;
            }
        }

        /* synthetic */ CastRemoteDisplayOptions(Builder builder, zzu zzuVar) {
            this.zza = builder.zza;
            this.zzb = builder.zzb;
            this.zzc = builder.zzc;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface CastRemoteDisplaySessionCallbacks {
        void onRemoteDisplayEnded(@NonNull Status status);
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Deprecated
    public interface CastRemoteDisplaySessionResult extends Result {
        @Nullable
        Display getPresentationDisplay();
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public @interface Configuration {
    }

    static {
        zzt zztVar = new zzt();
        zza = zztVar;
        Api<CastRemoteDisplayOptions> api = new Api<>("CastRemoteDisplay.API", zztVar, com.google.android.gms.cast.internal.zzai.zzc);
        API = api;
        CastRemoteDisplayApi = new com.google.android.gms.internal.cast.zzcl(api);
    }

    private CastRemoteDisplay() {
    }

    @NonNull
    public static CastRemoteDisplayClient getClient(@NonNull Context context) {
        return new CastRemoteDisplayClient(context);
    }

    public static final boolean isRemoteDisplaySdkSupported(@NonNull Context context) {
        com.google.android.gms.cast.internal.zzaa.zza(context);
        return ((Boolean) com.google.android.gms.cast.internal.zzaa.zza.get()).booleanValue();
    }
}
