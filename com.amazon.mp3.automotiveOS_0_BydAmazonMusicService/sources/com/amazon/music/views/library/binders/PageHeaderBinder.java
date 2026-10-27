package com.amazon.music.views.library.binders;

import android.content.Context;
import android.view.View;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.models.DownloadStateModel;
import com.amazon.music.views.library.models.PageHeaderActionIconModel;
import com.amazon.music.views.library.models.PageHeaderModel;
import com.amazon.music.views.library.providers.ContentEnabilityProvider;
import com.amazon.music.views.library.providers.MetadataContextMenuProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.views.PageHeaderView;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;

/* JADX INFO: compiled from: PageHeaderBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\b&\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0004*\u00020\u00032\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0005BC\u0012\u0006\u0010\u001a\u001a\u00020\u0019\u0012\u0006\u0010+\u001a\u00020*\u0012\u0016\b\u0002\u00101\u001a\u0010\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0011\u0018\u00010/\u0012\n\b\u0002\u00106\u001a\u0004\u0018\u000105\u0012\u0006\u0010;\u001a\u00020:¢\u0006\u0004\bT\u0010UJ\u001b\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006H\u0082@ø\u0001\u0000¢\u0006\u0004\b\t\u0010\nJ\u001f\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ3\u0010\u0015\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\b\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0014\u001a\u00020\u0013H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J\u001f\u0010\u0017\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H\u0002¢\u0006\u0004\b\u0017\u0010\u000fJ5\u0010\u001c\u001a\u0004\u0018\u00010\r2\b\u0010\u0018\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001a\u001a\u00020\u00192\b\u0010\u0010\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u001b\u001a\u00020\bH\u0002¢\u0006\u0004\b\u001c\u0010\u001dJ\u001f\u0010\u001e\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u0001H\u0016¢\u0006\u0004\b\u001e\u0010\u000fJ'\u0010!\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u00012\u0006\u0010 \u001a\u00020\u001fH\u0016¢\u0006\u0004\b!\u0010\"J'\u0010#\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u00012\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b#\u0010$J'\u0010%\u001a\u00020\r2\u0006\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00028\u00012\u0006\u0010\u0014\u001a\u00020\u0013H\u0016¢\u0006\u0004\b%\u0010&R\u001a\u0010\u001a\u001a\u00020\u00198\u0004X\u0084\u0004¢\u0006\f\n\u0004\b\u001a\u0010'\u001a\u0004\b(\u0010)R\u001a\u0010+\u001a\u00020*8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b-\u0010.R(\u00101\u001a\u0010\u0012\u0004\u0012\u000200\u0012\u0004\u0012\u00020\u0011\u0018\u00010/8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001c\u00106\u001a\u0004\u0018\u0001058\u0004X\u0084\u0004¢\u0006\f\n\u0004\b6\u00107\u001a\u0004\b8\u00109R\u001a\u0010;\u001a\u00020:8\u0004X\u0084\u0004¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>R\u001b\u0010D\u001a\u00020?8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR\"\u0010E\u001a\u00020\b8\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bE\u0010G\"\u0004\bH\u0010IR\"\u0010J\u001a\u00020\b8\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\bJ\u0010F\u001a\u0004\bK\u0010G\"\u0004\bL\u0010IR$\u0010N\u001a\u0004\u0018\u00010M8\u0014@\u0014X\u0094\u000e¢\u0006\u0012\n\u0004\bN\u0010O\u001a\u0004\bP\u0010Q\"\u0004\bR\u0010S\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006V"}, d2 = {"Lcom/amazon/music/views/library/binders/PageHeaderBinder;", "Lcom/amazon/music/views/library/views/PageHeaderView;", "V", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "M", "Lcom/amazon/music/views/library/binders/BaseStateChangeBinder;", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "", "isAvailable", "(Lcom/amazon/music/views/library/metadata/ContentMetadata;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "view", "model", "", "bindEnabledState", "(Lcom/amazon/music/views/library/views/PageHeaderView;Lcom/amazon/music/views/library/models/PageHeaderModel;)V", "metadata", "Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;", "onClickListener", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;", "actionIconModel", "addButton", "(Lcom/amazon/music/views/library/views/PageHeaderView;Lcom/amazon/music/views/library/metadata/ContentMetadata;Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;)V", "setBadgingIcons", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Landroid/content/Context;", "context", "isLargeIcon", "runOnClick", "(Lcom/amazon/music/views/library/binders/ActionIconOnClickListener;Landroid/content/Context;Lcom/amazon/music/views/library/metadata/ContentMetadata;Z)Lkotlin/Unit;", "bind", "", "payload", "handleStateChange", "(Lcom/amazon/music/views/library/views/PageHeaderView;Lcom/amazon/music/views/library/models/PageHeaderModel;Ljava/lang/Object;)V", "setActionIconsState", "(Lcom/amazon/music/views/library/views/PageHeaderView;Lcom/amazon/music/views/library/models/PageHeaderModel;Z)V", "setAvailableActionIconsState", "(Lcom/amazon/music/views/library/views/PageHeaderView;Lcom/amazon/music/views/library/models/PageHeaderModel;Lcom/amazon/music/views/library/models/PageHeaderActionIconModel;)V", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "", "Lcom/amazon/music/views/library/models/PageHeaderActionIconModel$ActionType;", "onClickListenerMap", "Ljava/util/Map;", "getOnClickListenerMap", "()Ljava/util/Map;", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "contextMenuListener", "Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "getContextMenuListener", "()Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "contentEnabilityProvider", "Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "getContentEnabilityProvider", "()Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;", "Lcom/amazon/music/views/library/binders/DownloadHeaderActionBinder;", "downloadHeaderActionBinder$delegate", "Lkotlin/Lazy;", "getDownloadHeaderActionBinder", "()Lcom/amazon/music/views/library/binders/DownloadHeaderActionBinder;", "downloadHeaderActionBinder", "isSonicRushEnabled", "Z", "()Z", "setSonicRushEnabled", "(Z)V", "shouldShowDownloadTextButton", "getShouldShowDownloadTextButton", "setShouldShowDownloadTextButton", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "setPageType", "(Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;)V", "<init>", "(Landroid/content/Context;Lcom/amazon/music/views/library/styles/StyleSheet;Ljava/util/Map;Lcom/amazon/music/views/library/providers/MetadataContextMenuProvider;Lcom/amazon/music/views/library/providers/ContentEnabilityProvider;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public abstract class PageHeaderBinder<V extends PageHeaderView, M extends PageHeaderModel> implements BaseStateChangeBinder<V, M> {
    private final ContentEnabilityProvider contentEnabilityProvider;
    private final Context context;
    private final MetadataContextMenuProvider contextMenuListener;

    /* JADX INFO: renamed from: downloadHeaderActionBinder$delegate, reason: from kotlin metadata */
    private final Lazy downloadHeaderActionBinder;
    private boolean isSonicRushEnabled;
    private final Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> onClickListenerMap;
    private PageType pageType;
    private boolean shouldShowDownloadTextButton;
    private final StyleSheet styleSheet;

    /* JADX INFO: compiled from: PageHeaderBinder.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PageHeaderActionIconModel.ActionType.values().length];
            iArr[PageHeaderActionIconModel.ActionType.OVERFLOW.ordinal()] = 1;
            iArr[PageHeaderActionIconModel.ActionType.FOLLOW.ordinal()] = 2;
            iArr[PageHeaderActionIconModel.ActionType.FAVORITE.ordinal()] = 3;
            iArr[PageHeaderActionIconModel.ActionType.DOWNLOAD.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public PageHeaderBinder(Context context, StyleSheet styleSheet, Map<PageHeaderActionIconModel.ActionType, ? extends ActionIconOnClickListener> map, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(contentEnabilityProvider, "contentEnabilityProvider");
        this.context = context;
        this.styleSheet = styleSheet;
        this.onClickListenerMap = map;
        this.contextMenuListener = metadataContextMenuProvider;
        this.contentEnabilityProvider = contentEnabilityProvider;
        this.downloadHeaderActionBinder = LazyKt__LazyJVMKt.lazy(new Function0<DownloadHeaderActionBinder>(this) { // from class: com.amazon.music.views.library.binders.PageHeaderBinder$downloadHeaderActionBinder$2
            final /* synthetic */ PageHeaderBinder<V, M> this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
                this.this$0 = this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final DownloadHeaderActionBinder invoke() {
                return new DownloadHeaderActionBinder(this.this$0.getStyleSheet());
            }
        });
    }

    public /* synthetic */ PageHeaderBinder(Context context, StyleSheet styleSheet, Map map, MetadataContextMenuProvider metadataContextMenuProvider, ContentEnabilityProvider contentEnabilityProvider, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, styleSheet, (i & 4) != 0 ? null : map, (i & 8) != 0 ? null : metadataContextMenuProvider, contentEnabilityProvider);
    }

    protected final Context getContext() {
        return this.context;
    }

    protected final StyleSheet getStyleSheet() {
        return this.styleSheet;
    }

    protected final MetadataContextMenuProvider getContextMenuListener() {
        return this.contextMenuListener;
    }

    protected final ContentEnabilityProvider getContentEnabilityProvider() {
        return this.contentEnabilityProvider;
    }

    private final DownloadHeaderActionBinder getDownloadHeaderActionBinder() {
        return (DownloadHeaderActionBinder) this.downloadHeaderActionBinder.getValue();
    }

    /* JADX INFO: renamed from: isSonicRushEnabled, reason: from getter */
    protected boolean getIsSonicRushEnabled() {
        return this.isSonicRushEnabled;
    }

    protected void setSonicRushEnabled(boolean z) {
        this.isSonicRushEnabled = z;
    }

    protected void setShouldShowDownloadTextButton(boolean z) {
        this.shouldShowDownloadTextButton = z;
    }

    protected void setPageType(PageType pageType) {
        this.pageType = pageType;
    }

    public void bind(V view, M model) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        view.clear();
        view.setLabelText(model.getLabelText());
        view.setHeadlineText(model.getHeadlineTitle(), model.getOverrideTitleCaps());
        view.setPrimaryText(model.getPrimaryTitle());
        view.setSecondaryText(model.getSecondaryTitle());
        view.setTertiaryText(model.getTertiaryTitle());
        view.setText4(model.getText4());
        view.setHeadlineTitleId(model.getHeadlineTitleId());
        view.setPrimaryTextViewId(model.getPrimaryTitleViewId());
        view.setSecondaryTextViewId(model.getSecondaryTextViewId());
        if (model.getLabelBadgeText() != null) {
            view.setLabelBadgeText(model.getLabelBadgeText(), model.getFontStyle(), model.getBadgeBackground());
        }
        setBadgingIcons(view, model);
        bindEnabledState(view, model);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.PageHeaderBinder$isAvailable$2, reason: invalid class name */
    /* JADX INFO: compiled from: PageHeaderBinder.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/music/views/library/views/PageHeaderView;", "V", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "M", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.binders.PageHeaderBinder$isAvailable$2", f = "PageHeaderBinder.kt", i = {}, l = {59}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Boolean>, Object> {
        final /* synthetic */ ContentMetadata $contentMetadata;
        int label;
        final /* synthetic */ PageHeaderBinder<V, M> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(PageHeaderBinder<V, M> pageHeaderBinder, ContentMetadata contentMetadata, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.this$0 = pageHeaderBinder;
            this.$contentMetadata = contentMetadata;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass2(this.this$0, this.$contentMetadata, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Boolean> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ContentEnabilityProvider contentEnabilityProvider = this.this$0.getContentEnabilityProvider();
                ContentMetadata contentMetadata = this.$contentMetadata;
                this.label = 1;
                obj = contentEnabilityProvider.isContentAvailable(contentMetadata, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return obj;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Object isAvailable(ContentMetadata contentMetadata, Continuation<? super Boolean> continuation) {
        return BuildersKt.withContext(Dispatchers.getIO(), new AnonymousClass2(this, contentMetadata, null), continuation);
    }

    /* JADX INFO: renamed from: com.amazon.music.views.library.binders.PageHeaderBinder$bindEnabledState$1, reason: invalid class name */
    /* JADX INFO: compiled from: PageHeaderBinder.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0006\u001a\u00020\u0005\"\b\b\u0000\u0010\u0001*\u00020\u0000\"\b\b\u0001\u0010\u0003*\u00020\u0002*\u00020\u0004H\u008a@"}, d2 = {"Lcom/amazon/music/views/library/views/PageHeaderView;", "V", "Lcom/amazon/music/views/library/models/PageHeaderModel;", "M", "Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.views.library.binders.PageHeaderBinder$bindEnabledState$1", f = "PageHeaderBinder.kt", i = {}, l = {65}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ M $model;
        final /* synthetic */ V $view;
        int label;
        final /* synthetic */ PageHeaderBinder<V, M> this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(M m, PageHeaderBinder<V, M> pageHeaderBinder, V v, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$model = m;
            this.this$0 = pageHeaderBinder;
            this.$view = v;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(this.$model, this.this$0, this.$view, continuation);
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
            boolean zBooleanValue = true;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ContentMetadata metadata = this.$model.getMetadata();
                if (metadata != null) {
                    PageHeaderBinder<V, M> pageHeaderBinder = this.this$0;
                    this.label = 1;
                    obj = pageHeaderBinder.isAvailable(metadata, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                this.this$0.setActionIconsState(this.$view, this.$model, zBooleanValue);
                return Unit.INSTANCE;
            }
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            zBooleanValue = ((Boolean) obj).booleanValue();
            this.this$0.setActionIconsState(this.$view, this.$model, zBooleanValue);
            return Unit.INSTANCE;
        }
    }

    private final void bindEnabledState(V view, M model) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, Dispatchers.getMain(), null, new AnonymousClass1(model, this, view, null), 2, null);
    }

    @Override // com.amazon.music.views.library.binders.BaseStateChangeBinder
    public void handleStateChange(V view, M model, Object payload) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(payload, "payload");
        if (Intrinsics.areEqual(payload, (Object) 12)) {
            DownloadStateModel downloadStateModel = model.getDownloadStateModel();
            if (downloadStateModel == null) {
                return;
            }
            getDownloadHeaderActionBinder().bind(view.getDownloadActionButton(), downloadStateModel);
            return;
        }
        if (Intrinsics.areEqual(payload, (Object) 16)) {
            view.setHeadlineText(model.getHeadlineTitle(), model.getOverrideTitleCaps());
            view.setText4(model.getText4());
        } else if (Intrinsics.areEqual(payload, (Object) 2)) {
            view.updateFollowState(model.getFollowState());
            view.updateFavoriteState(model.getFollowState());
        } else if (Intrinsics.areEqual(payload, (Object) 18) || Intrinsics.areEqual(payload, (Object) 19)) {
            view.clearActionIconContainer();
            bindEnabledState(view, model);
        }
    }

    private final void addButton(V view, final ContentMetadata metadata, final ActionIconOnClickListener onClickListener, final PageHeaderActionIconModel actionIconModel) {
        view.addButton(actionIconModel.getActionType(), actionIconModel.getIsEnabled(), actionIconModel.getCanClickWhenDisabled(), actionIconModel.getIsLargeIcon(), actionIconModel.getLargeButtonText(), actionIconModel.getViewId(), new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.PageHeaderBinder$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                PageHeaderBinder.m2346addButton$lambda1(this.f$0, onClickListener, metadata, actionIconModel, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: addButton$lambda-1, reason: not valid java name */
    public static final void m2346addButton$lambda1(PageHeaderBinder this$0, ActionIconOnClickListener actionIconOnClickListener, ContentMetadata contentMetadata, PageHeaderActionIconModel actionIconModel, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(actionIconModel, "$actionIconModel");
        this$0.runOnClick(actionIconOnClickListener, this$0.context, contentMetadata, actionIconModel.getIsLargeIcon());
    }

    public void setActionIconsState(final V view, M model, final boolean isAvailable) {
        V view2 = view;
        M model2 = model;
        Intrinsics.checkNotNullParameter(view2, "view");
        Intrinsics.checkNotNullParameter(model2, "model");
        for (PageHeaderActionIconModel pageHeaderActionIconModel : model.getActionIcons()) {
            int i = WhenMappings.$EnumSwitchMapping$0[pageHeaderActionIconModel.getActionType().ordinal()];
            if (i == 1) {
                final MetadataContextMenuProvider metadataContextMenuProvider = this.contextMenuListener;
                if (metadataContextMenuProvider != null) {
                    final ContentMetadata metadata = model.getMetadata();
                    if (metadata != null) {
                        if (!metadataContextMenuProvider.shouldDisplayContextMenu(metadata)) {
                            return;
                        }
                        PageHeaderView.addButton$default(view, pageHeaderActionIconModel.getActionType(), true, false, pageHeaderActionIconModel.getIsLargeIcon(), null, pageHeaderActionIconModel.getViewId(), new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.PageHeaderBinder$$ExternalSyntheticLambda0
                            @Override // android.view.View.OnClickListener
                            public final void onClick(View view3) {
                                PageHeaderBinder.m2347setActionIconsState$lambda4$lambda3$lambda2(isAvailable, metadataContextMenuProvider, view, metadata, this, view3);
                            }
                        }, 20, null);
                        metadataContextMenuProvider.updateStateProvider(metadata);
                    }
                    view2 = view;
                    model2 = model;
                } else {
                    continue;
                }
            } else if (i == 2) {
                view2.updateFollowState(model.getFollowState());
                if (isAvailable) {
                    setAvailableActionIconsState(view2, model2, pageHeaderActionIconModel);
                }
            } else if (i == 3) {
                view2.updateFavoriteState(model.getFollowState());
                if (isAvailable) {
                    setAvailableActionIconsState(view2, model2, pageHeaderActionIconModel);
                }
            } else if (i == 4) {
                DownloadStateModel downloadStateModel = model.getDownloadStateModel();
                if (downloadStateModel != null) {
                    if (isAvailable) {
                        setAvailableActionIconsState(view2, model2, pageHeaderActionIconModel);
                    }
                    getDownloadHeaderActionBinder().bind(view.getDownloadActionButton(), downloadStateModel);
                }
            } else if (isAvailable) {
                setAvailableActionIconsState(view2, model2, pageHeaderActionIconModel);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setActionIconsState$lambda-4$lambda-3$lambda-2, reason: not valid java name */
    public static final void m2347setActionIconsState$lambda4$lambda3$lambda2(boolean z, MetadataContextMenuProvider menuProvider, PageHeaderView view, ContentMetadata metadata, PageHeaderBinder this$0, View view2) {
        Intrinsics.checkNotNullParameter(menuProvider, "$menuProvider");
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(metadata, "$metadata");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            menuProvider.openContextMenu(view, metadata, InteractionType.TAP);
        } else {
            this$0.contentEnabilityProvider.handleContentAccessUnavailable(metadata);
        }
    }

    public void setAvailableActionIconsState(V view, final M model, PageHeaderActionIconModel actionIconModel) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(model, "model");
        Intrinsics.checkNotNullParameter(actionIconModel, "actionIconModel");
        Map<PageHeaderActionIconModel.ActionType, ActionIconOnClickListener> map = this.onClickListenerMap;
        final ActionIconOnClickListener actionIconOnClickListener = map == null ? null : map.get(actionIconModel.getActionType());
        if (WhenMappings.$EnumSwitchMapping$0[actionIconModel.getActionType().ordinal()] == 4) {
            if (model.getDownloadStateModel() == null) {
                return;
            }
            view.getDownloadActionButton().setTextButton(actionIconModel.getIsLargeIcon());
            view.getDownloadActionButton().setSonicRushEnabled(getIsSonicRushEnabled());
            PageHeaderView.addButton$default(view, actionIconModel.getActionType(), actionIconModel.getIsEnabled(), false, actionIconModel.getIsLargeIcon(), null, actionIconModel.getViewId(), new View.OnClickListener() { // from class: com.amazon.music.views.library.binders.PageHeaderBinder$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    PageHeaderBinder.m2348setAvailableActionIconsState$lambda8$lambda7(model, actionIconOnClickListener, this, view2);
                }
            }, 20, null);
            return;
        }
        addButton(view, model.getMetadata(), actionIconOnClickListener, actionIconModel);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setAvailableActionIconsState$lambda-8$lambda-7, reason: not valid java name */
    public static final void m2348setAvailableActionIconsState$lambda8$lambda7(PageHeaderModel model, ActionIconOnClickListener actionIconOnClickListener, PageHeaderBinder this$0, View view) {
        DownloadStateModel.DownloadState downloadState;
        Intrinsics.checkNotNullParameter(model, "$model");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DownloadStateModel downloadStateModel = model.getDownloadStateModel();
        if (downloadStateModel == null || (downloadState = downloadStateModel.getDownloadState()) == null) {
            return;
        }
        DownloadIconOnClickListener downloadIconOnClickListener = actionIconOnClickListener instanceof DownloadIconOnClickListener ? (DownloadIconOnClickListener) actionIconOnClickListener : null;
        if (downloadIconOnClickListener == null) {
            return;
        }
        downloadIconOnClickListener.onClick(this$0.getContext(), model.getMetadata(), downloadState);
    }

    private final void setBadgingIcons(V view, M model) {
        if (model.getMetadata() instanceof AlbumMetadata) {
            ContentMetadata metadata = model.getMetadata();
            if (metadata == null) {
                throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.metadata.AlbumMetadata");
            }
            List<String> contentEncodings = ((AlbumMetadata) metadata).getContentEncodings();
            if (contentEncodings == null) {
                return;
            }
            view.addBadgeIcons(contentEncodings);
        }
    }

    private final Unit runOnClick(ActionIconOnClickListener listener, Context context, ContentMetadata metadata, boolean isLargeIcon) {
        if (listener instanceof LargeIconOnClickListener) {
            ((LargeIconOnClickListener) listener).onClick(context, metadata, isLargeIcon);
            return Unit.INSTANCE;
        }
        if (listener == null) {
            return null;
        }
        listener.onClick(context, metadata);
        return Unit.INSTANCE;
    }
}
