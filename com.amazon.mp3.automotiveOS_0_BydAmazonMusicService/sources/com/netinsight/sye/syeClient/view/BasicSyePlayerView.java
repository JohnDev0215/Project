package com.netinsight.sye.syeClient.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.SurfaceView;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.netinsight.sye.syeClient.ISyePlayer;
import com.netinsight.sye.syeClient.R;
import com.netinsight.sye.syeClient.playerListeners.IStateChangeListener;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#B\u0019\b\u0016\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b\"\u0010&B!\b\u0016\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010%\u001a\u00020$\u0012\u0006\u0010'\u001a\u00020\u000e¢\u0006\u0004\b\"\u0010(J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\b\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\b\u0010\u0007J\r\u0010\t\u001a\u00020\u0005¢\u0006\u0004\b\t\u0010\nJ\r\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\nJ7\u0010\u0013\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0013\u0010\u0014R$\u0010\u001b\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00158\u0006@BX\u0086.¢\u0006\f\n\u0004\b\u0017\u0010\u0018\u001a\u0004\b\u0019\u0010\u001aR\u0016\u0010\u001f\u001a\u00020\u001c8\u0002@\u0002X\u0082D¢\u0006\u0006\n\u0004\b\u001d\u0010\u001e¨\u0006)"}, d2 = {"Lcom/netinsight/sye/syeClient/view/BasicSyePlayerView;", "Landroid/widget/RelativeLayout;", "Lcom/netinsight/sye/syeClient/view/ISyePlayerView;", "Lcom/netinsight/sye/syeClient/ISyePlayer;", "player", "", "attachTo", "(Lcom/netinsight/sye/syeClient/ISyePlayer;)V", "detachFrom", "setTextureView", "()V", "setSurfaceView", "", "changed", "", "left", "top", "right", "bottom", "onLayout", "(ZIIII)V", "Landroid/view/View;", "<set-?>", "b", "Landroid/view/View;", "getView", "()Landroid/view/View;", "view", "", "a", "Ljava/lang/String;", "TAG", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class BasicSyePlayerView extends RelativeLayout implements ISyePlayerView {

    /* JADX INFO: renamed from: a, reason: collision with root package name and from kotlin metadata */
    public final String TAG;

    /* JADX INFO: renamed from: b, reason: from kotlin metadata */
    public View view;

    public BasicSyePlayerView(Context context) {
        super(context);
        this.TAG = "BasicSyePlayerView";
        View.inflate(getContext(), R.layout.view_basic_sye_player, this);
        setSurfaceView();
    }

    public BasicSyePlayerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = "BasicSyePlayerView";
        View.inflate(getContext(), R.layout.view_basic_sye_player, this);
        setSurfaceView();
    }

    public BasicSyePlayerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.TAG = "BasicSyePlayerView";
        View.inflate(getContext(), R.layout.view_basic_sye_player, this);
        setSurfaceView();
    }

    @Override // com.netinsight.sye.syeClient.view.ISyePlayerView
    public void attachTo(ISyePlayer player) {
        KeyEvent.Callback callbackFindViewById = findViewById(R.id.spinner);
        Intrinsics.checkExpressionValueIsNotNull(callbackFindViewById, "findViewById<StalledCircleView>(R.id.spinner)");
        player.addListener((IStateChangeListener) callbackFindViewById);
        SyeClosedCaptionView ccv = (SyeClosedCaptionView) findViewById(R.id.ccView);
        Intrinsics.checkExpressionValueIsNotNull(ccv, "ccv");
        player.attach(ccv);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        if (view instanceof TextureView) {
            player.attach((TextureView) view);
        } else if (view instanceof SurfaceView) {
            player.attach((SurfaceView) view);
        }
    }

    @Override // com.netinsight.sye.syeClient.view.ISyePlayerView
    public void detachFrom(ISyePlayer player) {
        KeyEvent.Callback callbackFindViewById = findViewById(R.id.spinner);
        Intrinsics.checkExpressionValueIsNotNull(callbackFindViewById, "findViewById<StalledCircleView>(R.id.spinner)");
        player.removeListener((IStateChangeListener) callbackFindViewById);
        View viewFindViewById = findViewById(R.id.ccView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById<SyeClosedCaptionView>(R.id.ccView)");
        player.detach((SyeClosedCaptionView) viewFindViewById);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        if (view instanceof TextureView) {
            player.detach((TextureView) view);
        } else if (view instanceof SurfaceView) {
            player.detach((SurfaceView) view);
        }
    }

    public final View getView() {
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        return view;
    }

    @Override // android.widget.RelativeLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (changed) {
            View view = this.view;
            if (view == null) {
                Intrinsics.throwUninitializedPropertyAccessException("view");
            }
            if (view instanceof SurfaceView) {
                RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
                layoutParams.addRule(13);
                View view2 = this.view;
                if (view2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("view");
                }
                view2.setLayoutParams(layoutParams);
            }
        }
        SyeClosedCaptionView syeClosedCaptionView = (SyeClosedCaptionView) findViewById(R.id.ccView);
        View view3 = this.view;
        if (view3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        syeClosedCaptionView.setX(view3.getX());
        View view4 = this.view;
        if (view4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        syeClosedCaptionView.setY(view4.getY());
        ViewGroup.LayoutParams layoutParams2 = syeClosedCaptionView.getLayoutParams();
        View view5 = this.view;
        if (view5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        layoutParams2.width = view5.getWidth();
        ViewGroup.LayoutParams layoutParams3 = syeClosedCaptionView.getLayoutParams();
        View view6 = this.view;
        if (view6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        layoutParams3.height = view6.getHeight();
    }

    public final void setSurfaceView() {
        View viewFindViewById = findViewById(R.id.videoSurfaceView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.videoSurfaceView)");
        this.view = viewFindViewById;
        if (viewFindViewById == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        viewFindViewById.setVisibility(0);
        View viewFindViewById2 = findViewById(R.id.ccView);
        if (viewFindViewById2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.netinsight.sye.syeClient.view.SyeClosedCaptionView");
        }
        SyeClosedCaptionView syeClosedCaptionView = (SyeClosedCaptionView) viewFindViewById2;
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        syeClosedCaptionView.setVideoClientView(view);
        View viewFindViewById3 = findViewById(R.id.videoTextureView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById3, "findViewById<View>(R.id.videoTextureView)");
        viewFindViewById3.setVisibility(8);
    }

    public final void setTextureView() {
        View viewFindViewById = findViewById(R.id.videoTextureView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById, "findViewById(R.id.videoTextureView)");
        this.view = viewFindViewById;
        if (viewFindViewById == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        viewFindViewById.setVisibility(0);
        SyeClosedCaptionView syeClosedCaptionView = (SyeClosedCaptionView) findViewById(R.id.ccView);
        View view = this.view;
        if (view == null) {
            Intrinsics.throwUninitializedPropertyAccessException("view");
        }
        syeClosedCaptionView.setVideoClientView(view);
        View viewFindViewById2 = findViewById(R.id.videoSurfaceView);
        Intrinsics.checkExpressionValueIsNotNull(viewFindViewById2, "findViewById<View>(R.id.videoSurfaceView)");
        viewFindViewById2.setVisibility(8);
    }
}
