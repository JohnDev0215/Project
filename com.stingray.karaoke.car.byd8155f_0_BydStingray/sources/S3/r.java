package S3;

import S8.InterfaceC0725w;
import a4.AbstractC0922c;
import a4.AbstractC0927h;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import h8.AbstractC1386c;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class r extends s7.i implements InterfaceC2785n {
    public final /* synthetic */ O3.a l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Context f7962m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ String f7963n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public r(O3.a aVar, Context context, String str, InterfaceC2189c interfaceC2189c) {
        super(2, interfaceC2189c);
        this.l = aVar;
        this.f7962m = context;
        this.f7963n = str;
    }

    @Override // s7.AbstractC2408a
    public final InterfaceC2189c create(Object obj, InterfaceC2189c interfaceC2189c) {
        return new r(this.l, this.f7962m, this.f7963n, interfaceC2189c);
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws Throwable {
        r rVar = (r) create((InterfaceC0725w) obj, (InterfaceC2189c) obj2);
        m7.q qVar = m7.q.f19267a;
        rVar.invokeSuspend(qVar);
        return qVar;
    }

    @Override // s7.AbstractC2408a
    public final Object invokeSuspend(Object obj) throws Throwable {
        String str;
        EnumC2303a enumC2303a = EnumC2303a.l;
        AbstractC1386c.u(obj);
        for (O3.m mVar : ((HashMap) this.l.c()).values()) {
            A7.m.c(mVar);
            Bitmap bitmap = mVar.f5136f;
            String str2 = mVar.d;
            if (bitmap == null && Q8.o.z0(str2, "data:", false) && Q8.h.K0(str2, "base64,", 0, false, 6) > 0) {
                try {
                    String strSubstring = str2.substring(Q8.h.J0(str2, ',', 0, false, 6) + 1);
                    A7.m.e(strSubstring, "substring(...)");
                    byte[] bArrDecode = Base64.decode(strSubstring, 0);
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inScaled = true;
                    options.inDensity = 160;
                    mVar.f5136f = BitmapFactory.decodeByteArray(bArrDecode, 0, bArrDecode.length, options);
                } catch (IllegalArgumentException e10) {
                    AbstractC0922c.b("data URL did not have correct base64 format.", e10);
                }
            }
            Context context = this.f7962m;
            if (mVar.f5136f == null && (str = this.f7963n) != null) {
                try {
                    InputStream inputStreamOpen = context.getAssets().open(str + str2);
                    A7.m.c(inputStreamOpen);
                    Bitmap bitmapDecodeStream = null;
                    try {
                        BitmapFactory.Options options2 = new BitmapFactory.Options();
                        options2.inScaled = true;
                        options2.inDensity = 160;
                        bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpen, null, options2);
                    } catch (IllegalArgumentException e11) {
                        AbstractC0922c.b("Unable to decode image.", e11);
                    }
                    if (bitmapDecodeStream != null) {
                        mVar.f5136f = AbstractC0927h.d(bitmapDecodeStream, mVar.f5133a, mVar.f5134b);
                    }
                } catch (IOException e12) {
                    AbstractC0922c.b("Unable to open asset.", e12);
                }
            }
        }
        return m7.q.f19267a;
    }
}
