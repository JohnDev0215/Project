package com.android.car.ui.preference;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Switch;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.preference.CarUiTwoActionSwitchPreference;
import java.util.function.Consumer;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
@TargetApi(28)
public class CarUiTwoActionSwitchPreference extends CarUiTwoActionBasePreference {
    private boolean mSecondaryActionChecked;

    @Nullable
    public Consumer<Boolean> mSecondaryActionOnClickListener;

    public CarUiTwoActionSwitchPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(View view) {
        performSecondaryActionClick();
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    public void init(@Nullable AttributeSet attributeSet) {
        super.init(attributeSet);
        setLayoutResourceInternal(R$layout.car_ui_preference_two_action_switch);
    }

    public boolean isSecondaryActionChecked() {
        return this.mSecondaryActionChecked;
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewN = b2.n(preferenceViewHolder.itemView, R$id.car_ui_first_action_container);
        View viewN2 = b2.n(preferenceViewHolder.itemView, R$id.car_ui_second_action_container);
        View viewN3 = b2.n(preferenceViewHolder.itemView, R$id.car_ui_secondary_action);
        Switch r3 = (Switch) b2.n(preferenceViewHolder.itemView, R$id.car_ui_secondary_action_concrete);
        preferenceViewHolder.itemView.setFocusable(false);
        preferenceViewHolder.itemView.setClickable(false);
        final byte b = 0 == true ? 1 : 0;
        viewN.setOnClickListener(new View.OnClickListener(this) { // from class: spi.z1
            public final /* synthetic */ CarUiTwoActionSwitchPreference b;

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
        viewN.setEnabled(isEnabled() || isUxRestricted() || isClickableWhileDisabled());
        viewN.setFocusable(isEnabled() || isUxRestricted() || isClickableWhileDisabled());
        viewN2.setVisibility(this.mSecondaryActionVisible ? 0 : 8);
        r3.setChecked(this.mSecondaryActionChecked);
        r3.setEnabled(isSecondaryActionEnabled());
        viewN3.setOnClickListener(new View.OnClickListener(this) { // from class: spi.z1
            public final /* synthetic */ CarUiTwoActionSwitchPreference b;

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
        viewN3.setEnabled(isSecondaryActionEnabled() || isUxRestricted() || isClickableWhileDisabled());
        viewN3.setFocusable(isSecondaryActionEnabled() || isUxRestricted() || isClickableWhileDisabled());
        b2.k(viewN3, isSecondaryActionEnabled(), isUxRestricted());
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    public void performSecondaryActionClickInternal() {
        this.mSecondaryActionChecked = !this.mSecondaryActionChecked;
        notifyChanged();
        Consumer<Boolean> consumer = this.mSecondaryActionOnClickListener;
        if (consumer != null) {
            consumer.accept(Boolean.valueOf(this.mSecondaryActionChecked));
        }
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference, com.android.car.ui.preference.CarUiPreference, spi.j3
    @Deprecated
    public /* bridge */ /* synthetic */ void setMessageToShowWhenDisabledPreferenceClicked(@NonNull String str) {
        super.setMessageToShowWhenDisabledPreferenceClicked(str);
    }

    public void setOnSecondaryActionClickListener(@Nullable Consumer<Boolean> consumer) {
        this.mSecondaryActionOnClickListener = consumer;
        notifyChanged();
    }

    public void setSecondaryActionChecked(boolean z) {
        this.mSecondaryActionChecked = z;
        notifyChanged();
    }

    public CarUiTwoActionSwitchPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CarUiTwoActionSwitchPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public CarUiTwoActionSwitchPreference(Context context) {
        super(context);
    }
}
