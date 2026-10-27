package J4;

import A7.m;
import B2.O;
import android.view.View;
import android.view.Window;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final View f3082a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Window f3083b;
    public final O c;

    public a(View view, Window window) {
        m.f(view, "view");
        this.f3082a = view;
        this.f3083b = window;
        this.c = window != null ? new O(view, window) : null;
    }
}
