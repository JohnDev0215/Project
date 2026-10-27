package n7;

import A7.InterfaceC0057e;
import E0.C0167q;
import H7.InterfaceC0318c;
import Q5.S;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewParent;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.TextView;
import e0.C1178r;
import io.sentry.C1489n1;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;
import k6.C1700h;
import m0.AbstractC1792a;
import o8.C2057b;
import p.C2066a;
import x1.AbstractC2671j;

/* JADX INFO: loaded from: classes.dex */
public abstract class z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f20161a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static volatile boolean f20162b = true;

    public static final double A(long j3) {
        return ((j3 >>> 11) * ((double) 2048)) + (j3 & 2047);
    }

    public static final long a(float f10, float f11) {
        long jFloatToRawIntBits = (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
        int i7 = AbstractC1792a.f18877b;
        return jFloatToRawIntBits;
    }

    public static void b(String str, boolean z6, b0.b bVar, int i7) {
        C0167q c0167q;
        if ((i7 & 1) != 0) {
            str = A7.k.f("toString(...)");
        }
        boolean z10 = (i7 & 4) != 0;
        if ((i7 & 8) != 0) {
            z6 = false;
        }
        A7.m.f(str, "id");
        C1700h c1700h = new C1700h(str, new b0.b(1245116219, new S(z6, bVar, str, 1), true), null, z10);
        C1178r c1178r = k6.o.c;
        if (c1178r == null || !c1178r.isEmpty()) {
            ListIterator listIterator = c1178r.listIterator();
            do {
                c0167q = (C0167q) listIterator;
                if (c0167q.hasNext()) {
                }
            } while (!((C1700h) c0167q.next()).f18266a.equals(c1700h.f18266a));
            return;
        }
        k6.o.c.add(c1700h);
    }

    public static void c(String str) {
        C1489n1.s().f(str);
    }

    public static void d(InputStream inputStream, OutputStream outputStream) throws IOException {
        A7.m.f(inputStream, "<this>");
        byte[] bArr = new byte[8192];
        int i7 = inputStream.read(bArr);
        while (i7 >= 0) {
            outputStream.write(bArr, 0, i7);
            i7 = inputStream.read(bArr);
        }
    }

    public static String e(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry entry : map.entrySet()) {
            if (sb.length() > 0) {
                sb.append("&");
            }
            sb.append(P8.n.u((String) entry.getKey()));
            sb.append("=");
            sb.append(P8.n.u((String) entry.getValue()));
        }
        return sb.toString();
    }

    public static final V7.c f(V7.b bVar, C2057b c2057b, n8.f fVar) {
        A7.m.f(bVar, "<this>");
        A7.m.f(c2057b, "classId");
        A7.m.f(fVar, "jvmMetadataVersion");
        e7.f fVarA = bVar.a(c2057b, fVar);
        if (fVarA != null) {
            return (V7.c) fVarA.f14480m;
        }
        return null;
    }

    public static final InterfaceC0318c g(Annotation annotation) {
        A7.m.f(annotation, "<this>");
        Class<? extends Annotation> clsAnnotationType = annotation.annotationType();
        A7.m.e(clsAnnotationType, "annotationType(...)");
        return l(clsAnnotationType);
    }

    public static Drawable h(Context context, Context context2, int i7, Resources.Theme theme) {
        try {
            if (f20162b) {
                return p(context2, i7, theme);
            }
        } catch (Resources.NotFoundException unused) {
        } catch (IllegalStateException e10) {
            if (context.getPackageName().equals(context2.getPackageName())) {
                throw e10;
            }
            return context2.getDrawable(i7);
        } catch (NoClassDefFoundError unused2) {
            f20162b = false;
        }
        if (theme == null) {
            theme = context2.getTheme();
        }
        Resources resources = context2.getResources();
        ThreadLocal threadLocal = AbstractC2671j.f23708a;
        return resources.getDrawable(i7, theme);
    }

    public static final Class i(InterfaceC0318c interfaceC0318c) {
        A7.m.f(interfaceC0318c, "<this>");
        Class clsD = ((InterfaceC0057e) interfaceC0318c).d();
        A7.m.d(clsD, "null cannot be cast to non-null type java.lang.Class<T of kotlin.jvm.JvmClassMappingKt.<get-java>>");
        return clsD;
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class j(InterfaceC0318c interfaceC0318c) {
        A7.m.f(interfaceC0318c, "<this>");
        Class clsD = ((InterfaceC0057e) interfaceC0318c).d();
        if (!clsD.isPrimitive()) {
            return clsD;
        }
        String name = clsD.getName();
        switch (name.hashCode()) {
            case -1325958191:
                if (!name.equals("double")) {
                }
                break;
            case 104431:
                if (!name.equals("int")) {
                }
                break;
            case 3039496:
                if (!name.equals("byte")) {
                }
                break;
            case 3052374:
                if (!name.equals("char")) {
                }
                break;
            case 3327612:
                if (!name.equals("long")) {
                }
                break;
            case 3625364:
                if (!name.equals("void")) {
                }
                break;
            case 64711720:
                if (!name.equals("boolean")) {
                }
                break;
            case 97526364:
                if (!name.equals("float")) {
                }
                break;
            case 109413500:
                if (!name.equals("short")) {
                }
                break;
        }
        return clsD;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    public static final Class k(InterfaceC0318c interfaceC0318c) {
        A7.m.f(interfaceC0318c, "<this>");
        Class clsD = ((InterfaceC0057e) interfaceC0318c).d();
        if (clsD.isPrimitive()) {
            return clsD;
        }
        String name = clsD.getName();
        switch (name.hashCode()) {
            case -2056817302:
                if (name.equals("java.lang.Integer")) {
                    return Integer.TYPE;
                }
                return null;
            case -527879800:
                if (name.equals("java.lang.Float")) {
                    return Float.TYPE;
                }
                return null;
            case -515992664:
                if (name.equals("java.lang.Short")) {
                    return Short.TYPE;
                }
                return null;
            case 155276373:
                if (name.equals("java.lang.Character")) {
                    return Character.TYPE;
                }
                return null;
            case 344809556:
                if (name.equals("java.lang.Boolean")) {
                    return Boolean.TYPE;
                }
                return null;
            case 398507100:
                if (name.equals("java.lang.Byte")) {
                    return Byte.TYPE;
                }
                return null;
            case 398795216:
                if (name.equals("java.lang.Long")) {
                    return Long.TYPE;
                }
                return null;
            case 399092968:
                if (name.equals("java.lang.Void")) {
                    return Void.TYPE;
                }
                return null;
            case 761287205:
                if (name.equals("java.lang.Double")) {
                    return Double.TYPE;
                }
                return null;
            default:
                return null;
        }
    }

    public static final InterfaceC0318c l(Class cls) {
        A7.m.f(cls, "<this>");
        return A7.A.f443a.b(cls);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, m7.f] */
    /* JADX WARN: Type inference failed for: r0v7, types: [java.lang.Object, m7.f] */
    public static final Window m(View view) throws IllegalAccessException {
        Field field;
        A7.m.f(view, "<this>");
        Object obj = io.sentry.android.replay.A.f16314a;
        View rootView = view.getRootView();
        A7.m.e(rootView, "rootView");
        Class cls = (Class) io.sentry.android.replay.A.f16314a.getValue();
        if (cls == null || !cls.isInstance(rootView) || (field = (Field) io.sentry.android.replay.A.f16315b.getValue()) == null) {
            return null;
        }
        Object obj2 = field.get(rootView);
        A7.m.d(obj2, "null cannot be cast to non-null type android.view.Window");
        return (Window) obj2;
    }

    public static final int n(int i7, int i10, int i11) {
        if (i11 > 0) {
            if (i7 >= i10) {
                return i10;
            }
            int i12 = i10 % i11;
            if (i12 < 0) {
                i12 += i11;
            }
            int i13 = i7 % i11;
            if (i13 < 0) {
                i13 += i11;
            }
            int i14 = (i12 - i13) % i11;
            if (i14 < 0) {
                i14 += i11;
            }
            return i10 - i14;
        }
        if (i11 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        if (i7 <= i10) {
            return i10;
        }
        int i15 = -i11;
        int i16 = i7 % i15;
        if (i16 < 0) {
            i16 += i15;
        }
        int i17 = i10 % i15;
        if (i17 < 0) {
            i17 += i15;
        }
        int i18 = (i16 - i17) % i15;
        if (i18 < 0) {
            i18 += i15;
        }
        return i10 + i18;
    }

    public static Object o(Object obj, Map map) {
        A7.m.f(map, "<this>");
        Object obj2 = map.get(obj);
        if (obj2 != null || map.containsKey(obj)) {
            return obj2;
        }
        throw new NoSuchElementException("Key " + obj + " is missing in the map.");
    }

    public static Drawable p(Context context, int i7, Resources.Theme theme) {
        if (theme != null) {
            C2066a c2066a = new C2066a(context);
            c2066a.f20603b = theme;
            Configuration configuration = theme.getResources().getConfiguration();
            if (c2066a.f20604e != null) {
                throw new IllegalStateException("getResources() or getAssets() has already been called");
            }
            if (c2066a.d != null) {
                throw new IllegalStateException("Override configuration has already been set");
            }
            c2066a.d = new Configuration(configuration);
            context = c2066a;
        }
        return h6.f.k(context, i7);
    }

    public static int q(int i7) {
        if (i7 < 0) {
            return i7;
        }
        if (i7 < 3) {
            return i7 + 1;
        }
        if (i7 < 1073741824) {
            return (int) ((i7 / 0.75f) + 1.0f);
        }
        return Integer.MAX_VALUE;
    }

    public static Map r(m7.i iVar) {
        A7.m.f(iVar, "pair");
        Map mapSingletonMap = Collections.singletonMap(iVar.l, iVar.f19259m);
        A7.m.e(mapSingletonMap, "singletonMap(...)");
        return mapSingletonMap;
    }

    public static Map s(m7.i... iVarArr) {
        if (iVarArr.length <= 0) {
            return v.l;
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(q(iVarArr.length));
        v(linkedHashMap, iVarArr);
        return linkedHashMap;
    }

    public static void t(InputConnection inputConnection, EditorInfo editorInfo, TextView textView) {
        if (inputConnection == null || editorInfo.hintText != null) {
            return;
        }
        for (ViewParent parent = textView.getParent(); parent instanceof View; parent = parent.getParent()) {
        }
    }

    public static LinkedHashMap u(Map map, Map map2) {
        A7.m.f(map, "<this>");
        A7.m.f(map2, "map");
        LinkedHashMap linkedHashMap = new LinkedHashMap(map);
        linkedHashMap.putAll(map2);
        return linkedHashMap;
    }

    public static final void v(HashMap map, m7.i[] iVarArr) {
        for (m7.i iVar : iVarArr) {
            map.put(iVar.l, iVar.f19259m);
        }
    }

    public static final byte[] w(InputStream inputStream) throws IOException {
        A7.m.f(inputStream, "<this>");
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, inputStream.available()));
        d(inputStream, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        A7.m.e(byteArray, "toByteArray(...)");
        return byteArray;
    }

    public static Map x(List list) {
        v vVar = v.l;
        int size = list.size();
        if (size == 0) {
            return vVar;
        }
        if (size == 1) {
            return r((m7.i) list.get(0));
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap(q(list.size()));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            m7.i iVar = (m7.i) it.next();
            linkedHashMap.put(iVar.l, iVar.f19259m);
        }
        return linkedHashMap;
    }

    public static Map y(Map map) {
        A7.m.f(map, "<this>");
        int size = map.size();
        if (size == 0) {
            return v.l;
        }
        if (size != 1) {
            return z(map);
        }
        A7.m.f(map, "<this>");
        Map.Entry entry = (Map.Entry) map.entrySet().iterator().next();
        Map mapSingletonMap = Collections.singletonMap(entry.getKey(), entry.getValue());
        A7.m.e(mapSingletonMap, "with(...)");
        return mapSingletonMap;
    }

    public static LinkedHashMap z(Map map) {
        A7.m.f(map, "<this>");
        return new LinkedHashMap(map);
    }
}
