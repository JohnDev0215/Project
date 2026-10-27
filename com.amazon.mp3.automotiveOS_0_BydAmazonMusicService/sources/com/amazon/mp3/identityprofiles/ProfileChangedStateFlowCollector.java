package com.amazon.mp3.identityprofiles;

import android.content.Context;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.StateFlow;

/* JADX INFO: compiled from: ProfileChangedStateFlowCollector.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B%\u0012\u0012\b\u0002\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002R\u001e\u0010\b\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0007\u0018\u00010\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/identityprofiles/ProfileChangedStateFlowCollector;", "", "Landroid/content/Context;", "context", "", "collect", "Lkotlinx/coroutines/flow/StateFlow;", "", "profileChangedStateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "<init>", "(Lkotlinx/coroutines/flow/StateFlow;Lkotlinx/coroutines/CoroutineScope;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ProfileChangedStateFlowCollector {
    private final StateFlow<String> profileChangedStateFlow;
    private final CoroutineScope scope;

    public ProfileChangedStateFlowCollector() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public ProfileChangedStateFlowCollector(StateFlow<String> stateFlow, CoroutineScope scope) {
        Intrinsics.checkNotNullParameter(scope, "scope");
        this.profileChangedStateFlow = stateFlow;
        this.scope = scope;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ ProfileChangedStateFlowCollector(StateFlow stateFlow, CoroutineScope coroutineScope, int i, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i & 1) != 0) {
            AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
            String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
            Intrinsics.checkNotNull(originalClassName);
            InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
            ProfileProvider profileProvider = (ProfileProvider) (interfaceProvider instanceof ProfileProvider ? interfaceProvider : null);
            stateFlow = profileProvider == null ? null : (StateFlow) profileProvider.getProfileSwitchNotificationPublisher();
        }
        this(stateFlow, (i & 2) != 0 ? CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()) : coroutineScope);
    }

    public final void collect(Context context) {
        if (context == null) {
            return;
        }
        BuildersKt__Builders_commonKt.launch$default(this.scope, null, null, new ProfileChangedStateFlowCollector$collect$1$1(this, context, null), 3, null);
    }
}
