package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$styleable;
import com.android.car.ui.preference.CarUiTwoActionTextPreference;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
public class CarUiTwoActionTextPreference extends CarUiTwoActionBasePreference {

    @Nullable
    public Runnable mSecondaryActionOnClickListener;

    @Nullable
    private CharSequence mSecondaryActionText;

    public CarUiTwoActionTextPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(View view) {
        performSecondaryActionClick();
    }

    @Nullable
    public CharSequence getSecondaryActionText() {
        return this.mSecondaryActionText;
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    public void init(@Nullable AttributeSet attributeSet) {
        super.init(attributeSet);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiTwoActionTextPreference);
        try {
            int integer = typedArrayObtainStyledAttributes.getInteger(R$styleable.CarUiTwoActionTextPreference_secondaryActionStyle, 0);
            this.mSecondaryActionText = typedArrayObtainStyledAttributes.getString(R$styleable.CarUiTwoActionTextPreference_secondaryActionText);
            setLayoutResourceInternal(integer == 0 ? R$layout.car_ui_preference_two_action_text : R$layout.car_ui_preference_two_action_text_borderless);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewN = b2.n(preferenceViewHolder.itemView, R$id.car_ui_first_action_container);
        View viewN2 = b2.n(preferenceViewHolder.itemView, R$id.car_ui_second_action_container);
        Button button = (Button) b2.n(preferenceViewHolder.itemView, R$id.car_ui_secondary_action);
        preferenceViewHolder.itemView.setFocusable(false);
        preferenceViewHolder.itemView.setClickable(false);
        final byte b = 0 == true ? 1 : 0;
        viewN.setOnClickListener(new View.OnClickListener(this) { // from class: spi.a2
            public final /* synthetic */ CarUiTwoActionTextPreference b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (b) {
                    case 0:
                        this.b.performClickUnrestricted(view);
                        break;
                    default:
                        this.b.lambda$onBindViewHolder$0(view);
                        break;
                }
            }
        });
        final int i = 1;
        viewN.setEnabled(isEnabled() || isClickableWhileDisabled());
        viewN.setFocusable(isEnabled() || isClickableWhileDisabled());
        viewN2.setVisibility(this.mSecondaryActionVisible ? 0 : 8);
        button.setText(this.mSecondaryActionText);
        button.setOnClickListener(new View.OnClickListener(this) { // from class: spi.a2
            public final /* synthetic */ CarUiTwoActionTextPreference b;

            {
                this.b = this;
            }

            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                switch (i) {
                    case 0:
                        this.b.performClickUnrestricted(view);
                        break;
                    default:
                        this.b.lambda$onBindViewHolder$0(view);
                        break;
                }
            }
        });
        button.setEnabled(isSecondaryActionEnabled() || isClickableWhileDisabled());
        button.setFocusable(isSecondaryActionEnabled() || isClickableWhileDisabled());
        b2.k(button, isSecondaryActionEnabled(), isUxRestricted());
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    public void performSecondaryActionClickInternal() {
        Runnable runnable = this.mSecondaryActionOnClickListener;
        if (runnable != null) {
            runnable.run();
        }
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference, com.android.car.ui.preference.CarUiPreference, spi.j3
    @Deprecated
    public /* bridge */ /* synthetic */ void setMessageToShowWhenDisabledPreferenceClicked(@NonNull String str) {
        super.setMessageToShowWhenDisabledPreferenceClicked(str);
    }

    public void setOnSecondaryActionClickListener(@Nullable Runnable runnable) {
        this.mSecondaryActionOnClickListener = runnable;
        notifyChanged();
    }

    public void setSecondaryActionText(@Nullable CharSequence charSequence) {
        this.mSecondaryActionText = charSequence;
        notifyChanged();
    }

    public CarUiTwoActionTextPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CarUiTwoActionTextPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setSecondaryActionText(@StringRes int i) {
        setSecondaryActionText(getContext().getString(i));
    }

    public CarUiTwoActionTextPreference(Context context) {
        super(context);
    }
}
