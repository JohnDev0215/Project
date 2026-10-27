package com.amazon.avod.playback.sye.diagnostics;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.avod.media.VideoResolution;
import com.amazon.avod.media.diagnostics.BaseDiagnosticsController;
import com.amazon.avod.playback.sye.SyeDomainVendingMachine;
import com.amazon.avod.playback.sye.statistics.MetricsListener;
import com.google.common.base.Preconditions;
import com.netinsight.sye.syeClient.ISyeMetrics;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SyeDiagnosticsController extends BaseDiagnosticsController implements MetricsListener {
    private SyeDomainVendingMachine mSyeDomainVendingMachine;
    private final Handler mUiHandler;

    public SyeDiagnosticsController(@Nonnull Context context, @Nonnull SyeDomainVendingMachine syeDomainVendingMachine) {
        this.mUiHandler = new Handler(context.getMainLooper());
        this.mSyeDomainVendingMachine = (SyeDomainVendingMachine) Preconditions.checkNotNull(syeDomainVendingMachine, "syeDomainVendingMachine");
    }

    @Override // com.amazon.avod.playback.sye.statistics.MetricsListener
    public void onMetricsAvailable(final ISyeMetrics iSyeMetrics, int i, int i2, @Nullable VideoResolution videoResolution) {
        this.mUiHandler.post(new Runnable() { // from class: com.amazon.avod.playback.sye.diagnostics.SyeDiagnosticsController$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$onMetricsAvailable$0(iSyeMetrics);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onMetricsAvailable$0(ISyeMetrics iSyeMetrics) {
        TextView textView = this.mDiagnosticText;
        if (textView != null) {
            textView.setText(String.format(Locale.US, "%s | CDN:%s", iSyeMetrics.toString(), this.mSyeDomainVendingMachine.currentSyeDomain().getSyeDomain().getName()));
        }
    }

    @Override // com.amazon.avod.media.diagnostics.BaseDiagnosticsController, com.amazon.avod.media.diagnostics.DiagnosticsController
    public void showDiagnosticInfoString() {
        ViewGroup viewGroup;
        if (this.mDiagnosticInfoStringEnabled || (viewGroup = this.mAnchorView) == null) {
            return;
        }
        if (viewGroup.indexOfChild(this.mDiagnosticText) == -1) {
            TextView textView = new TextView(this.mAnchorView.getContext());
            this.mDiagnosticText = textView;
            this.mAnchorView.addView(textView);
            this.mDiagnosticText.setTextColor(-1);
            this.mDiagnosticText.setBackgroundColor(Color.parseColor("#AA000000"));
        }
        TextView textView2 = this.mDiagnosticText;
        if (textView2 != null) {
            textView2.invalidate();
            this.mDiagnosticText.setVisibility(0);
        }
        this.mDiagnosticInfoStringEnabled = true;
    }
}
