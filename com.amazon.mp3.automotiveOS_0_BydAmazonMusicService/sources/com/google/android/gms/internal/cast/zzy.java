package com.google.android.gms.internal.cast;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.internal.featurehighlight.HelpTextView;

/* JADX INFO: compiled from: com.google.android.gms:play-services-cast-framework@@21.1.0 */
/* JADX INFO: loaded from: classes6.dex */
public final class zzy extends RelativeLayout implements IntroductoryOverlay {
    private final boolean zza;
    private Activity zzb;
    private IntroductoryOverlay.OnOverlayDismissedListener zzc;
    private View zzd;
    private com.google.android.gms.cast.framework.internal.featurehighlight.zzh zze;
    private String zzf;
    private boolean zzg;
    private int zzh;

    @TargetApi(15)
    public zzy(IntroductoryOverlay.Builder builder) {
        super(builder.zzc());
        this.zzb = builder.zzc();
        this.zza = builder.zzh();
        this.zzc = builder.zze();
        this.zzd = builder.zzd();
        this.zzf = builder.zzg();
        this.zzh = builder.zzb();
    }

    static boolean zzf(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService("accessibility");
        return accessibilityManager != null && accessibilityManager.isEnabled() && accessibilityManager.isTouchExplorationEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzg() {
        removeAllViews();
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzf = null;
        this.zzh = 0;
        this.zzg = false;
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void remove() {
        if (this.zzg) {
            ((ViewGroup) this.zzb.getWindow().getDecorView()).removeView(this);
            zzg();
        }
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void show() {
        Activity activity = this.zzb;
        if (activity == null || this.zzd == null || this.zzg || zzf(activity)) {
            return;
        }
        if (this.zza && com.google.android.gms.cast.framework.zzaw.zzb(this.zzb)) {
            zzg();
            return;
        }
        com.google.android.gms.cast.framework.internal.featurehighlight.zzh zzhVar = new com.google.android.gms.cast.framework.internal.featurehighlight.zzh(this.zzb);
        this.zze = zzhVar;
        int i = this.zzh;
        if (i != 0) {
            zzhVar.zzl(i);
        }
        addView(this.zze);
        HelpTextView helpTextView = (HelpTextView) this.zzb.getLayoutInflater().inflate(R.layout.cast_help_text, (ViewGroup) this.zze, false);
        helpTextView.setText(this.zzf, null);
        this.zze.zzp(helpTextView);
        this.zze.zzk(this.zzd, null, true, new zzx(this));
        this.zzg = true;
        ((ViewGroup) this.zzb.getWindow().getDecorView()).addView(this);
        this.zze.zzn(null);
    }
}
