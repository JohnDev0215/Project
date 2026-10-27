package com.amazon.music.share.menu;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.LifecycleOwnerKt;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.picassoimageloader.CircleTransformation;
import com.amazon.music.picassoimageloader.ImageRoundCornerTransformation;
import com.amazon.music.platform.data.entity.CollectionType;
import com.amazon.music.platform.data.entity.EntityItemType;
import com.amazon.music.platform.data.entity.SingleType;
import com.amazon.music.platform.data.image.ImageUrlRepository;
import com.amazon.music.share.R$color;
import com.amazon.music.share.R$id;
import com.amazon.music.share.R$layout;
import com.amazon.music.share.ShareHelper;
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
import com.amazon.music.share.menu.providers.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.UIExtensionsKt;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;
import dagger.android.support.AndroidSupportInjection;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt__MutableCollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.TimeoutCancellationException;
import kotlinx.coroutines.TimeoutKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: compiled from: SharingFragment.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000È\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 i2\u00020\u0001:\u0002ijB\u0007¢\u0006\u0004\bg\u0010hJ\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\b\u0010\b\u001a\u00020\u0004H\u0002J\u0010\u0010\u000b\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\tH\u0002J*\u0010\u0014\u001a\u00020\u00042\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0004H\u0002J\u0018\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\tH\u0002J\u0012\u0010\u001b\u001a\u00020\u00042\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016J&\u0010!\u001a\u0004\u0018\u00010 2\u0006\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016J\b\u0010\"\u001a\u00020\u0004H\u0016J\u0010\u0010%\u001a\u00020\u00042\u0006\u0010$\u001a\u00020#H\u0016J\u0012\u0010)\u001a\u0004\u0018\u00010(2\b\u0010'\u001a\u0004\u0018\u00010&J\u0010\u0010,\u001a\u00020\u00042\u0006\u0010+\u001a\u00020*H\u0016R\"\u0010.\u001a\u00020-8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b.\u0010/\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\"\u00105\u001a\u0002048\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010<\u001a\u00020;8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\b<\u0010=\u001a\u0004\b>\u0010?\"\u0004\b@\u0010AR\"\u0010C\u001a\u00020B8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bC\u0010D\u001a\u0004\bE\u0010F\"\u0004\bG\u0010HR\"\u0010J\u001a\u00020I8\u0006@\u0006X\u0087.¢\u0006\u0012\n\u0004\bJ\u0010K\u001a\u0004\bL\u0010M\"\u0004\bN\u0010OR\u0016\u0010P\u001a\u00020 8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bP\u0010QR\u0016\u0010S\u001a\u00020R8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bS\u0010TR\u0016\u0010V\u001a\u00020U8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bV\u0010WR\u0016\u0010X\u001a\u00020U8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bX\u0010WR\u0016\u0010Y\u001a\u00020U8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bY\u0010WR\u0016\u0010Z\u001a\u00020\u001e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020U8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\\\u0010WR\u0014\u0010^\u001a\u00020]8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b^\u0010_R\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020\u00160`8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u0014\u0010f\u001a\u00020c8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bd\u0010e¨\u0006k"}, d2 = {"Lcom/amazon/music/share/menu/SharingFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "Landroid/content/Context;", "context", "", "appendImageDestinations", "initDestinations", "rebind", "rebindItems", "Lcom/amazon/music/share/menu/ShareableContent;", "content", "loadThumbnailAndRebind", "", "imageUrl", "", "aspectRatio", "", "isProfileOrArtist", "Lcom/squareup/picasso/Callback;", "callback", "loadThumbnailImage", "expandSheet", "Lcom/amazon/music/share/menu/destinations/ShareDestination;", "destination", "share", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onStart", "Landroid/content/DialogInterface;", "dialog", "onDismiss", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/EntityType;", PageUriUtils.ENTITY_TYPE_QUERY_PARAM, "Lcom/amazon/music/platform/data/entity/EntityItemType;", "toPlatformEntityItemType", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "styleSheetProvider", "Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "getStyleSheetProvider", "()Lcom/amazon/music/share/menu/providers/StyleSheetProvider;", "setStyleSheetProvider", "(Lcom/amazon/music/share/menu/providers/StyleSheetProvider;)V", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "socialMetadataProvider", "Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "getSocialMetadataProvider", "()Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;", "setSocialMetadataProvider", "(Lcom/amazon/music/share/menu/providers/SocialMetadataProvider;)V", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "environmentProvider", "Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "getEnvironmentProvider", "()Lcom/amazon/music/share/menu/providers/EnvironmentProvider;", "setEnvironmentProvider", "(Lcom/amazon/music/share/menu/providers/EnvironmentProvider;)V", "Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "imageUrlRepository", "Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "getImageUrlRepository", "()Lcom/amazon/music/platform/data/image/ImageUrlRepository;", "setImageUrlRepository", "(Lcom/amazon/music/platform/data/image/ImageUrlRepository;)V", "Lcom/amazon/music/share/ShareHelper;", "shareHelper", "Lcom/amazon/music/share/ShareHelper;", "getShareHelper", "()Lcom/amazon/music/share/ShareHelper;", "setShareHelper", "(Lcom/amazon/music/share/ShareHelper;)V", "layout", "Landroid/view/View;", "Landroid/widget/ImageView;", "thumbnail", "Landroid/widget/ImageView;", "Landroid/widget/TextView;", ContextMappingConstants.LABEL, "Landroid/widget/TextView;", "title", MediaTrack.ROLE_SUBTITLE, "itemContainer", "Landroid/view/ViewGroup;", "dismissButton", "Lorg/slf4j/Logger;", "logger", "Lorg/slf4j/Logger;", "", "destinations", "Ljava/util/List;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "getStyleSheet", "()Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "<init>", "()V", "Companion", "ShareItemViewHolder", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0})
public final class SharingFragment extends BottomSheetDialogFragment {
    private final List<ShareDestination> destinations;
    private TextView dismissButton;
    public EnvironmentProvider environmentProvider;
    public ImageUrlRepository imageUrlRepository;
    private ViewGroup itemContainer;
    private TextView label;
    private View layout;
    private final Logger logger;
    public ShareHelper shareHelper;
    public SocialMetadataProvider socialMetadataProvider;
    public StyleSheetProvider styleSheetProvider;
    private TextView subtitle;
    private ImageView thumbnail;
    private TextView title;

    /* JADX INFO: compiled from: SharingFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EntityType.values().length];
            iArr[EntityType.ALBUM.ordinal()] = 1;
            iArr[EntityType.ARTIST.ordinal()] = 2;
            iArr[EntityType.PLAYLIST.ordinal()] = 3;
            iArr[EntityType.USER_PLAYLIST.ordinal()] = 4;
            iArr[EntityType.TRACK.ordinal()] = 5;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public SharingFragment() {
        Logger logger = LoggerFactory.getLogger(SharingFragment.class.getSimpleName());
        Intrinsics.checkNotNullExpressionValue(logger, "getLogger(javaClass.simpleName)");
        this.logger = logger;
        this.destinations = new ArrayList();
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

    private final StyleSheet getStyleSheet() {
        return getStyleSheetProvider().getStyleSheet();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidSupportInjection.inject(this);
        Context context = getContext();
        if (context == null) {
            return;
        }
        initDestinations(context);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(R$layout.fragment_sharing, container, false);
        Intrinsics.checkNotNullExpressionValue(viewInflate, "inflater.inflate(R.layou…haring, container, false)");
        this.layout = viewInflate;
        if (viewInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            viewInflate = null;
        }
        View viewFindViewById = viewInflate.findViewById(R$id.thumbnail);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "layout.findViewById(R.id.thumbnail)");
        this.thumbnail = (ImageView) viewFindViewById;
        View view = this.layout;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            view = null;
        }
        View viewFindViewById2 = view.findViewById(R$id.label);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "layout.findViewById(R.id.label)");
        this.label = (TextView) viewFindViewById2;
        View view2 = this.layout;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            view2 = null;
        }
        View viewFindViewById3 = view2.findViewById(R$id.title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "layout.findViewById(R.id.title)");
        this.title = (TextView) viewFindViewById3;
        View view3 = this.layout;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            view3 = null;
        }
        View viewFindViewById4 = view3.findViewById(R$id.subtitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "layout.findViewById(R.id.subtitle)");
        this.subtitle = (TextView) viewFindViewById4;
        View view4 = this.layout;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            view4 = null;
        }
        View viewFindViewById5 = view4.findViewById(R$id.item_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "layout.findViewById(R.id.item_container)");
        this.itemContainer = (ViewGroup) viewFindViewById5;
        View view5 = this.layout;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            view5 = null;
        }
        View viewFindViewById6 = view5.findViewById(R$id.dismiss_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "layout.findViewById(R.id.dismiss_button)");
        this.dismissButton = (TextView) viewFindViewById6;
        TextView textView = this.label;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.LABEL);
            textView = null;
        }
        FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.LABEL);
        Intrinsics.checkNotNull(fontStyle);
        UIExtensionsKt.applyFontStyle(textView, fontStyle);
        TextView textView2 = this.title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView2 = null;
        }
        FontStyle fontStyle2 = getStyleSheet().getFontStyle(FontStyleKey.HEADLINE_3);
        Intrinsics.checkNotNull(fontStyle2);
        UIExtensionsKt.applyFontStyle(textView2, fontStyle2);
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView3 = null;
        }
        StyleSheet styleSheet = getStyleSheet();
        FontStyleKey fontStyleKey = FontStyleKey.PRIMARY;
        FontStyle fontStyle3 = styleSheet.getFontStyle(fontStyleKey);
        Intrinsics.checkNotNull(fontStyle3);
        UIExtensionsKt.applyFontStyle(textView3, fontStyle3);
        TextView textView4 = this.dismissButton;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            textView4 = null;
        }
        FontStyle fontStyle4 = getStyleSheet().getFontStyle(fontStyleKey);
        Intrinsics.checkNotNull(fontStyle4);
        UIExtensionsKt.applyFontStyle(textView4, fontStyle4);
        TextView textView5 = this.dismissButton;
        if (textView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("dismissButton");
            textView5 = null;
        }
        textView5.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.share.menu.SharingFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view6) {
                SharingFragment.m2184onCreateView$lambda1(this.f$0, view6);
            }
        });
        View view6 = this.layout;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
            view6 = null;
        }
        view6.setVisibility(8);
        ImageView imageView = this.thumbnail;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
            imageView = null;
        }
        imageView.setVisibility(8);
        rebind();
        View view7 = this.layout;
        if (view7 != null) {
            return view7;
        }
        Intrinsics.throwUninitializedPropertyAccessException("layout");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-1, reason: not valid java name */
    public static final void m2184onCreateView$lambda1(SharingFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        expandSheet();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        Context context;
        String privateEntityId;
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        super.onDismiss(dialog);
        Bundle arguments = getArguments();
        ShareableContent shareableContent = arguments == null ? null : (ShareableContent) arguments.getParcelable("com.amazon.music.share.menu.ShareableContent");
        if (shareableContent == null || shareableContent.getEntityType() != EntityType.USER_PLAYLIST || (context = getContext()) == null || (privateEntityId = shareableContent.getPrivateEntityId()) == null) {
            return;
        }
        getShareHelper().syncUserPlaylistData(context, privateEntityId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void appendImageDestinations(Context context) {
        List listMutableListOf = CollectionsKt__CollectionsKt.mutableListOf(new InstagramStoryShareDestination(context, getSocialMetadataProvider(), getEnvironmentProvider(), getStyleSheet()), new FacebookStoryShareDestination(context, getSocialMetadataProvider(), getEnvironmentProvider(), getStyleSheet()));
        if (ShareFeatureGating.SHARING_TO_SNAPCHAT.isEnabled()) {
            listMutableListOf.add(new SnapchatStoryShareDestination(context, getEnvironmentProvider(), getStyleSheet()));
        }
        this.destinations.addAll(0, listMutableListOf);
    }

    private final void initDestinations(Context context) {
        this.destinations.clear();
        CollectionsKt__MutableCollectionsKt.addAll(this.destinations, new ShareDestination[]{new WhatsAppShareDestination(context), new TwitterShareDestination(context), new MessagesShareDestination(context), new LineShareDestination(context), new ClipboardShareDestination(context), new DefaultShareDestination(context)});
    }

    private final void rebind() {
        String upperCase;
        Bundle arguments = getArguments();
        ShareableContent shareableContent = arguments == null ? null : (ShareableContent) arguments.getParcelable("com.amazon.music.share.menu.ShareableContent");
        if (shareableContent == null) {
            return;
        }
        if (shareableContent.getRef() == null) {
            shareableContent.setRef(RefMarkerUtils.INSTANCE.generateRefMarker());
        }
        TextView textView = this.label;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ContextMappingConstants.LABEL);
            textView = null;
        }
        String contentType = shareableContent.getContentType();
        if (contentType == null) {
            upperCase = null;
        } else {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            upperCase = contentType.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(locale)");
        }
        textView.setText(upperCase);
        TextView textView2 = this.title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView2 = null;
        }
        textView2.setText(shareableContent.getTitle());
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView3 = null;
        }
        textView3.setText(shareableContent.getSubtitle());
        BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this), null, null, new C04461(shareableContent, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragment$rebind$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: SharingFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragment$rebind$1", f = "SharingFragment.kt", i = {}, l = {177}, m = "invokeSuspend", n = {}, s = {})
    static final class C04461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ShareableContent $content;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04461(ShareableContent shareableContent, Continuation<? super C04461> continuation) {
            super(2, continuation);
            this.$content = shareableContent;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SharingFragment.this.new C04461(this.$content, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            ImageView imageView = null;
            try {
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    C01141 c01141 = new C01141(this.$content, SharingFragment.this, null);
                    this.label = 1;
                    if (TimeoutKt.withTimeout(10000L, c01141, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
            } catch (TimeoutCancellationException unused) {
                Picasso picasso = Picasso.get();
                ImageView imageView2 = SharingFragment.this.thumbnail;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
                } else {
                    imageView = imageView2;
                }
                picasso.cancelRequest(imageView);
                SharingFragment.this.logger.error("Image load for share menu thumbnail timed out. Loading menu without thumbnail");
                SharingFragment.this.rebindItems();
            }
            return Unit.INSTANCE;
        }

        /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragment$rebind$1$1, reason: invalid class name and collision with other inner class name */
        /* JADX INFO: compiled from: SharingFragment.kt */
        @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
        @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragment$rebind$1$1", f = "SharingFragment.kt", i = {}, l = {178}, m = "invokeSuspend", n = {}, s = {})
        static final class C01141 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ ShareableContent $content;
            int label;
            final /* synthetic */ SharingFragment this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            C01141(ShareableContent shareableContent, SharingFragment sharingFragment, Continuation<? super C01141> continuation) {
                super(2, continuation);
                this.$content = shareableContent;
                this.this$0 = sharingFragment;
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                return new C01141(this.$content, this.this$0, continuation);
            }

            @Override // kotlin.jvm.functions.Function2
            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
            public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                return ((C01141) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
            }

            /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragment$rebind$1$1$1, reason: invalid class name and collision with other inner class name */
            /* JADX INFO: compiled from: SharingFragment.kt */
            @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
            @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragment$rebind$1$1$1", f = "SharingFragment.kt", i = {}, l = {179, 183}, m = "invokeSuspend", n = {}, s = {})
            static final class C01151 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ ShareableContent $content;
                Object L$0;
                int label;
                final /* synthetic */ SharingFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C01151(ShareableContent shareableContent, SharingFragment sharingFragment, Continuation<? super C01151> continuation) {
                    super(2, continuation);
                    this.$content = shareableContent;
                    this.this$0 = sharingFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C01151(this.$content, this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
                public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C01151) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                /* JADX WARN: Removed duplicated region for block: B:18:0x006d A[RETURN] */
                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                    /*
                        r7 = this;
                        java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                        int r1 = r7.label
                        r2 = 2
                        r3 = 1
                        if (r1 == 0) goto L22
                        if (r1 == r3) goto L1a
                        if (r1 != r2) goto L12
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L6e
                    L12:
                        java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                        java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                        r8.<init>(r0)
                        throw r8
                    L1a:
                        java.lang.Object r1 = r7.L$0
                        com.amazon.music.share.menu.ShareableContent r1 = (com.amazon.music.share.menu.ShareableContent) r1
                        kotlin.ResultKt.throwOnFailure(r8)
                        goto L50
                    L22:
                        kotlin.ResultKt.throwOnFailure(r8)
                        com.amazon.music.share.menu.ShareableContent r1 = r7.$content
                        java.lang.String r8 = r1.getImage()
                        if (r8 != 0) goto L52
                        com.amazon.music.share.menu.SharingFragment r8 = r7.this$0
                        com.amazon.music.platform.data.image.ImageUrlRepository r8 = r8.getImageUrlRepository()
                        com.amazon.music.share.menu.ShareableContent r4 = r7.$content
                        java.lang.String r4 = r4.getEntityId()
                        com.amazon.music.share.menu.SharingFragment r5 = r7.this$0
                        com.amazon.music.share.menu.ShareableContent r6 = r7.$content
                        com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType r6 = r6.getEntityType()
                        com.amazon.music.platform.data.entity.EntityItemType r5 = r5.toPlatformEntityItemType(r6)
                        r7.L$0 = r1
                        r7.label = r3
                        java.lang.Object r8 = r8.getImageUrl(r4, r5, r7)
                        if (r8 != r0) goto L50
                        return r0
                    L50:
                        java.lang.String r8 = (java.lang.String) r8
                    L52:
                        r1.setImage(r8)
                        kotlinx.coroutines.MainCoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getMain()
                        com.amazon.music.share.menu.SharingFragment$rebind$1$1$1$1 r1 = new com.amazon.music.share.menu.SharingFragment$rebind$1$1$1$1
                        com.amazon.music.share.menu.SharingFragment r3 = r7.this$0
                        com.amazon.music.share.menu.ShareableContent r4 = r7.$content
                        r5 = 0
                        r1.<init>(r3, r4, r5)
                        r7.L$0 = r5
                        r7.label = r2
                        java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r1, r7)
                        if (r8 != r0) goto L6e
                        return r0
                    L6e:
                        kotlin.Unit r8 = kotlin.Unit.INSTANCE
                        return r8
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.music.share.menu.SharingFragment.C04461.C01141.C01151.invokeSuspend(java.lang.Object):java.lang.Object");
                }

                /* JADX INFO: renamed from: com.amazon.music.share.menu.SharingFragment$rebind$1$1$1$1, reason: invalid class name and collision with other inner class name */
                /* JADX INFO: compiled from: SharingFragment.kt */
                @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
                @DebugMetadata(c = "com.amazon.music.share.menu.SharingFragment$rebind$1$1$1$1", f = "SharingFragment.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
                static final class C01161 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    final /* synthetic */ ShareableContent $content;
                    int label;
                    final /* synthetic */ SharingFragment this$0;

                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    C01161(SharingFragment sharingFragment, ShareableContent shareableContent, Continuation<? super C01161> continuation) {
                        super(2, continuation);
                        this.this$0 = sharingFragment;
                        this.$content = shareableContent;
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new C01161(this.this$0, this.$content, continuation);
                    }

                    @Override // kotlin.jvm.functions.Function2
                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
                    public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((C01161) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        if (this.label != 0) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj);
                        this.this$0.loadThumbnailAndRebind(this.$content);
                        return Unit.INSTANCE;
                    }
                }
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    CoroutineDispatcher io2 = Dispatchers.getIO();
                    C01151 c01151 = new C01151(this.$content, this.this$0, null);
                    this.label = 1;
                    if (BuildersKt.withContext(io2, c01151, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                } else {
                    if (i != 1) {
                        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                    }
                    ResultKt.throwOnFailure(obj);
                }
                return Unit.INSTANCE;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void rebindItems() {
        Bundle arguments = getArguments();
        View view = null;
        final ShareableContent shareableContent = arguments == null ? null : (ShareableContent) arguments.getParcelable("com.amazon.music.share.menu.ShareableContent");
        if (shareableContent == null || getContext() == null) {
            return;
        }
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(getContext());
        ViewGroup viewGroup = this.itemContainer;
        if (viewGroup == null) {
            Intrinsics.throwUninitializedPropertyAccessException("itemContainer");
            viewGroup = null;
        }
        viewGroup.removeAllViews();
        List<ShareDestination> list = this.destinations;
        ArrayList<ShareDestination> arrayList = new ArrayList();
        for (Object obj : list) {
            if (((ShareDestination) obj).isAvailable()) {
                arrayList.add(obj);
            }
        }
        for (final ShareDestination shareDestination : arrayList) {
            int i = R$layout.sharing_item;
            ViewGroup viewGroup2 = this.itemContainer;
            if (viewGroup2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemContainer");
                viewGroup2 = null;
            }
            View viewInflate = layoutInflaterFrom.inflate(i, viewGroup2, false);
            Intrinsics.checkNotNullExpressionValue(viewInflate, "layoutInflater.inflate(\n…em, itemContainer, false)");
            ShareItemViewHolder shareItemViewHolder = new ShareItemViewHolder(viewInflate);
            TextView text = shareItemViewHolder.getText();
            FontStyle fontStyle = getStyleSheet().getFontStyle(FontStyleKey.PRIMARY);
            Intrinsics.checkNotNull(fontStyle);
            UIExtensionsKt.applyFontStyle(text, fontStyle);
            shareItemViewHolder.getText().setText(shareDestination.getNameResId());
            shareItemViewHolder.getIcon().setImageResource(shareDestination.getIconResId());
            shareItemViewHolder.getView().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.share.menu.SharingFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SharingFragment.m2185rebindItems$lambda7$lambda6$lambda5(this.f$0, shareDestination, shareableContent, view2);
                }
            });
            ViewGroup viewGroup3 = this.itemContainer;
            if (viewGroup3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("itemContainer");
                viewGroup3 = null;
            }
            viewGroup3.addView(shareItemViewHolder.getView());
        }
        TextView textView = this.title;
        if (textView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView = null;
        }
        TextView textView2 = this.title;
        if (textView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("title");
            textView2 = null;
        }
        CharSequence text2 = textView2.getText();
        boolean z = true;
        textView.setVisibility(text2 == null || text2.length() == 0 ? 8 : 0);
        TextView textView3 = this.subtitle;
        if (textView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView3 = null;
        }
        TextView textView4 = this.subtitle;
        if (textView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(MediaTrack.ROLE_SUBTITLE);
            textView4 = null;
        }
        CharSequence text3 = textView4.getText();
        if (text3 != null && text3.length() != 0) {
            z = false;
        }
        textView3.setVisibility(z ? 8 : 0);
        View view2 = this.layout;
        if (view2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("layout");
        } else {
            view = view2;
        }
        view.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: rebindItems$lambda-7$lambda-6$lambda-5, reason: not valid java name */
    public static final void m2185rebindItems$lambda7$lambda6$lambda5(SharingFragment this$0, ShareDestination d, ShareableContent content, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(d, "$d");
        Intrinsics.checkNotNullParameter(content, "$content");
        this$0.share(d, content);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void loadThumbnailAndRebind(ShareableContent content) {
        if (content.getImage() != null) {
            loadThumbnailImage(content.getImage(), content.getImageAspectRatio(), content.getEntityType() == EntityType.ARTIST || content.getEntityType() == EntityType.CUSTOMER_PROFILE, new Callback() { // from class: com.amazon.music.share.menu.SharingFragment.loadThumbnailAndRebind.1
                @Override // com.squareup.picasso.Callback
                public void onSuccess() {
                    SharingFragment.this.logger.info("Image load for share menu thumbnail successful. Loading menu with thumbnail");
                    Context context = SharingFragment.this.getContext();
                    if (context != null) {
                        SharingFragment sharingFragment = SharingFragment.this;
                        ImageView imageView = sharingFragment.thumbnail;
                        if (imageView == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
                            imageView = null;
                        }
                        imageView.setVisibility(0);
                        sharingFragment.appendImageDestinations(context);
                    }
                    SharingFragment.this.rebindItems();
                }

                @Override // com.squareup.picasso.Callback
                public void onError(Exception e) {
                    Intrinsics.checkNotNullParameter(e, "e");
                    SharingFragment.this.rebindItems();
                    SharingFragment.this.logger.error("Image load for share menu thumbnail failed. Loading menu without thumbnail");
                }
            });
        } else {
            rebindItems();
        }
    }

    private final void loadThumbnailImage(String imageUrl, float aspectRatio, boolean isProfileOrArtist, Callback callback) {
        ImageView imageView = this.thumbnail;
        ImageView imageView2 = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
            imageView = null;
        }
        int i = imageView.getLayoutParams().height;
        int i2 = (int) (i * aspectRatio);
        ImageView imageView3 = this.thumbnail;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
            imageView3 = null;
        }
        ImageView imageView4 = this.thumbnail;
        if (imageView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
            imageView4 = null;
        }
        ViewGroup.LayoutParams layoutParams = imageView4.getLayoutParams();
        layoutParams.width = i2;
        imageView3.setLayoutParams(layoutParams);
        RequestCreator requestCreatorResize = Picasso.get().load(imageUrl).resize(i2, i);
        if (isProfileOrArtist) {
            RequestCreator requestCreatorCenterCrop = requestCreatorResize.transform(new CircleTransformation()).centerCrop();
            ImageView imageView5 = this.thumbnail;
            if (imageView5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
            } else {
                imageView2 = imageView5;
            }
            requestCreatorCenterCrop.into(imageView2, callback);
            return;
        }
        ImageRoundCornerTransformation imageRoundCornerTransformation = new ImageRoundCornerTransformation();
        imageRoundCornerTransformation.setCornerRadius(Float.valueOf(getStyleSheet().getCornerSize(CornerSizeKey.MEDIUM) == null ? 0.0f : r8.intValue()));
        RequestCreator requestCreatorCenterCrop2 = requestCreatorResize.transform(imageRoundCornerTransformation).centerCrop();
        ImageView imageView6 = this.thumbnail;
        if (imageView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("thumbnail");
        } else {
            imageView2 = imageView6;
        }
        requestCreatorCenterCrop2.into(imageView2, callback);
    }

    private final void expandSheet() {
        Dialog dialog = getDialog();
        BottomSheetDialog bottomSheetDialog = dialog instanceof BottomSheetDialog ? (BottomSheetDialog) dialog : null;
        View viewFindViewById = bottomSheetDialog == null ? null : bottomSheetDialog.findViewById(R$id.design_bottom_sheet);
        if (viewFindViewById == null) {
            return;
        }
        viewFindViewById.setBackgroundColor(getResources().getColor(R$color.black, null));
        BottomSheetBehavior.from(viewFindViewById).setState(3);
    }

    private final void share(ShareDestination destination, ShareableContent content) {
        try {
            destination.share(content);
        } catch (Exception e) {
            this.logger.error("Error sharing [" + content.getEntityType() + "] using [" + destination.getActionType() + ']', (Throwable) e);
            if (!(destination instanceof DefaultShareDestination)) {
                Context context = getContext();
                if (context == null) {
                    return;
                }
                share(new DefaultShareDestination(context), content);
                return;
            }
        }
        dismiss();
    }

    public final EntityItemType toPlatformEntityItemType(EntityType entityType) {
        int i = entityType == null ? -1 : WhenMappings.$EnumSwitchMapping$0[entityType.ordinal()];
        if (i == 1) {
            return CollectionType.Album.INSTANCE;
        }
        if (i == 2) {
            return CollectionType.Artist.INSTANCE;
        }
        if (i == 3 || i == 4) {
            return CollectionType.Playlist.INSTANCE;
        }
        if (i != 5) {
            return null;
        }
        return SingleType.Track.INSTANCE;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        dismiss();
    }

    /* JADX INFO: compiled from: SharingFragment.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\n¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lcom/amazon/music/share/menu/SharingFragment$ShareItemViewHolder;", "", "view", "Landroid/view/View;", "(Landroid/view/View;)V", "icon", "Landroid/widget/ImageView;", "getIcon", "()Landroid/widget/ImageView;", "text", "Landroid/widget/TextView;", "getText", "()Landroid/widget/TextView;", "getView", "()Landroid/view/View;", "DMMShareProvider_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ShareItemViewHolder {
        private final ImageView icon;
        private final TextView text;
        private final View view;

        public ShareItemViewHolder(View view) {
            Intrinsics.checkNotNullParameter(view, "view");
            this.view = view;
            View viewFindViewById = view.findViewById(R$id.icon);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.icon)");
            this.icon = (ImageView) viewFindViewById;
            View viewFindViewById2 = view.findViewById(R$id.text);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.text)");
            this.text = (TextView) viewFindViewById2;
        }

        public final View getView() {
            return this.view;
        }

        public final ImageView getIcon() {
            return this.icon;
        }

        public final TextView getText() {
            return this.text;
        }
    }
}
