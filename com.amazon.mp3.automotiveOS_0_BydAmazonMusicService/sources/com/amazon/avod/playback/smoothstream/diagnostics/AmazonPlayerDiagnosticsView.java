package com.amazon.avod.playback.smoothstream.diagnostics;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import com.amazon.avod.playback.R$id;
import com.amazon.avod.playback.R$layout;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AmazonPlayerDiagnosticsView.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 )2\u00020\u00012\u00020\u0002:\u0001)B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\b\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020!2\u0006\u0010#\u001a\u00020$H\u0016J\b\u0010%\u001a\u00020!H\u0002J\b\u0010&\u001a\u00020!H\u0002J\b\u0010'\u001a\u00020!H\u0002J\b\u0010(\u001a\u00020!H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/AmazonPlayerDiagnosticsView;", "Landroid/widget/LinearLayout;", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticUpdateListener;", "context", "Landroid/content/Context;", "viewModel", "Lcom/amazon/avod/playback/smoothstream/diagnostics/AmazonPlayerDiagnosticsViewModel;", "(Landroid/content/Context;Lcom/amazon/avod/playback/smoothstream/diagnostics/AmazonPlayerDiagnosticsViewModel;)V", "audioStreamDiagnosticText", "Landroid/widget/TextView;", "bufferingCountDiagnosticText", "cdnInfoDiagnosticText", "contentTypeDiagnosticText", "deliveryProtocolDiagnosticText", "deviceModelNameDiagnosticText", "deviceProfileNameDiagnosticText", "diagnosticsCopyButton", "Landroid/widget/Button;", "diagnosticsLogButton", "diagnosticsScrollDownButton", "Landroid/widget/ImageButton;", "diagnosticsScrollUpButton", "diagnosticsScrollView", "Landroid/widget/ScrollView;", "droppedFramesDiagnosticText", "heuristicInfoDiagnosticText", "javaHeapDiagnosticText", "manifestUrlDiagnosticText", "nativeHeapDiagnosticText", "rendererDrmSchemeDiagnosticText", "sessionTypeDiagnosticText", "videoStreamDiagnosticText", "copyDiagnosticsToClipboard", "", "diagnosticDataUpdated", "collector", "Lcom/amazon/avod/playback/smoothstream/diagnostics/DiagnosticDataCollector;", "logDiagnostics", "scrollDown", "scrollUp", "updateDiagnosticsTextViews", "Companion", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class AmazonPlayerDiagnosticsView extends LinearLayout implements DiagnosticUpdateListener {
    private static final Companion Companion = new Companion(null);
    private final TextView audioStreamDiagnosticText;
    private final TextView bufferingCountDiagnosticText;
    private final TextView cdnInfoDiagnosticText;
    private final TextView contentTypeDiagnosticText;
    private final TextView deliveryProtocolDiagnosticText;
    private final TextView deviceModelNameDiagnosticText;
    private final TextView deviceProfileNameDiagnosticText;
    private final Button diagnosticsCopyButton;
    private final Button diagnosticsLogButton;
    private final ImageButton diagnosticsScrollDownButton;
    private final ImageButton diagnosticsScrollUpButton;
    private final ScrollView diagnosticsScrollView;
    private final TextView droppedFramesDiagnosticText;
    private final TextView heuristicInfoDiagnosticText;
    private final TextView javaHeapDiagnosticText;
    private final TextView manifestUrlDiagnosticText;
    private final TextView nativeHeapDiagnosticText;
    private final TextView rendererDrmSchemeDiagnosticText;
    private final TextView sessionTypeDiagnosticText;
    private final TextView videoStreamDiagnosticText;
    private final AmazonPlayerDiagnosticsViewModel viewModel;

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AmazonPlayerDiagnosticsView(Context context) {
        this(context, null, 2, 0 == true ? 1 : 0);
        Intrinsics.checkNotNullParameter(context, "context");
    }

    public /* synthetic */ AmazonPlayerDiagnosticsView(Context context, AmazonPlayerDiagnosticsViewModel amazonPlayerDiagnosticsViewModel, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? new DefaultAmazonPlayerDiagnosticsViewModel(null, null, null, 7, null) : amazonPlayerDiagnosticsViewModel);
    }

    /* JADX INFO: compiled from: AmazonPlayerDiagnosticsView.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/amazon/avod/playback/smoothstream/diagnostics/AmazonPlayerDiagnosticsView$Companion;", "", "()V", "BACKGROUND_ALPHA", "", "SCROLL_DELTA", "TAG", "", "ATVAndroidPlayback_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public AmazonPlayerDiagnosticsView(Context context, AmazonPlayerDiagnosticsViewModel viewModel) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(viewModel, "viewModel");
        this.viewModel = viewModel;
        setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        Object systemService = context.getSystemService("layout_inflater");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.view.LayoutInflater");
        }
        View viewInflate = ((LayoutInflater) systemService).inflate(R$layout.player_diagnostics_text, (ViewGroup) this, true);
        View viewFindViewById = viewInflate.findViewById(R$id.diagnosticsScrollView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "view.findViewById(R.id.diagnosticsScrollView)");
        this.diagnosticsScrollView = (ScrollView) viewFindViewById;
        View viewFindViewById2 = viewInflate.findViewById(R$id.diagnosticsCopyButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "view.findViewById(R.id.diagnosticsCopyButton)");
        Button button = (Button) viewFindViewById2;
        this.diagnosticsCopyButton = button;
        View viewFindViewById3 = viewInflate.findViewById(R$id.diagnosticsDLogButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "view.findViewById(R.id.diagnosticsDLogButton)");
        Button button2 = (Button) viewFindViewById3;
        this.diagnosticsLogButton = button2;
        View viewFindViewById4 = viewInflate.findViewById(R$id.diagnosticsScrollUpButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "view.findViewById(R.id.diagnosticsScrollUpButton)");
        ImageButton imageButton = (ImageButton) viewFindViewById4;
        this.diagnosticsScrollUpButton = imageButton;
        View viewFindViewById5 = viewInflate.findViewById(R$id.diagnosticScrollDownButton);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "view.findViewById(R.id.diagnosticScrollDownButton)");
        ImageButton imageButton2 = (ImageButton) viewFindViewById5;
        this.diagnosticsScrollDownButton = imageButton2;
        View viewFindViewById6 = viewInflate.findViewById(R$id.deliveryProtocolDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "view.findViewById(R.id.d…otocolDiagnosticTextView)");
        this.deliveryProtocolDiagnosticText = (TextView) viewFindViewById6;
        View viewFindViewById7 = viewInflate.findViewById(R$id.videoStreamDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "view.findViewById(R.id.v…StreamDiagnosticTextView)");
        this.videoStreamDiagnosticText = (TextView) viewFindViewById7;
        View viewFindViewById8 = viewInflate.findViewById(R$id.audioStreamDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "view.findViewById(R.id.a…StreamDiagnosticTextView)");
        this.audioStreamDiagnosticText = (TextView) viewFindViewById8;
        View viewFindViewById9 = viewInflate.findViewById(R$id.bufferingCountDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "view.findViewById(R.id.b…gCountDiagnosticTextView)");
        this.bufferingCountDiagnosticText = (TextView) viewFindViewById9;
        View viewFindViewById10 = viewInflate.findViewById(R$id.javaHeapStatsDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "view.findViewById(R.id.j…pStatsDiagnosticTextView)");
        this.javaHeapDiagnosticText = (TextView) viewFindViewById10;
        View viewFindViewById11 = viewInflate.findViewById(R$id.nativeHeapStatsDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "view.findViewById(R.id.n…pStatsDiagnosticTextView)");
        this.nativeHeapDiagnosticText = (TextView) viewFindViewById11;
        View viewFindViewById12 = viewInflate.findViewById(R$id.droppedFramesDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById12, "view.findViewById(R.id.d…FramesDiagnosticTextView)");
        this.droppedFramesDiagnosticText = (TextView) viewFindViewById12;
        View viewFindViewById13 = viewInflate.findViewById(R$id.rendererDrmSchemeDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById13, "view.findViewById(R.id.r…SchemeDiagnosticTextView)");
        this.rendererDrmSchemeDiagnosticText = (TextView) viewFindViewById13;
        View viewFindViewById14 = viewInflate.findViewById(R$id.deviceProfileNameDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById14, "view.findViewById(R.id.d…leNameDiagnosticTextView)");
        this.deviceProfileNameDiagnosticText = (TextView) viewFindViewById14;
        View viewFindViewById15 = viewInflate.findViewById(R$id.deviceModelNameDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById15, "view.findViewById(R.id.d…elNameDiagnosticTextView)");
        this.deviceModelNameDiagnosticText = (TextView) viewFindViewById15;
        View viewFindViewById16 = viewInflate.findViewById(R$id.sessionTypeDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById16, "view.findViewById(R.id.s…onTypeDiagnosticTextView)");
        this.sessionTypeDiagnosticText = (TextView) viewFindViewById16;
        View viewFindViewById17 = viewInflate.findViewById(R$id.cdnInfoDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById17, "view.findViewById(R.id.cdnInfoDiagnosticTextView)");
        this.cdnInfoDiagnosticText = (TextView) viewFindViewById17;
        View viewFindViewById18 = viewInflate.findViewById(R$id.contentTypeDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById18, "view.findViewById(R.id.c…ntTypeDiagnosticTextView)");
        this.contentTypeDiagnosticText = (TextView) viewFindViewById18;
        View viewFindViewById19 = viewInflate.findViewById(R$id.heuristicInfoDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById19, "view.findViewById(R.id.h…icInfoDiagnosticTextView)");
        this.heuristicInfoDiagnosticText = (TextView) viewFindViewById19;
        View viewFindViewById20 = viewInflate.findViewById(R$id.manifestUrlDiagnosticTextView);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById20, "view.findViewById(R.id.m…estUrlDiagnosticTextView)");
        this.manifestUrlDiagnosticText = (TextView) viewFindViewById20;
        button.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsView$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AmazonPlayerDiagnosticsView.m90_init_$lambda0(this.f$0, view);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsView$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AmazonPlayerDiagnosticsView.m91_init_$lambda1(this.f$0, view);
            }
        });
        imageButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsView$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AmazonPlayerDiagnosticsView.m92_init_$lambda2(this.f$0, view);
            }
        });
        imageButton2.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.avod.playback.smoothstream.diagnostics.AmazonPlayerDiagnosticsView$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AmazonPlayerDiagnosticsView.m93_init_$lambda3(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-0, reason: not valid java name */
    public static final void m90_init_$lambda0(AmazonPlayerDiagnosticsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.copyDiagnosticsToClipboard();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-1, reason: not valid java name */
    public static final void m91_init_$lambda1(AmazonPlayerDiagnosticsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.logDiagnostics();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-2, reason: not valid java name */
    public static final void m92_init_$lambda2(AmazonPlayerDiagnosticsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.scrollUp();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: _init_$lambda-3, reason: not valid java name */
    public static final void m93_init_$lambda3(AmazonPlayerDiagnosticsView this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.scrollDown();
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticUpdateListener
    public void diagnosticDataUpdated(DiagnosticDataCollector collector) {
        Intrinsics.checkNotNullParameter(collector, "collector");
        Preconditions.checkNotNull(collector, "collector", new Object[0]);
        this.viewModel.updateDiagnostics(collector);
        updateDiagnosticsTextViews();
    }

    private final void updateDiagnosticsTextViews() {
        this.deliveryProtocolDiagnosticText.setText(this.viewModel.getDeliveryProtocolDiagnostic());
        this.videoStreamDiagnosticText.setText(this.viewModel.getVideoStreamDiagnostic());
        this.audioStreamDiagnosticText.setText(this.viewModel.getAudioStreamDiagnostic());
        this.bufferingCountDiagnosticText.setText(this.viewModel.getBufferingCountDiagnostic());
        this.javaHeapDiagnosticText.setText(this.viewModel.getJavaHeapDiagnostic());
        this.nativeHeapDiagnosticText.setText(this.viewModel.getNativeHeapDiagnostic());
        this.droppedFramesDiagnosticText.setText(this.viewModel.getDroppedFramesDiagnostic());
        this.rendererDrmSchemeDiagnosticText.setText(this.viewModel.getRendererDrmSchemeDiagnostic());
        this.deviceProfileNameDiagnosticText.setText(this.viewModel.getDeviceProfileNameDiagnostic());
        this.deviceModelNameDiagnosticText.setText(this.viewModel.getDeviceModelNameDiagnostic());
        this.sessionTypeDiagnosticText.setText(this.viewModel.getSessionTypeDiagnostic());
        this.cdnInfoDiagnosticText.setText(this.viewModel.getCdnInfoDiagnostic());
        this.contentTypeDiagnosticText.setText(this.viewModel.getContentTypeDiagnostic());
        this.heuristicInfoDiagnosticText.setText(this.viewModel.getHeuristicsDiagnostic());
        this.manifestUrlDiagnosticText.setText(this.viewModel.getManifestUrlDiagnostic());
        invalidate();
    }

    private final void logDiagnostics() {
        DLog.devf("%s: tapped log diagnostics button", "SMOOTH_STREAM_DIAGNOSTICS_VIEW");
        DLog.logf("%s: %s", "SMOOTH_STREAM_DIAGNOSTICS_VIEW", this.viewModel.getCompleteDiagnosticsString());
    }

    private final void copyDiagnosticsToClipboard() {
        DLog.devf("%s: tapped copy diagnostics button UPDATED", "SMOOTH_STREAM_DIAGNOSTICS_VIEW");
        ClipData clipDataNewPlainText = ClipData.newPlainText("Smooth Stream Diagnostics", this.viewModel.getCompleteDiagnosticsString());
        Intrinsics.checkNotNullExpressionValue(clipDataNewPlainText, "newPlainText(clipLabel, …pleteDiagnosticsString())");
        Object systemService = getContext().getSystemService("clipboard");
        if (systemService == null) {
            throw new NullPointerException("null cannot be cast to non-null type android.content.ClipboardManager");
        }
        ((ClipboardManager) systemService).setPrimaryClip(clipDataNewPlainText);
    }

    private final void scrollUp() {
        DLog.devf("%s: tapped scroll up button", "SMOOTH_STREAM_DIAGNOSTICS_VIEW");
        this.diagnosticsScrollView.smoothScrollBy(-75, -75);
    }

    private final void scrollDown() {
        DLog.devf("%s: tapped scroll down button", "SMOOTH_STREAM_DIAGNOSTICS_VIEW");
        this.diagnosticsScrollView.smoothScrollBy(75, 75);
    }
}
