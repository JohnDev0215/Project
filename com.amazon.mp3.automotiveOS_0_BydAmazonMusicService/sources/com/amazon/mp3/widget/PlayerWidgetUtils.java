package com.amazon.mp3.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.widget.RemoteViews;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.ExistingWorkPolicy;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.amazon.identity.auth.device.api.MAPAccountManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.amp.widget.PlayerWidgetProvider;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.cloudqueue.sequencer.AdMediaItem;
import com.amazon.mp3.intents.PendingIntentUtil;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.voice.VoiceManagerFactory;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.MediaItem;
import com.amazon.music.media.playback.PlayStatus;
import com.amazon.music.voice.VoiceManagerSingleton;
import com.google.android.gms.cast.MediaTrack;
import com.visualon.OSMPUtils.voOSType;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt__IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/* JADX INFO: compiled from: PlayerWidgetUtils.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b-\u0010.J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J \u0010\f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007J+\u0010\r\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u000b\u001a\u00020\u0006H\u0087@ø\u0001\u0000¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J+\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0082@ø\u0001\u0000¢\u0006\u0004\b\u0012\u0010\u0013J \u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0002J \u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0002J \u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\n\u001a\u00020\tH\u0002J\u0018\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J0\u0010\u001d\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0018\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0018\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J(\u0010!\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J \u0010%\u001a\u00020$2\u0006\u0010#\u001a\u00020\"2\u0006\u0010\u001c\u001a\u00020\u001a2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\n\u0010'\u001a\u0004\u0018\u00010&H\u0002J\u0010\u0010(\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010)\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010*\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010+\u001a\u00020\u00042\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010,\u001a\u00020\tH\u0007\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006/"}, d2 = {"Lcom/amazon/mp3/widget/PlayerWidgetUtils;", "", "Landroid/content/Context;", "context", "", "enqueueDelayedWorker", "", "clearWidget", "updateWidgetWithWorker", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "playbackController", "partial", "setupWidgetAsync", "setupWidget", "(Landroid/content/Context;Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroid/widget/RemoteViews;", "playerWidgetView", "initTitleAndArtist", "initArtwork", "(Landroid/content/Context;Landroid/widget/RemoteViews;Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initButtons", "initPreviousButton", "initNextButton", "initPlayToggleButton", "", "buttonImageId", "", MediaTrack.ROLE_DESCRIPTION, "action", "setPlayPauseToggleButton", "initAlexaButton", "initNowPlayingLauncher", "viewId", "initButton", "Landroid/content/Intent;", MAPAccountManager.KEY_INTENT, "Landroid/app/PendingIntent;", "getPendingIntent", "Lcom/amazon/music/media/playback/MediaItem;", "getCurrentMediaItem", "showMusicLogo", "disablePrevButton", "disableNextButton", "disablePlayPauseButtonAndProgressbar", "getPlaybackController", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class PlayerWidgetUtils {
    public static final PlayerWidgetUtils INSTANCE = new PlayerWidgetUtils();

    /* JADX INFO: renamed from: com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$1, reason: invalid class name */
    /* JADX INFO: compiled from: PlayerWidgetUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.widget.PlayerWidgetUtils", f = "PlayerWidgetUtils.kt", i = {0}, l = {186}, m = "initArtwork", n = {"playerWidgetView"}, s = {"L$0"})
    static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlayerWidgetUtils.this.initArtwork(null, null, null, this);
        }
    }

    /* JADX INFO: renamed from: com.amazon.mp3.widget.PlayerWidgetUtils$setupWidget$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerWidgetUtils.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.amazon.mp3.widget.PlayerWidgetUtils", f = "PlayerWidgetUtils.kt", i = {0, 0, 0, 0}, l = {voOSType.VOOSMP_PID_ANALYTICS_EXPORT_LISTENER}, m = "setupWidget", n = {"context", "playbackController", "playerWidgetView", "partial"}, s = {"L$0", "L$1", "L$2", "Z$0"})
    static final class C04201 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;

        C04201(Continuation<? super C04201> continuation) {
            super(continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            this.result = obj;
            this.label |= Integer.MIN_VALUE;
            return PlayerWidgetUtils.setupWidget(null, null, false, this);
        }
    }

    private PlayerWidgetUtils() {
    }

    @JvmStatic
    public static final void enqueueDelayedWorker(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        WorkManager.getInstance(context).enqueueUniqueWork("appWidgetWorkerKeepEnabled", ExistingWorkPolicy.KEEP, new OneTimeWorkRequest.Builder(DelayedWidgetWorker.class).setInitialDelay(3650L, TimeUnit.DAYS).setConstraints(new Constraints.Builder().setRequiresCharging(true).build()).build());
    }

    @JvmStatic
    public static final void updateWidgetWithWorker(boolean clearWidget, Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        int i = 0;
        Pair[] pairArr = {TuplesKt.to("clear_widget", Boolean.valueOf(clearWidget))};
        Data.Builder builder = new Data.Builder();
        while (i < 1) {
            Pair pair = pairArr[i];
            i++;
            builder.put((String) pair.getFirst(), pair.getSecond());
        }
        Data dataBuild = builder.build();
        Intrinsics.checkNotNullExpressionValue(dataBuild, "dataBuilder.build()");
        OneTimeWorkRequest oneTimeWorkRequestBuild = new OneTimeWorkRequest.Builder(UpdatePlayerWidgetWorker.class).setInputData(dataBuild).build();
        Intrinsics.checkNotNullExpressionValue(oneTimeWorkRequestBuild, "OneTimeWorkRequestBuilde…\n                .build()");
        WorkManager.getInstance(context).enqueueUniqueWork("update_widget", ExistingWorkPolicy.KEEP, oneTimeWorkRequestBuild);
    }

    @JvmStatic
    public static final void clearWidget(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widget_player_view);
        PlayerWidgetUtils playerWidgetUtils = INSTANCE;
        playerWidgetUtils.showMusicLogo(remoteViews);
        remoteViews.setImageViewResource(R.id.AlbumArt, R.drawable.widget_player_placeholder);
        playerWidgetUtils.disablePrevButton(remoteViews);
        playerWidgetUtils.disableNextButton(remoteViews);
        playerWidgetUtils.disablePlayPauseButtonAndProgressbar(remoteViews);
        playerWidgetUtils.initNowPlayingLauncher(context, remoteViews);
        AppWidgetManager.getInstance(context).updateAppWidget(new ComponentName(context, (Class<?>) PlayerWidgetProvider.class), remoteViews);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.widget.PlayerWidgetUtils$setupWidgetAsync$1, reason: invalid class name and case insensitive filesystem */
    /* JADX INFO: compiled from: PlayerWidgetUtils.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.widget.PlayerWidgetUtils$setupWidgetAsync$1", f = "PlayerWidgetUtils.kt", i = {}, l = {119}, m = "invokeSuspend", n = {}, s = {})
    static final class C04211 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Context $context;
        final /* synthetic */ boolean $partial;
        final /* synthetic */ ActionValidatedPlaybackController $playbackController;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04211(Context context, ActionValidatedPlaybackController actionValidatedPlaybackController, boolean z, Continuation<? super C04211> continuation) {
            super(2, continuation);
            this.$context = context;
            this.$playbackController = actionValidatedPlaybackController;
            this.$partial = z;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04211(this.$context, this.$playbackController, this.$partial, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C04211) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt__IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Context context = this.$context;
                ActionValidatedPlaybackController actionValidatedPlaybackController = this.$playbackController;
                boolean z = this.$partial;
                this.label = 1;
                if (PlayerWidgetUtils.setupWidget(context, actionValidatedPlaybackController, z, this) == coroutine_suspended) {
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

    @JvmStatic
    public static final void setupWidgetAsync(Context context, ActionValidatedPlaybackController playbackController, boolean partial) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackController, "playbackController");
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getMain()), null, null, new C04211(context, playbackController, partial, null), 3, null);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    @kotlin.jvm.JvmStatic
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object setupWidget(android.content.Context r5, com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r6, boolean r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            boolean r0 = r8 instanceof com.amazon.mp3.widget.PlayerWidgetUtils.C04201
            if (r0 == 0) goto L13
            r0 = r8
            com.amazon.mp3.widget.PlayerWidgetUtils$setupWidget$1 r0 = (com.amazon.mp3.widget.PlayerWidgetUtils.C04201) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.widget.PlayerWidgetUtils$setupWidget$1 r0 = new com.amazon.mp3.widget.PlayerWidgetUtils$setupWidget$1
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L41
            if (r2 != r3) goto L39
            boolean r7 = r0.Z$0
            java.lang.Object r5 = r0.L$2
            android.widget.RemoteViews r5 = (android.widget.RemoteViews) r5
            java.lang.Object r6 = r0.L$1
            com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r6 = (com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController) r6
            java.lang.Object r0 = r0.L$0
            android.content.Context r0 = (android.content.Context) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r5
            r5 = r0
            goto L66
        L39:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L41:
            kotlin.ResultKt.throwOnFailure(r8)
            android.widget.RemoteViews r8 = new android.widget.RemoteViews
            java.lang.String r2 = r5.getPackageName()
            r4 = 2131624796(0x7f0e035c, float:1.8876782E38)
            r8.<init>(r2, r4)
            com.amazon.mp3.widget.PlayerWidgetUtils r2 = com.amazon.mp3.widget.PlayerWidgetUtils.INSTANCE
            r2.initTitleAndArtist(r8)
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r8
            r0.Z$0 = r7
            r0.label = r3
            java.lang.Object r0 = r2.initArtwork(r5, r8, r6, r0)
            if (r0 != r1) goto L66
            return r1
        L66:
            com.amazon.mp3.widget.PlayerWidgetUtils r0 = com.amazon.mp3.widget.PlayerWidgetUtils.INSTANCE
            r0.initButtons(r5, r8, r6)
            r0.initNowPlayingLauncher(r5, r8)
            android.content.ComponentName r6 = new android.content.ComponentName
            java.lang.Class<com.amazon.mp3.amp.widget.PlayerWidgetProvider> r0 = com.amazon.mp3.amp.widget.PlayerWidgetProvider.class
            r6.<init>(r5, r0)
            android.appwidget.AppWidgetManager r0 = android.appwidget.AppWidgetManager.getInstance(r5)
            int[] r6 = r0.getAppWidgetIds(r6)
            if (r7 == 0) goto L87
            android.appwidget.AppWidgetManager r5 = android.appwidget.AppWidgetManager.getInstance(r5)
            r5.partiallyUpdateAppWidget(r6, r8)
            goto L8e
        L87:
            android.appwidget.AppWidgetManager r5 = android.appwidget.AppWidgetManager.getInstance(r5)
            r5.updateAppWidget(r6, r8)
        L8e:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.widget.PlayerWidgetUtils.setupWidget(android.content.Context, com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, boolean, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void initTitleAndArtist(RemoteViews playerWidgetView) {
        MediaItem currentMediaItem = getCurrentMediaItem();
        if (currentMediaItem != null) {
            String name = currentMediaItem.getName();
            Intrinsics.checkNotNullExpressionValue(name, "mediaItem.name");
            String artistName = currentMediaItem.getArtistName();
            Intrinsics.checkNotNullExpressionValue(artistName, "mediaItem.artistName");
            if (TextUtils.isEmpty(name) && TextUtils.isEmpty(artistName)) {
                showMusicLogo(playerWidgetView);
                return;
            }
            playerWidgetView.setViewVisibility(R.id.MusicLogo, 8);
            playerWidgetView.setViewVisibility(R.id.SongInfo, 0);
            playerWidgetView.setViewVisibility(R.id.ArtistInfo, 0);
            playerWidgetView.setTextViewText(R.id.SongInfo, name);
            playerWidgetView.setTextViewText(R.id.ArtistInfo, artistName);
            return;
        }
        showMusicLogo(playerWidgetView);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object initArtwork(android.content.Context r8, android.widget.RemoteViews r9, com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) throws java.lang.Throwable {
        /*
            r7 = this;
            boolean r0 = r11 instanceof com.amazon.mp3.widget.PlayerWidgetUtils.AnonymousClass1
            if (r0 == 0) goto L13
            r0 = r11
            com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$1 r0 = (com.amazon.mp3.widget.PlayerWidgetUtils.AnonymousClass1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$1 r0 = new com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$1
            r0.<init>(r11)
        L18:
            java.lang.Object r11 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2131427345(0x7f0b0011, float:1.8476304E38)
            r4 = 1
            if (r2 == 0) goto L39
            if (r2 != r4) goto L31
            java.lang.Object r8 = r0.L$0
            r9 = r8
            android.widget.RemoteViews r9 = (android.widget.RemoteViews) r9
            kotlin.ResultKt.throwOnFailure(r11)
            goto L60
        L31:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r9 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r9)
            throw r8
        L39:
            kotlin.ResultKt.throwOnFailure(r11)
            r11 = 2131232009(0x7f080509, float:1.8080115E38)
            r9.setImageViewResource(r3, r11)
            com.amazon.music.media.playback.MediaItem r11 = r7.getCurrentMediaItem()
            if (r11 != 0) goto L4b
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L4b:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$albumArtwork$1 r5 = new com.amazon.mp3.widget.PlayerWidgetUtils$initArtwork$albumArtwork$1
            r6 = 0
            r5.<init>(r11, r8, r10, r6)
            r0.L$0 = r9
            r0.label = r4
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r2, r5, r0)
            if (r11 != r1) goto L60
            return r1
        L60:
            android.graphics.Bitmap r11 = (android.graphics.Bitmap) r11
            if (r11 == 0) goto L6b
            android.graphics.Bitmap r8 = com.amazon.mp3.widget.WidgetUtil.getCircleBitmap(r11)
            r9.setImageViewBitmap(r3, r8)
        L6b:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.widget.PlayerWidgetUtils.initArtwork(android.content.Context, android.widget.RemoteViews, com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void initButtons(Context context, RemoteViews playerWidgetView, ActionValidatedPlaybackController playbackController) {
        initPreviousButton(context, playerWidgetView, playbackController);
        initNextButton(context, playerWidgetView, playbackController);
        initPlayToggleButton(context, playerWidgetView);
        initAlexaButton(context, playerWidgetView);
    }

    private final void initPreviousButton(Context context, RemoteViews playerWidgetView, ActionValidatedPlaybackController playbackController) {
        if (playbackController.canDisplayAction("PREVIOUS") && playbackController.canSkipPrevious()) {
            playerWidgetView.setViewVisibility(R.id.PrevButtonDisabled, 8);
            playerWidgetView.setViewVisibility(R.id.WidgetPrevButton, 0);
            initButton("com.amazon.mp3.widget.prev", R.id.WidgetPrevButton, context, playerWidgetView);
            return;
        }
        disablePrevButton(playerWidgetView);
    }

    private final void initNextButton(Context context, RemoteViews playerWidgetView, ActionValidatedPlaybackController playbackController) {
        if (playbackController.canDisplayAction("NEXT") && playbackController.canSkipNext() && !(playbackController.getCurrentMediaItem() instanceof AdMediaItem)) {
            playerWidgetView.setViewVisibility(R.id.NextButtonDisabled, 8);
            playerWidgetView.setViewVisibility(R.id.WidgetNextButton, 0);
            initButton("com.amazon.mp3.widget.next", R.id.WidgetNextButton, context, playerWidgetView);
            return;
        }
        disableNextButton(playerWidgetView);
    }

    private final void initPlayToggleButton(Context context, RemoteViews playerWidgetView) {
        ActionValidatedPlaybackController controller = PlaybackControllerProvider.INSTANCE.getController(ControlSource.WIDGET);
        PlayStatus playStatus = controller.getPlayStatus();
        if (controller.getCurrentMediaItem() == null) {
            disablePlayPauseButtonAndProgressbar(playerWidgetView);
            return;
        }
        if (playStatus.isDelayed()) {
            playerWidgetView.setViewVisibility(R.id.PlayPauseButton, 8);
            playerWidgetView.setViewVisibility(R.id.PlayPauseButtonDisabled, 8);
            playerWidgetView.setViewVisibility(R.id.progress, 0);
        } else if (!playStatus.willPlay()) {
            String string = context.getResources().getString(R.string.dmusic_player_btn_play_description);
            Intrinsics.checkNotNullExpressionValue(string, "context.resources.getStr…yer_btn_play_description)");
            setPlayPauseToggleButton(R.drawable.widget_player_play, string, "com.amazon.mp3.widget.play", context, playerWidgetView);
        } else if (PlayStatus.RENDERING == playStatus) {
            String string2 = context.getResources().getString(R.string.dmusic_player_btn_pause_description);
            Intrinsics.checkNotNullExpressionValue(string2, "context.resources.getStr…er_btn_pause_description)");
            setPlayPauseToggleButton(R.drawable.widget_player_pause, string2, "com.amazon.mp3.widget.pause", context, playerWidgetView);
        }
    }

    private final void setPlayPauseToggleButton(int buttonImageId, String description, String action, Context context, RemoteViews playerWidgetView) {
        initButton(action, R.id.PlayPauseButton, context, playerWidgetView);
        playerWidgetView.setImageViewResource(R.id.PlayPauseButton, buttonImageId);
        playerWidgetView.setContentDescription(R.id.PlayPauseButton, description);
        playerWidgetView.setViewVisibility(R.id.progress, 8);
        playerWidgetView.setViewVisibility(R.id.PlayPauseButtonDisabled, 8);
        playerWidgetView.setViewVisibility(R.id.PlayPauseButton, 0);
    }

    private final void initAlexaButton(final Context context, RemoteViews playerWidgetView) {
        if (AmazonApplication.getCapabilities().isAlexaEnabled()) {
            Observable.fromCallable(new Callable() { // from class: com.amazon.mp3.widget.PlayerWidgetUtils$$ExternalSyntheticLambda0
                @Override // java.util.concurrent.Callable
                public final Object call() {
                    return PlayerWidgetUtils.m1844initAlexaButton$lambda0(context);
                }
            }).subscribeOn(AndroidSchedulers.mainThread()).subscribe();
            playerWidgetView.setViewVisibility(R.id.WidgetAlexaButton, 0);
            Intent intent = new Intent(context, (Class<?>) AlexaWidgetContainerActivity.class);
            intent.setAction("com.amazon.mp3.widget.startalexa");
            playerWidgetView.setOnClickPendingIntent(R.id.WidgetAlexaButton, PendingIntentUtil.INSTANCE.getActivity(context, 1, intent, 134217728, true));
            return;
        }
        playerWidgetView.setViewVisibility(R.id.WidgetAlexaButton, 4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initAlexaButton$lambda-0, reason: not valid java name */
    public static final Void m1844initAlexaButton$lambda0(Context context) {
        Intrinsics.checkNotNullParameter(context, "$context");
        if (VoiceManagerSingleton.isInitialized() && !VoiceManagerFactory.shouldRefresh()) {
            return null;
        }
        VoiceManagerFactory.initializeVoiceManager(context);
        return null;
    }

    private final void initNowPlayingLauncher(Context context, RemoteViews playerWidgetView) {
        playerWidgetView.setOnClickPendingIntent(R.id.AlbumArt, getPendingIntent(new Intent(context, (Class<?>) WidgetBroadcastReceiver.class), "open now playing", context));
    }

    private final void initButton(String action, int viewId, Context context, RemoteViews playerWidgetView) {
        playerWidgetView.setOnClickPendingIntent(viewId, getPendingIntent(new Intent(context, (Class<?>) WidgetBroadcastReceiver.class), action, context));
    }

    private final PendingIntent getPendingIntent(Intent intent, String action, Context context) {
        intent.setAction(action);
        return PendingIntentUtil.INSTANCE.getBroadcast(context, 1, intent, 134217728, true);
    }

    private final MediaItem getCurrentMediaItem() {
        return PlaybackControllerProvider.INSTANCE.getController(ControlSource.WIDGET).getCurrentMediaItem();
    }

    private final void showMusicLogo(RemoteViews playerWidgetView) {
        playerWidgetView.setViewVisibility(R.id.SongInfo, 8);
        playerWidgetView.setViewVisibility(R.id.ArtistInfo, 8);
        playerWidgetView.setViewVisibility(R.id.MusicLogo, 0);
    }

    private final void disablePrevButton(RemoteViews playerWidgetView) {
        playerWidgetView.setViewVisibility(R.id.WidgetPrevButton, 8);
        playerWidgetView.setViewVisibility(R.id.PrevButtonDisabled, 0);
    }

    private final void disableNextButton(RemoteViews playerWidgetView) {
        playerWidgetView.setViewVisibility(R.id.WidgetNextButton, 8);
        playerWidgetView.setViewVisibility(R.id.NextButtonDisabled, 0);
    }

    private final void disablePlayPauseButtonAndProgressbar(RemoteViews playerWidgetView) {
        playerWidgetView.setViewVisibility(R.id.PlayPauseButton, 8);
        playerWidgetView.setViewVisibility(R.id.progress, 8);
        playerWidgetView.setViewVisibility(R.id.PlayPauseButtonDisabled, 0);
    }

    @JvmStatic
    public static final ActionValidatedPlaybackController getPlaybackController() {
        return PlaybackControllerProvider.INSTANCE.getController(ControlSource.WIDGET);
    }
}
