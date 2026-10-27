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
    static HashMap<String, Constructor<? extends c>> f577a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private HashMap<Integer, ArrayList<c>> f578b = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends c>> map = new HashMap<>();
        f577a = map;
        try {
            map.put("KeyAttribute", d.class.getConstructor(new Class[0]));
            f577a.put("KeyPosition", i.class.getConstructor(new Class[0]));
            f577a.put("KeyCycle", f.class.getConstructor(new Class[0]));
            f577a.put("KeyTimeCycle", k.class.getConstructor(new Class[0]));
            f577a.put("KeyTrigger", l.class.getConstructor(new Class[0]));
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
                    if (f577a.containsKey(name)) {
                        try {
                            cVarNewInstance = f577a.get(name).newInstance(new Object[0]);
                            try {
                                cVarNewInstance.c(context, Xml.asAttributeSet(xmlPullParser));
                                b(cVarNewInstance);
                            } catch (Exception e2) {
                                e = e2;
                                Log.e("KeyFrames", "unable to create ", e);
                            }
                        } catch (Exception e3) {
                            cVarNewInstance = cVar;
                            e = e3;
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
        if (!this.f578b.containsKey(Integer.valueOf(cVar.f559c))) {
            this.f578b.put(Integer.valueOf(cVar.f559c), new ArrayList<>());
        }
        this.f578b.get(Integer.valueOf(cVar.f559c)).add(cVar);
    }

    public void a(n nVar) {
        ArrayList<c> arrayList = this.f578b.get(Integer.valueOf(nVar.f586b));
        if (arrayList != null) {
            nVar.b(arrayList);
        }
        ArrayList<c> arrayList2 = this.f578b.get(-1);
        if (arrayList2 != null) {
            for (c cVar : arrayList2) {
                if (cVar.d(((ConstraintLayout.b) nVar.f585a.getLayoutParams()).V)) {
                    nVar.a(cVar);
                }
            }
        }
    }
}
