package com.google.android.gms.cast.framework.media.widget;

import android.annotation.TargetApi;
import android.graphics.Bitmap;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zzh implements com.google.android.gms.cast.framework.media.internal.zza {
    final /* synthetic */ ExpandedControllerActivity zza;

    zzh(ExpandedControllerActivity expandedControllerActivity) {
        this.zza = expandedControllerActivity;
    }

    @Override // com.google.android.gms.cast.framework.media.internal.zza
    @TargetApi(23)
    public final void zza(Bitmap bitmap) {
        if (bitmap != null) {
            ExpandedControllerActivity expandedControllerActivity = this.zza;
            if (expandedControllerActivity.zzE != null) {
                expandedControllerActivity.zzE.setVisibility(8);
            }
            ExpandedControllerActivity expandedControllerActivity2 = this.zza;
            if (expandedControllerActivity2.zzD != null) {
                expandedControllerActivity2.zzD.setVisibility(0);
                this.zza.zzD.setImageBitmap(bitmap);
            }
        }
    }
}
