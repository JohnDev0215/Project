package com.google.android.gms.wearable;

import android.app.Activity;
import android.content.Context;
import android.os.Looper;
import androidx.annotation.Nullable;
import androidx.annotation.RecentlyNonNull;
import androidx.core.util.Preconditions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.wearable.internal.zzaf;
import com.google.android.gms.wearable.internal.zzam;
import com.google.android.gms.wearable.internal.zzav;
import com.google.android.gms.wearable.internal.zzbw;
import com.google.android.gms.wearable.internal.zzcj;
import com.google.android.gms.wearable.internal.zzcw;
import com.google.android.gms.wearable.internal.zzfc;
import com.google.android.gms.wearable.internal.zzfi;
import com.google.android.gms.wearable.internal.zzfp;
import com.google.android.gms.wearable.internal.zzfv;
import com.google.android.gms.wearable.internal.zzgu;
import com.google.android.gms.wearable.internal.zzhv;
import com.google.android.gms.wearable.internal.zzic;
import com.google.android.gms.wearable.internal.zzz;

/* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class Wearable {

    @RecentlyNonNull
    @Deprecated
    public static final Api<WearableOptions> API;
    private static final Api.ClientKey<zzhv> zzf;
    private static final Api.AbstractClientBuilder<zzhv, WearableOptions> zzg;

    @RecentlyNonNull
    @Deprecated
    public static final DataApi DataApi = new zzcj();

    @RecentlyNonNull
    @Deprecated
    public static final CapabilityApi CapabilityApi = new zzz();

    @RecentlyNonNull
    @Deprecated
    public static final MessageApi MessageApi = new zzfc();

    @RecentlyNonNull
    @Deprecated
    public static final NodeApi NodeApi = new zzfp();

    @RecentlyNonNull
    @Deprecated
    public static final ChannelApi ChannelApi = new zzam();

    @ShowFirstParty
    @Deprecated
    public static final com.google.android.gms.wearable.internal.zzk zza = new com.google.android.gms.wearable.internal.zzk();

    @ShowFirstParty
    @Deprecated
    public static final com.google.android.gms.wearable.internal.zzh zzb = new com.google.android.gms.wearable.internal.zzh();

    @ShowFirstParty
    @Deprecated
    public static final zzbw zzc = new zzbw();

    @ShowFirstParty
    @Deprecated
    public static final zzgu zzd = new zzgu();

    @ShowFirstParty
    @Deprecated
    public static final zzic zze = new zzic();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
    public static final class WearableOptions implements Api.ApiOptions.Optional {
        static final WearableOptions zza = new WearableOptions(new Builder());
        private final Looper zzb;

        /* JADX INFO: compiled from: com.google.android.gms:play-services-wearable@@17.1.0 */
        public static class Builder {
            private Looper zza;

            @RecentlyNonNull
            public WearableOptions build() {
                return new WearableOptions(this, null);
            }

            @RecentlyNonNull
            public Builder setLooper(@RecentlyNonNull Looper looper) {
                this.zza = looper;
                return this;
            }
        }

        private WearableOptions(Builder builder) {
            this.zzb = builder.zza;
        }

        /* synthetic */ WearableOptions(Builder builder, zzh zzhVar) {
            this(builder);
        }

        static /* synthetic */ GoogleApi.Settings zza(WearableOptions wearableOptions) {
            return wearableOptions.zzb != null ? new GoogleApi.Settings.Builder().setLooper(wearableOptions.zzb).build() : GoogleApi.Settings.DEFAULT_SETTINGS;
        }

        public boolean equals(@Nullable Object obj) {
            return obj instanceof WearableOptions;
        }

        public int hashCode() {
            return Objects.hashCode(WearableOptions.class);
        }
    }

    static {
        Api.ClientKey<zzhv> clientKey = new Api.ClientKey<>();
        zzf = clientKey;
        zzh zzhVar = new zzh();
        zzg = zzhVar;
        API = new Api<>("Wearable.API", zzhVar, clientKey);
    }

    private Wearable() {
    }

    @RecentlyNonNull
    public static CapabilityClient getCapabilityClient(@RecentlyNonNull Activity activity) {
        return new zzaf(activity, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static ChannelClient getChannelClient(@RecentlyNonNull Activity activity) {
        return new zzav(activity, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static DataClient getDataClient(@RecentlyNonNull Activity activity) {
        return new zzcw(activity, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static MessageClient getMessageClient(@RecentlyNonNull Activity activity) {
        return new zzfi(activity, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static NodeClient getNodeClient(@RecentlyNonNull Activity activity) {
        return new zzfv(activity, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static CapabilityClient getCapabilityClient(@RecentlyNonNull Activity activity, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzaf(activity, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static ChannelClient getChannelClient(@RecentlyNonNull Activity activity, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzav(activity, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static DataClient getDataClient(@RecentlyNonNull Activity activity, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzcw(activity, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static MessageClient getMessageClient(@RecentlyNonNull Activity activity, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfi(activity, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static NodeClient getNodeClient(@RecentlyNonNull Activity activity, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfv(activity, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static CapabilityClient getCapabilityClient(@RecentlyNonNull Context context) {
        return new zzaf(context, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static ChannelClient getChannelClient(@RecentlyNonNull Context context) {
        return new zzav(context, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static DataClient getDataClient(@RecentlyNonNull Context context) {
        return new zzcw(context, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static MessageClient getMessageClient(@RecentlyNonNull Context context) {
        return new zzfi(context, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static NodeClient getNodeClient(@RecentlyNonNull Context context) {
        return new zzfv(context, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @RecentlyNonNull
    public static CapabilityClient getCapabilityClient(@RecentlyNonNull Context context, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzaf(context, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static ChannelClient getChannelClient(@RecentlyNonNull Context context, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzav(context, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static DataClient getDataClient(@RecentlyNonNull Context context, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzcw(context, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static MessageClient getMessageClient(@RecentlyNonNull Context context, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfi(context, WearableOptions.zza(wearableOptions));
    }

    @RecentlyNonNull
    public static NodeClient getNodeClient(@RecentlyNonNull Context context, @RecentlyNonNull WearableOptions wearableOptions) {
        Preconditions.checkNotNull(wearableOptions, "options must not be null");
        return new zzfv(context, WearableOptions.zza(wearableOptions));
    }
}
