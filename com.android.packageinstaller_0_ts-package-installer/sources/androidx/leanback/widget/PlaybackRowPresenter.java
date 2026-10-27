package androidx.leanback.widget;

import android.view.View;
import androidx.leanback.widget.RowPresenter;

/* JADX INFO: loaded from: classes.dex */
public abstract class PlaybackRowPresenter extends RowPresenter {

    public static class ViewHolder extends RowPresenter.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }

    public void onReappear(RowPresenter.ViewHolder rowViewHolder) {
    }
}
