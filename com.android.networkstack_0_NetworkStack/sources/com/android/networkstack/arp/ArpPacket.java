package com.android.networkstack.arp;

import android.net.MacAddress;
import android.system.OsConstants;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class ArpPacket {
    public final short opCode;
    public final MacAddress senderHwAddress;
    public final Inet4Address senderIp;
    public final Inet4Address targetIp;

    ArpPacket(short s, MacAddress macAddress, Inet4Address inet4Address, MacAddress macAddress2, Inet4Address inet4Address2) {
        this.opCode = s;
        this.senderHwAddress = macAddress;
        this.senderIp = inet4Address;
        this.targetIp = inet4Address2;
    }

    public static ByteBuffer buildArpPacket(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, byte[] bArr5, short s) {
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(42);
        byteBufferAllocate.put(bArr);
        byteBufferAllocate.put(bArr2);
        byteBufferAllocate.putShort((short) OsConstants.ETH_P_ARP);
        byteBufferAllocate.putShort((short) 1);
        byteBufferAllocate.putShort((short) OsConstants.ETH_P_IP);
        byteBufferAllocate.put((byte) 6);
        byteBufferAllocate.put((byte) 4);
        byteBufferAllocate.putShort(s);
        byteBufferAllocate.put(bArr2);
        byteBufferAllocate.put(bArr5);
        byteBufferAllocate.put(bArr4);
        byteBufferAllocate.put(bArr3);
        byteBufferAllocate.flip();
        return byteBufferAllocate;
    }

    public static ArpPacket parseArpPacket(byte[] bArr, int i) throws ParseException {
        if (i >= 42) {
            try {
                if (bArr.length >= i) {
                    ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 0, i);
                    byteBufferWrap.get(new byte[6]);
                    byteBufferWrap.get(new byte[6]);
                    short s = byteBufferWrap.getShort();
                    if (s != OsConstants.ETH_P_ARP) {
                        throw new ParseException("Incorrect Ether Type: " + ((int) s));
                    }
                    short s2 = byteBufferWrap.getShort();
                    if (s2 != 1) {
                        throw new ParseException("Incorrect HW Type: " + ((int) s2));
                    }
                    short s3 = byteBufferWrap.getShort();
                    if (s3 != OsConstants.ETH_P_IP) {
                        throw new ParseException("Incorrect Protocol Type: " + ((int) s3));
                    }
                    byte b = byteBufferWrap.get();
                    if (b != 6) {
                        throw new ParseException("Incorrect HW address length: " + ((int) b));
                    }
                    byte b2 = byteBufferWrap.get();
                    if (b2 != 4) {
                        throw new ParseException("Incorrect Protocol address length: " + ((int) b2));
                    }
                    short s4 = byteBufferWrap.getShort();
                    if (s4 != 1 && s4 != 2) {
                        throw new ParseException("Incorrect opCode: " + ((int) s4));
                    }
                    byte[] bArr2 = new byte[6];
                    byte[] bArr3 = new byte[4];
                    byteBufferWrap.get(bArr2);
                    byteBufferWrap.get(bArr3);
                    byte[] bArr4 = new byte[6];
                    byte[] bArr5 = new byte[4];
                    byteBufferWrap.get(bArr4);
                    byteBufferWrap.get(bArr5);
                    return new ArpPacket(s4, MacAddress.fromBytes(bArr2), (Inet4Address) InetAddress.getByAddress(bArr3), MacAddress.fromBytes(bArr4), (Inet4Address) InetAddress.getByAddress(bArr5));
                }
            } catch (IllegalArgumentException unused) {
                throw new ParseException("Invalid MAC address representation");
            } catch (IndexOutOfBoundsException unused2) {
                throw new ParseException("Invalid index when wrapping a byte array into a buffer");
            } catch (UnknownHostException unused3) {
                throw new ParseException("Invalid IP address of Host");
            } catch (BufferUnderflowException unused4) {
                throw new ParseException("Invalid buffer position");
            }
        }
        throw new ParseException("Invalid packet length: " + i);
    }

    public static class ParseException extends Exception {
        ParseException(String str) {
            super(str);
        }
    }
}
