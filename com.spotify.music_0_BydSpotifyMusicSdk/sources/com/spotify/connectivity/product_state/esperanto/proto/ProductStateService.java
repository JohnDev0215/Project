package com.spotify.connectivity.product_state.esperanto.proto;

import com.spotify.esperanto.esperanto.ServiceBase;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.o;
import p.u4;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class ProductStateService implements ServiceBase {
    private final String name = "spotify.product_state.esperanto.proto.ProductState";

    public abstract Single<DelOverridesValuesResponse> DelOverridesValues(DelOverridesValuesRequest delOverridesValuesRequest);

    public abstract Single<GetValuesResponse> GetValues(GetValuesRequest getValuesRequest);

    public abstract Single<PutOverridesValuesResponse> PutOverridesValues(PutOverridesValuesRequest putOverridesValuesRequest);

    public abstract Single<PutValuesResponse> PutValues(PutValuesRequest putValuesRequest);

    public abstract Observable<GetValuesResponse> SubValues(SubValuesRequest subValuesRequest);

    @Override // com.spotify.esperanto.esperanto.Transport
    public Single<byte[]> callSingle(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (!v03.b(str, getName())) {
            StringBuilder sbP = u4.p("Attempted to access mismatched [", str, "], but this service is [");
            sbP.append(getName());
            sbP.append(']');
            throw new RuntimeException(sbP.toString());
        }
        if (v03.b(str2, "GetValues")) {
            GetValuesRequest from = GetValuesRequest.parseFrom(bArr);
            v03.i(from, "request_msg");
            Single map = GetValues(from).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateService.callSingle.1
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(GetValuesResponse getValuesResponse) {
                    v03.j(getValuesResponse, "it");
                    return getValuesResponse.toByteArray();
                }
            });
            v03.i(map, "GetValues(request_msg).m…it.toByteArray()\n      })");
            return map;
        }
        if (v03.b(str2, "PutValues")) {
            PutValuesRequest from2 = PutValuesRequest.parseFrom(bArr);
            v03.i(from2, "request_msg");
            Single map2 = PutValues(from2).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateService.callSingle.2
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(PutValuesResponse putValuesResponse) {
                    v03.j(putValuesResponse, "it");
                    return putValuesResponse.toByteArray();
                }
            });
            v03.i(map2, "PutValues(request_msg).m…it.toByteArray()\n      })");
            return map2;
        }
        if (v03.b(str2, "PutOverridesValues")) {
            PutOverridesValuesRequest from3 = PutOverridesValuesRequest.parseFrom(bArr);
            v03.i(from3, "request_msg");
            Single map3 = PutOverridesValues(from3).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateService.callSingle.3
                @Override // io.reactivex.rxjava3.functions.o
                public final byte[] apply(PutOverridesValuesResponse putOverridesValuesResponse) {
                    v03.j(putOverridesValuesResponse, "it");
                    return putOverridesValuesResponse.toByteArray();
                }
            });
            v03.i(map3, "PutOverridesValues(reque…it.toByteArray()\n      })");
            return map3;
        }
        if (!v03.b(str2, "DelOverridesValues")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        DelOverridesValuesRequest from4 = DelOverridesValuesRequest.parseFrom(bArr);
        v03.i(from4, "request_msg");
        Single map4 = DelOverridesValues(from4).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateService.callSingle.4
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(DelOverridesValuesResponse delOverridesValuesResponse) {
                v03.j(delOverridesValuesResponse, "it");
                return delOverridesValuesResponse.toByteArray();
            }
        });
        v03.i(map4, "DelOverridesValues(reque…it.toByteArray()\n      })");
        return map4;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public Observable<byte[]> callStream(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (!v03.b(str, getName())) {
            StringBuilder sbP = u4.p("Attempted to access mismatched [", str, "], but this service is [");
            sbP.append(getName());
            sbP.append(']');
            throw new RuntimeException(sbP.toString());
        }
        if (!v03.b(str2, "SubValues")) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        SubValuesRequest from = SubValuesRequest.parseFrom(bArr);
        v03.i(from, "request_msg");
        Observable map = SubValues(from).map(new o() { // from class: com.spotify.connectivity.product_state.esperanto.proto.ProductStateService.callStream.1
            @Override // io.reactivex.rxjava3.functions.o
            public final byte[] apply(GetValuesResponse getValuesResponse) {
                v03.j(getValuesResponse, "it");
                return getValuesResponse.toByteArray();
            }
        });
        v03.i(map, "SubValues(request_msg).m…it.toByteArray()\n      })");
        return map;
    }

    @Override // com.spotify.esperanto.esperanto.Transport
    public byte[] callSync(String str, String str2, byte[] bArr) {
        v03.j(str, "service");
        v03.j(str2, "method");
        v03.j(bArr, "payload");
        if (v03.b(str, getName())) {
            throw new RuntimeException(u4.i("Attempted to access unknown method. [", str, ':', str2, ']'));
        }
        StringBuilder sbP = u4.p("Attempted to access mismatched [", str, "], but this service is [");
        sbP.append(getName());
        sbP.append(']');
        throw new RuntimeException(sbP.toString());
    }

    @Override // com.spotify.esperanto.esperanto.ServiceBase
    public String getName() {
        return this.name;
    }
}
