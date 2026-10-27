package com.amazon.stratus.external;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.stratus.ListDevicesByCustomerIdRequest;
import com.amazon.stratus.ListDevicesByCustomerIdResponse;
import java.net.URL;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class ListDevicesByCustomerIdCall extends CoralCall<ListDevicesByCustomerIdRequest, ListDevicesByCustomerIdResponse> {
    public ListDevicesByCustomerIdCall(URL url, ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest, List<RequestInterceptor> list) {
        this(url, listDevicesByCustomerIdRequest, list, false);
    }

    public ListDevicesByCustomerIdCall(URL url, ListDevicesByCustomerIdRequest listDevicesByCustomerIdRequest, List<RequestInterceptor> list, boolean z) {
        super(url, listDevicesByCustomerIdRequest, list, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new ListDevicesByCustomerIdApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<ListDevicesByCustomerIdResponse> getResponseType() {
        return ListDevicesByCustomerIdResponse.class;
    }
}
