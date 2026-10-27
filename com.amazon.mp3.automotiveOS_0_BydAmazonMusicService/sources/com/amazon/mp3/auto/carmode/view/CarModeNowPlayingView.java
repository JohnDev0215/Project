package com.amazon.mp3.auto.carmode.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.CarModeAlexaFabViewController;
import com.amazon.mp3.auto.carmode.CarModePresetsUtility;
import com.amazon.mp3.auto.carmode.CarModeUtility;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.sequencer.CloudQueueSequencer;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.likes.LikesServiceProxy;
import com.amazon.mp3.playback.PlayerNavigationListener;
import com.amazon.mp3.playback.PlayerVerticalSwipeListener;
import com.amazon.mp3.playback.PlayerViewModel;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.playback.view.OnPlayerClickListener;
import com.amazon.mp3.playback.view.PlayerContentView;
import com.amazon.mp3.playback.view.PlayerView;
import com.amazon.mp3.playback.view.PlayerViewConfig;
import com.amazon.mp3.util.BitmapUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.StringUtil;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.music.find.utils.PageUriUtils;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.RepeatMode;
import com.amazon.music.media.playback.ratings.LikeState;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.BlurStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.media.playback.Playback;
import com.amazon.podcast.media.playback.PlaybackSpeed;
import com.amazon.ui.foundations.styles.ButtonStyle;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.BitmapEffectUtils;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeNowPlayingView.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000þ\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b%\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 í\u00012\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006:\u0002í\u0001B\u001d\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\n\u0010ê\u0001\u001a\u0005\u0018\u00010é\u0001¢\u0006\u0006\bë\u0001\u0010ì\u0001J\u0010\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\b\u0010\u000b\u001a\u00020\tH\u0002J\b\u0010\f\u001a\u00020\tH\u0002J\b\u0010\r\u001a\u00020\tH\u0002J\b\u0010\u000e\u001a\u00020\tH\u0002J\b\u0010\u000f\u001a\u00020\tH\u0002J\u001a\u0010\u0014\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0013\u001a\u00020\u0012H\u0002J\b\u0010\u0015\u001a\u00020\tH\u0002J\u0012\u0010\u0019\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\u0012\u0010\u001a\u001a\u00020\u00182\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0002J\b\u0010\u001b\u001a\u00020\tH\u0002J\b\u0010\u001c\u001a\u00020\tH\u0002J\b\u0010\u001d\u001a\u00020\tH\u0002J\b\u0010\u001e\u001a\u00020\tH\u0002J\b\u0010\u001f\u001a\u00020\tH\u0002J\b\u0010 \u001a\u00020\tH\u0002J\b\u0010!\u001a\u00020\tH\u0002J\b\u0010\"\u001a\u00020\tH\u0002J\b\u0010#\u001a\u00020\tH\u0002J\u001a\u0010&\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u00102\u0006\u0010%\u001a\u00020\u0018H\u0002J\b\u0010'\u001a\u00020\u0018H\u0002J\b\u0010(\u001a\u00020\tH\u0002J\b\u0010)\u001a\u00020\tH\u0002J\u0010\u0010,\u001a\u00020\t2\u0006\u0010+\u001a\u00020*H\u0002J\b\u0010-\u001a\u00020\tH\u0002J\b\u0010.\u001a\u00020\tH\u0002J\b\u0010/\u001a\u00020\tH\u0002J\b\u00100\u001a\u00020\tH\u0002J\b\u00101\u001a\u00020\tH\u0002J\u000e\u00104\u001a\u00020\t2\u0006\u00103\u001a\u000202J\u0010\u00107\u001a\u00020\t2\u0006\u00106\u001a\u000205H\u0016J\u0010\u0010:\u001a\u00020\t2\u0006\u00109\u001a\u000208H\u0016J\b\u0010;\u001a\u00020\tH\u0016J\u0012\u0010>\u001a\u00020\t2\b\u0010=\u001a\u0004\u0018\u00010<H\u0016J\u0012\u0010A\u001a\u00020\t2\b\u0010@\u001a\u0004\u0018\u00010?H\u0016J\u0012\u0010D\u001a\u00020\t2\b\u0010C\u001a\u0004\u0018\u00010BH\u0016J\u0012\u0010G\u001a\u00020\t2\b\u0010F\u001a\u0004\u0018\u00010EH\u0016J\u0012\u0010H\u001a\u00020\t2\b\u0010F\u001a\u0004\u0018\u00010EH\u0016J\u0010\u0010J\u001a\u00020\t2\u0006\u0010I\u001a\u00020\u0018H\u0016J\b\u0010K\u001a\u00020\u0003H\u0016J\b\u0010L\u001a\u00020\u0003H\u0016J\b\u0010M\u001a\u00020\u0003H\u0016J\u0010\u0010O\u001a\u00020\t2\u0006\u0010N\u001a\u00020\u0018H\u0016J\u0012\u0010P\u001a\u00020\t2\b\u0010F\u001a\u0004\u0018\u00010EH\u0016J\u0010\u0010R\u001a\u00020\t2\u0006\u0010Q\u001a\u00020\u0018H\u0016J\u0010\u0010U\u001a\u00020\t2\u0006\u0010T\u001a\u00020SH\u0016J\u001a\u0010Y\u001a\u00020\t2\b\u0010W\u001a\u0004\u0018\u00010V2\b\u0010X\u001a\u0004\u0018\u00010VJ\n\u0010Z\u001a\u0004\u0018\u00010SH\u0016J\b\u0010[\u001a\u00020\tH\u0016J\u0010\u0010^\u001a\u00020\t2\u0006\u0010]\u001a\u00020\\H\u0016J\u0010\u0010`\u001a\u00020\t2\u0006\u0010_\u001a\u00020\u0018H\u0016J\u0010\u0010b\u001a\u00020\t2\u0006\u0010a\u001a\u000208H\u0016J\u0012\u0010e\u001a\u00020\t2\b\u0010d\u001a\u0004\u0018\u00010cH\u0016J\u0012\u0010f\u001a\u00020\t2\b\u0010W\u001a\u0004\u0018\u00010VH\u0016J\u0012\u0010g\u001a\u00020\t2\b\u0010X\u001a\u0004\u0018\u00010VH\u0016J\u0012\u0010j\u001a\u00020\t2\b\u0010i\u001a\u0004\u0018\u00010hH\u0016J\u0012\u0010k\u001a\u00020\t2\b\u0010F\u001a\u0004\u0018\u00010EH\u0016J\b\u0010l\u001a\u00020\tH\u0016J\u0010\u0010n\u001a\u00020\t2\u0006\u0010m\u001a\u00020\u0018H\u0016J\u0010\u0010p\u001a\u00020\t2\u0006\u0010o\u001a\u000205H\u0016J\u0010\u0010r\u001a\u00020\t2\u0006\u0010q\u001a\u00020\u0018H\u0016J\u0010\u0010t\u001a\u00020\t2\u0006\u0010s\u001a\u000208H\u0016J\u0010\u0010v\u001a\u00020\t2\u0006\u0010u\u001a\u000205H\u0016J\b\u0010w\u001a\u00020\tH\u0014J\b\u0010x\u001a\u00020\tH\u0014J\u0012\u0010z\u001a\u00020\t2\b\u0010y\u001a\u0004\u0018\u00010VH\u0016J\u0006\u0010{\u001a\u00020\tR\u0018\u0010T\u001a\u0004\u0018\u00010S8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\bT\u0010|R\u0018\u0010}\u001a\u0004\u0018\u00010<8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R\u0018\u0010@\u001a\u0004\u0018\u00010?8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b@\u0010\u007fR\u0019\u00103\u001a\u0004\u0018\u0001028\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b3\u0010\u0080\u0001R\u0018\u0010\u0082\u0001\u001a\u00030\u0081\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0082\u0001\u0010\u0083\u0001R!\u0010\u0089\u0001\u001a\u00030\u0084\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R!\u0010\u008e\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u008b\u0001\u0010\u0086\u0001\u001a\u0006\b\u008c\u0001\u0010\u008d\u0001R!\u0010\u0091\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u008f\u0001\u0010\u0086\u0001\u001a\u0006\b\u0090\u0001\u0010\u008d\u0001R!\u0010\u0094\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0092\u0001\u0010\u0086\u0001\u001a\u0006\b\u0093\u0001\u0010\u008d\u0001R!\u0010\u0097\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0095\u0001\u0010\u0086\u0001\u001a\u0006\b\u0096\u0001\u0010\u008d\u0001R!\u0010\u009a\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0098\u0001\u0010\u0086\u0001\u001a\u0006\b\u0099\u0001\u0010\u008d\u0001R!\u0010\u009d\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009b\u0001\u0010\u0086\u0001\u001a\u0006\b\u009c\u0001\u0010\u008d\u0001R!\u0010 \u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u009e\u0001\u0010\u0086\u0001\u001a\u0006\b\u009f\u0001\u0010\u008d\u0001R!\u0010£\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¡\u0001\u0010\u0086\u0001\u001a\u0006\b¢\u0001\u0010\u008d\u0001R!\u0010¦\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¤\u0001\u0010\u0086\u0001\u001a\u0006\b¥\u0001\u0010\u008d\u0001R!\u0010©\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b§\u0001\u0010\u0086\u0001\u001a\u0006\b¨\u0001\u0010\u008d\u0001R!\u0010¬\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bª\u0001\u0010\u0086\u0001\u001a\u0006\b«\u0001\u0010\u008d\u0001R!\u0010¯\u0001\u001a\u00030\u008a\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u00ad\u0001\u0010\u0086\u0001\u001a\u0006\b®\u0001\u0010\u008d\u0001R!\u0010´\u0001\u001a\u00030°\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b±\u0001\u0010\u0086\u0001\u001a\u0006\b²\u0001\u0010³\u0001R!\u0010·\u0001\u001a\u00030°\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bµ\u0001\u0010\u0086\u0001\u001a\u0006\b¶\u0001\u0010³\u0001R!\u0010º\u0001\u001a\u00030°\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¸\u0001\u0010\u0086\u0001\u001a\u0006\b¹\u0001\u0010³\u0001R!\u0010½\u0001\u001a\u00030°\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b»\u0001\u0010\u0086\u0001\u001a\u0006\b¼\u0001\u0010³\u0001R!\u0010À\u0001\u001a\u00030°\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b¾\u0001\u0010\u0086\u0001\u001a\u0006\b¿\u0001\u0010³\u0001R!\u0010Å\u0001\u001a\u00030Á\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÂ\u0001\u0010\u0086\u0001\u001a\u0006\bÃ\u0001\u0010Ä\u0001R!\u0010È\u0001\u001a\u00030Á\u00018BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\bÆ\u0001\u0010\u0086\u0001\u001a\u0006\bÇ\u0001\u0010Ä\u0001R\u0017\u0010_\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\b_\u0010É\u0001R\u0017\u0010I\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bI\u0010É\u0001R\u0017\u0010m\u001a\u00020\u00188\u0002@\u0002X\u0082\u000e¢\u0006\u0007\n\u0005\bm\u0010É\u0001R\u001c\u0010Ë\u0001\u001a\u0005\u0018\u00010Ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bË\u0001\u0010Ì\u0001R\u001c\u0010Í\u0001\u001a\u0005\u0018\u00010Ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÍ\u0001\u0010Ì\u0001R\u001c\u0010Î\u0001\u001a\u0005\u0018\u00010Ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÎ\u0001\u0010Ì\u0001R\u001c\u0010Ï\u0001\u001a\u0005\u0018\u00010Ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÏ\u0001\u0010Ì\u0001R\u001c\u0010Ð\u0001\u001a\u0005\u0018\u00010Ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÐ\u0001\u0010Ì\u0001R\u001c\u0010Ò\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÒ\u0001\u0010Ó\u0001R\u001c\u0010Ô\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÔ\u0001\u0010Ó\u0001R\u001c\u0010Õ\u0001\u001a\u0005\u0018\u00010Ñ\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÕ\u0001\u0010Ó\u0001R\u001c\u0010Ö\u0001\u001a\u0005\u0018\u00010Ê\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÖ\u0001\u0010Ì\u0001R\u001c\u0010Ø\u0001\u001a\u0005\u0018\u00010×\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bØ\u0001\u0010Ù\u0001R\u001b\u0010Ú\u0001\u001a\u0004\u0018\u00010E8\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bÚ\u0001\u0010Û\u0001R\u0018\u0010Ý\u0001\u001a\u00030Ü\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\bÝ\u0001\u0010Þ\u0001R)\u0010ß\u0001\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bß\u0001\u0010É\u0001\u001a\u0006\bà\u0001\u0010á\u0001\"\u0006\bâ\u0001\u0010ã\u0001R)\u0010ä\u0001\u001a\u00020\u00188\u0006@\u0006X\u0086\u000e¢\u0006\u0018\n\u0006\bä\u0001\u0010É\u0001\u001a\u0006\bå\u0001\u0010á\u0001\"\u0006\bæ\u0001\u0010ã\u0001R\u0019\u0010ç\u0001\u001a\u0002088\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\bç\u0001\u0010è\u0001¨\u0006î\u0001"}, d2 = {"Lcom/amazon/mp3/auto/carmode/view/CarModeNowPlayingView;", "Landroid/widget/FrameLayout;", "Lcom/amazon/mp3/playback/view/PlayerView;", "Lcom/amazon/mp3/playback/view/PlayerContentView;", "Lcom/amazon/podcast/media/playback/Playback$StateCallback;", "Lcom/amazon/podcast/media/playback/Playback$PositionCallback;", "Lcom/amazon/podcast/media/playback/Playback$PlaybackEventCallback;", "Landroid/content/Context;", "context", "", "initView", "stylizeViews", "initializeLikeButton", "setProgressBar", "attachClickListeners", "onBackPressed", "Landroid/view/View;", "button", "Landroid/view/View$OnClickListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setButtonViewOnClickListener", PageUriUtils.DO_PLAY_VALUE, "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/InteractionType;", AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE, "", "next", "prev", "shuffle", "repeat", "thumbsUp", "rewind", "forward", "displayPlayQueue", "displayPodcastEpisodeList", "updatePlayQueueButton", "updateLikeButton", "view", "visible", "setViewVisibility", "isPlayQueueAvailable", "initPlaybackSpeed", "cyclePlaybackSpeeds", "Lcom/amazon/podcast/media/playback/PlaybackSpeed;", "speed", "updatePlaybackSpeedText", "showPauseIcon", "showPlayIcon", "setPodcastButtonLayout", "setMusicButtonLayout", "attachSwipeListener", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "setStyleSheet", "", "rating", "ratingChanged", "", "duration", "updateDuration", "animateToPrev", "Lcom/amazon/mp3/playback/view/OnPlayerClickListener;", "onPlayerClickListener", "addPlayerClickListener", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "playerNavigationListener", "addPlayerNavigationListener", "Lcom/amazon/mp3/playback/PlayerVerticalSwipeListener;", "playerVerticalSwipeListener", "addPlayerVerticalSwipeListener", "Landroid/graphics/Bitmap;", "bitmap", "setNextBitmap", "setCurrentBitmap", "hasPrev", "hasPrevTrack", "getPrevContentView", "getCurrentContentView", "getNextContentView", "connected", "castingStateChanged", "setPrevBitmap", "shuffled", "shuffleStateChanged", "Lcom/amazon/mp3/playback/view/PlayerViewConfig;", "playerViewConfig", "updatePlaybackControl", "", "title", MediaTrack.ROLE_SUBTITLE, "updatePlayerView", "getPlayerViewConfig", "animateToNext", "Lcom/amazon/music/media/playback/RepeatMode;", "repeatMode", "repeatStateChanged", "hasNext", "hasNextTrack", NotificationCompat.CATEGORY_PROGRESS, "updateProgress", "Lcom/amazon/mp3/playback/PlayerViewModel$PlayerState;", "playerState", "setPlayerState", "setTitle", "setSubtitle", "Landroid/graphics/drawable/Drawable;", "drawable", "setArtworkDrawable", "setArtworkBitmap", "setDefaultArtwork", "isLoading", "setLoading", "visibility", "setMinimizeViewVisibility", "added", "addedToLibrary", "position", "onPositionChange", "state", "onPlaybackStateChange", "onAttachedToWindow", "onDetachedFromWindow", "playbackEvent", "onPlaybackEventReceived", "clear", "Lcom/amazon/mp3/playback/view/PlayerViewConfig;", "playerClickListener", "Lcom/amazon/mp3/playback/view/OnPlayerClickListener;", "Lcom/amazon/mp3/playback/PlayerNavigationListener;", "Lcom/amazon/music/views/library/styles/StyleSheet;", "Lcom/amazon/mp3/view/TouchEventHandler;", "touchEventHandler", "Lcom/amazon/mp3/view/TouchEventHandler;", "Landroid/widget/ProgressBar;", "progressBar$delegate", "Lkotlin/Lazy;", "getProgressBar", "()Landroid/widget/ProgressBar;", "progressBar", "Lcom/amazon/ui/foundations/views/BaseButton;", "playButton$delegate", "getPlayButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "playButton", "nextButton$delegate", "getNextButton", "nextButton", "prevButton$delegate", "getPrevButton", "prevButton", "repeatButton$delegate", "getRepeatButton", "repeatButton", "shuffleButton$delegate", "getShuffleButton", "shuffleButton", "thumbsUpButton$delegate", "getThumbsUpButton", "thumbsUpButton", "rewindButton$delegate", "getRewindButton", "rewindButton", "forwardButton$delegate", "getForwardButton", "forwardButton", "closeButton$delegate", "getCloseButton", "closeButton", "alexaButton$delegate", "getAlexaButton", "alexaButton", "playQueueButton$delegate", "getPlayQueueButton", "playQueueButton", "podcastEpisodeListButton$delegate", "getPodcastEpisodeListButton", "podcastEpisodeListButton", "Landroid/widget/TextView;", "playbackSpeedControl$delegate", "getPlaybackSpeedControl", "()Landroid/widget/TextView;", "playbackSpeedControl", "progressTime$delegate", "getProgressTime", "progressTime", "remainingTime$delegate", "getRemainingTime", "remainingTime", "titleTextView$delegate", "getTitleTextView", "titleTextView", "subtitleTextView$delegate", "getSubtitleTextView", "subtitleTextView", "Landroid/widget/ImageView;", "npsBackgroundImageView$delegate", "getNpsBackgroundImageView", "()Landroid/widget/ImageView;", "npsBackgroundImageView", "npsArtwork$delegate", "getNpsArtwork", "npsArtwork", "Z", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "largeGlassIconBuilder", "Lcom/amazon/ui/foundations/views/BaseButton$StyleBuilder;", "hugePrimaryIconBuilder", "hugeAccentIconBuilder", "hugeGlassIconBuilder", "epicGlassIconBuilder", "Lcom/amazon/ui/foundations/styles/FontStyle;", "defaultSpeedFontStyle", "Lcom/amazon/ui/foundations/styles/FontStyle;", "nonDefaultSpeedFontStyle", "primaryT3FontStyle", "smallIconBuilder", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "alexaFabViewController", "Lcom/amazon/mp3/auto/CarModeAlexaFabViewController;", "currentBitmap", "Landroid/graphics/Bitmap;", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "enablePlayQueue", "getEnablePlayQueue", "()Z", "setEnablePlayQueue", "(Z)V", "enablePodcastEpisodeList", "getEnablePodcastEpisodeList", "setEnablePodcastEpisodeList", "playbackDuration", "J", "Landroid/util/AttributeSet;", "attributeSet", "<init>", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeNowPlayingView extends FrameLayout implements PlayerView, PlayerContentView, Playback.StateCallback, Playback.PositionCallback, Playback.PlaybackEventCallback {
    private static final String TAG = CarModeNowPlayingView.class.getSimpleName();

    /* JADX INFO: renamed from: alexaButton$delegate, reason: from kotlin metadata */
    private final Lazy alexaButton;
    private CarModeAlexaFabViewController alexaFabViewController;

    /* JADX INFO: renamed from: closeButton$delegate, reason: from kotlin metadata */
    private final Lazy closeButton;
    private Bitmap currentBitmap;
    private FontStyle defaultSpeedFontStyle;
    private boolean enablePlayQueue;
    private boolean enablePodcastEpisodeList;
    private BaseButton.StyleBuilder epicGlassIconBuilder;

    /* JADX INFO: renamed from: forwardButton$delegate, reason: from kotlin metadata */
    private final Lazy forwardButton;
    private boolean hasNext;
    private boolean hasPrev;
    private BaseButton.StyleBuilder hugeAccentIconBuilder;
    private BaseButton.StyleBuilder hugeGlassIconBuilder;
    private BaseButton.StyleBuilder hugePrimaryIconBuilder;
    private boolean isLoading;
    private BaseButton.StyleBuilder largeGlassIconBuilder;

    /* JADX INFO: renamed from: nextButton$delegate, reason: from kotlin metadata */
    private final Lazy nextButton;
    private FontStyle nonDefaultSpeedFontStyle;

    /* JADX INFO: renamed from: npsArtwork$delegate, reason: from kotlin metadata */
    private final Lazy npsArtwork;

    /* JADX INFO: renamed from: npsBackgroundImageView$delegate, reason: from kotlin metadata */
    private final Lazy npsBackgroundImageView;

    /* JADX INFO: renamed from: playButton$delegate, reason: from kotlin metadata */
    private final Lazy playButton;

    /* JADX INFO: renamed from: playQueueButton$delegate, reason: from kotlin metadata */
    private final Lazy playQueueButton;
    private final ActionValidatedPlaybackController playbackController;
    private long playbackDuration;

    /* JADX INFO: renamed from: playbackSpeedControl$delegate, reason: from kotlin metadata */
    private final Lazy playbackSpeedControl;
    private OnPlayerClickListener playerClickListener;
    private PlayerNavigationListener playerNavigationListener;
    private PlayerViewConfig playerViewConfig;

    /* JADX INFO: renamed from: podcastEpisodeListButton$delegate, reason: from kotlin metadata */
    private final Lazy podcastEpisodeListButton;

    /* JADX INFO: renamed from: prevButton$delegate, reason: from kotlin metadata */
    private final Lazy prevButton;
    private FontStyle primaryT3FontStyle;

    /* JADX INFO: renamed from: progressBar$delegate, reason: from kotlin metadata */
    private final Lazy progressBar;

    /* JADX INFO: renamed from: progressTime$delegate, reason: from kotlin metadata */
    private final Lazy progressTime;

    /* JADX INFO: renamed from: remainingTime$delegate, reason: from kotlin metadata */
    private final Lazy remainingTime;

    /* JADX INFO: renamed from: repeatButton$delegate, reason: from kotlin metadata */
    private final Lazy repeatButton;

    /* JADX INFO: renamed from: rewindButton$delegate, reason: from kotlin metadata */
    private final Lazy rewindButton;

    /* JADX INFO: renamed from: shuffleButton$delegate, reason: from kotlin metadata */
    private final Lazy shuffleButton;
    private BaseButton.StyleBuilder smallIconBuilder;
    private StyleSheet styleSheet;

    /* JADX INFO: renamed from: subtitleTextView$delegate, reason: from kotlin metadata */
    private final Lazy subtitleTextView;

    /* JADX INFO: renamed from: thumbsUpButton$delegate, reason: from kotlin metadata */
    private final Lazy thumbsUpButton;

    /* JADX INFO: renamed from: titleTextView$delegate, reason: from kotlin metadata */
    private final Lazy titleTextView;
    private final TouchEventHandler touchEventHandler;

    /* JADX INFO: compiled from: CarModeNowPlayingView.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[RepeatMode.values().length];
            iArr[RepeatMode.REPEAT_ONE.ordinal()] = 1;
            iArr[RepeatMode.REPEAT_ALL.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[PlayerViewModel.PlayerState.values().length];
            iArr2[PlayerViewModel.PlayerState.LOADING.ordinal()] = 1;
            iArr2[PlayerViewModel.PlayerState.PAUSED.ordinal()] = 2;
            iArr2[PlayerViewModel.PlayerState.PLAYING.ordinal()] = 3;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerVerticalSwipeListener(PlayerVerticalSwipeListener playerVerticalSwipeListener) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void animateToNext() {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void animateToPrev() {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void castingStateChanged(boolean connected) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerContentView getCurrentContentView() {
        return this;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerContentView getNextContentView() {
        return this;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerContentView getPrevContentView() {
        return this;
    }

    public void setArtworkDrawable(Drawable drawable) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setDefaultArtwork() {
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setMinimizeViewVisibility(int visibility) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setNextBitmap(Bitmap bitmap) {
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setPrevBitmap(Bitmap bitmap) {
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CarModeNowPlayingView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.touchEventHandler = new TouchEventHandler();
        this.progressBar = LazyKt__LazyJVMKt.lazy(new Function0<ProgressBar>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$progressBar$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ProgressBar invoke() {
                return (ProgressBar) this.this$0.findViewById(R.id.car_mode_nps_progress_bar);
            }
        });
        this.playButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$playButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_play_button);
            }
        });
        this.nextButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$nextButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_next_button);
            }
        });
        this.prevButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$prevButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_prev_button);
            }
        });
        this.repeatButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$repeatButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_repeat_button);
            }
        });
        this.shuffleButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$shuffleButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_shuffle_button);
            }
        });
        this.thumbsUpButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$thumbsUpButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_like_button);
            }
        });
        this.rewindButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$rewindButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_rewind_button);
            }
        });
        this.forwardButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$forwardButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_forward_button);
            }
        });
        this.closeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$closeButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_exit_button);
            }
        });
        this.alexaButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$alexaButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_alexa_button);
            }
        });
        this.playQueueButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$playQueueButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_play_queue_button);
            }
        });
        this.podcastEpisodeListButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$podcastEpisodeListButton$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final BaseButton invoke() {
                return (BaseButton) this.this$0.findViewById(R.id.car_mode_nps_podcast_episode_list_button);
            }
        });
        this.playbackSpeedControl = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$playbackSpeedControl$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_nps_playback_speed);
            }
        });
        this.progressTime = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$progressTime$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_nps_progress_time);
            }
        });
        this.remainingTime = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$remainingTime$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_nps_remaining_time);
            }
        });
        this.titleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$titleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_nps_title);
            }
        });
        this.subtitleTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$subtitleTextView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final TextView invoke() {
                return (TextView) this.this$0.findViewById(R.id.car_mode_nps_subtitle);
            }
        });
        this.npsBackgroundImageView = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$npsBackgroundImageView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R.id.car_mode_nps_background);
            }
        });
        this.npsArtwork = LazyKt__LazyJVMKt.lazy(new Function0<ImageView>() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$npsArtwork$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final ImageView invoke() {
                return (ImageView) this.this$0.findViewById(R.id.car_mode_nps_artwork);
            }
        });
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        initView(context);
    }

    private final ProgressBar getProgressBar() {
        Object value = this.progressBar.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-progressBar>(...)");
        return (ProgressBar) value;
    }

    private final BaseButton getPlayButton() {
        Object value = this.playButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getNextButton() {
        Object value = this.nextButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-nextButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getPrevButton() {
        Object value = this.prevButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-prevButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getRepeatButton() {
        Object value = this.repeatButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-repeatButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getShuffleButton() {
        Object value = this.shuffleButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-shuffleButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getThumbsUpButton() {
        Object value = this.thumbsUpButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-thumbsUpButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getRewindButton() {
        Object value = this.rewindButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-rewindButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getForwardButton() {
        Object value = this.forwardButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-forwardButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getCloseButton() {
        Object value = this.closeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-closeButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getAlexaButton() {
        Object value = this.alexaButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-alexaButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getPlayQueueButton() {
        Object value = this.playQueueButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playQueueButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getPodcastEpisodeListButton() {
        Object value = this.podcastEpisodeListButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-podcastEpisodeListButton>(...)");
        return (BaseButton) value;
    }

    private final TextView getPlaybackSpeedControl() {
        Object value = this.playbackSpeedControl.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-playbackSpeedControl>(...)");
        return (TextView) value;
    }

    private final TextView getProgressTime() {
        Object value = this.progressTime.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-progressTime>(...)");
        return (TextView) value;
    }

    private final TextView getRemainingTime() {
        Object value = this.remainingTime.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-remainingTime>(...)");
        return (TextView) value;
    }

    private final TextView getTitleTextView() {
        Object value = this.titleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-titleTextView>(...)");
        return (TextView) value;
    }

    private final TextView getSubtitleTextView() {
        Object value = this.subtitleTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-subtitleTextView>(...)");
        return (TextView) value;
    }

    private final ImageView getNpsBackgroundImageView() {
        Object value = this.npsBackgroundImageView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-npsBackgroundImageView>(...)");
        return (ImageView) value;
    }

    private final ImageView getNpsArtwork() {
        Object value = this.npsArtwork.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-npsArtwork>(...)");
        return (ImageView) value;
    }

    public final boolean getEnablePlayQueue() {
        return this.enablePlayQueue;
    }

    public final void setEnablePlayQueue(boolean z) {
        this.enablePlayQueue = z;
    }

    public final boolean getEnablePodcastEpisodeList() {
        return this.enablePodcastEpisodeList;
    }

    public final void setEnablePodcastEpisodeList(boolean z) {
        this.enablePodcastEpisodeList = z;
    }

    public final void setStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        this.styleSheet = styleSheet;
        stylizeViews();
    }

    private final void initView(Context context) {
        View.inflate(context, R.layout.car_mode_now_playing_view, this);
        setProgressBar();
        attachClickListeners();
        attachSwipeListener();
        stylizeViews();
        this.alexaFabViewController = new CarModeAlexaFabViewController(context, getAlexaButton(), PageType.CAR_MODE_NOW_PLAYING);
    }

    private final void stylizeViews() {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton.StyleBuilder styleBuilder3;
        BaseButton.StyleBuilder styleBuilderWithIcon3;
        BaseButton.StyleBuilder styleBuilder4;
        BaseButton.StyleBuilder styleBuilderWithIcon4;
        BaseButton.StyleBuilder styleBuilder5;
        BaseButton.StyleBuilder styleBuilderWithIcon5;
        BaseButton.StyleBuilder styleBuilder6;
        BaseButton.StyleBuilder styleBuilderWithIcon6;
        BaseButton.StyleBuilder styleBuilder7;
        BaseButton.StyleBuilder styleBuilderWithIcon7;
        BaseButton.StyleBuilder styleBuilder8;
        BaseButton.StyleBuilder styleBuilderWithIcon8;
        BaseButton.StyleBuilder styleBuilder9;
        BaseButton.StyleBuilder styleBuilderWithIcon9;
        BaseButton.StyleBuilder styleBuilder10;
        BaseButton.StyleBuilder styleBuilderWithIcon10;
        BaseButton.StyleBuilder styleBuilder11;
        BaseButton.StyleBuilder styleBuilderWithIcon11;
        FontStyle fontStyle;
        FontStyle fontStyle2;
        StyleSheet styleSheet = this.styleSheet;
        this.largeGlassIconBuilder = styleSheet == null ? null : styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.GLASS);
        StyleSheet styleSheet2 = this.styleSheet;
        this.hugeGlassIconBuilder = styleSheet2 == null ? null : styleSheet2.getIconBuilder(IconSizeKey.HUGE, IconStyleKey.GLASS);
        StyleSheet styleSheet3 = this.styleSheet;
        this.hugePrimaryIconBuilder = styleSheet3 == null ? null : styleSheet3.getIconBuilder(IconSizeKey.HUGE, IconStyleKey.PRIMARY);
        StyleSheet styleSheet4 = this.styleSheet;
        this.hugeAccentIconBuilder = styleSheet4 == null ? null : styleSheet4.getIconBuilder(IconSizeKey.HUGE, IconStyleKey.ACCENT);
        StyleSheet styleSheet5 = this.styleSheet;
        this.epicGlassIconBuilder = styleSheet5 == null ? null : styleSheet5.getIconBuilder(IconSizeKey.EPIC, IconStyleKey.GLASS);
        StyleSheet styleSheet6 = this.styleSheet;
        this.defaultSpeedFontStyle = styleSheet6 == null ? null : styleSheet6.getFontStyle(FontStyleKey.PRIMARY_HEADLINE_3_EMBER_BD);
        StyleSheet styleSheet7 = this.styleSheet;
        this.nonDefaultSpeedFontStyle = styleSheet7 == null ? null : styleSheet7.getFontStyle(FontStyleKey.ACCENT_HEADLINE_3_EMBER_BD);
        StyleSheet styleSheet8 = this.styleSheet;
        this.primaryT3FontStyle = styleSheet8 == null ? null : styleSheet8.getFontStyle(FontStyleKey.PRIMARY_GLASS_T3_BIG_EMBER_RG);
        StyleSheet styleSheet9 = this.styleSheet;
        this.smallIconBuilder = styleSheet9 != null ? styleSheet9.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS) : null;
        initializeLikeButton();
        StyleSheet styleSheet10 = this.styleSheet;
        if (styleSheet10 != null && (fontStyle2 = styleSheet10.getFontStyle(FontStyleKey.HEADLINE_3)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getTitleTextView(), fontStyle2);
        }
        StyleSheet styleSheet11 = this.styleSheet;
        if (styleSheet11 != null && (fontStyle = styleSheet11.getFontStyle(FontStyleKey.PRIMARY_GLASS_T1_HUGE_EMBER_RG)) != null) {
            FontUtil.INSTANCE.applyFontStyle(getSubtitleTextView(), fontStyle);
        }
        BaseButton playButton = getPlayButton();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_play_tray);
        if (drawable != null && (styleBuilder11 = this.epicGlassIconBuilder) != null && (styleBuilderWithIcon11 = styleBuilder11.withIcon(drawable)) != null) {
            styleBuilderWithIcon11.applyStyle(playButton);
        }
        BaseButton nextButton = getNextButton();
        Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_next_tray);
        if (drawable2 != null && (styleBuilder10 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon10 = styleBuilder10.withIcon(drawable2)) != null) {
            styleBuilderWithIcon10.applyStyle(nextButton);
        }
        BaseButton prevButton = getPrevButton();
        Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_previous_tray);
        if (drawable3 != null && (styleBuilder9 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon9 = styleBuilder9.withIcon(drawable3)) != null) {
            styleBuilderWithIcon9.applyStyle(prevButton);
        }
        BaseButton repeatButton = getRepeatButton();
        Drawable drawable4 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_repeat);
        if (drawable4 != null && (styleBuilder8 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon8 = styleBuilder8.withIcon(drawable4)) != null) {
            styleBuilderWithIcon8.applyStyle(repeatButton);
        }
        BaseButton shuffleButton = getShuffleButton();
        Drawable drawable5 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_shuffle);
        if (drawable5 != null && (styleBuilder7 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon7 = styleBuilder7.withIcon(drawable5)) != null) {
            styleBuilderWithIcon7.applyStyle(shuffleButton);
        }
        BaseButton alexaButton = getAlexaButton();
        Drawable drawable6 = ContextCompat.getDrawable(getContext(), R.drawable.ic_alexa_bauhaus);
        if (drawable6 != null && (styleBuilder6 = this.largeGlassIconBuilder) != null && (styleBuilderWithIcon6 = styleBuilder6.withIcon(drawable6)) != null) {
            styleBuilderWithIcon6.applyStyle(alexaButton);
        }
        BaseButton closeButton = getCloseButton();
        Drawable drawable7 = ContextCompat.getDrawable(getContext(), R.drawable.car_mode_caret_down);
        if (drawable7 != null && (styleBuilder5 = this.largeGlassIconBuilder) != null && (styleBuilderWithIcon5 = styleBuilder5.withIcon(drawable7)) != null) {
            styleBuilderWithIcon5.applyStyle(closeButton);
        }
        BaseButton forwardButton = getForwardButton();
        Drawable drawable8 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_fast_forward_mini_player);
        if (drawable8 != null && (styleBuilder4 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon4 = styleBuilder4.withIcon(drawable8)) != null) {
            styleBuilderWithIcon4.applyStyle(forwardButton);
        }
        BaseButton rewindButton = getRewindButton();
        Drawable drawable9 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_rewind);
        if (drawable9 != null && (styleBuilder3 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon3 = styleBuilder3.withIcon(drawable9)) != null) {
            styleBuilderWithIcon3.applyStyle(rewindButton);
        }
        BaseButton podcastEpisodeListButton = getPodcastEpisodeListButton();
        Drawable drawable10 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_playqueue);
        if (drawable10 != null && (styleBuilder2 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(drawable10)) != null) {
            styleBuilderWithIcon2.applyStyle(podcastEpisodeListButton);
        }
        BaseButton playQueueButton = getPlayQueueButton();
        Drawable drawable11 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_playqueue);
        if (drawable11 != null && (styleBuilder = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(drawable11)) != null) {
            styleBuilderWithIcon.applyStyle(playQueueButton);
        }
        initPlaybackSpeed();
        FontStyle fontStyle3 = this.primaryT3FontStyle;
        if (fontStyle3 == null) {
            return;
        }
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(getProgressTime(), fontStyle3);
        fontUtil.applyFontStyle(getRemainingTime(), fontStyle3);
    }

    private final void initializeLikeButton() {
        ButtonStyle iconStyle;
        StyleSheet styleSheet = this.styleSheet;
        BaseButton.StyleBuilder iconBuilder = styleSheet == null ? null : styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.PRIMARY);
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_action_like);
        if (drawable != null && iconBuilder != null) {
            iconBuilder.withIcon(drawable);
        }
        StyleSheet styleSheet2 = this.styleSheet;
        if (styleSheet2 != null && (iconStyle = styleSheet2.getIconStyle(IconStyleKey.ACCENT)) != null && iconBuilder != null) {
            iconBuilder.withActiveStyle(iconStyle);
        }
        if (iconBuilder == null) {
            return;
        }
        iconBuilder.applyStyle(getThumbsUpButton());
    }

    private final void setProgressBar() {
        getProgressBar().setProgressDrawable(ContextCompat.getDrawable(getContext(), R.drawable.car_mode_now_playing_seek_bar_colors));
        getProgressBar().setIndeterminateDrawable(ContextCompat.getDrawable(getContext(), R.drawable.car_mode_now_playing_seek_bar_colors));
    }

    private final void attachClickListeners() {
        setButtonViewOnClickListener(getNextButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m337attachClickListeners$lambda27(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getPlayButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m338attachClickListeners$lambda28(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getPrevButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m339attachClickListeners$lambda29(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getShuffleButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m340attachClickListeners$lambda30(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getRepeatButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m341attachClickListeners$lambda31(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getThumbsUpButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m342attachClickListeners$lambda32(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getCloseButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m343attachClickListeners$lambda33(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getRewindButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m344attachClickListeners$lambda34(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getForwardButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m345attachClickListeners$lambda35(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getPlayQueueButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m346attachClickListeners$lambda36(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getPlaybackSpeedControl(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m347attachClickListeners$lambda37(this.f$0, view);
            }
        });
        setButtonViewOnClickListener(getPodcastEpisodeListButton(), new View.OnClickListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                CarModeNowPlayingView.m348attachClickListeners$lambda38(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-27, reason: not valid java name */
    public static final void m337attachClickListeners$lambda27(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.next(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-28, reason: not valid java name */
    public static final void m338attachClickListeners$lambda28(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.play();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-29, reason: not valid java name */
    public static final void m339attachClickListeners$lambda29(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.prev(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-30, reason: not valid java name */
    public static final void m340attachClickListeners$lambda30(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.shuffle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-31, reason: not valid java name */
    public static final void m341attachClickListeners$lambda31(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.repeat();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-32, reason: not valid java name */
    public static final void m342attachClickListeners$lambda32(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.thumbsUp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-33, reason: not valid java name */
    public static final void m343attachClickListeners$lambda33(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-34, reason: not valid java name */
    public static final void m344attachClickListeners$lambda34(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.rewind();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-35, reason: not valid java name */
    public static final void m345attachClickListeners$lambda35(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.forward();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-36, reason: not valid java name */
    public static final void m346attachClickListeners$lambda36(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.displayPlayQueue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-37, reason: not valid java name */
    public static final void m347attachClickListeners$lambda37(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.cyclePlaybackSpeeds();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: attachClickListeners$lambda-38, reason: not valid java name */
    public static final void m348attachClickListeners$lambda38(CarModeNowPlayingView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.displayPodcastEpisodeList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onBackPressed() {
        Context context = getContext();
        Activity activity = context instanceof Activity ? (Activity) context : null;
        if (activity == null) {
            return;
        }
        activity.onBackPressed();
    }

    private final void setButtonViewOnClickListener(View button, View.OnClickListener listener) {
        if (button == null) {
            return;
        }
        button.setOnClickListener(listener);
    }

    private final void play() {
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onPlayPauseClicked();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean next(InteractionType type) {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return false;
        }
        if (type == null) {
            type = InteractionType.TAP;
        }
        return onPlayerClickListener.onNextClicked(type);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean prev(InteractionType type) {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return false;
        }
        if (type == null) {
            type = InteractionType.TAP;
        }
        return onPlayerClickListener.onPrevClicked(type);
    }

    private final void shuffle() {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onShuffleClicked();
    }

    private final void repeat() {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onRepeatClicked();
    }

    private final void thumbsUp() {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onThumbsUpClicked();
    }

    private final void rewind() {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onRewindClicked();
    }

    private final void forward() {
        OnPlayerClickListener onPlayerClickListener = this.playerClickListener;
        if (onPlayerClickListener == null) {
            return;
        }
        onPlayerClickListener.onForwardClicked();
    }

    private final void displayPlayQueue() {
        PlayerNavigationListener playerNavigationListener = this.playerNavigationListener;
        if (playerNavigationListener == null) {
            return;
        }
        playerNavigationListener.showPlayQueue();
    }

    private final void displayPodcastEpisodeList() {
        PlayerNavigationListener playerNavigationListener = this.playerNavigationListener;
        if (playerNavigationListener == null) {
            return;
        }
        playerNavigationListener.showPodcastEpisodeList();
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void ratingChanged(int rating) {
        if (rating != -1) {
            if (rating == 1) {
                getThumbsUpButton().setActive(true);
            } else {
                getThumbsUpButton().setActive(false);
            }
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updateDuration(long duration) {
        this.playbackDuration = duration;
        getProgressBar().setMax((int) duration);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerClickListener(OnPlayerClickListener onPlayerClickListener) {
        this.playerClickListener = onPlayerClickListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addPlayerNavigationListener(PlayerNavigationListener playerNavigationListener) {
        this.playerNavigationListener = playerNavigationListener;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setCurrentBitmap(Bitmap bitmap) {
        setArtworkBitmap(bitmap);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void hasPrevTrack(boolean hasPrev) {
        this.hasPrev = hasPrev;
        getPrevButton().setEnabled(hasPrev);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void shuffleStateChanged(boolean shuffled) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        if (shuffled) {
            BaseButton shuffleButton = getShuffleButton();
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_shuffle_active);
            if (drawable != null && (styleBuilder2 = this.hugeAccentIconBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(drawable)) != null) {
                styleBuilderWithIcon2.applyStyle(shuffleButton);
            }
        } else {
            BaseButton shuffleButton2 = getShuffleButton();
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_shuffle);
            if (drawable2 != null && (styleBuilder = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(drawable2)) != null) {
                styleBuilderWithIcon.applyStyle(shuffleButton2);
            }
        }
        getShuffleButton().setActivated(shuffled);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updatePlaybackControl(PlayerViewConfig playerViewConfig) {
        Intrinsics.checkNotNullParameter(playerViewConfig, "playerViewConfig");
        setViewVisibility(getNextButton(), playerViewConfig.isShowNext());
        setViewVisibility(getPrevButton(), playerViewConfig.isShowPrevious());
        setViewVisibility(getPlayButton(), playerViewConfig.isShowPlayPause());
        setViewVisibility(getShuffleButton(), playerViewConfig.isShowShuffle());
        setViewVisibility(getRepeatButton(), playerViewConfig.isShowRepeat());
        setViewVisibility(getThumbsUpButton(), playerViewConfig.isShowRating());
        setViewVisibility(getRewindButton(), playerViewConfig.isShowRewind());
        setViewVisibility(getForwardButton(), playerViewConfig.isShowForward());
        setViewVisibility(getPlaybackSpeedControl(), playerViewConfig.isShowSpeedControl());
        if (this.enablePlayQueue) {
            setViewVisibility(getPlayQueueButton(), playerViewConfig.isShowPlayQueue() || playerViewConfig.isMusicPlayQueueAvailable());
            updatePlayQueueButton();
        } else {
            getPlayQueueButton().setVisibility(8);
        }
        if (this.enablePodcastEpisodeList) {
            setViewVisibility(getPodcastEpisodeListButton(), playerViewConfig.isShowPodcastEpisodeList());
        } else {
            getPodcastEpisodeListButton().setVisibility(8);
        }
        if (!Intrinsics.areEqual(this.playerViewConfig, playerViewConfig)) {
            if (playerViewConfig.isPodcastView()) {
                setPodcastButtonLayout();
            } else {
                setMusicButtonLayout();
            }
        }
        this.playerViewConfig = playerViewConfig;
    }

    public final void updatePlayerView(String title, String subtitle) {
        if (Intrinsics.areEqual(getTitleTextView().getText(), title) && Intrinsics.areEqual(getSubtitleTextView().getText(), subtitle)) {
            return;
        }
        setTitle(title);
        setSubtitle(subtitle);
        updateLikeButton();
        updatePlayQueueButton();
    }

    private final void updatePlayQueueButton() {
        getPlayQueueButton().setEnabled(isPlayQueueAvailable());
    }

    private final void updateLikeButton() {
        MediaItem currentMediaItem = this.playbackController.getCurrentMediaItem();
        if (currentMediaItem != null && LikesServiceProxy.INSTANCE.shouldShowLikesFeature(currentMediaItem)) {
            if (currentMediaItem.getLikeState() == LikeState.LIKE) {
                ratingChanged(1);
            } else {
                ratingChanged(0);
            }
        }
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public PlayerViewConfig getPlayerViewConfig() {
        return this.playerViewConfig;
    }

    private final void setViewVisibility(View view, boolean visible) {
        if (view == null) {
            return;
        }
        view.setVisibility(visible ? 0 : 4);
    }

    private final boolean isPlayQueueAvailable() {
        CloudQueueSequencer cloudQueueSequencer = CloudQueueSequencer.INSTANCE.getCloudQueueSequencer();
        if ((cloudQueueSequencer != null && cloudQueueSequencer.getIsOnlineInteraction()) && cloudQueueSequencer.getMaxStationItems() <= 1) {
            return false;
        }
        List<MediaItem> currentQueue = this.playbackController.getCurrentQueue();
        return !(currentQueue == null || currentQueue.isEmpty()) && this.playbackController.getCurrentQueue().size() > 1;
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void repeatStateChanged(RepeatMode repeatMode) {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        BaseButton.StyleBuilder styleBuilder2;
        BaseButton.StyleBuilder styleBuilderWithIcon2;
        BaseButton.StyleBuilder styleBuilder3;
        BaseButton.StyleBuilder styleBuilderWithIcon3;
        Intrinsics.checkNotNullParameter(repeatMode, "repeatMode");
        int i = WhenMappings.$EnumSwitchMapping$0[repeatMode.ordinal()];
        if (i == 1) {
            BaseButton repeatButton = getRepeatButton();
            Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_repeat_one);
            if (drawable != null && (styleBuilder = this.hugeAccentIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(drawable)) != null) {
                styleBuilderWithIcon.applyStyle(repeatButton);
            }
            getRepeatButton().setActivated(true);
            return;
        }
        if (i == 2) {
            BaseButton repeatButton2 = getRepeatButton();
            Drawable drawable2 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_repeat_all);
            if (drawable2 != null && (styleBuilder2 = this.hugeAccentIconBuilder) != null && (styleBuilderWithIcon2 = styleBuilder2.withIcon(drawable2)) != null) {
                styleBuilderWithIcon2.applyStyle(repeatButton2);
            }
            getRepeatButton().setActivated(true);
            return;
        }
        BaseButton repeatButton3 = getRepeatButton();
        Drawable drawable3 = ContextCompat.getDrawable(getContext(), R.drawable.ic_playback_repeat);
        if (drawable3 != null && (styleBuilder3 = this.hugePrimaryIconBuilder) != null && (styleBuilderWithIcon3 = styleBuilder3.withIcon(drawable3)) != null) {
            styleBuilderWithIcon3.applyStyle(repeatButton3);
        }
        getRepeatButton().setActivated(false);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void hasNextTrack(boolean hasNext) {
        this.hasNext = hasNext;
        getNextButton().setEnabled(hasNext);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void updateProgress(long progress) {
        getProgressBar().setProgress((int) progress);
        getProgressTime().setText(StringUtil.getCachedFormattedTime(progress));
        getRemainingTime().setText(StringUtil.getCachedFormattedTime(this.playbackDuration - progress));
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void setPlayerState(PlayerViewModel.PlayerState playerState) {
        if (playerState == null) {
            return;
        }
        int i = WhenMappings.$EnumSwitchMapping$1[playerState.ordinal()];
        if (i == 1) {
            setLoading(true);
            showPauseIcon();
        } else if (i == 2) {
            setLoading(false);
            showPlayIcon();
        } else {
            if (i != 3) {
                return;
            }
            setLoading(false);
            showPauseIcon();
        }
    }

    private final void initPlaybackSpeed() {
        PlaybackSpeed currentSpeed = Podcast.getPlayback().getPlaybackSpeed();
        Intrinsics.checkNotNullExpressionValue(currentSpeed, "currentSpeed");
        updatePlaybackSpeedText(currentSpeed);
    }

    private final void cyclePlaybackSpeeds() {
        PlaybackSpeed default_standard_playback_speed;
        AutoUiEventUtil.sendUiClickEvent$default(ActionType.SELECT_PODCAST_PLAYBACK_SPEED, PageType.CAR_MODE_NOW_PLAYING, ExperienceMode.CAR_MODE, null, 8, null);
        PlaybackSpeed playbackSpeed = Podcast.getPlayback().getPlaybackSpeed();
        CarModeUtility carModeUtility = CarModeUtility.INSTANCE;
        List<PlaybackSpeed> playbackSpeeds = carModeUtility.getPlaybackSpeeds();
        int iIndexOf = playbackSpeeds.indexOf(playbackSpeed);
        if (iIndexOf >= 0) {
            default_standard_playback_speed = playbackSpeeds.get((iIndexOf + 1) % playbackSpeeds.size());
        } else {
            default_standard_playback_speed = carModeUtility.getDEFAULT_STANDARD_PLAYBACK_SPEED();
        }
        Podcast.getPlayback().setPlaybackSpeed(default_standard_playback_speed);
        updatePlaybackSpeedText(default_standard_playback_speed);
    }

    private final void updatePlaybackSpeedText(PlaybackSpeed speed) {
        getPlaybackSpeedControl().setText(speed.getDisplayText());
        if (speed == CarModeUtility.INSTANCE.getDEFAULT_STANDARD_PLAYBACK_SPEED()) {
            FontStyle fontStyle = this.defaultSpeedFontStyle;
            if (fontStyle == null) {
                return;
            }
            FontUtil.INSTANCE.applyFontStyle(getPlaybackSpeedControl(), fontStyle);
            return;
        }
        FontStyle fontStyle2 = this.nonDefaultSpeedFontStyle;
        if (fontStyle2 == null) {
            return;
        }
        FontUtil.INSTANCE.applyFontStyle(getPlaybackSpeedControl(), fontStyle2);
    }

    private final void showPauseIcon() {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        getPlayButton().clearAnimation();
        BaseButton playButton = getPlayButton();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_pause_tray);
        if (drawable != null && (styleBuilder = this.epicGlassIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(playButton);
        }
        if (this.playerViewConfig != null) {
            getPlayButton().setContentDescription(getResources().getString(R.string.dmusic_player_btn_pause_description));
        }
    }

    private final void showPlayIcon() {
        BaseButton.StyleBuilder styleBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        getPlayButton().clearAnimation();
        BaseButton playButton = getPlayButton();
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.mini_player_btn_play_tray);
        if (drawable != null && (styleBuilder = this.epicGlassIconBuilder) != null && (styleBuilderWithIcon = styleBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(playButton);
        }
        getPlayButton().setContentDescription(getResources().getString(R.string.dmusic_player_btn_play_description));
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setTitle(String title) {
        getTitleTextView().setText(title);
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setSubtitle(String subtitle) {
        getSubtitleTextView().setText(subtitle);
    }

    public void setArtworkBitmap(Bitmap bitmap) {
        if (Intrinsics.areEqual(this.currentBitmap, bitmap) || bitmap == null) {
            return;
        }
        this.currentBitmap = bitmap;
        StyleSheet styleSheet = this.styleSheet;
        Float blurStyle = styleSheet == null ? null : styleSheet.getBlurStyle(BlurStyleKey.BLUR_2);
        if (blurStyle != null) {
            blurStyle.floatValue();
            Context context = getContext();
            getNpsBackgroundImageView().setImageDrawable(context != null ? BitmapEffectUtils.INSTANCE.getBlurBitmapDrawable(context, bitmap, blurStyle.floatValue()) : null);
        }
        getNpsArtwork().setVisibility(0);
        getNpsArtwork().setImageDrawable(BitmapUtil.convertBitmapToDrawable(getResources(), bitmap));
    }

    @Override // com.amazon.mp3.playback.view.PlayerContentView
    public void setLoading(boolean isLoading) {
        this.isLoading = isLoading;
        getProgressBar().setIndeterminate(isLoading);
    }

    @Override // com.amazon.mp3.playback.view.PlayerView
    public void addedToLibrary(boolean added) {
        Log.debug(TAG, "add to library not supported");
    }

    private final void setPodcastButtonLayout() {
        if (this.enablePodcastEpisodeList) {
            getPrevButton().setVisibility(8);
            getNextButton().setVisibility(8);
            TextView playbackSpeedControl = getPlaybackSpeedControl();
            ViewGroup.LayoutParams layoutParams = playbackSpeedControl.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            }
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.rightToLeft = -1;
            layoutParams2.leftToRight = -1;
            layoutParams2.topToTop = getPodcastEpisodeListButton().getId();
            layoutParams2.bottomToBottom = getPodcastEpisodeListButton().getId();
            layoutParams2.endToStart = getPodcastEpisodeListButton().getId();
            layoutParams2.startToStart = 0;
            playbackSpeedControl.setLayoutParams(layoutParams2);
            return;
        }
        BaseButton prevButton = getPrevButton();
        ViewGroup.LayoutParams layoutParams3 = prevButton.getLayoutParams();
        if (layoutParams3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
        layoutParams4.topToTop = -1;
        layoutParams4.bottomToTop = -1;
        layoutParams4.bottomToBottom = 0;
        prevButton.setLayoutParams(layoutParams4);
        BaseButton nextButton = getNextButton();
        ViewGroup.LayoutParams layoutParams5 = nextButton.getLayoutParams();
        if (layoutParams5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) layoutParams5;
        layoutParams6.topToTop = -1;
        layoutParams6.bottomToTop = -1;
        layoutParams6.bottomToBottom = 0;
        nextButton.setLayoutParams(layoutParams6);
    }

    private final void setMusicButtonLayout() {
        if (this.enablePodcastEpisodeList) {
            TextView playbackSpeedControl = getPlaybackSpeedControl();
            ViewGroup.LayoutParams layoutParams = playbackSpeedControl.getLayoutParams();
            if (layoutParams == null) {
                throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
            }
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) layoutParams;
            layoutParams2.topToTop = getNextButton().getId();
            layoutParams2.bottomToBottom = getPrevButton().getId();
            layoutParams2.rightToLeft = getNextButton().getId();
            layoutParams2.leftToRight = getPrevButton().getId();
            layoutParams2.endToStart = -1;
            layoutParams2.startToStart = -1;
            playbackSpeedControl.setLayoutParams(layoutParams2);
            return;
        }
        BaseButton prevButton = getPrevButton();
        ViewGroup.LayoutParams layoutParams3 = prevButton.getLayoutParams();
        if (layoutParams3 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams4 = (ConstraintLayout.LayoutParams) layoutParams3;
        layoutParams4.bottomToBottom = -1;
        layoutParams4.bottomToTop = getRepeatButton().getId();
        layoutParams4.topToTop = 0;
        prevButton.setLayoutParams(layoutParams4);
        BaseButton nextButton = getNextButton();
        ViewGroup.LayoutParams layoutParams5 = nextButton.getLayoutParams();
        if (layoutParams5 == null) {
            throw new NullPointerException("null cannot be cast to non-null type androidx.constraintlayout.widget.ConstraintLayout.LayoutParams");
        }
        ConstraintLayout.LayoutParams layoutParams6 = (ConstraintLayout.LayoutParams) layoutParams5;
        layoutParams6.bottomToBottom = -1;
        layoutParams6.bottomToTop = getRepeatButton().getId();
        layoutParams6.topToTop = 0;
        nextButton.setLayoutParams(layoutParams6);
    }

    private final void attachSwipeListener() {
        this.touchEventHandler.setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.auto.carmode.view.CarModeNowPlayingView.attachSwipeListener.1
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
                if (!CarModeNowPlayingView.this.hasNext || Math.abs(x) < CarModeNowPlayingView.this.getWidth() / 4) {
                    return;
                }
                CarModeNowPlayingView.this.next(InteractionType.SWIPE);
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
                if (!CarModeNowPlayingView.this.hasPrev || Math.abs(x) < CarModeNowPlayingView.this.getWidth() / 4) {
                    return;
                }
                CarModeNowPlayingView.this.prev(InteractionType.SWIPE);
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
                Log.debug(CarModeNowPlayingView.TAG, "onSwipeDown");
                if (Math.abs(y) >= CarModeNowPlayingView.this.getHeight() / 8) {
                    CarModeNowPlayingView.this.onBackPressed();
                }
            }
        });
        getNpsArtwork().setOnTouchListener(this.touchEventHandler.getTouchEventListener());
        getTitleTextView().setOnTouchListener(this.touchEventHandler.getTouchEventListener());
    }

    @Override // com.amazon.podcast.media.playback.Playback.PositionCallback
    public void onPositionChange(long position) {
        updateProgress(position);
    }

    @Override // com.amazon.podcast.media.playback.Playback.StateCallback
    public void onPlaybackStateChange(int state) {
        setPlayerState(CarModePresetsUtility.INSTANCE.convertPlaybackState(state));
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Podcast.getPlayback().addPositionCallback(this);
        Podcast.getPlayback().addPlaybackEventCallback(this);
        Podcast.getPlayback().addStateCallback(this);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        Podcast.getPlayback().removePositionCallback(this);
        Podcast.getPlayback().removePlaybackEventCallback(this);
        Podcast.getPlayback().removeStateCallback(this);
        super.onDetachedFromWindow();
    }

    @Override // com.amazon.podcast.media.playback.Playback.PlaybackEventCallback
    public void onPlaybackEventReceived(String playbackEvent) {
        Log.info(TAG, Intrinsics.stringPlus("onPlaybackEventReceived with event:::", playbackEvent));
    }

    public final void clear() {
        CarModeAlexaFabViewController carModeAlexaFabViewController = this.alexaFabViewController;
        if (carModeAlexaFabViewController != null) {
            carModeAlexaFabViewController.destroy();
        }
        this.alexaFabViewController = null;
    }
}
