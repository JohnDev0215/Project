package com.amazon.mp3.weblab;

import com.amazon.mp3.util.Log;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.data.WeblabTreatment;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.WeblabProvider;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: LegacyWeblabController.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\t\b\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007J\u0012\u0010\u0006\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0007J\u0018\u0010\t\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u00022\u0006\u0010\b\u001a\u00020\u0007R\u0018\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/weblab/LegacyWeblabController;", "", "", "weblab", "Lcom/amazon/music/platform/data/WeblabTreatment;", "getTreatmentAndRecordTrigger", "getTreatmentAndDontTrigger", "", "shouldTrigger", "getTreatment", "Lcom/amazon/music/platform/providers/WeblabProvider;", "weblabProvider", "Lcom/amazon/music/platform/providers/WeblabProvider;", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LegacyWeblabController {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = LegacyWeblabController.class.getSimpleName();
    private static final Lazy<LegacyWeblabController> instance$delegate = LazyKt__LazyJVMKt.lazy(new Function0<LegacyWeblabController>() { // from class: com.amazon.mp3.weblab.LegacyWeblabController$Companion$instance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LegacyWeblabController invoke() {
            return new LegacyWeblabController(null);
        }
    });
    private WeblabProvider weblabProvider;

    public /* synthetic */ LegacyWeblabController(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    public static final LegacyWeblabController getInstance() {
        return INSTANCE.getInstance();
    }

    private LegacyWeblabController() {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(WeblabProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        this.weblabProvider = (WeblabProvider) (interfaceProvider instanceof WeblabProvider ? interfaceProvider : null);
    }

    @Deprecated(message = "Deprecated!!", replaceWith = @ReplaceWith(expression = "getTreatment(weblab, true)", imports = {}))
    public final WeblabTreatment getTreatmentAndRecordTrigger(String weblab) {
        WeblabTreatment treatment = getTreatment(weblab, true);
        Log.error(TAG, "Weblab: " + ((Object) weblab) + ", treatment: " + treatment);
        return treatment;
    }

    @Deprecated(message = "Deprecated!!", replaceWith = @ReplaceWith(expression = "getTreatment(weblab, false)", imports = {}))
    public final WeblabTreatment getTreatmentAndDontTrigger(String weblab) {
        WeblabTreatment treatment = getTreatment(weblab, false);
        Log.error(TAG, "Weblab: " + ((Object) weblab) + ", treatment: " + treatment);
        return treatment;
    }

    public final WeblabTreatment getTreatment(String weblab, boolean shouldTrigger) {
        if (weblab == null || weblab.length() == 0) {
            return WeblabTreatment.C;
        }
        WeblabProvider weblabProvider = this.weblabProvider;
        WeblabTreatment treatment = weblabProvider == null ? null : weblabProvider.getTreatment(weblab, shouldTrigger);
        return treatment == null ? WeblabTreatment.C : treatment;
    }

    /* JADX INFO: compiled from: LegacyWeblabController.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u0006\u001a\u00020\u00078FX\u0087\u0084\u0002¢\u0006\u0012\n\u0004\b\u000b\u0010\f\u0012\u0004\b\b\u0010\u0002\u001a\u0004\b\t\u0010\n¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/weblab/LegacyWeblabController$Companion;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "instance", "Lcom/amazon/mp3/weblab/LegacyWeblabController;", "getInstance$annotations", "getInstance", "()Lcom/amazon/mp3/weblab/LegacyWeblabController;", "instance$delegate", "Lkotlin/Lazy;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final LegacyWeblabController getInstance() {
            return (LegacyWeblabController) LegacyWeblabController.instance$delegate.getValue();
        }
    }
}
