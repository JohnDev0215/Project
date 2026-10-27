package com.ecarx.xui.adaptapi.tbox;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.util.Log;
import com.ecarx.xui.adaptapi.tbox.journallog.IJournalLog;
import com.ecarx.xui.adaptapi.tbox.ota.IOta;
import com.ecarx.xui.adaptapi.tbox.tboxmessager.ITBoxMessager;

/* JADX INFO: loaded from: classes2.dex */
public class MyTBox extends TBox {
    Context _context;

    MyTBox(Context context) {
        this._context = context;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getIMEI() {
        String string = Settings.System.getString(this._context.getContentResolver(), "IMEI");
        Log.d("TBox API", "IMEIStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getIMSI() {
        String string = Settings.System.getString(this._context.getContentResolver(), "IMSIVersion");
        Log.d("TBox API", "IMSIStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getTBoxID() {
        String string = Settings.System.getString(this._context.getContentResolver(), "TBoxId");
        this._context.sendBroadcast(new Intent("com.neusoft.shockwave.tboxclient.TBoxInfoQueryReceiver"));
        Log.d("TBox API", "tBoxIDStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getICCID() {
        String string = Settings.System.getString(this._context.getContentResolver(), "ICCId");
        Log.d("TBox API", "ICCIDStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getMSISDN() {
        String string = Settings.System.getString(this._context.getContentResolver(), "MSISDN");
        Log.d("TBox API", "MSISDNStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getSoftwareVersion() {
        String string = Settings.System.getString(this._context.getContentResolver(), "MPUVersion");
        Log.d("TBox API", "mpuVersionStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public int getSoftwareVersionCode() {
        Log.d("TBox API", "mpuVersionInt 无法提供 只返回默认值:0");
        return 0;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public String getHardwareVersion() {
        String string = Settings.System.getString(this._context.getContentResolver(), "hardwareVersion");
        Log.d("TBox API", "hardwareVersionStr:" + string);
        return string;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public IOta getOta() {
        Log.d("TBox API", "Ota 功能未实现 只返回默认值:null");
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public boolean setTBoxReset() {
        Log.d("TBox API", "setTBoxReset空实现 只返回默认值:false");
        return false;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public ITBoxMessager getTBoxMessager() {
        Log.d("TBox API", "getTBoxMessager空实现 只返回默认值:null");
        return null;
    }

    @Override // com.ecarx.xui.adaptapi.tbox.TBox
    public IJournalLog getIJournalLog() {
        Log.d("TBox API", "getIJournalLog空实现 只返回默认值:null");
        return null;
    }
}
