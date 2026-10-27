package ts.car.service.tbox.data;

import android.os.Parcel;
import android.util.Log;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes2.dex */
public class TboxMessage {
    private static String TAG = TboxMessage.class.getSimpleName();
    public int arg1;
    public int arg2;
    private byte[] mPayload;
    public long mTopic;
    private long timeStamp;

    public TboxMessage(long topic) {
        this.mTopic = topic;
        this.mPayload = null;
        this.timeStamp = System.currentTimeMillis();
    }

    public TboxMessage(long topic, byte[] payload) {
        this.mTopic = topic;
        setPayload(payload);
    }

    public TboxMessage(long topic, ArrayList<Byte> payload) {
        this.mTopic = topic;
        setPayload(payload);
    }

    public TboxMessage(Parcel in) {
        this.mTopic = in.readLong();
        this.arg1 = in.readInt();
        this.arg2 = in.readInt();
        int length = in.readInt();
        Log.d(TAG, "constructor length:" + length);
        if (length > 0) {
            byte[] bArr = new byte[length];
            this.mPayload = bArr;
            in.readByteArray(bArr);
        }
    }

    public long getTopic() {
        return this.mTopic;
    }

    public void setTopic(long topic) {
        this.mTopic = topic;
    }

    public byte[] getPayload() {
        return this.mPayload;
    }

    public void setPayload(byte[] payload) {
        this.mPayload = payload;
    }

    public void setPayload(ArrayList<Byte> payload) {
        if (payload != null) {
            this.mPayload = new byte[payload.size()];
            for (int i = 0; i < payload.size(); i++) {
                this.mPayload[i] = payload.get(i).byteValue();
            }
        }
    }

    public long getTimeStamp() {
        return this.timeStamp;
    }

    public void setTimeStamp(long timestamp) {
        this.timeStamp = timestamp;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TboxMessage{mTopic=0x");
        sb.append(Long.toHexString(this.mTopic));
        sb.append(", arg1=");
        sb.append(this.arg1);
        sb.append(", arg2=");
        sb.append(this.arg2);
        sb.append(", playload size:");
        byte[] bArr = this.mPayload;
        sb.append(bArr == null ? 0 : bArr.length);
        sb.append('}');
        return sb.toString();
    }
}
