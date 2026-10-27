package com.spotify.connectivity.product_state.esperanto.proto;

import com.spotify.esperanto.esperanto.ClientBase;
import com.spotify.esperanto.esperanto.Transport;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import java.util.Base64;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
final class ProductStateClientImpl extends ClientBase implements ProductStateClient {
    private final Transport transport;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ProductStateClientImpl(Transport transport) {
        super(transport);
        v03.j(transport, "transport");
        this.transport = transport;
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient
    public Single<DelOverridesValuesResponse> DelOverridesValues(DelOverridesValuesRequest delOverridesValuesRequest) {
        v03.j(delOverridesValuesRequest, "request");
        Single map = callSingle("spotify.product_state.esperanto.proto.ProductState", "DelOverridesValues", delOverridesValuesRequest).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateClientImpl.DelOverridesValues.1
            @Override // io.reactivex.rxjava3.functions.o
            public final DelOverridesValuesResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return DelOverridesValuesResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.prod…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient
    public Single<GetValuesResponse> GetValues(GetValuesRequest getValuesRequest) {
        v03.j(getValuesRequest, "request");
        Single map = callSingle("spotify.product_state.esperanto.proto.ProductState", "GetValues", getValuesRequest).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateClientImpl.GetValues.1
            @Override // io.reactivex.rxjava3.functions.o
            public final GetValuesResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return GetValuesResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.product_state.esperanto.proto.GetValuesResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.prod…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient
    public Single<PutOverridesValuesResponse> PutOverridesValues(PutOverridesValuesRequest putOverridesValuesRequest) {
        v03.j(putOverridesValuesRequest, "request");
        Single map = callSingle("spotify.product_state.esperanto.proto.ProductState", "PutOverridesValues", putOverridesValuesRequest).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateClientImpl.PutOverridesValues.1
            @Override // io.reactivex.rxjava3.functions.o
            public final PutOverridesValuesResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return PutOverridesValuesResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.prod…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient
    public Single<PutValuesResponse> PutValues(PutValuesRequest putValuesRequest) {
        v03.j(putValuesRequest, "request");
        Single map = callSingle("spotify.product_state.esperanto.proto.ProductState", "PutValues", putValuesRequest).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateClientImpl.PutValues.1
            @Override // io.reactivex.rxjava3.functions.o
            public final PutValuesResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return PutValuesResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.product_state.esperanto.proto.PutValuesResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callSingle(\"spotify.prod…     }\n                })");
        return map;
    }

    @Override // com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient
    public Observable<GetValuesResponse> SubValues(SubValuesRequest subValuesRequest) {
        v03.j(subValuesRequest, "request");
        Observable map = callStream("spotify.product_state.esperanto.proto.ProductState", "SubValues", subValuesRequest).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateClientImpl.SubValues.1
            @Override // io.reactivex.rxjava3.functions.o
            public final GetValuesResponse apply(byte[] bArr) {
                v03.j(bArr, "data");
                try {
                    return GetValuesResponse.parseFrom(bArr);
                } catch (Exception e) {
                    throw new RuntimeException(u4.j("Unable to parse data as com.spotify.connectivity.product_state.esperanto.proto.GetValuesResponse: '", Base64.getEncoder().encodeToString(bArr), "' (Base64)"), e);
                }
            }
        });
        v03.i(map, "callStream(\"spotify.prod…     }\n                })");
        return map;
    }
}
