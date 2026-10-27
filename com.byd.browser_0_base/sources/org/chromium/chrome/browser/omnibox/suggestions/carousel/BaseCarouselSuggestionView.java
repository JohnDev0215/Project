package org.chromium.chrome.browser.omnibox.suggestions.carousel;

import android.content.Context;
import android.graphics.Rect;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.ao3;
import defpackage.cd;
import defpackage.n3;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.omnibox.suggestions.header.HeaderView;
import org.chromium.ui.modelutil.m;

/* JADX INFO: loaded from: classes2.dex */
public class BaseCarouselSuggestionView extends LinearLayout {
    public final HeaderView e;
    public final RecyclerView f;
    public final cd g;
    public int h;

    public class a extends RecyclerView.l {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.l
        public final void g(Rect rect, View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            rect.left = 0;
            rect.right = BaseCarouselSuggestionView.this.h;
        }
    }

    public BaseCarouselSuggestionView(Context context, m mVar) {
        super(context);
        setClickable(false);
        setFocusable(false);
        setOrientation(1);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R$dimen.omnibox_carousel_suggestion_padding);
        setPaddingRelative(0, dimensionPixelSize, 0, dimensionPixelSize);
        HeaderView headerView = new HeaderView(context);
        this.e = headerView;
        headerView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        headerView.setVisibility(8);
        addView(headerView);
        RecyclerView recyclerView = new RecyclerView(context);
        this.f = recyclerView;
        recyclerView.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        recyclerView.setFocusable(true);
        recyclerView.setFocusableInTouchMode(true);
        recyclerView.setItemAnimator(null);
        recyclerView.setLayoutManager(new LinearLayoutManager(0));
        recyclerView.setClipToPadding(false);
        recyclerView.setPaddingRelative(getResources().getDimensionPixelSize(R$dimen.omnibox_suggestion_side_spacing), recyclerView.getPaddingTop(), recyclerView.getPaddingEnd(), recyclerView.getPaddingBottom());
        cd cdVar = new cd(recyclerView.getLayoutManager());
        this.g = cdVar;
        recyclerView.addOnChildAttachStateChangeListener(cdVar);
        recyclerView.g(new a());
        recyclerView.setAdapter(mVar);
        addView(recyclerView);
    }

    public m getAdapter() {
        return (m) this.f.getAdapter();
    }

    public TextView getHeaderTextView() {
        return this.e;
    }

    public View getHeaderView() {
        return this.e;
    }

    public RecyclerView getRecyclerViewForTest() {
        return this.f;
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        boolean z = getLayoutDirection() == 1;
        cd cdVar = this.g;
        if ((!z && n3.L0(keyEvent)) || (z && n3.K0(keyEvent))) {
            RecyclerView.m mVar = cdVar.b;
            if (mVar != null) {
                int i2 = cdVar.a;
                cdVar.a(i2 == -1 ? 0 : i2 < mVar.D() ? cdVar.a + 1 : mVar.D() - 1, false);
            }
            return true;
        }
        if (!(z && n3.L0(keyEvent)) && (z || !n3.K0(keyEvent))) {
            return super.onKeyDown(i, keyEvent);
        }
        if (cdVar.b != null) {
            int i3 = cdVar.a;
            cdVar.a(i3 == -1 ? r6.D() - 1 : i3 > 0 ? i3 - 1 : 0, false);
        }
        return true;
    }

    public void setCarouselHorizontalFade(boolean z) {
        this.f.setHorizontalFadingEdgeEnabled(z);
    }

    public void setCarouselRecycledViewPool(RecyclerView.r rVar) {
        if (rVar != null) {
            this.f.setRecycledViewPool(rVar);
        }
    }

    public void setItemSpacingPx(int i) {
        this.h = i;
        ao3.e(this.f, "BaseCarouselSuggestionView.setItemSpacingPx");
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        cd cdVar = this.g;
        if (z) {
            cdVar.a(0, true);
        } else {
            cdVar.a(-1, false);
        }
    }
}
