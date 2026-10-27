package defpackage;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Size;
import defpackage.pa1;
import org.chromium.chrome.R$string;
import org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c;
import org.chromium.ui.widget.Toast;

/* JADX INFO: compiled from: LongScreenshotsMediator.java */
/* JADX INFO: loaded from: classes2.dex */
public final class qa1 implements c.a {
    public final /* synthetic */ ra1 a;

    /* JADX INFO: compiled from: LongScreenshotsMediator.java */
    public class a implements pa1.a {
        public final /* synthetic */ pa1 e;

        public a(pa1 pa1Var) {
            this.e = pa1Var;
        }

        @Override // pa1.a
        public final void a(int i) {
            qa1 qa1Var = qa1.this;
            if (i != 3) {
                if (i == 6) {
                    return;
                }
                Toast.c(qa1Var.a.o, R$string.sharing_long_screenshot_unknown_error, 1).e();
                return;
            }
            Bitmap bitmap = this.e.d;
            long height = bitmap.getHeight() * bitmap.getWidth();
            if (height <= 33000000) {
                qa1Var.a.f(bitmap);
            } else {
                double dSqrt = Math.sqrt((height * 1.0d) / 3.3E7d);
                qa1Var.a.f(Bitmap.createScaledBitmap(bitmap, (int) Math.round(((double) bitmap.getWidth()) / dSqrt), (int) Math.round(((double) bitmap.getHeight()) / dSqrt), true));
            }
        }
    }

    public qa1(ra1 ra1Var) {
        this.a = ra1Var;
    }

    @Override // org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c.a
    public final void a(int i) {
        if (i == 5 || i == 4) {
            return;
        }
        this.a.p.d.c(this);
    }

    @Override // org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.c.a
    public final void b(Size size, Point point) {
        int iMin;
        ra1 ra1Var = this.a;
        ra1Var.p.d.c(this);
        c cVar = ra1Var.p;
        org.chromium.chrome.browser.share.long_screenshots.bitmap_generation.a aVar = cVar.c;
        re2 re2Var = cVar.f;
        int i = re2Var.e;
        int i2 = i * 7;
        int iMax = Math.max(re2Var.d.y - (i * 2), 0);
        if (iMax == 0) {
            iMin = Math.min(i2, re2Var.c.getHeight());
        } else {
            int iMin2 = Math.min((re2Var.e * 5) + re2Var.d.y, re2Var.c.getHeight());
            if (iMin2 == re2Var.c.getHeight()) {
                iMax = Math.max(re2Var.c.getHeight() - i2, 0);
            }
            iMin = iMin2;
        }
        pa1 pa1Var = new pa1(aVar, new Rect(0, iMax, 0, iMin), new td2(27, cVar));
        cVar.c(pa1Var, false);
        pa1Var.e = new a(pa1Var);
        int i3 = pa1Var.c;
        if (i3 != 0) {
            pa1Var.b(i3);
        }
    }
}
