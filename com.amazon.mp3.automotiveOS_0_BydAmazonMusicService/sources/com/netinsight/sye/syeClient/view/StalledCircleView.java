package com.netinsight.sye.syeClient.view;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ProgressBar;
import com.netinsight.sye.syeClient.generated.enums.SyePlayerState;
import com.netinsight.sye.syeClient.playerListeners.IStateChangeListener;
import kotlin.Metadata;

/* JADX INFO: loaded from: classes7.dex */
@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r¢\u0006\u0004\b\u000f\u0010\u0010B\u0019\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0011¢\u0006\u0004\b\u000f\u0010\u0013B!\b\u0016\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\u0006\u0010\u0014\u001a\u00020\u0003¢\u0006\u0004\b\u000f\u0010\u0015J\u0017\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u0003H\u0002¢\u0006\u0004\b\u0006\u0010\u0007J\u001f\u0010\u000b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lcom/netinsight/sye/syeClient/view/StalledCircleView;", "Landroid/widget/ProgressBar;", "Lcom/netinsight/sye/syeClient/playerListeners/IStateChangeListener;", "", "visibility", "", "setVisibilityOnUiThread", "(I)V", "Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerState;", "from", "to", "onStateChange", "(Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerState;Lcom/netinsight/sye/syeClient/generated/enums/SyePlayerState;)V", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "defStyleAttr", "(Landroid/content/Context;Landroid/util/AttributeSet;I)V", "syeClient_release"}, k = 1, mv = {1, 4, 0})
public final class StalledCircleView extends ProgressBar implements IStateChangeListener {
    public StalledCircleView(Context context) {
        super(context);
    }

    public StalledCircleView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StalledCircleView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    private final void setVisibilityOnUiThread(final int visibility) {
        Handler handler = getHandler();
        if (handler != null) {
            handler.post(new Runnable() { // from class: com.netinsight.sye.syeClient.view.StalledCircleView.setVisibilityOnUiThread.1
                @Override // java.lang.Runnable
                public final void run() {
                    StalledCircleView.this.setVisibility(visibility);
                }
            });
        }
    }

    @Override // com.netinsight.sye.syeClient.playerListeners.IStateChangeListener
    public void onStateChange(SyePlayerState from, SyePlayerState to) {
        int i;
        SyePlayerState syePlayerState = SyePlayerState.Stalled;
        if (to == syePlayerState) {
            i = 0;
        } else if (from != syePlayerState) {
            return;
        } else {
            i = 4;
        }
        setVisibilityOnUiThread(i);
    }
}
