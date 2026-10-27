package org.chromium.ui.autofill;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.ui.R;
import org.chromium.ui.UiUtils;
import org.chromium.ui.base.WindowAndroid;
import org.chromium.ui.gfx.DeviceDisplayInfo;

/* JADX INFO: loaded from: classes3.dex */
public class AutofillKeyboardAccessory extends LinearLayout implements WindowAndroid.KeyboardVisibilityListener, View.OnClickListener, View.OnLongClickListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final AutofillDelegate mAutofillDelegate;
    private final int mMaximumLabelWidthPx;
    private final int mMaximumSublabelWidthPx;
    private final WindowAndroid mWindowAndroid;

    public AutofillKeyboardAccessory(WindowAndroid windowAndroid, AutofillDelegate autofillDelegate) {
        super(windowAndroid.getActivity().get());
        this.mWindowAndroid = windowAndroid;
        this.mAutofillDelegate = autofillDelegate;
        int displayWidth = DeviceDisplayInfo.create(getContext()).getDisplayWidth();
        this.mMaximumLabelWidthPx = displayWidth / 2;
        this.mMaximumSublabelWidthPx = displayWidth / 4;
        windowAndroid.addKeyboardVisibilityListener(this);
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.keyboard_accessory_half_padding);
        setPadding(dimensionPixelSize, 0, dimensionPixelSize, 0);
    }

    public void showWithSuggestions(AutofillSuggestion[] autofillSuggestionArr, final boolean z) {
        View viewInflate;
        removeAllViews();
        int i = -1;
        for (int i2 = 0; i2 < autofillSuggestionArr.length; i2++) {
            AutofillSuggestion autofillSuggestion = autofillSuggestionArr[i2];
            if (!autofillSuggestion.isFillable() && autofillSuggestion.getIconId() != 0) {
                viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.autofill_keyboard_accessory_icon, (ViewGroup) this, false);
                if (i == -1) {
                    i = i2;
                }
                ImageView imageView = (ImageView) viewInflate;
                imageView.setImageResource(autofillSuggestion.getIconId());
                imageView.setContentDescription(autofillSuggestion.getLabel());
            } else {
                viewInflate = LayoutInflater.from(getContext()).inflate(R.layout.autofill_keyboard_accessory_item, (ViewGroup) this, false);
                TextView textView = (TextView) viewInflate.findViewById(R.id.autofill_keyboard_accessory_item_label);
                if (autofillSuggestion.isFillable()) {
                    textView.setMaxWidth(this.mMaximumLabelWidthPx);
                }
                textView.setText(autofillSuggestion.getLabel());
                if (autofillSuggestion.getIconId() != 0) {
                    ApiCompatibilityUtils.setCompoundDrawablesRelativeWithIntrinsicBounds(textView, autofillSuggestion.getIconId(), 0, 0, 0);
                }
                if (!TextUtils.isEmpty(autofillSuggestion.getSublabel())) {
                    TextView textView2 = (TextView) viewInflate.findViewById(R.id.autofill_keyboard_accessory_item_sublabel);
                    textView2.setText(autofillSuggestion.getSublabel());
                    textView2.setVisibility(0);
                    textView2.setMaxWidth(this.mMaximumSublabelWidthPx);
                }
            }
            viewInflate.setTag(Integer.valueOf(i2));
            viewInflate.setOnClickListener(this);
            if (autofillSuggestion.isDeletable()) {
                viewInflate.setOnLongClickListener(this);
            }
            addView(viewInflate);
        }
        if (i != -1) {
            View view = new View(getContext());
            view.setLayoutParams(new LinearLayout.LayoutParams(0, 0, 1.0f));
            addView(view, i);
        }
        ApiCompatibilityUtils.setLayoutDirection(this, z ? 1 : 0);
        final HorizontalScrollView horizontalScrollView = (HorizontalScrollView) this.mWindowAndroid.getKeyboardAccessoryView();
        if (getParent() == null) {
            horizontalScrollView.addView(this);
            horizontalScrollView.setVisibility(0);
            horizontalScrollView.sendAccessibilityEvent(32);
        }
        horizontalScrollView.post(new Runnable() { // from class: org.chromium.ui.autofill.AutofillKeyboardAccessory.1
            @Override // java.lang.Runnable
            public void run() {
                horizontalScrollView.scrollTo(z ? AutofillKeyboardAccessory.this.getRight() : 0, 0);
            }
        });
    }

    public void dismiss() {
        ViewGroup keyboardAccessoryView = this.mWindowAndroid.getKeyboardAccessoryView();
        keyboardAccessoryView.removeView(this);
        keyboardAccessoryView.setVisibility(8);
        this.mWindowAndroid.removeKeyboardVisibilityListener(this);
        ((View) keyboardAccessoryView.getParent()).requestLayout();
    }

    @Override // org.chromium.ui.base.WindowAndroid.KeyboardVisibilityListener
    public void keyboardVisibilityChanged(boolean z) {
        if (z) {
            return;
        }
        dismiss();
        this.mAutofillDelegate.dismissed();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        UiUtils.hideKeyboard(this);
        this.mAutofillDelegate.suggestionSelected(((Integer) view.getTag()).intValue());
    }

    @Override // android.view.View.OnLongClickListener
    public boolean onLongClick(View view) {
        this.mAutofillDelegate.deleteSuggestion(((Integer) view.getTag()).intValue());
        return true;
    }
}
