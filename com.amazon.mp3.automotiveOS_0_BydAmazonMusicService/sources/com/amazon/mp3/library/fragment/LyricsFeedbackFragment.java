package com.amazon.mp3.library.fragment;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.core.app.NotificationCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.lyrics.LyricsFeedbackOperations;
import com.amazon.mp3.lyrics.item.LyricsFeedbackOperationsMetadata;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.destination.parser.ParserUtil;
import com.amazon.music.explore.events.SingleLiveEvent;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.music.views.library.views.BauhausLoadingView;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import rx.functions.Action1;

/* JADX INFO: compiled from: LyricsFeedbackFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 e2\u00020\u0001:\u0002deB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010$\u001a\u00020%2\u0006\u0010&\u001a\u00020\u000bH\u0002J\b\u0010'\u001a\u00020%H\u0002J\u0012\u0010(\u001a\u00020%2\b\u0010)\u001a\u0004\u0018\u00010\u001cH\u0002J\n\u0010*\u001a\u0004\u0018\u00010\u0006H\u0002J.\u0010+\u001a\u00020,2\u0006\u0010-\u001a\u00020\u001c2\u0006\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020/2\f\u00101\u001a\b\u0012\u0004\u0012\u00020%02H\u0002J3\u00103\u001a\b\u0012\u0004\u0012\u00020,042\u0006\u00105\u001a\u00020\u00172\u0016\u00106\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u001c07\"\u0004\u0018\u00010\u001cH\u0002¢\u0006\u0002\u00108J\b\u00109\u001a\u00020%H\u0002J&\u0010:\u001a\u00020%2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010>\u001a\u00020?2\u0006\u0010\b\u001a\u00020\tJ\u0010\u0010@\u001a\u00020%2\u0006\u0010A\u001a\u00020BH\u0002J\u0010\u0010C\u001a\u00020%2\u0006\u0010A\u001a\u00020BH\u0002J\b\u0010D\u001a\u00020%H\u0002J\b\u0010E\u001a\u00020%H\u0002J\u0010\u0010F\u001a\u00020%2\u0006\u0010A\u001a\u00020BH\u0002J\b\u0010G\u001a\u00020%H\u0002J\b\u0010H\u001a\u00020%H\u0002J\u0010\u0010I\u001a\u00020%2\u0006\u0010J\u001a\u00020KH\u0002J\b\u0010L\u001a\u00020%H\u0002J&\u0010M\u001a\u0004\u0018\u00010\u001c2\u0006\u0010N\u001a\u00020O2\b\u0010P\u001a\u0004\u0018\u00010Q2\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\b\u0010T\u001a\u00020%H\u0016J\u0010\u0010U\u001a\u00020%2\u0006\u0010V\u001a\u00020\u001cH\u0002J\u0010\u0010W\u001a\u00020%2\u0006\u0010X\u001a\u00020YH\u0002J\u0010\u0010Z\u001a\u00020%2\u0006\u0010J\u001a\u00020KH\u0002J\u001a\u0010[\u001a\u00020%2\u0006\u0010-\u001a\u00020\u001c2\b\u0010R\u001a\u0004\u0018\u00010SH\u0016J\u0010\u0010\\\u001a\u00020%2\u0006\u0010V\u001a\u00020]H\u0002J\u0018\u0010^\u001a\u00020%2\u0006\u0010V\u001a\u00020]2\u0006\u0010_\u001a\u00020<H\u0002J\u0016\u0010`\u001a\u00020%2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020<04H\u0002J\b\u0010b\u001a\u00020%H\u0002J\b\u0010c\u001a\u00020%H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\u000e\u001a\u0004\u0018\u00010\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\"\u001a\u0004\u0018\u00010#X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"Lcom/amazon/mp3/library/fragment/LyricsFeedbackFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "actionButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "animatorSet", "Landroid/animation/AnimatorSet;", "closeButton", "closeCallback", "Lcom/amazon/mp3/library/fragment/LyricsFeedbackFragment$CloseCallback;", "currentSelection", "", "feedbackMetadata", "Lcom/amazon/mp3/lyrics/item/LyricsFeedbackOperationsMetadata;", "feedbackOperations", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations;", "getFeedbackOperations", "()Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations;", "feedbackOperations$delegate", "Lkotlin/Lazy;", "feedbackOptionsGroup", "Landroid/widget/RadioGroup;", "isFeedbackOperationsInitialized", "", "isTextBoxHighlighted", "loadingView", "Lcom/amazon/music/views/library/views/BauhausLoadingView;", "messageView", "Landroid/view/View;", "moreDetailsPrompt", "Landroid/widget/TextView;", "reportView", "textBoxDrawable", "Landroid/graphics/drawable/GradientDrawable;", "textbox", "Landroid/widget/EditText;", "changeTextBoxColor", "", "color", "closeFeedbackFragment", "closeKeyboard", "focusedView", "createAnimations", "fadeAnimation", "Landroid/animation/Animator;", "view", "begin", "", "end", "animationEndCallback", "Lkotlin/Function0;", "getToggleVisibilityAnimators", "", "show", "views", "", "(Z[Landroid/view/View;)Ljava/util/List;", "highlightTextBox", "initialize", ParserUtil.TRACK_ASIN_QUERY_PARAM_NAME, "", "trackTitle", "currentTimestamp", "", "initializeActionButton", "styleSheet", "Lcom/amazon/music/views/library/styles/StyleSheet;", "initializeCloseButton", "initializeEventObservers", "initializeFeedbackOptions", "initializeLoadingWidget", "initializeTextBox", "initializeViewStyling", "invokeMessageDialog", NotificationCompat.CATEGORY_STATUS, "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportStatus;", "onActionButtonClicked", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroyView", "onRadioButtonClicked", "radioButton", "onReportActionChange", "reportAction", "Lcom/amazon/mp3/lyrics/LyricsFeedbackOperations$ReportAction;", "onReportStatusChange", "onViewCreated", "setRadioButtonParams", "Landroid/widget/RadioButton;", "setRadioButtonText", "text", "setupRadioButtons", "feedbackOptions", "showLoadingWidget", "showMessageDialog", "CloseCallback", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class LyricsFeedbackFragment extends BottomSheetDialogFragment {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG;
    private BaseButton actionButton;
    private AnimatorSet animatorSet;
    private BaseButton closeButton;
    private CloseCallback closeCallback;
    private LyricsFeedbackOperationsMetadata feedbackMetadata;
    private RadioGroup feedbackOptionsGroup;
    private boolean isFeedbackOperationsInitialized;
    private boolean isTextBoxHighlighted;
    private BauhausLoadingView loadingView;
    private View messageView;
    private TextView moreDetailsPrompt;
    private View reportView;
    private GradientDrawable textBoxDrawable;
    private EditText textbox;

    /* JADX INFO: renamed from: feedbackOperations$delegate, reason: from kotlin metadata */
    private final Lazy feedbackOperations = LazyKt__LazyJVMKt.lazy(new Function0<LyricsFeedbackOperations>() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$feedbackOperations$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final LyricsFeedbackOperations invoke() {
            Context context = this.this$0.getContext();
            if (context == null) {
                return null;
            }
            return new LyricsFeedbackOperations(context);
        }
    });
    private int currentSelection = -1;

    /* JADX INFO: compiled from: LyricsFeedbackFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/library/fragment/LyricsFeedbackFragment$CloseCallback;", "", "onClose", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface CloseCallback {
        void onClose();
    }

    /* JADX INFO: compiled from: LyricsFeedbackFragment.kt */
    @Metadata(k = 3, mv = {1, 6, 0}, xi = 48)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[LyricsFeedbackOperations.ReportAction.values().length];
            iArr[LyricsFeedbackOperations.ReportAction.NONE.ordinal()] = 1;
            iArr[LyricsFeedbackOperations.ReportAction.SUBMIT.ordinal()] = 2;
            iArr[LyricsFeedbackOperations.ReportAction.SUBMIT_WITH_TEXT.ordinal()] = 3;
            iArr[LyricsFeedbackOperations.ReportAction.EXIT.ordinal()] = 4;
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LyricsFeedbackOperations.ReportStatus.values().length];
            iArr2[LyricsFeedbackOperations.ReportStatus.PROCESSING.ordinal()] = 1;
            iArr2[LyricsFeedbackOperations.ReportStatus.VALIDATION_ERROR.ordinal()] = 2;
            iArr2[LyricsFeedbackOperations.ReportStatus.SUBMIT_SUCCESS.ordinal()] = 3;
            iArr2[LyricsFeedbackOperations.ReportStatus.SUBMIT_ERROR.ordinal()] = 4;
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    /* JADX INFO: compiled from: LyricsFeedbackFragment.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lcom/amazon/mp3/library/fragment/LyricsFeedbackFragment$Companion;", "", "()V", "TAG", "", "getTAG", "()Ljava/lang/String;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final String getTAG() {
            return LyricsFeedbackFragment.TAG;
        }
    }

    static {
        String simpleName = LyricsFeedbackFragment.class.getSimpleName();
        Intrinsics.checkNotNullExpressionValue(simpleName, "LyricsFeedbackFragment::class.java.simpleName");
        TAG = simpleName;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LyricsFeedbackOperations getFeedbackOperations() {
        return (LyricsFeedbackOperations) this.feedbackOperations.getValue();
    }

    public final void initialize(String trackAsin, String trackTitle, long currentTimestamp, CloseCallback closeCallback) {
        LyricsFeedbackOperations feedbackOperations;
        Intrinsics.checkNotNullParameter(trackAsin, "trackAsin");
        Intrinsics.checkNotNullParameter(trackTitle, "trackTitle");
        Intrinsics.checkNotNullParameter(closeCallback, "closeCallback");
        this.closeCallback = closeCallback;
        this.feedbackMetadata = new LyricsFeedbackOperationsMetadata(trackAsin, trackTitle, currentTimestamp);
        if (getContext() == null) {
            return;
        }
        this.isFeedbackOperationsInitialized = true;
        LyricsFeedbackOperationsMetadata lyricsFeedbackOperationsMetadata = this.feedbackMetadata;
        if (lyricsFeedbackOperationsMetadata == null || (feedbackOperations = getFeedbackOperations()) == null) {
            return;
        }
        feedbackOperations.initializeOperation(lyricsFeedbackOperationsMetadata);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Window window;
        Window window2;
        Window window3;
        View decorView;
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        final View viewInflate = inflater.inflate(R.layout.fragment_lyrics_feedback, container, false);
        if (Build.VERSION.SDK_INT >= 30) {
            Dialog dialog = getDialog();
            if (dialog != null && (window3 = dialog.getWindow()) != null && (decorView = window3.getDecorView()) != null) {
                decorView.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda0
                    @Override // android.view.View.OnApplyWindowInsetsListener
                    public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                        return LyricsFeedbackFragment.m1250onCreateView$lambda3$lambda2(viewInflate, view, windowInsets);
                    }
                });
            }
            Dialog dialog2 = getDialog();
            if (dialog2 != null && (window2 = dialog2.getWindow()) != null) {
                window2.setDecorFitsSystemWindows(false);
            }
        } else {
            Dialog dialog3 = getDialog();
            if (dialog3 != null && (window = dialog3.getWindow()) != null) {
                window.setSoftInputMode(16);
            }
        }
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onCreateView$lambda-3$lambda-2, reason: not valid java name */
    public static final WindowInsets m1250onCreateView$lambda3$lambda2(View view, View view2, WindowInsets windowInsets) {
        view.setPadding(0, 0, 0, windowInsets.getInsets(WindowInsetsCompat.Type.ime()).bottom);
        return windowInsets;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Drawable background;
        LyricsFeedbackOperationsMetadata lyricsFeedbackOperationsMetadata;
        LyricsFeedbackOperations feedbackOperations;
        Intrinsics.checkNotNullParameter(view, "view");
        super.onViewCreated(view, savedInstanceState);
        if (!this.isFeedbackOperationsInitialized && (lyricsFeedbackOperationsMetadata = this.feedbackMetadata) != null && (feedbackOperations = getFeedbackOperations()) != null) {
            feedbackOperations.initializeOperation(lyricsFeedbackOperationsMetadata);
        }
        this.reportView = view.findViewById(R.id.lyrics_feedback_report_popup);
        this.messageView = view.findViewById(R.id.lyrics_feedback_message_dialog);
        this.closeButton = (BaseButton) view.findViewById(R.id.lyrics_feedback_close_button);
        this.feedbackOptionsGroup = (RadioGroup) view.findViewById(R.id.lyrics_feedback_radio_group);
        this.actionButton = (BaseButton) view.findViewById(R.id.lyrics_feedback_action_button);
        EditText editText = (EditText) view.findViewById(R.id.lyrics_feedback_text_box);
        this.textbox = editText;
        Drawable drawableMutate = null;
        if (editText != null && (background = editText.getBackground()) != null) {
            drawableMutate = background.mutate();
        }
        if (drawableMutate == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.graphics.drawable.GradientDrawable");
        }
        this.textBoxDrawable = (GradientDrawable) drawableMutate;
        this.moreDetailsPrompt = (TextView) view.findViewById(R.id.lyrics_feedback_more_details_prompt);
        this.loadingView = (BauhausLoadingView) view.findViewById(R.id.lyrics_feedback_loading_widget);
        initializeEventObservers();
        initializeViewStyling();
    }

    private final void initializeEventObservers() {
        SingleLiveEvent<Boolean> feedbackCloseEvent;
        MutableLiveData<LyricsFeedbackOperations.ReportAction> reportActionEvent;
        MutableLiveData<LyricsFeedbackOperations.ReportStatus> reportStatusEvent;
        LyricsFeedbackOperations feedbackOperations = getFeedbackOperations();
        if (feedbackOperations != null && (reportStatusEvent = feedbackOperations.getReportStatusEvent()) != null) {
            reportStatusEvent.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda2
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    LyricsFeedbackFragment.m1245initializeEventObservers$lambda5(this.f$0, (LyricsFeedbackOperations.ReportStatus) obj);
                }
            });
        }
        LyricsFeedbackOperations feedbackOperations2 = getFeedbackOperations();
        if (feedbackOperations2 != null && (reportActionEvent = feedbackOperations2.getReportActionEvent()) != null) {
            reportActionEvent.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda3
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    LyricsFeedbackFragment.m1246initializeEventObservers$lambda6(this.f$0, (LyricsFeedbackOperations.ReportAction) obj);
                }
            });
        }
        LyricsFeedbackOperations feedbackOperations3 = getFeedbackOperations();
        if (feedbackOperations3 == null || (feedbackCloseEvent = feedbackOperations3.getFeedbackCloseEvent()) == null) {
            return;
        }
        feedbackCloseEvent.observe(getViewLifecycleOwner(), new Observer() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                LyricsFeedbackFragment.m1247initializeEventObservers$lambda7(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeEventObservers$lambda-5, reason: not valid java name */
    public static final void m1245initializeEventObservers$lambda5(LyricsFeedbackFragment this$0, LyricsFeedbackOperations.ReportStatus status) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(status, "status");
        this$0.onReportStatusChange(status);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeEventObservers$lambda-6, reason: not valid java name */
    public static final void m1246initializeEventObservers$lambda6(LyricsFeedbackFragment this$0, LyricsFeedbackOperations.ReportAction action) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(action, "action");
        this$0.onReportActionChange(action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeEventObservers$lambda-7, reason: not valid java name */
    public static final void m1247initializeEventObservers$lambda7(LyricsFeedbackFragment this$0, Boolean shouldClose) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(shouldClose, "shouldClose");
        if (shouldClose.booleanValue()) {
            this$0.closeFeedbackFragment();
        }
    }

    private final void initializeViewStyling() {
        StyleSheetProvider.getStyleSheetObservable().subscribe(new Action1() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda1
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                LyricsFeedbackFragment.m1249initializeViewStyling$lambda8(this.f$0, (StyleSheet) obj);
            }
        });
        initializeFeedbackOptions();
        initializeTextBox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeViewStyling$lambda-8, reason: not valid java name */
    public static final void m1249initializeViewStyling$lambda8(LyricsFeedbackFragment this$0, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(styleSheet, "styleSheet");
        this$0.initializeActionButton(styleSheet);
        this$0.initializeCloseButton(styleSheet);
        this$0.initializeLoadingWidget(styleSheet);
    }

    private final void initializeTextBox() {
        EditText editText = this.textbox;
        if (editText != null) {
            editText.addTextChangedListener(new TextWatcher() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$initializeTextBox$$inlined$addTextChangedListener$default$1
                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence text, int start, int count, int after) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence text, int start, int before, int count) {
                }

                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable s) {
                    if (this.this$0.isTextBoxHighlighted) {
                        this.this$0.changeTextBoxColor(R.color.lyrics_feedback_text_box_stroke_color);
                        this.this$0.isTextBoxHighlighted = false;
                    }
                    LyricsFeedbackOperations feedbackOperations = this.this$0.getFeedbackOperations();
                    if (feedbackOperations == null) {
                        return;
                    }
                    feedbackOperations.onReportTextChanged(String.valueOf(s));
                }
            });
        }
        EditText editText2 = this.textbox;
        if (editText2 == null) {
            return;
        }
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda6
            @Override // android.view.View.OnFocusChangeListener
            public final void onFocusChange(View view, boolean z) {
                LyricsFeedbackFragment.m1248initializeTextBox$lambda10(this.f$0, view, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeTextBox$lambda-10, reason: not valid java name */
    public static final void m1248initializeTextBox$lambda10(LyricsFeedbackFragment this$0, View view, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (z) {
            return;
        }
        this$0.closeKeyboard(view);
    }

    private final void initializeActionButton(StyleSheet styleSheet) {
        BaseButton.StyleBuilder buttonBuilder;
        BaseButton baseButton = this.actionButton;
        if (baseButton != null && (buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.MEDIUM, ButtonStyleKey.SOLID)) != null) {
            buttonBuilder.applyStyle(baseButton);
        }
        BaseButton baseButton2 = this.actionButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsFeedbackFragment.m1243initializeActionButton$lambda12(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeActionButton$lambda-12, reason: not valid java name */
    public static final void m1243initializeActionButton$lambda12(LyricsFeedbackFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.onActionButtonClicked();
    }

    private final void initializeLoadingWidget(StyleSheet styleSheet) {
        BauhausLoadingView bauhausLoadingView = this.loadingView;
        if (bauhausLoadingView != null) {
            bauhausLoadingView.initStyle(styleSheet);
        }
        BauhausLoadingView bauhausLoadingView2 = this.loadingView;
        if (bauhausLoadingView2 == null) {
            return;
        }
        String string = getResources().getString(R.string.dmusic_status_loading);
        Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.st…ng.dmusic_status_loading)");
        bauhausLoadingView2.setText(string);
    }

    private final void onActionButtonClicked() {
        closeKeyboard(getView());
        LyricsFeedbackOperations feedbackOperations = getFeedbackOperations();
        if (feedbackOperations == null) {
            return;
        }
        feedbackOperations.performReportAction();
    }

    private final void initializeFeedbackOptions() {
        LyricsFeedbackOperations feedbackOperations = getFeedbackOperations();
        List<String> reasonStrings = feedbackOperations == null ? null : feedbackOperations.getReasonStrings();
        if (reasonStrings != null) {
            setupRadioButtons(reasonStrings);
        }
    }

    private final void setupRadioButtons(List<String> feedbackOptions) {
        int i = 0;
        for (String str : feedbackOptions) {
            int i2 = i + 1;
            View viewInflate = getLayoutInflater().inflate(R.layout.lyrics_feedback_options, (ViewGroup) null);
            if (viewInflate == null) {
                throw new NullPointerException("null cannot be cast to non-null type android.widget.RadioButton");
            }
            RadioButton radioButton = (RadioButton) viewInflate;
            radioButton.setTag(Integer.valueOf(i));
            setRadioButtonText(radioButton, str);
            setRadioButtonParams(radioButton);
            radioButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    LyricsFeedbackFragment.m1251setupRadioButtons$lambda13(this.f$0, view);
                }
            });
            RadioGroup radioGroup = this.feedbackOptionsGroup;
            if (radioGroup != null) {
                radioGroup.addView(radioButton);
            }
            i = i2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupRadioButtons$lambda-13, reason: not valid java name */
    public static final void m1251setupRadioButtons$lambda13(LyricsFeedbackFragment this$0, View it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.onRadioButtonClicked(it);
    }

    private final void onRadioButtonClicked(View radioButton) {
        int iIntValue;
        Editable text;
        int i = this.currentSelection;
        Object tag = radioButton.getTag();
        if ((tag instanceof Integer) && i == ((Number) tag).intValue()) {
            RadioGroup radioGroup = this.feedbackOptionsGroup;
            if (radioGroup != null) {
                radioGroup.clearCheck();
            }
            closeKeyboard(getView());
            iIntValue = -1;
        } else {
            Object tag2 = radioButton.getTag();
            if (tag2 == null) {
                throw new NullPointerException("null cannot be cast to non-null type kotlin.Int");
            }
            iIntValue = ((Integer) tag2).intValue();
        }
        this.currentSelection = iIntValue;
        EditText editText = this.textbox;
        if (editText != null && (text = editText.getText()) != null) {
            text.clear();
        }
        LyricsFeedbackOperations feedbackOperations = getFeedbackOperations();
        if (feedbackOperations == null) {
            return;
        }
        feedbackOperations.onReportActionChange(Integer.valueOf(this.currentSelection));
    }

    private final void onReportActionChange(LyricsFeedbackOperations.ReportAction reportAction) {
        if (this.isTextBoxHighlighted) {
            changeTextBoxColor(R.color.lyrics_feedback_text_box_stroke_color);
            this.isTextBoxHighlighted = false;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[reportAction.ordinal()];
        if (i == 1) {
            BaseButton baseButton = this.actionButton;
            if (baseButton != null) {
                baseButton.setEnabled(false);
            }
            EditText editText = this.textbox;
            if (editText != null) {
                editText.setEnabled(false);
            }
            BaseButton baseButton2 = this.actionButton;
            if (baseButton2 == null) {
                return;
            }
            String string = getResources().getString(R.string.dmusic_button_submit);
            Intrinsics.checkNotNullExpressionValue(string, "resources.getString(R.string.dmusic_button_submit)");
            baseButton2.setText(string);
            return;
        }
        if (i == 2 || i == 3) {
            BaseButton baseButton3 = this.actionButton;
            if (baseButton3 != null) {
                baseButton3.setEnabled(true);
            }
            EditText editText2 = this.textbox;
            if (editText2 != null) {
                editText2.setEnabled(true);
            }
            BaseButton baseButton4 = this.actionButton;
            if (baseButton4 == null) {
                return;
            }
            String string2 = getResources().getString(R.string.dmusic_button_submit);
            Intrinsics.checkNotNullExpressionValue(string2, "resources.getString(R.string.dmusic_button_submit)");
            baseButton4.setText(string2);
            return;
        }
        if (i != 4) {
            return;
        }
        BaseButton baseButton5 = this.actionButton;
        if (baseButton5 != null) {
            baseButton5.setEnabled(true);
        }
        EditText editText3 = this.textbox;
        if (editText3 != null) {
            editText3.setEnabled(true);
        }
        BaseButton baseButton6 = this.actionButton;
        if (baseButton6 == null) {
            return;
        }
        String string3 = getResources().getString(R.string.dmusic_button_done);
        Intrinsics.checkNotNullExpressionValue(string3, "resources.getString(R.string.dmusic_button_done)");
        baseButton6.setText(string3);
    }

    private final void setRadioButtonText(RadioButton radioButton, String text) {
        radioButton.setTypeface(Typeface.createFromAsset(getResources().getAssets(), getResources().getString(R.string.font_amazon_ember_regular)));
        radioButton.setText(text);
    }

    private final void setRadioButtonParams(RadioButton radioButton) {
        radioButton.setLayoutParams(new RadioGroup.LayoutParams(-2, getResources().getDimensionPixelSize(R.dimen.listview_standard_height)));
    }

    private final void initializeCloseButton(StyleSheet styleSheet) {
        BaseButton baseButton;
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_action_cancel, null);
        if (drawable != null && (baseButton = this.closeButton) != null && (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.PRIMARY)) != null && (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) != null) {
            styleBuilderWithIcon.applyStyle(baseButton);
        }
        BaseButton baseButton2 = this.closeButton;
        if (baseButton2 == null) {
            return;
        }
        baseButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LyricsFeedbackFragment.m1244initializeCloseButton$lambda16(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCloseButton$lambda-16, reason: not valid java name */
    public static final void m1244initializeCloseButton$lambda16(LyricsFeedbackFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.closeFeedbackFragment();
    }

    private final void closeFeedbackFragment() {
        CloseCallback closeCallback = this.closeCallback;
        if (closeCallback == null) {
            Intrinsics.throwUninitializedPropertyAccessException("closeCallback");
            closeCallback = null;
        }
        closeCallback.onClose();
    }

    private final void closeKeyboard(View focusedView) {
        Context context = getContext();
        if (context == null) {
            return;
        }
        Object systemService = context.getSystemService("input_method");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.inputmethod.InputMethodManager");
        }
        InputMethodManager inputMethodManager = (InputMethodManager) systemService;
        if (inputMethodManager.isAcceptingText()) {
            inputMethodManager.hideSoftInputFromWindow(focusedView == null ? null : focusedView.getWindowToken(), 0);
            if (focusedView == null) {
                return;
            }
            focusedView.clearFocus();
        }
    }

    private final List<Animator> getToggleVisibilityAnimators(final boolean show, View... views) {
        ArrayList arrayList = new ArrayList();
        int length = views.length;
        int i = 0;
        while (i < length) {
            final View view = views[i];
            i++;
            float f = 0.0f;
            float f2 = 1.0f;
            if (!show) {
                f2 = 0.0f;
                f = 1.0f;
            } else if (view != null) {
                view.setVisibility(0);
            }
            if (view != null) {
                arrayList.add(fadeAnimation(view, f, f2, new Function0<Unit>() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$getToggleVisibilityAnimators$1$1$animation$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
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
                        if (show) {
                            return;
                        }
                        view.setVisibility(4);
                    }
                }));
            }
        }
        return arrayList;
    }

    private final void onReportStatusChange(LyricsFeedbackOperations.ReportStatus status) {
        int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
        if (i == 1) {
            showLoadingWidget();
        } else if (i == 2) {
            highlightTextBox();
        } else {
            invokeMessageDialog(status);
        }
    }

    private final void highlightTextBox() {
        changeTextBoxColor(R.color.bauhaus_error);
        this.isTextBoxHighlighted = true;
    }

    private final void showLoadingWidget() {
        AnimatorSet animatorSetCreateAnimations = createAnimations();
        if (animatorSetCreateAnimations == null) {
            return;
        }
        animatorSetCreateAnimations.playTogether(CollectionsKt___CollectionsKt.plus((Collection) getToggleVisibilityAnimators(false, this.reportView, this.actionButton), (Iterable) getToggleVisibilityAnimators(true, this.loadingView)));
        animatorSetCreateAnimations.start();
    }

    private final void invokeMessageDialog(LyricsFeedbackOperations.ReportStatus status) {
        View view = this.messageView;
        if (view == null) {
            return;
        }
        TextView textView = (TextView) view.findViewById(R.id.message_header);
        TextView textView2 = (TextView) view.findViewById(R.id.message_text);
        int i = WhenMappings.$EnumSwitchMapping$1[status.ordinal()];
        if (i == 3) {
            textView.setText(view.getResources().getString(R.string.dmusic_generic_thankyou));
            textView2.setText(view.getResources().getString(R.string.lyrics_hmd_acknowledge_report_success));
        } else if (i == 4) {
            textView.setText(view.getResources().getString(R.string.dmusic_generic_error));
            textView2.setText(view.getResources().getString(R.string.lyrics_hmd_something_went_wrong));
        }
        showMessageDialog();
    }

    private final void showMessageDialog() {
        AnimatorSet animatorSetCreateAnimations = createAnimations();
        if (animatorSetCreateAnimations == null) {
            return;
        }
        animatorSetCreateAnimations.playTogether(CollectionsKt___CollectionsKt.plus((Collection) getToggleVisibilityAnimators(false, this.loadingView), (Iterable) getToggleVisibilityAnimators(true, this.messageView, this.actionButton)));
        animatorSetCreateAnimations.start();
    }

    private final AnimatorSet createAnimations() {
        AnimatorSet animatorSet;
        AnimatorSet animatorSet2 = this.animatorSet;
        boolean z = false;
        if (animatorSet2 != null && animatorSet2.isStarted()) {
            z = true;
        }
        if (z && (animatorSet = this.animatorSet) != null) {
            animatorSet.cancel();
        }
        AnimatorSet animatorSet3 = new AnimatorSet();
        this.animatorSet = animatorSet3;
        return animatorSet3;
    }

    private final Animator fadeAnimation(View view, float begin, float end, final Function0<Unit> animationEndCallback) {
        final ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", begin, end);
        objectAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.amazon.mp3.library.fragment.LyricsFeedbackFragment$fadeAnimation$1$1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                Intrinsics.checkNotNullParameter(animation, "animation");
                super.onAnimationEnd(animation);
                animationEndCallback.invoke();
                objectAnimatorOfFloat.removeListener(this);
            }
        });
        Intrinsics.checkNotNullExpressionValue(objectAnimatorOfFloat, "ofFloat(view, \"alpha\", b…\n            })\n        }");
        return objectAnimatorOfFloat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeTextBoxColor(int color) {
        GradientDrawable gradientDrawable = this.textBoxDrawable;
        if (gradientDrawable == null) {
            return;
        }
        gradientDrawable.setStroke(getResources().getDimensionPixelSize(R.dimen.spacing_2dp), ResourcesCompat.getColor(getResources(), color, null));
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        RadioGroup radioGroup = this.feedbackOptionsGroup;
        if (radioGroup != null) {
            radioGroup.removeAllViews();
        }
        LyricsFeedbackOperations feedbackOperations = getFeedbackOperations();
        if (feedbackOperations != null) {
            LifecycleOwner viewLifecycleOwner = getViewLifecycleOwner();
            Intrinsics.checkNotNullExpressionValue(viewLifecycleOwner, "viewLifecycleOwner");
            feedbackOperations.close(viewLifecycleOwner);
        }
        super.onDestroyView();
    }
}
