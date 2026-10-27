package android.net.netlink;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class StructInetDiagReqV2 {
    private final StructInetDiagSockId mId;
    private final byte mIdiagExt;
    private final byte mPad;
    private final byte mSdiagFamily;
    private final byte mSdiagProtocol;
    private final int mState;

    public StructInetDiagReqV2(int i, InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, int i2, int i3, int i4, int i5) throws NullPointerException {
        this.mSdiagFamily = (byte) i2;
        this.mSdiagProtocol = (byte) i;
        if ((inetSocketAddress == null) != (inetSocketAddress2 == null)) {
            throw new NullPointerException("Local and remote must be both null or both non-null");
        }
        this.mId = (inetSocketAddress == null || inetSocketAddress2 == null) ? null : new StructInetDiagSockId(inetSocketAddress, inetSocketAddress2);
        this.mPad = (byte) i3;
        this.mIdiagExt = (byte) i4;
        this.mState = i5;
    }

    public void pack(ByteBuffer byteBuffer) {
        byteBuffer.put(this.mSdiagFamily);
        byteBuffer.put(this.mSdiagProtocol);
        byteBuffer.put(this.mIdiagExt);
        byteBuffer.put(this.mPad);
        byteBuffer.putInt(this.mState);
        StructInetDiagSockId structInetDiagSockId = this.mId;
        if (structInetDiagSockId != null) {
            structInetDiagSockId.pack(byteBuffer);
        }
    }

    public String toString() {
        String strStringForAddressFamily = NetlinkConstants.stringForAddressFamily(this.mSdiagFamily);
        String strStringForAddressFamily2 = NetlinkConstants.stringForAddressFamily(this.mSdiagProtocol);
        StringBuilder sb = new StringBuilder();
        sb.append("StructInetDiagReqV2{ sdiag_family{");
        sb.append(strStringForAddressFamily);
        sb.append("}, sdiag_protocol{");
        sb.append(strStringForAddressFamily2);
        sb.append("}, idiag_ext{");
        sb.append((int) this.mIdiagExt);
        sb.append(")}, pad{");
        sb.append((int) this.mPad);
        sb.append("}, idiag_states{");
        sb.append(Integer.toHexString(this.mState));
        sb.append("}, ");
        StructInetDiagSockId structInetDiagSockId = this.mId;
        sb.append(structInetDiagSockId != null ? structInetDiagSockId.toString() : "inet_diag_sockid=null");
        sb.append("}");
        return sb.toString();
    }
}
