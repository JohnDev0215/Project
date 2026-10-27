package com.amazon.mp3.dialog.fragment;

import android.R;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.views.BaseButton;
import com.google.android.gms.cast.MediaTrack;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ImportSettingDialogFragment.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0003?@AB\u0007¢\u0006\u0004\b=\u0010>J\b\u0010\u0004\u001a\u00020\u0003H\u0002J\u001a\u0010\t\u001a\u00020\u00032\b\u0010\u0006\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u001a\u0010\u000b\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u001a\u0010\u000e\u001a\u00020\u00032\b\u0010\r\u001a\u0004\u0018\u00010\f2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\u0011\u001a\u00020\u00032\b\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0002J\b\u0010\u0012\u001a\u00020\u0003H\u0002J\u0012\u0010\u0015\u001a\u00020\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0013H\u0016J\u0012\u0010\u0017\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0016J&\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u0019\u001a\u00020\u00182\b\u0010\u001b\u001a\u0004\u0018\u00010\u001a2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\u001e\u001a\u00020\u00032\b\u0010\u0016\u001a\u0004\u0018\u00010\u0007H\u0016J\b\u0010\u001f\u001a\u00020\u0003H\u0016J\b\u0010!\u001a\u00020 H\u0016R$\u0010#\u001a\u0004\u0018\u00010\"8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010*\u001a\u0004\u0018\u00010)8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00101\u001a\u0004\u0018\u0001008\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b1\u00102\u001a\u0004\b3\u00104\"\u0004\b5\u00106R$\u00107\u001a\u0004\u0018\u00010\u001c8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b7\u00108\u001a\u0004\b9\u0010:\"\u0004\b;\u0010<¨\u0006B"}, d2 = {"Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment;", "Landroid/app/DialogFragment;", "Landroidx/lifecycle/LifecycleOwner;", "", "onConfigurationChange", "Landroid/widget/TextView;", "title", "Landroid/os/Bundle;", "bundle", "initializeTitleTextView", MediaTrack.ROLE_DESCRIPTION, "initializeDescriptionTextView", "Landroid/widget/Button;", "confirmationButton", "initializeConfirmButton", "Lcom/amazon/ui/foundations/views/BaseButton;", "closeButton", "initializeCloseButton", "setBackground", "Landroid/content/Context;", "context", "onAttach", "savedInstanceState", "onCreate", "Landroid/view/LayoutInflater;", "inflater", "Landroid/view/ViewGroup;", "container", "Landroid/view/View;", "onCreateView", "onActivityCreated", "onStart", "Landroidx/lifecycle/Lifecycle;", "getLifecycle", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "confirmAction", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "getConfirmAction", "()Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "setConfirmAction", "(Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;)V", "Landroidx/lifecycle/LifecycleRegistry;", "lifecycleRegistry", "Landroidx/lifecycle/LifecycleRegistry;", "getLifecycleRegistry", "()Landroidx/lifecycle/LifecycleRegistry;", "setLifecycleRegistry", "(Landroidx/lifecycle/LifecycleRegistry;)V", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmActionProvider;", "confirmActionProvider", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmActionProvider;", "getConfirmActionProvider", "()Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmActionProvider;", "setConfirmActionProvider", "(Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmActionProvider;)V", "rootView", "Landroid/view/View;", "getRootView", "()Landroid/view/View;", "setRootView", "(Landroid/view/View;)V", "<init>", "()V", "Companion", "ConfirmAction", "ConfirmActionProvider", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ImportSettingDialogFragment extends DialogFragment implements LifecycleOwner {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ImportSettingDialogFragment.class.getSimpleName();
    private ConfirmAction confirmAction;
    private ConfirmActionProvider confirmActionProvider;
    private LifecycleRegistry lifecycleRegistry;
    private View rootView;

    /* JADX INFO: compiled from: ImportSettingDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "", "execute", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ConfirmAction {
        void execute();
    }

    /* JADX INFO: compiled from: ImportSettingDialogFragment.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&¨\u0006\u0004"}, d2 = {"Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmActionProvider;", "", "getConfirmAction", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface ConfirmActionProvider {
        ConfirmAction getConfirmAction();
    }

    public final void setConfirmAction(ConfirmAction confirmAction) {
        this.confirmAction = confirmAction;
    }

    /* JADX INFO: compiled from: ImportSettingDialogFragment.kt */
    @Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0013\u0010\u0014J6\u0010\f\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\tR\u0014\u0010\r\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u000eR\u0014\u0010\u0010\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u000eR\u0014\u0010\u0011\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0011\u0010\u000eR\u0014\u0010\u0012\u001a\u00020\u00028\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0012\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$Companion;", "", "", "title", MediaTrack.ROLE_DESCRIPTION, "buttonText", "", "buttonTextColor", "buttonColor", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment$ConfirmAction;", "action", "Lcom/amazon/mp3/dialog/fragment/ImportSettingDialogFragment;", "newInstance", "BUTTON_COLOR_KEY", "Ljava/lang/String;", "BUTTON_TEXT_COLOR_KEY", "BUTTON_TEXT_KEY", "DESCRIPTION_KEY", "TITLE_KEY", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ImportSettingDialogFragment newInstance(String title, String description, String buttonText, int buttonTextColor, int buttonColor, ConfirmAction action) {
            Intrinsics.checkNotNullParameter(title, "title");
            Intrinsics.checkNotNullParameter(description, "description");
            Intrinsics.checkNotNullParameter(buttonText, "buttonText");
            Intrinsics.checkNotNullParameter(action, "action");
            Bundle bundle = new Bundle();
            bundle.putString("TITLE_KEY", title);
            bundle.putString("DESCRIPTION_KEY", description);
            bundle.putString("BUTTON_TEXT_KEY", buttonText);
            bundle.putInt("BUTTON_TEXT_COLOR_KEY", buttonTextColor);
            bundle.putInt("BUTTON_COLOR_KEY", buttonColor);
            ImportSettingDialogFragment importSettingDialogFragment = new ImportSettingDialogFragment();
            importSettingDialogFragment.setConfirmAction(action);
            importSettingDialogFragment.setArguments(bundle);
            return importSettingDialogFragment;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // android.app.DialogFragment, android.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            this.confirmActionProvider = (ConfirmActionProvider) context;
        } catch (Exception unused) {
            Log.error(TAG, Intrinsics.stringPlus("Failed to cast host activity to a ", ConfirmActionProvider.class.getSimpleName()));
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(0, R.style.Theme.Black.NoTitleBar.Fullscreen);
        LifecycleRegistry lifecycleRegistry = new LifecycleRegistry(this);
        this.lifecycleRegistry = lifecycleRegistry;
        lifecycleRegistry.markState(Lifecycle.State.CREATED);
    }

    @Override // android.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        View viewInflate = inflater.inflate(com.amazon.mp3.automotiveOS.R.layout.import_setting_confirmation_layout, container);
        this.rootView = viewInflate;
        TextView textView = viewInflate == null ? null : (TextView) viewInflate.findViewById(com.amazon.mp3.automotiveOS.R.id.title);
        View view = this.rootView;
        TextView textView2 = view == null ? null : (TextView) view.findViewById(com.amazon.mp3.automotiveOS.R.id.description);
        View view2 = this.rootView;
        Button button = view2 == null ? null : (Button) view2.findViewById(com.amazon.mp3.automotiveOS.R.id.confirm_button);
        View view3 = this.rootView;
        BaseButton baseButton = view3 != null ? (BaseButton) view3.findViewById(com.amazon.mp3.automotiveOS.R.id.closeButton) : null;
        Bundle bundle = getArguments();
        Intrinsics.checkNotNullExpressionValue(bundle, "bundle");
        initializeTitleTextView(textView, bundle);
        initializeDescriptionTextView(textView2, bundle);
        initializeConfirmButton(button, bundle);
        initializeCloseButton(baseButton);
        setBackground();
        return this.rootView;
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            onConfigurationChange();
        }
    }

    @Override // android.app.DialogFragment, android.app.Fragment
    public void onStart() {
        super.onStart();
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        if (lifecycleRegistry == null) {
            return;
        }
        lifecycleRegistry.markState(Lifecycle.State.STARTED);
    }

    @Override // androidx.lifecycle.LifecycleOwner
    public Lifecycle getLifecycle() {
        LifecycleRegistry lifecycleRegistry = this.lifecycleRegistry;
        Intrinsics.checkNotNull(lifecycleRegistry);
        return lifecycleRegistry;
    }

    private final void onConfigurationChange() {
        ConfirmActionProvider confirmActionProvider = this.confirmActionProvider;
        this.confirmAction = confirmActionProvider == null ? null : confirmActionProvider.getConfirmAction();
        View view = this.rootView;
        Button button = view != null ? (Button) view.findViewById(com.amazon.mp3.automotiveOS.R.id.confirm_button) : null;
        if (button == null) {
            return;
        }
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                ImportSettingDialogFragment.m912onConfigurationChange$lambda0(this.f$0, view2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onConfigurationChange$lambda-0, reason: not valid java name */
    public static final void m912onConfigurationChange$lambda0(ImportSettingDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConfirmAction confirmAction = this$0.confirmAction;
        if (confirmAction != null) {
            confirmAction.execute();
        }
        this$0.dismiss();
    }

    private final void initializeTitleTextView(TextView title, Bundle bundle) {
        if (title != null) {
            title.setText(bundle.getString("TITLE_KEY"));
        }
        if (title == null) {
            return;
        }
        title.setTextSize(0, getResources().getDimension(com.amazon.mp3.automotiveOS.R.dimen.text_size_large));
    }

    private final void initializeDescriptionTextView(TextView description, Bundle bundle) {
        if (description != null) {
            description.setText(bundle.getString("DESCRIPTION_KEY"));
        }
        if (description == null) {
            return;
        }
        description.setTextSize(0, getResources().getDimension(com.amazon.mp3.automotiveOS.R.dimen.text_size_medium));
    }

    private final void initializeConfirmButton(Button confirmationButton, Bundle bundle) {
        Drawable background;
        if (confirmationButton != null) {
            confirmationButton.setText(bundle.getString("BUTTON_TEXT_KEY"));
        }
        if (confirmationButton != null && (background = confirmationButton.getBackground()) != null) {
            background.setTint(bundle.getInt("BUTTON_COLOR_KEY"));
        }
        if (confirmationButton != null) {
            confirmationButton.setTextColor(bundle.getInt("BUTTON_TEXT_COLOR_KEY"));
        }
        if (confirmationButton == null) {
            return;
        }
        confirmationButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImportSettingDialogFragment.m911initializeConfirmButton$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeConfirmButton$lambda-1, reason: not valid java name */
    public static final void m911initializeConfirmButton$lambda1(ImportSettingDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ConfirmAction confirmAction = this$0.confirmAction;
        if (confirmAction != null) {
            confirmAction.execute();
        }
        this$0.dismiss();
    }

    private final void initializeCloseButton(final BaseButton closeButton) {
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ImportSettingDialogFragment.m909initializeCloseButton$lambda2(this.f$0, closeButton, (StyleSheet) obj);
            }
        });
        if (closeButton == null) {
            return;
        }
        closeButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.dialog.fragment.ImportSettingDialogFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ImportSettingDialogFragment.m910initializeCloseButton$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCloseButton$lambda-2, reason: not valid java name */
    public static final void m909initializeCloseButton$lambda2(ImportSettingDialogFragment this$0, BaseButton baseButton, StyleSheet styleSheet) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        BaseButton.StyleBuilder iconBuilder = styleSheet.getIconBuilder(IconSizeKey.SMALL, IconStyleKey.GLASS);
        if (iconBuilder == null) {
            return;
        }
        Drawable drawable = this$0.getActivity().getResources().getDrawable(com.amazon.mp3.automotiveOS.R.drawable.ic_action_cancel);
        Intrinsics.checkNotNullExpressionValue(drawable, "activity.resources.getDr…rawable.ic_action_cancel)");
        BaseButton.StyleBuilder styleBuilderWithIcon = iconBuilder.withIcon(drawable);
        if (styleBuilderWithIcon == null) {
            return;
        }
        Intrinsics.checkNotNull(baseButton);
        styleBuilderWithIcon.applyStyle(baseButton);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: initializeCloseButton$lambda-3, reason: not valid java name */
    public static final void m910initializeCloseButton$lambda3(ImportSettingDialogFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismiss();
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
