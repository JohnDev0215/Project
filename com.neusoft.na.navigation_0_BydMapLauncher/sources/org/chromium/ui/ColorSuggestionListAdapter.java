package org.chromium.ui;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import androidx.core.view.ViewCompat;
import org.chromium.base.ApiCompatibilityUtils;

/* JADX INFO: loaded from: classes3.dex */
public class ColorSuggestionListAdapter extends BaseAdapter implements View.OnClickListener {
    private static final int COLORS_PER_ROW = 4;
    private Context mContext;
    private OnColorSuggestionClickListener mListener;
    private ColorSuggestion[] mSuggestions;

    public interface OnColorSuggestionClickListener {
        void onColorSuggestionClick(ColorSuggestion colorSuggestion);
    }

    @Override // android.widget.Adapter
    public Object getItem(int i) {
        return null;
    }

    @Override // android.widget.Adapter
    public long getItemId(int i) {
        return i;
    }

    ColorSuggestionListAdapter(Context context, ColorSuggestion[] colorSuggestionArr) {
        this.mContext = context;
        this.mSuggestions = colorSuggestionArr;
    }

    public void setOnColorSuggestionClickListener(OnColorSuggestionClickListener onColorSuggestionClickListener) {
        this.mListener = onColorSuggestionClickListener;
    }

    private void setUpColorButton(View view, int i) {
        ColorSuggestion[] colorSuggestionArr = this.mSuggestions;
        if (i >= colorSuggestionArr.length) {
            view.setTag(null);
            view.setContentDescription(null);
            view.setVisibility(4);
            return;
        }
        view.setTag(colorSuggestionArr[i]);
        view.setVisibility(0);
        ColorSuggestion colorSuggestion = this.mSuggestions[i];
        ((GradientDrawable) ((LayerDrawable) view.getBackground()).findDrawableByLayerId(R.id.color_button_swatch)).setColor(colorSuggestion.mColor);
        String str = colorSuggestion.mLabel;
        if (TextUtils.isEmpty(str)) {
            str = String.format("#%06X", Integer.valueOf(colorSuggestion.mColor & ViewCompat.MEASURED_SIZE_MASK));
        }
        view.setContentDescription(str);
        view.setOnClickListener(this);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ColorSuggestion colorSuggestion;
        if (this.mListener == null || (colorSuggestion = (ColorSuggestion) view.getTag()) == null) {
            return;
        }
        this.mListener.onColorSuggestionClick(colorSuggestion);
    }

    @Override // android.widget.Adapter
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout linearLayout;
        if (view instanceof LinearLayout) {
            linearLayout = (LinearLayout) view;
        } else {
            linearLayout = new LinearLayout(this.mContext);
            linearLayout.setLayoutParams(new AbsListView.LayoutParams(-1, -2));
            linearLayout.setOrientation(0);
            linearLayout.setBackgroundColor(-1);
            int dimensionPixelOffset = this.mContext.getResources().getDimensionPixelOffset(R.dimen.color_button_height);
            for (int i2 = 0; i2 < 4; i2++) {
                View view2 = new View(this.mContext);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, dimensionPixelOffset, 1.0f);
                ApiCompatibilityUtils.setMarginStart(layoutParams, -1);
                if (i2 == 3) {
                    ApiCompatibilityUtils.setMarginEnd(layoutParams, -1);
                }
                view2.setLayoutParams(layoutParams);
                view2.setBackgroundResource(R.drawable.color_button_background);
                linearLayout.addView(view2);
            }
        }
        for (int i3 = 0; i3 < 4; i3++) {
            setUpColorButton(linearLayout.getChildAt(i3), (i * 4) + i3);
        }
        return linearLayout;
    }

    @Override // android.widget.Adapter
    public int getCount() {
        return ((this.mSuggestions.length + 4) - 1) / 4;
    }
}
