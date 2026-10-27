package androidx.core.graphics.drawable;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final PorterDuff.Mode f998a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object f1000c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f999b = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f1001d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Parcelable f1002e = null;
    public int f = 0;
    public int g = 0;
    public ColorStateList h = null;
    PorterDuff.Mode i = f998a;
    public String j = null;

    private static int b(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon resource", e4);
            return 0;
        }
    }

    private static String d(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon package", e4);
            return null;
        }
    }

    private static String g(int i) {
        switch (i) {
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
        int i = this.f999b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return b((Icon) this.f1000c);
        }
        if (i == 2) {
            return this.f;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String c() {
        int i = this.f999b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return d((Icon) this.f1000c);
        }
        if (i == 2) {
            return ((String) this.f1000c).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public void e() {
        this.i = PorterDuff.Mode.valueOf(this.j);
        switch (this.f999b) {
            case -1:
                Parcelable parcelable = this.f1002e;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f1000c = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.f1002e;
                if (parcelable2 != null) {
                    this.f1000c = parcelable2;
                    return;
                }
                byte[] bArr = this.f1001d;
                this.f1000c = bArr;
                this.f999b = 3;
                this.f = 0;
                this.g = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                this.f1000c = new String(this.f1001d, Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1000c = this.f1001d;
                return;
        }
    }

    public void f(boolean z) {
        this.j = this.i.name();
        switch (this.f999b) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.f1002e = (Parcelable) this.f1000c;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z) {
                    this.f1002e = (Parcelable) this.f1000c;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f1000c;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f1001d = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f1001d = ((String) this.f1000c).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1001d = (byte[]) this.f1000c;
                return;
            case 4:
            case 6:
                this.f1001d = this.f1000c.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    public String toString() {
        if (this.f999b == -1) {
            return String.valueOf(this.f1000c);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(g(this.f999b));
        switch (this.f999b) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f1000c).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f1000c).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(c());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(a())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f);
                if (this.g != 0) {
                    sb.append(" off=");
                    sb.append(this.g);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f1000c);
                break;
        }
        if (this.h != null) {
            sb.append(" tint=");
            sb.append(this.h);
        }
        if (this.i != f998a) {
            sb.append(" mode=");
            sb.append(this.i);
        }
        sb.append(")");
        return sb.toString();
    }
}
