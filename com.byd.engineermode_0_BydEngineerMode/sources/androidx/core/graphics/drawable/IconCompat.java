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
    static final PorterDuff.Mode f871a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    Object f873c;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f872b = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f874d = null;
    public Parcelable e = null;
    public int f = 0;
    public int g = 0;
    public ColorStateList h = null;
    PorterDuff.Mode i = f871a;
    public String j = null;

    private static int b(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon resource", e);
            return 0;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon resource", e2);
            return 0;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        }
    }

    private static String d(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon package", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon package", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
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
        int i = this.f872b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return b((Icon) this.f873c);
        }
        if (i == 2) {
            return this.f;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String c() {
        int i = this.f872b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return d((Icon) this.f873c);
        }
        if (i == 2) {
            return ((String) this.f873c).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public void e() {
        this.i = PorterDuff.Mode.valueOf(this.j);
        switch (this.f872b) {
            case -1:
                Parcelable parcelable = this.e;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f873c = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.e;
                if (parcelable2 != null) {
                    this.f873c = parcelable2;
                    return;
                }
                byte[] bArr = this.f874d;
                this.f873c = bArr;
                this.f872b = 3;
                this.f = 0;
                this.g = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                this.f873c = new String(this.f874d, Charset.forName("UTF-16"));
                return;
            case 3:
                this.f873c = this.f874d;
                return;
        }
    }

    public void f(boolean z) {
        this.j = this.i.name();
        switch (this.f872b) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.e = (Parcelable) this.f873c;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z) {
                    this.e = (Parcelable) this.f873c;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f873c;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f874d = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f874d = ((String) this.f873c).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f874d = (byte[]) this.f873c;
                return;
            case 4:
            case 6:
                this.f874d = this.f873c.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    public String toString() {
        if (this.f872b == -1) {
            return String.valueOf(this.f873c);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(g(this.f872b));
        switch (this.f872b) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f873c).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f873c).getHeight());
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
                sb.append(this.f873c);
                break;
        }
        if (this.h != null) {
            sb.append(" tint=");
            sb.append(this.h);
        }
        if (this.i != f871a) {
            sb.append(" mode=");
            sb.append(this.i);
        }
        sb.append(")");
        return sb.toString();
    }
}
