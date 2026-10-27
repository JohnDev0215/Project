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
    public static final PorterDuff.Mode f1163a = PorterDuff.Mode.SRC_IN;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f1164b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public Object f1165c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f1166d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public Parcelable f1167e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1168f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public int f1169g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public ColorStateList f1170h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public PorterDuff.Mode f1171i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public String f1172j;

    public IconCompat() {
        this.f1164b = -1;
        this.f1166d = null;
        this.f1167e = null;
        this.f1168f = 0;
        this.f1169g = 0;
        this.f1170h = null;
        this.f1171i = f1163a;
        this.f1172j = null;
    }

    public static Bitmap a(Bitmap bitmap, boolean z3) {
        int iMin = (int) (Math.min(bitmap.getWidth(), bitmap.getHeight()) * 0.6666667f);
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iMin, iMin, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint(3);
        float f3 = iMin;
        float f4 = 0.5f * f3;
        float f5 = 0.9166667f * f4;
        if (z3) {
            float f6 = 0.010416667f * f3;
            paint.setColor(0);
            paint.setShadowLayer(f6, 0.0f, f3 * 0.020833334f, 1023410176);
            canvas.drawCircle(f4, f4, f5, paint);
            paint.setShadowLayer(f6, 0.0f, 0.0f, 503316480);
            canvas.drawCircle(f4, f4, f5, paint);
            paint.clearShadowLayer();
        }
        paint.setColor(-16777216);
        Shader.TileMode tileMode = Shader.TileMode.CLAMP;
        BitmapShader bitmapShader = new BitmapShader(bitmap, tileMode, tileMode);
        Matrix matrix = new Matrix();
        matrix.setTranslate((-(bitmap.getWidth() - iMin)) / 2, (-(bitmap.getHeight() - iMin)) / 2);
        bitmapShader.setLocalMatrix(matrix);
        paint.setShader(bitmapShader);
        canvas.drawCircle(f4, f4, f5, paint);
        canvas.setBitmap(null);
        return bitmapCreateBitmap;
    }

    public static IconCompat b(Resources resources, String str, int i3) {
        if (str == null) {
            throw new IllegalArgumentException("Package must not be null.");
        }
        if (i3 == 0) {
            throw new IllegalArgumentException("Drawable resource ID must not be 0");
        }
        IconCompat iconCompat = new IconCompat(2);
        iconCompat.f1168f = i3;
        if (resources != null) {
            try {
                iconCompat.f1165c = resources.getResourceName(i3);
            } catch (Resources.NotFoundException unused) {
                throw new IllegalArgumentException("Icon resource cannot be found");
            }
        } else {
            iconCompat.f1165c = str;
        }
        return iconCompat;
    }

    public static int d(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResId();
        }
        try {
            return ((Integer) icon.getClass().getMethod("getResId", new Class[0]).invoke(icon, new Object[0])).intValue();
        } catch (IllegalAccessException e3) {
            Log.e("IconCompat", "Unable to get icon resource", e3);
            return 0;
        } catch (NoSuchMethodException e4) {
            Log.e("IconCompat", "Unable to get icon resource", e4);
            return 0;
        } catch (InvocationTargetException e5) {
            Log.e("IconCompat", "Unable to get icon resource", e5);
            return 0;
        }
    }

    public static String f(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getResPackage();
        }
        try {
            return (String) icon.getClass().getMethod("getResPackage", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e3) {
            Log.e("IconCompat", "Unable to get icon package", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            Log.e("IconCompat", "Unable to get icon package", e4);
            return null;
        } catch (InvocationTargetException e5) {
            Log.e("IconCompat", "Unable to get icon package", e5);
            return null;
        }
    }

    public static Uri h(Icon icon) {
        if (Build.VERSION.SDK_INT >= 28) {
            return icon.getUri();
        }
        try {
            return (Uri) icon.getClass().getMethod("getUri", new Class[0]).invoke(icon, new Object[0]);
        } catch (IllegalAccessException e3) {
            Log.e("IconCompat", "Unable to get icon uri", e3);
            return null;
        } catch (NoSuchMethodException e4) {
            Log.e("IconCompat", "Unable to get icon uri", e4);
            return null;
        } catch (InvocationTargetException e5) {
            Log.e("IconCompat", "Unable to get icon uri", e5);
            return null;
        }
    }

    public static String n(int i3) {
        switch (i3) {
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
        int i3 = this.f1164b;
        if (i3 == -1 && Build.VERSION.SDK_INT >= 23) {
            return d((Icon) this.f1165c);
        }
        if (i3 == 2) {
            return this.f1168f;
        }
        throw new IllegalStateException("called getResId() on " + this);
    }

    public String e() {
        int i3 = this.f1164b;
        if (i3 == -1 && Build.VERSION.SDK_INT >= 23) {
            return f((Icon) this.f1165c);
        }
        if (i3 == 2) {
            return ((String) this.f1165c).split(":", -1)[0];
        }
        throw new IllegalStateException("called getResPackage() on " + this);
    }

    public Uri g() {
        int i3 = this.f1164b;
        if (i3 == -1 && Build.VERSION.SDK_INT >= 23) {
            return h((Icon) this.f1165c);
        }
        if (i3 == 4 || i3 == 6) {
            return Uri.parse((String) this.f1165c);
        }
        throw new IllegalStateException("called getUri() on " + this);
    }

    public final InputStream i(Context context) {
        Uri uriG = g();
        String scheme = uriG.getScheme();
        if ("content".equals(scheme) || "file".equals(scheme)) {
            try {
                return context.getContentResolver().openInputStream(uriG);
            } catch (Exception e3) {
                Log.w("IconCompat", "Unable to load image from URI: " + uriG, e3);
                return null;
            }
        }
        try {
            return new FileInputStream(new File((String) this.f1165c));
        } catch (FileNotFoundException e4) {
            Log.w("IconCompat", "Unable to load image from path: " + uriG, e4);
            return null;
        }
    }

    public void j() {
        this.f1171i = PorterDuff.Mode.valueOf(this.f1172j);
        switch (this.f1164b) {
            case -1:
                Parcelable parcelable = this.f1167e;
                if (parcelable == null) {
                    throw new IllegalArgumentException("Invalid icon");
                }
                this.f1165c = parcelable;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                Parcelable parcelable2 = this.f1167e;
                if (parcelable2 != null) {
                    this.f1165c = parcelable2;
                    return;
                }
                byte[] bArr = this.f1166d;
                this.f1165c = bArr;
                this.f1164b = 3;
                this.f1168f = 0;
                this.f1169g = bArr.length;
                return;
            case 2:
            case 4:
            case 6:
                this.f1165c = new String(this.f1166d, Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1165c = this.f1166d;
                return;
        }
    }

    public void k(boolean z3) {
        this.f1172j = this.f1171i.name();
        switch (this.f1164b) {
            case -1:
                if (z3) {
                    throw new IllegalArgumentException("Can't serialize Icon created with IconCompat#createFromIcon");
                }
                this.f1167e = (Parcelable) this.f1165c;
                return;
            case 0:
            default:
                return;
            case 1:
            case 5:
                if (!z3) {
                    this.f1167e = (Parcelable) this.f1165c;
                    return;
                }
                Bitmap bitmap = (Bitmap) this.f1165c;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 90, byteArrayOutputStream);
                this.f1166d = byteArrayOutputStream.toByteArray();
                return;
            case 2:
                this.f1166d = ((String) this.f1165c).getBytes(Charset.forName("UTF-16"));
                return;
            case 3:
                this.f1166d = (byte[]) this.f1165c;
                return;
            case 4:
            case 6:
                this.f1166d = this.f1165c.toString().getBytes(Charset.forName("UTF-16"));
                return;
        }
    }

    @Deprecated
    public Icon l() {
        return m(null);
    }

    public Icon m(Context context) {
        Icon iconCreateWithBitmap;
        switch (this.f1164b) {
            case -1:
                return (Icon) this.f1165c;
            case 0:
            default:
                throw new IllegalArgumentException("Unknown type");
            case 1:
                iconCreateWithBitmap = Icon.createWithBitmap((Bitmap) this.f1165c);
                break;
            case 2:
                iconCreateWithBitmap = Icon.createWithResource(e(), this.f1168f);
                break;
            case 3:
                iconCreateWithBitmap = Icon.createWithData((byte[]) this.f1165c, this.f1168f, this.f1169g);
                break;
            case 4:
                iconCreateWithBitmap = Icon.createWithContentUri((String) this.f1165c);
                break;
            case 5:
                iconCreateWithBitmap = Build.VERSION.SDK_INT < 26 ? Icon.createWithBitmap(a((Bitmap) this.f1165c, false)) : Icon.createWithAdaptiveBitmap((Bitmap) this.f1165c);
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
        ColorStateList colorStateList = this.f1170h;
        if (colorStateList != null) {
            iconCreateWithBitmap.setTintList(colorStateList);
        }
        PorterDuff.Mode mode = this.f1171i;
        if (mode != f1163a) {
            iconCreateWithBitmap.setTintMode(mode);
        }
        return iconCreateWithBitmap;
    }

    public String toString() {
        if (this.f1164b == -1) {
            return String.valueOf(this.f1165c);
        }
        StringBuilder sb = new StringBuilder("Icon(typ=");
        sb.append(n(this.f1164b));
        switch (this.f1164b) {
            case 1:
            case 5:
                sb.append(" size=");
                sb.append(((Bitmap) this.f1165c).getWidth());
                sb.append("x");
                sb.append(((Bitmap) this.f1165c).getHeight());
                break;
            case 2:
                sb.append(" pkg=");
                sb.append(e());
                sb.append(" id=");
                sb.append(String.format("0x%08x", Integer.valueOf(c())));
                break;
            case 3:
                sb.append(" len=");
                sb.append(this.f1168f);
                if (this.f1169g != 0) {
                    sb.append(" off=");
                    sb.append(this.f1169g);
                }
                break;
            case 4:
            case 6:
                sb.append(" uri=");
                sb.append(this.f1165c);
                break;
        }
        if (this.f1170h != null) {
            sb.append(" tint=");
            sb.append(this.f1170h);
        }
        if (this.f1171i != f1163a) {
            sb.append(" mode=");
            sb.append(this.f1171i);
        }
        sb.append(")");
        return sb.toString();
    }

    public IconCompat(int i3) {
        this.f1164b = -1;
        this.f1166d = null;
        this.f1167e = null;
        this.f1168f = 0;
        this.f1169g = 0;
        this.f1170h = null;
        this.f1171i = f1163a;
        this.f1172j = null;
        this.f1164b = i3;
    }
}
