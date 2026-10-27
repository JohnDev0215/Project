package org.chromium.media;

import J.N;
import android.annotation.SuppressLint;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.MediaDrmException;
import android.media.NotProvisionedException;
import android.os.Handler;
import android.util.Log;
import defpackage.c6;
import defpackage.fa1;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import org.chromium.base.Callback;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.media.MediaDrmStorageBridge;
import org.chromium.media.l;

/* JADX INFO: loaded from: classes3.dex */
@SuppressLint({"WrongConstant"})
public class MediaDrmBridge {
    public static final UUID l = UUID.fromString("edef8ba9-79d6-4ace-a3c8-27dcd51d21ed");
    public static final byte[] m = {0};
    public static final byte[] n = "unprovision".getBytes(StandardCharsets.UTF_8);
    public static final h o = new h();
    public MediaDrm a;
    public MediaCrypto b;
    public long c;
    public final UUID d;
    public final boolean e;
    public l.a f;
    public l g;
    public final MediaDrmStorageBridge h;
    public String i;
    public boolean j;
    public i k;

    public static class KeyStatus {
        public final byte[] a;
        public final int b;

        public KeyStatus(byte[] bArr, int i) {
            this.a = bArr;
            this.b = i;
        }

        @CalledByNative
        public final byte[] getKeyId() {
            return this.a;
        }

        @CalledByNative
        public final int getStatusCode() {
            return this.b;
        }
    }

    public class a implements Runnable {
        public a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            UUID uuid = MediaDrmBridge.l;
            MediaDrmBridge.this.d();
        }
    }

    public class b implements Callback<l.a> {
        public final /* synthetic */ long e;

        public b(long j) {
            this.e = j;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(l.a aVar) {
            boolean z;
            boolean z2;
            l.a aVar2 = aVar;
            long j = this.e;
            MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
            if (aVar2 == null) {
                UUID uuid = MediaDrmBridge.l;
                mediaDrmBridge.getClass();
                byte[] bArr = new byte[0];
                mediaDrmBridge.j(j, new l.a(bArr, bArr, null));
                return;
            }
            byte[] bArr2 = aVar2.a;
            UUID uuid2 = MediaDrmBridge.l;
            mediaDrmBridge.getClass();
            boolean z3 = true;
            try {
                byte[] bArrL = mediaDrmBridge.l();
                if (bArrL == null) {
                    mediaDrmBridge.i(j, "Failed to open session to load license.");
                } else {
                    l lVar = mediaDrmBridge.g;
                    l.b bVarA = lVar.a(aVar2);
                    aVar2.b = bArrL;
                    lVar.b.put(ByteBuffer.wrap(bArrL), bVarA);
                    try {
                        if (mediaDrmBridge.g.a(aVar2).c == 3) {
                            Log.w("cr_media", "Persistent license is waiting for release ack.");
                            mediaDrmBridge.j(j, aVar2);
                            ArrayList arrayList = new ArrayList();
                            arrayList.add(new KeyStatus(MediaDrmBridge.m, 1));
                            Object[] array = arrayList.toArray();
                            if (mediaDrmBridge.h()) {
                                z3 = true;
                                N.Mk8V79M2(mediaDrmBridge.c, mediaDrmBridge, aVar2.a, array, false, true);
                            }
                        } else {
                            mediaDrmBridge.k = new i(aVar2);
                            mediaDrmBridge.a.restoreKeys(aVar2.b, aVar2.c);
                            mediaDrmBridge.j(j, aVar2);
                            ArrayList<Runnable> arrayList2 = mediaDrmBridge.k.b;
                            Iterator<Runnable> it = arrayList2.iterator();
                            while (it.hasNext()) {
                                it.next().run();
                            }
                            arrayList2.clear();
                            mediaDrmBridge.k = null;
                        }
                    } catch (NotProvisionedException unused) {
                        Log.w("cr_media", "Persistent license load fail because origin isn't provisioned.");
                        mediaDrmBridge.c(aVar2);
                        l lVar2 = mediaDrmBridge.g;
                        org.chromium.media.i iVar = new org.chromium.media.i(mediaDrmBridge, j);
                        lVar2.getClass();
                        aVar2.c = null;
                        MediaDrmStorageBridge mediaDrmStorageBridge = lVar2.c;
                        long j2 = mediaDrmStorageBridge.a;
                        if (j2 != -1 ? z2 : false) {
                            N.MYa_y6Dg(j2, mediaDrmStorageBridge, bArr2, iVar);
                        } else {
                            iVar.onResult(Boolean.TRUE);
                        }
                    } catch (IllegalStateException unused2) {
                        mediaDrmBridge.c(aVar2);
                        l lVar3 = mediaDrmBridge.g;
                        org.chromium.media.i iVar2 = new org.chromium.media.i(mediaDrmBridge, j);
                        lVar3.getClass();
                        aVar2.c = null;
                        MediaDrmStorageBridge mediaDrmStorageBridge2 = lVar3.c;
                        long j3 = mediaDrmStorageBridge2.a;
                        if (j3 != -1 ? z : false) {
                            N.MYa_y6Dg(j3, mediaDrmStorageBridge2, bArr2, iVar2);
                        } else {
                            iVar2.onResult(Boolean.TRUE);
                        }
                    }
                }
            } catch (NotProvisionedException unused3) {
                z2 = z3;
            } catch (IllegalStateException unused4) {
                z = z3;
            }
        }
    }

    public class c implements Callback<Boolean> {
        public final /* synthetic */ long e;
        public final /* synthetic */ l.a f;
        public final /* synthetic */ l.b g;

        public c(long j, l.a aVar, l.b bVar) {
            this.e = j;
            this.f = aVar;
            this.g = bVar;
        }

        @Override // org.chromium.base.Callback
        public final void onResult(Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            long j = this.e;
            MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
            if (!zBooleanValue) {
                UUID uuid = MediaDrmBridge.l;
                mediaDrmBridge.i(j, "Fail to update persistent storage");
                return;
            }
            l.a aVar = this.f;
            String str = this.g.b;
            UUID uuid2 = MediaDrmBridge.l;
            mediaDrmBridge.getClass();
            try {
                MediaDrm.KeyRequest keyRequestE = mediaDrmBridge.e(aVar, null, str, 3, null);
                if (keyRequestE == null) {
                    mediaDrmBridge.i(j, "Fail to generate key release request");
                    return;
                }
                if (mediaDrmBridge.h()) {
                    N.MOzXytse(mediaDrmBridge.c, mediaDrmBridge, j);
                }
                mediaDrmBridge.k(aVar, keyRequestE);
            } catch (NotProvisionedException unused) {
                Log.e("cr_media", "removeSession called on unprovisioned device");
                mediaDrmBridge.i(j, "Unknown failure");
            }
        }
    }

    public class d implements MediaDrm.OnEventListener {
        public d() {
        }

        @Override // android.media.MediaDrm.OnEventListener
        public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            if (bArr == null) {
                fa1.b("media", "EventListener: No session for event %d.", Integer.valueOf(i));
                return;
            }
            MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
            l.a aVarB = MediaDrmBridge.b(mediaDrmBridge, bArr);
            if (aVarB == null) {
                fa1.b("media", "EventListener: Invalid session %s", l.a.a(bArr));
                return;
            }
            l.b bVarA = mediaDrmBridge.g.a(aVarB);
            if (i != 2) {
                if (i == 3 || i == 4) {
                    return;
                }
                Log.e("cr_media", "Invalid DRM event " + i);
                return;
            }
            try {
                MediaDrm.KeyRequest keyRequestE = MediaDrmBridge.this.e(aVarB, bArr2, bVarA.b, bVarA.c, null);
                if (keyRequestE != null) {
                    mediaDrmBridge.k(aVarB, keyRequestE);
                } else {
                    Log.e("cr_media", "EventListener: getKeyRequest failed.");
                }
            } catch (NotProvisionedException e) {
                Log.e("cr_media", "Device not provisioned", e);
            }
        }
    }

    public class e implements MediaDrm.OnExpirationUpdateListener {

        public class a implements Runnable {
            public final /* synthetic */ l.a e;
            public final /* synthetic */ long f;

            public a(l.a aVar, long j) {
                this.e = aVar;
                this.f = j;
            }

            @Override // java.lang.Runnable
            public final void run() {
                l.a aVar = this.e;
                aVar.b();
                long j = this.f;
                MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
                UUID uuid = MediaDrmBridge.l;
                if (mediaDrmBridge.h()) {
                    N.MFLUFEZc(mediaDrmBridge.c, mediaDrmBridge, aVar.a, j);
                }
            }
        }

        public e() {
        }

        @Override // android.media.MediaDrm.OnExpirationUpdateListener
        public final void onExpirationUpdate(MediaDrm mediaDrm, byte[] bArr, long j) {
            MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
            l.a aVarB = MediaDrmBridge.b(mediaDrmBridge, bArr);
            MediaDrmBridge.a(mediaDrmBridge, aVarB, new a(aVarB, j));
        }
    }

    public class f implements MediaDrm.OnKeyStatusChangeListener {

        public class a implements Runnable {
            public final /* synthetic */ l.a e;
            public final /* synthetic */ boolean f;
            public final /* synthetic */ List g;
            public final /* synthetic */ boolean h;

            public a(l.a aVar, boolean z, List list, boolean z2) {
                this.e = aVar;
                this.f = z;
                this.g = list;
                this.h = z2;
            }

            @Override // java.lang.Runnable
            public final void run() {
                l.a aVar = this.e;
                aVar.b();
                MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
                ArrayList arrayList = new ArrayList();
                for (MediaDrm.KeyStatus keyStatus : this.g) {
                    arrayList.add(new KeyStatus(keyStatus.getKeyId(), keyStatus.getStatusCode()));
                }
                Object[] array = arrayList.toArray();
                UUID uuid = MediaDrmBridge.l;
                boolean z = this.f;
                boolean z2 = this.h;
                if (mediaDrmBridge.h()) {
                    N.Mk8V79M2(mediaDrmBridge.c, mediaDrmBridge, aVar.a, array, z, z2);
                }
            }
        }

        public f() {
        }

        @Override // android.media.MediaDrm.OnKeyStatusChangeListener
        public final void onKeyStatusChange(MediaDrm mediaDrm, byte[] bArr, List<MediaDrm.KeyStatus> list, boolean z) {
            MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
            l.a aVarB = MediaDrmBridge.b(mediaDrmBridge, bArr);
            MediaDrmBridge.a(mediaDrmBridge, aVarB, new a(aVarB, z, list, mediaDrmBridge.g.a(aVarB).c == 3));
        }
    }

    public class g implements Callback<Boolean> {
        public final l.a e;
        public final long f;

        public g(l.a aVar, long j, boolean z) {
            this.e = aVar;
            this.f = j;
        }

        @Override // org.chromium.base.Callback
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public final void onResult(Boolean bool) {
            boolean zBooleanValue = bool.booleanValue();
            long j = this.f;
            MediaDrmBridge mediaDrmBridge = MediaDrmBridge.this;
            if (!zBooleanValue) {
                UUID uuid = MediaDrmBridge.l;
                mediaDrmBridge.i(j, "failed to update key after response accepted");
                return;
            }
            this.e.b();
            UUID uuid2 = MediaDrmBridge.l;
            if (mediaDrmBridge.h()) {
                N.MOzXytse(mediaDrmBridge.c, mediaDrmBridge, j);
            }
        }
    }

    public static class h {
        public boolean a = false;
        public final ArrayDeque b = new ArrayDeque();
    }

    public static class i {
        public final l.a a;
        public final ArrayList<Runnable> b = new ArrayList<>();

        public i(l.a aVar) {
            this.a = aVar;
        }
    }

    public MediaDrmBridge(UUID uuid, boolean z, long j, long j2) {
        this.d = uuid;
        this.a = new MediaDrm(uuid);
        this.e = z;
        this.c = j;
        MediaDrmStorageBridge mediaDrmStorageBridge = new MediaDrmStorageBridge(j2);
        this.h = mediaDrmStorageBridge;
        this.g = new l(mediaDrmStorageBridge);
        this.a.setOnEventListener(new d());
        this.a.setOnExpirationUpdateListener(new e(), (Handler) null);
        this.a.setOnKeyStatusChangeListener(new f(), (Handler) null);
        if (uuid.equals(l)) {
            this.a.setPropertyString("privacyMode", "enable");
            this.a.setPropertyString("sessionSharing", "enable");
        }
    }

    public static void a(MediaDrmBridge mediaDrmBridge, l.a aVar, Runnable runnable) {
        i iVar = mediaDrmBridge.k;
        if (iVar != null) {
            l.a aVar2 = iVar.a;
            aVar2.getClass();
            if (Arrays.equals(aVar2.a, aVar.a)) {
                mediaDrmBridge.k.b.add(runnable);
                return;
            }
        }
        runnable.run();
    }

    public static l.a b(MediaDrmBridge mediaDrmBridge, byte[] bArr) {
        if (mediaDrmBridge.f == null) {
            Log.e("cr_media", "Session doesn't exist because media crypto session is not created.");
            return null;
        }
        l.b bVar = mediaDrmBridge.g.b.get(ByteBuffer.wrap(bArr));
        l.a aVar = bVar == null ? null : bVar.a;
        if (aVar == null) {
            return null;
        }
        return aVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:25:0x0061  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0093  */
    @org.chromium.base.annotations.CalledByNative
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static org.chromium.media.MediaDrmBridge create(byte[] r15, java.lang.String r16, java.lang.String r17, boolean r18, long r19, long r21) {
        /*
            r1 = r16
            r0 = r17
            java.lang.String r2 = "Failed to create MediaDrmBridge"
            java.lang.String r3 = "cr_media"
            java.lang.String r4 = "media"
            java.lang.String r5 = "Create MediaDrmBridge with level %s and origin %s"
            defpackage.fa1.h(r4, r5, r0, r1)
            r5 = 0
            java.util.UUID r13 = g(r15)     // Catch: java.lang.IllegalStateException -> La2 java.lang.IllegalArgumentException -> La7 android.media.UnsupportedSchemeException -> Lac
            if (r13 == 0) goto La1
            boolean r6 = android.media.MediaDrm.isCryptoSchemeSupported(r13)     // Catch: java.lang.IllegalStateException -> La2 java.lang.IllegalArgumentException -> La7 android.media.UnsupportedSchemeException -> Lac
            if (r6 != 0) goto L1e
            goto La1
        L1e:
            org.chromium.media.MediaDrmBridge r14 = new org.chromium.media.MediaDrmBridge     // Catch: java.lang.IllegalStateException -> La2 java.lang.IllegalArgumentException -> La7 android.media.UnsupportedSchemeException -> Lac
            r6 = r14
            r7 = r13
            r8 = r18
            r9 = r19
            r11 = r21
            r6.<init>(r7, r8, r9, r11)     // Catch: java.lang.IllegalStateException -> La2 java.lang.IllegalArgumentException -> La7 android.media.UnsupportedSchemeException -> Lac
            boolean r2 = r17.isEmpty()
            java.util.UUID r3 = org.chromium.media.MediaDrmBridge.l
            r6 = 0
            r7 = 1
            if (r2 != 0) goto L65
            boolean r2 = r13.equals(r3)
            if (r2 != 0) goto L3c
            goto L57
        L3c:
            java.lang.String r2 = r14.getSecurityLevel()
            java.lang.String r8 = ""
            boolean r8 = r2.equals(r8)
            if (r8 == 0) goto L49
            goto L5e
        L49:
            boolean r2 = r0.equals(r2)
            if (r2 == 0) goto L50
            goto L57
        L50:
            android.media.MediaDrm r2 = r14.a     // Catch: java.lang.Throwable -> L59
            java.lang.String r8 = "securityLevel"
            r2.setPropertyString(r8, r0)     // Catch: java.lang.Throwable -> L59
        L57:
            r0 = r7
            goto L5f
        L59:
            java.lang.String r2 = "Security level %s not supported!"
            defpackage.fa1.b(r4, r2, r0)
        L5e:
            r0 = r6
        L5f:
            if (r0 != 0) goto L65
            r14.n()
            return r5
        L65:
            boolean r0 = r16.isEmpty()
            if (r0 != 0) goto L97
            java.lang.String r2 = "Failed to set security origin %s"
            java.util.UUID r0 = r14.d
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L76
            goto L81
        L76:
            android.media.MediaDrm r0 = r14.a     // Catch: java.lang.IllegalStateException -> L83 java.lang.IllegalArgumentException -> L88
            java.lang.String r3 = "origin"
            r0.setPropertyString(r3, r1)     // Catch: java.lang.IllegalStateException -> L83 java.lang.IllegalArgumentException -> L88
            r14.i = r1     // Catch: java.lang.IllegalStateException -> L83 java.lang.IllegalArgumentException -> L88
            r14.j = r7     // Catch: java.lang.IllegalStateException -> L83 java.lang.IllegalArgumentException -> L88
        L81:
            r6 = r7
            goto L91
        L83:
            r0 = move-exception
            defpackage.fa1.c(r4, r2, r1, r0)
            goto L8c
        L88:
            r0 = move-exception
            defpackage.fa1.c(r4, r2, r1, r0)
        L8c:
            java.lang.String r0 = "Security origin %s not supported!"
            defpackage.fa1.b(r4, r0, r1)
        L91:
            if (r6 != 0) goto L97
            r14.n()
            return r5
        L97:
            if (r18 == 0) goto La0
            boolean r0 = r14.d()
            if (r0 != 0) goto La0
            return r5
        La0:
            return r14
        La1:
            return r5
        La2:
            r0 = move-exception
            android.util.Log.e(r3, r2, r0)
            return r5
        La7:
            r0 = move-exception
            android.util.Log.e(r3, r2, r0)
            return r5
        Lac:
            r0 = move-exception
            java.lang.String r1 = "Unsupported DRM scheme"
            android.util.Log.e(r3, r1, r0)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.chromium.media.MediaDrmBridge.create(byte[], java.lang.String, java.lang.String, boolean, long, long):org.chromium.media.MediaDrmBridge");
    }

    public static UUID g(byte[] bArr) {
        if (bArr.length != 16) {
            return null;
        }
        long j = 0;
        long j2 = 0;
        for (int i2 = 0; i2 < 8; i2++) {
            j2 = (j2 << 8) | ((long) (bArr[i2] & 255));
        }
        for (int i3 = 8; i3 < 16; i3++) {
            j = (j << 8) | ((long) (bArr[i3] & 255));
        }
        return new UUID(j2, j);
    }

    @CalledByNative
    public static int getFirstApiLevel() {
        try {
            return ((Integer) Class.forName("android.os.SystemProperties").getMethod("getInt", String.class, Integer.TYPE).invoke(null, "ro.product.first_api_level", 0)).intValue();
        } catch (Exception e2) {
            fa1.c("media", "Exception while getting system property %s. Using default.", "ro.product.first_api_level", e2);
            return 0;
        }
    }

    @CalledByNative
    public static boolean isCryptoSchemeSupported(byte[] bArr, String str) {
        UUID uuidG = g(bArr);
        return str.isEmpty() ? MediaDrm.isCryptoSchemeSupported(uuidG) : MediaDrm.isCryptoSchemeSupported(uuidG, str);
    }

    public final void c(l.a aVar) {
        try {
            this.a.closeSession(aVar.b);
        } catch (Exception e2) {
            Log.e("cr_media", "closeSession failed: ", e2);
        }
    }

    @CalledByNative
    public final void closeSession(byte[] bArr, long j) {
        if (this.a == null) {
            i(j, "closeSession() called when MediaDrm is null.");
            return;
        }
        l.a aVarF = f(bArr);
        if (aVarF == null) {
            i(j, "Invalid sessionId in closeSession(): " + l.a.a(bArr));
            return;
        }
        try {
            this.a.removeKeys(aVarF.b);
        } catch (Exception e2) {
            Log.e("cr_media", "removeKeys failed: ", e2);
        }
        c(aVarF);
        l lVar = this.g;
        lVar.a(aVarF);
        lVar.a.remove(ByteBuffer.wrap(aVarF.a));
        byte[] bArr2 = aVarF.b;
        if (bArr2 != null) {
            lVar.b.remove(ByteBuffer.wrap(bArr2));
        }
        if (h()) {
            N.MOzXytse(this.c, this, j);
        }
        if (h()) {
            N.MulYy5b7(this.c, this, aVarF.a);
        }
        aVarF.b();
    }

    @CalledByNative
    public final void createSessionFromNative(byte[] bArr, String str, int i2, String[] strArr, long j) {
        l.a aVar;
        HashMap<String, String> map = new HashMap<>();
        boolean z = false;
        if (strArr != null) {
            if (strArr.length % 2 != 0) {
                throw new IllegalArgumentException("Additional data array doesn't have equal keys/values");
            }
            for (int i3 = 0; i3 < strArr.length; i3 += 2) {
                map.put(strArr[i3], strArr[i3 + 1]);
            }
        }
        if (this.a == null) {
            Log.e("cr_media", "createSession() called when MediaDrm is null.");
            i(j, "MediaDrm released previously.");
            return;
        }
        try {
            byte[] bArrL = l();
            if (bArrL == null) {
                i(j, "Open session failed.");
                return;
            }
            try {
                if (i2 == 2) {
                    char[] cArr = l.a.d;
                    aVar = new l.a(c6.b(UUID.randomUUID().toString().replace('-', '0')), bArrL, null);
                } else {
                    aVar = new l.a(bArrL, bArrL, null);
                }
                l.a aVar2 = aVar;
                MediaDrm.KeyRequest keyRequestE = e(aVar2, bArr, str, i2, map);
                if (keyRequestE == null) {
                    c(aVar2);
                    i(j, "Generate request failed.");
                    return;
                }
                aVar2.b();
                j(j, aVar2);
                k(aVar2, keyRequestE);
                l lVar = this.g;
                lVar.getClass();
                l.b bVar = new l.b(aVar2, str, i2);
                lVar.a.put(ByteBuffer.wrap(aVar2.a), bVar);
                byte[] bArr2 = aVar2.b;
                if (bArr2 != null) {
                    lVar.b.put(ByteBuffer.wrap(bArr2), bVar);
                    return;
                }
                return;
            } catch (NotProvisionedException e2) {
                e = e2;
                z = true;
            }
        } catch (NotProvisionedException e3) {
            e = e3;
        }
        Log.e("cr_media", "Device not provisioned", e);
        if (z) {
            c(null);
        }
        i(j, "Device not provisioned during createSession().");
    }

    public final boolean d() {
        UUID uuid = this.d;
        try {
            byte[] bArrL = l();
            if (bArrL == null) {
                Log.e("cr_media", "Cannot create MediaCrypto Session.");
                return false;
            }
            l.a aVar = new l.a(bArrL, bArrL, null);
            this.f = aVar;
            aVar.b();
            try {
            } catch (MediaCryptoException e2) {
                Log.e("cr_media", "Cannot create MediaCrypto", e2);
            }
            if (!MediaCrypto.isCryptoSchemeSupported(uuid)) {
                Log.e("cr_media", "Cannot create MediaCrypto for unsupported scheme.");
                n();
                return false;
            }
            MediaCrypto mediaCrypto = new MediaCrypto(uuid, this.f.b);
            this.b = mediaCrypto;
            if (h()) {
                N.MV9yuwVC(this.c, this, mediaCrypto);
            }
            return true;
        } catch (NotProvisionedException unused) {
            h hVar = o;
            if (!hVar.a) {
                return o();
            }
            hVar.b.add(new a());
            return true;
        }
    }

    @CalledByNative
    public final void destroy() {
        this.c = 0L;
        if (this.a != null) {
            n();
        }
    }

    public final MediaDrm.KeyRequest e(l.a aVar, byte[] bArr, String str, int i2, HashMap<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        HashMap<String, String> map2 = map;
        try {
            return this.a.getKeyRequest(i2 == 3 ? aVar.c : aVar.b, bArr, str, i2, map2);
        } catch (IllegalStateException e2) {
            Log.e("cr_media", "Failed to getKeyRequest().", e2);
            return null;
        }
    }

    public final l.a f(byte[] bArr) {
        if (this.f == null) {
            Log.e("cr_media", "Session doesn't exist because media crypto session is not created.");
            return null;
        }
        l.b bVar = this.g.a.get(ByteBuffer.wrap(bArr));
        l.a aVar = bVar == null ? null : bVar.a;
        if (aVar == null) {
            return null;
        }
        return aVar;
    }

    @CalledByNative
    public final String getSecurityLevel() {
        if (this.a == null || !this.d.equals(l)) {
            Log.e("cr_media", "getSecurityLevel(): MediaDrm is null or security level is not supported.");
            return "";
        }
        try {
            return this.a.getPropertyString("securityLevel");
        } catch (IllegalStateException e2) {
            Log.e("cr_media", "Failed to get current security level", e2);
            return "";
        } catch (Exception e3) {
            Log.e("cr_media", "Failed to get current security level", e3);
            return "";
        }
    }

    public final boolean h() {
        return this.c != 0;
    }

    public final void i(long j, String str) {
        fa1.b("media", "onPromiseRejected: %s", str);
        if (h()) {
            N.M2P7BQ98(this.c, this, j, str);
        }
    }

    public final void j(long j, l.a aVar) {
        if (h()) {
            N.MtWWjNjU(this.c, this, j, aVar.a);
        }
    }

    public final void k(l.a aVar, MediaDrm.KeyRequest keyRequest) {
        if (h()) {
            N.Mf7HZHqV(this.c, this, aVar.a, keyRequest.getRequestType(), keyRequest.getData());
        }
    }

    public final byte[] l() throws NotProvisionedException {
        try {
            return (byte[]) this.a.openSession().clone();
        } catch (NotProvisionedException e2) {
            throw e2;
        } catch (MediaDrmException e3) {
            Log.e("cr_media", "Cannot open a new session", e3);
            n();
            return null;
        } catch (RuntimeException e4) {
            Log.e("cr_media", "Cannot open a new session", e4);
            n();
            return null;
        }
    }

    @CalledByNative
    public final void loadSession(byte[] bArr, long j) {
        l lVar = this.g;
        b bVar = new b(j);
        lVar.getClass();
        k kVar = new k(lVar, bVar);
        MediaDrmStorageBridge mediaDrmStorageBridge = lVar.c;
        long j2 = mediaDrmStorageBridge.a;
        if (j2 != -1) {
            N.Mmi_qOX8(j2, mediaDrmStorageBridge, bArr, kVar);
        } else {
            kVar.onResult(null);
        }
    }

    public final boolean m(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            Log.e("cr_media", "Invalid provision response.");
            return false;
        }
        try {
            this.a.provideProvisionResponse(bArr);
            return true;
        } catch (DeniedByServerException e2) {
            Log.e("cr_media", "failed to provide provision response", e2);
            return false;
        } catch (IllegalStateException e3) {
            Log.e("cr_media", "failed to provide provision response", e3);
            return false;
        }
    }

    public final void n() {
        l lVar = this.g;
        lVar.getClass();
        ArrayList<l.a> arrayList = new ArrayList();
        Iterator<l.b> it = lVar.a.values().iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().a);
        }
        for (l.a aVar : arrayList) {
            try {
                this.a.removeKeys(aVar.b);
            } catch (Exception e2) {
                Log.e("cr_media", "removeKeys failed: ", e2);
            }
            c(aVar);
            if (h()) {
                N.MulYy5b7(this.c, this, aVar.a);
            }
        }
        this.g = new l(this.h);
        l.a aVar2 = this.f;
        if (aVar2 != null) {
            c(aVar2);
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
        } else if (h()) {
            N.MV9yuwVC(this.c, this, null);
        }
    }

    public final boolean o() {
        if (!h()) {
            return false;
        }
        if (this.e) {
            o.a = true;
        }
        try {
            MediaDrm.ProvisionRequest provisionRequest = this.a.getProvisionRequest();
            fa1.g("media", "Provisioning origin ID %s", this.j ? this.i : "<none>");
            N.MmhSkOYV(this.c, this, provisionRequest.getDefaultUrl(), provisionRequest.getData());
            return true;
        } catch (IllegalStateException e2) {
            Log.e("cr_media", "Failed to get provisioning request", e2);
            return false;
        }
    }

    @CalledByNative
    public final void processProvisionResponse(boolean z, byte[] bArr) {
        boolean zM = (this.a == null || !z) ? false : m(bArr);
        boolean z2 = this.e;
        if (!z2) {
            N.MAaklmRW(this.c, this, zM);
            if (!zM) {
                n();
            }
        } else if (!zM) {
            n();
        } else if (this.j) {
            j jVar = new j(this);
            MediaDrmStorageBridge mediaDrmStorageBridge = this.h;
            long j = mediaDrmStorageBridge.a;
            if (j != -1) {
                N.ME6vNmlv(j, mediaDrmStorageBridge, jVar);
            } else {
                jVar.onResult(Boolean.TRUE);
            }
        } else {
            d();
        }
        if (z2) {
            h hVar = o;
            hVar.a = false;
            do {
                ArrayDeque arrayDeque = hVar.b;
                if (arrayDeque.isEmpty()) {
                    return;
                }
                Runnable runnable = (Runnable) arrayDeque.element();
                arrayDeque.remove();
                runnable.run();
            } while (!hVar.a);
        }
    }

    @CalledByNative
    public final void provision() {
        if (!this.j) {
            Log.e("cr_media", "Calling provision() without an origin.");
            N.MAaklmRW(this.c, this, false);
            return;
        }
        try {
            byte[] bArrL = l();
            if (bArrL != null) {
                c(new l.a(bArrL, bArrL, null));
            }
            N.MAaklmRW(this.c, this, true);
        } catch (NotProvisionedException unused) {
            if (o()) {
                return;
            }
            N.MAaklmRW(this.c, this, false);
        }
    }

    @CalledByNative
    public final void removeSession(byte[] bArr, long j) {
        l.a aVarF = f(bArr);
        if (aVarF == null) {
            i(j, "Session doesn't exist");
            return;
        }
        l.b bVarA = this.g.a(aVarF);
        if (bVarA.c == 1) {
            i(j, "Removing temporary session isn't implemented");
            return;
        }
        l lVar = this.g;
        c cVar = new c(j, aVarF, bVarA);
        l.b bVarA2 = lVar.a(aVarF);
        bVarA2.c = 3;
        l.a aVar = bVarA2.a;
        MediaDrmStorageBridge.PersistentInfo persistentInfo = new MediaDrmStorageBridge.PersistentInfo(aVar.a, aVar.c, bVarA2.b, 3);
        MediaDrmStorageBridge mediaDrmStorageBridge = lVar.c;
        long j2 = mediaDrmStorageBridge.a;
        if (j2 != -1) {
            N.MeALR1v2(j2, mediaDrmStorageBridge, persistentInfo, cVar);
        } else {
            cVar.onResult(Boolean.FALSE);
        }
    }

    @CalledByNative
    public final boolean setServerCertificate(byte[] bArr) {
        if (!this.d.equals(l)) {
            return true;
        }
        try {
            this.a.setPropertyByteArray("serviceCertificate", bArr);
            return true;
        } catch (IllegalArgumentException e2) {
            Log.e("cr_media", "Failed to set server certificate", e2);
            return false;
        } catch (IllegalStateException e3) {
            Log.e("cr_media", "Failed to set server certificate", e3);
            return false;
        }
    }

    @CalledByNative
    public final void unprovision() {
        if (this.a != null && this.j) {
            m(n);
        }
    }

    @CalledByNative
    public final void updateSession(byte[] bArr, byte[] bArr2, long j) {
        byte[] bArrProvideKeyResponse;
        if (this.a == null) {
            i(j, "updateSession() called when MediaDrm is null.");
            return;
        }
        l.a aVarF = f(bArr);
        if (aVarF == null) {
            i(j, "Invalid session in updateSession: " + l.a.a(bArr));
            return;
        }
        try {
            l.b bVarA = this.g.a(aVarF);
            boolean z = bVarA.c == 3;
            if (z) {
                this.a.provideKeyResponse(aVarF.c, bArr2);
                bArrProvideKeyResponse = null;
            } else {
                bArrProvideKeyResponse = this.a.provideKeyResponse(aVarF.b, bArr2);
            }
            g gVar = new g(aVarF, j, z);
            if (!z) {
                if (bVarA.c != 2 || bArrProvideKeyResponse == null || bArrProvideKeyResponse.length <= 0) {
                    gVar.onResult(Boolean.TRUE);
                    return;
                } else {
                    this.g.b(aVarF, bArrProvideKeyResponse, gVar);
                    return;
                }
            }
            l lVar = this.g;
            lVar.getClass();
            aVarF.c = null;
            MediaDrmStorageBridge mediaDrmStorageBridge = lVar.c;
            long j2 = mediaDrmStorageBridge.a;
            if (j2 != -1) {
                N.MYa_y6Dg(j2, mediaDrmStorageBridge, aVarF.a, gVar);
            } else {
                gVar.onResult(Boolean.TRUE);
            }
        } catch (DeniedByServerException e2) {
            Log.e("cr_media", "failed to provide key response", e2);
            i(j, "Update session failed.");
            n();
        } catch (NotProvisionedException e3) {
            Log.e("cr_media", "failed to provide key response", e3);
            i(j, "Update session failed.");
            n();
        } catch (IllegalArgumentException e4) {
            Log.e("cr_media", "failed to provide key response", e4);
            i(j, "Update session failed.");
            n();
        } catch (IllegalStateException e5) {
            Log.e("cr_media", "failed to provide key response", e5);
            i(j, "Update session failed.");
            n();
        }
    }
}
