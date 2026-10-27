package defpackage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Base64;

/* JADX INFO: compiled from: WebappIcon.java */
/* JADX INFO: loaded from: classes.dex */
public final class ks3 {
    public final byte[] a;
    public String b;
    public Bitmap c;
    public final String d;
    public final int e;
    public final boolean f;

    public ks3() {
    }

    public ks3(byte[] bArr) {
        this.a = bArr;
    }

    public final Bitmap a() {
        int i;
        if (this.c == null) {
            String str = this.b;
            Bitmap bitmap = null;
            if (str == null || !this.f) {
                String str2 = this.d;
                if (str2 != null && (i = this.e) != 0) {
                    try {
                        BitmapDrawable bitmapDrawable = (BitmapDrawable) c6.c(xz.a.getPackageManager().getResourcesForApplication(str2), i, 0);
                        if (bitmapDrawable != null) {
                            bitmap = bitmapDrawable.getBitmap();
                        }
                    } catch (Exception unused) {
                    }
                }
            } else if (!TextUtils.isEmpty(str)) {
                byte[] bArrDecode = Base64.decode(str, 0);
                bitmap = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length);
            }
            this.c = bitmap;
        }
        return this.c;
    }

    public final String b() {
        if (this.b == null) {
            this.b = we.a(a());
        }
        return this.b;
    }

    public ks3(String str) {
        this.b = str;
        this.f = true;
    }

    public ks3(Bitmap bitmap) {
        this.c = bitmap;
    }

    public ks3(String str, int i) {
        this.d = str;
        this.e = i;
    }
}
