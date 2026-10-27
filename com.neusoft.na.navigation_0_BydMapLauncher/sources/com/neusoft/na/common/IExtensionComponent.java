package com.neusoft.na.common;

import androidx.core.app.NotificationCompat;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Metadata;

/* JADX INFO: compiled from: IExtensionComponent.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\tH&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\fH&J\b\u0010\r\u001a\u00020\u0003H&¨\u0006\u000e"}, d2 = {"Lcom/neusoft/na/common/IExtensionComponent;", "", "setLanguageOnOffCallback", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/neusoft/na/common/ILanOnOffListener;", "setTimeFormatCallback", "Lcom/neusoft/na/common/ITimeFormatListener;", "setTtsCallback", "Lcom/neusoft/na/common/ITTSListener;", "speak", NotificationCompat.CATEGORY_MESSAGE, "", "stopTTS", "common_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public interface IExtensionComponent {
    void setLanguageOnOffCallback(ILanOnOffListener listener);

    void setTimeFormatCallback(ITimeFormatListener listener);

    void setTtsCallback(ITTSListener listener);

    void speak(String msg);

    void stopTTS();
}
