package org.openapitools.client.infrastructure;

import A7.m;
import E5.InterfaceC0189n;
import E5.N;
import java.net.URI;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001J\u0017\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lorg/openapitools/client/infrastructure/URIAdapter;", "", "Ljava/net/URI;", "uri", "", "toJson", "(Ljava/net/URI;)Ljava/lang/String;", "s", "fromJson", "(Ljava/lang/String;)Ljava/net/URI;", "kca-v2_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class URIAdapter {
    @InterfaceC0189n
    public final URI fromJson(String s6) {
        m.f(s6, "s");
        URI uriCreate = URI.create(s6);
        m.e(uriCreate, "create(...)");
        return uriCreate;
    }

    @N
    public final String toJson(URI uri) {
        m.f(uri, "uri");
        String string = uri.toString();
        m.e(string, "toString(...)");
        return string;
    }
}
