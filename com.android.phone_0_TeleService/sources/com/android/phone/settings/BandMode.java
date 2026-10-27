package com.android.phone.settings;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.AsyncResult;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AlertDialog;
import com.android.internal.telephony.Phone;
import com.android.internal.telephony.PhoneFactory;
import com.android.phone.R;

/* JADX INFO: loaded from: classes.dex */
public class BandMode extends Activity {
    private static final String[] BAND_NAMES = {"Automatic", "Europe", "United States", "Japan", "Australia", "Australia 2", "Cellular 800", "PCS", "Class 3 (JTACS)", "Class 4 (Korea-PCS)", "Class 5", "Class 6 (IMT2000)", "Class 7 (700Mhz-Upper)", "Class 8 (1800Mhz-Upper)", "Class 9 (900Mhz)", "Class 10 (800Mhz-Secondary)", "Class 11 (Europe PAMR 400Mhz)", "Class 15 (US-AWS)", "Class 16 (US-2500Mhz)"};
    private ListView mBandList;
    private ArrayAdapter mBandListAdapter;
    private DialogInterface mProgressPanel;
    private BandListItem mTargetBand = null;
    private Phone mPhone = null;
    private AdapterView.OnItemClickListener mBandSelectionHandler = new AdapterView.OnItemClickListener() { // from class: com.android.phone.settings.BandMode.1
        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView adapterView, View view, int i, long j) {
            BandMode.this.getWindow().setFeatureInt(5, -1);
            BandMode.this.mTargetBand = (BandListItem) adapterView.getAdapter().getItem(i);
            BandMode.this.mPhone.setBandMode(BandMode.this.mTargetBand.getBand(), BandMode.this.mHandler.obtainMessage(200));
        }
    };
    private Handler mHandler = new Handler() { // from class: com.android.phone.settings.BandMode.2
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 100) {
                BandMode.this.bandListLoaded((AsyncResult) message.obj);
            } else {
                if (i != 200) {
                    return;
                }
                AsyncResult asyncResult = (AsyncResult) message.obj;
                BandMode.this.getWindow().setFeatureInt(5, -2);
                if (BandMode.this.isFinishing()) {
                    return;
                }
                BandMode.this.displayBandSelectionResult(asyncResult.exception);
            }
        }
    };

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(5);
        setContentView(R.layout.band_mode);
        this.mPhone = PhoneFactory.getDefaultPhone();
        this.mBandList = (ListView) findViewById(R.id.band);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        this.mBandListAdapter = arrayAdapter;
        this.mBandList.setAdapter((ListAdapter) arrayAdapter);
        this.mBandList.setOnItemClickListener(this.mBandSelectionHandler);
        loadBandList();
    }

    private static class BandListItem {
        private int mBandMode;

        BandListItem(int i) {
            this.mBandMode = 0;
            this.mBandMode = i;
        }

        public int getBand() {
            return this.mBandMode;
        }

        public String toString() {
            if (this.mBandMode < BandMode.BAND_NAMES.length) {
                return BandMode.BAND_NAMES[this.mBandMode];
            }
            return "Band mode " + this.mBandMode;
        }
    }

    private void loadBandList() {
        String string = getString(R.string.band_mode_loading);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(string);
        this.mProgressPanel = builder.show();
        this.mPhone.queryAvailableBandMode(this.mHandler.obtainMessage(100));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void bandListLoaded(android.os.AsyncResult r7) {
        /*
            r6 = this;
            android.content.DialogInterface r0 = r6.mProgressPanel
            if (r0 == 0) goto L7
            r0.dismiss()
        L7:
            r6.clearList()
            java.lang.Object r7 = r7.result
            r0 = 1
            r1 = 0
            if (r7 == 0) goto L42
            int[] r7 = (int[]) r7
            int r2 = r7.length
            if (r2 != 0) goto L1d
            java.lang.String r6 = "phone"
            java.lang.String r7 = "No Supported Band Modes"
            android.util.Log.wtf(r6, r7)
            return
        L1d:
            r2 = r7[r1]
            if (r2 <= 0) goto L42
            android.widget.ArrayAdapter r3 = r6.mBandListAdapter
            com.android.phone.settings.BandMode$BandListItem r4 = new com.android.phone.settings.BandMode$BandListItem
            r4.<init>(r1)
            r3.add(r4)
            r3 = r0
        L2c:
            if (r3 > r2) goto L43
            r4 = r7[r3]
            if (r4 != 0) goto L33
            goto L3f
        L33:
            com.android.phone.settings.BandMode$BandListItem r4 = new com.android.phone.settings.BandMode$BandListItem
            r5 = r7[r3]
            r4.<init>(r5)
            android.widget.ArrayAdapter r5 = r6.mBandListAdapter
            r5.add(r4)
        L3f:
            int r3 = r3 + 1
            goto L2c
        L42:
            r0 = r1
        L43:
            if (r0 != 0) goto L56
        L45:
            r7 = 19
            if (r1 >= r7) goto L56
            com.android.phone.settings.BandMode$BandListItem r7 = new com.android.phone.settings.BandMode$BandListItem
            r7.<init>(r1)
            android.widget.ArrayAdapter r0 = r6.mBandListAdapter
            r0.add(r7)
            int r1 = r1 + 1
            goto L45
        L56:
            android.widget.ListView r6 = r6.mBandList
            r6.requestFocus()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.settings.BandMode.bandListLoaded(android.os.AsyncResult):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayBandSelectionResult(Throwable th) {
        String str;
        String str2 = getString(R.string.band_mode_set) + " [" + this.mTargetBand.toString() + "] ";
        if (th != null) {
            str = str2 + getString(R.string.band_mode_failed);
        } else {
            str = str2 + getString(R.string.band_mode_succeeded);
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(str);
        builder.setPositiveButton(android.R.string.ok, null);
        this.mProgressPanel = builder.show();
    }

    private void clearList() {
        while (this.mBandListAdapter.getCount() > 0) {
            ArrayAdapter arrayAdapter = this.mBandListAdapter;
            arrayAdapter.remove(arrayAdapter.getItem(0));
        }
    }
}
