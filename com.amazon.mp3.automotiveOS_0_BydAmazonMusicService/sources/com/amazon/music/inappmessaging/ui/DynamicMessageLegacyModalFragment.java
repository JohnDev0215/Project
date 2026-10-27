package com.amazon.music.inappmessaging.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.DynamicMessageOrchestrator;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.DynamicMessageEvent;
import com.amazon.music.inappmessaging.external.model.DynamicMessageMetricsAttributes;
import com.amazon.music.inappmessaging.external.model.LegacyModalTemplate;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageTemplateHelper;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ContainerType;
import java.net.URI;
import java.util.UUID;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/* JADX INFO: loaded from: classes4.dex */
public class DynamicMessageLegacyModalFragment extends DialogFragment {
    private static final String PAGE_TYPE_KEY = "PAGE_TYPE_KEY";
    private static final String REASON_TYPE_KEY = "REASON_TYPE_KEY";
    private static final String TAG = DynamicMessageLegacyModalFragment.class.getSimpleName();
    private static final String TEMPLATE_KEY = "TEMPLATE_KEY";
    private View backgroundView;
    private TextView bylineTextView;
    private TextView comfortTextView;
    private View dividingLine;
    private TextView headlineTextView;
    private View loadingView;
    private String localContentId;
    private String pageType;
    private Button primaryButton;
    private String reasonType;
    private Button secondaryButton;
    private LegacyModalTemplate template;
    private TextView trailingTextView;

    public static DynamicMessageLegacyModalFragment newInstance(final LegacyModalTemplate template, final DynamicMessageMetricsAttributes metricsAttributes) {
        DynamicMessageLegacyModalFragment dynamicMessageLegacyModalFragment = new DynamicMessageLegacyModalFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TEMPLATE_KEY, template);
        if (metricsAttributes == null) {
            bundle.putSerializable(PAGE_TYPE_KEY, null);
            bundle.putSerializable(REASON_TYPE_KEY, null);
        } else {
            bundle.putSerializable(PAGE_TYPE_KEY, metricsAttributes.getPageType());
            bundle.putSerializable(REASON_TYPE_KEY, metricsAttributes.getReasonType());
        }
        dynamicMessageLegacyModalFragment.setArguments(bundle);
        return dynamicMessageLegacyModalFragment;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.DialogTransparent);
    }

    @Override // androidx.fragment.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        long jCurrentTimeMillis = System.currentTimeMillis();
        View viewInflate = inflater.inflate(R.layout.legacy_dialog, container, false);
        this.headlineTextView = (TextView) viewInflate.findViewById(R.id.headline);
        this.bylineTextView = (TextView) viewInflate.findViewById(R.id.byline);
        this.primaryButton = (Button) viewInflate.findViewById(R.id.primaryButton);
        this.secondaryButton = (Button) viewInflate.findViewById(R.id.secondaryButton);
        this.comfortTextView = (TextView) viewInflate.findViewById(R.id.comfortText);
        this.trailingTextView = (TextView) viewInflate.findViewById(R.id.trailingText);
        this.dividingLine = viewInflate.findViewById(R.id.dividingLine);
        this.backgroundView = viewInflate.findViewById(R.id.backgroundView);
        this.loadingView = viewInflate.findViewById(R.id.loadingView);
        this.localContentId = UUID.randomUUID().toString().toLowerCase();
        Bundle arguments = getArguments();
        this.template = (LegacyModalTemplate) arguments.getSerializable(TEMPLATE_KEY);
        this.pageType = (String) arguments.getSerializable(PAGE_TYPE_KEY);
        this.reasonType = (String) arguments.getSerializable(REASON_TYPE_KEY);
        setupView();
        setupButtonClickHandler();
        if (savedInstanceState == null) {
            DynamicMessageViewUtil.sendUIContentViewEvent(this.template.messageRef, this.localContentId, this.pageType, this.reasonType, ContainerType.MODAL);
        }
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.ui.DynamicMessageLegacyModalFragment.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
                        return false;
                    }
                    DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_BACK_PRESSED_CLICK, DynamicMessageLegacyModalFragment.this.template.messageRef, DynamicMessageLegacyModalFragment.this.localContentId);
                    return false;
                }
            });
        }
        DynamicMessageEventSender.withInteractionType(DynamicMessagingInteractionType.DYNAMIC_MESSAGING_RENDER_LATENCY).withRenderLatencyMillis(Long.valueOf(System.currentTimeMillis() - jCurrentTimeMillis)).withTemplate(this.template.templateId).send();
        return viewInflate;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(final DialogInterface dialog) {
        super.onDismiss(dialog);
        if (getActivity() == null || !(getActivity() instanceof DynamicMessageDialogFragmentListener)) {
            return;
        }
        ((DynamicMessageDialogFragmentListener) getActivity()).onDismissClickEvent(dialog);
    }

    private void setupView() {
        String str = this.template.backgroundColor;
        if (str != null) {
            this.backgroundView.setBackgroundColor(Color.parseColor(str));
        }
        Context context = getContext();
        TextView textView = this.headlineTextView;
        LegacyModalTemplate legacyModalTemplate = this.template;
        DynamicMessageModalUtil.setHtmlText(context, textView, legacyModalTemplate.headline, legacyModalTemplate.messageRef, this.localContentId);
        String str2 = this.template.headlineFontSize;
        if (str2 != null) {
            this.headlineTextView.setTextSize(0, Float.parseFloat(str2));
        }
        Context context2 = getContext();
        TextView textView2 = this.bylineTextView;
        LegacyModalTemplate legacyModalTemplate2 = this.template;
        DynamicMessageModalUtil.setHtmlText(context2, textView2, legacyModalTemplate2.byline, legacyModalTemplate2.messageRef, this.localContentId);
        String str3 = this.template.bylineFontSize;
        if (str3 != null) {
            this.bylineTextView.setTextSize(0, Float.parseFloat(str3));
        }
        this.primaryButton.setText(this.template.primaryButtonText);
        String str4 = this.template.primaryButtonTextColor;
        if (str4 != null) {
            this.primaryButton.setTextColor(Color.parseColor(str4));
        }
        String str5 = this.template.primaryButtonBackgroundColor;
        if (str5 != null) {
            this.primaryButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(str5)));
            if (Color.parseColor(this.template.primaryButtonBackgroundColor) == 0) {
                setTransparentButtonBackground(this.primaryButton);
            }
        } else {
            this.primaryButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.primary_button_background_color)));
        }
        String str6 = this.template.buttonFontSize;
        if (str6 != null) {
            this.primaryButton.setTextSize(0, Float.parseFloat(str6));
        }
        if (this.template.secondaryButtonText != null) {
            this.secondaryButton.setVisibility(0);
            this.secondaryButton.setText(this.template.secondaryButtonText);
            String str7 = this.template.secondaryButtonTextColor;
            if (str7 != null) {
                this.secondaryButton.setTextColor(Color.parseColor(str7));
            }
            String str8 = this.template.buttonFontSize;
            if (str8 != null) {
                this.secondaryButton.setTextSize(0, Float.parseFloat(str8));
            }
            String str9 = this.template.secondaryButtonBackgroundColor;
            if (str9 != null) {
                this.secondaryButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(str9)));
                if (Color.parseColor(this.template.secondaryButtonBackgroundColor) == 0) {
                    setTransparentButtonBackground(this.secondaryButton);
                }
            } else {
                this.secondaryButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.secondary_button_background_color)));
            }
        }
        if (this.template.comfortText != null) {
            Context context3 = getContext();
            TextView textView3 = this.comfortTextView;
            LegacyModalTemplate legacyModalTemplate3 = this.template;
            DynamicMessageModalUtil.setHtmlText(context3, textView3, legacyModalTemplate3.comfortText, legacyModalTemplate3.messageRef, this.localContentId);
            this.comfortTextView.setVisibility(0);
            String str10 = this.template.comfortTextFontSize;
            if (str10 != null) {
                this.comfortTextView.setTextSize(0, Float.parseFloat(str10));
            }
        }
        if (this.template.trailingText != null) {
            this.dividingLine.setVisibility(0);
            this.trailingTextView.setVisibility(0);
            Context context4 = getContext();
            TextView textView4 = this.trailingTextView;
            LegacyModalTemplate legacyModalTemplate4 = this.template;
            DynamicMessageModalUtil.setHtmlText(context4, textView4, legacyModalTemplate4.trailingText, legacyModalTemplate4.messageRef, this.localContentId);
            String str11 = this.template.trailingTextFontSize;
            if (str11 != null) {
                this.trailingTextView.setTextSize(0, Float.parseFloat(str11));
            }
        }
    }

    private void setupButtonClickHandler() {
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.DynamicMessageLegacyModalFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$0(view);
            }
        });
        this.secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.DynamicMessageLegacyModalFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$1(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$0(View view) {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_CTA_CLICK, this.template.messageRef, this.localContentId);
        handleButtonClick(this.template.primaryButtonLink);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$1(View view) {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_CTA2_CLICK, this.template.messageRef, this.localContentId);
        handleButtonClick(this.template.secondaryButtonLink);
    }

    private void handleButtonClick(final URI buttonUri) {
        if (DynamicMessageTemplateHelper.isDismissButton(buttonUri.toString())) {
            getDialog().dismiss();
        } else {
            actionHandler(buttonUri);
        }
    }

    private void actionHandler(final URI buttonUri) {
        this.loadingView.setVisibility(0);
        DynamicMessageOrchestrator dynamicMessageOrchestrator = DynamicMessageOrchestrator.getInstance();
        final DynamicMessageEvent dynamicMessageEvent = new DynamicMessageEvent(buttonUri);
        dynamicMessageOrchestrator.displayDynamicMessage(getActivity(), dynamicMessageEvent, null).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1() { // from class: com.amazon.music.inappmessaging.ui.DynamicMessageLegacyModalFragment$$ExternalSyntheticLambda2
            @Override // rx.functions.Action1
            public final void call(Object obj) {
                this.f$0.lambda$actionHandler$2(dynamicMessageEvent, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$actionHandler$2(DynamicMessageEvent dynamicMessageEvent, Boolean bool) {
        DynamicMessageModalUtil.postEventExecute(dynamicMessageEvent);
        this.loadingView.setVisibility(8);
        dismiss();
    }

    private void setTransparentButtonBackground(final Button button) {
        button.setPadding(0, 20, 0, 0);
        button.setMinHeight(0);
        button.setMinimumHeight(0);
    }
}
