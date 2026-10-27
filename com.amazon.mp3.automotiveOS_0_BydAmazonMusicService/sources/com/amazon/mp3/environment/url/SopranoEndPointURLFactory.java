package com.amazon.mp3.environment.url;

import com.amazon.mp3.util.Log;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SopranoEndPointURLFactory.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00052\u00020\u0001:\u0001\u0005B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/environment/url/SopranoEndPointURLFactory;", "Lcom/amazon/mp3/environment/url/BaseEndPointURLFactory;", "()V", "get", "Lcom/amazon/mp3/environment/url/EndPointURL;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SopranoEndPointURLFactory extends BaseEndPointURLFactory {
    private static final String TAG = SopranoEndPointURLFactory.class.getSimpleName();

    @Override // com.amazon.mp3.environment.url.BaseEndPointURLFactory, com.amazon.mp3.environment.url.EndPointURLFactory
    public EndPointURL get() {
        try {
            return new EndPointURL(EndpointsProvider.get().getSopranosServiceEndpoint());
        } catch (Exception e) {
            Log.error(TAG, Intrinsics.stringPlus("Can't get Soprano endpoint ", e));
            return null;
        }
    }
}
