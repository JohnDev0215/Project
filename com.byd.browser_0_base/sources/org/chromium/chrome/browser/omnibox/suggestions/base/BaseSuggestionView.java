package org.chromium.chrome.browser.omnibox.suggestions.base;

import android.content.Context;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.appcompat.widget.AppCompatImageView;
import defpackage.n3;
import defpackage.sc2;
import java.util.ArrayList;
import java.util.List;
import org.chromium.chrome.R$dimen;
import org.chromium.chrome.browser.omnibox.suggestions.base.SuggestionLayout;

/* JADX INFO: loaded from: classes2.dex */
public class BaseSuggestionView<T extends View> extends SuggestionLayout {
    public final ArrayList j;
    public final ImageView k;
    public final ActionChipsView l;
    public final T m;
    public Runnable n;

    public BaseSuggestionView(T t) {
        super(t.getContext());
        setClickable(true);
        setFocusable(true);
        ImageView imageView = new ImageView(getContext());
        this.k = imageView;
        imageView.setOutlineProvider(new sc2(getResources().getDimensionPixelSize(R$dimen.default_rounded_corner_radius)));
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        addView(imageView, new SuggestionLayout.a(-2, 1));
        ActionChipsView actionChipsView = new ActionChipsView(getContext());
        this.l = actionChipsView;
        actionChipsView.setVisibility(8);
        addView(actionChipsView, new SuggestionLayout.a(-2, 3));
        this.j = new ArrayList();
        this.m = t;
        t.setLayoutParams(new SuggestionLayout.a(-2, 0));
        addView(t);
    }

    public List<ImageView> getActionButtons() {
        return this.j;
    }

    public ActionChipsView getActionChipsView() {
        return this.l;
    }

    public T getContentView() {
        return this.m;
    }

    public ImageView getSuggestionImageView() {
        return this.k;
    }

    @Override // android.view.View
    public final boolean isFocused() {
        return super.isFocused() || (isSelected() && !isInTouchMode());
    }

    @Override // android.view.View, android.view.KeyEvent.Callback
    public final boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (this.l.onKeyDown(i, keyEvent)) {
            return true;
        }
        boolean z = getLayoutDirection() == 1;
        if ((!z && n3.L0(keyEvent)) || (z && n3.K0(keyEvent))) {
            ArrayList arrayList = this.j;
            if (arrayList.size() == 1) {
                ((ImageView) arrayList.get(0)).callOnClick();
            }
        }
        return super.onKeyDown(i, keyEvent);
    }

    public void setActionButtonsCount(int i) {
        ArrayList arrayList = this.j;
        int size = arrayList.size();
        if (size >= i) {
            if (size > i) {
                for (int i2 = i; i2 < arrayList.size(); i2++) {
                    removeView((View) arrayList.get(i2));
                }
                arrayList.subList(i, arrayList.size()).clear();
                return;
            }
            return;
        }
        for (int size2 = arrayList.size(); size2 < i; size2++) {
            AppCompatImageView appCompatImageView = new AppCompatImageView(getContext());
            appCompatImageView.setClickable(true);
            appCompatImageView.setFocusable(true);
            appCompatImageView.setScaleType(ImageView.ScaleType.CENTER);
            appCompatImageView.setLayoutParams(new SuggestionLayout.a(-2, 2));
            arrayList.add(appCompatImageView);
            addView(appCompatImageView);
        }
    }

    public void setOnFocusViaSelectionListener(Runnable runnable) {
        this.n = runnable;
    }

    @Override // android.view.View
    public void setSelected(boolean z) {
        Runnable runnable;
        super.setSelected(z);
        if (!z || (runnable = this.n) == null) {
            return;
        }
        runnable.run();
    }

    public BaseSuggestionView(Context context, int i) {
        this(LayoutInflater.from(context).inflate(i, (ViewGroup) null));
    }
}
