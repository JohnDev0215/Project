package org.chromium.chrome.browser.keyboard_accessory.bar_component;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.recyclerview.widget.RecyclerView;
import defpackage.in3;
import defpackage.l10;
import defpackage.oj2;
import defpackage.om3;
import defpackage.ql0;
import defpackage.xe3;
import java.util.Objects;
import java.util.WeakHashMap;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.R$drawable;
import org.chromium.chrome.R$id;
import org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView;
import org.chromium.chrome.browser.profiles.Profile;

/* JADX INFO: loaded from: classes.dex */
class KeyboardAccessoryModernView extends KeyboardAccessoryView {
    public final a g;

    public class a extends RecyclerView.q {
        public a() {
        }

        @Override // androidx.recyclerview.widget.RecyclerView.q
        public final void a(int i, RecyclerView recyclerView) {
            if (i != 0) {
                KeyboardAccessoryModernView keyboardAccessoryModernView = KeyboardAccessoryModernView.this;
                keyboardAccessoryModernView.e.removeOnScrollListener(keyboardAccessoryModernView.g);
                xe3 xe3VarT = oj2.t(Profile.d());
                if (!xe3VarT.isInitialized()) {
                    xe3VarT = null;
                }
                if (xe3VarT != null) {
                    xe3VarT.notifyEvent("keyboard_accessory_bar_swiped");
                }
            }
        }
    }

    public class b extends KeyboardAccessoryView.a {
        public b(int i) {
            super(i);
        }

        @Override // org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView.a
        public final int j(View view, RecyclerView recyclerView, RecyclerView.w wVar) {
            boolean z = RecyclerView.I(view) == recyclerView.getAdapter().k() - 1;
            int i = this.a;
            if (!z) {
                return i;
            }
            if (view.getWidth() == 0 && wVar.f) {
                view.post(new ql0(12, recyclerView));
                return recyclerView.getWidth() - (KeyboardAccessoryModernView.this.getContext().getResources().getDimensionPixelSize(R$dimen.keyboard_accessory_tab_size) * ((ViewGroup) view).getChildCount());
            }
            int width = recyclerView.getWidth();
            int i2 = 0;
            for (int i3 = 0; i3 < recyclerView.getChildCount(); i3++) {
                View childAt = recyclerView.getChildAt(i3);
                int width2 = childAt.getWidth();
                ViewGroup.LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams instanceof ViewGroup.MarginLayoutParams) {
                    ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
                    width2 = width2 + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                }
                i2 += width2;
            }
            return Math.max(((width - i2) - ((recyclerView.getChildCount() - 1) * i)) - (recyclerView.getPaddingStart() + recyclerView.getPaddingEnd()), i);
        }
    }

    public KeyboardAccessoryModernView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = new a();
    }

    @Override // org.chromium.chrome.browser.keyboard_accessory.bar_component.KeyboardAccessoryView, android.view.View
    public final void onFinishInflate() {
        super.onFinishInflate();
        ((ImageView) findViewById(R$id.show_keyboard)).setImageDrawable(AppCompatResources.a(getContext(), R$drawable.ic_arrow_back_24dp));
        this.e.g(new b(getResources().getDimensionPixelSize(R$dimen.keyboard_accessory_bar_item_padding)));
        this.e.addOnScrollListener(this.g);
        RecyclerView recyclerView = this.e;
        WeakHashMap<View, in3> weakHashMap = om3.a;
        om3.e.i(recyclerView, 0, 0, 0, 0);
    }

    @Override // android.view.View
    public final void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        RecyclerView recyclerView = this.e;
        Objects.requireNonNull(recyclerView);
        recyclerView.post(new l10(24, recyclerView));
    }
}
