package a.f.b;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: loaded from: classes.dex */
public class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f617a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public String f618b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public EnumC0008a f619c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f620d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f621e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public String f622f;
    public boolean g;
    public int h;

    /* JADX INFO: renamed from: a.f.b.a$a, reason: collision with other inner class name */
    public enum EnumC0008a {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE,
        REFERENCE_TYPE
    }

    public a(a aVar, Object obj) {
        this.f617a = false;
        this.f618b = aVar.f618b;
        this.f619c = aVar.f619c;
        c(obj);
    }

    public a(String str, EnumC0008a enumC0008a, Object obj, boolean z) {
        this.f617a = false;
        this.f618b = str;
        this.f619c = enumC0008a;
        this.f617a = z;
        c(obj);
    }

    public static void a(Context context, XmlPullParser xmlPullParser, HashMap<String, a> map) {
        EnumC0008a enumC0008a;
        int resourceId;
        Object string;
        float dimension;
        EnumC0008a enumC0008a2 = EnumC0008a.DIMENSION_TYPE;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), i.CustomAttribute);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        String string2 = null;
        Object objValueOf = null;
        EnumC0008a enumC0008a3 = null;
        boolean z = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == i.CustomAttribute_attributeName) {
                string2 = typedArrayObtainStyledAttributes.getString(index);
                if (string2 != null && string2.length() > 0) {
                    string2 = Character.toUpperCase(string2.charAt(0)) + string2.substring(1);
                }
            } else if (index == i.CustomAttribute_methodName) {
                string2 = typedArrayObtainStyledAttributes.getString(index);
                z = true;
            } else if (index == i.CustomAttribute_customBoolean) {
                objValueOf = Boolean.valueOf(typedArrayObtainStyledAttributes.getBoolean(index, false));
                enumC0008a3 = EnumC0008a.BOOLEAN_TYPE;
            } else {
                if (index == i.CustomAttribute_customColorValue) {
                    enumC0008a = EnumC0008a.COLOR_TYPE;
                } else if (index == i.CustomAttribute_customColorDrawableValue) {
                    enumC0008a = EnumC0008a.COLOR_DRAWABLE_TYPE;
                } else {
                    if (index == i.CustomAttribute_customPixelDimension) {
                        dimension = TypedValue.applyDimension(1, typedArrayObtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics());
                    } else if (index == i.CustomAttribute_customDimension) {
                        dimension = typedArrayObtainStyledAttributes.getDimension(index, 0.0f);
                    } else {
                        if (index == i.CustomAttribute_customFloatValue) {
                            enumC0008a = EnumC0008a.FLOAT_TYPE;
                            string = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, Float.NaN));
                        } else {
                            if (index == i.CustomAttribute_customIntegerValue) {
                                enumC0008a = EnumC0008a.INT_TYPE;
                                resourceId = typedArrayObtainStyledAttributes.getInteger(index, -1);
                            } else if (index == i.CustomAttribute_customStringValue) {
                                enumC0008a = EnumC0008a.STRING_TYPE;
                                string = typedArrayObtainStyledAttributes.getString(index);
                            } else if (index == i.CustomAttribute_customReference) {
                                enumC0008a = EnumC0008a.REFERENCE_TYPE;
                                resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                                if (resourceId == -1) {
                                    resourceId = typedArrayObtainStyledAttributes.getInt(index, -1);
                                }
                            }
                            string = Integer.valueOf(resourceId);
                        }
                        Object obj = string;
                        enumC0008a3 = enumC0008a;
                        objValueOf = obj;
                    }
                    objValueOf = Float.valueOf(dimension);
                    enumC0008a3 = enumC0008a2;
                }
                resourceId = typedArrayObtainStyledAttributes.getColor(index, 0);
                string = Integer.valueOf(resourceId);
                Object obj2 = string;
                enumC0008a3 = enumC0008a;
                objValueOf = obj2;
            }
        }
        if (string2 != null && objValueOf != null) {
            map.put(string2, new a(string2, enumC0008a3, objValueOf, z));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void b(View view, HashMap<String, a> map) {
        Class<?> cls = view.getClass();
        for (String str : map.keySet()) {
            a aVar = map.get(str);
            String strC = !aVar.f617a ? b.a.a.a.a.c("set", str) : str;
            try {
                switch (aVar.f619c) {
                    case INT_TYPE:
                        cls.getMethod(strC, Integer.TYPE).invoke(view, Integer.valueOf(aVar.f620d));
                        break;
                    case FLOAT_TYPE:
                        cls.getMethod(strC, Float.TYPE).invoke(view, Float.valueOf(aVar.f621e));
                        break;
                    case COLOR_TYPE:
                        cls.getMethod(strC, Integer.TYPE).invoke(view, Integer.valueOf(aVar.h));
                        break;
                    case COLOR_DRAWABLE_TYPE:
                        Method method = cls.getMethod(strC, Drawable.class);
                        ColorDrawable colorDrawable = new ColorDrawable();
                        colorDrawable.setColor(aVar.h);
                        method.invoke(view, colorDrawable);
                        break;
                    case STRING_TYPE:
                        cls.getMethod(strC, CharSequence.class).invoke(view, aVar.f622f);
                        break;
                    case BOOLEAN_TYPE:
                        cls.getMethod(strC, Boolean.TYPE).invoke(view, Boolean.valueOf(aVar.g));
                        break;
                    case DIMENSION_TYPE:
                        cls.getMethod(strC, Float.TYPE).invoke(view, Float.valueOf(aVar.f621e));
                        break;
                    case REFERENCE_TYPE:
                        cls.getMethod(strC, Integer.TYPE).invoke(view, Integer.valueOf(aVar.f620d));
                        break;
                }
            } catch (IllegalAccessException e2) {
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e2.printStackTrace();
            } catch (NoSuchMethodException e3) {
                Log.e("TransitionLayout", e3.getMessage());
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getName());
                sb.append(" must have a method ");
                sb.append(strC);
                Log.e("TransitionLayout", sb.toString());
            } catch (InvocationTargetException e4) {
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e4.printStackTrace();
            }
        }
    }

    public void c(Object obj) {
        switch (this.f619c) {
            case INT_TYPE:
            case REFERENCE_TYPE:
                this.f620d = ((Integer) obj).intValue();
                break;
            case FLOAT_TYPE:
            case DIMENSION_TYPE:
                this.f621e = ((Float) obj).floatValue();
                break;
            case COLOR_TYPE:
            case COLOR_DRAWABLE_TYPE:
                this.h = ((Integer) obj).intValue();
                break;
            case STRING_TYPE:
                this.f622f = (String) obj;
                break;
            case BOOLEAN_TYPE:
                this.g = ((Boolean) obj).booleanValue();
                break;
        }
    }
}
