package com.amazon.music.inappmessaging.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.EngagementSplashTemplate;
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
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public class EngagementSplashDialogFragment extends DialogFragment {
    private static final String IS_FTU_KEY = "IS_FTU_KEY";
    private static final String PAGE_TYPE_KEY = "PAGE_TYPE_KEY";
    private static final String REASON_TYPE_KEY = "REASON_TYPE_KEY";
    private static final String TAG = EngagementSplashDialogFragment.class.getSimpleName();
    private static final String TEMPLATE_KEY = "TEMPLATE_KEY";
    private ImageView backgroundImage;
    private View backgroundView;
    private TextView byline;
    private BaseButton closeButton;
    private TextView copy;
    private ImageView gradient;
    private TextView headline;
    private Boolean isFTU;
    private Guideline leftGuideLine;
    private String localContentId;
    private String pageType;
    private TextView primaryButton;
    private String reasonType;
    private Guideline rightGuideLine;
    private TextView subhead;
    private EngagementSplashTemplate template;

    public static EngagementSplashDialogFragment newInstance(final EngagementSplashTemplate template, final DynamicMessageMetricsAttributes metricsAttributes) {
        EngagementSplashDialogFragment engagementSplashDialogFragment = new EngagementSplashDialogFragment();
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
        engagementSplashDialogFragment.setArguments(bundle);
        return engagementSplashDialogFragment;
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
        View viewInflate = inflater.inflate(R.layout.engagement_splash, container, false);
        this.subhead = (TextView) viewInflate.findViewById(R.id.subhead);
        this.headline = (TextView) viewInflate.findViewById(R.id.headline);
        this.byline = (TextView) viewInflate.findViewById(R.id.byline);
        this.copy = (TextView) viewInflate.findViewById(R.id.copy);
        this.primaryButton = (TextView) viewInflate.findViewById(R.id.primaryButton);
        this.closeButton = (BaseButton) viewInflate.findViewById(R.id.closeButton);
        this.rightGuideLine = (Guideline) viewInflate.findViewById(R.id.right_base_line);
        this.leftGuideLine = (Guideline) viewInflate.findViewById(R.id.left_base_line);
        this.backgroundView = viewInflate.findViewById(R.id.backgroundView);
        this.backgroundImage = (ImageView) viewInflate.findViewById(R.id.backgroundImage);
        this.gradient = (ImageView) viewInflate.findViewById(R.id.backgroundGradient);
        this.localContentId = UUID.randomUUID().toString().toLowerCase();
        Bundle arguments = getArguments();
        this.template = (EngagementSplashTemplate) arguments.getSerializable(TEMPLATE_KEY);
        this.pageType = (String) arguments.getSerializable(PAGE_TYPE_KEY);
        this.reasonType = (String) arguments.getSerializable(REASON_TYPE_KEY);
        this.isFTU = (Boolean) arguments.getSerializable(IS_FTU_KEY);
        StyleSheet styleSheet = DynamicMessageStyleSheetLoader.getInstance().getStyleSheet();
        if (styleSheet == null) {
            Log.e(TAG, "Stylesheet not initialized, could not display engagement splash.");
            return null;
        }
        viewInflate.setVisibility(4);
        setupView(styleSheet, viewInflate);
        setupButtonClickHandler();
        if (savedInstanceState == null) {
            DynamicMessageViewUtil.sendUIPageViewEvent(this.template.blockRef, this.isFTU.booleanValue());
        }
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.ui.EngagementSplashDialogFragment.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
                        return false;
                    }
                    DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_BACK_PRESSED_CLICK, EngagementSplashDialogFragment.this.template.blockRef, EngagementSplashDialogFragment.this.localContentId);
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
        super.onDismiss(dialog);
        Context context = getContext();
        if (context != null) {
            context.sendBroadcast(new Intent(InAppMessageBroadcasts.SPLASH_DISMISSED));
        }
        if (getActivity() == null || !(getActivity() instanceof DynamicMessageDialogFragmentListener)) {
            return;
        }
        ((DynamicMessageDialogFragmentListener) getActivity()).onDismissClickEvent(dialog);
    }

    private void setupView(StyleSheet styleSheet, final View view) {
        Picasso.get().load(chooseBackgroundImageUrl()).into(this.backgroundImage, new Callback() { // from class: com.amazon.music.inappmessaging.ui.EngagementSplashDialogFragment.2
            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                view.setVisibility(0);
            }

            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                Log.e(EngagementSplashDialogFragment.TAG, "Could not load background image for Engagement Splash");
                EngagementSplashDialogFragment.this.getDialog().dismiss();
            }
        });
        String str = this.template.hasGradient;
        if (str != null && str.equals("true")) {
            this.gradient.setVisibility(0);
        }
        if (this.template.subhead != null) {
            FontUtil.INSTANCE.applyFontStyle(this.subhead, styleSheet.getFontStyle(FontStyleKey.LABEL));
            this.subhead.setTextColor(styleSheet.getColor(ColorKey.COLOR_PRIMARY).intValue());
            Context context = getContext();
            TextView textView = this.subhead;
            EngagementSplashTemplate engagementSplashTemplate = this.template;
            DynamicMessageModalUtil.setHtmlText(context, textView, engagementSplashTemplate.subhead, engagementSplashTemplate.blockRef, this.localContentId);
            this.subhead.setBackgroundResource(R.drawable.rounded_edge_with_border);
            this.subhead.setVisibility(0);
        }
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(this.headline, styleSheet.getFontStyle(FontStyleKey.HEADLINE_3));
        fontUtil.applyFontSize(this.headline, styleSheet.getFontSize(FontSizeKey.HEADLINE_2));
        Context context2 = getContext();
        TextView textView2 = this.headline;
        EngagementSplashTemplate engagementSplashTemplate2 = this.template;
        DynamicMessageModalUtil.setHtmlTextSplashMigration(context2, textView2, engagementSplashTemplate2.headline, engagementSplashTemplate2.blockRef, this.localContentId);
        if (this.template.byline != null) {
            fontUtil.applyFontStyle(this.byline, styleSheet.getFontStyle(FontStyleKey.SECONDARY_BIG));
            fontUtil.applyFontSize(this.byline, styleSheet.getFontSize(FontSizeKey.HEADLINE_4));
            this.byline.setTextColor(styleSheet.getColor(ColorKey.COLOR_PRIMARY).intValue());
            Context context3 = getContext();
            TextView textView3 = this.byline;
            EngagementSplashTemplate engagementSplashTemplate3 = this.template;
            DynamicMessageModalUtil.setHtmlTextSplashMigration(context3, textView3, engagementSplashTemplate3.byline, engagementSplashTemplate3.blockRef, this.localContentId);
            this.byline.setVisibility(0);
        }
        if (this.template.copy != null) {
            fontUtil.applyFontStyle(this.copy, styleSheet.getFontStyle(FontStyleKey.PRIMARY));
            Context context4 = getContext();
            TextView textView4 = this.copy;
            EngagementSplashTemplate engagementSplashTemplate4 = this.template;
            DynamicMessageModalUtil.setHtmlText(context4, textView4, engagementSplashTemplate4.copy, engagementSplashTemplate4.blockRef, this.localContentId);
            this.copy.setVisibility(0);
        }
        fontUtil.applyFontStyle(this.primaryButton, styleSheet.getFontStyle(FontStyleKey.SECONDARY_TERTIARY));
        this.primaryButton.setBackgroundResource(R.drawable.rounded_edges);
        Context context5 = getContext();
        TextView textView5 = this.primaryButton;
        EngagementSplashTemplate engagementSplashTemplate5 = this.template;
        DynamicMessageModalUtil.setHtmlText(context5, textView5, engagementSplashTemplate5.buttonPrimaryText, engagementSplashTemplate5.blockRef, this.localContentId);
        String str2 = this.template.buttonPrimaryColor;
        if (str2 != null) {
            ((GradientDrawable) this.primaryButton.getBackground()).setColor(Color.parseColor(str2));
        }
        styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.PRIMARY).withIcon(getResources().getDrawable(R.drawable.ic_dialog_close_dark)).applyStyle(this.closeButton);
        if (getResources().getConfiguration().screenWidthDp > 640) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.leftGuideLine.getLayoutParams();
            layoutParams.guidePercent = 0.65f;
            this.leftGuideLine.setLayoutParams(layoutParams);
            ConstraintLayout.LayoutParams layoutParams2 = (ConstraintLayout.LayoutParams) this.rightGuideLine.getLayoutParams();
            layoutParams2.guidePercent = 0.9f;
            this.rightGuideLine.setLayoutParams(layoutParams2);
        }
    }

    private void setupButtonClickHandler() {
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.EngagementSplashDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$0(view);
            }
        });
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.EngagementSplashDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$1(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$0(View view) {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.ACT_ON_IAM, this.template.blockRef, this.localContentId);
        getDialog().dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$1(View view) {
        DynamicMessageModalUtil.dialogDismissHandler(getDialog(), this.template.blockRef, this.localContentId);
    }

    private String chooseBackgroundImageUrl() {
        return DynamicMessageModalUtil.chooseSplashBackgroundImageUrl(getResources().getConfiguration().screenWidthDp, getResources().getConfiguration().screenHeightDp, getResources().getDisplayMetrics().density, getResources().getConfiguration().orientation == 1, this.template);
    }
}
