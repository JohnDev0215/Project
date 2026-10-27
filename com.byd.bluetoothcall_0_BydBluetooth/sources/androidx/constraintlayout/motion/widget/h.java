package androidx.constraintlayout.motion.widget;

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
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static HashMap<String, Constructor<? extends e>> f932a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<Integer, ArrayList<e>> f933b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends e>> map = new HashMap<>();
        f932a = map;
        try {
            map.put("KeyAttribute", f.class.getConstructor(new Class[0]));
            f932a.put("KeyPosition", i.class.getConstructor(new Class[0]));
            f932a.put("KeyCycle", g.class.getConstructor(new Class[0]));
            f932a.put("KeyTimeCycle", k.class.getConstructor(new Class[0]));
            f932a.put("KeyTrigger", l.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e2) {
            Log.e("KeyFrames", "unable to load", e2);
        }
    }

    public h() {
    }

    public void a(n nVar) {
        ArrayList<e> arrayList = this.f933b.get(-1);
        if (arrayList != null) {
            nVar.b(arrayList);
        }
    }

    public void b(n nVar) {
        ArrayList<e> arrayList = this.f933b.get(Integer.valueOf(nVar.f943c));
        if (arrayList != null) {
            nVar.b(arrayList);
        }
        ArrayList<e> arrayList2 = this.f933b.get(-1);
        if (arrayList2 != null) {
            for (e eVar : arrayList2) {
                if (eVar.f(((ConstraintLayout.b) nVar.f942b.getLayoutParams()).c0)) {
                    nVar.a(eVar);
                }
            }
        }
    }

    public void c(e eVar) {
        if (!this.f933b.containsKey(Integer.valueOf(eVar.f927c))) {
            this.f933b.put(Integer.valueOf(eVar.f927c), new ArrayList<>());
        }
        ArrayList<e> arrayList = this.f933b.get(Integer.valueOf(eVar.f927c));
        if (arrayList != null) {
            arrayList.add(eVar);
        }
    }

    public ArrayList<e> d(int i) {
        return this.f933b.get(Integer.valueOf(i));
    }

    public h(Context context, XmlPullParser xmlPullParser) {
        e eVarNewInstance;
        Exception e2;
        Constructor<? extends e> constructor;
        HashMap<String, ConstraintAttribute> map;
        HashMap<String, ConstraintAttribute> map2;
        e eVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType != 2) {
                    if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                        return;
                    }
                } else {
                    String name = xmlPullParser.getName();
                    if (f932a.containsKey(name)) {
                        try {
                            constructor = f932a.get(name);
                        } catch (Exception e3) {
                            eVarNewInstance = eVar;
                            e2 = e3;
                        }
                        if (constructor != null) {
                            eVarNewInstance = constructor.newInstance(new Object[0]);
                            try {
                                eVarNewInstance.e(context, Xml.asAttributeSet(xmlPullParser));
                                c(eVarNewInstance);
                            } catch (Exception e4) {
                                e2 = e4;
                                Log.e("KeyFrames", "unable to create ", e2);
                            }
                            eVar = eVarNewInstance;
                        } else {
                            throw new NullPointerException("Keymaker for " + name + " not found");
                        }
                        Log.e("KeyFrames", "unable to create ", e2);
                        eVar = eVarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute")) {
                        if (eVar != null && (map2 = eVar.f) != null) {
                            ConstraintAttribute.i(context, xmlPullParser, map2);
                        }
                    } else if (name.equalsIgnoreCase("CustomMethod") && eVar != null && (map = eVar.f) != null) {
                        ConstraintAttribute.i(context, xmlPullParser, map);
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        } catch (XmlPullParserException e6) {
            e6.printStackTrace();
        }
    }
}
