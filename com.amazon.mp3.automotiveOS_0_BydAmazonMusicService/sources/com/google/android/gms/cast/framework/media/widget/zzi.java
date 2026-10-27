package com.google.android.gms.cast.framework.media.widget;

import android.view.View;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzi implements View.OnClickListener {
    final /* synthetic */ ExpandedControllerActivity zza;

    zzi(ExpandedControllerActivity expandedControllerActivity) {
        this.zza = expandedControllerActivity;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        RemoteMediaClient remoteMediaClientZzl;
        if (!this.zza.zzG.isClickable() || (remoteMediaClientZzl = this.zza.zzl()) == null) {
            return;
        }
        remoteMediaClientZzl.skipAd();
    }
}
