package com.amazon.music.platform.providers;

import com.amazon.music.inappmessaging.internal.model.Splash;
import kotlin.Metadata;

/* JADX INFO: compiled from: BuildInfoProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0012\u0010\u0006\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\b¨\u0006\t"}, d2 = {"Lcom/amazon/music/platform/providers/BuildInfoProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", Splash.PARAMS_APP_VERSION, "", "getAppVersion", "()Ljava/lang/String;", "isDebug", "", "()Z", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface BuildInfoProvider extends InterfaceProvider {
    String getAppVersion();

    boolean isDebug();
}
