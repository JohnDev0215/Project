package androidx.core.graphics.drawable;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Parcelable;
import android.util.Log;
import androidx.versionedparcelable.CustomVersionedParcelable;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.Charset;

/* JADX INFO: loaded from: classes.dex */
public class IconCompat extends CustomVersionedParcelable {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final PorterDuff.Mode f453a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f454b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f455c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f456d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Parcelable f457e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f458f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f459g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f460h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f461i;
    public String j;

    public IconCompat() {
        this.f454b = -1;
        this.f456d = null;
        this.f457e = null;
        this.f458f = 0;
        this.f459g = 0;
        this.f460h = null;
        this.f461i = f453a;
        this.j = null;
    }

    public static Bitmap a(Bitmap bitmap, boolean z) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f2 = iMin;
        float f3 = 0.5f * f2;
        float f4 = 0.9166667f * f3;
        if (z) {
            float f5 = 0.010416667f * f2;
            paint.setColor(0);
            paint.setShadowLayer(f5, 0.0f, f2 * 0.020833334f, 1023410176);
            canvas.drawCircle(f3, f3, f4, paint);
            paint.setShadowLayer(f5, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f3, f3, f4, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2, (-(bitmap.getHeight() - iMin)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f3, f3, f4, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    public static IconCompat b(Resources resources, String str, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i2 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f458f = i2;
        if (resources != null) {
            try {
                iconCompat.f455c = resources.getResourceName(i2);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f455c = str;
        }
        return iconCompat;
    }

    public static int d(Icon icon) {
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

    public static String f(Icon icon) {
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

    public static Uri h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e2) {
            Log.e("IconCompat", "Unable to get icon uri", e2);
            return null;
        } catch (NoSuchMethodException e3) {
            Log.e("IconCompat", "Unable to get icon uri", e3);
            return null;
        } catch (InvocationTargetException e4) {
            Log.e("IconCompat", "Unable to get icon uri", e4);
            return null;
        }
    }

    public static String n(int i2) {
        switch (i2) {
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

    public int c() {
        int i2 = this.f454b;
        if (i2 == -1 && Build.VERSION.SDK_INT >= 23) {
            return d((Icon) this.f455c);
        }
        if (i2 == 2) {
            return this.f458f;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String e() {
        int i2 = this.f454b;
        if (i2 == -1 && Build.VERSION.SDK_INT >= 23) {
            return f((Icon) this.f455c);
        }
        if (i2 == 2) {
            return ((String) this.f455c).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public Uri g() {
        int i2 = this.f454b;
        if (i2 == -1 && Build.VERSION.SDK_INT >= 23) {
            return h((Icon) this.f455c);
        }
        if (i2 == 4 || i2 == 6) {
            return Uri.parse((String) this.f455c);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public final InputStream i(Context context) {
        Uri uriG = g();
        String scheme = uriG.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriG);
            } catch (Exception e2) {
                Log.w("IconCompat", "Unable to load image from URI: " + uriG, e2);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.f455c));
        } catch (FileNotFoundException e3) {
            Log.w("IconCompat", "Unable to load image from path: " + uriG, e3);
            return null;
        }
    }

    public void j() {
        this.f461i = PorterDuff.Mode.valueOf(this.j);
        switch (this.f454b) {
            case -1:
                Parcelable parcelable = this.f457e;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f455c = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.f457e;
                if (parcelable2 != null) {
                    this.f455c = parcelable2;
                    return;
                }
                byte[] bArr = this.f456d;
                this.f455c = bArr;
                this.f454b = 3;
                this.f458f = 0;
                this.f459g = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                this.f455c = new String(this.f456d, Charset.forName("UTF-16"));
                return;
            case 3:
                this.f455c = this.f456d;
                return;
        }
    }

    public void k(boolean z) {
        this.j = this.f461i.name();
        switch (this.f454b) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.f457e = (Parcelable) this.f455c;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z) {
                    this.f457e = (Parcelable) this.f455c;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f455c;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f456d = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f456d = ((String) this.f455c).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f456d = (byte[]) this.f455c;
                return;
            case 4:
            case 6:
                this.f456d = this.f455c.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    @Deprecated
    public Icon l() {
        return m(null);
    }

    public Icon m(Context context) {
        Icon iconCreateWithBitmap;
        switch (this.f454b) {
            case -1:
                return (Icon) this.f455c;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                iconCreateWithBitmap = Icon.createWithBitmap((Bitmap) this.f455c);
                break;
            case 2:
                iconCreateWithBitmap = Icon.createWithResource(e(), this.f458f);
                break;
            case 3:
                iconCreateWithBitmap = Icon.createWithData((byte[]) this.f455c, this.f458f, this.f459g);
                break;
            case 4:
                iconCreateWithBitmap = Icon.createWithContentUri((String) this.f455c);
                break;
            case 5:
                iconCreateWithBitmap = Build.VERSION.SDK_INT < 26 ? Icon.createWithBitmap(a((Bitmap) this.f455c, false)) : Icon.createWithAdaptiveBitmap((Bitmap) this.f455c);
                break;
            case 6:
                if (context == null) {
                    throw new IllegalArgumentException("Context is required to resolve the file uri of the icon: " + g());
                }
                InputStream inputStreamI = i(context);
                if (inputStreamI == null) {
                    throw new IllegalStateException("Cannot load adaptive icon from uri: " + g());
                }
                if (Build.VERSION.SDK_INT < 26) {
                    iconCreateWithBitmap = Icon.createWithBitmap(a(BitmapFactory.decodeStream(inputStreamI), false));
                } else {
                    iconCreateWithBitmap = Icon.createWithAdaptiveBitmap(BitmapFactory.decodeStream(inputStreamI));
                }
                break;
                break;
        }
        ColorStateList colorStateList = this.f460h;
        if (colorStateList != null) {
            iconCreateWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.f461i;
        if (mode != f453a) {
            iconCreateWithBitmap.setTintMode(mode);
        }
        return iconCreateWithBitmap;
    }

    public String toString() {
        if (this.f454b == -1) {
            return String.valueOf(this.f455c);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(n(this.f454b));
        switch (this.f454b) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f455c).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f455c).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(e());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(c())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f458f);
                if (this.f459g != 0) {
                    sb.append(" off=");
                    sb.append(this.f459g);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f455c);
                break;
        }
        if (this.f460h != null) {
            sb.append(" tint=");
            sb.append(this.f460h);
        }
        if (this.f461i != f453a) {
            sb.append(" mode=");
            sb.append(this.f461i);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat(int i2) {
        this.f454b = -1;
        this.f456d = null;
        this.f457e = null;
        this.f458f = 0;
        this.f459g = 0;
        this.f460h = null;
        this.f461i = f453a;
        this.j = null;
        this.f454b = i2;
    }
}
