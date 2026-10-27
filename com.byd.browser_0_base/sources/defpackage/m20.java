package defpackage;

import android.app.Activity;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageButton;
import org.chromium.chrome.browser.customtabs.CustomTabBottomBarView;

/* JADX INFO: compiled from: CustomButtonParams.java */
/* JADX INFO: loaded from: classes.dex */
public interface m20 {
    BitmapDrawable a(Activity activity);

    void b(Bitmap bitmap, String str);

    boolean c();

    String d();

    PendingIntent e();

    ImageButton f(Activity activity, CustomTabBottomBarView customTabBottomBarView, c30 c30Var);

    boolean g(Activity activity);

    int getId();
}
