package defpackage;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import androidx.constraintlayout.widget.R$styleable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

/* JADX INFO: compiled from: ConstraintAttribute.java */
/* JADX INFO: loaded from: classes.dex */
public final class ox {
    public final boolean a;
    public final String b;
    public final int c;
    public int d;
    public float e;
    public String f;
    public boolean g;
    public int h;

    public ox(String str, int i, Object obj, boolean z) {
        this.a = false;
        this.b = str;
        this.c = i;
        this.a = z;
        f(obj);
    }

    public static void d(Context context, XmlResourceParser xmlResourceParser, HashMap map) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), R$styleable.CustomAttribute);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        String string = null;
        int i = 0;
        boolean z = false;
        Object objValueOf = null;
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i2);
            if (index == R$styleable.CustomAttribute_attributeName) {
                string = typedArrayObtainStyledAttributes.getString(index);
                if (string != null && string.length() > 0) {
                    string = Character.toUpperCase(string.charAt(0)) + string.substring(1);
                }
            } else if (index == R$styleable.CustomAttribute_methodName) {
                string = typedArrayObtainStyledAttributes.getString(index);
                z = true;
            } else if (index == R$styleable.CustomAttribute_customBoolean) {
                objValueOf = Boolean.valueOf(typedArrayObtainStyledAttributes.getBoolean(index, false));
                i = 6;
            } else if (index == R$styleable.CustomAttribute_customColorValue) {
                objValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(index, 0));
                i = 3;
            } else if (index == R$styleable.CustomAttribute_customColorDrawableValue) {
                objValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(index, 0));
                i = 4;
            } else {
                if (index == R$styleable.CustomAttribute_customPixelDimension) {
                    objValueOf = Float.valueOf(TypedValue.applyDimension(1, typedArrayObtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics()));
                } else if (index == R$styleable.CustomAttribute_customDimension) {
                    objValueOf = Float.valueOf(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                } else if (index == R$styleable.CustomAttribute_customFloatValue) {
                    objValueOf = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, Float.NaN));
                    i = 2;
                } else if (index == R$styleable.CustomAttribute_customIntegerValue) {
                    objValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getInteger(index, -1));
                    i = 1;
                } else if (index == R$styleable.CustomAttribute_customStringValue) {
                    objValueOf = typedArrayObtainStyledAttributes.getString(index);
                    i = 5;
                } else if (index == R$styleable.CustomAttribute_customReference) {
                    int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                    if (resourceId == -1) {
                        resourceId = typedArrayObtainStyledAttributes.getInt(index, -1);
                    }
                    objValueOf = Integer.valueOf(resourceId);
                    i = 8;
                }
                i = 7;
            }
        }
        if (string != null && objValueOf != null) {
            map.put(string, new ox(string, i, objValueOf, z));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void e(View view, HashMap<String, ox> map) {
        Class<?> cls = view.getClass();
        for (String str : map.keySet()) {
            ox oxVar = map.get(str);
            String strJ = !oxVar.a ? C0175b.j("set", str) : str;
            try {
                switch (gq2.m(oxVar.c)) {
                    case 0:
                        cls.getMethod(strJ, Integer.TYPE).invoke(view, Integer.valueOf(oxVar.d));
                        break;
                    case 1:
                        cls.getMethod(strJ, Float.TYPE).invoke(view, Float.valueOf(oxVar.e));
                        break;
                    case 2:
                        cls.getMethod(strJ, Integer.TYPE).invoke(view, Integer.valueOf(oxVar.h));
                        break;
                    case 3:
                        Method method = cls.getMethod(strJ, Drawable.class);
                        ColorDrawable colorDrawable = new ColorDrawable();
                        colorDrawable.setColor(oxVar.h);
                        method.invoke(view, colorDrawable);
                        break;
                    case 4:
                        cls.getMethod(strJ, CharSequence.class).invoke(view, oxVar.f);
                        break;
                    case 5:
                        cls.getMethod(strJ, Boolean.TYPE).invoke(view, Boolean.valueOf(oxVar.g));
                        break;
                    case 6:
                        cls.getMethod(strJ, Float.TYPE).invoke(view, Float.valueOf(oxVar.e));
                        break;
                    case 7:
                        cls.getMethod(strJ, Integer.TYPE).invoke(view, Integer.valueOf(oxVar.d));
                        break;
                }
            } catch (IllegalAccessException e) {
                StringBuilder sbN = C0175b.n(" Custom Attribute \"", str, "\" not found on ");
                sbN.append(cls.getName());
                Log.e("TransitionLayout", sbN.toString(), e);
            } catch (NoSuchMethodException e2) {
                Log.e("TransitionLayout", cls.getName() + " must have a method " + strJ, e2);
            } catch (InvocationTargetException e3) {
                StringBuilder sbN2 = C0175b.n(" Custom Attribute \"", str, "\" not found on ");
                sbN2.append(cls.getName());
                Log.e("TransitionLayout", sbN2.toString(), e3);
            }
        }
    }

    public final float a() {
        switch (gq2.m(this.c)) {
            case 0:
                return this.d;
            case 1:
            case 6:
                return this.e;
            case 2:
            case 3:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 4:
                throw new RuntimeException("Cannot interpolate String");
            case 5:
                return this.g ? 1.0f : 0.0f;
            default:
                return Float.NaN;
        }
    }

    public final void b(float[] fArr) {
        switch (gq2.m(this.c)) {
            case 0:
                fArr[0] = this.d;
                return;
            case 1:
                fArr[0] = this.e;
                return;
            case 2:
            case 3:
                int i = (this.h >> 24) & 255;
                float fPow = (float) Math.pow(((r9 >> 16) & 255) / 255.0f, 2.2d);
                float fPow2 = (float) Math.pow(((r9 >> 8) & 255) / 255.0f, 2.2d);
                float fPow3 = (float) Math.pow((r9 & 255) / 255.0f, 2.2d);
                fArr[0] = fPow;
                fArr[1] = fPow2;
                fArr[2] = fPow3;
                fArr[3] = i / 255.0f;
                return;
            case 4:
                throw new RuntimeException("Color does not have a single color to interpolate");
            case 5:
                fArr[0] = this.g ? 1.0f : 0.0f;
                return;
            case 6:
                fArr[0] = this.e;
                return;
            default:
                return;
        }
    }

    public final int c() {
        int iM = gq2.m(this.c);
        return (iM == 2 || iM == 3) ? 4 : 1;
    }

    public final void f(Object obj) {
        switch (gq2.m(this.c)) {
            case 0:
            case 7:
                this.d = ((Integer) obj).intValue();
                break;
            case 1:
                this.e = ((Float) obj).floatValue();
                break;
            case 2:
            case 3:
                this.h = ((Integer) obj).intValue();
                break;
            case 4:
                this.f = (String) obj;
                break;
            case 5:
                this.g = ((Boolean) obj).booleanValue();
                break;
            case 6:
                this.e = ((Float) obj).floatValue();
                break;
        }
    }

    public ox(ox oxVar, Object obj) {
        this.a = false;
        this.b = oxVar.b;
        this.c = oxVar.c;
        f(obj);
    }
}
