package org.bouncycastle.util.encoders;

import com.ecarx.xui.adaptapi.hvac.specific.Define;

/* JADX INFO: loaded from: classes3.dex */
public class UrlBase64Encoder extends Base64Encoder {
    public UrlBase64Encoder() {
        this.encodingTable[this.encodingTable.length - 2] = Define.cSubCodeSetADS;
        this.encodingTable[this.encodingTable.length - 1] = 95;
        this.padding = Define.cSubCodeSetFraganceSwitch;
        initialiseDecodingTable();
    }
}
