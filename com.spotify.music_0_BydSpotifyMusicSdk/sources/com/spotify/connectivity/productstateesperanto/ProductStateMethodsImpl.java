package com.spotify.connectivity.productstateesperanto;

import com.google.protobuf.i;
import com.spotify.connectivity.product_state.esperanto.proto.DelOverridesValuesRequest;
import com.spotify.connectivity.product_state.esperanto.proto.GetValuesResponse;
import com.spotify.connectivity.product_state.esperanto.proto.ProductStateClient;
import com.spotify.connectivity.product_state.esperanto.proto.PutOverridesValuesRequest;
import com.spotify.connectivity.product_state.esperanto.proto.PutValuesRequest;
import com.spotify.connectivity.product_state.esperanto.proto.SubValuesRequest;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.o;
import java.util.Map;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ProductStateMethodsImpl implements ProductStateMethods {
    private final ProductStateClient productStateClient;

    public ProductStateMethodsImpl(ProductStateClient productStateClient) {
        v03.j(productStateClient, "productStateClient");
        this.productStateClient = productStateClient;
    }

    @Override // com.spotify.connectivity.productstateesperanto.ProductStateMethods
    public Completable overrideValue(String str, String str2) {
        v03.j(str, "key");
        v03.j(str2, "value");
        ProductStateClient productStateClient = this.productStateClient;
        i iVarBuild = PutOverridesValuesRequest.newBuilder().putPairs(str, str2).build();
        v03.i(iVarBuild, "newBuilder()\n           …\n                .build()");
        Completable completableIgnoreElement = productStateClient.PutOverridesValues((PutOverridesValuesRequest) iVarBuild).ignoreElement();
        v03.i(completableIgnoreElement, "productStateClient.PutOv…        ).ignoreElement()");
        return completableIgnoreElement;
    }

    @Override // com.spotify.connectivity.productstateesperanto.ProductStateMethods
    public Completable removeOverride(String str) {
        v03.j(str, "key");
        ProductStateClient productStateClient = this.productStateClient;
        i iVarBuild = DelOverridesValuesRequest.newBuilder().addKeys(str).build();
        v03.i(iVarBuild, "newBuilder()\n           …\n                .build()");
        Completable completableIgnoreElement = productStateClient.DelOverridesValues((DelOverridesValuesRequest) iVarBuild).ignoreElement();
        v03.i(completableIgnoreElement, "productStateClient.DelOv…        ).ignoreElement()");
        return completableIgnoreElement;
    }

    @Override // com.spotify.connectivity.productstateesperanto.ProductStateMethods
    public Completable updateState(String str, String str2) {
        v03.j(str, "key");
        v03.j(str2, "value");
        ProductStateClient productStateClient = this.productStateClient;
        i iVarBuild = PutValuesRequest.newBuilder().putPairs(str, str2).build();
        v03.i(iVarBuild, "newBuilder().putPairs(key, value).build()");
        Completable completableIgnoreElement = productStateClient.PutValues((PutValuesRequest) iVarBuild).ignoreElement();
        v03.i(completableIgnoreElement, "productStateClient.PutVa…         .ignoreElement()");
        return completableIgnoreElement;
    }

    @Override // com.spotify.connectivity.productstateesperanto.ProductStateMethods
    public Observable<Map<String, String>> values() {
        ProductStateClient productStateClient = this.productStateClient;
        SubValuesRequest defaultInstance = SubValuesRequest.getDefaultInstance();
        v03.i(defaultInstance, "getDefaultInstance()");
        Observable map = productStateClient.SubValues(defaultInstance).map(new o() { // from class: com.spotify.connectivity.productstateesperanto.ProductStateMethodsImpl.values.1
            @Override // io.reactivex.rxjava3.functions.o
            public final Map<String, String> apply(GetValuesResponse getValuesResponse) {
                return getValuesResponse.getPairsMap();
            }
        });
        v03.i(map, "productStateClient\n     …se -> response.pairsMap }");
        return map;
    }
}
