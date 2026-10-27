package p;

import com.spotify.eventsender.gabo.PublishEventsRequest;
import com.spotify.eventsender.gabo.PublishEventsResponse;

/* JADX INFO: loaded from: classes.dex */
public interface tz0 {
    @t11({"No-Webgate-Authentication: true", "Content-Type: application/x-protobuf"})
    @gr1("gabo-receiver-service/public/v3/events")
    jp<PublishEventsResponse> a(@hm PublishEventsRequest publishEventsRequest);

    @t11({"Content-Type: application/x-protobuf"})
    @gr1("gabo-receiver-service/v3/events")
    jp<PublishEventsResponse> b(@hm PublishEventsRequest publishEventsRequest);
}
