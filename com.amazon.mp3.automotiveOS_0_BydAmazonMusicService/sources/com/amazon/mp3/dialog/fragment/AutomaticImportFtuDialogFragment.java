package com.amazon.mp3.dialog.fragment;

import android.R;
import android.app.Dialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.automaticimport.AutomaticImportManager;
import com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutomaticImportFtuDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b-\u0010.J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002J\u0012\u0010\u0007\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002H\u0002J8\u0010\u0011\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000e\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u000fH\u0002J\b\u0010\u0013\u001a\u00020\u0004H\u0002J\u0012\u0010\u0016\u001a\u00020\u00042\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u00192\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u001e\u001a\u00020\u001dH\u0016R$\u0010!\u001a\u0004\u0018\u00010 8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$\"\u0004\b%\u0010&R$\u0010'\u001a\u0004\u0018\u00010\u001b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*\"\u0004\b+\u0010,¨\u0006/"}, d2 = {"Lcom/amazon/mp3/dialog/fragment/AutomaticImportFtuDialogFragment;", "Landroidx/fragment/app/DialogFragment;", "Lcom/amazon/ui/foundations/views/BaseButton;", "acceptButton", "", "initAcceptButton", "declineButton", "initDeclineButton", "", "title", MediaTrack.ROLE_DESCRIPTION, "buttonText", "", "buttonTextColor", "buttonColor", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "action", "showConfirmationDialog", "getAction", "setBackground", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "Landroid/content/Context;", "context", "onAttach", "Lcom/amazon/mp3/automaticimport/AutomaticImportManager;", "autoImportManager", "Lcom/amazon/mp3/automaticimport/AutomaticImportManager;", "getAutoImportManager", "()Lcom/amazon/mp3/automaticimport/AutomaticImportManager;", "setAutoImportManager", "(Lcom/amazon/mp3/automaticimport/AutomaticImportManager;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomaticImportFtuDialogFragment extends DialogFragment {
    private AutomaticImportManager autoImportManager;
    private View rootView;

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.Theme.Black.NoTitleBar.Fullscreen);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(com.amazon.mp3.automotiveOS.R.layout.automatic_import_ftu_layout, container);
        this.rootView = viewInflate;
        initAcceptButton(viewInflate == null ? null : (BaseButton) viewInflate.findViewById(com.amazon.mp3.automotiveOS.R.id.accept_button));
        View view = this.rootView;
        initDeclineButton(view != null ? (BaseButton) view.findViewById(com.amazon.mp3.automotiveOS.R.id.decline_button) : null);
        setBackground();
        return this.rootView;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onAttach(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        super.onAttach(context);
        this.autoImportManager = new AutomaticImportManager(context);
    }

    private final void initAcceptButton(final BaseButton acceptButton) {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.dialog.fragment.AutomaticImportFtuDialogFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AutomaticImportFtuDialogFragment.m903initAcceptButton$lambda0(this.f$0, acceptButton, (StyleSheet) obj);
            }
        });
        if (acceptButton == null) {
            return;
        }
        acceptButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.AutomaticImportFtuDialogFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutomaticImportFtuDialogFragment.m904initAcceptButton$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initAcceptButton$lambda-0, reason: not valid java name */
    public static final void m903initAcceptButton$lambda0(AutomaticImportFtuDialogFragment this$0, BaseButton baseButton, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.SOLID);
        Intrinsics.checkNotNull(buttonBuilder);
        String string = this$0.getString(com.amazon.mp3.automotiveOS.R.string.dmusic_button_yes);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_yes)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        Intrinsics.checkNotNull(baseButton);
        styleBuilderWithText.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initAcceptButton$lambda-1, reason: not valid java name */
    public static final void m904initAcceptButton$lambda1(AutomaticImportFtuDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomaticImportManager automaticImportManager = this$0.autoImportManager;
        if (automaticImportManager != null) {
            automaticImportManager.setAutomaticImportPersistedState(true);
        }
        AutomaticImportManager automaticImportManager2 = this$0.autoImportManager;
        if (automaticImportManager2 != null) {
            automaticImportManager2.automaticImportFtuCompleted();
        }
        String string = this$0.getString(com.amazon.mp3.automotiveOS.R.string.automatic_import_enabled);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.automatic_import_enabled)");
        String string2 = this$0.getString(com.amazon.mp3.automotiveOS.R.string.automatic_import_enabled_description);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.autom…port_enabled_description)");
        String string3 = this$0.getString(com.amazon.mp3.automotiveOS.R.string.dmusic_button_ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.dmusic_button_ok)");
        this$0.showConfirmationDialog(string, string2, string3, this$0.getResources().getColor(com.amazon.mp3.automotiveOS.R.color.black), this$0.getResources().getColor(com.amazon.mp3.automotiveOS.R.color.prime_blue), this$0.getAction());
        this$0.dismiss();
    }

    private final void initDeclineButton(final BaseButton declineButton) {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.dialog.fragment.AutomaticImportFtuDialogFragment$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AutomaticImportFtuDialogFragment.m905initDeclineButton$lambda2(this.f$0, declineButton, (StyleSheet) obj);
            }
        });
        if (declineButton == null) {
            return;
        }
        declineButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.AutomaticImportFtuDialogFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AutomaticImportFtuDialogFragment.m906initDeclineButton$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initDeclineButton$lambda-2, reason: not valid java name */
    public static final void m905initDeclineButton$lambda2(AutomaticImportFtuDialogFragment this$0, BaseButton baseButton, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(ButtonSizeKey.LARGE, ButtonStyleKey.OUTLINE);
        Intrinsics.checkNotNull(buttonBuilder);
        String string = this$0.getString(com.amazon.mp3.automotiveOS.R.string.dmusic_button_no);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_no)");
        BaseButton.StyleBuilder styleBuilderWithText = buttonBuilder.withText(string);
        if (styleBuilderWithText == null) {
            return;
        }
        Intrinsics.checkNotNull(baseButton);
        styleBuilderWithText.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initDeclineButton$lambda-3, reason: not valid java name */
    public static final void m906initDeclineButton$lambda3(AutomaticImportFtuDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        AutomaticImportManager automaticImportManager = this$0.autoImportManager;
        if (automaticImportManager != null) {
            automaticImportManager.setAutomaticImportPersistedState(false);
        }
        AutomaticImportManager automaticImportManager2 = this$0.autoImportManager;
        if (automaticImportManager2 != null) {
            Handler backgroundHandler = AmazonApplication.getBackgroundHandler();
            Intrinsics.checkNotNullExpressionValue(backgroundHandler, "getBackgroundHandler()");
            automaticImportManager2.removeImportedContent(backgroundHandler);
        }
        AutomaticImportManager automaticImportManager3 = this$0.autoImportManager;
        if (automaticImportManager3 != null) {
            automaticImportManager3.automaticImportFtuCompleted();
        }
        String string = this$0.getString(com.amazon.mp3.automotiveOS.R.string.automatic_import_disabled);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.automatic_import_disabled)");
        String string2 = this$0.getString(com.amazon.mp3.automotiveOS.R.string.automatic_import_disabled_description);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.autom…ort_disabled_description)");
        String string3 = this$0.getString(com.amazon.mp3.automotiveOS.R.string.dmusic_button_ok);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.dmusic_button_ok)");
        this$0.showConfirmationDialog(string, string2, string3, this$0.getResources().getColor(com.amazon.mp3.automotiveOS.R.color.black), this$0.getResources().getColor(com.amazon.mp3.automotiveOS.R.color.prime_blue), this$0.getAction());
        this$0.dismiss();
    }

    private final void showConfirmationDialog(String title, String description, String buttonText, int buttonTextColor, int buttonColor, ImportSettingDialogFragment.ConfirmAction action) {
        FragmentManager fragmentManager;
        FragmentActivity activity = getActivity();
        FragmentTransaction fragmentTransactionBeginTransaction = null;
        if (activity != null && (fragmentManager = activity.getFragmentManager()) != null) {
            fragmentTransactionBeginTransaction = fragmentManager.beginTransaction();
        }
        ImportSettingDialogFragment.INSTANCE.newInstance(title, description, buttonText, buttonTextColor, buttonColor, getAction()).show(fragmentTransactionBeginTransaction, ImportSettingDialogFragment.class.getSimpleName());
    }

    private final ImportSettingDialogFragment.ConfirmAction getAction() {
        return new ImportSettingDialogFragment.ConfirmAction() { // from class: com.amazon.mp3.dialog.fragment.AutomaticImportFtuDialogFragment.getAction.1
            @Override // com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment.ConfirmAction
            public void execute() {
            }
        };
    }

    private final void setBackground() {
        Window window;
        Drawable drawable = getResources().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.bauhaus_gradient_background);
        Dialog dialog = getDialog();
        if (dialog == null || (window = dialog.getWindow()) == null) {
            return;
        }
        window.setBackgroundDrawable(drawable);
    }
}
