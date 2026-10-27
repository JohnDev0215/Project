package u;

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
    public static HashMap<String, Constructor<? extends c>> f5121a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public HashMap<Integer, ArrayList<c>> f5122b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends c>> map = new HashMap<>();
        f5121a = map;
        try {
            map.put("KeyAttribute", d.class.getConstructor(new Class[0]));
            f5121a.put("KeyPosition", i.class.getConstructor(new Class[0]));
            f5121a.put("KeyCycle", f.class.getConstructor(new Class[0]));
            f5121a.put("KeyTimeCycle", k.class.getConstructor(new Class[0]));
            f5121a.put("KeyTrigger", l.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e3) {
            Log.e("KeyFrames", "unable to load", e3);
        }
    }

    public h(Context context, XmlPullParser xmlPullParser) {
        c cVarNewInstance;
        Exception e3;
        HashMap<String, ConstraintAttribute> map;
        c cVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType == 2) {
                    String name = xmlPullParser.getName();
                    if (f5121a.containsKey(name)) {
                        try {
                            cVarNewInstance = f5121a.get(name).newInstance(new Object[0]);
                        } catch (Exception e4) {
                            cVarNewInstance = cVar;
                            e3 = e4;
                        }
                        try {
                            cVarNewInstance.c(context, Xml.asAttributeSet(xmlPullParser));
                            b(cVarNewInstance);
                        } catch (Exception e5) {
                            e3 = e5;
                            Log.e("KeyFrames", "unable to create ", e3);
                        }
                        cVar = cVarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute") && cVar != null && (map = cVar.f5055f) != null) {
                        ConstraintAttribute.g(context, xmlPullParser, map);
                    }
                } else if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                    return;
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e6) {
            e6.printStackTrace();
        } catch (XmlPullParserException e7) {
            e7.printStackTrace();
        }
    }

    public void a(n nVar) {
        ArrayList<c> arrayList = this.f5122b.get(Integer.valueOf(nVar.f5202b));
        if (arrayList != null) {
            nVar.b(arrayList);
        }
        ArrayList<c> arrayList2 = this.f5122b.get(-1);
        if (arrayList2 != null) {
            for (c cVar : arrayList2) {
                if (cVar.d(((ConstraintLayout.b) nVar.f5201a.getLayoutParams()).V)) {
                    nVar.a(cVar);
                }
            }
        }
    }

    public final void b(c cVar) {
        if (!this.f5122b.containsKey(Integer.valueOf(cVar.f5052c))) {
            this.f5122b.put(Integer.valueOf(cVar.f5052c), new ArrayList<>());
        }
        this.f5122b.get(Integer.valueOf(cVar.f5052c)).add(cVar);
    }
}
