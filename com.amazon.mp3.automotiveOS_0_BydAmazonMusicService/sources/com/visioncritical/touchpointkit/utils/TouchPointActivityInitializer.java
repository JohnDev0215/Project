package com.visioncritical.touchpointkit.utils;

import android.content.Context;
import androidx.startup.Initializer;
import com.visioncritical.touchpointkit.utils.TouchPointActivity;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TouchPointActivityInitializer.kt */
/* JADX INFO: loaded from: classes7.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\u001a\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\b\u0001\u0012\u0006\u0012\u0002\b\u00030\u00010\t0\bH\u0016¨\u0006\n"}, d2 = {"Lcom/visioncritical/touchpointkit/utils/TouchPointActivityInitializer;", "Landroidx/startup/Initializer;", "Lcom/visioncritical/touchpointkit/utils/TouchPointActivity;", "()V", "create", "context", "Landroid/content/Context;", "dependencies", "", "Ljava/lang/Class;", "touchpointkit_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TouchPointActivityInitializer implements Initializer<TouchPointActivity> {
    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.startup.Initializer
    public TouchPointActivity create(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        TouchPointActivity.Companion companion = TouchPointActivity.INSTANCE;
        companion.getShared().initialize(context);
        return companion.getShared();
    }

    @Override // androidx.startup.Initializer
    public List<Class<? extends Initializer<?>>> dependencies() {
        return CollectionsKt__CollectionsKt.emptyList();
    }
}
