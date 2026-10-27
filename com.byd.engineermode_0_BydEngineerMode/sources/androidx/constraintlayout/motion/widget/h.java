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
    static HashMap<String, Constructor<? extends c>> f567a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<Integer, ArrayList<c>> f568b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends c>> map = new HashMap<>();
        f567a = map;
        try {
            map.put("KeyAttribute", d.class.getConstructor(new Class[0]));
            f567a.put("KeyPosition", i.class.getConstructor(new Class[0]));
            f567a.put("KeyCycle", f.class.getConstructor(new Class[0]));
            f567a.put("KeyTimeCycle", k.class.getConstructor(new Class[0]));
            f567a.put("KeyTrigger", l.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e) {
            Log.e("KeyFrames", "unable to load", e);
        }
    }

    public h(Context context, XmlPullParser xmlPullParser) {
        c cVarNewInstance;
        Exception e;
        HashMap<String, ConstraintAttribute> map;
        c cVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (f567a.containsKey(name)) {
                        try {
                            cVarNewInstance = f567a.get(name).newInstance(new Object[0]);
                        } catch (Exception e2) {
                            cVarNewInstance = cVar;
                            e = e2;
                        }
                        try {
                            cVarNewInstance.c(context, Xml.asAttributeSet(xmlPullParser));
                            b(cVarNewInstance);
                        } catch (Exception e3) {
                            e = e3;
                            Log.e("KeyFrames", "unable to create ", e);
                        }
                        cVar = cVarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && cVar != null && (map = cVar.f) != null) {
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

    private void b(c cVar) {
        if (!this.f568b.containsKey(Integer.valueOf(cVar.f549c))) {
            this.f568b.put(Integer.valueOf(cVar.f549c), new ArrayList<>());
        }
        this.f568b.get(Integer.valueOf(cVar.f549c)).add(cVar);
    }

    public void a(n nVar) {
        ArrayList<c> arrayList = this.f568b.get(Integer.valueOf(nVar.f576b));
        if (arrayList != null) {
            nVar.b(arrayList);
        }
        ArrayList<c> arrayList2 = this.f568b.get(-1);
        if (arrayList2 != null) {
            for (c cVar : arrayList2) {
                if (cVar.d(((ConstraintLayout.b) nVar.f575a.getLayoutParams()).V)) {
                    nVar.a(cVar);
                }
            }
        }
    }
}
