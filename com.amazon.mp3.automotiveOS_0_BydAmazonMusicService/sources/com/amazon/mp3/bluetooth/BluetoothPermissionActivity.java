package com.amazon.mp3.bluetooth;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.activity.OnBackPressedDispatcherKt;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelLazy;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStore;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.bluetooth.BluetoothPermissionViewModel;
import com.amazon.mp3.styles.StyleSheetProvider;
import com.amazon.mp3.util.extensions.ContextKt;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiPageViewEvent;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.styles.StyleSheet;
import com.amazon.music.views.library.styles.keys.ButtonSizeKey;
import com.amazon.music.views.library.styles.keys.ButtonStyleKey;
import com.amazon.music.views.library.styles.keys.FontStyleKey;
import com.amazon.music.views.library.styles.keys.IconSizeKey;
import com.amazon.music.views.library.styles.keys.IconStyleKey;
import com.amazon.ui.foundations.styles.FontStyle;
import com.amazon.ui.foundations.utils.FontUtil;
import com.amazon.ui.foundations.views.BaseButton;
import kotlin.Lazy;
import kotlin.LazyKt__LazyJVMKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: compiled from: BluetoothPermissionActivity.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b6\u00107J\b\u0010\u0003\u001a\u00020\u0002H\u0002J\b\u0010\u0004\u001a\u00020\u0002H\u0002J\b\u0010\u0005\u001a\u00020\u0002H\u0002J\b\u0010\u0006\u001a\u00020\u0002H\u0002J\u0010\u0010\t\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0018\u0010\f\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\b\u001a\u00020\u0007H\u0002J\u0012\u0010\u000f\u001a\u00020\u00022\b\u0010\u000e\u001a\u0004\u0018\u00010\rH\u0014J\b\u0010\u0010\u001a\u00020\u0002H\u0014R\u001b\u0010\u0016\u001a\u00020\u00118FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\"\u0010\u0018\u001a\u00020\u00178\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001b\"\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001f\u001a\u0004\u0018\u00010\u001e8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u001b\u0010%\u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u0013\u001a\u0004\b#\u0010$R\u001b\u0010*\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b'\u0010\u0013\u001a\u0004\b(\u0010)R\u001b\u0010-\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010\u0013\u001a\u0004\b,\u0010)R\u001b\u00100\u001a\u00020&8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b.\u0010\u0013\u001a\u0004\b/\u0010)R\"\u00104\u001a\u0010\u0012\f\u0012\n 3*\u0004\u0018\u00010202018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105¨\u00068"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothPermissionActivity;", "Landroidx/activity/ComponentActivity;", "", "setupViews", "observeBluetoothAction", "setupForDialog", "setupForSettings", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "sendPageViewMetric", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "actionType", "sendUiClickMetric", "Landroid/os/Bundle;", "savedInstanceState", "onCreate", "onResume", "Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getViewModel", "()Lcom/amazon/mp3/bluetooth/BluetoothPermissionViewModel;", "viewModel", "", "externalSettingsOpen", "Z", "getExternalSettingsOpen", "()Z", "setExternalSettingsOpen", "(Z)V", "Lcom/amazon/mp3/activity/GrantPermissionActivity$PermissionResultReceiver;", "permissionResultReceiver", "Lcom/amazon/mp3/activity/GrantPermissionActivity$PermissionResultReceiver;", "Landroid/widget/TextView;", "contentTextView$delegate", "getContentTextView", "()Landroid/widget/TextView;", "contentTextView", "Lcom/amazon/ui/foundations/views/BaseButton;", "positiveButton$delegate", "getPositiveButton", "()Lcom/amazon/ui/foundations/views/BaseButton;", "positiveButton", "negativeButton$delegate", "getNegativeButton", "negativeButton", "closeButton$delegate", "getCloseButton", "closeButton", "Landroidx/activity/result/ActivityResultLauncher;", "", "kotlin.jvm.PlatformType", "requestPermissionLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BluetoothPermissionActivity extends ComponentActivity {
    private boolean externalSettingsOpen;
    private GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver;
    private final ActivityResultLauncher<String> requestPermissionLauncher;

    /* JADX INFO: renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel = new ViewModelLazy(Reflection.getOrCreateKotlinClass(BluetoothPermissionViewModel.class), new Function0<ViewModelStore>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$special$$inlined$viewModels$default$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelStore invoke() {
            ViewModelStore viewModelStore = this.getViewModelStore();
            Intrinsics.checkNotNullExpressionValue(viewModelStore, "viewModelStore");
            return viewModelStore;
        }
    }, new Function0<ViewModelProvider.Factory>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$special$$inlined$viewModels$default$1
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final ViewModelProvider.Factory invoke() {
            return this.getDefaultViewModelProviderFactory();
        }
    });

    /* JADX INFO: renamed from: contentTextView$delegate, reason: from kotlin metadata */
    private final Lazy contentTextView = LazyKt__LazyJVMKt.lazy(new Function0<TextView>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$contentTextView$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final TextView invoke() {
            return (TextView) this.this$0.findViewById(R.id.bt_permission_text);
        }
    });

    /* JADX INFO: renamed from: positiveButton$delegate, reason: from kotlin metadata */
    private final Lazy positiveButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$positiveButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.findViewById(R.id.bt_permission_positive_button);
        }
    });

    /* JADX INFO: renamed from: negativeButton$delegate, reason: from kotlin metadata */
    private final Lazy negativeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$negativeButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.findViewById(R.id.bt_permission_negative_button);
        }
    });

    /* JADX INFO: renamed from: closeButton$delegate, reason: from kotlin metadata */
    private final Lazy closeButton = LazyKt__LazyJVMKt.lazy(new Function0<BaseButton>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$closeButton$2
        {
            super(0);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final BaseButton invoke() {
            return (BaseButton) this.this$0.findViewById(R.id.bt_permission_close_button);
        }
    });

    public BluetoothPermissionActivity() {
        ActivityResultLauncher<String> activityResultLauncherRegisterForActivityResult = registerForActivityResult(new ActivityResultContracts.RequestPermission(), new ActivityResultCallback() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda4
            @Override // androidx.activity.result.ActivityResultCallback
            public final void onActivityResult(Object obj) {
                BluetoothPermissionActivity.m420requestPermissionLauncher$lambda6(this.f$0, (Boolean) obj);
            }
        });
        Intrinsics.checkNotNullExpressionValue(activityResultLauncherRegisterForActivityResult, "registerForActivityResul…   finish()\n            }");
        this.requestPermissionLauncher = activityResultLauncherRegisterForActivityResult;
    }

    public final BluetoothPermissionViewModel getViewModel() {
        return (BluetoothPermissionViewModel) this.viewModel.getValue();
    }

    private final TextView getContentTextView() {
        Object value = this.contentTextView.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-contentTextView>(...)");
        return (TextView) value;
    }

    private final BaseButton getPositiveButton() {
        Object value = this.positiveButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-positiveButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getNegativeButton() {
        Object value = this.negativeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-negativeButton>(...)");
        return (BaseButton) value;
    }

    private final BaseButton getCloseButton() {
        Object value = this.closeButton.getValue();
        Intrinsics.checkNotNullExpressionValue(value, "<get-closeButton>(...)");
        return (BaseButton) value;
    }

    @Override // androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver;
        super.onCreate(savedInstanceState);
        sendPageViewMetric(PageType.NEARBY_DEVICES_BLUETOOTH_COACHMARK);
        setContentView(R.layout.activity_bluetooth_permission);
        OnBackPressedDispatcher onBackPressedDispatcher = getOnBackPressedDispatcher();
        Intrinsics.checkNotNullExpressionValue(onBackPressedDispatcher, "onBackPressedDispatcher");
        OnBackPressedDispatcherKt.addCallback$default(onBackPressedDispatcher, this, false, new Function1<OnBackPressedCallback, Unit>() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity.onCreate.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(OnBackPressedCallback onBackPressedCallback) {
                invoke2(onBackPressedCallback);
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(OnBackPressedCallback addCallback) {
                Intrinsics.checkNotNullParameter(addCallback, "$this$addCallback");
                GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver2 = BluetoothPermissionActivity.this.permissionResultReceiver;
                if (permissionResultReceiver2 != null) {
                    permissionResultReceiver2.send(BluetoothUtil.INSTANCE.getNotGrantedResult(), new Bundle());
                }
                BluetoothPermissionActivity.this.finish();
            }
        }, 2, null);
        setupViews();
        observeBluetoothAction();
        Intent intent = getIntent();
        if (intent != null && (permissionResultReceiver = (GrantPermissionActivity.PermissionResultReceiver) intent.getParcelableExtra(GrantPermissionActivity.INTENT_EXTRA_RESULT_CALLBACK)) != null) {
            this.permissionResultReceiver = permissionResultReceiver;
        }
        getViewModel().checkPermissionState(this);
    }

    private final void setupViews() {
        getContentTextView().setMovementMethod(new LinkMovementMethod());
        getNegativeButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothPermissionActivity.m423setupViews$lambda1(this.f$0, view);
            }
        });
        BaseButton negativeButton = getNegativeButton();
        String string = getString(R.string.car_mode_bt_permission_button_maybe_later);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.car_m…ssion_button_maybe_later)");
        negativeButton.setText(string);
        getCloseButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothPermissionActivity.m424setupViews$lambda2(this.f$0, view);
            }
        });
        StyleSheetProvider.getStyleSheet().observe(this, new Observer() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BluetoothPermissionActivity.m425setupViews$lambda5(this.f$0, (StyleSheet) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m423setupViews$lambda1(BluetoothPermissionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric(ActionType.BLUETOOTH_COACHMARK_MAYBE_LATER, PageType.NEARBY_DEVICES_BLUETOOTH_COACHMARK);
        GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver = this$0.permissionResultReceiver;
        if (permissionResultReceiver != null) {
            permissionResultReceiver.send(BluetoothUtil.INSTANCE.getNotGrantedResult(), new Bundle());
        }
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m424setupViews$lambda2(BluetoothPermissionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric(ActionType.BLUETOOTH_COACHMARK_CLOSE, PageType.NEARBY_DEVICES_BLUETOOTH_COACHMARK);
        GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver = this$0.permissionResultReceiver;
        if (permissionResultReceiver != null) {
            permissionResultReceiver.send(BluetoothUtil.INSTANCE.getNotGrantedResult(), new Bundle());
        }
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m425setupViews$lambda5(BluetoothPermissionActivity this$0, StyleSheet styleSheet) {
        BaseButton.StyleBuilder iconBuilder;
        BaseButton.StyleBuilder styleBuilderWithIcon;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FontStyle fontStyle = styleSheet.getFontStyle(FontStyleKey.PRIMARY_T1_BIG_SHARPGROTESK);
        if (fontStyle != null) {
            FontUtil.INSTANCE.applyFontStyle(this$0.getContentTextView(), fontStyle);
        }
        ButtonSizeKey buttonSizeKey = ButtonSizeKey.LARGE;
        BaseButton.StyleBuilder buttonBuilder = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.SOLID);
        if (buttonBuilder != null) {
            buttonBuilder.applyStyle(this$0.getPositiveButton());
        }
        BaseButton.StyleBuilder buttonBuilder2 = styleSheet.getButtonBuilder(buttonSizeKey, ButtonStyleKey.GLASS);
        if (buttonBuilder2 != null) {
            buttonBuilder2.applyStyle(this$0.getNegativeButton());
        }
        Drawable drawable = ContextCompat.getDrawable(this$0, R.drawable.ic_close_light);
        if (drawable == null || (iconBuilder = styleSheet.getIconBuilder(IconSizeKey.MEDIUM, IconStyleKey.GLASS)) == null || (styleBuilderWithIcon = iconBuilder.withIcon(drawable)) == null) {
            return;
        }
        styleBuilderWithIcon.applyStyle(this$0.getCloseButton());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: requestPermissionLauncher$lambda-6, reason: not valid java name */
    public static final void m420requestPermissionLauncher$lambda6(BluetoothPermissionActivity this$0, Boolean it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Context applicationContext = this$0.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "applicationContext");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        BluetoothUtil.syncBTPermissionPref(applicationContext, it.booleanValue());
        if (it.booleanValue()) {
            this$0.sendUiClickMetric(ActionType.BLUETOOTH_OS_PROMPT_ALLOW, PageType.NEARBY_DEVICES_BLUETOOTH_OS_PROMPT);
            BluetoothUtil bluetoothUtil = BluetoothUtil.INSTANCE;
            bluetoothUtil.setBTPermissionFirstTimeRequested(this$0, true);
            GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver = this$0.permissionResultReceiver;
            if (permissionResultReceiver != null) {
                permissionResultReceiver.send(bluetoothUtil.getAllGrantedResult(), new Bundle());
            }
        } else {
            this$0.sendUiClickMetric(ActionType.BLUETOOTH_OS_PROMPT_DENY, PageType.NEARBY_DEVICES_BLUETOOTH_OS_PROMPT);
            GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver2 = this$0.permissionResultReceiver;
            if (permissionResultReceiver2 != null) {
                permissionResultReceiver2.send(BluetoothUtil.INSTANCE.getNotGrantedResult(), new Bundle());
            }
        }
        this$0.finish();
    }

    private final void observeBluetoothAction() {
        getViewModel().getAction().observe(this, new Observer() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                BluetoothPermissionActivity.m419observeBluetoothAction$lambda7(this.f$0, (BluetoothPermissionViewModel.BluetoothAction) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: observeBluetoothAction$lambda-7, reason: not valid java name */
    public static final void m419observeBluetoothAction$lambda7(BluetoothPermissionActivity this$0, BluetoothPermissionViewModel.BluetoothAction bluetoothAction) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (Intrinsics.areEqual(bluetoothAction, BluetoothPermissionViewModel.BluetoothAction.Finish.INSTANCE)) {
            this$0.finish();
        } else if (Intrinsics.areEqual(bluetoothAction, BluetoothPermissionViewModel.BluetoothAction.ShowSettings.INSTANCE)) {
            this$0.setupForSettings();
        } else if (Intrinsics.areEqual(bluetoothAction, BluetoothPermissionViewModel.BluetoothAction.ShowSystemDialog.INSTANCE)) {
            this$0.setupForDialog();
        }
    }

    private final void setupForDialog() {
        getContentTextView().setText(getString(R.string.car_mode_bt_permission_explanation));
        BaseButton positiveButton = getPositiveButton();
        String string = getString(R.string.car_mode_bt_permission_button_allow_bluetooth);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.car_m…n_button_allow_bluetooth)");
        positiveButton.setText(string);
        getPositiveButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothPermissionActivity.m421setupForDialog$lambda8(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupForDialog$lambda-8, reason: not valid java name */
    public static final void m421setupForDialog$lambda8(BluetoothPermissionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric(ActionType.BLUETOOTH_COACHMARK_CONTINUE, PageType.NEARBY_DEVICES_BLUETOOTH_COACHMARK);
        BluetoothUtil.INSTANCE.setBTPermissionFirstTimeRequested(this$0, false);
        this$0.requestPermissionLauncher.launch("android.permission.BLUETOOTH_CONNECT");
    }

    private final void setupForSettings() {
        getContentTextView().setText(getString(R.string.car_mode_bt_permission_settings_explanation));
        BaseButton positiveButton = getPositiveButton();
        String string = getString(R.string.dmusic_button_goto_settings);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.dmusic_button_goto_settings)");
        positiveButton.setText(string);
        getPositiveButton().setOnClickListener(new View.OnClickListener() { // from class: com.amazon.mp3.bluetooth.BluetoothPermissionActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                BluetoothPermissionActivity.m422setupForSettings$lambda9(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setupForSettings$lambda-9, reason: not valid java name */
    public static final void m422setupForSettings$lambda9(BluetoothPermissionActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.sendUiClickMetric(ActionType.BLUETOOTH_COACHMARK_GO_TO_SETTINGS, PageType.NEARBY_DEVICES_BLUETOOTH_COACHMARK);
        this$0.externalSettingsOpen = true;
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        Uri uriFromParts = Uri.fromParts("package", this$0.getPackageName(), null);
        Intrinsics.checkNotNullExpressionValue(uriFromParts, "fromParts(\"package\", packageName, null)");
        intent.setData(uriFromParts);
        this$0.startActivity(intent);
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        if (this.externalSettingsOpen) {
            GrantPermissionActivity.PermissionResultReceiver permissionResultReceiver = this.permissionResultReceiver;
            if (permissionResultReceiver != null) {
                permissionResultReceiver.send(ContextKt.isPermissionGranted(this, "android.permission.BLUETOOTH_CONNECT") ? BluetoothUtil.INSTANCE.getAllGrantedResult() : BluetoothUtil.INSTANCE.getNotGrantedResult(), new Bundle());
            }
            finish();
        }
    }

    private final void sendPageViewMetric(PageType pageType) {
        MetricsHolder.getManager().handleEvent(UiPageViewEvent.builder(pageType.getMetricsValue()).build());
    }

    private final void sendUiClickMetric(ActionType actionType, PageType pageType) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).withPageType(pageType).build());
    }
}
