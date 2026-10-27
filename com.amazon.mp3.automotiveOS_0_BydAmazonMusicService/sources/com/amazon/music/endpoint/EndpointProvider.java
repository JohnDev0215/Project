package com.amazon.music.endpoint;

import java.net.URL;
import org.apache.commons.lang3.Validate;

/* JADX INFO: loaded from: classes4.dex */
public class EndpointProvider {
    public URL getCirrusEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.CIRRUS);
    }

    public URL getPlaylistEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.PLAYLIST);
    }

    public URL getMuseEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.MUSE);
    }

    public URL getMuseBrushEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.MUSE_BRUSH);
    }

    public URL getStratusEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.STRATUS);
    }

    public URL getTenzingTextSearchEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.TENZING_TEXT_SEARCH);
    }

    public URL getTenzingSuggestEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.TENZING_TEXT_SUGGEST);
    }

    public URL getTenzingInstantSearchEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.TENZING_TEXT_INSTANT_SEARCH);
    }

    public URL getNautilusEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.NAUTILUS);
    }

    public URL getDMLSEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.DMLS);
    }

    public URL getIAMEndpoint(EndpointURLRequest endpointURLRequest) throws Exception {
        return getEndpoint(endpointURLRequest, EndpointType.IAM);
    }

    private URL getEndpoint(EndpointURLRequest endpointURLRequest, EndpointType endpointType) throws Exception {
        Validate.notNull(endpointURLRequest);
        Validate.notNull(endpointURLRequest.getMarketplace());
        return new URL(Configuration.get().get(endpointURLRequest.getMarketplace()).get(endpointType));
    }
}
