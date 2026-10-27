package com.engine.tbox.data;

import com.engine.tbox.core.utils.LogUtils;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: classes.dex */
public class ApnArgs {
    private static final int APN_PROTOCOL_LENGTH = 1;
    private static final int APN_TYPE_LENGTH = 1;
    private static final int DEFAULT_APN_PROTOCOL = 0;
    private static final int DEFAULT_APN_TYPE = 2;
    private static final int MAX_APN_NAME_LENGTH = 100;
    private static final int MAX_PASS_LENGTH = 32;
    private static final int MAX_PORT_LENGTH = 5;
    private static final int MAX_PROXY_LENGTH = 64;
    private static final int MAX_USER_LENGTH = 32;
    private static final int TOTAL_LENGTH = 235;
    private final String apnName;
    private final int apnProtocol;
    private final int apnType;
    private final String pass;
    private final String port;
    private final String proxy;
    private final String user;

    private ApnArgs(Builder builder) {
        this.apnName = builder.apnName;
        this.user = builder.user;
        this.pass = builder.pass;
        this.apnType = builder.apnType;
        this.apnProtocol = builder.apnProtocol;
        this.proxy = builder.proxy;
        this.port = builder.port;
    }

    public String getApnName() {
        return this.apnName;
    }

    public String getUser() {
        return this.user;
    }

    public String getPass() {
        return this.pass;
    }

    public int getApnType() {
        return this.apnType;
    }

    public int getApnProtocol() {
        return this.apnProtocol;
    }

    public String getProxy() {
        return this.proxy;
    }

    public String getPort() {
        return this.port;
    }

    public static class Builder {
        private String apnName;
        private String pass;
        private String port;
        private String proxy;
        private String user;
        private int apnType = 2;
        private int apnProtocol = 0;

        public Builder apnName(String apnName) {
            if (apnName == null || apnName.length() > 100) {
                throw new IllegalArgumentException("APN name must be between 1 and 100 characters.");
            }
            this.apnName = apnName;
            return this;
        }

        public Builder user(String user) {
            if (user != null && user.length() > 32) {
                throw new IllegalArgumentException("User name must not exceed 32 characters.");
            }
            this.user = user;
            return this;
        }

        public Builder pass(String pass) {
            if (pass != null && pass.length() > 32) {
                throw new IllegalArgumentException("Password must not exceed 32 characters.");
            }
            this.pass = pass;
            return this;
        }

        public Builder apnType(int apnType) {
            this.apnType = apnType;
            return this;
        }

        public Builder apnProtocol(int apnProtocol) {
            this.apnProtocol = apnProtocol;
            return this;
        }

        public Builder proxy(String proxy) {
            if (proxy != null && proxy.length() > ApnArgs.MAX_PROXY_LENGTH) {
                throw new IllegalArgumentException("Proxy must not exceed 64 characters.");
            }
            this.proxy = proxy;
            return this;
        }

        public Builder port(String port) {
            if (port != null && port.length() > 5) {
                throw new IllegalArgumentException("Port must not exceed 5 characters.");
            }
            this.port = port;
            return this;
        }

        public ApnArgs build() {
            return new ApnArgs(this);
        }
    }

    public byte[] toByteBuf() {
        ByteBuffer byteBuf = ByteBuffer.allocate(235);
        writeFixedLengthString(byteBuf, this.apnName, 100);
        writeFixedLengthString(byteBuf, this.user, 32);
        writeFixedLengthString(byteBuf, this.pass, 32);
        byteBuf.put((byte) this.apnType);
        byteBuf.put((byte) this.apnProtocol);
        writeFixedLengthString(byteBuf, this.proxy, MAX_PROXY_LENGTH);
        writeFixedLengthString(byteBuf, this.port, 5);
        return byteBuf.array();
    }

    private void writeFixedLengthString(ByteBuffer byteBuffer, String str, int maxLength) {
        if (str == null) {
            str = "";
        }
        byte[] bytes = str.getBytes(StandardCharsets.US_ASCII);
        int len = Math.min(bytes.length, maxLength);
        byteBuffer.put(bytes, 0, len);
        for (int i = len; i < maxLength; i++) {
            byteBuffer.put((byte) 0);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer("ApnArgs{");
        sb.append("apnName='");
        sb.append(this.apnName);
        sb.append('\'');
        sb.append(", user='");
        sb.append(this.user);
        sb.append('\'');
        sb.append(", pass='");
        sb.append(MaskUtils.maskString(this.pass));
        sb.append('\'');
        sb.append(", apnType=");
        sb.append(this.apnType);
        sb.append(", apnProtocol=");
        sb.append(this.apnProtocol);
        sb.append(", proxy='");
        sb.append(MaskUtils.maskString(this.proxy));
        sb.append('\'');
        sb.append(", port='");
        sb.append(this.port);
        sb.append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static ApnArgs bytesToApnArgs(byte[] bytes) {
        if (bytes == null || bytes.length < 235) {
            return null;
        }
        Builder builder = new Builder();
        String apnName = new String(bytes, 0, 100, StandardCharsets.US_ASCII).trim();
        builder.apnName(apnName);
        int pos = 0 + 100;
        String userName = new String(bytes, pos, 32, StandardCharsets.US_ASCII).trim();
        builder.user(userName);
        int pos2 = pos + 32;
        String password = new String(bytes, pos2, 32, StandardCharsets.US_ASCII).trim();
        builder.pass(password);
        int pos3 = pos2 + 32;
        int pos4 = pos3 + 1;
        builder.apnType(bytes[pos3]);
        int pos5 = pos4 + 1;
        builder.apnProtocol(bytes[pos4]);
        String proxy = new String(bytes, pos5, MAX_PROXY_LENGTH, StandardCharsets.US_ASCII).trim();
        builder.proxy(proxy);
        String port = new String(bytes, pos5 + MAX_PROXY_LENGTH, 5, StandardCharsets.US_ASCII).trim();
        builder.port(port);
        ApnArgs args = builder.build();
        LogUtils.d(LogUtils.TAG, "bytesToApnArgs: " + args.toString(), new Object[0]);
        return args;
    }
}
