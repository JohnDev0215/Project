package com.amazon.mp3.audioquality.nerdstats;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.amazon.mp3.automotiveOS.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: FragmentAudioStatsV2Binding.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\t\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bR\u0011\u0010\u000b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\bR\u0013\u0010\r\u001a\u0004\u0018\u00010\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0011\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\bR\u0011\u0010\u0013\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\bR\u0011\u0010\u0015\u001a\u00020\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0010R\u0011\u0010\u0017\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\bR\u0011\u0010\u0019\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\bR\u0013\u0010\u001b\u001a\u0004\u0018\u00010\u001c¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001eR\u0011\u0010\u001f\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b \u0010\bR\u0011\u0010!\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\bR\u0011\u0010#\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\b¨\u0006%"}, d2 = {"Lcom/amazon/mp3/audioquality/nerdstats/FragmentAudioStatsV2Binding;", "", "rootView", "Landroid/view/View;", "(Landroid/view/View;)V", "deviceNodeName", "Landroid/widget/TextView;", "getDeviceNodeName", "()Landroid/widget/TextView;", "deviceNodeSubtext", "getDeviceNodeSubtext", "deviceToOutputAlert", "getDeviceToOutputAlert", "headerImage", "Landroid/widget/ImageView;", "getHeaderImage", "()Landroid/widget/ImageView;", "headerQuality", "getHeaderQuality", "headerQualityMessage", "getHeaderQualityMessage", "outputNodeImage", "getOutputNodeImage", "outputNodeName", "getOutputNodeName", "outputNodeSubtext", "getOutputNodeSubtext", "signalFlowLayoutBorder", "Landroidx/constraintlayout/widget/ConstraintLayout;", "getSignalFlowLayoutBorder", "()Landroidx/constraintlayout/widget/ConstraintLayout;", "trackNodeQuality", "getTrackNodeQuality", "trackNodeSubtext", "getTrackNodeSubtext", "trackToDeviceAlert", "getTrackToDeviceAlert", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class FragmentAudioStatsV2Binding {
    private final TextView deviceNodeName;
    private final TextView deviceNodeSubtext;
    private final TextView deviceToOutputAlert;
    private final ImageView headerImage;
    private final TextView headerQuality;
    private final TextView headerQualityMessage;
    private final ImageView outputNodeImage;
    private final TextView outputNodeName;
    private final TextView outputNodeSubtext;
    private final ConstraintLayout signalFlowLayoutBorder;
    private final TextView trackNodeQuality;
    private final TextView trackNodeSubtext;
    private final TextView trackToDeviceAlert;

    public FragmentAudioStatsV2Binding(View rootView) {
        Intrinsics.checkNotNullParameter(rootView, "rootView");
        this.headerImage = (ImageView) rootView.findViewById(R.id.nerd_stats_signal_flow_image);
        View viewFindViewById = rootView.findViewById(R.id.nerd_stats_signal_flow_quality_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById, "rootView.findViewById(R.…signal_flow_quality_text)");
        this.headerQuality = (TextView) viewFindViewById;
        View viewFindViewById2 = rootView.findViewById(R.id.nerd_stats_signal_flow_info_text);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "rootView.findViewById(R.…ts_signal_flow_info_text)");
        this.headerQualityMessage = (TextView) viewFindViewById2;
        View viewFindViewById3 = rootView.findViewById(R.id.nerd_stats_signal_flow_node1_primary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "rootView.findViewById(R.…ignal_flow_node1_primary)");
        this.trackNodeQuality = (TextView) viewFindViewById3;
        View viewFindViewById4 = rootView.findViewById(R.id.nerd_stats_signal_flow_node1_secondary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "rootView.findViewById(R.…nal_flow_node1_secondary)");
        this.trackNodeSubtext = (TextView) viewFindViewById4;
        View viewFindViewById5 = rootView.findViewById(R.id.nerd_stats_signal_flow_signal_message1);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById5, "rootView.findViewById(R.…nal_flow_signal_message1)");
        this.trackToDeviceAlert = (TextView) viewFindViewById5;
        View viewFindViewById6 = rootView.findViewById(R.id.nerd_stats_signal_flow_node2_primary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById6, "rootView.findViewById(R.…ignal_flow_node2_primary)");
        this.deviceNodeName = (TextView) viewFindViewById6;
        View viewFindViewById7 = rootView.findViewById(R.id.nerd_stats_signal_flow_node2_secondary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById7, "rootView.findViewById(R.…nal_flow_node2_secondary)");
        this.deviceNodeSubtext = (TextView) viewFindViewById7;
        View viewFindViewById8 = rootView.findViewById(R.id.nerd_stats_signal_flow_signal_message2);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById8, "rootView.findViewById(R.…nal_flow_signal_message2)");
        this.deviceToOutputAlert = (TextView) viewFindViewById8;
        View viewFindViewById9 = rootView.findViewById(R.id.nerd_stats_signal_flow_node3_image);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById9, "rootView.findViewById(R.…_signal_flow_node3_image)");
        this.outputNodeImage = (ImageView) viewFindViewById9;
        View viewFindViewById10 = rootView.findViewById(R.id.nerd_stats_signal_flow_node3_primary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById10, "rootView.findViewById(R.…ignal_flow_node3_primary)");
        this.outputNodeName = (TextView) viewFindViewById10;
        View viewFindViewById11 = rootView.findViewById(R.id.nerd_stats_signal_flow_node3_secondary);
        Intrinsics.checkNotNullExpressionValue(viewFindViewById11, "rootView.findViewById(R.…nal_flow_node3_secondary)");
        this.outputNodeSubtext = (TextView) viewFindViewById11;
        this.signalFlowLayoutBorder = (ConstraintLayout) rootView.findViewById(R.id.nerd_stats_signal_flow_layout);
    }

    public final ImageView getHeaderImage() {
        return this.headerImage;
    }

    public final TextView getHeaderQuality() {
        return this.headerQuality;
    }

    public final TextView getHeaderQualityMessage() {
        return this.headerQualityMessage;
    }

    public final TextView getTrackNodeQuality() {
        return this.trackNodeQuality;
    }

    public final TextView getTrackNodeSubtext() {
        return this.trackNodeSubtext;
    }

    public final TextView getTrackToDeviceAlert() {
        return this.trackToDeviceAlert;
    }

    public final TextView getDeviceNodeName() {
        return this.deviceNodeName;
    }

    public final TextView getDeviceNodeSubtext() {
        return this.deviceNodeSubtext;
    }

    public final TextView getDeviceToOutputAlert() {
        return this.deviceToOutputAlert;
    }

    public final ImageView getOutputNodeImage() {
        return this.outputNodeImage;
    }

    public final TextView getOutputNodeName() {
        return this.outputNodeName;
    }

    public final TextView getOutputNodeSubtext() {
        return this.outputNodeSubtext;
    }

    public final ConstraintLayout getSignalFlowLayoutBorder() {
        return this.signalFlowLayoutBorder;
    }
}
