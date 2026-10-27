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
import androidx.navigation.n;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: NavInflater.java */
/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final ThreadLocal<TypedValue> f1716a = new ThreadLocal<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Context f1717b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private r f1718c;

    public m(Context context, r rVar) {
        this.f1717b = context;
        this.f1718c = rVar;
    }

    private static o a(TypedValue typedValue, o oVar, o oVar2, String str, String str2) throws XmlPullParserException {
        if (oVar == null || oVar == oVar2) {
            return oVar != null ? oVar : oVar2;
        }
        throw new XmlPullParserException("Type is " + str + " but found " + str2 + ": " + typedValue.data);
    }

    private i b(Resources resources, XmlResourceParser xmlResourceParser, AttributeSet attributeSet, int i2) throws XmlPullParserException {
        int depth;
        i iVarA = this.f1718c.e(xmlResourceParser.getName()).a();
        iVarA.m(this.f1717b, attributeSet);
        int depth2 = xmlResourceParser.getDepth() + 1;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || ((depth = xmlResourceParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2) {
                String name = xmlResourceParser.getName();
                if ("argument".equals(name)) {
                    g(resources, iVarA, attributeSet, i2);
                } else if ("deepLink".equals(name)) {
                    h(resources, iVarA, attributeSet);
                } else if ("action".equals(name)) {
                    d(resources, iVarA, attributeSet, xmlResourceParser, i2);
                } else if ("include".equals(name) && (iVarA instanceof j)) {
                    TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, t.NavInclude);
                    ((j) iVarA).s(c(typedArrayObtainAttributes.getResourceId(t.NavInclude_graph, 0)));
                    typedArrayObtainAttributes.recycle();
                } else if (iVarA instanceof j) {
                    ((j) iVarA).s(b(resources, xmlResourceParser, attributeSet, i2));
                }
            }
        }
        return iVarA;
    }

    private void d(Resources resources, i iVar, AttributeSet attributeSet, XmlResourceParser xmlResourceParser, int i2) throws XmlPullParserException {
        int depth;
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.navigation.u.a.NavAction);
        int resourceId = typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_android_id, 0);
        c cVar = new c(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_destination, 0));
        n.a aVar = new n.a();
        aVar.d(typedArrayObtainAttributes.getBoolean(androidx.navigation.u.a.NavAction_launchSingleTop, false));
        aVar.g(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_popUpTo, -1), typedArrayObtainAttributes.getBoolean(androidx.navigation.u.a.NavAction_popUpToInclusive, false));
        aVar.b(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_enterAnim, -1));
        aVar.c(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_exitAnim, -1));
        aVar.e(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_popEnterAnim, -1));
        aVar.f(typedArrayObtainAttributes.getResourceId(androidx.navigation.u.a.NavAction_popExitAnim, -1));
        cVar.e(aVar.a());
        Bundle bundle = new Bundle();
        int depth2 = xmlResourceParser.getDepth() + 1;
        while (true) {
            int next = xmlResourceParser.next();
            if (next == 1 || ((depth = xmlResourceParser.getDepth()) < depth2 && next == 3)) {
                break;
            }
            if (next == 2 && depth <= depth2 && "argument".equals(xmlResourceParser.getName())) {
                f(resources, bundle, attributeSet, i2);
            }
        }
        if (!bundle.isEmpty()) {
            cVar.d(bundle);
        }
        iVar.n(resourceId, cVar);
        typedArrayObtainAttributes.recycle();
    }

    private d e(TypedArray typedArray, Resources resources, int i2) throws XmlPullParserException {
        d.a aVar = new d.a();
        aVar.c(typedArray.getBoolean(androidx.navigation.u.a.NavArgument_nullable, false));
        ThreadLocal<TypedValue> threadLocal = f1716a;
        TypedValue typedValue = threadLocal.get();
        if (typedValue == null) {
            typedValue = new TypedValue();
            threadLocal.set(typedValue);
        }
        String string = typedArray.getString(androidx.navigation.u.a.NavArgument_argType);
        Object objK = null;
        o<?> oVarA = string != null ? o.a(string, resources.getResourcePackageName(i2)) : null;
        int i3 = androidx.navigation.u.a.NavArgument_android_defaultValue;
        if (typedArray.getValue(i3, typedValue)) {
            o<Integer> oVar = o.f1734b;
            if (oVarA == oVar) {
                int i4 = typedValue.resourceId;
                if (i4 != 0) {
                    objK = Integer.valueOf(i4);
                } else {
                    if (typedValue.type != 16 || typedValue.data != 0) {
                        throw new XmlPullParserException("unsupported value '" + ((Object) typedValue.string) + "' for " + oVarA.c() + ". Must be a reference to a resource.");
                    }
                    objK = 0;
                }
            } else {
                int i5 = typedValue.resourceId;
                if (i5 != 0) {
                    if (oVarA != null) {
                        throw new XmlPullParserException("unsupported value '" + ((Object) typedValue.string) + "' for " + oVarA.c() + ". You must use a \"" + oVar.c() + "\" type to reference other resources.");
                    }
                    oVarA = oVar;
                    objK = Integer.valueOf(i5);
                } else if (oVarA == o.j) {
                    objK = typedArray.getString(i3);
                } else {
                    int i6 = typedValue.type;
                    if (i6 == 3) {
                        String string2 = typedValue.string.toString();
                        if (oVarA == null) {
                            oVarA = o.d(string2);
                        }
                        objK = oVarA.k(string2);
                    } else if (i6 == 4) {
                        oVarA = a(typedValue, oVarA, o.f1738f, string, "float");
                        objK = Float.valueOf(typedValue.getFloat());
                    } else if (i6 == 5) {
                        oVarA = a(typedValue, oVarA, o.f1733a, string, "dimension");
                        objK = Integer.valueOf((int) typedValue.getDimension(resources.getDisplayMetrics()));
                    } else if (i6 == 18) {
                        oVarA = a(typedValue, oVarA, o.f1740h, string, "boolean");
                        objK = Boolean.valueOf(typedValue.data != 0);
                    } else {
                        if (i6 < 16 || i6 > 31) {
                            throw new XmlPullParserException("unsupported argument type " + typedValue.type);
                        }
                        o<Float> oVar2 = o.f1738f;
                        if (oVarA == oVar2) {
                            oVarA = a(typedValue, oVarA, oVar2, string, "float");
                            objK = Float.valueOf(typedValue.data);
                        } else {
                            oVarA = a(typedValue, oVarA, o.f1733a, string, "integer");
                            objK = Integer.valueOf(typedValue.data);
                        }
                    }
                }
            }
        }
        if (objK != null) {
            aVar.b(objK);
        }
        if (oVarA != null) {
            aVar.d(oVarA);
        }
        return aVar.a();
    }

    private void f(Resources resources, Bundle bundle, AttributeSet attributeSet, int i2) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.navigation.u.a.NavArgument);
        String string = typedArrayObtainAttributes.getString(androidx.navigation.u.a.NavArgument_android_name);
        if (string == null) {
            throw new XmlPullParserException("Arguments must have a name");
        }
        d dVarE = e(typedArrayObtainAttributes, resources, i2);
        if (dVarE.b()) {
            dVarE.c(string, bundle);
        }
        typedArrayObtainAttributes.recycle();
    }

    private void g(Resources resources, i iVar, AttributeSet attributeSet, int i2) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.navigation.u.a.NavArgument);
        String string = typedArrayObtainAttributes.getString(androidx.navigation.u.a.NavArgument_android_name);
        if (string == null) {
            throw new XmlPullParserException("Arguments must have a name");
        }
        iVar.a(string, e(typedArrayObtainAttributes, resources, i2));
        typedArrayObtainAttributes.recycle();
    }

    private void h(Resources resources, i iVar, AttributeSet attributeSet) throws XmlPullParserException {
        TypedArray typedArrayObtainAttributes = resources.obtainAttributes(attributeSet, androidx.navigation.u.a.NavDeepLink);
        String string = typedArrayObtainAttributes.getString(androidx.navigation.u.a.NavDeepLink_uri);
        String string2 = typedArrayObtainAttributes.getString(androidx.navigation.u.a.NavDeepLink_action);
        String string3 = typedArrayObtainAttributes.getString(androidx.navigation.u.a.NavDeepLink_mimeType);
        if (TextUtils.isEmpty(string) && TextUtils.isEmpty(string2) && TextUtils.isEmpty(string3)) {
            throw new XmlPullParserException("Every <deepLink> must include at least one of app:uri, app:action, or app:mimeType");
        }
        g.a aVar = new g.a();
        if (string != null) {
            aVar.d(string.replace("${applicationId}", this.f1717b.getPackageName()));
        }
        if (!TextUtils.isEmpty(string2)) {
            aVar.b(string2.replace("${applicationId}", this.f1717b.getPackageName()));
        }
        if (string3 != null) {
            aVar.c(string3.replace("${applicationId}", this.f1717b.getPackageName()));
        }
        iVar.b(aVar.a());
        typedArrayObtainAttributes.recycle();
    }

    @SuppressLint({"ResourceType"})
    public j c(int i2) {
        int next;
        Resources resources = this.f1717b.getResources();
        XmlResourceParser xml = resources.getXml(i2);
        AttributeSet attributeSetAsAttributeSet = Xml.asAttributeSet(xml);
        do {
            try {
                try {
                    next = xml.next();
                    if (next == 2) {
                        break;
                    }
                } catch (Exception e2) {
                    throw new RuntimeException("Exception inflating " + resources.getResourceName(i2) + " line " + xml.getLineNumber(), e2);
                }
            } finally {
                xml.close();
            }
        } while (next != 1);
        if (next != 2) {
            throw new XmlPullParserException("No start tag found");
        }
        String name = xml.getName();
        i iVarB = b(resources, xml, attributeSetAsAttributeSet, i2);
        if (iVarB instanceof j) {
            return (j) iVarB;
        }
        throw new IllegalArgumentException("Root element <" + name + "> did not inflate into a NavGraph");
    }
}
