package androidx.constraintlayout.widget;

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
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    String f1094a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private EnumC0034b f1095b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f1096c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private float f1097d;
    private String e;
    boolean f;
    private int g;

    static /* synthetic */ class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        static final /* synthetic */ int[] f1098a;

        static {
            int[] iArr = new int[EnumC0034b.values().length];
            f1098a = iArr;
            try {
                iArr[EnumC0034b.COLOR_TYPE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                f1098a[EnumC0034b.COLOR_DRAWABLE_TYPE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                f1098a[EnumC0034b.INT_TYPE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                f1098a[EnumC0034b.FLOAT_TYPE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                f1098a[EnumC0034b.STRING_TYPE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                f1098a[EnumC0034b.BOOLEAN_TYPE.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                f1098a[EnumC0034b.DIMENSION_TYPE.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
        }
    }

    /* JADX INFO: renamed from: androidx.constraintlayout.widget.b$b, reason: collision with other inner class name */
    public enum EnumC0034b {
        INT_TYPE,
        FLOAT_TYPE,
        COLOR_TYPE,
        COLOR_DRAWABLE_TYPE,
        STRING_TYPE,
        BOOLEAN_TYPE,
        DIMENSION_TYPE
    }

    public b(b bVar, Object obj) {
        this.f1094a = bVar.f1094a;
        this.f1095b = bVar.f1095b;
        d(obj);
    }

    public b(String str, EnumC0034b enumC0034b, Object obj) {
        this.f1094a = str;
        this.f1095b = enumC0034b;
        d(obj);
    }

    public static HashMap<String, b> a(HashMap<String, b> map, View view) {
        HashMap<String, b> map2 = new HashMap<>();
        Class<?> cls = view.getClass();
        for (String str : map.keySet()) {
            b bVar = map.get(str);
            try {
                map2.put(str, str.equals("BackgroundColor") ? new b(bVar, Integer.valueOf(((ColorDrawable) view.getBackground()).getColor())) : new b(bVar, cls.getMethod("getMap" + str, new Class[0]).invoke(view, new Object[0])));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                e2.printStackTrace();
            } catch (InvocationTargetException e3) {
                e3.printStackTrace();
            }
        }
        return map2;
    }

    public static void b(Context context, XmlPullParser xmlPullParser, HashMap<String, b> map) {
        EnumC0034b enumC0034b;
        Object string;
        int integer;
        float dimension;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), j.B2);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        String string2 = null;
        Object objValueOf = null;
        EnumC0034b enumC0034b2 = null;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == j.C2) {
                string2 = typedArrayObtainStyledAttributes.getString(index);
                if (string2 != null && string2.length() > 0) {
                    string2 = Character.toUpperCase(string2.charAt(0)) + string2.substring(1);
                }
            } else if (index == j.D2) {
                objValueOf = Boolean.valueOf(typedArrayObtainStyledAttributes.getBoolean(index, false));
                enumC0034b2 = EnumC0034b.BOOLEAN_TYPE;
            } else {
                if (index == j.F2) {
                    enumC0034b = EnumC0034b.COLOR_TYPE;
                } else if (index == j.E2) {
                    enumC0034b = EnumC0034b.COLOR_DRAWABLE_TYPE;
                } else {
                    if (index == j.J2) {
                        enumC0034b = EnumC0034b.DIMENSION_TYPE;
                        dimension = TypedValue.applyDimension(1, typedArrayObtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics());
                    } else if (index == j.G2) {
                        enumC0034b = EnumC0034b.DIMENSION_TYPE;
                        dimension = typedArrayObtainStyledAttributes.getDimension(index, 0.0f);
                    } else if (index == j.H2) {
                        enumC0034b = EnumC0034b.FLOAT_TYPE;
                        dimension = typedArrayObtainStyledAttributes.getFloat(index, Float.NaN);
                    } else if (index == j.I2) {
                        enumC0034b = EnumC0034b.INT_TYPE;
                        integer = typedArrayObtainStyledAttributes.getInteger(index, -1);
                        string = Integer.valueOf(integer);
                        Object obj = string;
                        enumC0034b2 = enumC0034b;
                        objValueOf = obj;
                    } else if (index == j.K2) {
                        enumC0034b = EnumC0034b.STRING_TYPE;
                        string = typedArrayObtainStyledAttributes.getString(index);
                        Object obj2 = string;
                        enumC0034b2 = enumC0034b;
                        objValueOf = obj2;
                    }
                    string = Float.valueOf(dimension);
                    Object obj22 = string;
                    enumC0034b2 = enumC0034b;
                    objValueOf = obj22;
                }
                integer = typedArrayObtainStyledAttributes.getColor(index, 0);
                string = Integer.valueOf(integer);
                Object obj222 = string;
                enumC0034b2 = enumC0034b;
                objValueOf = obj222;
            }
        }
        if (string2 != null && objValueOf != null) {
            map.put(string2, new b(string2, enumC0034b2, objValueOf));
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static void c(View view, HashMap<String, b> map) {
        Class<?> cls = view.getClass();
        for (String str : map.keySet()) {
            b bVar = map.get(str);
            String str2 = "set" + str;
            try {
                switch (a.f1098a[bVar.f1095b.ordinal()]) {
                    case 1:
                        cls.getMethod(str2, Integer.TYPE).invoke(view, Integer.valueOf(bVar.g));
                        break;
                    case 2:
                        Method method = cls.getMethod(str2, Drawable.class);
                        ColorDrawable colorDrawable = new ColorDrawable();
                        colorDrawable.setColor(bVar.g);
                        method.invoke(view, colorDrawable);
                        break;
                    case 3:
                        cls.getMethod(str2, Integer.TYPE).invoke(view, Integer.valueOf(bVar.f1096c));
                        break;
                    case 4:
                        cls.getMethod(str2, Float.TYPE).invoke(view, Float.valueOf(bVar.f1097d));
                        break;
                    case 5:
                        cls.getMethod(str2, CharSequence.class).invoke(view, bVar.e);
                        break;
                    case 6:
                        cls.getMethod(str2, Boolean.TYPE).invoke(view, Boolean.valueOf(bVar.f));
                        break;
                    case 7:
                        cls.getMethod(str2, Float.TYPE).invoke(view, Float.valueOf(bVar.f1097d));
                        break;
                }
            } catch (IllegalAccessException e) {
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e.printStackTrace();
            } catch (NoSuchMethodException e2) {
                Log.e("TransitionLayout", e2.getMessage());
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                StringBuilder sb = new StringBuilder();
                sb.append(cls.getName());
                sb.append(" must have a method ");
                sb.append(str2);
                Log.e("TransitionLayout", sb.toString());
            } catch (InvocationTargetException e3) {
                Log.e("TransitionLayout", " Custom Attribute \"" + str + "\" not found on " + cls.getName());
                e3.printStackTrace();
            }
        }
    }

    public void d(Object obj) {
        switch (a.f1098a[this.f1095b.ordinal()]) {
            case 1:
            case 2:
                this.g = ((Integer) obj).intValue();
                break;
            case 3:
                this.f1096c = ((Integer) obj).intValue();
                break;
            case 4:
            case 7:
                this.f1097d = ((Float) obj).floatValue();
                break;
            case 5:
                this.e = (String) obj;
                break;
            case 6:
                this.f = ((Boolean) obj).booleanValue();
                break;
        }
    }
}
