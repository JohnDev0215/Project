package android.view;

import android.graphics.RenderNode;

/* JADX INFO: loaded from: classes3.dex */
public class ViewAnimationHostBridge implements RenderNode.AnimationHost {
    private final View mView;

    public ViewAnimationHostBridge(View view) {
        this.mView = view;
    }

    @Override // android.graphics.RenderNode.AnimationHost
    public void registerAnimatingRenderNode(RenderNode renderNode) {
        this.mView.mAttachInfo.mViewRootImpl.registerAnimatingRenderNode(renderNode);
    }

    @Override // android.graphics.RenderNode.AnimationHost
    public void registerVectorDrawableAnimator(NativeVectorDrawableAnimator nativeVectorDrawableAnimator) {
        this.mView.mAttachInfo.mViewRootImpl.registerVectorDrawableAnimator(nativeVectorDrawableAnimator);
    }

    @Override // android.graphics.RenderNode.AnimationHost
    public boolean isAttached() {
        return this.mView.mAttachInfo != null;
    }
}
