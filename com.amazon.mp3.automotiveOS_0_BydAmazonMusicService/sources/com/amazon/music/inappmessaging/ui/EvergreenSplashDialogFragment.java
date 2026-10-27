package com.amazon.music.inappmessaging.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.EvergreenSplashTemplate;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontSizeKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.text.ParseException;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public class EvergreenSplashDialogFragment extends DialogFragment {
    private static final String IS_FTU_KEY = "IS_FTU_KEY";
    private static final int LEGAL_TOU_LINE_LIMIT = 3;
    private static final String PAGE_TYPE_KEY = "PAGE_TYPE_KEY";
    private static final String REASON_TYPE_KEY = "REASON_TYPE_KEY";
    private static final String TAG = EvergreenSplashDialogFragment.class.getSimpleName();
    private static final String TEMPLATE_KEY = "TEMPLATE_KEY";
    private ImageView backgroundImage;
    private View backgroundView;
    private TextView byline;
    private BaseButton closeButton;
    private ConstraintLayout contentView;
    private TextView copy;
    private CountDownTimer countDownTimer;
    private TextView headline;
    private Boolean isFTU;
    private View loadingView;
    private String localContentId;
    private ImageView logo;
    private String pageType;
    private TextView primaryButton;
    private String reasonType;
    private ScrollView scrollView;
    private TextView secondaryButtonText;
    private EvergreenSplashTemplate template;
    private TextView termsOfUseTextView;
    private LinearLayout topSpacer;

    public static EvergreenSplashDialogFragment newInstance(final EvergreenSplashTemplate template, final DynamicMessageMetricsAttributes metricsAttributes) {
        EvergreenSplashDialogFragment evergreenSplashDialogFragment = new EvergreenSplashDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TEMPLATE_KEY, template);
        if (metricsAttributes == null) {
            bundle.putSerializable(PAGE_TYPE_KEY, null);
            bundle.putSerializable(REASON_TYPE_KEY, null);
            bundle.putSerializable(IS_FTU_KEY, Boolean.FALSE);
        } else {
            bundle.putSerializable(PAGE_TYPE_KEY, metricsAttributes.getPageType());
            bundle.putSerializable(REASON_TYPE_KEY, metricsAttributes.getReasonType());
            bundle.putSerializable(IS_FTU_KEY, Boolean.valueOf(metricsAttributes.getIsFTU()));
        }
        evergreenSplashDialogFragment.setArguments(bundle);
        return evergreenSplashDialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.FullScreenTransparent);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        View viewInflate = inflater.inflate(R.layout.evergreen_splash, container, false);
        this.contentView = (ConstraintLayout) viewInflate.findViewById(R.id.contentView);
        this.scrollView = (ScrollView) viewInflate.findViewById(R.id.scrollView);
        this.topSpacer = (LinearLayout) viewInflate.findViewById(R.id.topSpacer);
        this.headline = (TextView) viewInflate.findViewById(R.id.headline);
        this.byline = (TextView) viewInflate.findViewById(R.id.byline);
        this.copy = (TextView) viewInflate.findViewById(R.id.copy);
        this.primaryButton = (TextView) viewInflate.findViewById(R.id.primaryButton);
        this.secondaryButtonText = (TextView) viewInflate.findViewById(R.id.secondaryButtonText);
        this.closeButton = (BaseButton) viewInflate.findViewById(R.id.closeButton);
        this.termsOfUseTextView = (TextView) viewInflate.findViewById(R.id.termsOfUseText);
        this.logo = (ImageView) viewInflate.findViewById(R.id.musicLogo);
        this.loadingView = viewInflate.findViewById(R.id.loadingView);
        this.backgroundView = viewInflate.findViewById(R.id.backgroundView);
        this.backgroundImage = (ImageView) viewInflate.findViewById(R.id.backgroundImage);
        this.localContentId = UUID.randomUUID().toString().toLowerCase();
        Bundle arguments = getArguments();
        this.template = (EvergreenSplashTemplate) arguments.getSerializable(TEMPLATE_KEY);
        this.pageType = (String) arguments.getSerializable(PAGE_TYPE_KEY);
        this.reasonType = (String) arguments.getSerializable(REASON_TYPE_KEY);
        this.isFTU = (Boolean) arguments.getSerializable(IS_FTU_KEY);
        StyleSheet styleSheet = DynamicMessageStyleSheetLoader.getInstance().getStyleSheet();
        if (styleSheet == null) {
            Log.e(TAG, "Stylesheet not initialized, could not display evergreen splash.");
            return null;
        }
        viewInflate.setVisibility(4);
        try {
            setupView(styleSheet, viewInflate);
        } catch (ParseException unused) {
            Log.e(TAG, "Error setting up Countdown Timer in Evergreen Splash");
        }
        setupButtonClickHandler();
        if (savedInstanceState == null) {
            DynamicMessageViewUtil.sendUIPageViewEvent(this.template.blockRef, this.isFTU.booleanValue());
        }
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
                        return false;
                    }
                    DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_BACK_PRESSED_CLICK, EvergreenSplashDialogFragment.this.template.blockRef, EvergreenSplashDialogFragment.this.localContentId);
                    return false;
                }
            });
        }
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_RENDER_LATENCY).withRenderLatencyMillis(Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)).withTemplate(this.template.template).send();
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
        CountDownTimer countDownTimer;
        super.onDismiss(dialog);
        if (this.template.promoEndDate != null && (countDownTimer = this.countDownTimer) != null) {
            countDownTimer.cancel();
        }
        Context context = getContext();
        if (context != null) {
            context.sendBroadcast(new Intent(InAppMessageBroadcasts.SPLASH_DISMISSED));
        }
        if (getActivity() == null || !(getActivity() instanceof DynamicMessageDialogFragmentListener)) {
            return;
        }
        ((DynamicMessageDialogFragmentListener) getActivity()).onDismissClickEvent(dialog);
    }

    private void setupButtonClickHandler() {
        final Uri uri = Uri.parse(this.template.buttonPrimaryLink);
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$0(uri, view);
            }
        });
        this.secondaryButtonText.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$1(view);
            }
        });
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$2(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$0(Uri uri, View view) {
        DynamicMessageModalUtil.splashDeeplinkListener(getContext(), uri, this.template.blockRef, this.localContentId, getDialog());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$1(View view) {
        DynamicMessageModalUtil.dialogDismissHandler(getDialog(), this.template.blockRef, this.localContentId);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$2(View view) {
        DynamicMessageModalUtil.dialogDismissHandler(getDialog(), this.template.blockRef, this.localContentId);
    }

    private void setupView(StyleSheet styleSheet, final View view) throws ParseException {
        Picasso.get().load(chooseBackgroundImageUrl()).into(this.backgroundImage, new Callback() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment.2
            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                view.setVisibility(0);
            }

            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                Log.e(EvergreenSplashDialogFragment.TAG, "Could not load background image for Evergreen Splash");
                EvergreenSplashDialogFragment.this.getDialog().dismiss();
            }
        });
        String strChooseLogoImageUrl = chooseLogoImageUrl();
        if (!strChooseLogoImageUrl.isEmpty()) {
            Picasso.get().load(strChooseLogoImageUrl).into(this.logo, new Callback() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment.3
                @Override // com.squareup.picasso.Callback
                public void onSuccess() {
                    EvergreenSplashDialogFragment.this.logo.setVisibility(0);
                }

                @Override // com.squareup.picasso.Callback
                public void onError(Exception e) {
                    Log.e(EvergreenSplashDialogFragment.TAG, "Could not load Amazon Music logo for Evergreen Splash");
                }
            });
        }
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(this.headline, styleSheet.getFontStyle(FontStyleKey.HEADLINE_1_NARROW));
        Context context = getContext();
        TextView textView = this.headline;
        EvergreenSplashTemplate evergreenSplashTemplate = this.template;
        DynamicMessageModalUtil.setHtmlTextSplashMigration(context, textView, evergreenSplashTemplate.headline, evergreenSplashTemplate.blockRef, this.localContentId);
        if (this.template.byline != null) {
            fontUtil.applyFontStyle(this.byline, styleSheet.getFontStyle(FontStyleKey.SECONDARY_BIG));
            fontUtil.applyFontSize(this.byline, styleSheet.getFontSize(FontSizeKey.HEADLINE_4));
            this.byline.setTextColor(styleSheet.getColor(ColorKey.COLOR_PRIMARY).intValue());
            this.byline.setLinkTextColor(styleSheet.getColor(ColorKey.COLOR_ACCENT).intValue());
            Context context2 = getContext();
            TextView textView2 = this.byline;
            EvergreenSplashTemplate evergreenSplashTemplate2 = this.template;
            DynamicMessageModalUtil.setHtmlTextSplashMigration(context2, textView2, evergreenSplashTemplate2.byline, evergreenSplashTemplate2.blockRef, this.localContentId);
            EvergreenSplashTemplate evergreenSplashTemplate3 = this.template;
            if (evergreenSplashTemplate3.promoEndDate != null && DynamicMessageModalUtil.textContainsTimerToken(evergreenSplashTemplate3.byline)) {
                TextView textView3 = this.byline;
                EvergreenSplashTemplate evergreenSplashTemplate4 = this.template;
                CountDownTimer countdownTimer = DynamicMessageModalUtil.countdownTimer(textView3, evergreenSplashTemplate4.byline, evergreenSplashTemplate4.promoEndDate, getActivity().getResources());
                this.countDownTimer = countdownTimer;
                countdownTimer.start();
            }
        } else {
            this.byline.setVisibility(8);
        }
        this.copy.setLinkTextColor(styleSheet.getColor(ColorKey.COLOR_ACCENT).intValue());
        EvergreenSplashTemplate evergreenSplashTemplate5 = this.template;
        if (evergreenSplashTemplate5.promoEndDate != null && DynamicMessageModalUtil.textContainsTimerToken(evergreenSplashTemplate5.copy)) {
            TextView textView4 = this.copy;
            EvergreenSplashTemplate evergreenSplashTemplate6 = this.template;
            CountDownTimer countdownTimer2 = DynamicMessageModalUtil.countdownTimer(textView4, evergreenSplashTemplate6.copy, evergreenSplashTemplate6.promoEndDate, getActivity().getResources());
            this.countDownTimer = countdownTimer2;
            countdownTimer2.start();
        }
        fontUtil.applyFontStyle(this.copy, styleSheet.getFontStyle(FontStyleKey.PRIMARY));
        Context context3 = getContext();
        TextView textView5 = this.copy;
        EvergreenSplashTemplate evergreenSplashTemplate7 = this.template;
        DynamicMessageModalUtil.setHtmlText(context3, textView5, evergreenSplashTemplate7.copy, evergreenSplashTemplate7.blockRef, this.localContentId);
        fontUtil.applyFontStyle(this.primaryButton, styleSheet.getFontStyle(FontStyleKey.SECONDARY_TERTIARY));
        this.primaryButton.setBackgroundResource(R.drawable.rounded_edges);
        Context context4 = getContext();
        TextView textView6 = this.primaryButton;
        EvergreenSplashTemplate evergreenSplashTemplate8 = this.template;
        DynamicMessageModalUtil.setHtmlText(context4, textView6, evergreenSplashTemplate8.buttonPrimaryText, evergreenSplashTemplate8.blockRef, this.localContentId);
        String str = this.template.buttonPrimaryColor;
        if (str != null) {
            ((GradientDrawable) this.primaryButton.getBackground()).setColor(Color.parseColor(str));
        }
        TextView textView7 = this.secondaryButtonText;
        FontStyleKey fontStyleKey = FontStyleKey.INDEX_PRIMARY;
        fontUtil.applyFontStyle(textView7, styleSheet.getFontStyle(fontStyleKey));
        TextView textView8 = this.secondaryButtonText;
        textView8.setPaintFlags(textView8.getPaintFlags() | 8);
        this.secondaryButtonText.setAllCaps(false);
        Context context5 = getContext();
        TextView textView9 = this.secondaryButtonText;
        EvergreenSplashTemplate evergreenSplashTemplate9 = this.template;
        DynamicMessageModalUtil.setHtmlText(context5, textView9, evergreenSplashTemplate9.buttonSecondaryText, evergreenSplashTemplate9.blockRef, this.localContentId);
        if (this.template.termsOfUse != null) {
            fontUtil.applyFontStyle(this.termsOfUseTextView, styleSheet.getFontStyle(fontStyleKey));
            this.termsOfUseTextView.setAllCaps(false);
            this.termsOfUseTextView.setTypeface(Typeface.SANS_SERIF);
            Context context6 = getContext();
            TextView textView10 = this.termsOfUseTextView;
            EvergreenSplashTemplate evergreenSplashTemplate10 = this.template;
            DynamicMessageModalUtil.setHtmlText(context6, textView10, evergreenSplashTemplate10.termsOfUse, evergreenSplashTemplate10.blockRef, this.localContentId);
            this.termsOfUseTextView.setLinkTextColor(styleSheet.getColor(ColorKey.COLOR_PRIMARY).intValue());
        } else {
            this.termsOfUseTextView.setVisibility(8);
        }
        styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.PRIMARY).withIcon(getResources().getDrawable(R.drawable.ic_dialog_close_dark)).applyStyle(this.closeButton);
        this.scrollView.post(new Runnable() { // from class: com.amazon.music.inappmessaging.ui.EvergreenSplashDialogFragment$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$setupView$3();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupView$3() {
        try {
            if (this.termsOfUseTextView.getLineCount() > 3) {
                int height = 0;
                int height2 = (this.termsOfUseTextView.getHeight() - this.termsOfUseTextView.getPaddingTop()) - ((this.termsOfUseTextView.getLayout().getLineTop(3) + this.termsOfUseTextView.getLayout().getLineBottom(3)) / 2);
                if (this.scrollView.getTop() > 0) {
                    height = this.scrollView.getTop() + height2;
                } else if (this.contentView.getHeight() > this.scrollView.getHeight()) {
                    height = height2 - (this.contentView.getHeight() - this.scrollView.getHeight());
                }
                setTopMargin(this.topSpacer, height);
            }
        } catch (Exception unused) {
            Log.e(TAG, "Exception while updating the spacer, the whole TOU would be shown on the splash.");
        }
    }

    private void setTopMargin(View view, float top) {
        if (view.getLayoutParams() instanceof ViewGroup.LayoutParams) {
            view.getLayoutParams().height = (int) top;
            view.requestLayout();
        }
    }

    private String chooseBackgroundImageUrl() {
        return DynamicMessageModalUtil.chooseSplashBackgroundImageUrl(getResources().getConfiguration().screenWidthDp, getResources().getConfiguration().screenHeightDp, getResources().getDisplayMetrics().density, getResources().getConfiguration().orientation == 1, this.template);
    }

    private String chooseLogoImageUrl() {
        float f = getResources().getDisplayMetrics().density;
        if (this.template.logoUrl == null || DynamicMessageModalUtil.isDisplayRetina(f)) {
            return (this.template.logoRetinaUrl == null || !DynamicMessageModalUtil.isDisplayRetina(f)) ? "" : this.template.logoRetinaUrl;
        }
        return this.template.logoUrl;
    }
}
