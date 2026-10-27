package com.google.android.gms.cast.framework.internal.featurehighlight;

import android.view.GestureDetector;
import android.view.MotionEvent;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
final class zza extends GestureDetector.SimpleOnGestureListener {
    final /* synthetic */ zzh zza;

    zza(zzh zzhVar) {
        this.zza = zzhVar;
    }

    @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
    public final boolean onSingleTapUp(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.zza.zzc.contains(Math.round(x), Math.round(y)) && this.zza.zzd.zzg(x, y)) {
            return true;
        }
        this.zza.zzk.zza();
        return true;
    }
}
