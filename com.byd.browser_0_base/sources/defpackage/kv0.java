package defpackage;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import org.chromium.chrome.browser.bookmarks.BookMarkSelectableItemView;
import org.chromium.components.browser_ui.widget.selectable_list.a;

/* JADX INFO: compiled from: HistorySelectableItemViewHolder.java */
/* JADX INFO: loaded from: classes.dex */
public final class kv0<E> extends RecyclerView.a0 {
    public final BookMarkSelectableItemView<E> y;

    public kv0(View view, a<E> aVar) {
        super(view);
        BookMarkSelectableItemView<E> bookMarkSelectableItemView = (BookMarkSelectableItemView) view;
        this.y = bookMarkSelectableItemView;
        bookMarkSelectableItemView.setSelectionDelegate(aVar);
    }
}
