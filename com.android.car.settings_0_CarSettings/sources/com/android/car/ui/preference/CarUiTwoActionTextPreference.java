package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes6.dex */
public class CarUiTwoActionTextPreference extends CarUiTwoActionBasePreference {
    protected Runnable mSecondaryActionOnClickListener;
    private CharSequence mSecondaryActionText;

    public CarUiTwoActionTextPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CarUiTwoActionTextPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CarUiTwoActionTextPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarUiTwoActionTextPreference(Context context) {
        super(context);
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    protected void init(AttributeSet attrs) {
        int i;
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiTwoActionTextPreference);
        try {
            int actionStyle = a.getInteger(R.styleable.CarUiTwoActionTextPreference_secondaryActionStyle, 0);
            this.mSecondaryActionText = a.getString(R.styleable.CarUiTwoActionTextPreference_secondaryActionText);
            if (actionStyle == 0) {
                i = R.layout.car_ui_preference_two_action_text;
            } else {
                i = R.layout.car_ui_preference_two_action_text_borderless;
            }
            setLayoutResourceInternal(i);
        } finally {
            a.recycle();
        }
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    protected void performSecondaryActionClickInternal() {
        if (isSecondaryActionEnabled()) {
            if (isUxRestricted()) {
                Consumer<Preference> restrictedListener = getOnClickWhileRestrictedListener();
                if (restrictedListener != null) {
                    restrictedListener.accept(this);
                    return;
                }
                return;
            }
            Runnable runnable = this.mSecondaryActionOnClickListener;
            if (runnable != null) {
                runnable.run();
            }
        }
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        View firstActionContainer = CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_first_action_container);
        View secondActionContainer = CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_second_action_container);
        Button secondaryButton = (Button) CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_secondary_action);
        holder.itemView.setFocusable(false);
        holder.itemView.setClickable(false);
        firstActionContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionTextPreference$wxpvPGH7hvVgrH0DY9xPBZueU60
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.performClick(view);
            }
        });
        firstActionContainer.setEnabled(isEnabled());
        firstActionContainer.setFocusable(isEnabled());
        secondActionContainer.setVisibility(this.mSecondaryActionVisible ? 0 : 8);
        secondaryButton.setText(this.mSecondaryActionText);
        secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionTextPreference$j69f2pekOeod6mk1_rZdVxfuhlU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$CarUiTwoActionTextPreference(view);
            }
        });
        secondaryButton.setEnabled(isSecondaryActionEnabled());
        secondaryButton.setFocusable(isSecondaryActionEnabled());
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$CarUiTwoActionTextPreference(View v) {
        performSecondaryActionClickInternal();
    }

    public CharSequence getSecondaryActionText() {
        return this.mSecondaryActionText;
    }

    public void setSecondaryActionText(CharSequence title) {
        this.mSecondaryActionText = title;
        notifyChanged();
    }

    public void setSecondaryActionText(int resid) {
        setSecondaryActionText(getContext().getString(resid));
    }

    public void setOnSecondaryActionClickListener(Runnable onClickListener) {
        this.mSecondaryActionOnClickListener = onClickListener;
        notifyChanged();
    }
}
