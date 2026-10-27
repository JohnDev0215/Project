package p;

import com.spotify.voiceassistants.playermodels.CommandPlayRequest;
import com.spotify.voiceassistants.playermodels.CommandPlayResponse;
import com.spotify.voiceassistants.playermodels.SearchEndpointRequest;
import com.spotify.voiceassistants.playermodels.SearchEndpointResponse;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes.dex */
public interface se2 {
    @gr1("speakeasy/v2/search")
    Single<SearchEndpointResponse> a(@hm SearchEndpointRequest searchEndpointRequest, @ez1("include") String str);

    @gr1("speakeasy/v2/command/play")
    Single<CommandPlayResponse> b(@hm CommandPlayRequest commandPlayRequest);
}
