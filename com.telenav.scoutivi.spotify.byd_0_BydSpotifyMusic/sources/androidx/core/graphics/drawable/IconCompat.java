package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Parcelable;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    static final PorterDuff.Mode f1955k = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    Object f1957b;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f1965j;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public int f1956a = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f1958c = null;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Parcelable f1959d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f1960e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1961f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ColorStateList f1962g = null;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    PorterDuff.Mode f1963h = f1955k;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public String f1964i = null;

    private static int b(Icon icon) {
        return icon.getResId();
    }

    private static String e(int i7) {
        switch (i7) {
            case 1:
                return "BITMAP";
            case 2:
                return "RESOURCE";
            case 3:
                return "DATA";
            case 4:
                return "URI";
            case 5:
                return "BITMAP_MASKABLE";
            case 6:
                return "URI_MASKABLE";
            default:
                return "UNKNOWN";
        }
    }

    public int a() {
        int i7 = this.f1956a;
        if (i7 == -1) {
            return b((Icon) this.f1957b);
        }
        if (i7 == 2) {
            return this.f1960e;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public void c() {
        this.f1963h = PorterDuff.Mode.valueOf(this.f1964i);
        switch (this.f1956a) {
            case -1:
                Parcelable parcelable = this.f1959d;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f1957b = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.f1959d;
                if (parcelable2 != null) {
                    this.f1957b = parcelable2;
                    return;
                }
                byte[] bArr = this.f1958c;
                this.f1957b = bArr;
                this.f1956a = 3;
                this.f1960e = 0;
                this.f1961f = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                String str = new String(this.f1958c, Charset.forName("UTF-16"));
                this.f1957b = str;
                if (this.f1956a == 2 && this.f1965j == null) {
                    this.f1965j = str.split(":", -1)[0];
                    return;
                }
                return;
            case 3:
                this.f1957b = this.f1958c;
                return;
        }
    }

    public void d(boolean z6) {
        this.f1964i = this.f1963h.name();
        switch (this.f1956a) {
            case -1:
                if (z6) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.f1959d = (Parcelable) this.f1957b;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z6) {
                    this.f1959d = (Parcelable) this.f1957b;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f1957b;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f1958c = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f1958c = ((String) this.f1957b).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1958c = (byte[]) this.f1957b;
                return;
            case 4:
            case 6:
                this.f1958c = this.f1957b.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    public String toString() {
        if (this.f1956a == -1) {
            return String.valueOf(this.f1957b);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(e(this.f1956a));
        switch (this.f1956a) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f1957b).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f1957b).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(this.f1965j);
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(a())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f1960e);
                if (this.f1961f != 0) {
                    sb.append(" off=");
                    sb.append(this.f1961f);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f1957b);
                break;
        }
        if (this.f1962g != null) {
            sb.append(" tint=");
            sb.append(this.f1962g);
        }
        if (this.f1963h != f1955k) {
            sb.append(" mode=");
            sb.append(this.f1963h);
        }
        sb.append(")");
        return sb.toString();
    }
}
