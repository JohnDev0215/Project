package androidx.leanback.widget;

/* JADX INFO: loaded from: classes3.dex */
public class SectionRow extends Row {
    public SectionRow(HeaderItem headerItem) {
        super(headerItem);
    }

    public SectionRow(long id, String name) {
        super(new HeaderItem(id, name));
    }

    public SectionRow(String name) {
        super(new HeaderItem(name));
    }

    @Override // androidx.leanback.widget.Row
    public final boolean isRenderedAsRowView() {
        return false;
    }
}
