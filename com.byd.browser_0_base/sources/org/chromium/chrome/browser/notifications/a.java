package org.chromium.chrome.browser.notifications;

import android.R;
import android.app.PendingIntent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.os.Bundle;
import androidx.core.app.a;
import androidx.core.graphics.drawable.IconCompat;
import defpackage.lp1;
import defpackage.o8;
import defpackage.oa2;
import defpackage.qq;
import defpackage.uc2;
import java.util.ArrayList;
import java.util.HashSet;

/* JADX INFO: compiled from: NotificationBuilderBase.java */
/* JADX INFO: loaded from: classes2.dex */
public abstract class a {
    public final int a;
    public final int b;
    public final uc2 c;
    public CharSequence d;
    public CharSequence e;
    public CharSequence f;
    public String g;
    public CharSequence h;
    public Bitmap i;
    public int j;
    public Bitmap k;
    public Bitmap l;
    public o8 m;
    public o8 n;
    public final ArrayList o = new ArrayList(2);
    public C0095a p;
    public int q;
    public long[] r;
    public boolean s;
    public long t;
    public boolean u;
    public Bitmap v;

    public a(Resources resources) {
        this.a = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width);
        this.b = resources.getDimensionPixelSize(R.dimen.notification_large_icon_height);
        int dimensionPixelSize = resources.getDimensionPixelSize(R.dimen.notification_large_icon_width);
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R.dimen.notification_large_icon_height);
        this.c = new uc2(dimensionPixelSize, dimensionPixelSize2, Math.min(dimensionPixelSize, dimensionPixelSize2) / 2, -6908266, resources.getDisplayMetrics().density * 28.0f);
    }

    public static void a(qq qqVar, C0095a c0095a) {
        a.C0006a c0006a;
        PendingIntent pendingIntent = (PendingIntent) c0095a.d.c;
        CharSequence charSequence = c0095a.c;
        Bitmap bitmap = c0095a.b;
        if (bitmap != null) {
            IconCompat iconCompat = new IconCompat(1);
            iconCompat.b = bitmap;
            c0006a = new a.C0006a(iconCompat, charSequence, pendingIntent);
        } else {
            int i = c0095a.a;
            c0006a = new a.C0006a(i != 0 ? IconCompat.b(null, "", i) : null, charSequence, pendingIntent, new Bundle());
        }
        if (c0095a.e == 1) {
            oa2 oa2Var = new oa2("key_text_reply", c0095a.g, true, new Bundle(), new HashSet());
            if (c0006a.f == null) {
                c0006a.f = new ArrayList<>();
            }
            c0006a.f.add(oa2Var);
        }
        int i2 = c0095a.f;
        if (i2 == -1) {
            qqVar.a.b.add(c0006a.a());
        } else {
            qqVar.s(c0006a.a(), c0095a.d.a, i2);
        }
    }

    public static CharSequence d(CharSequence charSequence) {
        return (charSequence != null && charSequence.length() > 5120) ? charSequence.subSequence(0, 5120) : charSequence;
    }

    public final void b(Bitmap bitmap, String str, o8 o8Var, int i, String str2) {
        ArrayList arrayList = this.o;
        if (arrayList.size() == 2) {
            throw new IllegalStateException("Cannot add more than 2 actions.");
        }
        if (bitmap != null) {
            Paint paint = new Paint();
            paint.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_ATOP));
            new Canvas(bitmap).drawBitmap(bitmap, 0.0f, 0.0f, paint);
        }
        arrayList.add(new C0095a(bitmap, d(str), o8Var, i, str2));
    }

    public abstract lp1 c(o8 o8Var);

    /* JADX INFO: renamed from: org.chromium.chrome.browser.notifications.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: NotificationBuilderBase.java */
    public static class C0095a {
        public final int a;
        public final Bitmap b;
        public final CharSequence c;
        public final o8 d;
        public final int e;
        public final int f;
        public final String g;

        public C0095a(int i, CharSequence charSequence, o8 o8Var) {
            this.a = i;
            this.c = charSequence;
            this.d = o8Var;
            this.e = 0;
            this.g = null;
            this.f = 12;
        }

        public C0095a(Bitmap bitmap, CharSequence charSequence, o8 o8Var, int i, String str) {
            this.b = bitmap;
            this.c = charSequence;
            this.d = o8Var;
            this.e = i;
            this.g = str;
            this.f = -1;
        }
    }
}
