package com.amazon.music.share.menu;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.image.ImageUrlRepository;
import com.amazon.music.share.featuregaing.ShareFeatureGating;
import com.amazon.music.share.menu.destinations.ClipboardShareDestination;
import com.amazon.music.share.menu.destinations.DefaultShareDestination;
import com.amazon.music.share.menu.destinations.FacebookStoryShareDestination;
import com.amazon.music.share.menu.destinations.InstagramStoryShareDestination;
import com.amazon.music.share.menu.destinations.LineShareDestination;
import com.amazon.music.share.menu.destinations.MessagesShareDestination;
import com.amazon.music.share.menu.destinations.ShareDestination;
import com.amazon.music.share.menu.destinations.SnapchatStoryShareDestination;
import com.amazon.music.share.menu.destinations.TwitterShareDestination;
import com.amazon.music.share.menu.destinations.WhatsAppShareDestination;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: DefaultSharingViewModel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001%B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ \u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u000f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012J$\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u001c\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019J \u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00152\b\u0010 \u001a\u0004\u0018\u00010!J\u0006\u0010\"\u001a\u00020\u001eJ\u000e\u0010#\u001a\u00020\u001e2\u0006\u0010\t\u001a\u00020\nJ \u0010$\u001a\u00020\u001e2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020!H\u0002R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/amazon/music/share/menu/DefaultSharingViewModel;", "Landroidx/lifecycle/ViewModel;", "socialMetadataProvider", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "imageUrlRepository", "Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "(Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;Lcom/amazon/music/share/menu/providers/EnvironmentProvider;Lcom/amazon/music/platform/data/image/ImageUrlRepository;)V", "closeCallback", "Lcom/amazon/music/share/menu/DefaultSharingViewModel$CloseCallback;", "logger", "Lorg/slf4j/Logger;", "fetchImageUrl", "Landroidx/lifecycle/MutableLiveData;", "", ContextMappingConstants.ENTITY_ID, PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", "getDestinations", "", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "context", "Landroid/content/Context;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "imageDestinations", "", "getImageDestinations", "onItemShared", "", "destination", "content", "Lcom/amazon/music/share/menu/ShareableContent;", "removeCloseCallback", "setCloseCallback", "share", "CloseCallback", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultSharingViewModel extends ViewModel {
    private CloseCallback closeCallback;
    private final EnvironmentProvider environmentProvider;
    private final ImageUrlRepository imageUrlRepository;
    private final Logger logger;
    private final SocialMetadataProvider socialMetadataProvider;

    /* JADX INFO: compiled from: DefaultSharingViewModel.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/music/share/menu/DefaultSharingViewModel$CloseCallback;", "", "close", "", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface CloseCallback {
        void close();
    }

    public DefaultSharingViewModel(SocialMetadataProvider socialMetadataProvider, EnvironmentProvider environmentProvider, ImageUrlRepository imageUrlRepository) {
        Intrinsics.checkNotNullParameter(socialMetadataProvider, "socialMetadataProvider");
        Intrinsics.checkNotNullParameter(environmentProvider, "environmentProvider");
        Intrinsics.checkNotNullParameter(imageUrlRepository, "imageUrlRepository");
        this.socialMetadataProvider = socialMetadataProvider;
        this.environmentProvider = environmentProvider;
        this.imageUrlRepository = imageUrlRepository;
        Logger logger = LoggerFactory.getLogger(DefaultSharingViewModel.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
    }

    public final List<ShareDestination> getDestinations(Context context, StyleSheet styleSheet, boolean imageDestinations) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        ArrayList arrayList = new ArrayList();
        if (imageDestinations) {
            arrayList.addAll(getImageDestinations(context, styleSheet));
        }
        arrayList.addAll(CollectionsKt__CollectionsKt.mutableListOf(new WhatsAppShareDestination(context), new TwitterShareDestination(context), new MessagesShareDestination(context), new LineShareDestination(context), new ClipboardShareDestination(context), new DefaultShareDestination(context)));
        return arrayList;
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.DefaultSharingViewModel$fetchImageUrl$1, reason: invalid class name */
    /* JADX INFO: compiled from: DefaultSharingViewModel.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.DefaultSharingViewModel$fetchImageUrl$1", f = "DefaultSharingViewModel.kt", i = {}, l = {77, 78}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $entityId;
        final /* synthetic */ EntityType $entityType;
        final /* synthetic */ MutableLiveData<String> $liveData;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, EntityType entityType, MutableLiveData<String> mutableLiveData, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$entityId = str;
            this.$entityType = entityType;
            this.$liveData = mutableLiveData;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DefaultSharingViewModel.this.new AnonymousClass1(this.$entityId, this.$entityType, this.$liveData, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ImageUrlRepository imageUrlRepository = DefaultSharingViewModel.this.imageUrlRepository;
                String str = this.$entityId;
                EntityItemType platformEntityItemType = SharingUtil.INSTANCE.toPlatformEntityItemType(this.$entityType);
                this.label = 1;
                obj = imageUrlRepository.getImageUrl(str, platformEntityItemType, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            MainCoroutineDispatcher main2 = Dispatchers.getMain();
            C01131 c01131 = new C01131((String) obj, this.$liveData, null);
            this.label = 2;
            if (BuildersKt.withContext(main2, c01131, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.music.share.menu.DefaultSharingViewModel$fetchImageUrl$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: DefaultSharingViewModel.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.share.menu.DefaultSharingViewModel$fetchImageUrl$1$1", f = "DefaultSharingViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class C01131 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ String $imageUrl;
            final /* synthetic */ MutableLiveData<String> $liveData;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01131(String str, MutableLiveData<String> mutableLiveData, Continuation<? super C01131> continuation) {
                super(2, continuation);
                this.$imageUrl = str;
                this.$liveData = mutableLiveData;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01131(this.$imageUrl, this.$liveData, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01131) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                String str = this.$imageUrl;
                if (str != null) {
                    this.$liveData.setValue(str);
                }
                return Unit.INSTANCE;
            }
        }
    }

    public final MutableLiveData<String> fetchImageUrl(String entityId, EntityType entityType) {
        MutableLiveData<String> mutableLiveData = new MutableLiveData<>();
        BuildersKt__Builders_commonKt.launch$default(ViewModelKt.getViewModelScope(this), Dispatchers.getIO(), null, new AnonymousClass1(entityId, entityType, mutableLiveData, null), 2, null);
        return mutableLiveData;
    }

    public final List<ShareDestination> getImageDestinations(Context context, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        List<ShareDestination> listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new InstagramStoryShareDestination(context, this.socialMetadataProvider, this.environmentProvider, styleSheet), new FacebookStoryShareDestination(context, this.socialMetadataProvider, this.environmentProvider, styleSheet));
        if (ShareFeatureGating.SHARING_TO_SNAPCHAT.isEnabled()) {
            listMutableListOf.add(new SnapchatStoryShareDestination(context, this.environmentProvider, styleSheet));
        }
        return listMutableListOf;
    }

    public final void onItemShared(Context context, ShareDestination destination, ShareableContent content) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(destination, "destination");
        if (content == null) {
            return;
        }
        share(context, destination, content);
    }

    private final void share(Context context, ShareDestination destination, ShareableContent content) {
        try {
            destination.share(content);
        } catch (Exception e) {
            this.logger.error("Error sharing [" + content.getEntityType() + "] using [" + destination.getActionType() + ']', (Throwable) e);
            if (!(destination instanceof DefaultShareDestination)) {
                share(context, new DefaultShareDestination(context), content);
                return;
            }
        }
        CloseCallback closeCallback = this.closeCallback;
        if (closeCallback == null) {
            return;
        }
        closeCallback.close();
    }

    public final void setCloseCallback(CloseCallback closeCallback) {
        Intrinsics.checkNotNullParameter(closeCallback, "closeCallback");
        this.closeCallback = closeCallback;
    }

    public final void removeCloseCallback() {
        this.closeCallback = null;
    }
}
