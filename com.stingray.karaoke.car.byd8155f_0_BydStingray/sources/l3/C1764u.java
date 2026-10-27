package l3;

import A.C0050y0;
import F1.InterfaceC0255n;
import F8.AbstractC0287w;
import Q7.C0661n;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0666t;
import T7.AbstractC0789h;
import T7.C0790i;
import android.media.MediaCodec;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.v4.media.session.MediaSessionCompat$Token;
import android.util.Log;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.profileinstaller.ProfileInstallReceiver;
import com.stingray.karaoke.scoring.MicrophoneCommand;
import h2.AbstractC1346c;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.Stack;
import java.util.WeakHashMap;
import l4.C1770a;
import l8.C1789g;
import n0.C1873b;
import n0.InterfaceC1889r;
import n6.AbstractC1982o;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import o5.AbstractC2047c;
import o8.AbstractC2063h;
import o8.C2061f;
import p2.RunnableC2092g;
import p8.AbstractC2129d;
import q.C2147d;
import q.InterfaceC2156m;
import q.MenuC2151h;
import q.MenuItemC2152i;
import q.SubMenuC2161r;
import q.ViewOnKeyListenerC2148e;
import q3.InterfaceC2177d;
import q8.C2202f;
import q8.C2203g;
import q8.C2206j;
import q8.EnumC2204h;
import q8.EnumC2211o;
import r.C2250i;
import r.f0;
import r2.InterfaceC2281c;
import t1.C2428d;
import v2.InterfaceC2545c;
import x.AbstractC2641j;

/* JADX INFO: renamed from: l3.u, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1764u implements k4.r, l5.o, n4.l, f0, InterfaceC2177d, InterfaceC0659l, InterfaceC2156m, InterfaceC2281c, s2.l, InterfaceC0255n, t4.b, InterfaceC2545c {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f18608m;

    public /* synthetic */ C1764u(int i7, boolean z6) {
        this.l = i7;
    }

    /* JADX WARN: Code restructure failed: missing block: B:346:0x015b, code lost:
    
        r6 = 0;
     */
    /* JADX WARN: Removed duplicated region for block: B:153:0x0280  */
    /* JADX WARN: Removed duplicated region for block: B:205:0x03e7  */
    /* JADX WARN: Removed duplicated region for block: B:209:0x0401  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static java.util.ArrayList R(l3.C1764u r38, java.lang.String r39) {
        /*
            Method dump skipped, instruction units count: 1796
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.C1764u.R(l3.u, java.lang.String):java.util.ArrayList");
    }

    @Override // k4.r
    public k4.q A(k4.w wVar) {
        return new C1770a((e7.f) this.f18608m);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x0047  */
    @Override // Q7.InterfaceC0659l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object B(T7.C0790i r18, java.lang.Object r19) {
        /*
            Method dump skipped, instruction units count: 312
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.C1764u.B(T7.i, java.lang.Object):java.lang.Object");
    }

    @Override // Q7.InterfaceC0659l
    public /* bridge */ /* synthetic */ Object C(InterfaceC0666t interfaceC0666t, Object obj) {
        Y(interfaceC0666t, (StringBuilder) obj);
        return m7.q.f19267a;
    }

    @Override // Q7.InterfaceC0659l
    public Object G(T7.z zVar, Object obj) {
        ((C2203g) this.f18608m).P(zVar, (StringBuilder) obj, true);
        return m7.q.f19267a;
    }

    @Override // l5.o
    public Object H() {
        switch (this.l) {
            case 3:
                Class cls = (Class) this.f18608m;
                try {
                    return l5.w.f18668a.a(cls);
                } catch (Exception e10) {
                    throw new RuntimeException("Unable to create instance of " + cls + ". Registering an InstanceCreator or a TypeAdapter for this type, or adding a no-args constructor may fix this problem.", e10);
                }
            default:
                Constructor constructor = (Constructor) this.f18608m;
                try {
                    return constructor.newInstance(null);
                } catch (IllegalAccessException e11) {
                    h6.r rVar = AbstractC2047c.f20529a;
                    throw new RuntimeException("Unexpected IllegalAccessException occurred (Gson 2.11.0). Certain ReflectionAccessFilter features require Java >= 9 to work correctly. If you are not using ReflectionAccessFilter, report this to the Gson maintainers.", e11);
                } catch (InstantiationException e12) {
                    throw new RuntimeException("Failed to invoke constructor '" + AbstractC2047c.b(constructor) + "' with no args", e12);
                } catch (InvocationTargetException e13) {
                    throw new RuntimeException("Failed to invoke constructor '" + AbstractC2047c.b(constructor) + "' with no args", e13.getCause());
                }
        }
    }

    @Override // Q7.InterfaceC0659l
    public Object I(T7.y yVar, Object obj) {
        C0790i c0790iP0;
        String str;
        StringBuilder sb = (StringBuilder) obj;
        C2203g c2203g = (C2203g) this.f18608m;
        c2203g.getClass();
        boolean z6 = yVar.E() == 4;
        if (!c2203g.r()) {
            c2203g.y(sb, yVar, null);
            List listA0 = yVar.A0();
            A7.m.e(listA0, "klass.contextReceivers");
            c2203g.C(sb, listA0);
            if (!z6) {
                C0661n c0661nE = yVar.e();
                A7.m.e(c0661nE, "klass.visibility");
                c2203g.h0(c0661nE, sb);
            }
            if ((yVar.E() != 2 || yVar.h() != 4) && (!M5.b.a(yVar.E()) || yVar.h() != 1)) {
                int iH = yVar.h();
                A7.k.s(iH, "klass.modality");
                c2203g.L(iH, C2203g.v(yVar), sb);
            }
            c2203g.K(yVar, sb);
            c2203g.N(sb, c2203g.q().contains(EnumC2204h.INNER) && yVar.d0(), "inner");
            c2203g.N(sb, c2203g.q().contains(EnumC2204h.DATA) && yVar.H0(), "data");
            c2203g.N(sb, c2203g.q().contains(EnumC2204h.INLINE) && yVar.n(), "inline");
            c2203g.N(sb, c2203g.q().contains(EnumC2204h.VALUE) && yVar.b0(), "value");
            c2203g.N(sb, c2203g.q().contains(EnumC2204h.FUN) && yVar.L(), "fun");
            if (yVar.F()) {
                str = "companion object";
            } else {
                int iC = AbstractC2641j.c(yVar.E());
                if (iC == 0) {
                    str = "class";
                } else if (iC == 1) {
                    str = "interface";
                } else if (iC == 2) {
                    str = "enum class";
                } else if (iC == 3) {
                    str = "enum entry";
                } else if (iC == 4) {
                    str = "annotation class";
                } else {
                    if (iC != 5) {
                        throw new A9.k();
                    }
                    str = "object";
                }
            }
            sb.append(c2203g.I(str));
        }
        boolean zL = r8.d.l(yVar);
        C2206j c2206j = c2203g.f21281a;
        if (zL) {
            if (((Boolean) c2206j.f21303F.a(C2206j.f21298W[30], c2206j)).booleanValue()) {
                if (c2203g.r()) {
                    sb.append("companion object");
                }
                C2203g.X(sb);
                InterfaceC0657j interfaceC0657jO = yVar.o();
                if (interfaceC0657jO != null) {
                    sb.append("of ");
                    C2061f name = interfaceC0657jO.getName();
                    A7.m.e(name, "containingDeclaration.name");
                    sb.append(c2203g.O(name, false));
                }
            }
            if (c2203g.u() || !A7.m.a(yVar.getName(), AbstractC2063h.f20576b)) {
                if (!c2203g.r()) {
                    C2203g.X(sb);
                }
                C2061f name2 = yVar.getName();
                A7.m.e(name2, "descriptor.name");
                sb.append(c2203g.O(name2, true));
            }
        } else {
            if (!c2203g.r()) {
                C2203g.X(sb);
            }
            c2203g.P(yVar, sb, true);
        }
        if (!z6) {
            List listS = yVar.s();
            A7.m.e(listS, "klass.declaredTypeParameters");
            c2203g.d0(sb, listS, false);
            c2203g.A(yVar, sb);
            if (!M5.b.a(yVar.E())) {
                if (((Boolean) c2206j.f21326i.a(C2206j.f21298W[7], c2206j)).booleanValue() && (c0790iP0 = yVar.p0()) != null) {
                    sb.append(" ");
                    c2203g.y(sb, c0790iP0, null);
                    C0790i c0790i = c0790iP0;
                    C0661n c0661nE2 = c0790i.e();
                    A7.m.e(c0661nE2, "primaryConstructor.visibility");
                    c2203g.h0(c0661nE2, sb);
                    sb.append(c2203g.I("constructor"));
                    List listD0 = c0790i.D0();
                    A7.m.e(listD0, "primaryConstructor.valueParameters");
                    c2203g.g0(sb, listD0, c0790iP0.T());
                }
            }
            if (!((Boolean) c2206j.f21338w.a(C2206j.f21298W[21], c2206j)).booleanValue() && !N7.i.D(yVar.j())) {
                Collection collectionO = yVar.C().o();
                A7.m.e(collectionO, "klass.typeConstructor.supertypes");
                if (!collectionO.isEmpty() && (collectionO.size() != 1 || !N7.i.w((AbstractC0287w) collectionO.iterator().next()))) {
                    C2203g.X(sb);
                    sb.append(": ");
                    AbstractC1998m.d0(collectionO, sb, ", ", (124 & 4) != 0 ? "" : null, (124 & 8) != 0 ? "" : null, -1, "...", (124 & 64) != 0 ? null : new C2202f(c2203g, 2));
                }
            }
            c2203g.i0(sb, listS);
        }
        return m7.q.f19267a;
    }

    @Override // t4.b
    public void J() {
        Iterator it = A4.q.e((Set) this.f18608m).iterator();
        while (it.hasNext()) {
            ((x4.c) it.next()).J();
        }
    }

    @Override // Q7.InterfaceC0659l
    public Object K(T7.Q q4, Object obj) {
        ((C2203g) this.f18608m).f0(q4, true, (StringBuilder) obj, true);
        return m7.q.f19267a;
    }

    public void L(AbstractC2129d abstractC2129d) {
        if (!abstractC2129d.r()) {
            if (!(abstractC2129d instanceof p8.y)) {
                String strValueOf = String.valueOf(abstractC2129d.getClass());
                throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(strValueOf.length() + 49), "Has a new type of ByteString been created? Found ", strValueOf));
            }
            p8.y yVar = (p8.y) abstractC2129d;
            L(yVar.f20998n);
            L(yVar.o);
            return;
        }
        int size = abstractC2129d.size();
        int[] iArr = p8.y.f20996s;
        int iBinarySearch = Arrays.binarySearch(iArr, size);
        if (iBinarySearch < 0) {
            iBinarySearch = (-(iBinarySearch + 1)) - 1;
        }
        int i7 = iArr[iBinarySearch + 1];
        Stack stack = (Stack) this.f18608m;
        if (stack.isEmpty() || ((AbstractC2129d) stack.peek()).size() >= i7) {
            stack.push(abstractC2129d);
            return;
        }
        int i10 = iArr[iBinarySearch];
        AbstractC2129d yVar2 = (AbstractC2129d) stack.pop();
        while (!stack.isEmpty() && ((AbstractC2129d) stack.peek()).size() < i10) {
            yVar2 = new p8.y((AbstractC2129d) stack.pop(), yVar2);
        }
        p8.y yVar3 = new p8.y(yVar2, abstractC2129d);
        while (!stack.isEmpty()) {
            int[] iArr2 = p8.y.f20996s;
            int iBinarySearch2 = Arrays.binarySearch(iArr2, yVar3.f20997m);
            if (iBinarySearch2 < 0) {
                iBinarySearch2 = (-(iBinarySearch2 + 1)) - 1;
            }
            if (((AbstractC2129d) stack.peek()).size() >= iArr2[iBinarySearch2 + 1]) {
                break;
            } else {
                yVar3 = new p8.y((AbstractC2129d) stack.pop(), yVar3);
            }
        }
        stack.push(yVar3);
    }

    @Override // Q7.InterfaceC0659l
    public Object M(T7.H h10, Object obj) {
        A7.m.f(h10, "descriptor");
        C2203g.n((C2203g) this.f18608m, h10, (StringBuilder) obj);
        return m7.q.f19267a;
    }

    public void N(float f10, float f11, float f12, float f13) {
        h3.y yVar = (h3.y) this.f18608m;
        InterfaceC1889r interfaceC1889rM = yVar.m();
        long jF = h6.j.f(m0.f.d(yVar.y()) - (f12 + f10), m0.f.b(yVar.y()) - (f13 + f11));
        if (m0.f.d(jF) < 0.0f || m0.f.b(jF) < 0.0f) {
            throw new IllegalArgumentException("Width and height must be greater than or equal to zero");
        }
        yVar.L(jF);
        interfaceC1889rM.j(f10, f11);
    }

    @Override // Q7.InterfaceC0659l
    public Object O(T7.I i7, Object obj) {
        Z(i7, (StringBuilder) obj, "getter");
        return m7.q.f19267a;
    }

    public void P(Exception exc) {
        AbstractC1346c.q("MediaCodecAudioRenderer", "Audio sink error", exc);
        C1789g c1789g = ((p2.x) this.f18608m).f20838O0;
        Handler handler = (Handler) c1789g.l;
        if (handler != null) {
            handler.post(new RunnableC2092g(c1789g, exc, 6));
        }
    }

    public void Q(O o, Bundle bundle) throws RemoteException {
        if (bundle == null) {
            bundle = new Bundle();
        }
        bundle.putInt("extra_service_version", 2);
        Bundle bundle2 = new Bundle();
        bundle2.putString("data_media_item_id", "androidx.media3.session.MediaLibraryService");
        bundle2.putParcelable("data_media_session_token", h6.n.g(o, MediaSessionCompat$Token.CREATOR));
        bundle2.putBundle("data_root_hints", bundle);
        V(1, bundle2);
    }

    public void S(float f10, long j3) {
        InterfaceC1889r interfaceC1889rM = ((h3.y) this.f18608m).m();
        interfaceC1889rM.j(m0.c.d(j3), m0.c.e(j3));
        interfaceC1889rM.c(f10);
        interfaceC1889rM.j(-m0.c.d(j3), -m0.c.e(j3));
    }

    public void T(float f10, float f11, long j3) {
        InterfaceC1889r interfaceC1889rM = ((h3.y) this.f18608m).m();
        interfaceC1889rM.j(m0.c.d(j3), m0.c.e(j3));
        interfaceC1889rM.b(f10, f11);
        interfaceC1889rM.j(-m0.c.d(j3), -m0.c.e(j3));
    }

    public void U(MicrophoneCommand microphoneCommand) {
        A7.m.f(microphoneCommand, "command");
        Log.d("===", "sendCommand: " + microphoneCommand);
        switch (AbstractC1982o.f20123a[microphoneCommand.ordinal()]) {
            case 1:
                a0(AbstractC1999n.D(156, 1, 145, 2, 153, 0));
                return;
            case 2:
                a0(AbstractC1999n.D(145, 0));
                return;
            case 3:
                a0(AbstractC1999n.D(134, 1));
                return;
            case 4:
                a0(AbstractC1999n.D(134, 0));
                return;
            case 5:
                a0(AbstractC1999n.D(142, 0));
                return;
            case 6:
                a0(AbstractC1999n.D(142, 1));
                return;
            default:
                throw new A9.k();
        }
    }

    public void V(int i7, Bundle bundle) throws RemoteException {
        Message messageObtain = Message.obtain();
        messageObtain.what = i7;
        messageObtain.arg1 = 2;
        if (bundle != null) {
            messageObtain.setData(bundle);
        }
        ((Messenger) this.f18608m).send(messageObtain);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0073, code lost:
    
        if (r1 >= 26) goto L45;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0078, code lost:
    
        if (r1 >= 34) goto L45;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int W(e2.C1208p r6) {
        /*
            r5 = this;
            r5 = 4
            r0 = 1
            java.lang.String r1 = r6.f14316n
            r2 = 0
            if (r1 == 0) goto L84
            boolean r1 = e2.L.i(r1)
            if (r1 != 0) goto Lf
            goto L84
        Lf:
            int r1 = h2.AbstractC1343A.f15195a
            java.lang.String r6 = r6.f14316n
            r6.getClass()
            int r1 = h2.AbstractC1343A.f15195a
            r3 = -1
            int r4 = r6.hashCode()
            switch(r4) {
                case -1487656890: goto L63;
                case -1487464693: goto L58;
                case -1487464690: goto L4d;
                case -1487394660: goto L42;
                case -1487018032: goto L37;
                case -879272239: goto L2c;
                case -879258763: goto L21;
                default: goto L20;
            }
        L20:
            goto L6d
        L21:
            java.lang.String r4 = "image/png"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L2a
            goto L6d
        L2a:
            r3 = 6
            goto L6d
        L2c:
            java.lang.String r4 = "image/bmp"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L35
            goto L6d
        L35:
            r3 = 5
            goto L6d
        L37:
            java.lang.String r4 = "image/webp"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L40
            goto L6d
        L40:
            r3 = r5
            goto L6d
        L42:
            java.lang.String r4 = "image/jpeg"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L4b
            goto L6d
        L4b:
            r3 = 3
            goto L6d
        L4d:
            java.lang.String r4 = "image/heif"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L56
            goto L6d
        L56:
            r3 = 2
            goto L6d
        L58:
            java.lang.String r4 = "image/heic"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L61
            goto L6d
        L61:
            r3 = r0
            goto L6d
        L63:
            java.lang.String r4 = "image/avif"
            boolean r6 = r6.equals(r4)
            if (r6 != 0) goto L6c
            goto L6d
        L6c:
            r3 = r2
        L6d:
            switch(r3) {
                case 0: goto L76;
                case 1: goto L71;
                case 2: goto L71;
                case 3: goto L7a;
                case 4: goto L7a;
                case 5: goto L7a;
                case 6: goto L7a;
                default: goto L70;
            }
        L70:
            goto L7f
        L71:
            r6 = 26
            if (r1 < r6) goto L7f
            goto L7a
        L76:
            r6 = 34
            if (r1 < r6) goto L7f
        L7a:
            int r5 = n2.AbstractC1905e.d(r5, r2, r2, r2)
            goto L83
        L7f:
            int r5 = n2.AbstractC1905e.d(r0, r2, r2, r2)
        L83:
            return r5
        L84:
            int r5 = n2.AbstractC1905e.d(r2, r2, r2, r2)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.C1764u.W(e2.p):int");
    }

    public void X(float f10, float f11) {
        ((h3.y) this.f18608m).m().j(f10, f11);
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x01a7  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void Y(Q7.InterfaceC0666t r10, java.lang.StringBuilder r11) {
        /*
            Method dump skipped, instruction units count: 451
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l3.C1764u.Y(Q7.t, java.lang.StringBuilder):void");
    }

    public void Z(Q7.K k10, StringBuilder sb, String str) {
        C2203g c2203g = (C2203g) this.f18608m;
        C2206j c2206j = c2203g.f21281a;
        int iOrdinal = ((EnumC2211o) c2206j.f21304G.a(C2206j.f21298W[31], c2206j)).ordinal();
        if (iOrdinal != 0) {
            if (iOrdinal != 1) {
                return;
            }
            Y(k10, sb);
        } else {
            c2203g.K(k10, sb);
            sb.append(str.concat(" for "));
            Q7.L lC1 = ((T7.F) k10).c1();
            A7.m.e(lC1, "descriptor.correspondingProperty");
            C2203g.n(c2203g, lC1, sb);
        }
    }

    @Override // q.InterfaceC2156m
    public void a(MenuC2151h menuC2151h, boolean z6) {
        if (menuC2151h instanceof SubMenuC2161r) {
            ((SubMenuC2161r) menuC2151h).f21115w.j().c(false);
        }
        InterfaceC2156m interfaceC2156m = ((C2250i) this.f18608m).f21485p;
        if (interfaceC2156m != null) {
            interfaceC2156m.a(menuC2151h, z6);
        }
    }

    public void a0(List list) {
        Log.d("===", "writeCommands: " + list);
        byte[] bArr = new byte[list.size()];
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            bArr[i7] = (byte) ((Number) list.get(i7)).intValue();
        }
        ((N1.S) this.f18608m).getClass();
    }

    @Override // s2.l
    public void b(int i7, m2.c cVar, long j3, int i10) {
        ((MediaCodec) this.f18608m).queueSecureInputBuffer(i7, 0, cVar.f18952i, j3, i10);
    }

    @Override // s2.l
    public void c(int i7, int i10, int i11, long j3) {
        ((MediaCodec) this.f18608m).queueInputBuffer(i7, 0, i10, j3, i11);
    }

    @Override // s2.l
    public void d(Bundle bundle) {
        ((MediaCodec) this.f18608m).setParameters(bundle);
    }

    @Override // Q7.InterfaceC0659l
    public Object e(T7.J j3, Object obj) {
        Z(j3, (StringBuilder) obj, "setter");
        return m7.q.f19267a;
    }

    @Override // q3.InterfaceC2177d
    public void f(int i7, Serializable serializable) {
        String str;
        switch (i7) {
            case 1:
                str = "RESULT_INSTALL_SUCCESS";
                break;
            case 2:
                str = "RESULT_ALREADY_INSTALLED";
                break;
            case 3:
                str = "RESULT_UNSUPPORTED_ART_VERSION";
                break;
            case 4:
                str = "RESULT_NOT_WRITABLE";
                break;
            case 5:
                str = "RESULT_DESIRED_FORMAT_UNSUPPORTED";
                break;
            case 6:
                str = "RESULT_BASELINE_PROFILE_NOT_FOUND";
                break;
            case 7:
                str = "RESULT_IO_EXCEPTION";
                break;
            case 8:
                str = "RESULT_PARSE_EXCEPTION";
                break;
            case 9:
            default:
                str = "";
                break;
            case 10:
                str = "RESULT_INSTALL_SKIP_FILE_SUCCESS";
                break;
            case 11:
                str = "RESULT_DELETE_SKIP_FILE_SUCCESS";
                break;
        }
        if (i7 == 6 || i7 == 7 || i7 == 8) {
            h6.n.i("ProfileInstaller", str, (Throwable) serializable);
        } else {
            Log.d("ProfileInstaller", str);
        }
        ((ProfileInstallReceiver) this.f18608m).setResultCode(i7);
    }

    @Override // s2.l
    public void flush() {
    }

    @Override // n4.l
    public long h(long j3) throws IOException {
        if (j3 < 0) {
            return 0L;
        }
        long j10 = j3;
        while (j10 > 0) {
            InputStream inputStream = (InputStream) this.f18608m;
            long jSkip = inputStream.skip(j10);
            if (jSkip > 0) {
                j10 -= jSkip;
            } else {
                if (inputStream.read() == -1) {
                    break;
                }
                j10--;
            }
        }
        return j3 - j10;
    }

    @Override // n4.l
    public short j() throws IOException {
        int i7 = ((InputStream) this.f18608m).read();
        if (i7 != -1) {
            return (short) i7;
        }
        throw new n4.k();
    }

    @Override // s2.l
    public void k() {
    }

    @Override // Q7.InterfaceC0659l
    public Object l(T7.B b10, Object obj) {
        StringBuilder sb = (StringBuilder) obj;
        C2203g c2203g = (C2203g) this.f18608m;
        c2203g.getClass();
        c2203g.T(b10.f8767q, "package-fragment", sb);
        if (c2203g.f21281a.n()) {
            sb.append(" in ");
            c2203g.P(b10.o(), sb, false);
        }
        return m7.q.f19267a;
    }

    @Override // Q7.InterfaceC0659l
    public Object m(D8.v vVar, Object obj) {
        StringBuilder sb = (StringBuilder) obj;
        C2203g c2203g = (C2203g) this.f18608m;
        c2203g.getClass();
        c2203g.y(sb, vVar, null);
        C0661n c0661n = vVar.f1284q;
        A7.m.e(c0661n, "typeAlias.visibility");
        c2203g.h0(c0661n, sb);
        c2203g.K(vVar, sb);
        sb.append(c2203g.I("typealias"));
        sb.append(" ");
        c2203g.P(vVar, sb, true);
        c2203g.d0(sb, vVar.s(), false);
        c2203g.A(vVar, sb);
        sb.append(" = ");
        sb.append(c2203g.Y(vVar.e1()));
        return m7.q.f19267a;
    }

    @Override // Q7.InterfaceC0659l
    public Object o(T7.u uVar, Object obj) {
        A7.m.f(uVar, "descriptor");
        ((StringBuilder) obj).append(uVar.getName());
        return m7.q.f19267a;
    }

    @Override // n4.l
    public int p() {
        return j() | (j() << 8);
    }

    @Override // Q7.InterfaceC0659l
    public Object q(T7.w wVar, Object obj) {
        StringBuilder sb = (StringBuilder) obj;
        C2203g c2203g = (C2203g) this.f18608m;
        c2203g.getClass();
        c2203g.T(wVar.f8913p, "package", sb);
        if (c2203g.f21281a.n()) {
            sb.append(" in context of ");
            c2203g.P(wVar.o, sb, false);
        }
        return m7.q.f19267a;
    }

    @Override // r.f0
    public void r(MenuC2151h menuC2151h, MenuItemC2152i menuItemC2152i) {
        ((ViewOnKeyListenerC2148e) this.f18608m).f21034q.removeCallbacksAndMessages(menuC2151h);
    }

    @Override // q3.InterfaceC2177d
    public void s() {
        Log.d("ProfileInstaller", "DIAGNOSTIC_PROFILE_IS_COMPRESSED");
    }

    @Override // s2.l
    public void shutdown() {
    }

    @Override // s2.l
    public void start() {
    }

    @Override // q.InterfaceC2156m
    public boolean v(MenuC2151h menuC2151h) {
        C2250i c2250i = (C2250i) this.f18608m;
        if (menuC2151h == c2250i.f21484n) {
            return false;
        }
        ((SubMenuC2161r) menuC2151h).f21116x.getClass();
        c2250i.getClass();
        InterfaceC2156m interfaceC2156m = c2250i.f21485p;
        if (interfaceC2156m != null) {
            return interfaceC2156m.v(menuC2151h);
        }
        return false;
    }

    @Override // n4.l
    public int w(int i7, byte[] bArr) throws n4.k {
        int i10 = 0;
        int i11 = 0;
        while (i10 < i7 && (i11 = ((InputStream) this.f18608m).read(bArr, i10, i7 - i10)) != -1) {
            i10 += i11;
        }
        if (i10 == 0 && i11 == -1) {
            throw new n4.k();
        }
        return i10;
    }

    @Override // F1.InterfaceC0255n
    public F1.V x(View view, F1.V v10) {
        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.f18608m;
        if (!Objects.equals(coordinatorLayout.f11734y, v10)) {
            coordinatorLayout.f11734y = v10;
            boolean z6 = v10.a() > 0;
            coordinatorLayout.f11735z = z6;
            coordinatorLayout.setWillNotDraw(!z6 && coordinatorLayout.getBackground() == null);
            F1.U u4 = v10.f2321a;
            if (!u4.j()) {
                int childCount = coordinatorLayout.getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = coordinatorLayout.getChildAt(i7);
                    int[] iArr = F1.H.f2303a;
                    if (childAt.getFitsSystemWindows() && ((C2428d) childAt.getLayoutParams()).f22429a != null && u4.j()) {
                        break;
                    }
                }
            }
            coordinatorLayout.requestLayout();
        }
        return v10;
    }

    @Override // r.f0
    public void y(MenuC2151h menuC2151h, MenuItemC2152i menuItemC2152i) {
        ViewOnKeyListenerC2148e viewOnKeyListenerC2148e = (ViewOnKeyListenerC2148e) this.f18608m;
        viewOnKeyListenerC2148e.f21034q.removeCallbacksAndMessages(null);
        ArrayList arrayList = viewOnKeyListenerC2148e.f21036s;
        int size = arrayList.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                i7 = -1;
                break;
            } else if (menuC2151h == ((C2147d) arrayList.get(i7)).f21020b) {
                break;
            } else {
                i7++;
            }
        }
        if (i7 == -1) {
            return;
        }
        int i10 = i7 + 1;
        viewOnKeyListenerC2148e.f21034q.postAtTime(new b7.p(this, i10 < arrayList.size() ? (C2147d) arrayList.get(i10) : null, menuItemC2152i, menuC2151h, 2), menuC2151h, SystemClock.uptimeMillis() + 200);
    }

    @Override // Q7.InterfaceC0659l
    public Object z(AbstractC0789h abstractC0789h, Object obj) {
        ((C2203g) this.f18608m).b0(abstractC0789h, (StringBuilder) obj, true);
        return m7.q.f19267a;
    }

    public /* synthetic */ C1764u(Object obj, int i7) {
        this.l = i7;
        this.f18608m = obj;
    }

    public C1764u(a1.b bVar) {
        this.l = 29;
        this.f18608m = new w.J(w.O.f23082a, bVar);
    }

    public C1764u(int i7) {
        this.l = i7;
        switch (i7) {
            case 6:
                this.f18608m = new C1873b();
                break;
            case 7:
                this.f18608m = new C0050y0(9);
                break;
            case 14:
                this.f18608m = new Stack();
                break;
            case 20:
                this.f18608m = new o2.d(28);
                break;
            case 26:
                this.f18608m = Collections.newSetFromMap(new WeakHashMap());
                break;
            case 28:
                this.f18608m = new io.sentry.hints.i(14);
                break;
            default:
                this.f18608m = new e7.f(25, (byte) 0);
                break;
        }
    }
}
