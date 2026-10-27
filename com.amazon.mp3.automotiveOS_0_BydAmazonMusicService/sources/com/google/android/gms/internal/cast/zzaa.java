package com.google.android.gms.internal.cast;

import android.animation.ObjectAnimator;
import android.view.View;
import com.google.android.gms.common.util.PlatformVersion;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzaa implements View.OnClickListener {
    final /* synthetic */ zzad zza;

    zzaa(zzad zzadVar) {
        this.zza = zzadVar;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        if (!PlatformVersion.isAtLeastJellyBean()) {
            zzad.zza(this.zza);
            return;
        }
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this, "alpha", 0.0f);
        objectAnimatorOfFloat.setDuration(400L).addListener(new zzz(this));
        objectAnimatorOfFloat.start();
    }
}
