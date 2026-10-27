package androidx.core.view;

import android.graphics.Rect;
import android.os.Build;
import android.view.DisplayCutout;
import androidx.core.graphics.Insets;
import androidx.core.os.BuildCompat;
import androidx.core.util.ObjectsCompat;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class DisplayCutoutCompat {
    private final Object mDisplayCutout;

    public DisplayCutoutCompat(Rect safeInsets, List<Rect> boundingRects) {
        this(new DisplayCutout(safeInsets, boundingRects));
    }

    public DisplayCutoutCompat(Insets safeInsets, Rect boundLeft, Rect boundTop, Rect boundRight, Rect boundBottom, Insets waterfallInsets) {
        this(constructDisplayCutout(safeInsets, boundLeft, boundTop, boundRight, boundBottom, waterfallInsets));
    }

    private static DisplayCutout constructDisplayCutout(Insets safeInsets, Rect boundLeft, Rect boundTop, Rect boundRight, Rect boundBottom, Insets waterfallInsets) {
        if (BuildCompat.isAtLeastR()) {
            return new DisplayCutout(safeInsets.toPlatformInsets(), boundLeft, boundTop, boundRight, boundBottom, waterfallInsets.toPlatformInsets());
        }
        if (Build.VERSION.SDK_INT >= 29) {
            return new DisplayCutout(safeInsets.toPlatformInsets(), boundLeft, boundTop, boundRight, boundBottom);
        }
        Rect rect = new Rect(safeInsets.left, safeInsets.top, safeInsets.right, safeInsets.bottom);
        ArrayList arrayList = new ArrayList();
        if (boundLeft != null) {
            arrayList.add(boundLeft);
        }
        if (boundTop != null) {
            arrayList.add(boundTop);
        }
        if (boundRight != null) {
            arrayList.add(boundRight);
        }
        if (boundBottom != null) {
            arrayList.add(boundBottom);
        }
        return new DisplayCutout(rect, arrayList);
    }

    private DisplayCutoutCompat(Object displayCutout) {
        this.mDisplayCutout = displayCutout;
    }

    public int getSafeInsetTop() {
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetTop();
    }

    public int getSafeInsetBottom() {
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetBottom();
    }

    public int getSafeInsetLeft() {
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetLeft();
    }

    public int getSafeInsetRight() {
        return ((DisplayCutout) this.mDisplayCutout).getSafeInsetRight();
    }

    public List<Rect> getBoundingRects() {
        return ((DisplayCutout) this.mDisplayCutout).getBoundingRects();
    }

    public Insets getWaterfallInsets() {
        if (BuildCompat.isAtLeastR()) {
            return Insets.toCompatInsets(((DisplayCutout) this.mDisplayCutout).getWaterfallInsets());
        }
        return Insets.NONE;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return ObjectsCompat.equals(this.mDisplayCutout, ((DisplayCutoutCompat) o).mDisplayCutout);
    }

    public int hashCode() {
        Object obj = this.mDisplayCutout;
        if (obj == null) {
            return 0;
        }
        return obj.hashCode();
    }

    public String toString() {
        return "DisplayCutoutCompat{" + this.mDisplayCutout + "}";
    }

    static DisplayCutoutCompat wrap(Object displayCutout) {
        if (displayCutout == null) {
            return null;
        }
        return new DisplayCutoutCompat(displayCutout);
    }

    DisplayCutout unwrap() {
        return (DisplayCutout) this.mDisplayCutout;
    }
}
