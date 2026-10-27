package com.amazon.mp3.actionbar.view;

import android.animation.LayoutTransition;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.view.KeyEventDispatcher;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.MusicHomeActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingBaseButton;
import com.amazon.mp3.casting.CastingButtonHelper;
import com.amazon.mp3.catalog.fragment.BrushFragment;
import com.amazon.mp3.customerprofile.CustomerProfileDetailManager;
import com.amazon.mp3.fragment.navigation.BrushFragmentNavigation;
import com.amazon.mp3.identityprofiles.IdentityActivityHelper;
import com.amazon.mp3.providers.DefaultCachedArtworkProvider;
import com.amazon.mp3.service.metrics.MetricsLogger;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.AccessibilityUtil;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.find.view.FindSearchBarView;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManager;
import com.amazon.music.identity.profiles.datasource.mockprofile.MockProfileManagerSingleton;
import com.amazon.music.identity.profiles.datasource.mockprofile.ProfileBaseModel;
import com.amazon.music.identity.profiles.view.util.ViewUtil;
import com.amazon.music.imageloader.ImageLoader;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContentName;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.picassoimageloader.PicassoImageLoader;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.providers.CachedArtworkProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.CornerSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.ScreenSizeKey;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.views.library.views.presentation.ActionBarOverlayable;
import com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.ImageSize;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.google.android.gms.cast.MediaTrack;
import com.google.firebase.perf.plugin.util.AsmString;
import com.squareup.picasso.Picasso;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt___RangesKt;
import kotlin.text.StringsKt__StringsJVMKt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rx.Subscription;
import rx.functions.Action1;

/* JADX INFO: compiled from: BauhausActionBarView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000ò\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b3\b\u0016\u0018\u0000 Þ\u00012\u00020\u00012\u00020\u0002:\u0004Þ\u0001ß\u0001BR\u0012\n\b\u0002\u0010X\u001a\u0004\u0018\u00010W\u0012\n\b\u0002\u0010>\u001a\u0004\u0018\u00010\u0003\u0012\u000b\b\u0002\u0010×\u0001\u001a\u0004\u0018\u00010\u0003\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010_\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010b\u001a\u0004\u0018\u00010a¢\u0006\u0006\bØ\u0001\u0010Ù\u0001B\u001f\b\u0016\u0012\b\u0010X\u001a\u0004\u0018\u00010W\u0012\b\u0010>\u001a\u0004\u0018\u00010\u0003¢\u0006\u0006\bØ\u0001\u0010Ú\u0001B*\b\u0016\u0012\b\u0010X\u001a\u0004\u0018\u00010W\u0012\b\u0010>\u001a\u0004\u0018\u00010\u0003\u0012\t\u0010×\u0001\u001a\u0004\u0018\u00010\u0003¢\u0006\u0006\bØ\u0001\u0010Û\u0001B3\b\u0016\u0012\b\u0010X\u001a\u0004\u0018\u00010W\u0012\b\u0010>\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010_\u001a\u0004\u0018\u00010\u0006¢\u0006\u0006\bØ\u0001\u0010Ü\u0001B=\b\u0016\u0012\b\u0010X\u001a\u0004\u0018\u00010W\u0012\b\u0010>\u001a\u0004\u0018\u00010\u0003\u0012\b\u0010\b\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010_\u001a\u0004\u0018\u00010\u0006\u0012\b\u0010b\u001a\u0004\u0018\u00010a¢\u0006\u0006\bØ\u0001\u0010Ý\u0001J\u001c\u0010\u0007\u001a\u00020\u00062\b\u0010\u0004\u001a\u0004\u0018\u00010\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0002J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002J_\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u00102\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u00122\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001b\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\fH\u0002J\b\u0010\u001c\u001a\u00020\u0006H\u0002J\u000e\u0010\u001f\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\u001dJ\u000e\u0010!\u001a\u00020\t2\u0006\u0010 \u001a\u00020\u0003J\u0006\u0010\"\u001a\u00020\tJ\u0018\u0010&\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u00062\b\u0010%\u001a\u0004\u0018\u00010$J\u000e\u0010(\u001a\u00020\t2\u0006\u0010'\u001a\u00020\u0006J\u0006\u0010)\u001a\u00020\tJ\u0006\u0010*\u001a\u00020\tJ\u0006\u0010+\u001a\u00020\tJ\u0010\u0010-\u001a\u00020\t2\b\u0010,\u001a\u0004\u0018\u00010\u0003J\u0006\u0010.\u001a\u00020\tJ\u0006\u0010/\u001a\u00020\tJ\u0006\u00100\u001a\u00020\tJ\u0006\u00101\u001a\u00020\tJ\u0006\u00102\u001a\u00020\tJ\u0006\u00103\u001a\u00020\tJ\b\u00104\u001a\u00020\tH\u0016J\u0012\u00107\u001a\u00020\t2\b\u00106\u001a\u0004\u0018\u000105H\u0014J7\u0010:\u001a\u00020\t2\b\u00108\u001a\u0004\u0018\u00010\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u00122\b\u00109\u001a\u0004\u0018\u00010\u00122\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012H\u0016¢\u0006\u0004\b:\u0010;J\u0006\u0010=\u001a\u00020<J\u001c\u0010@\u001a\u00020\t2\b\u0010>\u001a\u0004\u0018\u00010\u00032\b\u0010?\u001a\u0004\u0018\u00010\u0003H\u0016J\u0006\u0010B\u001a\u00020AJ\u000e\u0010C\u001a\u00020\t2\u0006\u0010#\u001a\u00020\u0006J\u0006\u0010D\u001a\u00020\u0006J\u0017\u0010F\u001a\u00020\t2\b\u0010E\u001a\u0004\u0018\u00010\u0012¢\u0006\u0004\bF\u0010GJ\u0010\u0010I\u001a\u00020\t2\u0006\u0010H\u001a\u00020AH\u0016J\b\u0010J\u001a\u00020\tH\u0014J\b\u0010K\u001a\u00020\tH\u0014J\u0010\u0010L\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0010H\u0004J\u0006\u0010N\u001a\u00020MJ\u0016\u0010P\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010O\u001a\u00020\u001dJ\u0006\u0010Q\u001a\u00020\tJ\u0006\u0010R\u001a\u00020\tJ\u0006\u0010S\u001a\u00020\u0006J\b\u0010T\u001a\u00020\tH\u0016J\u000e\u0010V\u001a\u00020\t2\u0006\u0010U\u001a\u00020\u0006R\u0019\u0010X\u001a\u0004\u0018\u00010W8\u0006¢\u0006\f\n\u0004\bX\u0010Y\u001a\u0004\bZ\u0010[R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\b\u0010\\\u001a\u0004\b]\u0010^R\u0019\u0010_\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b_\u0010\\\u001a\u0004\b`\u0010^R\u0019\u0010b\u001a\u0004\u0018\u00010a8\u0006¢\u0006\f\n\u0004\bb\u0010c\u001a\u0004\bd\u0010eR\u0018\u0010g\u001a\u0004\u0018\u00010f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bg\u0010hR.\u0010j\u001a\u0004\u0018\u00010\u001d2\b\u0010i\u001a\u0004\u0018\u00010\u001d8\u0006@FX\u0086\u000e¢\u0006\u0012\n\u0004\bj\u0010k\u001a\u0004\bl\u0010m\"\u0004\bn\u0010oR\u0017\u0010q\u001a\u00020p8\u0006¢\u0006\f\n\u0004\bq\u0010r\u001a\u0004\bs\u0010tR\u0014\u0010u\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bu\u0010kR\u0014\u0010v\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bv\u0010kR\u0014\u0010w\u001a\u00020\u001d8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bw\u0010kR\u0014\u0010x\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bx\u0010yR\u0014\u0010z\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bz\u0010yR\u0014\u0010|\u001a\u00020{8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b|\u0010}R\u0015\u0010\u007f\u001a\u00020~8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u007f\u0010\u0080\u0001R\u0017\u0010\u0081\u0001\u001a\u00020~8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0081\u0001\u0010\u0080\u0001R\u0017\u0010\u0082\u0001\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0017\u0010\u0084\u0001\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0084\u0001\u0010\u0083\u0001R\u0018\u0010\u0086\u0001\u001a\u00030\u0085\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0018\u0010\u0089\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0089\u0001\u0010\u008a\u0001R\u0018\u0010\u008c\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\u0018\u0010\u008f\u0001\u001a\u00030\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0018\u0010\u0091\u0001\u001a\u00030\u0088\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0091\u0001\u0010\u008a\u0001R\u0018\u0010\u0093\u0001\u001a\u00030\u0092\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0017\u0010\u0095\u0001\u001a\u00020~8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0080\u0001R\u0017\u0010\u0096\u0001\u001a\u00020~8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0096\u0001\u0010\u0080\u0001R\u0016\u0010\u0097\u0001\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0097\u0001\u0010yR\u0016\u0010\u0098\u0001\u001a\u00020<8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0098\u0001\u0010yR\u0017\u0010\u0099\u0001\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0018\u0010\u009b\u0001\u001a\u00030\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009b\u0001\u0010\u008d\u0001R\u0018\u0010\u009d\u0001\u001a\u00030\u009c\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0018\u0010 \u0001\u001a\u00030\u009f\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b \u0001\u0010¡\u0001R\u001c\u0010£\u0001\u001a\u0005\u0018\u00010¢\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b£\u0001\u0010¤\u0001R\u001c\u0010¦\u0001\u001a\u0005\u0018\u00010¥\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¦\u0001\u0010§\u0001R\u001c\u0010©\u0001\u001a\u0005\u0018\u00010¨\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b©\u0001\u0010ª\u0001R\u001b\u0010«\u0001\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b«\u0001\u0010¬\u0001R\u001c\u0010®\u0001\u001a\u0005\u0018\u00010\u00ad\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b®\u0001\u0010¯\u0001R\u0019\u0010°\u0001\u001a\u00020\u00068\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b°\u0001\u0010±\u0001R\u0019\u0010²\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b²\u0001\u0010³\u0001R\u0019\u0010´\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0001\u0010³\u0001R \u0010¶\u0001\u001a\u000b µ\u0001*\u0004\u0018\u00010\u00100\u00108\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¶\u0001\u0010·\u0001R \u0010¸\u0001\u001a\u000b µ\u0001*\u0004\u0018\u00010~0~8\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¸\u0001\u0010\u0080\u0001R\u0017\u0010»\u0001\u001a\u00020~8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¹\u0001\u0010º\u0001R\u0017\u0010½\u0001\u001a\u00020~8VX\u0096\u0004¢\u0006\b\u001a\u0006\b¼\u0001\u0010º\u0001R)\u0010¾\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\b¾\u0001\u0010¿\u0001\"\u0006\bÀ\u0001\u0010Á\u0001R)\u0010Â\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\bÂ\u0001\u0010¿\u0001\"\u0006\bÃ\u0001\u0010Á\u0001R)\u0010Ä\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\bÄ\u0001\u0010¿\u0001\"\u0006\bÅ\u0001\u0010Á\u0001R)\u0010Æ\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\bÆ\u0001\u0010¿\u0001\"\u0006\bÇ\u0001\u0010Á\u0001R\u0019\u0010Ê\u0001\u001a\u0004\u0018\u00010\u00108VX\u0096\u0004¢\u0006\b\u001a\u0006\bÈ\u0001\u0010É\u0001R+\u0010>\u001a\u0004\u0018\u00010\u00032\b\u0010i\u001a\u0004\u0018\u00010\u00038V@VX\u0096\u000e¢\u0006\u000f\u001a\u0005\b=\u0010Ë\u0001\"\u0006\bÌ\u0001\u0010Í\u0001R,\u0010?\u001a\u0004\u0018\u00010\u00032\b\u0010i\u001a\u0004\u0018\u00010\u00038V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\bÎ\u0001\u0010Ë\u0001\"\u0006\bÏ\u0001\u0010Í\u0001R+\u0010Ô\u0001\u001a\u00030\u00ad\u00012\u0007\u0010i\u001a\u00030\u00ad\u00018V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\bÐ\u0001\u0010Ñ\u0001\"\u0006\bÒ\u0001\u0010Ó\u0001R)\u0010Õ\u0001\u001a\u00020\u00062\u0006\u0010i\u001a\u00020\u00068V@VX\u0096\u000e¢\u0006\u0010\u001a\u0006\bÕ\u0001\u0010¿\u0001\"\u0006\bÖ\u0001\u0010Á\u0001¨\u0006à\u0001"}, d2 = {"Lcom/amazon/mp3/actionbar/view/BauhausActionBarView;", "Landroid/widget/LinearLayout;", "Lcom/amazon/music/views/library/views/presentation/TranslucentActionBarDecorator;", "", "origTitle", "newTitle", "", "titleChanged", "showProfileButton", "", "setupActionBar", "(Ljava/lang/Boolean;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "updateStyle", "resetButtonShadows", "Landroid/view/View;", "view", "", "width", "height", "left", "top", "right", "bottom", "setViewMargins", "(Landroid/view/View;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "initViewSpacing", "isBauhausTopAppBarEnabled", "Landroid/view/View$OnClickListener;", "contextMenuOverflowButtonClickListener", "setContextMenuOverflowOnClickListener", "uri", "showAlbumImage", "hideAlbumImage", "shouldShow", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "metadata", "showCachedImage", "isShown", "showContextMenuOverflowButton", "showProfileIcon", "showIngressButtons", "showToggleView", "link", "handleDeepLink", "hideActionBar", "showTitle", "hideTitle", "showActionBar", "addTopMarginToToolBar", "hideFeedButton", "addTopMargin", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", AsmString.METHOD_FIREBASE_PERF_TRACE_START, "end", "setSearchBarMargins", "(Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;)V", "Landroid/widget/TextView;", "getTitle", "title", MediaTrack.ROLE_SUBTITLE, "setTitles", "Landroidx/recyclerview/widget/RecyclerView;", "getHeaderNavigationView", "showHeaderNavigationView", "isHeaderNavigationViewShown", "colorId", "setToolBarBackgroundColor", "(Ljava/lang/Integer;)V", "recyclerView", "updateBackgroundOpacity", "onDetachedFromWindow", "onAttachedToWindow", "addViewBelowActionBar", "Lcom/amazon/mp3/actionbar/view/ActionBarRightButton;", "getActionOverflowButton", "onClickListener", "setFindUpsellButton", "hideFindUpsellButton", "showFindUpsellButton", "isFindUpsellButtonLayoutVisible", "clearSubscriptions", "isOverlay", "updateActionBarOverlayState", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/FragmentActivity;", "getActivity", "()Landroidx/fragment/app/FragmentActivity;", "Ljava/lang/Boolean;", "getShowProfileButton", "()Ljava/lang/Boolean;", "showOverflowButton", "getShowOverflowButton", "Lcom/amazon/music/find/view/FindSearchBarView;", "findSearchBarView", "Lcom/amazon/music/find/view/FindSearchBarView;", "getFindSearchBarView", "()Lcom/amazon/music/find/view/FindSearchBarView;", "Lrx/Subscription;", "subscription", "Lrx/Subscription;", "value", "onCenterClickListener", "Landroid/view/View$OnClickListener;", "getOnCenterClickListener", "()Landroid/view/View$OnClickListener;", "setOnCenterClickListener", "(Landroid/view/View$OnClickListener;)V", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "onScrollListener", "Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "getOnScrollListener", "()Landroidx/recyclerview/widget/RecyclerView$OnScrollListener;", "musicIngressOnClickListener", "podcastIngressOnClickListener", "profileBarRightImageOnClickListener", "titleView", "Landroid/widget/TextView;", "subTitleView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "constraintLayout", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lcom/amazon/ui/foundations/views/BaseButton;", "backButtonView", "Lcom/amazon/ui/foundations/views/BaseButton;", "feedButton", "actionButtonOverflow", "Lcom/amazon/mp3/actionbar/view/ActionBarRightButton;", "actionButtonProfile", "Landroid/widget/ImageView;", "logoView", "Landroid/widget/ImageView;", "Landroid/widget/FrameLayout;", "leftButtonContainer", "Landroid/widget/FrameLayout;", "Lcom/amazon/ui/foundations/views/BaseImage;", "albumImageView", "Lcom/amazon/ui/foundations/views/BaseImage;", "Landroid/widget/RelativeLayout;", "centerTextContainer", "Landroid/widget/RelativeLayout;", "rightButtonContainer", "Lcom/amazon/mp3/casting/CastingBaseButton;", "castingButton", "Lcom/amazon/mp3/casting/CastingBaseButton;", "actionButtonView", "contextMenuOverflowButton", "musicIngressButton", "podcastIngressButton", "headerNavigationView", "Landroidx/recyclerview/widget/RecyclerView;", "profileBarRightImage", "Landroid/util/DisplayMetrics;", "displayMetrics", "Landroid/util/DisplayMetrics;", "Lcom/amazon/music/imageloader/ImageLoader;", "imageLoader", "Lcom/amazon/music/imageloader/ImageLoader;", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "cacheArtworkProvider", "Lcom/amazon/music/views/library/providers/CachedArtworkProvider;", "Lcom/amazon/mp3/casting/CastingButtonHelper;", "castingButtonHelper", "Lcom/amazon/mp3/casting/CastingButtonHelper;", "Landroidx/appcompat/widget/Toolbar;", "toolbar", "Landroidx/appcompat/widget/Toolbar;", "artworkUri", "Ljava/lang/String;", "", "lastOpacity", "Ljava/lang/Float;", "enableButtonBackgrounds", "Z", "actionBarHeight", "I", "actionBarFadeOffset", "kotlin.jvm.PlatformType", "findUpsellButtonLayout", "Landroid/view/View;", "findUpsellButton", "getActionButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "actionButton", "getBackButton", "backButton", "isBackButtonVisible", "()Z", "setBackButtonVisible", "(Z)V", "isButtonBackgroundEnabled", "setButtonBackgroundEnabled", "isFeedButtonVisible", "setFeedButtonVisible", "isLogoVisible", "setLogoVisible", "getSearchBar", "()Landroid/view/View;", "searchBar", "()Ljava/lang/String;", "setTitle", "(Ljava/lang/String;)V", "getSubtitle", "setSubtitle", "getBackgroundOpacity", "()F", "setBackgroundOpacity", "(F)V", "backgroundOpacity", "isActionBarVisible", "setActionBarVisible", "subTitle", "<init>", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/find/view/FindSearchBarView;)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/String;)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;)V", "(Landroidx/fragment/app/FragmentActivity;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/amazon/music/find/view/FindSearchBarView;)V", "Companion", "ContentOffsetItemDecoration", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public class BauhausActionBarView extends LinearLayout implements TranslucentActionBarDecorator {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = BauhausActionBarView.class.getSimpleName();
    private static final Logger logger = LoggerFactory.getLogger(BauhausActionBarView.class.getName());
    private int actionBarFadeOffset;
    private int actionBarHeight;
    private final ActionBarRightButton actionButtonOverflow;
    private final ActionBarRightButton actionButtonProfile;
    private final BaseButton actionButtonView;
    private final FragmentActivity activity;
    private final BaseImage albumImageView;
    private String artworkUri;
    private final BaseButton backButtonView;
    private CachedArtworkProvider cacheArtworkProvider;
    private final CastingBaseButton castingButton;
    private CastingButtonHelper castingButtonHelper;
    private final RelativeLayout centerTextContainer;
    private final ConstraintLayout constraintLayout;
    private final BaseButton contextMenuOverflowButton;
    private final DisplayMetrics displayMetrics;
    private boolean enableButtonBackgrounds;
    private final BaseButton feedButton;
    private final FindSearchBarView findSearchBarView;
    private final BaseButton findUpsellButton;
    private final View findUpsellButtonLayout;
    private final RecyclerView headerNavigationView;
    private final ImageLoader imageLoader;
    private Float lastOpacity;
    private final FrameLayout leftButtonContainer;
    private final ImageView logoView;
    private final TextView musicIngressButton;
    private final View.OnClickListener musicIngressOnClickListener;
    private View.OnClickListener onCenterClickListener;
    private final RecyclerView.OnScrollListener onScrollListener;
    private final TextView podcastIngressButton;
    private final View.OnClickListener podcastIngressOnClickListener;
    private final BaseImage profileBarRightImage;
    private final View.OnClickListener profileBarRightImageOnClickListener;
    private final FrameLayout rightButtonContainer;
    private final Boolean showOverflowButton;
    private final Boolean showProfileButton;
    private final TextView subTitleView;
    private Subscription subscription;
    private final TextView titleView;
    private Toolbar toolbar;

    public BauhausActionBarView() {
        this(null, null, null, null, null, null, 63, null);
    }

    @JvmStatic
    public static final int getActionBarHeight(Resources resources) {
        return INSTANCE.getActionBarHeight(resources);
    }

    public final FragmentActivity getActivity() {
        return this.activity;
    }

    public /* synthetic */ BauhausActionBarView(FragmentActivity fragmentActivity, String str, String str2, Boolean bool, Boolean bool2, FindSearchBarView findSearchBarView, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : fragmentActivity, (i & 2) != 0 ? null : str, (i & 4) != 0 ? null : str2, (i & 8) != 0 ? Boolean.FALSE : bool, (i & 16) != 0 ? Boolean.FALSE : bool2, (i & 32) != 0 ? null : findSearchBarView);
    }

    public final Boolean getShowProfileButton() {
        return this.showProfileButton;
    }

    public final Boolean getShowOverflowButton() {
        return this.showOverflowButton;
    }

    public BauhausActionBarView(FragmentActivity fragmentActivity, String str, String str2, Boolean bool, Boolean bool2, FindSearchBarView findSearchBarView) {
        Drawable background;
        super(fragmentActivity);
        this.activity = fragmentActivity;
        this.showProfileButton = bool;
        this.showOverflowButton = bool2;
        this.findSearchBarView = findSearchBarView;
        this.onScrollListener = new RecyclerView.OnScrollListener() { // from class: com.amazon.mp3.actionbar.view.BauhausActionBarView$onScrollListener$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                super.onScrolled(recyclerView, dx, dy);
                if (recyclerView.getVisibility() != 0) {
                    return;
                }
                this.this$0.updateBackgroundOpacity(recyclerView);
            }
        };
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.BauhausActionBarView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BauhausActionBarView.m132musicIngressOnClickListener$lambda0(this.f$0, view);
            }
        };
        this.musicIngressOnClickListener = onClickListener;
        View.OnClickListener onClickListener2 = new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.BauhausActionBarView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
                BauhausActionBarView.m133podcastIngressOnClickListener$lambda1(this.f$0, view);
            }
        };
        this.podcastIngressOnClickListener = onClickListener2;
        View.OnClickListener onClickListener3 = new View.OnClickListener() { // from class: com.amazon.mp3.actionbar.view.BauhausActionBarView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BauhausActionBarView.m134profileBarRightImageOnClickListener$lambda2(this.f$0, view);
            }
        };
        this.profileBarRightImageOnClickListener = onClickListener3;
        DisplayMetrics displayMetrics = getContext().getApplicationContext().getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "context\n            .app…          .displayMetrics");
        this.displayMetrics = displayMetrics;
        PicassoImageLoader picassoImageLoaderBuild = PicassoImageLoader.builder(getContext(), Picasso.get()).build();
        Intrinsics.checkNotNullExpressionValue(picassoImageLoaderBuild, "builder(context, Picasso.get()).build()");
        this.imageLoader = picassoImageLoaderBuild;
        Companion companion = INSTANCE;
        Resources resources = getContext().getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        this.actionBarHeight = companion.getActionBarHeight(resources);
        this.actionBarFadeOffset = getContext().getResources().getDimensionPixelSize(R.dimen.action_bar_fade_offset);
        View viewInflate = View.inflate(getContext(), R.layout.find_upsell_button, null);
        this.findUpsellButtonLayout = viewInflate;
        this.findUpsellButton = (BaseButton) viewInflate.findViewById(R.id.upsell_button);
        View.inflate(getContext(), R.layout.bauhaus_action_bar_view, this);
        setOrientation(1);
        setLayoutTransition(new LayoutTransition());
        View viewFindViewById = findViewById(R.id.Title);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "findViewById(R.id.Title)");
        this.titleView = (TextView) viewFindViewById;
        View viewFindViewById2 = findViewById(R.id.Subtitle);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "findViewById(R.id.Subtitle)");
        this.subTitleView = (TextView) viewFindViewById2;
        View viewFindViewById3 = findViewById(R.id.Action_Button_Back);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "findViewById(R.id.Action_Button_Back)");
        this.backButtonView = (BaseButton) viewFindViewById3;
        View viewFindViewById4 = findViewById(R.id.feed_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "findViewById(R.id.feed_button)");
        this.feedButton = (BaseButton) viewFindViewById4;
        View viewFindViewById5 = findViewById(R.id.Action_Button_Overflow);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "findViewById(R.id.Action_Button_Overflow)");
        this.actionButtonOverflow = (ActionBarRightButton) viewFindViewById5;
        View viewFindViewById6 = findViewById(R.id.Logo_Image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "findViewById(R.id.Logo_Image)");
        this.logoView = (ImageView) viewFindViewById6;
        View viewFindViewById7 = findViewById(R.id.action_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "findViewById(R.id.action_button)");
        this.actionButtonView = (BaseButton) viewFindViewById7;
        View viewFindViewById8 = findViewById(R.id.action_bar_layout);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "findViewById(R.id.action_bar_layout)");
        this.constraintLayout = (ConstraintLayout) viewFindViewById8;
        View viewFindViewById9 = findViewById(R.id.left_button_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "findViewById(R.id.left_button_container)");
        this.leftButtonContainer = (FrameLayout) viewFindViewById9;
        View viewFindViewById10 = findViewById(R.id.album_artwork);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "findViewById(R.id.album_artwork)");
        this.albumImageView = (BaseImage) viewFindViewById10;
        View viewFindViewById11 = findViewById(R.id.music_ingress_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "findViewById(R.id.music_ingress_button)");
        TextView textView = (TextView) viewFindViewById11;
        this.musicIngressButton = textView;
        View viewFindViewById12 = findViewById(R.id.podcast_ingress_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "findViewById(R.id.podcast_ingress_button)");
        TextView textView2 = (TextView) viewFindViewById12;
        this.podcastIngressButton = textView2;
        View viewFindViewById13 = findViewById(R.id.headerNavigationView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById13, "findViewById(R.id.headerNavigationView)");
        this.headerNavigationView = (RecyclerView) viewFindViewById13;
        View viewFindViewById14 = findViewById(R.id.center_text_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById14, "findViewById(R.id.center_text_container)");
        this.centerTextContainer = (RelativeLayout) viewFindViewById14;
        View viewFindViewById15 = findViewById(R.id.right_button_container);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById15, "findViewById(R.id.right_button_container)");
        this.rightButtonContainer = (FrameLayout) viewFindViewById15;
        View viewFindViewById16 = findViewById(R.id.context_menu_overflow_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById16, "findViewById(R.id.context_menu_overflow_button)");
        this.contextMenuOverflowButton = (BaseButton) viewFindViewById16;
        View viewFindViewById17 = findViewById(R.id.casting_button);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById17, "findViewById(R.id.casting_button)");
        this.castingButton = (CastingBaseButton) viewFindViewById17;
        View viewFindViewById18 = findViewById(R.id.Action_Button_Profile);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById18, "findViewById(R.id.Action_Button_Profile)");
        this.actionButtonProfile = (ActionBarRightButton) viewFindViewById18;
        this.toolbar = fragmentActivity != null ? (Toolbar) fragmentActivity.findViewById(R.id.toolbar) : null;
        setClipChildren(false);
        setClipToPadding(false);
        View viewFindViewById19 = findViewById(R.id.profile_bar_right_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById19, "findViewById(R.id.profile_bar_right_image)");
        BaseImage baseImage = (BaseImage) viewFindViewById19;
        this.profileBarRightImage = baseImage;
        setTitles(str, str2);
        setupActionBar(bool);
        if (findSearchBarView != null) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
            int dimension = (int) getResources().getDimension(R.dimen.spacer_20);
            layoutParams.setMargins(dimension, dimension, dimension, dimension);
            findSearchBarView.setLayoutParams(layoutParams);
            addView(findSearchBarView);
        }
        addView(viewInflate);
        if (fragmentActivity != null) {
            this.subscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.actionbar.view.BauhausActionBarView$$ExternalSyntheticLambda3
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    BauhausActionBarView.m131_init_$lambda3(this.f$0, (StyleSheet) obj);
                }
            });
        }
        textView.setOnClickListener(onClickListener);
        textView2.setOnClickListener(onClickListener2);
        baseImage.setOnClickListener(onClickListener3);
        Toolbar toolbar = this.toolbar;
        if (toolbar == null || (background = toolbar.getBackground()) == null) {
            return;
        }
        background.mutate();
    }

    public final FindSearchBarView getFindSearchBarView() {
        return this.findSearchBarView;
    }

    /* JADX INFO: compiled from: BauhausActionBarView.kt */
    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\n \n*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/actionbar/view/BauhausActionBarView$Companion;", "", "()V", "MAX_DRAWABLE_OPACITY", "", "MAX_TOOLBAR_OPACITY_VALUE", "MAX_TOOLBAR_OPACITY_VALUE_FACETED_NAV", "MIN_TOOLBAR_OPACITY_VALUE", "TAG", "", "kotlin.jvm.PlatformType", "logger", "Lorg/slf4j/Logger;", "getActionBarHeight", "", "resources", "Landroid/content/res/Resources;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        @JvmStatic
        public final int getActionBarHeight(Resources resources) {
            Intrinsics.checkNotNullParameter(resources, "resources");
            return resources.getDimensionPixelSize(R.dimen.action_bar_height);
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BauhausActionBarView(FragmentActivity fragmentActivity, String str) {
        Boolean bool = Boolean.FALSE;
        this(fragmentActivity, str, null, bool, bool, null);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BauhausActionBarView(FragmentActivity fragmentActivity, String str, String str2) {
        Boolean bool = Boolean.FALSE;
        this(fragmentActivity, str, str2, bool, bool, null);
    }

    public BauhausActionBarView(FragmentActivity fragmentActivity, String str, Boolean bool, Boolean bool2) {
        this(fragmentActivity, str, null, bool, bool2, null);
    }

    public BauhausActionBarView(FragmentActivity fragmentActivity, String str, Boolean bool, Boolean bool2, FindSearchBarView findSearchBarView) {
        this(fragmentActivity, str, null, bool, bool2, findSearchBarView);
    }

    public final View.OnClickListener getOnCenterClickListener() {
        return this.onCenterClickListener;
    }

    public final void setOnCenterClickListener(View.OnClickListener onClickListener) {
        this.onCenterClickListener = onClickListener;
        this.centerTextContainer.setOnClickListener(onClickListener);
        this.albumImageView.setOnClickListener(onClickListener);
    }

    public final RecyclerView.OnScrollListener getOnScrollListener() {
        return this.onScrollListener;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: musicIngressOnClickListener$lambda-0, reason: not valid java name */
    public static final void m132musicIngressOnClickListener$lambda0(BauhausActionBarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BrushFragment brushFragmentCreateFragment = new BrushFragmentNavigation.Builder("home-music").createFragment();
        FragmentActivity fragmentActivity = this$0.activity;
        if (fragmentActivity == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.mp3.activity.MusicHomeActivity");
        }
        ((MusicHomeActivity) fragmentActivity).getFragmentController().changeScreenFragment(brushFragmentCreateFragment, true, true);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_MUSIC_HOME).withInteractionType(InteractionType.TAP).withPageType(PageType.BROWSE_HOME).withEventTime(System.currentTimeMillis()).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: podcastIngressOnClickListener$lambda-1, reason: not valid java name */
    public static final void m133podcastIngressOnClickListener$lambda1(BauhausActionBarView this$0, View view) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String strPodcastHomeForBrowseHome = Deeplinks.podcastHomeForBrowseHome();
        this$0.handleDeepLink(strPodcastHomeForBrowseHome);
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.GO_PODCASTS_HOME).withInteractionType(InteractionType.TAP).withPageType(PageType.BROWSE_HOME).withUrl(strPodcastHomeForBrowseHome).withEventTime(System.currentTimeMillis()).build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: profileBarRightImageOnClickListener$lambda-2, reason: not valid java name */
    public static final void m134profileBarRightImageOnClickListener$lambda2(BauhausActionBarView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        IdentityActivityHelper.Companion companion = IdentityActivityHelper.INSTANCE;
        Context context = this$0.getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (companion.isProfileSelected(context)) {
            Context context2 = this$0.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            this$0.getContext().startActivity(companion.getIntentToProfileSetting(context2));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m131_init_$lambda3(BauhausActionBarView this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this$0.updateStyle(styleSheet);
        this$0.cacheArtworkProvider = new DefaultCachedArtworkProvider(this$0.activity, styleSheet);
    }

    public final void setContextMenuOverflowOnClickListener(View.OnClickListener contextMenuOverflowButtonClickListener) {
        Intrinsics.checkNotNullParameter(contextMenuOverflowButtonClickListener, "contextMenuOverflowButtonClickListener");
        this.contextMenuOverflowButton.setOnClickListener(contextMenuOverflowButtonClickListener);
    }

    public final void showAlbumImage(String uri) {
        Intrinsics.checkNotNullParameter(uri, "uri");
        if (!Intrinsics.areEqual(uri, this.artworkUri)) {
            this.imageLoader.loadImage(uri, this.albumImageView.getImage(), R.drawable.placeholder_sm);
            this.artworkUri = uri;
        }
        this.albumImageView.setVisibility(0);
        this.centerTextContainer.setVisibility(8);
    }

    public final void hideAlbumImage() {
        this.albumImageView.setVisibility(8);
        this.centerTextContainer.setVisibility(0);
    }

    public final void showCachedImage(boolean shouldShow, ContentMetadata metadata) {
        CachedArtworkProvider cachedArtworkProvider;
        if (shouldShow && metadata != null && (cachedArtworkProvider = this.cacheArtworkProvider) != null) {
            cachedArtworkProvider.loadCachedArtwork(this.albumImageView.getImage(), metadata);
            this.albumImageView.setVisibility(0);
            this.centerTextContainer.setVisibility(8);
        } else {
            this.albumImageView.setVisibility(8);
            this.centerTextContainer.setVisibility(0);
        }
    }

    public final void showContextMenuOverflowButton(boolean isShown) {
        if (Intrinsics.areEqual(this.showOverflowButton, Boolean.TRUE)) {
            return;
        }
        this.actionButtonOverflow.setVisibility(8);
        this.contextMenuOverflowButton.setVisibility(isShown ? 0 : 8);
    }

    public final void showProfileIcon() {
        IdentityActivityHelper.Companion companion = IdentityActivityHelper.INSTANCE;
        Context context = getContext();
        Intrinsics.checkNotNullExpressionValue(context, "context");
        if (companion.isProfileSelected(context)) {
            MockProfileManager mockProfileManager = MockProfileManagerSingleton.INSTANCE.get();
            ProfileBaseModel profileBaseModelM2127getCurrentProfile = mockProfileManager == null ? null : mockProfileManager.m2127getCurrentProfile();
            ViewUtil viewUtil = ViewUtil.INSTANCE;
            Context context2 = getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "context");
            viewUtil.setImageViewRound(context2, this.profileBarRightImage.getImage(), profileBaseModelM2127getCurrentProfile == null ? null : profileBaseModelM2127getCurrentProfile.getAvatarUrl(), (16 & 8) != 0 ? null : profileBaseModelM2127getCurrentProfile != null ? profileBaseModelM2127getCurrentProfile.getBitmap() : null, (16 & 16) != 0 ? null : null);
            this.profileBarRightImage.setImageSize(ImageSize.INSTANCE.getRoundImageSize((int) getResources().getDimension(R.dimen.spacing_28)));
            this.actionButtonOverflow.setVisibility(8);
            this.profileBarRightImage.setVisibility(0);
        }
    }

    public final void showIngressButtons() {
        this.centerTextContainer.setVisibility(8);
        this.musicIngressButton.setText(getResources().getString(R.string.dmusic_music_ingress_pill_text));
        this.podcastIngressButton.setText(getResources().getString(R.string.dmusic_podcast_ingress_pill_text));
        this.musicIngressButton.setVisibility(0);
        this.podcastIngressButton.setVisibility(0);
    }

    public final void showToggleView() {
        this.centerTextContainer.setVisibility(8);
        ((LinearLayout) findViewById(R.id.ingress_button_container)).setVisibility(8);
        ((ConstraintLayout) findViewById(R.id.toggle_view_container)).setVisibility(0);
    }

    public final void handleDeepLink(String link) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        DeeplinksManager.get(AmazonApplication.getContext()).handleInternal(Uri.parse(link));
    }

    public final void hideActionBar() {
        this.constraintLayout.setVisibility(8);
    }

    public final void showTitle() {
        this.albumImageView.setVisibility(8);
        this.centerTextContainer.setVisibility(0);
    }

    public final void hideTitle() {
        this.albumImageView.setVisibility(0);
        this.centerTextContainer.setVisibility(8);
    }

    public final void showActionBar() {
        this.constraintLayout.setVisibility(0);
    }

    private final boolean titleChanged(String origTitle, String newTitle) {
        if (origTitle == null || newTitle == null) {
            if (origTitle == newTitle) {
                return false;
            }
        } else if (StringsKt__StringsJVMKt.equals(newTitle, origTitle, true)) {
            return false;
        }
        return true;
    }

    public final void addTopMarginToToolBar() {
        Resources.Theme theme;
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(this.displayMetrics.widthPixels, -2);
        TypedValue typedValue = new TypedValue();
        FragmentActivity fragmentActivity = this.activity;
        Boolean boolValueOf = null;
        if (fragmentActivity != null && (theme = fragmentActivity.getTheme()) != null) {
            boolValueOf = Boolean.valueOf(theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true));
        }
        if (boolValueOf != null && boolValueOf.booleanValue() && this.findSearchBarView != null) {
            layoutParams.setMargins(0, (TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics()) - ((int) getResources().getDimension(R.dimen.spacer_48))) / 2, 0, 0);
        }
        setLayoutParams(layoutParams);
    }

    private final void setupActionBar(Boolean showProfileButton) {
        setLayoutParams(new Toolbar.LayoutParams(this.displayMetrics.widthPixels, -2));
        this.constraintLayout.setLayoutParams(new LinearLayout.LayoutParams(this.displayMetrics.widthPixels, -1));
        if (showProfileButton != null) {
            if (showProfileButton.booleanValue() && AmazonApplication.getCapabilities().isActivityFeedSupported() && AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
                CustomerProfileDetailManager.getInstance().syncCustomerInformation(getContext());
            } else if (showProfileButton.booleanValue() && AmazonApplication.getCapabilities().isCustomerProfileSupported()) {
                CustomerProfileDetailManager.getInstance().loadAvatarImage(getContext(), (ImageView) this.actionButtonProfile.findViewById(R.id.Profile));
                this.actionButtonProfile.setVisibility(0);
            }
        }
        this.actionButtonOverflow.setVisibility(Intrinsics.areEqual(this.showOverflowButton, Boolean.TRUE) ? 0 : 8);
        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.actionbar.view.BauhausActionBarView.setupActionBar.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                BauhausActionBarView.this.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                if (BauhausActionBarView.this.getShowOverflowButton() == null || !BauhausActionBarView.this.getShowOverflowButton().booleanValue()) {
                    return;
                }
                BauhausActionBarView.this.setX(0.0f);
            }
        });
    }

    private final void updateStyle(StyleSheet styleSheet) {
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.HEADLINE_4);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(this.titleView, fontStyle);
        }
        FontStyle fontStyle2 = styleSheet.getFontStyle(FontStyleKey.TERTIARY);
        if (fontStyle2 != null) {
            FontUtil.INSTANCE.applyFontStyle(this.subTitleView, fontStyle2);
        }
        this.actionButtonOverflow.initButtonStyle(styleSheet);
        this.castingButton.initStyleSheet(styleSheet);
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
        if (iconBuilder != null) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_navigation_goback);
            Intrinsics.checkNotNullExpressionValue(drawable, "resources.getDrawable(R.…ble.ic_navigation_goback)");
            iconBuilder.withIcon(drawable).applyStyle(this.backButtonView);
            Drawable drawable2 = getResources().getDrawable(R.drawable.action_bar_overflow_open);
            Intrinsics.checkNotNullExpressionValue(drawable2, "resources.getDrawable(R.…action_bar_overflow_open)");
            iconBuilder.withIcon(drawable2).applyStyle(this.contextMenuOverflowButton);
        }
        Integer cornerSize = styleSheet.getCornerSize(CornerSizeKey.SMALL);
        if (cornerSize != null) {
            this.albumImageView.applyCornerRadius(cornerSize.intValue());
        }
        initViewSpacing(styleSheet);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    /* JADX INFO: renamed from: getActionButton, reason: from getter */
    public BaseButton getActionButtonView() {
        return this.actionButtonView;
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    /* JADX INFO: renamed from: getBackButton, reason: from getter */
    public BaseButton getBackButtonView() {
        return this.backButtonView;
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setBackButtonVisible(boolean z) {
        this.backButtonView.setVisibility(z ? 0 : 8);
        this.backButtonView.setClickable(z);
        this.backButtonView.setEnabled(z);
    }

    public final void hideFeedButton() {
        this.feedButton.setVisibility(8);
    }

    /* JADX INFO: renamed from: isButtonBackgroundEnabled, reason: from getter */
    public boolean getEnableButtonBackgrounds() {
        return this.enableButtonBackgrounds;
    }

    @Override // com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator
    public void setButtonBackgroundEnabled(boolean z) {
        if (z != this.enableButtonBackgrounds) {
            this.enableButtonBackgrounds = z;
            resetButtonShadows();
        }
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setFeedButtonVisible(boolean z) {
        this.feedButton.setVisibility(z ? 0 : 8);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setLogoVisible(boolean z) {
        if (z) {
            this.albumImageView.setVisibility(8);
            this.centerTextContainer.setVisibility(0);
            this.titleView.setVisibility(8);
            this.subTitleView.setVisibility(8);
            this.logoView.setVisibility(0);
            return;
        }
        this.titleView.setVisibility(0);
        this.subTitleView.setVisibility(0);
        this.logoView.setVisibility(8);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public View getSearchBar() {
        return this.findSearchBarView;
    }

    /* JADX INFO: renamed from: getTitle, reason: collision with other method in class */
    public String m135getTitle() {
        CharSequence text = this.titleView.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setTitle(String str) {
        setTitles(str, getSubtitle());
    }

    public String getSubtitle() {
        CharSequence text = this.subTitleView.getText();
        if (text == null) {
            return null;
        }
        return text.toString();
    }

    public void setSubtitle(String str) {
        setTitles(m135getTitle(), str);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void addTopMargin() {
        Resources.Theme theme;
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(this.displayMetrics.widthPixels, -2);
        TypedValue typedValue = new TypedValue();
        FragmentActivity fragmentActivity = this.activity;
        Boolean boolValueOf = null;
        if (fragmentActivity != null && (theme = fragmentActivity.getTheme()) != null) {
            boolValueOf = Boolean.valueOf(theme.resolveAttribute(android.R.attr.actionBarSize, typedValue, true));
        }
        if (boolValueOf != null && boolValueOf.booleanValue() && this.findSearchBarView != null) {
            layoutParams.setMargins(0, (TypedValue.complexToDimensionPixelSize(typedValue.data, getResources().getDisplayMetrics()) - ((int) getResources().getDimension(R.dimen.spacer_48))) / 2, 0, 0);
        }
        setLayoutParams(layoutParams);
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Companion companion = INSTANCE;
        Resources resources = getContext().getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "context.resources");
        this.actionBarHeight = companion.getActionBarHeight(resources);
    }

    private final void resetButtonShadows() {
        if (!getEnableButtonBackgrounds()) {
            this.backButtonView.setBackground(null);
            this.actionButtonView.setBackground(null);
        } else if (getEnableButtonBackgrounds()) {
            this.backButtonView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.action_bar_button_shadow));
            this.actionButtonView.setBackground(ContextCompat.getDrawable(getContext(), R.drawable.action_bar_button_shadow));
        }
    }

    static /* synthetic */ void setViewMargins$default(BauhausActionBarView bauhausActionBarView, View view, Integer num, Integer num2, Integer num3, Integer num4, Integer num5, Integer num6, int i, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: setViewMargins");
        }
        bauhausActionBarView.setViewMargins(view, (i & 2) != 0 ? null : num, (i & 4) != 0 ? null : num2, (i & 8) != 0 ? null : num3, (i & 16) != 0 ? null : num4, (i & 32) != 0 ? null : num5, (i & 64) == 0 ? num6 : null);
    }

    private final void setViewMargins(View view, Integer width, Integer height, Integer left, Integer top, Integer right, Integer bottom) {
        LayoutParamUtils.INSTANCE.setLayoutMargins(view, width == null ? -2 : width.intValue(), height != null ? height.intValue() : -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : Integer.valueOf(left == null ? 0 : left.intValue()), (240 & 32) != 0 ? null : Integer.valueOf(top == null ? 0 : top.intValue()), (240 & 64) != 0 ? null : Integer.valueOf(right == null ? 0 : right.intValue()), (240 & 128) != 0 ? null : Integer.valueOf(bottom != null ? bottom.intValue() : 0));
    }

    private final void initViewSpacing(StyleSheet styleSheet) {
        Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MICRO);
        int iIntValue = spacerInPixels == null ? 0 : spacerInPixels.intValue();
        Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.SMALL);
        int iIntValue2 = spacerInPixels2 != null ? spacerInPixels2.intValue() : 0;
        ScreenSizeKey widthScreenSizeKey = styleSheet.getWidthScreenSizeKey();
        ScreenSizeKey screenSizeKey = ScreenSizeKey.XSMALL;
        if (widthScreenSizeKey == screenSizeKey) {
            iIntValue2 = iIntValue;
        }
        ConstraintLayout constraintLayout = this.constraintLayout;
        constraintLayout.setPadding(iIntValue2, constraintLayout.getPaddingTop(), iIntValue2, constraintLayout.getPaddingBottom());
        setViewMargins$default(this, this.leftButtonContainer, null, null, null, null, Integer.valueOf(iIntValue), null, 94, null);
        setViewMargins$default(this, this.castingButton, null, null, null, null, Integer.valueOf(iIntValue), null, 94, null);
        setViewMargins$default(this, this.actionButtonProfile, null, null, null, null, Integer.valueOf(iIntValue), null, 94, null);
        ButtonSize iconSize = styleSheet.getIconSize(IconSizeKey.MEDIUM);
        if (iconSize != null) {
            setViewMargins$default(this, this.rightButtonContainer, iconSize.getWidth(), iconSize.getHeight(), Integer.valueOf(iIntValue), null, null, null, 112, null);
        }
        if (widthScreenSizeKey == screenSizeKey) {
            setViewMargins$default(this, this.titleView, null, null, null, Integer.valueOf(iIntValue), null, null, 110, null);
            setViewMargins$default(this, this.subTitleView, null, null, null, null, null, Integer.valueOf(iIntValue), 62, null);
        }
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setSearchBarMargins(Integer start, Integer top, Integer end, Integer bottom) {
        FindSearchBarView findSearchBarView = this.findSearchBarView;
        if (findSearchBarView == null) {
            return;
        }
        LayoutParamUtils.INSTANCE.setLayoutMargins(findSearchBarView, -1, -2, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : start, (240 & 32) != 0 ? null : top, (240 & 64) != 0 ? null : end, (240 & 128) != 0 ? null : bottom);
    }

    /* JADX INFO: renamed from: getTitle, reason: from getter */
    public final TextView getTitleView() {
        return this.titleView;
    }

    public void setTitles(String title, String subtitle) {
        CharSequence text = this.titleView.getText();
        String string = text == null ? null : text.toString();
        boolean z = true;
        if (title == null || title.length() == 0) {
            this.titleView.setVisibility(8);
        } else {
            this.titleView.setText(title);
            this.titleView.setVisibility(0);
        }
        if (subtitle != null && subtitle.length() != 0) {
            z = false;
        }
        if (z) {
            this.titleView.setGravity(16);
            this.subTitleView.setVisibility(8);
        } else {
            this.titleView.setGravity(0);
            this.subTitleView.setVisibility(0);
            this.subTitleView.setText(subtitle);
        }
        if (AccessibilityUtil.isAccessibilityEnabled(getContext()) && titleChanged(string, title)) {
            this.titleView.sendAccessibilityEvent(8);
        }
    }

    public final RecyclerView getHeaderNavigationView() {
        return this.headerNavigationView;
    }

    public final void showHeaderNavigationView(boolean shouldShow) {
        this.headerNavigationView.setVisibility(shouldShow ? 0 : 8);
        if (shouldShow) {
            this.constraintLayout.setPadding(0, getPaddingTop(), 0, getPaddingBottom());
        }
    }

    public final boolean isHeaderNavigationViewShown() {
        return this.headerNavigationView.getVisibility() == 0;
    }

    public final void setToolBarBackgroundColor(Integer colorId) {
        Integer numValueOf;
        int iIntValue;
        Integer numValueOf2 = null;
        if (colorId == null) {
            numValueOf = null;
        } else {
            colorId.intValue();
            numValueOf = Integer.valueOf(getResources().getColor(colorId.intValue()));
        }
        if (numValueOf == null) {
            Resources.Theme theme = getContext().getTheme();
            if (theme != null) {
                TypedArray typedArrayObtainStyledAttributes = theme.obtainStyledAttributes(new int[]{android.R.attr.colorPrimaryDark});
                Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "it.obtainStyledAttribute…R.attr.colorPrimaryDark))");
                numValueOf2 = Integer.valueOf(typedArrayObtainStyledAttributes.getColor(0, 0));
            }
            if (numValueOf2 != null) {
                iIntValue = numValueOf2.intValue();
            } else {
                iIntValue = getResources().getColor(R.color.background_dark_blue);
            }
        } else {
            iIntValue = numValueOf.intValue();
        }
        Toolbar toolbar = this.toolbar;
        if (toolbar == null) {
            return;
        }
        toolbar.setBackgroundColor(iIntValue);
    }

    @Override // com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator
    public void updateBackgroundOpacity(RecyclerView recyclerView) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        LinearLayoutManager linearLayoutManager = layoutManager instanceof LinearLayoutManager ? (LinearLayoutManager) layoutManager : null;
        Integer numValueOf = linearLayoutManager != null ? Integer.valueOf(linearLayoutManager.findFirstVisibleItemPosition()) : null;
        float fMin = isHeaderNavigationViewShown() ? 1.0f : 0.85f;
        if (numValueOf == null || numValueOf.intValue() <= 0) {
            fMin = ((fMin - 0.0f) * (Math.min(this.actionBarHeight, recyclerView.computeVerticalScrollOffset() - this.actionBarFadeOffset) / this.actionBarHeight)) + 0.0f;
        }
        setBackgroundOpacity(fMin);
    }

    @Override // com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator
    public float getBackgroundOpacity() {
        Drawable background;
        Toolbar toolbar = this.toolbar;
        return ((toolbar == null || (background = toolbar.getBackground()) == null) ? 255.0f : background.getAlpha()) / 255.0f;
    }

    @Override // com.amazon.music.views.library.views.presentation.TranslucentActionBarDecorator
    public void setBackgroundOpacity(float f) {
        int iCoerceIn = (int) (RangesKt___RangesKt.coerceIn(f, 0.0f, 1.0f) * 255.0f);
        Toolbar toolbar = this.toolbar;
        Drawable background = toolbar == null ? null : toolbar.getBackground();
        if (background != null) {
            background.setAlpha(iCoerceIn);
        }
        Drawable background2 = this.backButtonView.getBackground();
        if (background2 != null) {
            background2.setAlpha(255 - iCoerceIn);
        }
        Drawable background3 = this.actionButtonView.getBackground();
        if (background3 == null) {
            return;
        }
        background3.setAlpha(255 - iCoerceIn);
    }

    @Override // com.amazon.music.views.library.views.presentation.ActionBarDecorator
    public void setActionBarVisible(boolean z) {
        this.constraintLayout.setVisibility(z ? 0 : 8);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Drawable background;
        super.onDetachedFromWindow();
        CastingButtonHelper castingButtonHelper = this.castingButtonHelper;
        if (castingButtonHelper != null) {
            castingButtonHelper.unregisterListeners();
        }
        Toolbar toolbar = this.toolbar;
        Float fValueOf = null;
        if (toolbar != null && (background = toolbar.getBackground()) != null) {
            fValueOf = Float.valueOf(background.getAlpha() / 255.0f);
        }
        this.lastOpacity = fValueOf;
        setBackgroundOpacity(0.0f);
        Subscription subscription = this.subscription;
        if (subscription == null) {
            return;
        }
        subscription.unsubscribe();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Float f = this.lastOpacity;
        if (f == null) {
            return;
        }
        setBackgroundOpacity(f.floatValue());
        this.lastOpacity = null;
    }

    protected final void addViewBelowActionBar(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setLayoutTransition(null);
        linearLayout.setLayoutParams(layoutParams);
        view.setLayoutParams(layoutParams);
        linearLayout.addView(view);
        addView(linearLayout);
    }

    /* JADX INFO: renamed from: getActionOverflowButton, reason: from getter */
    public final ActionBarRightButton getActionButtonOverflow() {
        return this.actionButtonOverflow;
    }

    public final void setFindUpsellButton(StyleSheet styleSheet, View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        ButtonStyle buttonStyle = styleSheet.getButtonStyle(ButtonStyleKey.SOLID);
        ButtonSize buttonSize = styleSheet.getButtonSize(ButtonSizeKey.MEDIUM_WITH_ICON);
        Integer cornerSize = styleSheet.getCornerSize(CornerSizeKey.ROUND);
        if (buttonStyle == null || buttonSize == null || cornerSize == null) {
            return;
        }
        BaseButton.StyleBuilder styleBuilderWithStyle = new BaseButton.StyleBuilder().withSize(buttonSize).withStyle(buttonStyle);
        String string = getContext().getString(R.string.find_upsell_button_text);
        Intrinsics.checkNotNullExpressionValue(string, "context.getString(FindR.….find_upsell_button_text)");
        BaseButton.StyleBuilder styleBuilderWithText = styleBuilderWithStyle.withText(string);
        Drawable drawable = getContext().getResources().getDrawable(R.drawable.ic_star_cluster);
        Intrinsics.checkNotNullExpressionValue(drawable, "context.resources.getDra…drawable.ic_star_cluster)");
        BaseButton.StyleBuilder styleBuilderWithCornerRadius = styleBuilderWithText.withIcon(drawable).withCornerRadius(cornerSize.intValue());
        BaseButton findUpsellButton = this.findUpsellButton;
        Intrinsics.checkNotNullExpressionValue(findUpsellButton, "findUpsellButton");
        styleBuilderWithCornerRadius.applyStyle(findUpsellButton);
        this.findUpsellButton.setOnClickListener(onClickListener);
        this.findUpsellButtonLayout.setVisibility(0);
        MetricsLogger.emitUIContentViewMetric(ContentName.UPSELL_BUTTON, ContainerType.UPSELL_BUTTON);
    }

    public final void hideFindUpsellButton() {
        this.findUpsellButton.setVisibility(8);
    }

    public final void showFindUpsellButton() {
        this.findUpsellButton.setVisibility(0);
    }

    public final boolean isFindUpsellButtonLayoutVisible() {
        return this.findUpsellButtonLayout.getVisibility() == 0;
    }

    public void clearSubscriptions() {
        Subscription subscription = this.subscription;
        if (subscription == null || subscription.isUnsubscribed()) {
            return;
        }
        subscription.unsubscribe();
    }

    /* JADX INFO: compiled from: BauhausActionBarView.kt */
    @Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/amazon/mp3/actionbar/view/BauhausActionBarView$ContentOffsetItemDecoration;", "Landroidx/recyclerview/widget/RecyclerView$ItemDecoration;", "topPadding", "", "(I)V", "getItemOffsets", "", "outRect", "Landroid/graphics/Rect;", "view", "Landroid/view/View;", "parent", "Landroidx/recyclerview/widget/RecyclerView;", "state", "Landroidx/recyclerview/widget/RecyclerView$State;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ContentOffsetItemDecoration extends RecyclerView.ItemDecoration {
        private final int topPadding;

        public ContentOffsetItemDecoration(int i) {
            this.topPadding = i;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            Intrinsics.checkNotNullParameter(outRect, "outRect");
            Intrinsics.checkNotNullParameter(view, "view");
            Intrinsics.checkNotNullParameter(parent, "parent");
            Intrinsics.checkNotNullParameter(state, "state");
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(0, parent.getChildAdapterPosition(view) == 0 ? this.topPadding : 0, 0, 0);
        }
    }

    private final boolean isBauhausTopAppBarEnabled() {
        return AmazonApplication.getCapabilities().isBauhausTopAppBarEnabled();
    }

    public final void updateActionBarOverlayState(boolean isOverlay) {
        KeyEventDispatcher.Component component = this.activity;
        ActionBarOverlayable actionBarOverlayable = component instanceof ActionBarOverlayable ? (ActionBarOverlayable) component : null;
        if (actionBarOverlayable == null) {
            return;
        }
        actionBarOverlayable.setActionBarOverlay(isOverlay & isBauhausTopAppBarEnabled());
    }
}
