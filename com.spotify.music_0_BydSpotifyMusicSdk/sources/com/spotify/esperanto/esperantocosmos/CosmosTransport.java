package com.spotify.esperanto.esperantocosmos;

import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.nio.charset.Charset;
import p.hf2;
import p.ry0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosTransport implements Transport {
    private final Charset CHARSET_UTF8;
    private final EsperantoRxRouter mRouter;

    public CosmosTransport(EsperantoRxRouter esperantoRxRouter) {
        v03.j(esperantoRxRouter, "mRouter");
        this.mRouter = esperantoRxRouter;
        this.CHARSET_UTF8 = Charset.forName("UTF8");
    }

    private final Observable<byte[]> call(String str, String str2, byte[] bArr, String str3) {
        final String str4 = "sp://esperanto/" + str + '/' + str2;
        Request request = new Request(str3, str4, bArr);
        final ry0 ry0Var = new ry0() { // from class: com.spotify.esperanto.esperantocosmos.CosmosTransport$call$mapFunc$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // p.ry0
            public final byte[] invoke(Response response) {
                String str5;
                v03.j(response, "response");
                if (response.getStatus() == 200) {
                    byte[] body = response.getBody();
                    return body == null ? new byte[0] : body;
                }
                byte[] body2 = response.getBody();
                if (body2 != null) {
                    Charset charset = this.this$0.CHARSET_UTF8;
                    v03.i(charset, "CHARSET_UTF8");
                    str5 = new String(body2, charset);
                } else {
                    str5 = new String();
                }
                StringBuilder sb = new StringBuilder("Response for [");
                sb.append(str4);
                sb.append("] returned with status code ");
                sb.append(response.getStatus());
                sb.append(": '");
                throw new RuntimeException(hf2.d(sb, str5, '\''));
            }
        };
        Observable map = this.mRouter.resolve(request).map(new o(ry0Var) { // from class: com.spotify.esperanto.esperantocosmos.CosmosTransport$sam$io_reactivex_rxjava3_functions_Function$0
            private final /* synthetic */ ry0 function;

            {
                v03.j(ry0Var, "function");
                this.function = ry0Var;
            }

            @Override // io.reactivex.rxjava3.functions.o
            public final /* synthetic */ Object apply(Object obj) {
                return this.function.invoke(obj);
            }
        });
        v03.i(map, "mRouter.resolve(request).map(mapFunc)");
        return map;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Single<byte[]> callSingle(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        Single<byte[]> singleFirstOrError = call(str, str2, bArr, Request.POST).firstOrError();
        v03.i(singleFirstOrError, "call(service, method, pa…uest.POST).firstOrError()");
        return singleFirstOrError;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable<byte[]> callStream(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        return call(str, str2, bArr, Request.SUB);
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public byte[] callSync(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        return new byte[0];
    }
}
