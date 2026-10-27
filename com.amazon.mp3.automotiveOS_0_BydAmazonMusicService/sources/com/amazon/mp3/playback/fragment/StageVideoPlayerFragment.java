package com.amazon.mp3.playback.fragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.PictureInPictureParams;
import android.app.PictureInPictureParams$Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.OrientationEventListener;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewGroupKt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.lifecycle.LifecycleObserver;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import com.amazon.avod.util.playback.DurationUtils;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.dagger.DaggerSubcomponents;
import com.amazon.mp3.fragment.BaseFragment;
import com.amazon.mp3.playback.activity.LeavingActivityReason;
import com.amazon.mp3.playback.activity.NowPlayingFragmentActivity;
import com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$metadataChangedListener$2;
import com.amazon.mp3.playback.usecase.AudioVideoToggleUseCase;
import com.amazon.mp3.playback.util.VideoPreviewSeekBarTracker;
import com.amazon.mp3.playback.video.VideoUtil;
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
import com.amazon.mp3.view.stage.StageBottomButtonsView;
import com.amazon.mp3.view.stage.StageBottomIconType;
import com.amazon.mp3.view.stage.StageLandscapeControlsView;
import com.amazon.mp3.view.stage.StageViewPresenter;
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
import com.amazon.music.views.library.styles.keys.AlphaKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.styles.keys.LandscapeType;
import com.amazon.ui.foundations.styles.ButtonSize;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.styles.StatefulStyle;
import com.amazon.ui.foundations.utils.FontUtil;
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
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.SequencesKt___SequencesKt;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: compiled from: StageVideoPlayerFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000Ö\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 ú\u00012\u00020\u0001:\u0002ú\u0001B\t¢\u0006\u0006\bø\u0001\u0010ù\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u0002J\u0010\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0005H\u0002J\u0010\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\u0002H\u0002J\b\u0010\u000e\u001a\u00020\u0002H\u0002J\b\u0010\u000f\u001a\u00020\u0002H\u0003J\b\u0010\u0010\u001a\u00020\u0002H\u0002J\b\u0010\u0011\u001a\u00020\u0002H\u0002J\u0012\u0010\u0014\u001a\u00020\u00022\b\b\u0002\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\u0002H\u0002J\u0010\u0010\u0016\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0002J\b\u0010\u0017\u001a\u00020\u0002H\u0002J\b\u0010\u0018\u001a\u00020\u0002H\u0002J\b\u0010\u0019\u001a\u00020\u0002H\u0002J\b\u0010\u001a\u001a\u00020\u0002H\u0002J\b\u0010\u001b\u001a\u00020\u0002H\u0002J\b\u0010\u001c\u001a\u00020\u0002H\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0002J\b\u0010\u001f\u001a\u00020\u001eH\u0002J\b\u0010 \u001a\u00020\u0002H\u0002J\u0010\u0010\"\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0012H\u0002J\b\u0010#\u001a\u00020\u0002H\u0002J\b\u0010$\u001a\u00020\u0002H\u0002J\b\u0010%\u001a\u00020\u0002H\u0003J\u0010\u0010&\u001a\u00020\u00022\u0006\u0010!\u001a\u00020\u0012H\u0002J\b\u0010'\u001a\u00020\u0002H\u0002J\b\u0010(\u001a\u00020\u0002H\u0002J\u0018\u0010-\u001a\u00020\u00022\u0006\u0010*\u001a\u00020)2\u0006\u0010,\u001a\u00020+H\u0002J\u0010\u0010.\u001a\u00020\u00022\u0006\u0010*\u001a\u00020)H\u0002J\b\u0010/\u001a\u00020\u0002H\u0002J\b\u00100\u001a\u00020\u0002H\u0002J\b\u00101\u001a\u00020\u0002H\u0002J\u0010\u00103\u001a\u00020\u00022\u0006\u00102\u001a\u00020\u0012H\u0002J\b\u00104\u001a\u00020\u0012H\u0002J\u0010\u00107\u001a\u00020\u00022\u0006\u00106\u001a\u000205H\u0002J$\u0010>\u001a\u00020\u00062\u0006\u00109\u001a\u0002082\b\u0010;\u001a\u0004\u0018\u00010:2\b\u0010=\u001a\u0004\u0018\u00010<H\u0016J\u001a\u0010@\u001a\u00020\u00022\u0006\u0010?\u001a\u00020\u00062\b\u0010=\u001a\u0004\u0018\u00010<H\u0016J\b\u0010A\u001a\u00020\u0002H\u0016J\b\u0010B\u001a\u00020\u0002H\u0016J\b\u0010C\u001a\u00020\u0002H\u0016J\b\u0010D\u001a\u00020\u0002H\u0016J\b\u0010E\u001a\u00020\u0002H\u0016J\u0010\u0010H\u001a\u00020\u00022\u0006\u0010G\u001a\u00020FH\u0016J\u0010\u0010J\u001a\u00020\u00022\u0006\u0010I\u001a\u00020\u0012H\u0016J\u0006\u0010K\u001a\u00020\u0002J\u000e\u0010N\u001a\u00020\u00022\u0006\u0010M\u001a\u00020LJ\u0016\u0010R\u001a\u00020\u00022\u0006\u0010O\u001a\u00020\u00122\u0006\u0010Q\u001a\u00020PR\u001b\u0010X\u001a\u00020S8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bT\u0010U\u001a\u0004\bV\u0010WR\u001b\u0010[\u001a\u00020S8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bY\u0010U\u001a\u0004\bZ\u0010WR\u001b\u0010`\u001a\u00020\\8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b]\u0010U\u001a\u0004\b^\u0010_R\u001b\u0010e\u001a\u00020a8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bb\u0010U\u001a\u0004\bc\u0010dR\u001b\u0010j\u001a\u00020f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bg\u0010U\u001a\u0004\bh\u0010iR\u001b\u0010o\u001a\u00020k8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bl\u0010U\u001a\u0004\bm\u0010nR\u001b\u0010t\u001a\u00020p8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bq\u0010U\u001a\u0004\br\u0010sR\u001b\u0010y\u001a\u00020u8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bv\u0010U\u001a\u0004\bw\u0010xR\u001b\u0010~\u001a\u00020z8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b{\u0010U\u001a\u0004\b|\u0010}R\u001d\u0010\u0081\u0001\u001a\u00020f8BX\u0082\u0084\u0002¢\u0006\r\n\u0004\b\u007f\u0010U\u001a\u0005\b\u0080\u0001\u0010iR \u0010\u0086\u0001\u001a\u00030\u0082\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0083\u0001\u0010U\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u001e\u0010\u0089\u0001\u001a\u00020a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0087\u0001\u0010U\u001a\u0005\b\u0088\u0001\u0010dR\u001e\u0010\u008c\u0001\u001a\u00020a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u008a\u0001\u0010U\u001a\u0005\b\u008b\u0001\u0010dR\"\u0010\u0091\u0001\u001a\u0005\u0018\u00010\u008d\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u008e\u0001\u0010U\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\"\u0010\u0094\u0001\u001a\u0005\u0018\u00010\u008d\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0092\u0001\u0010U\u001a\u0006\b\u0093\u0001\u0010\u0090\u0001R\u001e\u0010\u0097\u0001\u001a\u00020a8BX\u0082\u0084\u0002¢\u0006\u000e\n\u0005\b\u0095\u0001\u0010U\u001a\u0005\b\u0096\u0001\u0010dR\"\u0010\u009a\u0001\u001a\u0005\u0018\u00010\u008d\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u0098\u0001\u0010U\u001a\u0006\b\u0099\u0001\u0010\u0090\u0001R \u0010\u009f\u0001\u001a\u00030\u009b\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b\u009c\u0001\u0010U\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\"\u0010¤\u0001\u001a\u0005\u0018\u00010 \u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¡\u0001\u0010U\u001a\u0006\b¢\u0001\u0010£\u0001R \u0010§\u0001\u001a\u00030 \u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¥\u0001\u0010U\u001a\u0006\b¦\u0001\u0010£\u0001R \u0010¬\u0001\u001a\u00030¨\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b©\u0001\u0010U\u001a\u0006\bª\u0001\u0010«\u0001R\u001c\u0010®\u0001\u001a\u0005\u0018\u00010\u00ad\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b®\u0001\u0010¯\u0001R\u001a\u0010±\u0001\u001a\u00030°\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b±\u0001\u0010²\u0001R\u001c\u0010´\u0001\u001a\u0005\u0018\u00010³\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b´\u0001\u0010µ\u0001R\u001c\u0010¶\u0001\u001a\u0005\u0018\u00010³\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¶\u0001\u0010µ\u0001R\u001c\u0010·\u0001\u001a\u0005\u0018\u00010³\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b·\u0001\u0010µ\u0001R\u001a\u0010¹\u0001\u001a\u00030¸\u00018\u0002@\u0002X\u0082.¢\u0006\b\n\u0006\b¹\u0001\u0010º\u0001R\u001c\u0010¼\u0001\u001a\u0005\u0018\u00010»\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¼\u0001\u0010½\u0001R \u0010Â\u0001\u001a\u00030¾\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\b¿\u0001\u0010U\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u001f\u0010Ä\u0001\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bÃ\u0001\u0010U\u001a\u0006\bÄ\u0001\u0010Å\u0001R\u0019\u0010Æ\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÆ\u0001\u0010Ç\u0001R\u0019\u0010È\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÈ\u0001\u0010Ç\u0001R\u0019\u0010É\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÉ\u0001\u0010Ç\u0001R\"\u0010Ì\u0001\u001a\r Ë\u0001*\u0005\u0018\u00010Ê\u00010Ê\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÌ\u0001\u0010Í\u0001R\u001c\u0010Ï\u0001\u001a\u0005\u0018\u00010Î\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÏ\u0001\u0010Ð\u0001R*\u0010Ò\u0001\u001a\u00030Ñ\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\bÒ\u0001\u0010Ó\u0001\u001a\u0006\bÔ\u0001\u0010Õ\u0001\"\u0006\bÖ\u0001\u0010×\u0001R*\u0010Ù\u0001\u001a\u00030Ø\u00018\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0006\bÙ\u0001\u0010Ú\u0001\u001a\u0006\bÛ\u0001\u0010Ü\u0001\"\u0006\bÝ\u0001\u0010Þ\u0001R\u0019\u0010ß\u0001\u001a\u00020\u00128\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bß\u0001\u0010Ç\u0001R\u001a\u0010á\u0001\u001a\u0005\u0018\u00010à\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bá\u0001\u0010â\u0001R,\u0010ç\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020ã\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bä\u0001\u0010U\u001a\u0006\bå\u0001\u0010æ\u0001R\u0018\u0010é\u0001\u001a\u00030è\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bé\u0001\u0010ê\u0001R \u0010ï\u0001\u001a\u00030ë\u00018BX\u0082\u0084\u0002¢\u0006\u000f\n\u0005\bì\u0001\u0010U\u001a\u0006\bí\u0001\u0010î\u0001R\u0017\u0010ð\u0001\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bð\u0001\u0010ñ\u0001R\u0017\u0010ò\u0001\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bò\u0001\u0010ñ\u0001R\u0017\u0010ó\u0001\u001a\u00020)8\u0002X\u0082\u0004¢\u0006\b\n\u0006\bó\u0001\u0010ñ\u0001R\u001a\u0010÷\u0001\u001a\u0005\u0018\u00010ô\u00018BX\u0082\u0004¢\u0006\b\u001a\u0006\bõ\u0001\u0010ö\u0001¨\u0006û\u0001"}, d2 = {"Lcom/amazon/mp3/playback/fragment/StageVideoPlayerFragment;", "Lcom/amazon/mp3/fragment/BaseFragment;", "", "adjustLandscapeViews", "adjustPortraitViews", "", "Landroid/view/View;", "getLandscapeViews", "getPortraitViews", "", "visibility", "togglePortraitViews", "toggleLandscapeViews", "initStyleSheet", "initViewStyling", "setupViews", "setupTrickPlay", "handlePlayAreaTap", "", "fadeOut", "fadeInUserControls", "fadeOutUserControls", "toggleUserControls", "clearUserControlsAnimation", "setupPipView", "handleUserControlsVisibility", "hideUserControlsForPip", "startPlayback", "initPlayer", "initPlayerAndResumePlaybackIfNeeded", "Lcom/amazon/video/sdk/player/FeatureConfigs;", "getFeatureConfigs", "setupCaptions", "enabled", "setCaptionsEnabled", "toggleCaptionsButton", "expandContractButtonClicked", "enterPIPMode", "setImmersiveMode", "registerConnectivityReceiver", "unregisterConnectivityReceiver", "Landroid/content/BroadcastReceiver;", "receiver", "Landroid/content/IntentFilter;", "intentFilter", "registerLocalBroadcastActivityReceiver", "unregisterLocalBroadcastActivityReceiver", "updateAll", "updateSeekBar", "updateHeader", "playNext", "skipVideo", "isPlayerPaused", "Landroid/content/Context;", "appContext", "navigateToLauncherTask", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/os/Bundle;", "savedInstanceState", "onCreateView", "view", "onViewCreated", "onResume", "onPause", "onStart", "onStop", "onDestroy", "Landroid/content/res/Configuration;", "newConfig", "onConfigurationChanged", "isInPictureInPictureMode", "onPictureInPictureModeChanged", "addLandscapeAudioVideoToggle", "", "currentTimeMs", "updateVideoPreviewWindow", "isVideoOn", "", "asin", "sendUiPageViewMetrics", "Landroid/widget/RelativeLayout;", "viewRoot$delegate", "Lkotlin/Lazy;", "getViewRoot", "()Landroid/widget/RelativeLayout;", "viewRoot", "videoSurfaceView$delegate", "getVideoSurfaceView", "videoSurfaceView", "Landroid/widget/FrameLayout;", "overlayView$delegate", "getOverlayView", "()Landroid/widget/FrameLayout;", "overlayView", "Lcom/amazon/ui/foundations/views/BaseButton;", "landscapeCloseButton$delegate", "getLandscapeCloseButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "landscapeCloseButton", "Landroid/widget/LinearLayout;", "landscapeAudioVideoToggleView$delegate", "getLandscapeAudioVideoToggleView", "()Landroid/widget/LinearLayout;", "landscapeAudioVideoToggleView", "Lcom/amazon/mp3/view/player/PlayerMetadataView;", "landscapePlayerMetadataView$delegate", "getLandscapePlayerMetadataView", "()Lcom/amazon/mp3/view/player/PlayerMetadataView;", "landscapePlayerMetadataView", "Landroidx/constraintlayout/widget/ConstraintLayout;", "landscapePlaybackControlsAndBottomButtons$delegate", "getLandscapePlaybackControlsAndBottomButtons", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "landscapePlaybackControlsAndBottomButtons", "Lcom/amazon/mp3/view/stage/StageLandscapeControlsView;", "landscapePlaybackControlsView$delegate", "getLandscapePlaybackControlsView", "()Lcom/amazon/mp3/view/stage/StageLandscapeControlsView;", "landscapePlaybackControlsView", "Landroid/widget/ProgressBar;", "landscapePlaybackProgressBar$delegate", "getLandscapePlaybackProgressBar", "()Landroid/widget/ProgressBar;", "landscapePlaybackProgressBar", "landscapeControlTipAndBottomButtons$delegate", "getLandscapeControlTipAndBottomButtons", "landscapeControlTipAndBottomButtons", "Lcom/amazon/mp3/view/stage/StageBottomButtonsView;", "landscapeBottomButtons$delegate", "getLandscapeBottomButtons", "()Lcom/amazon/mp3/view/stage/StageBottomButtonsView;", "landscapeBottomButtons", "portraitCaptionsButton$delegate", "getPortraitCaptionsButton", "portraitCaptionsButton", "landscapeCaptionsButton$delegate", "getLandscapeCaptionsButton", "landscapeCaptionsButton", "Landroid/graphics/drawable/Drawable;", "closedCaptionsActiveDrawable$delegate", "getClosedCaptionsActiveDrawable", "()Landroid/graphics/drawable/Drawable;", "closedCaptionsActiveDrawable", "closedCaptionsInactiveDrawable$delegate", "getClosedCaptionsInactiveDrawable", "closedCaptionsInactiveDrawable", "portraitExpandContractButton$delegate", "getPortraitExpandContractButton", "portraitExpandContractButton", "portraitExpandDrawable$delegate", "getPortraitExpandDrawable", "portraitExpandDrawable", "Landroid/widget/TextView;", "controlTipView$delegate", "getControlTipView", "()Landroid/widget/TextView;", "controlTipView", "Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "portraitSeekBar$delegate", "getPortraitSeekBar", "()Lcom/amazon/mp3/view/BauhausSeekBarWidget;", "portraitSeekBar", "landscapeSeekBar$delegate", "getLandscapeSeekBar", "landscapeSeekBar", "Lcom/amazon/mp3/playback/view/VideoPreviewWindow;", "videoPreviewWindow$delegate", "getVideoPreviewWindow", "()Lcom/amazon/mp3/playback/view/VideoPreviewWindow;", "videoPreviewWindow", "Lcom/amazon/mp3/playback/util/VideoPreviewSeekBarTracker;", "videoPreviewSeekBarTracker", "Lcom/amazon/mp3/playback/util/VideoPreviewSeekBarTracker;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lrx/Subscription;", "styleSheetSubscription", "Lrx/Subscription;", "viewAnimationSubscription", "leavingActivitySubscription", "Landroid/view/OrientationEventListener;", "orientationEventListener", "Landroid/view/OrientationEventListener;", "Landroidx/lifecycle/LifecycleObserver;", "resumeObserver", "Landroidx/lifecycle/LifecycleObserver;", "Lcom/amazon/mp3/view/TouchEventHandler;", "touchEventHandler$delegate", "getTouchEventHandler", "()Lcom/amazon/mp3/view/TouchEventHandler;", "touchEventHandler", "isTrickPlaySupported$delegate", "isTrickPlaySupported", "()Z", "hasShownControlsOnce", "Z", "currentControllerVisibility", "shouldResumePlayback", "Lcom/amazon/music/media/playback/PlaybackController;", "kotlin.jvm.PlatformType", "playbackController", "Lcom/amazon/music/media/playback/PlaybackController;", "Lcom/amazon/music/media/playback/MediaItem;", "mediaItem", "Lcom/amazon/music/media/playback/MediaItem;", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "audioVideoToggleInteractor", "Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "getAudioVideoToggleInteractor", "()Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;", "setAudioVideoToggleInteractor", "(Lcom/amazon/mp3/playback/usecase/AudioVideoToggleUseCase;)V", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "videoPlayerStateProvider", "Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "getVideoPlayerStateProvider", "()Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;", "setVideoPlayerStateProvider", "(Lcom/amazon/mp3/starlight/provider/VideoPlayerStateProvider;)V", "isFragmentVisible", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "videoPlayer", "Lcom/amazon/music/media/playback/player/impl/PrimeVideoPlayer;", "Lkotlin/Function1;", "onPlaybackStarted$delegate", "getOnPlaybackStarted", "()Lkotlin/jvm/functions/Function1;", "onPlaybackStarted", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "playStateListener", "Lcom/amazon/music/media/playback/OnPlayStateChangedListener;", "Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "metadataChangedListener$delegate", "getMetadataChangedListener", "()Lcom/amazon/music/media/playback/OnMetadataChangedListener;", "metadataChangedListener", "connectivityReceiver", "Landroid/content/BroadcastReceiver;", "homeActivityReceiver", "dialogActivityReceiver", "Lcom/amazon/mp3/view/stage/StageViewPresenter;", "getStageViewPresenter", "()Lcom/amazon/mp3/view/stage/StageViewPresenter;", "stageViewPresenter", "<init>", "()V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class StageVideoPlayerFragment extends BaseFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = StageVideoPlayerFragment.class.getSimpleName();
    public AudioVideoToggleUseCase audioVideoToggleInteractor;
    private final BroadcastReceiver connectivityReceiver;
    private boolean currentControllerVisibility;
    private final BroadcastReceiver dialogActivityReceiver;
    private boolean hasShownControlsOnce;
    private final BroadcastReceiver homeActivityReceiver;
    private boolean isFragmentVisible;
    private Subscription leavingActivitySubscription;
    private MediaItem mediaItem;

    /* JADX INFO: renamed from: metadataChangedListener$delegate, reason: from kotlin metadata */
    private final Lazy metadataChangedListener;

    /* JADX INFO: renamed from: onPlaybackStarted$delegate, reason: from kotlin metadata */
    private final Lazy onPlaybackStarted;
    private OrientationEventListener orientationEventListener;
    private final OnPlayStateChangedListener playStateListener;
    private LifecycleObserver resumeObserver;
    private boolean shouldResumePlayback;
    private StyleSheet styleSheet;
    private Subscription styleSheetSubscription;
    private final PrimeVideoPlayer videoPlayer;
    public VideoPlayerStateProvider videoPlayerStateProvider;
    private VideoPreviewSeekBarTracker videoPreviewSeekBarTracker;
    private Subscription viewAnimationSubscription;

    /* JADX INFO: renamed from: viewRoot$delegate, reason: from kotlin metadata */
    private final Lazy viewRoot = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$viewRoot$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RelativeLayout invoke() {
            View viewInflate = this.this$0.getLayoutInflater().inflate(R.layout.stage_video_player_fragment, (ViewGroup) null);
            if (viewInflate != null) {
                return (RelativeLayout) viewInflate;
            }
            throw new NullPointerException("null cannot be cast to non-null type android.widget.RelativeLayout");
        }
    });

    /* JADX INFO: renamed from: videoSurfaceView$delegate, reason: from kotlin metadata */
    private final Lazy videoSurfaceView = LazyKt__LazyJVMKt.lazy(new Function0<RelativeLayout>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$videoSurfaceView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final RelativeLayout invoke() {
            return (RelativeLayout) this.this$0.getViewRoot().findViewById(R.id.VideoPlaybackSurfaceLayout);
        }
    });

    /* JADX INFO: renamed from: overlayView$delegate, reason: from kotlin metadata */
    private final Lazy overlayView = LazyKt__LazyJVMKt.lazy(new Function0<FrameLayout>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$overlayView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final FrameLayout invoke() {
            return (FrameLayout) this.this$0.getViewRoot().findViewById(R.id.VideoBackgroundOverlay);
        }
    });

    /* JADX INFO: renamed from: landscapeCloseButton$delegate, reason: from kotlin metadata */
    private final Lazy landscapeCloseButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapeCloseButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.getViewRoot().findViewById(R.id.LandscapeVideoHeaderClose);
        }
    });

    /* JADX INFO: renamed from: landscapeAudioVideoToggleView$delegate, reason: from kotlin metadata */
    private final Lazy landscapeAudioVideoToggleView = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapeAudioVideoToggleView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LinearLayout invoke() {
            return (LinearLayout) this.this$0.getViewRoot().findViewById(R.id.LandscapeAudioVideoToggleView);
        }
    });

    /* JADX INFO: renamed from: landscapePlayerMetadataView$delegate, reason: from kotlin metadata */
    private final Lazy landscapePlayerMetadataView = LazyKt__LazyJVMKt.lazy(new Function0<PlayerMetadataView>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapePlayerMetadataView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final PlayerMetadataView invoke() {
            return (PlayerMetadataView) this.this$0.getViewRoot().findViewById(R.id.LandscapeVideoMetadataView);
        }
    });

    /* JADX INFO: renamed from: landscapePlaybackControlsAndBottomButtons$delegate, reason: from kotlin metadata */
    private final Lazy landscapePlaybackControlsAndBottomButtons = LazyKt__LazyJVMKt.lazy(new Function0<ConstraintLayout>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapePlaybackControlsAndBottomButtons$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ConstraintLayout invoke() {
            return (ConstraintLayout) this.this$0.getViewRoot().findViewById(R.id.LandscapePlaybackControlsAndBottomButtons);
        }
    });

    /* JADX INFO: renamed from: landscapePlaybackControlsView$delegate, reason: from kotlin metadata */
    private final Lazy landscapePlaybackControlsView = LazyKt__LazyJVMKt.lazy(new Function0<StageLandscapeControlsView>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapePlaybackControlsView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final StageLandscapeControlsView invoke() {
            return (StageLandscapeControlsView) this.this$0.getLandscapePlaybackControlsAndBottomButtons().findViewById(R.id.LandscapePlaybackControlsView);
        }
    });

    /* JADX INFO: renamed from: landscapePlaybackProgressBar$delegate, reason: from kotlin metadata */
    private final Lazy landscapePlaybackProgressBar = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapePlaybackProgressBar$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ProgressBar invoke() {
            return (ProgressBar) this.this$0.getLandscapePlaybackControlsView().findViewById(R.id.LandscapeVideoProgress);
        }
    });

    /* JADX INFO: renamed from: landscapeControlTipAndBottomButtons$delegate, reason: from kotlin metadata */
    private final Lazy landscapeControlTipAndBottomButtons = LazyKt__LazyJVMKt.lazy(new Function0<LinearLayout>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapeControlTipAndBottomButtons$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LinearLayout invoke() {
            return (LinearLayout) this.this$0.getLandscapePlaybackControlsAndBottomButtons().findViewById(R.id.LandscapeControlTipAndBottomButtons);
        }
    });

    /* JADX INFO: renamed from: landscapeBottomButtons$delegate, reason: from kotlin metadata */
    private final Lazy landscapeBottomButtons = LazyKt__LazyJVMKt.lazy(new Function0<StageBottomButtonsView>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapeBottomButtons$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final StageBottomButtonsView invoke() {
            return (StageBottomButtonsView) this.this$0.getLandscapeControlTipAndBottomButtons().findViewById(R.id.BottomButtons);
        }
    });

    /* JADX INFO: renamed from: portraitCaptionsButton$delegate, reason: from kotlin metadata */
    private final Lazy portraitCaptionsButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$portraitCaptionsButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.getViewRoot().findViewById(R.id.PortraitVideoPlayerToggleCaptions);
        }
    });

    /* JADX INFO: renamed from: landscapeCaptionsButton$delegate, reason: from kotlin metadata */
    private final Lazy landscapeCaptionsButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapeCaptionsButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.getLandscapeBottomButtons().findViewById(R.id.StageVideoClosedCaptionsButton);
        }
    });

    /* JADX INFO: renamed from: closedCaptionsActiveDrawable$delegate, reason: from kotlin metadata */
    private final Lazy closedCaptionsActiveDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$closedCaptionsActiveDrawable$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Drawable invoke() {
            Context context = this.this$0.getContext();
            if (context == null) {
                return null;
            }
            return AppCompatResources.getDrawable(context, R.drawable.ic_other_closedcaptionsus_active);
        }
    });

    /* JADX INFO: renamed from: closedCaptionsInactiveDrawable$delegate, reason: from kotlin metadata */
    private final Lazy closedCaptionsInactiveDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$closedCaptionsInactiveDrawable$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Drawable invoke() {
            Context context = this.this$0.getContext();
            if (context == null) {
                return null;
            }
            return AppCompatResources.getDrawable(context, R.drawable.ic_other_closedcaptionsus_inactive);
        }
    });

    /* JADX INFO: renamed from: portraitExpandContractButton$delegate, reason: from kotlin metadata */
    private final Lazy portraitExpandContractButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$portraitExpandContractButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.getViewRoot().findViewById(R.id.PortraitVideoPlayerExpandContract);
        }
    });

    /* JADX INFO: renamed from: portraitExpandDrawable$delegate, reason: from kotlin metadata */
    private final Lazy portraitExpandDrawable = LazyKt__LazyJVMKt.lazy(new Function0<Drawable>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$portraitExpandDrawable$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Drawable invoke() {
            Context context = this.this$0.getContext();
            if (context == null) {
                return null;
            }
            return AppCompatResources.getDrawable(context, R.drawable.ic_other_expand);
        }
    });

    /* JADX INFO: renamed from: controlTipView$delegate, reason: from kotlin metadata */
    private final Lazy controlTipView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$controlTipView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextView invoke() {
            FontStyle fontStyleCopy$default;
            View viewFindViewById = this.this$0.getLandscapeControlTipAndBottomButtons().findViewById(R.id.ControlTip);
            StageVideoPlayerFragment stageVideoPlayerFragment = this.this$0;
            TextView textView = (TextView) viewFindViewById;
            StyleSheet styleSheet = stageVideoPlayerFragment.styleSheet;
            StyleSheet styleSheet2 = null;
            if (styleSheet == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                styleSheet = null;
            }
            Integer color = styleSheet.getColor(ColorKey.COLOR_PRIMARY, AlphaKey.GLASS_5);
            if (color != null) {
                StyleSheet styleSheet3 = stageVideoPlayerFragment.styleSheet;
                if (styleSheet3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                } else {
                    styleSheet2 = styleSheet3;
                }
                FontStyle fontStyle = styleSheet2.getFontStyle(FontStyleKey.LABEL);
                if (fontStyle != null && (fontStyleCopy$default = FontStyle.copy$default(fontStyle, null, null, color.intValue(), Boolean.FALSE, 3, null)) != null) {
                    FontUtil fontUtil = FontUtil.INSTANCE;
                    Intrinsics.checkNotNullExpressionValue(textView, "this");
                    fontUtil.applyFontStyle(textView, fontStyleCopy$default);
                }
            }
            return textView;
        }
    });

    /* JADX INFO: renamed from: portraitSeekBar$delegate, reason: from kotlin metadata */
    private final Lazy portraitSeekBar = LazyKt__LazyJVMKt.lazy(new Function0<BauhausSeekBarWidget>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$portraitSeekBar$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BauhausSeekBarWidget invoke() {
            View view;
            Fragment parentFragment = this.this$0.getParentFragment();
            if (parentFragment == null || (view = parentFragment.getView()) == null) {
                return null;
            }
            return (BauhausSeekBarWidget) view.findViewById(R.id.stage_seekbar);
        }
    });

    /* JADX INFO: renamed from: landscapeSeekBar$delegate, reason: from kotlin metadata */
    private final Lazy landscapeSeekBar = LazyKt__LazyJVMKt.lazy(new Function0<BauhausSeekBarWidget>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$landscapeSeekBar$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BauhausSeekBarWidget invoke() {
            return (BauhausSeekBarWidget) this.this$0.getViewRoot().findViewById(R.id.LandscapeSeekBar);
        }
    });

    /* JADX INFO: renamed from: videoPreviewWindow$delegate, reason: from kotlin metadata */
    private final Lazy videoPreviewWindow = LazyKt__LazyJVMKt.lazy(new Function0<VideoPreviewWindow>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$videoPreviewWindow$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final VideoPreviewWindow invoke() {
            return (VideoPreviewWindow) this.this$0.getViewRoot().findViewById(R.id.VideoPreviewWindow);
        }
    });

    /* JADX INFO: renamed from: touchEventHandler$delegate, reason: from kotlin metadata */
    private final Lazy touchEventHandler = LazyKt__LazyJVMKt.lazy(new Function0<TouchEventHandler>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$touchEventHandler$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TouchEventHandler invoke() {
            return new TouchEventHandler();
        }
    });

    /* JADX INFO: renamed from: isTrickPlaySupported$delegate, reason: from kotlin metadata */
    private final Lazy isTrickPlaySupported = LazyKt__LazyJVMKt.lazy(new Function0<Boolean>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.isTrickPlaySupported.2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final Boolean invoke() {
            return Boolean.valueOf(AmazonApplication.getCapabilities().isTrickPlaySupprted());
        }
    });
    private final PlaybackController playbackController = Playback.getInstance().getPlaybackController(ControlSource.APP_UI);

    public StageVideoPlayerFragment() {
        PlaybackConfig playbackConfig;
        Playback playback = Playback.getInstance();
        Player primeVideoPlayer = (playback == null || (playbackConfig = playback.getPlaybackConfig()) == null) ? null : playbackConfig.getPrimeVideoPlayer();
        this.videoPlayer = primeVideoPlayer instanceof PrimeVideoPlayer ? (PrimeVideoPlayer) primeVideoPlayer : null;
        this.onPlaybackStarted = LazyKt__LazyJVMKt.lazy(new Function0<Function1<? super Unit, ? extends Unit>>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2
            {
                super(0);
            }

            /* JADX INFO: renamed from: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2$1, reason: invalid class name */
            /* JADX INFO: compiled from: StageVideoPlayerFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "it", "invoke", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 6, 0}, xi = 48)
            static final class AnonymousClass1 extends Lambda implements Function1<Unit, Unit> {
                final /* synthetic */ StageVideoPlayerFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                AnonymousClass1(StageVideoPlayerFragment stageVideoPlayerFragment) {
                    super(1);
                    this.this$0 = stageVideoPlayerFragment;
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(Unit unit) {
                    invoke2(unit);
                    return Unit.INSTANCE;
                }

                /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(Unit it) {
                    Intrinsics.checkNotNullParameter(it, "it");
                    FragmentActivity activity = this.this$0.getActivity();
                    if (activity == null) {
                        return;
                    }
                    final StageVideoPlayerFragment stageVideoPlayerFragment = this.this$0;
                    activity.runOnUiThread(
                    /*  JADX ERROR: Method code generation error
                        jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0015: INVOKE 
                          (r3v2 'activity' androidx.fragment.app.FragmentActivity)
                          (wrap:java.lang.Runnable:0x0012: CONSTRUCTOR (r0v1 'stageVideoPlayerFragment' com.amazon.mp3.playback.fragment.StageVideoPlayerFragment A[DONT_INLINE]) A[MD:(com.amazon.mp3.playback.fragment.StageVideoPlayerFragment):void (m), WRAPPED] call: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2$1$$ExternalSyntheticLambda0.<init>(com.amazon.mp3.playback.fragment.StageVideoPlayerFragment):void type: CONSTRUCTOR)
                         VIRTUAL call: android.app.Activity.runOnUiThread(java.lang.Runnable):void A[MD:(java.lang.Runnable):void (c)] in method: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2.1.invoke(kotlin.Unit):void, file: classes3.dex
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:310)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:273)
                        	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:94)
                        	at jadx.core.dex.nodes.IBlock.generate(IBlock.java:15)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.dex.regions.Region.generate(Region.java:35)
                        	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:66)
                        	at jadx.core.codegen.MethodGen.addRegionInsns(MethodGen.java:305)
                        	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:284)
                        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:412)
                        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
                        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
                        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
                        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
                        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
                        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
                        Caused by: jadx.core.utils.exceptions.JadxRuntimeException: Expected class to be processed at this point, class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2$1$$ExternalSyntheticLambda0, state: NOT_LOADED
                        	at jadx.core.dex.nodes.ClassNode.ensureProcessed(ClassNode.java:306)
                        	at jadx.core.codegen.InsnGen.inlineAnonymousConstructor(InsnGen.java:807)
                        	at jadx.core.codegen.InsnGen.makeConstructor(InsnGen.java:730)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:418)
                        	at jadx.core.codegen.InsnGen.addWrappedArg(InsnGen.java:145)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:121)
                        	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:108)
                        	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:1143)
                        	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:910)
                        	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:422)
                        	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:303)
                        	... 19 more
                        */
                    /*
                        this = this;
                        java.lang.String r0 = "it"
                        kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
                        com.amazon.mp3.playback.fragment.StageVideoPlayerFragment r3 = r2.this$0
                        androidx.fragment.app.FragmentActivity r3 = r3.getActivity()
                        if (r3 != 0) goto Le
                        goto L18
                    Le:
                        com.amazon.mp3.playback.fragment.StageVideoPlayerFragment r0 = r2.this$0
                        com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2$1$$ExternalSyntheticLambda0 r1 = new com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2$1$$ExternalSyntheticLambda0
                        r1.<init>(r0)
                        r3.runOnUiThread(r1)
                    L18:
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$onPlaybackStarted$2.AnonymousClass1.invoke2(kotlin.Unit):void");
                }

                /* JADX INFO: Access modifiers changed from: private */
                /* JADX INFO: renamed from: invoke$lambda-0, reason: not valid java name */
                public static final void m1437invoke$lambda0(StageVideoPlayerFragment this$0) {
                    RenderingConfig startRenderingConfig;
                    Intrinsics.checkNotNullParameter(this$0, "this$0");
                    PrimeVideoPlayer primeVideoPlayer = this$0.videoPlayer;
                    if (((primeVideoPlayer == null || (startRenderingConfig = primeVideoPlayer.getStartRenderingConfig()) == null) ? null : startRenderingConfig.getSurfaceHandlingMode()) == SurfaceHandlingMode.AUDIO_ONLY && this$0.isFragmentVisible) {
                        this$0.initPlayer();
                        this$0.videoPlayer.setStartRenderingConfig(null);
                    }
                    this$0.setupCaptions();
                }
            }

            @Override // kotlin.jvm.functions.Function0
            public final Function1<? super Unit, ? extends Unit> invoke() {
                return new AnonymousClass1(this.this$0);
            }
        });
        this.playStateListener = new OnPlayStateChangedListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda4
            @Override // com.amazon.music.media.playback.OnPlayStateChangedListener
            public final void onPlayStateChanged() {
                StageVideoPlayerFragment.m1435playStateListener$lambda0(this.f$0);
            }
        };
        this.metadataChangedListener = LazyKt__LazyJVMKt.lazy(new Function0<StageVideoPlayerFragment$metadataChangedListener$2.AnonymousClass1>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$metadataChangedListener$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v0, types: [com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$metadataChangedListener$2$1] */
            @Override // kotlin.jvm.functions.Function0
            public final AnonymousClass1 invoke() {
                final StageVideoPlayerFragment stageVideoPlayerFragment = this.this$0;
                return new OnMetadataChangedListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$metadataChangedListener$2.1
                    @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                    public void onMediaCollectionInfoChanged(MediaCollectionInfo mediaCollectionInfo) {
                    }

                    @Override // com.amazon.music.media.playback.OnMetadataChangedListener
                    public void onMetadataChanged(MediaItem mediaItem) {
                        stageVideoPlayerFragment.updateAll();
                    }
                };
            }
        });
        this.connectivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$connectivityReceiver$1
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
        this.homeActivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$homeActivityReceiver$1
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
        this.dialogActivityReceiver = new BroadcastReceiver() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$dialogActivityReceiver$1
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
        DaggerSubcomponents.INSTANCE.getNowPlayingComponent().inject(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final RelativeLayout getViewRoot() {
        return (RelativeLayout) this.viewRoot.getValue();
    }

    private final StageViewPresenter getStageViewPresenter() {
        Fragment parentFragment = getParentFragment();
        NowPlayingFragment nowPlayingFragment = parentFragment instanceof NowPlayingFragment ? (NowPlayingFragment) parentFragment : null;
        if (nowPlayingFragment == null) {
            return null;
        }
        return nowPlayingFragment.getStageViewPresenter();
    }

    private final RelativeLayout getVideoSurfaceView() {
        Object value = this.videoSurfaceView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-videoSurfaceView>(...)");
        return (RelativeLayout) value;
    }

    private final FrameLayout getOverlayView() {
        Object value = this.overlayView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-overlayView>(...)");
        return (FrameLayout) value;
    }

    private final BaseButton getLandscapeCloseButton() {
        Object value = this.landscapeCloseButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapeCloseButton>(...)");
        return (BaseButton) value;
    }

    private final LinearLayout getLandscapeAudioVideoToggleView() {
        Object value = this.landscapeAudioVideoToggleView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapeAudioVideoToggleView>(...)");
        return (LinearLayout) value;
    }

    private final PlayerMetadataView getLandscapePlayerMetadataView() {
        Object value = this.landscapePlayerMetadataView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapePlayerMetadataView>(...)");
        return (PlayerMetadataView) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ConstraintLayout getLandscapePlaybackControlsAndBottomButtons() {
        Object value = this.landscapePlaybackControlsAndBottomButtons.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapePlaybackCo…olsAndBottomButtons>(...)");
        return (ConstraintLayout) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StageLandscapeControlsView getLandscapePlaybackControlsView() {
        Object value = this.landscapePlaybackControlsView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapePlaybackControlsView>(...)");
        return (StageLandscapeControlsView) value;
    }

    private final ProgressBar getLandscapePlaybackProgressBar() {
        Object value = this.landscapePlaybackProgressBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapePlaybackProgressBar>(...)");
        return (ProgressBar) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LinearLayout getLandscapeControlTipAndBottomButtons() {
        Object value = this.landscapeControlTipAndBottomButtons.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapeControlTipAndBottomButtons>(...)");
        return (LinearLayout) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final StageBottomButtonsView getLandscapeBottomButtons() {
        Object value = this.landscapeBottomButtons.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapeBottomButtons>(...)");
        return (StageBottomButtonsView) value;
    }

    private final BaseButton getPortraitCaptionsButton() {
        Object value = this.portraitCaptionsButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-portraitCaptionsButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getLandscapeCaptionsButton() {
        Object value = this.landscapeCaptionsButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapeCaptionsButton>(...)");
        return (BaseButton) value;
    }

    private final Drawable getClosedCaptionsActiveDrawable() {
        return (Drawable) this.closedCaptionsActiveDrawable.getValue();
    }

    private final Drawable getClosedCaptionsInactiveDrawable() {
        return (Drawable) this.closedCaptionsInactiveDrawable.getValue();
    }

    private final BaseButton getPortraitExpandContractButton() {
        Object value = this.portraitExpandContractButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-portraitExpandContractButton>(...)");
        return (BaseButton) value;
    }

    private final Drawable getPortraitExpandDrawable() {
        return (Drawable) this.portraitExpandDrawable.getValue();
    }

    private final TextView getControlTipView() {
        Object value = this.controlTipView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-controlTipView>(...)");
        return (TextView) value;
    }

    private final BauhausSeekBarWidget getPortraitSeekBar() {
        return (BauhausSeekBarWidget) this.portraitSeekBar.getValue();
    }

    private final BauhausSeekBarWidget getLandscapeSeekBar() {
        Object value = this.landscapeSeekBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-landscapeSeekBar>(...)");
        return (BauhausSeekBarWidget) value;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final VideoPreviewWindow getVideoPreviewWindow() {
        Object value = this.videoPreviewWindow.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-videoPreviewWindow>(...)");
        return (VideoPreviewWindow) value;
    }

    private final TouchEventHandler getTouchEventHandler() {
        return (TouchEventHandler) this.touchEventHandler.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isTrickPlaySupported() {
        return ((Boolean) this.isTrickPlaySupported.getValue()).booleanValue();
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

    private final Function1<Unit, Unit> getOnPlaybackStarted() {
        return (Function1) this.onPlaybackStarted.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: playStateListener$lambda-0, reason: not valid java name */
    public static final void m1435playStateListener$lambda0(StageVideoPlayerFragment this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.updateSeekBar();
        MediaItem currentMediaItem = this$0.playbackController.getCurrentMediaItem();
        if (currentMediaItem != null && !Intrinsics.areEqual(currentMediaItem, this$0.mediaItem)) {
            if (this$0.isTrickPlaySupported()) {
                this$0.getVideoPreviewWindow().resetVideoPreviewWindow();
            }
            this$0.updateHeader();
        }
        this$0.handleUserControlsVisibility();
    }

    private final OnMetadataChangedListener getMetadataChangedListener() {
        return (OnMetadataChangedListener) this.metadataChangedListener.getValue();
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
        return getViewRoot();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        setupViews();
        initStyleSheet();
        handleUserControlsVisibility();
    }

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onResume() {
        Observable<LeavingActivityReason> observable;
        super.onResume();
        initStyleSheet();
        if (AmazonApplication.getCapabilities().isPipEnabled()) {
            Subscription subscription = this.leavingActivitySubscription;
            if (subscription != null) {
                subscription.unsubscribe();
            }
            FragmentActivity activity = getActivity();
            Subscription subscriptionSubscribe = null;
            NowPlayingFragmentActivity nowPlayingFragmentActivity = activity instanceof NowPlayingFragmentActivity ? (NowPlayingFragmentActivity) activity : null;
            if (nowPlayingFragmentActivity != null && (observable = nowPlayingFragmentActivity.mLeavingActivitySubject) != null) {
                subscriptionSubscribe = observable.subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda3
                    @Override // rx.functions.Action1
                    public final void call(Object obj) {
                        StageVideoPlayerFragment.m1434onResume$lambda2(this.f$0, (LeavingActivityReason) obj);
                    }
                });
            }
            this.leavingActivitySubscription = subscriptionSubscribe;
        }
        this.playbackController.addOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.addOnMetadataChangedListener(getMetadataChangedListener());
        if (ScreenUtil.isLandscape()) {
            addLandscapeAudioVideoToggle();
            setImmersiveMode(true);
        } else {
            setImmersiveMode(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onResume$lambda-2, reason: not valid java name */
    public static final void m1434onResume$lambda2(StageVideoPlayerFragment this$0, LeavingActivityReason leavingActivityReason) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (leavingActivityReason == LeavingActivityReason.USER_LEAVE_HINT) {
            if (!ActivityKt.isAvailable(this$0.getActivity()) || this$0.playbackController.getPlayStatus() == PlayStatus.USER_PAUSED) {
                return;
            }
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

    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    public void onPause() {
        super.onPause();
        this.shouldResumePlayback = !isPlayerPaused();
        if (!VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
            if (primeVideoPlayer != null) {
                primeVideoPlayer.setPlayerVisibility(false);
            }
            this.resumeObserver = FragmentKt.doOnResume(this, true, new Function0<Unit>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.onPause.1
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
                    StageVideoPlayerFragment.this.initPlayerAndResumePlaybackIfNeeded();
                }
            });
        }
        this.playbackController.removeOnPlayStateChangedListener(this.playStateListener);
        this.playbackController.removeOnMetadataChangedListener(getMetadataChangedListener());
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
        getLandscapeSeekBar().pauseSeekbarProgressUpdate();
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
            this.resumeObserver = FragmentKt.doOnResume(this, true, new Function0<Unit>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.onStop.1
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
                    StageVideoPlayerFragment.this.initPlayerAndResumePlaybackIfNeeded();
                }
            });
        }
        this.isFragmentVisible = false;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0082  */
    @Override // com.amazon.mp3.fragment.BaseFragment, com.amazon.mp3.service.job.JobSessionFragment, androidx.fragment.app.Fragment
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onDestroy() {
        /*
            r4 = this;
            super.onDestroy()
            java.lang.String r0 = com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.TAG
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onDestroy() NowPlayingUtil.isVideoPlaying(): "
            r1.append(r2)
            boolean r2 = com.amazon.mp3.playback.activity.NowPlayingUtil.isVideoPlaying()
            r1.append(r2)
            java.lang.String r2 = " videoPlayer.getContentState: "
            r1.append(r2)
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r2 = r4.videoPlayer
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
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r2 = r4.videoPlayer
            if (r2 != 0) goto L34
            r2 = r3
            goto L38
        L34:
            com.amazon.video.sdk.player.PlaybackState r2 = r2.getPlaybackState()
        L38:
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.amazon.mp3.util.Log.debug(r0, r1)
            com.amazon.mp3.view.BauhausSeekBarWidget r0 = r4.getLandscapeSeekBar()
            r0.pauseSeekbarProgressUpdate()
            rx.Subscription r0 = r4.styleSheetSubscription
            if (r0 != 0) goto L4e
            goto L51
        L4e:
            r0.unsubscribe()
        L51:
            boolean r0 = com.amazon.mp3.playback.activity.NowPlayingUtil.isVideoPlaying()
            r1 = 0
            if (r0 == 0) goto L82
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r4.videoPlayer
            if (r0 != 0) goto L5e
            r0 = r3
            goto L62
        L5e:
            com.amazon.video.sdk.player.ContentState r0 = r0.getContentState()
        L62:
            com.amazon.video.sdk.player.ContentState r2 = com.amazon.video.sdk.player.ContentState.Ready
            if (r0 != r2) goto L70
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r4.videoPlayer
            com.amazon.video.sdk.player.PlaybackState r0 = r0.getPlaybackState()
            com.amazon.video.sdk.player.PlaybackState r2 = com.amazon.video.sdk.player.PlaybackState.Playing
            if (r0 == r2) goto L82
        L70:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r4.videoPlayer
            if (r0 != 0) goto L75
            goto L78
        L75:
            r0.pause(r3, r3)
        L78:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r4.videoPlayer
            if (r0 != 0) goto L7d
            goto L8a
        L7d:
            r2 = 1
            r0.destroy(r2)
            goto L8a
        L82:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r4.videoPlayer
            if (r0 != 0) goto L87
            goto L8a
        L87:
            r0.setPlayerVisibility(r1)
        L8a:
            com.amazon.music.media.playback.player.impl.PrimeVideoPlayer r0 = r4.videoPlayer
            if (r0 != 0) goto L8f
            goto L96
        L8f:
            kotlin.jvm.functions.Function1 r2 = r4.getOnPlaybackStarted()
            r0.removeOnPlaybackStartedListener(r2)
        L96:
            android.content.BroadcastReceiver r0 = r4.homeActivityReceiver
            r4.unregisterLocalBroadcastActivityReceiver(r0)
            android.content.BroadcastReceiver r0 = r4.dialogActivityReceiver
            r4.unregisterLocalBroadcastActivityReceiver(r0)
            r4.unregisterConnectivityReceiver()
            r4.setImmersiveMode(r1)
            androidx.lifecycle.LifecycleObserver r0 = r4.resumeObserver
            if (r0 != 0) goto Lab
            goto Lb2
        Lab:
            androidx.lifecycle.Lifecycle r1 = r4.getLifecycle()
            r1.removeObserver(r0)
        Lb2:
            r4.clearUserControlsAnimation()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.onDestroy():void");
    }

    @Override // androidx.fragment.app.Fragment, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration newConfig) {
        Intrinsics.checkNotNullParameter(newConfig, "newConfig");
        super.onConfigurationChanged(newConfig);
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            return;
        }
        this.hasShownControlsOnce = false;
        setupTrickPlay();
        updateAll();
        setupViews();
        setupCaptions();
        initStyleSheet();
        handleUserControlsVisibility();
        if (newConfig.orientation == 2) {
            adjustLandscapeViews();
        } else {
            adjustPortraitViews();
        }
    }

    private final void adjustLandscapeViews() {
        float dimension;
        Context context = getContext();
        LandscapeType landscapeType = null;
        StyleSheet styleSheet = null;
        if (context != null) {
            StyleSheet styleSheet2 = this.styleSheet;
            if (styleSheet2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            } else {
                styleSheet = styleSheet2;
            }
            landscapeType = styleSheet.getLandscapeType(context);
        }
        if (landscapeType == LandscapeType.TABLET_LANDSCAPE) {
            dimension = getResources().getDimension(R.dimen.spacer_48);
        } else {
            dimension = getResources().getDimension(R.dimen.spacer_40);
        }
        int i = (int) dimension;
        LayoutParamUtils.INSTANCE.setLayoutMargins(getLandscapePlaybackProgressBar(), i, i, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        togglePortraitViews(8);
        toggleLandscapeViews(0);
        StageViewPresenter stageViewPresenter = getStageViewPresenter();
        if (stageViewPresenter == null) {
            return;
        }
        stageViewPresenter.updateVideoPlaybackControls(true, getLandscapePlaybackControlsView(), getLandscapePlaybackProgressBar());
    }

    private final void adjustPortraitViews() {
        toggleLandscapeViews(8);
        togglePortraitViews(0);
        StageViewPresenter stageViewPresenter = getStageViewPresenter();
        if (stageViewPresenter == null) {
            return;
        }
        StageViewPresenter.updateVideoPlaybackControls$default(stageViewPresenter, false, null, null, 6, null);
    }

    private final List<View> getLandscapeViews() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new ViewGroup[]{getLandscapeCloseButton(), getLandscapeAudioVideoToggleView(), getLandscapePlayerMetadataView(), getLandscapePlaybackControlsAndBottomButtons(), getLandscapeSeekBar()});
    }

    private final List<View> getPortraitViews() {
        return CollectionsKt__CollectionsKt.listOf((Object[]) new ConstraintLayout[]{getPortraitCaptionsButton(), getPortraitExpandContractButton(), getPortraitSeekBar()});
    }

    private final void togglePortraitViews(int visibility) {
        for (View view : getPortraitViews()) {
            if (view != null) {
                view.setVisibility(visibility);
            }
        }
    }

    private final void toggleLandscapeViews(int visibility) {
        Iterator<T> it = getLandscapeViews().iterator();
        while (it.hasNext()) {
            ((View) it.next()).setVisibility(visibility);
        }
    }

    private final void initStyleSheet() {
        this.styleSheetSubscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda0
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StageVideoPlayerFragment.m1429initStyleSheet$lambda8(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initStyleSheet$lambda-8, reason: not valid java name */
    public static final void m1429initStyleSheet$lambda8(StageVideoPlayerFragment this$0, StyleSheet it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.styleSheet = it;
        Context context = this$0.getContext();
        if (context != null) {
            StyleSheet styleSheet = this$0.styleSheet;
            if (styleSheet == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                styleSheet = null;
            }
            if (styleSheet.getLandscapeType(context) == LandscapeType.TABLET_LANDSCAPE) {
                this$0.getControlTipView().setVisibility(0);
            }
        }
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(this$0.getActivity())) {
            return;
        }
        if (ScreenUtil.isPortrait()) {
            this$0.adjustPortraitViews();
        } else {
            this$0.adjustLandscapeViews();
        }
        if (this$0.hasShownControlsOnce) {
            return;
        }
        this$0.fadeInUserControls(true);
    }

    private final void initViewStyling() {
        this.styleSheetSubscription = StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                StageVideoPlayerFragment.m1430initViewStyling$lambda20(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViewStyling$lambda-20, reason: not valid java name */
    public static final void m1430initViewStyling$lambda20(final StageVideoPlayerFragment this$0, StyleSheet it) {
        StatefulStyle statefulStyleCopy;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton.StyleBuilder styleBuilderWithStyle;
        Drawable closedCaptionsActiveDrawable;
        BaseButton.StyleBuilder styleBuilderWithIcon3;
        BaseButton.StyleBuilder styleBuilderWithActiveIcon;
        BaseButton.StyleBuilder styleBuilderWithActiveStyle;
        Drawable drawable;
        BaseButton.StyleBuilder styleBuilderWithIcon4;
        BaseButton.StyleBuilder styleBuilderWithSize;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.styleSheet = it;
        StageLandscapeControlsView landscapePlaybackControlsView = this$0.getLandscapePlaybackControlsView();
        StyleSheet styleSheet = this$0.styleSheet;
        ButtonStyle buttonStyleCopy$default = null;
        if (styleSheet == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet = null;
        }
        landscapePlaybackControlsView.initViewStyling(styleSheet);
        PlayerMetadataView landscapePlayerMetadataView = this$0.getLandscapePlayerMetadataView();
        StyleSheet styleSheet2 = this$0.styleSheet;
        if (styleSheet2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet2 = null;
        }
        landscapePlayerMetadataView.initViewStyling(styleSheet2);
        this$0.getLandscapePlayerMetadataView().switchToStageLandscape();
        this$0.getLandscapeBottomButtons().setVideoPlayerStateProvider(this$0.getVideoPlayerStateProvider());
        StageBottomButtonsView landscapeBottomButtons = this$0.getLandscapeBottomButtons();
        StyleSheet styleSheet3 = this$0.styleSheet;
        if (styleSheet3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet3 = null;
        }
        landscapeBottomButtons.initViewStyling(styleSheet3, true);
        Context context = this$0.getContext();
        if (context != null && (drawable = AppCompatResources.getDrawable(context, R.drawable.ic_chevron_caretdown)) != null) {
            StyleSheet styleSheet4 = this$0.styleSheet;
            if (styleSheet4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                styleSheet4 = null;
            }
            BaseButton.StyleBuilder iconBuilder = styleSheet4.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
            if (iconBuilder != null && (styleBuilderWithIcon4 = iconBuilder.withIcon(drawable)) != null && (styleBuilderWithSize = styleBuilderWithIcon4.withSize(new ButtonSize(this$0.getResources().getDimension(R.dimen.spacer_40), null, this$0.getResources().getDimension(R.dimen.spacer_20), 2, null))) != null) {
                styleBuilderWithSize.applyStyle(this$0.getLandscapeCloseButton());
            }
        }
        this$0.getLandscapeCloseButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageVideoPlayerFragment.m1431initViewStyling$lambda20$lambda11(this.f$0, view);
            }
        });
        StyleSheet styleSheet5 = this$0.styleSheet;
        if (styleSheet5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet5 = null;
        }
        IconSizeKey iconSizeKey = IconSizeKey.SMALL;
        IconStyleKey iconStyleKey = IconStyleKey.PRIMARY;
        BaseButton.StyleBuilder iconBuilder2 = styleSheet5.getIconBuilder(iconSizeKey, iconStyleKey);
        Drawable closedCaptionsInactiveDrawable = this$0.getClosedCaptionsInactiveDrawable();
        if (closedCaptionsInactiveDrawable != null && (closedCaptionsActiveDrawable = this$0.getClosedCaptionsActiveDrawable()) != null) {
            StyleSheet styleSheet6 = this$0.styleSheet;
            if (styleSheet6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
                styleSheet6 = null;
            }
            ButtonStyle iconStyle = styleSheet6.getIconStyle(IconStyleKey.ACCENT);
            if (iconStyle != null && iconBuilder2 != null && (styleBuilderWithIcon3 = iconBuilder2.withIcon(closedCaptionsInactiveDrawable)) != null && (styleBuilderWithActiveIcon = styleBuilderWithIcon3.withActiveIcon(closedCaptionsActiveDrawable)) != null && (styleBuilderWithActiveStyle = styleBuilderWithActiveIcon.withActiveStyle(iconStyle)) != null) {
                styleBuilderWithActiveStyle.applyStyle(this$0.getPortraitCaptionsButton());
            }
        }
        this$0.getPortraitCaptionsButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageVideoPlayerFragment.m1432initViewStyling$lambda20$lambda15(this.f$0, view);
            }
        });
        StyleSheet styleSheet7 = this$0.styleSheet;
        if (styleSheet7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("styleSheet");
            styleSheet7 = null;
        }
        ButtonStyle iconStyle2 = styleSheet7.getIconStyle(iconStyleKey);
        StatefulStyle statefulStyle = iconStyle2 == null ? null : iconStyle2.getStatefulStyle();
        if (statefulStyle == null) {
            statefulStyleCopy = null;
        } else {
            statefulStyleCopy = statefulStyle.copy((253 & 1) != 0 ? statefulStyle.color : null, (253 & 2) != 0 ? statefulStyle.pressedColor : -1, (253 & 4) != 0 ? statefulStyle.focusedColor : null, (253 & 8) != 0 ? statefulStyle.disabledColor : null, (253 & 16) != 0 ? statefulStyle.backgroundColor : null, (253 & 32) != 0 ? statefulStyle.pressedBackgroundColor : null, (253 & 64) != 0 ? statefulStyle.focusedBackgroundColor : null, (253 & 128) != 0 ? statefulStyle.disabledBackgroundColor : null);
        }
        if (statefulStyleCopy != null && iconStyle2 != null) {
            buttonStyleCopy$default = ButtonStyle.copy$default(iconStyle2, statefulStyleCopy, null, null, null, 14, null);
        }
        Drawable portraitExpandDrawable = this$0.getPortraitExpandDrawable();
        if (portraitExpandDrawable != null) {
            if (buttonStyleCopy$default != null) {
                if (iconBuilder2 != null && (styleBuilderWithIcon2 = iconBuilder2.withIcon(portraitExpandDrawable)) != null && (styleBuilderWithStyle = styleBuilderWithIcon2.withStyle(buttonStyleCopy$default)) != null) {
                    styleBuilderWithStyle.applyStyle(this$0.getPortraitExpandContractButton());
                }
            } else if (iconBuilder2 != null && (styleBuilderWithIcon = iconBuilder2.withIcon(portraitExpandDrawable)) != null) {
                styleBuilderWithIcon.applyStyle(this$0.getPortraitExpandContractButton());
            }
        }
        this$0.getPortraitExpandContractButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                StageVideoPlayerFragment.m1433initViewStyling$lambda20$lambda19(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViewStyling$lambda-20$lambda-11, reason: not valid java name */
    public static final void m1431initViewStyling$lambda20$lambda11(StageVideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentActivity activity = this$0.getActivity();
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViewStyling$lambda-20$lambda-15, reason: not valid java name */
    public static final void m1432initViewStyling$lambda20$lambda15(StageVideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toggleCaptionsButton();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initViewStyling$lambda-20$lambda-19, reason: not valid java name */
    public static final void m1433initViewStyling$lambda20$lambda19(StageVideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.expandContractButtonClicked();
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private final void setupViews() {
        PrimeVideoPlayer primeVideoPlayer;
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            setupPipView();
        } else if (ScreenUtil.isLandscape()) {
            setImmersiveMode(true);
        } else {
            setImmersiveMode(false);
        }
        getLandscapeSeekBar().updateProgressBar();
        (ScreenUtil.isPortrait() ? getPortraitCaptionsButton() : getLandscapeCaptionsButton()).setActive(getVideoPlayerStateProvider().getIsCaptionsEnabled());
        getViewRoot().setOnTouchListener(getTouchEventHandler().getTouchEventListener());
        getTouchEventHandler().setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.setupViews.1
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
                if (((int) x) >= 120) {
                    StageVideoPlayerFragment.this.skipVideo(false);
                }
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
                if (((int) x) >= 120) {
                    StageVideoPlayerFragment.this.skipVideo(true);
                }
            }
        });
        Context context = getContext();
        if (context != null) {
            getTouchEventHandler().setOnClickListener(context, new View.OnClickListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    StageVideoPlayerFragment.m1436setupViews$lambda22$lambda21(this.f$0, view);
                }
            });
        }
        getLandscapeSeekBar().setTrackTouchListener(new BauhausSeekBarWidget.TrackTouchListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.setupViews.3
            @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
            public void onStartTrackingTouch() {
                StageVideoPlayerFragment.this.fadeInUserControls(false);
            }

            @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                if (StageVideoPlayerFragment.this.isTrickPlaySupported()) {
                    long progress2 = (((long) seekBar.getProgress()) * StageVideoPlayerFragment.this.playbackController.getDurationMillis()) / ((long) seekBar.getMax());
                    PrimeVideoPlayer primeVideoPlayer2 = StageVideoPlayerFragment.this.videoPlayer;
                    if (primeVideoPlayer2 != null) {
                        primeVideoPlayer2.trackPlayAssetForPosition(progress2);
                    }
                    StageVideoPlayerFragment.this.updateVideoPreviewWindow(progress2);
                }
            }

            @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
            public void onStopTrackingTouch() {
                StageVideoPlayerFragment.this.getVideoPreviewWindow().hide();
                if (StageVideoPlayerFragment.this.playbackController.getPlayStatus() == PlayStatus.RENDERING) {
                    StageVideoPlayerFragment.this.fadeInUserControls(true);
                }
            }
        });
        BauhausSeekBarWidget portraitSeekBar = getPortraitSeekBar();
        if (portraitSeekBar != null) {
            portraitSeekBar.setTrackTouchListener(new BauhausSeekBarWidget.TrackTouchListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.setupViews.4
                @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
                public void onStartTrackingTouch() {
                    StageVideoPlayerFragment.this.fadeInUserControls(false);
                }

                @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    Intrinsics.checkNotNullParameter(seekBar, "seekBar");
                    if (StageVideoPlayerFragment.this.isTrickPlaySupported()) {
                        long progress2 = (((long) seekBar.getProgress()) * StageVideoPlayerFragment.this.playbackController.getDurationMillis()) / ((long) seekBar.getMax());
                        PrimeVideoPlayer primeVideoPlayer2 = StageVideoPlayerFragment.this.videoPlayer;
                        if (primeVideoPlayer2 != null) {
                            primeVideoPlayer2.trackPlayAssetForPosition(progress2);
                        }
                        StageVideoPlayerFragment.this.updateVideoPreviewWindow(progress2);
                    }
                }

                @Override // com.amazon.mp3.view.BauhausSeekBarWidget.TrackTouchListener
                public void onStopTrackingTouch() {
                    StageVideoPlayerFragment.this.getVideoPreviewWindow().hide();
                    if (StageVideoPlayerFragment.this.playbackController.getPlayStatus() == PlayStatus.RENDERING) {
                        StageVideoPlayerFragment.this.fadeInUserControls(true);
                    }
                }
            });
        }
        if (!isTrickPlaySupported() || (primeVideoPlayer = this.videoPlayer) == null) {
            return;
        }
        primeVideoPlayer.setTrickPlayListener(new PrimeVideoPlayer.TrickPlayListener() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.setupViews.5
            @Override // com.amazon.music.media.playback.player.impl.PrimeVideoPlayer.TrickPlayListener
            public void didReceiveTrickPlayImage(Drawable image) {
                Intrinsics.checkNotNullParameter(image, "image");
                StageVideoPlayerFragment.this.getVideoPreviewWindow().updateImage(image);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-22$lambda-21, reason: not valid java name */
    public static final void m1436setupViews$lambda22$lambda21(StageVideoPlayerFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handlePlayAreaTap();
    }

    private final void setupTrickPlay() {
        BauhausSeekBarWidget landscapeSeekBar;
        if (isTrickPlaySupported()) {
            getVideoPreviewWindow().initialize(getViewRoot());
            getVideoPreviewWindow().setVisibility(0);
            if (!ScreenUtil.isPortrait() || (landscapeSeekBar = getPortraitSeekBar()) == null) {
                landscapeSeekBar = getLandscapeSeekBar();
            }
            this.videoPreviewSeekBarTracker = new VideoPreviewSeekBarTracker(landscapeSeekBar);
        }
    }

    private final void handlePlayAreaTap() {
        if (this.currentControllerVisibility) {
            fadeOutUserControls();
        } else {
            fadeInUserControls(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void fadeInUserControls(boolean fadeOut) {
        this.currentControllerVisibility = true;
        this.hasShownControlsOnce = true;
        clearUserControlsAnimation();
        toggleUserControls(0);
        if (fadeOut) {
            this.viewAnimationSubscription = Observable.timer(CoroutineLiveDataKt.DEFAULT_TIMEOUT, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$$ExternalSyntheticLambda8
                @Override // rx.functions.Action1
                public final void call(Object obj) {
                    StageVideoPlayerFragment.m1428fadeInUserControls$lambda23(this.f$0, (Long) obj);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: fadeInUserControls$lambda-23, reason: not valid java name */
    public static final void m1428fadeInUserControls$lambda23(StageVideoPlayerFragment this$0, Long l) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.fadeOutUserControls();
    }

    private final void fadeOutUserControls() {
        this.currentControllerVisibility = false;
        toggleUserControls(4);
    }

    private final void toggleUserControls(int visibility) {
        ViewExtensions.fadeAndToggleViewVisibility(getOverlayView(), visibility, 400L);
        if (ScreenUtil.isPortrait()) {
            PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
            boolean z = false;
            if (primeVideoPlayer != null && primeVideoPlayer.hasCaptions()) {
                z = true;
            }
            if (z) {
                getPortraitCaptionsButton().setEnabled(true);
                ViewExtensions.fadeAndToggleViewVisibility(getPortraitCaptionsButton(), visibility, 400L);
            } else {
                ViewExtensions.fadeAndToggleViewVisibility(getPortraitCaptionsButton(), 4, 400L);
            }
            ViewExtensions.fadeAndToggleViewVisibility(getPortraitExpandContractButton(), visibility, 400L);
            return;
        }
        Iterator<T> it = getLandscapeViews().iterator();
        while (it.hasNext()) {
            ViewExtensions.fadeAndToggleViewVisibility((View) it.next(), visibility, 400L);
        }
    }

    private final void clearUserControlsAnimation() {
        Subscription subscription = this.viewAnimationSubscription;
        if (subscription != null) {
            boolean z = false;
            if (subscription != null && !subscription.isUnsubscribed()) {
                z = true;
            }
            if (z) {
                Subscription subscription2 = this.viewAnimationSubscription;
                if (subscription2 != null) {
                    subscription2.unsubscribe();
                }
                this.viewAnimationSubscription = null;
            }
        }
    }

    private final void setupPipView() {
        getLandscapeAudioVideoToggleView().setVisibility(8);
        getLandscapeControlTipAndBottomButtons().setVisibility(8);
        getPortraitCaptionsButton().setVisibility(8);
        getPortraitExpandContractButton().setVisibility(8);
    }

    private final void handleUserControlsVisibility() {
        if (VideoUtil.INSTANCE.isInPictureInPictureMode(getActivity())) {
            hideUserControlsForPip();
        }
    }

    private final void hideUserControlsForPip() {
        this.currentControllerVisibility = false;
        getOverlayView().setVisibility(4);
        toggleLandscapeViews(4);
        getPortraitCaptionsButton().setVisibility(4);
        getPortraitExpandContractButton().setVisibility(4);
    }

    @Override // androidx.fragment.app.Fragment
    public void onPictureInPictureModeChanged(boolean isInPictureInPictureMode) {
        super.onPictureInPictureModeChanged(isInPictureInPictureMode);
        setupCaptions();
        if (isInPictureInPictureMode) {
            hideUserControlsForPip();
        }
    }

    public final void addLandscapeAudioVideoToggle() {
        View toggleView = getAudioVideoToggleInteractor().getToggleView();
        ViewExtensions.removeFromParent(toggleView);
        getLandscapeAudioVideoToggleView().addView(toggleView);
    }

    private final void startPlayback() {
        initPlayer();
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer == null) {
            return;
        }
        primeVideoPlayer.addOnPlaybackStartedListener(getOnPlaybackStarted());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initPlayer() {
        Context contextRequireContext = requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
        PlayerConfigData playerConfigData = new PlayerConfigData(contextRequireContext, getFeatureConfigs(), new AdditionalPlayerConfigs(true));
        RenderingConfig renderingConfigCreateRenderingConfig = PlayerUtil.INSTANCE.createRenderingConfig(SurfaceHandlingMode.CREATE, getVideoSurfaceView(), null, getViewRoot().getSystemUiVisibility(), true);
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer == null) {
            return;
        }
        primeVideoPlayer.setPlayerConfig(playerConfigData, renderingConfigCreateRenderingConfig);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void initPlayerAndResumePlaybackIfNeeded() {
        initPlayer();
        if (this.shouldResumePlayback) {
            this.shouldResumePlayback = false;
        }
    }

    private final FeatureConfigs getFeatureConfigs() {
        return new FeatureConfigs() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment.getFeatureConfigs.1
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
                return new VideoPreviewAssetFeatureConfig() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$getFeatureConfigs$1$videoPreviewAsset$1
                    @Override // com.amazon.video.sdk.player.videopreviewasset.VideoPreviewAssetFeatureConfig
                    public boolean getPreloadAssets() {
                        return true;
                    }
                };
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupCaptions() {
        VideoUtil videoUtil = VideoUtil.INSTANCE;
        if (videoUtil.isInPictureInPictureMode(getActivity())) {
            return;
        }
        BaseButton portraitCaptionsButton = ScreenUtil.isPortrait() ? getPortraitCaptionsButton() : getLandscapeCaptionsButton();
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        boolean z = false;
        boolean z2 = primeVideoPlayer != null && primeVideoPlayer.hasCaptions();
        portraitCaptionsButton.setEnabled(z2);
        if (z2) {
            portraitCaptionsButton.setVisibility(0);
            PrimeVideoPlayer primeVideoPlayer2 = this.videoPlayer;
            if (primeVideoPlayer2 != null) {
                if (getVideoPlayerStateProvider().getIsCaptionsEnabled() && !videoUtil.isInPictureInPictureMode(getActivity())) {
                    z = true;
                }
                primeVideoPlayer2.showCaptions(z);
            }
            portraitCaptionsButton.setActive(getVideoPlayerStateProvider().getIsCaptionsEnabled());
            getLandscapeBottomButtons().updateButtonVisibility(StageBottomIconType.CLOSED_CAPTIONS, true);
            return;
        }
        portraitCaptionsButton.setVisibility(8);
    }

    private final void setCaptionsEnabled(boolean enabled) {
        PrimeVideoPlayer primeVideoPlayer = this.videoPlayer;
        if (primeVideoPlayer != null) {
            primeVideoPlayer.showCaptions(enabled);
        }
        (ScreenUtil.isPortrait() ? getPortraitCaptionsButton() : getLandscapeCaptionsButton()).setActive(getVideoPlayerStateProvider().getIsCaptionsEnabled());
        Log.info(TAG, Intrinsics.stringPlus("CC ", enabled ? "Enabled" : "Disabled"));
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

    private final void expandContractButtonClicked() {
        Activity activity;
        ActionType actionType;
        String nowPlayingMediaItemAsin = MediaPlaybackUtil.getNowPlayingMediaItemAsin();
        if (ScreenUtil.isLandscape()) {
            Context context = getContext();
            activity = context instanceof Activity ? (Activity) context : null;
            if (activity != null) {
                activity.setRequestedOrientation(1);
            }
            actionType = ActionType.SELECT_PORTRAIT_MODE;
        } else {
            Context context2 = getContext();
            activity = context2 instanceof Activity ? (Activity) context2 : null;
            if (activity != null) {
                activity.setRequestedOrientation(0);
            }
            actionType = ActionType.SELECT_LANDSCAPE_MODE;
        }
        ActionType actionType2 = actionType;
        if (nowPlayingMediaItemAsin == null) {
            return;
        }
        VideoUtil.sendUiClickMetric$default(VideoUtil.INSTANCE, nowPlayingMediaItemAsin, actionType2, null, PageType.NOW_PLAYING, null, PageSubType.ONDEMAND_VIDEO, 20, null);
    }

    public final void updateVideoPreviewWindow(long currentTimeMs) {
        int bottom;
        VideoPreviewSeekBarTracker videoPreviewSeekBarTracker = this.videoPreviewSeekBarTracker;
        Point thumbScreenCoords = videoPreviewSeekBarTracker == null ? null : videoPreviewSeekBarTracker.getThumbScreenCoords();
        if (thumbScreenCoords != null) {
            getViewRoot().getLocationOnScreen(new int[]{0, 0});
            if (ScreenUtil.isPortrait()) {
                bottom = getViewRoot().getBottom();
            } else {
                bottom = thumbScreenCoords.y;
            }
            getVideoPreviewWindow().move(thumbScreenCoords.x, bottom);
        }
        getVideoPreviewWindow().updateText(DurationUtils.format(currentTimeMs, DurationUtils.getSuggestedFormat(currentTimeMs), new StringBuilder(), true));
        getVideoPreviewWindow().show();
    }

    @SuppressLint({"NewApi"})
    private final void enterPIPMode() {
        View videoSurfaceView = (SurfaceView) SequencesKt___SequencesKt.firstOrNull(SequencesKt___SequencesKt.filter(ViewGroupKt.getChildren(getVideoSurfaceView()), new Function1<Object, Boolean>() { // from class: com.amazon.mp3.playback.fragment.StageVideoPlayerFragment$enterPIPMode$$inlined$filterIsInstance$1
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

    private final void setImmersiveMode(boolean enabled) {
        FragmentActivity activity = getActivity();
        if (activity == null) {
            return;
        }
        ActivityKt.setImmersiveMode(activity, enabled);
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

    /* JADX INFO: Access modifiers changed from: private */
    public final void updateAll() {
        updateSeekBar();
        updateHeader();
    }

    private final void updateSeekBar() {
        if (ScreenUtil.isLandscape()) {
            getLandscapeSeekBar().updateProgressBar();
            getLandscapeSeekBar().updateSeekBar();
        }
    }

    private final void updateHeader() {
        if (ScreenUtil.isLandscape()) {
            MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
            this.mediaItem = currentMediaItem;
            if (currentMediaItem != null) {
                PlayerMetadataView landscapePlayerMetadataView = getLandscapePlayerMetadataView();
                MediaItem mediaItem = this.mediaItem;
                String name = mediaItem == null ? null : mediaItem.getName();
                MediaItem mediaItem2 = this.mediaItem;
                landscapePlayerMetadataView.setText(name, mediaItem2 == null ? null : mediaItem2.getArtistName(), null);
                getLandscapePlayerMetadataView().displayLoadingView(false);
            }
        }
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

    private final void navigateToLauncherTask(Context appContext) {
        ActivityManager.AppTask next;
        Object systemService = appContext.getSystemService(MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME);
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.app.ActivityManager");
        }
        List<ActivityManager.AppTask> appTasks = ((ActivityManager) systemService).getAppTasks();
        Intrinsics.checkNotNullExpressionValue(appTasks, "activityManager.appTasks");
        Iterator<ActivityManager.AppTask> it = appTasks.iterator();
        while (it.hasNext() && (next = it.next()) != null) {
            ActivityManager.RecentTaskInfo taskInfo = next.getTaskInfo();
            if (taskInfo != null) {
                Intent intent = taskInfo.baseIntent;
                Intrinsics.checkNotNullExpressionValue(intent, "info.baseIntent");
                Set<String> categories = intent.getCategories();
                if (categories != null && categories.contains("android.intent.category.LAUNCHER")) {
                    next.moveToFront();
                    return;
                }
            }
        }
    }

    public final void sendUiPageViewMetrics(boolean isVideoOn, String asin) {
        Intrinsics.checkNotNullParameter(asin, "asin");
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(PageType.NOW_PLAYING_STAGE.getMetricsValue()).withPageSubType(PageSubType.ONDEMAND_VIDEO.getMetricsValue()).withDetailPageItemId(asin).withDetailPageItemIdType(EntityIdType.ASIN.getMetricsValue()).withViewType(ScreenUtil.getScreenViewType(getContext())).build());
    }

    /* JADX INFO: compiled from: StageVideoPlayerFragment.kt */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0010\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n \t*\u0004\u0018\u00010\b0\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/playback/fragment/StageVideoPlayerFragment$Companion;", "", "()V", "ANIMATION_DURATION", "", "SWIPE_MIN_DISTANCE", "", "TAG", "", "kotlin.jvm.PlatformType", "newInstance", "Lcom/amazon/mp3/playback/fragment/StageVideoPlayerFragment;", "extras", "Landroid/os/Bundle;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final StageVideoPlayerFragment newInstance(Bundle extras) {
            StageVideoPlayerFragment stageVideoPlayerFragment = new StageVideoPlayerFragment();
            stageVideoPlayerFragment.setArguments(extras);
            return stageVideoPlayerFragment;
        }
    }
}
