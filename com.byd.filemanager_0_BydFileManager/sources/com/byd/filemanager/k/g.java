package com.byd.filemanager.k;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import com.byd.filemanager.FileApplication;
import com.byd.filemanager.R;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: KeyboardUtils.java */
/* JADX INFO: loaded from: classes.dex */
public final class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3616a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static ViewTreeObserver.OnGlobalLayoutListener f3617b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static HashMap<View, b> f3618c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static int f3619d = 0;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final int f3620e = FileApplication.a().getResources().getDimensionPixelSize(R.dimen.dp_148);

    /* JADX INFO: compiled from: KeyboardUtils.java */
    class a implements ViewTreeObserver.OnGlobalLayoutListener {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ Activity f3621b;

        a(Activity activity) {
            this.f3621b = activity;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            int iC = g.c(this.f3621b);
            if (g.f3616a != iC) {
                Iterator it = g.f3618c.values().iterator();
                while (it.hasNext()) {
                    ((b) it.next()).a(iC);
                }
                g.f3616a = iC;
            }
        }
    }

    /* JADX INFO: compiled from: KeyboardUtils.java */
    public interface b {
        void a(int i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int c(Activity activity) {
        View decorView = activity.getWindow().getDecorView();
        if (decorView == null) {
            return f3616a;
        }
        Rect rect = new Rect();
        decorView.getWindowVisibleDisplayFrame(rect);
        int iAbs = Math.abs(decorView.getBottom() - rect.bottom);
        if (iAbs > d()) {
            return iAbs - f3619d;
        }
        f3619d = iAbs;
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
        f3616a = c(activity);
        f3618c.put(frameLayout, bVar);
        f3617b = new a(activity);
        frameLayout.getViewTreeObserver().addOnGlobalLayoutListener(f3617b);
    }

    public static void g(View view) {
        ((InputMethodManager) view.getContext().getSystemService("input_method")).showSoftInput(view, 1);
    }

    public static void h(Activity activity) {
        FrameLayout frameLayout = (FrameLayout) activity.findViewById(android.R.id.content);
        if (frameLayout == null) {
            return;
        }
        frameLayout.getViewTreeObserver().removeOnGlobalLayoutListener(f3617b);
        f3618c.remove(frameLayout);
        f3617b = null;
    }
}
