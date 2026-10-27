package defpackage;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintAttribute;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: KeyFrames.java */
/* JADX INFO: loaded from: classes.dex */
public class x4 {
    public static HashMap<String, Constructor<? extends s4>> a;
    public HashMap<Integer, ArrayList<s4>> b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends s4>> map = new HashMap<>();
        a = map;
        try {
            map.put("KeyAttribute", t4.class.getConstructor(new Class[0]));
            a.put("KeyPosition", y4.class.getConstructor(new Class[0]));
            a.put("KeyCycle", v4.class.getConstructor(new Class[0]));
            a.put("KeyTimeCycle", a5.class.getConstructor(new Class[0]));
            a.put("KeyTrigger", b5.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public x4(Context context, XmlPullParser xmlPullParser) {
        s4 s4VarNewInstance;
        Exception e;
        HashMap<String, ConstraintAttribute> map;
        s4 s4Var = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (a.containsKey(name)) {
                        try {
                            s4VarNewInstance = a.get(name).newInstance(new Object[0]);
                            try {
                                s4VarNewInstance.c(context, Xml.asAttributeSet(xmlPullParser));
                                b(s4VarNewInstance);
                            } catch (Exception e2) {
                                e = e2;
                                Log.e("KeyFrames", "unable to create ", e);
                            }
                        } catch (Exception e3) {
                            s4VarNewInstance = s4Var;
                            e = e3;
                        }
                        s4Var = s4VarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && s4Var != null && (map = s4Var.f) != null) {
                        ConstraintAttribute.g(context, xmlPullParser, map);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e4) {
            e4.printStackTrace();
        } catch (XmlPullParserException e5) {
            e5.printStackTrace();
        }
    }

    public void a(d5 d5Var) {
        ArrayList<s4> arrayList = this.b.get(Integer.valueOf(d5Var.b));
        if (arrayList != null) {
            d5Var.b(arrayList);
        }
        ArrayList<s4> arrayList2 = this.b.get(-1);
        if (arrayList2 != null) {
            for (s4 s4Var : arrayList2) {
                if (s4Var.d(((ConstraintLayout.b) d5Var.a.getLayoutParams()).V)) {
                    d5Var.a(s4Var);
                }
            }
        }
    }

    public final void b(s4 s4Var) {
        if (!this.b.containsKey(Integer.valueOf(s4Var.c))) {
            this.b.put(Integer.valueOf(s4Var.c), new ArrayList<>());
        }
        this.b.get(Integer.valueOf(s4Var.c)).add(s4Var);
    }
}
