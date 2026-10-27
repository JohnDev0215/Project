package com.amazon.music.find.skyfire.providers;

import CoreInterface.v1_0.Element;
import Touch.WidgetsInterface.v1_0.SearchBarElement;
import com.amazon.music.find.skyfire.binders.SearchBarElementBinder;
import com.amazon.music.skyfire.ui.providers.DownloadProvider;
import com.amazon.music.skyfire.ui.providers.StyleSheetProvider;
import com.amazon.music.skyfire.ui.providers.UserSubscriptionProvider;
import com.amazon.music.skyfire.ui.providers.impl.DefaultElementBindingProvider;
import com.amazon.music.skyfire.ui.widgets.binders.ElementBinder;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FindElementBindingProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ6\u0010\u0010\u001a0\u0012,\u0012*\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\r0\f\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e0\f0\u000bj\u0002`\u000f0\nH\u0014R<\u0010\t\u001a0\u0012,\u0012*\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00020\r0\f\u0012\u0014\u0012\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u000e0\f0\u000bj\u0002`\u000f0\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/find/skyfire/providers/FindElementBindingProvider;", "Lcom/amazon/music/skyfire/ui/providers/impl/DefaultElementBindingProvider;", "userSubscriptionProvider", "Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;", "styleSheetProvider", "Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;", "downloadProvider", "Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;", "(Lcom/amazon/music/skyfire/ui/providers/UserSubscriptionProvider;Lcom/amazon/music/skyfire/ui/providers/StyleSheetProvider;Lcom/amazon/music/skyfire/ui/providers/DownloadProvider;)V", "findBinderTypePairs", "", "Lkotlin/Pair;", "Ljava/lang/Class;", "LCoreInterface/v1_0/Element;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "Lcom/amazon/music/skyfire/ui/providers/impl/ElementBindingPair;", "initializeBindingPairs", "DMMFindExperience_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FindElementBindingProvider extends DefaultElementBindingProvider {
    private final List<Pair<Class<? extends Element>, Class<? extends ElementBinder<?, ?>>>> findBinderTypePairs;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FindElementBindingProvider(UserSubscriptionProvider userSubscriptionProvider, StyleSheetProvider styleSheetProvider, DownloadProvider downloadProvider) {
        super(userSubscriptionProvider, styleSheetProvider, downloadProvider);
        Intrinsics.checkNotNullParameter(userSubscriptionProvider, "userSubscriptionProvider");
        Intrinsics.checkNotNullParameter(styleSheetProvider, "styleSheetProvider");
        Intrinsics.checkNotNullParameter(downloadProvider, "downloadProvider");
        this.findBinderTypePairs = CollectionsKt__CollectionsJVMKt.listOf(TuplesKt.to(SearchBarElement.class, SearchBarElementBinder.class));
    }

    @Override // com.amazon.music.skyfire.ui.providers.impl.DefaultElementBindingProvider
    protected List<Pair<Class<? extends Element>, Class<? extends ElementBinder<?, ?>>>> initializeBindingPairs() {
        return CollectionsKt___CollectionsKt.plus((Collection) this.findBinderTypePairs, (Iterable) super.initializeBindingPairs());
    }
}
