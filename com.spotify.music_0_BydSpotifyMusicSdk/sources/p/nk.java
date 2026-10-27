package p;

import android.net.Uri;
import android.os.Bundle;
import com.spotify.core.core.DeviceType;
import com.spotify.voiceassistants.playermodels.ParsedQuery;
import com.spotify.voiceassistants.playermodels.ParsedQueryKt;
import com.spotify.voiceassistants.playermodels.SearchEndpointRequest;
import com.spotify.voiceassistants.playermodels.SourceDevice;

/* JADX INFO: loaded from: classes.dex */
public final class nk {
    public final pv2 a;
    public final SourceDevice b;

    public nk(pv2 pv2Var, DeviceType deviceType, String str, String str2) {
        this.a = pv2Var;
        this.b = new SourceDevice(str, str2, deviceType.toString(), "local_device", true);
    }

    public final SearchEndpointRequest a(Uri uri, Bundle bundle) {
        String string = bundle.getString("android.intent.extra.user_query", "");
        String string2 = bundle.getString("android.intent.extra.user_query_language", "");
        ParsedQuery parsedQuery = new ParsedQuery(ParsedQueryKt.INTENT_PLAY, uri.toString());
        SourceDevice sourceDevice = this.b;
        return new SearchEndpointRequest(string, string2, sourceDevice, sourceDevice, parsedQuery, "UNKNOWN", null);
    }
}
