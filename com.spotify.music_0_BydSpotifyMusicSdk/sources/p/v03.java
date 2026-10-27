package p;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ComponentInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.os.Process;
import android.os.StrictMode;
import android.util.AttributeSet;
import android.util.TypedValue;
import com.spotify.automotive.ubi.Referrer;
import com.spotify.mediasession.imageprovider.MediaUriUtil$Transformation;
import com.spotify.mediasession.mediaitem.MediaBrowserItemsExtrasBuilder$IconHint;
import com.spotify.music.R;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import java.util.logging.Level;
import java.util.logging.Logger;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.EmptyList;

/* JADX INFO: loaded from: classes.dex */
public abstract class v03 implements i00 {
    public static final float[][] a = {new float[]{0.401288f, 0.650173f, -0.051461f}, new float[]{-0.250268f, 1.204414f, 0.045854f}, new float[]{-0.002079f, 0.048952f, 0.953127f}};
    public static final float[][] b = {new float[]{1.8620678f, -1.0112547f, 0.14918678f}, new float[]{0.38752654f, 0.62144744f, -0.00897398f}, new float[]{-0.0158415f, -0.03412294f, 1.0499644f}};
    public static final float[] c = {95.047f, 100.0f, 108.883f};
    public static final float[][] d = {new float[]{0.41233894f, 0.35762063f, 0.18051042f}, new float[]{0.2126f, 0.7152f, 0.0722f}, new float[]{0.01932141f, 0.11916382f, 0.9503448f}};
    public static final char[] e = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
    public static final byte[] f = {48, 49, 53, 0};
    public static final byte[] g = {48, 49, 48, 0};
    public static final byte[] h = {48, 48, 57, 0};
    public static final byte[] i = {48, 48, 53, 0};
    public static final byte[] j = {48, 48, 49, 0};
    public static final byte[] k = {48, 48, 49, 0};
    public static final byte[] l = {48, 48, 50, 0};
    public static final int[] m = {R.attr.colorPrimary};
    public static final int[] n = {R.attr.colorPrimaryVariant};
    public static final yn o = new yn(15);

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static String f199p;
    public static int q;

    public static float A(int i2) {
        float f2 = i2 / 255.0f;
        return (f2 <= 0.04045f ? f2 / 12.92f : (float) Math.pow((f2 + 0.055f) / 1.055f, 2.4000000953674316d)) * 100.0f;
    }

    public static List B(Object obj) {
        List listSingletonList = Collections.singletonList(obj);
        i(listSingletonList, "singletonList(element)");
        return listSingletonList;
    }

    public static List C(Object... objArr) {
        j(objArr, "elements");
        return objArr.length > 0 ? rb.h0(objArr) : EmptyList.c;
    }

    public static ArrayList D(Object... objArr) {
        j(objArr, "elements");
        return objArr.length == 0 ? new ArrayList() : new ArrayList(new fb(objArr, true));
    }

    public static TypedArray E(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, int... iArr2) {
        e(context, attributeSet, i2, i3);
        l(context, attributeSet, iArr, i2, i3, iArr2);
        return context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
    }

    public static List F(List list) {
        int size = list.size();
        return size != 0 ? size != 1 ? list : B(list.get(0)) : EmptyList.c;
    }

    public static final q22 G(Set set) {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        HashMap map3 = new HashMap();
        Iterator it = set.iterator();
        while (it.hasNext()) {
            n01 n01Var = (n01) it.next();
            Long l2 = (Long) n01Var.c;
            String str = n01Var.a;
            if (l2 != null) {
                map.put(str, Long.valueOf(l2.longValue()));
            }
            Long l3 = n01Var.d;
            if (l3 != null) {
                map2.put(str, Long.valueOf(l3.longValue()));
            }
            String str2 = n01Var.b;
            if (str2 != null) {
                map3.put(str, str2);
            }
        }
        return new q22(map, map2, map3);
    }

    public static final void H(ga0 ga0Var, c00 c00Var, boolean z) {
        Object objH = ga0Var.h();
        Throwable thD = ga0Var.d(objH);
        Object objB = thD != null ? kotlin.a.b(thD) : ga0Var.e(objH);
        if (!z) {
            c00Var.k(objB);
            return;
        }
        g(c00Var, "null cannot be cast to non-null type kotlinx.coroutines.internal.DispatchedContinuation<T of kotlinx.coroutines.DispatchedTaskKt.resume>");
        ea0 ea0Var = (ea0) c00Var;
        c00 c00Var2 = ea0Var.m;
        y10 context = c00Var2.getContext();
        Object objC = kotlinx.coroutines.internal.c.c(context, ea0Var.o);
        jq2 jq2VarE = objC != kotlinx.coroutines.internal.c.a ? kotlinx.coroutines.a.e(c00Var2, context, objC) : null;
        try {
            c00Var2.k(objB);
        } finally {
            if (jq2VarE == null || jq2VarE.b0()) {
                kotlinx.coroutines.internal.c.a(context, objC);
            }
        }
    }

    public static void I(String str, RuntimeException runtimeException) {
        StackTraceElement[] stackTrace = runtimeException.getStackTrace();
        int length = stackTrace.length;
        int i2 = -1;
        for (int i3 = 0; i3 < length; i3++) {
            if (str.equals(stackTrace[i3].getClassName())) {
                i2 = i3;
            }
        }
        runtimeException.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(stackTrace, i2 + 1, length));
    }

    public static final sc J(Socket socket) throws IOException {
        Logger logger = jp1.a;
        be2 be2Var = new be2(socket);
        OutputStream outputStream = socket.getOutputStream();
        i(outputStream, "getOutputStream(...)");
        return new sc(be2Var, new sc(outputStream, be2Var));
    }

    public static final tc K(File file) {
        Logger logger = jp1.a;
        j(file, "<this>");
        return new tc(new FileInputStream(file), tl2.d);
    }

    public static final tc L(InputStream inputStream) {
        Logger logger = jp1.a;
        j(inputStream, "<this>");
        return new tc(inputStream, new tl2());
    }

    public static final tc M(Socket socket) throws IOException {
        Logger logger = jp1.a;
        be2 be2Var = new be2(socket);
        InputStream inputStream = socket.getInputStream();
        i(inputStream, "getInputStream(...)");
        return new tc(be2Var, new tc(inputStream, be2Var));
    }

    public static final long N(String str, long j2, long j3, long j4) {
        String property;
        int i2 = pi2.a;
        try {
            property = System.getProperty(str);
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null) {
            return j2;
        }
        Long lZ = mg2.Z(property);
        if (lZ == null) {
            throw new IllegalStateException(("System property '" + str + "' has unrecognized value '" + property + '\'').toString());
        }
        long jLongValue = lZ.longValue();
        if (j3 <= jLongValue && jLongValue <= j4) {
            return jLongValue;
        }
        throw new IllegalStateException(("System property '" + str + "' should be in range " + j3 + ".." + j4 + ", but is '" + jLongValue + '\'').toString());
    }

    public static int O(String str, int i2, int i3, int i4, int i5) {
        if ((i5 & 4) != 0) {
            i3 = 1;
        }
        if ((i5 & 8) != 0) {
            i4 = Integer.MAX_VALUE;
        }
        return (int) N(str, i2, i3, i4);
    }

    public static void P() {
        throw new ArithmeticException("Index overflow has happened.");
    }

    public static void Q(String str) {
        UninitializedPropertyAccessException uninitializedPropertyAccessException = new UninitializedPropertyAccessException(u4.j("lateinit property ", str, " has not been initialized"));
        I(v03.class.getName(), uninitializedPropertyAccessException);
        throw uninitializedPropertyAccessException;
    }

    public static String R(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        char[] cArr = new char[bArr.length * 2];
        for (int i2 = 0; i2 < bArr.length; i2++) {
            byte b2 = bArr[i2];
            int i3 = i2 * 2;
            char[] cArr2 = e;
            cArr[i3] = cArr2[(b2 & 255) >>> 4];
            cArr[i3 + 1] = cArr2[b2 & 15];
        }
        return new String(cArr);
    }

    public static final dg1 S(wb wbVar, Referrer referrer, Integer num, ki1 ki1Var, lh1 lh1Var, w40 w40Var, String str) {
        j(wbVar, "<this>");
        j(referrer, "referrer");
        j(ki1Var, "mediaUriUtil");
        j(w40Var, "customBrowseActionHandler");
        tb tbVar = wbVar.c.a;
        Uri uriB = wb1.b(he1.r(tbVar != null ? tbVar.b : null), ki1Var, MediaUriUtil$Transformation.c, ki1Var.a(R.drawable.artist_placeholder));
        String str2 = wbVar.a;
        kh1 kh1VarS = yn.s(str2);
        kh1VarS.b(referrer);
        kh1VarS.d = num;
        String strA = kh1VarS.a();
        ArrayList arrayListA = w40Var.a(strA);
        p01 p01Var = new p01(25);
        p01Var.D(2);
        p01Var.E(1);
        p01Var.H(str);
        p01Var.A();
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.HEART);
        p01Var.F(arrayListA);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        jh1Var.a = strA;
        jh1Var.g = Uri.parse(str2);
        jh1Var.f = uriB;
        jh1Var.b = wbVar.b.a;
        jh1Var.h = bundle;
        return new dg1(jh1Var, lh1Var.a(str2));
    }

    public static final dg1 T(aw1 aw1Var, String str, String str2, Integer num, ki1 ki1Var, lh1 lh1Var) {
        j(aw1Var, "<this>");
        j(ki1Var, "mediaUriUtil");
        yv1 yv1Var = aw1Var.a;
        Uri uriB = wb1.b(he1.r(yv1Var != null ? yv1Var.b : null), ki1Var, MediaUriUtil$Transformation.e, ki1Var.a(R.drawable.podcast_placeholder));
        p01 p01Var = new p01(25);
        p01Var.E(1);
        p01Var.H(str2);
        p01Var.A();
        p01Var.B(MediaBrowserItemsExtrasBuilder$IconHint.PLUS);
        Bundle bundle = (Bundle) p01Var.e;
        jh1 jh1Var = new jh1();
        String str3 = aw1Var.e;
        kh1 kh1VarS = yn.s(str3);
        kh1VarS.c = str;
        kh1VarS.d = num;
        jh1Var.a = kh1VarS.a();
        jh1Var.g = Uri.parse(str3);
        jh1Var.f = uriB;
        jh1Var.b = aw1Var.c;
        jh1Var.h = bundle;
        zv1 zv1Var = aw1Var.d;
        jh1Var.c = zv1Var != null ? zv1Var.a : null;
        return new dg1(jh1Var, lh1Var.a(str3));
    }

    public static float U() {
        return ((float) Math.pow((((double) 50.0f) + 16.0d) / 116.0d, 3.0d)) * 100.0f;
    }

    public static void V(PackageManager packageManager, ComponentName componentName) {
        ComponentInfo componentInfo;
        int componentEnabledSetting = packageManager.getComponentEnabledSetting(componentName);
        if (componentEnabledSetting != 1) {
            if (componentEnabledSetting != 2) {
                String packageName = componentName.getPackageName();
                String className = componentName.getClassName();
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 516);
                    ComponentInfo[][] componentInfoArr = {packageInfo.activities, packageInfo.services, packageInfo.providers};
                    int i2 = 0;
                    loop0: while (true) {
                        if (i2 >= 3) {
                            componentInfo = null;
                            break;
                        }
                        ComponentInfo[] componentInfoArr2 = componentInfoArr[i2];
                        if (componentInfoArr2 != null) {
                            int length = componentInfoArr2.length;
                            for (int i3 = 0; i3 < length; i3++) {
                                componentInfo = componentInfoArr2[i3];
                                if (componentInfo.name.equals(className)) {
                                    break loop0;
                                }
                            }
                        }
                        i2++;
                    }
                    if (componentInfo != null) {
                        if (componentInfo.isEnabled()) {
                            return;
                        }
                    }
                } catch (PackageManager.NameNotFoundException unused) {
                }
            }
            packageManager.setComponentEnabledSetting(componentName, 1, 1);
        }
    }

    public static void a(StringBuilder sb, Object obj, ry0 ry0Var) {
        if (ry0Var != null) {
            sb.append((CharSequence) ry0Var.invoke(obj));
            return;
        }
        if (obj == null || (obj instanceof CharSequence)) {
            sb.append((CharSequence) obj);
        } else if (obj instanceof Character) {
            sb.append(((Character) obj).charValue());
        } else {
            sb.append((CharSequence) String.valueOf(obj));
        }
    }

    public static boolean b(Object obj, Object obj2) {
        return obj == null ? obj2 == null : obj.equals(obj2);
    }

    public static final v02 c(pd2 pd2Var) {
        j(pd2Var, "<this>");
        return new v02(pd2Var);
    }

    public static final w02 d(de2 de2Var) {
        j(de2Var, "<this>");
        return new w02(de2Var);
    }

    public static void e(Context context, AttributeSet attributeSet, int i2, int i3) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.B, i2, i3);
        boolean z = typedArrayObtainStyledAttributes.getBoolean(1, false);
        typedArrayObtainStyledAttributes.recycle();
        if (z) {
            TypedValue typedValue = new TypedValue();
            if (!context.getTheme().resolveAttribute(R.attr.isMaterialTheme, typedValue, true) || (typedValue.type == 18 && typedValue.data == 0)) {
                m(context, n, "Theme.MaterialComponents");
            }
        }
        m(context, m, "Theme.AppCompat");
    }

    public static void f(Object obj, String str) {
        if (obj != null) {
            return;
        }
        IllegalStateException illegalStateException = new IllegalStateException(str.concat(" must not be null"));
        I(v03.class.getName(), illegalStateException);
        throw illegalStateException;
    }

    public static void g(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str);
        I(v03.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void h(Object obj) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException();
        I(v03.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void i(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(str.concat(" must not be null"));
        I(v03.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void j(Object obj, String str) {
        if (obj != null) {
            return;
        }
        NullPointerException nullPointerException = new NullPointerException(p(str));
        I(v03.class.getName(), nullPointerException);
        throw nullPointerException;
    }

    public static void k(Object obj, String str) {
        if (obj != null) {
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(p(str));
        I(v03.class.getName(), illegalArgumentException);
        throw illegalArgumentException;
    }

    public static void l(Context context, AttributeSet attributeSet, int[] iArr, int i2, int i3, int... iArr2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, xz1.B, i2, i3);
        boolean z = false;
        if (!typedArrayObtainStyledAttributes.getBoolean(2, false)) {
            typedArrayObtainStyledAttributes.recycle();
            return;
        }
        if (iArr2.length != 0) {
            TypedArray typedArrayObtainStyledAttributes2 = context.obtainStyledAttributes(attributeSet, iArr, i2, i3);
            for (int i4 : iArr2) {
                if (typedArrayObtainStyledAttributes2.getResourceId(i4, -1) == -1) {
                    typedArrayObtainStyledAttributes2.recycle();
                    break;
                }
            }
            typedArrayObtainStyledAttributes2.recycle();
            z = true;
        } else if (typedArrayObtainStyledAttributes.getResourceId(0, -1) != -1) {
            z = true;
        }
        typedArrayObtainStyledAttributes.recycle();
        if (!z) {
            throw new IllegalArgumentException("This component requires that you specify a valid TextAppearance attribute. Update your app theme to inherit from Theme.MaterialComponents (or a descendant).");
        }
    }

    public static void m(Context context, int[] iArr, String str) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(iArr);
        for (int i2 = 0; i2 < iArr.length; i2++) {
            if (!typedArrayObtainStyledAttributes.hasValue(i2)) {
                typedArrayObtainStyledAttributes.recycle();
                throw new IllegalArgumentException(u4.j("The style on this component requires your app theme to be ", str, " (or a descendant)."));
            }
        }
        typedArrayObtainStyledAttributes.recycle();
    }

    public static int n(int i2, int i3) {
        if (i2 < i3) {
            return -1;
        }
        return i2 == i3 ? 0 : 1;
    }

    public static String p(String str) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String name = v03.class.getName();
        int i2 = 0;
        while (!stackTrace[i2].getClassName().equals(name)) {
            i2++;
        }
        while (stackTrace[i2].getClassName().equals(name)) {
            i2++;
        }
        StackTraceElement stackTraceElement = stackTrace[i2];
        return "Parameter specified as non-null is null: method " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + ", parameter " + str;
    }

    public static int q(List list) {
        j(list, "<this>");
        return list.size() - 1;
    }

    public static String r() throws Throwable {
        BufferedReader bufferedReader;
        String str;
        StrictMode.ThreadPolicy threadPolicyAllowThreadDiskReads;
        if (f199p == null) {
            int iMyPid = q;
            if (iMyPid == 0) {
                iMyPid = Process.myPid();
                q = iMyPid;
            }
            String strTrim = null;
            strTrim = null;
            strTrim = null;
            BufferedReader bufferedReader2 = null;
            if (iMyPid > 0) {
                try {
                    str = "/proc/" + iMyPid + "/cmdline";
                    threadPolicyAllowThreadDiskReads = StrictMode.allowThreadDiskReads();
                } catch (IOException unused) {
                    bufferedReader = null;
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    bufferedReader = new BufferedReader(new FileReader(str));
                    try {
                        String line = bufferedReader.readLine();
                        he1.g(line);
                        strTrim = line.trim();
                    } catch (IOException unused2) {
                        if (bufferedReader != null) {
                        }
                        f199p = strTrim;
                        return f199p;
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        if (bufferedReader2 != null) {
                            try {
                                bufferedReader2.close();
                            } catch (IOException unused3) {
                            }
                        }
                        throw th;
                    }
                    try {
                        bufferedReader.close();
                    } catch (IOException unused4) {
                    }
                } finally {
                    StrictMode.setThreadPolicy(threadPolicyAllowThreadDiskReads);
                }
            }
            f199p = strTrim;
        }
        return f199p;
    }

    public static final zp s(c00 c00Var) {
        zp zpVar;
        zp zpVar2;
        if (!(c00Var instanceof ea0)) {
            return new zp(1, c00Var);
        }
        ea0 ea0Var = (ea0) c00Var;
        loop0: while (true) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = ea0.f120p;
            Object obj = atomicReferenceFieldUpdater.get(ea0Var);
            zpVar = null;
            if (obj == null) {
                atomicReferenceFieldUpdater.set(ea0Var, fa0.b);
                zpVar2 = null;
                break;
            }
            if (obj instanceof zp) {
                tz2 tz2Var = fa0.b;
                while (!atomicReferenceFieldUpdater.compareAndSet(ea0Var, obj, tz2Var)) {
                    if (atomicReferenceFieldUpdater.get(ea0Var) != obj) {
                        break;
                    }
                }
                zpVar2 = (zp) obj;
                break loop0;
            }
            if (obj != fa0.b && !(obj instanceof Throwable)) {
                throw new IllegalStateException(("Inconsistent state " + obj).toString());
            }
        }
        if (zpVar2 != null) {
            AtomicReferenceFieldUpdater atomicReferenceFieldUpdater2 = zp.o;
            Object obj2 = atomicReferenceFieldUpdater2.get(zpVar2);
            if (!(obj2 instanceof fv) || ((fv) obj2).d == null) {
                zp.n.set(zpVar2, 536870911);
                atomicReferenceFieldUpdater2.set(zpVar2, m4.c);
                zpVar = zpVar2;
            } else {
                zpVar2.n();
            }
            if (zpVar != null) {
                return zpVar;
            }
        }
        return new zp(2, c00Var);
    }

    public static final int t(int i2, int i3, int i4) {
        if (i4 > 0) {
            if (i2 >= i3) {
                return i3;
            }
            int i5 = i3 % i4;
            if (i5 < 0) {
                i5 += i4;
            }
            int i6 = i2 % i4;
            if (i6 < 0) {
                i6 += i4;
            }
            int i7 = (i5 - i6) % i4;
            if (i7 < 0) {
                i7 += i4;
            }
            return i3 - i7;
        }
        if (i4 >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        }
        if (i2 <= i3) {
            return i3;
        }
        int i8 = -i4;
        int i9 = i2 % i8;
        if (i9 < 0) {
            i9 += i8;
        }
        int i10 = i3 % i8;
        if (i10 < 0) {
            i10 += i8;
        }
        int i11 = (i9 - i10) % i8;
        if (i11 < 0) {
            i11 += i8;
        }
        return i3 + i11;
    }

    public static void u(Object obj, m11 m11Var) {
        ia0 ia0VarB = m11Var.b();
        Class<?> cls = m11Var.getClass();
        if (ia0VarB == null) {
            throw new NullPointerException("%s.androidInjector() returned null".replace("%s", cls.getCanonicalName()));
        }
        ia0VarB.a(obj);
    }

    public static int v(float f2) {
        if (f2 < 1.0f) {
            return -16777216;
        }
        if (f2 > 99.0f) {
            return -1;
        }
        float f3 = (f2 + 16.0f) / 116.0f;
        float f4 = f2 > 8.0f ? f3 * f3 * f3 : f2 / 903.2963f;
        float f5 = f3 * f3 * f3;
        boolean z = f5 > 0.008856452f;
        float f6 = z ? f5 : ((f3 * 116.0f) - 16.0f) / 903.2963f;
        if (!z) {
            f5 = ((f3 * 116.0f) - 16.0f) / 903.2963f;
        }
        float[] fArr = c;
        return qu.a(f6 * fArr[0], f4 * fArr[1], f5 * fArr[2]);
    }

    public static final boolean w(AssertionError assertionError) {
        String message;
        Logger logger = jp1.a;
        return (assertionError.getCause() == null || (message = assertionError.getMessage()) == null || !kotlin.text.b.i0(message, "getsockname failed")) ? false : true;
    }

    public static final boolean x(int i2) {
        return i2 == 1 || i2 == 2;
    }

    public static boolean y(String str) {
        int i2 = it1.a;
        return str == null || str.isEmpty();
    }

    public static String z(String str, Object... objArr) {
        int iIndexOf;
        String string;
        String strValueOf = String.valueOf(str);
        int i2 = 0;
        for (int i3 = 0; i3 < objArr.length; i3++) {
            Object obj = objArr[i3];
            if (obj == null) {
                string = "null";
            } else {
                try {
                    string = obj.toString();
                } catch (Exception e2) {
                    String str2 = obj.getClass().getName() + '@' + Integer.toHexString(System.identityHashCode(obj));
                    Logger.getLogger("com.google.common.base.Strings").log(Level.WARNING, "Exception during lenientFormat for " + str2, (Throwable) e2);
                    StringBuilder sbP = u4.p("<", str2, " threw ");
                    sbP.append(e2.getClass().getName());
                    sbP.append(">");
                    string = sbP.toString();
                }
            }
            objArr[i3] = string;
        }
        StringBuilder sb = new StringBuilder((objArr.length * 16) + strValueOf.length());
        int i4 = 0;
        while (i2 < objArr.length && (iIndexOf = strValueOf.indexOf("%s", i4)) != -1) {
            sb.append((CharSequence) strValueOf, i4, iIndexOf);
            sb.append(objArr[i2]);
            i4 = iIndexOf + 2;
            i2++;
        }
        sb.append((CharSequence) strValueOf, i4, strValueOf.length());
        if (i2 < objArr.length) {
            sb.append(" [");
            sb.append(objArr[i2]);
            for (int i5 = i2 + 1; i5 < objArr.length; i5++) {
                sb.append(", ");
                sb.append(objArr[i5]);
            }
            sb.append(']');
        }
        return sb.toString();
    }

    public abstract Object o(Object obj);
}
