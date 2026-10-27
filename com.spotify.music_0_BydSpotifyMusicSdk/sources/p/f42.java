package p;

import com.spotify.rcs.resolver.grpc.v0.ResolveRequest;
import com.spotify.rcs.resolver.grpc.v0.ResolveResponse;
import com.spotify.ucs.proto.v0.UcsRequest;
import com.spotify.ucs.proto.v0.UcsResponseWrapper;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface f42 {
    @t11({"Content-Type: application/protobuf", "Accept: application/protobuf", "No-Webgate-Authentication: true"})
    @gr1("remote-config-resolver/v3/unauth/configuration/")
    Single<u42<ResolveResponse>> a(@hm ResolveRequest resolveRequest);

    @t11({"Content-Type: application/protobuf", "Accept: application/protobuf"})
    @gr1("user-customization-service/v1/customize")
    Single<u42<UcsResponseWrapper>> b(@hm UcsRequest ucsRequest, @q11("if-none-match") String str);

    @t11({"Content-Type: application/protobuf", "Accept: application/protobuf"})
    @gr1("user-customization-service/v1/customize")
    Single<u42<UcsResponseWrapper>> c(@hm UcsRequest ucsRequest);
}
