package p;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.TypedValue;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute$AttributeType;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public final class yx {
    public boolean a = false;
    public ConstraintAttribute$AttributeType b;
    public int c;
    public float d;
    public String e;
    public boolean f;
    public int g;

    public yx(yx yxVar, Object obj) {
        yxVar.getClass();
        this.b = yxVar.b;
        b(obj);
    }

    public static void a(Context context, XmlResourceParser xmlResourceParser, HashMap map) {
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType;
        Object objValueOf;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlResourceParser), sz1.d);
        int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
        String string = null;
        Object objValueOf2 = null;
        ConstraintAttribute$AttributeType constraintAttribute$AttributeType2 = null;
        boolean z = false;
        for (int i = 0; i < indexCount; i++) {
            int index = typedArrayObtainStyledAttributes.getIndex(i);
            if (index == 0) {
                string = typedArrayObtainStyledAttributes.getString(index);
                if (string != null && string.length() > 0) {
                    string = Character.toUpperCase(string.charAt(0)) + string.substring(1);
                }
            } else if (index == 10) {
                string = typedArrayObtainStyledAttributes.getString(index);
                z = true;
            } else if (index == 1) {
                objValueOf2 = Boolean.valueOf(typedArrayObtainStyledAttributes.getBoolean(index, false));
                constraintAttribute$AttributeType2 = ConstraintAttribute$AttributeType.n;
            } else {
                if (index == 3) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.k;
                    objValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(index, 0));
                } else if (index == 2) {
                    constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.l;
                    objValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(index, 0));
                } else {
                    ConstraintAttribute$AttributeType constraintAttribute$AttributeType3 = ConstraintAttribute$AttributeType.o;
                    if (index == 7) {
                        objValueOf2 = Float.valueOf(TypedValue.applyDimension(1, typedArrayObtainStyledAttributes.getDimension(index, 0.0f), context.getResources().getDisplayMetrics()));
                    } else if (index == 4) {
                        objValueOf2 = Float.valueOf(typedArrayObtainStyledAttributes.getDimension(index, 0.0f));
                    } else if (index == 5) {
                        constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.e;
                        objValueOf = Float.valueOf(typedArrayObtainStyledAttributes.getFloat(index, Float.NaN));
                    } else if (index == 6) {
                        constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.c;
                        objValueOf = Integer.valueOf(typedArrayObtainStyledAttributes.getInteger(index, -1));
                    } else if (index == 9) {
                        constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.m;
                        objValueOf = typedArrayObtainStyledAttributes.getString(index);
                    } else if (index == 8) {
                        constraintAttribute$AttributeType = ConstraintAttribute$AttributeType.f15p;
                        int resourceId = typedArrayObtainStyledAttributes.getResourceId(index, -1);
                        if (resourceId == -1) {
                            resourceId = typedArrayObtainStyledAttributes.getInt(index, -1);
                        }
                        objValueOf = Integer.valueOf(resourceId);
                    }
                    constraintAttribute$AttributeType2 = constraintAttribute$AttributeType3;
                }
                Object obj = objValueOf;
                constraintAttribute$AttributeType2 = constraintAttribute$AttributeType;
                objValueOf2 = obj;
            }
        }
        if (string != null && objValueOf2 != null) {
            yx yxVar = new yx();
            yxVar.b = constraintAttribute$AttributeType2;
            yxVar.a = z;
            yxVar.b(objValueOf2);
            map.put(string, yxVar);
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public final void b(Object obj) {
        switch (this.b.ordinal()) {
            case 0:
            case 7:
                this.c = ((Integer) obj).intValue();
                break;
            case 1:
                this.d = ((Float) obj).floatValue();
                break;
            case 2:
            case 3:
                this.g = ((Integer) obj).intValue();
                break;
            case 4:
                this.e = (String) obj;
                break;
            case 5:
                this.f = ((Boolean) obj).booleanValue();
                break;
            case 6:
                this.d = ((Float) obj).floatValue();
                break;
        }
    }
}
