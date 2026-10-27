package com.neusoft.na.common;

import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;

/* JADX INFO: compiled from: IGreatWallComponent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/neusoft/na/common/IGreatWallComponent;", "", "getAppRunningStatus", "", "setAppRunningStatusListener", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/neusoft/na/common/IAppRunningStatusListener;", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IGreatWallComponent {
    boolean getAppRunningStatus();

    void setAppRunningStatusListener(IAppRunningStatusListener listener);
}
