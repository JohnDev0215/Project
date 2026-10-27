package defpackage;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import org.chromium.chrome.browser.tab.Tab;

/* JADX INFO: compiled from: TwaSplashController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ah3 implements tq2 {
    public final Activity a;
    public final uq2 b;
    public final oj c;

    public ah3(sq2 sq2Var, Activity activity, uq2 uq2Var, oj ojVar) {
        this.a = activity;
        this.b = uq2Var;
        this.c = ojVar;
        long jK = q11.k("androidx.browser.trusted.KEY_SPLASH_SCREEN_FADE_OUT_DURATION", 0, ojVar.t().getBundleExtra("androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS"));
        sq2Var.k = this;
        sq2Var.o = jK;
        if (sq2Var.q) {
            sq2Var.F1();
        }
    }

    @Override // defpackage.tq2
    public final View b() {
        oj ojVar = this.c;
        Bitmap bitmapRemove = this.b.a.remove(ojVar.z());
        float[] floatArray = null;
        if (bitmapRemove == null) {
            return null;
        }
        ImageView imageView = new ImageView(this.a);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setImageBitmap(bitmapRemove);
        Bundle bundleExtra = ojVar.t().getBundleExtra("androidx.browser.trusted.EXTRA_SPLASH_SCREEN_PARAMS");
        imageView.setBackgroundColor(z7.A(q11.k("androidx.browser.trusted.trusted.KEY_SPLASH_SCREEN_BACKGROUND_COLOR", -1, bundleExtra)));
        int iK = q11.k("androidx.browser.trusted.KEY_SPLASH_SCREEN_SCALE_TYPE", -1, bundleExtra);
        ImageView.ScaleType[] scaleTypeArrValues = ImageView.ScaleType.values();
        ImageView.ScaleType scaleType = (iK < 0 || iK >= scaleTypeArrValues.length) ? ImageView.ScaleType.CENTER : scaleTypeArrValues[iK];
        imageView.setScaleType(scaleType);
        if (scaleType == ImageView.ScaleType.MATRIX) {
            try {
                floatArray = bundleExtra.getFloatArray("androidx.browser.trusted.KEY_SPLASH_SCREEN_TRANSFORMATION_MATRIX");
            } catch (Throwable unused) {
                Log.e("cr_IntentUtils", "getFloatArray failed on bundle " + bundleExtra);
            }
            if (floatArray != null && floatArray.length == 9) {
                Matrix matrix = new Matrix();
                matrix.setValues(floatArray);
                imageView.setImageMatrix(matrix);
            }
        }
        return imageView;
    }

    @Override // defpackage.tq2
    public final boolean c() {
        return false;
    }

    @Override // defpackage.tq2
    public final void a(Tab tab) {
    }
}
