package com.android.bluetooth.map;

import android.util.Log;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapbMessageEmail extends BluetoothMapbMessage {
    private String mEmailBody = null;

    public void setEmailBody(String emailBody) {
        this.mEmailBody = emailBody;
        this.mCharset = "UTF-8";
        this.mEncoding = "8bit";
    }

    public String getEmailBody() {
        return this.mEmailBody;
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public void parseMsgPart(String msgPart) {
        if (this.mEmailBody == null) {
            this.mEmailBody = msgPart;
            return;
        }
        this.mEmailBody += msgPart;
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public void parseMsgInit() {
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public byte[] encode() throws UnsupportedEncodingException {
        ArrayList<byte[]> bodyFragments = new ArrayList<>();
        String str = this.mEmailBody;
        if (str != null) {
            String tmpBody = str.replaceAll("END:MSG", "/END\\:MSG");
            bodyFragments.add(tmpBody.getBytes("UTF-8"));
        } else {
            Log.e("BluetoothMapbMessage", "Email has no body - this should not be possible");
            bodyFragments.add(new byte[0]);
        }
        return encodeGeneric(bodyFragments);
    }
}
