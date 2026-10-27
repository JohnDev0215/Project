package coil.network;

import android.graphics.Bitmap;
import java.io.EOFException;
import kotlin.UnsafeLazyImpl;
import kotlin.text.b;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.Response;
import p.i;
import p.py0;
import p.v02;
import p.v03;
import p.v81;
import p.w02;

/* JADX INFO: loaded from: classes.dex */
public final class a {
    public final v81 a = new UnsafeLazyImpl(new py0() { // from class: coil.network.CacheResponse$cacheControl$2
        {
            super(0);
        }

        @Override // p.py0
        public final Object invoke() {
            CacheControl.Companion companion = CacheControl.n;
            Headers headers = this.this$0.f;
            companion.getClass();
            return CacheControl.Companion.a(headers);
        }
    });
    public final v81 b = new UnsafeLazyImpl(new py0() { // from class: coil.network.CacheResponse$contentType$2
        {
            super(0);
        }

        @Override // p.py0
        public final Object invoke() {
            String strA = this.this$0.f.a("Content-Type");
            if (strA == null) {
                return null;
            }
            MediaType.d.getClass();
            return MediaType.Companion.b(strA);
        }
    });
    public final long c;
    public final long d;
    public final boolean e;
    public final Headers f;

    public a(w02 w02Var) throws EOFException {
        this.c = Long.parseLong(w02Var.g0(Long.MAX_VALUE));
        this.d = Long.parseLong(w02Var.g0(Long.MAX_VALUE));
        this.e = Integer.parseInt(w02Var.g0(Long.MAX_VALUE)) > 0;
        int i = Integer.parseInt(w02Var.g0(Long.MAX_VALUE));
        Headers.Builder builder = new Headers.Builder();
        for (int i2 = 0; i2 < i; i2++) {
            String strG0 = w02Var.g0(Long.MAX_VALUE);
            Bitmap.Config[] configArr = i.a;
            int iN0 = b.n0(strG0, ':', 0, false, 6);
            if (iN0 == -1) {
                throw new IllegalArgumentException("Unexpected header: ".concat(strG0).toString());
            }
            String strSubstring = strG0.substring(0, iN0);
            v03.i(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            String string = b.G0(strSubstring).toString();
            String strSubstring2 = strG0.substring(iN0 + 1);
            v03.i(strSubstring2, "this as java.lang.String).substring(startIndex)");
            builder.d(string, strSubstring2);
        }
        this.f = builder.e();
    }

    public final void a(v02 v02Var) {
        v02Var.t0(this.c);
        v02Var.R(10);
        v02Var.t0(this.d);
        v02Var.R(10);
        v02Var.t0(this.e ? 1L : 0L);
        v02Var.R(10);
        Headers headers = this.f;
        v02Var.t0(headers.size());
        v02Var.R(10);
        int size = headers.size();
        for (int i = 0; i < size; i++) {
            v02Var.s0(headers.b(i));
            v02Var.s0(": ");
            v02Var.s0(headers.d(i));
            v02Var.R(10);
        }
    }

    public a(Response response) {
        this.c = response.s;
        this.d = response.t;
        this.e = response.m != null;
        this.f = response.n;
    }
}
