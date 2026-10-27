package com.amazon.mp3.playback.fragment;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureParams$Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.Observer;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.avod.util.playback.DurationUtils;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.library.provider.source.nowplaying.MediaItemHelper;
import com.amazon.mp3.library.provider.source.nowplaying.NowPlayingQueueUtil;
import com.amazon.mp3.playback.PlayerContextMenu;
import com.amazon.mp3.playback.activity.LeavingActivityReason;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.VideoPlayerFragment$metadataChangedListener$2;
import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.playback.util.VideoPreviewSeekBarTracker;
import com.amazon.mp3.playback.video.VideoUtil;
import com.amazon.mp3.playback.view.PersistentPlayer;
import com.amazon.mp3.playback.view.VideoPreviewWindow;
import com.amazon.mp3.starlight.provider.VideoPlayerStateProvider;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MediaPlaybackUtil;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.util.extensions.ActivityKt;
import com.amazon.mp3.util.extensions.FragmentKt;
import com.amazon.mp3.util.extensions.ViewExtensions;
import com.amazon.mp3.view.BauhausSeekBarWidget;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.mp3.view.player.PlayerMetadataView;
import com.amazon.mp3.voice.NowPlayingVoiceAccessProvider;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaCollectionInfo;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.OnMetadataChangedListener;
import com.amazon.music.media.playback.OnPlayStateChangedListener;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.PlaybackController;
import com.amazon.music.media.playback.config.PlaybackConfig;
import com.amazon.music.media.playback.player.Player;
import com.amazon.music.media.playback.player.impl.PrimeVideoPlayer;
import com.amazon.music.media.playback.util.PlayerUtil;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.EntityIdType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageSubType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.SpacerKey;
import com.amazon.music.voice.AlexaEntryPoint;
import com.amazon.ui.foundations.styles.GridSize;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.amazon.ui.foundations.views.BaseButton;
import com.amazon.video.sdk.player.AdditionalPlayerConfigs;
import com.amazon.video.sdk.player.FeatureConfigs;
import com.amazon.video.sdk.player.PlayerConfigData;
import com.amazon.video.sdk.player.RenderingConfig;
import com.amazon.video.sdk.player.SurfaceHandlingMode;
import com.amazon.video.sdk.player.playlist.PlaylistFeatureConfig;
import com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureConfig;
import com.amazon.video.sdk.stream.StreamFeatureConfig;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt___SequencesKt;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: VideoPlayerFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000à\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \u0081\u00022\u00020\u0001:\u0002\u0081\u0002B\t¢\u0006\u0006\bÿ\u0001\u0010\u0080\u0002J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0003J\u0012\u0010\b\u001a\u00020\u00022\b\b\u0001\u0010\u0007\u001a\u00020\u0006H\u0002J\b\u0010\t\u001a\u00020\u0002H\u0002J\b\u0010\u000b\u001a\u00020\nH\u0002J\b\u0010\f\u001a\u00020\u0002H\u0002J\u0014\u0010\u000f\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0003J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\nH\u0002J\u0010\u0010\u0013\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\nH\u0002J\b\u0010\u0014\u001a\u00020\u0002H\u0002J\u0010\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u0002H\u0002J\u0010\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\nH\u0002J\b\u0010\u001b\u001a\u00020\u0002H\u0002J\b\u0010\u001c\u001a\u00020\u0002H\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0002J\b\u0010\u001e\u001a\u00020\u0002H\u0002J\b\u0010\u001f\u001a\u00020\u0002H\u0002J\n\u0010!\u001a\u0004\u0018\u00010 H\u0002J\u0010\u0010#\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020\nH\u0002J\u0010\u0010%\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u0015H\u0002J\u0010\u0010'\u001a\u00020\u00022\u0006\u0010&\u001a\u00020\nH\u0002J\b\u0010(\u001a\u00020\u0002H\u0002J\u0010\u0010)\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\nH\u0002J\b\u0010*\u001a\u00020\u0002H\u0002J\b\u0010+\u001a\u00020\u0002H\u0002J\u0018\u00100\u001a\u00020\u00022\u0006\u0010-\u001a\u00020,2\u0006\u0010/\u001a\u00020.H\u0002J\u0010\u00101\u001a\u00020\u00022\u0006\u0010-\u001a\u00020,H\u0002J\b\u00102\u001a\u00020\u0002H\u0002J\b\u00103\u001a\u00020\u0002H\u0002J\u0012\u00106\u001a\u00020\u00022\b\u00105\u001a\u0004\u0018\u000104H\u0002J\b\u00107\u001a\u00020\u0002H\u0002J\u0014\u00108\u001a\u00020\u00022\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0002J&\u0010@\u001a\u0004\u0018\u00010?2\u0006\u0010:\u001a\u0002092\b\u0010<\u001a\u0004\u0018\u00010;2\b\u0010>\u001a\u0004\u0018\u00010=H\u0016J\u001a\u0010B\u001a\u00020\u00022\u0006\u0010A\u001a\u00020?2\b\u0010>\u001a\u0004\u0018\u00010=H\u0016J\b\u0010C\u001a\u00020\u0002H\u0016J\b\u0010D\u001a\u00020\u0002H\u0016J\u0010\u0010F\u001a\u00020\u00022\u0006\u0010E\u001a\u00020\nH\u0016J\b\u0010G\u001a\u00020\u0002H\u0016J\b\u0010H\u001a\u00020\u0002H\u0016J\b\u0010I\u001a\u00020\u0002H\u0016J\u0010\u0010J\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0016\u0010N\u001a\u00020\u00022\u0006\u0010K\u001a\u00020\n2\u0006\u0010M\u001a\u00020LJ\u000e\u0010Q\u001a\u00020\u00022\u0006\u0010P\u001a\u00020OJ\u0010\u0010T\u001a\u00020\n2\u0006\u0010S\u001a\u00020RH\u0016J\u0006\u0010U\u001a\u00020\u0002R\u0018\u0010W\u001a\u0004\u0018\u00010V8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bW\u0010XR\u0018\u0010Z\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bZ\u0010[R\u0016\u0010\\\u001a\u00020\u00158\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\\\u0010]R\u0016\u0010^\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b^\u0010_R\u0016\u0010`\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b`\u0010_R\u0016\u0010a\u001a\u00020\n8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\ba\u0010_R\u0018\u0010c\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bc\u0010dR\u001b\u0010j\u001a\u00020e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bf\u0010g\u001a\u0004\bh\u0010iR\u001b\u0010m\u001a\u00020e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bk\u0010g\u001a\u0004\bl\u0010iR\u001b\u0010r\u001a\u00020n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bo\u0010g\u001a\u0004\bp\u0010qR\u001b\u0010u\u001a\u00020n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bs\u0010g\u001a\u0004\bt\u0010qR\u001b\u0010z\u001a\u00020v8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bw\u0010g\u001a\u0004\bx\u0010yR\u001b\u0010\u007f\u001a\u00020{8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b|\u0010g\u001a\u0004\b}\u0010~R\u001e\u0010\u0082\u0001\u001a\u00020n8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0080\u0001\u0010g\u001a\u0005\b\u0081\u0001\u0010qR\u001e\u0010\u0085\u0001\u001a\u00020n8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0083\u0001\u0010g\u001a\u0005\b\u0084\u0001\u0010qR \u0010\u008a\u0001\u001a\u00030\u0086\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0087\u0001\u0010g\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001R\u001a\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008c\u0001\u0010\u008d\u0001R\"\u0010\u0090\u0001\u001a\r \u008f\u0001*\u0005\u0018\u00010\u008e\u00010\u008e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R \u0010\u0096\u0001\u001a\u00030\u0092\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0093\u0001\u0010g\u001a\u0006\b\u0094\u0001\u0010\u0095\u0001R\u001f\u0010\u009a\u0001\u001a\u00020;8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0097\u0001\u0010g\u001a\u0006\b\u0098\u0001\u0010\u0099\u0001R \u0010\u009f\u0001\u001a\u00030\u009b\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u009c\u0001\u0010g\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R \u0010¢\u0001\u001a\u00030\u009b\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b \u0001\u0010g\u001a\u0006\b¡\u0001\u0010\u009e\u0001R \u0010¥\u0001\u001a\u00030\u009b\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b£\u0001\u0010g\u001a\u0006\b¤\u0001\u0010\u009e\u0001R\u001f\u0010©\u0001\u001a\u00020?8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¦\u0001\u0010g\u001a\u0006\b§\u0001\u0010¨\u0001R \u0010¬\u0001\u001a\u0004\u0018\u00010{8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\bª\u0001\u0010g\u001a\u0005\b«\u0001\u0010~R!\u0010¯\u0001\u001a\u0004\u0018\u00010?8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u00ad\u0001\u0010g\u001a\u0006\b®\u0001\u0010¨\u0001R \u0010´\u0001\u001a\u00030°\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b±\u0001\u0010g\u001a\u0006\b²\u0001\u0010³\u0001R\"\u0010¹\u0001\u001a\u0005\u0018\u00010µ\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¶\u0001\u0010g\u001a\u0006\b·\u0001\u0010¸\u0001R \u0010¾\u0001\u001a\u00030º\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b»\u0001\u0010g\u001a\u0006\b¼\u0001\u0010½\u0001R\u001c\u0010À\u0001\u001a\u0005\u0018\u00010¿\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÀ\u0001\u0010Á\u0001R\u001c\u0010Ã\u0001\u001a\u0005\u0018\u00010Â\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÃ\u0001\u0010Ä\u0001R\u001c\u0010Æ\u0001\u001a\u0005\u0018\u00010Å\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÆ\u0001\u0010Ç\u0001R\u001c\u0010É\u0001\u001a\u0005\u0018\u00010È\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010Ê\u0001R*\u0010Ì\u0001\u001a\u00030Ë\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\bÌ\u0001\u0010Í\u0001\u001a\u0006\bÎ\u0001\u0010Ï\u0001\"\u0006\bÐ\u0001\u0010Ñ\u0001R*\u0010Ó\u0001\u001a\u00030Ò\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\bÓ\u0001\u0010Ô\u0001\u001a\u0006\bÕ\u0001\u0010Ö\u0001\"\u0006\b×\u0001\u0010Ø\u0001R\u001a\u0010Ú\u0001\u001a\u00030Ù\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bÚ\u0001\u0010Û\u0001R$\u0010Ý\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020Ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÝ\u0001\u0010Þ\u0001R\u001a\u0010à\u0001\u001a\u00030ß\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\bà\u0001\u0010á\u0001R\u001a\u0010â\u0001\u001a\u0004\u0018\u00010Y8\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bâ\u0001\u0010[R\u001f\u0010ä\u0001\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bã\u0001\u0010g\u001a\u0006\bä\u0001\u0010å\u0001R \u0010ê\u0001\u001a\u00030æ\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bç\u0001\u0010g\u001a\u0006\bè\u0001\u0010é\u0001R\u0017\u0010ë\u0001\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bë\u0001\u0010ì\u0001R\u0017\u0010í\u0001\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bí\u0001\u0010ì\u0001R\u0017\u0010î\u0001\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bî\u0001\u0010ì\u0001R \u0010ó\u0001\u001a\u00030ï\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bð\u0001\u0010g\u001a\u0006\bñ\u0001\u0010ò\u0001R \u0010ø\u0001\u001a\u00030ô\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bõ\u0001\u0010g\u001a\u0006\bö\u0001\u0010÷\u0001R\u0018\u0010ú\u0001\u001a\u00030ù\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bú\u0001\u0010û\u0001R\u0017\u0010þ\u0001\u001a\u00020\u00158BX\u0082\u0004¢\u0006\b\u001a\u0006\bü\u0001\u0010ý\u0001¨\u0006\u0082\u0002"}, d2 = {"Lcom/amazon/mp3/playback/fragment/VideoPlayerFragment;", "Lcom/amazon/mp3/fragment/BaseFragment;", "", "setupTrickPlay", "initViewStyling", "enterPIPMode", "Landroid/content/Context;", "appContext", "navigateToLauncherTask", "initPlayerAndResumePlaybackIfNeeded", "", "isPlayerPaused", "replacePersistentPlayer", "Landroid/content/res/Configuration;", "newConfig", "setupViews", "autoFadeOut", "showControlsAndOptionallyFadeOut", "playNext", "skipVideo", "setupPipView", "", "orientation", "updateVideoPlayerLayout", "setupCaptions", "enabled", "setImmersiveMode", "updateAll", "updateSeekBar", "updateHeader", "startPlayback", "initPlayer", "Lcom/amazon/video/sdk/player/FeatureConfigs;", "getFeatureConfigs", "isLandscape", "hideUserControls", "visibility", "toggleUserControls", "visible", "toggleActionBar", "toggleCaptionsButton", "setCaptionsEnabled", "registerConnectivityReceiver", "unregisterConnectivityReceiver", "Landroid/content/BroadcastReceiver;", "receiver", "Landroid/content/IntentFilter;", "intentFilter", "registerLocalBroadcastActivityReceiver", "unregisterLocalBroadcastActivityReceiver", "handleUserControlsVisibility", "handleUserControlsVisibilityOnPlaybackStateChange", "Lcom/amazon/ui/foundations/styles/GridSize;", "gridSize", "adjustLandscapeViews", "adjustPortraitViews", "initStyleSheet", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "Landroid/view/View;", "onCreateView", "view", "onViewCreated", "onDestroy", "onResume", "isInPictureInPictureMode", "onPictureInPictureModeChanged", "onPause", "onStart", "onStop", "onConfigurationChanged", "isVideoOn", "", "asin", "sendUiPageViewMetrics", "", "currentTimeMs", "updateVideoPreviewWindow", "Landroid/view/MenuItem;", "item", "onContextItemSelected", "addToggle", "Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;", "voiceAccessProvider", "Lcom/amazon/mp3/voice/NowPlayingVoiceAccessProvider;", "Lrx/Subscription;", "leavingActivitySubscription", "Lrx/Subscription;", "currentControllerVisibility", "I", "shouldResumePlayback", "Z", "hasShownControlsOnce", "isFragmentVisible", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "iconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "Landroid/widget/RelativeLayout;", "videoRootView$delegate", "Lkotlin/Lazy;", "getVideoRootView", "()Landroid/widget/RelativeLayout;", "videoRootView", "videoSurfaceView$delegate", "getVideoSurfaceView", "videoSurfaceView", "Landroid/widget/ImageView;", "captionsButton$delegate", "getCaptionsButton", "()Landroid/widget/ImageView;", "captionsButton", "expandContractButton$delegate", "getExpandContractButton", "expandContractButton", "Landroid/widget/ImageButton;", "videoPlayerShareButton$delegate", "getVideoPlayerShareButton", "()Landroid/widget/ImageButton;", "videoPlayerShareButton", "Landroid/widget/FrameLayout;", "overlayView$delegate", "getOverlayView", "()Landroid/widget/FrameLayout;", "overlayView", "closeButton$delegate", "getCloseButton", "closeButton", "overflowButton$delegate", "getOverflowButton", "overflowButton", "Lcom/amazon/mp3/view/player/PlayerMetadataView;", "playerMetadataView$delegate", "getPlayerMetadataView", "()Lcom/amazon/mp3/view/player/PlayerMetadataView;", "playerMetadataView", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "videoPlayer", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "Lcom/amazon/music/media/playback/PlaybackController;", "kotlin.jvm.PlatformType", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "Lcom/amazon/mp3/playback/PlayerContextMenu;", "playerContextMenu$delegate", "getPlayerContextMenu", "()Lcom/amazon/mp3/playback/PlayerContextMenu;", "playerContextMenu", "controlsContainer$delegate", "getControlsContainer", "()Landroid/view/ViewGroup;", "controlsContainer", "Lcom/amazon/ui/foundations/views/BaseButton;", "alexaButton$delegate", "getAlexaButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "alexaButton", "shareButton$delegate", "getShareButton", "shareButton", "playQueueButton$delegate", "getPlayQueueButton", "playQueueButton", "bottomSpacing$delegate", "getBottomSpacing", "()Landroid/view/View;", "bottomSpacing", "videoPersistentPlayerContainer$delegate", "getVideoPersistentPlayerContainer", "videoPersistentPlayerContainer", "toolbarLayout$delegate", "getToolbarLayout", "toolbarLayout", "Landroid/widget/LinearLayout;", "audioVideoToggleView$delegate", "getAudioVideoToggleView", "()Landroid/widget/LinearLayout;", "audioVideoToggleView", "Lcom/amazon/mp3/playback/view/VideoPreviewWindow;", "mVideoPreviewWindow$delegate", "getMVideoPreviewWindow", "()Lcom/amazon/mp3/playback/view/VideoPreviewWindow;", "mVideoPreviewWindow", "Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "seekBar$delegate", "getSeekBar", "()Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "seekBar", "Lcom/amazon/mp3/playback/util/VideoPreviewSeekBarTracker;", "mVideoPreviewSeekBarTracker", "Lcom/amazon/mp3/playback/util/VideoPreviewSeekBarTracker;", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Landroidx/lifecycle/LifecycleObserver;", "resumeObserver", "Landroidx/lifecycle/LifecycleObserver;", "Lcom/amazon/mp3/playback/fragment/PersistentPlayerFragment;", "persistentPlayerFragment", "Lcom/amazon/mp3/playback/fragment/PersistentPlayerFragment;", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "audioVideoToggleInteractor", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "getAudioVideoToggleInteractor", "()Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "setAudioVideoToggleInteractor", "(Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;)V", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "videoPlayerStateProvider", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "getVideoPlayerStateProvider", "()Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "setVideoPlayerStateProvider", "(Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;)V", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lkotlin/Function1;", "onPlaybackStarted", "Lkotlin/jvm/functions/Function1;", "Landroid/view/OrientationEventListener;", "orientationEventListener", "Landroid/view/OrientationEventListener;", "viewAnimationSubscription", "isTrickPlaySupported$delegate", "isTrickPlaySupported", "()Z", "Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "metadataChangedListener$delegate", "getMetadataChangedListener", "()Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "metadataChangedListener", "connectivityReceiver", "Landroid/content/BroadcastReceiver;", "homeActivityReceiver", "dialogActivityReceiver", "Landroid/view/MenuInflater;", "menuInflater$delegate", "getMenuInflater", "()Landroid/view/MenuInflater;", "menuInflater", "Landroid/widget/PopupMenu;", "popupMenu$delegate", "getPopupMenu", "()Landroid/widget/PopupMenu;", "popupMenu", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "playStateListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "getDeviceWidth", "()I", "deviceWidth", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class VideoPlayerFragment extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = VideoPlayerFragment.class.getSimpleName();

    /* JADX INFO: renamed from: alexaButton$delegate, reason: from kotlin metadata */
    private final Lazy alexaButton;
    public AudioVideoToggleUseCase audioVideoToggleInteractor;

    /* JADX INFO: renamed from: audioVideoToggleView$delegate, reason: from kotlin metadata */
    private final Lazy audioVideoToggleView;

    /* JADX INFO: renamed from: bottomSpacing$delegate, reason: from kotlin metadata */
    private final Lazy bottomSpacing;
    private final BroadcastReceiver connectivityReceiver;

    /* JADX INFO: renamed from: controlsContainer$delegate, reason: from kotlin metadata */
    private final Lazy controlsContainer;
    private final BroadcastReceiver dialogActivityReceiver;
    private boolean hasShownControlsOnce;
    private final BroadcastReceiver homeActivityReceiver;
    private BaseButton.StyleBuilder iconBuilder;
    private boolean isFragmentVisible;

    /* JADX INFO: renamed from: isTrickPlaySupported$delegate, reason: from kotlin metadata */
    private final Lazy isTrickPlaySupported;
    private Subscription leavingActivitySubscription;
    private VideoPreviewSeekBarTracker mVideoPreviewSeekBarTracker;

    /* JADX INFO: renamed from: mVideoPreviewWindow$delegate, reason: from kotlin metadata */
    private final Lazy mVideoPreviewWindow;
    private MediaItem mediaItem;

    /* JADX INFO: renamed from: menuInflater$delegate, reason: from kotlin metadata */
    private final Lazy menuInflater;

    /* JADX INFO: renamed from: metadataChangedListener$delegate, reason: from kotlin metadata */
    private final Lazy metadataChangedListener;
    private final Function1<Unit, Unit> onPlaybackStarted;
    private OrientationEventListener orientationEventListener;
    private PersistentPlayerFragment persistentPlayerFragment;

    /* JADX INFO: renamed from: playQueueButton$delegate, reason: from kotlin metadata */
    private final Lazy playQueueButton;
    private final OnPlayStateChangedListener playStateListener;
    private final PlaybackController playbackController;

    /* JADX INFO: renamed from: playerContextMenu$delegate, reason: from kotlin metadata */
    private final Lazy playerContextMenu;

    /* JADX INFO: renamed from: popupMenu$delegate, reason: from kotlin metadata */
    private final Lazy popupMenu;
    private LifecycleObserver resumeObserver;

    /* JADX INFO: renamed from: seekBar$delegate, reason: from kotlin metadata */
    private final Lazy seekBar;

    /* JADX INFO: renamed from: shareButton$delegate, reason: from kotlin metadata */
    private final Lazy shareButton;
    private boolean shouldResumePlayback;
    private StyleSheet styleSheet;

    /* JADX INFO: renamed from: toolbarLayout$delegate, reason: from kotlin metadata */
    private final Lazy toolbarLayout;

    /* JADX INFO: renamed from: videoPersistentPlayerContainer$delegate, reason: from kotlin metadata */
    private final Lazy videoPersistentPlayerContainer;
    private final PrimeVideoPlayer videoPlayer;
    public VideoPlayerStateProvider videoPlayerStateProvider;
    private Subscription viewAnimationSubscription;
    private NowPlayingVoiceAccessProvider voiceAccessProvider;
    private int currentControllerVisibility = 4;

    /* JADX INFO: renamed from: videoRootView$delegate, reason: from kotlin metadata */
    private final Lazy videoRootView = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$videoRootView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RelativeLayout invoke() {
            View viewInflate = this.this$0.getLayoutInflater().inflate(R.layout.video_player_fragment_container, (ViewGroup) null);
            if (viewInflate != null) {
                return (RelativeLayout) viewInflate;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
    });

    /* JADX INFO: renamed from: videoSurfaceView$delegate, reason: from kotlin metadata */
    private final Lazy videoSurfaceView = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$videoSurfaceView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RelativeLayout invoke() {
            return (RelativeLayout) this.this$0.getVideoRootView().findViewById(R.id.VideoPlaybackSurfaceLayout);
        }
    });

    /* JADX INFO: renamed from: captionsButton$delegate, reason: from kotlin metadata */
    private final Lazy captionsButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$captionsButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageView invoke() {
            return (ImageView) this.this$0.getVideoRootView().findViewById(R.id.VideoPlayerToggleCaptions);
        }
    });

    /* JADX INFO: renamed from: expandContractButton$delegate, reason: from kotlin metadata */
    private final Lazy expandContractButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$expandContractButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageView invoke() {
            return (ImageView) this.this$0.getVideoRootView().findViewById(R.id.VideoPlayerExpandContract);
        }
    });

    /* JADX INFO: renamed from: videoPlayerShareButton$delegate, reason: from kotlin metadata */
    private final Lazy videoPlayerShareButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageButton>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$videoPlayerShareButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageButton invoke() {
            return (ImageButton) this.this$0.getVideoRootView().findViewById(R.id.VideoPlayerShareButton);
        }
    });

    /* JADX INFO: renamed from: overlayView$delegate, reason: from kotlin metadata */
    private final Lazy overlayView = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$overlayView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FrameLayout invoke() {
            return (FrameLayout) this.this$0.getVideoRootView().findViewById(R.id.VideoBackgroundOverlay);
        }
    });

    /* JADX INFO: renamed from: closeButton$delegate, reason: from kotlin metadata */
    private final Lazy closeButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$closeButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageView invoke() {
            return (ImageView) this.this$0.getVideoRootView().findViewById(R.id.VideoHeaderClose);
        }
    });

    /* JADX INFO: renamed from: overflowButton$delegate, reason: from kotlin metadata */
    private final Lazy overflowButton = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$overflowButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ImageView invoke() {
            return (ImageView) this.this$0.getVideoRootView().findViewById(R.id.VideoHeaderOverflow);
        }
    });

    /* JADX INFO: renamed from: playerMetadataView$delegate, reason: from kotlin metadata */
    private final Lazy playerMetadataView = LazyKt__LazyJVMKt.lazy(new Function0<PlayerMetadataView>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$playerMetadataView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PlayerMetadataView invoke() {
            return (PlayerMetadataView) this.this$0.getVideoRootView().findViewById(R.id.VideoMetadataView);
        }
    });

    public VideoPlayerFragment() {
        PlaybackConfig playbackConfig;
        Playback playback = Playback.getInstance();
        Player primeVideoPlayer = (playback == null || (playbackConfig = playback.getPlaybackConfig()) == null) ? null : playbackConfig.getPrimeVideoPlayer();
        this.videoPlayer = primeVideoPlayer instanceof PrimeVideoPlayer ? (PrimeVideoPlayer) primeVideoPlayer : null;
        this.playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);
        this.playerContextMenu = LazyKt__LazyJVMKt.lazy(new Function0<PlayerContextMenu>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$playerContextMenu$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PlayerContextMenu invoke() {
                return new PlayerContextMenu(this.this$0.getActivity());
            }
        });
        this.controlsContainer = LazyKt__LazyJVMKt.lazy(new Function0<ViewGroup>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$controlsContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ViewGroup invoke() {
                return (ViewGroup) this.this$0.getVideoRootView().findViewById(R.id.controlsContainer);
            }
        });
        this.alexaButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$alexaButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.getVideoRootView().findViewById(R.id.iv_alexa);
            }
        });
        this.shareButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$shareButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.getVideoRootView().findViewById(R.id.iv_share);
            }
        });
        this.playQueueButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$playQueueButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.getVideoRootView().findViewById(R.id.PersistentPlayerPlayQueueButton);
            }
        });
        this.bottomSpacing = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$bottomSpacing$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                return this.this$0.getVideoRootView().findViewById(R.id.bottom_spacing);
            }
        });
        this.videoPersistentPlayerContainer = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$videoPersistentPlayerContainer$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final FrameLayout invoke() {
                return (FrameLayout) this.this$0.getVideoRootView().findViewById(R.id.VideoPersistentPlayerContainer);
            }
        });
        this.toolbarLayout = LazyKt__LazyJVMKt.lazy(new Function0<View>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$toolbarLayout$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final View invoke() {
                FragmentActivity activity = this.this$0.getActivity();
                if (activity == null) {
                    return null;
                }
                return activity.findViewById(R.id.toolbar_layout);
            }
        });
        this.audioVideoToggleView = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$audioVideoToggleView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LinearLayout invoke() {
                return (LinearLayout) this.this$0.getVideoRootView().findViewById(R.id.audioVideoToggleView);
            }
        });
        this.mVideoPreviewWindow = LazyKt__LazyJVMKt.lazy(new Function0<VideoPreviewWindow>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$mVideoPreviewWindow$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final VideoPreviewWindow invoke() {
                return (VideoPreviewWindow) this.this$0.getVideoRootView().findViewById(R.id.VideoPreviewWindow);
            }
        });
        this.seekBar = LazyKt__LazyJVMKt.lazy(new Function0<BauhausSeekBarWidget>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$seekBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BauhausSeekBarWidget invoke() {
                return (BauhausSeekBarWidget) this.this$0.getVideoRootView().findViewById(R.id.VideoPlayerCoverViewSeekBar);
            }
        });
        this.onPlaybackStarted = new VideoPlayerFragment$onPlaybackStarted$1(this);
        this.isTrickPlaySupported = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.isTrickPlaySupported.2
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final Boolean invoke() {
                return Boolean.valueOf(AmazonApplication.getCapabilities().isTrickPlaySupprted());
            }
        });
        this.metadataChangedListener = LazyKt__LazyJVMKt.lazy(new Function0<VideoPlayerFragment$metadataChangedListener$2.AnonymousClass1>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$metadataChangedListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.mp3.playback.fragment.VideoPlayerFragment$metadataChangedListener$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                final VideoPlayerFragment videoPlayerFragment = this.this$0;
                return new OnMetadataChangedListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$metadataChangedListener$2.1
                    @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                    public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
                    }

                    @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                    public void onMetadataChanged(MediaItem mediaItem) {
                        videoPlayerFragment.updateAll();
                    }
                };
            }
        });
        this.connectivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$connectivityReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                PrimeVideoPlayer primeVideoPlayer2;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                VideoUtil videoUtil = VideoUtil.INSTANCE;
                if (videoUtil.isConnectivityOtherThanWifi(context) && videoUtil.isVideoStreamingPreferenceWifi(context) && (primeVideoPlayer2 = this.this$0.videoPlayer) != null) {
                    primeVideoPlayer2.onConnectivityLost();
                }
            }
        };
        this.homeActivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$homeActivityReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                VideoUtil videoUtil = VideoUtil.INSTANCE;
                if (videoUtil.isInPictureInPictureMode(this.this$0.getActivity())) {
                    videoUtil.launchNowPlayingActivity(context);
                }
            }
        };
        this.dialogActivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$dialogActivityReceiver$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                LifecycleObserver lifecycleObserver;
                Intrinsics.checkNotNullParameter(context, "context");
                Intrinsics.checkNotNullParameter(intent, "intent");
                if (intent.getExtras() == null || intent.getIntExtra("com.amazon.mp3.activity.BROADCAST_BUTTON_RUNNABLE_ID", -1) != 10 || (lifecycleObserver = this.this$0.resumeObserver) == null) {
                    return;
                }
                this.this$0.getLifecycle().removeObserver(lifecycleObserver);
            }
        };
        this.menuInflater = LazyKt__LazyJVMKt.lazy(new Function0<MenuInflater>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$menuInflater$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final MenuInflater invoke() {
                return new MenuInflater(this.this$0.getActivity());
            }
        });
        this.popupMenu = LazyKt__LazyJVMKt.lazy(new Function0<PopupMenu>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$popupMenu$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final PopupMenu invoke() {
                PopupMenu popupMenu = new PopupMenu(this.this$0.requireContext(), this.this$0.getOverflowButton());
                VideoPlayerFragment videoPlayerFragment = this.this$0;
                videoPlayerFragment.getPlayerContextMenu().onCreateOptionMenu(popupMenu.getMenu(), videoPlayerFragment.getMenuInflater());
                return popupMenu;
            }
        });
        this.playStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda0
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                VideoPlayerFragment.m1450playStateListener$lambda2(this.f$0);
            }
        };
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RelativeLayout getVideoRootView() {
        return (RelativeLayout) this.videoRootView.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RelativeLayout getVideoSurfaceView() {
        Object value = this.videoSurfaceView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-videoSurfaceView>(...)");
        return (RelativeLayout) value;
    }

    private final ImageView getCaptionsButton() {
        Object value = this.captionsButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-captionsButton>(...)");
        return (ImageView) value;
    }

    private final ImageView getExpandContractButton() {
        Object value = this.expandContractButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-expandContractButton>(...)");
        return (ImageView) value;
    }

    private final ImageButton getVideoPlayerShareButton() {
        Object value = this.videoPlayerShareButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-videoPlayerShareButton>(...)");
        return (ImageButton) value;
    }

    private final FrameLayout getOverlayView() {
        Object value = this.overlayView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-overlayView>(...)");
        return (FrameLayout) value;
    }

    private final ImageView getCloseButton() {
        Object value = this.closeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-closeButton>(...)");
        return (ImageView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageView getOverflowButton() {
        Object value = this.overflowButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-overflowButton>(...)");
        return (ImageView) value;
    }

    private final PlayerMetadataView getPlayerMetadataView() {
        Object value = this.playerMetadataView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playerMetadataView>(...)");
        return (PlayerMetadataView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PlayerContextMenu getPlayerContextMenu() {
        return (PlayerContextMenu) this.playerContextMenu.getValue();
    }

    private final ViewGroup getControlsContainer() {
        Object value = this.controlsContainer.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-controlsContainer>(...)");
        return (ViewGroup) value;
    }

    private final BaseButton getAlexaButton() {
        Object value = this.alexaButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-alexaButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getShareButton() {
        Object value = this.shareButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-shareButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getPlayQueueButton() {
        Object value = this.playQueueButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playQueueButton>(...)");
        return (BaseButton) value;
    }

    private final View getBottomSpacing() {
        Object value = this.bottomSpacing.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-bottomSpacing>(...)");
        return (View) value;
    }

    private final FrameLayout getVideoPersistentPlayerContainer() {
        return (FrameLayout) this.videoPersistentPlayerContainer.getValue();
    }

    private final View getToolbarLayout() {
        return (View) this.toolbarLayout.getValue();
    }

    private final LinearLayout getAudioVideoToggleView() {
        Object value = this.audioVideoToggleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-audioVideoToggleView>(...)");
        return (LinearLayout) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoPreviewWindow getMVideoPreviewWindow() {
        return (VideoPreviewWindow) this.mVideoPreviewWindow.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final BauhausSeekBarWidget getSeekBar() {
        Object value = this.seekBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-seekBar>(...)");
        return (BauhausSeekBarWidget) value;
    }

    private final int getDeviceWidth() {
        Integer numValueOf;
        FragmentActivity activity = getActivity();
        if (activity == null) {
            numValueOf = null;
        } else {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            numValueOf = Integer.valueOf(displayMetrics.widthPixels);
        }
        if (numValueOf != null) {
            return numValueOf.intValue();
        }
        return ScreenUtil.getDeviceWidth();
    }

    public final AudioVideoToggleUseCase getAudioVideoToggleInteractor() {
        AudioVideoToggleUseCase audioVideoToggleUseCase = this.audioVideoToggleInteractor;
        if (audioVideoToggleUseCase != null) {
            return audioVideoToggleUseCase;
        }
        Intrinsics.throwUninitializedPropertyAccessException("audioVideoToggleInteractor");
        return null;
    }

    public final VideoPlayerStateProvider getVideoPlayerStateProvider() {
        VideoPlayerStateProvider videoPlayerStateProvider = this.videoPlayerStateProvider;
        if (videoPlayerStateProvider != null) {
            return videoPlayerStateProvider;
        }
        Intrinsics.throwUninitializedPropertyAccessException("videoPlayerStateProvider");
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTrickPlaySupported() {
        return ((Boolean) this.isTrickPlaySupported.getValue()).booleanValue();
    }

    private final OnMetadataChangedListener getMetadataChangedListener() {
        return (OnMetadataChangedListener) this.metadataChangedListener.getValue();
    }

    /* JADX INFO: compiled from: VideoPlayerFragment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/amazon/mp3/playback/fragment/VideoPlayerFragment$Companion;", "", "()V", "ANIMATION_DURATION", "", "SWIPE_MIN_DISTANCE", "", "TAG", "", "kotlin.jvm.PlatformType", "TAG_VIDEO_PLAYER_CONTRACT", "TAG_VIDEO_PLAYER_EXPAND", "USER_CONTROLS_VISIBILITY_TIMEOUT", "newInstance", "Lcom/amazon/mp3/playback/fragment/VideoPlayerFragment;", "extras", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final VideoPlayerFragment newInstance(Bundle extras) {
            VideoPlayerFragment videoPlayerFragment = new VideoPlayerFragment();
            videoPlayerFragment.setArguments(extras);
            return videoPlayerFragment;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MenuInflater getMenuInflater() {
        return (MenuInflater) this.menuInflater.getValue();
    }

    private final PopupMenu getPopupMenu() {
        return (PopupMenu) this.popupMenu.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playStateListener$lambda-2, reason: not valid java name */
    public static final void m1450playStateListener$lambda2(VideoPlayerFragment this$0) {
        VideoPreviewWindow mVideoPreviewWindow;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateSeekBar();
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        if (currentMediaItem != null && !Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            if (this$0.isTrickPlaySupported() && (mVideoPreviewWindow = this$0.getMVideoPreviewWindow()) != null) {
                mVideoPreviewWindow.resetVideoPreviewWindow();
            }
            this$0.updateHeader();
        }
        this$0.handleUserControlsVisibilityOnPlaybackStateChange();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        Log.debug(TAG, "onCreateView()");
        startPlayback();
        registerLocalBroadcastActivityReceiver(this.homeActivityReceiver, new IntentFilter("com.amazon.mp3.library.activity.nowplaying.handlePip"));
        registerLocalBroadcastActivityReceiver(this.dialogActivityReceiver, new IntentFilter("com.amazon.mp3.library.activity.nowplaying.dialogDismiss"));
        registerConnectivityReceiver();
        initViewStyling();
        setupTrickPlay();
        return getVideoRootView();
    }

    private final void setupTrickPlay() {
        if (isTrickPlaySupported()) {
            VideoPreviewWindow mVideoPreviewWindow = getMVideoPreviewWindow();
            if (mVideoPreviewWindow != null) {
                mVideoPreviewWindow.initialize(getVideoRootView());
            }
            VideoPreviewWindow mVideoPreviewWindow2 = getMVideoPreviewWindow();
            if (mVideoPreviewWindow2 != null) {
                mVideoPreviewWindow2.setVisibility(0);
            }
            this.mVideoPreviewSeekBarTracker = new VideoPreviewSeekBarTracker(getSeekBar());
        }
    }

    private final void initViewStyling() {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlayerFragment.m1448initViewStyling$lambda6(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x002f  */
    /* JADX INFO: renamed from: initViewStyling$lambda-6, reason: not valid java name */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m1448initViewStyling$lambda6(com.amazon.mp3.playback.fragment.VideoPlayerFragment r4, com.amazon.music.views.library.styles.StyleSheet r5) {
        /*
            Method dump skipped, instruction units count: 226
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.fragment.VideoPlayerFragment.m1448initViewStyling$lambda6(com.amazon.mp3.playback.fragment.VideoPlayerFragment, com.amazon.music.views.library.styles.StyleSheet):void");
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setupViews$default(this, null, 1, null);
        replacePersistentPlayer();
        initStyleSheet$default(this, null, 1, null);
        handleUserControlsVisibility();
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x007e  */
    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDestroy() {
        /*
            r5 = this;
            super.onDestroy()
            java.lang.String r0 = com.amazon.mp3.playback.fragment.VideoPlayerFragment.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onDestroy() NowPlayingUtil.isVideoPlaying(): "
            r1.append(r2)
            boolean r2 = com.amazon.mp3.playback.activity.NowPlayingUtil.isVideoPlaying()
            r1.append(r2)
            java.lang.String r2 = " videoPlayer.getContentState: "
            r1.append(r2)
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r2 = r5.videoPlayer
            r3 = 0
            if (r2 != 0) goto L22
            r2 = r3
            goto L26
        L22:
            com.amazon.video.sdk.player.ContentState r2 = r2.getContentState()
        L26:
            r1.append(r2)
            java.lang.String r2 = " videoPlayer.getPlaybackState: "
            r1.append(r2)
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r2 = r5.videoPlayer
            if (r2 != 0) goto L34
            r2 = r3
            goto L38
        L34:
            com.amazon.video.sdk.player.PlaybackState r2 = r2.getPlaybackState()
        L38:
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.amazon.mp3.util.Log.debug(r0, r1)
            com.amazon.mp3.view.BauhausSeekBarWidget r0 = r5.getSeekBar()
            r0.pauseSeekbarProgressUpdate()
            boolean r0 = com.amazon.mp3.playback.activity.NowPlayingUtil.isVideoPlaying()
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L7e
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r5.videoPlayer
            if (r0 != 0) goto L57
            r0 = r3
            goto L5b
        L57:
            com.amazon.video.sdk.player.ContentState r0 = r0.getContentState()
        L5b:
            com.amazon.video.sdk.player.ContentState r4 = com.amazon.video.sdk.player.ContentState.Ready
            if (r0 != r4) goto L6d
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r5.videoPlayer
            if (r0 != 0) goto L65
            r0 = r3
            goto L69
        L65:
            com.amazon.video.sdk.player.PlaybackState r0 = r0.getPlaybackState()
        L69:
            com.amazon.video.sdk.player.PlaybackState r4 = com.amazon.video.sdk.player.PlaybackState.Playing
            if (r0 == r4) goto L7e
        L6d:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r5.videoPlayer
            if (r0 != 0) goto L72
            goto L75
        L72:
            r0.pause(r3, r3)
        L75:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r5.videoPlayer
            if (r0 != 0) goto L7a
            goto L86
        L7a:
            r0.destroy(r1)
            goto L86
        L7e:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r5.videoPlayer
            if (r0 != 0) goto L83
            goto L86
        L83:
            r0.setPlayerVisibility(r2)
        L86:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r5.videoPlayer
            if (r0 != 0) goto L8b
            goto L90
        L8b:
            kotlin.jvm.functions.Function1<kotlin.Unit, kotlin.Unit> r4 = r5.onPlaybackStarted
            r0.removeOnPlaybackStartedListener(r4)
        L90:
            android.content.BroadcastReceiver r0 = r5.homeActivityReceiver
            r5.unregisterLocalBroadcastActivityReceiver(r0)
            android.content.BroadcastReceiver r0 = r5.dialogActivityReceiver
            r5.unregisterLocalBroadcastActivityReceiver(r0)
            r5.unregisterConnectivityReceiver()
            r5.setImmersiveMode(r2)
            androidx.lifecycle.LifecycleObserver r0 = r5.resumeObserver
            if (r0 != 0) goto La5
            goto Lac
        La5:
            androidx.lifecycle.Lifecycle r4 = r5.getLifecycle()
            r4.removeObserver(r0)
        Lac:
            rx.Subscription r0 = r5.viewAnimationSubscription
            if (r0 == 0) goto Lc6
            if (r0 != 0) goto Lb4
        Lb2:
            r1 = r2
            goto Lba
        Lb4:
            boolean r0 = r0.isUnsubscribed()
            if (r0 != 0) goto Lb2
        Lba:
            if (r1 == 0) goto Lc6
            rx.Subscription r0 = r5.viewAnimationSubscription
            if (r0 != 0) goto Lc1
            goto Lc4
        Lc1:
            r0.unsubscribe()
        Lc4:
            r5.viewAnimationSubscription = r3
        Lc6:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.fragment.VideoPlayerFragment.onDestroy():void");
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        Observable<LeavingActivityReason> observable;
        super.onResume();
        Subscription subscriptionSubscribe = null;
        initStyleSheet$default(this, null, 1, null);
        if (AmazonApplication.getCapabilities().isPipEnabled()) {
            Subscription subscription = this.leavingActivitySubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            FragmentActivity activity = getActivity();
            NowPlayingFragmentActivity nowPlayingFragmentActivity = activity instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) activity : null;
            if (nowPlayingFragmentActivity != null && (observable = nowPlayingFragmentActivity.mLeavingActivitySubject) != null) {
                subscriptionSubscribe = observable.subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda3
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        VideoPlayerFragment.m1449onResume$lambda9(this.f$0, (LeavingActivityReason) obj);
                    }
                });
            }
            this.leavingActivitySubscription = subscriptionSubscribe;
        }
        this.playbackController.addOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.addOnMetadataChangedListener(getMetadataChangedListener());
        if (ScreenUtil.isLandscape()) {
            addToggle();
            setImmersiveMode(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onResume$lambda-9, reason: not valid java name */
    public static final void m1449onResume$lambda9(VideoPlayerFragment this$0, LeavingActivityReason leavingActivityReason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (leavingActivityReason == LeavingActivityReason.USER_LEAVE_HINT) {
            if (!ActivityKt.isAvailable(this$0.getActivity()) || this$0.playbackController.getPlayStatus() == PlayStatus.USER_PAUSED) {
                return;
            }
            this$0.toggleActionBar(false);
            this$0.enterPIPMode();
            this$0.setCaptionsEnabled(false);
            return;
        }
        PrimeVideoPlayer primeVideoPlayer = this$0.videoPlayer;
        if (primeVideoPlayer != null) {
            primeVideoPlayer.setPlayerVisibility(false);
        }
        Context context = this$0.getContext();
        if (context == null) {
            return;
        }
        this$0.navigateToLauncherTask(context);
    }

    @SuppressLint({"NewApi"})
    private final void enterPIPMode() {
        View videoSurfaceView = (SurfaceView) SequencesKt___SequencesKt.firstOrNull(SequencesKt___SequencesKt.filter(ViewGroupKt.getChildren(getVideoSurfaceView()), new Function1<Object, Boolean>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$enterPIPMode$$inlined$filterIsInstance$1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function1
            public final Boolean invoke(Object obj) {
                return Boolean.valueOf(obj instanceof SurfaceView);
            }
        }));
        if (videoSurfaceView == null) {
            videoSurfaceView = getVideoSurfaceView();
        }
        PictureInPictureParams pictureInPictureParamsBuild = new PictureInPictureParams$Builder().setSourceRectHint(ViewExtensions.getAbsoluteRect(videoSurfaceView)).build();
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        activity.enterPictureInPictureMode(pictureInPictureParamsBuild);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        setupCaptions();
        if (isInPictureInPictureMode) {
            hideUserControls(true);
            adjustLandscapeViews(null);
        }
    }

    private final void navigateToLauncherTask(@NonNull Context appContext) {
        ActivityManager.AppTask next;
        Intent intent;
        Set<String> categories;
        Object systemService = appContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) systemService).getAppTasks();
        Intrinsics.checkNotNullExpressionValue(appTasks, "activityManager.appTasks");
        Iterator<ActivityManager.AppTask> it = appTasks.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            ActivityManager.RecentTaskInfo taskInfo = next.getTaskInfo();
            if (taskInfo != null && (intent = taskInfo.baseIntent) != null && (categories = intent.getCategories()) != null && categories.contains("android.intent.category.LAUNCHER")) {
                next.moveToFront();
                return;
            }
        }
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.shouldResumePlayback = !isPlayerPaused();
        if (!VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
            if (primeVideoPlayer != null) {
                primeVideoPlayer.setPlayerVisibility(false);
            }
            this.resumeObserver = FragmentKt.doOnResume(this, true, new Function0<Unit>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.onPause.1
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
                    VideoPlayerFragment.this.initPlayerAndResumePlaybackIfNeeded();
                }
            });
        }
        this.playbackController.removeOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.removeOnMetadataChangedListener(getMetadataChangedListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initPlayerAndResumePlaybackIfNeeded() {
        initPlayer();
        if (this.shouldResumePlayback) {
            this.shouldResumePlayback = false;
        }
    }

    private final boolean isPlayerPaused() {
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if ((primeVideoPlayer == null ? null : primeVideoPlayer.getPlayStatus()) != PlayStatus.USER_PAUSED) {
            PrimeVideoPlayer primeVideoPlayer2 = this.videoPlayer;
            if ((primeVideoPlayer2 != null ? primeVideoPlayer2.getPlayStatus() : null) != PlayStatus.SYSTEM_PAUSED) {
                return false;
            }
        }
        return true;
    }

    @Override // androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        updateAll();
        OrientationEventListener orientationEventListener = ScreenUtil.getOrientationEventListener(getActivity());
        Intrinsics.checkNotNullExpressionValue(orientationEventListener, "getOrientationEventListener(activity)");
        this.orientationEventListener = orientationEventListener;
        if (orientationEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
            orientationEventListener = null;
        }
        orientationEventListener.enable();
        this.isFragmentVisible = true;
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer == null) {
            return;
        }
        primeVideoPlayer.setPlayerVisibility(true);
    }

    @Override // androidx.fragment.app.Fragment
    public void onStop() {
        super.onStop();
        getSeekBar().pauseSeekbarProgressUpdate();
        OrientationEventListener orientationEventListener = this.orientationEventListener;
        if (orientationEventListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException("orientationEventListener");
            orientationEventListener = null;
        }
        ScreenUtil.removeOrientationEventListener(orientationEventListener);
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer != null) {
            primeVideoPlayer.setPlayerVisibility(false);
        }
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            this.resumeObserver = FragmentKt.doOnResume(this, true, new Function0<Unit>() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.onStop.1
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
                    VideoPlayerFragment.this.initPlayerAndResumePlaybackIfNeeded();
                }
            });
        }
        this.isFragmentVisible = false;
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        setupViews(newConfig);
        replacePersistentPlayer();
        handleUserControlsVisibility();
        initStyleSheet(newConfig);
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            hideUserControls(true);
        }
    }

    public final void sendUiPageViewMetrics(boolean isVideoOn, String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(PageType.NOW_PLAYING.getMetricsValue()).withPageSubType(PageSubType.ONDEMAND_VIDEO.getMetricsValue()).withDetailPageItemId(asin).withDetailPageItemIdType(EntityIdType.ASIN.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build());
    }

    private final void replacePersistentPlayer() {
        PersistentPlayerFragment persistentPlayerFragment = new PersistentPlayerFragment();
        this.persistentPlayerFragment = persistentPlayerFragment;
        persistentPlayerFragment.registerForPlaybackError(false);
        PersistentPlayerFragment persistentPlayerFragment2 = this.persistentPlayerFragment;
        if (persistentPlayerFragment2 == null) {
            return;
        }
        FragmentTransaction fragmentTransactionBeginTransaction = getChildFragmentManager().beginTransaction();
        Intrinsics.checkNotNullExpressionValue(fragmentTransactionBeginTransaction, "childFragmentManager.beginTransaction()");
        persistentPlayerFragment2.setPersistentPlayerType(PersistentPlayer.PlayerType.VIDEO);
        fragmentTransactionBeginTransaction.replace(R.id.VideoPersistentPlayerContainer, persistentPlayerFragment2, PersistentPlayerFragment.getFragmentTag());
        fragmentTransactionBeginTransaction.commitAllowingStateLoss();
        getChildFragmentManager().executePendingTransactions();
    }

    static /* synthetic */ void setupViews$default(VideoPlayerFragment videoPlayerFragment, Configuration configuration, int i, Object obj) {
        if ((i & 1) != 0) {
            configuration = null;
        }
        videoPlayerFragment.setupViews(configuration);
    }

    @SuppressLint({"NewApi", "ClickableViewAccessibility"})
    private final void setupViews(Configuration newConfig) {
        PrimeVideoPlayer primeVideoPlayer;
        Integer numValueOf = newConfig == null ? null : Integer.valueOf(newConfig.orientation);
        final int iIntValue = numValueOf == null ? getVideoRootView().getResources().getConfiguration().orientation : numValueOf.intValue();
        if (iIntValue == 2) {
            getExpandContractButton().setImageResource(R.drawable.ic_other_contract);
        } else {
            getExpandContractButton().setImageResource(R.drawable.ic_other_expand);
        }
        hideUserControls(iIntValue == 2);
        updateVideoPlayerLayout(iIntValue);
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            setupPipView();
        } else {
            toggleActionBar(iIntValue == 1);
            setImmersiveMode(iIntValue == 2);
        }
        if (getVideoPlayerStateProvider().getIsCaptionsEnabled()) {
            getCaptionsButton().setImageResource(R.drawable.ic_other_closedcaptionsus_active);
        } else {
            getCaptionsButton().setImageResource(R.drawable.ic_other_closedcaptionsus_inactive);
        }
        getCaptionsButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1451setupViews$lambda13(this.f$0, view);
            }
        });
        getExpandContractButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1452setupViews$lambda15(iIntValue, this, view);
            }
        });
        getVideoPlayerShareButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1453setupViews$lambda16(this.f$0, view);
            }
        });
        getSeekBar().updateProgressBar();
        getPopupMenu().setOnDismissListener(new PopupMenu.OnDismissListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda9
            @Override // android.widget.PopupMenu.OnDismissListener
            public final void onDismiss(PopupMenu popupMenu) {
                VideoPlayerFragment.m1454setupViews$lambda17(this.f$0, iIntValue, popupMenu);
            }
        });
        getVideoRootView().setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda10
            @Override // android.view.View.OnCreateContextMenuListener
            public final void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                VideoPlayerFragment.m1455setupViews$lambda18(this.f$0, contextMenu, view, contextMenuInfo);
            }
        });
        getOverflowButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1456setupViews$lambda20(this.f$0, view);
            }
        });
        getCloseButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1458setupViews$lambda21(this.f$0, view);
            }
        });
        getPlayQueueButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1459setupViews$lambda23(this.f$0, view);
            }
        });
        getShareButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda14
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1460setupViews$lambda24(this.f$0, view);
            }
        });
        getAlexaButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda15
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoPlayerFragment.m1461setupViews$lambda27(this.f$0, view);
            }
        });
        getAlexaButton().setVisibility(AmazonApplication.getCapabilities().isAlexaEnabled() ? 0 : 8);
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        getVideoRootView().setOnTouchListener(touchEventHandler.getTouchEventListener());
        touchEventHandler.setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.setupViews.11
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
                if (((int) x) >= 120) {
                    VideoPlayerFragment.this.skipVideo(false);
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
                if (((int) x) >= 120) {
                    VideoPlayerFragment.this.skipVideo(true);
                }
            }
        });
        Context context = getContext();
        if (context != null) {
            touchEventHandler.setOnClickListener(context, new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda6
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    VideoPlayerFragment.m1462setupViews$lambda29$lambda28(this.f$0, view);
                }
            });
        }
        if ((newConfig != null && newConfig.orientation == 2) || ScreenUtil.isLandscape()) {
            getShareButton().setVisibility(4);
        }
        getSeekBar().setTrackTouchListener(new BauhausSeekBarWidget.TrackTouchListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.setupViews.13
            @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
            public void onStartTrackingTouch() {
                VideoPlayerFragment.this.showControlsAndOptionallyFadeOut(false);
            }

            @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                if (VideoPlayerFragment.this.isTrickPlaySupported()) {
                    long progress2 = (((long) seekBar.getProgress()) * VideoPlayerFragment.this.playbackController.getDurationMillis()) / ((long) seekBar.getMax());
                    PrimeVideoPlayer primeVideoPlayer2 = VideoPlayerFragment.this.videoPlayer;
                    if (primeVideoPlayer2 != null) {
                        primeVideoPlayer2.trackPlayAssetForPosition(progress2);
                    }
                    VideoPlayerFragment.this.updateVideoPreviewWindow(progress2);
                }
            }

            @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
            public void onStopTrackingTouch() {
                VideoPreviewWindow mVideoPreviewWindow = VideoPlayerFragment.this.getMVideoPreviewWindow();
                if (mVideoPreviewWindow != null) {
                    mVideoPreviewWindow.hide();
                }
                if (VideoPlayerFragment.this.playbackController.getPlayStatus() == PlayStatus.RENDERING) {
                    VideoPlayerFragment.this.showControlsAndOptionallyFadeOut(true);
                }
            }
        });
        if (!isTrickPlaySupported() || (primeVideoPlayer = this.videoPlayer) == null) {
            return;
        }
        primeVideoPlayer.setTrickPlayListener(new PrimeVideoPlayer.TrickPlayListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.setupViews.14
            @Override // com.amazon.music.media.playback.player.impl.PrimeVideoPlayer.TrickPlayListener
            public void didReceiveTrickPlayImage(Drawable image) {
                Intrinsics.checkNotNullParameter(image, "image");
                VideoPreviewWindow mVideoPreviewWindow = VideoPlayerFragment.this.getMVideoPreviewWindow();
                if (mVideoPreviewWindow == null) {
                    return;
                }
                mVideoPreviewWindow.updateImage(image);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-13, reason: not valid java name */
    public static final void m1451setupViews$lambda13(VideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleCaptionsButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-15, reason: not valid java name */
    public static final void m1452setupViews$lambda15(int i, VideoPlayerFragment this$0, View view) {
        ActionType actionType;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String nowPlayingMediaItemAsin = MediaPlaybackUtil.getNowPlayingMediaItemAsin();
        if (i == 2) {
            FragmentActivity activity = this$0.getActivity();
            if (activity != null) {
                activity.setRequestedOrientation(1);
            }
            this$0.getExpandContractButton().setTag("VideoPlayerExpand");
            actionType = ActionType.SELECT_PORTRAIT_MODE;
        } else {
            FragmentActivity activity2 = this$0.getActivity();
            if (activity2 != null) {
                activity2.setRequestedOrientation(0);
            }
            this$0.getExpandContractButton().setTag("VideoPlayerContract");
            actionType = ActionType.SELECT_LANDSCAPE_MODE;
        }
        ActionType actionType2 = actionType;
        if (nowPlayingMediaItemAsin == null) {
            return;
        }
        VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, nowPlayingMediaItemAsin, actionType2, null, PageType.NOW_PLAYING, null, PageSubType.ONDEMAND_VIDEO, 20, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-16, reason: not valid java name */
    public static final void m1453setupViews$lambda16(VideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItemHelper.shareMediaItem(this$0.getContext(), this$0.mediaItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-17, reason: not valid java name */
    public static final void m1454setupViews$lambda17(VideoPlayerFragment this$0, int i, PopupMenu popupMenu) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.setImmersiveMode(i == 2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-18, reason: not valid java name */
    public static final void m1455setupViews$lambda18(VideoPlayerFragment this$0, ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPlayerContextMenu().onCreateOptionMenu(contextMenu, this$0.getMenuInflater());
        this$0.getPlayerContextMenu().onUpdateOptionMenu(contextMenu);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-20, reason: not valid java name */
    public static final void m1456setupViews$lambda20(final VideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getPlayerContextMenu().onUpdateOptionMenu(this$0.getPopupMenu().getMenu());
        this$0.getPopupMenu().setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda16
            @Override // android.widget.PopupMenu.OnMenuItemClickListener
            public final boolean onMenuItemClick(MenuItem menuItem) {
                return VideoPlayerFragment.m1457setupViews$lambda20$lambda19(this.f$0, menuItem);
            }
        });
        this$0.getPopupMenu().show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-20$lambda-19, reason: not valid java name */
    public static final boolean m1457setupViews$lambda20$lambda19(VideoPlayerFragment this$0, MenuItem menuItem) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        return this$0.getPlayerContextMenu().onOptionsItemSelected(menuItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-21, reason: not valid java name */
    public static final void m1458setupViews$lambda21(VideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-23, reason: not valid java name */
    public static final void m1459setupViews$lambda23(VideoPlayerFragment this$0, View view) {
        Context context;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context context2 = this$0.getContext();
        if (context2 != null) {
            this$0.navigateToLauncherTask(context2);
        }
        Intent nowPlayingQueueIntent = NowPlayingQueueUtil.getNowPlayingQueueIntent(this$0.getContext());
        if (nowPlayingQueueIntent == null || (context = this$0.getContext()) == null) {
            return;
        }
        context.startActivity(nowPlayingQueueIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-24, reason: not valid java name */
    public static final void m1460setupViews$lambda24(VideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MediaItemHelper.shareMediaItem(this$0.getContext(), this$0.mediaItem);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-27, reason: not valid java name */
    public static final void m1461setupViews$lambda27(VideoPlayerFragment this$0, View view) {
        NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.voiceAccessProvider == null) {
            FragmentActivity activity = this$0.getActivity();
            if (activity == null) {
                nowPlayingVoiceAccessProvider = null;
            } else {
                NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider2 = new NowPlayingVoiceAccessProvider(activity);
                nowPlayingVoiceAccessProvider2.initAlexa();
                nowPlayingVoiceAccessProvider = nowPlayingVoiceAccessProvider2;
            }
            this$0.voiceAccessProvider = nowPlayingVoiceAccessProvider;
        }
        NowPlayingVoiceAccessProvider nowPlayingVoiceAccessProvider3 = this$0.voiceAccessProvider;
        if (nowPlayingVoiceAccessProvider3 == null || nowPlayingVoiceAccessProvider3 == null) {
            return;
        }
        nowPlayingVoiceAccessProvider3.onAlexaClicked(AlexaEntryPoint.NOW_PLAYING_VIDEO);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-29$lambda-28, reason: not valid java name */
    public static final void m1462setupViews$lambda29$lambda28(VideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.currentControllerVisibility == 0) {
            this$0.toggleUserControls(4);
        } else {
            this$0.showControlsAndOptionallyFadeOut(true);
        }
    }

    public final void updateVideoPreviewWindow(long currentTimeMs) {
        VideoPreviewWindow mVideoPreviewWindow;
        VideoPreviewSeekBarTracker videoPreviewSeekBarTracker = this.mVideoPreviewSeekBarTracker;
        Point thumbScreenCoords = videoPreviewSeekBarTracker == null ? null : videoPreviewSeekBarTracker.getThumbScreenCoords();
        if (thumbScreenCoords != null && (mVideoPreviewWindow = getMVideoPreviewWindow()) != null) {
            mVideoPreviewWindow.move(thumbScreenCoords.x, thumbScreenCoords.y);
        }
        String str = DurationUtils.format(currentTimeMs, DurationUtils.getSuggestedFormat(currentTimeMs), new StringBuilder(), true);
        VideoPreviewWindow mVideoPreviewWindow2 = getMVideoPreviewWindow();
        if (mVideoPreviewWindow2 != null) {
            mVideoPreviewWindow2.updateText(str);
        }
        VideoPreviewWindow mVideoPreviewWindow3 = getMVideoPreviewWindow();
        if (mVideoPreviewWindow3 == null) {
            return;
        }
        mVideoPreviewWindow3.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showControlsAndOptionallyFadeOut(boolean autoFadeOut) {
        Subscription subscription = this.viewAnimationSubscription;
        if (subscription != null) {
            if ((subscription == null || subscription.isUnsubscribed()) ? false : true) {
                Subscription subscription2 = this.viewAnimationSubscription;
                if (subscription2 != null) {
                    subscription2.unsubscribe();
                }
                this.viewAnimationSubscription = null;
            }
        }
        toggleUserControls(0);
        if (autoFadeOut && this.playbackController.getPlayStatus().willPlay()) {
            this.viewAnimationSubscription = Observable.timer(4L, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda4
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    VideoPlayerFragment.m1463showControlsAndOptionallyFadeOut$lambda31(this.f$0, (Long) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: showControlsAndOptionallyFadeOut$lambda-31, reason: not valid java name */
    public static final void m1463showControlsAndOptionallyFadeOut$lambda31(VideoPlayerFragment this$0, Long l) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleUserControls(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void skipVideo(boolean playNext) {
        if (playNext) {
            if (this.playbackController.canSkipNext()) {
                this.playbackController.skipNext();
            }
        } else if (this.playbackController.canSkipPrevious()) {
            this.playbackController.previousOrRestart();
        }
    }

    private final void setupPipView() {
        getAudioVideoToggleInteractor().hideToggle();
        getControlsContainer().setVisibility(8);
        getExpandContractButton().setVisibility(8);
        getCaptionsButton().setVisibility(8);
        PersistentPlayerFragment persistentPlayerFragment = this.persistentPlayerFragment;
        View view = persistentPlayerFragment == null ? null : persistentPlayerFragment.getView();
        if (view != null) {
            view.setVisibility(8);
        }
        getVideoPlayerShareButton().setVisibility(8);
    }

    private final void updateVideoPlayerLayout(int orientation) {
        RelativeLayout videoRootView = getVideoRootView();
        ViewGroup.LayoutParams layoutParams = getVideoRootView().getLayoutParams();
        if (layoutParams == null) {
            layoutParams = null;
        } else {
            layoutParams.width = orientation == 1 ? getDeviceWidth() : -1;
            layoutParams.height = -1;
        }
        videoRootView.setLayoutParams(layoutParams);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupCaptions() {
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        boolean z = primeVideoPlayer != null && primeVideoPlayer.hasCaptions();
        getCaptionsButton().setEnabled(z);
        if (z) {
            if (this.currentControllerVisibility == 0) {
                ViewExtensions.fadeAndToggleViewVisibility(getCaptionsButton(), 0, 400L);
            } else {
                ViewExtensions.fadeAndToggleViewVisibility(getCaptionsButton(), 4, 400L);
            }
            PrimeVideoPlayer primeVideoPlayer2 = this.videoPlayer;
            if (primeVideoPlayer2 == null) {
                return;
            }
            primeVideoPlayer2.showCaptions(getVideoPlayerStateProvider().getIsCaptionsEnabled() && !VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity()));
            return;
        }
        getCaptionsButton().setVisibility(4);
    }

    @Override // androidx.fragment.app.Fragment
    public boolean onContextItemSelected(MenuItem item) {
        Intrinsics.checkNotNullParameter(item, "item");
        super.onContextItemSelected(item);
        return getPlayerContextMenu().onOptionsItemSelected(item);
    }

    private final void setImmersiveMode(boolean enabled) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        ActivityKt.setImmersiveMode(activity, enabled);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAll() {
        updateSeekBar();
        updateHeader();
    }

    private final void updateSeekBar() {
        getSeekBar().updateProgressBar();
        getSeekBar().updateSeekBar();
    }

    private final void updateHeader() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        this.mediaItem = currentMediaItem;
        if (currentMediaItem != null) {
            PlayerMetadataView playerMetadataView = getPlayerMetadataView();
            MediaItem mediaItem = this.mediaItem;
            String name = mediaItem == null ? null : mediaItem.getName();
            MediaItem mediaItem2 = this.mediaItem;
            playerMetadataView.setText(name, mediaItem2 == null ? null : mediaItem2.getArtistName(), null);
            getPlayerMetadataView().displayLoadingView(false);
        }
    }

    private final void startPlayback() {
        initPlayer();
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer == null) {
            return;
        }
        primeVideoPlayer.addOnPlaybackStartedListener(this.onPlaybackStarted);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initPlayer() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        PlayerConfigData playerConfigData = new PlayerConfigData(contextRequireContext, getFeatureConfigs(), new AdditionalPlayerConfigs(true));
        RenderingConfig renderingConfigCreateRenderingConfig = PlayerUtil.INSTANCE.createRenderingConfig(SurfaceHandlingMode.CREATE, getVideoSurfaceView(), null, getVideoRootView().getSystemUiVisibility(), true);
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer == null) {
            return;
        }
        primeVideoPlayer.setPlayerConfig(playerConfigData, renderingConfigCreateRenderingConfig);
    }

    private final FeatureConfigs getFeatureConfigs() {
        return new FeatureConfigs() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment.getFeatureConfigs.1
            @Override // com.amazon.video.sdk.player.FeatureConfigs
            public PlaylistFeatureConfig getPlaylist() {
                return null;
            }

            @Override // com.amazon.video.sdk.player.FeatureConfigs
            public StreamFeatureConfig getStream() {
                return null;
            }

            @Override // com.amazon.video.sdk.player.FeatureConfigs
            public VideoPreviewAssetFeatureConfig getVideoPreviewAsset() {
                return new VideoPreviewAssetFeatureConfig() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$getFeatureConfigs$1$videoPreviewAsset$1
                    @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureConfig
                    public boolean getPreloadAssets() {
                        return true;
                    }
                };
            }
        };
    }

    private final void hideUserControls(boolean isLandscape) {
        this.currentControllerVisibility = 4;
        getOverlayView().setVisibility(4);
        getCloseButton().setVisibility(4);
        getOverflowButton().setVisibility(4);
        getPlayerMetadataView().setVisibility(4);
        getCaptionsButton().setVisibility(4);
        getExpandContractButton().setVisibility(4);
        FrameLayout videoPersistentPlayerContainer = getVideoPersistentPlayerContainer();
        if (videoPersistentPlayerContainer != null) {
            videoPersistentPlayerContainer.setVisibility(4);
        }
        getControlsContainer().setVisibility(4);
        getVideoPlayerShareButton().setVisibility(4);
        getAudioVideoToggleView().setVisibility(4);
        if (isLandscape || VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            getSeekBar().setVisibility(4);
        }
    }

    private final void toggleUserControls(int visibility) {
        this.hasShownControlsOnce = true;
        this.currentControllerVisibility = visibility;
        if (ScreenUtil.isLandscape()) {
            ViewExtensions.fadeAndToggleViewVisibility(getCloseButton(), visibility, 400L);
            ViewExtensions.fadeAndToggleViewVisibility(getOverflowButton(), visibility, 400L);
            ViewExtensions.fadeAndToggleViewVisibility(getPlayerMetadataView(), visibility, 400L);
            FrameLayout videoPersistentPlayerContainer = getVideoPersistentPlayerContainer();
            if (videoPersistentPlayerContainer != null) {
                ViewExtensions.fadeAndToggleViewVisibility(videoPersistentPlayerContainer, visibility, 400L);
            }
            ViewExtensions.fadeAndToggleViewVisibility(getControlsContainer(), visibility, 400L);
            ViewExtensions.fadeAndToggleViewVisibility(getAudioVideoToggleView(), visibility, 400L);
            ViewExtensions.fadeAndToggleViewVisibility(getSeekBar(), visibility, 400L);
            ViewExtensions.fadeAndToggleViewVisibility(getPlayQueueButton(), visibility, 400L);
            ViewExtensions.fadeAndToggleViewVisibility(getVideoPlayerShareButton(), visibility, 400L);
        }
        ImageView captionsButton = getCaptionsButton();
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        captionsButton.setEnabled(primeVideoPlayer != null && primeVideoPlayer.hasCaptions());
        ViewExtensions.fadeAndToggleViewVisibility(getCaptionsButton(), getCaptionsButton().isEnabled() ? visibility : 4, 400L);
        ViewExtensions.fadeAndToggleViewVisibility(getExpandContractButton(), visibility, 400L);
        ViewExtensions.fadeAndToggleViewVisibility(getOverlayView(), visibility, 400L);
    }

    private final void toggleActionBar(boolean visible) {
        View toolbarLayout = getToolbarLayout();
        if (toolbarLayout == null) {
            return;
        }
        toolbarLayout.setVisibility(visible ? 0 : 8);
    }

    private final void toggleCaptionsButton() {
        String nowPlayingMediaItemAsin = MediaPlaybackUtil.getNowPlayingMediaItemAsin();
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        boolean z = primeVideoPlayer != null && primeVideoPlayer.isShowingCaptions();
        ActionType actionType = z ? ActionType.VIDEO_TOGGLE_CAPTION_OFF : ActionType.VIDEO_TOGGLE_CAPTION_ON;
        if (nowPlayingMediaItemAsin != null) {
            VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, nowPlayingMediaItemAsin, actionType, null, PageType.NOW_PLAYING, null, null, 52, null);
        }
        getVideoPlayerStateProvider().setCaptionsEnabled(!z);
        setCaptionsEnabled(!z);
    }

    private final void setCaptionsEnabled(boolean enabled) {
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer != null) {
            primeVideoPlayer.showCaptions(enabled);
        }
        getCaptionsButton().setImageResource(enabled ? R.drawable.ic_other_closedcaptionsus_active : R.drawable.ic_other_closedcaptionsus_inactive);
        Log.info(TAG, Intrinsics.stringPlus("CC ", enabled ? "Enabled" : "Disabled"));
    }

    private final void registerConnectivityReceiver() {
        registerReceiver(this.connectivityReceiver, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    private final void unregisterConnectivityReceiver() {
        unregisterReceiver(this.connectivityReceiver);
    }

    private final void registerLocalBroadcastActivityReceiver(BroadcastReceiver receiver, IntentFilter intentFilter) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        LocalBroadcastManager.getInstance(activity).registerReceiver(receiver, intentFilter);
    }

    private final void unregisterLocalBroadcastActivityReceiver(BroadcastReceiver receiver) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        LocalBroadcastManager.getInstance(activity).unregisterReceiver(receiver);
    }

    public final void addToggle() {
        View toggleView = getAudioVideoToggleInteractor().getToggleView();
        ViewExtensions.removeFromParent(toggleView);
        getAudioVideoToggleView().addView(toggleView);
    }

    private final void handleUserControlsVisibility() {
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            hideUserControls(true);
        } else {
            if (!ScreenUtil.isLandscape() || this.playbackController.getPlayStatus() == PlayStatus.RENDERING) {
                return;
            }
            showControlsAndOptionallyFadeOut(false);
        }
    }

    private final void handleUserControlsVisibilityOnPlaybackStateChange() {
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            hideUserControls(true);
        } else if (ScreenUtil.isLandscape()) {
            showControlsAndOptionallyFadeOut(this.playbackController.getPlayStatus() == PlayStatus.RENDERING);
        }
    }

    private final void adjustLandscapeViews(GridSize gridSize) {
        Resources resources;
        Resources resources2;
        if (getContext() == null) {
            return;
        }
        RelativeLayout videoSurfaceView = getVideoSurfaceView();
        ViewGroup.LayoutParams layoutParams = getVideoSurfaceView().getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = null;
        RelativeLayout.LayoutParams layoutParams3 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
        if (layoutParams3 == null) {
            layoutParams3 = null;
        } else {
            layoutParams3.height = -1;
            layoutParams3.width = -1;
            layoutParams3.setMargins(0, 0, 0, 0);
        }
        videoSurfaceView.setLayoutParams(layoutParams3);
        BauhausSeekBarWidget seekBar = getSeekBar();
        ViewGroup.LayoutParams layoutParams4 = getSeekBar().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams5 = layoutParams4 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams4 : null;
        if (layoutParams5 == null) {
            layoutParams5 = null;
        } else {
            layoutParams5.addRule(2, R.id.controlsContainer);
            layoutParams5.addRule(5, getCaptionsButton().getId());
            layoutParams5.addRule(7, getControlsContainer().getId());
            layoutParams5.addRule(14);
            layoutParams5.removeRule(12);
            layoutParams5.setMargins(0, 0, 0, 0);
        }
        seekBar.setLayoutParams(layoutParams5);
        ImageView captionsButton = getCaptionsButton();
        ViewGroup.LayoutParams layoutParams6 = getCaptionsButton().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams7 = layoutParams6 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams6 : null;
        if (layoutParams7 == null) {
            layoutParams7 = null;
        } else {
            layoutParams7.removeRule(6);
            layoutParams7.removeRule(8);
            layoutParams7.addRule(2, R.id.bottom_spacing);
            Context context = getContext();
            layoutParams7.setMargins((gridSize == null ? 0 : gridSize.getMarginSpace()) + (gridSize == null ? 0 : gridSize.getColumnSpace()), 0, layoutParams7.rightMargin, (context == null || (resources = context.getResources()) == null) ? 0 : (int) resources.getDimension(R.dimen.spacer_4));
        }
        captionsButton.setLayoutParams(layoutParams7);
        ImageButton videoPlayerShareButton = getVideoPlayerShareButton();
        ViewGroup.LayoutParams layoutParams8 = getVideoPlayerShareButton().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams9 = layoutParams8 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams8 : null;
        if (layoutParams9 == null) {
            layoutParams9 = null;
        } else {
            layoutParams9.addRule(0, R.id.VideoHeaderOverflow);
            layoutParams9.addRule(6, R.id.VideoHeaderOverflow);
            layoutParams9.setMargins(0, 0, 0, 0);
        }
        videoPlayerShareButton.setLayoutParams(layoutParams9);
        ImageView expandContractButton = getExpandContractButton();
        ViewGroup.LayoutParams layoutParams10 = getExpandContractButton().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams11 = layoutParams10 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams10 : null;
        if (layoutParams11 == null) {
            layoutParams11 = null;
        } else {
            layoutParams11.addRule(0, R.id.VideoPlayerShareButton);
            layoutParams11.addRule(6, R.id.VideoHeaderOverflow);
            layoutParams11.removeRule(6);
            layoutParams11.removeRule(11);
            layoutParams11.setMargins(0, 0, 0, 0);
        }
        expandContractButton.setLayoutParams(layoutParams11);
        FrameLayout videoPersistentPlayerContainer = getVideoPersistentPlayerContainer();
        if (videoPersistentPlayerContainer == null) {
            return;
        }
        FrameLayout videoPersistentPlayerContainer2 = getVideoPersistentPlayerContainer();
        ViewGroup.LayoutParams layoutParams12 = videoPersistentPlayerContainer2 == null ? null : videoPersistentPlayerContainer2.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams13 = layoutParams12 instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams12 : null;
        if (layoutParams13 != null) {
            Context context2 = getContext();
            layoutParams13.setMargins((context2 == null || (resources2 = context2.getResources()) == null) ? 0 : (int) resources2.getDimension(R.dimen.spacer_8), 0, 0, 0);
            layoutParams2 = layoutParams13;
        }
        videoPersistentPlayerContainer.setLayoutParams(layoutParams2);
    }

    private final void adjustPortraitViews() {
        Context context = getContext();
        if (context == null) {
            return;
        }
        final float deviceWidth = (getDeviceWidth() * 1) / context.getResources().getFraction(R.fraction.aspect_ratio_16_9, 1, 1);
        getSeekBar().setVisibility(0);
        getSeekBar().setAlpha(1.0f);
        BauhausSeekBarWidget seekBar = getSeekBar();
        ViewGroup.LayoutParams layoutParams = getSeekBar().getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams2 = null;
        RelativeLayout.LayoutParams layoutParams3 = layoutParams instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams : null;
        if (layoutParams3 == null) {
            layoutParams3 = null;
        } else {
            layoutParams3.addRule(12, -1);
            layoutParams3.removeRule(2);
            layoutParams3.removeRule(5);
            layoutParams3.removeRule(7);
            layoutParams3.setMargins(layoutParams3.leftMargin, 0, layoutParams3.rightMargin, 0);
        }
        seekBar.setLayoutParams(layoutParams3);
        getSeekBar().getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$adjustPortraitViews$1$2
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                this.this$0.getSeekBar().getViewTreeObserver().removeOnGlobalLayoutListener(this);
                RelativeLayout videoSurfaceView = this.this$0.getVideoSurfaceView();
                ViewGroup.LayoutParams layoutParams4 = this.this$0.getVideoSurfaceView().getLayoutParams();
                RelativeLayout.LayoutParams layoutParams5 = null;
                RelativeLayout.LayoutParams layoutParams6 = layoutParams4 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams4 : null;
                if (layoutParams6 != null) {
                    float f = deviceWidth;
                    VideoPlayerFragment videoPlayerFragment = this.this$0;
                    layoutParams6.height = (int) f;
                    layoutParams6.addRule(12, -1);
                    layoutParams6.setMargins(0, 0, 0, videoPlayerFragment.getSeekBar().getHeight() / 2);
                    layoutParams5 = layoutParams6;
                }
                videoSurfaceView.setLayoutParams(layoutParams5);
            }
        });
        int dimension = (int) context.getResources().getDimension(R.dimen.video_player_base_margin);
        ImageView captionsButton = getCaptionsButton();
        ViewGroup.LayoutParams layoutParams4 = getCaptionsButton().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams5 = layoutParams4 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams4 : null;
        if (layoutParams5 == null) {
            layoutParams5 = null;
        } else {
            layoutParams5.addRule(6, R.id.VideoPlaybackSurfaceLayout);
            layoutParams5.removeRule(8);
            layoutParams5.removeRule(2);
            layoutParams5.setMargins(dimension, dimension, 0, 0);
        }
        captionsButton.setLayoutParams(layoutParams5);
        ImageView expandContractButton = getExpandContractButton();
        ViewGroup.LayoutParams layoutParams6 = getExpandContractButton().getLayoutParams();
        RelativeLayout.LayoutParams layoutParams7 = layoutParams6 instanceof RelativeLayout.LayoutParams ? (RelativeLayout.LayoutParams) layoutParams6 : null;
        if (layoutParams7 == null) {
            layoutParams7 = null;
        } else {
            layoutParams7.addRule(6, R.id.VideoPlaybackSurfaceLayout);
            layoutParams7.addRule(11, -1);
            layoutParams7.removeRule(10);
            layoutParams7.removeRule(0);
            layoutParams7.setMargins(0, dimension, dimension, 0);
        }
        expandContractButton.setLayoutParams(layoutParams7);
        FrameLayout videoPersistentPlayerContainer = getVideoPersistentPlayerContainer();
        if (videoPersistentPlayerContainer == null) {
            return;
        }
        FrameLayout videoPersistentPlayerContainer2 = getVideoPersistentPlayerContainer();
        ViewGroup.LayoutParams layoutParams8 = videoPersistentPlayerContainer2 == null ? null : videoPersistentPlayerContainer2.getLayoutParams();
        ConstraintLayout.LayoutParams layoutParams9 = layoutParams8 instanceof ConstraintLayout.LayoutParams ? (ConstraintLayout.LayoutParams) layoutParams8 : null;
        if (layoutParams9 != null) {
            layoutParams9.setMargins(0, 0, 0, 0);
            layoutParams2 = layoutParams9;
        }
        videoPersistentPlayerContainer.setLayoutParams(layoutParams2);
    }

    static /* synthetic */ void initStyleSheet$default(VideoPlayerFragment videoPlayerFragment, Configuration configuration, int i, Object obj) {
        if ((i & 1) != 0) {
            configuration = null;
        }
        videoPlayerFragment.initStyleSheet(configuration);
    }

    private final void initStyleSheet(Configuration newConfig) {
        final boolean z = false;
        if ((newConfig != null && newConfig.orientation == 1) || (newConfig == null && ScreenUtil.isPortrait())) {
            z = true;
        }
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.playback.fragment.VideoPlayerFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                VideoPlayerFragment.m1447initStyleSheet$lambda52(this.f$0, z, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initStyleSheet$lambda-52, reason: not valid java name */
    public static final void m1447initStyleSheet$lambda52(VideoPlayerFragment this$0, boolean z, StyleSheet styleSheet) {
        Integer spacerInPixels;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        this$0.styleSheet = styleSheet;
        Context context = this$0.getContext();
        GridSize gridSize = context == null ? null : styleSheet.getGridSize(context);
        if (gridSize != null) {
            int marginSpace = gridSize.getMarginSpace() + gridSize.getColumnSpace();
            int iIntValue = (!ScreenUtil.isLandscape() || (spacerInPixels = styleSheet.getSpacerInPixels(SpacerKey.MINI)) == null) ? 0 : spacerInPixels.intValue();
            LayoutParamUtils layoutParamUtils = LayoutParamUtils.INSTANCE;
            ViewGroup controlsContainer = this$0.getControlsContainer();
            int i = z ? 0 : -2;
            Boolean bool = Boolean.FALSE;
            layoutParamUtils.setLayoutMargins(controlsContainer, 0, i, bool, Integer.valueOf(marginSpace), 0, Integer.valueOf(z ? 0 : marginSpace), Integer.valueOf(iIntValue));
            if (z) {
                layoutParamUtils.setLayoutMargins(this$0.getSeekBar(), -1, -2, (240 & 8) != 0 ? Boolean.FALSE : bool, (240 & 16) != 0 ? null : Integer.valueOf(marginSpace), (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : Integer.valueOf(marginSpace), (240 & 128) != 0 ? null : null);
            }
            View bottomSpacing = this$0.getBottomSpacing();
            ViewGroup.LayoutParams layoutParams = this$0.getBottomSpacing().getLayoutParams();
            layoutParams.height = iIntValue;
            bottomSpacing.setLayoutParams(layoutParams);
            ViewGroup controlsContainer2 = this$0.getControlsContainer();
            ViewGroup.LayoutParams layoutParams2 = this$0.getControlsContainer().getLayoutParams();
            if (layoutParams2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout.LayoutParams");
            }
            RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) layoutParams2;
            layoutParams3.setMargins(0, 0, marginSpace, 0);
            controlsContainer2.setLayoutParams(layoutParams3);
            if (z && !VideoUtil.INSTANCE.isInPictureInPictureMode(this$0.getActivity())) {
                this$0.adjustPortraitViews();
            } else {
                this$0.adjustLandscapeViews(gridSize);
            }
            if (this$0.hasShownControlsOnce || VideoUtil.INSTANCE.isInPictureInPictureMode(this$0.getActivity())) {
                return;
            }
            this$0.showControlsAndOptionallyFadeOut(true);
        }
    }
}
