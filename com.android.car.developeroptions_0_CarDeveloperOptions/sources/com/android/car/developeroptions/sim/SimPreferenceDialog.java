package com.android.car.developeroptions.sim;

import android.R;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.Utils;

/* JADX INFO: loaded from: classes.dex */
public class SimPreferenceDialog extends Activity {
    AlertDialog.Builder mBuilder;
    private String[] mColorStrings;
    private Context mContext;
    View mDialogLayout;
    private int mSlotId;
    private SubscriptionInfo mSubInfoRecord;
    private SubscriptionManager mSubscriptionManager;
    private int[] mTintArr;
    private int mTintSelectorPos;

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.mContext = this;
        this.mSlotId = getIntent().getExtras().getInt("slot_id", -1);
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(this.mContext);
        this.mSubscriptionManager = subscriptionManagerFrom;
        this.mSubInfoRecord = subscriptionManagerFrom.getActiveSubscriptionInfoForSimSlotIndex(this.mSlotId);
        this.mTintArr = this.mContext.getResources().getIntArray(R.array.config_notificationFallbackVibeWaveform);
        this.mColorStrings = this.mContext.getResources().getStringArray(com.android.car.developeroptions.R.array.color_picker);
        this.mTintSelectorPos = 0;
        this.mBuilder = new AlertDialog.Builder(this.mContext);
        View viewInflate = ((LayoutInflater) this.mContext.getSystemService("layout_inflater")).inflate(com.android.car.developeroptions.R.layout.multi_sim_dialog, (ViewGroup) null);
        this.mDialogLayout = viewInflate;
        this.mBuilder.setView(viewInflate);
        createEditDialog(bundle);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putInt("tint_pos", this.mTintSelectorPos);
        bundle.putString("sim_name", ((EditText) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.sim_name)).getText().toString());
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    public void onRestoreInstanceState(Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        int i = bundle.getInt("tint_pos");
        ((Spinner) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.spinner)).setSelection(i);
        this.mTintSelectorPos = i;
        EditText editText = (EditText) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.sim_name);
        editText.setText(bundle.getString("sim_name"));
        Utils.setEditTextCursorPosition(editText);
    }

    private void createEditDialog(Bundle bundle) {
        Resources resources = this.mContext.getResources();
        EditText editText = (EditText) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.sim_name);
        editText.setText(this.mSubInfoRecord.getDisplayName());
        Utils.setEditTextCursorPosition(editText);
        final Spinner spinner = (Spinner) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.spinner);
        SelectColorAdapter selectColorAdapter = new SelectColorAdapter(this.mContext, com.android.car.developeroptions.R.layout.settings_color_picker_item, this.mColorStrings);
        selectColorAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter((SpinnerAdapter) selectColorAdapter);
        int i = 0;
        while (true) {
            int[] iArr = this.mTintArr;
            if (i >= iArr.length) {
                break;
            }
            if (iArr[i] == this.mSubInfoRecord.getIconTint()) {
                spinner.setSelection(i);
                this.mTintSelectorPos = i;
                break;
            }
            i++;
        }
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() { // from class: com.android.car.developeroptions.sim.SimPreferenceDialog.1
            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

            @Override // android.widget.AdapterView.OnItemSelectedListener
            public void onItemSelected(AdapterView<?> adapterView, View view, int i2, long j) {
                spinner.setSelection(i2);
                SimPreferenceDialog.this.mTintSelectorPos = i2;
            }
        });
        TelephonyManager telephonyManager = (TelephonyManager) this.mContext.getSystemService("phone");
        TextView textView = (TextView) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.number);
        String line1Number = telephonyManager.getLine1Number(this.mSubInfoRecord.getSubscriptionId());
        if (TextUtils.isEmpty(line1Number)) {
            textView.setText(resources.getString(R.string.unknownName));
        } else {
            textView.setText(PhoneNumberUtils.formatNumber(line1Number));
        }
        String simOperatorName = telephonyManager.getSimOperatorName(this.mSubInfoRecord.getSubscriptionId());
        TextView textView2 = (TextView) this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.carrier);
        if (TextUtils.isEmpty(simOperatorName)) {
            simOperatorName = this.mContext.getString(R.string.unknownName);
        }
        textView2.setText(simOperatorName);
        this.mBuilder.setTitle(String.format(resources.getString(com.android.car.developeroptions.R.string.sim_editor_title), Integer.valueOf(this.mSubInfoRecord.getSimSlotIndex() + 1)));
        this.mBuilder.setPositiveButton(com.android.car.developeroptions.R.string.okay, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.sim.SimPreferenceDialog.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                EditText editText2 = (EditText) SimPreferenceDialog.this.mDialogLayout.findViewById(com.android.car.developeroptions.R.id.sim_name);
                Utils.setEditTextCursorPosition(editText2);
                String string = editText2.getText().toString();
                int subscriptionId = SimPreferenceDialog.this.mSubInfoRecord.getSubscriptionId();
                SimPreferenceDialog.this.mSubInfoRecord.setDisplayName(string);
                SimPreferenceDialog.this.mSubscriptionManager.setDisplayName(string, subscriptionId, 2);
                int selectedItemPosition = spinner.getSelectedItemPosition();
                int subscriptionId2 = SimPreferenceDialog.this.mSubInfoRecord.getSubscriptionId();
                int i3 = SimPreferenceDialog.this.mTintArr[selectedItemPosition];
                SimPreferenceDialog.this.mSubInfoRecord.setIconTint(i3);
                SimPreferenceDialog.this.mSubscriptionManager.setIconTint(i3, subscriptionId2);
                dialogInterface.dismiss();
            }
        });
        this.mBuilder.setNegativeButton(com.android.car.developeroptions.R.string.cancel, new DialogInterface.OnClickListener(this) { // from class: com.android.car.developeroptions.sim.SimPreferenceDialog.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.dismiss();
            }
        });
        this.mBuilder.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.android.car.developeroptions.sim.SimPreferenceDialog.4
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                SimPreferenceDialog.this.finish();
            }
        });
        this.mBuilder.create().show();
    }

    private class SelectColorAdapter extends ArrayAdapter<CharSequence> {
        private Context mContext;
        private int mResId;

        public SelectColorAdapter(Context context, int i, String[] strArr) {
            super(context, i, strArr);
            this.mContext = context;
            this.mResId = i;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            Resources resources = this.mContext.getResources();
            int dimensionPixelSize = resources.getDimensionPixelSize(com.android.car.developeroptions.R.dimen.color_swatch_size);
            int dimensionPixelSize2 = resources.getDimensionPixelSize(com.android.car.developeroptions.R.dimen.color_swatch_stroke_width);
            if (view == null) {
                view = layoutInflater.inflate(this.mResId, (ViewGroup) null);
                viewHolder = new ViewHolder();
                ShapeDrawable shapeDrawable = new ShapeDrawable(new OvalShape());
                shapeDrawable.setIntrinsicHeight(dimensionPixelSize);
                shapeDrawable.setIntrinsicWidth(dimensionPixelSize);
                shapeDrawable.getPaint().setStrokeWidth(dimensionPixelSize2);
                viewHolder.label = (TextView) view.findViewById(com.android.car.developeroptions.R.id.color_text);
                viewHolder.icon = (ImageView) view.findViewById(com.android.car.developeroptions.R.id.color_icon);
                viewHolder.swatch = shapeDrawable;
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.label.setText(getItem(i));
            viewHolder.swatch.getPaint().setColor(SimPreferenceDialog.this.mTintArr[i]);
            viewHolder.swatch.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            viewHolder.icon.setVisibility(0);
            viewHolder.icon.setImageDrawable(viewHolder.swatch);
            return view;
        }

        @Override // android.widget.ArrayAdapter, android.widget.BaseAdapter, android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            View view2 = getView(i, view, viewGroup);
            ViewHolder viewHolder = (ViewHolder) view2.getTag();
            if (SimPreferenceDialog.this.mTintSelectorPos == i) {
                viewHolder.swatch.getPaint().setStyle(Paint.Style.FILL_AND_STROKE);
            } else {
                viewHolder.swatch.getPaint().setStyle(Paint.Style.STROKE);
            }
            viewHolder.icon.setVisibility(0);
            return view2;
        }

        private class ViewHolder {
            ImageView icon;
            TextView label;
            ShapeDrawable swatch;

            private ViewHolder(SelectColorAdapter selectColorAdapter) {
            }
        }
    }
}
