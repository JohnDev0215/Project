package B8;

import Q7.InterfaceC0657j;
import T.C0746i0;
import android.media.MediaCodec;
import android.media.MediaMuxer;
import android.view.Surface;
import e0.C1183w;
import h8.C1391h;
import io.sentry.EnumC1495p1;
import io.sentry.F1;
import java.util.List;
import l8.AbstractC1783a;
import l8.C1790h;
import l8.InterfaceC1788f;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class k {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Object f779a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f780b;
    public final Object c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f781e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f782f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f783g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f784h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public Object f785i;

    public k(F1 f12, io.sentry.android.replay.video.a aVar) {
        A7.m.f(f12, "options");
        this.f779a = f12;
        this.f780b = aVar;
        this.c = null;
        m7.g gVar = m7.g.f19257m;
        m7.f fVarN = h6.r.n(gVar, io.sentry.android.replay.video.c.f16450m);
        this.d = fVarN;
        MediaCodec mediaCodecCreateByCodecName = ((Boolean) fVarN.getValue()).booleanValue() ? MediaCodec.createByCodecName("c2.android.avc.encoder") : MediaCodec.createEncoderByType(aVar.f16445f);
        A7.m.e(mediaCodecCreateByCodecName, "if (hasExynosCodec) {\n  …onfig.mimeType)\n        }");
        this.f781e = mediaCodecCreateByCodecName;
        this.f782f = h6.r.n(gVar, new C1183w(this, 4));
        this.f783g = new MediaCodec.BufferInfo();
        String absolutePath = aVar.f16442a.getAbsolutePath();
        A7.m.e(absolutePath, "muxerConfig.file.absolutePath");
        this.f784h = new io.sentry.android.replay.video.b(absolutePath, aVar.d);
    }

    public k a(InterfaceC0657j interfaceC0657j, List list, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, C1790h c1790h, AbstractC1783a abstractC1783a) {
        A7.m.f(list, "typeParameterProtos");
        A7.m.f(interfaceC1788f, "nameResolver");
        A7.m.f(c0746i0, "typeTable");
        A7.m.f(c1790h, "versionRequirementTable");
        A7.m.f(abstractC1783a, "metadataVersion");
        int i7 = abstractC1783a.f18823b;
        return new k((i) this.f779a, interfaceC1788f, interfaceC0657j, c0746i0, ((i7 != 1 || abstractC1783a.c < 4) && i7 <= 1) ? (C1790h) this.f781e : c1790h, abstractC1783a, (C1391h) this.f783g, (D) this.f784h, list);
    }

    /* JADX WARN: Code restructure failed: missing block: B:43:0x014a, code lost:
    
        throw new java.lang.RuntimeException(A7.k.g("encoderOutputBuffer ", r5, " was null"));
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(boolean r13) {
        /*
            Method dump skipped, instruction units count: 331
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.k.c(boolean):void");
    }

    public void d() {
        MediaCodec mediaCodec = (MediaCodec) this.f781e;
        try {
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) this.c;
            if (interfaceC2772a != null) {
                interfaceC2772a.a();
            }
            c(true);
            mediaCodec.stop();
            mediaCodec.release();
            Surface surface = (Surface) this.f785i;
            if (surface != null) {
                surface.release();
            }
            MediaMuxer mediaMuxer = ((io.sentry.android.replay.video.b) this.f784h).f16447b;
            mediaMuxer.stop();
            mediaMuxer.release();
        } catch (Throwable th) {
            ((F1) this.f779a).getLogger().s(EnumC1495p1.DEBUG, "Failed to properly release video encoder", th);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:8:0x0073  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public k(B8.i r2, l8.InterfaceC1788f r3, Q7.InterfaceC0657j r4, T.C0746i0 r5, l8.C1790h r6, l8.AbstractC1783a r7, h8.C1391h r8, B8.D r9, java.util.List r10) {
        /*
            r1 = this;
            java.lang.String r0 = "components"
            A7.m.f(r2, r0)
            java.lang.String r0 = "nameResolver"
            A7.m.f(r3, r0)
            java.lang.String r0 = "containingDeclaration"
            A7.m.f(r4, r0)
            java.lang.String r0 = "typeTable"
            A7.m.f(r5, r0)
            java.lang.String r0 = "versionRequirementTable"
            A7.m.f(r6, r0)
            java.lang.String r0 = "metadataVersion"
            A7.m.f(r7, r0)
            java.lang.String r0 = "typeParameters"
            A7.m.f(r10, r0)
            r1.<init>()
            r1.f779a = r2
            r1.f780b = r3
            r1.c = r4
            r1.d = r5
            r1.f781e = r6
            r1.f782f = r7
            r1.f783g = r8
            B8.D r0 = new B8.D
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Deserializer for \""
            r2.<init>(r3)
            o8.f r3 = r4.getName()
            r2.append(r3)
            r3 = 34
            r2.append(r3)
            java.lang.String r6 = r2.toString()
            if (r8 == 0) goto L73
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "Class '"
            r2.<init>(r3)
            o8.b r3 = r8.a()
            o8.c r3 = r3.b()
            java.lang.String r3 = r3.b()
            r2.append(r3)
            r3 = 39
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            if (r2 != 0) goto L71
            goto L73
        L71:
            r7 = r2
            goto L76
        L73:
            java.lang.String r2 = "[container not found]"
            goto L71
        L76:
            r2 = r0
            r3 = r1
            r4 = r9
            r5 = r10
            r2.<init>(r3, r4, r5, r6, r7)
            r1.f784h = r0
            B8.s r2 = new B8.s
            r2.<init>(r1)
            r1.f785i = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: B8.k.<init>(B8.i, l8.f, Q7.j, T.i0, l8.h, l8.a, h8.h, B8.D, java.util.List):void");
    }
}
