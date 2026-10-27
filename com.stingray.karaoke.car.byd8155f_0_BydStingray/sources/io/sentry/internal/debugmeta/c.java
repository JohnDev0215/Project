package io.sentry.internal.debugmeta;

import A.I0;
import A2.g;
import A4.q;
import A7.k;
import B5.K;
import B5.m;
import B8.InterfaceC0089a;
import B8.t;
import E0.C0159i;
import E0.F;
import E0.h0;
import E0.t0;
import E8.e;
import F8.A;
import F8.AbstractC0287w;
import F8.N;
import F8.a0;
import H8.f;
import K.L0;
import N5.c0;
import N5.e0;
import Q7.InterfaceC0671y;
import Q7.Q;
import W2.j;
import Y2.l;
import android.content.Context;
import android.media.session.MediaController;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.support.v4.media.MediaDescriptionCompat;
import android.text.TextUtils;
import d0.C1053b;
import d0.n;
import d5.I;
import d5.d0;
import e8.C1236a;
import g1.AbstractC1274a;
import g1.C1279f;
import g3.C1292a;
import h2.AbstractC1346c;
import h2.InterfaceC1345b;
import h2.y;
import h3.D;
import h3.E;
import h3.H;
import h6.r;
import h8.AbstractC1386c;
import h8.p;
import io.sentry.C1489n1;
import io.sentry.C1524x0;
import io.sentry.EnumC1495p1;
import j7.AbstractC1554a;
import j8.C1559d;
import j8.C1562g;
import j8.C1567l;
import j8.C1574t;
import j8.C1579y;
import j8.G;
import j8.W;
import j8.Z;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Callable;
import java.util.logging.Logger;
import java.util.zip.GZIPOutputStream;
import k1.C1596a;
import k1.i;
import k2.InterfaceC1606g;
import k2.h;
import l3.C1736A;
import l3.C1737B;
import l3.C1739D;
import l3.C1740E;
import l3.C1745a;
import l3.C1746b;
import l3.InterfaceC1750f;
import l3.O;
import l3.P;
import l3.X;
import l8.InterfaceC1788f;
import l9.d;
import n7.AbstractC2000o;
import n7.u;
import o8.C2061f;
import p8.AbstractC2136k;
import x.AbstractC2641j;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class c implements a, InterfaceC0089a, InterfaceC1606g, P0.c, n, l, D, InterfaceC1345b {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f16569m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f16570n;

    public /* synthetic */ c(int i7, boolean z6) {
        this.l = i7;
    }

    public static void O(c cVar, C1279f c1279f, float f10, int i7) {
        if ((i7 & 2) != 0) {
            f10 = 0;
        }
        cVar.N(c1279f, f10, 0);
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x003a A[Catch: IOException -> 0x005b, TryCatch #0 {IOException -> 0x005b, blocks: (B:2:0x0000, B:3:0x000a, B:5:0x000d, B:7:0x001e, B:9:0x0026, B:21:0x0042, B:19:0x003a, B:20:0x003d, B:23:0x0047, B:24:0x004a, B:27:0x005d), top: B:31:0x0000 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static io.sentry.internal.debugmeta.c Q(java.lang.String... r12) {
        /*
            int r0 = r12.length     // Catch: java.io.IOException -> L5b
            r9.l[] r0 = new r9.C2322l[r0]     // Catch: java.io.IOException -> L5b
            r9.i r1 = new r9.i     // Catch: java.io.IOException -> L5b
            r1.<init>()     // Catch: java.io.IOException -> L5b
            r2 = 0
            r3 = r2
        La:
            int r4 = r12.length     // Catch: java.io.IOException -> L5b
            if (r3 >= r4) goto L5d
            r4 = r12[r3]     // Catch: java.io.IOException -> L5b
            java.lang.String[] r5 = Z3.a.f10885p     // Catch: java.io.IOException -> L5b
            r6 = 34
            r1.X(r6)     // Catch: java.io.IOException -> L5b
            int r7 = r4.length()     // Catch: java.io.IOException -> L5b
            r8 = r2
            r9 = r8
        L1c:
            if (r8 >= r7) goto L45
            char r10 = r4.charAt(r8)     // Catch: java.io.IOException -> L5b
            r11 = 128(0x80, float:1.8E-43)
            if (r10 >= r11) goto L2b
            r10 = r5[r10]     // Catch: java.io.IOException -> L5b
            if (r10 != 0) goto L38
            goto L42
        L2b:
            r11 = 8232(0x2028, float:1.1535E-41)
            if (r10 != r11) goto L32
            java.lang.String r10 = "\\u2028"
            goto L38
        L32:
            r11 = 8233(0x2029, float:1.1537E-41)
            if (r10 != r11) goto L42
            java.lang.String r10 = "\\u2029"
        L38:
            if (r9 >= r8) goto L3d
            r1.d0(r4, r9, r8)     // Catch: java.io.IOException -> L5b
        L3d:
            r1.c0(r10)     // Catch: java.io.IOException -> L5b
            int r9 = r8 + 1
        L42:
            int r8 = r8 + 1
            goto L1c
        L45:
            if (r9 >= r7) goto L4a
            r1.d0(r4, r9, r7)     // Catch: java.io.IOException -> L5b
        L4a:
            r1.X(r6)     // Catch: java.io.IOException -> L5b
            r1.z()     // Catch: java.io.IOException -> L5b
            long r4 = r1.f21932m     // Catch: java.io.IOException -> L5b
            r9.l r4 = r1.F(r4)     // Catch: java.io.IOException -> L5b
            r0[r3] = r4     // Catch: java.io.IOException -> L5b
            int r3 = r3 + 1
            goto La
        L5b:
            r12 = move-exception
            goto L6f
        L5d:
            io.sentry.internal.debugmeta.c r1 = new io.sentry.internal.debugmeta.c     // Catch: java.io.IOException -> L5b
            java.lang.Object r12 = r12.clone()     // Catch: java.io.IOException -> L5b
            java.lang.String[] r12 = (java.lang.String[]) r12     // Catch: java.io.IOException -> L5b
            r9.y r0 = r9.AbstractC2312b.g(r0)     // Catch: java.io.IOException -> L5b
            r2 = 14
            r1.<init>(r12, r2, r0)     // Catch: java.io.IOException -> L5b
            return r1
        L6f:
            java.lang.AssertionError r0 = new java.lang.AssertionError
            r0.<init>(r12)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.internal.debugmeta.c.Q(java.lang.String[]):io.sentry.internal.debugmeta.c");
    }

    @Override // P0.c
    public int A(int i7) {
        do {
            g gVar = (g) this.f16570n;
            gVar.c(i7);
            i7 = ((BreakIterator) gVar.f321p).preceding(i7);
            if (i7 == -1 || i7 == 0) {
                return -1;
            }
        } while (Character.isWhitespace(((CharSequence) this.f16569m).charAt(i7 - 1)));
        return i7;
    }

    public void B(F f10) {
        if (f10.V()) {
            ((t0) this.f16570n).add(f10);
        } else {
            d.h0("DepthSortedSet.add called on an unattached node");
            throw null;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00ea  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void C(java.lang.String r13) {
        /*
            Method dump skipped, instruction units count: 418
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.internal.debugmeta.c.C(java.lang.String):void");
    }

    public void D(byte[] bArr) {
        C1489n1 c1489n1 = (C1489n1) this.f16569m;
        if (c1489n1 == null) {
            throw new RuntimeException("got binary data when not reconstructing a packet");
        }
        ((ArrayList) c1489n1.f16625n).add(bArr);
        int size = ((ArrayList) c1489n1.f16625n).size();
        j7.d dVar = (j7.d) c1489n1.f16624m;
        if (size == dVar.f17068e) {
            ArrayList arrayList = (ArrayList) c1489n1.f16625n;
            byte[][] bArr2 = (byte[][]) arrayList.toArray(new byte[arrayList.size()][]);
            Logger logger = AbstractC1554a.f17064a;
            dVar.d = AbstractC1554a.b(dVar.d, bArr2);
            dVar.f17068e = -1;
            c1489n1.f16624m = null;
            c1489n1.f16625n = new ArrayList();
        } else {
            dVar = null;
        }
        if (dVar != null) {
            this.f16569m = null;
            b7.g gVar = (b7.g) this.f16570n;
            if (gVar != null) {
                gVar.f12287a.N0("packet", dVar);
            }
        }
    }

    public void E(C1740E c1740e, int i7) {
        C1736A c1736a = (C1736A) this.f16569m;
        if ((c1736a.f18506a.getFlags() & 4) == 0) {
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", h6.n.g(c1740e, MediaDescriptionCompat.CREATOR));
        bundle.putInt("android.support.v4.media.session.command.ARGUMENT_INDEX", i7);
        c1736a.f18506a.sendCommand("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT", bundle, null);
    }

    public B5.n F() throws IOException {
        InputStream errorStream;
        ((m) this.f16569m).getClass();
        HttpURLConnection httpURLConnectionC = m.c("https://cdn-settings.segment.com/v1/projects/" + ((String) this.f16570n) + "/settings");
        int responseCode = httpURLConnectionC.getResponseCode();
        if (responseCode == 200) {
            try {
                errorStream = httpURLConnectionC.getInputStream();
            } catch (IOException unused) {
                errorStream = httpURLConnectionC.getErrorStream();
            }
            return new B5.n(httpURLConnectionC, errorStream, null, 1);
        }
        httpURLConnectionC.disconnect();
        StringBuilder sbJ = M5.b.j("HTTP ", responseCode, ": ");
        sbJ.append(httpURLConnectionC.getResponseMessage());
        throw new IOException(sbJ.toString());
    }

    public byte[] G() {
        Callable callable;
        if (((byte[]) this.f16569m) == null && (callable = (Callable) this.f16570n) != null) {
            this.f16569m = (byte[]) callable.call();
        }
        byte[] bArr = (byte[]) this.f16569m;
        return bArr != null ? bArr : new byte[0];
    }

    public a0 H(C1236a c1236a) {
        a0 a0VarZ;
        A a10 = c1236a.f14486f;
        return (a10 == null || (a0VarZ = AbstractC1274a.Z(a10)) == null) ? (f) ((m7.n) this.f16569m).getValue() : a0VarZ;
    }

    public AbstractC0287w I(Q q4, C1236a c1236a) {
        A7.m.f(q4, "typeParameter");
        A7.m.f(c1236a, "typeAttr");
        return (AbstractC0287w) ((e) this.f16570n).b(new N(q4, c1236a));
    }

    public C1737B J() {
        MediaController.PlaybackInfo playbackInfo = ((C1736A) this.f16569m).f18506a.getPlaybackInfo();
        if (playbackInfo != null) {
            return new C1737B(playbackInfo.getPlaybackType(), new C1746b(new C1745a(playbackInfo.getAudioAttributes())), playbackInfo.getVolumeControl(), playbackInfo.getMaxVolume(), playbackInfo.getCurrentVolume());
        }
        return null;
    }

    public X K() {
        C1736A c1736a = (C1736A) this.f16569m;
        InterfaceC1750f interfaceC1750fA = c1736a.f18508e.a();
        if (interfaceC1750fA != null) {
            try {
                return interfaceC1750fA.e();
            } catch (RemoteException e10) {
                h6.n.i("MediaControllerCompat", "Dead object in getPlaybackState.", e10);
            }
        }
        PlaybackState playbackState = c1736a.f18506a.getPlaybackState();
        if (playbackState != null) {
            return X.a(playbackState);
        }
        return null;
    }

    public String L(e4.e eVar) {
        String str;
        synchronized (((A4.m) this.f16569m)) {
            str = (String) ((A4.m) this.f16569m).a(eVar);
        }
        if (str == null) {
            i4.f fVar = (i4.f) ((P) this.f16570n).i();
            try {
                eVar.b(fVar.l);
                byte[] bArrDigest = fVar.l.digest();
                char[] cArr = q.f433b;
                synchronized (cArr) {
                    for (int i7 = 0; i7 < bArrDigest.length; i7++) {
                        byte b10 = bArrDigest[i7];
                        int i10 = i7 * 2;
                        char[] cArr2 = q.f432a;
                        cArr[i10] = cArr2[(b10 & 255) >>> 4];
                        cArr[i10 + 1] = cArr2[b10 & 15];
                    }
                    str = new String(cArr);
                }
            } finally {
                ((P) this.f16570n).B(fVar);
            }
        }
        synchronized (((A4.m) this.f16569m)) {
            ((A4.m) this.f16569m).d(eVar, str);
        }
        return str;
    }

    public C1739D M() {
        return new C1739D(((C1736A) this.f16569m).f18506a.getTransportControls());
    }

    public void N(C1279f c1279f, float f10, float f11) {
        int i7 = c1279f.f14720b;
        String str = "top";
        if (i7 != 0) {
            if (i7 != 1) {
                h6.n.j("CCL", "horizontalAnchorIndexToAnchorName: Unknown horizontal index");
            } else {
                str = "bottom";
            }
        }
        C1596a c1596a = new C1596a(new char[0]);
        c1596a.s(i.s(c1279f.f14719a.toString()));
        c1596a.s(i.s(str));
        c1596a.s(new k1.e(f10));
        c1596a.s(new k1.e(f11));
        ((k1.g) this.f16569m).G((String) this.f16570n, c1596a);
    }

    public Properties P() {
        String str = (String) this.f16569m;
        try {
            File file = new File(str);
            if (!file.isFile() || !file.canRead()) {
                return null;
            }
            BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
            try {
                Properties properties = new Properties();
                properties.load(bufferedInputStream);
                bufferedInputStream.close();
                return properties;
            } finally {
            }
        } catch (IOException e10) {
            ((C1524x0) this.f16570n).p(EnumC1495p1.ERROR, e10, "Failed to load Sentry configuration from file: %s", str);
            return null;
        }
    }

    public boolean R(F f10) {
        if (f10.V()) {
            return ((t0) this.f16570n).remove(f10);
        }
        d.h0("DepthSortedSet.remove called on an unattached node");
        throw null;
    }

    public void S(C1740E c1740e) {
        C1736A c1736a = (C1736A) this.f16569m;
        if ((c1736a.f18506a.getFlags() & 4) == 0) {
            throw new UnsupportedOperationException("This session doesn't support queue management operations");
        }
        Bundle bundle = new Bundle();
        bundle.putParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION", h6.n.g(c1740e, MediaDescriptionCompat.CREATOR));
        c1736a.f18506a.sendCommand("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM", bundle, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x012c  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x01b4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public o7.C2055g T(F8.W r17, java.util.List r18, e8.C1236a r19) {
        /*
            Method dump skipped, instruction units count: 540
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.internal.debugmeta.c.T(F8.W, java.util.List, e8.a):o7.g");
    }

    public B5.n U(String str) {
        HttpURLConnection httpURLConnectionG = ((m) this.f16569m).g(str);
        return new B5.n(httpURLConnectionG, null, TextUtils.equals("gzip", httpURLConnectionG.getRequestProperty("Content-Encoding")) ? new GZIPOutputStream(httpURLConnectionG.getOutputStream()) : httpURLConnectionG.getOutputStream(), 0);
    }

    public t V(C2061f c2061f, String str) {
        A7.m.f(c2061f, "name");
        String strB = c2061f.b();
        A7.m.e(strB, "name.asString()");
        return new t(this, new p(strB.concat(str)));
    }

    @Override // h3.D
    public void a(y yVar, B2.t tVar, H h10) {
    }

    @Override // B8.InterfaceC0091c
    public List b(K k10, AbstractC2136k abstractC2136k, int i7) {
        List list;
        A7.m.f(abstractC2136k, "proto");
        k.p(i7, "kind");
        boolean z6 = abstractC2136k instanceof C1567l;
        C8.a aVar = (C8.a) this.f16569m;
        if (z6) {
            list = (List) ((C1567l) abstractC2136k).k(aVar.f466b);
        } else if (abstractC2136k instanceof C1579y) {
            list = (List) ((C1579y) abstractC2136k).k(aVar.d);
        } else {
            if (!(abstractC2136k instanceof G)) {
                throw new IllegalStateException(("Unknown message: " + abstractC2136k).toString());
            }
            int iC = AbstractC2641j.c(i7);
            if (iC == 1) {
                list = (List) ((G) abstractC2136k).k(aVar.f467e);
            } else if (iC == 2) {
                list = (List) ((G) abstractC2136k).k(aVar.f468f);
            } else {
                if (iC != 3) {
                    throw new IllegalStateException("Unsupported callable kind with property proto");
                }
                list = (List) ((G) abstractC2136k).k(aVar.f469g);
            }
        }
        if (list == null) {
            list = u.l;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) k10.f670b));
        }
        return arrayList;
    }

    @Override // h3.D
    public void c(h2.t tVar) {
        if (tVar.v() != 0 || (tVar.v() & 128) == 0) {
            return;
        }
        tVar.I(6);
        int iA = tVar.a() / 4;
        int i7 = 0;
        while (true) {
            h3.F f10 = (h3.F) this.f16570n;
            if (i7 >= iA) {
                f10.getClass();
                f10.f15280g.remove(0);
                return;
            }
            B2.N n10 = (B2.N) this.f16569m;
            tVar.f(0, 4, n10.d);
            n10.r(0);
            int i10 = n10.i(16);
            n10.u(3);
            if (i10 == 0) {
                n10.u(13);
            } else {
                int i11 = n10.i(13);
                if (f10.f15280g.get(i11) == null) {
                    f10.f15280g.put(i11, new E(new j(f10, i11)));
                    f10.f15285m++;
                }
            }
            i7++;
        }
    }

    @Override // B8.InterfaceC0089a
    public Object e(K k10, G g7, AbstractC0287w abstractC0287w) {
        A7.m.f(g7, "proto");
        C1559d c1559d = (C1559d) AbstractC1386c.h(g7, ((C8.a) this.f16569m).f471i);
        if (c1559d == null) {
            return null;
        }
        return ((M.t) this.f16570n).M(abstractC0287w, c1559d, (InterfaceC1788f) k10.f670b);
    }

    public boolean equals(Object obj) {
        switch (this.l) {
            case 11:
                if (!(obj instanceof E1.b)) {
                    return false;
                }
                E1.b bVar = (E1.b) obj;
                Object obj2 = bVar.f1715a;
                String str = (String) this.f16569m;
                if (obj2 != str && (obj2 == null || !obj2.equals(str))) {
                    return false;
                }
                String str2 = (String) this.f16570n;
                Object obj3 = bVar.f1716b;
                return obj3 == str2 || (obj3 != null && obj3.equals(str2));
            default:
                return super.equals(obj);
        }
    }

    @Override // B8.InterfaceC0091c
    public ArrayList f(B8.u uVar) {
        A7.m.f(uVar, "container");
        Iterable iterable = (List) uVar.f810e.k(((C8.a) this.f16569m).c);
        if (iterable == null) {
            iterable = u.l;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) uVar.f670b));
        }
        return arrayList;
    }

    @Override // B8.InterfaceC0091c
    public List g(K k10, AbstractC2136k abstractC2136k, int i7, int i10, Z z6) {
        A7.m.f(abstractC2136k, "callableProto");
        k.p(i7, "kind");
        Iterable iterable = (List) z6.k(((C8.a) this.f16569m).f472j);
        if (iterable == null) {
            iterable = u.l;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) k10.f670b));
        }
        return arrayList;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:128:0x0251  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x025d  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x0368  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x036d  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:197:0x0385  */
    /* JADX WARN: Removed duplicated region for block: B:238:0x0362 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ae  */
    /* JADX WARN: Type inference failed for: r13v18 */
    /* JADX WARN: Type inference failed for: r13v19, types: [boolean] */
    /* JADX WARN: Type inference failed for: r13v20 */
    /* JADX WARN: Type inference failed for: r14v2 */
    /* JADX WARN: Type inference failed for: r14v3 */
    /* JADX WARN: Type inference failed for: r14v5 */
    /* JADX WARN: Type inference failed for: r2v25 */
    /* JADX WARN: Type inference failed for: r2v26 */
    /* JADX WARN: Type inference failed for: r2v30 */
    /* JADX WARN: Type inference failed for: r3v51 */
    /* JADX WARN: Type inference failed for: r3v52 */
    /* JADX WARN: Type inference failed for: r3v53 */
    /*  JADX ERROR: UnsupportedOperationException in pass: RegionMakerVisitor
        java.lang.UnsupportedOperationException
        	at java.base/java.util.Collections$UnmodifiableCollection.add(Collections.java:1091)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker$1.leaveRegion(SwitchRegionMaker.java:390)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:23)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaksForCase(SwitchRegionMaker.java:370)
        	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.insertBreaks(SwitchRegionMaker.java:85)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.leaveRegion(PostProcessRegions.java:33)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:70)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.Collections$UnmodifiableCollection.forEach(Collections.java:1116)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.lambda$traverseInternal$0(DepthRegionTraversal.java:68)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseInternal(DepthRegionTraversal.java:68)
        	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverse(DepthRegionTraversal.java:19)
        	at jadx.core.dex.visitors.regions.PostProcessRegions.process(PostProcessRegions.java:23)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:31)
        */
    @Override // Y2.l
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void h(byte[] r20, int r21, int r22, Y2.k r23, h2.g r24) {
        /*
            Method dump skipped, instruction units count: 1066
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.sentry.internal.debugmeta.c.h(byte[], int, int, Y2.k, h2.g):void");
    }

    public int hashCode() {
        switch (this.l) {
            case 11:
                String str = (String) this.f16569m;
                int iHashCode = str == null ? 0 : str.hashCode();
                String str2 = (String) this.f16570n;
                return iHashCode ^ (str2 != null ? str2.hashCode() : 0);
            default:
                return super.hashCode();
        }
    }

    @Override // B8.InterfaceC0089a
    public Object i(K k10, G g7, AbstractC0287w abstractC0287w) {
        A7.m.f(g7, "proto");
        return null;
    }

    @Override // io.sentry.internal.debugmeta.a
    public List k() {
        switch (this.l) {
            case 0:
                io.sentry.K k10 = (io.sentry.K) this.f16569m;
                ArrayList arrayList = new ArrayList();
                try {
                    Enumeration<URL> resources = ((ClassLoader) this.f16570n).getResources("sentry-debug-meta.properties");
                    while (resources.hasMoreElements()) {
                        URL urlNextElement = resources.nextElement();
                        try {
                            InputStream inputStreamOpenStream = urlNextElement.openStream();
                            try {
                                Properties properties = new Properties();
                                properties.load(inputStreamOpenStream);
                                arrayList.add(properties);
                                k10.k(EnumC1495p1.INFO, "Debug Meta Data Properties loaded from %s", urlNextElement);
                                if (inputStreamOpenStream != null) {
                                    inputStreamOpenStream.close();
                                }
                            } catch (Throwable th) {
                                if (inputStreamOpenStream != null) {
                                    try {
                                        inputStreamOpenStream.close();
                                    } catch (Throwable th2) {
                                        th.addSuppressed(th2);
                                    }
                                    break;
                                }
                                throw th;
                            }
                        } catch (RuntimeException e10) {
                            k10.p(EnumC1495p1.ERROR, e10, "%s file is malformed.", urlNextElement);
                        }
                        break;
                    }
                } catch (IOException e11) {
                    k10.p(EnumC1495p1.ERROR, e11, "Failed to load %s", "sentry-debug-meta.properties");
                }
                if (!arrayList.isEmpty()) {
                    return arrayList;
                }
                k10.k(EnumC1495p1.INFO, "No %s file was found.", "sentry-debug-meta.properties");
                return null;
            default:
                io.sentry.K k11 = (io.sentry.K) this.f16569m;
                try {
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(((Context) this.f16570n).getAssets().open("sentry-debug-meta.properties"));
                    try {
                        Properties properties2 = new Properties();
                        properties2.load(bufferedInputStream);
                        List listSingletonList = Collections.singletonList(properties2);
                        bufferedInputStream.close();
                        return listSingletonList;
                    } catch (Throwable th3) {
                        try {
                            bufferedInputStream.close();
                            break;
                        } catch (Throwable th4) {
                            th3.addSuppressed(th4);
                        }
                        throw th3;
                    }
                } catch (FileNotFoundException e12) {
                    k11.p(EnumC1495p1.INFO, e12, "%s file was not found.", "sentry-debug-meta.properties");
                    return null;
                } catch (IOException e13) {
                    k11.s(EnumC1495p1.ERROR, "Error getting Proguard UUIDs.", e13);
                    return null;
                } catch (RuntimeException e14) {
                    k11.p(EnumC1495p1.ERROR, e14, "%s file is malformed.", "sentry-debug-meta.properties");
                    return null;
                }
        }
    }

    @Override // P0.c
    public int l(int i7) {
        do {
            g gVar = (g) this.f16570n;
            gVar.c(i7);
            i7 = ((BreakIterator) gVar.f321p).preceding(i7);
            if (i7 == -1) {
                return -1;
            }
        } while (Character.isWhitespace(((CharSequence) this.f16569m).charAt(i7)));
        return i7;
    }

    @Override // P0.c
    public int m(int i7) {
        do {
            g gVar = (g) this.f16570n;
            gVar.c(i7);
            i7 = ((BreakIterator) gVar.f321p).following(i7);
            if (i7 == -1) {
                return -1;
            }
        } while (Character.isWhitespace(((CharSequence) this.f16569m).charAt(i7 - 1)));
        return i7;
    }

    @Override // B8.InterfaceC0091c
    public List n(K k10, G g7) {
        A7.m.f(g7, "proto");
        ((C8.a) this.f16569m).getClass();
        u uVar = u.l;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(uVar, 10));
        Iterator<E> it = uVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) k10.f670b));
        }
        return arrayList;
    }

    @Override // B8.InterfaceC0091c
    public List o(K k10, AbstractC2136k abstractC2136k, int i7) {
        A7.m.f(abstractC2136k, "proto");
        k.p(i7, "kind");
        boolean z6 = abstractC2136k instanceof C1579y;
        C8.a aVar = (C8.a) this.f16569m;
        if (z6) {
            aVar.getClass();
        } else {
            if (!(abstractC2136k instanceof G)) {
                throw new IllegalStateException(("Unknown message: " + abstractC2136k).toString());
            }
            int iC = AbstractC2641j.c(i7);
            if (iC != 1 && iC != 2 && iC != 3) {
                throw new IllegalStateException("Unsupported callable kind with property proto for receiver annotations: ".concat(i7 != 1 ? i7 != 2 ? i7 != 3 ? i7 != 4 ? "null" : "PROPERTY_SETTER" : "PROPERTY_GETTER" : "PROPERTY" : "FUNCTION").toString());
            }
            aVar.getClass();
        }
        u uVar = u.l;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(uVar, 10));
        Iterator<E> it = uVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) k10.f670b));
        }
        return arrayList;
    }

    @Override // h2.InterfaceC1345b
    public h5.y p(byte[] bArr) {
        byte[] bArr2;
        h3.y yVar = (h3.y) this.f16570n;
        if (yVar == null || (bArr2 = (byte[]) yVar.f15499m) == null || !Arrays.equals(bArr2, bArr)) {
            h5.y yVarP = ((k2.i) this.f16569m).p(bArr);
            this.f16570n = new h3.y(bArr, yVarP);
            return yVarP;
        }
        h5.y yVar2 = (h5.y) ((h3.y) this.f16570n).o;
        AbstractC1346c.k(yVar2);
        return yVar2;
    }

    @Override // k2.InterfaceC1606g
    public h q() {
        L0 l02 = (L0) this.f16569m;
        return new e0(new k2.q(l02.f3180m, l02.f3181n, (C1489n1) l02.o), (c0) this.f16570n);
    }

    @Override // B8.InterfaceC0091c
    public ArrayList r(W w10, InterfaceC1788f interfaceC1788f) {
        A7.m.f(w10, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        Iterable iterable = (List) w10.k(((C8.a) this.f16569m).l);
        if (iterable == null) {
            iterable = u.l;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), interfaceC1788f));
        }
        return arrayList;
    }

    @Override // Y2.l
    public int s() {
        return 1;
    }

    @Override // B8.InterfaceC0091c
    public List t(K k10, G g7) {
        A7.m.f(g7, "proto");
        ((C8.a) this.f16569m).getClass();
        u uVar = u.l;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(uVar, 10));
        Iterator<E> it = uVar.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) k10.f670b));
        }
        return arrayList;
    }

    public String toString() {
        switch (this.l) {
            case 3:
                return ((t0) this.f16570n).toString();
            case 11:
                StringBuilder sb = new StringBuilder("Pair{");
                sb.append(this.f16569m);
                sb.append(" ");
                sb.append(this.f16570n);
                sb.append("}");
                return sb.toString();
            case 18:
                String string = "[ ";
                if (((h1.e) this.f16569m) != null) {
                    for (int i7 = 0; i7 < 9; i7++) {
                        StringBuilder sbI = M5.b.i(string);
                        sbI.append(((h1.e) this.f16569m).f15190s[i7]);
                        sbI.append(" ");
                        string = sbI.toString();
                    }
                }
                StringBuilder sbL = k.l(string, "] ");
                sbL.append((h1.e) this.f16569m);
                return sbL.toString();
            default:
                return super.toString();
        }
    }

    @Override // B8.InterfaceC0091c
    public List u(K k10, C1574t c1574t) {
        A7.m.f(k10, "container");
        Iterable iterable = (List) c1574t.k(((C8.a) this.f16569m).f470h);
        if (iterable == null) {
            iterable = u.l;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), (InterfaceC1788f) k10.f670b));
        }
        return arrayList;
    }

    @Override // d0.n
    public Object v(C1053b c1053b, Object obj) {
        return ((InterfaceC2785n) this.f16569m).invoke(c1053b, obj);
    }

    @Override // B8.InterfaceC0091c
    public ArrayList w(j8.Q q4, InterfaceC1788f interfaceC1788f) {
        A7.m.f(q4, "proto");
        A7.m.f(interfaceC1788f, "nameResolver");
        Iterable iterable = (List) q4.k(((C8.a) this.f16569m).f473k);
        if (iterable == null) {
            iterable = u.l;
        }
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        Iterator it = iterable.iterator();
        while (it.hasNext()) {
            arrayList.add(((M.t) this.f16570n).z((C1562g) it.next(), interfaceC1788f));
        }
        return arrayList;
    }

    @Override // P0.c
    public int y(int i7) {
        CharSequence charSequence;
        do {
            g gVar = (g) this.f16570n;
            gVar.c(i7);
            i7 = ((BreakIterator) gVar.f321p).following(i7);
            if (i7 != -1) {
                charSequence = (CharSequence) this.f16569m;
                if (i7 == charSequence.length()) {
                }
            }
            return -1;
        } while (Character.isWhitespace(charSequence.charAt(i7)));
        return i7;
    }

    @Override // h2.InterfaceC1345b
    public h5.y z(Uri uri) {
        Uri uri2;
        h3.y yVar = (h3.y) this.f16570n;
        if (yVar == null || (uri2 = (Uri) yVar.f15500n) == null || !uri2.equals(uri)) {
            h5.y yVarZ = ((k2.i) this.f16569m).z(uri);
            this.f16570n = new h3.y(uri, yVarZ);
            return yVarZ;
        }
        h5.y yVar2 = (h5.y) ((h3.y) this.f16570n).o;
        AbstractC1346c.k(yVar2);
        return yVar2;
    }

    public /* synthetic */ c(Object obj, int i7) {
        this.l = i7;
        this.f16570n = obj;
    }

    public /* synthetic */ c(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f16569m = obj;
        this.f16570n = obj2;
    }

    public c(Y4.e eVar) {
        this.l = 6;
        E8.l lVar = new E8.l("Type parameter upper bound erasure results");
        this.f16569m = r.o(new I0(this, 17));
        this.f16570n = lVar.b(new A.F(this, 16));
    }

    public c(String str, HashMap map) {
        this.l = 23;
        h6.e.q(str, "url is required");
        try {
            this.f16569m = URI.create(str).toURL();
            this.f16570n = map;
        } catch (MalformedURLException e10) {
            throw new IllegalArgumentException("Failed to compose the Sentry's server URL.", e10);
        }
    }

    public c(Object obj) {
        this.l = 5;
        this.f16569m = obj;
        this.f16570n = Thread.currentThread();
    }

    public c(io.sentry.K k10) {
        this.l = 0;
        ClassLoader classLoader = c.class.getClassLoader();
        this.f16569m = k10;
        this.f16570n = classLoader == null ? ClassLoader.getSystemClassLoader() : classLoader;
    }

    public c(Context context, io.sentry.K k10) {
        this.l = 25;
        Context applicationContext = context.getApplicationContext();
        this.f16570n = applicationContext != null ? applicationContext : context;
        this.f16569m = k10;
    }

    public c(int i7) {
        this.l = i7;
        switch (i7) {
            case 17:
                this.f16569m = new h2.t();
                this.f16570n = new C1292a();
                break;
            case 22:
                this.f16569m = new A4.m(1000L);
                this.f16570n = B4.d.a(10, new Y4.e(23));
                break;
            default:
                this.f16569m = r.n(m7.g.f19257m, C0159i.o);
                this.f16570n = new t0(new h0(1));
                break;
        }
    }

    public c(InterfaceC0671y interfaceC0671y, t tVar, C8.a aVar) {
        this.l = 2;
        A7.m.f(interfaceC0671y, "module");
        A7.m.f(aVar, "protocol");
        this.f16569m = aVar;
        this.f16570n = new M.t(interfaceC0671y, tVar);
    }

    public c(k2.i iVar) {
        this.l = 28;
        this.f16569m = iVar;
    }

    public c(String str, m mVar) {
        this.l = 1;
        this.f16570n = str;
        this.f16569m = mVar;
    }

    public c(H0.b bVar, HashMap map, HashMap map2) {
        this.l = 20;
        this.f16569m = bVar;
        this.f16570n = map;
    }

    public c(int i7, k1.g gVar) {
        this.l = 16;
        this.f16569m = gVar;
        String str = "top";
        if (i7 != 0) {
            if (i7 != 1) {
                h6.n.j("CCL", "horizontalAnchorIndexToAnchorName: Unknown horizontal index");
            } else {
                str = "bottom";
            }
        }
        this.f16570n = str;
    }

    public c(Context context, O o) {
        this.l = 29;
        if (o != null) {
            this.f16570n = Collections.synchronizedSet(new HashSet());
            this.f16569m = new C1736A(context, o);
            return;
        }
        throw new IllegalArgumentException("sessionToken must not be null");
    }

    public c(d0 d0Var, int[] iArr) {
        this.l = 21;
        this.f16569m = I.w(d0Var);
        this.f16570n = iArr;
    }

    public c(h3.F f10) {
        this.l = 19;
        this.f16570n = f10;
        this.f16569m = new B2.N(4, new byte[4]);
    }
}
