package com.google.android.material.picker;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.fragment.app.DialogFragment;
import com.google.android.material.R;
import java.text.SimpleDateFormat;
import java.util.Locale;

/* JADX INFO: loaded from: classes8.dex */
public abstract class MaterialPickerDialogFragment<S> extends DialogFragment {
    private static final String THEME_RESOURCE_ID_KEY = "themeResId";
    private TextView header;
    private MaterialCalendarView<? extends S> materialCalendarView;
    private S selection;
    private SimpleDateFormat simpleDateFormat;
    private int themeResId;

    protected abstract MaterialCalendarView<? extends S> createMaterialCalendarView();

    protected abstract int getDefaultThemeAttr();

    protected abstract String getHeaderText();

    protected static void addThemeToBundle(Bundle args, int themeResId) {
        args.putInt(THEME_RESOURCE_ID_KEY, themeResId);
    }

    private static int getThemeResource(Context context, int defaultThemeAttr, int themeResId) {
        if (themeResId != 0) {
            return themeResId;
        }
        TypedValue outValue = new TypedValue();
        context.getTheme().resolveAttribute(defaultThemeAttr, outValue, true);
        return outValue.resourceId;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.simpleDateFormat = new SimpleDateFormat(getResources().getString(R.string.mtrl_picker_date_format), Locale.getDefault());
        this.themeResId = getThemeResource(getContext(), getDefaultThemeAttr(), getArguments().getInt(THEME_RESOURCE_ID_KEY));
        MaterialCalendarView<? extends S> materialCalendarViewCreateMaterialCalendarView = createMaterialCalendarView();
        this.materialCalendarView = materialCalendarViewCreateMaterialCalendarView;
        materialCalendarViewCreateMaterialCalendarView.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.picker.MaterialPickerDialogFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                MaterialPickerDialogFragment.this.updateHeader();
            }
        });
    }

    @Override // androidx.fragment.app.DialogFragment
    public final Dialog onCreateDialog(Bundle bundle) {
        return new Dialog(requireContext(), this.themeResId);
    }

    @Override // androidx.fragment.app.Fragment
    public final View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View root = layoutInflater.inflate(R.layout.mtrl_picker_dialog, viewGroup);
        this.header = (TextView) root.findViewById(R.id.date_picker_header_title);
        FrameLayout calendarViewFrame = (FrameLayout) root.findViewById(R.id.date_picker_calendar_view_frame);
        calendarViewFrame.addView(this.materialCalendarView);
        Button confirmButton = (Button) root.findViewById(R.id.confirm_button);
        Button cancelButton = (Button) root.findViewById(R.id.cancel_button);
        confirmButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.picker.MaterialPickerDialogFragment.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MaterialPickerDialogFragment materialPickerDialogFragment = MaterialPickerDialogFragment.this;
                materialPickerDialogFragment.selection = materialPickerDialogFragment.materialCalendarView.getSelection();
                MaterialPickerDialogFragment.this.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.material.picker.MaterialPickerDialogFragment.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                MaterialPickerDialogFragment.this.selection = null;
                MaterialPickerDialogFragment.this.dismiss();
            }
        });
        updateHeader();
        return root;
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public final void onDismiss(DialogInterface dialogInterface) {
        ViewGroup viewGroup = (ViewGroup) getView();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        super.onDismiss(dialogInterface);
    }

    public final S getSelection() {
        return this.selection;
    }

    public final void setSimpleDateFormat(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
    }

    public final SimpleDateFormat getSimpleDateFormat() {
        return this.simpleDateFormat;
    }

    public final MaterialCalendarView<? extends S> getMaterialCalendarView() {
        return this.materialCalendarView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHeader() {
        this.header.setText(getHeaderText());
    }
}
