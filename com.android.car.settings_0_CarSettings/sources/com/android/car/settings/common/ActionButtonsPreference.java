package com.android.car.settings.common;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.preference.PreferenceViewHolder;
import com.android.car.settings.R;
import com.android.car.settings.common.ActionButtonInfo;
import com.android.car.ui.preference.CarUiPreference;

/* JADX INFO: loaded from: classes5.dex */
public class ActionButtonsPreference extends CarUiPreference implements ActionButtonInfo.ButtonInfoChangeListener {
    private boolean mAllowDividerAbove;
    private boolean mAllowDividerBelow;
    private ActionButtonInfo mButton1Info;
    private ActionButtonInfo mButton2Info;
    private ActionButtonInfo mButton3Info;
    private ActionButtonInfo mButton4Info;

    public enum ActionButtons {
        BUTTON1,
        BUTTON2,
        BUTTON3,
        BUTTON4
    }

    public ActionButtonsPreference(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public ActionButtonsPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ActionButtonsPreference(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ActionButtonsPreference(Context context) {
        super(context);
        init(context, null);
    }

    private void init(Context context, AttributeSet attrs) {
        setLayoutResource(R.layout.action_buttons_preference);
        setSelectable(false);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.Preference);
        this.mAllowDividerAbove = a.getBoolean(R.styleable.Preference_allowDividerAbove, true);
        this.mAllowDividerBelow = a.getBoolean(R.styleable.Preference_allowDividerBelow, true);
        this.mButton1Info = new ActionButtonInfo(context, this);
        this.mButton2Info = new ActionButtonInfo(context, this);
        this.mButton3Info = new ActionButtonInfo(context, this);
        this.mButton4Info = new ActionButtonInfo(context, this);
    }

    @Override // com.android.car.ui.preference.CarUiPreference, androidx.preference.Preference
    public void onBindViewHolder(PreferenceViewHolder holder) {
        super.onBindViewHolder(holder);
        holder.findViewById(R.id.topDivider).setVisibility(this.mAllowDividerAbove ? 0 : 8);
        holder.findViewById(R.id.bottomDivider).setVisibility(this.mAllowDividerBelow ? 0 : 8);
        this.mButton1Info.setButtonView(holder.findViewById(R.id.button1)).setButtonTextView((TextView) holder.findViewById(R.id.button1Text)).setButtonIconView((ImageView) holder.findViewById(R.id.button1Icon)).setPreferenceRestricted(isUxRestricted());
        this.mButton2Info.setButtonView(holder.findViewById(R.id.button2)).setButtonTextView((TextView) holder.findViewById(R.id.button2Text)).setButtonIconView((ImageView) holder.findViewById(R.id.button2Icon)).setPreferenceRestricted(isUxRestricted());
        this.mButton3Info.setButtonView(holder.findViewById(R.id.button3)).setButtonTextView((TextView) holder.findViewById(R.id.button3Text)).setButtonIconView((ImageView) holder.findViewById(R.id.button3Icon)).setPreferenceRestricted(isUxRestricted());
        this.mButton4Info.setButtonView(holder.findViewById(R.id.button4)).setButtonTextView((TextView) holder.findViewById(R.id.button4Text)).setButtonIconView((ImageView) holder.findViewById(R.id.button4Icon)).setPreferenceRestricted(isUxRestricted());
        this.mButton1Info.setUpButton();
        this.mButton2Info.setUpButton();
        this.mButton3Info.setUpButton();
        this.mButton4Info.setUpButton();
    }

    @Override // com.android.car.settings.common.ActionButtonInfo.ButtonInfoChangeListener
    public void onButtonInfoChange(ActionButtonInfo buttonInfo) {
        notifyChanged();
    }

    /* JADX INFO: renamed from: com.android.car.settings.common.ActionButtonsPreference$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$android$car$settings$common$ActionButtonsPreference$ActionButtons;

        static {
            int[] iArr = new int[ActionButtons.values().length];
            $SwitchMap$com$android$car$settings$common$ActionButtonsPreference$ActionButtons = iArr;
            try {
                iArr[ActionButtons.BUTTON1.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$android$car$settings$common$ActionButtonsPreference$ActionButtons[ActionButtons.BUTTON2.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$android$car$settings$common$ActionButtonsPreference$ActionButtons[ActionButtons.BUTTON3.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$android$car$settings$common$ActionButtonsPreference$ActionButtons[ActionButtons.BUTTON4.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public ActionButtonInfo getButton(ActionButtons button) {
        int i = AnonymousClass1.$SwitchMap$com$android$car$settings$common$ActionButtonsPreference$ActionButtons[button.ordinal()];
        if (i == 1) {
            return this.mButton1Info;
        }
        if (i == 2) {
            return this.mButton2Info;
        }
        if (i == 3) {
            return this.mButton3Info;
        }
        if (i == 4) {
            return this.mButton4Info;
        }
        throw new IllegalArgumentException("Invalid button requested");
    }
}
