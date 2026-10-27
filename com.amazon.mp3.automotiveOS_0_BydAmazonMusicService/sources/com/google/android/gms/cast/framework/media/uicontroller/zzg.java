package com.google.android.gms.cast.framework.media.uicontroller;

import android.view.View;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzg implements View.OnClickListener {
    final /* synthetic */ long zza;
    final /* synthetic */ UIMediaController zzb;

    zzg(UIMediaController uIMediaController, long j) {
        this.zzb = uIMediaController;
        this.zza = j;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        this.zzb.onRewindClicked(view, this.zza);
    }
}
