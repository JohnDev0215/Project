package com.amazon.music.voice.ui.scrim;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.FloatRange;
import androidx.annotation.NonNull;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.curate.skyfire.bootstrap.ContextMappingConstants;
import com.amazon.music.events.NavigationAppEvent;
import com.amazon.music.events.UserInteractionAppEvent;
import com.amazon.music.find.metrics.SearchMetricsServiceDefault;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.voice.AlexaAccessProvider;
import com.amazon.music.voice.R$id;
import com.amazon.music.voice.R$layout;
import com.amazon.music.voice.R$string;
import com.amazon.music.voice.ui.AlexaHintClickListener;
import com.amazon.music.voice.ui.AlexaHintsAdapter;
import com.amazon.music.voice.ui.AlexaHintsItemDecoration;
import com.amazon.music.voice.ui.AlexaHintsViewModel;
import com.amazon.music.voice.ui.AlexaHintsViewModelProvider;
import com.amazon.music.voice.ui.AlexaUIStyleProvider;
import com.amazon.music.voice.ui.AlexaUiListener;
import com.amazon.music.voice.ui.ScrimControllerState;
import com.amazon.music.voice.ui.ViewController;
import com.amazon.music.voice.ui.util.AnimationUtils;
import com.amazon.music.voice.ui.widget.ChromeView;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.utils.ScreenUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.TimeUnit;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
public final class ScrimController extends ViewController implements ScrimContract$View {
    private Activity activity;
    private AlexaAccessProvider alexaAccessProvider;
    private ImageView alexaIconImageView;
    private String associateTag;
    private View chromeBorder;
    private ChromeView chromeView;
    private Context context;
    private View errorMessageContainer;
    private View hintsContainer;
    private AlexaHintsAdapter mAlexaHintsAdapter;
    private AlexaHintsScrollState mAlexaHintsScrollState;
    private AlexaHintsViewModel mAlexaHintsViewModel;
    private AlexaHintsViewModelProvider mAlexaHintsViewModelProvider;
    private AlexaUIStyleProvider mAlexaUIStyleProvider;
    private View mButtonsContainer;
    private TextView mHintsPrefix;
    private RecyclerView mHintsRecyclerView;
    private BaseButton mNegativeButton;
    private BaseButton mPositiveButton;
    private TextView messageTextView;
    private ScrimContract$Presenter presenter;
    private String refMarker;
    private static final Logger LOG = LoggerFactory.getLogger(ScrimController.class.getSimpleName());
    private static final long ANIMATION_PERIOD = TimeUnit.SECONDS.toMillis(2);
    private final String SCRIM_CONTROLLER_STATE = "scrim_controller_state";
    private final List<AlexaHintClickListener> mAlexaHintClickListeners = new ArrayList();
    private Set<AlexaUiListener> alexaUiListeners = new CopyOnWriteArraySet();
    private PositiveButtonDestination positiveButtonDestination = PositiveButtonDestination.NONE;
    private AnimationState animationState = AnimationState.NONE;
    private Handler handler = new Handler(Looper.getMainLooper());
    private Runnable blinkingAnimator = new Runnable() { // from class: com.amazon.music.voice.ui.scrim.ScrimController.1
        @Override // java.lang.Runnable
        public void run() {
            ScrimController.this.updateIconAlpha();
        }
    };

    public enum AnimationState {
        NONE,
        LISTENING,
        PROCESSING,
        RESPONDING
    }

    public enum PositiveButtonDestination {
        NETWORK_SETTINGS,
        NONE
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$onCreateView$1(View view) {
    }

    @Override // com.amazon.music.voice.ui.ViewController
    protected void onCreate() {
    }

    public ScrimController(Context context) {
        this.context = context;
    }

    @Override // com.amazon.music.voice.ui.ViewController
    @NonNull
    protected View onCreateView(@NonNull LayoutInflater layoutInflater, @NonNull ViewGroup viewGroup) {
        this.presenter = new ScrimPresenter(this, new ScrimInteractor(getComponent().getScrimProvider(), new ScrimMediator(this)));
        View viewInflate = layoutInflater.inflate(R$layout.alexa_ui, viewGroup, false);
        View viewFindViewById = viewInflate.findViewById(R$id.content_view);
        this.chromeView = (ChromeView) viewInflate.findViewById(R$id.chrome);
        this.chromeBorder = viewInflate.findViewById(R$id.chrome_border);
        this.hintsContainer = viewInflate.findViewById(R$id.hints_container);
        this.mHintsPrefix = (TextView) viewInflate.findViewById(R$id.tv_hint_prefix);
        RecyclerView recyclerView = (RecyclerView) viewInflate.findViewById(R$id.hints_list);
        this.mHintsRecyclerView = recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this.context, 0, false));
        AlexaHintsAdapter alexaHintsAdapter = new AlexaHintsAdapter(this.mAlexaUIStyleProvider, new Function1() { // from class: com.amazon.music.voice.ui.scrim.ScrimController$$ExternalSyntheticLambda1
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$onCreateView$0((String) obj);
            }
        });
        this.mAlexaHintsAdapter = alexaHintsAdapter;
        this.mHintsRecyclerView.setAdapter(alexaHintsAdapter);
        this.mHintsRecyclerView.addItemDecoration(new AlexaHintsItemDecoration());
        this.mHintsRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.music.voice.ui.scrim.ScrimController.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i) {
                super.onScrollStateChanged(recyclerView2, i);
                if (i == 1) {
                    ScrimController.this.mAlexaHintsScrollState.setScrolling(true);
                } else {
                    ScrimController.this.mAlexaHintsScrollState.setScrolling(false);
                    ScrimController.this.mAlexaHintsScrollState.setLastScrollTime(System.currentTimeMillis());
                }
            }
        });
        this.mAlexaHintsScrollState = new AlexaHintsScrollState(false, 0L);
        this.errorMessageContainer = viewInflate.findViewById(R$id.error_message_container);
        this.messageTextView = (TextView) viewInflate.findViewById(R$id.tv_message);
        this.mButtonsContainer = viewInflate.findViewById(R$id.buttons_container);
        this.mNegativeButton = (BaseButton) viewInflate.findViewById(R$id.btn_negative);
        this.mPositiveButton = (BaseButton) viewInflate.findViewById(R$id.btn_positive);
        this.alexaIconImageView = (ImageView) viewInflate.findViewById(R$id.iv_alexa);
        configurePositiveButton(this.mPositiveButton);
        configureNegativeButton(this.mNegativeButton);
        FontStyle alexaTextFontStyle = this.mAlexaUIStyleProvider.getAlexaTextFontStyle();
        if (alexaTextFontStyle != null) {
            FontUtil fontUtil = FontUtil.INSTANCE;
            fontUtil.applyFontStyle(this.mHintsPrefix, alexaTextFontStyle);
            fontUtil.applyFontStyle(this.messageTextView, alexaTextFontStyle);
        }
        addChromeTopMarginIfRequired();
        viewFindViewById.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.voice.ui.scrim.ScrimController$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ScrimController.lambda$onCreateView$1(view);
            }
        });
        viewInflate.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.voice.ui.scrim.ScrimController.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (ScrimController.this.errorMessageContainer == null || ScrimController.this.errorMessageContainer.getVisibility() != 0 || ScrimController.this.messageTextView == null || ScrimController.this.messageTextView.getVisibility() != 0) {
                    ScrimController.this.sendUiCloseUiClickMetric();
                } else {
                    ScrimController.this.sendUiClickMetric("alexaCancelError");
                }
                Iterator it = ScrimController.this.alexaUiListeners.iterator();
                while (it.hasNext()) {
                    ((AlexaUiListener) it.next()).onAlexaUiClicked();
                }
            }
        });
        return viewInflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$onCreateView$0(String str) {
        sendHintClickMetric();
        Iterator<AlexaHintClickListener> it = this.mAlexaHintClickListeners.iterator();
        while (it.hasNext()) {
            it.next().onAlexaHintClicked(str);
        }
        return Unit.INSTANCE;
    }

    private void configurePositiveButton(BaseButton baseButton) {
        BaseButton.StyleBuilder positiveButtonStyleBuilder = this.mAlexaUIStyleProvider.getPositiveButtonStyleBuilder();
        if (positiveButtonStyleBuilder != null) {
            positiveButtonStyleBuilder.withText(this.context.getString(R$string.alexa_button_settings)).applyStyle(baseButton);
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.voice.ui.scrim.ScrimController$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$configurePositiveButton$2(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configurePositiveButton$2(View view) {
        Iterator<AlexaUiListener> it = this.alexaUiListeners.iterator();
        while (it.hasNext()) {
            it.next().onPositiveButtonPressed(this.positiveButtonDestination);
        }
    }

    private void configureNegativeButton(BaseButton baseButton) {
        BaseButton.StyleBuilder negativeButtonStyleBuilder = this.mAlexaUIStyleProvider.getNegativeButtonStyleBuilder();
        if (negativeButtonStyleBuilder != null) {
            negativeButtonStyleBuilder.withText(this.context.getString(R$string.alexa_button_cancel)).applyStyle(baseButton);
        }
        baseButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.voice.ui.scrim.ScrimController$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$configureNegativeButton$3(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configureNegativeButton$3(View view) {
        Iterator<AlexaUiListener> it = this.alexaUiListeners.iterator();
        while (it.hasNext()) {
            it.next().onCancelButtonPressed(this.positiveButtonDestination);
        }
    }

    /* JADX INFO: renamed from: com.amazon.music.voice.ui.scrim.ScrimController$4, reason: invalid class name */
    static /* synthetic */ class AnonymousClass4 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState;

        static {
            int[] iArr = new int[AnimationState.values().length];
            $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState = iArr;
            try {
                iArr[AnimationState.LISTENING.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState[AnimationState.PROCESSING.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState[AnimationState.RESPONDING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState[AnimationState.NONE.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiCloseUiClickMetric() {
        String str;
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState[this.animationState.ordinal()];
        if (i == 1) {
            str = "alexaCancelListening";
        } else if (i == 2) {
            str = "alexaCancelThinking";
        } else if (i != 3) {
            return;
        } else {
            str = "alexaCancelTalking";
        }
        sendUiClickMetric(str);
    }

    private void sendUiPageViewMetric(String str) {
        NavigationAppEvent.builder(str).withRefMarker(this.refMarker).withAssociateTag(this.associateTag).publish();
        this.refMarker = null;
        this.associateTag = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendUiClickMetric(String str) {
        UserInteractionAppEvent.builder(str).publish();
    }

    private void sendHintClickMetric() {
        HashMap map = new HashMap();
        map.put(ContextMappingConstants.CONTENT_SRC, SearchMetricsServiceDefault.CONTENT_SRC_CLIENT);
        PageType pageType = PageType.ALEXA_LISTENING;
        map.put("contentName", pageType.getMetricsValue());
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(ActionType.ALEXA_SELECT_HINT).withPageType(pageType).withContentInfo(Collections.singletonList(map)).build());
    }

    public void addAlexaUiListener(AlexaUiListener alexaUiListener) {
        this.alexaUiListeners.add(alexaUiListener);
    }

    public void removeAlexaUiListener(AlexaUiListener alexaUiListener) {
        this.alexaUiListeners.remove(alexaUiListener);
    }

    @Override // com.amazon.music.voice.ui.ViewController
    protected void onDestroyView(@NonNull View view) {
        this.chromeView = null;
        this.mHintsRecyclerView = null;
        this.mAlexaHintsAdapter = null;
        this.hintsContainer = null;
        this.messageTextView = null;
        this.errorMessageContainer = null;
        this.mButtonsContainer = null;
        this.mNegativeButton = null;
        this.mPositiveButton = null;
        this.alexaIconImageView = null;
    }

    @Override // com.amazon.music.voice.ui.ViewController
    @NonNull
    public Bundle saveState() {
        Bundle bundleSaveState = super.saveState();
        BaseButton baseButton = this.mPositiveButton;
        CharSequence text = baseButton == null ? null : baseButton.getButton().getText();
        String string = text == null ? "" : text.toString();
        View view = this.mButtonsContainer;
        int visibility = view == null ? 8 : view.getVisibility();
        ChromeView chromeView = this.chromeView;
        bundleSaveState.putSerializable("scrim_controller_state", new ScrimControllerState(this.animationState, this.hintsContainer.getVisibility(), this.mAlexaHintsAdapter.getHints(), this.errorMessageContainer.getVisibility(), this.messageTextView.getVisibility(), this.messageTextView.getText().toString(), string, visibility, chromeView == null ? 8 : chromeView.getVisibility()));
        return bundleSaveState;
    }

    @Override // com.amazon.music.voice.ui.ViewController
    public void restoreState(@NonNull Bundle bundle) {
        super.restoreState(bundle);
        ScrimControllerState scrimControllerState = (ScrimControllerState) bundle.getSerializable("scrim_controller_state");
        if (scrimControllerState != null) {
            restoreAnimationState(scrimControllerState.getChromeAnimationState());
            View view = this.hintsContainer;
            if (view != null) {
                view.setVisibility(scrimControllerState.getHintsContainerVisibility());
            }
            AlexaHintsAdapter alexaHintsAdapter = this.mAlexaHintsAdapter;
            if (alexaHintsAdapter != null) {
                alexaHintsAdapter.setHints(scrimControllerState.getHints());
            }
            View view2 = this.errorMessageContainer;
            if (view2 != null) {
                view2.setVisibility(scrimControllerState.getErrorMessageContainerVisibility());
            }
            updateIconAlpha();
            TextView textView = this.messageTextView;
            if (textView != null) {
                textView.setVisibility(scrimControllerState.getMessageTextViewVisibility());
                this.messageTextView.setText(scrimControllerState.getErrorMessageText());
            }
            BaseButton baseButton = this.mPositiveButton;
            if (baseButton != null) {
                baseButton.setText(scrimControllerState.getPositiveButtonText());
            }
            View view3 = this.mButtonsContainer;
            if (view3 != null) {
                view3.setVisibility(scrimControllerState.getDialogButtonsContainerVisibility());
            }
            setChromeVisibility(scrimControllerState.getChromeVisibility());
        }
    }

    private void restoreAnimationState(AnimationState animationState) {
        int i = AnonymousClass4.$SwitchMap$com$amazon$music$voice$ui$scrim$ScrimController$AnimationState[animationState.ordinal()];
        if (i == 1) {
            startListeningAnimation();
            return;
        }
        if (i == 2) {
            startProcessingAnimation();
        } else if (i == 3) {
            startResponseAnimation();
        } else {
            stopAllAnimations();
        }
    }

    public void toggleNetworkMessageVisibility(boolean z) {
        if (getView() == null || this.messageTextView == null || this.mPositiveButton == null) {
            LOG.warn("The method is called before views are initialized.", (Throwable) new IllegalStateException("Views are not initialized."));
            return;
        }
        if (z) {
            sendUiPageViewMetric("alexaError-network");
            this.positiveButtonDestination = PositiveButtonDestination.NETWORK_SETTINGS;
            this.mPositiveButton.setText(this.context.getResources().getString(R$string.alexa_button_settings));
            setMessageView();
            this.messageTextView.setText(this.context.getResources().getText(R$string.alexa_network_reason));
            setChromeVisibility(8);
        }
        toggleButtonsVisibility(z);
        toggleErrorMessageContainerVisibility(z);
    }

    public void toggleGenericErrorMessageVisibility(boolean z) {
        if (getView() == null || this.messageTextView == null) {
            LOG.warn("The method is called before views are initialized.", (Throwable) new IllegalStateException("Views are not initialized."));
            return;
        }
        if (z) {
            sendUiPageViewMetric("alexaError-generic");
            setMessageView();
            this.messageTextView.setText(this.context.getResources().getText(R$string.alexa_generic_reason));
            this.positiveButtonDestination = PositiveButtonDestination.NONE;
            setChromeVisibility(8);
        }
        toggleButtonsVisibility(false);
        toggleErrorMessageContainerVisibility(z);
    }

    private void toggleButtonsVisibility(boolean z) {
        this.mButtonsContainer.setVisibility(z ? 0 : 8);
    }

    private boolean isErrorShowing() {
        return this.errorMessageContainer.getVisibility() == 0;
    }

    public void notifyOnSsmlSpeechDomain(String str) {
        Iterator<AlexaUiListener> it = this.alexaUiListeners.iterator();
        while (it.hasNext()) {
            it.next().onSsmlSpeechDomain(str);
        }
    }

    private void setMessageView() {
        TextView textView = this.messageTextView;
        if (textView == null || this.mButtonsContainer == null) {
            LOG.warn("The method is called before views are initialized.", (Throwable) new IllegalStateException("Views are not initialized."));
        } else {
            textView.setVisibility(0);
            this.mButtonsContainer.setVisibility(8);
        }
    }

    public void toggleErrorMessageContainerVisibility(boolean z) {
        View view;
        View view2 = this.hintsContainer;
        if (view2 == null || (view = this.errorMessageContainer) == null) {
            LOG.warn("The method is called before views are initialized.", (Throwable) new IllegalStateException("Views are not initialized."));
            return;
        }
        if (z) {
            view2.setVisibility(8);
            this.errorMessageContainer.setVisibility(0);
        } else {
            view.setVisibility(8);
        }
        updateIconAlpha();
    }

    public void toggleHintsVisibility(boolean z) {
        View view = this.hintsContainer;
        if (view == null || this.errorMessageContainer == null) {
            LOG.warn("The method is called before views are initialized.", (Throwable) new IllegalStateException("Views are not initialized."));
            return;
        }
        if (z) {
            if (this.alexaAccessProvider.isTextCapabilityFeatureSupported()) {
                fetchHints();
            }
            this.errorMessageContainer.setVisibility(8);
            this.hintsContainer.setVisibility(0);
            updateIconAlpha();
            return;
        }
        Animation animationNewCollapseAnimation = AnimationUtils.newCollapseAnimation(view, true);
        animationNewCollapseAnimation.setInterpolator(new FastOutSlowInInterpolator());
        animationNewCollapseAnimation.setDuration(500L);
        this.hintsContainer.startAnimation(animationNewCollapseAnimation);
    }

    private void fetchHints() {
        this.mAlexaHintsViewModel.fetchHints(new Function1() { // from class: com.amazon.music.voice.ui.scrim.ScrimController$$ExternalSyntheticLambda0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return this.f$0.lambda$fetchHints$4((List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Unit lambda$fetchHints$4(List list) {
        this.mAlexaHintsAdapter.setHints(list);
        return Unit.INSTANCE;
    }

    public void startResponseAnimation() {
        if (this.chromeView != null) {
            sendUiPageViewMetric("alexaTalking");
            this.chromeView.startResponseAnimation();
            this.animationState = AnimationState.RESPONDING;
        }
    }

    public void startProcessingAnimation() {
        if (this.chromeView != null) {
            sendUiPageViewMetric("alexaThinking");
            toggleHintsVisibility(false);
            this.chromeView.startProcessingAnimation();
            this.animationState = AnimationState.PROCESSING;
        }
    }

    public void stopProcessingAnimation() {
        ChromeView chromeView = this.chromeView;
        if (chromeView != null) {
            chromeView.stopProcessingAnimation();
            this.animationState = AnimationState.NONE;
        }
    }

    public void stopAllAnimations() {
        ChromeView chromeView = this.chromeView;
        if (chromeView != null) {
            chromeView.stopAllAnimations();
            this.animationState = AnimationState.NONE;
        }
        updateIconAlpha();
    }

    public void startListeningAnimation() {
        sendUiPageViewMetric("alexaListening");
        stopAllAnimations();
        ScrimContract$Presenter scrimContract$Presenter = this.presenter;
        if (scrimContract$Presenter != null) {
            scrimContract$Presenter.start();
        }
        this.animationState = AnimationState.LISTENING;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIconAlpha() {
        this.handler.removeCallbacks(this.blinkingAnimator);
        if (this.alexaIconImageView == null) {
            return;
        }
        if (isErrorShowing()) {
            this.alexaIconImageView.setAlpha(0.5f);
        } else {
            if (this.animationState == AnimationState.LISTENING) {
                this.alexaIconImageView.setAlpha((((float) Math.sin(((System.currentTimeMillis() * 2) * 3.141592653589793d) / ANIMATION_PERIOD)) / 4.0f) + 0.75f);
                this.handler.postDelayed(this.blinkingAnimator, 16L);
                return;
            }
            this.alexaIconImageView.setAlpha(1.0f);
        }
    }

    @Override // com.amazon.music.voice.ui.ViewController
    protected void onAttach(@NonNull View view) {
        this.mAlexaHintsViewModel = this.mAlexaHintsViewModelProvider.getAlexaHintsViewModel(this.context);
    }

    @Override // com.amazon.music.voice.ui.ViewController
    protected void onDetach(@NonNull View view) {
        ScrimContract$Presenter scrimContract$Presenter = this.presenter;
        if (scrimContract$Presenter != null) {
            scrimContract$Presenter.stop();
        }
        this.mAlexaHintsViewModel.cancelHintsFetch();
        this.mAlexaHintsViewModel = null;
    }

    @Override // com.amazon.music.voice.ui.ViewController
    protected void onSaveViewState(@NonNull View view, @NonNull Bundle bundle) {
        super.onSaveViewState(view, bundle);
        BaseButton baseButton = this.mPositiveButton;
        if (baseButton != null) {
            bundle.putBoolean("POSITIVE_BUTTON_ENABLED", baseButton.isEnabled());
        }
    }

    @Override // com.amazon.music.voice.ui.ViewController
    protected void onRestoreViewState(@NonNull View view, @NonNull Bundle bundle) {
        BaseButton baseButton = this.mPositiveButton;
        if (baseButton == null) {
            return;
        }
        baseButton.setEnabled(bundle.getBoolean("POSITIVE_BUTTON_ENABLED"));
        super.onRestoreViewState(view, bundle);
    }

    public void notifyViewOpened() {
        Iterator<AlexaUiListener> it = this.alexaUiListeners.iterator();
        while (it.hasNext()) {
            it.next().onAlexaUiOpened();
        }
    }

    public void notifyViewClosed() {
        Iterator<AlexaUiListener> it = this.alexaUiListeners.iterator();
        while (it.hasNext()) {
            it.next().onAlexaUiClosed();
        }
    }

    @Override // com.amazon.music.voice.ui.scrim.ScrimContract$View
    public void setChromeLevel(@FloatRange(from = 0.0d, to = 1.0d) float f) {
        ChromeView chromeView = this.chromeView;
        if (chromeView != null) {
            chromeView.setLevelAnimated(f);
        }
    }

    public void setAlexaAccessProvider(AlexaAccessProvider alexaAccessProvider) {
        this.alexaAccessProvider = alexaAccessProvider;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void setAlexaUIStyleProvider(AlexaUIStyleProvider alexaUIStyleProvider) {
        this.mAlexaUIStyleProvider = alexaUIStyleProvider;
    }

    public void setAlexaHintsViewModelProvider(AlexaHintsViewModelProvider alexaHintsViewModelProvider) {
        this.mAlexaHintsViewModelProvider = alexaHintsViewModelProvider;
    }

    public void setRefMarkerAndAssociateTag(String str, String str2) {
        this.refMarker = str;
        this.associateTag = str2;
    }

    public void addAlexaHintClickListener(AlexaHintClickListener alexaHintClickListener) {
        this.mAlexaHintClickListeners.add(alexaHintClickListener);
    }

    public AlexaHintsScrollState getAlexaHintsScrollState() {
        if (this.mAlexaHintsScrollState == null) {
            this.mAlexaHintsScrollState = new AlexaHintsScrollState(false, 0L);
        }
        return this.mAlexaHintsScrollState;
    }

    private void setChromeVisibility(int i) {
        ChromeView chromeView = this.chromeView;
        if (chromeView != null) {
            chromeView.setVisibility(i);
        }
        View view = this.chromeBorder;
        if (view != null) {
            view.setVisibility(i);
        }
    }

    private void addChromeTopMarginIfRequired() {
        if (ScreenUtil.INSTANCE.isPortrait(this.context) || this.alexaAccessProvider.inImmersiveMode(this.activity) || !(this.chromeBorder.getLayoutParams() instanceof ViewGroup.MarginLayoutParams)) {
            return;
        }
        ((ViewGroup.MarginLayoutParams) this.chromeBorder.getLayoutParams()).setMargins(0, getStatusBarHeight(), 0, 0);
        this.chromeBorder.requestLayout();
    }

    private int getStatusBarHeight() {
        int identifier = this.context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (identifier > 0) {
            return this.context.getResources().getDimensionPixelSize(identifier);
        }
        return 0;
    }

    public static class AlexaHintsScrollState {
        private boolean isScrolling;
        private long lastScrollTime;

        public AlexaHintsScrollState(boolean z, long j) {
            this.isScrolling = z;
            this.lastScrollTime = j;
        }

        public boolean isScrolling() {
            return this.isScrolling;
        }

        public long getLastScrollTime() {
            return this.lastScrollTime;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setScrolling(boolean z) {
            this.isScrolling = z;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setLastScrollTime(long j) {
            this.lastScrollTime = j;
        }
    }
}
