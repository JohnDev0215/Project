package defpackage;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class L0 {
    public final Context a;
    public final TypedArray b;
    public TypedValue c;

    public L0(Context context, TypedArray typedArray) {
        this.a = context;
        this.b = typedArray;
    }

    public static L0 l(Context context, AttributeSet attributeSet, int[] iArr, int i, int i2) {
        return new L0(context, context.obtainStyledAttributes(attributeSet, iArr, i, i2));
    }

    public boolean a(int i, boolean z) {
        return this.b.getBoolean(i, z);
    }

    public int b(int i, int i2) {
        return this.b.getColor(i, i2);
    }

    public ColorStateList c(int i) {
        int resourceId;
        ColorStateList colorStateListA;
        return (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0 || (colorStateListA = AbstractC1631v.a(this.a, resourceId)) == null) ? this.b.getColorStateList(i) : colorStateListA;
    }

    public int d(int i, int i2) {
        return this.b.getDimensionPixelOffset(i, i2);
    }

    public int e(int i, int i2) {
        return this.b.getDimensionPixelSize(i, i2);
    }

    public Drawable f(int i) {
        int resourceId;
        return (!this.b.hasValue(i) || (resourceId = this.b.getResourceId(i, 0)) == 0) ? this.b.getDrawable(i) : AbstractC1631v.b(this.a, resourceId);
    }

    public Typeface g(int i, int i2, AbstractC1140m2 abstractC1140m2) {
        int resourceId = this.b.getResourceId(i, 0);
        if (resourceId == 0) {
            return null;
        }
        if (this.c == null) {
            this.c = new TypedValue();
        }
        Context context = this.a;
        TypedValue typedValue = this.c;
        if (context.isRestricted()) {
            return null;
        }
        Resources resources = context.getResources();
        resources.getValue(resourceId, typedValue, true);
        CharSequence charSequence = typedValue.string;
        if (charSequence == null) {
            StringBuilder sbF = Z7.f("Resource \"");
            sbF.append(resources.getResourceName(resourceId));
            sbF.append("\" (");
            sbF.append(Integer.toHexString(resourceId));
            sbF.append(") is not a Font: ");
            sbF.append(typedValue);
            throw new Resources.NotFoundException(sbF.toString());
        }
        String string = charSequence.toString();
        if (!string.startsWith("res/")) {
            abstractC1140m2.a(-3, null);
            return null;
        }
        Typeface typeface = (Typeface) AbstractC1414r2.b.a(AbstractC1414r2.c(resources, resourceId, i2));
        if (typeface != null) {
            abstractC1140m2.b(typeface, null);
            return typeface;
        }
        try {
            if (!string.toLowerCase().endsWith(".xml")) {
                Typeface typefaceB = AbstractC1414r2.b(context, resources, resourceId, string, i2);
                if (typefaceB != null) {
                    abstractC1140m2.b(typefaceB, null);
                } else {
                    abstractC1140m2.a(-3, null);
                }
                return typefaceB;
            }
            InterfaceC0693e2 interfaceC0693e2A = AbstractC0917i2.a(resources.getXml(resourceId), resources);
            if (interfaceC0693e2A != null) {
                return AbstractC1414r2.a(context, interfaceC0693e2A, resources, resourceId, i2, abstractC1140m2, null, true);
            }
            Log.e("ResourcesCompat", "Failed to find font-family tag");
            abstractC1140m2.a(-3, null);
            return null;
        } catch (IOException e) {
            Log.e("ResourcesCompat", "Failed to read xml resource " + string, e);
            abstractC1140m2.a(-3, null);
            return null;
        } catch (XmlPullParserException e2) {
            Log.e("ResourcesCompat", "Failed to parse xml resource " + string, e2);
            abstractC1140m2.a(-3, null);
            return null;
        }
    }

    public int h(int i, int i2) {
        return this.b.getInt(i, i2);
    }

    public int i(int i, int i2) {
        return this.b.getResourceId(i, i2);
    }

    public CharSequence j(int i) {
        return this.b.getText(i);
    }

    public boolean k(int i) {
        return this.b.hasValue(i);
    }
}
