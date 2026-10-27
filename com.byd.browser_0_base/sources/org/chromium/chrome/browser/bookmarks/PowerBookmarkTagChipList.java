package org.chromium.chrome.browser.bookmarks;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import defpackage.mg;
import defpackage.mo;
import defpackage.ya1;
import java.util.HashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.components.bookmarks.BookmarkId;

/* JADX INFO: loaded from: classes.dex */
public class PowerBookmarkTagChipList extends FrameLayout {
    public static final /* synthetic */ int h = 0;
    public mo e;
    public final ya1 f;
    public final HashMap g;

    public class a extends mg {
        public a() {
        }

        @Override // defpackage.mg
        public final void f() {
            int i = PowerBookmarkTagChipList.h;
            PowerBookmarkTagChipList powerBookmarkTagChipList = PowerBookmarkTagChipList.this;
            powerBookmarkTagChipList.g.clear();
            powerBookmarkTagChipList.f.clear();
            throw null;
        }
    }

    public PowerBookmarkTagChipList(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f = new ya1();
        this.g = new HashMap();
        new a();
    }

    @Override // android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        mo moVar = new mo(getContext(), this.f);
        this.e = moVar;
        moVar.a.g(new mo.a(getResources().getDimensionPixelSize(R$dimen.contextual_search_chip_list_chip_spacing), getResources().getDimensionPixelSize(R$dimen.contextual_search_chip_list_side_padding)));
        addView(this.e.a);
    }

    public void setBookmarkFolder(BookmarkId bookmarkId) {
        this.g.clear();
        this.f.clear();
        throw null;
    }
}
