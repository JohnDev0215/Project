package p;

import com.spotify.voiceassistants.playermodels.ContextResponseKt;
import com.spotify.voiceassistants.playermodels.SearchEndpointResponse;
import com.spotify.voiceassistants.playermodels.SpeakeasyDualResponse;

/* JADX INFO: loaded from: classes.dex */
public final class we2 implements io.reactivex.rxjava3.functions.o {
    public static final we2 c = new we2();

    @Override // io.reactivex.rxjava3.functions.o
    public final Object apply(Object obj) {
        SearchEndpointResponse searchEndpointResponse = (SearchEndpointResponse) obj;
        String action = searchEndpointResponse.getAction();
        return (!v03.b(searchEndpointResponse.getResult(), ContextResponseKt.RESULT_SUCCESS) || action == null) ? new SpeakeasyDualResponse.FailedResponse(searchEndpointResponse.getResult(), searchEndpointResponse, searchEndpointResponse.getError()) : new SpeakeasyDualResponse.CanPrepareResponse.PartialResponse(action, searchEndpointResponse);
    }
}
