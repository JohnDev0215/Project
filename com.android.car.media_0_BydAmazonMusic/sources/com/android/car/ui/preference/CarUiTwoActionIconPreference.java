package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R$id;
import com.android.car.ui.R$layout;
import com.android.car.ui.R$styleable;
import com.android.car.ui.preference.CarUiTwoActionIconPreference;
import spi.b2;

/* JADX INFO: loaded from: classes2.dex */
public class CarUiTwoActionIconPreference extends CarUiTwoActionBasePreference {

    @Nullable
    private Drawable mSecondaryActionIcon;

    @Nullable
    public Runnable mSecondaryActionOnClickListener;

    public CarUiTwoActionIconPreference(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$onBindViewHolder$0(View view) {
        performSecondaryActionClick();
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    public void init(@Nullable AttributeSet attributeSet) {
        super.init(attributeSet);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R$styleable.CarUiTwoActionIconPreference);
        try {
            this.mSecondaryActionIcon = typedArrayObtainStyledAttributes.getDrawable(R$styleable.CarUiTwoActionIconPreference_secondaryActionIcon);
            typedArrayObtainStyledAttributes.recycle();
            setLayoutResourceInternal(R$layout.car_ui_preference_two_action_icon);
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder preferenceViewHolder) {
        super.onBindViewHolder(preferenceViewHolder);
        View viewN = b2.n(preferenceViewHolder.itemView, R$id.car_ui_first_action_container);
        View viewN2 = b2.n(preferenceViewHolder.itemView, R$id.car_ui_second_action_container);
        ViewGroup viewGroup = (ViewGroup) b2.n(preferenceViewHolder.itemView, R$id.car_ui_secondary_action);
        ImageView imageView = (ImageView) b2.n(preferenceViewHolder.itemView, R$id.car_ui_secondary_action_concrete);
        preferenceViewHolder.itemView.setFocusable(false);
        preferenceViewHolder.itemView.setClickable(false);
        final byte b = 0 == true ? 1 : 0;
        viewN.setOnClickListener(new View.OnClickListener(this) { // from class: spi.y1
            public final /* synthetic */ CarUiTwoActionIconPreference b;

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
        imageView.setImageDrawable(this.mSecondaryActionIcon);
        imageView.setEnabled(isSecondaryActionEnabled() || isClickableWhileDisabled());
        viewGroup.setEnabled(isSecondaryActionEnabled() || isClickableWhileDisabled());
        viewGroup.setFocusable(isSecondaryActionEnabled() || isClickableWhileDisabled());
        viewGroup.setOnClickListener(new View.OnClickListener(this) { // from class: spi.y1
            public final /* synthetic */ CarUiTwoActionIconPreference b;

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
        b2.k(viewGroup, isSecondaryActionEnabled(), isUxRestricted());
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

    public void setSecondaryActionIcon(@Nullable Drawable drawable) {
        this.mSecondaryActionIcon = drawable;
        notifyChanged();
    }

    public CarUiTwoActionIconPreference(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public CarUiTwoActionIconPreference(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setSecondaryActionIcon(@DrawableRes int i) {
        setSecondaryActionIcon(ContextCompat.getDrawable(getContext(), i));
    }

    public CarUiTwoActionIconPreference(Context context) {
        super(context);
    }
}
