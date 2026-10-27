package com.android.car.developeroptions.sim;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.telecom.PhoneAccount;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.SubscriptionInfo;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import com.android.car.developeroptions.R;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/* JADX INFO: loaded from: classes.dex */
public class SimDialogActivity extends Activity {
    public static String DIALOG_TYPE_KEY = "dialog_type";
    public static String PREFERRED_SIM = "preferred_sim";

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        int intExtra = getIntent().getIntExtra(DIALOG_TYPE_KEY, -1);
        if (intExtra == 0 || intExtra == 1 || intExtra == 2) {
            createDialog(this, intExtra).show();
            return;
        }
        if (intExtra == 3) {
            displayPreferredDialog(getIntent().getIntExtra(PREFERRED_SIM, 0));
            return;
        }
        throw new IllegalArgumentException("Invalid dialog type " + intExtra + " sent.");
    }

    private void displayPreferredDialog(int i) {
        Resources resources = getResources();
        final Context applicationContext = getApplicationContext();
        final SubscriptionInfo activeSubscriptionInfoForSimSlotIndex = SubscriptionManager.from(applicationContext).getActiveSubscriptionInfoForSimSlotIndex(i);
        if (activeSubscriptionInfoForSimSlotIndex != null) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.sim_preferred_title);
            builder.setMessage(resources.getString(R.string.sim_preferred_message, activeSubscriptionInfoForSimSlotIndex.getDisplayName()));
            builder.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.sim.SimDialogActivity.1
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    int subscriptionId = activeSubscriptionInfoForSimSlotIndex.getSubscriptionId();
                    PhoneAccountHandle phoneAccountHandleSubscriptionIdToPhoneAccountHandle = SimDialogActivity.this.subscriptionIdToPhoneAccountHandle(subscriptionId);
                    SimDialogActivity.setDefaultDataSubId(applicationContext, subscriptionId);
                    SimDialogActivity.setDefaultSmsSubId(applicationContext, subscriptionId);
                    SimDialogActivity.this.setUserSelectedOutgoingPhoneAccount(phoneAccountHandleSubscriptionIdToPhoneAccountHandle);
                    SimDialogActivity.this.finish();
                }
            });
            builder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.sim.SimDialogActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i2) {
                    SimDialogActivity.this.finish();
                }
            });
            builder.create().show();
            return;
        }
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setDefaultDataSubId(Context context, int i) {
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(context);
        TelephonyManager telephonyManagerCreateForSubscriptionId = TelephonyManager.from(context).createForSubscriptionId(i);
        subscriptionManagerFrom.setDefaultDataSubId(i);
        telephonyManagerCreateForSubscriptionId.setDataEnabled(true);
        Toast.makeText(context, R.string.data_switch_started, 1).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void setDefaultSmsSubId(Context context, int i) {
        SubscriptionManager.from(context).setDefaultSmsSubId(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setUserSelectedOutgoingPhoneAccount(PhoneAccountHandle phoneAccountHandle) {
        TelecomManager.from(this).setUserSelectedOutgoingPhoneAccount(phoneAccountHandle);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public PhoneAccountHandle subscriptionIdToPhoneAccountHandle(int i) {
        TelecomManager telecomManagerFrom = TelecomManager.from(this);
        TelephonyManager telephonyManagerFrom = TelephonyManager.from(this);
        ListIterator<PhoneAccountHandle> listIterator = telecomManagerFrom.getCallCapablePhoneAccounts().listIterator();
        while (listIterator.hasNext()) {
            PhoneAccountHandle next = listIterator.next();
            if (i == telephonyManagerFrom.getSubIdForPhoneAccount(telecomManagerFrom.getPhoneAccount(next))) {
                return next;
            }
        }
        return null;
    }

    public Dialog createDialog(final Context context, final int i) {
        ArrayList arrayList = new ArrayList();
        final List activeSubscriptionInfoList = SubscriptionManager.from(context).getActiveSubscriptionInfoList(true);
        int size = activeSubscriptionInfoList == null ? 0 : activeSubscriptionInfoList.size();
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() { // from class: com.android.car.developeroptions.sim.SimDialogActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                int i3 = i;
                if (i3 == 0) {
                    SimDialogActivity.setDefaultDataSubId(context, ((SubscriptionInfo) activeSubscriptionInfoList.get(i2)).getSubscriptionId());
                } else if (i3 == 1) {
                    SimDialogActivity.this.setUserSelectedOutgoingPhoneAccount(i2 < 1 ? null : TelecomManager.from(context).getCallCapablePhoneAccounts().get(i2 - 1));
                } else if (i3 == 2) {
                    SimDialogActivity.setDefaultSmsSubId(context, ((SubscriptionInfo) activeSubscriptionInfoList.get(i2)).getSubscriptionId());
                } else {
                    throw new IllegalArgumentException("Invalid dialog type " + i + " in SIM dialog.");
                }
                SimDialogActivity.this.finish();
            }
        };
        DialogInterface.OnKeyListener onKeyListener = new DialogInterface.OnKeyListener() { // from class: com.android.car.developeroptions.sim.SimDialogActivity.4
            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                if (i2 != 4) {
                    return true;
                }
                SimDialogActivity.this.finish();
                return true;
            }
        };
        ArrayList arrayList2 = new ArrayList();
        if (i == 1) {
            TelecomManager telecomManagerFrom = TelecomManager.from(context);
            TelephonyManager telephonyManagerFrom = TelephonyManager.from(context);
            ListIterator<PhoneAccountHandle> listIterator = telecomManagerFrom.getCallCapablePhoneAccounts().listIterator();
            arrayList.add(getResources().getString(R.string.sim_calls_ask_first_prefs_title));
            arrayList2.add(null);
            while (listIterator.hasNext()) {
                PhoneAccount phoneAccount = telecomManagerFrom.getPhoneAccount(listIterator.next());
                arrayList.add((String) phoneAccount.getLabel());
                int subIdForPhoneAccount = telephonyManagerFrom.getSubIdForPhoneAccount(phoneAccount);
                if (subIdForPhoneAccount != -1) {
                    arrayList2.add(SubscriptionManager.from(context).getActiveSubscriptionInfo(subIdForPhoneAccount));
                } else {
                    arrayList2.add(null);
                }
            }
        } else {
            for (int i2 = 0; i2 < size; i2++) {
                CharSequence displayName = ((SubscriptionInfo) activeSubscriptionInfoList.get(i2)).getDisplayName();
                if (displayName == null) {
                    displayName = "";
                }
                arrayList.add(displayName.toString());
            }
        }
        String[] strArr = (String[]) arrayList.toArray(new String[0]);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (i == 1) {
            activeSubscriptionInfoList = arrayList2;
        }
        SelectAccountListAdapter selectAccountListAdapter = new SelectAccountListAdapter(activeSubscriptionInfoList, builder.getContext(), R.layout.select_account_list_item, strArr, i);
        if (i == 0) {
            builder.setTitle(R.string.select_sim_for_data);
        } else if (i == 1) {
            builder.setTitle(R.string.select_sim_for_calls);
        } else if (i == 2) {
            builder.setTitle(R.string.select_sim_for_sms);
        } else {
            throw new IllegalArgumentException("Invalid dialog type " + i + " in SIM dialog.");
        }
        builder.setAdapter(selectAccountListAdapter, onClickListener);
        AlertDialog alertDialogCreate = builder.create();
        alertDialogCreate.setOnKeyListener(onKeyListener);
        alertDialogCreate.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.android.car.developeroptions.sim.SimDialogActivity.5
            @Override // android.content.DialogInterface.OnCancelListener
            public void onCancel(DialogInterface dialogInterface) {
                SimDialogActivity.this.finish();
            }
        });
        return alertDialogCreate;
    }

    private class SelectAccountListAdapter extends ArrayAdapter<String> {
        private Context mContext;
        private int mResId;
        private List<SubscriptionInfo> mSubInfoList;

        public SelectAccountListAdapter(List<SubscriptionInfo> list, Context context, int i, String[] strArr, int i2) {
            super(context, i, strArr);
            this.mContext = context;
            this.mResId = i;
            this.mSubInfoList = list;
        }

        @Override // android.widget.ArrayAdapter, android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            LayoutInflater layoutInflater = (LayoutInflater) this.mContext.getSystemService("layout_inflater");
            if (view == null) {
                view = layoutInflater.inflate(this.mResId, (ViewGroup) null);
                viewHolder = new ViewHolder();
                viewHolder.title = (TextView) view.findViewById(R.id.title);
                viewHolder.summary = (TextView) view.findViewById(R.id.summary);
                viewHolder.icon = (ImageView) view.findViewById(R.id.icon);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            SubscriptionInfo subscriptionInfo = this.mSubInfoList.get(i);
            if (subscriptionInfo == null) {
                viewHolder.title.setText(getItem(i));
                viewHolder.summary.setText("");
                viewHolder.icon.setImageDrawable(SimDialogActivity.this.getResources().getDrawable(R.drawable.ic_live_help));
                viewHolder.icon.setAlpha(0.54f);
            } else {
                viewHolder.title.setText(subscriptionInfo.getDisplayName());
                viewHolder.summary.setText(subscriptionInfo.getNumber());
                viewHolder.icon.setImageBitmap(subscriptionInfo.createIconBitmap(this.mContext));
            }
            return view;
        }

        private class ViewHolder {
            ImageView icon;
            TextView summary;
            TextView title;

            private ViewHolder(SelectAccountListAdapter selectAccountListAdapter) {
            }
        }
    }
}
