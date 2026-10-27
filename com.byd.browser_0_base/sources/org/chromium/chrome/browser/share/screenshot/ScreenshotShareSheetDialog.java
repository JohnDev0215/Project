package org.chromium.chrome.browser.share.screenshot;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.ViewGroup;
import androidx.appcompat.app.AlertDialog;
import defpackage.d92;
import defpackage.eu1;
import defpackage.r90;
import defpackage.rq;
import defpackage.v62;
import defpackage.we2;
import defpackage.xe2;
import defpackage.ye2;
import java.util.ArrayList;
import java.util.Arrays;
import org.chromium.base.Callback;
import org.chromium.chrome.R$id;
import org.chromium.chrome.R$layout;
import org.chromium.chrome.R$style;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.modelutil.PropertyModel;
import org.chromium.ui.modelutil.j;
import org.chromium.ui.modelutil.k;
import org.chromium.ui.widget.ChromeImageView;

/* JADX INFO: loaded from: classes2.dex */
public class ScreenshotShareSheetDialog extends r90 {
    public Context p0;
    public Bitmap q0;
    public WindowAndroid r0;
    public String s0;
    public rq t0;
    public Callback<Runnable> u0;

    @Override // defpackage.r90
    public final Dialog J0(Bundle bundle) {
        AlertDialog.Builder builder = new AlertDialog.Builder(D(), R$style.ThemeOverlay_BrowserUI_Fullscreen);
        ScreenshotShareSheetView screenshotShareSheetView = (ScreenshotShareSheetView) D().getLayoutInflater().inflate(R$layout.screenshot_share_sheet, (ViewGroup) null);
        builder.a.r = screenshotShareSheetView;
        Context context = this.p0;
        Bitmap bitmap = this.q0;
        d92 d92Var = new d92(1, this);
        WindowAndroid windowAndroid = this.r0;
        String str = this.s0;
        rq rqVar = this.t0;
        Callback<Runnable> callback = this.u0;
        PropertyModel propertyModel = new PropertyModel(new ArrayList(Arrays.asList(ye2.c)));
        propertyModel.p(ye2.b, bitmap);
        new we2(context, propertyModel, d92Var, new eu1(17, new xe2(context, propertyModel, d92Var, windowAndroid)), windowAndroid, str, rqVar, callback);
        j.b(propertyModel, screenshotShareSheetView, new j.a() { // from class: org.chromium.chrome.browser.share.screenshot.a
            @Override // org.chromium.ui.modelutil.j.a
            public final void b(k kVar, Object obj, Object obj2) {
                PropertyModel propertyModel2 = (PropertyModel) kVar;
                ScreenshotShareSheetView screenshotShareSheetView2 = (ScreenshotShareSheetView) obj;
                v62 v62Var = (v62) obj2;
                PropertyModel.r<Callback<Integer>> rVar = ye2.a;
                if (rVar != v62Var) {
                    PropertyModel.r<Bitmap> rVar2 = ye2.b;
                    if (rVar2 == v62Var) {
                        ((ChromeImageView) screenshotShareSheetView2.findViewById(R$id.screenshot)).setImageDrawable(new BitmapDrawable((Bitmap) propertyModel2.j(rVar2)));
                        return;
                    }
                    return;
                }
                Callback<Integer> callback2 = (Callback) propertyModel2.j(rVar);
                screenshotShareSheetView2.a(1, R$id.share, callback2);
                screenshotShareSheetView2.a(2, R$id.save, callback2);
                screenshotShareSheetView2.a(3, R$id.delete, callback2);
                screenshotShareSheetView2.a(3, R$id.close_button, callback2);
                screenshotShareSheetView2.a(4, R$id.edit, callback2);
            }
        });
        return builder.a();
    }

    @Override // defpackage.r90, androidx.fragment.app.Fragment
    public final void a0(Context context) {
        super.a0(context);
        this.p0 = context;
    }
}
