package defpackage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import androidx.appcompat.content.res.AppCompatResources;
import java.util.HashMap;
import java.util.Iterator;
import org.chromium.base.ThreadUtils;
import org.chromium.base.b;
import org.chromium.chrome.browser.signin.services.R$dimen;
import org.chromium.chrome.browser.signin.services.R$drawable;
import org.chromium.components.signin.base.AccountInfo;
import org.chromium.components.signin.identitymanager.a;

/* JADX INFO: compiled from: ProfileDataCache.java */
/* JADX INFO: loaded from: classes2.dex */
public final class h62 implements a.InterfaceC0143a {
    public final Context a;
    public final int b;
    public a c;
    public final BitmapDrawable e;
    public final HashMap d = new HashMap();
    public final org.chromium.base.b<b> f = new org.chromium.base.b<>();
    public final HashMap g = new HashMap();

    /* JADX INFO: compiled from: ProfileDataCache.java */
    public static class a {
        public final Drawable a;
        public final int b;
        public final Point c;
        public final int d;

        public a(Context context, int i) {
            Resources resources = context.getResources();
            this.a = AppCompatResources.a(context, i);
            this.b = resources.getDimensionPixelSize(R$dimen.badge_size);
            this.c = new Point(resources.getDimensionPixelOffset(R$dimen.badge_position_x), resources.getDimensionPixelOffset(R$dimen.badge_position_y));
            this.d = resources.getDimensionPixelSize(R$dimen.badge_border_size);
        }
    }

    /* JADX INFO: compiled from: ProfileDataCache.java */
    public interface b {
        void n(String str);
    }

    public h62(Context context, int i, a aVar) {
        this.a = context;
        this.b = i;
        this.c = aVar;
        Drawable drawableA = AppCompatResources.a(context, R$drawable.logo_avatar_anonymous);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(i, i, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        canvas.drawColor(0);
        drawableA.setBounds(0, 0, i, i);
        drawableA.draw(canvas);
        this.e = new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
        n3.m0().g(new jb1(2, this));
    }

    public static h62 c(Context context) {
        return new h62(context, context.getResources().getDimensionPixelSize(R$dimen.user_picture_size), null);
    }

    @Override // org.chromium.components.signin.identitymanager.a.InterfaceC0143a
    public final void a(AccountInfo accountInfo) {
        boolean z;
        BitmapDrawable bitmapDrawable;
        if (accountInfo == null) {
            return;
        }
        boolean zIsEmpty = TextUtils.isEmpty(accountInfo.d);
        Bitmap bitmap = accountInfo.f;
        boolean z2 = (zIsEmpty && TextUtils.isEmpty(accountInfo.e) && bitmap == null) ? false : true;
        HashMap map = this.d;
        if (!z2) {
            String email = accountInfo.getEmail();
            if ((map.get(email) != null ? (a) map.get(email) : this.c) == null) {
                return;
            }
        }
        String email2 = accountInfo.getEmail();
        String str = accountInfo.d;
        String str2 = accountInfo.e;
        int capabilityByName = accountInfo.g.getCapabilityByName("accountcapabilities/haytqlldmfya");
        if (capabilityByName != 0) {
            if (capabilityByName != 1) {
                accountInfo.getEmail();
            }
            z = true;
        } else {
            z = false;
        }
        int i = this.b;
        Context context = this.a;
        BitmapDrawable bitmapDrawableA = bitmap != null ? ob.a(context.getResources(), bitmap, i) : this.e;
        a aVar = map.get(email2) != null ? (a) map.get(email2) : this.c;
        if (aVar != null) {
            Point point = aVar.c;
            int i2 = point.x;
            int i3 = aVar.b;
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(Math.max(i2 + i3, i), Math.max(point.y + i3, i), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            bitmapDrawableA.setBounds(0, 0, i, i);
            bitmapDrawableA.draw(canvas);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            int i4 = i3 / 2;
            canvas.drawCircle(point.x + i4, point.y + i4, i4 + aVar.d, paint);
            int i5 = point.x;
            int i6 = point.y;
            Drawable drawable = aVar.a;
            drawable.setBounds(i5, i6, i5 + i3, i3 + i6);
            drawable.draw(canvas);
            bitmapDrawable = new BitmapDrawable(context.getResources(), bitmapCreateBitmap);
        } else {
            bitmapDrawable = bitmapDrawableA;
        }
        this.g.put(email2, new fb0(email2, bitmapDrawable, str, str2, z));
        Iterator<b> it = this.f.iterator();
        while (true) {
            b.a aVar2 = (b.a) it;
            if (!aVar2.hasNext()) {
                return;
            } else {
                ((b) aVar2.next()).n(email2);
            }
        }
    }

    public final void b(b bVar) {
        Object obj = ThreadUtils.a;
        org.chromium.base.b<b> bVar2 = this.f;
        if (bVar2.isEmpty()) {
            n3.m0().g(new g62(this, 0));
        }
        bVar2.a(bVar);
    }

    public final fb0 d(String str) {
        fb0 fb0Var = (fb0) this.g.get(str);
        return fb0Var == null ? new fb0(str, this.e, null, null, true) : fb0Var;
    }

    public final void e(b bVar) {
        Object obj = ThreadUtils.a;
        org.chromium.base.b<b> bVar2 = this.f;
        bVar2.c(bVar);
        if (bVar2.isEmpty()) {
            n3.m0().g(new f62(this, 0));
        }
    }
}
