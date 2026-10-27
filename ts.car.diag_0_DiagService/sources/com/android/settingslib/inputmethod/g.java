package com.android.settingslib.inputmethod;

import android.content.Context;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class g {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final String f1367c = "g";
    private static volatile g d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ArrayList<InputMethodInfo> f1368a = new ArrayList<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final InputMethodManager f1369b;

    private g(Context context) {
        context.getContentResolver();
        this.f1369b = (InputMethodManager) context.getSystemService(InputMethodManager.class);
        b();
    }

    public static g a(Context context) {
        if (d == null) {
            synchronized (f1367c) {
                if (d == null) {
                    d = new g(context);
                }
            }
        }
        return d;
    }

    public void b() {
        this.f1368a.clear();
        this.f1368a.addAll(this.f1369b.getInputMethodList());
    }
}
