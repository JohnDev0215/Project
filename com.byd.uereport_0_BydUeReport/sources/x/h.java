package x;

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
    public static HashMap<String, Constructor<? extends c>> f8835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HashMap<Integer, ArrayList<c>> f8836b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends c>> map = new HashMap<>();
        f8835a = map;
        try {
            map.put("KeyAttribute", d.class.getConstructor(new Class[0]));
            f8835a.put("KeyPosition", i.class.getConstructor(new Class[0]));
            f8835a.put("KeyCycle", f.class.getConstructor(new Class[0]));
            f8835a.put("KeyTimeCycle", k.class.getConstructor(new Class[0]));
            f8835a.put("KeyTrigger", l.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e8) {
            Log.e("KeyFrames", "unable to load", e8);
        }
    }

    public h(Context context, XmlPullParser xmlPullParser) {
        c cVarNewInstance;
        Exception e8;
        HashMap<String, ConstraintAttribute> map;
        c cVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (f8835a.containsKey(name)) {
                        try {
                            cVarNewInstance = f8835a.get(name).newInstance(new Object[0]);
                            try {
                                cVarNewInstance.c(context, Xml.asAttributeSet(xmlPullParser));
                                b(cVarNewInstance);
                            } catch (Exception e9) {
                                e8 = e9;
                                Log.e("KeyFrames", "unable to create ", e8);
                            }
                        } catch (Exception e10) {
                            cVarNewInstance = cVar;
                            e8 = e10;
                        }
                        cVar = cVarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && cVar != null && (map = cVar.f8769f) != null) {
                        ConstraintAttribute.g(context, xmlPullParser, map);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e11) {
            e11.printStackTrace();
        } catch (XmlPullParserException e12) {
            e12.printStackTrace();
        }
    }

    public void a(n nVar) {
        ArrayList<c> arrayList = this.f8836b.get(Integer.valueOf(nVar.f8914b));
        if (arrayList != null) {
            nVar.b(arrayList);
        }
        ArrayList<c> arrayList2 = this.f8836b.get(-1);
        if (arrayList2 != null) {
            for (c cVar : arrayList2) {
                if (cVar.d(((ConstraintLayout.b) nVar.f8913a.getLayoutParams()).V)) {
                    nVar.a(cVar);
                }
            }
        }
    }

    public final void b(c cVar) {
        if (!this.f8836b.containsKey(Integer.valueOf(cVar.f8766c))) {
            this.f8836b.put(Integer.valueOf(cVar.f8766c), new ArrayList<>());
        }
        this.f8836b.get(Integer.valueOf(cVar.f8766c)).add(cVar);
    }
}
