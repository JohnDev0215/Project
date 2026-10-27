package com.ts.avm.sdk.wrappers;

import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import com.ts.avm.sdk.utils.LogManager;
import java.util.HashMap;

/* JADX INFO: loaded from: classes2.dex */
public class CmdPool {
    private static final String TAG = "CmdPool";
    private static final HashMap<AvmManager.Command, CmdBean> cmdMap;

    static {
        HashMap<AvmManager.Command, CmdBean> map = new HashMap<>();
        cmdMap = map;
        map.put(AvmManager.Default.NONE, new CmdBuilder().build());
        map.put(AvmManager.State.START, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.State.START.getNum()}).build());
        map.put(AvmManager.State.STOP, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.State.STOP.getNum()}).build());
        map.put(AvmManager.Back.DEFAULT, new CmdBuilder().setId(33558528).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Back.DEFAULT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Calibration.START, new CmdBuilder().setId(AvmManagerImpl.AVM_CALIBRATION_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Calibration.START.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Calibration.STOP, new CmdBuilder().setId(AvmManagerImpl.AVM_CALIBRATION_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Calibration.STOP.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Kill.DEFAULT, new CmdBuilder().setId(AvmManagerImpl.AVM_CALIBRATION_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Kill.DEFAULT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Orientation.HORIZONTAL, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Orientation.HORIZONTAL.getNum()}).build());
        map.put(AvmManager.Orientation.VERTICAL, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Orientation.VERTICAL.getNum()}).build());
        map.put(AvmManager.Theme.LIGHT, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Theme.LIGHT.getNum()}).build());
        map.put(AvmManager.Theme.DARK, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Theme.DARK.getNum()}).build());
        map.put(AvmManager.Language.CHINESE, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.CHINESE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.ENGLISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.ENGLISH.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_1, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_1.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_2, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_2.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_3, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_3.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_4, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_4.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_5, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_5.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_6, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_6.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_7, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_7.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_8, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_8.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_9, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_9.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_10, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_10.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_11, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_11.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_12, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_12.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_13, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_13.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_14, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_14.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_15, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_15.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_16, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_16.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_17, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_17.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_18, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_18.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.Language.OTHER_19, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.OTHER_19.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.START_AVM, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.START_AVM.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.STOP_AVM, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.STOP_AVM.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.MODE_3D, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MODE_3D.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.MODE_2D, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MODE_2D.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.TRANSPORT_START, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.TRANSPORT_START.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.TRANSPOR_STOP, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.TRANSPOR_STOP.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.LEFT_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.LEFT_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.RIGHT_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.RIGHT_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.FRONT_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.FRONT_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.REAR_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.REAR_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.FRONT_WIDE_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.FRONT_WIDE_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.REAR_WIDE_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.REAR_WIDE_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.MULTI_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MULTI_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.MULTI_FRONT_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MULTI_FRONT_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.MULTI_REAR_MODE, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MULTI_REAR_MODE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.STOP_RADAR, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.STOP_RADAR.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.START_FOREHOLD, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.START_FOREHOLD.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.VoiceEvent.STOP_FOREHOLD, new CmdBuilder().setId(16777216).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.STOP_FOREHOLD.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.NONE, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.NONE.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_FRONT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_REAR.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_LEFT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_LEFT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_RIGHT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_RIGHT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_WIDE_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_WIDE_FRONT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_WIDE_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_WIDE_REAR.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_MULTI_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_MULTI_FRONT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_MULTI_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_MULTI_REAR.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_2D_MULTI_VIEW, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_MULTI_VIEW.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_3D_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_FRONT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_3D_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_REAR.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_3D_LEFT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_LEFT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_3D_RIGHT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_RIGHT.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.VIEW_3D_FOREHOLD, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_FOREHOLD.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.RADAR_FLOATING, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.RADAR_FLOATING.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.TRACTION_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.TRACTION_REAR.getNum(), 0, 0, 0}).build());
        map.put(AvmManager.ViewMode.TRACTION_WIDE_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.TRACTION_WIDE_REAR.getNum(), 0, 0, 0}).build());
    }

    public static CmdBean getCmd(AvmManager.Command command) {
        HashMap<AvmManager.Command, CmdBean> map = cmdMap;
        if (!map.containsKey(command)) {
            LogManager.loge(TAG, "Command ERROR ", new Throwable());
            return map.get(AvmManager.Default.NONE);
        }
        return map.get(command);
    }
}
