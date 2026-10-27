package spi;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.media.MediaBrowserServiceCompat;
import com.android.car.ui.R$color;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/* JADX INFO: compiled from: MediaSource.java */
/* JADX INFO: loaded from: classes.dex */
public final class b7 {

    @NonNull
    public final ComponentName a;

    @NonNull
    public final CharSequence b;

    @NonNull
    public final Drawable c;

    @NonNull
    public final t4 d;

    @VisibleForTesting
    public b7(@NonNull ComponentName componentName, @NonNull CharSequence charSequence, @NonNull Drawable drawable, @NonNull t4 t4Var) {
        this.a = componentName;
        this.b = charSequence;
        this.c = drawable;
        this.d = t4Var;
    }

    @Nullable
    public static b7 a(@NonNull Context context, @NonNull ComponentName componentName) {
        ServiceInfo serviceInfo;
        PackageManager packageManager = context.getPackageManager();
        Intent intent = new Intent();
        intent.setAction(MediaBrowserServiceCompat.SERVICE_INTERFACE);
        intent.setPackage(componentName.getPackageName());
        List<ResolveInfo> listQueryIntentServices = packageManager.queryIntentServices(intent, 64);
        if (listQueryIntentServices == null || listQueryIntentServices.isEmpty()) {
            serviceInfo = null;
        } else {
            String className = componentName.getClassName();
            if (TextUtils.isEmpty(className)) {
                serviceInfo = listQueryIntentServices.get(0).serviceInfo;
            } else {
                Iterator<ResolveInfo> it = listQueryIntentServices.iterator();
                while (it.hasNext()) {
                    ServiceInfo serviceInfo2 = it.next().serviceInfo;
                    if (serviceInfo2.name.equals(className)) {
                        serviceInfo = serviceInfo2;
                        break;
                    }
                }
                serviceInfo = null;
            }
        }
        String str = serviceInfo != null ? serviceInfo.name : null;
        if (TextUtils.isEmpty(str)) {
            StringBuilder sbK = c.k("No MediaBrowserService found in component ");
            sbK.append(componentName.flattenToString());
            Log.w("MediaSource", sbK.toString());
            return null;
        }
        try {
            String packageName = componentName.getPackageName();
            return new b7(new ComponentName(packageName, str), (serviceInfo == null || serviceInfo.labelRes == 0) ? context.getPackageManager().getApplicationInfo(packageName, 128).loadLabel(context.getPackageManager()) : serviceInfo.loadLabel(context.getPackageManager()), b(context, serviceInfo, packageName), new t4(context));
        } catch (PackageManager.NameNotFoundException unused) {
            StringBuilder sbK2 = c.k("Component not found ");
            sbK2.append(componentName.flattenToString());
            Log.w("MediaSource", sbK2.toString());
            return null;
        }
    }

    @NonNull
    public static Drawable b(@NonNull Context context, @Nullable ServiceInfo serviceInfo, @NonNull String str) {
        Drawable drawableLoadIcon = serviceInfo != null ? serviceInfo.loadIcon(context.getPackageManager()) : context.getPackageManager().getApplicationIcon(str);
        if (!(drawableLoadIcon instanceof BitmapDrawable)) {
            return drawableLoadIcon;
        }
        h2 h2VarA = h2.a(context);
        Bitmap bitmap = ((BitmapDrawable) drawableLoadIcon).getBitmap();
        return (!h2VarA.b() || bitmap == null) ? drawableLoadIcon : new BitmapDrawable(context.getResources(), x.a(bitmap, context.getColor(R$color.improper_image_refs_tint_color)));
    }

    public final Bitmap c() {
        t4 t4Var = this.d;
        Drawable drawable = this.c;
        Objects.requireNonNull(t4Var);
        Bitmap bitmapB = x.b(drawable, null);
        int width = bitmapB.getWidth();
        int height = bitmapB.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(-1);
        canvas.save();
        canvas.scale(width / 100.0f, height / 100.0f);
        canvas.drawPath(t4Var.a, paint);
        canvas.restore();
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapB, 0.0f, 0.0f, paint);
        return bitmapCreateBitmap;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || b7.class != obj.getClass()) {
            return false;
        }
        return Objects.equals(this.a, ((b7) obj).a);
    }

    public final int hashCode() {
        return Objects.hash(this.a);
    }

    @NonNull
    public final String toString() {
        return this.a.flattenToString();
    }
}
