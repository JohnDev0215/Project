package com.byd.cleardata.i;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.byd.cleardata.FileApplication;
import com.byd.cleardata.R;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f2702a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ViewTreeObserver.OnGlobalLayoutListener f2703b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static HashMap<View, b> f2704c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f2705d = 0;
    public static final int e = FileApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_148);

    class a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f2706b;

        a(Activity activity) {
            this.f2706b = activity;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int iC = h.c(this.f2706b);
            if (h.f2702a != iC) {
                Iterator it = h.f2704c.values().iterator();
                while (it.hasNext()) {
                    ((b) it.next()).a(iC);
                }
                h.f2702a = iC;
            }
        }
    }

    public interface b {
        void a(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            return f2702a;
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int iAbs = Math.abs(decorView.getBottom() - rect.bottom);
        if (iAbs > d()) {
            return iAbs - f2705d;
        }
        f2705d = iAbs;
        return 0;
    }

    private static int d() {
        Resources system = Resources.getSystem();
        int identifier = system.getIdentifier("navigation_bar_height", "dimen", "android");
        if (identifier != 0) {
            return system.getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static void e(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static void f(Activity activity, b bVar) {
        if ((activity.getWindow().getAttributes().flags & 512) != 0) {
            activity.getWindow().clearFlags(512);
        }
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(android.R.id.content);
        f2702a = c(activity);
        f2704c.put(frameLayout, bVar);
        f2703b = new a(activity);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(f2703b);
    }

    public static void g(Activity activity) {
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(android.R.id.content);
        if (frameLayout == null) {
            return;
        }
        frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(f2703b);
        f2704c.remove(frameLayout);
        f2703b = null;
    }
}
