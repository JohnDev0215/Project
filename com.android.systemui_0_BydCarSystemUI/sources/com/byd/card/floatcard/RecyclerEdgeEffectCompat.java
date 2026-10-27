package com.byd.card.floatcard;

import android.view.View;
import android.widget.EdgeEffect;
import androidx.recyclerview.widget.RecyclerView;
import com.byd.card.utils.AppUtils;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public class RecyclerEdgeEffectCompat extends RecyclerView.EdgeEffectFactory {
    private boolean mIsRightRubber = AppUtils.isRightRudder();

    @Override // androidx.recyclerview.widget.RecyclerView.EdgeEffectFactory
    protected EdgeEffect createEdgeEffect(final RecyclerView view, final int direction) {
        return new EdgeEffect(view.getContext()) { // from class: com.byd.card.floatcard.RecyclerEdgeEffectCompat.1
            static final /* synthetic */ boolean $assertionsDisabled = false;

            @Override // android.widget.EdgeEffect
            public void onPull(float deltaDistance) {
                super.onPull(deltaDistance);
                handlePull(deltaDistance);
            }

            @Override // android.widget.EdgeEffect
            public void onPull(float deltaDistance, float displacement) {
                super.onPull(deltaDistance, displacement);
                handlePull(deltaDistance);
            }

            @Override // android.widget.EdgeEffect
            public void onRelease() {
                super.onRelease();
                RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
                int childCount = layoutManager.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    MyViewHolder myViewHolder = (MyViewHolder) view.getChildViewHolder((View) Objects.requireNonNull(layoutManager.getChildAt(i)));
                    if (myViewHolder.itemView.getTranslationX() != 0.0f) {
                        myViewHolder.springAnimation.start();
                    }
                }
            }

            @Override // android.widget.EdgeEffect
            public void onAbsorb(int velocity) {
                super.onAbsorb(velocity);
                float f = (direction == 0 ? 1 : -1) * velocity * 0.8f;
                RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
                int childCount = layoutManager.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    ((MyViewHolder) view.getChildViewHolder((View) Objects.requireNonNull(layoutManager.getChildAt(i)))).springAnimation.setStartVelocity(f).start();
                }
            }

            private void handlePull(float f) {
                float width = (direction == 0 ? 1 : -1) * view.getWidth() * f * 0.5f;
                RecyclerView.LayoutManager layoutManager = view.getLayoutManager();
                int childCount = layoutManager.getChildCount();
                float f2 = 0.0f;
                for (int i = 0; i < childCount; i++) {
                    MyViewHolder myViewHolder = (MyViewHolder) view.getChildViewHolder((View) Objects.requireNonNull(layoutManager.getChildAt(i)));
                    myViewHolder.springAnimation.cancel();
                    myViewHolder.itemView.setTranslationX(myViewHolder.itemView.getTranslationX() + width + f2);
                    f2 += (RecyclerEdgeEffectCompat.this.mIsRightRubber ? -25.0f : 25.0f) * f;
                }
            }
        };
    }
}
