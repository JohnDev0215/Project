package org.chromium.media;

import J.N;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.os.Build;
import android.os.Handler;
import defpackage.AH;
import defpackage.AbstractC1623us;
import defpackage.BH;
import defpackage.C1210nH;
import defpackage.C1265oH;
import defpackage.C1320pH;
import defpackage.C1375qH;
import defpackage.C1429rH;
import defpackage.C1539tH;
import defpackage.C1649vH;
import defpackage.C1704wH;
import defpackage.C1759xH;
import defpackage.C1814yH;
import defpackage.C1869zH;
import defpackage.CH;
import defpackage.DH;
import defpackage.RunnableC1155mH;
import defpackage.Ts;
import defpackage.Z7;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public class MediaDrmBridge {
    public static final UUID m = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");
    public static final byte[] n = {0};
    public static final byte[] o = AbstractC1623us.b("unprovision");
    public static final C1759xH p = new C1759xH();
    public MediaDrm a;
    public MediaCrypto b;
    public long c;
    public UUID d;
    public final boolean e;
    public AH f;
    public CH g;
    public DH h;
    public boolean i;
    public String j;
    public boolean k;
    public C1814yH l;

    /* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
    public class KeyStatus {
        public final byte[] a;
        public final int b;

        public KeyStatus(byte[] bArr, int i, RunnableC1155mH runnableC1155mH) {
            this.a = bArr;
            this.b = i;
        }

        private byte[] getKeyId() {
            return this.a;
        }

        private int getStatusCode() {
            return this.b;
        }
    }

    public MediaDrmBridge(UUID uuid, boolean z, long j, long j2) {
        this.d = uuid;
        this.a = new MediaDrm(uuid);
        this.e = z;
        this.c = j;
        DH dh = new DH(j2);
        this.h = dh;
        this.g = new CH(dh);
        this.i = false;
        this.a.setOnEventListener(new C1429rH(this, null));
        if (Build.VERSION.SDK_INT >= 23) {
            this.a.setOnExpirationUpdateListener(new C1539tH(this, null), (Handler) null);
            this.a.setOnKeyStatusChangeListener(new C1649vH(this, null), (Handler) null);
        }
        if (s()) {
            this.a.setPropertyString("privacyMode", "enable");
            this.a.setPropertyString("sessionSharing", "enable");
        }
    }

    public static AH b(MediaDrmBridge mediaDrmBridge, byte[] bArr) {
        if (mediaDrmBridge.f == null) {
            Ts.a("media", "Session doesn't exist because media crypto session is not created.", new Object[0]);
            return null;
        }
        BH bh = (BH) mediaDrmBridge.g.b.get(ByteBuffer.wrap(bArr));
        AH ah = bh == null ? null : bh.a;
        if (ah == null) {
            return null;
        }
        return ah;
    }

    public static List c(int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyStatus(n, i, null));
        return arrayList;
    }

    private void closeSession(byte[] bArr, long j) {
        if (this.a == null) {
            t(j, "closeSession() called when MediaDrm is null.");
            return;
        }
        AH ahP = p(bArr);
        if (ahP == null) {
            StringBuilder sbF = Z7.f("Invalid sessionId in closeSession(): ");
            sbF.append(AH.d(bArr));
            t(j, sbF.toString());
            return;
        }
        try {
            this.a.removeKeys(ahP.b);
        } catch (Exception e) {
            Ts.a("media", "removeKeys failed: ", e);
        }
        m(ahP);
        CH ch = this.g;
        ch.b(ahP);
        ch.a.remove(ByteBuffer.wrap(ahP.a));
        byte[] bArr2 = ahP.b;
        if (bArr2 != null) {
            ch.b.remove(ByteBuffer.wrap(bArr2));
        }
        if (r()) {
            N.MOzXytse(this.c, this, j);
        }
        if (r()) {
            N.MulYy5b7(this.c, this, ahP.a);
        }
        ahP.c();
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0068  */
    /* JADX WARN: Removed duplicated region for block: B:38:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.media.MediaDrmBridge create(byte[] r16, java.lang.String r17, java.lang.String r18, boolean r19, long r20, long r22) {
        /*
            Method dump skipped, instruction units count: 211
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.create(byte[], java.lang.String, java.lang.String, boolean, long, long):org.chromium.media.MediaDrmBridge");
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b0  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void createSessionFromNative(byte[] r16, java.lang.String r17, int r18, java.lang.String[] r19, long r20) {
        /*
            r15 = this;
            r7 = r15
            r0 = r18
            r1 = r19
            r8 = r20
            java.util.HashMap r6 = new java.util.HashMap
            r6.<init>()
            r2 = 2
            r10 = 0
            if (r1 == 0) goto L2c
            int r3 = r1.length
            int r3 = r3 % r2
            if (r3 != 0) goto L24
            r3 = 0
        L15:
            int r4 = r1.length
            if (r3 >= r4) goto L2c
            r4 = r1[r3]
            int r5 = r3 + 1
            r5 = r1[r5]
            r6.put(r4, r5)
            int r3 = r3 + 2
            goto L15
        L24:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Additional data array doesn't have equal keys/values"
            r0.<init>(r1)
            throw r0
        L2c:
            android.media.MediaDrm r1 = r7.a
            java.lang.String r11 = "media"
            if (r1 != 0) goto L40
            java.lang.Object[] r0 = new java.lang.Object[r10]
            java.lang.String r1 = "createSession() called when MediaDrm is null."
            defpackage.Ts.a(r11, r1, r0)
            java.lang.String r0 = "MediaDrm released previously."
            r15.t(r8, r0)
            goto Lb8
        L40:
            r12 = 0
            r13 = 1
            byte[] r1 = r15.x()     // Catch: android.media.NotProvisionedException -> La3
            if (r1 != 0) goto L4f
            java.lang.String r0 = "Open session failed."
            r15.t(r8, r0)     // Catch: android.media.NotProvisionedException -> La3
            goto Lb8
        L4f:
            if (r0 != r2) goto L56
            AH r1 = defpackage.AH.a(r1)     // Catch: android.media.NotProvisionedException -> La0
            goto L5a
        L56:
            AH r1 = defpackage.AH.b(r1)     // Catch: android.media.NotProvisionedException -> La0
        L5a:
            r14 = r1
            r1 = r15
            r2 = r14
            r3 = r16
            r4 = r17
            r5 = r18
            android.media.MediaDrm$KeyRequest r1 = r1.o(r2, r3, r4, r5, r6)     // Catch: android.media.NotProvisionedException -> L9d
            if (r1 != 0) goto L72
            r15.m(r14)     // Catch: android.media.NotProvisionedException -> L9d
            java.lang.String r0 = "Generate request failed."
            r15.t(r8, r0)     // Catch: android.media.NotProvisionedException -> L9d
            goto Lb8
        L72:
            r14.c()     // Catch: android.media.NotProvisionedException -> L9d
            r15.u(r8, r14)     // Catch: android.media.NotProvisionedException -> L9d
            r15.w(r14, r1)     // Catch: android.media.NotProvisionedException -> L9d
            CH r1 = r7.g     // Catch: android.media.NotProvisionedException -> L9d
            BH r2 = new BH     // Catch: android.media.NotProvisionedException -> L9d
            r3 = r17
            r2.<init>(r14, r3, r0, r12)     // Catch: android.media.NotProvisionedException -> L9d
            java.util.HashMap r0 = r1.a     // Catch: android.media.NotProvisionedException -> L9d
            byte[] r3 = r14.a     // Catch: android.media.NotProvisionedException -> L9d
            java.nio.ByteBuffer r3 = java.nio.ByteBuffer.wrap(r3)     // Catch: android.media.NotProvisionedException -> L9d
            r0.put(r3, r2)     // Catch: android.media.NotProvisionedException -> L9d
            byte[] r0 = r14.b     // Catch: android.media.NotProvisionedException -> L9d
            if (r0 == 0) goto Lb8
            java.util.HashMap r1 = r1.b     // Catch: android.media.NotProvisionedException -> L9d
            java.nio.ByteBuffer r0 = java.nio.ByteBuffer.wrap(r0)     // Catch: android.media.NotProvisionedException -> L9d
            r1.put(r0, r2)     // Catch: android.media.NotProvisionedException -> L9d
            goto Lb8
        L9d:
            r0 = move-exception
            r12 = r14
            goto La1
        La0:
            r0 = move-exception
        La1:
            r1 = 1
            goto La5
        La3:
            r0 = move-exception
            r1 = 0
        La5:
            java.lang.Object[] r2 = new java.lang.Object[r13]
            r2[r10] = r0
            java.lang.String r0 = "Device not provisioned"
            defpackage.Ts.a(r11, r0, r2)
            if (r1 == 0) goto Lb3
            r15.m(r12)
        Lb3:
            java.lang.String r0 = "Device not provisioned during createSession()."
            r15.t(r8, r0)
        Lb8:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.createSessionFromNative(byte[], java.lang.String, int, java.lang.String[], long):void");
    }

    private void destroy() {
        this.c = 0L;
        if (this.a != null) {
            z();
        }
    }

    public static void e(MediaDrmBridge mediaDrmBridge, AH ah, Runnable runnable) {
        C1814yH c1814yH = mediaDrmBridge.l;
        if (c1814yH == null || !Arrays.equals(c1814yH.a.a, ah.a)) {
            runnable.run();
        } else {
            mediaDrmBridge.l.b.add(runnable);
        }
    }

    public static void f(MediaDrmBridge mediaDrmBridge, AH ah, long j) {
        if (mediaDrmBridge.r()) {
            N.MFLUFEZc(mediaDrmBridge.c, mediaDrmBridge, ah.a, j);
        }
    }

    public static void g(MediaDrmBridge mediaDrmBridge, long j) {
        if (mediaDrmBridge.r()) {
            N.MOzXytse(mediaDrmBridge.c, mediaDrmBridge, j);
        }
    }

    public static int getFirstApiLevel() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, "ro.product.first_api_level", 0)).intValue();
        } catch (Exception e) {
            Ts.a("media", "Exception while getting system property %s. Using default.", "ro.product.first_api_level", e);
            return 0;
        }
    }

    private String getSecurityLevel() {
        if (this.a == null || !s()) {
            Ts.a("media", "getSecurityLevel(): MediaDrm is null or security level is not supported.", new Object[0]);
            return "";
        }
        try {
            return this.a.getPropertyString("securityLevel");
        } catch (IllegalStateException e) {
            Ts.a("media", "Failed to get current security level", e);
            return "";
        } catch (Exception e2) {
            Ts.a("media", "Failed to get current security level", e2);
            return "";
        }
    }

    public static void i(MediaDrmBridge mediaDrmBridge, long j) {
        Objects.requireNonNull(mediaDrmBridge);
        mediaDrmBridge.u(j, AH.b(new byte[0]));
    }

    public static boolean isCryptoSchemeSupported(byte[] bArr, String str) {
        UUID uuidQ = q(bArr);
        return str.isEmpty() ? MediaDrm.isCryptoSchemeSupported(uuidQ) : MediaDrm.isCryptoSchemeSupported(uuidQ, str);
    }

    public static void j(MediaDrmBridge mediaDrmBridge, AH ah, long j) {
        Objects.requireNonNull(mediaDrmBridge);
        try {
            byte[] bArrX = mediaDrmBridge.x();
            if (bArrX == null) {
                mediaDrmBridge.t(j, "Failed to open session to load license.");
            } else {
                CH ch = mediaDrmBridge.g;
                BH bhB = ch.b(ah);
                ah.b = bArrX;
                ch.b.put(ByteBuffer.wrap(bArrX), bhB);
                if (mediaDrmBridge.g.b(ah).c == 3) {
                    Ts.f("media", "Persistent license is waiting for release ack.", new Object[0]);
                    mediaDrmBridge.u(j, ah);
                    ArrayList arrayList = new ArrayList();
                    arrayList.add(new KeyStatus(n, 1, null));
                    mediaDrmBridge.v(ah, arrayList.toArray(), false, true);
                } else {
                    mediaDrmBridge.l = new C1814yH(ah);
                    mediaDrmBridge.a.restoreKeys(ah.b, ah.c);
                    mediaDrmBridge.u(j, ah);
                    mediaDrmBridge.l.a();
                    mediaDrmBridge.l = null;
                }
            }
        } catch (NotProvisionedException unused) {
            Ts.f("media", "Persistent license load fail because origin isn't provisioned.", new Object[0]);
            mediaDrmBridge.m(ah);
            mediaDrmBridge.g.a(ah, new C1265oH(mediaDrmBridge, j));
        } catch (IllegalStateException unused2) {
            mediaDrmBridge.m(ah);
            mediaDrmBridge.g.a(ah, new C1265oH(mediaDrmBridge, j));
        }
    }

    public static void l(MediaDrmBridge mediaDrmBridge, AH ah, String str, long j) {
        Objects.requireNonNull(mediaDrmBridge);
        try {
            MediaDrm.KeyRequest keyRequestO = mediaDrmBridge.o(ah, null, str, 3, null);
            if (keyRequestO == null) {
                mediaDrmBridge.t(j, "Fail to generate key release request");
                return;
            }
            if (mediaDrmBridge.r()) {
                N.MOzXytse(mediaDrmBridge.c, mediaDrmBridge, j);
            }
            mediaDrmBridge.w(ah, keyRequestO);
        } catch (NotProvisionedException unused) {
            Ts.a("media", "removeSession called on unprovisioned device", new Object[0]);
            mediaDrmBridge.t(j, "Unknown failure");
        }
    }

    private void loadSession(byte[] bArr, long j) {
        CH ch = this.g;
        C1210nH c1210nH = new C1210nH(this, j);
        DH dh = ch.c;
        C1869zH c1869zH = new C1869zH(ch, c1210nH);
        if (dh.a()) {
            N.Mmi_qOX8(dh.a, dh, bArr, c1869zH);
        } else {
            c1869zH.a(null);
        }
    }

    private void processProvisionResponse(boolean z, byte[] bArr) {
        this.i = false;
        boolean zY = (this.a == null || !z) ? false : y(bArr);
        if (!this.e) {
            N.MAaklmRW(this.c, this, zY);
            if (!zY) {
                z();
            }
        } else if (!zY) {
            z();
        } else if (this.k) {
            DH dh = this.h;
            C1375qH c1375qH = new C1375qH(this);
            if (dh.a()) {
                N.ME6vNmlv(dh.a, dh, c1375qH);
            } else {
                c1375qH.a(Boolean.TRUE);
            }
        } else {
            n();
        }
        if (this.e) {
            C1759xH c1759xH = p;
            c1759xH.a = false;
            while (!c1759xH.b.isEmpty()) {
                Runnable runnable = (Runnable) c1759xH.b.element();
                c1759xH.b.remove();
                runnable.run();
                if (c1759xH.a) {
                    return;
                }
            }
        }
    }

    private void provision() {
        if (!this.k) {
            Ts.a("media", "Calling provision() without an origin.", new Object[0]);
            N.MAaklmRW(this.c, this, false);
            return;
        }
        try {
            byte[] bArrX = x();
            if (bArrX != null) {
                m(AH.b(bArrX));
            }
            N.MAaklmRW(this.c, this, true);
        } catch (NotProvisionedException unused) {
            if (A()) {
                return;
            }
            N.MAaklmRW(this.c, this, false);
        }
    }

    public static UUID q(byte[] bArr) {
        if (bArr.length != 16) {
            return null;
        }
        long j = 0;
        long j2 = 0;
        for (int i = 0; i < 8; i++) {
            j2 = (j2 << 8) | ((long) (bArr[i] & 255));
        }
        for (int i2 = 8; i2 < 16; i2++) {
            j = (j << 8) | ((long) (bArr[i2] & 255));
        }
        return new UUID(j2, j);
    }

    private void removeSession(byte[] bArr, long j) {
        AH ahP = p(bArr);
        if (ahP == null) {
            t(j, "Session doesn't exist");
            return;
        }
        BH bhB = this.g.b(ahP);
        if (bhB.c == 1) {
            t(j, "Removing temporary session isn't implemented");
            return;
        }
        CH ch = this.g;
        C1320pH c1320pH = new C1320pH(this, j, ahP, bhB);
        BH bhB2 = ch.b(ahP);
        bhB2.c = 3;
        DH dh = ch.c;
        AH ah = bhB2.a;
        MediaDrmStorageBridge$PersistentInfo mediaDrmStorageBridge$PersistentInfo = new MediaDrmStorageBridge$PersistentInfo(ah.a, ah.c, bhB2.b, 3);
        if (dh.a()) {
            N.MeALR1v2(dh.a, dh, mediaDrmStorageBridge$PersistentInfo, c1320pH);
        } else {
            c1320pH.a(Boolean.FALSE);
        }
    }

    private boolean setServerCertificate(byte[] bArr) {
        if (!s()) {
            return true;
        }
        try {
            this.a.setPropertyByteArray("serviceCertificate", bArr);
            return true;
        } catch (IllegalArgumentException e) {
            Ts.a("media", "Failed to set server certificate", e);
            return false;
        } catch (IllegalStateException e2) {
            Ts.a("media", "Failed to set server certificate", e2);
            return false;
        }
    }

    private void unprovision() {
        if (this.a != null && this.k) {
            y(o);
        }
    }

    private void updateSession(byte[] bArr, byte[] bArr2, long j) {
        if (this.a == null) {
            t(j, "updateSession() called when MediaDrm is null.");
            return;
        }
        AH ahP = p(bArr);
        if (ahP == null) {
            StringBuilder sbF = Z7.f("Invalid session in updateSession: ");
            sbF.append(AH.d(bArr));
            t(j, sbF.toString());
            return;
        }
        try {
            BH bhB = this.g.b(ahP);
            boolean z = bhB.c == 3;
            byte[] bArrProvideKeyResponse = null;
            if (z) {
                this.a.provideKeyResponse(ahP.c, bArr2);
            } else {
                bArrProvideKeyResponse = this.a.provideKeyResponse(ahP.b, bArr2);
            }
            byte[] bArr3 = bArrProvideKeyResponse;
            C1704wH c1704wH = new C1704wH(this, ahP, j, z);
            if (z) {
                this.g.a(ahP, c1704wH);
            } else if (bhB.c != 2 || bArr3 == null || bArr3.length <= 0) {
                c1704wH.b(Boolean.TRUE);
            } else {
                this.g.d(ahP, bArr3, c1704wH);
            }
        } catch (DeniedByServerException e) {
            Ts.a("media", "failed to provide key response", e);
            t(j, "Update session failed.");
            z();
        } catch (NotProvisionedException e2) {
            Ts.a("media", "failed to provide key response", e2);
            t(j, "Update session failed.");
            z();
        } catch (IllegalStateException e3) {
            Ts.a("media", "failed to provide key response", e3);
            t(j, "Update session failed.");
            z();
        }
    }

    public final boolean A() {
        this.i = true;
        if (!r()) {
            return false;
        }
        if (this.e) {
            p.a = true;
        }
        try {
            MediaDrm.ProvisionRequest provisionRequest = this.a.getProvisionRequest();
            Object[] objArr = new Object[1];
            objArr[0] = this.k ? this.j : "<none>";
            Ts.d("media", "Provisioning origin ID %s", objArr);
            N.MmhSkOYV(this.c, this, provisionRequest.getDefaultUrl(), provisionRequest.getData());
            return true;
        } catch (IllegalStateException e) {
            Ts.a("media", "Failed to get provisioning request", e);
            return false;
        }
    }

    public final void m(AH ah) {
        try {
            this.a.closeSession(ah.b);
        } catch (Exception e) {
            Ts.a("media", "closeSession failed: ", e);
        }
    }

    public final boolean n() {
        try {
            byte[] bArrX = x();
            if (bArrX == null) {
                Ts.a("media", "Cannot create MediaCrypto Session.", new Object[0]);
                return false;
            }
            AH ahB = AH.b(bArrX);
            this.f = ahB;
            ahB.c();
            try {
            } catch (MediaCryptoException e) {
                Ts.a("media", "Cannot create MediaCrypto", e);
            }
            if (!MediaCrypto.isCryptoSchemeSupported(this.d)) {
                Ts.a("media", "Cannot create MediaCrypto for unsupported scheme.", new Object[0]);
                z();
                return false;
            }
            MediaCrypto mediaCrypto = new MediaCrypto(this.d, this.f.b);
            this.b = mediaCrypto;
            if (r()) {
                N.MV9yuwVC(this.c, this, mediaCrypto);
            }
            return true;
        } catch (NotProvisionedException unused) {
            C1759xH c1759xH = p;
            if (!c1759xH.a) {
                return A();
            }
            c1759xH.b.add(new RunnableC1155mH(this));
            return true;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final MediaDrm.KeyRequest o(AH ah, byte[] bArr, String str, int i, HashMap map) {
        if (map == null) {
            map = new HashMap();
        }
        try {
            return this.a.getKeyRequest(i == 3 ? ah.c : ah.b, bArr, str, i, map);
        } catch (IllegalStateException e) {
            if (!(e instanceof MediaDrm.MediaDrmStateException)) {
                return null;
            }
            Ts.a("media", "MediaDrmStateException fired during getKeyRequest().", e);
            return null;
        }
    }

    public final AH p(byte[] bArr) {
        if (this.f == null) {
            Ts.a("media", "Session doesn't exist because media crypto session is not created.", new Object[0]);
            return null;
        }
        AH ahC = this.g.c(bArr);
        if (ahC == null) {
            return null;
        }
        return ahC;
    }

    public final boolean r() {
        return this.c != 0;
    }

    public final boolean s() {
        return this.d.equals(m);
    }

    public final void t(long j, String str) {
        Ts.a("media", "onPromiseRejected: %s", str);
        if (r()) {
            N.M2P7BQ98(this.c, this, j, str);
        }
    }

    public final void u(long j, AH ah) {
        if (r()) {
            N.MtWWjNjU(this.c, this, j, ah.a);
        }
    }

    public final void v(AH ah, Object[] objArr, boolean z, boolean z2) {
        if (r()) {
            N.Mk8V79M2(this.c, this, ah.a, objArr, z, z2);
        }
    }

    public final void w(AH ah, MediaDrm.KeyRequest keyRequest) {
        if (r()) {
            N.Mf7HZHqV(this.c, this, ah.a, Build.VERSION.SDK_INT >= 23 ? keyRequest.getRequestType() : !keyRequest.getDefaultUrl().isEmpty() ? 1 : 0, keyRequest.getData());
        }
    }

    public final byte[] x() throws NotProvisionedException {
        try {
            return (byte[]) this.a.openSession().clone();
        } catch (NotProvisionedException e) {
            throw e;
        } catch (MediaDrmException e2) {
            Ts.a("media", "Cannot open a new session", e2);
            z();
            return null;
        } catch (RuntimeException e3) {
            Ts.a("media", "Cannot open a new session", e3);
            z();
            return null;
        }
    }

    public boolean y(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            Ts.a("media", "Invalid provision response.", new Object[0]);
            return false;
        }
        try {
            this.a.provideProvisionResponse(bArr);
            return true;
        } catch (DeniedByServerException e) {
            Ts.a("media", "failed to provide provision response", e);
            return false;
        } catch (IllegalStateException e2) {
            Ts.a("media", "failed to provide provision response", e2);
            return false;
        }
    }

    public final void z() {
        CH ch = this.g;
        Objects.requireNonNull(ch);
        ArrayList<AH> arrayList = new ArrayList();
        Iterator it = ch.a.values().iterator();
        while (it.hasNext()) {
            arrayList.add(((BH) it.next()).a);
        }
        for (AH ah : arrayList) {
            try {
                this.a.removeKeys(ah.b);
            } catch (Exception e) {
                Ts.a("media", "removeKeys failed: ", e);
            }
            m(ah);
            if (r()) {
                N.MulYy5b7(this.c, this, ah.a);
            }
        }
        this.g = new CH(this.h);
        AH ah2 = this.f;
        if (ah2 != null) {
            m(ah2);
            this.f = null;
        }
        MediaDrm mediaDrm = this.a;
        if (mediaDrm != null) {
            mediaDrm.release();
            this.a = null;
        }
        MediaCrypto mediaCrypto = this.b;
        if (mediaCrypto != null) {
            mediaCrypto.release();
            this.b = null;
        } else if (r()) {
            N.MV9yuwVC(this.c, this, null);
        }
    }
}
