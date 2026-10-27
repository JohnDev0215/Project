package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.cast.zzkk;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public interface IntroductoryOverlay {

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    void remove();

    void show();

    /* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
    public static class Builder {
        private final Activity zza;
        private final View zzb;
        private int zzc;
        private String zzd;
        private OnOverlayDismissedListener zze;
        private boolean zzf;
        private float zzg;
        private String zzh;

        public Builder(@NonNull Activity activity, @NonNull MenuItem menuItem) {
            this.zza = (Activity) Preconditions.checkNotNull(activity);
            this.zzb = ((MenuItem) Preconditions.checkNotNull(menuItem)).getActionView();
        }

        @NonNull
        public IntroductoryOverlay build() {
            com.google.android.gms.internal.cast.zzl.zzd(zzkk.INSTRUCTIONS_VIEW);
            return PlatformVersion.isAtLeastJellyBean() ? new com.google.android.gms.internal.cast.zzy(this) : new com.google.android.gms.internal.cast.zzad(this, null, R.attr.castIntroOverlayStyle);
        }

        @NonNull
        public Builder setButtonText(@StringRes int i) {
            this.zzh = this.zza.getResources().getString(i);
            return this;
        }

        @NonNull
        public Builder setButtonText(@NonNull String str) {
            this.zzh = str;
            return this;
        }

        @NonNull
        public Builder setFocusRadius(float f) {
            this.zzg = f;
            return this;
        }

        @NonNull
        public Builder setFocusRadiusId(@DimenRes int i) {
            this.zzg = this.zza.getResources().getDimension(i);
            return this;
        }

        @NonNull
        public Builder setOnOverlayDismissedListener(@NonNull OnOverlayDismissedListener onOverlayDismissedListener) {
            this.zze = onOverlayDismissedListener;
            return this;
        }

        @NonNull
        public Builder setOverlayColor(@ColorRes int i) {
            this.zzc = this.zza.getResources().getColor(i);
            return this;
        }

        @NonNull
        public Builder setSingleTime() {
            this.zzf = true;
            return this;
        }

        @NonNull
        public Builder setTitleText(@StringRes int i) {
            this.zzd = this.zza.getResources().getString(i);
            return this;
        }

        @NonNull
        public Builder setTitleText(@NonNull String str) {
            this.zzd = str;
            return this;
        }

        public final float zza() {
            return this.zzg;
        }

        public final int zzb() {
            return this.zzc;
        }

        @NonNull
        public final Activity zzc() {
            return this.zza;
        }

        @NonNull
        public final View zzd() {
            return this.zzb;
        }

        @NonNull
        public final OnOverlayDismissedListener zze() {
            return this.zze;
        }

        @NonNull
        public final String zzf() {
            return this.zzh;
        }

        @NonNull
        public final String zzg() {
            return this.zzd;
        }

        public final boolean zzh() {
            return this.zzf;
        }

        public Builder(@NonNull Activity activity, @NonNull MediaRouteButton mediaRouteButton) {
            this.zza = (Activity) Preconditions.checkNotNull(activity);
            this.zzb = (View) Preconditions.checkNotNull(mediaRouteButton);
        }
    }
}
