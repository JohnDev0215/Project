package com.android.car.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.imewidescreen.CarUiImeWideScreenController;
import com.android.car.ui.recyclerview.CarUiListItemAdapter;
import com.android.car.ui.recyclerview.CarUiRadioButtonListItemAdapter;
import com.android.car.ui.utils.CarUiUtils;

/* JADX INFO: loaded from: classes7.dex */
public class AlertDialogBuilder {
    private boolean mAllowDismissButton;
    private AlertDialog.Builder mBuilder;
    private EditText mCarUiEditText;
    private Context mContext;
    private boolean mHasSingleChoiceBodyButton;
    private Drawable mIcon;
    private boolean mIconTinted;
    private InputMethodManager mInputMethodManager;
    private boolean mNegativeButtonSet;
    private boolean mNeutralButtonSet;
    private final View.OnApplyWindowInsetsListener mOnApplyWindowInsetsListener;
    private final DialogInterface.OnDismissListener mOnDismissListener;
    private boolean mPositiveButtonSet;
    private ViewGroup mRoot;
    private CharSequence mSubtitle;
    private CharSequence mTitle;
    private String mWideScreenTitle;
    private String mWideScreenTitleDesc;

    public /* synthetic */ WindowInsets lambda$new$0$AlertDialogBuilder(View v, WindowInsets insets) {
        if (Build.VERSION.SDK_INT < 30) {
            return v.onApplyWindowInsets(insets);
        }
        if (insets.isVisible(WindowInsets.Type.ime())) {
            Bundle bundle = new Bundle();
            String title = this.mWideScreenTitle;
            if (title == null) {
                title = this.mTitle.toString();
            }
            bundle.putString(CarUiImeWideScreenController.ADD_DESC_TITLE_TO_CONTENT_AREA, title);
            String str = this.mWideScreenTitleDesc;
            if (str != null) {
                bundle.putString(CarUiImeWideScreenController.ADD_DESC_TO_CONTENT_AREA, str);
            }
            this.mInputMethodManager.sendAppPrivateCommand(this.mCarUiEditText, CarUiImeWideScreenController.WIDE_SCREEN_ACTION, bundle);
        }
        return v.onApplyWindowInsets(insets);
    }

    public /* synthetic */ void lambda$new$1$AlertDialogBuilder(DialogInterface dialog) {
        ViewGroup viewGroup = this.mRoot;
        if (viewGroup != null) {
            viewGroup.setOnApplyWindowInsetsListener(null);
        }
    }

    public AlertDialogBuilder(Context context) {
        this(context, 0);
    }

    public AlertDialogBuilder(Context context, int themeResId) {
        this.mAllowDismissButton = true;
        this.mHasSingleChoiceBodyButton = false;
        this.mOnApplyWindowInsetsListener = new View.OnApplyWindowInsetsListener() { // from class: com.android.car.ui.-$$Lambda$AlertDialogBuilder$MUP4IX8R1OUHR9U791mPN6CBWyg
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
                return this.f$0.lambda$new$0$AlertDialogBuilder(view, windowInsets);
            }
        };
        this.mOnDismissListener = new DialogInterface.OnDismissListener() { // from class: com.android.car.ui.-$$Lambda$AlertDialogBuilder$ul5Osxow355Z2I-3kry73AO2H2M
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                this.f$0.lambda$new$1$AlertDialogBuilder(dialogInterface);
            }
        };
        this.mBuilder = new AlertDialog.Builder(context, themeResId);
        this.mInputMethodManager = (InputMethodManager) context.getSystemService("input_method");
        this.mContext = context;
    }

    public Context getContext() {
        return this.mBuilder.getContext();
    }

    public AlertDialogBuilder setTitle(int titleId) {
        return setTitle(this.mContext.getText(titleId));
    }

    public AlertDialogBuilder setTitle(CharSequence title) {
        this.mTitle = title;
        this.mBuilder.setTitle(title);
        return this;
    }

    public AlertDialogBuilder setSubtitle(int subtitle) {
        return setSubtitle(this.mContext.getString(subtitle));
    }

    public AlertDialogBuilder setSubtitle(CharSequence subtitle) {
        this.mSubtitle = subtitle;
        return this;
    }

    public AlertDialogBuilder setMessage(int messageId) {
        this.mBuilder.setMessage(messageId);
        return this;
    }

    public AlertDialogBuilder setMessage(CharSequence message) {
        this.mBuilder.setMessage(message);
        return this;
    }

    public AlertDialogBuilder setIcon(int iconId) {
        return setIcon(this.mContext.getDrawable(iconId));
    }

    public AlertDialogBuilder setIcon(Drawable icon) {
        this.mIcon = icon;
        return this;
    }

    public AlertDialogBuilder setIconTinted(boolean tinted) {
        this.mIconTinted = tinted;
        return this;
    }

    public AlertDialogBuilder setIconAttribute(int attrId) {
        this.mBuilder.setIconAttribute(attrId);
        return this;
    }

    public AlertDialogBuilder setPositiveButton(int textId, DialogInterface.OnClickListener listener) {
        this.mBuilder.setPositiveButton(textId, listener);
        this.mPositiveButtonSet = true;
        return this;
    }

    public AlertDialogBuilder setPositiveButton(CharSequence text, DialogInterface.OnClickListener listener) {
        this.mBuilder.setPositiveButton(text, listener);
        this.mPositiveButtonSet = true;
        return this;
    }

    public AlertDialogBuilder setNegativeButton(int textId, DialogInterface.OnClickListener listener) {
        this.mBuilder.setNegativeButton(textId, listener);
        this.mNegativeButtonSet = true;
        return this;
    }

    public AlertDialogBuilder setNegativeButton(CharSequence text, DialogInterface.OnClickListener listener) {
        this.mBuilder.setNegativeButton(text, listener);
        this.mNegativeButtonSet = true;
        return this;
    }

    public AlertDialogBuilder setNeutralButton(int textId, DialogInterface.OnClickListener listener) {
        this.mBuilder.setNeutralButton(textId, listener);
        this.mNeutralButtonSet = true;
        return this;
    }

    public AlertDialogBuilder setNeutralButton(CharSequence text, DialogInterface.OnClickListener listener) {
        this.mBuilder.setNeutralButton(text, listener);
        this.mNeutralButtonSet = true;
        return this;
    }

    public AlertDialogBuilder setCancelable(boolean cancelable) {
        this.mBuilder.setCancelable(cancelable);
        return this;
    }

    public AlertDialogBuilder setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.mBuilder.setOnCancelListener(onCancelListener);
        return this;
    }

    public AlertDialogBuilder setOnDismissListener(DialogInterface.OnDismissListener onDismissListener) {
        this.mBuilder.setOnDismissListener(onDismissListener);
        return this;
    }

    public AlertDialogBuilder setOnKeyListener(DialogInterface.OnKeyListener onKeyListener) {
        this.mBuilder.setOnKeyListener(onKeyListener);
        return this;
    }

    public AlertDialogBuilder setItems(int itemsId, DialogInterface.OnClickListener listener) {
        this.mBuilder.setItems(itemsId, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setItems(CharSequence[] items, DialogInterface.OnClickListener listener) {
        this.mBuilder.setItems(items, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    @Deprecated
    public AlertDialogBuilder setAdapter(ListAdapter adapter, DialogInterface.OnClickListener listener) {
        this.mBuilder.setAdapter(adapter, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setAdapter(CarUiListItemAdapter adapter) {
        setCustomList(adapter);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    private void setCustomList(CarUiListItemAdapter adapter) {
        View customList = LayoutInflater.from(this.mContext).inflate(R.layout.car_ui_alert_dialog_list, (ViewGroup) null);
        RecyclerView mList = (RecyclerView) CarUiUtils.requireViewByRefId(customList, R.id.list);
        mList.setLayoutManager(new LinearLayoutManager(this.mContext));
        mList.setAdapter(adapter);
        this.mBuilder.setView(customList);
    }

    public AlertDialogBuilder setCursor(Cursor cursor, DialogInterface.OnClickListener listener, String labelColumn) {
        this.mBuilder.setCursor(cursor, listener, labelColumn);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setMultiChoiceItems(int itemsId, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
        this.mBuilder.setMultiChoiceItems(itemsId, checkedItems, listener);
        this.mHasSingleChoiceBodyButton = false;
        return this;
    }

    public AlertDialogBuilder setMultiChoiceItems(CharSequence[] items, boolean[] checkedItems, DialogInterface.OnMultiChoiceClickListener listener) {
        this.mBuilder.setMultiChoiceItems(items, checkedItems, listener);
        this.mHasSingleChoiceBodyButton = false;
        return this;
    }

    public AlertDialogBuilder setMultiChoiceItems(Cursor cursor, String isCheckedColumn, String labelColumn, DialogInterface.OnMultiChoiceClickListener listener) {
        this.mBuilder.setMultiChoiceItems(cursor, isCheckedColumn, labelColumn, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setSingleChoiceItems(int itemsId, int checkedItem, DialogInterface.OnClickListener listener) {
        this.mBuilder.setSingleChoiceItems(itemsId, checkedItem, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setSingleChoiceItems(Cursor cursor, int checkedItem, String labelColumn, DialogInterface.OnClickListener listener) {
        this.mBuilder.setSingleChoiceItems(cursor, checkedItem, labelColumn, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setSingleChoiceItems(CharSequence[] items, int checkedItem, DialogInterface.OnClickListener listener) {
        this.mBuilder.setSingleChoiceItems(items, checkedItem, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    @Deprecated
    public AlertDialogBuilder setSingleChoiceItems(ListAdapter adapter, int checkedItem, DialogInterface.OnClickListener listener) {
        this.mBuilder.setSingleChoiceItems(adapter, checkedItem, listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    @Deprecated
    public AlertDialogBuilder setSingleChoiceItems(CarUiRadioButtonListItemAdapter adapter, DialogInterface.OnClickListener listener) {
        setCustomList(adapter);
        this.mHasSingleChoiceBodyButton = false;
        return this;
    }

    public AlertDialogBuilder setSingleChoiceItems(CarUiRadioButtonListItemAdapter adapter) {
        setCustomList(adapter);
        this.mHasSingleChoiceBodyButton = false;
        return this;
    }

    public AlertDialogBuilder setOnItemSelectedListener(AdapterView.OnItemSelectedListener listener) {
        this.mBuilder.setOnItemSelectedListener(listener);
        this.mHasSingleChoiceBodyButton = true;
        return this;
    }

    public AlertDialogBuilder setEditBox(String prompt, TextWatcher textChangedListener, InputFilter[] inputFilters, int inputType) {
        View contentView = LayoutInflater.from(this.mContext).inflate(R.layout.car_ui_alert_dialog_edit_text, (ViewGroup) null);
        EditText editText = (EditText) CarUiUtils.requireViewByRefId(contentView, R.id.textbox);
        this.mCarUiEditText = editText;
        editText.setText(prompt);
        if (textChangedListener != null) {
            this.mCarUiEditText.addTextChangedListener(textChangedListener);
        }
        if (inputFilters != null) {
            this.mCarUiEditText.setFilters(inputFilters);
        }
        if (inputType != 0) {
            this.mCarUiEditText.setInputType(inputType);
        }
        this.mBuilder.setView(contentView);
        return this;
    }

    public AlertDialogBuilder setEditBox(String prompt, TextWatcher textChangedListener, InputFilter[] inputFilters) {
        return setEditBox(prompt, textChangedListener, inputFilters, 0);
    }

    public AlertDialogBuilder setAllowDismissButton(boolean allowDismissButton) {
        this.mAllowDismissButton = allowDismissButton;
        return this;
    }

    public AlertDialogBuilder setEditTextTitleAndDescForWideScreen(String title, String desc) {
        this.mWideScreenTitle = title;
        this.mWideScreenTitleDesc = desc;
        return this;
    }

    private void prepareDialog() {
        View customTitle = LayoutInflater.from(this.mContext).inflate(R.layout.car_ui_alert_dialog_title_with_subtitle, (ViewGroup) null);
        TextView mTitleView = (TextView) CarUiUtils.requireViewByRefId(customTitle, R.id.car_ui_alert_title);
        TextView mSubtitleView = (TextView) CarUiUtils.requireViewByRefId(customTitle, R.id.car_ui_alert_subtitle);
        mSubtitleView.setMovementMethod(LinkMovementMethod.getInstance());
        ImageView mIconView = (ImageView) CarUiUtils.requireViewByRefId(customTitle, R.id.car_ui_alert_icon);
        mTitleView.setText(this.mTitle);
        mTitleView.setVisibility(TextUtils.isEmpty(this.mTitle) ? 8 : 0);
        mSubtitleView.setText(this.mSubtitle);
        mSubtitleView.setVisibility(TextUtils.isEmpty(this.mSubtitle) ? 8 : 0);
        mIconView.setImageDrawable(this.mIcon);
        mIconView.setVisibility(this.mIcon != null ? 0 : 8);
        if (this.mIconTinted) {
            mIconView.setImageTintList(this.mContext.getColorStateList(R.color.car_ui_dialog_icon_color));
        }
        this.mBuilder.setCustomTitle(customTitle);
        if (!this.mAllowDismissButton && !this.mHasSingleChoiceBodyButton && !this.mNeutralButtonSet && !this.mNegativeButtonSet && !this.mPositiveButtonSet) {
            throw new RuntimeException("The dialog must have at least one button to disable the dismiss button");
        }
        if (this.mContext.getResources().getBoolean(R.bool.car_ui_alert_dialog_force_dismiss_button) && !this.mNeutralButtonSet && !this.mNegativeButtonSet && !this.mPositiveButtonSet && this.mAllowDismissButton) {
            String mDefaultButtonText = this.mContext.getString(R.string.car_ui_alert_dialog_default_button);
            this.mBuilder.setNegativeButton(mDefaultButtonText, new DialogInterface.OnClickListener() { // from class: com.android.car.ui.-$$Lambda$AlertDialogBuilder$h51GnhnbQvhgpbFYKLXgK9OTHic
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i) {
                    AlertDialogBuilder.lambda$prepareDialog$2(dialogInterface, i);
                }
            });
        }
    }

    static /* synthetic */ void lambda$prepareDialog$2(DialogInterface dialog, int which) {
    }

    public AlertDialog create() {
        prepareDialog();
        AlertDialog alertDialog = this.mBuilder.create();
        this.mRoot = (ViewGroup) alertDialog.getWindow().getDecorView().getRootView();
        FocusParkingView fpv = new FocusParkingView(this.mContext);
        this.mRoot.addView(fpv);
        this.mRoot.setOnApplyWindowInsetsListener(this.mOnApplyWindowInsetsListener);
        setOnDismissListener(this.mOnDismissListener);
        return alertDialog;
    }

    public AlertDialog show() {
        AlertDialog alertDialog = create();
        alertDialog.show();
        return alertDialog;
    }
}
