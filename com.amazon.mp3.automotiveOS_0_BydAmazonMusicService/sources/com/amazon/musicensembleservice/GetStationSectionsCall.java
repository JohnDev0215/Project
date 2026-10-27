package com.amazon.musicensembleservice;

import com.amazon.hermes.ApiInfo;
import com.amazon.hermes.CoralCall;
import com.amazon.hermes.RequestInterceptor;
import com.amazon.musicensembleservice.stations.GetStationSectionsRequest;
import com.amazon.musicensembleservice.stations.GetStationSectionsResponse;
import java.net.URL;

/* JADX INFO: loaded from: classes2.dex */
public class GetStationSectionsCall extends CoralCall<GetStationSectionsRequest, GetStationSectionsResponse> {
    public GetStationSectionsCall(URL url, GetStationSectionsRequest getStationSectionsRequest, RequestInterceptor requestInterceptor) {
        this(url, getStationSectionsRequest, requestInterceptor, false);
    }

    public GetStationSectionsCall(URL url, GetStationSectionsRequest getStationSectionsRequest, RequestInterceptor requestInterceptor, boolean z) {
        super(url, getStationSectionsRequest, requestInterceptor, z);
    }

    @Override // com.amazon.hermes.CoralCall
    public ApiInfo getApiInfo() {
        return new GetStationSectionsApiInfo();
    }

    @Override // com.amazon.hermes.CoralCall
    public Class<GetStationSectionsResponse> getResponseType() {
        return GetStationSectionsResponse.class;
    }
}
