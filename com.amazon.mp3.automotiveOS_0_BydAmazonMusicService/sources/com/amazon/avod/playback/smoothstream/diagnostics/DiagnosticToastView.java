package com.amazon.avod.playback.smoothstream.diagnostics;

import android.content.Context;
import android.widget.Toast;
import com.amazon.avod.playback.PlaybackRestartEvent;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.base.Preconditions;
import java.util.Locale;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
class DiagnosticToastView implements DiagnosticUpdateListener {
    private final Toast mToast;

    DiagnosticToastView(@Nonnull Context context) {
        this.mToast = Toast.makeText((Context) Preconditions.checkNotNull(context, "context"), "", 1);
    }

    @Override // com.amazon.avod.playback.smoothstream.diagnostics.DiagnosticUpdateListener
    public void diagnosticDataUpdated(@Nonnull DiagnosticDataCollector diagnosticDataCollector) {
        Preconditions.checkNotNull(diagnosticDataCollector, "collector");
        PlaybackEvent queuedPlaybackEvent = diagnosticDataCollector.getQueuedPlaybackEvent();
        if (queuedPlaybackEvent instanceof PlaybackRestartEvent) {
            this.mToast.setText(String.format(Locale.US, "Restart on %s event", ((PlaybackRestartEvent) queuedPlaybackEvent).getTypeString()));
            this.mToast.show();
        }
    }
}
