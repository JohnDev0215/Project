package androidx.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.util.Xml;
import androidx.navigation.d;
import androidx.navigation.g;
import androidx.navigation.o;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: NavInflater.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f2724c = new ThreadLocal<>();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private Context f2725a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private s f2726b;

    public n(Context context, s sVar) {
        this.f2725a = context;
        this.f2726b = sVar;
    }

    private static p a(TypedValue typedValue, p pVar, p pVar2, String str, String str2) throws XmlPullParserException {
        if (pVar == null || pVar == pVar2) {
            return pVar != null ? pVar : pVar2;
        }
        throw new XmlPullParserException("Type is " + str + " but found " + str2 + ": " + typedValue.data);
    }

    private j b(Resources resources, XmlResourceParser xmlResourceParser, AttributeSet attributeSet, int i7) throws XmlPullParserException {
        int depth;
        j jVarA = this.f2726b.e(xmlResourceParser.getName()).a();
        jVarA.p(this.f2725a, attributeSet);
        int depth2 = xmlResourceParser.getDepth() + 1;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || ((depth = xmlResourceParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2) {
                String name = xmlResourceParser.getName();
                if ("argument".equals(name)) {
                    g(resources, jVarA, attributeSet, i7);
                } else if ("deepLink".equals(name)) {
                    h(resources, jVarA, attributeSet);
                } else if ("action".equals(name)) {
                    d(resources, jVarA, attributeSet, xmlResourceParser, i7);
                } else if ("include".equals(name) && (jVarA instanceof k)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, u.NavInclude);
                    ((k) jVarA).v(c(typedArrayObtainAttributes.getResourceId(u.NavInclude_graph, 0)));
                    typedArrayObtainAttributes.recycle();
                } else if (jVarA instanceof k) {
                    ((k) jVarA).v(b(resources, xmlResourceParser, attributeSet, i7));
                }
            }
        }
        return jVarA;
    }

    private void d(Resources resources, j jVar, AttributeSet attributeSet, XmlResourceParser xmlResourceParser, int i7) throws XmlPullParserException {
        int depth;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, w0.a.NavAction);
        int resourceId = typedArrayObtainAttributes.getResourceId(w0.a.NavAction_android_id, 0);
        c cVar = new c(typedArrayObtainAttributes.getResourceId(w0.a.NavAction_destination, 0));
        o.a aVar = new o.a();
        aVar.d(typedArrayObtainAttributes.getBoolean(w0.a.NavAction_launchSingleTop, false));
        aVar.g(typedArrayObtainAttributes.getResourceId(w0.a.NavAction_popUpTo, -1), typedArrayObtainAttributes.getBoolean(w0.a.NavAction_popUpToInclusive, false));
        aVar.b(typedArrayObtainAttributes.getResourceId(w0.a.NavAction_enterAnim, -1));
        aVar.c(typedArrayObtainAttributes.getResourceId(w0.a.NavAction_exitAnim, -1));
        aVar.e(typedArrayObtainAttributes.getResourceId(w0.a.NavAction_popEnterAnim, -1));
        aVar.f(typedArrayObtainAttributes.getResourceId(w0.a.NavAction_popExitAnim, -1));
        cVar.e(aVar.a());
        Bundle bundle = new Bundle();
        int depth2 = xmlResourceParser.getDepth() + 1;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || ((depth = xmlResourceParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && "argument".equals(xmlResourceParser.getName())) {
                f(resources, bundle, attributeSet, i7);
            }
        }
        if (!bundle.isEmpty()) {
            cVar.d(bundle);
        }
        jVar.q(resourceId, cVar);
        typedArrayObtainAttributes.recycle();
    }

    private d e(TypedArray typedArray, Resources resources, int i7) throws XmlPullParserException {
        d.a aVar = new d.a();
        aVar.c(typedArray.getBoolean(w0.a.NavArgument_nullable, false));
        ThreadLocal<TypedValue> threadLocal = f2724c;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        String string = typedArray.getString(w0.a.NavArgument_argType);
        Object objK = null;
        p<?> pVarA = string != null ? p.a(string, resources.getResourcePackageName(i7)) : null;
        int i8 = w0.a.NavArgument_android_defaultValue;
        if (typedArray.getValue(i8, typedValue)) {
            p<Integer> pVar = p.f2742c;
            if (pVarA == pVar) {
                int i9 = typedValue.resourceId;
                if (i9 != 0) {
                    objK = Integer.valueOf(i9);
                } else {
                    if (typedValue.type != 16 || typedValue.data != 0) {
                        throw new XmlPullParserException("unsupported value '" + ((Object) typedValue.string) + "' for " + pVarA.c() + ". Must be a reference to a resource.");
                    }
                    objK = 0;
                }
            } else {
                int i10 = typedValue.resourceId;
                if (i10 != 0) {
                    if (pVarA != null) {
                        throw new XmlPullParserException("unsupported value '" + ((Object) typedValue.string) + "' for " + pVarA.c() + ". You must use a \"" + pVar.c() + "\" type to reference other resources.");
                    }
                    pVarA = pVar;
                    objK = Integer.valueOf(i10);
                } else if (pVarA == p.f2750k) {
                    objK = typedArray.getString(i8);
                } else {
                    int i11 = typedValue.type;
                    if (i11 == 3) {
                        String string2 = typedValue.string.toString();
                        if (pVarA == null) {
                            pVarA = p.d(string2);
                        }
                        objK = pVarA.k(string2);
                    } else if (i11 == 4) {
                        pVarA = a(typedValue, pVarA, p.f2746g, string, "float");
                        objK = Float.valueOf(typedValue.getFloat());
                    } else if (i11 == 5) {
                        pVarA = a(typedValue, pVarA, p.f2741b, string, "dimension");
                        objK = Integer.valueOf((int) typedValue.getDimension(resources.getDisplayMetrics()));
                    } else if (i11 == 18) {
                        pVarA = a(typedValue, pVarA, p.f2748i, string, "boolean");
                        objK = Boolean.valueOf(typedValue.data != 0);
                    } else {
                        if (i11 < 16 || i11 > 31) {
                            throw new XmlPullParserException("unsupported argument type " + typedValue.type);
                        }
                        p<Float> pVar2 = p.f2746g;
                        if (pVarA == pVar2) {
                            pVarA = a(typedValue, pVarA, pVar2, string, "float");
                            objK = Float.valueOf(typedValue.data);
                        } else {
                            pVarA = a(typedValue, pVarA, p.f2741b, string, "integer");
                            objK = Integer.valueOf(typedValue.data);
                        }
                    }
                }
            }
        }
        if (objK != null) {
            aVar.b(objK);
        }
        if (pVarA != null) {
            aVar.d(pVarA);
        }
        return aVar.a();
    }

    private void f(Resources resources, Bundle bundle, AttributeSet attributeSet, int i7) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, w0.a.NavArgument);
        String string = typedArrayObtainAttributes.getString(w0.a.NavArgument_android_name);
        if (string == null) {
            throw new XmlPullParserException("Arguments must have a name");
        }
        d dVarE = e(typedArrayObtainAttributes, resources, i7);
        if (dVarE.b()) {
            dVarE.c(string, bundle);
        }
        typedArrayObtainAttributes.recycle();
    }

    private void g(Resources resources, j jVar, AttributeSet attributeSet, int i7) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, w0.a.NavArgument);
        String string = typedArrayObtainAttributes.getString(w0.a.NavArgument_android_name);
        if (string == null) {
            throw new XmlPullParserException("Arguments must have a name");
        }
        jVar.c(string, e(typedArrayObtainAttributes, resources, i7));
        typedArrayObtainAttributes.recycle();
    }

    private void h(Resources resources, j jVar, AttributeSet attributeSet) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, w0.a.NavDeepLink);
        String string = typedArrayObtainAttributes.getString(w0.a.NavDeepLink_uri);
        String string2 = typedArrayObtainAttributes.getString(w0.a.NavDeepLink_action);
        String string3 = typedArrayObtainAttributes.getString(w0.a.NavDeepLink_mimeType);
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
            throw new XmlPullParserException("Every <deepLink> must include at least one of app:uri, app:action, or app:mimeType");
        }
        g.a aVar = new g.a();
        if (string != null) {
            aVar.d(string.replace("${applicationId}", this.f2725a.getPackageName()));
        }
        if (!TextUtils.isEmpty(string2)) {
            aVar.b(string2.replace("${applicationId}", this.f2725a.getPackageName()));
        }
        if (string3 != null) {
            aVar.c(string3.replace("${applicationId}", this.f2725a.getPackageName()));
        }
        jVar.d(aVar.a());
        typedArrayObtainAttributes.recycle();
    }

    @SuppressLint({"ResourceType"})
    public k c(int i7) {
        int next;
        Resources resources = this.f2725a.getResources();
        XmlResourceParser xml = resources.getXml(i7);
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
        do {
            try {
                try {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } catch (Exception e7) {
                    throw new RuntimeException("Exception inflating " + resources.getResourceName(i7) + " line " + xml.getLineNumber(), e7);
                }
            } finally {
                xml.close();
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        j jVarB = b(resources, xml, attributeSetAsAttributeSet, i7);
        if (jVarB instanceof k) {
            return (k) jVarB;
        }
        throw new IllegalArgumentException("Root element <" + name + "> did not inflate into a NavGraph");
    }
}
