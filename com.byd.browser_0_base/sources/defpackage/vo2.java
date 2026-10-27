package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.WeakHashMap;
import org.json.JSONException;

/* JADX INFO: compiled from: SkinCompatUserThemeManager.java */
/* JADX INFO: loaded from: classes3.dex */
public final class vo2 {
    public static final vo2 i = new vo2();
    public boolean d;
    public boolean h;
    public final HashMap<String, dv> a = new HashMap<>();
    public final Object b = new Object();
    public final WeakHashMap<Integer, WeakReference<ColorStateList>> c = new WeakHashMap<>();
    public final HashMap<String, String> e = new HashMap<>();
    public final Object f = new Object();
    public final WeakHashMap<Integer, WeakReference<Drawable>> g = new WeakHashMap<>();

    public vo2() {
        try {
            d();
        } catch (JSONException unused) {
            this.a.clear();
            this.e.clear();
        }
    }

    public static String c(int i2, String str) {
        Context context = no2.o.g;
        if (str.equalsIgnoreCase(context.getResources().getResourceTypeName(i2))) {
            return context.getResources().getResourceEntryName(i2);
        }
        return null;
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x007f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.content.res.ColorStateList a(int r10) {
        /*
            Method dump skipped, instruction units count: 605
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vo2.a(int):android.content.res.ColorStateList");
    }

    public final Drawable b(int i2) {
        Drawable bitmapDrawable;
        synchronized (this.f) {
            WeakReference<Drawable> weakReference = this.g.get(Integer.valueOf(i2));
            if (weakReference == null) {
                bitmapDrawable = null;
            } else {
                bitmapDrawable = weakReference.get();
                if (bitmapDrawable == null) {
                    this.g.remove(Integer.valueOf(i2));
                    bitmapDrawable = null;
                }
            }
        }
        if (bitmapDrawable == null) {
            String strC = c(i2, "drawable");
            if (!TextUtils.isEmpty(strC)) {
                String str = this.e.get(strC);
                if (!TextUtils.isEmpty(str)) {
                    String[] strArrSplit = str.split(":");
                    boolean z = false;
                    String str2 = strArrSplit[0];
                    int iIntValue = strArrSplit.length == 2 ? Integer.valueOf(strArrSplit[1]).intValue() : 0;
                    if (!TextUtils.isEmpty(str2) && new File(str2).exists()) {
                        z = true;
                    }
                    if (z) {
                        if (iIntValue == 0) {
                            bitmapDrawable = Drawable.createFromPath(str2);
                        } else {
                            Matrix matrix = new Matrix();
                            matrix.postRotate(iIntValue);
                            Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str2);
                            bitmapDrawable = new BitmapDrawable((Resources) null, Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix, true));
                        }
                        if (bitmapDrawable != null) {
                            synchronized (this.f) {
                                this.g.put(Integer.valueOf(i2), new WeakReference<>(bitmapDrawable));
                            }
                        }
                    }
                }
            }
        }
        return bitmapDrawable;
    }

    /* JADX WARN: Removed duplicated region for block: B:122:0x01d5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void d() throws org.json.JSONException {
        /*
            Method dump skipped, instruction units count: 558
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.vo2.d():void");
    }
}
