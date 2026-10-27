package com.amazon.mp3.dmsfcore.providers;

import android.content.Context;
import com.amazon.mp3.util.BauhausToastUtils;
import com.amazon.music.skyfire.ui.providers.NotificationProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt__StringsJVMKt;

/* JADX INFO: compiled from: DMSFNotificationProvider.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\t2\b\u0010\n\u001a\u0004\u0018\u00010\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/dmsfcore/providers/DMSFNotificationProvider;", "Lcom/amazon/music/skyfire/ui/providers/NotificationProvider;", "applicationContext", "Landroid/content/Context;", "(Landroid/content/Context;)V", "showNotification", "", "context", "message", "", "style", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DMSFNotificationProvider implements NotificationProvider {
    private final Context applicationContext;

    public DMSFNotificationProvider(Context applicationContext) {
        Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
        this.applicationContext = applicationContext;
    }

    @Override // com.amazon.music.skyfire.ui.providers.NotificationProvider
    public void showNotification(Context context, String message, String style) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (!StringsKt__StringsJVMKt.equals$default(style, "COLORED", false, 2, null)) {
            context = this.applicationContext;
        }
        BauhausToastUtils.showTextToast(context, message, 1);
    }
}
