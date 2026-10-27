package q.rorbin.verticaltablayout.adapter;

import q.rorbin.verticaltablayout.widget.ITabView;

/* JADX INFO: loaded from: classes5.dex */
public abstract class SimpleTabAdapter implements TabAdapter {
    @Override // q.rorbin.verticaltablayout.adapter.TabAdapter
    public int getBackground(int i) {
        return 0;
    }

    @Override // q.rorbin.verticaltablayout.adapter.TabAdapter
    public ITabView.TabBadge getBadge(int i) {
        return null;
    }

    @Override // q.rorbin.verticaltablayout.adapter.TabAdapter
    public abstract int getCount();

    @Override // q.rorbin.verticaltablayout.adapter.TabAdapter
    public ITabView.TabIcon getIcon(int i) {
        return null;
    }

    @Override // q.rorbin.verticaltablayout.adapter.TabAdapter
    public ITabView.TabTitle getTitle(int i) {
        return null;
    }
}
