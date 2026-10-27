package com.amazon.mp3.playback.view.lyrics;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.Lyrics;
import com.amazon.mp3.playback.OnLyricsAvailableListener;
import com.amazon.mp3.util.ScreenUtil;
import com.amazon.mp3.view.OnSwipeEventListener;
import com.amazon.mp3.view.TouchEventHandler;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.ui.foundations.utils.LayoutParamUtils;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: LyricsTextViewContainer.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0001-B\u0011\b\u0016\u0012\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b(\u0010)B\u0019\b\u0016\u0012\u0006\u0010'\u001a\u00020&\u0012\u0006\u0010+\u001a\u00020*¢\u0006\u0004\b(\u0010,J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u0010\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0016J\u0012\u0010\t\u001a\u00020\u00022\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0010\u0010\u000f\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rJ\u0010\u0010\u0012\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u0010H\u0016J\u0010\u0010\u0015\u001a\u00020\u00022\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013J\n\u0010\u0017\u001a\u0004\u0018\u00010\u0016H\u0016J\b\u0010\u0018\u001a\u00020\u0002H\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019R\u001b\u0010!\u001a\u00020\u001c8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0018\u0010\"\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%¨\u0006."}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer;", "Lcom/amazon/mp3/playback/view/lyrics/LyricsViewContainer;", "", "initInteractionListener", "", "linePosition", "setCurrentPlayingLyricsLine", "Lcom/amazon/music/media/playback/PlayStatus;", "playStatus", "setPlayStatus", "Lcom/amazon/mp3/lyrics/item/Lyrics;", "lyrics", "onLyricsLoaded", "Lcom/amazon/mp3/playback/OnLyricsAvailableListener;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "setOnLyricsAvailableListener", "Lcom/amazon/music/views/library/styles/StyleSheet;", "styleSheet", "initStyleSheet", "Lcom/amazon/mp3/view/OnSwipeEventListener;", "onSwipeEventListener", "setSwipeEventListener", "Landroid/view/View;", "getLyricsView", "onDestroy", "Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer$LyricsWindowInteractionListener;", "lyricsWindowInteractionListener", "setLyricsWindowInteractionListener", "Lcom/amazon/mp3/playback/view/lyrics/LyricsView;", "mLyricsView$delegate", "Lkotlin/Lazy;", "getMLyricsView", "()Lcom/amazon/mp3/playback/view/lyrics/LyricsView;", "mLyricsView", "mLyricsWindowInteractionListener", "Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer$LyricsWindowInteractionListener;", "mOnLyricsAvailableListener", "Lcom/amazon/mp3/playback/OnLyricsAvailableListener;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attributeSet", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "LyricsWindowInteractionListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsTextViewContainer extends LyricsViewContainer {

    /* JADX INFO: renamed from: mLyricsView$delegate, reason: from kotlin metadata */
    private final Lazy mLyricsView;
    private LyricsWindowInteractionListener mLyricsWindowInteractionListener;
    private OnLyricsAvailableListener mOnLyricsAvailableListener;

    /* JADX INFO: compiled from: LyricsTextViewContainer.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\b\u0010\u0004\u001a\u00020\u0003H&¨\u0006\u0005"}, d2 = {"Lcom/amazon/mp3/playback/view/lyrics/LyricsTextViewContainer$LyricsWindowInteractionListener;", "", "onLyricsWindowClicked", "", "onLyricsWindowSwipedUp", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface LyricsWindowInteractionListener {
        void onLyricsWindowClicked();

        void onLyricsWindowSwipedUp();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsTextViewContainer(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.mLyricsView = LazyKt__LazyJVMKt.lazy(new Function0<LyricsView>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer$mLyricsView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricsView invoke() {
                return (LyricsView) this.this$0.findViewById(R.id.LyricsView);
            }
        });
        if (ScreenUtil.isLandscape()) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_lyrics_text_view_container_land, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.view_lyrics_text_view_container, this);
        }
        initInteractionListener();
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LyricsTextViewContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(attributeSet, "attributeSet");
        this.mLyricsView = LazyKt__LazyJVMKt.lazy(new Function0<LyricsView>() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer$mLyricsView$2
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // kotlin.jvm.functions.Function0
            public final LyricsView invoke() {
                return (LyricsView) this.this$0.findViewById(R.id.LyricsView);
            }
        });
        if (ScreenUtil.isLandscape()) {
            LayoutInflater.from(getContext()).inflate(R.layout.view_lyrics_text_view_container_land, this);
        } else {
            LayoutInflater.from(getContext()).inflate(R.layout.view_lyrics_text_view_container, this);
        }
        initInteractionListener();
    }

    private final LyricsView getMLyricsView() {
        Object value = this.mLyricsView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-mLyricsView>(...)");
        return (LyricsView) value;
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void setCurrentPlayingLyricsLine(int linePosition) {
        getMLyricsView().setCurrentPosition(linePosition);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void setPlayStatus(PlayStatus playStatus) {
        getMLyricsView().updateEqualizerIcon(playStatus == PlayStatus.RENDERING);
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer, com.amazon.mp3.playback.view.lyrics.LyricsProvider.LoadLyricsCallback
    public void onLyricsLoaded(Lyrics lyrics) {
        Intrinsics.checkNotNullParameter(lyrics, "lyrics");
        super.onLyricsLoaded(lyrics);
        getMLyricsView().setLyrics(lyrics);
        this.mCachedViewState.setViewState(ViewState.PREVIEW);
        OnLyricsAvailableListener onLyricsAvailableListener = this.mOnLyricsAvailableListener;
        if (onLyricsAvailableListener == null) {
            return;
        }
        onLyricsAvailableListener.onLyricsAvailable(lyrics);
    }

    public final void setOnLyricsAvailableListener(OnLyricsAvailableListener listener) {
        this.mOnLyricsAvailableListener = listener;
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void initStyleSheet(StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(styleSheet, "styleSheet");
        super.initStyleSheet(styleSheet);
        getMLyricsView().initStyleSheet(styleSheet);
        if (isStage()) {
            LayoutParamUtils.INSTANCE.setLayoutMargins(this, -1, -1, (240 & 8) != 0 ? Boolean.FALSE : null, (240 & 16) != 0 ? null : null, (240 & 32) != 0 ? null : null, (240 & 64) != 0 ? null : null, (240 & 128) != 0 ? null : null);
        }
    }

    public final void setSwipeEventListener(OnSwipeEventListener onSwipeEventListener) {
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        touchEventHandler.setOnClickListener(getContext(), new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsTextViewContainer.m1534setSwipeEventListener$lambda0(this.f$0, view);
            }
        });
        touchEventHandler.setOnSwipeEventListener(onSwipeEventListener);
        setOnTouchListener(touchEventHandler.getTouchEventListener());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setSwipeEventListener$lambda-0, reason: not valid java name */
    public static final void m1534setSwipeEventListener$lambda0(LyricsTextViewContainer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsWindowInteractionListener lyricsWindowInteractionListener = this$0.mLyricsWindowInteractionListener;
        if (lyricsWindowInteractionListener == null) {
            return;
        }
        lyricsWindowInteractionListener.onLyricsWindowClicked();
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public View getLyricsView() {
        return getMLyricsView();
    }

    @Override // com.amazon.mp3.playback.view.lyrics.LyricsViewContainer
    public void onDestroy() {
        super.onDestroy();
        setLyricsWindowInteractionListener(null);
    }

    public final void setLyricsWindowInteractionListener(LyricsWindowInteractionListener lyricsWindowInteractionListener) {
        this.mLyricsWindowInteractionListener = lyricsWindowInteractionListener;
    }

    private final void initInteractionListener() {
        TouchEventHandler touchEventHandler = new TouchEventHandler();
        setOnTouchListener(touchEventHandler.getTouchEventListener());
        touchEventHandler.setOnClickListener(getContext(), new View.OnClickListener() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsTextViewContainer.m1533initInteractionListener$lambda1(this.f$0, view);
            }
        });
        touchEventHandler.setOnSwipeEventListener(new OnSwipeEventListener() { // from class: com.amazon.mp3.playback.view.lyrics.LyricsTextViewContainer.initInteractionListener.2
            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeDown(float y) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeLeft(float x) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeRight(float x) {
            }

            @Override // com.amazon.mp3.view.OnSwipeEventListener
            public void onSwipeUp(float y) {
                LyricsWindowInteractionListener lyricsWindowInteractionListener = LyricsTextViewContainer.this.mLyricsWindowInteractionListener;
                if (lyricsWindowInteractionListener == null) {
                    return;
                }
                lyricsWindowInteractionListener.onLyricsWindowSwipedUp();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initInteractionListener$lambda-1, reason: not valid java name */
    public static final void m1533initInteractionListener$lambda1(LyricsTextViewContainer this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        LyricsWindowInteractionListener lyricsWindowInteractionListener = this$0.mLyricsWindowInteractionListener;
        if (lyricsWindowInteractionListener == null) {
            return;
        }
        lyricsWindowInteractionListener.onLyricsWindowClicked();
    }
}
