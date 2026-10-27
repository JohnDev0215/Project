package androidx.leanback.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.leanback.R;
import androidx.leanback.widget.MultiActionsProvider;
import androidx.leanback.widget.Presenter;

/* JADX INFO: loaded from: classes.dex */
class MediaItemActionPresenter extends Presenter {
    MediaItemActionPresenter() {
    }

    static class ViewHolder extends Presenter.ViewHolder {
        final ImageView mIcon;

        public ViewHolder(View view) {
            super(view);
            this.mIcon = (ImageView) view.findViewById(R.id.actionIcon);
        }

        public ImageView getIcon() {
            return this.mIcon;
        }
    }

    @Override // androidx.leanback.widget.Presenter
    public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
        Context context = parent.getContext();
        View actionView = LayoutInflater.from(context).inflate(R.layout.lb_row_media_item_action, parent, false);
        return new ViewHolder(actionView);
    }

    @Override // androidx.leanback.widget.Presenter
    public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
        ViewHolder actionViewHolder = (ViewHolder) viewHolder;
        MultiActionsProvider.MultiAction action = (MultiActionsProvider.MultiAction) item;
        actionViewHolder.getIcon().setImageDrawable(action.getCurrentDrawable());
    }

    @Override // androidx.leanback.widget.Presenter
    public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
    }
}
