package l8;

import A7.m;
import N1.C0420a;
import T1.i;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.media.LoudnessCodecController;
import android.media.MediaCodec;
import android.media.MediaFormat;
import android.os.Bundle;
import android.os.Handler;
import android.text.method.KeyListener;
import android.text.method.NumberKeyListener;
import android.util.AttributeSet;
import android.view.Surface;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import androidx.datastore.preferences.protobuf.g0;
import c3.C1018a;
import e2.C1207o;
import e2.C1208p;
import e4.k;
import g4.x;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h2.AbstractC1347d;
import h3.y;
import h4.InterfaceC1359a;
import h6.n;
import io.sentry.C1489n1;
import j8.I;
import j8.J;
import j8.K;
import j8.L;
import java.io.File;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import l9.l;
import m7.o;
import n.AbstractC1850a;
import n2.C1906f;
import n2.RunnableC1918s;
import n4.C1956b;
import n4.C1958d;
import n7.AbstractC1998m;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import p8.AbstractC2126a;
import p8.AbstractC2136k;
import p8.C2133h;
import p8.C2137l;
import p8.EnumC2124L;
import s3.C2365D;
import t6.C2440a;
import t6.C2441b;
import t6.C2442c;
import t6.C2443d;
import t6.C2444e;
import v4.C2550c;
import z2.C2758d;
import z2.s;

/* JADX INFO: renamed from: l8.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1789g implements InterfaceC1788f, k, s2.k {
    public final Object l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f18861m;

    public /* synthetic */ C1789g(Object obj, Object obj2) {
        this.l = obj;
        this.f18861m = obj2;
    }

    public static int F(float f10) {
        return C7.a.W(f10 * 1000);
    }

    public synchronized ArrayList A(Class cls, Class cls2) {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator it = ((ArrayList) this.l).iterator();
        while (it.hasNext()) {
            List<C2550c> list = (List) ((HashMap) this.f18861m).get((String) it.next());
            if (list != null) {
                for (C2550c c2550c : list) {
                    if ((c2550c.f23005a.isAssignableFrom(cls) && cls2.isAssignableFrom(c2550c.f23006b)) && !arrayList.contains(c2550c.f23006b)) {
                        arrayList.add(c2550c.f23006b);
                    }
                }
            }
        }
        return arrayList;
    }

    public boolean B(View view) {
        C2365D c2365d = (C2365D) this.l;
        int iD = c2365d.d();
        int iC = c2365d.c();
        int iB = c2365d.b(view);
        int iA = c2365d.a(view);
        C1018a c1018a = (C1018a) this.f18861m;
        c1018a.f12352b = iD;
        c1018a.c = iC;
        c1018a.d = iB;
        c1018a.f12353e = iA;
        c1018a.f12351a = 24579;
        return c1018a.a();
    }

    public void C(AttributeSet attributeSet, int i7) {
        TypedArray typedArrayObtainStyledAttributes = ((EditText) this.l).getContext().obtainStyledAttributes(attributeSet, AbstractC1850a.f19356g, i7, 0);
        try {
            boolean z6 = typedArrayObtainStyledAttributes.hasValue(14) ? typedArrayObtainStyledAttributes.getBoolean(14, true) : true;
            typedArrayObtainStyledAttributes.recycle();
            E(z6);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    public V1.b D(InputConnection inputConnection, EditorInfo editorInfo) {
        InputConnection inputConnection2;
        C0420a c0420a = (C0420a) this.f18861m;
        if (inputConnection == null) {
            c0420a.getClass();
            inputConnection2 = null;
        } else {
            C1489n1 c1489n1 = (C1489n1) c0420a.l;
            c1489n1.getClass();
            if (!(inputConnection instanceof V1.b)) {
                inputConnection = new V1.b((EditText) c1489n1.f16624m, inputConnection, editorInfo);
            }
            inputConnection2 = inputConnection;
        }
        return (V1.b) inputConnection2;
    }

    public void E(boolean z6) {
        V1.h hVar = (V1.h) ((C1489n1) ((C0420a) this.f18861m).l).f16625n;
        if (hVar.f9325n != z6) {
            if (hVar.f9324m != null) {
                i iVarA = i.a();
                V1.g gVar = hVar.f9324m;
                iVarA.getClass();
                l.F(gVar, "initCallback cannot be null");
                ReentrantReadWriteLock reentrantReadWriteLock = iVarA.f8496a;
                reentrantReadWriteLock.writeLock().lock();
                try {
                    iVarA.f8497b.remove(gVar);
                } finally {
                    reentrantReadWriteLock.writeLock().unlock();
                }
            }
            hVar.f9325n = z6;
            if (z6) {
                V1.h.a(hVar.l, i.a().b());
            }
        }
    }

    public o G(int i7) {
        LinkedList linkedList = new LinkedList();
        LinkedList linkedList2 = new LinkedList();
        boolean z6 = false;
        while (i7 != -1) {
            J j3 = (J) ((K) this.f18861m).f17145m.get(i7);
            String str = (String) ((L) this.l).f17149m.get(j3.o);
            I i10 = j3.f17140p;
            m.c(i10);
            int iOrdinal = i10.ordinal();
            if (iOrdinal == 0) {
                linkedList2.addFirst(str);
            } else if (iOrdinal == 1) {
                linkedList.addFirst(str);
            } else if (iOrdinal == 2) {
                linkedList2.addFirst(str);
                z6 = true;
            }
            i7 = j3.f17139n;
        }
        return new o(linkedList, linkedList2, Boolean.valueOf(z6));
    }

    public void H(int i7, A2.g gVar) {
        while (true) {
            Map.Entry entry = (Map.Entry) this.f18861m;
            if (entry == null || ((C2137l) entry.getKey()).l >= i7) {
                return;
            }
            C2137l c2137l = (C2137l) ((Map.Entry) this.f18861m).getKey();
            Object value = ((Map.Entry) this.f18861m).getValue();
            C2133h c2133h = C2133h.c;
            EnumC2124L enumC2124L = c2137l.f20984m;
            boolean z6 = c2137l.f20985n;
            int i10 = c2137l.l;
            if (z6) {
                for (Object obj : (List) value) {
                    if (enumC2124L == EnumC2124L.f20951p) {
                        gVar.N(i10, 3);
                        ((AbstractC2126a) obj).f(gVar);
                        gVar.N(i10, 4);
                    } else {
                        gVar.N(i10, enumC2124L.f20955m);
                        C2133h.k(gVar, enumC2124L, obj);
                    }
                }
            } else if (enumC2124L == EnumC2124L.f20951p) {
                gVar.N(i10, 3);
                ((AbstractC2126a) value).f(gVar);
                gVar.N(i10, 4);
            } else {
                gVar.N(i10, enumC2124L.f20955m);
                C2133h.k(gVar, enumC2124L, value);
            }
            Iterator it = (Iterator) this.l;
            if (it.hasNext()) {
                this.f18861m = (Map.Entry) it.next();
            } else {
                this.f18861m = null;
            }
        }
    }

    @Override // s2.k
    public void a() {
        y yVar = (y) this.f18861m;
        MediaCodec mediaCodec = (MediaCodec) this.l;
        try {
            int i7 = AbstractC1343A.f15195a;
            if (i7 >= 30 && i7 < 33) {
                mediaCodec.stop();
            }
            if (i7 >= 35 && yVar != null) {
                yVar.H(mediaCodec);
            }
            mediaCodec.release();
        } catch (Throwable th) {
            if (AbstractC1343A.f15195a >= 35 && yVar != null) {
                yVar.H(mediaCodec);
            }
            mediaCodec.release();
            throw th;
        }
    }

    @Override // s2.k
    public void b(int i7, m2.c cVar, long j3, int i10) {
        ((MediaCodec) this.l).queueSecureInputBuffer(i7, 0, cVar.f18952i, j3, i10);
    }

    @Override // s2.k
    public void c(int i7, int i10, int i11, long j3) {
        ((MediaCodec) this.l).queueInputBuffer(i7, 0, i10, j3, i11);
    }

    @Override // s2.k
    public void d(Bundle bundle) {
        ((MediaCodec) this.l).setParameters(bundle);
    }

    @Override // s2.k
    public int e(MediaCodec.BufferInfo bufferInfo) {
        int iDequeueOutputBuffer;
        do {
            iDequeueOutputBuffer = ((MediaCodec) this.l).dequeueOutputBuffer(bufferInfo, 0L);
        } while (iDequeueOutputBuffer == -3);
        return iDequeueOutputBuffer;
    }

    @Override // s2.k
    public void f(long j3, int i7) {
        ((MediaCodec) this.l).releaseOutputBuffer(i7, j3);
    }

    @Override // s2.k
    public void flush() {
        ((MediaCodec) this.l).flush();
    }

    @Override // s2.k
    public void g(int i7) {
        ((MediaCodec) this.l).releaseOutputBuffer(i7, false);
    }

    @Override // s2.k
    public void h(C2758d c2758d, Handler handler) {
        ((MediaCodec) this.l).setOnFrameRenderedListener(new s2.b(this, c2758d, 1), handler);
    }

    @Override // s2.k
    public void i(int i7) {
        ((MediaCodec) this.l).setVideoScalingMode(i7);
    }

    @Override // s2.k
    public MediaFormat j() {
        return ((MediaCodec) this.l).getOutputFormat();
    }

    @Override // s2.k
    public void k() {
        ((MediaCodec) this.l).detachOutputSurface();
    }

    @Override // s2.k
    public ByteBuffer l(int i7) {
        return ((MediaCodec) this.l).getInputBuffer(i7);
    }

    @Override // s2.k
    public void m(Surface surface) {
        ((MediaCodec) this.l).setOutputSurface(surface);
    }

    @Override // l8.InterfaceC1788f
    public String n(int i7) {
        String str = (String) ((L) this.l).f17149m.get(i7);
        m.e(str, "strings.getString(index)");
        return str;
    }

    @Override // s2.k
    public ByteBuffer o(int i7) {
        return ((MediaCodec) this.l).getOutputBuffer(i7);
    }

    @Override // l8.InterfaceC1788f
    public boolean p(int i7) {
        return ((Boolean) G(i7).f19265n).booleanValue();
    }

    @Override // e4.InterfaceC1226b
    public boolean q(Object obj, File file, e4.h hVar) {
        return ((C1956b) this.f18861m).q(new C1958d(((BitmapDrawable) ((x) obj).get()).getBitmap(), (InterfaceC1359a) this.l), file, hVar);
    }

    @Override // s2.k
    public int r() {
        return ((MediaCodec) this.l).dequeueInputBuffer(0L);
    }

    @Override // e4.k
    public int t(e4.h hVar) {
        return 2;
    }

    @Override // l8.InterfaceC1788f
    public String u(int i7) {
        o oVarG = G(i7);
        List list = (List) oVarG.l;
        String strF0 = AbstractC1998m.f0((List) oVarG.f19264m, ".", null, null, null, 62);
        if (list.isEmpty()) {
            return strF0;
        }
        return AbstractC1998m.f0(list, "/", null, null, null, 62) + '/' + strF0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v14 */
    /* JADX WARN: Type inference failed for: r1v7, types: [java.lang.String, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r1v74 */
    /* JADX WARN: Type inference failed for: r1v75 */
    /* JADX WARN: Type inference failed for: r1v8 */
    /* JADX WARN: Type inference failed for: r3v26, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r4v1, types: [org.xmlpull.v1.XmlPullParser] */
    /* JADX WARN: Type inference failed for: r6v0 */
    /* JADX WARN: Type inference failed for: r6v1, types: [java.lang.String, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r6v10 */
    /* JADX WARN: Type inference failed for: r6v15 */
    /* JADX WARN: Type inference failed for: r6v16, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r6v17 */
    /* JADX WARN: Type inference failed for: r6v18, types: [java.lang.String, java.lang.Throwable] */
    /* JADX WARN: Type inference failed for: r6v19 */
    /* JADX WARN: Type inference failed for: r6v21 */
    /* JADX WARN: Type inference failed for: r6v9 */
    public t6.h v(InputStreamReader inputStreamReader) {
        String str;
        String str2;
        ?? r12;
        String str3;
        int i7;
        String str4;
        String str5;
        Object obj;
        String str6;
        String str7;
        String str8;
        String str9;
        CharSequence charSequenceSubSequence;
        ArrayList arrayList;
        boolean z6 = true;
        ?? r42 = (XmlPullParser) this.l;
        r42.setInput(inputStreamReader);
        r42.nextTag();
        int i10 = 2;
        ?? r62 = 0;
        r42.require(2, null, "karaoke");
        String str10 = "id";
        String attributeValue = r42.getAttributeValue(null, "id");
        if (attributeValue == null) {
            attributeValue = "NoId";
        }
        String attributeValue2 = r42.getAttributeValue(null, "d");
        m.e(attributeValue2, "getAttributeValue(...)");
        F(Float.parseFloat(attributeValue2));
        this.f18861m = new t6.h(attributeValue);
        while (r42.next() != z6) {
            if (r42.getEventType() == i10) {
                String name = r42.getName();
                if (name != null) {
                    int iHashCode = name.hashCode();
                    int i11 = 3;
                    if (iHashCode == -1335633477) {
                        ?? r13 = r62;
                        str2 = str10;
                        if (name.equals("define")) {
                            r42.require(2, r13, "define");
                            while (true) {
                                if (r42.next() == 3 && m.a(r42.getName(), "define")) {
                                    r42.require(3, r13, "define");
                                    r12 = r13;
                                    break;
                                }
                                if (r42.getEventType() == 2) {
                                    r42.require(2, r13, "color");
                                    t6.h hVar = (t6.h) this.f18861m;
                                    if (hVar == null) {
                                        m.j("song");
                                        throw r13;
                                    }
                                    LinkedHashMap linkedHashMap = hVar.c;
                                    String str11 = str2;
                                    String attributeValue3 = r42.getAttributeValue(r13, str11);
                                    String attributeValue4 = r42.getAttributeValue(r13, "r");
                                    m.e(attributeValue4, "getAttributeValue(...)");
                                    int i12 = Integer.parseInt(attributeValue4);
                                    String attributeValue5 = r42.getAttributeValue(r13, "g");
                                    m.e(attributeValue5, "getAttributeValue(...)");
                                    int i13 = Integer.parseInt(attributeValue5);
                                    String attributeValue6 = r42.getAttributeValue(r13, "b");
                                    m.e(attributeValue6, "getAttributeValue(...)");
                                    linkedHashMap.put(attributeValue3, new C2440a(i12, i13, Integer.parseInt(attributeValue6)));
                                    r42.nextTag();
                                    r42.require(3, r13, "color");
                                    str2 = str11;
                                }
                            }
                        } else {
                            str = str2;
                            i10 = 2;
                            n.t("KmlDeserializer", "The XML node " + r42.getName() + " is not supported.");
                            str10 = str;
                            z6 = true;
                            r62 = 0;
                        }
                    } else {
                        String str12 = "";
                        if (iHashCode != 3575) {
                            if (iHashCode == 3094784) {
                                if (name.equals("duet")) {
                                    r42.require(i10, r62, "duet");
                                    while (true) {
                                        if (r42.next() == 3 && m.a(r42.getName(), "duet")) {
                                            r42.require(3, r62, "duet");
                                            break;
                                        }
                                        if (r42.getEventType() == i10) {
                                            r42.require(i10, r62, "singer");
                                            String attributeValue7 = r42.getAttributeValue(r62, "gender");
                                            m.e(attributeValue7, "getAttributeValue(...)");
                                            t6.g gVar = new t6.g(attributeValue7, z6);
                                            while (true) {
                                                if (r42.next() == 3 && m.a(r42.getName(), "singer")) {
                                                    break;
                                                }
                                                if (r42.getEventType() == i10) {
                                                    r42.require(i10, r62, "part");
                                                    int next = r42.next();
                                                    ArrayList arrayList2 = gVar.c;
                                                    if (next == 4) {
                                                        String text = r42.getText();
                                                        m.e(text, "getText(...)");
                                                        arrayList2.add(Q8.h.g1(text).toString());
                                                    } else {
                                                        arrayList2.add("");
                                                    }
                                                    r42.nextTag();
                                                    r42.require(3, null, "part");
                                                    r62 = 0;
                                                    z6 = true;
                                                    i10 = 2;
                                                }
                                            }
                                            r42.require(3, r62, "singer");
                                            t6.h hVar2 = (t6.h) this.f18861m;
                                            if (hVar2 == null) {
                                                m.j("song");
                                                throw r62;
                                            }
                                            hVar2.f22494e.add(gVar);
                                        }
                                    }
                                }
                            } else if (iHashCode == 259109393 && name.equals("coldstartoffset")) {
                                r42.require(i10, r62, "coldstartoffset");
                                if (((t6.h) this.f18861m) == null) {
                                    m.j("song");
                                    throw r62;
                                }
                                String attributeValue8 = r42.getAttributeValue(r62, "B");
                                m.e(attributeValue8, "getAttributeValue(...)");
                                Integer.parseInt(attributeValue8);
                                t6.h hVar3 = (t6.h) this.f18861m;
                                if (hVar3 == null) {
                                    m.j("song");
                                    throw r62;
                                }
                                String attributeValue9 = r42.getAttributeValue(r62, "C");
                                m.e(attributeValue9, "getAttributeValue(...)");
                                hVar3.f22492a = Integer.parseInt(attributeValue9);
                                if (((t6.h) this.f18861m) == null) {
                                    m.j("song");
                                    throw r62;
                                }
                                String attributeValue10 = r42.getAttributeValue(r62, "D");
                                m.e(attributeValue10, "getAttributeValue(...)");
                                Integer.parseInt(attributeValue10);
                                r42.nextTag();
                                r42.require(3, r62, "coldstartoffset");
                            }
                            n.t("KmlDeserializer", "The XML node " + r42.getName() + " is not supported.");
                            str10 = str;
                            z6 = true;
                            r62 = 0;
                        } else {
                            ?? r32 = r62;
                            String str13 = "pg";
                            if (name.equals("pg")) {
                                r42.require(2, r32, "pg");
                                String attributeValue11 = r42.getAttributeValue(r32, str10);
                                m.c(attributeValue11);
                                if (Q8.o.z0(attributeValue11, "lyrics", false)) {
                                    t6.h hVar4 = (t6.h) this.f18861m;
                                    if (hVar4 == null) {
                                        m.j("song");
                                        throw null;
                                    }
                                    if (hVar4.f22494e.isEmpty()) {
                                        t6.h hVar5 = (t6.h) this.f18861m;
                                        if (hVar5 == null) {
                                            m.j("song");
                                            throw null;
                                        }
                                        hVar5.f22494e.add(new t6.g("male", false));
                                    }
                                    String attributeValue12 = r42.getAttributeValue(null, "t");
                                    m.e(attributeValue12, "getAttributeValue(...)");
                                    int iF = F(Float.parseFloat(attributeValue12));
                                    String attributeValue13 = r42.getAttributeValue(null, "d");
                                    m.e(attributeValue13, "getAttributeValue(...)");
                                    C2444e c2444e = new C2444e(iF, F(Float.parseFloat(attributeValue13)));
                                    while (true) {
                                        int next2 = r42.next();
                                        ArrayList arrayList3 = c2444e.c;
                                        if (next2 == i11 && m.a(r42.getName(), str13)) {
                                            r42.require(i11, null, str13);
                                            t6.h hVar6 = (t6.h) this.f18861m;
                                            if (hVar6 == null) {
                                                m.j("song");
                                                throw null;
                                            }
                                            if (hVar6.f22494e.size() > 1) {
                                                t6.h hVar7 = (t6.h) this.f18861m;
                                                if (hVar7 == null) {
                                                    m.j("song");
                                                    throw null;
                                                }
                                                for (t6.g gVar2 : hVar7.f22494e) {
                                                    C2444e c2444e2 = new C2444e(c2444e.f22481a, c2444e.f22482b);
                                                    Iterator it = arrayList3.iterator();
                                                    while (true) {
                                                        boolean zHasNext = it.hasNext();
                                                        arrayList = c2444e2.c;
                                                        if (!zHasNext) {
                                                            break;
                                                        }
                                                        C2442c c2442c = (C2442c) it.next();
                                                        if (gVar2.c.contains(c2442c.f22477a)) {
                                                            arrayList.add(c2442c);
                                                        }
                                                    }
                                                    if (!arrayList.isEmpty()) {
                                                        gVar2.f22491b.add(c2444e2);
                                                    }
                                                }
                                            } else if (!arrayList3.isEmpty()) {
                                                t6.h hVar8 = (t6.h) this.f18861m;
                                                if (hVar8 == null) {
                                                    m.j("song");
                                                    throw null;
                                                }
                                                ((t6.g) hVar8.f22494e.get(0)).f22491b.add(c2444e);
                                            }
                                            str6 = null;
                                            str2 = str10;
                                            str3 = str13;
                                        } else if (r42.getEventType() == 2 && m.a(r42.getName(), "ln")) {
                                            r42.require(2, null, "ln");
                                            String attributeValue14 = r42.getAttributeValue(null, "c");
                                            if (attributeValue14 != null) {
                                                C2442c c2442c2 = new C2442c(attributeValue14);
                                                while (true) {
                                                    int next3 = r42.next();
                                                    ArrayList arrayList4 = c2442c2.f22478b;
                                                    C2444e c2444e3 = c2444e;
                                                    if (next3 != 3 || !m.a(r42.getName(), "ln")) {
                                                        String str14 = str13;
                                                        String str15 = str10;
                                                        String str16 = str12;
                                                        if (r42.getEventType() != 2) {
                                                            c2444e = c2444e3;
                                                        } else {
                                                            r42.require(2, null, "lyr");
                                                            String attributeValue15 = r42.getAttributeValue(null, "t");
                                                            m.e(attributeValue15, "getAttributeValue(...)");
                                                            int iF2 = F(Float.parseFloat(attributeValue15));
                                                            t6.h hVar9 = (t6.h) this.f18861m;
                                                            if (hVar9 == null) {
                                                                m.j("song");
                                                                throw null;
                                                            }
                                                            if (hVar9.f22493b > iF2) {
                                                                hVar9.f22493b = iF2;
                                                            }
                                                            String attributeValue16 = r42.getAttributeValue(null, "d");
                                                            m.e(attributeValue16, "getAttributeValue(...)");
                                                            int iF3 = F(Float.parseFloat(attributeValue16));
                                                            C2442c c2442c3 = c2442c2;
                                                            String attributeValue17 = r42.getAttributeValue(null, "s");
                                                            m.e(attributeValue17, "getAttributeValue(...)");
                                                            String upperCase = attributeValue17.toUpperCase(Locale.ROOT);
                                                            m.e(upperCase, "toUpperCase(...)");
                                                            ArrayList arrayList5 = arrayList3;
                                                            String attributeValue18 = r42.getAttributeValue(null, "c");
                                                            m.e(attributeValue18, "getAttributeValue(...)");
                                                            arrayList4.add(new C2443d(upperCase, attributeValue18, iF2, iF3));
                                                            r42.nextTag();
                                                            r42.require(3, null, "lyr");
                                                            c2444e = c2444e3;
                                                            c2442c2 = c2442c3;
                                                            arrayList3 = arrayList5;
                                                        }
                                                        str10 = str15;
                                                        str12 = str16;
                                                        str13 = str14;
                                                    } else if (arrayList4.isEmpty()) {
                                                        c2444e = c2444e3;
                                                    } else {
                                                        if (arrayList4.size() > 0) {
                                                            C2443d c2443d = (C2443d) AbstractC1347d.e(1, arrayList4);
                                                            String str17 = c2443d.c;
                                                            char[] cArr = {' '};
                                                            m.f(str17, "<this>");
                                                            int length = str17.length() - 1;
                                                            if (length >= 0) {
                                                                while (true) {
                                                                    int i14 = length - 1;
                                                                    str8 = str10;
                                                                    char cCharAt = str17.charAt(length);
                                                                    str7 = str13;
                                                                    str9 = str12;
                                                                    int i15 = 1;
                                                                    int i16 = 0;
                                                                    while (true) {
                                                                        if (i16 >= i15) {
                                                                            i16 = -1;
                                                                            break;
                                                                        }
                                                                        if (cCharAt == cArr[i16]) {
                                                                            i15 = 1;
                                                                            break;
                                                                        }
                                                                        i15 = 1;
                                                                        i16++;
                                                                    }
                                                                    if ((i16 >= 0 ? i15 : 0) == 0) {
                                                                        charSequenceSubSequence = str17.subSequence(0, length + i15);
                                                                        break;
                                                                    }
                                                                    if (i14 < 0) {
                                                                        break;
                                                                    }
                                                                    length = i14;
                                                                    str10 = str8;
                                                                    str12 = str9;
                                                                    str13 = str7;
                                                                }
                                                            } else {
                                                                str7 = str13;
                                                                str8 = str10;
                                                                str9 = str12;
                                                            }
                                                            charSequenceSubSequence = str9;
                                                            c2443d.c = charSequenceSubSequence.toString();
                                                        } else {
                                                            str7 = str13;
                                                            str8 = str10;
                                                            str9 = str12;
                                                        }
                                                        arrayList3.add(c2442c2);
                                                        r42.require(3, null, "ln");
                                                        c2444e = c2444e3;
                                                        i11 = 3;
                                                        str10 = str8;
                                                        str12 = str9;
                                                        str13 = str7;
                                                    }
                                                }
                                            }
                                            i11 = 3;
                                        } else {
                                            c2444e = c2444e;
                                            str10 = str10;
                                            str12 = str12;
                                            str13 = str13;
                                            i11 = 3;
                                        }
                                    }
                                } else {
                                    String str18 = "pg";
                                    str2 = str10;
                                    Object obj2 = "";
                                    if (attributeValue11.equals("credits_page")) {
                                        t6.h hVar10 = (t6.h) this.f18861m;
                                        if (hVar10 == null) {
                                            m.j("song");
                                            throw null;
                                        }
                                        String attributeValue19 = r42.getAttributeValue(null, "t");
                                        m.e(attributeValue19, "getAttributeValue(...)");
                                        int iF4 = F(Float.parseFloat(attributeValue19));
                                        String attributeValue20 = r42.getAttributeValue(null, "d");
                                        m.e(attributeValue20, "getAttributeValue(...)");
                                        F(Float.parseFloat(attributeValue20));
                                        hVar10.d = new C2441b(iF4);
                                        while (true) {
                                            if (r42.next() == 3) {
                                                str3 = str18;
                                                if (m.a(r42.getName(), str3)) {
                                                    r42.require(3, null, str3);
                                                    str6 = null;
                                                    break;
                                                }
                                            } else {
                                                str3 = str18;
                                            }
                                            if (r42.getEventType() == 2) {
                                                r42.require(2, null, "ln");
                                                if (r42.next() == 4) {
                                                    t6.h hVar11 = (t6.h) this.f18861m;
                                                    if (hVar11 == null) {
                                                        m.j("song");
                                                        throw null;
                                                    }
                                                    ArrayList arrayList6 = hVar11.d.f22476b;
                                                    String text2 = r42.getText();
                                                    m.e(text2, "getText(...)");
                                                    arrayList6.add(Q8.h.g1(text2).toString());
                                                    r42.nextTag();
                                                    obj = obj2;
                                                    str5 = null;
                                                } else {
                                                    str5 = null;
                                                    t6.h hVar12 = (t6.h) this.f18861m;
                                                    if (hVar12 == null) {
                                                        m.j("song");
                                                        throw null;
                                                    }
                                                    obj = obj2;
                                                    hVar12.d.f22476b.add(obj);
                                                }
                                                r42.require(3, str5, "ln");
                                                obj2 = obj;
                                            }
                                            str18 = str3;
                                        }
                                    } else {
                                        str3 = str18;
                                        if (!attributeValue11.equals("closing_page")) {
                                            r12 = 0;
                                        } else {
                                            if (((t6.h) this.f18861m) == null) {
                                                m.j("song");
                                                throw null;
                                            }
                                            String attributeValue21 = r42.getAttributeValue(null, "t");
                                            m.e(attributeValue21, "getAttributeValue(...)");
                                            F(Float.parseFloat(attributeValue21));
                                            String attributeValue22 = r42.getAttributeValue(null, "d");
                                            m.e(attributeValue22, "getAttributeValue(...)");
                                            F(Float.parseFloat(attributeValue22));
                                            while (true) {
                                                i7 = 3;
                                                if (r42.next() == 3 && m.a(r42.getName(), str3)) {
                                                    break;
                                                }
                                                r42.getEventType();
                                            }
                                            str4 = null;
                                            r42.require(3, null, str3);
                                            r42.require(i7, str4, str3);
                                            r12 = str4;
                                        }
                                    }
                                }
                                i7 = 3;
                                str4 = str6;
                                r42.require(i7, str4, str3);
                                r12 = str4;
                            } else {
                                str = str10;
                                i10 = 2;
                                n.t("KmlDeserializer", "The XML node " + r42.getName() + " is not supported.");
                                str10 = str;
                                z6 = true;
                                r62 = 0;
                            }
                        }
                    }
                    r62 = r12;
                    str10 = str2;
                    z6 = true;
                    i10 = 2;
                }
                str = str10;
                n.t("KmlDeserializer", "The XML node " + r42.getName() + " is not supported.");
                str10 = str;
                z6 = true;
                r62 = 0;
            }
        }
        t6.h hVar13 = (t6.h) this.f18861m;
        if (hVar13 == null) {
            m.j("song");
            throw null;
        }
        Iterator it2 = hVar13.f22494e.iterator();
        while (it2.hasNext()) {
            for (C2444e c2444e4 : ((t6.g) it2.next()).f22491b) {
                C2443d c2443d2 = (C2443d) ((C2442c) c2444e4.c.get(0)).f22478b.get(0);
                C2443d c2443d3 = (C2443d) AbstractC1347d.e(1, ((C2442c) AbstractC1347d.e(1, c2444e4.c)).f22478b);
                int i17 = c2443d2.f22479a;
                c2444e4.f22481a = i17;
                c2444e4.f22482b = (c2443d3.f22479a + c2443d3.f22480b) - i17;
            }
        }
        t6.h hVar14 = (t6.h) this.f18861m;
        if (hVar14 != null) {
            return hVar14;
        }
        m.j("song");
        throw null;
    }

    public void w(C1906f c1906f) {
        synchronized (c1906f) {
        }
        Handler handler = (Handler) this.l;
        if (handler != null) {
            handler.post(new RunnableC1918s(this, 2, c1906f));
        }
    }

    public View x(int i7, int i10, int i11, int i12) {
        View viewU;
        C2365D c2365d = (C2365D) this.l;
        int iD = c2365d.d();
        int iC = c2365d.c();
        int i13 = i10 > i7 ? 1 : -1;
        View view = null;
        while (i7 != i10) {
            switch (c2365d.f22175a) {
                case 0:
                    viewU = c2365d.f22176b.u(i7);
                    break;
                default:
                    viewU = c2365d.f22176b.u(i7);
                    break;
            }
            int iB = c2365d.b(viewU);
            int iA = c2365d.a(viewU);
            C1018a c1018a = (C1018a) this.f18861m;
            c1018a.f12352b = iD;
            c1018a.c = iC;
            c1018a.d = iB;
            c1018a.f12353e = iA;
            if (i11 != 0) {
                c1018a.f12351a = i11;
                if (c1018a.a()) {
                    return viewU;
                }
            }
            if (i12 != 0) {
                c1018a.f12351a = i12;
                if (c1018a.a()) {
                    view = viewU;
                }
            }
            i7 += i13;
        }
        return view;
    }

    public KeyListener y(KeyListener keyListener) {
        if (keyListener instanceof NumberKeyListener) {
            return keyListener;
        }
        ((C1489n1) ((C0420a) this.f18861m).l).getClass();
        if (keyListener instanceof V1.d) {
            return keyListener;
        }
        if (keyListener == null) {
            return null;
        }
        return keyListener instanceof NumberKeyListener ? keyListener : new V1.d(keyListener);
    }

    public synchronized List z(String str) {
        List arrayList;
        if (!((ArrayList) this.l).contains(str)) {
            ((ArrayList) this.l).add(str);
        }
        arrayList = (List) ((HashMap) this.f18861m).get(str);
        if (arrayList == null) {
            arrayList = new ArrayList();
            ((HashMap) this.f18861m).put(str, arrayList);
        }
        return arrayList;
    }

    public C1789g(int i7) {
        switch (i7) {
            case 9:
                this.l = new ArrayList();
                this.f18861m = new HashMap();
                break;
            default:
                this.l = XmlPullParserFactory.newInstance().newPullParser();
                break;
        }
    }

    public C1789g(L l, K k10) {
        m.f(l, "strings");
        m.f(k10, "qualifiedNames");
        this.l = l;
        this.f18861m = k10;
    }

    public C1789g(z2.o oVar, s sVar) {
        this.l = oVar;
        this.f18861m = sVar;
        new C1208p(new C1207o());
    }

    public C1789g(EditText editText) {
        this.l = editText;
        C0420a c0420a = new C0420a();
        c0420a.l = new C1489n1(editText);
        this.f18861m = c0420a;
    }

    public C1789g(MediaCodec mediaCodec, y yVar) {
        this.l = mediaCodec;
        this.f18861m = yVar;
        if (AbstractC1343A.f15195a < 35 || yVar == null) {
            return;
        }
        LoudnessCodecController loudnessCodecController = (LoudnessCodecController) yVar.o;
        if (loudnessCodecController == null || loudnessCodecController.addMediaCodec(mediaCodec)) {
            AbstractC1346c.j(((HashSet) yVar.f15499m).add(mediaCodec));
        }
    }

    public C1789g(C2365D c2365d) {
        this.l = c2365d;
        C1018a c1018a = new C1018a();
        c1018a.f12351a = 0;
        this.f18861m = c1018a;
    }

    public C1789g(AbstractC2136k abstractC2136k) {
        C2133h c2133h = abstractC2136k.l;
        c2133h.getClass();
        Iterator it = ((g0) c2133h.f20980a.entrySet()).iterator();
        this.l = it;
        if (it.hasNext()) {
            this.f18861m = (Map.Entry) it.next();
        }
    }
}
