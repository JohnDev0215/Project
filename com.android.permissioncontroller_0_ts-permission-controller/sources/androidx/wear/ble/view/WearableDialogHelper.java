package androidx.wear.ble.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.widget.Button;

/* JADX INFO: loaded from: classes7.dex */
public class WearableDialogHelper {
    private static final String TAG = "WearableDialogHelper";
    private Drawable mNegativeIcon;
    private int mNegativeIconId;
    private Drawable mNeutralIcon;
    private int mNeutralIconId;
    private Drawable mPositiveIcon;
    private int mPositiveIconId;
    Resources mResources;
    Resources.Theme mTheme;

    public WearableDialogHelper(Context context) {
        this(context.getResources(), context.getTheme());
    }

    public WearableDialogHelper(Resources resources, Resources.Theme theme) {
        this.mResources = resources;
        this.mTheme = theme;
    }

    public Drawable getPositiveIcon() {
        return resolveDrawable(this.mPositiveIcon, this.mPositiveIconId);
    }

    public Drawable getNegativeIcon() {
        return resolveDrawable(this.mNegativeIcon, this.mNegativeIconId);
    }

    public Drawable getNeutralIcon() {
        return resolveDrawable(this.mNeutralIcon, this.mNeutralIconId);
    }

    public WearableDialogHelper setPositiveIcon(int resId) {
        this.mPositiveIconId = resId;
        this.mPositiveIcon = null;
        return this;
    }

    public WearableDialogHelper setPositiveIcon(Drawable icon) {
        this.mPositiveIcon = icon;
        this.mPositiveIconId = 0;
        return this;
    }

    public WearableDialogHelper setNegativeIcon(int resId) {
        this.mNegativeIconId = resId;
        this.mNegativeIcon = null;
        return this;
    }

    public WearableDialogHelper setNegativeIcon(Drawable icon) {
        this.mNegativeIcon = icon;
        this.mNegativeIconId = 0;
        return this;
    }

    public WearableDialogHelper setNeutralIcon(int resId) {
        this.mNeutralIconId = resId;
        this.mNeutralIcon = null;
        return this;
    }

    public WearableDialogHelper setNeutralIcon(Drawable icon) {
        this.mNeutralIcon = icon;
        this.mNeutralIconId = 0;
        return this;
    }

    public void apply(AlertDialog dialog) {
        applyButton(dialog.getButton(-1), getPositiveIcon());
        applyButton(dialog.getButton(-2), getNegativeIcon());
        applyButton(dialog.getButton(-3), getNeutralIcon());
    }

    void applyButton(Button button, Drawable drawable) {
        if (button != null) {
            button.setCompoundDrawablesRelativeWithIntrinsicBounds(drawable, (Drawable) null, (Drawable) null, (Drawable) null);
            button.setAllCaps(false);
        } else if (drawable != null) {
            Log.w(TAG, "non-null drawable used with missing button, did you call AlertDialog.create()?");
        }
    }

    Drawable resolveDrawable(Drawable drawable, int resId) {
        return (drawable != null || resId == 0) ? drawable : this.mResources.getDrawable(resId, this.mTheme);
    }

    public static class DialogBuilder extends AlertDialog.Builder {
        private final WearableDialogHelper mHelper;

        public DialogBuilder(Context context) {
            super(context);
            this.mHelper = new WearableDialogHelper(context.getResources(), context.getTheme());
        }

        public DialogBuilder(Context context, int themeResId) {
            super(context, themeResId);
            this.mHelper = new WearableDialogHelper(context.getResources(), context.getTheme());
        }

        public WearableDialogHelper getHelper() {
            return this.mHelper;
        }

        public DialogBuilder setPositiveIcon(int iconId) {
            this.mHelper.setPositiveIcon(iconId);
            return this;
        }

        public DialogBuilder setPositiveIcon(Drawable icon) {
            this.mHelper.setPositiveIcon(icon);
            return this;
        }

        public DialogBuilder setNegativeIcon(int iconId) {
            this.mHelper.setNegativeIcon(iconId);
            return this;
        }

        public DialogBuilder setNegativeIcon(Drawable icon) {
            this.mHelper.setNegativeIcon(icon);
            return this;
        }

        public DialogBuilder setNeutralIcon(int iconId) {
            this.mHelper.setNeutralIcon(iconId);
            return this;
        }

        public DialogBuilder setNeutralIcon(Drawable icon) {
            this.mHelper.setNeutralIcon(icon);
            return this;
        }

        @Override // android.app.AlertDialog.Builder
        public AlertDialog create() {
            AlertDialog dialog = super.create();
            dialog.create();
            this.mHelper.apply(dialog);
            return dialog;
        }

        @Override // android.app.AlertDialog.Builder
        public AlertDialog show() {
            AlertDialog dialog = create();
            dialog.show();
            return dialog;
        }
    }
}
