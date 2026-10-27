package q2;

import B5.H;
import android.media.DeniedByServerException;
import android.media.MediaCrypto;
import android.media.MediaCryptoException;
import android.media.MediaDrm;
import android.media.metrics.LogSessionId;
import e2.AbstractC1199g;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.UUID;
import m2.InterfaceC1794a;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes.dex */
public final class v implements s {
    public static final o2.d o = new o2.d(27);
    public final UUID l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final MediaDrm f21222m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f21223n;

    public v(UUID uuid) {
        uuid.getClass();
        UUID uuid2 = AbstractC1199g.f14100b;
        AbstractC1346c.b("Use C.CLEARKEY_UUID instead", !uuid2.equals(uuid));
        this.l = uuid;
        MediaDrm mediaDrm = new MediaDrm((AbstractC1343A.f15195a >= 27 || !AbstractC1199g.c.equals(uuid)) ? uuid : uuid2);
        this.f21222m = mediaDrm;
        this.f21223n = 1;
        if (AbstractC1199g.d.equals(uuid) && "ASUS_Z00AD".equals(AbstractC1343A.d)) {
            mediaDrm.setPropertyString("securityLevel", "L3");
        }
    }

    @Override // q2.s
    public final void B(byte[] bArr, o2.l lVar) {
        if (AbstractC1343A.f15195a >= 31) {
            try {
                MediaDrm mediaDrm = this.f21222m;
                o2.k kVar = lVar.f20450b;
                kVar.getClass();
                LogSessionId logSessionId = kVar.f20448a;
                if (logSessionId.equals(LogSessionId.LOG_SESSION_ID_NONE)) {
                    return;
                }
                MediaDrm.PlaybackComponent playbackComponent = mediaDrm.getPlaybackComponent(bArr);
                playbackComponent.getClass();
                o2.i.e(playbackComponent).setLogSessionId(logSessionId);
            } catch (UnsupportedOperationException unused) {
                AbstractC1346c.z("FrameworkMediaDrm", "setLogSessionId failed.");
            }
        }
    }

    @Override // q2.s
    public final synchronized void a() {
        int i7 = this.f21223n - 1;
        this.f21223n = i7;
        if (i7 == 0) {
            this.f21222m.release();
        }
    }

    @Override // q2.s
    public final void b(byte[] bArr, byte[] bArr2) {
        this.f21222m.restoreKeys(bArr, bArr2);
    }

    @Override // q2.s
    public final Map c(byte[] bArr) {
        return this.f21222m.queryKeyStatus(bArr);
    }

    @Override // q2.s
    public final void e(byte[] bArr) {
        this.f21222m.closeSession(bArr);
    }

    @Override // q2.s
    public final byte[] f(byte[] bArr, byte[] bArr2) {
        if (AbstractC1199g.c.equals(this.l) && AbstractC1343A.f15195a < 27) {
            try {
                JSONObject jSONObject = new JSONObject(AbstractC1343A.m(bArr2));
                StringBuilder sb = new StringBuilder("{\"keys\":[");
                JSONArray jSONArray = jSONObject.getJSONArray("keys");
                for (int i7 = 0; i7 < jSONArray.length(); i7++) {
                    if (i7 != 0) {
                        sb.append(",");
                    }
                    JSONObject jSONObject2 = jSONArray.getJSONObject(i7);
                    sb.append("{\"k\":\"");
                    sb.append(jSONObject2.getString("k").replace('-', '+').replace('_', '/'));
                    sb.append("\",\"kid\":\"");
                    sb.append(jSONObject2.getString("kid").replace('-', '+').replace('_', '/'));
                    sb.append("\",\"kty\":\"");
                    sb.append(jSONObject2.getString("kty"));
                    sb.append("\"}");
                }
                sb.append("]}");
                bArr2 = sb.toString().getBytes(StandardCharsets.UTF_8);
            } catch (JSONException e10) {
                AbstractC1346c.q("ClearKeyUtil", "Failed to adjust response data: ".concat(AbstractC1343A.m(bArr2)), e10);
            }
        }
        return this.f21222m.provideKeyResponse(bArr, bArr2);
    }

    @Override // q2.s
    public final r h() {
        MediaDrm.ProvisionRequest provisionRequest = this.f21222m.getProvisionRequest();
        return new r(provisionRequest.getDefaultUrl(), provisionRequest.getData());
    }

    @Override // q2.s
    public final void j(byte[] bArr) throws DeniedByServerException {
        this.f21222m.provideProvisionResponse(bArr);
    }

    /* JADX WARN: Removed duplicated region for block: B:108:0x023e  */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01cd  */
    @Override // q2.s
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final q2.q l(byte[] r17, java.util.List r18, int r19, java.util.HashMap r20) throws android.media.NotProvisionedException {
        /*
            Method dump skipped, instruction units count: 645
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: q2.v.l(byte[], java.util.List, int, java.util.HashMap):q2.q");
    }

    @Override // q2.s
    public final void m(final C2172c c2172c) {
        this.f21222m.setOnEventListener(new MediaDrm.OnEventListener() { // from class: q2.u
            @Override // android.media.MediaDrm.OnEventListener
            public final void onEvent(MediaDrm mediaDrm, byte[] bArr, int i7, int i10, byte[] bArr2) {
                v vVar = this.f21220a;
                C2172c c2172c2 = c2172c;
                vVar.getClass();
                H h10 = c2172c2.f21183a.f21204x;
                h10.getClass();
                h10.obtainMessage(i7, bArr).sendToTarget();
            }
        });
    }

    @Override // q2.s
    public final int o() {
        return 2;
    }

    @Override // q2.s
    public final InterfaceC1794a w(byte[] bArr) {
        int i7 = AbstractC1343A.f15195a;
        UUID uuid = this.l;
        if (i7 < 27 && AbstractC1199g.c.equals(uuid)) {
            uuid = AbstractC1199g.f14100b;
        }
        return new t(uuid, bArr);
    }

    @Override // q2.s
    public final boolean x(String str, byte[] bArr) throws Throwable {
        MediaCrypto mediaCrypto;
        Throwable th;
        boolean zEquals;
        int i7 = AbstractC1343A.f15195a;
        UUID uuid = this.l;
        if (i7 >= 31) {
            boolean zEquals2 = uuid.equals(AbstractC1199g.d);
            MediaDrm mediaDrm = this.f21222m;
            if (zEquals2) {
                String propertyString = mediaDrm.getPropertyString("version");
                zEquals = (propertyString.startsWith("v5.") || propertyString.startsWith("14.") || propertyString.startsWith("15.") || propertyString.startsWith("16.0")) ? false : true;
            } else {
                zEquals = uuid.equals(AbstractC1199g.c);
            }
            if (zEquals) {
                return mediaDrm.requiresSecureDecoder(str, mediaDrm.getSecurityLevel(bArr));
            }
        }
        MediaCrypto mediaCrypto2 = null;
        try {
            mediaCrypto = new MediaCrypto(uuid, bArr);
        } catch (MediaCryptoException unused) {
        } catch (Throwable th2) {
            mediaCrypto = null;
            th = th2;
        }
        try {
            boolean zRequiresSecureDecoderComponent = mediaCrypto.requiresSecureDecoderComponent(str);
            mediaCrypto.release();
            return zRequiresSecureDecoderComponent;
        } catch (MediaCryptoException unused2) {
            mediaCrypto2 = mediaCrypto;
            if (mediaCrypto2 == null) {
                return true;
            }
            mediaCrypto2.release();
            return true;
        } catch (Throwable th3) {
            th = th3;
            if (mediaCrypto != null) {
                mediaCrypto.release();
            }
            throw th;
        }
    }

    @Override // q2.s
    public final byte[] z() {
        return this.f21222m.openSession();
    }
}
