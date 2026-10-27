package com.amazon.music.inappmessaging.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.FullScreenBauhausModalTemplate;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.inappmessaging.ui.widget.WrapWidthCenteredTextView;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingErrorCode;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Picasso;
import java.net.URI;
import java.util.UUID;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
public class FullScreenDynamicMessageModalFragment extends DialogFragment {
    private static final String IS_BLOCKING = "IS_BLOCKING";
    private static final String PAGE_TYPE_KEY = "PAGE_TYPE_KEY";
    private static final String PLAYBACK_QUERY_PARAMETER = "do=play";
    private static final String REASON_TYPE_KEY = "REASON_TYPE_KEY";
    private static final String TAG = FullScreenDynamicMessageModalFragment.class.getSimpleName();
    private static final String TEMPLATE_KEY = "TEMPLATE_KEY";
    private View backgroundView;
    private TextView byline;
    private BaseButton closeButton;
    private TextView comfort;
    private TextView headline;
    private Boolean isBlocking;
    private View loadingView;
    private String localContentId;
    private ImageView logo;
    private String pageType;
    private BaseButton primaryButton;
    private String reasonType;
    private WrapWidthCenteredTextView secondaryButton;
    private ImageView secondaryButtonStationOutline;
    private ViewGroup secondaryButtonWrapper;
    private FullScreenBauhausModalTemplate template;
    private TextView trialingTextView;

    public static FullScreenDynamicMessageModalFragment newInstance(final FullScreenBauhausModalTemplate template, final DynamicMessageMetricsAttributes metricsAttributes, final Boolean isBlocking) {
        FullScreenDynamicMessageModalFragment fullScreenDynamicMessageModalFragment = new FullScreenDynamicMessageModalFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TEMPLATE_KEY, template);
        bundle.putSerializable(IS_BLOCKING, isBlocking);
        if (metricsAttributes == null) {
            bundle.putSerializable(PAGE_TYPE_KEY, null);
            bundle.putSerializable(REASON_TYPE_KEY, null);
        } else {
            bundle.putSerializable(PAGE_TYPE_KEY, metricsAttributes.getPageType());
            bundle.putSerializable(REASON_TYPE_KEY, metricsAttributes.getReasonType());
        }
        fullScreenDynamicMessageModalFragment.setArguments(bundle);
        return fullScreenDynamicMessageModalFragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.FullScreenTransparent);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        View viewInflate = inflater.inflate(R.layout.full_screen_bauhaus, container, false);
        this.headline = (TextView) viewInflate.findViewById(R.id.headline);
        this.byline = (TextView) viewInflate.findViewById(R.id.byline);
        this.primaryButton = (BaseButton) viewInflate.findViewById(R.id.primaryButton);
        this.secondaryButtonWrapper = (ViewGroup) viewInflate.findViewById(R.id.secondaryButtonWrapper);
        this.secondaryButton = (WrapWidthCenteredTextView) viewInflate.findViewById(R.id.secondaryButton);
        this.secondaryButtonStationOutline = (ImageView) viewInflate.findViewById(R.id.secondaryButtonStationOutline);
        this.closeButton = (BaseButton) viewInflate.findViewById(R.id.closeButton);
        this.comfort = (TextView) viewInflate.findViewById(R.id.comfortText);
        this.trialingTextView = (TextView) viewInflate.findViewById(R.id.trialingText);
        this.logo = (ImageView) viewInflate.findViewById(R.id.musicLogo);
        this.loadingView = viewInflate.findViewById(R.id.loadingView);
        this.backgroundView = viewInflate.findViewById(R.id.backgroundView);
        this.localContentId = UUID.randomUUID().toString().toLowerCase();
        Bundle arguments = getArguments();
        this.template = (FullScreenBauhausModalTemplate) arguments.getSerializable(TEMPLATE_KEY);
        this.pageType = (String) arguments.getSerializable(PAGE_TYPE_KEY);
        this.reasonType = (String) arguments.getSerializable(REASON_TYPE_KEY);
        this.isBlocking = (Boolean) arguments.getSerializable(IS_BLOCKING);
        StyleSheet styleSheet = DynamicMessageStyleSheetLoader.getInstance().getStyleSheet();
        if (styleSheet == null) {
            return null;
        }
        setupView(styleSheet, this.isBlocking);
        setupButtonClickHandler();
        if (savedInstanceState == null) {
            DynamicMessageViewUtil.sendUIContentViewEvent(this.template.messageRef, this.localContentId, this.pageType, this.reasonType, ContainerType.MODAL);
        }
        if (getDialog() != null) {
            if (getDialog().getWindow() != null) {
                getDialog().getWindow().requestFeature(1);
            }
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.ui.FullScreenDynamicMessageModalFragment.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
                        return false;
                    }
                    DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_BACK_PRESSED_CLICK, FullScreenDynamicMessageModalFragment.this.template.messageRef, FullScreenDynamicMessageModalFragment.this.localContentId);
                    return false;
                }
            });
        }
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_RENDER_LATENCY).withRenderLatencyMillis(Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)).withTemplate(this.template.templateId).send();
        return viewInflate;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onStart() {
        super.onStart();
        if (getDialog() != null) {
            getDialog().getWindow().setLayout(-1, -1);
        }
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getActivity() == null || !(getActivity() instanceof DynamicMessageDialogFragmentListener)) {
            return;
        }
        ((DynamicMessageDialogFragmentListener) getActivity()).onDismissClickEvent(dialog);
    }

    private void setupButtonClickHandler() {
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.FullScreenDynamicMessageModalFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$0(view);
            }
        });
        if (this.template.secondaryButtonLink != null) {
            this.secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.FullScreenDynamicMessageModalFragment$$ExternalSyntheticLambda2
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setupButtonClickHandler$1(view);
                }
            });
        }
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.FullScreenDynamicMessageModalFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$2(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$0(View view) {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_CTA_CLICK, this.template.messageRef, this.localContentId);
        FullScreenBauhausModalTemplate fullScreenBauhausModalTemplate = this.template;
        if (fullScreenBauhausModalTemplate.shouldPrimaryButtonDismissDialog) {
            getDialog().dismiss();
        } else {
            actionHandler(new DynamicMessageEvent(fullScreenBauhausModalTemplate.primaryButtonLink));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$1(View view) {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_CTA2_CLICK, this.template.messageRef, this.localContentId);
        FullScreenBauhausModalTemplate fullScreenBauhausModalTemplate = this.template;
        if (fullScreenBauhausModalTemplate.shouldSecondaryButtonDismissDialog) {
            getDialog().dismiss();
        } else {
            actionHandler(new DynamicMessageEvent(fullScreenBauhausModalTemplate.secondaryButtonLink));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$2(View view) {
        dismissHandler();
    }

    private void dismissHandler() {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_DISMISS_CLICK, this.template.messageRef, this.localContentId);
        if (getDialog() != null) {
            getDialog().dismiss();
        }
    }

    private void actionHandler(final DynamicMessageEvent event) {
        this.loadingView.setVisibility(0);
        DynamicMessageOrchestrator.getInstance().displayDynamicMessage(getActivity(), event, null).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.inappmessaging.ui.FullScreenDynamicMessageModalFragment$$ExternalSyntheticLambda4
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$actionHandler$3(event, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$actionHandler$3(DynamicMessageEvent dynamicMessageEvent, Boolean bool) {
        DynamicMessageModalUtil.postEventExecute(dynamicMessageEvent);
        this.loadingView.setVisibility(8);
        try {
            dismissAllowingStateLoss();
        } catch (Exception e) {
            DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_ERROR).withErrorCode(DynamicMessagingErrorCode.OTHER).withErrorMessage("Error dismissing modal fragment: " + e.getMessage()).withTrigger(dynamicMessageEvent.getEvent()).send();
        }
    }

    private void setupView(StyleSheet styleSheet, Boolean isBlocking) {
        String str = this.template.backgroundColor;
        if (str != null) {
            this.backgroundView.setBackgroundColor(Color.parseColor(str));
        } else {
            this.backgroundView.setBackground(getResources().getDrawable(R.drawable.bauhaus_gradient_background));
        }
        Context context = getContext();
        TextView textView = this.headline;
        FullScreenBauhausModalTemplate fullScreenBauhausModalTemplate = this.template;
        DynamicMessageModalUtil.setHtmlText(context, textView, fullScreenBauhausModalTemplate.headline, fullScreenBauhausModalTemplate.messageRef, this.localContentId);
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(this.headline, styleSheet.getFontStyle(FontStyleKey.DYNAMIC_MESSAGING_MODAL_HEADLINE));
        String str2 = this.template.headlineFontSize;
        if (str2 != null) {
            this.headline.setTextSize(0, Float.parseFloat(str2));
        }
        Context context2 = getContext();
        TextView textView2 = this.byline;
        FullScreenBauhausModalTemplate fullScreenBauhausModalTemplate2 = this.template;
        DynamicMessageModalUtil.setHtmlText(context2, textView2, fullScreenBauhausModalTemplate2.byline, fullScreenBauhausModalTemplate2.messageRef, this.localContentId);
        TextView textView3 = this.byline;
        ColorKey colorKey = ColorKey.COLOR_ACCENT;
        textView3.setLinkTextColor(styleSheet.getColor(colorKey).intValue());
        TextView textView4 = this.byline;
        FontStyleKey fontStyleKey = FontStyleKey.PRIMARY;
        fontUtil.applyFontStyle(textView4, styleSheet.getFontStyle(fontStyleKey));
        String str3 = this.template.bylineFontSize;
        if (str3 != null) {
            this.byline.setTextSize(0, Float.parseFloat(str3));
        }
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.LARGE;
        ButtonStyleKey buttonStyleKey = ButtonStyleKey.SOLID;
        BaseButton.StyleBuilder styleBuilderWithWidth = styleSheet.getButtonBuilder(buttonSizeKey, buttonStyleKey).withText(this.template.primaryButtonText).withWidth(-1);
        String str4 = this.template.primaryButtonBackgroundColor;
        if (str4 != null) {
            styleBuilderWithWidth = styleBuilderWithWidth.withStyle(DynamicMessageModalUtil.buildButtonStyle(styleSheet.getButtonStyle(buttonStyleKey), Color.parseColor(str4)));
        }
        String str5 = this.template.primaryButtonFontSize;
        if (str5 != null) {
            styleBuilderWithWidth = styleBuilderWithWidth.withSize(DynamicMessageModalUtil.buildButtonSize(styleSheet.getButtonSize(buttonSizeKey), (int) TypedValue.applyDimension(0, Float.parseFloat(str5), getResources().getDisplayMetrics())));
        }
        WrapWidthCenteredTextView wrapWidthCenteredTextView = this.secondaryButton;
        if (wrapWidthCenteredTextView != null) {
            wrapWidthCenteredTextView.setPaintFlags(wrapWidthCenteredTextView.getPaintFlags() | 8);
        }
        styleBuilderWithWidth.applyStyle(this.primaryButton);
        if (this.template.secondaryButtonText != null) {
            this.secondaryButtonWrapper.setVisibility(0);
            this.secondaryButton.setText(this.template.secondaryButtonText);
            fontUtil.applyFontStyle(this.secondaryButton, styleSheet.getFontStyle(fontStyleKey));
            String str6 = this.template.secondaryButtonFontSize;
            if (str6 != null) {
                this.secondaryButton.setTextSize(0, Float.parseFloat(str6));
            }
            URI uri = this.template.secondaryButtonLink;
            if (uri != null && uri.toString().contains(PLAYBACK_QUERY_PARAMETER) && DynamicMessageModalUtil.isNightwingUser()) {
                this.secondaryButtonStationOutline.setVisibility(0);
                int dimension = (int) getResources().getDimension(R.dimen.bauhaus_spacer_huge);
                int dimension2 = (int) getResources().getDimension(R.dimen.dialog_bottom_margin);
                DisplayMetrics displayMetrics = new DisplayMetrics();
                if (Build.VERSION.SDK_INT >= 30) {
                    getContext().getDisplay().getMetrics(displayMetrics);
                } else if (getDialog() != null) {
                    getDialog().getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                }
                this.secondaryButton.setMaxWidth((displayMetrics.widthPixels - (dimension * 2)) - (dimension2 * 2));
            }
        }
        if (this.template.comfortText != null) {
            Context context3 = getContext();
            TextView textView5 = this.comfort;
            FullScreenBauhausModalTemplate fullScreenBauhausModalTemplate3 = this.template;
            DynamicMessageModalUtil.setHtmlText(context3, textView5, fullScreenBauhausModalTemplate3.comfortText, fullScreenBauhausModalTemplate3.messageRef, this.localContentId);
            fontUtil.applyFontStyle(this.comfort, styleSheet.getFontStyle(FontStyleKey.DYNAMIC_MESSAGING_MODAL_BODY));
            this.comfort.setAllCaps(false);
            this.comfort.setTypeface(Typeface.SANS_SERIF);
            String str7 = this.template.comfortTextFontSize;
            if (str7 != null) {
                this.comfort.setTextSize(0, Float.parseFloat(str7));
            }
        } else {
            this.comfort.setVisibility(8);
        }
        if (this.template.trailingText != null) {
            this.trialingTextView.setLinkTextColor(styleSheet.getColor(colorKey).intValue());
            Context context4 = getContext();
            TextView textView6 = this.trialingTextView;
            FullScreenBauhausModalTemplate fullScreenBauhausModalTemplate4 = this.template;
            DynamicMessageModalUtil.setHtmlText(context4, textView6, fullScreenBauhausModalTemplate4.trailingText, fullScreenBauhausModalTemplate4.messageRef, this.localContentId);
            fontUtil.applyFontStyle(this.trialingTextView, styleSheet.getFontStyle(FontStyleKey.DYNAMIC_MESSAGING_MODAL_BODY));
            this.trialingTextView.setAllCaps(false);
            this.trialingTextView.setTypeface(Typeface.SANS_SERIF);
            String str8 = this.template.trailingTextFontSize;
            if (str8 != null) {
                this.trialingTextView.setTextSize(0, Float.parseFloat(str8));
            }
        } else {
            this.trialingTextView.setVisibility(8);
        }
        if (this.template.logoImageUrl != null) {
            if (getResources().getDisplayMetrics() != null) {
                Picasso.get().load(this.template.logoImageUrl).resize((int) (((double) getResources().getDisplayMetrics().widthPixels) * 0.7d), 0).onlyScaleDown().into(this.logo);
            } else {
                Picasso.get().load(this.template.logoImageUrl).into(this.logo);
            }
        }
        if (isBlocking.booleanValue()) {
            styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS).withIcon(getResources().getDrawable(R.drawable.ic_dialog_close_dark)).applyStyle(this.closeButton);
            this.closeButton.setVisibility(0);
            return;
        }
        this.closeButton.setVisibility(8);
        if (this.template.shouldSecondaryButtonDismissDialog) {
            this.secondaryButton.setVisibility(8);
            this.secondaryButtonWrapper.setVisibility(8);
            this.secondaryButtonStationOutline.setVisibility(8);
        }
    }
}
