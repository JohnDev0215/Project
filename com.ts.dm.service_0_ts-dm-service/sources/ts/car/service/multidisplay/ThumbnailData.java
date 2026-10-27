package ts.car.service.multidisplay;

import android.graphics.Bitmap;
import android.graphics.Rect;

/* JADX INFO: loaded from: classes.dex */
public class ThumbnailData {
    public static final int ORIENTATION_UNDEFINED = 0;
    public static final int WINDOWING_MODE_UNDEFINED = 0;
    public Bitmap thumbnail = null;
    public int orientation = 0;
    public Rect insets = new Rect();
    public boolean reducedResolution = false;
    public float scale = 1.0f;
    public boolean isRealSnapshot = true;
    public boolean isTranslucent = false;
    public int windowingMode = 0;
    public int systemUiVisibility = 0;
}
