package com.android.car.ui.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import androidx.preference.Preference;
import androidx.preference.PreferenceViewHolder;
import com.android.car.ui.R;
import com.android.car.ui.utils.CarUiUtils;
import java.util.function.Consumer;

/* JADX INFO: loaded from: classes7.dex */
public class CarUiTwoActionIconPreference extends CarUiTwoActionBasePreference {
    private Drawable mSecondaryActionIcon;
    protected Runnable mSecondaryActionOnClickListener;

    public CarUiTwoActionIconPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public CarUiTwoActionIconPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CarUiTwoActionIconPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CarUiTwoActionIconPreference(Context context) {
        super(context);
    }

    @Override // com.android.car.ui.preference.CarUiTwoActionBasePreference
    protected void init(AttributeSet attrs) {
        super.init(attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.CarUiTwoActionIconPreference);
        try {
            this.mSecondaryActionIcon = a.getDrawable(R.styleable.CarUiTwoActionIconPreference_secondaryActionIcon);
            a.recycle();
            setLayoutResourceInternal(R.layout.car_ui_preference_two_action_icon);
        } catch (Throwable th) {
            a.recycle();
            throw th;
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
        ViewGroup secondaryButton = (ViewGroup) CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_secondary_action);
        ImageView iconView = (ImageView) CarUiUtils.requireViewByRefId(holder.itemView, R.id.car_ui_secondary_action_concrete);
        holder.itemView.setFocusable(false);
        holder.itemView.setClickable(false);
        firstActionContainer.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionIconPreference$fzt1io5tFfIO5qWzt2q8uw4WiZU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.performClick(view);
            }
        });
        firstActionContainer.setEnabled(isEnabled());
        firstActionContainer.setFocusable(isEnabled());
        secondActionContainer.setVisibility(this.mSecondaryActionVisible ? 0 : 8);
        iconView.setImageDrawable(this.mSecondaryActionIcon);
        iconView.setEnabled(isSecondaryActionEnabled());
        secondaryButton.setEnabled(isSecondaryActionEnabled());
        secondaryButton.setFocusable(isSecondaryActionEnabled());
        secondaryButton.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.ui.preference.-$$Lambda$CarUiTwoActionIconPreference$nXxe6ZJpNtNSmnRywZeOfDhq8cM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$onBindViewHolder$0$CarUiTwoActionIconPreference(view);
            }
        });
    }

    public /* synthetic */ void lambda$onBindViewHolder$0$CarUiTwoActionIconPreference(View v) {
        performSecondaryActionClickInternal();
    }

    public void setSecondaryActionIcon(Drawable drawable) {
        this.mSecondaryActionIcon = drawable;
        notifyChanged();
    }

    public void setSecondaryActionIcon(int resid) {
        setSecondaryActionIcon(ContextCompat.getDrawable(getContext(), resid));
    }

    public void setOnSecondaryActionClickListener(Runnable onClickListener) {
        this.mSecondaryActionOnClickListener = onClickListener;
        notifyChanged();
    }
}
