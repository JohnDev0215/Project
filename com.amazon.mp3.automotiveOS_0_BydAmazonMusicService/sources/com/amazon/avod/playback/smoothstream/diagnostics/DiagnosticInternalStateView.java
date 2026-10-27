package com.amazon.avod.playback.smoothstream.diagnostics;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.amazon.avod.playback.R$id;
import com.amazon.avod.playback.R$layout;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class DiagnosticInternalStateView implements DiagnosticUpdateListener {
    private final View mInternalStateView;
    private final TextView mPeriodIdView;

    DiagnosticInternalStateView(@Nonnull Context context, @Nonnull ViewGroup viewGroup) {
        Preconditions.checkNotNull(context, "context");
        Preconditions.checkNotNull(viewGroup, "root");
        View viewFindViewById = LayoutInflater.from(context).inflate(R$layout.player_internal_state, viewGroup).findViewById(R$id.internal_state);
        this.mInternalStateView = viewFindViewById;
        this.mPeriodIdView = (TextView) viewFindViewById.findViewById(R$id.internal_periodId);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticUpdateListener
    public void diagnosticDataUpdated(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        Preconditions.checkNotNull(diagnosticDataCollector, "collector");
        StringBuilder sb = new StringBuilder();
        sb.append("periodId: ");
        sb.append(diagnosticDataCollector.getPeriodId() == null ? "None" : diagnosticDataCollector.getPeriodId());
        this.mPeriodIdView.setText(sb.toString());
    }

    public void setVisibility(int i) {
        Preconditions.checkState(i == 0 || i == 8);
        this.mInternalStateView.setVisibility(i);
    }
}
