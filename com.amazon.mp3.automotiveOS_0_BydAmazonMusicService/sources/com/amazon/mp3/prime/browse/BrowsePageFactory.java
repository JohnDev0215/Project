package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.brush.RxUiV3Page;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.ProfileProvider;
import java.util.concurrent.CompletableFuture;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;

/* JADX INFO: compiled from: BrowsePageFactory.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\u0018\u0000 \u00032\u00020\u0001:\u0001\u0003B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/prime/browse/BrowsePageFactory;", "", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class BrowsePageFactory {
    private static final ProfileProvider profileProvider;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(0 == true ? 1 : 0);
    private static final CoroutineScope viewModelScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());

    /* JADX INFO: compiled from: BrowsePageFactory.kt */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0007\u001a\u00020\b2\u000e\u0010\t\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\n2\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\f\u001a\u00020\rR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/prime/browse/BrowsePageFactory$Companion;", "", "()V", "profileProvider", "Lcom/amazon/music/platform/providers/ProfileProvider;", "viewModelScope", "Lkotlinx/coroutines/CoroutineScope;", "createRxUiV3Page", "", "pageRequestFuture", "Ljava/util/concurrent/CompletableFuture;", "Lcom/amazon/mp3/brush/RxUiV3Page;", "context", "Landroid/content/Context;", "getProfileId", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void createRxUiV3Page(CompletableFuture<RxUiV3Page> pageRequestFuture, Context context) {
            Intrinsics.checkNotNullParameter(pageRequestFuture, "pageRequestFuture");
            Intrinsics.checkNotNullParameter(context, "context");
            BuildersKt__Builders_commonKt.launch$default(BrowsePageFactory.viewModelScope, null, null, new BrowsePageFactory$Companion$createRxUiV3Page$1(context, pageRequestFuture, null), 3, null);
        }

        public final String getProfileId(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            ProfileProvider profileProvider = BrowsePageFactory.profileProvider;
            if (profileProvider == null) {
                return null;
            }
            profileProvider.currentProfile();
            return null;
        }
    }

    static {
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(ProfileProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        profileProvider = (ProfileProvider) (interfaceProvider instanceof ProfileProvider ? interfaceProvider : null);
    }
}
