package com.google.android.gms.cast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.json.JSONObject;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public class MediaSeekOptions {
    public static final int RESUME_STATE_PAUSE = 2;
    public static final int RESUME_STATE_PLAY = 1;
    public static final int RESUME_STATE_UNCHANGED = 0;
    private final long zza;
    private final int zzb;
    private final boolean zzc;

    @Nullable
    private final JSONObject zzd;

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    public static class Builder {
        private long zza;
        private int zzb = 0;
        private boolean zzc;

        @Nullable
        private JSONObject zzd;

        @NonNull
        public MediaSeekOptions build() {
            return new MediaSeekOptions(this.zza, this.zzb, this.zzc, this.zzd, null);
        }

        @NonNull
        public Builder setCustomData(@Nullable JSONObject jSONObject) {
            this.zzd = jSONObject;
            return this;
        }

        @NonNull
        public Builder setIsSeekToInfinite(boolean z) {
            this.zzc = z;
            return this;
        }

        @NonNull
        public Builder setPosition(long j) {
            this.zza = j;
            return this;
        }

        @NonNull
        public Builder setResumeState(int i) {
            this.zzb = i;
            return this;
        }
    }

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast@@21.1.0 */
    @Retention(RetentionPolicy.SOURCE)
    public @interface ResumeState {
    }

    /* synthetic */ MediaSeekOptions(long j, int i, boolean z, JSONObject jSONObject, zzcj zzcjVar) {
        this.zza = j;
        this.zzb = i;
        this.zzc = z;
        this.zzd = jSONObject;
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MediaSeekOptions)) {
            return false;
        }
        MediaSeekOptions mediaSeekOptions = (MediaSeekOptions) obj;
        return this.zza == mediaSeekOptions.zza && this.zzb == mediaSeekOptions.zzb && this.zzc == mediaSeekOptions.zzc && Objects.equal(this.zzd, mediaSeekOptions.zzd);
    }

    @Nullable
    public JSONObject getCustomData() {
        return this.zzd;
    }

    public long getPosition() {
        return this.zza;
    }

    public int getResumeState() {
        return this.zzb;
    }

    public int hashCode() {
        return Objects.hashCode(Long.valueOf(this.zza), Integer.valueOf(this.zzb), Boolean.valueOf(this.zzc), this.zzd);
    }

    public boolean isSeekToInfinite() {
        return this.zzc;
    }
}
