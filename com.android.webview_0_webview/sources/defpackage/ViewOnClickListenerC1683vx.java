package defpackage;

import android.graphics.Color;
import android.view.View;
import org.chromium.components.embedder_support.delegate.ColorPickerAdvanced;

/* JADX INFO: renamed from: vx, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class ViewOnClickListenerC1683vx implements View.OnClickListener {
    public final /* synthetic */ AlertDialogC1738wx s;

    public ViewOnClickListenerC1683vx(AlertDialogC1738wx alertDialogC1738wx) {
        this.s = alertDialogC1738wx;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AlertDialogC1738wx alertDialogC1738wx = this.s;
        alertDialogC1738wx.u.setVisibility(8);
        alertDialogC1738wx.findViewById(AbstractC0371Um.m0).setVisibility(8);
        alertDialogC1738wx.s.setVisibility(0);
        ColorPickerAdvanced colorPickerAdvanced = alertDialogC1738wx.s;
        colorPickerAdvanced.v = alertDialogC1738wx;
        int i = alertDialogC1738wx.z;
        colorPickerAdvanced.w = i;
        Color.colorToHSV(i, colorPickerAdvanced.x);
        colorPickerAdvanced.b();
    }
}
