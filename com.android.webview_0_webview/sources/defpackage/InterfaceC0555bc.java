package defpackage;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.view.View;

/* JADX INFO: renamed from: bc, reason: case insensitive filesystem */
/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public interface InterfaceC0555bc extends InterfaceC0250Nr {
    void a(Canvas canvas, long j, Runnable runnable);

    void b(View view, long j);

    int c(Resources resources, String str);

    void e(Canvas canvas, long j);

    void f(View view, long j, boolean z);

    String g();

    void h(Context context);

    void i(InterfaceC0499ac interfaceC0499ac);

    boolean isMultiProcessEnabled();

    boolean j();

    String k(Context context, int i);

    Application l();

    boolean m(View view);
}
