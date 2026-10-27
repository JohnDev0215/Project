package com.amazon.music.views.library.recyclerview.indexer;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.InsetDrawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import com.amazon.music.views.library.R$color;
import com.amazon.music.views.library.R$dimen;
import com.amazon.music.views.library.R$drawable;
import kotlin.Metadata;

/* JADX INFO: compiled from: DefaultScrollerViewProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0016J\n\u0010\u0005\u001a\u0004\u0018\u00010\u0004H\u0016¨\u0006\u0006"}, d2 = {"Lcom/amazon/music/views/library/recyclerview/indexer/DefaultScrollerViewProvider;", "Lcom/amazon/music/views/library/recyclerview/indexer/ScrollerViewProvider;", "()V", "provideHandleView", "Landroid/view/View;", "provideScrollBar", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public class DefaultScrollerViewProvider extends ScrollerViewProvider {
    @Override // com.amazon.music.views.library.recyclerview.indexer.ScrollerViewProvider
    public View provideHandleView() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        View view = new View(context);
        Resources resources = context.getResources();
        Integer numValueOf = resources != null ? Integer.valueOf(resources.getDimensionPixelSize(R$dimen.spacer_8)) : null;
        view.setBackground(new InsetDrawable(context.getDrawable(R$drawable.fastscroll_handle), numValueOf == null ? 0 : numValueOf.intValue(), 0, 0, 0));
        return view;
    }

    @Override // com.amazon.music.views.library.recyclerview.indexer.ScrollerViewProvider
    public View provideScrollBar() {
        Context context = getContext();
        if (context == null) {
            return null;
        }
        View view = new View(context);
        view.setBackgroundColor(ContextCompat.getColor(context, R$color.glass_2));
        return view;
    }
}
