package defpackage;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import androidx.appcompat.app.AlertDialog;
import org.chromium.chrome.R$string;
import org.chromium.chrome.R$style;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: compiled from: SaveBitmapDelegate.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ud2 {
    public final Context a;
    public final Bitmap b;
    public final int c;
    public final WindowAndroid d;
    public final Runnable e;
    public AlertDialog f;

    /* JADX INFO: compiled from: SaveBitmapDelegate.java */
    public class a implements DialogInterface.OnClickListener {
        public a() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
            Uri.Builder builderScheme = new Uri.Builder().scheme("package");
            ud2 ud2Var = ud2.this;
            intent.setData(builderScheme.opaquePart(ud2Var.a.getPackageName()).build());
            ((Activity) ud2Var.a).startActivity(intent);
        }
    }

    /* JADX INFO: compiled from: SaveBitmapDelegate.java */
    public class b implements DialogInterface.OnClickListener {
        public b() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public final void onClick(DialogInterface dialogInterface, int i) {
            ud2.this.f.cancel();
        }
    }

    public ud2(Context context, Bitmap bitmap, int i, Runnable runnable, WindowAndroid windowAndroid) {
        this.a = context;
        this.b = bitmap;
        this.c = i;
        this.d = windowAndroid;
        this.e = runnable;
    }

    public final void a() {
        if (this.b == null) {
            return;
        }
        boolean zA = yj.a();
        WindowAndroid windowAndroid = this.d;
        if (!((zA || windowAndroid.hasPermission("android.permission.WRITE_EXTERNAL_STORAGE") || windowAndroid.canRequestPermission("android.permission.WRITE_EXTERNAL_STORAGE")) ? false : true)) {
            km0.a(windowAndroid, new wy2(new lj1(21, this), 2));
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this.a, R$style.ThemeOverlay_BrowserUI_AlertDialog);
        builder.b(R$string.sharing_hub_storage_disabled_text);
        builder.e(R$string.cancel, new b());
        builder.f(R$string.sharing_hub_open_settings_label, new a());
        AlertDialog alertDialogA = builder.a();
        this.f = alertDialogA;
        alertDialogA.setCanceledOnTouchOutside(false);
        this.f.show();
    }
}
