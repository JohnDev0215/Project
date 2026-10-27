package com.amazon.mp3.upsellweb;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ManageFamilyPageWebTargetBuilderFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\u0003\u001a\u00020\u0002H\u0016R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/amazon/mp3/upsellweb/ManageFamilyPageWebTargetBuilderFactory;", "", "Lcom/amazon/mp3/upsellweb/ManageFamilyPageWebTargetBuilder;", "newBuilder", "Landroid/content/Context;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ManageFamilyPageWebTargetBuilderFactory {
    private final Context context;

    public ManageFamilyPageWebTargetBuilderFactory(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    public ManageFamilyPageWebTargetBuilder newBuilder() {
        return new ManageFamilyPageWebTargetBuilder(this.context);
    }
}
