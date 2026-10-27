package com.android.car.settings.common;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;

/* JADX INFO: loaded from: classes5.dex */
public class EntityHeaderPreference extends Preference {
    public EntityHeaderPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public EntityHeaderPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public EntityHeaderPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public EntityHeaderPreference(Context context) {
        super(context);
        init();
    }

    private void init() {
        setLayoutResource(R.layout.entity_header_preference);
        setSelectable(false);
    }

    @Override // androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View itemView = holder.itemView;
        setIconView(itemView, R.id.entity_header_icon, getIcon());
        setTextView(itemView, R.id.entity_header_title, getTitle());
        setTextView(itemView, R.id.entity_header_summary, getSummary());
    }

    private void setTextView(View view, int id, CharSequence text) {
        TextView textView = (TextView) view.findViewById(id);
        if (textView != null) {
            textView.setText(text);
            textView.setVisibility(TextUtils.isEmpty(text) ? 8 : 0);
        }
    }

    private void setIconView(View view, int id, Drawable icon) {
        ImageView iconView = (ImageView) view.findViewById(id);
        if (iconView != null) {
            iconView.setImageDrawable(icon);
            iconView.setVisibility(icon == null ? 8 : 0);
        }
    }
}
