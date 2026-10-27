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
    public static final PorterDuff.Mode f330a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f331b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f332c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f333d;
    public Parcelable e;
    public int f;
    public int g;
    public ColorStateList h;
    public PorterDuff.Mode i;
    public String j;

    public IconCompat() {
        this.f331b = -1;
        this.f333d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.i = f330a;
        this.j = null;
    }

    public static Bitmap a(Bitmap bitmap, boolean z) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f = iMin;
        float f2 = 0.5f * f;
        float f3 = 0.9166667f * f2;
        if (z) {
            float f4 = 0.010416667f * f;
            paint.setColor(0);
            paint.setShadowLayer(f4, 0.0f, f * 0.020833334f, 1023410176);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.setShadowLayer(f4, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f2, f2, f3, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2, (-(bitmap.getHeight() - iMin)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f2, f2, f3, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    public static IconCompat b(Resources resources, String str, int i) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f = i;
        if (resources != null) {
            try {
                iconCompat.f332c = resources.getResourceName(i);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f332c = str;
        }
        return iconCompat;
    }

    public static int d(Icon icon) {
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

    public static String f(Icon icon) {
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

    public static Uri h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e) {
            Log.e("IconCompat", "Unable to get icon uri", e);
            return null;
        } catch (NoSuchMethodException e2) {
            Log.e("IconCompat", "Unable to get icon uri", e2);
            return null;
        } catch (InvocationTargetException e3) {
            Log.e("IconCompat", "Unable to get icon uri", e3);
            return null;
        }
    }

    public static String n(int i) {
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

    public int c() {
        int i = this.f331b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return d((Icon) this.f332c);
        }
        if (i == 2) {
            return this.f;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String e() {
        int i = this.f331b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return f((Icon) this.f332c);
        }
        if (i == 2) {
            return ((String) this.f332c).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public Uri g() {
        int i = this.f331b;
        if (i == -1 && Build.VERSION.SDK_INT >= 23) {
            return h((Icon) this.f332c);
        }
        if (i == 4 || i == 6) {
            return Uri.parse((String) this.f332c);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public final InputStream i(Context context) {
        Uri uriG = g();
        String scheme = uriG.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriG);
            } catch (Exception e) {
                Log.w("IconCompat", "Unable to load image from URI: " + uriG, e);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.f332c));
        } catch (FileNotFoundException e2) {
            Log.w("IconCompat", "Unable to load image from path: " + uriG, e2);
            return null;
        }
    }

    public void j() {
        this.i = PorterDuff.Mode.valueOf(this.j);
        switch (this.f331b) {
            case -1:
                Parcelable parcelable = this.e;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f332c = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.e;
                if (parcelable2 != null) {
                    this.f332c = parcelable2;
                    return;
                }
                byte[] bArr = this.f333d;
                this.f332c = bArr;
                this.f331b = 3;
                this.f = 0;
                this.g = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                this.f332c = new String(this.f333d, Charset.forName("UTF-16"));
                return;
            case 3:
                this.f332c = this.f333d;
                return;
        }
    }

    public void k(boolean z) {
        this.j = this.i.name();
        switch (this.f331b) {
            case -1:
                if (z) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.e = (Parcelable) this.f332c;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z) {
                    this.e = (Parcelable) this.f332c;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f332c;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f333d = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f333d = ((String) this.f332c).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f333d = (byte[]) this.f332c;
                return;
            case 4:
            case 6:
                this.f333d = this.f332c.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    @Deprecated
    public Icon l() {
        return m(null);
    }

    public Icon m(Context context) {
        Icon iconCreateWithBitmap;
        switch (this.f331b) {
            case -1:
                return (Icon) this.f332c;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                iconCreateWithBitmap = Icon.createWithBitmap((Bitmap) this.f332c);
                break;
            case 2:
                iconCreateWithBitmap = Icon.createWithResource(e(), this.f);
                break;
            case 3:
                iconCreateWithBitmap = Icon.createWithData((byte[]) this.f332c, this.f, this.g);
                break;
            case 4:
                iconCreateWithBitmap = Icon.createWithContentUri((String) this.f332c);
                break;
            case 5:
                iconCreateWithBitmap = Build.VERSION.SDK_INT < 26 ? Icon.createWithBitmap(a((Bitmap) this.f332c, false)) : Icon.createWithAdaptiveBitmap((Bitmap) this.f332c);
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
        ColorStateList colorStateList = this.h;
        if (colorStateList != null) {
            iconCreateWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.i;
        if (mode != f330a) {
            iconCreateWithBitmap.setTintMode(mode);
        }
        return iconCreateWithBitmap;
    }

    public String toString() {
        if (this.f331b == -1) {
            return String.valueOf(this.f332c);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(n(this.f331b));
        switch (this.f331b) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f332c).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f332c).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(e());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(c())));
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
                sb.append(this.f332c);
                break;
        }
        if (this.h != null) {
            sb.append(" tint=");
            sb.append(this.h);
        }
        if (this.i != f330a) {
            sb.append(" mode=");
            sb.append(this.i);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat(int i) {
        this.f331b = -1;
        this.f333d = null;
        this.e = null;
        this.f = 0;
        this.g = 0;
        this.h = null;
        this.i = f330a;
        this.j = null;
        this.f331b = i;
    }
}
