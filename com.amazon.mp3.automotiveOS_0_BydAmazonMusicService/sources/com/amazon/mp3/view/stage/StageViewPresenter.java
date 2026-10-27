package com.amazon.mp3.view.stage;

import Touch.SwipeablePagesTemplateInterface.v1_0.ImpressionEndReason;
import Touch.WidgetsInterface.v1_0.PillNavigatorWidgetItemElement;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableKt;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.activity.navigation.FragmentController;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.casting.CastingBaseButton;
import com.amazon.mp3.casting.CastingFragment;
import com.amazon.mp3.catalog.fragment.PlayQueueFragment;
import com.amazon.mp3.cloudqueue.FreeTierPlaybackUtil;
import com.amazon.mp3.cloudqueue.metrics.CloudQueueUIMetricsManager;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.fragment.contextmenu.shoveler.ShovelerExtensionFunctionsKt;
import com.amazon.mp3.library.fragment.LyricsFragment;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.activity.NowPlayingUtil;
import com.amazon.mp3.playback.fragment.NowPlayingFragment;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.PlayQueueButton;
import com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer;
import com.amazon.mp3.skiplimit.impl.SkipLimitProviderImpl;
import com.amazon.mp3.util.ConnectivityUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.mp3.util.extensions.MediaItemExtensions;
import com.amazon.mp3.util.extensions.ViewExtensions;
import com.amazon.mp3.view.BauhausSeekBarWidget;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.mp3.view.lyrics.sharing.data.LyricsSharingDataModel;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingDataRepository;
import com.amazon.mp3.view.lyrics.sharing.utils.LyricsSharingMetricsSender;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsCardSelectionFragment;
import com.amazon.mp3.view.lyrics.sharing.view.LyricsSelectionFragment;
import com.amazon.mp3.view.player.EnabledViewState;
import com.amazon.mp3.view.player.PlayerMetadataView;
import com.amazon.mp3.view.player.PlayerViewAction;
import com.amazon.mp3.view.player.PlayerViewEnabledStateManager;
import com.amazon.mp3.view.stage.StageDataModel;
import com.amazon.mp3.view.stage.StageLyrics;
import com.amazon.mp3.view.stage.StageViewPresenter;
import com.amazon.mp3.view.stage.StageXRayManager;
import com.amazon.mp3.view.stage.extensions.ToolbarKt;
import com.amazon.mp3.view.stage.overlay.OverlayContainer;
import com.amazon.mp3.view.stage.overlay.OverlayLyricsStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayPosition;
import com.amazon.mp3.view.stage.overlay.OverlaySelectionType;
import com.amazon.mp3.view.stage.overlay.OverlayType;
import com.amazon.mp3.view.stage.overlay.OverlayTypeStateProvider;
import com.amazon.mp3.view.stage.overlay.OverlayViewType;
import com.amazon.mp3.view.stage.overlay.OverlayXrayStateProvider;
import com.amazon.mp3.view.stage.util.StageLyricsUtil;
import com.amazon.music.castingviews.CastingUIMetricsLogger;
import com.amazon.music.explore.fragment.StageSwipeablePagesFragment;
import com.amazon.music.explore.views.swipeablePages.StageSwipeablePagesView;
import com.amazon.music.explore.widgets.views.PillNavigatorWidgetItemView;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.util.AndroidUtils;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.ui.foundations.views.BaseImage;
import com.facebook.react.modules.appstate.AppStateModule;
import com.google.firebase.perf.util.Constants;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.Unit;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import rx.Observable;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func3;
import rx.schedulers.Schedulers;
import rx.subjects.BehaviorSubject;
import rx.subjects.PublishSubject;
import rx.subscriptions.CompositeSubscription;

/* JADX INFO: compiled from: StageViewPresenter.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000\u009c\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010 \n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\b\t*\u0004Î\u0001È\u0003\u0018\u00002\u00020\u0001:\u0002Ï\u0003B5\u0012\u000e\u0010«\u0001\u001a\t\u0012\u0004\u0012\u00020\r0ª\u0001\u0012\u000e\u0010Ì\u0003\u001a\t\u0012\u0004\u0012\u00020{0ª\u0001\u0012\n\u0010®\u0001\u001a\u0005\u0018\u00010\u00ad\u0001¢\u0006\u0006\bÍ\u0003\u0010Î\u0003J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0005H\u0002J \u0010\f\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\tH\u0002J \u0010\u0010\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\tH\u0002J\b\u0010\u0011\u001a\u00020\u0002H\u0002J\b\u0010\u0012\u001a\u00020\u0002H\u0002J\b\u0010\u0013\u001a\u00020\u0002H\u0002J\b\u0010\u0014\u001a\u00020\u0002H\u0002J\b\u0010\u0015\u001a\u00020\u0002H\u0002J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\b\u0010\u0018\u001a\u00020\u0002H\u0002J\b\u0010\u0019\u001a\u00020\u0002H\u0002J\b\u0010\u001b\u001a\u00020\u001aH\u0002J\b\u0010\u001c\u001a\u00020\u0002H\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0002J\b\u0010\u001e\u001a\u00020\u0002H\u0002J\b\u0010\u001f\u001a\u00020\u0002H\u0002J\u0010\u0010\"\u001a\u00020\u00022\u0006\u0010!\u001a\u00020 H\u0002J\u0010\u0010%\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0002J\b\u0010&\u001a\u00020\u0002H\u0002J\b\u0010'\u001a\u00020\u0002H\u0002J\u0018\u0010)\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u001aH\u0002J\b\u0010*\u001a\u00020\u001aH\u0002J\u001a\u0010,\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001a2\b\b\u0002\u0010+\u001a\u00020\u001aH\u0002J\b\u0010-\u001a\u00020\u0002H\u0002J\"\u00101\u001a\u00020\u00022\b\b\u0002\u0010.\u001a\u00020\t2\u0006\u0010/\u001a\u00020\u001a2\u0006\u00100\u001a\u00020\u001aH\u0002J\u0010\u00102\u001a\u00020\u00022\u0006\u0010.\u001a\u00020\tH\u0002J\u0010\u00104\u001a\u00020\u00022\u0006\u00103\u001a\u00020\rH\u0002J\u0018\u00107\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001a2\u0006\u00106\u001a\u000205H\u0002J\"\u0010;\u001a\u00020\u00022\u0018\u0010:\u001a\u0014\u0012\u0004\u0012\u000209\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u001a08H\u0002J\u001c\u0010>\u001a\u00020\u00022\u0012\u0010=\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0<H\u0002J\u0010\u0010?\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\rH\u0002J\u0010\u0010A\u001a\u00020\u00022\u0006\u0010@\u001a\u00020\u001aH\u0002J\u0018\u0010D\u001a\u00020\u00022\u0006\u0010C\u001a\u00020B2\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u0010\u0010E\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0005H\u0002J\u001c\u0010G\u001a\u00020\u00022\b\b\u0002\u0010+\u001a\u00020\u001a2\b\b\u0002\u0010F\u001a\u00020\u001aH\u0002J\u0010\u0010H\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0002J\u0010\u0010I\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#H\u0002J\n\u0010K\u001a\u0004\u0018\u00010JH\u0002J\n\u0010L\u001a\u0004\u0018\u00010\rH\u0002J\b\u0010M\u001a\u00020\u0002H\u0002J\u0010\u0010P\u001a\u00020\u00022\u0006\u0010O\u001a\u00020NH\u0002J\b\u0010Q\u001a\u00020\u0002H\u0002J\b\u0010R\u001a\u00020\u0002H\u0002J\b\u0010S\u001a\u00020\u0002H\u0002J\n\u0010T\u001a\u0004\u0018\u00010BH\u0002J\b\u0010U\u001a\u00020\u0005H\u0002J\u0010\u0010W\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0VH\u0002J\b\u0010X\u001a\u00020\u0002H\u0002J\b\u0010Y\u001a\u00020\u0002H\u0002J\b\u0010Z\u001a\u00020\u0002H\u0002J\b\u0010[\u001a\u00020\u0002H\u0002J\u0010\u0010]\u001a\u00020\u00022\u0006\u0010\\\u001a\u00020\tH\u0002J\b\u0010^\u001a\u00020\u0002H\u0002J\b\u0010_\u001a\u00020\u0002H\u0002J\b\u0010`\u001a\u00020\u001aH\u0002J\u000e\u0010a\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#J\u000e\u0010b\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#J\u000e\u0010c\u001a\u00020\u00022\u0006\u0010$\u001a\u00020#J\u0006\u0010d\u001a\u00020\u0002J\b\u0010e\u001a\u00020\u0002H\u0007J\u000e\u0010f\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001aJ&\u0010l\u001a\u00020\u00022\u0006\u0010g\u001a\u00020\u001a2\n\b\u0002\u0010i\u001a\u0004\u0018\u00010h2\n\b\u0002\u0010k\u001a\u0004\u0018\u00010jJ\u0006\u0010m\u001a\u00020\u0002J\u0006\u0010n\u001a\u00020\u0002J\b\u0010o\u001a\u00020\u0002H\u0007J\u0006\u0010p\u001a\u00020\u0002J\u0006\u0010q\u001a\u00020\u0002J\u0006\u0010r\u001a\u00020\u0002J\u000e\u0010u\u001a\u00020\u00022\u0006\u0010t\u001a\u00020sJ\u0006\u0010v\u001a\u00020\u0002J\u0006\u0010w\u001a\u00020\u0002J\u000e\u0010y\u001a\u00020\u00022\u0006\u0010x\u001a\u00020\u001aJ\u0006\u0010z\u001a\u00020\u0002J\u0016\u0010\u007f\u001a\u00020\u00022\u0006\u0010|\u001a\u00020{2\u0006\u0010~\u001a\u00020}J\u0011\u0010\u0082\u0001\u001a\u00020\u00022\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001J\u000f\u0010\u0083\u0001\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001aJ\u0011\u0010\u0084\u0001\u001a\u00020\u00022\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001J\u0010\u0010\u0086\u0001\u001a\u00020\u00022\u0007\u0010\u0085\u0001\u001a\u00020\u001aJ\u0007\u0010\u0087\u0001\u001a\u00020\u001aJ\u0010\u0010\u0089\u0001\u001a\u00020\u00022\u0007\u0010\u0088\u0001\u001a\u00020\u001aJ\u000f\u0010\u008a\u0001\u001a\u00020\u00022\u0006\u0010(\u001a\u00020\u001aJ\u0007\u0010\u008b\u0001\u001a\u00020\u0002J\u0007\u0010\u008c\u0001\u001a\u00020\u0002J\u001f\u0010\u0090\u0001\u001a\u00020\u00022\n\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008d\u00012\n\u0010\u008f\u0001\u001a\u0005\u0018\u00010\u008d\u0001J\u0011\u0010\u0091\u0001\u001a\u00020\u00022\b\u0010\u0081\u0001\u001a\u00030\u0080\u0001J\u0007\u0010\u0092\u0001\u001a\u00020\u0002J1\u0010\u0095\u0001\u001a\u00020\u00022\u0006\u0010C\u001a\u00020B2\b\b\u0002\u0010R\u001a\u00020\u001a2\t\b\u0002\u0010\u0093\u0001\u001a\u00020\u001a2\t\b\u0002\u0010\u0094\u0001\u001a\u00020\u001aH\u0007J\u0007\u0010\u0096\u0001\u001a\u00020\u0002J\u0007\u0010\u0097\u0001\u001a\u00020\u001aJ\u0007\u0010\u0098\u0001\u001a\u00020\u001aJ\u0012\u0010\u009c\u0001\u001a\u00030\u009b\u00012\b\u0010\u009a\u0001\u001a\u00030\u0099\u0001J\u001e\u0010 \u0001\u001a\u0005\u0018\u00010\u009b\u00012\b\u0010\u009d\u0001\u001a\u00030\u009b\u00012\b\u0010\u009f\u0001\u001a\u00030\u009e\u0001J\u0007\u0010¡\u0001\u001a\u00020\u0002J\u0007\u0010¢\u0001\u001a\u00020\u0002J\u0013\u0010¥\u0001\u001a\u00020\u00022\n\u0010¤\u0001\u001a\u0005\u0018\u00010£\u0001J\u0007\u0010¦\u0001\u001a\u00020\u0002J\u0011\u0010©\u0001\u001a\u00020\u00022\b\u0010¨\u0001\u001a\u00030§\u0001R\u001e\u0010«\u0001\u001a\t\u0012\u0004\u0012\u00020\r0ª\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b«\u0001\u0010¬\u0001R,\u0010®\u0001\u001a\u0005\u0018\u00010\u00ad\u00018\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b®\u0001\u0010¯\u0001\u001a\u0006\b°\u0001\u0010±\u0001\"\u0006\b²\u0001\u0010³\u0001R\u001c\u0010µ\u0001\u001a\u0005\u0018\u00010´\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bµ\u0001\u0010¶\u0001R\"\u0010¸\u0001\u001a\r ·\u0001*\u0005\u0018\u00010\u008d\u00010\u008d\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¸\u0001\u0010¹\u0001R#\u0010»\u0001\u001a\t\u0012\u0004\u0012\u00020B0º\u00018\u0006¢\u0006\u0010\n\u0006\b»\u0001\u0010¼\u0001\u001a\u0006\b½\u0001\u0010¾\u0001R+\u0010¿\u0001\u001a\u0004\u0018\u00010B8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\b¿\u0001\u0010À\u0001\u001a\u0006\bÁ\u0001\u0010Â\u0001\"\u0006\bÃ\u0001\u0010Ä\u0001R#\u0010Ê\u0001\u001a\u0005\u0018\u00010Å\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÆ\u0001\u0010Ç\u0001\u001a\u0006\bÈ\u0001\u0010É\u0001R\u0019\u0010Ë\u0001\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bË\u0001\u0010Ì\u0001R\u001c\u0010Í\u0001\u001a\u0005\u0018\u00010´\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÍ\u0001\u0010¶\u0001R\u0018\u0010Ï\u0001\u001a\u00030Î\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÏ\u0001\u0010Ð\u0001R#\u0010Õ\u0001\u001a\u0005\u0018\u00010Ñ\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÒ\u0001\u0010Ç\u0001\u001a\u0006\bÓ\u0001\u0010Ô\u0001R#\u0010Ú\u0001\u001a\u0005\u0018\u00010Ö\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b×\u0001\u0010Ç\u0001\u001a\u0006\bØ\u0001\u0010Ù\u0001R#\u0010ß\u0001\u001a\u0005\u0018\u00010Û\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÜ\u0001\u0010Ç\u0001\u001a\u0006\bÝ\u0001\u0010Þ\u0001R\"\u0010ã\u0001\u001a\u0004\u0018\u00010\u00078FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\bà\u0001\u0010Ç\u0001\u001a\u0006\bá\u0001\u0010â\u0001R\"\u0010ç\u0001\u001a\u0004\u0018\u00010h8BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bä\u0001\u0010Ç\u0001\u001a\u0006\bå\u0001\u0010æ\u0001R#\u0010ê\u0001\u001a\u0005\u0018\u00010Û\u00018FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\bè\u0001\u0010Ç\u0001\u001a\u0006\bé\u0001\u0010Þ\u0001R#\u0010ï\u0001\u001a\u0005\u0018\u00010ë\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bì\u0001\u0010Ç\u0001\u001a\u0006\bí\u0001\u0010î\u0001R#\u0010ò\u0001\u001a\u0005\u0018\u00010Û\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bð\u0001\u0010Ç\u0001\u001a\u0006\bñ\u0001\u0010Þ\u0001R\"\u0010õ\u0001\u001a\u0004\u0018\u00010\u00078BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bó\u0001\u0010Ç\u0001\u001a\u0006\bô\u0001\u0010â\u0001R#\u0010ú\u0001\u001a\u0005\u0018\u00010ö\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b÷\u0001\u0010Ç\u0001\u001a\u0006\bø\u0001\u0010ù\u0001R#\u0010ÿ\u0001\u001a\u0005\u0018\u00010û\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bü\u0001\u0010Ç\u0001\u001a\u0006\bý\u0001\u0010þ\u0001R\u001a\u0010\u0080\u0002\u001a\u0005\u0018\u00010û\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0080\u0002\u0010\u0081\u0002R#\u0010\u0086\u0002\u001a\u0005\u0018\u00010\u0082\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0083\u0002\u0010Ç\u0001\u001a\u0006\b\u0084\u0002\u0010\u0085\u0002R#\u0010\u008b\u0002\u001a\u0005\u0018\u00010\u0087\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0088\u0002\u0010Ç\u0001\u001a\u0006\b\u0089\u0002\u0010\u008a\u0002R.\u0010\u008d\u0002\u001a\u0004\u0018\u00010j2\t\u0010\u008c\u0002\u001a\u0004\u0018\u00010j8\u0002@BX\u0082\u000e¢\u0006\u0010\n\u0006\b\u008d\u0002\u0010\u008e\u0002\"\u0006\b\u008f\u0002\u0010\u0090\u0002R\u001c\u0010\u0091\u0002\u001a\u0005\u0018\u00010\u0087\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0091\u0002\u0010\u0092\u0002R\u001c\u0010\u0093\u0002\u001a\u0005\u0018\u00010\u0087\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0093\u0002\u0010\u0092\u0002R\u001c\u0010\u0094\u0002\u001a\u0005\u0018\u00010\u0087\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0094\u0002\u0010\u0092\u0002R#\u0010\u0097\u0002\u001a\u0005\u0018\u00010\u0087\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0095\u0002\u0010Ç\u0001\u001a\u0006\b\u0096\u0002\u0010\u008a\u0002R#\u0010\u009a\u0002\u001a\u0005\u0018\u00010\u0087\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0098\u0002\u0010Ç\u0001\u001a\u0006\b\u0099\u0002\u0010\u008a\u0002R#\u0010\u009d\u0002\u001a\u0005\u0018\u00010\u0087\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009b\u0002\u0010Ç\u0001\u001a\u0006\b\u009c\u0002\u0010\u008a\u0002R#\u0010 \u0002\u001a\u0005\u0018\u00010\u0087\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009e\u0002\u0010Ç\u0001\u001a\u0006\b\u009f\u0002\u0010\u008a\u0002R#\u0010¥\u0002\u001a\u0005\u0018\u00010¡\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¢\u0002\u0010Ç\u0001\u001a\u0006\b£\u0002\u0010¤\u0002R#\u0010¨\u0002\u001a\u0005\u0018\u00010Û\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¦\u0002\u0010Ç\u0001\u001a\u0006\b§\u0002\u0010Þ\u0001R#\u0010«\u0002\u001a\u0005\u0018\u00010Û\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b©\u0002\u0010Ç\u0001\u001a\u0006\bª\u0002\u0010Þ\u0001R#\u0010°\u0002\u001a\u0005\u0018\u00010¬\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u00ad\u0002\u0010Ç\u0001\u001a\u0006\b®\u0002\u0010¯\u0002R#\u0010³\u0002\u001a\u0005\u0018\u00010¬\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b±\u0002\u0010Ç\u0001\u001a\u0006\b²\u0002\u0010¯\u0002R#\u0010¶\u0002\u001a\u0005\u0018\u00010¬\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b´\u0002\u0010Ç\u0001\u001a\u0006\bµ\u0002\u0010¯\u0002R#\u0010»\u0002\u001a\u0005\u0018\u00010·\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¸\u0002\u0010Ç\u0001\u001a\u0006\b¹\u0002\u0010º\u0002R#\u0010À\u0002\u001a\u0005\u0018\u00010¼\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b½\u0002\u0010Ç\u0001\u001a\u0006\b¾\u0002\u0010¿\u0002R#\u0010Å\u0002\u001a\u0005\u0018\u00010Á\u00028BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÂ\u0002\u0010Ç\u0001\u001a\u0006\bÃ\u0002\u0010Ä\u0002R#\u0010È\u0002\u001a\u0005\u0018\u00010Á\u00028FX\u0086\u0084\u0002¢\u0006\u0010\n\u0006\bÆ\u0002\u0010Ç\u0001\u001a\u0006\bÇ\u0002\u0010Ä\u0002R#\u0010Ê\u0002\u001a\t\u0012\u0004\u0012\u00020\u001a0É\u00028\u0006¢\u0006\u0010\n\u0006\bÊ\u0002\u0010Ë\u0002\u001a\u0006\bÌ\u0002\u0010Í\u0002R$\u0010Î\u0002\u001a\n\u0012\u0005\u0012\u00030\u009b\u00010É\u00028\u0006¢\u0006\u0010\n\u0006\bÎ\u0002\u0010Ë\u0002\u001a\u0006\bÏ\u0002\u0010Í\u0002R#\u0010Ð\u0002\u001a\t\u0012\u0004\u0012\u00020\u00050É\u00028\u0006¢\u0006\u0010\n\u0006\bÐ\u0002\u0010Ë\u0002\u001a\u0006\bÑ\u0002\u0010Í\u0002RE\u0010Ô\u0002\u001a\u001e\u0012\u0019\u0012\u0017\u0012\u0005\u0012\u00030Ó\u0002\u0012\u0005\u0012\u00030Ó\u0002\u0012\u0005\u0012\u00030Ó\u0002080Ò\u00028\u0006@\u0006X\u0086.¢\u0006\u0018\n\u0006\bÔ\u0002\u0010Õ\u0002\u001a\u0006\bÖ\u0002\u0010×\u0002\"\u0006\bØ\u0002\u0010Ù\u0002R\u001e\u0010Ú\u0002\u001a\t\u0012\u0004\u0012\u00020 0É\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÚ\u0002\u0010Ë\u0002R,\u0010Ü\u0002\u001a\u0005\u0018\u00010Û\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bÜ\u0002\u0010Ý\u0002\u001a\u0006\bÞ\u0002\u0010ß\u0002\"\u0006\bà\u0002\u0010á\u0002R\u0017\u0010$\u001a\u00020#8\u0002@\u0002X\u0082.¢\u0006\u0007\n\u0005\b$\u0010â\u0002R*\u0010ä\u0002\u001a\u00030ã\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bä\u0002\u0010å\u0002\u001a\u0006\bæ\u0002\u0010ç\u0002\"\u0006\bè\u0002\u0010é\u0002R)\u0010ê\u0002\u001a\u00020\u001a8\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bê\u0002\u0010Ì\u0001\u001a\u0006\bë\u0002\u0010ì\u0002\"\u0006\bí\u0002\u0010î\u0002R,\u0010ð\u0002\u001a\u0005\u0018\u00010ï\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bð\u0002\u0010ñ\u0002\u001a\u0006\bò\u0002\u0010ó\u0002\"\u0006\bô\u0002\u0010õ\u0002R\u001c\u0010÷\u0002\u001a\u0005\u0018\u00010ö\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b÷\u0002\u0010ø\u0002R\u001f\u0010ú\u0002\u001a\u0005\u0018\u00010ù\u00028\u0006¢\u0006\u0010\n\u0006\bú\u0002\u0010û\u0002\u001a\u0006\bü\u0002\u0010ý\u0002R\u001c\u0010ÿ\u0002\u001a\u0005\u0018\u00010þ\u00028\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÿ\u0002\u0010\u0080\u0003R\u0018\u0010\u0082\u0003\u001a\u00030\u0081\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0003\u0010\u0083\u0003R\u001a\u0010t\u001a\u00020s8\u0006¢\u0006\u000f\n\u0005\bt\u0010\u0084\u0003\u001a\u0006\b\u0085\u0003\u0010\u0086\u0003R\u001c\u0010\u0088\u0003\u001a\u0005\u0018\u00010\u0087\u00038\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u0088\u0003\u0010\u0089\u0003R\u001a\u0010\u008a\u0003\u001a\u00030\u0080\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008a\u0003\u0010\u008b\u0003R\u001a\u0010\u008c\u0003\u001a\u00030\u0080\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b\u008c\u0003\u0010\u008b\u0003R@\u0010\u008e\u0003\u001a+\u0012\r\u0012\u000b ·\u0001*\u0004\u0018\u00010\u001a0\u001a ·\u0001*\u0014\u0012\r\u0012\u000b ·\u0001*\u0004\u0018\u00010\u001a0\u001a\u0018\u00010\u008d\u00030\u008d\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008e\u0003\u0010\u008f\u0003R\"\u0010\u0090\u0003\u001a\r ·\u0001*\u0005\u0018\u00010ï\u00020ï\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0090\u0003\u0010ñ\u0002R@\u0010\u0091\u0003\u001a+\u0012\r\u0012\u000b ·\u0001*\u0004\u0018\u00010\t0\t ·\u0001*\u0014\u0012\r\u0012\u000b ·\u0001*\u0004\u0018\u00010\t0\t\u0018\u00010\u008d\u00030\u008d\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0091\u0003\u0010\u008f\u0003R\"\u0010\u0092\u0003\u001a\r ·\u0001*\u0005\u0018\u00010ï\u00020ï\u00028\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0092\u0003\u0010ñ\u0002R*\u0010\u0094\u0003\u001a\u00030\u0093\u00038\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b\u0094\u0003\u0010\u0095\u0003\u001a\u0006\b\u0096\u0003\u0010\u0097\u0003\"\u0006\b\u0098\u0003\u0010\u0099\u0003R*\u0010\u009b\u0003\u001a\u00030\u009a\u00038\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b\u009b\u0003\u0010\u009c\u0003\u001a\u0006\b\u009d\u0003\u0010\u009e\u0003\"\u0006\b\u009f\u0003\u0010 \u0003R*\u0010¢\u0003\u001a\u00030¡\u00038\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b¢\u0003\u0010£\u0003\u001a\u0006\b¤\u0003\u0010¥\u0003\"\u0006\b¦\u0003\u0010§\u0003R*\u0010©\u0003\u001a\u00030¨\u00038\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b©\u0003\u0010ª\u0003\u001a\u0006\b«\u0003\u0010¬\u0003\"\u0006\b\u00ad\u0003\u0010®\u0003R*\u0010°\u0003\u001a\u00030¯\u00038\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b°\u0003\u0010±\u0003\u001a\u0006\b²\u0003\u0010³\u0003\"\u0006\b´\u0003\u0010µ\u0003R*\u0010·\u0003\u001a\u00030¶\u00038\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\b·\u0003\u0010¸\u0003\u001a\u0006\b¹\u0003\u0010º\u0003\"\u0006\b»\u0003\u0010¼\u0003R\u0018\u0010¾\u0003\u001a\u00030½\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¾\u0003\u0010¿\u0003R\u0018\u0010Á\u0003\u001a\u00030À\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÁ\u0003\u0010Â\u0003R\u001d\u0010Ä\u0003\u001a\u00030Ã\u00038\u0006¢\u0006\u0010\n\u0006\bÄ\u0003\u0010Å\u0003\u001a\u0006\bÆ\u0003\u0010Ç\u0003R\u0018\u0010É\u0003\u001a\u00030È\u00038\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÉ\u0003\u0010Ê\u0003R\u0017\u0010Ë\u0003\u001a\u00020\u001a8BX\u0082\u0004¢\u0006\b\u001a\u0006\bË\u0003\u0010ì\u0002¨\u0006Ð\u0003"}, d2 = {"Lcom/amazon/mp3/view/stage/StageViewPresenter;", "Landroidx/lifecycle/LifecycleObserver;", "", "setBottomButtonsOnClickListener", "resizeViewsForSmallScreens", "Lcom/amazon/mp3/view/stage/overlay/OverlayType;", "getNextOverlay", "Landroidx/constraintlayout/widget/ConstraintLayout;", "view", "", "topMargin", "bottomMargin", "setViewMargins", "Landroid/view/View;", "height", "width", "setViewSize", "startListeningToSkipLimitUpdates", "setupLoadingStageBackground", "resetBackgrounds", "setupInitialStageBackground", "showFullScreenBlurAlbumArt", "overlayType", "handleOverlayTypeChange", "setUpStateForAdOrInterlude", "presentInitialOverlays", "", "shouldShowLyrics", "deselectLyricsPill", "selectLyricsPill", "initializeXrayContainer", "requestXray", "Lcom/amazon/mp3/view/stage/StageViewPresenter$OverlaySequence;", "overlaySequence", "handleAutomaticSequencing", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initLandscapeStageView", "onLyricsOverlayButtonClick", "onXRayOverlayButtonClick", "show", "handleOverlaySelectionChange", "isXrayShownForCurrentTrack", "frugalLyricsFinished", "showHideLyricsPartialOverlay", "showLyricsFullScreenOverlay", "cardPosition", "shouldResetLyricsSharing", "openedFromShareSheet", "showLyricsSelectionOverlay", "showLyricsCardSelectionOverlay", "xrayView", "showXrayFullScreenOverlay", "Lcom/amazon/mp3/view/stage/StageXRayManager$XrayState;", "xrayState", "showHideXrayPartialOverlay", "Lkotlin/Triple;", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItemViewPair", "showXray", "Landroidx/core/util/Pair;", "xrayPageAndSectionIndexPair", "onXrayStateChanged", "showXrayPartialOverlay", "isXrayFinished", "onXrayFinished", "Lcom/amazon/mp3/view/stage/overlay/OverlayContainer;", "overlayContainer", "showPartialOverlay", "adjustOverlayContainerPosition", "forceHideAlbumArt", "hidePartialOverlay", "setupLikeButton", "setupStageToolbar", "Landroidx/appcompat/widget/Toolbar;", "getToolbar", "getToolbarLayout", "showHideAlexaButton", "Landroidx/fragment/app/Fragment;", "fragment", "removeOverlayFragment", "clearOverlayBackstack", "hideControls", "showActionBar", "emptyBackStackAndReturnLastElement", "getPresentPartialOverlay", "", "getLandscapeAudioViews", "handleLandscapePlayAreaTap", "fadeInLandscapeViews", "fadeOutLandscapeViews", "clearLandscapeViewsAnimation", "visibility", "toggleLandscapeViews", "refreshPortraitPlaybackControls", "setupPlaybackControlsClickListeners", "isOffline", "onCreate", "showVideoView", "showAudioView", "resetOverlayState", "requestXrayOnResume", "showHideControls", "isLandscape", "Lcom/amazon/mp3/view/stage/StageLandscapeControlsView;", "stageLandscapeVideoControlsView", "Landroid/widget/ProgressBar;", "progressBar", "updateVideoPlaybackControls", "hideLandscapePlayerMetadata", "onDestroy", "onNowPlayingFragmentDestroyed", "onNextButtonClick", "onPrevButtonClick", "setupTouchHandlers", "Lcom/amazon/mp3/view/TouchEventHandler;", "touchEventHandler", "setupSwipeGestures", "handleTouchEvent", "onPlayPauseButtonClick", "playing", "setPlayButtonImage", "setLoadingImage", "Landroidx/fragment/app/FragmentActivity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "Landroidx/fragment/app/DialogFragment;", "dialog", "showDialog", "Landroid/view/View$OnClickListener;", "onClickListener", "setOverflowButtonOnClickListener", "showOverflowButton", "setLikeButtonOnClickListener", AppStateModule.APP_STATE_ACTIVE, "setLikeButtonState", "isLikeButtonActive", Constants.ENABLE_DISABLE, "setLikeButtonEnabledState", "showLikeButton", "onAlexaButtonClick", "initializeAlexa", "", "badgeText", "contentDescription", "updateAudioQualityBadge", "setAudioQualityBadgeClickListener", "hideAudioQualityBadge", "addToBackStack", "animate", "addScreenOverlay", "showControls", "popOverlayFragmentFromBackstack", "isInFullScreenOverlayMode", "Landroid/graphics/drawable/Drawable;", "drawable", "Landroid/graphics/Bitmap;", "getBitmapFromDrawable", "bitmap", "", "blurRadius", "getBlurBitmap", "refreshBottomButtonsVisibility", "updateOverflowMenuButtonSecondaryIcon", "Lcom/amazon/mp3/view/player/PlayerViewEnabledStateManager;", "enabledStateManager", "updatePlayerControls", "refreshBottomButtons", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "Ljava/lang/ref/WeakReference;", "viewRoot", "Ljava/lang/ref/WeakReference;", "Landroidx/lifecycle/Lifecycle;", "lifecycle", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "()Landroidx/lifecycle/Lifecycle;", "setLifecycle", "(Landroidx/lifecycle/Lifecycle;)V", "Lrx/Subscription;", "overlaySequenceSubscription", "Lrx/Subscription;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Ljava/util/Stack;", "fullscreenOverlayBackstack", "Ljava/util/Stack;", "getFullscreenOverlayBackstack", "()Ljava/util/Stack;", "partialOverlayContainer", "Lcom/amazon/mp3/view/stage/overlay/OverlayContainer;", "getPartialOverlayContainer", "()Lcom/amazon/mp3/view/stage/overlay/OverlayContainer;", "setPartialOverlayContainer", "(Lcom/amazon/mp3/view/stage/overlay/OverlayContainer;)V", "Lcom/amazon/mp3/activity/navigation/FragmentController;", "fragmentController$delegate", "Lkotlin/Lazy;", "getFragmentController", "()Lcom/amazon/mp3/activity/navigation/FragmentController;", "fragmentController", "landscapeControlsVisible", "Z", "landscapeControlsAnimationSubscription", "com/amazon/mp3/view/stage/StageViewPresenter$onSwipeEventListener$1", "onSwipeEventListener", "Lcom/amazon/mp3/view/stage/StageViewPresenter$onSwipeEventListener$1;", "Lcom/amazon/mp3/view/stage/StageLyrics;", "stageLyrics$delegate", "getStageLyrics", "()Lcom/amazon/mp3/view/stage/StageLyrics;", "stageLyrics", "Lcom/amazon/mp3/view/stage/StageXRayManager;", "stageXrayManager$delegate", "getStageXrayManager", "()Lcom/amazon/mp3/view/stage/StageXRayManager;", "stageXrayManager", "Landroid/widget/FrameLayout;", "landscapeAudioBackgroundOverlay$delegate", "getLandscapeAudioBackgroundOverlay", "()Landroid/widget/FrameLayout;", "landscapeAudioBackgroundOverlay", "stageContainerLayout$delegate", "getStageContainerLayout", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "stageContainerLayout", "landscapeControlsView$delegate", "getLandscapeControlsView", "()Lcom/amazon/mp3/view/stage/StageLandscapeControlsView;", "landscapeControlsView", "stageAlbumArtContainer$delegate", "getStageAlbumArtContainer", "stageAlbumArtContainer", "Lcom/amazon/ui/foundations/views/BaseImage;", "albumArtView$delegate", "getAlbumArtView", "()Lcom/amazon/ui/foundations/views/BaseImage;", "albumArtView", "overlayContainerView$delegate", "getOverlayContainerView", "overlayContainerView", "playerCoverViewRoot$delegate", "getPlayerCoverViewRoot", "playerCoverViewRoot", "Lcom/amazon/mp3/view/stage/StageBottomButtonsView;", "bottomButtonsContainerView$delegate", "getBottomButtonsContainerView", "()Lcom/amazon/mp3/view/stage/StageBottomButtonsView;", "bottomButtonsContainerView", "Lcom/amazon/mp3/view/stage/StagePortraitPlaybackControlsView;", "playbackControlsView$delegate", "getPlaybackControlsView", "()Lcom/amazon/mp3/view/stage/StagePortraitPlaybackControlsView;", "playbackControlsView", "videoPlaybackControlsView", "Lcom/amazon/mp3/view/stage/StagePortraitPlaybackControlsView;", "Lcom/amazon/mp3/view/stage/StageOverlayButtonsLayout;", "overlayButtonsContainerLayout$delegate", "getOverlayButtonsContainerLayout", "()Lcom/amazon/mp3/view/stage/StageOverlayButtonsLayout;", "overlayButtonsContainerLayout", "Lcom/amazon/ui/foundations/views/BaseButton;", "lyricsOverlayButton$delegate", "getLyricsOverlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "lyricsOverlayButton", "value", "playPauseProgressBar", "Landroid/widget/ProgressBar;", "setPlayPauseProgressBar", "(Landroid/widget/ProgressBar;)V", "playPauseButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "nextButton", "prevButton", "overflowButton$delegate", "getOverflowButton", "overflowButton", "likeButton$delegate", "getLikeButton", "likeButton", "alexaButton$delegate", "getAlexaButton", "alexaButton", "backButton$delegate", "getBackButton", "backButton", "Landroid/widget/TextView;", "controlTipView$delegate", "getControlTipView", "()Landroid/widget/TextView;", "controlTipView", "stageBackgroundFrame$delegate", "getStageBackgroundFrame", "stageBackgroundFrame", "artistTreatmentView$delegate", "getArtistTreatmentView", "artistTreatmentView", "Landroid/widget/ImageView;", "artistImageView$delegate", "getArtistImageView", "()Landroid/widget/ImageView;", "artistImageView", "artistImageViewBlurred$delegate", "getArtistImageViewBlurred", "artistImageViewBlurred", "albumArtBlurredView$delegate", "getAlbumArtBlurredView", "albumArtBlurredView", "Landroid/widget/LinearLayout;", "playerMetadataContainer$delegate", "getPlayerMetadataContainer", "()Landroid/widget/LinearLayout;", "playerMetadataContainer", "Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "seekbarView$delegate", "getSeekbarView", "()Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "seekbarView", "Lcom/amazon/mp3/view/player/PlayerMetadataView;", "landscapePlayerMetadata$delegate", "getLandscapePlayerMetadata", "()Lcom/amazon/mp3/view/player/PlayerMetadataView;", "landscapePlayerMetadata", "playerMetadata$delegate", "getPlayerMetadata", "playerMetadata", "Lrx/subjects/PublishSubject;", "onNextClickedSubject", "Lrx/subjects/PublishSubject;", "getOnNextClickedSubject", "()Lrx/subjects/PublishSubject;", "blurAlbumArtSubject", "getBlurAlbumArtSubject", "overlayTypeSubject", "getOverlayTypeSubject", "Lrx/Observable;", "Lcom/amazon/mp3/view/stage/StageDataModel$State;", "stateObservable", "Lrx/Observable;", "getStateObservable", "()Lrx/Observable;", "setStateObservable", "(Lrx/Observable;)V", "overlaySequenceSubject", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "setContext", "(Landroid/content/Context;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/mp3/view/stage/StageViewModel;", "stageViewModel", "Lcom/amazon/mp3/view/stage/StageViewModel;", "getStageViewModel", "()Lcom/amazon/mp3/view/stage/StageViewModel;", "setStageViewModel", "(Lcom/amazon/mp3/view/stage/StageViewModel;)V", "artworkVisible", "getArtworkVisible", "()Z", "setArtworkVisible", "(Z)V", "Lio/reactivex/rxjava3/disposables/Disposable;", "lyricsDisposable", "Lio/reactivex/rxjava3/disposables/Disposable;", "getLyricsDisposable", "()Lio/reactivex/rxjava3/disposables/Disposable;", "setLyricsDisposable", "(Lio/reactivex/rxjava3/disposables/Disposable;)V", "Lrx/subscriptions/CompositeSubscription;", "stateSubscription", "Lrx/subscriptions/CompositeSubscription;", "Lcom/amazon/mp3/view/stage/StageAnimations;", "stageAnimations", "Lcom/amazon/mp3/view/stage/StageAnimations;", "getStageAnimations", "()Lcom/amazon/mp3/view/stage/StageAnimations;", "Lcom/amazon/mp3/view/stage/StageTooltipPresenter;", "stageTooltipPresenter", "Lcom/amazon/mp3/view/stage/StageTooltipPresenter;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "Lcom/amazon/mp3/view/TouchEventHandler;", "getTouchEventHandler", "()Lcom/amazon/mp3/view/TouchEventHandler;", "Lcom/amazon/music/views/library/styles/keys/LandscapeType;", "landscapeType", "Lcom/amazon/music/views/library/styles/keys/LandscapeType;", "castingButtonClickListener", "Landroid/view/View$OnClickListener;", "playQueueOnClickListener", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "openLyricsSelectionFragmentSubject", "Lio/reactivex/rxjava3/subjects/PublishSubject;", "openLyricsSelectionFragmentSubscription", "openLyricsCardSelectionFragmentSubject", "openLyricsCardFragmentSubscription", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "overlayTypeStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "getOverlayTypeStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;", "setOverlayTypeStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayTypeStateProvider;)V", "Lcom/amazon/mp3/view/stage/overlay/OverlayXrayStateProvider;", "overlayXrayStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayXrayStateProvider;", "getOverlayXrayStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayXrayStateProvider;", "setOverlayXrayStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayXrayStateProvider;)V", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "overlayLyricsStateProvider", "Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "getOverlayLyricsStateProvider", "()Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;", "setOverlayLyricsStateProvider", "(Lcom/amazon/mp3/view/stage/overlay/OverlayLyricsStateProvider;)V", "Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "stageAutomatedSequencingHandler", "Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "getStageAutomatedSequencingHandler", "()Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;", "setStageAutomatedSequencingHandler", "(Lcom/amazon/mp3/view/stage/StageAutomatedSequencingHandler;)V", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "stageOverflowMenuSecondaryIconStateProvider", "Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "getStageOverflowMenuSecondaryIconStateProvider", "()Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;", "setStageOverflowMenuSecondaryIconStateProvider", "(Lcom/amazon/mp3/view/stage/StageOverflowMenuSecondaryIconStateProvider;)V", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "lyricsSharingDataModel", "Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "getLyricsSharingDataModel", "()Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;", "setLyricsSharingDataModel", "(Lcom/amazon/mp3/view/lyrics/sharing/data/LyricsSharingDataModel;)V", "Lcom/amazon/mp3/view/stage/StageOverlayMetricsReporter;", "stageOverlayMetricsReporter", "Lcom/amazon/mp3/view/stage/StageOverlayMetricsReporter;", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitProviderImpl$OnSkipLimitUpdateListener;", "mSkipLimitUpdateListener", "Lcom/amazon/mp3/skiplimit/impl/SkipLimitProviderImpl$OnSkipLimitUpdateListener;", "Lcom/squareup/picasso/Target;", "artistImageTarget", "Lcom/squareup/picasso/Target;", "getArtistImageTarget", "()Lcom/squareup/picasso/Target;", "com/amazon/mp3/view/stage/StageViewPresenter$xrayWindowInteractionListener$1", "xrayWindowInteractionListener", "Lcom/amazon/mp3/view/stage/StageViewPresenter$xrayWindowInteractionListener$1;", "isVideoTrack", "fragmentActivity", "<init>", "(Ljava/lang/ref/WeakReference;Ljava/lang/ref/WeakReference;Landroidx/lifecycle/Lifecycle;)V", "OverlaySequence", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageViewPresenter implements LifecycleObserver {
    private final String TAG;

    /* JADX INFO: renamed from: albumArtBlurredView$delegate, reason: from kotlin metadata */
    private final Lazy albumArtBlurredView;

    /* JADX INFO: renamed from: albumArtView$delegate, reason: from kotlin metadata */
    private final Lazy albumArtView;

    /* JADX INFO: renamed from: alexaButton$delegate, reason: from kotlin metadata */
    private final Lazy alexaButton;
    private final Target artistImageTarget;

    /* JADX INFO: renamed from: artistImageView$delegate, reason: from kotlin metadata */
    private final Lazy artistImageView;

    /* JADX INFO: renamed from: artistImageViewBlurred$delegate, reason: from kotlin metadata */
    private final Lazy artistImageViewBlurred;

    /* JADX INFO: renamed from: artistTreatmentView$delegate, reason: from kotlin metadata */
    private final Lazy artistTreatmentView;
    private boolean artworkVisible;

    /* JADX INFO: renamed from: backButton$delegate, reason: from kotlin metadata */
    private final Lazy backButton;
    private final PublishSubject<Bitmap> blurAlbumArtSubject;

    /* JADX INFO: renamed from: bottomButtonsContainerView$delegate, reason: from kotlin metadata */
    private final Lazy bottomButtonsContainerView;
    private View.OnClickListener castingButtonClickListener;
    private Context context;

    /* JADX INFO: renamed from: controlTipView$delegate, reason: from kotlin metadata */
    private final Lazy controlTipView;

    /* JADX INFO: renamed from: fragmentController$delegate, reason: from kotlin metadata */
    private final Lazy fragmentController;
    private final Stack<OverlayContainer> fullscreenOverlayBackstack;

    /* JADX INFO: renamed from: landscapeAudioBackgroundOverlay$delegate, reason: from kotlin metadata */
    private final Lazy landscapeAudioBackgroundOverlay;
    private Subscription landscapeControlsAnimationSubscription;

    /* JADX INFO: renamed from: landscapeControlsView$delegate, reason: from kotlin metadata */
    private final Lazy landscapeControlsView;
    private boolean landscapeControlsVisible;

    /* JADX INFO: renamed from: landscapePlayerMetadata$delegate, reason: from kotlin metadata */
    private final Lazy landscapePlayerMetadata;
    private LandscapeType landscapeType;
    private Lifecycle lifecycle;

    /* JADX INFO: renamed from: likeButton$delegate, reason: from kotlin metadata */
    private final Lazy likeButton;
    private Disposable lyricsDisposable;

    /* JADX INFO: renamed from: lyricsOverlayButton$delegate, reason: from kotlin metadata */
    private final Lazy lyricsOverlayButton;
    public LyricsSharingDataModel lyricsSharingDataModel;
    private final SkipLimitProviderImpl.OnSkipLimitUpdateListener mSkipLimitUpdateListener;
    private BaseButton nextButton;
    private final PublishSubject<Boolean> onNextClickedSubject;
    private final StageViewPresenter$onSwipeEventListener$1 onSwipeEventListener;
    private final Disposable openLyricsCardFragmentSubscription;
    private final io.reactivex.rxjava3.subjects.PublishSubject<Integer> openLyricsCardSelectionFragmentSubject;
    private final io.reactivex.rxjava3.subjects.PublishSubject<Boolean> openLyricsSelectionFragmentSubject;
    private final Disposable openLyricsSelectionFragmentSubscription;

    /* JADX INFO: renamed from: overflowButton$delegate, reason: from kotlin metadata */
    private final Lazy overflowButton;

    /* JADX INFO: renamed from: overlayButtonsContainerLayout$delegate, reason: from kotlin metadata */
    private final Lazy overlayButtonsContainerLayout;

    /* JADX INFO: renamed from: overlayContainerView$delegate, reason: from kotlin metadata */
    private final Lazy overlayContainerView;
    public OverlayLyricsStateProvider overlayLyricsStateProvider;
    private final PublishSubject<OverlaySequence> overlaySequenceSubject;
    private Subscription overlaySequenceSubscription;
    public OverlayTypeStateProvider overlayTypeStateProvider;
    private final PublishSubject<OverlayType> overlayTypeSubject;
    public OverlayXrayStateProvider overlayXrayStateProvider;
    private OverlayContainer partialOverlayContainer;
    private BaseButton playPauseButton;
    private ProgressBar playPauseProgressBar;
    private View.OnClickListener playQueueOnClickListener;
    private final ActionValidatedPlaybackController playbackController;

    /* JADX INFO: renamed from: playbackControlsView$delegate, reason: from kotlin metadata */
    private final Lazy playbackControlsView;

    /* JADX INFO: renamed from: playerCoverViewRoot$delegate, reason: from kotlin metadata */
    private final Lazy playerCoverViewRoot;

    /* JADX INFO: renamed from: playerMetadata$delegate, reason: from kotlin metadata */
    private final Lazy playerMetadata;

    /* JADX INFO: renamed from: playerMetadataContainer$delegate, reason: from kotlin metadata */
    private final Lazy playerMetadataContainer;
    private BaseButton prevButton;

    /* JADX INFO: renamed from: seekbarView$delegate, reason: from kotlin metadata */
    private final Lazy seekbarView;

    /* JADX INFO: renamed from: stageAlbumArtContainer$delegate, reason: from kotlin metadata */
    private final Lazy stageAlbumArtContainer;
    private final StageAnimations stageAnimations;
    public StageAutomatedSequencingHandler stageAutomatedSequencingHandler;

    /* JADX INFO: renamed from: stageBackgroundFrame$delegate, reason: from kotlin metadata */
    private final Lazy stageBackgroundFrame;

    /* JADX INFO: renamed from: stageContainerLayout$delegate, reason: from kotlin metadata */
    private final Lazy stageContainerLayout;

    /* JADX INFO: renamed from: stageLyrics$delegate, reason: from kotlin metadata */
    private final Lazy stageLyrics;
    public StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider;
    private final StageOverlayMetricsReporter stageOverlayMetricsReporter;
    private StageTooltipPresenter stageTooltipPresenter;
    private StageViewModel stageViewModel;

    /* JADX INFO: renamed from: stageXrayManager$delegate, reason: from kotlin metadata */
    private final Lazy stageXrayManager;
    public Observable<Triple<StageDataModel.State, StageDataModel.State, StageDataModel.State>> stateObservable;
    private CompositeSubscription stateSubscription;
    private StyleSheet styleSheet;
    private final TouchEventHandler touchEventHandler;
    private final StagePortraitPlaybackControlsView videoPlaybackControlsView;
    private final WeakReference<View> viewRoot;
    private final StageViewPresenter$xrayWindowInteractionListener$1 xrayWindowInteractionListener;

    /* JADX INFO: compiled from: StageViewPresenter.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Lcom/amazon/mp3/view/stage/StageViewPresenter$OverlaySequence;", "", "(Ljava/lang/String;I)V", "ALBUM_ART_PREVIEW_FINISHED", "FRUGAL_LYRICS_FINISHED", "FRUGAL_XRAY_FINISHED", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum OverlaySequence {
        ALBUM_ART_PREVIEW_FINISHED,
        FRUGAL_LYRICS_FINISHED,
        FRUGAL_XRAY_FINISHED
    }

    /* JADX INFO: compiled from: StageViewPresenter.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[StageLyrics.LyricsState.values().length];
            iArr[StageLyrics.LyricsState.FRUGAL_HIDDEN.ordinal()] = 1;
            iArr[StageLyrics.LyricsState.FRUGAL_DISPLAYED.ordinal()] = 2;
            iArr[StageLyrics.LyricsState.NO_LYRICS.ordinal()] = 3;
            iArr[StageLyrics.LyricsState.LYRICS_AVAILABLE.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[OverlayType.values().length];
            iArr2[OverlayType.LYRICS_PARTIAL.ordinal()] = 1;
            iArr2[OverlayType.XRAY_PARTIAL.ordinal()] = 2;
            iArr2[OverlayType.LYRICS_FULLSCREEN.ordinal()] = 3;
            iArr2[OverlayType.CASTING.ordinal()] = 4;
            iArr2[OverlayType.PLAY_QUEUE.ordinal()] = 5;
            iArr2[OverlayType.LYRICS_SELECTION.ordinal()] = 6;
            iArr2[OverlayType.LYRICS_CARDS_SELECTION.ordinal()] = 7;
            iArr2[OverlayType.XRAY_FULLSCREEN.ordinal()] = 8;
            iArr2[OverlayType.ALBUM_ART.ordinal()] = 9;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-11$lambda-10, reason: not valid java name */
    public static final Lifecycle m1768onCreate$lambda11$lambda10(Lifecycle it) {
        Intrinsics.checkNotNullParameter(it, "$it");
        return it;
    }

    /* JADX WARN: Type inference failed for: r4v6, types: [com.amazon.mp3.view.stage.StageViewPresenter$onSwipeEventListener$1] */
    public StageViewPresenter(WeakReference<View> viewRoot, final WeakReference<FragmentActivity> fragmentActivity, Lifecycle lifecycle) {
        Intrinsics.checkNotNullParameter(viewRoot, "viewRoot");
        Intrinsics.checkNotNullParameter(fragmentActivity, "fragmentActivity");
        this.viewRoot = viewRoot;
        this.lifecycle = lifecycle;
        if (lifecycle != null) {
            lifecycle.addObserver(this);
        }
        this.TAG = StageViewPresenter.class.getSimpleName();
        this.fullscreenOverlayBackstack = new Stack<>();
        this.fragmentController = LazyKt__LazyJVMKt.lazy(new Function0<FragmentController>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$fragmentController$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FragmentController invoke() {
                FragmentActivity fragmentActivity2;
                View view = (View) this.this$0.viewRoot.get();
                if (view == null || view.findViewById(R.id.OverlayContainer) == null || (fragmentActivity2 = fragmentActivity.get()) == null) {
                    return null;
                }
                return new FragmentController(fragmentActivity2, R.id.OverlayContainer);
            }
        });
        this.onSwipeEventListener = new OnSwipeEventListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$onSwipeEventListener$1
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
                if (x > 120.0f) {
                    this.this$0.onNextButtonClick();
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
                if (x > 120.0f) {
                    this.this$0.onPrevButtonClick();
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
                if (y > 120.0f) {
                    Context context = this.this$0.getContext();
                    FragmentActivity fragmentActivity2 = context instanceof FragmentActivity ? (FragmentActivity) context : null;
                    if (fragmentActivity2 == null) {
                        return;
                    }
                    fragmentActivity2.onBackPressed();
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
                if (!Intrinsics.areEqual(this.this$0.getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.Lyrics.INSTANCE)) {
                    OverlaySelectionType overlaySelectionType = this.this$0.getOverlayTypeStateProvider().getOverlaySelectionType();
                    OverlaySelectionType.Automatic automatic = OverlaySelectionType.Automatic.INSTANCE;
                    if (!Intrinsics.areEqual(overlaySelectionType, automatic) || this.this$0.getStageAutomatedSequencingHandler().getAutomatedSequenceCurrentOverlay() != OverlayType.LYRICS_PARTIAL) {
                        if (Intrinsics.areEqual(this.this$0.getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.Xray.INSTANCE) || (Intrinsics.areEqual(this.this$0.getOverlayTypeStateProvider().getOverlaySelectionType(), automatic) && this.this$0.getStageAutomatedSequencingHandler().getAutomatedSequenceCurrentOverlay() == OverlayType.XRAY_PARTIAL)) {
                            this.this$0.xrayWindowInteractionListener.onXrayWindowClicked();
                            return;
                        }
                        return;
                    }
                }
                if (ScreenUtil.isPortrait()) {
                    this.this$0.showLyricsFullScreenOverlay();
                    StageViewModel.sendUiClickEvent$default(this.this$0.getStageViewModel(), ActionType.EXPAND_LYRICS_FULL, null, 2, null);
                } else {
                    this.this$0.handleLandscapePlayAreaTap();
                }
            }
        };
        this.stageLyrics = LazyKt__LazyJVMKt.lazy(new Function0<StageLyrics>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageLyrics$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StageLyrics invoke() {
                Context context;
                if (!AmazonApplication.getCapabilities().isLyricsSupported() || (context = this.this$0.getContext()) == null) {
                    return null;
                }
                final StageViewPresenter stageViewPresenter = this.this$0;
                return new StageLyrics(context, stageViewPresenter.onSwipeEventListener, new Function0<Unit>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageLyrics$2$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        if (ScreenUtil.isPortrait()) {
                            stageViewPresenter.showLyricsFullScreenOverlay();
                            StageViewModel.sendUiClickEvent$default(stageViewPresenter.getStageViewModel(), ActionType.EXPAND_LYRICS_FULL, null, 2, null);
                        } else {
                            stageViewPresenter.handleLandscapePlayAreaTap();
                        }
                    }
                });
            }
        });
        this.stageXrayManager = LazyKt__LazyJVMKt.lazy(new Function0<StageXRayManager>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageXrayManager$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StageXRayManager invoke() {
                Context context;
                if (!AmazonApplication.getCapabilities().isXRayEnabled() || (context = this.this$0.getContext()) == null) {
                    return null;
                }
                WeakReference<FragmentActivity> weakReference = fragmentActivity;
                final StageViewPresenter stageViewPresenter = this.this$0;
                return new StageXRayManager(context, weakReference, stageViewPresenter.getFragmentController(), stageViewPresenter.getStageViewModel(), new Function0<Unit>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageXrayManager$2$1$1
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        stageViewPresenter.initializeXrayContainer();
                    }
                }, new Function0<Unit>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageXrayManager$2$1$2
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public /* bridge */ /* synthetic */ Unit invoke() {
                        invoke2();
                        return Unit.INSTANCE;
                    }

                    /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2() {
                        stageViewPresenter.onXRayOverlayButtonClick();
                    }
                });
            }
        });
        this.landscapeAudioBackgroundOverlay = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$landscapeAudioBackgroundOverlay$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                Activity activity;
                View view = (View) this.this$0.viewRoot.get();
                if (view == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view)) == null) {
                    return null;
                }
                return (FrameLayout) activity.findViewById(R.id.AudioBackgroundOverlay);
            }
        });
        this.stageContainerLayout = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageContainerLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (ConstraintLayout) view.findViewById(R.id.stage_controls);
            }
        });
        this.landscapeControlsView = LazyKt__LazyJVMKt.lazy(new Function0<StageLandscapeControlsView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$landscapeControlsView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StageLandscapeControlsView invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (StageLandscapeControlsView) view.findViewById(R.id.stage_landscape_controls);
            }
        });
        this.stageAlbumArtContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageAlbumArtContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (FrameLayout) view.findViewById(R.id.StageAlbumArtContainer);
            }
        });
        this.albumArtView = LazyKt__LazyJVMKt.lazy(new Function0<BaseImage>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$albumArtView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseImage invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (BaseImage) view.findViewById(R.id.PlayerCoverViewAlbumArt);
            }
        });
        this.overlayContainerView = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$overlayContainerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (FrameLayout) view.findViewById(R.id.OverlayContainer);
            }
        });
        this.playerCoverViewRoot = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$playerCoverViewRoot$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ConstraintLayout invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (ConstraintLayout) view.findViewById(R.id.PlayerCoverViewRoot);
            }
        });
        this.bottomButtonsContainerView = LazyKt__LazyJVMKt.lazy(new Function0<StageBottomButtonsView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$bottomButtonsContainerView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StageBottomButtonsView invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (StageBottomButtonsView) view.findViewById(R.id.stage_bottom_buttons_view);
            }
        });
        this.playbackControlsView = LazyKt__LazyJVMKt.lazy(new Function0<StagePortraitPlaybackControlsView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$playbackControlsView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StagePortraitPlaybackControlsView invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (StagePortraitPlaybackControlsView) view.findViewById(R.id.stage_portrait_controls);
            }
        });
        this.videoPlaybackControlsView = getPlaybackControlsView();
        this.overlayButtonsContainerLayout = LazyKt__LazyJVMKt.lazy(new Function0<StageOverlayButtonsLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$overlayButtonsContainerLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final StageOverlayButtonsLayout invoke() {
                View view = (View) this.this$0.viewRoot.get();
                if (view == null) {
                    return null;
                }
                return (StageOverlayButtonsLayout) view.findViewById(R.id.stage_overlay_button_layout);
            }
        });
        this.lyricsOverlayButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$lyricsOverlayButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                StageOverlayButtonsLayout overlayButtonsContainerLayout = this.this$0.getOverlayButtonsContainerLayout();
                if (overlayButtonsContainerLayout == null) {
                    return null;
                }
                return overlayButtonsContainerLayout.getLyricsOverlayButton();
            }
        });
        View view = viewRoot.get();
        byte b = 0;
        byte b2 = 0;
        this.playPauseProgressBar = view == null ? null : (ProgressBar) view.findViewById(android.R.id.progress);
        View view2 = viewRoot.get();
        this.playPauseButton = view2 == null ? null : (BaseButton) view2.findViewById(R.id.PersistentPlayerPlayButton);
        View view3 = viewRoot.get();
        this.nextButton = view3 == null ? null : (BaseButton) view3.findViewById(R.id.PersistentPlayerNextButton);
        View view4 = viewRoot.get();
        this.prevButton = view4 == null ? null : (BaseButton) view4.findViewById(R.id.PersistentPlayerPrevButton);
        this.overflowButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$overflowButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null) {
                    return null;
                }
                return (BaseButton) view5.findViewById(R.id.MoreOptions);
            }
        });
        this.likeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$likeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null) {
                    return null;
                }
                return (BaseButton) view5.findViewById(R.id.StageLikeButton);
            }
        });
        this.alexaButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$alexaButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                Toolbar toolbar = this.this$0.getToolbar();
                if (toolbar == null) {
                    return null;
                }
                return (BaseButton) toolbar.findViewById(R.id.iv_alexa);
            }
        });
        this.backButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$backButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                Toolbar toolbar = this.this$0.getToolbar();
                if (toolbar == null) {
                    return null;
                }
                return (BaseButton) toolbar.findViewById(R.id.StageBackButton);
            }
        });
        this.controlTipView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$controlTipView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null) {
                    return null;
                }
                return (TextView) view5.findViewById(R.id.stage_control_tip);
            }
        });
        this.stageBackgroundFrame = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$stageBackgroundFrame$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                Activity activity;
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view5)) == null) {
                    return null;
                }
                return (FrameLayout) activity.findViewById(R.id.stage_background_frame);
            }
        });
        this.artistTreatmentView = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$artistTreatmentView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                Activity activity;
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view5)) == null) {
                    return null;
                }
                return (FrameLayout) activity.findViewById(R.id.artist_background_treatment_view);
            }
        });
        this.artistImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$artistImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                Activity activity;
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view5)) == null) {
                    return null;
                }
                return (ImageView) activity.findViewById(R.id.artist_image_background);
            }
        });
        this.artistImageViewBlurred = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$artistImageViewBlurred$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                Activity activity;
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view5)) == null) {
                    return null;
                }
                return (ImageView) activity.findViewById(R.id.artist_image_background_blurred);
            }
        });
        this.albumArtBlurredView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$albumArtBlurredView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                Activity activity;
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view5)) == null) {
                    return null;
                }
                return (ImageView) activity.findViewById(R.id.album_art_background_blurred);
            }
        });
        this.playerMetadataContainer = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$playerMetadataContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                Activity activity;
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null || (activity = ShovelerExtensionFunctionsKt.getActivity(view5)) == null) {
                    return null;
                }
                return (LinearLayout) activity.findViewById(R.id.stage_player_metadata_container);
            }
        });
        this.seekbarView = LazyKt__LazyJVMKt.lazy(new Function0<BauhausSeekBarWidget>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$seekbarView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BauhausSeekBarWidget invoke() {
                View view5 = (View) this.this$0.viewRoot.get();
                if (view5 == null) {
                    return null;
                }
                return (BauhausSeekBarWidget) view5.findViewById(R.id.stage_seekbar);
            }
        });
        this.landscapePlayerMetadata = LazyKt__LazyJVMKt.lazy(new Function0<PlayerMetadataView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$landscapePlayerMetadata$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayerMetadataView invoke() {
                Toolbar toolbar = this.this$0.getToolbar();
                if (toolbar == null) {
                    return null;
                }
                return (PlayerMetadataView) toolbar.findViewById(R.id.player_metadata);
            }
        });
        this.playerMetadata = LazyKt__LazyJVMKt.lazy(new Function0<PlayerMetadataView>() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$playerMetadata$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayerMetadataView invoke() {
                if (ScreenUtil.isPortrait()) {
                    View view5 = (View) this.this$0.viewRoot.get();
                    if (view5 == null) {
                        return null;
                    }
                    return (PlayerMetadataView) view5.findViewById(R.id.player_metadata);
                }
                return this.this$0.getLandscapePlayerMetadata();
            }
        });
        PublishSubject<Boolean> publishSubjectCreate = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate, "create()");
        this.onNextClickedSubject = publishSubjectCreate;
        PublishSubject<Bitmap> publishSubjectCreate2 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate2, "create()");
        this.blurAlbumArtSubject = publishSubjectCreate2;
        PublishSubject<OverlayType> publishSubjectCreate3 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate3, "create()");
        this.overlayTypeSubject = publishSubjectCreate3;
        PublishSubject<OverlaySequence> publishSubjectCreate4 = PublishSubject.create();
        Intrinsics.checkNotNullExpressionValue(publishSubjectCreate4, "create()");
        this.overlaySequenceSubject = publishSubjectCreate4;
        View view5 = viewRoot.get();
        this.context = view5 == null ? null : view5.getContext();
        this.stageViewModel = new StageViewModel(this, b2 == true ? 1 : 0, 2, b == true ? 1 : 0);
        this.artworkVisible = true;
        this.stateSubscription = new CompositeSubscription();
        Context context = this.context;
        this.stageAnimations = context != null ? new StageAnimations(context) : null;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        Context context2 = getContext();
        if (context2 != null) {
            touchEventHandler.setOnClickListener(context2, new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view6) {
                    StageViewPresenter.m1793touchEventHandler$lambda3$lambda2$lambda1(this.f$0, view6);
                }
            });
        }
        this.touchEventHandler = touchEventHandler;
        this.castingButtonClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view6) {
                StageViewPresenter.m1766castingButtonClickListener$lambda5(this.f$0, view6);
            }
        };
        this.playQueueOnClickListener = new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view6) {
                StageViewPresenter.m1789playQueueOnClickListener$lambda6(this.f$0, view6);
            }
        };
        io.reactivex.rxjava3.subjects.PublishSubject<Boolean> publishSubjectCreate5 = io.reactivex.rxjava3.subjects.PublishSubject.create();
        this.openLyricsSelectionFragmentSubject = publishSubjectCreate5;
        this.openLyricsSelectionFragmentSubscription = publishSubjectCreate5.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageViewPresenter.m1788openLyricsSelectionFragmentSubscription$lambda7(this.f$0, (Boolean) obj);
            }
        });
        io.reactivex.rxjava3.subjects.PublishSubject<Integer> publishSubjectCreate6 = io.reactivex.rxjava3.subjects.PublishSubject.create();
        this.openLyricsCardSelectionFragmentSubject = publishSubjectCreate6;
        this.openLyricsCardFragmentSubscription = publishSubjectCreate6.observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageViewPresenter.m1787openLyricsCardFragmentSubscription$lambda8(this.f$0, (Integer) obj);
            }
        });
        this.stageOverlayMetricsReporter = StageOverlayMetricsReporter.INSTANCE.getStageOverlayMetricsReporter(this.context, this.lifecycle);
        this.mSkipLimitUpdateListener = new StageViewPresenter$mSkipLimitUpdateListener$1(this);
        this.artistImageTarget = new Target() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$artistImageTarget$1
            @Override // com.squareup.picasso.Target
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                Drawable blurBitmapDrawable;
                BehaviorSubject<StageDataModel.State> artistImageAvailability;
                Log.debug(this.this$0.TAG, "onBitmapLoaded drawable = " + bitmap + " loadedFrom = " + loadedFrom);
                Context context3 = this.this$0.getContext();
                if (context3 == null) {
                    return;
                }
                StageViewPresenter stageViewPresenter = this.this$0;
                BitmapDrawable bitmapDrawable = new BitmapDrawable(context3.getResources(), bitmap);
                if (bitmap == null) {
                    blurBitmapDrawable = null;
                } else {
                    BitmapEffectUtils bitmapEffectUtils = BitmapEffectUtils.INSTANCE;
                    blurBitmapDrawable = bitmapEffectUtils.getBlurBitmapDrawable(context3, bitmapEffectUtils.getBlurBitmap(context3, bitmap, 24.0f), 24.0f);
                }
                StageDataModel stageDataModel = stageViewPresenter.getStageViewModel().getStageDataModel();
                if (stageDataModel != null) {
                    stageDataModel.setArtistImage(bitmapDrawable);
                }
                StageDataModel stageDataModel2 = stageViewPresenter.getStageViewModel().getStageDataModel();
                if (stageDataModel2 != null) {
                    stageDataModel2.setBlurArtistImage(blurBitmapDrawable);
                }
                StageDataModel stageDataModel3 = stageViewPresenter.getStageViewModel().getStageDataModel();
                if (stageDataModel3 == null || (artistImageAvailability = stageDataModel3.getArtistImageAvailability()) == null) {
                    return;
                }
                artistImageAvailability.onNext(StageDataModel.State.AVAILABLE);
            }

            @Override // com.squareup.picasso.Target
            public void onBitmapFailed(Exception e, Drawable drawable) {
                BehaviorSubject<StageDataModel.State> artistImageAvailability;
                Log.debug(this.this$0.TAG, "onBitmapFailed drawable = " + drawable + " exception = " + e);
                StageDataModel stageDataModel = this.this$0.getStageViewModel().getStageDataModel();
                if (stageDataModel == null || (artistImageAvailability = stageDataModel.getArtistImageAvailability()) == null) {
                    return;
                }
                artistImageAvailability.onNext(StageDataModel.State.UNAVAILABLE);
            }

            @Override // com.squareup.picasso.Target
            public void onPrepareLoad(Drawable drawable) {
                Log.debug(this.this$0.TAG, Intrinsics.stringPlus("onPrepareLoad drawable = ", drawable));
            }
        };
        this.xrayWindowInteractionListener = new StageViewPresenter$xrayWindowInteractionListener$1(this);
    }

    public final Stack<OverlayContainer> getFullscreenOverlayBackstack() {
        return this.fullscreenOverlayBackstack;
    }

    public final void setPartialOverlayContainer(OverlayContainer overlayContainer) {
        this.partialOverlayContainer = overlayContainer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FragmentController getFragmentController() {
        return (FragmentController) this.fragmentController.getValue();
    }

    private final StageLyrics getStageLyrics() {
        return (StageLyrics) this.stageLyrics.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StageXRayManager getStageXrayManager() {
        return (StageXRayManager) this.stageXrayManager.getValue();
    }

    private final boolean isVideoTrack() {
        return NowPlayingUtil.isVideoPlaying();
    }

    private final FrameLayout getLandscapeAudioBackgroundOverlay() {
        return (FrameLayout) this.landscapeAudioBackgroundOverlay.getValue();
    }

    public final ConstraintLayout getStageContainerLayout() {
        return (ConstraintLayout) this.stageContainerLayout.getValue();
    }

    private final StageLandscapeControlsView getLandscapeControlsView() {
        return (StageLandscapeControlsView) this.landscapeControlsView.getValue();
    }

    public final FrameLayout getStageAlbumArtContainer() {
        return (FrameLayout) this.stageAlbumArtContainer.getValue();
    }

    private final BaseImage getAlbumArtView() {
        return (BaseImage) this.albumArtView.getValue();
    }

    private final FrameLayout getOverlayContainerView() {
        return (FrameLayout) this.overlayContainerView.getValue();
    }

    private final ConstraintLayout getPlayerCoverViewRoot() {
        return (ConstraintLayout) this.playerCoverViewRoot.getValue();
    }

    private final StageBottomButtonsView getBottomButtonsContainerView() {
        return (StageBottomButtonsView) this.bottomButtonsContainerView.getValue();
    }

    private final StagePortraitPlaybackControlsView getPlaybackControlsView() {
        return (StagePortraitPlaybackControlsView) this.playbackControlsView.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StageOverlayButtonsLayout getOverlayButtonsContainerLayout() {
        return (StageOverlayButtonsLayout) this.overlayButtonsContainerLayout.getValue();
    }

    private final BaseButton getLyricsOverlayButton() {
        return (BaseButton) this.lyricsOverlayButton.getValue();
    }

    private final void setPlayPauseProgressBar(ProgressBar progressBar) {
        ProgressBar progressBar2 = this.playPauseProgressBar;
        if (progressBar2 != null) {
            progressBar2.setVisibility(8);
        }
        this.playPauseProgressBar = progressBar;
    }

    private final BaseButton getOverflowButton() {
        return (BaseButton) this.overflowButton.getValue();
    }

    private final BaseButton getLikeButton() {
        return (BaseButton) this.likeButton.getValue();
    }

    private final BaseButton getAlexaButton() {
        return (BaseButton) this.alexaButton.getValue();
    }

    private final BaseButton getBackButton() {
        return (BaseButton) this.backButton.getValue();
    }

    private final TextView getControlTipView() {
        return (TextView) this.controlTipView.getValue();
    }

    private final FrameLayout getArtistTreatmentView() {
        return (FrameLayout) this.artistTreatmentView.getValue();
    }

    private final ImageView getArtistImageView() {
        return (ImageView) this.artistImageView.getValue();
    }

    private final ImageView getArtistImageViewBlurred() {
        return (ImageView) this.artistImageViewBlurred.getValue();
    }

    private final ImageView getAlbumArtBlurredView() {
        return (ImageView) this.albumArtBlurredView.getValue();
    }

    private final LinearLayout getPlayerMetadataContainer() {
        return (LinearLayout) this.playerMetadataContainer.getValue();
    }

    private final BauhausSeekBarWidget getSeekbarView() {
        return (BauhausSeekBarWidget) this.seekbarView.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayerMetadataView getLandscapePlayerMetadata() {
        return (PlayerMetadataView) this.landscapePlayerMetadata.getValue();
    }

    public final PlayerMetadataView getPlayerMetadata() {
        return (PlayerMetadataView) this.playerMetadata.getValue();
    }

    public final PublishSubject<Boolean> getOnNextClickedSubject() {
        return this.onNextClickedSubject;
    }

    public final PublishSubject<Bitmap> getBlurAlbumArtSubject() {
        return this.blurAlbumArtSubject;
    }

    public final Observable<Triple<StageDataModel.State, StageDataModel.State, StageDataModel.State>> getStateObservable() {
        Observable<Triple<StageDataModel.State, StageDataModel.State, StageDataModel.State>> observable = this.stateObservable;
        if (observable != null) {
            return observable;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stateObservable");
        return null;
    }

    public final void setStateObservable(Observable<Triple<StageDataModel.State, StageDataModel.State, StageDataModel.State>> observable) {
        Intrinsics.checkNotNullParameter(observable, "<set-?>");
        this.stateObservable = observable;
    }

    public final Context getContext() {
        return this.context;
    }

    public final StageViewModel getStageViewModel() {
        return this.stageViewModel;
    }

    public final TouchEventHandler getTouchEventHandler() {
        return this.touchEventHandler;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: touchEventHandler$lambda-3$lambda-2$lambda-1, reason: not valid java name */
    public static final void m1793touchEventHandler$lambda3$lambda2$lambda1(StageViewPresenter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handleTouchEvent();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: castingButtonClickListener$lambda-5, reason: not valid java name */
    public static final void m1766castingButtonClickListener$lambda5(StageViewPresenter this$0, View view) {
        StageXRayManager stageXrayManager;
        StageSwipeablePagesView xrayContainer;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getOverlayTypeStateProvider().getCurrentOverlay().getValue() == OverlayType.XRAY_PARTIAL && (stageXrayManager = this$0.getStageXrayManager()) != null && (xrayContainer = stageXrayManager.getXrayContainer()) != null) {
            xrayContainer.impressionEnded(ImpressionEndReason.AUTO, false);
        }
        OverlayType overlayType = OverlayType.CASTING;
        this$0.adjustOverlayContainerPosition(overlayType);
        CastingFragment castingFragment = new CastingFragment();
        castingFragment.isStageCustomToolbar = Boolean.TRUE;
        addScreenOverlay$default(this$0, new OverlayContainer(new OverlayViewType.FragmentOverlay(castingFragment, overlayType)), false, false, false, 14, null);
        FrameLayout stageAlbumArtContainer = this$0.getStageAlbumArtContainer();
        if (stageAlbumArtContainer != null) {
            stageAlbumArtContainer.setAlpha(0.0f);
        }
        CastingUIMetricsLogger.recordCastingButtonClickMetric(CastingUIMetricsLogger.ContentName.NOW_PLAYING_STAGE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playQueueOnClickListener$lambda-6, reason: not valid java name */
    public static final void m1789playQueueOnClickListener$lambda6(StageViewPresenter this$0, View view) {
        StageXRayManager stageXrayManager;
        StageSwipeablePagesView xrayContainer;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getOverlayTypeStateProvider().getCurrentOverlay().getValue() == OverlayType.XRAY_PARTIAL && (stageXrayManager = this$0.getStageXrayManager()) != null && (xrayContainer = stageXrayManager.getXrayContainer()) != null) {
            xrayContainer.impressionEnded(ImpressionEndReason.AUTO, false);
        }
        OverlayType overlayType = OverlayType.PLAY_QUEUE;
        this$0.adjustOverlayContainerPosition(overlayType);
        addScreenOverlay$default(this$0, new OverlayContainer(new OverlayViewType.FragmentOverlay(new PlayQueueFragment(), overlayType)), false, false, false, 6, null);
        FrameLayout stageAlbumArtContainer = this$0.getStageAlbumArtContainer();
        if (stageAlbumArtContainer != null) {
            stageAlbumArtContainer.setAlpha(0.0f);
        }
        UiClickEvent.Builder builderWithEntityType = UiClickEvent.builder(ActionType.GO_PLAY_QUEUE).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withEntityType(EntityType.TRACK);
        builderWithEntityType.withPageType(PageType.NOW_PLAYING_STAGE);
        MetricsHolder.getManager().handleEvent(builderWithEntityType.build());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: openLyricsSelectionFragmentSubscription$lambda-7, reason: not valid java name */
    public static final void m1788openLyricsSelectionFragmentSubscription$lambda7(StageViewPresenter this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.showLyricsSelectionOverlay(0, it.booleanValue(), false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: openLyricsCardFragmentSubscription$lambda-8, reason: not valid java name */
    public static final void m1787openLyricsCardFragmentSubscription$lambda8(StageViewPresenter this$0, Integer it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.showLyricsCardSelectionOverlay(it.intValue());
    }

    public final OverlayTypeStateProvider getOverlayTypeStateProvider() {
        OverlayTypeStateProvider overlayTypeStateProvider = this.overlayTypeStateProvider;
        if (overlayTypeStateProvider != null) {
            return overlayTypeStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayTypeStateProvider");
        return null;
    }

    public final OverlayXrayStateProvider getOverlayXrayStateProvider() {
        OverlayXrayStateProvider overlayXrayStateProvider = this.overlayXrayStateProvider;
        if (overlayXrayStateProvider != null) {
            return overlayXrayStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayXrayStateProvider");
        return null;
    }

    public final OverlayLyricsStateProvider getOverlayLyricsStateProvider() {
        OverlayLyricsStateProvider overlayLyricsStateProvider = this.overlayLyricsStateProvider;
        if (overlayLyricsStateProvider != null) {
            return overlayLyricsStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("overlayLyricsStateProvider");
        return null;
    }

    public final StageAutomatedSequencingHandler getStageAutomatedSequencingHandler() {
        StageAutomatedSequencingHandler stageAutomatedSequencingHandler = this.stageAutomatedSequencingHandler;
        if (stageAutomatedSequencingHandler != null) {
            return stageAutomatedSequencingHandler;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stageAutomatedSequencingHandler");
        return null;
    }

    public final StageOverflowMenuSecondaryIconStateProvider getStageOverflowMenuSecondaryIconStateProvider() {
        StageOverflowMenuSecondaryIconStateProvider stageOverflowMenuSecondaryIconStateProvider = this.stageOverflowMenuSecondaryIconStateProvider;
        if (stageOverflowMenuSecondaryIconStateProvider != null) {
            return stageOverflowMenuSecondaryIconStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("stageOverflowMenuSecondaryIconStateProvider");
        return null;
    }

    public final LyricsSharingDataModel getLyricsSharingDataModel() {
        LyricsSharingDataModel lyricsSharingDataModel = this.lyricsSharingDataModel;
        if (lyricsSharingDataModel != null) {
            return lyricsSharingDataModel;
        }
        Intrinsics.throwUninitializedPropertyAccessException("lyricsSharingDataModel");
        return null;
    }

    public final void onCreate(StyleSheet styleSheet) {
        io.reactivex.rxjava3.core.Observable<StageLyrics.LyricsState> lyricsObservable;
        io.reactivex.rxjava3.core.Observable<StageLyrics.LyricsState> observableSubscribeOn;
        StageLyrics stageLyrics;
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        StageLyrics stageLyrics2;
        BehaviorSubject<StageDataModel.State> artistImageAvailability;
        CompositeSubscription compositeSubscription;
        BehaviorSubject<StageDataModel.State> xRayAvailability;
        CompositeSubscription compositeSubscription2;
        BehaviorSubject<StageDataModel.State> lyricsAvailability;
        CompositeSubscription compositeSubscription3;
        PublishSubject<Triple<MediaItem, View, Boolean>> xrayContainerSubject;
        Observable<Triple<MediaItem, View, Boolean>> observableObserveOn;
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease2;
        StageBottomButtonsView bottomButtonsContainerView;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Log.debug(this.TAG, "onCreate()");
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
        this.styleSheet = styleSheet;
        Context context = this.context;
        this.landscapeType = context == null ? null : styleSheet.getLandscapeType(context);
        StageBottomButtonsView bottomButtonsContainerView2 = getBottomButtonsContainerView();
        if (bottomButtonsContainerView2 != null) {
            bottomButtonsContainerView2.setStageOverflowMenuSecondaryIconStateProvider(getStageOverflowMenuSecondaryIconStateProvider());
        }
        StageBottomButtonsView bottomButtonsContainerView3 = getBottomButtonsContainerView();
        if (bottomButtonsContainerView3 != null) {
            StageBottomButtonsView.initViewStyling$default(bottomButtonsContainerView3, styleSheet, false, 2, null);
        }
        StageBottomButtonsView bottomButtonsContainerView4 = getBottomButtonsContainerView();
        if (bottomButtonsContainerView4 != null) {
            io.reactivex.rxjava3.subjects.PublishSubject<Integer> openLyricsCardSelectionFragmentSubject = this.openLyricsCardSelectionFragmentSubject;
            Intrinsics.checkNotNullExpressionValue(openLyricsCardSelectionFragmentSubject, "openLyricsCardSelectionFragmentSubject");
            bottomButtonsContainerView4.setOpenLyricsCardSelectionFragmentSubject(openLyricsCardSelectionFragmentSubject);
        }
        final Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null && (bottomButtonsContainerView = getBottomButtonsContainerView()) != null) {
            bottomButtonsContainerView.setLifecycleOwner(new LifecycleOwner() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda9
                @Override // androidx.lifecycle.LifecycleOwner
                public final Lifecycle getLifecycle() {
                    return StageViewPresenter.m1768onCreate$lambda11$lambda10(lifecycle);
                }
            });
        }
        setBottomButtonsOnClickListener();
        StagePortraitPlaybackControlsView playbackControlsView = getPlaybackControlsView();
        if (playbackControlsView != null) {
            playbackControlsView.initViewStyling(styleSheet);
        }
        StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout != null) {
            overlayButtonsContainerLayout.initViewStyling(styleSheet);
        }
        StageLyrics stageLyrics3 = getStageLyrics();
        if (stageLyrics3 != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease2 = stageLyrics3.getLyricsViewContainer()) != null) {
            lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease2.initStyleSheet(styleSheet);
        }
        setupLikeButton(styleSheet);
        StageXRayManager stageXrayManager = getStageXrayManager();
        if (stageXrayManager != null) {
            stageXrayManager.setXrayWindowInteractionListener(this.xrayWindowInteractionListener);
        }
        StageXRayManager stageXrayManager2 = getStageXrayManager();
        if (stageXrayManager2 != null) {
            stageXrayManager2.initViewStyling(styleSheet);
        }
        StageXRayManager stageXrayManager3 = getStageXrayManager();
        if (stageXrayManager3 != null) {
            stageXrayManager3.setOverlayButtonContainer(getOverlayButtonsContainerLayout());
        }
        StageXRayManager stageXrayManager4 = getStageXrayManager();
        if (stageXrayManager4 != null && (xrayContainerSubject = stageXrayManager4.getXrayContainerSubject()) != null && (observableObserveOn = xrayContainerSubject.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())) != null) {
            observableObserveOn.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda20
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    this.f$0.showXray((Triple) obj);
                }
            });
        }
        if (ScreenUtil.isLandscape()) {
            initLandscapeStageView(styleSheet);
            fadeInLandscapeViews();
        }
        this.stageViewModel.onCreate();
        this.stageViewModel.updatePlaybackButton();
        setupTouchHandlers();
        setupPlaybackControlsClickListeners();
        BaseButton lyricsOverlayButton = getLyricsOverlayButton();
        if (lyricsOverlayButton != null) {
            lyricsOverlayButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda21
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StageViewPresenter.m1769onCreate$lambda12(this.f$0, view);
                }
            });
        }
        BaseButton alexaButton = getAlexaButton();
        if (alexaButton != null) {
            alexaButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda22
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StageViewPresenter.m1770onCreate$lambda13(this.f$0, view);
                }
            });
        }
        setupLoadingStageBackground();
        initializeAlexa();
        StageLyrics stageLyrics4 = getStageLyrics();
        if (stageLyrics4 != null) {
            stageLyrics4.startListeningToPlaybackChanges();
        }
        if (ScreenUtil.isPortrait() || !isVideoTrack()) {
            setupStageToolbar(styleSheet);
        }
        if (NowPlayingUtil.isSmallScreen(this.context) && ScreenUtil.isPortrait()) {
            resizeViewsForSmallScreens();
        }
        CompositeSubscription compositeSubscription4 = this.stateSubscription;
        if (compositeSubscription4 != null) {
            compositeSubscription4.add(this.blurAlbumArtSubject.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda23
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1771onCreate$lambda14(this.f$0, (Bitmap) obj);
                }
            }));
        }
        CompositeSubscription compositeSubscription5 = this.stateSubscription;
        if (compositeSubscription5 != null) {
            compositeSubscription5.add(this.overlayTypeSubject.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda24
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1772onCreate$lambda16(this.f$0, (OverlayType) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda25
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1773onCreate$lambda17(this.f$0, (Throwable) obj);
                }
            }));
        }
        StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
        if (stageDataModel != null && (lyricsAvailability = stageDataModel.getLyricsAvailability()) != null && (compositeSubscription3 = this.stateSubscription) != null) {
            compositeSubscription3.add(lyricsAvailability.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda26
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1774onCreate$lambda20$lambda18(this.f$0, (StageDataModel.State) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda27
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1775onCreate$lambda20$lambda19(this.f$0, (Throwable) obj);
                }
            }));
        }
        StageDataModel stageDataModel2 = this.stageViewModel.getStageDataModel();
        if (stageDataModel2 != null && (xRayAvailability = stageDataModel2.getXRayAvailability()) != null && (compositeSubscription2 = this.stateSubscription) != null) {
            compositeSubscription2.add(xRayAvailability.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda28
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1776onCreate$lambda23$lambda21(this.f$0, (StageDataModel.State) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda10
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1777onCreate$lambda23$lambda22(this.f$0, (Throwable) obj);
                }
            }));
        }
        StageDataModel stageDataModel3 = this.stageViewModel.getStageDataModel();
        if (stageDataModel3 != null && (artistImageAvailability = stageDataModel3.getArtistImageAvailability()) != null && (compositeSubscription = this.stateSubscription) != null) {
            compositeSubscription.add(artistImageAvailability.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda11
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1778onCreate$lambda26$lambda24(this.f$0, (StageDataModel.State) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda12
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1779onCreate$lambda26$lambda25(this.f$0, (Throwable) obj);
                }
            }));
        }
        StageDataModel stageDataModel4 = this.stageViewModel.getStageDataModel();
        BehaviorSubject<StageDataModel.State> lyricsAvailability2 = stageDataModel4 == null ? null : stageDataModel4.getLyricsAvailability();
        StageDataModel stageDataModel5 = this.stageViewModel.getStageDataModel();
        BehaviorSubject<StageDataModel.State> xRayAvailability2 = stageDataModel5 == null ? null : stageDataModel5.getXRayAvailability();
        StageDataModel stageDataModel6 = this.stageViewModel.getStageDataModel();
        Observable<Triple<StageDataModel.State, StageDataModel.State, StageDataModel.State>> observableDistinctUntilChanged = Observable.combineLatest(lyricsAvailability2, xRayAvailability2, stageDataModel6 == null ? null : stageDataModel6.getAlbumArtAvailability(), new Func3() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda13
            @Override // rx.functions.Func3
            public final Object call(Object obj, Object obj2, Object obj3) {
                return StageViewPresenter.m1780onCreate$lambda27((StageDataModel.State) obj, (StageDataModel.State) obj2, (StageDataModel.State) obj3);
            }
        }).observeOn(rx.android.schedulers.AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io()).distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "combineLatest(\n         …  .distinctUntilChanged()");
        setStateObservable(observableDistinctUntilChanged);
        CompositeSubscription compositeSubscription6 = this.stateSubscription;
        if (compositeSubscription6 != null) {
            compositeSubscription6.add(getStateObservable().subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda14
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1781onCreate$lambda28(this.f$0, (Triple) obj);
                }
            }, new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda15
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageViewPresenter.m1782onCreate$lambda29(this.f$0, (Throwable) obj);
                }
            }));
        }
        StageLyrics stageLyrics5 = getStageLyrics();
        this.lyricsDisposable = (stageLyrics5 == null || (lyricsObservable = stageLyrics5.getLyricsObservable()) == null || (observableSubscribeOn = lyricsObservable.subscribeOn(io.reactivex.rxjava3.schedulers.Schedulers.io())) == null) ? null : observableSubscribeOn.subscribe(new Consumer() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda16
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageViewPresenter.m1783onCreate$lambda30(this.f$0, (StageLyrics.LyricsState) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda17
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageViewPresenter.m1784onCreate$lambda31(this.f$0, (Throwable) obj);
            }
        });
        if (isVideoTrack()) {
            StageOverlayButtonsLayout overlayButtonsContainerLayout2 = getOverlayButtonsContainerLayout();
            if (overlayButtonsContainerLayout2 != null) {
                overlayButtonsContainerLayout2.setVisibility(8);
            }
            if (ScreenUtil.isLandscape()) {
                StagePortraitPlaybackControlsView stagePortraitPlaybackControlsView = this.videoPlaybackControlsView;
                if (stagePortraitPlaybackControlsView != null) {
                    stagePortraitPlaybackControlsView.setVisibility(8);
                }
                StageBottomButtonsView bottomButtonsContainerView5 = getBottomButtonsContainerView();
                if (bottomButtonsContainerView5 != null) {
                    bottomButtonsContainerView5.setVisibility(8);
                }
            } else {
                StagePortraitPlaybackControlsView stagePortraitPlaybackControlsView2 = this.videoPlaybackControlsView;
                if (stagePortraitPlaybackControlsView2 != null) {
                    stagePortraitPlaybackControlsView2.setVisibility(0);
                }
                StageBottomButtonsView bottomButtonsContainerView6 = getBottomButtonsContainerView();
                if (bottomButtonsContainerView6 != null) {
                    bottomButtonsContainerView6.setVisibility(0);
                }
            }
        }
        this.overlaySequenceSubscription = this.overlaySequenceSubject.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda18
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StageViewPresenter.m1785onCreate$lambda32(this.f$0, (StageViewPresenter.OverlaySequence) obj);
            }
        }, new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda19
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StageViewPresenter.m1786onCreate$lambda33(this.f$0, (Throwable) obj);
            }
        });
        Context context2 = this.context;
        this.stageTooltipPresenter = context2 != null ? new StageTooltipPresenter(context2, styleSheet) : null;
        if (Intrinsics.areEqual(getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.Lyrics.INSTANCE) && (stageLyrics2 = getStageLyrics()) != null) {
            stageLyrics2.setShowFullLyricsOnOverlays(true);
        }
        updatePlayerControls(this.stageViewModel.getEnabledStateManager());
        startListeningToSkipLimitUpdates();
        if (this.stageViewModel.isAdOrInterLude()) {
            setUpStateForAdOrInterlude();
        }
        if (getOverlayLyricsStateProvider().getShouldShowTapToViewLyrics() && (stageLyrics = getStageLyrics()) != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics.getLyricsViewContainer()) != null) {
            lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.setHasUserInteracted(false);
        }
        if (AmazonApplication.getCapabilities().isLyricsSharingEnabled()) {
            LyricsSharingDataRepository.INSTANCE.fetchLyricsShareCardsBackgroundBitmaps();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-12, reason: not valid java name */
    public static final void m1769onCreate$lambda12(StageViewPresenter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onLyricsOverlayButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-13, reason: not valid java name */
    public static final void m1770onCreate$lambda13(StageViewPresenter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onAlexaButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-14, reason: not valid java name */
    public static final void m1771onCreate$lambda14(StageViewPresenter this$0, Bitmap bitmap) {
        BehaviorSubject<StageDataModel.State> albumArtAvailability;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        StageDataModel stageDataModel = this$0.stageViewModel.getStageDataModel();
        if (stageDataModel != null) {
            stageDataModel.setBlurAlbumArt(bitmap);
        }
        StageBottomButtonsView bottomButtonsContainerView = this$0.getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            bottomButtonsContainerView.setBlurAlbumArt(bitmap);
        }
        StageDataModel stageDataModel2 = this$0.stageViewModel.getStageDataModel();
        if (stageDataModel2 != null && (albumArtAvailability = stageDataModel2.getAlbumArtAvailability()) != null) {
            albumArtAvailability.onNext(StageDataModel.State.AVAILABLE);
        }
        if (this$0.isOffline()) {
            ImageView albumArtBlurredView = this$0.getAlbumArtBlurredView();
            if (albumArtBlurredView != null) {
                albumArtBlurredView.setVisibility(0);
            }
            ImageView albumArtBlurredView2 = this$0.getAlbumArtBlurredView();
            if (albumArtBlurredView2 != null) {
                StageDataModel stageDataModel3 = this$0.stageViewModel.getStageDataModel();
                albumArtBlurredView2.setImageBitmap(stageDataModel3 == null ? null : stageDataModel3.getBlurAlbumArt());
            }
            ImageView albumArtBlurredView3 = this$0.getAlbumArtBlurredView();
            if (albumArtBlurredView3 == null) {
                return;
            }
            albumArtBlurredView3.setAlpha(0.9f);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-16, reason: not valid java name */
    public static final void m1772onCreate$lambda16(StageViewPresenter this$0, OverlayType overlayType) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (overlayType == null) {
            return;
        }
        this$0.handleOverlayTypeChange(overlayType);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-17, reason: not valid java name */
    public static final void m1773onCreate$lambda17(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "overlayTypeSubject failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-20$lambda-18, reason: not valid java name */
    public static final void m1774onCreate$lambda20$lambda18(StageViewPresenter this$0, StageDataModel.State state) {
        StageDataModel stageDataModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (state == StageDataModel.State.AVAILABLE) {
            StageDataModel stageDataModel2 = this$0.stageViewModel.getStageDataModel();
            if (stageDataModel2 == null) {
                return;
            }
            stageDataModel2.setLyrics(true);
            return;
        }
        if (state != StageDataModel.State.UNAVAILABLE || (stageDataModel = this$0.stageViewModel.getStageDataModel()) == null) {
            return;
        }
        stageDataModel.setLyrics(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-20$lambda-19, reason: not valid java name */
    public static final void m1775onCreate$lambda20$lambda19(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "lyricsAvailability failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-23$lambda-21, reason: not valid java name */
    public static final void m1776onCreate$lambda23$lambda21(StageViewPresenter this$0, StageDataModel.State state) {
        StageDataModel stageDataModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (state == StageDataModel.State.AVAILABLE) {
            StageDataModel stageDataModel2 = this$0.stageViewModel.getStageDataModel();
            if (stageDataModel2 == null) {
                return;
            }
            stageDataModel2.setXray(true);
            return;
        }
        if (state != StageDataModel.State.UNAVAILABLE || (stageDataModel = this$0.stageViewModel.getStageDataModel()) == null) {
            return;
        }
        stageDataModel.setXray(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-23$lambda-22, reason: not valid java name */
    public static final void m1777onCreate$lambda23$lambda22(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "xRayAvailability failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-26$lambda-24, reason: not valid java name */
    public static final void m1778onCreate$lambda26$lambda24(StageViewPresenter this$0, StageDataModel.State state) {
        StageDataModel stageDataModel;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (state == StageDataModel.State.AVAILABLE) {
            StageDataModel stageDataModel2 = this$0.stageViewModel.getStageDataModel();
            if (stageDataModel2 == null) {
                return;
            }
            stageDataModel2.setArtistImage(true);
            return;
        }
        if (state != StageDataModel.State.UNAVAILABLE || (stageDataModel = this$0.stageViewModel.getStageDataModel()) == null) {
            return;
        }
        stageDataModel.setArtistImage(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-26$lambda-25, reason: not valid java name */
    public static final void m1779onCreate$lambda26$lambda25(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "artistImageAvailability failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-27, reason: not valid java name */
    public static final Triple m1780onCreate$lambda27(StageDataModel.State state, StageDataModel.State state2, StageDataModel.State state3) {
        return new Triple(state, state2, state3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-28, reason: not valid java name */
    public static final void m1781onCreate$lambda28(StageViewPresenter this$0, Triple triple) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.debug(this$0.TAG, "stateObservable- lyrics " + triple.getFirst() + ", xray - " + triple.getSecond() + ", albumArt - " + triple.getThird());
        Object first = triple.getFirst();
        StageDataModel.State state = StageDataModel.State.UNINITIALIZED;
        if (first == state || triple.getSecond() == state || triple.getThird() == state) {
            return;
        }
        Log.debug(this$0.TAG, Intrinsics.stringPlus("stateObservable- Ready to setup stage UI with StageDataModel - ", this$0.stageViewModel.getStageDataModel()));
        this$0.setupInitialStageBackground();
        this$0.presentInitialOverlays();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-29, reason: not valid java name */
    public static final void m1782onCreate$lambda29(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "stateObservable failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-30, reason: not valid java name */
    public static final void m1783onCreate$lambda30(StageViewPresenter this$0, StageLyrics.LyricsState lyricsState) {
        BehaviorSubject<StageDataModel.State> lyricsAvailability;
        BehaviorSubject<StageDataModel.State> lyricsAvailability2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = lyricsState == null ? -1 : WhenMappings.$EnumSwitchMapping$0[lyricsState.ordinal()];
        if (i == 2) {
            if (Intrinsics.areEqual(this$0.getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.Lyrics.INSTANCE) || this$0.getOverlayLyricsStateProvider().getFrugalLyricsCompleted()) {
                return;
            }
            this$0.getOverlayLyricsStateProvider().setFrugalLyricsCompleted(true);
            if (!this$0.isInFullScreenOverlayMode()) {
                this$0.showHideLyricsPartialOverlay(false, true);
                this$0.overlayTypeSubject.onNext(this$0.getNextOverlay());
                this$0.overlaySequenceSubject.onNext(OverlaySequence.FRUGAL_LYRICS_FINISHED);
                return;
            } else {
                this$0.getStageAutomatedSequencingHandler().setAutomatedSequenceCurrentOverlay(this$0.getNextOverlay());
                this$0.deselectLyricsPill();
                return;
            }
        }
        if (i == 3) {
            BaseButton lyricsOverlayButton = this$0.getLyricsOverlayButton();
            if (lyricsOverlayButton != null) {
                lyricsOverlayButton.setVisibility(8);
            }
            StageDataModel stageDataModel = this$0.stageViewModel.getStageDataModel();
            if (stageDataModel == null || (lyricsAvailability = stageDataModel.getLyricsAvailability()) == null) {
                return;
            }
            lyricsAvailability.onNext(StageDataModel.State.UNAVAILABLE);
            return;
        }
        if (i != 4) {
            return;
        }
        BaseButton lyricsOverlayButton2 = this$0.getLyricsOverlayButton();
        if (lyricsOverlayButton2 != null) {
            lyricsOverlayButton2.setVisibility(0);
        }
        StageDataModel stageDataModel2 = this$0.stageViewModel.getStageDataModel();
        if (stageDataModel2 == null || (lyricsAvailability2 = stageDataModel2.getLyricsAvailability()) == null) {
            return;
        }
        lyricsAvailability2.onNext(StageDataModel.State.AVAILABLE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-31, reason: not valid java name */
    public static final void m1784onCreate$lambda31(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "stageLyrics lyricsObservable failed", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-32, reason: not valid java name */
    public static final void m1785onCreate$lambda32(StageViewPresenter this$0, OverlaySequence overlaySequence) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(overlaySequence, "overlaySequence");
        this$0.handleAutomaticSequencing(overlaySequence);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreate$lambda-33, reason: not valid java name */
    public static final void m1786onCreate$lambda33(StageViewPresenter this$0, Throwable th) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Log.error(this$0.TAG, "overlay sequence observable failed", th);
    }

    private final void setBottomButtonsOnClickListener() {
        PlayQueueButton playQueueButton;
        CastingBaseButton castingBaseButton;
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        boolean z = false;
        if (currentMediaItem != null && !MediaItemExtensions.isVideo(currentMediaItem)) {
            z = true;
        }
        if (z) {
            StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
            if (bottomButtonsContainerView != null && (castingBaseButton = (CastingBaseButton) bottomButtonsContainerView.findViewById(R.id.CastingButton)) != null) {
                castingBaseButton.setOnCastingButtonClickListener(this.castingButtonClickListener);
            }
            StageBottomButtonsView bottomButtonsContainerView2 = getBottomButtonsContainerView();
            if (bottomButtonsContainerView2 == null || (playQueueButton = (PlayQueueButton) bottomButtonsContainerView2.findViewById(R.id.PersistentPlayerPlayQueueButton)) == null) {
                return;
            }
            playQueueButton.setOnClickListener(this.playQueueOnClickListener);
        }
    }

    private final void resizeViewsForSmallScreens() {
        Resources resources;
        Resources resources2;
        Resources resources3;
        Resources resources4;
        Resources resources5;
        Context context = this.context;
        int dimensionPixelSize = (context == null || (resources = context.getResources()) == null) ? 0 : resources.getDimensionPixelSize(R.dimen.spacer_2);
        BaseImage albumArtView = getAlbumArtView();
        if (albumArtView != null) {
            Context context2 = getContext();
            int dimensionPixelSize2 = (context2 == null || (resources4 = context2.getResources()) == null) ? 0 : resources4.getDimensionPixelSize(R.dimen.stage_artwork_image_height_small);
            Context context3 = getContext();
            setViewSize(albumArtView, dimensionPixelSize2, (context3 == null || (resources5 = context3.getResources()) == null) ? 0 : resources5.getDimensionPixelSize(R.dimen.stage_artwork_image_height_small));
        }
        ProgressBar progressBar = this.playPauseProgressBar;
        if (progressBar != null) {
            Context context4 = getContext();
            int dimensionPixelSize3 = (context4 == null || (resources2 = context4.getResources()) == null) ? 0 : resources2.getDimensionPixelSize(R.dimen.spacer_48);
            Context context5 = getContext();
            setViewSize(progressBar, dimensionPixelSize3, (context5 == null || (resources3 = context5.getResources()) == null) ? 0 : resources3.getDimensionPixelSize(R.dimen.spacer_48));
        }
        ConstraintLayout stageContainerLayout = getStageContainerLayout();
        if (stageContainerLayout != null) {
            setViewMargins(stageContainerLayout, dimensionPixelSize, dimensionPixelSize);
        }
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            setViewMargins(bottomButtonsContainerView, dimensionPixelSize, 0);
        }
        BauhausSeekBarWidget seekbarView = getSeekbarView();
        if (seekbarView != null) {
            setViewMargins(seekbarView, 0, dimensionPixelSize);
        }
        StagePortraitPlaybackControlsView playbackControlsView = getPlaybackControlsView();
        if (playbackControlsView != null) {
            setViewMargins(playbackControlsView, 0, dimensionPixelSize);
        }
        StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout == null) {
            return;
        }
        setViewMargins(overlayButtonsContainerLayout, 0, 0);
    }

    private final OverlayType getNextOverlay() {
        StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
        boolean z = false;
        if (stageDataModel != null && stageDataModel.getIsXray()) {
            z = true;
        }
        if (z) {
            return OverlayType.XRAY_PARTIAL;
        }
        return OverlayType.ALBUM_ART;
    }

    private final void setViewMargins(ConstraintLayout view, int topMargin, int bottomMargin) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        ((ViewGroup.MarginLayoutParams) layoutParams2).topMargin = topMargin;
        ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = bottomMargin;
        view.setLayoutParams(layoutParams2);
    }

    private final void setViewSize(View view, int height, int width) {
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.height = height;
        }
        if (layoutParams != null) {
            layoutParams.width = width;
        }
        view.setLayoutParams(layoutParams);
    }

    private final void startListeningToSkipLimitUpdates() {
        SkipLimitProviderImpl.INSTANCE.addOnSkipLimitUpdateListener(this.mSkipLimitUpdateListener);
    }

    public final void showVideoView(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Log.debug(this.TAG, "showVideoView()");
        setupLoadingStageBackground();
        StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout != null) {
            overlayButtonsContainerLayout.setVisibility(8);
        }
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            StageBottomButtonsView.initViewStyling$default(bottomButtonsContainerView, styleSheet, false, 2, null);
        }
        setBottomButtonsOnClickListener();
        FrameLayout stageAlbumArtContainer = getStageAlbumArtContainer();
        if (stageAlbumArtContainer != null) {
            stageAlbumArtContainer.setVisibility(0);
        }
        FrameLayout overlayContainerView = getOverlayContainerView();
        if (overlayContainerView != null) {
            overlayContainerView.setVisibility(8);
        }
        if (ScreenUtil.isLandscape()) {
            StagePortraitPlaybackControlsView stagePortraitPlaybackControlsView = this.videoPlaybackControlsView;
            if (stagePortraitPlaybackControlsView != null) {
                stagePortraitPlaybackControlsView.setVisibility(8);
            }
            StageBottomButtonsView bottomButtonsContainerView2 = getBottomButtonsContainerView();
            if (bottomButtonsContainerView2 != null) {
                bottomButtonsContainerView2.setVisibility(8);
            }
        }
        setupLikeButton(styleSheet);
        hideAudioQualityBadge();
    }

    public final void showAudioView(StyleSheet styleSheet) {
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        Log.debug(this.TAG, "showAudioView()");
        setupLoadingStageBackground();
        StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout != null) {
            overlayButtonsContainerLayout.setVisibility(0);
        }
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            StageBottomButtonsView.initViewStyling$default(bottomButtonsContainerView, styleSheet, false, 2, null);
        }
        setBottomButtonsOnClickListener();
        FrameLayout overlayContainerView = getOverlayContainerView();
        if (overlayContainerView != null) {
            overlayContainerView.setVisibility(0);
        }
        StageLyrics stageLyrics = getStageLyrics();
        if (stageLyrics != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics.getLyricsViewContainer()) != null) {
            lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.initStyleSheet(styleSheet);
        }
        StageLyrics stageLyrics2 = getStageLyrics();
        if (stageLyrics2 != null) {
            stageLyrics2.startListeningToPlaybackChanges();
        }
        setupLikeButton(styleSheet);
    }

    private final void setupLoadingStageBackground() {
        FrameLayout stageAlbumArtContainer;
        if (isVideoTrack()) {
            FrameLayout stageAlbumArtContainer2 = getStageAlbumArtContainer();
            if (stageAlbumArtContainer2 != null) {
                stageAlbumArtContainer2.setAlpha(1.0f);
            }
            FrameLayout overlayContainerView = getOverlayContainerView();
            if (overlayContainerView != null) {
                overlayContainerView.setVisibility(8);
            }
            if (ScreenUtil.isLandscape()) {
                ConstraintLayout stageContainerLayout = getStageContainerLayout();
                if (stageContainerLayout == null) {
                    return;
                }
                stageContainerLayout.setVisibility(8);
                return;
            }
            ConstraintLayout stageContainerLayout2 = getStageContainerLayout();
            if (stageContainerLayout2 == null) {
                return;
            }
            stageContainerLayout2.setVisibility(0);
            return;
        }
        if (Intrinsics.areEqual(getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.None.INSTANCE)) {
            FrameLayout stageAlbumArtContainer3 = getStageAlbumArtContainer();
            if (stageAlbumArtContainer3 != null) {
                ViewExtensions.fadeIn(stageAlbumArtContainer3);
            }
        } else if (!this.stageViewModel.isAdOrInterLude() && (stageAlbumArtContainer = getStageAlbumArtContainer()) != null) {
            stageAlbumArtContainer.setVisibility(8);
        }
        ConstraintLayout stageContainerLayout3 = getStageContainerLayout();
        if (stageContainerLayout3 == null) {
            return;
        }
        stageContainerLayout3.setVisibility(0);
    }

    private final void resetBackgrounds() {
        FrameLayout stageAlbumArtContainer;
        if (!Intrinsics.areEqual(getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.None.INSTANCE) && (stageAlbumArtContainer = getStageAlbumArtContainer()) != null) {
            ViewExtensions.fadeOut(stageAlbumArtContainer);
        }
        ImageView albumArtBlurredView = getAlbumArtBlurredView();
        if (albumArtBlurredView != null) {
            albumArtBlurredView.setImageDrawable(null);
        }
        ImageView artistImageView = getArtistImageView();
        if (artistImageView != null) {
            artistImageView.setImageDrawable(null);
        }
        ImageView artistImageViewBlurred = getArtistImageViewBlurred();
        if (artistImageViewBlurred == null) {
            return;
        }
        artistImageViewBlurred.setImageDrawable(null);
    }

    private final void setupInitialStageBackground() {
        if (isVideoTrack()) {
            return;
        }
        if (isInFullScreenOverlayMode() && (this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() == OverlayType.LYRICS_SELECTION || this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() == OverlayType.LYRICS_CARDS_SELECTION)) {
            return;
        }
        boolean z = false;
        if (isOffline()) {
            Log.debug(this.TAG, "setupInitialStageBackground handle offline mode");
            ImageView albumArtBlurredView = getAlbumArtBlurredView();
            if (albumArtBlurredView == null) {
                return;
            }
            albumArtBlurredView.setVisibility(0);
            return;
        }
        Log.debug(this.TAG, Intrinsics.stringPlus("setupArtistImageBackground , overlay state type - ", getOverlayTypeStateProvider().getOverlaySelectionType()));
        ImageView albumArtBlurredView2 = getAlbumArtBlurredView();
        if (albumArtBlurredView2 != null) {
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            albumArtBlurredView2.setImageBitmap(stageDataModel == null ? null : stageDataModel.getBlurAlbumArt());
        }
        StageDataModel stageDataModel2 = this.stageViewModel.getStageDataModel();
        Boolean boolValueOf = stageDataModel2 == null ? null : Boolean.valueOf(stageDataModel2.getIsArtistImage());
        Log.debug(this.TAG, Intrinsics.stringPlus("setupArtistImageBackground , artistImage available? - ", boolValueOf));
        if (Intrinsics.areEqual(boolValueOf, Boolean.TRUE)) {
            ImageView artistImageView = getArtistImageView();
            if (artistImageView != null) {
                StageDataModel stageDataModel3 = this.stageViewModel.getStageDataModel();
                artistImageView.setImageBitmap(stageDataModel3 == null ? null : stageDataModel3.getBlurAlbumArt());
            }
            ImageView artistImageViewBlurred = getArtistImageViewBlurred();
            if (artistImageViewBlurred != null) {
                StageDataModel stageDataModel4 = this.stageViewModel.getStageDataModel();
                artistImageViewBlurred.setImageBitmap(stageDataModel4 != null ? stageDataModel4.getBlurAlbumArt() : null);
            }
        } else {
            Log.debug(this.TAG, "Artist Image is not available, falling back to full screen blurred Album artwork");
            ImageView artistImageView2 = getArtistImageView();
            if (artistImageView2 != null) {
                StageDataModel stageDataModel5 = this.stageViewModel.getStageDataModel();
                artistImageView2.setImageBitmap(stageDataModel5 == null ? null : stageDataModel5.getBlurAlbumArt());
            }
            ImageView artistImageViewBlurred2 = getArtistImageViewBlurred();
            if (artistImageViewBlurred2 != null) {
                StageDataModel stageDataModel6 = this.stageViewModel.getStageDataModel();
                artistImageViewBlurred2.setImageBitmap(stageDataModel6 != null ? stageDataModel6.getBlurAlbumArt() : null);
            }
        }
        FrameLayout artistTreatmentView = getArtistTreatmentView();
        if (artistTreatmentView != null) {
            artistTreatmentView.setVisibility(8);
        }
        ImageView albumArtBlurredView3 = getAlbumArtBlurredView();
        if (albumArtBlurredView3 != null) {
            albumArtBlurredView3.setVisibility(8);
        }
        OverlaySelectionType overlaySelectionType = getOverlayTypeStateProvider().getOverlaySelectionType();
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Lyrics.INSTANCE)) {
            StageDataModel stageDataModel7 = this.stageViewModel.getStageDataModel();
            if (stageDataModel7 != null && stageDataModel7.getIsLyrics()) {
                z = true;
            }
            if (z) {
                showFullScreenBlurAlbumArt();
                return;
            } else {
                showFullScreenBlurAlbumArt();
                return;
            }
        }
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Xray.INSTANCE)) {
            StageDataModel stageDataModel8 = this.stageViewModel.getStageDataModel();
            if ((stageDataModel8 == null || stageDataModel8.getIsXray()) ? false : true) {
                StageDataModel stageDataModel9 = this.stageViewModel.getStageDataModel();
                if (stageDataModel9 != null && stageDataModel9.getIsLyrics()) {
                    z = true;
                }
                if (z) {
                    showFullScreenBlurAlbumArt();
                    return;
                }
            }
            showFullScreenBlurAlbumArt();
            return;
        }
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.None.INSTANCE)) {
            showFullScreenBlurAlbumArt();
            return;
        }
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Automatic.INSTANCE)) {
            if (getStageAutomatedSequencingHandler().getAutomatedSequenceCurrentOverlay() == OverlayType.ALBUM_ART || getStageAutomatedSequencingHandler().getAutomatedSequenceCurrentOverlay() == OverlayType.XRAY_PARTIAL) {
                showFullScreenBlurAlbumArt();
            } else {
                showFullScreenBlurAlbumArt();
            }
        }
    }

    private final void showFullScreenBlurAlbumArt() {
        ImageView albumArtBlurredView;
        FrameLayout artistTreatmentView;
        if (isOffline() || (albumArtBlurredView = getAlbumArtBlurredView()) == null || (artistTreatmentView = getArtistTreatmentView()) == null) {
            return;
        }
        ViewExtensions.crossFade(artistTreatmentView, albumArtBlurredView, 1000L, 0.9f);
    }

    private final void handleOverlayTypeChange(OverlayType overlayType) {
        isVideoTrack();
    }

    public final void resetOverlayState() {
        StageSwipeablePagesView xrayContainer;
        StageSwipeablePagesView xrayContainer2;
        BehaviorSubject<StageDataModel.State> xRayAvailability;
        Log.debug(this.TAG, "resetOverlayState");
        getStageAutomatedSequencingHandler().resetAutomatedSequenceCurrentOverlay();
        if (isOffline()) {
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            if (stageDataModel != null && (xRayAvailability = stageDataModel.getXRayAvailability()) != null) {
                xRayAvailability.onNext(StageDataModel.State.UNAVAILABLE);
            }
        } else if (this.stageViewModel.isAdOrInterLude()) {
            setUpStateForAdOrInterlude();
            BaseButton lyricsOverlayButton = getLyricsOverlayButton();
            if (lyricsOverlayButton != null) {
                lyricsOverlayButton.setVisibility(8);
            }
            StageXRayManager stageXrayManager = getStageXrayManager();
            BaseButton xrayPillButton = stageXrayManager == null ? null : stageXrayManager.getXrayPillButton();
            if (xrayPillButton != null) {
                xrayPillButton.setVisibility(8);
            }
        } else if (this.playbackController.getCurrentMediaItem() != null && !Intrinsics.areEqual(getOverlayXrayStateProvider().getMediaItem(), this.playbackController.getCurrentMediaItem())) {
            StageXRayManager stageXrayManager2 = getStageXrayManager();
            if (stageXrayManager2 != null) {
                stageXrayManager2.removeXrayPillButtonIfAlreadyExists(getOverlayButtonsContainerLayout());
            }
            StageXRayManager stageXrayManager3 = getStageXrayManager();
            if (stageXrayManager3 != null && (xrayContainer2 = stageXrayManager3.getXrayContainer()) != null) {
                xrayContainer2.removeAllViews();
            }
            getOverlayXrayStateProvider().reset();
            StageXRayManager stageXrayManager4 = getStageXrayManager();
            if (stageXrayManager4 != null) {
                stageXrayManager4.clearXray();
            }
            Context context = this.context;
            NowPlayingFragmentActivity nowPlayingFragmentActivity = context instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) context : null;
            if (nowPlayingFragmentActivity != null) {
                Fragment currentFragment = nowPlayingFragmentActivity.getCurrentFragment();
                NowPlayingFragment nowPlayingFragment = currentFragment instanceof NowPlayingFragment ? (NowPlayingFragment) currentFragment : null;
                if (nowPlayingFragment != null && nowPlayingFragment.isResumed()) {
                    StageXRayManager stageXrayManager5 = getStageXrayManager();
                    if (stageXrayManager5 != null && (xrayContainer = stageXrayManager5.getXrayContainer()) != null) {
                        xrayContainer.impressionEnded(ImpressionEndReason.AUTO, false);
                    }
                    requestXray();
                }
            }
        }
        if (!isInFullScreenOverlayMode()) {
            hidePartialOverlay$default(this, false, true, 1, null);
        } else {
            getOverlayTypeStateProvider().getCurrentOverlay().setValue(getOverlayTypeStateProvider().getCurrentOverlay().getValue());
        }
        getOverlayLyricsStateProvider().setFrugalLyricsCompleted(false);
        StageLyrics stageLyrics = getStageLyrics();
        if (stageLyrics != null) {
            stageLyrics.startListeningToPlaybackChanges();
        }
        deselectLyricsPill();
        if (isInFullScreenOverlayMode() && (this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() == OverlayType.LYRICS_SELECTION || this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() == OverlayType.LYRICS_CARDS_SELECTION)) {
            return;
        }
        resetBackgrounds();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public final void requestXrayOnResume() {
        ActionValidatedPlaybackController actionValidatedPlaybackController = this.playbackController;
        if (actionValidatedPlaybackController == null || actionValidatedPlaybackController.getCurrentMediaItem() == null || Intrinsics.areEqual(getOverlayXrayStateProvider().getMediaItem(), this.playbackController.getCurrentMediaItem())) {
            return;
        }
        requestXray();
    }

    private final void setUpStateForAdOrInterlude() {
        BehaviorSubject<StageDataModel.State> lyricsAvailability;
        BehaviorSubject<StageDataModel.State> xRayAvailability;
        StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
        if (stageDataModel != null && (xRayAvailability = stageDataModel.getXRayAvailability()) != null) {
            xRayAvailability.onNext(StageDataModel.State.UNAVAILABLE);
        }
        StageDataModel stageDataModel2 = this.stageViewModel.getStageDataModel();
        if (stageDataModel2 == null || (lyricsAvailability = stageDataModel2.getLyricsAvailability()) == null) {
            return;
        }
        lyricsAvailability.onNext(StageDataModel.State.UNAVAILABLE);
    }

    private final void presentInitialOverlays() {
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease2;
        if (isInFullScreenOverlayMode()) {
            OverlaySelectionType overlaySelectionType = getOverlayTypeStateProvider().getOverlaySelectionType();
            if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Lyrics.INSTANCE)) {
                StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
                if (((stageDataModel == null || stageDataModel.getIsLyrics()) ? false : true) && this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() != OverlayType.LYRICS_SELECTION && this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() != OverlayType.LYRICS_CARDS_SELECTION) {
                    popOverlayFragmentFromBackstack();
                }
            } else if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Xray.INSTANCE)) {
                StageDataModel stageDataModel2 = this.stageViewModel.getStageDataModel();
                if ((stageDataModel2 == null || stageDataModel2.getIsXray()) ? false : true) {
                    popOverlayFragmentFromBackstack();
                }
            }
            if (Intrinsics.areEqual(getOverlayTypeStateProvider().getOverlaySelectionType(), OverlaySelectionType.Automatic.INSTANCE)) {
                StageAutomatedSequencingHandler stageAutomatedSequencingHandler = getStageAutomatedSequencingHandler();
                PublishSubject<OverlaySequence> publishSubject = this.overlaySequenceSubject;
                StageLyricsUtil.Companion companion = StageLyricsUtil.INSTANCE;
                StageLyrics stageLyrics = getStageLyrics();
                stageAutomatedSequencingHandler.startAlbumArtTimer(publishSubject, Long.valueOf(companion.getTimeToTransitionFromAlbumArtToLyrics(stageLyrics != null ? stageLyrics.getLyrics() : null)));
                return;
            }
            return;
        }
        if (isVideoTrack()) {
            return;
        }
        Log.debug(this.TAG, Intrinsics.stringPlus("presentInitialOverlays - overlay state is - ", getOverlayTypeStateProvider().getOverlaySelectionType()));
        OverlaySelectionType overlaySelectionType2 = getOverlayTypeStateProvider().getOverlaySelectionType();
        if (Intrinsics.areEqual(overlaySelectionType2, OverlaySelectionType.None.INSTANCE)) {
            Log.debug(this.TAG, "overlay state is none, show album art & return ");
            hidePartialOverlay$default(this, false, false, 3, null);
            return;
        }
        if (Intrinsics.areEqual(overlaySelectionType2, OverlaySelectionType.Lyrics.INSTANCE)) {
            StageDataModel stageDataModel3 = this.stageViewModel.getStageDataModel();
            if (stageDataModel3 != null && stageDataModel3.getIsLyrics()) {
                StageLyrics stageLyrics2 = getStageLyrics();
                if (stageLyrics2 != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease2 = stageLyrics2.getLyricsViewContainer()) != null) {
                    lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease2.setFrugalLyricsEnabled(true);
                }
                showHideLyricsPartialOverlay$default(this, true, false, 2, null);
                return;
            }
            Log.debug(this.TAG, "overlay state is lyrics & lyrics are NOT available");
            StageDataModel stageDataModel4 = this.stageViewModel.getStageDataModel();
            if ((stageDataModel4 != null && stageDataModel4.getIsXray()) && !isXrayShownForCurrentTrack()) {
                Log.debug(this.TAG, "overlay state is lyrics & lyrics are NOT available but xray is Available");
                showHideXrayPartialOverlay(true, StageXRayManager.XrayState.PUSH_STATE);
                return;
            } else {
                Log.debug(this.TAG, "overlay state is lyrics but lyrics & xray are not Available");
                hidePartialOverlay$default(this, false, false, 3, null);
                return;
            }
        }
        if (Intrinsics.areEqual(overlaySelectionType2, OverlaySelectionType.Xray.INSTANCE)) {
            StageDataModel stageDataModel5 = this.stageViewModel.getStageDataModel();
            if (stageDataModel5 != null && stageDataModel5.getIsXray()) {
                if (!isXrayShownForCurrentTrack()) {
                    showHideXrayPartialOverlay(true, StageXRayManager.XrayState.USER_INITIATED_STATE);
                    return;
                } else {
                    hidePartialOverlay$default(this, false, false, 3, null);
                    return;
                }
            }
            Log.debug(this.TAG, "overlay state is xray & xray is NOT Available");
            if (shouldShowLyrics()) {
                Log.debug(this.TAG, "overlay state is xray & xray is NOT available but Lyrics are Available");
                StageLyrics stageLyrics3 = getStageLyrics();
                if (stageLyrics3 != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics3.getLyricsViewContainer()) != null) {
                    lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.setFrugalLyricsEnabled(true);
                }
                showHideLyricsPartialOverlay$default(this, true, false, 2, null);
                return;
            }
            Log.debug(this.TAG, "overlay state is xray & lyrics & xray is not Available");
            hidePartialOverlay$default(this, false, false, 3, null);
            return;
        }
        if (Intrinsics.areEqual(overlaySelectionType2, OverlaySelectionType.Automatic.INSTANCE)) {
            OverlayType automatedSequenceCurrentOverlay = getStageAutomatedSequencingHandler().getAutomatedSequenceCurrentOverlay();
            OverlayType overlayType = OverlayType.ALBUM_ART;
            if (automatedSequenceCurrentOverlay == overlayType) {
                StageAutomatedSequencingHandler stageAutomatedSequencingHandler2 = getStageAutomatedSequencingHandler();
                PublishSubject<OverlaySequence> publishSubject2 = this.overlaySequenceSubject;
                StageLyricsUtil.Companion companion2 = StageLyricsUtil.INSTANCE;
                StageLyrics stageLyrics4 = getStageLyrics();
                stageAutomatedSequencingHandler2.startAlbumArtTimer(publishSubject2, Long.valueOf(companion2.getTimeToTransitionFromAlbumArtToLyrics(stageLyrics4 != null ? stageLyrics4.getLyrics() : null)));
                FrameLayout stageAlbumArtContainer = getStageAlbumArtContainer();
                if (stageAlbumArtContainer != null) {
                    ViewExtensions.fadeIn(stageAlbumArtContainer);
                }
                getOverlayTypeStateProvider().getCurrentOverlay().setValue(overlayType);
                return;
            }
            this.overlaySequenceSubject.onNext(OverlaySequence.ALBUM_ART_PREVIEW_FINISHED);
        }
    }

    private final boolean shouldShowLyrics() {
        boolean frugalLyricsCompleted = getOverlayLyricsStateProvider().getFrugalLyricsCompleted();
        StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
        return (stageDataModel != null && stageDataModel.getIsLyrics()) && !frugalLyricsCompleted;
    }

    private final void deselectLyricsPill() {
        Resources resources;
        BaseButton lyricsOverlayButton = getLyricsOverlayButton();
        if (lyricsOverlayButton != null) {
            lyricsOverlayButton.setActive(false);
        }
        BaseButton lyricsOverlayButton2 = getLyricsOverlayButton();
        if (lyricsOverlayButton2 == null) {
            return;
        }
        Context context = this.context;
        String string = null;
        if (context != null && (resources = context.getResources()) != null) {
            string = resources.getString(R.string.non_active_lyrics_pill_description);
        }
        lyricsOverlayButton2.setContentDescription(string);
    }

    private final void selectLyricsPill() {
        Resources resources;
        BaseButton lyricsOverlayButton = getLyricsOverlayButton();
        if (lyricsOverlayButton != null) {
            lyricsOverlayButton.setActive(true);
        }
        BaseButton lyricsOverlayButton2 = getLyricsOverlayButton();
        if (lyricsOverlayButton2 == null) {
            return;
        }
        Context context = this.context;
        String string = null;
        if (context != null && (resources = context.getResources()) != null) {
            string = resources.getString(R.string.active_lyrics_pill_description);
        }
        lyricsOverlayButton2.setContentDescription(string);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initializeXrayContainer() {
        StageSwipeablePagesView xrayContainer;
        BehaviorSubject<StageDataModel.State> xRayAvailability;
        BehaviorSubject<StageDataModel.State> xRayAvailability2;
        if (isOffline()) {
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            if (stageDataModel == null || (xRayAvailability2 = stageDataModel.getXRayAvailability()) == null) {
                return;
            }
            xRayAvailability2.onNext(StageDataModel.State.UNAVAILABLE);
            return;
        }
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem != null && Intrinsics.areEqual(getOverlayXrayStateProvider().getMediaItem(), currentMediaItem)) {
            StageXRayManager stageXrayManager = getStageXrayManager();
            if (stageXrayManager != null) {
                stageXrayManager.setXrayPillButtonView(getOverlayXrayStateProvider().getPillNavigatorWidgetItemElement());
            }
            StageXRayManager stageXrayManager2 = getStageXrayManager();
            if (stageXrayManager2 != null) {
                stageXrayManager2.setXrayContainerView(getOverlayXrayStateProvider().getSwipeablePagesTemplate(), getOverlayXrayStateProvider().getCurrentPageIndex(), getOverlayXrayStateProvider().getCurrentSectionIndex(), getOverlayXrayStateProvider().getHasUserInteracted());
            }
            StageDataModel stageDataModel2 = this.stageViewModel.getStageDataModel();
            if (stageDataModel2 == null || (xRayAvailability = stageDataModel2.getXRayAvailability()) == null) {
                return;
            }
            xRayAvailability.onNext(StageDataModel.State.AVAILABLE);
            return;
        }
        StageXRayManager stageXrayManager3 = getStageXrayManager();
        if (stageXrayManager3 != null) {
            stageXrayManager3.removeXrayPillButtonIfAlreadyExists(getOverlayButtonsContainerLayout());
        }
        StageXRayManager stageXrayManager4 = getStageXrayManager();
        if (stageXrayManager4 != null && (xrayContainer = stageXrayManager4.getXrayContainer()) != null) {
            xrayContainer.removeAllViews();
        }
        getOverlayXrayStateProvider().reset();
        StageXRayManager stageXrayManager5 = getStageXrayManager();
        if (stageXrayManager5 == null) {
            return;
        }
        stageXrayManager5.requestXrayOverlayIfXrayAvailable(StageXRayManager.XrayState.HIDDEN);
    }

    private final void requestXray() {
        StageXRayManager stageXrayManager = getStageXrayManager();
        boolean z = false;
        if (stageXrayManager != null && stageXrayManager.isFullScreenXrayOpen()) {
            z = true;
        }
        if (z) {
            StageXRayManager stageXrayManager2 = getStageXrayManager();
            if (stageXrayManager2 != null) {
                stageXrayManager2.removeViewFromFullScreenFragment();
            }
            StageXRayManager stageXrayManager3 = getStageXrayManager();
            if (stageXrayManager3 == null) {
                return;
            }
            stageXrayManager3.requestXrayOverlayIfXrayAvailable(StageXRayManager.XrayState.FULL_SCREEN);
            return;
        }
        StageXRayManager stageXrayManager4 = getStageXrayManager();
        if (stageXrayManager4 == null) {
            return;
        }
        stageXrayManager4.requestXrayOverlayIfXrayAvailable(StageXRayManager.XrayState.HIDDEN);
    }

    private final void handleAutomaticSequencing(OverlaySequence overlaySequence) {
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        OverlaySequence overlaySequence2 = OverlaySequence.ALBUM_ART_PREVIEW_FINISHED;
        if (overlaySequence == overlaySequence2 && shouldShowLyrics()) {
            StageLyrics stageLyrics = getStageLyrics();
            if (stageLyrics != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics.getLyricsViewContainer()) != null) {
                lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.setFrugalLyricsEnabled(true);
            }
            if (this.fullscreenOverlayBackstack.isEmpty()) {
                showHideLyricsPartialOverlay$default(this, true, false, 2, null);
            }
            StageAutomatedSequencingHandler stageAutomatedSequencingHandler = getStageAutomatedSequencingHandler();
            OverlayType overlayType = OverlayType.LYRICS_PARTIAL;
            stageAutomatedSequencingHandler.setAutomatedSequenceCurrentOverlay(overlayType);
            this.overlayTypeSubject.onNext(overlayType);
            return;
        }
        if (overlaySequence == OverlaySequence.FRUGAL_LYRICS_FINISHED || overlaySequence == overlaySequence2) {
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            if ((stageDataModel != null && stageDataModel.getIsXray()) && !isXrayShownForCurrentTrack()) {
                if (this.fullscreenOverlayBackstack.isEmpty()) {
                    showHideXrayPartialOverlay(true, StageXRayManager.XrayState.PUSH_STATE);
                }
                getStageAutomatedSequencingHandler().setAutomatedSequenceCurrentOverlay(OverlayType.XRAY_PARTIAL);
                return;
            } else {
                if (this.fullscreenOverlayBackstack.isEmpty()) {
                    hidePartialOverlay$default(this, false, false, 3, null);
                }
                getStageAutomatedSequencingHandler().setAutomatedSequenceCurrentOverlay(OverlayType.ALBUM_ART);
                return;
            }
        }
        if (overlaySequence == OverlaySequence.FRUGAL_XRAY_FINISHED) {
            StageXRayManager stageXrayManager = getStageXrayManager();
            if (stageXrayManager != null) {
                stageXrayManager.deselectXrayPillButton();
            }
            getStageAutomatedSequencingHandler().setAutomatedSequenceCurrentOverlay(OverlayType.ALBUM_ART);
            if (this.fullscreenOverlayBackstack.isEmpty()) {
                hidePartialOverlay$default(this, false, false, 3, null);
            }
        }
    }

    private final void initLandscapeStageView(StyleSheet styleSheet) {
        Resources resources;
        FontStyle fontStyle;
        FontStyle fontStyleCopy$default;
        PlayerMetadataView landscapePlayerMetadata;
        if (!isVideoTrack() && (landscapePlayerMetadata = getLandscapePlayerMetadata()) != null) {
            landscapePlayerMetadata.initViewStyling(styleSheet);
        }
        StageLandscapeControlsView landscapeControlsView = getLandscapeControlsView();
        if (landscapeControlsView != null) {
            landscapeControlsView.initViewStyling(styleSheet);
        }
        View view = this.viewRoot.get();
        ConstraintLayout constraintLayout = view == null ? null : (ConstraintLayout) view.findViewById(R.id.stage_controls);
        ViewGroup.LayoutParams layoutParams = constraintLayout != null ? constraintLayout.getLayoutParams() : null;
        if (layoutParams == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
        if (this.landscapeType == LandscapeType.TABLET_LANDSCAPE) {
            Integer spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.HUGE);
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = spacerInPixels == null ? 0 : spacerInPixels.intValue();
            TextView controlTipView = getControlTipView();
            if (controlTipView != null) {
                controlTipView.setVisibility(0);
                Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_5);
                if (color != null && (fontStyle = styleSheet.getFontStyle(FontStyleKey.LABEL)) != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, null, null, color.intValue(), Boolean.FALSE, 3, null)) != null) {
                    FontUtil.INSTANCE.applyFontStyle(controlTipView, fontStyleCopy$default);
                }
            }
            Context context = this.context;
            if (context != null && (resources = context.getResources()) != null) {
                dimension = (int) resources.getDimension(R.dimen.spacer_48);
            }
            int i = dimension;
            LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
            ProgressBar progressBar = this.playPauseProgressBar;
            if (progressBar == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.view.View");
            }
            layoutParamUtils.setLayoutMargins(progressBar, i, i, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        } else {
            Integer spacerInPixels2 = styleSheet.getSpacerInPixels(SpacerKey.MINI);
            ((ViewGroup.MarginLayoutParams) layoutParams2).bottomMargin = spacerInPixels2 != null ? spacerInPixels2.intValue() : 0;
        }
        constraintLayout.setLayoutParams(layoutParams2);
    }

    public final void showHideControls(boolean show) {
        if (show) {
            StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
            if (bottomButtonsContainerView != null) {
                bottomButtonsContainerView.setVisibility(0);
            }
            LinearLayout playerMetadataContainer = getPlayerMetadataContainer();
            if (playerMetadataContainer != null) {
                playerMetadataContainer.setVisibility(0);
            }
            StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
            if (overlayButtonsContainerLayout != null) {
                overlayButtonsContainerLayout.setVisibility(8);
            }
            StagePortraitPlaybackControlsView playbackControlsView = getPlaybackControlsView();
            if (playbackControlsView == null) {
                return;
            }
            playbackControlsView.setVisibility(0);
            return;
        }
        StageBottomButtonsView bottomButtonsContainerView2 = getBottomButtonsContainerView();
        if (bottomButtonsContainerView2 != null) {
            bottomButtonsContainerView2.setVisibility(8);
        }
        LinearLayout playerMetadataContainer2 = getPlayerMetadataContainer();
        if (playerMetadataContainer2 != null) {
            playerMetadataContainer2.setVisibility(8);
        }
        StageOverlayButtonsLayout overlayButtonsContainerLayout2 = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout2 != null) {
            overlayButtonsContainerLayout2.setVisibility(8);
        }
        StagePortraitPlaybackControlsView playbackControlsView2 = getPlaybackControlsView();
        if (playbackControlsView2 == null) {
            return;
        }
        playbackControlsView2.setVisibility(8);
    }

    public static /* synthetic */ void updateVideoPlaybackControls$default(StageViewPresenter stageViewPresenter, boolean z, StageLandscapeControlsView stageLandscapeControlsView, ProgressBar progressBar, int i, Object obj) {
        if ((i & 2) != 0) {
            stageLandscapeControlsView = null;
        }
        if ((i & 4) != 0) {
            progressBar = null;
        }
        stageViewPresenter.updateVideoPlaybackControls(z, stageLandscapeControlsView, progressBar);
    }

    public final void updateVideoPlaybackControls(boolean isLandscape, StageLandscapeControlsView stageLandscapeVideoControlsView, ProgressBar progressBar) {
        if (isLandscape) {
            this.playPauseButton = stageLandscapeVideoControlsView == null ? null : stageLandscapeVideoControlsView.getPlayPauseButton();
            this.nextButton = stageLandscapeVideoControlsView == null ? null : stageLandscapeVideoControlsView.getNextButton();
            this.prevButton = stageLandscapeVideoControlsView != null ? stageLandscapeVideoControlsView.getPrevButton() : null;
            setPlayPauseProgressBar(progressBar);
        } else {
            refreshPortraitPlaybackControls();
        }
        setupPlaybackControlsClickListeners();
        this.stageViewModel.resetAndUpdatePlaybackState();
    }

    public final void hideLandscapePlayerMetadata() {
        PlayerMetadataView landscapePlayerMetadata = getLandscapePlayerMetadata();
        if (landscapePlayerMetadata == null) {
            return;
        }
        landscapePlayerMetadata.setVisibility(8);
    }

    public final void onDestroy() {
        StageXRayManager stageXrayManager;
        StageSwipeablePagesView xrayContainer;
        Log.debug(this.TAG, "onDestroy()");
        if (getOverlayTypeStateProvider().getCurrentOverlay().getValue() == OverlayType.XRAY_PARTIAL && (stageXrayManager = getStageXrayManager()) != null && (xrayContainer = stageXrayManager.getXrayContainer()) != null) {
            xrayContainer.impressionEnded(ImpressionEndReason.AUTO, false);
        }
        clearOverlayBackstack();
        clearLandscapeViewsAnimation();
        this.stageViewModel.onDestroy();
        StageXRayManager stageXrayManager2 = getStageXrayManager();
        if (stageXrayManager2 != null) {
            stageXrayManager2.clearXrayManager();
        }
        this.context = null;
        this.partialOverlayContainer = null;
        StageLyrics stageLyrics = getStageLyrics();
        if (stageLyrics != null) {
            stageLyrics.stopListeningToPlaybackChanges();
        }
        StageLyrics stageLyrics2 = getStageLyrics();
        if (stageLyrics2 != null) {
            stageLyrics2.unsubscribeFrugalLyricsCompletedSubscription();
        }
        Disposable disposable = this.lyricsDisposable;
        if (disposable != null) {
            disposable.dispose();
        }
        StageTooltipPresenter stageTooltipPresenter = this.stageTooltipPresenter;
        if (stageTooltipPresenter != null) {
            stageTooltipPresenter.onDestroy();
        }
        CompositeSubscription compositeSubscription = this.stateSubscription;
        if (compositeSubscription != null) {
            compositeSubscription.unsubscribe();
        }
        Subscription subscription = this.overlaySequenceSubscription;
        if (subscription != null) {
            subscription.unsubscribe();
        }
        this.stateSubscription = null;
        StageTooltipPresenter stageTooltipPresenter2 = this.stageTooltipPresenter;
        if (stageTooltipPresenter2 != null) {
            stageTooltipPresenter2.dismissAllToolTips();
        }
        if (!this.openLyricsSelectionFragmentSubscription.isDisposed()) {
            this.openLyricsSelectionFragmentSubscription.dispose();
        }
        if (!this.openLyricsCardFragmentSubscription.isDisposed()) {
            this.openLyricsCardFragmentSubscription.dispose();
        }
        SkipLimitProviderImpl.INSTANCE.removeOnSkipLimitUpdateListener(this.mSkipLimitUpdateListener);
        LyricsSharingDataRepository.INSTANCE.cancelBackgroundFetch();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public final void onNowPlayingFragmentDestroyed() {
        Lifecycle lifecycle = this.lifecycle;
        if (lifecycle != null) {
            lifecycle.removeObserver(this);
        }
        this.lifecycle = null;
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            bottomButtonsContainerView.clearLifecycleOwner();
        }
        StageBottomButtonsView bottomButtonsContainerView2 = getBottomButtonsContainerView();
        if (bottomButtonsContainerView2 == null) {
            return;
        }
        bottomButtonsContainerView2.unregisterCastingListeners();
    }

    private final void onLyricsOverlayButtonClick() {
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        StageLyrics stageLyrics = getStageLyrics();
        if (stageLyrics != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics.getLyricsViewContainer()) != null) {
            lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.setFrugalLyricsEnabled(false);
        }
        StageXRayManager stageXrayManager = getStageXrayManager();
        if (stageXrayManager != null) {
            stageXrayManager.deselectXrayPillButton();
        }
        boolean z = getLyricsOverlayButton() == null ? false : !r0.getIsActive();
        OverlayType overlayType = OverlayType.LYRICS_PARTIAL;
        handleOverlaySelectionChange(overlayType, z);
        showHideLyricsPartialOverlay$default(this, z, false, 2, null);
        if (z) {
            this.overlayTypeSubject.onNext(overlayType);
        } else {
            this.overlayTypeSubject.onNext(OverlayType.ALBUM_ART);
        }
        StageViewModel.sendUiClickEvent$default(this.stageViewModel, z ? ActionType.SELECT_LYRICS_OVERLAY_ON : ActionType.SELECT_LYRICS_OVERLAY_OFF, null, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onXRayOverlayButtonClick() {
        BaseButton xrayPillButton;
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        StageLyrics stageLyrics = getStageLyrics();
        boolean z = false;
        if (stageLyrics != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics.getLyricsViewContainer()) != null) {
            lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.setFrugalLyricsEnabled(false);
        }
        deselectLyricsPill();
        StageXRayManager stageXrayManager = getStageXrayManager();
        if (stageXrayManager != null && (xrayPillButton = stageXrayManager.getXrayPillButton()) != null) {
            z = !xrayPillButton.getIsActive();
        }
        if (z) {
            this.overlayTypeSubject.onNext(OverlayType.XRAY_PARTIAL);
        } else {
            this.overlayTypeSubject.onNext(OverlayType.ALBUM_ART);
        }
        handleOverlaySelectionChange(OverlayType.XRAY_PARTIAL, z);
        showHideXrayPartialOverlay(z, StageXRayManager.XrayState.USER_INITIATED_STATE);
        StageViewModel.sendUiClickEvent$default(this.stageViewModel, z ? ActionType.SELECT_XRAY_OVERLAY_ON : ActionType.SELECT_XRAY_OVERLAY_OFF, null, 2, null);
    }

    private final void handleOverlaySelectionChange(OverlayType overlayType, boolean show) {
        StageLyrics stageLyrics;
        if (show) {
            if (overlayType == OverlayType.LYRICS_PARTIAL) {
                getOverlayTypeStateProvider().setOverlaySelectionType(OverlaySelectionType.Lyrics.INSTANCE);
                StageLyrics stageLyrics2 = getStageLyrics();
                if (stageLyrics2 == null) {
                    return;
                }
                stageLyrics2.setShowFullLyricsOnOverlays(true);
                return;
            }
            if (overlayType == OverlayType.XRAY_PARTIAL) {
                getOverlayTypeStateProvider().setOverlaySelectionType(OverlaySelectionType.Xray.INSTANCE);
                return;
            }
            return;
        }
        getOverlayTypeStateProvider().setOverlaySelectionType(OverlaySelectionType.None.INSTANCE);
        if (overlayType != OverlayType.LYRICS_PARTIAL || (stageLyrics = getStageLyrics()) == null) {
            return;
        }
        stageLyrics.setShowFullLyricsOnOverlays(false);
    }

    private final boolean isXrayShownForCurrentTrack() {
        return getOverlayXrayStateProvider().getMediaItem() != null && Intrinsics.areEqual(getOverlayXrayStateProvider().getMediaItem(), this.playbackController.getCurrentMediaItem()) && Intrinsics.areEqual(getOverlayXrayStateProvider().getWasXrayShownInPreview(), Boolean.TRUE);
    }

    static /* synthetic */ void showHideLyricsPartialOverlay$default(StageViewPresenter stageViewPresenter, boolean z, boolean z2, int i, Object obj) {
        if ((i & 2) != 0) {
            z2 = false;
        }
        stageViewPresenter.showHideLyricsPartialOverlay(z, z2);
    }

    private final void showHideLyricsPartialOverlay(boolean show, boolean frugalLyricsFinished) {
        StageXRayManager stageXrayManager;
        StageSwipeablePagesView xrayContainer;
        if (show) {
            if (getOverlayTypeStateProvider().getCurrentOverlay().getValue() == OverlayType.XRAY_PARTIAL && (stageXrayManager = getStageXrayManager()) != null && (xrayContainer = stageXrayManager.getXrayContainer()) != null) {
                xrayContainer.impressionEnded(ImpressionEndReason.MANUAL, false);
            }
            StageLyrics stageLyrics = getStageLyrics();
            if (stageLyrics != null) {
                stageLyrics.startListeningToPlaybackChanges();
            }
            selectLyricsPill();
            StageLyrics stageLyrics2 = getStageLyrics();
            LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics2 != null ? stageLyrics2.getLyricsViewContainer() : null;
            if (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease == null) {
                return;
            }
            OverlayPosition.Top top = OverlayPosition.Top.INSTANCE;
            OverlayType overlayType = OverlayType.LYRICS_PARTIAL;
            showPartialOverlay(new OverlayContainer(new OverlayViewType.ViewOverlay(lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease, top, overlayType)), overlayType);
            return;
        }
        StageLyrics stageLyrics3 = getStageLyrics();
        if (stageLyrics3 != null) {
            stageLyrics3.stopListeningToPlaybackChanges();
        }
        deselectLyricsPill();
        hidePartialOverlay$default(this, frugalLyricsFinished, false, 2, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showLyricsFullScreenOverlay() {
        LyricsTextViewContainer lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease;
        if (getOverlayLyricsStateProvider().getShouldShowTapToViewLyrics()) {
            getOverlayLyricsStateProvider().setShouldShowTapToViewLyrics(false);
            StageLyrics stageLyrics = getStageLyrics();
            if (stageLyrics != null && (lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease = stageLyrics.getLyricsViewContainer()) != null) {
                lyricsViewContainer$DigitalMusicAndroid3P_automotiveProdRelease.setHasUserInteracted(true);
            }
        }
        getOverlayTypeStateProvider().setOverlaySelectionType(OverlaySelectionType.Lyrics.INSTANCE);
        boolean z = (this.fullscreenOverlayBackstack.isEmpty() ^ true) && this.fullscreenOverlayBackstack.peek().getOverlayViewType().getOverlayType() == OverlayType.LYRICS_FULLSCREEN;
        OverlayType overlayType = OverlayType.LYRICS_FULLSCREEN;
        adjustOverlayContainerPosition(overlayType);
        if (z) {
            return;
        }
        if (this.landscapeType == LandscapeType.TABLET_LANDSCAPE) {
            TextView controlTipView = getControlTipView();
            if (controlTipView != null) {
                controlTipView.setVisibility(8);
            }
            BaseButton likeButton = getLikeButton();
            if (likeButton != null) {
                likeButton.setVisibility(8);
            }
        }
        getOverlayTypeStateProvider().setLyricsSharingFromShareSheet(false);
        LyricsFragment lyricsFragment = new LyricsFragment(this.openLyricsSelectionFragmentSubject);
        lyricsFragment.setTransparent(true);
        addScreenOverlay$default(this, new OverlayContainer(new OverlayViewType.FragmentOverlay(lyricsFragment, overlayType)), false, false, false, 14, null);
    }

    private final void showLyricsSelectionOverlay(int cardPosition, boolean shouldResetLyricsSharing, boolean openedFromShareSheet) {
        ImageView image;
        if (shouldResetLyricsSharing) {
            getLyricsSharingDataModel().reset();
            LyricsSharingDataModel lyricsSharingDataModel = getLyricsSharingDataModel();
            MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
            String name = this.playbackController.getCurrentMediaItem().getName();
            String artistName = this.playbackController.getCurrentMediaItem().getArtistName();
            BaseImage albumArtView = getAlbumArtView();
            Drawable drawable = (albumArtView == null || (image = albumArtView.getImage()) == null) ? null : image.getDrawable();
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            Bitmap blurAlbumArt = stageDataModel == null ? null : stageDataModel.getBlurAlbumArt();
            StageLyrics stageLyrics = getStageLyrics();
            lyricsSharingDataModel.setLyricsSharingDataModelForCurrentTrack(currentMediaItem, name, artistName, drawable, blurAlbumArt, stageLyrics != null ? stageLyrics.getLyrics() : null);
            if (openedFromShareSheet) {
                Lyrics lyrics = getLyricsSharingDataModel().getLyrics();
                if (lyrics != null) {
                    Pair<Integer, Integer> pairFindCurrentPlayingLyricsLines = StageLyricsUtil.INSTANCE.findCurrentPlayingLyricsLines(lyrics);
                    lyrics.getLines().get(pairFindCurrentPlayingLyricsLines.getFirst().intValue()).setLineSelected(true);
                    lyrics.getLines().get(pairFindCurrentPlayingLyricsLines.getSecond().intValue()).setLineSelected(true);
                    getLyricsSharingDataModel().setStartIndexOfLyricsSelected(pairFindCurrentPlayingLyricsLines.getFirst().intValue());
                    getLyricsSharingDataModel().setEndIndexOfLyricsSelected(pairFindCurrentPlayingLyricsLines.getSecond().intValue());
                }
                LyricsSharingDataModel lyricsSharingDataModel2 = getLyricsSharingDataModel();
                int startIndexOfLyricsSelected = lyricsSharingDataModel2.getStartIndexOfLyricsSelected();
                int endIndexOfLyricsSelected = (lyricsSharingDataModel2.getEndIndexOfLyricsSelected() - startIndexOfLyricsSelected) + 1;
                MediaItem mediaItem = lyricsSharingDataModel2.getMediaItem();
                if (mediaItem != null) {
                    LyricsSharingMetricsSender lyricsSharingMetricsSender = LyricsSharingMetricsSender.INSTANCE;
                    lyricsSharingMetricsSender.sendLyricsShareIntentFromTrackShareMetrics(mediaItem, startIndexOfLyricsSelected, endIndexOfLyricsSelected);
                    LyricsSharingMetricsSender.sendUIClickMetrics$default(lyricsSharingMetricsSender, mediaItem, ActionType.LYRICS_SHARE_INTENT_FROM_TRACK_SHARE, PageType.NOW_PLAYING_STAGE, null, 8, null);
                }
            }
        }
        OverlayType overlayType = OverlayType.LYRICS_SELECTION;
        adjustOverlayContainerPosition(overlayType);
        io.reactivex.rxjava3.subjects.PublishSubject<Integer> openLyricsCardSelectionFragmentSubject = this.openLyricsCardSelectionFragmentSubject;
        Intrinsics.checkNotNullExpressionValue(openLyricsCardSelectionFragmentSubject, "openLyricsCardSelectionFragmentSubject");
        addScreenOverlay$default(this, new OverlayContainer(new OverlayViewType.FragmentOverlay(new LyricsSelectionFragment(openLyricsCardSelectionFragmentSubject, cardPosition), overlayType)), false, false, true, 6, null);
        FrameLayout stageAlbumArtContainer = getStageAlbumArtContainer();
        if (stageAlbumArtContainer == null) {
            return;
        }
        stageAlbumArtContainer.setAlpha(0.0f);
    }

    private final void showLyricsCardSelectionOverlay(int cardPosition) {
        if (!isInFullScreenOverlayMode()) {
            showLyricsSelectionOverlay(cardPosition, true, true);
            return;
        }
        OverlayType overlayType = OverlayType.LYRICS_CARDS_SELECTION;
        adjustOverlayContainerPosition(overlayType);
        addScreenOverlay$default(this, new OverlayContainer(new OverlayViewType.FragmentOverlay(new LyricsCardSelectionFragment(getLyricsSharingDataModel(), cardPosition), overlayType)), false, false, true, 6, null);
        FrameLayout stageAlbumArtContainer = getStageAlbumArtContainer();
        if (stageAlbumArtContainer == null) {
            return;
        }
        stageAlbumArtContainer.setAlpha(0.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showXrayFullScreenOverlay(View xrayView) {
        OverlayType overlayType = OverlayType.XRAY_FULLSCREEN;
        adjustOverlayContainerPosition(overlayType);
        StageXRayManager stageXrayManager = getStageXrayManager();
        if (stageXrayManager != null) {
            stageXrayManager.setHasUserInteracted(true);
        }
        getOverlayTypeStateProvider().setOverlaySelectionType(OverlaySelectionType.Xray.INSTANCE);
        View toolbarLayout = getToolbarLayout();
        if (toolbarLayout != null) {
            toolbarLayout.setVisibility(8);
        }
        StageXRayManager stageXrayManager2 = getStageXrayManager();
        if (stageXrayManager2 != null) {
            stageXrayManager2.setIsXrayFullScreen(true);
        }
        StageXRayManager stageXrayManager3 = getStageXrayManager();
        if (stageXrayManager3 != null && stageXrayManager3.isFullScreenXrayOpen()) {
            StageXRayManager stageXrayManager4 = getStageXrayManager();
            if (stageXrayManager4 == null) {
                return;
            }
            stageXrayManager4.addViewInFullScreenFragment(xrayView);
            return;
        }
        StageXRayManager stageXrayManager5 = getStageXrayManager();
        StageSwipeablePagesFragment xrayFullScreenFragment = stageXrayManager5 == null ? null : stageXrayManager5.getXrayFullScreenFragment(xrayView);
        if (xrayFullScreenFragment == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.Fragment");
        }
        addScreenOverlay$default(this, new OverlayContainer(new OverlayViewType.FragmentOverlay(xrayFullScreenFragment, overlayType)), false, false, false, 14, null);
    }

    private final void showHideXrayPartialOverlay(boolean show, StageXRayManager.XrayState xrayState) {
        StageSwipeablePagesView xrayContainer;
        PublishSubject<ImpressionEndReason> impressionEndReasonPublishSubject;
        StageSwipeablePagesView xrayContainer2;
        PublishSubject<ImpressionEndReason> impressionEndReasonPublishSubject2;
        if (show) {
            StageXRayManager stageXrayManager = getStageXrayManager();
            if (stageXrayManager != null) {
                stageXrayManager.requestXrayOverlayIfXrayAvailable(xrayState);
            }
            StageLyrics stageLyrics = getStageLyrics();
            if (stageLyrics != null) {
                stageLyrics.stopListeningToPlaybackChanges();
            }
            deselectLyricsPill();
            return;
        }
        StageXRayManager stageXrayManager2 = getStageXrayManager();
        if (stageXrayManager2 != null) {
            stageXrayManager2.deselectXrayPillButton();
        }
        if (xrayState == StageXRayManager.XrayState.USER_INITIATED_STATE) {
            StageXRayManager stageXrayManager3 = getStageXrayManager();
            if (stageXrayManager3 != null && (xrayContainer2 = stageXrayManager3.getXrayContainer()) != null && (impressionEndReasonPublishSubject2 = xrayContainer2.getImpressionEndReasonPublishSubject()) != null) {
                impressionEndReasonPublishSubject2.onNext(ImpressionEndReason.MANUAL);
            }
        } else {
            StageXRayManager stageXrayManager4 = getStageXrayManager();
            if (stageXrayManager4 != null && (xrayContainer = stageXrayManager4.getXrayContainer()) != null && (impressionEndReasonPublishSubject = xrayContainer.getImpressionEndReasonPublishSubject()) != null) {
                impressionEndReasonPublishSubject.onNext(ImpressionEndReason.AUTO);
            }
        }
        hidePartialOverlay$default(this, false, false, 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showXray(Triple<? extends MediaItem, ? extends View, Boolean> mediaItemViewPair) {
        Observable<androidx.core.util.Pair<Integer, Integer>> observableObserveOn;
        PillNavigatorWidgetItemView pillNavigatorWidgetItemViewFindXrayButton;
        if (mediaItemViewPair.getSecond() instanceof StageSwipeablePagesView) {
            getOverlayXrayStateProvider().setSwipeablePagesTemplate(((StageSwipeablePagesView) mediaItemViewPair.getSecond()).getTemplate());
            getOverlayXrayStateProvider().setHasUserInteracted(Boolean.valueOf(((StageSwipeablePagesView) mediaItemViewPair.getSecond()).getHasUserInteracted()));
            getOverlayXrayStateProvider().setMediaItem(mediaItemViewPair.getFirst());
            getOverlayXrayStateProvider().setWasXrayShownInPreview(Boolean.FALSE);
            OverlayXrayStateProvider overlayXrayStateProvider = getOverlayXrayStateProvider();
            StageXRayManager stageXrayManager = getStageXrayManager();
            PillNavigatorWidgetItemElement pillNavigatorWidgetItemElement = null;
            if (stageXrayManager != null && (pillNavigatorWidgetItemViewFindXrayButton = stageXrayManager.findXrayButton(getOverlayButtonsContainerLayout())) != null) {
                pillNavigatorWidgetItemElement = pillNavigatorWidgetItemViewFindXrayButton.getPillNavigatorWidgetItemElement();
            }
            overlayXrayStateProvider.setPillNavigatorWidgetItemElement(pillNavigatorWidgetItemElement);
            PublishSubject<androidx.core.util.Pair<Integer, Integer>> xrayStateEvent = ((StageSwipeablePagesView) mediaItemViewPair.getSecond()).getXrayStateEvent();
            if (xrayStateEvent != null && (observableObserveOn = xrayStateEvent.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())) != null) {
                observableObserveOn.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda31
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        this.f$0.onXrayStateChanged((androidx.core.util.Pair) obj);
                    }
                });
            }
            if (mediaItemViewPair.getThird().booleanValue()) {
                StageXRayManager stageXrayManager2 = getStageXrayManager();
                boolean z = false;
                if (stageXrayManager2 != null && stageXrayManager2.isFullScreenXrayOpen()) {
                    z = true;
                }
                if (z) {
                    showXrayFullScreenOverlay(mediaItemViewPair.getSecond());
                    return;
                } else {
                    showXrayPartialOverlay(mediaItemViewPair.getSecond());
                    return;
                }
            }
            getOverlayXrayStateProvider().setWasXrayShownInPreview(Boolean.TRUE);
            this.overlaySequenceSubject.onNext(OverlaySequence.FRUGAL_XRAY_FINISHED);
            return;
        }
        this.overlaySequenceSubject.onNext(OverlaySequence.FRUGAL_XRAY_FINISHED);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onXrayStateChanged(androidx.core.util.Pair<Integer, Integer> xrayPageAndSectionIndexPair) {
        OverlayXrayStateProvider overlayXrayStateProvider = getOverlayXrayStateProvider();
        Integer num = xrayPageAndSectionIndexPair.first;
        Intrinsics.checkNotNullExpressionValue(num, "xrayPageAndSectionIndexPair.first");
        overlayXrayStateProvider.setCurrentPageIndex(num.intValue());
        OverlayXrayStateProvider overlayXrayStateProvider2 = getOverlayXrayStateProvider();
        Integer num2 = xrayPageAndSectionIndexPair.second;
        Intrinsics.checkNotNullExpressionValue(num2, "xrayPageAndSectionIndexPair.second");
        overlayXrayStateProvider2.setCurrentSectionIndex(num2.intValue());
    }

    private final void showXrayPartialOverlay(View view) {
        PublishSubject<Boolean> xrayFinishEvent;
        Observable<Boolean> observableObserveOn;
        StageXRayManager stageXrayManager = getStageXrayManager();
        if (stageXrayManager != null) {
            stageXrayManager.selectXrayPillButton();
        }
        OverlayPosition.Top top = OverlayPosition.Top.INSTANCE;
        OverlayType overlayType = OverlayType.XRAY_PARTIAL;
        showPartialOverlay(new OverlayContainer(new OverlayViewType.ViewOverlay(view, top, overlayType)), overlayType);
        StageXRayManager stageXrayManager2 = getStageXrayManager();
        if (stageXrayManager2 == null || (xrayFinishEvent = stageXrayManager2.getXrayFinishEvent()) == null || (observableObserveOn = xrayFinishEvent.observeOn(rx.android.schedulers.AndroidSchedulers.mainThread())) == null) {
            return;
        }
        observableObserveOn.subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda32
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.onXrayFinished(((Boolean) obj).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onXrayFinished(boolean isXrayFinished) {
        if (isXrayFinished) {
            getOverlayXrayStateProvider().setWasXrayShownInPreview(Boolean.TRUE);
            this.overlaySequenceSubject.onNext(OverlaySequence.FRUGAL_XRAY_FINISHED);
        }
    }

    private final void showPartialOverlay(OverlayContainer overlayContainer, OverlayType overlayType) {
        Fragment overlayFragment;
        OverlayContainer overlayContainer2 = this.partialOverlayContainer;
        if (overlayContainer2 != null && (overlayFragment = overlayContainer2.getOverlayFragment()) != null) {
            removeOverlayFragment(overlayFragment);
        }
        FrameLayout stageAlbumArtContainer = getStageAlbumArtContainer();
        if (stageAlbumArtContainer != null) {
            ViewExtensions.fadeOut(stageAlbumArtContainer);
        }
        this.artworkVisible = false;
        adjustOverlayContainerPosition(overlayType);
        if (this.context == null) {
            return;
        }
        addScreenOverlay$default(this, overlayContainer, false, false, false, 8, null);
        setPartialOverlayContainer(overlayContainer);
    }

    private final void adjustOverlayContainerPosition(OverlayType overlayType) {
        ConstraintSet constraintSet = new ConstraintSet();
        constraintSet.clone(getPlayerCoverViewRoot());
        switch (WhenMappings.$EnumSwitchMapping$1[overlayType.ordinal()]) {
            case 1:
                FrameLayout overlayContainerView = getOverlayContainerView();
                if (overlayContainerView != null) {
                    constraintSet.constrainHeight(overlayContainerView.getId(), 0);
                    constraintSet.connect(overlayContainerView.getId(), 3, 0, 3);
                    ConstraintLayout stageContainerLayout = getStageContainerLayout();
                    if (stageContainerLayout != null) {
                        constraintSet.connect(overlayContainerView.getId(), 4, stageContainerLayout.getId(), 3);
                    }
                }
                break;
            case 2:
                FrameLayout overlayContainerView2 = getOverlayContainerView();
                if (overlayContainerView2 != null) {
                    if (ScreenUtil.isPortrait()) {
                        constraintSet.constrainHeight(overlayContainerView2.getId(), 0);
                    } else {
                        constraintSet.constrainHeight(overlayContainerView2.getId(), -2);
                    }
                    constraintSet.connect(overlayContainerView2.getId(), 3, 0, 3);
                    ConstraintLayout stageContainerLayout2 = getStageContainerLayout();
                    if (stageContainerLayout2 != null) {
                        constraintSet.connect(overlayContainerView2.getId(), 4, stageContainerLayout2.getId(), 3);
                    }
                }
                break;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
                FrameLayout overlayContainerView3 = getOverlayContainerView();
                if (overlayContainerView3 != null) {
                    constraintSet.constrainHeight(overlayContainerView3.getId(), 0);
                    constraintSet.connect(overlayContainerView3.getId(), 3, 0, 3);
                    constraintSet.connect(overlayContainerView3.getId(), 4, 0, 4);
                }
                break;
            case 8:
                FrameLayout overlayContainerView4 = getOverlayContainerView();
                if (overlayContainerView4 != null) {
                    constraintSet.constrainHeight(overlayContainerView4.getId(), 0);
                    constraintSet.connect(overlayContainerView4.getId(), 3, 0, 3);
                    constraintSet.connect(overlayContainerView4.getId(), 4, 0, 4);
                }
                break;
        }
        constraintSet.applyTo(getPlayerCoverViewRoot());
    }

    static /* synthetic */ void hidePartialOverlay$default(StageViewPresenter stageViewPresenter, boolean z, boolean z2, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if ((i & 2) != 0) {
            z2 = false;
        }
        stageViewPresenter.hidePartialOverlay(z, z2);
    }

    private final void hidePartialOverlay(boolean frugalLyricsFinished, boolean forceHideAlbumArt) {
        Fragment overlayFragment;
        OverlayContainer overlayContainer = this.partialOverlayContainer;
        if (overlayContainer != null && (overlayFragment = overlayContainer.getOverlayFragment()) != null) {
            removeOverlayFragment(overlayFragment);
        }
        this.partialOverlayContainer = null;
        if (forceHideAlbumArt) {
            return;
        }
        if (frugalLyricsFinished) {
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            boolean z = false;
            if (stageDataModel != null && stageDataModel.getIsXray()) {
                z = true;
            }
            if (z) {
                return;
            }
        }
        FrameLayout stageAlbumArtContainer = getStageAlbumArtContainer();
        if (stageAlbumArtContainer != null) {
            ViewExtensions.fadeIn(stageAlbumArtContainer);
        }
        getOverlayTypeStateProvider().getCurrentOverlay().setValue(OverlayType.ALBUM_ART);
        this.artworkVisible = true;
    }

    public final void onNextButtonClick() {
        StageViewModel.onNextButtonClick$default(this.stageViewModel, null, 1, null);
    }

    public final void onPrevButtonClick() {
        StageViewModel.onPrevButtonClick$default(this.stageViewModel, null, 1, null);
    }

    public final void setupTouchHandlers() {
        setupSwipeGestures(this.touchEventHandler);
        View view = this.viewRoot.get();
        if (view != null) {
            view.setOnTouchListener(this.touchEventHandler.getTouchEventListener());
        }
        BaseImage albumArtView = getAlbumArtView();
        if (albumArtView == null) {
            return;
        }
        albumArtView.setOnTouchListener(this.touchEventHandler.getTouchEventListener());
    }

    public final void setupSwipeGestures(TouchEventHandler touchEventHandler) {
        Intrinsics.checkNotNullParameter(touchEventHandler, "touchEventHandler");
        touchEventHandler.setOnSwipeEventListener(this.onSwipeEventListener);
    }

    public final void handleTouchEvent() {
        if (isVideoTrack()) {
            return;
        }
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem instanceof AdMediaItem) {
            AdMediaItem adMediaItem = (AdMediaItem) currentMediaItem;
            Uri adClickUri = adMediaItem.getAdClickUri();
            if (FreeTierPlaybackUtil.canHandleAdClick(adClickUri)) {
                CloudQueueUIMetricsManager.get().sendAdClickingEvent(adMediaItem);
                Context context = this.context;
                if (context == null) {
                    return;
                }
                context.startActivity(new Intent("android.intent.action.VIEW", adClickUri));
                return;
            }
            return;
        }
        if (ScreenUtil.isLandscape()) {
            handleLandscapePlayAreaTap();
        }
    }

    public final void onPlayPauseButtonClick() {
        this.stageViewModel.onPlayPauseButtonClick();
    }

    public final void setPlayButtonImage(boolean playing) {
        Drawable drawable;
        ProgressBar progressBar = this.playPauseProgressBar;
        if (progressBar != null) {
            progressBar.setVisibility(8);
        }
        BaseButton baseButton = this.playPauseButton;
        String string = null;
        if (baseButton != null) {
            Context context = this.context;
            if (context == null) {
                drawable = null;
            } else {
                drawable = context.getDrawable(playing ? R.drawable.ic_playback_pause : R.drawable.ic_play_bauhaus);
            }
            baseButton.setIcon(drawable);
        }
        BaseButton baseButton2 = this.playPauseButton;
        if (baseButton2 != null) {
            Context context2 = this.context;
            if (context2 != null) {
                string = context2.getString(playing ? R.string.dmusic_player_btn_pause_description : R.string.dmusic_player_btn_play_description);
            }
            baseButton2.setContentDescription(string);
        }
        BaseButton baseButton3 = this.playPauseButton;
        if (baseButton3 == null) {
            return;
        }
        baseButton3.setVisibility(0);
    }

    public final void setLoadingImage() {
        PlayerViewEnabledStateManager enabledStateManager = this.stageViewModel.getEnabledStateManager();
        if (enabledStateManager != null && enabledStateManager.getControlsAreEnabled()) {
            BaseButton baseButton = this.playPauseButton;
            if (baseButton != null) {
                baseButton.setVisibility(4);
            }
            ProgressBar progressBar = this.playPauseProgressBar;
            if (progressBar == null) {
                return;
            }
            progressBar.setVisibility(0);
        }
    }

    public final void showDialog(FragmentActivity activity, DialogFragment dialog) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(dialog, "dialog");
        FragmentTransaction fragmentTransactionBeginTransaction = activity.getSupportFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "activity.supportFragmentManager.beginTransaction()");
        fragmentTransactionBeginTransaction.add(dialog, (String) null);
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
    }

    private final void setupLikeButton(StyleSheet styleSheet) {
        BaseButton.StyleBuilder iconBuilder;
        Resources resources;
        if (ScreenUtil.isPortrait()) {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY);
        } else if (this.landscapeType == LandscapeType.TABLET_LANDSCAPE) {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        } else {
            iconBuilder = styleSheet.getIconBuilder(IconSizeKey.TINY, IconStyleKey.GLASS);
        }
        Context context = this.context;
        if (context != null) {
            Drawable drawable = ContextCompat.getDrawable(context, R.drawable.ic_action_favorite_svg);
            if (drawable != null && iconBuilder != null) {
                iconBuilder.withIcon(drawable);
            }
            Drawable drawable2 = ContextCompat.getDrawable(context, R.drawable.ic_action_favoriteon_svg);
            if (drawable2 != null) {
                if (iconBuilder != null) {
                    iconBuilder.withActiveIcon(drawable2);
                }
                Context context2 = getContext();
                Integer numValueOf = null;
                if (context2 != null && (resources = context2.getResources()) != null) {
                    numValueOf = Integer.valueOf(resources.getColor(R.color.cyan_accent));
                }
                if (numValueOf != null) {
                    numValueOf.intValue();
                    drawable2.setColorFilter(numValueOf.intValue(), PorterDuff.Mode.MULTIPLY);
                }
            }
        }
        BaseButton likeButton = getLikeButton();
        if (likeButton != null && iconBuilder != null) {
            iconBuilder.applyStyle(likeButton);
        }
        this.stageViewModel.updateLikeButton();
    }

    private final void setupStageToolbar(StyleSheet styleSheet) {
        Toolbar toolbar = getToolbar();
        if (toolbar != null) {
            ConstraintLayout constraintLayout = (ConstraintLayout) toolbar.findViewById(R.id.toolbar_inner_layout);
            if (constraintLayout != null) {
                Context context = getContext();
                if (context == null) {
                    throw new NullPointerException("null cannot be cast to non-null type android.app.Activity");
                }
                ToolbarKt.attachToToolbarLayout(toolbar, constraintLayout, (Activity) context);
            }
            toolbar.setAlpha(1.0f);
        }
        Context context2 = this.context;
        if (context2 != null && toolbar != null) {
            ToolbarKt.initAlexaButtonStyle(toolbar, styleSheet, getAlexaButton(), context2);
        }
        BaseButton backButton = getBackButton();
        if (backButton != null) {
            backButton.setVisibility(0);
        }
        Context context3 = this.context;
        if (context3 == null || toolbar == null) {
            return;
        }
        ToolbarKt.initStageBackButtonStyle(toolbar, styleSheet, getBackButton(), context3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Toolbar getToolbar() {
        View toolbarLayout = getToolbarLayout();
        if (toolbarLayout == null) {
            return null;
        }
        return (Toolbar) toolbarLayout.findViewById(R.id.toolbar);
    }

    private final View getToolbarLayout() {
        Context context = this.context;
        Activity activity = context instanceof Activity ? (Activity) context : null;
        boolean z = false;
        if (activity != null && ActivityKt.isAvailable(activity)) {
            z = true;
        }
        if (z) {
            return activity.findViewById(R.id.toolbar_layout);
        }
        return null;
    }

    public final void setOverflowButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        BaseButton overflowButton = getOverflowButton();
        if (overflowButton == null) {
            return;
        }
        overflowButton.setOnClickListener(onClickListener);
    }

    public final void showOverflowButton(boolean show) {
        BaseButton overflowButton = getOverflowButton();
        if (overflowButton == null) {
            return;
        }
        overflowButton.setVisibility(show ? 0 : 8);
    }

    public final void setLikeButtonOnClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        BaseButton likeButton = getLikeButton();
        if (likeButton == null) {
            return;
        }
        likeButton.setOnClickListener(onClickListener);
    }

    public final void setLikeButtonState(boolean active) {
        Resources resources;
        BaseButton likeButton = getLikeButton();
        if (likeButton != null) {
            likeButton.setActive(active);
        }
        int i = active ? R.string.dmusic_like_button_liked_state_desc : R.string.dmusic_like_button_neutral_state_desc;
        BaseButton likeButton2 = getLikeButton();
        if (likeButton2 == null) {
            return;
        }
        Context context = this.context;
        String string = null;
        if (context != null && (resources = context.getResources()) != null) {
            string = resources.getString(i);
        }
        likeButton2.setContentDescription(string);
    }

    public final boolean isLikeButtonActive() {
        BaseButton likeButton = getLikeButton();
        if (likeButton == null) {
            return false;
        }
        return likeButton.getIsActive();
    }

    public final void setLikeButtonEnabledState(boolean isEnabled) {
        BaseButton likeButton = getLikeButton();
        if (likeButton == null) {
            return;
        }
        likeButton.setEnabled(isEnabled);
    }

    public final void showLikeButton(boolean show) {
        BaseButton likeButton = getLikeButton();
        if (likeButton == null) {
            return;
        }
        likeButton.setVisibility(show ? 0 : 8);
    }

    private final void showHideAlexaButton() {
        BaseButton alexaButton = getAlexaButton();
        if (alexaButton == null) {
            return;
        }
        alexaButton.setVisibility((AmazonApplication.getCapabilities().isAlexaEnabled() && ScreenUtil.isPortrait()) ? 0 : 8);
    }

    public final void onAlexaButtonClick() {
        this.stageViewModel.onAlexaButtonClick();
    }

    public final void initializeAlexa() {
        this.stageViewModel.initializeAlexa();
        showHideAlexaButton();
    }

    public final void updateAudioQualityBadge(String badgeText, String contentDescription) {
        BauhausSeekBarWidget seekbarView = getSeekbarView();
        if (seekbarView == null) {
            return;
        }
        seekbarView.updateAudioQualityBadge(badgeText, contentDescription, this.stageTooltipPresenter);
    }

    public final void setAudioQualityBadgeClickListener(View.OnClickListener onClickListener) {
        Intrinsics.checkNotNullParameter(onClickListener, "onClickListener");
        BauhausSeekBarWidget seekbarView = getSeekbarView();
        if (seekbarView == null) {
            return;
        }
        seekbarView.setAudioQualityBadgeClickListener(onClickListener);
    }

    public final void hideAudioQualityBadge() {
        BauhausSeekBarWidget seekbarView = getSeekbarView();
        if (seekbarView == null) {
            return;
        }
        seekbarView.hideAudioQualityBadge();
    }

    public static /* synthetic */ void addScreenOverlay$default(StageViewPresenter stageViewPresenter, OverlayContainer overlayContainer, boolean z, boolean z2, boolean z3, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        if ((i & 4) != 0) {
            z2 = true;
        }
        if ((i & 8) != 0) {
            z3 = true;
        }
        stageViewPresenter.addScreenOverlay(overlayContainer, z, z2, z3);
    }

    @JvmOverloads
    public final void addScreenOverlay(OverlayContainer overlayContainer, boolean hideControls, boolean addToBackStack, boolean animate) {
        Fragment overlayFragment;
        Intrinsics.checkNotNullParameter(overlayContainer, "overlayContainer");
        if (addToBackStack) {
            emptyBackStackAndReturnLastElement();
        }
        getOverlayTypeStateProvider().getCurrentOverlay().setValue(overlayContainer.getOverlayViewType().getOverlayType());
        Fragment overlayFragment2 = overlayContainer.getOverlayFragment();
        OverlayContainer overlayContainer2 = this.partialOverlayContainer;
        if (overlayContainer2 != null && (overlayFragment = overlayContainer2.getOverlayFragment()) != null) {
            removeOverlayFragment(overlayFragment);
        }
        FragmentController fragmentController = getFragmentController();
        if (fragmentController != null) {
            fragmentController.changeScreenFragment(overlayFragment2, true, true, animate);
        }
        if (addToBackStack) {
            this.fullscreenOverlayBackstack.add(overlayContainer);
        }
        if (hideControls) {
            hideControls();
        }
    }

    private final void removeOverlayFragment(Fragment fragment) {
        FragmentController fragmentController = getFragmentController();
        if (fragmentController != null) {
            fragmentController.removeFragment(fragment);
        }
        FragmentController fragmentController2 = getFragmentController();
        if (fragmentController2 == null) {
            return;
        }
        fragmentController2.clearBackStack(fragment);
    }

    private final void clearOverlayBackstack() {
        while (!this.fullscreenOverlayBackstack.isEmpty()) {
            Log.info(this.TAG, Intrinsics.stringPlus("Popping overlay fragment ", this.fullscreenOverlayBackstack.get(0)));
            emptyBackStackAndReturnLastElement();
        }
    }

    private final void hideControls() {
        StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout != null) {
            overlayButtonsContainerLayout.setVisibility(8);
        }
        if (ScreenUtil.isPortrait()) {
            StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
            if (bottomButtonsContainerView != null) {
                bottomButtonsContainerView.setVisibility(8);
            }
            StagePortraitPlaybackControlsView playbackControlsView = getPlaybackControlsView();
            if (playbackControlsView != null) {
                playbackControlsView.setVisibility(8);
            }
        } else {
            StageLandscapeControlsView landscapeControlsView = getLandscapeControlsView();
            if (landscapeControlsView != null) {
                landscapeControlsView.setVisibility(8);
            }
        }
        BauhausSeekBarWidget seekbarView = getSeekbarView();
        if (seekbarView != null) {
            seekbarView.setVisibility(8);
        }
        LinearLayout playerMetadataContainer = getPlayerMetadataContainer();
        if (playerMetadataContainer == null) {
            return;
        }
        playerMetadataContainer.setVisibility(8);
    }

    public final void showControls() {
        StageOverlayButtonsLayout overlayButtonsContainerLayout = getOverlayButtonsContainerLayout();
        if (overlayButtonsContainerLayout != null) {
            overlayButtonsContainerLayout.setVisibility(0);
        }
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            bottomButtonsContainerView.setVisibility(0);
        }
        if (ScreenUtil.isLandscape()) {
            StageLandscapeControlsView landscapeControlsView = getLandscapeControlsView();
            if (landscapeControlsView != null) {
                landscapeControlsView.setVisibility(0);
            }
        } else {
            StagePortraitPlaybackControlsView playbackControlsView = getPlaybackControlsView();
            if (playbackControlsView != null) {
                playbackControlsView.setVisibility(0);
            }
        }
        BauhausSeekBarWidget seekbarView = getSeekbarView();
        if (seekbarView != null) {
            seekbarView.setVisibility(0);
        }
        LinearLayout playerMetadataContainer = getPlayerMetadataContainer();
        if (playerMetadataContainer == null) {
            return;
        }
        playerMetadataContainer.setVisibility(0);
    }

    private final void showActionBar() {
        Integer compatColor;
        Activity currentActivity = AndroidUtils.getCurrentActivity();
        ColorDrawable colorDrawable = null;
        NowPlayingFragmentActivity nowPlayingFragmentActivity = currentActivity instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) currentActivity : null;
        if (nowPlayingFragmentActivity == null) {
            return;
        }
        ActionBar supportActionBar = nowPlayingFragmentActivity.getSupportActionBar();
        if (supportActionBar != null) {
            Context context = getContext();
            if (context != null && (compatColor = ContextKt.getCompatColor(context, R.color.transparent)) != null) {
                colorDrawable = new ColorDrawable(compatColor.intValue());
            }
            supportActionBar.setBackgroundDrawable(colorDrawable);
        }
        nowPlayingFragmentActivity.showActionBar();
    }

    private final OverlayContainer emptyBackStackAndReturnLastElement() {
        Fragment overlayFragment;
        OverlayContainer overlayContainerPeek = null;
        while (!this.fullscreenOverlayBackstack.isEmpty()) {
            overlayContainerPeek = this.fullscreenOverlayBackstack.peek();
            if (overlayContainerPeek != null && (overlayFragment = overlayContainerPeek.getOverlayFragment()) != null) {
                removeOverlayFragment(overlayFragment);
            }
            this.fullscreenOverlayBackstack.pop();
        }
        return overlayContainerPeek;
    }

    /* JADX WARN: Removed duplicated region for block: B:42:0x007a  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean popOverlayFragmentFromBackstack() {
        /*
            Method dump skipped, instruction units count: 368
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.view.stage.StageViewPresenter.popOverlayFragmentFromBackstack():boolean");
    }

    private final OverlayType getPresentPartialOverlay() {
        OverlaySelectionType overlaySelectionType = getOverlayTypeStateProvider().getOverlaySelectionType();
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.None.INSTANCE)) {
            return OverlayType.ALBUM_ART;
        }
        boolean z = false;
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Lyrics.INSTANCE)) {
            StageDataModel stageDataModel = this.stageViewModel.getStageDataModel();
            if (stageDataModel != null && stageDataModel.getIsLyrics()) {
                return OverlayType.LYRICS_PARTIAL;
            }
            StageDataModel stageDataModel2 = this.stageViewModel.getStageDataModel();
            if (stageDataModel2 != null && stageDataModel2.getIsXray()) {
                z = true;
            }
            if (z) {
                return OverlayType.XRAY_PARTIAL;
            }
            return OverlayType.ALBUM_ART;
        }
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Xray.INSTANCE)) {
            StageDataModel stageDataModel3 = this.stageViewModel.getStageDataModel();
            if ((stageDataModel3 != null && stageDataModel3.getIsXray()) && !isXrayShownForCurrentTrack()) {
                return OverlayType.XRAY_PARTIAL;
            }
            StageDataModel stageDataModel4 = this.stageViewModel.getStageDataModel();
            if (stageDataModel4 != null && stageDataModel4.getIsLyrics()) {
                z = true;
            }
            if (z) {
                return OverlayType.LYRICS_PARTIAL;
            }
            return OverlayType.ALBUM_ART;
        }
        if (Intrinsics.areEqual(overlaySelectionType, OverlaySelectionType.Automatic.INSTANCE)) {
            return getStageAutomatedSequencingHandler().getAutomatedSequenceCurrentOverlay();
        }
        throw new NoWhenBranchMatchedException();
    }

    public final boolean isInFullScreenOverlayMode() {
        return !this.fullscreenOverlayBackstack.isEmpty();
    }

    public final Bitmap getBitmapFromDrawable(Drawable drawable) {
        Intrinsics.checkNotNullParameter(drawable, "drawable");
        return DrawableKt.toBitmap$default(drawable, 0, 0, null, 7, null);
    }

    public final Bitmap getBlurBitmap(Bitmap bitmap, float blurRadius) {
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        Context context = this.context;
        if (context == null) {
            return null;
        }
        return BitmapEffectUtils.INSTANCE.getBlurBitmap(context, bitmap, blurRadius);
    }

    public final void refreshBottomButtonsVisibility() {
        if (NowPlayingUtil.isVideoPlaying()) {
            StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
            if (bottomButtonsContainerView == null) {
                return;
            }
            bottomButtonsContainerView.updateButtonVisibility(StageBottomIconType.CASTING, false);
            return;
        }
        StageBottomButtonsView bottomButtonsContainerView2 = getBottomButtonsContainerView();
        if (bottomButtonsContainerView2 == null) {
            return;
        }
        bottomButtonsContainerView2.updateButtonVisibility(StageBottomIconType.CASTING, true);
    }

    private final List<View> getLandscapeAudioViews() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new ViewGroup[]{getToolbar(), getStageContainerLayout(), getLandscapeAudioBackgroundOverlay()});
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void handleLandscapePlayAreaTap() {
        if (this.landscapeControlsVisible) {
            fadeOutLandscapeViews();
        } else {
            fadeInLandscapeViews();
        }
    }

    private final void fadeInLandscapeViews() {
        this.landscapeControlsVisible = true;
        clearLandscapeViewsAnimation();
        toggleLandscapeViews(0);
        this.landscapeControlsAnimationSubscription = Observable.timer(CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS, rx.android.schedulers.AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda3
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StageViewPresenter.m1767fadeInLandscapeViews$lambda83(this.f$0, (Long) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fadeInLandscapeViews$lambda-83, reason: not valid java name */
    public static final void m1767fadeInLandscapeViews$lambda83(StageViewPresenter this$0, Long l) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fadeOutLandscapeViews();
    }

    private final void fadeOutLandscapeViews() {
        this.landscapeControlsVisible = false;
        toggleLandscapeViews(4);
    }

    private final void clearLandscapeViewsAnimation() {
        Subscription subscription = this.landscapeControlsAnimationSubscription;
        if (subscription != null) {
            boolean z = false;
            if (subscription != null && !subscription.isUnsubscribed()) {
                z = true;
            }
            if (z) {
                Subscription subscription2 = this.landscapeControlsAnimationSubscription;
                if (subscription2 != null) {
                    subscription2.unsubscribe();
                }
                this.landscapeControlsAnimationSubscription = null;
            }
        }
    }

    private final void toggleLandscapeViews(int visibility) {
        for (View view : getLandscapeAudioViews()) {
            if (view != null) {
                ViewExtensions.fadeAndToggleViewVisibility(view, visibility, 400L);
            }
        }
    }

    private final void refreshPortraitPlaybackControls() {
        View view = this.viewRoot.get();
        StagePortraitPlaybackControlsView stagePortraitPlaybackControlsView = view == null ? null : (StagePortraitPlaybackControlsView) view.findViewById(R.id.stage_portrait_controls);
        this.playPauseButton = stagePortraitPlaybackControlsView == null ? null : (BaseButton) stagePortraitPlaybackControlsView.findViewById(R.id.PersistentPlayerPlayButton);
        this.nextButton = stagePortraitPlaybackControlsView == null ? null : (BaseButton) stagePortraitPlaybackControlsView.findViewById(R.id.PersistentPlayerNextButton);
        this.prevButton = stagePortraitPlaybackControlsView == null ? null : (BaseButton) stagePortraitPlaybackControlsView.findViewById(R.id.PersistentPlayerPrevButton);
        View view2 = this.viewRoot.get();
        setPlayPauseProgressBar(view2 != null ? (ProgressBar) view2.findViewById(android.R.id.progress) : null);
    }

    public final void updateOverflowMenuButtonSecondaryIcon() {
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView == null) {
            return;
        }
        bottomButtonsContainerView.updateOverflowMenuSecondaryIconTo();
    }

    private final void setupPlaybackControlsClickListeners() {
        BaseButton baseButton = this.playPauseButton;
        if (baseButton != null) {
            baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StageViewPresenter.m1790setupPlaybackControlsClickListeners$lambda85(this.f$0, view);
                }
            });
        }
        BaseButton baseButton2 = this.nextButton;
        if (baseButton2 != null) {
            baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StageViewPresenter.m1791setupPlaybackControlsClickListeners$lambda86(this.f$0, view);
                }
            });
        }
        BaseButton baseButton3 = this.prevButton;
        if (baseButton3 == null) {
            return;
        }
        baseButton3.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageViewPresenter.m1792setupPlaybackControlsClickListeners$lambda87(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPlaybackControlsClickListeners$lambda-85, reason: not valid java name */
    public static final void m1790setupPlaybackControlsClickListeners$lambda85(StageViewPresenter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onPlayPauseButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPlaybackControlsClickListeners$lambda-86, reason: not valid java name */
    public static final void m1791setupPlaybackControlsClickListeners$lambda86(StageViewPresenter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onNextButtonClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupPlaybackControlsClickListeners$lambda-87, reason: not valid java name */
    public static final void m1792setupPlaybackControlsClickListeners$lambda87(StageViewPresenter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onPrevButtonClick();
    }

    public final void updatePlayerControls(PlayerViewEnabledStateManager enabledStateManager) {
        EnabledViewState enabledViewState;
        EnabledViewState enabledViewState2;
        EnabledViewState enabledViewState3;
        BaseButton baseButton = this.nextButton;
        if (baseButton != null && enabledStateManager != null && (enabledViewState3 = enabledStateManager.getEnabledViewState(PlayerViewAction.NEXT)) != null) {
            enabledViewState3.updateViewState(baseButton, Boolean.FALSE);
        }
        BaseButton baseButton2 = this.prevButton;
        if (baseButton2 != null && enabledStateManager != null && (enabledViewState2 = enabledStateManager.getEnabledViewState(PlayerViewAction.PREVIOUS)) != null) {
            enabledViewState2.updateViewState(baseButton2, Boolean.FALSE);
        }
        BaseButton baseButton3 = this.playPauseButton;
        if (baseButton3 != null && enabledStateManager != null && (enabledViewState = enabledStateManager.getEnabledViewState(PlayerViewAction.PLAY)) != null) {
            enabledViewState.updateViewState(baseButton3, Boolean.FALSE);
        }
        io.reactivex.rxjava3.core.Observable<AbstractItem> abstractItemObservable = MediaItemHelper.getAbstractItemObservable(this.playbackController.getCurrentMediaItem());
        if (abstractItemObservable == null) {
            return;
        }
        abstractItemObservable.subscribe(new Consumer() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda29
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageViewPresenter.m1794updatePlayerControls$lambda92(this.f$0, (AbstractItem) obj);
            }
        }, new Consumer() { // from class: com.amazon.mp3.view.stage.StageViewPresenter$$ExternalSyntheticLambda30
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                StageViewPresenter.m1795updatePlayerControls$lambda93(this.f$0, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updatePlayerControls$lambda-92, reason: not valid java name */
    public static final void m1794updatePlayerControls$lambda92(StageViewPresenter this$0, AbstractItem abstractItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean zIsPrevButtonEnabled = this$0.getStageViewModel().isPrevButtonEnabled(abstractItem);
        StagePortraitPlaybackControlsView playbackControlsView = this$0.getPlaybackControlsView();
        if (playbackControlsView != null) {
            playbackControlsView.updateControls(this$0.getStageViewModel().isNextButtonEnabled(), zIsPrevButtonEnabled);
        }
        StageLandscapeControlsView landscapeControlsView = this$0.getLandscapeControlsView();
        if (landscapeControlsView == null) {
            return;
        }
        landscapeControlsView.updateControls(this$0.getStageViewModel().isNextButtonEnabled(), zIsPrevButtonEnabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: updatePlayerControls$lambda-93, reason: not valid java name */
    public static final void m1795updatePlayerControls$lambda93(StageViewPresenter this$0, Throwable throwable) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(throwable, "throwable");
        throwable.printStackTrace();
        Log.error(this$0.TAG, "Failed updating previous button", throwable);
    }

    private final boolean isOffline() {
        return !ConnectivityUtil.hasAnyInternetConnection(this.context);
    }

    public final void refreshBottomButtons() {
        StageBottomButtonsView bottomButtonsContainerView = getBottomButtonsContainerView();
        if (bottomButtonsContainerView != null) {
            StyleSheet styleSheet = this.styleSheet;
            if (styleSheet == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                styleSheet = null;
            }
            StageBottomButtonsView.initViewStyling$default(bottomButtonsContainerView, styleSheet, false, 2, null);
        }
        setBottomButtonsOnClickListener();
    }

    public final void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        this.stageOverlayMetricsReporter.onConfigurationChanged();
    }
}
