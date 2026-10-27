package com.ecarx.xui.adaptapi.tbox;

import android.content.Context;
import com.ecarx.xui.adaptapi.AdaptAPI;
import com.ecarx.xui.adaptapi.sign.SignVerify;
import com.ecarx.xui.adaptapi.tbox.journallog.IJournalLog;
import com.ecarx.xui.adaptapi.tbox.ota.IOta;
import com.ecarx.xui.adaptapi.tbox.tboxmessager.ITBoxMessager;

/* JADX INFO: loaded from: classes2.dex */
public abstract class TBox extends AdaptAPI {
    public abstract String getHardwareVersion();

    public abstract String getICCID();

    public abstract IJournalLog getIJournalLog();

    public abstract String getIMEI();

    public abstract String getIMSI();

    public abstract String getMSISDN();

    public abstract IOta getOta();

    public abstract String getSoftwareVersion();

    public abstract int getSoftwareVersionCode();

    public abstract String getTBoxID();

    public abstract ITBoxMessager getTBoxMessager();

    public abstract boolean setTBoxReset();

    public static TBox create(Context context) {
        if (SignVerify.instance().appVerify(context)) {
            return new MyTBox(context);
        }
        return null;
    }
}
