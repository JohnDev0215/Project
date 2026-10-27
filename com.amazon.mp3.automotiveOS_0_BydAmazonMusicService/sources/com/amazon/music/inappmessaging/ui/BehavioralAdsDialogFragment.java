package com.amazon.music.inappmessaging.ui;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import com.amazon.music.inappmessaging.R;
import com.amazon.music.inappmessaging.external.DynamicMessageDialogFragmentListener;
import com.amazon.music.inappmessaging.external.DynamicMessageStyleSheetLoader;
import com.amazon.music.inappmessaging.external.events.DynamicMessageEventSender;
import com.amazon.music.inappmessaging.external.model.BehavioralAdsTemplate;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageModalUtil;
import com.amazon.music.inappmessaging.internal.util.DynamicMessageViewUtil;
import com.amazon.music.metrics.mts.event.types.DynamicMessagingInteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import java.util.UUID;

/* JADX INFO: loaded from: classes4.dex */
public class BehavioralAdsDialogFragment extends DialogFragment {
    private static final String TAG = BehavioralAdsDialogFragment.class.getSimpleName();
    private static final String TEMPLATE_KEY = "TEMPLATE_KEY";
    private View backgroundView;
    private TextView body;
    private BaseButton closeButton;
    private TextView headline;
    private String localContentId;
    private BaseButton primaryButton;
    private BehavioralAdsTemplate template;

    public static BehavioralAdsDialogFragment newInstance(final BehavioralAdsTemplate template) {
        BehavioralAdsDialogFragment behavioralAdsDialogFragment = new BehavioralAdsDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable(TEMPLATE_KEY, template);
        behavioralAdsDialogFragment.setArguments(bundle);
        return behavioralAdsDialogFragment;
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
        View viewInflate = inflater.inflate(R.layout.behavioral_ads_notification_dialog, container, false);
        this.headline = (TextView) viewInflate.findViewById(R.id.ba_headline);
        this.body = (TextView) viewInflate.findViewById(R.id.ba_body);
        this.primaryButton = (BaseButton) viewInflate.findViewById(R.id.ba_primaryButton);
        this.closeButton = (BaseButton) viewInflate.findViewById(R.id.ba_closeButton);
        this.backgroundView = viewInflate.findViewById(R.id.ba_backgroundView);
        this.localContentId = UUID.randomUUID().toString().toLowerCase();
        this.template = (BehavioralAdsTemplate) getArguments().getSerializable(TEMPLATE_KEY);
        setupView();
        setupButtonClickHandler();
        if (savedInstanceState == null) {
            DynamicMessageViewUtil.sendUIPageViewEvent(this.template.blockRef, false);
        }
        if (getDialog() != null) {
            getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.amazon.music.inappmessaging.ui.BehavioralAdsDialogFragment.1
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                    if (keyEvent.getAction() != 0 || keyEvent.getKeyCode() != 4) {
                        return false;
                    }
                    DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_BACK_PRESSED_CLICK, BehavioralAdsDialogFragment.this.template.blockRef, BehavioralAdsDialogFragment.this.localContentId);
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
            if (getActivity() == null || !(getActivity() instanceof DynamicMessageDialogFragmentListener)) {
                return;
            }
            ((DynamicMessageDialogFragmentListener) getActivity()).onShow(getDialog());
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

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$0(View view) {
        dismissHandler();
    }

    private void setupButtonClickHandler() {
        this.primaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.BehavioralAdsDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$0(view);
            }
        });
        this.closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.music.inappmessaging.ui.BehavioralAdsDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$setupButtonClickHandler$1(view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setupButtonClickHandler$1(View view) {
        dismissHandler();
    }

    private void dismissHandler() {
        DynamicMessageModalUtil.sendUIClickEvent(ActionType.DYNAMIC_MESSAGE_DISMISS_CLICK, this.template.blockRef, this.localContentId);
        getDialog().dismiss();
    }

    private void setupView() {
        StyleSheet styleSheet = DynamicMessageStyleSheetLoader.getInstance().getStyleSheet();
        this.backgroundView.setBackground(getResources().getDrawable(R.drawable.bauhaus_gradient_background));
        this.headline.setText(this.template.headline);
        FontUtil fontUtil = FontUtil.INSTANCE;
        fontUtil.applyFontStyle(this.headline, styleSheet.getFontStyle(FontStyleKey.HEADLINE_4));
        this.body.setText(this.template.body);
        fontUtil.applyFontStyle(this.body, styleSheet.getFontStyle(FontStyleKey.PRIMARY));
        styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID).withText(this.template.buttonPrimaryText).withWidth(-1).applyStyle(this.primaryButton);
        styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS).withIcon(getResources().getDrawable(R.drawable.ic_dialog_close_dark)).applyStyle(this.closeButton);
    }
}
