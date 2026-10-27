package org.chromium.components.payments;

import J.N;
import defpackage.e70;
import defpackage.gq0;
import defpackage.lz1;
import defpackage.m8;
import defpackage.nz1;
import defpackage.sy1;
import defpackage.ty1;
import defpackage.wz1;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes3.dex */
public class PaymentRequestSpec {
    public long a;

    public PaymentRequestSpec(nz1 nz1Var, sy1 sy1Var, Collection<lz1> collection, String str) {
        ByteBuffer byteBufferB = nz1Var.b();
        ByteBuffer byteBufferB2 = sy1Var.b();
        ByteBuffer[] byteBufferArr = new ByteBuffer[collection.size()];
        Iterator<lz1> it = collection.iterator();
        int i = 0;
        while (it.hasNext()) {
            byteBufferArr[i] = it.next().b();
            i++;
        }
        this.a = N.MAELBHG4(byteBufferB, byteBufferB2, byteBufferArr, str);
    }

    public final m8 a() {
        m8 m8Var = new m8();
        for (byte[] bArr : N.MFWoiqvU(this.a)) {
            lz1 lz1VarD = lz1.d(new e70(new gq0(ByteBuffer.wrap(bArr), new ArrayList())));
            m8Var.put(lz1VarD.b, lz1VarD);
        }
        return m8Var;
    }

    public final m8 b() {
        m8 m8Var = new m8();
        sy1 sy1VarC = c();
        if (sy1VarC.e != null) {
            int i = 0;
            while (true) {
                ty1[] ty1VarArr = sy1VarC.e;
                if (i >= ty1VarArr.length) {
                    break;
                }
                ty1 ty1Var = ty1VarArr[i];
                m8Var.put(ty1Var.d.b, ty1Var);
                i++;
            }
        }
        return m8Var;
    }

    public final sy1 c() {
        return sy1.d(new e70(new gq0(ByteBuffer.wrap(N.MNkVMjnE(this.a)), new ArrayList())));
    }

    public final nz1 d() {
        return nz1.d(new e70(new gq0(ByteBuffer.wrap(N.Mc7EwNM$(this.a)), new ArrayList())));
    }

    public final List<wz1> e() {
        wz1[] wz1VarArr = c().d;
        return Collections.unmodifiableList(wz1VarArr != null ? Arrays.asList(wz1VarArr) : new ArrayList());
    }

    @CalledByNative
    public final long getNativePointer() {
        return this.a;
    }
}
