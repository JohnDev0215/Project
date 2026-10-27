package com.amazon.music.share.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.image.ImageUrlRepository;
import com.amazon.music.share.R$dimen;
import com.amazon.music.share.R$drawable;
import com.amazon.music.share.R$id;
import com.amazon.music.share.R$layout;
import com.amazon.music.share.R$string;
import com.amazon.music.share.ShareHelper;
import com.amazon.music.share.menu.DefaultSharingViewModel;
import com.amazon.music.share.menu.providers.EnvironmentProvider;
import com.amazon.music.share.menu.providers.SocialMetadataProvider;
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import com.amazon.music.share.menu.ui.LyricsShareView;
import com.amazon.music.share.menu.viewmodel.LyricsShareViewModel;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import dagger.android.support.AndroidSupportInjection;
import io.reactivex.rxjava3.subjects.PublishSubject;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt__StringsJVMKt;
import kotlin.text.StringsKt__StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import kotlinx.coroutines.TimeoutKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SharingFragmentV2.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000Ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 {2\u00020\u0001:\u0001{B\u0007¢\u0006\u0004\by\u0010zJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0015\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0002H\u0002J\b\u0010\b\u001a\u00020\u0002H\u0002J\b\u0010\t\u001a\u00020\u0002H\u0002J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0002J\u0010\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\u0002H\u0002J\b\u0010\u0011\u001a\u00020\u0002H\u0002J\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\nH\u0002J\u001a\u0010\u0015\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\n2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0002J*\u0010\u001c\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0002J\b\u0010\u001e\u001a\u00020\u0002H\u0002J\b\u0010 \u001a\u00020\u001fH\u0002J\u0012\u0010#\u001a\u00020\u00022\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016J$\u0010(\u001a\u00020\u000e2\u0006\u0010%\u001a\u00020$2\b\u0010'\u001a\u0004\u0018\u00010&2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016J\u001a\u0010*\u001a\u00020\u00022\u0006\u0010)\u001a\u00020\u000e2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0016J\u0010\u0010-\u001a\u00020\u00022\u0006\u0010,\u001a\u00020+H\u0016J\u0016\u0010/\u001a\u00020\u00022\u000e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010.R\"\u00101\u001a\u0002008\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R\"\u00108\u001a\u0002078\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b8\u00109\u001a\u0004\b:\u0010;\"\u0004\b<\u0010=R\"\u0010?\u001a\u00020>8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b?\u0010@\u001a\u0004\bA\u0010B\"\u0004\bC\u0010DR\"\u0010F\u001a\u00020E8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bF\u0010G\u001a\u0004\bH\u0010I\"\u0004\bJ\u0010KR\"\u0010M\u001a\u00020L8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\u0016\u0010S\u001a\u00020\u000e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bS\u0010TR\u0018\u0010U\u001a\u0004\u0018\u00010\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bU\u0010VR\u001e\u0010/\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u0010WR\u0014\u0010Y\u001a\u00020X8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bY\u0010ZR\u0014\u0010^\u001a\u00020[8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\\\u0010]R\u0014\u0010b\u001a\u00020_8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b`\u0010aR\u0014\u0010f\u001a\u00020c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bd\u0010eR\u0014\u0010h\u001a\u00020_8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bg\u0010aR\u0014\u0010l\u001a\u00020i8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0014\u0010p\u001a\u00020m8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0014\u0010t\u001a\u00020q8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\br\u0010sR\u0014\u0010x\u001a\u00020u8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bv\u0010w\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006|"}, d2 = {"Lcom/amazon/music/share/menu/SharingFragmentV2;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "", "fetchImageUrlAndBindSharingViews", "", "fetchImageUrl", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "bindSharingViews", "addSpace", "bindLyricsShareView", "Lcom/amazon/music/share/menu/ShareableContent;", "it", "bindDefaultShareView", "content", "Landroid/view/View;", "getDefaultShareView", "initStyles", "bindHeader", "bindThumbnail", "bindTitleAndSubtitle", "imageUrl", "loadThumbnailAndRebind", "", "aspectRatio", "", "isProfileOrArtist", "Lcom/squareup/picasso/Callback;", "callback", "loadThumbnailImage", "showCollapsedSheet", "expandSheet", "", "getPeekHeight", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "onCreateView", "view", "onViewCreated", "Landroid/content/DialogInterface;", "dialog", "onDismiss", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "openLyricsCardSelectionFragmentSubject", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "styleSheetProvider", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "getStyleSheetProvider", "()Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "setStyleSheetProvider", "(Lcom/amazon/music/share/menu/providers/StyleSheetProvider;)V", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "socialMetadataProvider", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "getSocialMetadataProvider", "()Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "setSocialMetadataProvider", "(Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;)V", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "getEnvironmentProvider", "()Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "setEnvironmentProvider", "(Lcom/amazon/music/share/menu/providers/EnvironmentProvider;)V", "Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "imageUrlRepository", "Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "getImageUrlRepository", "()Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "setImageUrlRepository", "(Lcom/amazon/music/platform/data/image/ImageUrlRepository;)V", "Lcom/amazon/music/share/ShareHelper;", "shareHelper", "Lcom/amazon/music/share/ShareHelper;", "getShareHelper", "()Lcom/amazon/music/share/ShareHelper;", "setShareHelper", "(Lcom/amazon/music/share/ShareHelper;)V", "rootView", "Landroid/view/View;", "shareableContent", "Lcom/amazon/music/share/menu/ShareableContent;", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "Landroid/widget/ImageView;", "getThumbnail", "()Landroid/widget/ImageView;", "thumbnail", "Landroid/widget/TextView;", "getTitle", "()Landroid/widget/TextView;", "title", "Lcom/amazon/ui/foundations/views/BaseButton;", "getSongTag", "()Lcom/amazon/ui/foundations/views/BaseButton;", "songTag", "getSubtitle", MediaTrack.ROLE_SUBTITLE, "Landroidx/core/widget/NestedScrollView;", "getScrollView", "()Landroidx/core/widget/NestedScrollView;", "scrollView", "Landroid/widget/LinearLayout;", "getShareItemsContainer", "()Landroid/widget/LinearLayout;", "shareItemsContainer", "Landroidx/cardview/widget/CardView;", "getBarView", "()Landroidx/cardview/widget/CardView;", "barView", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "<init>", "()V", "Companion", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class SharingFragmentV2 extends BottomSheetDialogFragment {
    public EnvironmentProvider environmentProvider;
    public ImageUrlRepository imageUrlRepository;
    private final Logger logger;
    private PublishSubject<Integer> openLyricsCardSelectionFragmentSubject;
    private View rootView;
    public ShareHelper shareHelper;
    private ShareableContent shareableContent;
    public SocialMetadataProvider socialMetadataProvider;
    public StyleSheetProvider styleSheetProvider;

    public SharingFragmentV2() {
        Logger logger = LoggerFactory.getLogger(SharingFragmentV2.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
    }

    public final StyleSheetProvider getStyleSheetProvider() {
        StyleSheetProvider styleSheetProvider = this.styleSheetProvider;
        if (styleSheetProvider != null) {
            return styleSheetProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("styleSheetProvider");
        return null;
    }

    public final SocialMetadataProvider getSocialMetadataProvider() {
        SocialMetadataProvider socialMetadataProvider = this.socialMetadataProvider;
        if (socialMetadataProvider != null) {
            return socialMetadataProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("socialMetadataProvider");
        return null;
    }

    public final EnvironmentProvider getEnvironmentProvider() {
        EnvironmentProvider environmentProvider = this.environmentProvider;
        if (environmentProvider != null) {
            return environmentProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("environmentProvider");
        return null;
    }

    public final ImageUrlRepository getImageUrlRepository() {
        ImageUrlRepository imageUrlRepository = this.imageUrlRepository;
        if (imageUrlRepository != null) {
            return imageUrlRepository;
        }
        Intrinsics.throwUninitializedPropertyAccessException("imageUrlRepository");
        return null;
    }

    public final ShareHelper getShareHelper() {
        ShareHelper shareHelper = this.shareHelper;
        if (shareHelper != null) {
            return shareHelper;
        }
        Intrinsics.throwUninitializedPropertyAccessException("shareHelper");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getThumbnail() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R$id.thumbnail);
        if (viewFindViewById != null) {
            return (ImageView) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.ImageView");
    }

    private final TextView getTitle() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R$id.title);
        if (viewFindViewById != null) {
            return (TextView) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
    }

    private final BaseButton getSongTag() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R$id.song_tag);
        if (viewFindViewById != null) {
            return (BaseButton) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.ui.foundations.views.BaseButton");
    }

    private final TextView getSubtitle() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R$id.subtitle);
        if (viewFindViewById != null) {
            return (TextView) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.TextView");
    }

    private final NestedScrollView getScrollView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R$id.nested_scroll_view);
        if (viewFindViewById != null) {
            return (NestedScrollView) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type androidx.core.widget.NestedScrollView");
    }

    private final LinearLayout getShareItemsContainer() {
        View viewFindViewById = getScrollView().findViewById(R$id.share_items_container);
        if (viewFindViewById != null) {
            return (LinearLayout) viewFindViewById;
        }
        throw new NullPointerException("null cannot be cast to non-null type android.widget.LinearLayout");
    }

    private final CardView getBarView() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        View viewFindViewById = view.findViewById(R$id.bar_view);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.id.bar_view)");
        return (CardView) viewFindViewById;
    }

    private final StyleSheet getStyleSheet() {
        return getStyleSheetProvider().getStyleSheet();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        Bundle arguments = getArguments();
        this.shareableContent = arguments == null ? null : (ShareableContent) arguments.getParcelable("com.amazon.music.share.menu.ShareableContent");
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R$layout.fragment_sharing_v2, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…ing_v2, container, false)");
        this.rootView = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            viewInflate = null;
        }
        viewInflate.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.music.share.menu.SharingFragmentV2.onCreateView.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                View view = SharingFragmentV2.this.rootView;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                    view = null;
                }
                if (view.getHeight() == 0) {
                    return;
                }
                View view2 = SharingFragmentV2.this.rootView;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                    view2 = null;
                }
                view2.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                LifecycleOwnerKt.getLifecycleScope(SharingFragmentV2.this).launchWhenStarted(new SharingFragmentV2$onCreateView$1$onGlobalLayout$1(SharingFragmentV2.this, null));
            }
        });
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        view.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.share.menu.SharingFragmentV2$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                SharingFragmentV2.m2187onCreateView$lambda0(this.f$0, view2);
            }
        });
        View view2 = this.rootView;
        if (view2 != null) {
            return view2;
        }
        Intrinsics.throwUninitializedPropertyAccessException("rootView");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-0, reason: not valid java name */
    public static final void m2187onCreateView$lambda0(SharingFragmentV2 this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.expandSheet();
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        initStyles();
        bindHeader();
        ShareableContent shareableContent = this.shareableContent;
        View view2 = null;
        if ((shareableContent == null ? null : shareableContent.getImage()) == null) {
            fetchImageUrlAndBindSharingViews();
            return;
        }
        bindSharingViews();
        View view3 = this.rootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        } else {
            view2 = view3;
        }
        view2.setVisibility(0);
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrlAndBindSharingViews$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SharingFragmentV2.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrlAndBindSharingViews$1", f = "SharingFragmentV2.kt", i = {}, l = {135, 142, 142, 142}, m = "invokeSuspend", n = {}, s = {})
    static final class C04471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        int label;

        C04471(Continuation<? super C04471> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SharingFragmentV2.this.new C04471(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r2v0 */
        /* JADX WARN: Type inference failed for: r2v2 */
        /* JADX WARN: Type inference failed for: r2v4, types: [com.amazon.music.share.menu.SharingFragmentV2] */
        /* JADX WARN: Type inference failed for: r2v5 */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) throws Throwable {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            int i2 = 4;
            i2 = 4;
            try {
                try {
                } catch (Exception e) {
                    SharingFragmentV2.this.logger.error(Intrinsics.stringPlus("Error fetching imageUrl - ", e));
                    MainCoroutineDispatcher main2 = Dispatchers.getMain();
                    AnonymousClass2 anonymousClass2 = new AnonymousClass2(SharingFragmentV2.this, null);
                    this.label = 3;
                    if (BuildersKt.withContext(main2, anonymousClass2, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    C01171 c01171 = new C01171(SharingFragmentV2.this, null);
                    this.label = 1;
                    if (BuildersKt.withContext(io2, c01171, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        if (i == 2 || i == 3) {
                            ResultKt.throwOnFailure(obj);
                            return Unit.INSTANCE;
                        }
                        if (i == 4) {
                            Throwable th = (Throwable) this.L$0;
                            ResultKt.throwOnFailure(obj);
                            throw th;
                        }
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                MainCoroutineDispatcher main3 = Dispatchers.getMain();
                i2 = SharingFragmentV2.this;
                AnonymousClass2 anonymousClass22 = new AnonymousClass2(i2, null);
                this.label = 2;
                if (BuildersKt.withContext(main3, anonymousClass22, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                return Unit.INSTANCE;
            } catch (Throwable th2) {
                MainCoroutineDispatcher main4 = Dispatchers.getMain();
                AnonymousClass2 anonymousClass23 = new AnonymousClass2(SharingFragmentV2.this, null);
                this.L$0 = th2;
                this.label = i2;
                if (BuildersKt.withContext(main4, anonymousClass23, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                throw th2;
            }
        }

        /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrlAndBindSharingViews$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SharingFragmentV2.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrlAndBindSharingViews$1$1", f = "SharingFragmentV2.kt", i = {}, l = {136}, m = "invokeSuspend", n = {}, s = {})
        static final class C01171 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SharingFragmentV2 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01171(SharingFragmentV2 sharingFragmentV2, Continuation<? super C01171> continuation) {
                super(2, continuation);
                this.this$0 = sharingFragmentV2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01171(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01171) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    SharingFragmentV2 sharingFragmentV2 = this.this$0;
                    this.label = 1;
                    obj = sharingFragmentV2.fetchImageUrl(this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                String str = (String) obj;
                ShareableContent shareableContent = this.this$0.shareableContent;
                if (shareableContent != null) {
                    shareableContent.setImage(str);
                }
                return Unit.INSTANCE;
            }
        }

        /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrlAndBindSharingViews$1$2, reason: invalid class name */
        /* JADX INFO: compiled from: SharingFragmentV2.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrlAndBindSharingViews$1$2", f = "SharingFragmentV2.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
        static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            int label;
            final /* synthetic */ SharingFragmentV2 this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            AnonymousClass2(SharingFragmentV2 sharingFragmentV2, Continuation<? super AnonymousClass2> continuation) {
                super(2, continuation);
                this.this$0 = sharingFragmentV2;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new AnonymousClass2(this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                if (this.label != 0) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
                this.this$0.bindSharingViews();
                View view = this.this$0.rootView;
                if (view == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("rootView");
                    view = null;
                }
                view.setVisibility(0);
                return Unit.INSTANCE;
            }
        }
    }

    private final void fetchImageUrlAndBindSharingViews() {
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C04471(null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrl$2, reason: invalid class name */
    /* JADX INFO: compiled from: SharingFragmentV2.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0002\u001a\u0004\u0018\u00010\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragmentV2$fetchImageUrl$2", f = "SharingFragmentV2.kt", i = {}, l = {156}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super String>, Object> {
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SharingFragmentV2.this.new AnonymousClass2(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super String> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ImageUrlRepository imageUrlRepository = SharingFragmentV2.this.getImageUrlRepository();
                ShareableContent shareableContent = SharingFragmentV2.this.shareableContent;
                String entityId = shareableContent == null ? null : shareableContent.getEntityId();
                SharingUtil sharingUtil = SharingUtil.INSTANCE;
                ShareableContent shareableContent2 = SharingFragmentV2.this.shareableContent;
                EntityItemType platformEntityItemType = sharingUtil.toPlatformEntityItemType(shareableContent2 != null ? shareableContent2.getEntityType() : null);
                this.label = 1;
                obj = imageUrlRepository.getImageUrl(entityId, platformEntityItemType, this);
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
    public final Object fetchImageUrl(Continuation<? super String> continuation) {
        return TimeoutKt.withTimeoutOrNull(10000L, new AnonymousClass2(null), continuation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bindSharingViews() {
        ShareableContent shareableContent = this.shareableContent;
        if (shareableContent == null) {
            return;
        }
        bindDefaultShareView(shareableContent);
        ShareableContent shareableContent2 = this.shareableContent;
        if ((shareableContent2 == null ? null : shareableContent2.getLyrics()) != null) {
            addSpace();
            bindLyricsShareView();
        }
    }

    private final void addSpace() {
        Space space = new Space(getContext());
        space.setMinimumHeight((int) getResources().getDimension(R$dimen.share_views_margin));
        getShareItemsContainer().addView(space);
    }

    private final void bindLyricsShareView() {
        final LyricsShareViewModel lyricsShareViewModel = new LyricsShareViewModel(this.shareableContent, this.openLyricsCardSelectionFragmentSubject);
        lyricsShareViewModel.setCloseCallback(new LyricsShareViewModel.CloseCallback() { // from class: com.amazon.music.share.menu.SharingFragmentV2.bindLyricsShareView.1
            @Override // com.amazon.music.share.menu.viewmodel.LyricsShareViewModel.CloseCallback
            public void close() {
                lyricsShareViewModel.removeCloseCallback();
                this.dismiss();
            }
        });
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        getShareItemsContainer().addView(new LyricsShareView(contextRequireContext, getStyleSheet(), this, lyricsShareViewModel));
    }

    private final void bindDefaultShareView(ShareableContent it) {
        getShareItemsContainer().addView(getDefaultShareView(it));
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, com.amazon.music.share.menu.DefaultSharingViewModel] */
    private final View getDefaultShareView(ShareableContent content) {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? defaultSharingViewModel = new DefaultSharingViewModel(getSocialMetadataProvider(), getEnvironmentProvider(), getImageUrlRepository());
        objectRef.element = defaultSharingViewModel;
        defaultSharingViewModel.setCloseCallback(new DefaultSharingViewModel.CloseCallback() { // from class: com.amazon.music.share.menu.SharingFragmentV2.getDefaultShareView.1
            @Override // com.amazon.music.share.menu.DefaultSharingViewModel.CloseCallback
            public void close() {
                objectRef.element.removeCloseCallback();
                this.dismiss();
            }
        });
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        return new DefaultSharingView(contextRequireContext, this, content, getStyleSheet(), (DefaultSharingViewModel) objectRef.element);
    }

    private final void initStyles() {
        View view = this.rootView;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
            view = null;
        }
        view.setVisibility(8);
        Integer color = getStyleSheet().getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_4);
        if (color != null) {
            getBarView().setCardBackgroundColor(color.intValue());
        }
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
        if (fontStyle != null) {
            UIExtensionsKt.applyFontStyle(getTitle(), fontStyle);
        }
        FontStyle fontStyle2 = getStyleSheet().getFontStyle(FontStyleKey.SECONDARY);
        if (fontStyle2 != null) {
            UIExtensionsKt.applyFontStyle(getSubtitle(), fontStyle2);
        }
        getThumbnail().setImageResource(R$drawable.placeholder);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        String privateEntityId;
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        ShareableContent shareableContent = this.shareableContent;
        if (shareableContent == null) {
            return;
        }
        if (shareableContent.getEntityType() == EntityType.USER_PLAYLIST && (privateEntityId = shareableContent.getPrivateEntityId()) != null) {
            ShareHelper shareHelper = getShareHelper();
            Context contextRequireContext = requireContext();
            Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
            shareHelper.syncUserPlaylistData(contextRequireContext, privateEntityId);
        }
        this.openLyricsCardSelectionFragmentSubject = null;
    }

    private final void bindHeader() {
        ShareableContent shareableContent = this.shareableContent;
        if (shareableContent == null) {
            return;
        }
        if (shareableContent.getRef() == null) {
            shareableContent.setRef(RefMarkerUtils.INSTANCE.generateRefMarker());
        }
        bindTitleAndSubtitle(shareableContent);
        bindThumbnail(shareableContent);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [T, java.lang.String] */
    private final void bindThumbnail(ShareableContent content) {
        Unit unit;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        ?? image = content.getImage();
        objectRef.element = image;
        if (image == 0) {
            unit = null;
        } else {
            loadThumbnailAndRebind(content, image);
            unit = Unit.INSTANCE;
        }
        if (unit == null) {
            BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), Dispatchers.getIO(), null, new SharingFragmentV2$bindThumbnail$2$1(objectRef, this, content, null), 2, null);
        }
    }

    private final void bindTitleAndSubtitle(ShareableContent content) {
        String title;
        String title2 = content.getTitle();
        ButtonSize buttonSizeCopy = null;
        if (title2 != null && StringsKt__StringsKt.contains$default((CharSequence) title2, (CharSequence) "[Explicit]", false, 2, (Object) null)) {
            String title3 = content.getTitle();
            title = title3 == null ? null : StringsKt__StringsJVMKt.replace$default(title3, "[Explicit]", "", false, 4, (Object) null);
        } else {
            title = content.getTitle();
            getSongTag().setVisibility(8);
        }
        ButtonSize buttonSize = getStyleSheet().getButtonSize(ButtonSizeKey.SMALL);
        if (buttonSize != null) {
            buttonSizeCopy = buttonSize.copy((4026 & 1) != 0 ? buttonSize.height : Integer.valueOf((int) getResources().getDimension(R$dimen.spacer_16)), (4026 & 2) != 0 ? buttonSize.width : null, (4026 & 4) != 0 ? buttonSize.paddingHorizontal : (int) getResources().getDimension(R$dimen.spacer_4), (4026 & 8) != 0 ? buttonSize.paddingVertical : null, (4026 & 16) != 0 ? buttonSize.minWidth : null, (4026 & 32) != 0 ? buttonSize.cornerRadius : null, (4026 & 64) != 0 ? buttonSize.textSize : Integer.valueOf((int) getResources().getDimension(R$dimen.text_size_12)), (4026 & 128) != 0 ? buttonSize.textTypeface : null, (4026 & 256) != 0 ? buttonSize.textAllCaps : null, (4026 & 512) != 0 ? buttonSize.iconSize : null, (4026 & 1024) != 0 ? buttonSize.iconPaddingLeft : null, (4026 & 2048) != 0 ? buttonSize.iconPaddingRight : null);
        }
        if (buttonSizeCopy == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilderWithSize = new BaseButton.StyleBuilder().withSize(buttonSizeCopy);
        String string = getResources().getString(R$string.explicit_badge);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.explicit_badge)");
        styleBuilderWithSize.withText(string).applyStyle(getSongTag());
        getTitle().setText(title);
        getSubtitle().setText(content.getSubtitle());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadThumbnailAndRebind(ShareableContent content, String imageUrl) {
        if (imageUrl != null) {
            loadThumbnailImage(imageUrl, content.getImageAspectRatio(), content.getEntityType() == EntityType.ARTIST || content.getEntityType() == EntityType.CUSTOMER_PROFILE, new Callback() { // from class: com.amazon.music.share.menu.SharingFragmentV2.loadThumbnailAndRebind.1
                @Override // com.squareup.picasso.Callback
                public void onSuccess() {
                    SharingFragmentV2.this.logger.info("Image load for share menu thumbnail successful. Loading menu with thumbnail");
                    SharingFragmentV2.this.getThumbnail().setVisibility(0);
                }

                @Override // com.squareup.picasso.Callback
                public void onError(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    SharingFragmentV2.this.logger.error("Image load for share menu thumbnail failed. Loading menu without thumbnail");
                }
            });
        }
    }

    private final void loadThumbnailImage(String imageUrl, float aspectRatio, boolean isProfileOrArtist, Callback callback) {
        Resources resources = getResources();
        int i = R$dimen.share_sheet2_header_thumbnail_size;
        int dimension = (int) resources.getDimension(i);
        ImageView thumbnail = getThumbnail();
        ViewGroup.LayoutParams layoutParams = getThumbnail().getLayoutParams();
        layoutParams.width = (int) getResources().getDimension(i);
        thumbnail.setLayoutParams(layoutParams);
        RequestCreator requestCreatorResize = Picasso.get().load(imageUrl).resize((int) (dimension * aspectRatio), dimension);
        if (isProfileOrArtist) {
            requestCreatorResize.transform(new CircleTransformation()).centerCrop().into(getThumbnail(), callback);
            return;
        }
        ImageRoundCornerTransformation imageRoundCornerTransformation = new ImageRoundCornerTransformation();
        imageRoundCornerTransformation.setCornerRadius(Float.valueOf(getStyleSheet().getCornerSize(CornerSizeKey.MEDIUM) == null ? 0.0f : r8.intValue()));
        requestCreatorResize.transform(imageRoundCornerTransformation).centerCrop().into(getThumbnail(), callback);
    }

    public final void openLyricsCardSelectionFragmentSubject(PublishSubject<Integer> openLyricsCardSelectionFragmentSubject) {
        this.openLyricsCardSelectionFragmentSubject = openLyricsCardSelectionFragmentSubject;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showCollapsedSheet() {
        Dialog dialog = getDialog();
        BottomSheetDialog bottomSheetDialog = dialog instanceof BottomSheetDialog ? (BottomSheetDialog) dialog : null;
        View viewFindViewById = bottomSheetDialog != null ? bottomSheetDialog.findViewById(R$id.design_bottom_sheet) : null;
        if (viewFindViewById == null) {
            return;
        }
        viewFindViewById.setBackgroundResource(R$drawable.sharing_fragment_v2_background);
        BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(viewFindViewById);
        bottomSheetBehaviorFrom.setPeekHeight(getPeekHeight());
        bottomSheetBehaviorFrom.setState(4);
    }

    private final void expandSheet() {
        Dialog dialog = getDialog();
        BottomSheetDialog bottomSheetDialog = dialog instanceof BottomSheetDialog ? (BottomSheetDialog) dialog : null;
        View viewFindViewById = bottomSheetDialog != null ? bottomSheetDialog.findViewById(R$id.design_bottom_sheet) : null;
        if (viewFindViewById == null) {
            return;
        }
        viewFindViewById.setBackgroundResource(R$drawable.sharing_fragment_v2_background);
        BottomSheetBehavior bottomSheetBehaviorFrom = BottomSheetBehavior.from(viewFindViewById);
        bottomSheetBehaviorFrom.setPeekHeight(getPeekHeight());
        bottomSheetBehaviorFrom.setState(3);
    }

    private final int getPeekHeight() {
        ShareableContent shareableContent = this.shareableContent;
        View view = null;
        if ((shareableContent == null ? null : shareableContent.getLyrics()) == null) {
            View view2 = this.rootView;
            if (view2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("rootView");
            } else {
                view = view2;
            }
            return view.getHeight();
        }
        int dimension = (int) getResources().getDimension(R$dimen.lyrics_share_card_width);
        float f = ResourcesCompat.getFloat(requireContext().getResources(), R$dimen.lyrics_share_card_dimen_ratio);
        int dimension2 = (int) getResources().getDimension(R$dimen.share_items_container_margin_bottom);
        int i = (int) (((double) (dimension * f)) * 0.4d);
        View view3 = this.rootView;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("rootView");
        } else {
            view = view3;
        }
        return view.getHeight() - (i + dimension2);
    }
}
