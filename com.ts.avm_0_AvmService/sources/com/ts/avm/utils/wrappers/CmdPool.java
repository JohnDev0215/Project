package com.ts.avm.utils.wrappers;

import com.ts.avm.utils.Command;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CmdPool {
    public static final int COMMON_SRC_ID = 9;
    public static final int COMMON_TARGET_ID = 7;
    private static final int GET_AVM_VIEW_MODE_ID = 33554490;
    public static final int SID_AVM_SEND = 33554432;
    public static final int SID_FDR = 1000;
    public static final int SID_USB_MOUNT_STATUS_SEND = 36985214;
    public static final int VALUE_3 = 3;
    private static final HashMap<Command, VideoCatCmd> cmdMap;
    private static final byte[] register;

    static {
        HashMap<Command, VideoCatCmd> map = new HashMap<>();
        cmdMap = map;
        map.put(Command.START_AVM, new VideocatCmdBuilder().setId(33554432).setSrcId(9).setTargetId(7).setValue(new int[]{1, 0, 0, 0}).build());
        cmdMap.put(Command.STOP_AVM, new VideocatCmdBuilder().setId(33554432).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, 0}).build());
        cmdMap.put(Command.GET_VIEW_MODE, new VideocatCmdBuilder().setId(GET_AVM_VIEW_MODE_ID).setSrcId(9).setTargetId(7).setValue(new int[]{1, 0, 0, 0}).build());
        register = VideoCatCmd.intToByteArray(11);
    }

    public static HashMap<Command, VideoCatCmd> getCmdMap() {
        return cmdMap;
    }

    public static byte[] getRegister() {
        return register;
    }

    public static VideoCatCmd parseCommand(byte[] data) {
        if (data == null || data.length == 0) {
            return new VideoCatCmd();
        }
        int id = VideoCatCmd.bytes2int(data, 20);
        int srcId = VideoCatCmd.bytes2int(data, 8);
        int targetId = VideoCatCmd.bytes2int(data, 12);
        int value0 = VideoCatCmd.bytes2int(data, 40);
        int value1 = VideoCatCmd.bytes2int(data, 44);
        int value2 = VideoCatCmd.bytes2int(data, 48);
        int value3 = VideoCatCmd.bytes2int(data, 52);
        return new VideocatCmdBuilder().setId(id).setSrcId(srcId).setTargetId(targetId).setValue(new int[]{value0, value1, value2, value3}).build();
    }
}
