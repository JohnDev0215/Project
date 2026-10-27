package com.amazon.avod.playbackclient.subtitle.views.util;

import android.widget.TextView;
import androidx.core.view.ViewCompat;
import com.amazon.avod.util.Preconditions2;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class EdgeRenderer {
    private static final ImmutableMap<EdgeType, EdgeRenderAction> EDGE_TO_ACTION_MAP;

    public enum EdgeType {
        NONE,
        RAISED,
        DEPRESSED,
        UNIFORM,
        LEFT_DROP_SHADOW,
        RIGHT_DROP_SHADOW
    }

    static {
        EDGE_TO_ACTION_MAP = (ImmutableMap) Preconditions2.checkFullKeyMapping(EdgeType.class, ImmutableMap.builder().put(EdgeType.NONE, new NoEdgeRenderAction()).put(EdgeType.RAISED, new RaisedEdgeRenderAction()).put(EdgeType.DEPRESSED, new DepressedEdgeRenderAction()).put(EdgeType.UNIFORM, new UniformEdgeRenderAction()).put(EdgeType.LEFT_DROP_SHADOW, new LeftDropShadowRenderAction()).put(EdgeType.RIGHT_DROP_SHADOW, new RightDropShadowRenderAction()).build());
    }

    public void applyEdge(@Nonnull TextView textView, @Nonnull EdgeType edgeType) {
        Preconditions.checkNotNull(textView);
        Preconditions.checkNotNull(edgeType);
        EDGE_TO_ACTION_MAP.get(edgeType).renderEdge(textView);
    }

    private static class NoEdgeRenderAction implements EdgeRenderAction {
        @Override // com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderAction
        public void renderEdge(TextView textView) {
        }

        private NoEdgeRenderAction() {
        }
    }

    private static class RaisedEdgeRenderAction implements EdgeRenderAction {
        private RaisedEdgeRenderAction() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderAction
        public void renderEdge(TextView textView) {
            textView.setShadowLayer(0.6f, -1.0f, -1.0f, -1);
        }
    }

    private static class DepressedEdgeRenderAction implements EdgeRenderAction {
        private DepressedEdgeRenderAction() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderAction
        public void renderEdge(TextView textView) {
            textView.setShadowLayer(0.6f, 1.0f, 1.0f, -1);
        }
    }

    private static class UniformEdgeRenderAction implements EdgeRenderAction {
        private UniformEdgeRenderAction() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderAction
        public void renderEdge(TextView textView) {
            textView.setShadowLayer(4.0f, 0.0f, 0.0f, -1);
        }
    }

    private static class LeftDropShadowRenderAction implements EdgeRenderAction {
        private LeftDropShadowRenderAction() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderAction
        public void renderEdge(TextView textView) {
            textView.setShadowLayer(1.5f, -3.0f, 3.0f, ViewCompat.MEASURED_STATE_MASK);
        }
    }

    private static class RightDropShadowRenderAction implements EdgeRenderAction {
        private RightDropShadowRenderAction() {
        }

        @Override // com.amazon.avod.playbackclient.subtitle.views.util.EdgeRenderAction
        public void renderEdge(TextView textView) {
            textView.setShadowLayer(1.5f, 3.0f, 3.0f, ViewCompat.MEASURED_STATE_MASK);
        }
    }
}
