package com.amazon.music.skyfire.ui.providers;

import com.amazon.music.skyfire.ui.skyfire.ServiceStage;
import com.google.android.gms.common.internal.ImagesContract;
import kotlin.Metadata;

/* JADX INFO: compiled from: SkyFireConfigurationProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0012\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\nH&R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004R\u0012\u0010\u0005\u001a\u00020\u0006X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0012\u0010\t\u001a\u00020\nX¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/music/skyfire/ui/providers/SkyFireConfigurationProvider;", "", "isStageOverridden", "", "()Z", "serviceStage", "Lcom/amazon/music/skyfire/ui/skyfire/ServiceStage;", "getServiceStage", "()Lcom/amazon/music/skyfire/ui/skyfire/ServiceStage;", "serviceURL", "", "getServiceURL", "()Ljava/lang/String;", "setServiceURL", "", ImagesContract.URL, "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public interface SkyFireConfigurationProvider {
    ServiceStage getServiceStage();

    String getServiceURL();

    boolean isStageOverridden();

    void setServiceURL(String url);
}
