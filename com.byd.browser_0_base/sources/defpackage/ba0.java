package defpackage;

import android.R;
import android.app.Activity;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowInsets;

/* JADX INFO: compiled from: DimensionCompat.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class ba0 {
    public final Activity a;

    /* JADX INFO: compiled from: DimensionCompat.java */
    public static class a extends ba0 {
        public a(Activity activity) {
            super(activity);
        }

        @Override // defpackage.ba0
        public final int a() {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            Activity activity = this.a;
            if (activity.isInMultiWindowMode()) {
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            } else {
                activity.getWindowManager().getDefaultDisplay().getRealMetrics(displayMetrics);
            }
            int i = displayMetrics.heightPixels;
            int height = activity.findViewById(R.id.content).getHeight();
            int identifier = activity.getResources().getIdentifier("status_bar_height", "dimen", "android");
            int dimensionPixelSize = height + (identifier > 0 ? activity.getResources().getDimensionPixelSize(identifier) : 0);
            Display defaultDisplay = activity.getWindowManager().getDefaultDisplay();
            Point point = new Point();
            defaultDisplay.getSize(point);
            return i - Math.max(dimensionPixelSize, point.y);
        }
    }

    /* JADX INFO: compiled from: DimensionCompat.java */
    public static class b extends ba0 {
        public b(Activity activity) {
            super(activity);
        }

        @Override // defpackage.ba0
        public final int a() {
            return this.a.getWindowManager().getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.navigationBars()).bottom;
        }
    }

    public ba0(Activity activity) {
        this.a = activity;
    }

    public abstract int a();
}
