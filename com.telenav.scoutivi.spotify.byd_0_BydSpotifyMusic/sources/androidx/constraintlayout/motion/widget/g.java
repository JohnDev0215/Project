package androidx.constraintlayout.motion.widget;

import android.content.Context;
import android.util.Log;
import android.util.Xml;
import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: KeyFrames.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static HashMap<String, Constructor<? extends d>> f1394b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private HashMap<Integer, ArrayList<d>> f1395a = new HashMap<>();

    static {
        HashMap<String, Constructor<? extends d>> map = new HashMap<>();
        f1394b = map;
        try {
            map.put("KeyAttribute", e.class.getConstructor(new Class[0]));
            f1394b.put("KeyPosition", h.class.getConstructor(new Class[0]));
            f1394b.put("KeyCycle", f.class.getConstructor(new Class[0]));
            f1394b.put("KeyTimeCycle", j.class.getConstructor(new Class[0]));
            f1394b.put("KeyTrigger", k.class.getConstructor(new Class[0]));
        } catch (NoSuchMethodException e7) {
            Log.e("KeyFrames", "unable to load", e7);
        }
    }

    public g() {
    }

    public void a(m mVar) {
        ArrayList<d> arrayList = this.f1395a.get(-1);
        if (arrayList != null) {
            mVar.b(arrayList);
        }
    }

    public void b(m mVar) {
        ArrayList<d> arrayList = this.f1395a.get(Integer.valueOf(mVar.f1473c));
        if (arrayList != null) {
            mVar.b(arrayList);
        }
        ArrayList<d> arrayList2 = this.f1395a.get(-1);
        if (arrayList2 != null) {
            for (d dVar : arrayList2) {
                if (dVar.f(((ConstraintLayout.b) mVar.f1472b.getLayoutParams()).f1677c0)) {
                    mVar.a(dVar);
                }
            }
        }
    }

    public void c(d dVar) {
        if (!this.f1395a.containsKey(Integer.valueOf(dVar.f1351b))) {
            this.f1395a.put(Integer.valueOf(dVar.f1351b), new ArrayList<>());
        }
        ArrayList<d> arrayList = this.f1395a.get(Integer.valueOf(dVar.f1351b));
        if (arrayList != null) {
            arrayList.add(dVar);
        }
    }

    public ArrayList<d> d(int i7) {
        return this.f1395a.get(Integer.valueOf(i7));
    }

    public g(Context context, XmlPullParser xmlPullParser) {
        d dVarNewInstance;
        Exception e7;
        Constructor<? extends d> constructor;
        HashMap<String, androidx.constraintlayout.widget.a> map;
        HashMap<String, androidx.constraintlayout.widget.a> map2;
        d dVar = null;
        try {
            int eventType = xmlPullParser.getEventType();
            while (eventType != 1) {
                if (eventType != 2) {
                    if (eventType == 3 && "KeyFrameSet".equals(xmlPullParser.getName())) {
                        return;
                    }
                } else {
                    String name = xmlPullParser.getName();
                    if (f1394b.containsKey(name)) {
                        try {
                            constructor = f1394b.get(name);
                        } catch (Exception e8) {
                            dVarNewInstance = dVar;
                            e7 = e8;
                        }
                        if (constructor != null) {
                            dVarNewInstance = constructor.newInstance(new Object[0]);
                            try {
                                dVarNewInstance.e(context, Xml.asAttributeSet(xmlPullParser));
                                c(dVarNewInstance);
                            } catch (Exception e9) {
                                e7 = e9;
                                Log.e("KeyFrames", "unable to create ", e7);
                            }
                            dVar = dVarNewInstance;
                        } else {
                            throw new NullPointerException("Keymaker for " + name + " not found");
                        }
                        Log.e("KeyFrames", "unable to create ", e7);
                        dVar = dVarNewInstance;
                    } else if (name.equalsIgnoreCase("CustomAttribute")) {
                        if (dVar != null && (map2 = dVar.f1354e) != null) {
                            androidx.constraintlayout.widget.a.i(context, xmlPullParser, map2);
                        }
                    } else if (name.equalsIgnoreCase("CustomMethod") && dVar != null && (map = dVar.f1354e) != null) {
                        androidx.constraintlayout.widget.a.i(context, xmlPullParser, map);
                    }
                }
                eventType = xmlPullParser.next();
            }
        } catch (IOException e10) {
            e10.printStackTrace();
        } catch (XmlPullParserException e11) {
            e11.printStackTrace();
        }
    }
}
