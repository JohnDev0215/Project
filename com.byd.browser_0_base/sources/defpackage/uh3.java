package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.content.res.AppCompatResources;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: compiled from: UiUtils.java */
/* JADX INFO: loaded from: classes3.dex */
public final class uh3 {
    public static final HashMap a;
    public static Boolean b;

    static {
        HashMap map = new HashMap();
        a = map;
        map.put("htc", 26);
    }

    public static int a(ListAdapter listAdapter, ListView listView) {
        View view;
        int iMakeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int iMakeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        AbsListView.LayoutParams layoutParams = new AbsListView.LayoutParams(-2, -2);
        View[] viewArr = new View[listAdapter.getViewTypeCount()];
        int iMax = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            int itemViewType = listAdapter.getItemViewType(i);
            if (itemViewType < 0) {
                view = listAdapter.getView(i, null, listView);
            } else {
                View view2 = listAdapter.getView(i, viewArr[itemViewType], listView);
                viewArr[itemViewType] = view2;
                view = view2;
            }
            view.setLayoutParams(layoutParams);
            view.measure(iMakeMeasureSpec, iMakeMeasureSpec2);
            iMax = Math.max(iMax, view.getMeasuredWidth());
        }
        return iMax;
    }

    /* JADX WARN: Removed duplicated region for block: B:24:0x008d A[DONT_GENERATE, PHI: r3
  0x008d: PHI (r3v2 android.graphics.Bitmap) = (r3v0 android.graphics.Bitmap), (r3v4 android.graphics.Bitmap) binds: [B:30:0x009a, B:23:0x008b] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap b(android.view.View r13, int r14, android.graphics.Bitmap.Config r15) {
        /*
            boolean r0 = r13.isDrawingCacheEnabled()
            r1 = 1
            r2 = 0
            r3 = 0
            k(r13, r1)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r0 != 0) goto Lf
            r13.setDrawingCacheEnabled(r1)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
        Lf:
            android.graphics.Bitmap r4 = r13.getDrawingCache()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r4 == 0) goto L48
            int r5 = r4.getHeight()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r5 = (double) r5     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r7 = r4.getWidth()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r7 = (double) r7     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r9 = (int) r7     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r10 = (int) r5     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r14 <= 0) goto L36
            double r9 = (double) r14     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r11 = java.lang.Math.max(r7, r5)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r9 = r9 / r11
            double r7 = r7 * r9
            long r7 = java.lang.Math.round(r7)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r14 = (int) r7     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r5 = r5 * r9
            long r5 = java.lang.Math.round(r5)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r10 = (int) r5     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            r9 = r14
        L36:
            android.graphics.Bitmap r14 = android.graphics.Bitmap.createScaledBitmap(r4, r9, r10, r1)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            android.graphics.Bitmap$Config r1 = r14.getConfig()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r1 == r15) goto L8a
            android.graphics.Bitmap r3 = r14.copy(r15, r2)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            r14.recycle()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            goto L8b
        L48:
            int r1 = r13.getMeasuredHeight()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r1 <= 0) goto L8b
            int r1 = r13.getMeasuredWidth()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r1 <= 0) goto L8b
            int r1 = r13.getMeasuredHeight()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r4 = (double) r1     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r1 = r13.getMeasuredWidth()     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r6 = (double) r1     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r1 = (int) r6     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r8 = (int) r4     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            if (r14 <= 0) goto L75
            double r8 = (double) r14     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r10 = java.lang.Math.max(r6, r4)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r8 = r8 / r10
            double r10 = r6 * r8
            long r10 = java.lang.Math.round(r10)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r1 = (int) r10     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r8 = r8 * r4
            long r8 = java.lang.Math.round(r8)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            int r8 = (int) r8     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
        L75:
            android.graphics.Bitmap r14 = android.graphics.Bitmap.createBitmap(r1, r8, r15)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            android.graphics.Canvas r15 = new android.graphics.Canvas     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            r15.<init>(r14)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r9 = (double) r1     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r9 = r9 / r6
            float r1 = (float) r9     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r6 = (double) r8     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            double r6 = r6 / r4
            float r4 = (float) r6     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            r15.scale(r1, r4)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
            r13.draw(r15)     // Catch: java.lang.Throwable -> L94 java.lang.OutOfMemoryError -> L96
        L8a:
            r3 = r14
        L8b:
            if (r0 != 0) goto L90
        L8d:
            r13.setDrawingCacheEnabled(r2)
        L90:
            k(r13, r2)
            goto L9d
        L94:
            r14 = move-exception
            goto L9e
        L96:
            r14 = move-exception
            r14.getMessage()     // Catch: java.lang.Throwable -> L94
            if (r0 != 0) goto L90
            goto L8d
        L9d:
            return r3
        L9e:
            if (r0 != 0) goto La3
            r13.setDrawingCacheEnabled(r2)
        La3:
            k(r13, r2)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.uh3.b(android.view.View, int, android.graphics.Bitmap$Config):android.graphics.Bitmap");
    }

    public static File c(Context context) {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            File file = new File(context.getFilesDir(), "images");
            if (!file.exists() && !file.mkdir()) {
                throw new IOException("Folder cannot be created.");
            }
            return file;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static Drawable d(Context context, TypedArray typedArray, int i) {
        int resourceId;
        if (typedArray == null || (resourceId = typedArray.getResourceId(i, -1)) == -1) {
            return null;
        }
        return AppCompatResources.a(context, resourceId);
    }

    public static Drawable e(Context context, int i, int i2) {
        return f(context, i, bz.b(context, i2));
    }

    public static Drawable f(Context context, int i, ColorStateList colorStateList) {
        Drawable drawableMutate = AppCompatResources.a(context, i).mutate();
        drawableMutate.setTintList(colorStateList);
        return drawableMutate;
    }

    public static void g(View view, View view2, ViewGroup viewGroup) {
        i(viewGroup, view, view2, true);
    }

    public static void h(View view, View view2, ViewGroup viewGroup) {
        i(viewGroup, view, view2, false);
    }

    public static int i(ViewGroup viewGroup, View view, View view2, boolean z) {
        int iIndexOfChild = viewGroup.indexOfChild(view);
        if (iIndexOfChild >= 0) {
            return iIndexOfChild;
        }
        int iIndexOfChild2 = viewGroup.indexOfChild(view2);
        if (iIndexOfChild2 < 0) {
            return -1;
        }
        if (z) {
            iIndexOfChild2++;
        }
        viewGroup.addView(view, iIndexOfChild2);
        return iIndexOfChild2;
    }

    public static boolean j() {
        if (b == null) {
            b = Boolean.FALSE;
            HashMap map = a;
            String str = Build.MANUFACTURER;
            Locale locale = Locale.US;
            if (map.containsKey(str.toLowerCase(locale))) {
                b = Boolean.valueOf(Build.VERSION.SDK_INT < ((Integer) map.get(str.toLowerCase(locale))).intValue());
            }
        }
        return b.booleanValue();
    }

    public static void k(View view, boolean z) {
        if (!(view instanceof ViewGroup)) {
            if (view instanceof SurfaceView) {
                view.setWillNotDraw(!z);
            }
        } else {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                k(viewGroup.getChildAt(i), z);
            }
        }
    }

    public static void l(View view) {
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup == null) {
            return;
        }
        viewGroup.removeView(view);
    }

    public static void m(View view, boolean z) {
        int systemUiVisibility = view.getSystemUiVisibility();
        view.setSystemUiVisibility(z ? systemUiVisibility | 16 : systemUiVisibility & (-17));
    }
}
