package com.amazon.mp3.lyrics;

import UpsellInterface.v1_0.PlaybackMode;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.item.LyricsFeedbackOperationsMetadata;
import com.amazon.mp3.lyrics.item.LyricsOperationsMetadata;
import com.amazon.mp3.util.Log;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.platform.AtomicMap;
import com.amazon.music.platform.locator.Providers;
import com.amazon.music.platform.locator.ProvidersKt;
import com.amazon.music.platform.providers.InterfaceProvider;
import com.amazon.music.platform.providers.LyricsReportProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import org.xmlpull.v1.XmlPullParserException;

/* JADX INFO: compiled from: LyricsFeedbackOperations.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u0000 A2\u00020\u0001:\u0004ABCDB\u000f\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b?\u0010@J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\u0010\u0010\b\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0010\u0010\u000f\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\rH\u0016J\u0006\u0010\u0010\u001a\u00020\u0002J\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\t0\u0011J\u0017\u0010\u0014\u001a\u00020\u00022\b\u0010\u0013\u001a\u0004\u0018\u00010\u000b¢\u0006\u0004\b\u0014\u0010\u0015J\u000e\u0010\u0017\u001a\u00020\u00022\u0006\u0010\u0016\u001a\u00020\tJ\u000e\u0010\u001a\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u0018R\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0016\u0010\u000e\u001a\u00020\u001e8\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u000e\u0010\u001fR$\u0010#\u001a\u0012\u0012\u0004\u0012\u00020!0 j\b\u0012\u0004\u0012\u00020!`\"8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u000b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0018\u0010'\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010*\u001a\u0004\u0018\u00010)8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u0014\u0010-\u001a\u00020,8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u00100\u001a\u00020/8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b0\u00101R\u001d\u00104\u001a\b\u0012\u0004\u0012\u000203028\u0006¢\u0006\f\n\u0004\b4\u00105\u001a\u0004\b6\u00107R\u001d\u00108\u001a\b\u0012\u0004\u0012\u00020/028\u0006¢\u0006\f\n\u0004\b8\u00105\u001a\u0004\b9\u00107R\u001d\u0010;\u001a\b\u0012\u0004\u0012\u00020\u00040:8\u0006¢\u0006\f\n\u0004\b;\u0010<\u001a\u0004\b=\u0010>¨\u0006E"}, d2 = {"Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations;", "", "", "submitLyricsFeedback", "", "validateReportText", "Landroid/content/res/Resources;", "resources", "initializeStringIdToReasonCodeMapping", "", "resourceName", "", "getResourceId", "Lcom/amazon/mp3/lyrics/item/LyricsOperationsMetadata;", "feedbackMetadata", "initializeOperation", "performReportAction", "", "getReasonStrings", "index", "onReportActionChange", "(Ljava/lang/Integer;)V", "text", "onReportTextChanged", "Landroidx/lifecycle/LifecycleOwner;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_OWNER, "close", "Landroid/content/Context;", "currentContext", "Landroid/content/Context;", "Lcom/amazon/mp3/lyrics/item/LyricsFeedbackOperationsMetadata;", "Lcom/amazon/mp3/lyrics/item/LyricsFeedbackOperationsMetadata;", "Ljava/util/ArrayList;", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportReason;", "Lkotlin/collections/ArrayList;", "reportReasons", "Ljava/util/ArrayList;", "selectedReasonIndex", "I", "reportText", "Ljava/lang/String;", "Lcom/amazon/music/platform/providers/LyricsReportProvider;", "lyricsReportProvider", "Lcom/amazon/music/platform/providers/LyricsReportProvider;", "Lkotlinx/coroutines/CoroutineScope;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "allowedAction", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "Landroidx/lifecycle/MutableLiveData;", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportStatus;", "reportStatusEvent", "Landroidx/lifecycle/MutableLiveData;", "getReportStatusEvent", "()Landroidx/lifecycle/MutableLiveData;", "reportActionEvent", "getReportActionEvent", "Lcom/amazon/music/explore/events/SingleLiveEvent;", "feedbackCloseEvent", "Lcom/amazon/music/explore/events/SingleLiveEvent;", "getFeedbackCloseEvent", "()Lcom/amazon/music/explore/events/SingleLiveEvent;", "<init>", "(Landroid/content/Context;)V", "Companion", "ReportAction", "ReportReason", "ReportStatus", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class LyricsFeedbackOperations {
    private static final String TAG = LyricsOperationsMetadata.class.getSimpleName();
    private ReportAction allowedAction;
    private final CoroutineScope coroutineScope;
    private final Context currentContext;
    private final SingleLiveEvent<Boolean> feedbackCloseEvent;
    private LyricsFeedbackOperationsMetadata feedbackMetadata;
    private final LyricsReportProvider lyricsReportProvider;
    private final MutableLiveData<ReportAction> reportActionEvent;
    private final ArrayList<ReportReason> reportReasons;
    private final MutableLiveData<ReportStatus> reportStatusEvent;
    private String reportText;
    private int selectedReasonIndex;

    /* JADX INFO: compiled from: LyricsFeedbackOperations.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "", "(Ljava/lang/String;I)V", "SUBMIT", "SUBMIT_WITH_TEXT", "EXIT", PlaybackMode.NONE, "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ReportAction {
        SUBMIT,
        SUBMIT_WITH_TEXT,
        EXIT,
        NONE
    }

    /* JADX INFO: compiled from: LyricsFeedbackOperations.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportStatus;", "", "(Ljava/lang/String;I)V", "SUBMIT_SUCCESS", "SUBMIT_ERROR", "PROCESSING", "VALIDATION_ERROR", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public enum ReportStatus {
        SUBMIT_SUCCESS,
        SUBMIT_ERROR,
        PROCESSING,
        VALIDATION_ERROR
    }

    public LyricsFeedbackOperations(Context currentContext) {
        Intrinsics.checkNotNullParameter(currentContext, "currentContext");
        this.currentContext = currentContext;
        this.reportReasons = new ArrayList<>();
        this.selectedReasonIndex = -1;
        AtomicMap<String, InterfaceProvider> providerInstances = Providers.INSTANCE.getProviderInstances();
        String originalClassName = ProvidersKt.getOriginalClassName(Reflection.getOrCreateKotlinClass(LyricsReportProvider.class));
        Intrinsics.checkNotNull(originalClassName);
        InterfaceProvider interfaceProvider = providerInstances.get(originalClassName);
        this.lyricsReportProvider = (LyricsReportProvider) (interfaceProvider instanceof LyricsReportProvider ? interfaceProvider : null);
        this.coroutineScope = CoroutineScopeKt.CoroutineScope(Dispatchers.getIO());
        this.allowedAction = ReportAction.NONE;
        this.reportStatusEvent = new MutableLiveData<>();
        this.reportActionEvent = new MutableLiveData<>();
        this.feedbackCloseEvent = new SingleLiveEvent<>();
    }

    public final MutableLiveData<ReportStatus> getReportStatusEvent() {
        return this.reportStatusEvent;
    }

    public final MutableLiveData<ReportAction> getReportActionEvent() {
        return this.reportActionEvent;
    }

    public final SingleLiveEvent<Boolean> getFeedbackCloseEvent() {
        return this.feedbackCloseEvent;
    }

    public void initializeOperation(LyricsOperationsMetadata feedbackMetadata) {
        Intrinsics.checkNotNullParameter(feedbackMetadata, "feedbackMetadata");
        if (feedbackMetadata instanceof LyricsFeedbackOperationsMetadata) {
            this.feedbackMetadata = (LyricsFeedbackOperationsMetadata) feedbackMetadata;
            Resources resources = this.currentContext.getResources();
            Intrinsics.checkNotNullExpressionValue(resources, "currentContext.resources");
            initializeStringIdToReasonCodeMapping(resources);
            this.reportActionEvent.setValue(this.allowedAction);
        }
    }

    public final void performReportAction() {
        ReportAction reportAction = this.allowedAction;
        if (reportAction == ReportAction.SUBMIT) {
            submitLyricsFeedback();
            return;
        }
        if (reportAction == ReportAction.EXIT) {
            this.feedbackCloseEvent.setValue(Boolean.TRUE);
        } else if (reportAction == ReportAction.SUBMIT_WITH_TEXT) {
            if (validateReportText()) {
                submitLyricsFeedback();
            } else {
                this.reportStatusEvent.setValue(ReportStatus.VALIDATION_ERROR);
            }
        }
    }

    public final List<String> getReasonStrings() {
        ArrayList arrayList = new ArrayList();
        Iterator<ReportReason> it = this.reportReasons.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getReasonString());
        }
        return arrayList;
    }

    public final void onReportActionChange(Integer index) {
        ReportAction reportAction;
        if (index == null) {
            reportAction = ReportAction.EXIT;
        } else if (index.intValue() >= 0) {
            this.selectedReasonIndex = index.intValue();
            reportAction = this.reportReasons.get(index.intValue()).getReportAction();
        } else {
            reportAction = ReportAction.NONE;
        }
        this.allowedAction = reportAction;
        this.reportActionEvent.setValue(reportAction);
    }

    public final void onReportTextChanged(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        this.reportText = text;
    }

    public final void close(LifecycleOwner owner) {
        Intrinsics.checkNotNullParameter(owner, "owner");
        this.reportStatusEvent.removeObservers(owner);
        this.reportActionEvent.removeObservers(owner);
        CoroutineScopeKt.cancel$default(this.coroutineScope, null, 1, null);
    }

    private final void submitLyricsFeedback() {
        ReportAction reportAction = ReportAction.NONE;
        this.allowedAction = reportAction;
        this.reportActionEvent.setValue(reportAction);
        this.reportStatusEvent.setValue(ReportStatus.PROCESSING);
        String str = this.reportText;
        if (str == null) {
            str = "";
        }
        BuildersKt__Builders_commonKt.launch$default(this.coroutineScope, null, null, new AnonymousClass1(str, null), 3, null);
    }

    /* JADX INFO: renamed from: com.amazon.mp3.lyrics.LyricsFeedbackOperations$submitLyricsFeedback$1, reason: invalid class name */
    /* JADX INFO: compiled from: LyricsFeedbackOperations.kt */
    @Metadata(bv = {}, d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\u008a@"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", "", "<anonymous>"}, k = 3, mv = {1, 6, 0})
    @DebugMetadata(c = "com.amazon.mp3.lyrics.LyricsFeedbackOperations$submitLyricsFeedback$1", f = "LyricsFeedbackOperations.kt", i = {}, l = {128}, m = "invokeSuspend", n = {}, s = {})
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $text;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$text = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LyricsFeedbackOperations.this.new AnonymousClass1(this.$text, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method and merged with bridge method [inline-methods] */
        public final Object mo4invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:26:0x0087  */
        /* JADX WARN: Removed duplicated region for block: B:29:0x0095  */
        /* JADX WARN: Removed duplicated region for block: B:30:0x00a1  */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r14) {
            /*
                r13 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r13.label
                r2 = 1
                r3 = 0
                if (r1 == 0) goto L19
                if (r1 != r2) goto L11
                kotlin.ResultKt.throwOnFailure(r14)
                goto L82
            L11:
                java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r14.<init>(r0)
                throw r14
            L19:
                kotlin.ResultKt.throwOnFailure(r14)
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                com.amazon.music.platform.providers.LyricsReportProvider r4 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getLyricsReportProvider$p(r14)
                if (r4 != 0) goto L26
                r14 = r3
                goto L84
            L26:
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                com.amazon.mp3.lyrics.item.LyricsFeedbackOperationsMetadata r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getFeedbackMetadata$p(r14)
                java.lang.String r1 = "feedbackMetadata"
                if (r14 != 0) goto L34
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r14 = r3
            L34:
                java.lang.String r5 = r14.getTrackAsin()
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                com.amazon.mp3.lyrics.item.LyricsFeedbackOperationsMetadata r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getFeedbackMetadata$p(r14)
                if (r14 != 0) goto L44
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r14 = r3
            L44:
                java.lang.String r6 = r14.getTrackTitle()
                java.util.concurrent.TimeUnit r14 = java.util.concurrent.TimeUnit.MILLISECONDS
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r7 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                com.amazon.mp3.lyrics.item.LyricsFeedbackOperationsMetadata r7 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getFeedbackMetadata$p(r7)
                if (r7 != 0) goto L56
                kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r1)
                r7 = r3
            L56:
                long r7 = r7.getReportTimeStamp()
                long r7 = r14.toSeconds(r7)
                double r7 = (double) r7
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                java.util.ArrayList r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getReportReasons$p(r14)
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r1 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                int r1 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getSelectedReasonIndex$p(r1)
                java.lang.Object r14 = r14.get(r1)
                com.amazon.mp3.lyrics.LyricsFeedbackOperations$ReportReason r14 = (com.amazon.mp3.lyrics.LyricsFeedbackOperations.ReportReason) r14
                java.lang.String r9 = r14.getReasonCode()
                java.lang.String r10 = r13.$text
                r11 = 0
                r13.label = r2
                r12 = r13
                java.lang.Object r14 = r4.submitCustomerReport(r5, r6, r7, r9, r10, r11, r12)
                if (r14 != r0) goto L82
                return r0
            L82:
                com.amazon.music.platform.lyricReport.data.LyricsReportResponse r14 = (com.amazon.music.platform.lyricReport.data.LyricsReportResponse) r14
            L84:
                if (r14 != 0) goto L87
                goto L8b
            L87:
                java.lang.Boolean r3 = r14.getReportStatus()
            L8b:
                java.lang.Boolean r14 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r2)
                boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual(r3, r14)
                if (r14 == 0) goto La1
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                androidx.lifecycle.MutableLiveData r14 = r14.getReportStatusEvent()
                com.amazon.mp3.lyrics.LyricsFeedbackOperations$ReportStatus r0 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.ReportStatus.SUBMIT_SUCCESS
                r14.postValue(r0)
                goto Lac
            La1:
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                androidx.lifecycle.MutableLiveData r14 = r14.getReportStatusEvent()
                com.amazon.mp3.lyrics.LyricsFeedbackOperations$ReportStatus r0 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.ReportStatus.SUBMIT_ERROR
                r14.postValue(r0)
            Lac:
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                com.amazon.mp3.lyrics.LyricsFeedbackOperations$ReportAction r0 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.ReportAction.EXIT
                com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$setAllowedAction$p(r14, r0)
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r14 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                androidx.lifecycle.MutableLiveData r14 = r14.getReportActionEvent()
                com.amazon.mp3.lyrics.LyricsFeedbackOperations r0 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.this
                com.amazon.mp3.lyrics.LyricsFeedbackOperations$ReportAction r0 = com.amazon.mp3.lyrics.LyricsFeedbackOperations.access$getAllowedAction$p(r0)
                r14.postValue(r0)
                kotlin.Unit r14 = kotlin.Unit.INSTANCE
                return r14
            */
            throw new UnsupportedOperationException("Method not decompiled: com.amazon.mp3.lyrics.LyricsFeedbackOperations.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    private final boolean validateReportText() {
        String str = this.reportText;
        return !(str == null || str.length() == 0);
    }

    private final void initializeStringIdToReasonCodeMapping(Resources resources) {
        try {
            XmlResourceParser xml = resources.getXml(R.xml.lyrics_feedback_string_id_to_reason_code_link);
            Intrinsics.checkNotNullExpressionValue(xml, "resources.getXml(R.xml.l…g_id_to_reason_code_link)");
            for (int next = xml.next(); next != 1; next = xml.next()) {
                if (next == 2) {
                    if (Intrinsics.areEqual("entry", xml.getName())) {
                        String stringId = xml.getAttributeValue(null, "key");
                        String reportActionText = xml.getAttributeValue(null, "allowedAction");
                        String reasonCode = xml.nextText();
                        Intrinsics.checkNotNullExpressionValue(stringId, "stringId");
                        String string = resources.getString(getResourceId(stringId, resources));
                        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(getR…eId(stringId, resources))");
                        ArrayList<ReportReason> arrayList = this.reportReasons;
                        Intrinsics.checkNotNullExpressionValue(reasonCode, "reasonCode");
                        Intrinsics.checkNotNullExpressionValue(reportActionText, "reportActionText");
                        String upperCase = reportActionText.toUpperCase(Locale.ROOT);
                        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
                        arrayList.add(new ReportReason(reasonCode, stringId, string, ReportAction.valueOf(upperCase)));
                    }
                }
            }
        } catch (IOException e) {
            Log.error(TAG, "Error while reading reason code from XML", e);
        } catch (XmlPullParserException e2) {
            Log.error(TAG, "Error while parsing reason code XML", e2);
        }
    }

    private final int getResourceId(String resourceName, Resources resources) {
        try {
            return resources.getIdentifier(resourceName, "string", this.currentContext.getPackageName());
        } catch (Exception e) {
            throw new RuntimeException(Intrinsics.stringPlus("No resource ID found for: ", resourceName), e);
        }
    }

    /* JADX INFO: compiled from: LyricsFeedbackOperations.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\u0006\u0010\r\u001a\u00020\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u0002\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u0016\u0010\u0017J\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\t\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u000f\u0010\n\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0012\u001a\u00020\u00118\u0006¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportReason;", "", "", "toString", "", "hashCode", "other", "", "equals", "reasonCode", "Ljava/lang/String;", "getReasonCode", "()Ljava/lang/String;", "stringId", "getStringId", "reasonString", "getReasonString", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "reportAction", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "getReportAction", "()Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final /* data */ class ReportReason {
        private final String reasonCode;
        private final String reasonString;
        private final ReportAction reportAction;
        private final String stringId;

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ReportReason)) {
                return false;
            }
            ReportReason reportReason = (ReportReason) other;
            return Intrinsics.areEqual(this.reasonCode, reportReason.reasonCode) && Intrinsics.areEqual(this.stringId, reportReason.stringId) && Intrinsics.areEqual(this.reasonString, reportReason.reasonString) && this.reportAction == reportReason.reportAction;
        }

        public int hashCode() {
            return (((((this.reasonCode.hashCode() * 31) + this.stringId.hashCode()) * 31) + this.reasonString.hashCode()) * 31) + this.reportAction.hashCode();
        }

        public String toString() {
            return "ReportReason(reasonCode=" + this.reasonCode + ", stringId=" + this.stringId + ", reasonString=" + this.reasonString + ", reportAction=" + this.reportAction + ')';
        }

        public ReportReason(String reasonCode, String stringId, String reasonString, ReportAction reportAction) {
            Intrinsics.checkNotNullParameter(reasonCode, "reasonCode");
            Intrinsics.checkNotNullParameter(stringId, "stringId");
            Intrinsics.checkNotNullParameter(reasonString, "reasonString");
            Intrinsics.checkNotNullParameter(reportAction, "reportAction");
            this.reasonCode = reasonCode;
            this.stringId = stringId;
            this.reasonString = reasonString;
            this.reportAction = reportAction;
        }

        public final String getReasonCode() {
            return this.reasonCode;
        }

        public final String getReasonString() {
            return this.reasonString;
        }

        public final ReportAction getReportAction() {
            return this.reportAction;
        }
    }
}
