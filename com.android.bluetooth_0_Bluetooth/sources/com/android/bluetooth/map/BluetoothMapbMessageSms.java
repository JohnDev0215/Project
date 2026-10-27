package com.android.bluetooth.map;

import android.net.INetd;
import com.android.bluetooth.DeviceWorkArounds;
import com.android.bluetooth.map.BluetoothMapSmsPdu;
import com.android.bluetooth.map.BluetoothMapUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class BluetoothMapbMessageSms extends BluetoothMapbMessage {
    private ArrayList<BluetoothMapSmsPdu.SmsPdu> mSmsBodyPdus = null;
    private String mSmsBody = null;

    public void setSmsBodyPdus(ArrayList<BluetoothMapSmsPdu.SmsPdu> smsBodyPdus) {
        this.mSmsBodyPdus = smsBodyPdus;
        this.mCharset = null;
        if (smsBodyPdus.size() > 0) {
            this.mEncoding = smsBodyPdus.get(0).getEncodingString();
        }
    }

    public String getSmsBody() {
        return this.mSmsBody;
    }

    public void setSmsBody(String smsBody) {
        this.mSmsBody = smsBody;
        this.mCharset = "UTF-8";
        this.mEncoding = null;
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public void parseMsgPart(String msgPart) {
        if (this.mAppParamCharset == 0) {
            byte[] msgBytes = decodeBinary(msgPart);
            if (msgBytes.length > 0 && msgBytes[0] < msgBytes.length - 1 && (msgBytes[msgBytes[0] + 1] & 3) != 1) {
                throw new IllegalArgumentException("Only submit PDUs are supported");
            }
            StringBuilder sb = new StringBuilder();
            sb.append(this.mSmsBody);
            sb.append(BluetoothMapSmsPdu.decodePdu(msgBytes, this.mType == BluetoothMapUtils.TYPE.SMS_CDMA ? 2 : 1));
            this.mSmsBody = sb.toString();
            return;
        }
        this.mSmsBody += msgPart;
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public void parseMsgInit() {
        this.mSmsBody = INetd.NEXTHOP_NONE;
    }

    @Override // com.android.bluetooth.map.BluetoothMapbMessage
    public byte[] encode() throws UnsupportedEncodingException {
        ArrayList<byte[]> bodyFragments = new ArrayList<>();
        String str = this.mSmsBody;
        if (str != null) {
            String tmpBody = str.replaceAll("END:MSG", "/END\\:MSG");
            String remoteAddress = BluetoothMapService.getRemoteDevice().getAddress();
            if (DeviceWorkArounds.addressStartsWith(remoteAddress, DeviceWorkArounds.PCM_CARKIT)) {
                tmpBody = tmpBody.replaceAll("\r", INetd.NEXTHOP_NONE);
            } else if (DeviceWorkArounds.addressStartsWith(remoteAddress, DeviceWorkArounds.FORD_SYNC_CARKIT)) {
                tmpBody = tmpBody.replaceAll("\n", INetd.NEXTHOP_NONE);
            } else if (DeviceWorkArounds.addressStartsWith(remoteAddress, DeviceWorkArounds.SYNC_CARKIT) && tmpBody.length() > 0) {
                int trailingLF = 0;
                while (tmpBody.charAt((tmpBody.length() - trailingLF) - 1) == '\n') {
                    trailingLF++;
                }
                tmpBody = tmpBody.substring(0, tmpBody.length() - trailingLF);
            }
            bodyFragments.add(tmpBody.getBytes("UTF-8"));
        } else {
            ArrayList<BluetoothMapSmsPdu.SmsPdu> arrayList = this.mSmsBodyPdus;
            if (arrayList != null && arrayList.size() > 0) {
                for (BluetoothMapSmsPdu.SmsPdu pdu : this.mSmsBodyPdus) {
                    bodyFragments.add(encodeBinary(pdu.getData(), pdu.getScAddress()).getBytes("UTF-8"));
                }
            } else {
                bodyFragments.add(new byte[0]);
            }
        }
        return encodeGeneric(bodyFragments);
    }
}
