package b.e.a.b;

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
    public static HashMap<String, Constructor<? extends c>> f1278a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HashMap<Integer, ArrayList<c>> f1279b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends c>> map = new HashMap<>();
        f1278a = map;
        try {
            map.put("KeyAttribute", d.class.getConstructor(new Class[0]));
            f1278a.put("KeyPosition", i.class.getConstructor(new Class[0]));
            f1278a.put("KeyCycle", f.class.getConstructor(new Class[0]));
            f1278a.put("KeyTimeCycle", k.class.getConstructor(new Class[0]));
            f1278a.put("KeyTrigger", l.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e2) {
            Log.e("KeyFrames", "unable to load", e2);
        }
    }

    public h(Context context, XmlPullParser xmlPullParser) {
        c cVarNewInstance;
        Exception e2;
        HashMap<String, ConstraintAttribute> map;
        c cVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (f1278a.containsKey(name)) {
                        try {
                            cVarNewInstance = f1278a.get(name).newInstance(new Object[0]);
                        } catch (Exception e3) {
                            cVarNewInstance = cVar;
                            e2 = e3;
                        }
                        try {
                            cVarNewInstance.c(context, Xml.asAttributeSet(xmlPullParser));
                            b(cVarNewInstance);
                        } catch (Exception e4) {
                            e2 = e4;
                            Log.e("KeyFrames", "unable to create ", e2);
                        }
                        cVar = cVarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && cVar != null && (map = cVar.f1245f) != null) {
                        ConstraintAttribute.g(context, xmlPullParser, map);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e5) {
            e5.printStackTrace();
        } catch (XmlPullParserException e6) {
            e6.printStackTrace();
        }
    }

    public void a(n nVar) {
        ArrayList<c> arrayList = this.f1279b.get(Integer.valueOf(nVar.f1295b));
        if (arrayList != null) {
            nVar.b(arrayList);
        }
        ArrayList<c> arrayList2 = this.f1279b.get(-1);
        if (arrayList2 != null) {
            for (c cVar : arrayList2) {
                if (cVar.d(((ConstraintLayout.b) nVar.f1294a.getLayoutParams()).V)) {
                    nVar.a(cVar);
                }
            }
        }
    }

    public final void b(c cVar) {
        if (!this.f1279b.containsKey(Integer.valueOf(cVar.f1242c))) {
            this.f1279b.put(Integer.valueOf(cVar.f1242c), new ArrayList<>());
        }
        this.f1279b.get(Integer.valueOf(cVar.f1242c)).add(cVar);
    }
}
