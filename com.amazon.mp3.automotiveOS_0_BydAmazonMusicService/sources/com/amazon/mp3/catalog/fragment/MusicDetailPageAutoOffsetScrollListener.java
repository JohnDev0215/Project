package com.amazon.mp3.catalog.fragment;

import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.catalog.fragment.AutoOffsetScrollListener;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MusicDetailPageAutoOffsetScrollListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B3\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u0007\u0012\b\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0016R\u0016\u0010\u000b\u001a\n \r*\u0004\u0018\u00010\f0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/amazon/mp3/catalog/fragment/MusicDetailPageAutoOffsetScrollListener;", "Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener;", "view", "Landroid/view/View;", "viewIndexToAlign", "", "lastScrollPosition", "Landroid/util/Pair;", "onBindCallback", "Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$OnBindCallback;", "(Landroid/view/View;ILandroid/util/Pair;Lcom/amazon/mp3/catalog/fragment/AutoOffsetScrollListener$OnBindCallback;)V", "TAG", "", "kotlin.jvm.PlatformType", "lockableGradientBackground", "Landroid/widget/ImageView;", "offsetAfterLockingGradientBackground", "", "onScrolledAfterBinding", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "dx", "dy", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class MusicDetailPageAutoOffsetScrollListener extends AutoOffsetScrollListener {
    private final String TAG;
    private ImageView lockableGradientBackground;
    private float offsetAfterLockingGradientBackground;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MusicDetailPageAutoOffsetScrollListener(View view, int i, Pair<Integer, Integer> lastScrollPosition, AutoOffsetScrollListener.OnBindCallback onBindCallback) {
        super(view, i, lastScrollPosition, onBindCallback);
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(lastScrollPosition, "lastScrollPosition");
        this.TAG = MusicDetailPageAutoOffsetScrollListener.class.getSimpleName();
        this.lockableGradientBackground = (ImageView) view.findViewById(R.id.lockableGradientBackground);
    }

    @Override // com.amazon.mp3.catalog.fragment.AutoOffsetScrollListener
    public void onScrolledAfterBinding(RecyclerView recyclerView, int dx, int dy) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        ImageView imageView = this.lockableGradientBackground;
        if (imageView == null) {
            return;
        }
        float f = this.offsetAfterLockingGradientBackground;
        if (f == 0.0f) {
            if (dy > 0) {
                float f2 = dy;
                if ((getView().getY() + imageView.getY()) - f2 <= 0.0f) {
                    this.offsetAfterLockingGradientBackground = f2 - (getView().getY() + imageView.getY());
                    getView().setY(-imageView.getY());
                    return;
                }
            }
            View view = getView();
            view.setY(view.getY() - dy);
            return;
        }
        if (dy < 0) {
            float f3 = dy;
            if (f + f3 <= 0.0f) {
                getView().setY((-imageView.getY()) - (this.offsetAfterLockingGradientBackground + f3));
                this.offsetAfterLockingGradientBackground = 0.0f;
                return;
            }
        }
        this.offsetAfterLockingGradientBackground = f + dy;
    }
}
