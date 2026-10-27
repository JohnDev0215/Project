package H4;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

/* JADX INFO: loaded from: classes.dex */
public final class h implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final h f2806a = new h();

    @Override // H4.i
    public final Paint a() {
        Paint paint = new Paint();
        paint.setColor(com.bumptech.glide.d.c(0L));
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST));
        return paint;
    }
}
