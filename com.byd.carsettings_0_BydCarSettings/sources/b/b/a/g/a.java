package b.b.a.g;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.byd.base.R$anim;
import com.byd.base.R$id;
import com.byd.base.R$layout;
import java.lang.ref.WeakReference;

/* JADX INFO: compiled from: BydToast.java */
/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static WeakReference<Toast> f521a;

    public static void a() {
        Toast toast;
        WeakReference<Toast> weakReference = f521a;
        if (weakReference == null || (toast = weakReference.get()) == null) {
            return;
        }
        toast.cancel();
        weakReference.clear();
    }

    public static String b(int i, Context context, String str) {
        return "Toast level = " + i + " [ context = " + context + " , msg = " + str + " ]";
    }

    public static void c(Context context, String str) {
    }

    public static void d(Context context, String str) {
        if (context == null) {
            return;
        }
        e(context.getApplicationContext(), str, false);
    }

    public static void e(Context context, String str, boolean z) {
        if (context == null) {
            return;
        }
        c(context, str);
        h(context, str, z);
    }

    public static void f(Context context, String str, int i, boolean z) {
        g(context, str, i, z, null);
    }

    @SuppressLint({"MissingPermission"})
    public static void g(Context context, String str, int i, boolean z, Drawable drawable) {
        if (context == null || TextUtils.isEmpty(str)) {
            Log.w("BydToast", b(i, context, str));
            return;
        }
        c(context, str);
        a();
        Toast toast = new Toast(context);
        View viewInflate = LayoutInflater.from(context).inflate(R$layout.base_view_toast, (ViewGroup) null);
        toast.setView(viewInflate);
        int i2 = 0;
        toast.setGravity(17, 0, 0);
        toast.setDuration(z ? 1 : 0);
        ((TextView) viewInflate.findViewById(R$id.tvMsg)).setText(str);
        ImageView imageView = (ImageView) viewInflate.findViewById(R$id.ivIcon);
        imageView.clearAnimation();
        if (drawable != null) {
            imageView.setImageDrawable(drawable);
        } else {
            if (i == 0) {
                i2 = 8;
            } else if (i == 1) {
                Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R$anim.rotate);
                animationLoadAnimation.setRepeatCount(-1);
                animationLoadAnimation.setRepeatMode(1);
                imageView.startAnimation(animationLoadAnimation);
            }
            imageView.setVisibility(i2);
            imageView.setImageLevel(i);
        }
        f521a = new WeakReference<>(toast);
        toast.show();
    }

    public static void h(Context context, String str, boolean z) {
        f(context, str, 0, z);
    }
}
