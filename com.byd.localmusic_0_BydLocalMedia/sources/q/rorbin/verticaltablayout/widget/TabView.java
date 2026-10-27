package q.rorbin.verticaltablayout.widget;

import android.content.Context;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import q.rorbin.badgeview.Badge;

/* JADX INFO: loaded from: classes5.dex */
public abstract class TabView extends FrameLayout implements Checkable, ITabView {
    public abstract Badge getBadgeView();

    @Deprecated
    public abstract ImageView getIconView();

    @Override // q.rorbin.verticaltablayout.widget.ITabView
    public TabView getTabView() {
        return this;
    }

    public abstract TextView getTitleView();

    public TabView(Context context) {
        super(context);
    }
}
