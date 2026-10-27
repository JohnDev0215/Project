package h2;

import android.media.MediaFormat;
import android.opengl.GLES20;
import android.opengl.GLU;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import c5.InterfaceC1027d;
import d5.C1077F;
import d5.I;
import d5.d0;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import org.xmlpull.v1.XmlPullParser;

/* JADX INFO: renamed from: h2.c, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC1346c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Object f15207a = new Object();

    public static void A(String str, String str2, Throwable th) {
        synchronized (f15207a) {
            h6.n.t(str, a(str2, th));
        }
    }

    public static String a(String str, Throwable th) {
        String strReplace;
        if (th != null) {
            synchronized (f15207a) {
                Throwable cause = th;
                while (true) {
                    if (cause == null) {
                        strReplace = Log.getStackTraceString(th).trim().replace("\t", "    ");
                        break;
                    }
                    try {
                        if (cause instanceof UnknownHostException) {
                            strReplace = "UnknownHostException (no network)";
                        } else {
                            cause = cause.getCause();
                        }
                    } finally {
                    }
                }
            }
        } else {
            strReplace = null;
        }
        if (TextUtils.isEmpty(strReplace)) {
            return str;
        }
        StringBuilder sbL = A7.k.l(str, "\n  ");
        sbL.append(strReplace.replace("\n", "\n  "));
        sbL.append('\n');
        return sbL.toString();
    }

    public static void b(String str, boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException(String.valueOf(str));
        }
    }

    public static void c(boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException();
        }
    }

    public static void d() throws j {
        StringBuilder sb = new StringBuilder();
        boolean z6 = false;
        while (true) {
            int iGlGetError = GLES20.glGetError();
            if (iGlGetError == 0) {
                break;
            }
            if (z6) {
                sb.append('\n');
            }
            String strGluErrorString = GLU.gluErrorString(iGlGetError);
            if (strGluErrorString == null) {
                strGluErrorString = "error code: 0x" + Integer.toHexString(iGlGetError);
            }
            sb.append("glError: ");
            sb.append(strGluErrorString);
            z6 = true;
        }
        if (z6) {
            throw new j(sb.toString());
        }
    }

    public static void e(String str, boolean z6) throws j {
        if (!z6) {
            throw new j(str);
        }
    }

    public static void f(int i7, int i10) {
        if (i7 < 0 || i7 >= i10) {
            throw new IndexOutOfBoundsException();
        }
    }

    public static void g(Object obj) {
        obj.getClass();
    }

    public static void h(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    public static void i(String str, boolean z6) {
        if (!z6) {
            throw new IllegalStateException(String.valueOf(str));
        }
    }

    public static void j(boolean z6) {
        if (!z6) {
            throw new IllegalStateException();
        }
    }

    public static void k(Object obj) {
        if (obj == null) {
            throw new IllegalStateException();
        }
    }

    public static void l(Object obj, String str) {
        if (obj == null) {
            throw new IllegalStateException(str);
        }
    }

    public static FloatBuffer m(float[] fArr) {
        return (FloatBuffer) ByteBuffer.allocateDirect(fArr.length * 4).order(ByteOrder.nativeOrder()).asFloatBuffer().put(fArr).flip();
    }

    public static void n(String str, String str2) {
        synchronized (f15207a) {
            Log.d(str, a(str2, null));
        }
    }

    public static void o(String str, String str2, Exception exc) {
        synchronized (f15207a) {
            Log.d(str, a(str2, exc));
        }
    }

    public static void p(String str, String str2) {
        synchronized (f15207a) {
            h6.n.j(str, a(str2, null));
        }
    }

    public static void q(String str, String str2, Throwable th) {
        synchronized (f15207a) {
            h6.n.j(str, a(str2, th));
        }
    }

    public static d0 r(InterfaceC1027d interfaceC1027d, List list) {
        C1077F c1077fU = I.u();
        for (int i7 = 0; i7 < list.size(); i7++) {
            Bundle bundle = (Bundle) list.get(i7);
            bundle.getClass();
            c1077fU.a(interfaceC1027d.apply(bundle));
        }
        return c1077fU.h();
    }

    public static String s(XmlPullParser xmlPullParser, String str) {
        int attributeCount = xmlPullParser.getAttributeCount();
        for (int i7 = 0; i7 < attributeCount; i7++) {
            if (xmlPullParser.getAttributeName(i7).equals(str)) {
                return xmlPullParser.getAttributeValue(i7);
            }
        }
        return null;
    }

    public static void t(String str, String str2) {
        synchronized (f15207a) {
            Log.i(str, a(str2, null));
        }
    }

    public static boolean u(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getEventType() == 3 && xmlPullParser.getName().equals(str);
    }

    public static boolean v(XmlPullParser xmlPullParser, String str) {
        return xmlPullParser.getEventType() == 2 && xmlPullParser.getName().equals(str);
    }

    public static void w(MediaFormat mediaFormat, String str, int i7) {
        if (i7 != -1) {
            mediaFormat.setInteger(str, i7);
        }
    }

    public static void x(MediaFormat mediaFormat, List list) {
        for (int i7 = 0; i7 < list.size(); i7++) {
            mediaFormat.setByteBuffer(android.hardware.bydauto.ac.tsimpl.a.e(i7, "csd-"), ByteBuffer.wrap((byte[]) list.get(i7)));
        }
    }

    public static ArrayList y(Collection collection, InterfaceC1027d interfaceC1027d) {
        ArrayList arrayList = new ArrayList(collection.size());
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            arrayList.add((Bundle) interfaceC1027d.apply(it.next()));
        }
        return arrayList;
    }

    public static void z(String str, String str2) {
        synchronized (f15207a) {
            h6.n.t(str, a(str2, null));
        }
    }
}
