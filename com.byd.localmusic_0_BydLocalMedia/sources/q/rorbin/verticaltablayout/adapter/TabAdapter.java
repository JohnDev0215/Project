package q.rorbin.verticaltablayout.adapter;

import q.rorbin.verticaltablayout.widget.ITabView;

/* JADX INFO: loaded from: classes5.dex */
public interface TabAdapter {
    int getBackground(int i);

    ITabView.TabBadge getBadge(int i);

    int getCount();

    ITabView.TabIcon getIcon(int i);

    ITabView.TabTitle getTitle(int i);
}
