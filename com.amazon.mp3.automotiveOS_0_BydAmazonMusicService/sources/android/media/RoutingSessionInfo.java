package android.media;

import android.annotation.NonNull;
import android.annotation.Nullable;
import android.os.Bundle;
import android.os.Parcelable;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class RoutingSessionInfo implements Parcelable {

    public final /* synthetic */ class Builder {
        static {
            throw new NoClassDefFoundError();
        }

        public /* synthetic */ Builder(@NonNull RoutingSessionInfo routingSessionInfo) {
        }

        public /* synthetic */ Builder(@NonNull String str, @NonNull String str2) {
        }

        @NonNull
        public native /* synthetic */ Builder addDeselectableRoute(@NonNull String str);

        @NonNull
        public native /* synthetic */ Builder addSelectableRoute(@NonNull String str);

        @NonNull
        public native /* synthetic */ Builder addSelectedRoute(@NonNull String str);

        @NonNull
        public native /* synthetic */ Builder addTransferableRoute(@NonNull String str);

        @NonNull
        public native /* synthetic */ RoutingSessionInfo build();

        @NonNull
        public native /* synthetic */ Builder clearDeselectableRoutes();

        @NonNull
        public native /* synthetic */ Builder clearSelectableRoutes();

        @NonNull
        public native /* synthetic */ Builder clearSelectedRoutes();

        @NonNull
        public native /* synthetic */ Builder clearTransferableRoutes();

        @NonNull
        public native /* synthetic */ Builder setControlHints(@Nullable Bundle bundle);

        @NonNull
        public native /* synthetic */ Builder setName(@Nullable CharSequence charSequence);

        @NonNull
        public native /* synthetic */ Builder setVolume(int i);

        @NonNull
        public native /* synthetic */ Builder setVolumeHandling(int i);

        @NonNull
        public native /* synthetic */ Builder setVolumeMax(int i);
    }

    static {
        throw new NoClassDefFoundError();
    }

    @Nullable
    public native /* synthetic */ Bundle getControlHints();

    @Nullable
    public native /* synthetic */ CharSequence getName();

    @NonNull
    public native /* synthetic */ List<String> getSelectedRoutes();
}
