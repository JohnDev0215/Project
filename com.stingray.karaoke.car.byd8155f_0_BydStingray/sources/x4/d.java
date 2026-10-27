package x4;

import A4.h;
import android.content.Context;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;
import java.util.ArrayList;
import t1.ViewTreeObserverOnPreDrawListenerC2429e;

/* JADX INFO: loaded from: classes.dex */
public final class d {
    public static Integer d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final ImageView f23781a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final ArrayList f23782b = new ArrayList();
    public ViewTreeObserverOnPreDrawListenerC2429e c;

    public d(ImageView imageView) {
        this.f23781a = imageView;
    }

    public final int a(int i7, int i10, int i11) {
        int i12 = i10 - i11;
        if (i12 > 0) {
            return i12;
        }
        int i13 = i7 - i11;
        if (i13 > 0) {
            return i13;
        }
        ImageView imageView = this.f23781a;
        if (imageView.isLayoutRequested() || i10 != -2) {
            return 0;
        }
        if (Log.isLoggable("ViewTarget", 4)) {
            Log.i("ViewTarget", "Glide treats LayoutParams.WRAP_CONTENT as a request for an image the size of this device's screen dimensions. If you want to load the original image and are ok with the corresponding memory cost and OOMs (depending on the input size), use override(Target.SIZE_ORIGINAL). Otherwise, use LayoutParams.MATCH_PARENT, set layout_width and layout_height to fixed dimension, or use .override() with fixed dimensions.");
        }
        Context context = imageView.getContext();
        if (d == null) {
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            h.c(windowManager, "Argument must not be null");
            Display defaultDisplay = windowManager.getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            d = Integer.valueOf(Math.max(point.x, point.y));
        }
        return d.intValue();
    }
}
