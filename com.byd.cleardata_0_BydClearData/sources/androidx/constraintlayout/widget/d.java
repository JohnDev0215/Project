package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.Log;
import android.util.SparseArray;
import android.util.Xml;
import java.io.IOException;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final ConstraintLayout f1105a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f1106b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f1107c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private SparseArray<a> f1108d = new SparseArray<>();
    private SparseArray<e> e = new SparseArray<>();

    static class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1109a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ArrayList<b> f1110b = new ArrayList<>();

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        int f1111c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        e f1112d;

        public a(Context context, XmlPullParser xmlPullParser) {
            this.f1111c = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), j.E4);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.F4) {
                    this.f1109a = typedArrayObtainStyledAttributes.getResourceId(index, this.f1109a);
                } else if (index == j.G4) {
                    this.f1111c = typedArrayObtainStyledAttributes.getResourceId(index, this.f1111c);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.f1111c);
                    context.getResources().getResourceName(this.f1111c);
                    if ("layout".equals(resourceTypeName)) {
                        e eVar = new e();
                        this.f1112d = eVar;
                        eVar.e(context, this.f1111c);
                    }
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }

        void a(b bVar) {
            this.f1110b.add(bVar);
        }
    }

    static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        float f1113a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        float f1114b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        float f1115c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        float f1116d;
        int e;
        e f;

        public b(Context context, XmlPullParser xmlPullParser) {
            this.f1113a = Float.NaN;
            this.f1114b = Float.NaN;
            this.f1115c = Float.NaN;
            this.f1116d = Float.NaN;
            this.e = -1;
            TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(Xml.asAttributeSet(xmlPullParser), j.a5);
            int indexCount = typedArrayObtainStyledAttributes.getIndexCount();
            for (int i = 0; i < indexCount; i++) {
                int index = typedArrayObtainStyledAttributes.getIndex(i);
                if (index == j.b5) {
                    this.e = typedArrayObtainStyledAttributes.getResourceId(index, this.e);
                    String resourceTypeName = context.getResources().getResourceTypeName(this.e);
                    context.getResources().getResourceName(this.e);
                    if ("layout".equals(resourceTypeName)) {
                        e eVar = new e();
                        this.f = eVar;
                        eVar.e(context, this.e);
                    }
                } else if (index == j.c5) {
                    this.f1116d = typedArrayObtainStyledAttributes.getDimension(index, this.f1116d);
                } else if (index == j.d5) {
                    this.f1114b = typedArrayObtainStyledAttributes.getDimension(index, this.f1114b);
                } else if (index == j.e5) {
                    this.f1115c = typedArrayObtainStyledAttributes.getDimension(index, this.f1115c);
                } else if (index == j.f5) {
                    this.f1113a = typedArrayObtainStyledAttributes.getDimension(index, this.f1113a);
                } else {
                    Log.v("ConstraintLayoutStates", "Unknown tag");
                }
            }
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    d(Context context, ConstraintLayout constraintLayout, int i) {
        this.f1105a = constraintLayout;
        a(context, i);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private void a(Context context, int i) {
        XmlResourceParser xml = context.getResources().getXml(i);
        a aVar = null;
        try {
            for (int eventType = xml.getEventType(); eventType != 1; eventType = xml.next()) {
                if (eventType == 0) {
                    xml.getName();
                } else if (eventType == 2) {
                    String name = xml.getName();
                    byte b2 = -1;
                    switch (name.hashCode()) {
                        case -1349929691:
                            if (name.equals("ConstraintSet")) {
                                b2 = 4;
                            }
                            break;
                        case 80204913:
                            if (name.equals("State")) {
                                b2 = 2;
                            }
                            break;
                        case 1382829617:
                            if (name.equals("StateSet")) {
                                b2 = 1;
                            }
                            break;
                        case 1657696882:
                            if (name.equals("layoutDescription")) {
                                b2 = 0;
                            }
                            break;
                        case 1901439077:
                            if (name.equals("Variant")) {
                                b2 = 3;
                            }
                            break;
                    }
                    if (b2 != 0 && b2 != 1) {
                        if (b2 == 2) {
                            aVar = new a(context, xml);
                            this.f1108d.put(aVar.f1109a, aVar);
                        } else if (b2 == 3) {
                            b bVar = new b(context, xml);
                            if (aVar != null) {
                                aVar.a(bVar);
                            }
                        } else if (b2 != 4) {
                            Log.v("ConstraintLayoutStates", "unknown tag " + name);
                        } else {
                            b(context, xml);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XmlPullParserException e2) {
            e2.printStackTrace();
        }
    }

    private void b(Context context, XmlPullParser xmlPullParser) {
        e eVar = new e();
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i = 0; i < attributeCount; i++) {
            if ("id".equals(xmlPullParser.getAttributeName(i))) {
                String attributeValue = xmlPullParser.getAttributeValue(i);
                int identifier = attributeValue.contains("/") ? context.getResources().getIdentifier(attributeValue.substring(attributeValue.indexOf(47) + 1), "id", context.getPackageName()) : -1;
                if (identifier == -1) {
                    if (attributeValue.length() > 1) {
                        identifier = Integer.parseInt(attributeValue.substring(1));
                    } else {
                        Log.e("ConstraintLayoutStates", "error in parsing id");
                    }
                }
                eVar.k(context, xmlPullParser);
                this.e.put(identifier, eVar);
                return;
            }
        }
    }

    public void c(g gVar) {
    }
}
