package com.amazon.music.inappmessaging.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
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
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.inappmessaging.external.InAppMessageBroadcasts;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.ThirdPartyHostedSplashTemplate;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ColorKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.UUID;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes4.dex */
public class ThirdPartyHostedDialogFragment extends DialogFragment {
    private static final String IS_FTU_KEY = "IS_FTU_KEY";
    private static final String PAGE_TYPE_KEY = "PAGE_TYPE_KEY";
    private static final String REASON_TYPE_KEY = "REASON_TYPE_KEY";
    private static final String TAG = ThirdPartyHostedDialogFragment.class.getSimpleName();
    private static final String TEMPLATE_KEY = "TEMPLATE_KEY";
    private ImageView backgroundImage;
    private View backgroundView;
    private BaseButton closeButton;
    private ConstraintLayout contentView;
    private Boolean isFTU;
    private String localContentId;
    private String pageType;
    private TextView primaryButton;
    private String reasonType;
    private TextView secondaryButton;
    private ThirdPartyHostedSplashTemplate template;
    private TextView termsOfUse;

    public static ThirdPartyHostedDialogFragment newInstance(final ThirdPartyHostedSplashTemplate template, final DynamicMessageMetricsAttributes metricsAttributes) {
        ThirdPartyHostedDialogFragment thirdPartyHostedDialogFragment = new ThirdPartyHostedDialogFragment();
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
        thirdPartyHostedDialogFragment.setArguments(bundle);
        return thirdPartyHostedDialogFragment;
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
        this.primaryButton = (TextView) viewInflate.findViewById(R.id.primaryButton);
        this.secondaryButton = (TextView) viewInflate.findViewById(R.id.secondaryButton);
        this.termsOfUse = (TextView) viewInflate.findViewById(R.id.termsOfUseText);
        this.closeButton = (BaseButton) viewInflate.findViewById(R.id.closeButton);
        this.contentView = (ConstraintLayout) viewInflate.findViewById(R.id.contentView);
        this.backgroundView = viewInflate.findViewById(R.id.backgroundView);
        this.backgroundImage = (ImageView) viewInflate.findViewById(R.id.backgroundImage);
        this.localContentId = UUID.randomUUID().toString().toLowerCase();
        Bundle arguments = getArguments();
        this.template = (ThirdPartyHostedSplashTemplate) arguments.getSerializable(TEMPLATE_KEY);
        this.pageType = (String) arguments.getSerializable(PAGE_TYPE_KEY);
        this.reasonType = (String) arguments.getSerializable(REASON_TYPE_KEY);
        this.isFTU = (Boolean) arguments.getSerializable(IS_FTU_KEY);
        StyleSheet styleSheet = DynamicMessageStyleSheetLoader.getInstance().getStyleSheet();
        if (styleSheet == null) {
            Log.e(TAG, "Stylesheet not initialized, could not display 3P Hosted splash.");
            return null;
        }
        viewInflate.setVisibility(4);
        setupView(styleSheet, viewInflate);
        setupButtonClickHandler();
        if (savedInstanceState == null) {
            DynamicMessageViewUtil.sendUIPageViewEvent(this.template.blockRef, this.isFTU.booleanValue());
        }
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.ui.ThirdPartyHostedDialogFragment.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
                        return false;
                    }
                    DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_BACK_PRESSED_CLICK, ThirdPartyHostedDialogFragment.this.template.blockRef, ThirdPartyHostedDialogFragment.this.localContentId);
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
        String str;
        int i = getResources().getConfiguration().screenWidthDp;
        if (!(getResources().getConfiguration().orientation == 1) && StringUtils.isNotBlank(this.template.templateImageLinkLandscape)) {
            str = this.template.templateImageLinkLandscape;
        } else {
            str = this.template.templateImageLink;
        }
        Picasso.get().load(str).into(this.backgroundImage, new Callback() { // from class: com.amazon.music.inappmessaging.ui.ThirdPartyHostedDialogFragment.2
            @Override // com.squareup.picasso.Callback
            public void onSuccess() {
                view.setVisibility(0);
            }

            @Override // com.squareup.picasso.Callback
            public void onError(Exception e) {
                Log.e(ThirdPartyHostedDialogFragment.TAG, "Could not load background image for 3P Splash");
                ThirdPartyHostedDialogFragment.this.getDialog().dismiss();
            }
        });
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(this.primaryButton, styleSheet.getFontStyle(FontStyleKey.SECONDARY_TERTIARY));
        this.primaryButton.setBackgroundResource(R.drawable.rounded_edges);
        Context context = getContext();
        TextView textView = this.primaryButton;
        ThirdPartyHostedSplashTemplate thirdPartyHostedSplashTemplate = this.template;
        DynamicMessageModalUtil.setHtmlText(context, textView, thirdPartyHostedSplashTemplate.buttonPrimaryText, thirdPartyHostedSplashTemplate.blockRef, this.localContentId);
        String str2 = this.template.buttonPrimaryColor;
        if (str2 != null) {
            ((GradientDrawable) this.primaryButton.getBackground()).setColor(Color.parseColor(str2));
        }
        if (this.template.buttonSecondaryText != null) {
            fontUtil.applyFontStyle(this.secondaryButton, styleSheet.getFontStyle(FontStyleKey.INDEX_PRIMARY));
            TextView textView2 = this.secondaryButton;
            textView2.setPaintFlags(textView2.getPaintFlags() | 8);
            this.secondaryButton.setAllCaps(false);
            Context context2 = getContext();
            TextView textView3 = this.secondaryButton;
            ThirdPartyHostedSplashTemplate thirdPartyHostedSplashTemplate2 = this.template;
            DynamicMessageModalUtil.setHtmlText(context2, textView3, thirdPartyHostedSplashTemplate2.buttonSecondaryText, thirdPartyHostedSplashTemplate2.blockRef, this.localContentId);
            this.secondaryButton.setVisibility(0);
        }
        if (this.template.termsOfUse != null) {
            fontUtil.applyFontStyle(this.termsOfUse, styleSheet.getFontStyle(FontStyleKey.INDEX_PRIMARY));
            this.termsOfUse.setAllCaps(false);
            this.termsOfUse.setTypeface(Typeface.SANS_SERIF);
            Context context3 = getContext();
            TextView textView4 = this.termsOfUse;
            ThirdPartyHostedSplashTemplate thirdPartyHostedSplashTemplate3 = this.template;
            DynamicMessageModalUtil.setHtmlText(context3, textView4, thirdPartyHostedSplashTemplate3.termsOfUse, thirdPartyHostedSplashTemplate3.blockRef, this.localContentId);
            this.termsOfUse.setLinkTextColor(styleSheet.getColor(ColorKey.COLOR_PRIMARY).intValue());
            this.termsOfUse.setVisibility(0);
        }
        styleSheet.getIconBuilder(IconSizeKey.LARGE, IconStyleKey.PRIMARY).withIcon(getResources().getDrawable(R.drawable.ic_dialog_close_dark)).applyStyle(this.closeButton);
        if (i > 640) {
            ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) this.contentView.getLayoutParams();
            layoutParams.verticalBias = 1.0f;
            this.contentView.setLayoutParams(layoutParams);
        }
    }

    private void setupButtonClickHandler() {
        final Uri uri = Uri.parse(this.template.buttonPrimaryLink);
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.ThirdPartyHostedDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$0(uri, view);
            }
        });
        if (this.template.buttonSecondaryText != null) {
            this.secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.ThirdPartyHostedDialogFragment$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.lambda$setupButtonClickHandler$1(view);
                }
            });
        }
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.ThirdPartyHostedDialogFragment$$ExternalSyntheticLambda2
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
}
