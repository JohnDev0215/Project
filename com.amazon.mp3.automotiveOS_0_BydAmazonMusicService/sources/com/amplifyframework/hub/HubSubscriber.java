package com.amplifyframework.hub;

import android.util.Log;
import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.hub.HubEvent;

/* JADX INFO: loaded from: classes5.dex */
public interface HubSubscriber {
    void onEvent(@NonNull HubEvent<?> hubEvent);

    static <T extends HubEvent.Data> HubSubscriber create(@NonNull final Consumer<T> consumer) {
        return new HubSubscriber() { // from class: com.amplifyframework.hub.HubSubscriber$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.hub.HubSubscriber
            public final void onEvent(HubEvent hubEvent) {
                HubSubscriber.lambda$create$0(consumer, hubEvent);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    static /* synthetic */ void lambda$create$0(Consumer consumer, HubEvent hubEvent) {
        try {
            consumer.accept((HubEvent.Data) hubEvent.getData());
        } catch (Exception e) {
            Log.w("amplify:aws-hub", "Unable to cast event data for event type " + hubEvent.getName(), e);
        }
    }
}
