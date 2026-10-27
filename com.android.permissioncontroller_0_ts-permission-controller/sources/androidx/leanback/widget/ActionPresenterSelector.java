package androidx.leanback.widget;

import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.leanback.R;
import androidx.leanback.widget.Presenter;

/* JADX INFO: loaded from: classes3.dex */
class ActionPresenterSelector extends PresenterSelector {
    private final Presenter mOneLineActionPresenter = new OneLineActionPresenter();
    private final Presenter[] mPresenters;
    private final Presenter mTwoLineActionPresenter;

    ActionPresenterSelector() {
        TwoLineActionPresenter twoLineActionPresenter = new TwoLineActionPresenter();
        this.mTwoLineActionPresenter = twoLineActionPresenter;
        this.mPresenters = new Presenter[]{this.mOneLineActionPresenter, twoLineActionPresenter};
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter getPresenter(Object item) {
        Action action = (Action) item;
        if (TextUtils.isEmpty(action.getLabel2())) {
            return this.mOneLineActionPresenter;
        }
        return this.mTwoLineActionPresenter;
    }

    @Override // androidx.leanback.widget.PresenterSelector
    public Presenter[] getPresenters() {
        return this.mPresenters;
    }

    static class ActionViewHolder extends Presenter.ViewHolder {
        Action mAction;
        Button mButton;
        int mLayoutDirection;

        public ActionViewHolder(View view, int layoutDirection) {
            super(view);
            this.mButton = (Button) view.findViewById(R.id.lb_action_button);
            this.mLayoutDirection = layoutDirection;
        }
    }

    static abstract class ActionPresenter extends Presenter {
        ActionPresenter() {
        }

        @Override // androidx.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
            Action action = (Action) item;
            ActionViewHolder vh = (ActionViewHolder) viewHolder;
            vh.mAction = action;
            Drawable icon = action.getIcon();
            if (icon != null) {
                int startPadding = vh.view.getResources().getDimensionPixelSize(R.dimen.lb_action_with_icon_padding_start);
                int endPadding = vh.view.getResources().getDimensionPixelSize(R.dimen.lb_action_with_icon_padding_end);
                vh.view.setPaddingRelative(startPadding, 0, endPadding, 0);
            } else {
                int padding = vh.view.getResources().getDimensionPixelSize(R.dimen.lb_action_padding_horizontal);
                vh.view.setPaddingRelative(padding, 0, padding, 0);
            }
            if (vh.mLayoutDirection == 1) {
                vh.mButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, icon, (Drawable) null);
            } else {
                vh.mButton.setCompoundDrawablesWithIntrinsicBounds(icon, (Drawable) null, (Drawable) null, (Drawable) null);
            }
        }

        @Override // androidx.leanback.widget.Presenter
        public void onUnbindViewHolder(Presenter.ViewHolder viewHolder) {
            ActionViewHolder vh = (ActionViewHolder) viewHolder;
            vh.mButton.setCompoundDrawablesWithIntrinsicBounds((Drawable) null, (Drawable) null, (Drawable) null, (Drawable) null);
            vh.view.setPadding(0, 0, 0, 0);
            vh.mAction = null;
        }
    }

    static class OneLineActionPresenter extends ActionPresenter {
        OneLineActionPresenter() {
        }

        @Override // androidx.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_action_1_line, parent, false);
            return new ActionViewHolder(v, parent.getLayoutDirection());
        }

        @Override // androidx.leanback.widget.ActionPresenterSelector.ActionPresenter, androidx.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
            super.onBindViewHolder(viewHolder, item);
            ActionViewHolder vh = (ActionViewHolder) viewHolder;
            Action action = (Action) item;
            vh.mButton.setText(action.getLabel1());
        }
    }

    static class TwoLineActionPresenter extends ActionPresenter {
        TwoLineActionPresenter() {
        }

        @Override // androidx.leanback.widget.Presenter
        public Presenter.ViewHolder onCreateViewHolder(ViewGroup parent) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lb_action_2_lines, parent, false);
            return new ActionViewHolder(v, parent.getLayoutDirection());
        }

        @Override // androidx.leanback.widget.ActionPresenterSelector.ActionPresenter, androidx.leanback.widget.Presenter
        public void onBindViewHolder(Presenter.ViewHolder viewHolder, Object item) {
            super.onBindViewHolder(viewHolder, item);
            Action action = (Action) item;
            ActionViewHolder vh = (ActionViewHolder) viewHolder;
            CharSequence line1 = action.getLabel1();
            CharSequence line2 = action.getLabel2();
            if (TextUtils.isEmpty(line1)) {
                vh.mButton.setText(line2);
                return;
            }
            if (TextUtils.isEmpty(line2)) {
                vh.mButton.setText(line1);
                return;
            }
            vh.mButton.setText(((Object) line1) + "\n" + ((Object) line2));
        }
    }
}
