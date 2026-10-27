package org.chromium.components.webauthn;

import J.N;
import defpackage.e70;
import defpackage.et1;
import defpackage.fg1;
import defpackage.gc3;
import defpackage.gq0;
import defpackage.gq2;
import defpackage.h72;
import defpackage.k72;
import defpackage.ke0;
import defpackage.oj2;
import defpackage.qy1;
import defpackage.r9;
import defpackage.ry1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.content_public.browser.RenderFrameHost;
import org.chromium.url.Origin;

/* JADX INFO: loaded from: classes3.dex */
public class InternalAuthenticator {
    public long a;
    public final r9 b;

    public InternalAuthenticator(long j, RenderFrameHost renderFrameHost) {
        this.a = j;
        this.b = new r9(null, renderFrameHost);
    }

    @CalledByNative
    public static InternalAuthenticator create(long j, RenderFrameHost renderFrameHost) {
        return new InternalAuthenticator(j, renderFrameHost);
    }

    @CalledByNative
    public void cancel() {
        this.b.getClass();
    }

    @CalledByNative
    public void clearNativePtr() {
        this.a = 0L;
    }

    @CalledByNative
    public void getAssertion(ByteBuffer byteBuffer) {
        this.b.e1(k72.d(new e70(new gq0(byteBuffer, new ArrayList()))), new gc3(this));
    }

    @CalledByNative
    public void getMatchingCredentialIds(String str, byte[][] bArr, boolean z) {
        this.b.getClass();
        ArrayList arrayList = new ArrayList();
        long j = this.a;
        if (j != 0) {
            N.M8quEYAo(j, (byte[][]) arrayList.toArray(new byte[0][]));
        }
    }

    @CalledByNative
    public boolean isGetMatchingCredentialIdsSupported() {
        this.b.getClass();
        return false;
    }

    @CalledByNative
    public void isUserVerifyingPlatformAuthenticatorAvailable() {
        this.b.getClass();
        Boolean bool = Boolean.FALSE;
        oj2.O("WebAuthentication.IsUVPlatformAuthenticatorAvailable2", bool.booleanValue());
        long j = this.a;
        if (j != 0) {
            N.MdPQq0XF(j, bool.booleanValue());
        }
    }

    @CalledByNative
    public void makeCredential(ByteBuffer byteBuffer) {
        this.b.n(h72.d(new e70(new gq0(byteBuffer, new ArrayList()))), new ke0(5, this));
    }

    @CalledByNative
    public void setEffectiveOrigin(Origin origin) {
        this.b.getClass();
    }

    @CalledByNative
    public void setPaymentOptions(ByteBuffer byteBuffer) {
        e70 e70VarC = gq2.c(new gq0(byteBuffer, new ArrayList()));
        try {
            e70VarC.c(fg1.d);
            ry1.d(e70VarC.t(8, false));
            qy1.d(e70VarC.t(16, false));
            e70VarC.w(24, true);
            et1.d(e70VarC.t(32, true));
            e70VarC.a();
            this.b.getClass();
        } catch (Throwable th) {
            e70VarC.a();
            throw th;
        }
    }
}
