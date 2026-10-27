package defpackage;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: KeyFrames.java */
/* JADX INFO: loaded from: classes.dex */
public final class f41 {
    public static final HashMap<String, Constructor<? extends y31>> b;
    public final HashMap<Integer, ArrayList<y31>> a = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends y31>> map = new HashMap<>();
        b = map;
        try {
            map.put("KeyAttribute", z31.class.getConstructor(new Class[0]));
            map.put("KeyPosition", g41.class.getConstructor(new Class[0]));
            map.put("KeyCycle", b41.class.getConstructor(new Class[0]));
            map.put("KeyTimeCycle", j41.class.getConstructor(new Class[0]));
            map.put("KeyTrigger", k41.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public f41() {
    }

    public final void a(bj1 bj1Var) {
        Integer numValueOf = Integer.valueOf(bj1Var.c);
        HashMap<Integer, ArrayList<y31>> map = this.a;
        ArrayList<y31> arrayList = map.get(numValueOf);
        if (arrayList != null) {
            bj1Var.w.addAll(arrayList);
        }
        ArrayList<y31> arrayList2 = map.get(-1);
        if (arrayList2 != null) {
            for (y31 y31Var : arrayList2) {
                String str = ((ConstraintLayout.LayoutParams) bj1Var.b.getLayoutParams()).Y;
                String str2 = y31Var.c;
                if ((str2 == null || str == null) ? false : str.matches(str2)) {
                    bj1Var.a(y31Var);
                }
            }
        }
    }

    public final void b(y31 y31Var) {
        Integer numValueOf = Integer.valueOf(y31Var.b);
        HashMap<Integer, ArrayList<y31>> map = this.a;
        if (!map.containsKey(numValueOf)) {
            map.put(Integer.valueOf(y31Var.b), new ArrayList<>());
        }
        ArrayList<y31> arrayList = map.get(Integer.valueOf(y31Var.b));
        if (arrayList != null) {
            arrayList.add(y31Var);
        }
    }

    public f41(Context context, XmlResourceParser xmlResourceParser) {
        Exception e;
        y31 y31VarNewInstance;
        Constructor<? extends y31> constructor;
        HashMap<String, ox> map;
        HashMap<String, ox> map2;
        try {
            int eventType = xmlResourceParser.getEventType();
            y31 y31Var = null;
            while (eventType != 1) {
                if (eventType != 2) {
                    if (eventType == 3 && "KeyFrameSet".equals(xmlResourceParser.getName())) {
                        return;
                    }
                } else {
                    String name = xmlResourceParser.getName();
                    HashMap<String, Constructor<? extends y31>> map3 = b;
                    if (map3.containsKey(name)) {
                        try {
                            constructor = map3.get(name);
                        } catch (Exception e2) {
                            y31 y31Var2 = y31Var;
                            e = e2;
                            y31VarNewInstance = y31Var2;
                        }
                        if (constructor != null) {
                            y31VarNewInstance = constructor.newInstance(new Object[0]);
                            try {
                                y31VarNewInstance.e(context, Xml.asAttributeSet(xmlResourceParser));
                                b(y31VarNewInstance);
                            } catch (Exception e3) {
                                e = e3;
                                Log.e("KeyFrames", "unable to create ", e);
                            }
                            y31Var = y31VarNewInstance;
                        } else {
                            throw new NullPointerException("Keymaker for " + name + " not found");
                        }
                        Log.e("KeyFrames", "unable to create ", e);
                        y31Var = y31VarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute")) {
                        if (y31Var != null && (map2 = y31Var.d) != null) {
                            ox.d(context, xmlResourceParser, map2);
                        }
                    } else if (name.equalsIgnoreCase("CustomMethod") && y31Var != null && (map = y31Var.d) != null) {
                        ox.d(context, xmlResourceParser, map);
                    }
                }
                eventType = xmlResourceParser.next();
            }
        } catch (IOException e4) {
            Log.e("KeyFrames", "Error parsing XML resource", e4);
        } catch (XmlPullParserException e5) {
            Log.e("KeyFrames", "Error parsing XML resource", e5);
        }
    }
}
