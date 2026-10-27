package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Parcelable;
import androidx.versionedparcelable.CustomVersionedParcelable;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final PorterDuff.Mode f11741k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f11742a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f11743b;
    public byte[] c;
    public Parcelable d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f11744e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f11745f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f11746g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public PorterDuff.Mode f11747h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f11748i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f11749j;

    public IconCompat() {
        this.f11742a = -1;
        this.c = null;
        this.d = null;
        this.f11744e = 0;
        this.f11745f = 0;
        this.f11746g = null;
        this.f11747h = f11741k;
        this.f11748i = null;
    }

    public static IconCompat a(Context context, int i7) {
        context.getClass();
        return b(context.getResources(), context.getPackageName(), i7);
    }

    public static IconCompat b(Resources resources, String str, int i7) {
        str.getClass();
        if (i7 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f11744e = i7;
        if (resources != null) {
            try {
                iconCompat.f11743b = resources.getResourceName(i7);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f11743b = str;
        }
        iconCompat.f11749j = str;
        return iconCompat;
    }

    public final int c() {
        int i7 = this.f11742a;
        if (i7 == -1) {
            return ((Icon) this.f11743b).getResId();
        }
        if (i7 == 2) {
            return this.f11744e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public final Uri d() {
        int i7 = this.f11742a;
        if (i7 == -1) {
            return ((Icon) this.f11743b).getUri();
        }
        if (i7 == 4 || i7 == 6) {
            return Uri.parse((String) this.f11743b);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x007e  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final android.graphics.drawable.Icon e(android.content.Context r5) {
        /*
            Method dump skipped, instruction units count: 336
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.core.graphics.drawable.IconCompat.e(android.content.Context):android.graphics.drawable.Icon");
    }

    public final String toString() {
        String str;
        if (this.f11742a == -1) {
            return String.valueOf(this.f11743b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        switch (this.f11742a) {
            case 1:
                str = "BITMAP";
                break;
            case 2:
                str = "RESOURCE";
                break;
            case 3:
                str = "DATA";
                break;
            case 4:
                str = "URI";
                break;
            case 5:
                str = "BITMAP_MASKABLE";
                break;
            case 6:
                str = "URI_MASKABLE";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        sb.append(str);
        switch (this.f11742a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f11743b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f11743b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f11749j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(c())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f11744e);
                if (this.f11745f != 0) {
                    sb.append(" off=");
                    sb.append(this.f11745f);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f11743b);
                break;
        }
        if (this.f11746g != null) {
            sb.append(" tint=");
            sb.append(this.f11746g);
        }
        if (this.f11747h != f11741k) {
            sb.append(" mode=");
            sb.append(this.f11747h);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat(int i7) {
        this.c = null;
        this.d = null;
        this.f11744e = 0;
        this.f11745f = 0;
        this.f11746g = null;
        this.f11747h = f11741k;
        this.f11748i = null;
        this.f11742a = i7;
    }
}
