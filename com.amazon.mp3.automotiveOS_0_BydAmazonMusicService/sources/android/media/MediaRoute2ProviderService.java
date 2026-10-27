package android.media;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.app.Service;
import java.util.Collection;

/* JADX INFO: loaded from: classes.dex */
public /* synthetic */ class MediaRoute2ProviderService extends Service {
    static {
        throw new NoClassDefFoundError();
    }

    @Nullable
    public final native /* synthetic */ RoutingSessionInfo getSessionInfo(@NonNull String str);

    public final native /* synthetic */ void notifyRequestFailed(long j, int i);

    public final native /* synthetic */ void notifyRoutes(@NonNull Collection<MediaRoute2Info> collection);

    public final native /* synthetic */ void notifySessionCreated(long j, @NonNull RoutingSessionInfo routingSessionInfo);

    public final native /* synthetic */ void notifySessionReleased(@NonNull String str);

    public final native /* synthetic */ void notifySessionUpdated(@NonNull RoutingSessionInfo routingSessionInfo);
}
