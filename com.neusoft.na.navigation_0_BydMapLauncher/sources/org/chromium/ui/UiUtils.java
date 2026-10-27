package org.chromium.ui;

import android.content.Context;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Handler;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import java.io.File;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import org.chromium.base.ContentUriUtils;

/* JADX INFO: loaded from: classes3.dex */
public class UiUtils {
    public static final String EXTERNAL_IMAGE_FILE_PATH = "browser-images";
    public static final String IMAGE_FILE_PATH = "images";
    private static final float KEYBOARD_DETECT_BOTTOM_THRESHOLD_DP = 100.0f;
    private static final int KEYBOARD_RETRY_ATTEMPTS = 10;
    private static final long KEYBOARD_RETRY_DELAY_MS = 100;
    private static final String TAG = "UiUtils";
    private static KeyboardShowingDelegate sKeyboardShowingDelegate;

    public interface KeyboardShowingDelegate {
        boolean disableKeyboardCheck(Context context, View view);
    }

    private UiUtils() {
    }

    public static void setKeyboardShowingDelegate(KeyboardShowingDelegate keyboardShowingDelegate) {
        sKeyboardShowingDelegate = keyboardShowingDelegate;
    }

    public static void showKeyboard(final View view) {
        final Handler handler = new Handler();
        final AtomicInteger atomicInteger = new AtomicInteger();
        new Runnable() { // from class: org.chromium.ui.UiUtils.1
            @Override // java.lang.Runnable
            public void run() {
                InputMethodManager inputMethodManager = (InputMethodManager) view.getContext().getSystemService("input_method");
                StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                try {
                    try {
                        inputMethodManager.showSoftInput(view, 0);
                    } catch (IllegalArgumentException e) {
                        if (atomicInteger.incrementAndGet() <= 10) {
                            handler.postDelayed(this, UiUtils.KEYBOARD_RETRY_DELAY_MS);
                        } else {
                            Log.e(UiUtils.TAG, "Unable to open keyboard.  Giving up.", e);
                        }
                    }
                } finally {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                }
            }
        }.run();
    }

    public static boolean hideKeyboard(View view) {
        return ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static boolean isKeyboardShowing(Context context, View view) {
        View rootView;
        KeyboardShowingDelegate keyboardShowingDelegate = sKeyboardShowingDelegate;
        if ((keyboardShowingDelegate != null && keyboardShowingDelegate.disableKeyboardCheck(context, view)) || (rootView = view.getRootView()) == null) {
            return false;
        }
        Rect rect = new Rect();
        rootView.getWindowVisibleDisplayFrame(rect);
        return ((float) Math.abs(rootView.getHeight() - rect.height())) / context.getResources().getDisplayMetrics().density > 100.0f;
    }

    public static Set<String> getIMELocales(Context context) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        List<InputMethodInfo> enabledInputMethodList = inputMethodManager.getEnabledInputMethodList();
        for (int i = 0; i < enabledInputMethodList.size(); i++) {
            List<InputMethodSubtype> enabledInputMethodSubtypeList = inputMethodManager.getEnabledInputMethodSubtypeList(enabledInputMethodList.get(i), true);
            if (enabledInputMethodSubtypeList != null) {
                for (int i2 = 0; i2 < enabledInputMethodSubtypeList.size(); i2++) {
                    String locale = enabledInputMethodSubtypeList.get(i2).getLocale();
                    if (!TextUtils.isEmpty(locale)) {
                        linkedHashSet.add(locale);
                    }
                }
            }
        }
        return linkedHashSet;
    }

    public static int insertBefore(ViewGroup viewGroup, View view, View view2) {
        return insertView(viewGroup, view, view2, false);
    }

    public static int insertAfter(ViewGroup viewGroup, View view, View view2) {
        return insertView(viewGroup, view, view2, true);
    }

    private static int insertView(ViewGroup viewGroup, View view, View view2, boolean z) {
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

    /* JADX WARN: Removed duplicated region for block: B:24:0x0095 A[DONT_GENERATE, PHI: r7
  0x0095: PHI (r7v2 android.graphics.Bitmap) = (r7v0 android.graphics.Bitmap), (r7v4 android.graphics.Bitmap) binds: [B:30:0x00b5, B:23:0x0093] A[DONT_GENERATE, DONT_INLINE]] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap generateScaledScreenshot(android.view.View r17, int r18, android.graphics.Bitmap.Config r19) {
        /*
            r1 = r17
            r0 = r18
            r2 = r19
            java.lang.String r3 = "Unable to capture screenshot and scale it down."
            boolean r4 = r17.isDrawingCacheEnabled()
            r5 = 1
            r6 = 0
            r7 = 0
            prepareViewHierarchyForScreenshot(r1, r5)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r4 != 0) goto L17
            r1.setDrawingCacheEnabled(r5)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
        L17:
            android.graphics.Bitmap r8 = r17.getDrawingCache()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r8 == 0) goto L50
            int r9 = r8.getHeight()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r9 = (double) r9     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r11 = r8.getWidth()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r11 = (double) r11     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r13 = (int) r11     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r14 = (int) r9     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r0 <= 0) goto L3e
            double r13 = (double) r0     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r15 = java.lang.Math.max(r11, r9)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r13 = r13 / r15
            double r11 = r11 * r13
            long r11 = java.lang.Math.round(r11)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r0 = (int) r11     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r9 = r9 * r13
            long r9 = java.lang.Math.round(r9)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r14 = (int) r9     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            r13 = r0
        L3e:
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createScaledBitmap(r8, r13, r14, r5)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            android.graphics.Bitmap$Config r5 = r0.getConfig()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r5 == r2) goto L92
            android.graphics.Bitmap r7 = r0.copy(r2, r6)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            r0.recycle()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            goto L93
        L50:
            int r5 = r17.getMeasuredHeight()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r5 <= 0) goto L93
            int r5 = r17.getMeasuredWidth()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r5 <= 0) goto L93
            int r5 = r17.getMeasuredHeight()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r8 = (double) r5     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r5 = r17.getMeasuredWidth()     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r10 = (double) r5     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r5 = (int) r10     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r12 = (int) r8     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            if (r0 <= 0) goto L7d
            double r12 = (double) r0     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r14 = java.lang.Math.max(r10, r8)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r12 = r12 / r14
            double r14 = r10 * r12
            long r14 = java.lang.Math.round(r14)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r5 = (int) r14     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r12 = r12 * r8
            long r12 = java.lang.Math.round(r12)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            int r12 = (int) r12     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
        L7d:
            android.graphics.Bitmap r0 = android.graphics.Bitmap.createBitmap(r5, r12, r2)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            android.graphics.Canvas r2 = new android.graphics.Canvas     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            r2.<init>(r0)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r13 = (double) r5     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r13 = r13 / r10
            float r5 = (float) r13     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r10 = (double) r12     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            double r10 = r10 / r8
            float r8 = (float) r10     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            r2.scale(r5, r8)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
            r1.draw(r2)     // Catch: java.lang.Throwable -> L9c java.lang.OutOfMemoryError -> L9e
        L92:
            r7 = r0
        L93:
            if (r4 != 0) goto L98
        L95:
            r1.setDrawingCacheEnabled(r6)
        L98:
            prepareViewHierarchyForScreenshot(r1, r6)
            goto Lb8
        L9c:
            r0 = move-exception
            goto Lb9
        L9e:
            r0 = move-exception
            java.lang.String r2 = "UiUtils"
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L9c
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r0 = r0.getMessage()     // Catch: java.lang.Throwable -> L9c
            java.lang.StringBuilder r0 = r5.append(r0)     // Catch: java.lang.Throwable -> L9c
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L9c
            android.util.Log.d(r2, r0)     // Catch: java.lang.Throwable -> L9c
            if (r4 != 0) goto L98
            goto L95
        Lb8:
            return r7
        Lb9:
            if (r4 != 0) goto Lbe
            r1.setDrawingCacheEnabled(r6)
        Lbe:
            prepareViewHierarchyForScreenshot(r1, r6)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.ui.UiUtils.generateScaledScreenshot(android.view.View, int, android.graphics.Bitmap$Config):android.graphics.Bitmap");
    }

    private static void prepareViewHierarchyForScreenshot(View view, boolean z) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                prepareViewHierarchyForScreenshot(viewGroup.getChildAt(i), z);
            }
            return;
        }
        if (view instanceof SurfaceView) {
            view.setWillNotDraw(!z);
        }
    }

    public static File getDirectoryForImageCapture(Context context) throws IOException {
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            File file = new File(context.getFilesDir(), IMAGE_FILE_PATH);
            if (!file.exists() && !file.mkdir()) {
                throw new IOException("Folder cannot be created.");
            }
            return file;
        } finally {
            StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
        }
    }

    public static Uri getUriForImageCaptureFile(Context context, File file) {
        return ContentUriUtils.getContentUriFromFile(context, file);
    }
}
