package com.amazon.mp3.widget;

import android.content.Intent;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.widget.WidgetMetrics;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AlexaWidgetContainerActivityObserver.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lcom/amazon/mp3/widget/AlexaWidgetContainerActivityObserver;", "Landroidx/lifecycle/DefaultLifecycleObserver;", "widgetMetrics", "Lcom/amazon/mp3/widget/WidgetMetrics;", MAPAccountManager.KEY_INTENT, "Landroid/content/Intent;", "(Lcom/amazon/mp3/widget/WidgetMetrics;Landroid/content/Intent;)V", "onCreate", "", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "Landroidx/lifecycle/LifecycleOwner;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AlexaWidgetContainerActivityObserver implements DefaultLifecycleObserver {
    private final Intent intent;
    private final WidgetMetrics widgetMetrics;

    public AlexaWidgetContainerActivityObserver(WidgetMetrics widgetMetrics, Intent intent) {
        Intrinsics.checkNotNullParameter(widgetMetrics, "widgetMetrics");
        Intrinsics.checkNotNullParameter(intent, "intent");
        this.widgetMetrics = widgetMetrics;
        this.intent = intent;
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        if ("com.amazon.mp3.widget.startalexa" == this.intent.getAction()) {
            this.widgetMetrics.sendWidgetMetricEvent(WidgetMetrics.WidgetClickEvent.ALEXA);
        }
    }
}
