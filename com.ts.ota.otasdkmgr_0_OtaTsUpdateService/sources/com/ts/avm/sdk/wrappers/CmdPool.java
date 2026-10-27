package com.ts.avm.sdk.wrappers;

import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import com.ts.avm.sdk.utils.LogManager;
import java.util.HashMap;

/* JADX INFO: loaded from: classes.dex */
public class CmdPool {
    public static final int AVM_COMMON_SRC_ID = 9;
    public static final int AVM_COMMON_TARGET_ID = 7;
    public static final int CONTROL_PIP = 33554689;
    private static final String TAG = CmdPool.class.getSimpleName();
    private static final HashMap<AvmManager.Command, CmdBean> cmdMap;

    static {
        HashMap<AvmManager.Command, CmdBean> map = new HashMap<>();
        cmdMap = map;
        map.put(AvmManager.Default.NONE, new CmdBuilder().build());
        cmdMap.put(AvmManager.State.START, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.State.START.getNum()}).build());
        cmdMap.put(AvmManager.State.STOP, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.State.STOP.getNum()}).build());
        cmdMap.put(AvmManager.Back.DEFAULT, new CmdBuilder().setId(33558528).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Back.DEFAULT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Calibration.START, new CmdBuilder().setId(AvmManagerImpl.AVM_CALIBRATION_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Calibration.START.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Calibration.STOP, new CmdBuilder().setId(AvmManagerImpl.AVM_CALIBRATION_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Calibration.STOP.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Kill.DEFAULT, new CmdBuilder().setId(AvmManagerImpl.AVM_CALIBRATION_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Kill.DEFAULT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Orientation.HORIZONTAL, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Orientation.HORIZONTAL.getNum()}).build());
        cmdMap.put(AvmManager.Orientation.VERTICAL, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Orientation.VERTICAL.getNum()}).build());
        cmdMap.put(AvmManager.Theme.LIGHT, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Theme.LIGHT.getNum()}).build());
        cmdMap.put(AvmManager.Theme.DARK, new CmdBuilder().setId(AvmManagerImpl.AVM_COMMAND_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{0, 0, 0, AvmManager.Theme.DARK.getNum()}).build());
        cmdMap.put(AvmManager.Language.CHINESE, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.CHINESE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.ENGLISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.ENGLISH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.GERMAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.GERMAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.SPANISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.SPANISH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.SPANISH_AMERICA, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.SPANISH_AMERICA.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.PORTUGUESE_BRAZIL, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.PORTUGUESE_BRAZIL.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.FRANCH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.FRANCH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.TURKISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.TURKISH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.PORTUGUESE, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.PORTUGUESE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.NUOWAY, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.NUOWAY.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.IVRIT, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.IVRIT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.SWEDEN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.SWEDEN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.UZBEKISTAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.UZBEKISTAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.RUSSIAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.RUSSIAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.ITALIN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.ITALIN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.THAI, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.THAI.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.DANISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.DANISH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.FINNISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.FINNISH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.DUTCH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.DUTCH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.HUNGARIAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.HUNGARIAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.GREEK, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.GREEK.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.ARABIC, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.ARABIC.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.POLISH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.POLISH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.JAPANESE, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.JAPANESE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.CZECH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.CZECH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.TRADITIONAL_CHINESE, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.TRADITIONAL_CHINESE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.MALAY, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.MALAY.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.BAHASA_INDONESIA, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.BAHASA_INDONESIA.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.VIETNAMESE, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.VIETNAMESE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.ROMANIAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.ROMANIAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.KOREAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.KOREAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.SLOVAK, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.SLOVAK.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.CROATIAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.CROATIAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.CATALAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.CATALAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.KAZAKH, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.KAZAKH.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.ENGLISH_AUSTRALIA, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.ENGLISH_AUSTRALIA.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.HINDI, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.HINDI.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.BULGARIAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.BULGARIAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.UKRAINIAN, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.UKRAINIAN.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.Language.FRENCH_CANADA, new CmdBuilder().setId(100663296).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.Language.FRENCH_CANADA.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.START_AVM, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.START_AVM.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.STOP_AVM, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.STOP_AVM.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.MODE_3D, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MODE_3D.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.MODE_2D, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MODE_2D.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.TRANSPORT_START, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.TRANSPORT_START.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.TRANSPOR_STOP, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.TRANSPOR_STOP.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.LEFT_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.LEFT_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.RIGHT_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.RIGHT_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.FRONT_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.FRONT_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.REAR_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.REAR_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.FRONT_WIDE_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.FRONT_WIDE_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.REAR_WIDE_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.REAR_WIDE_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.MULTI_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MULTI_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.MULTI_FRONT_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MULTI_FRONT_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.MULTI_REAR_MODE, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.MULTI_REAR_MODE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.STOP_RADAR, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.STOP_RADAR.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.START_FOREHOLD, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.START_FOREHOLD.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.STOP_FOREHOLD, new CmdBuilder().setId(AvmManagerImpl.AVM_VR_CODE).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.VoiceEvent.STOP_FOREHOLD.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.START_PIP, new CmdBuilder().setId(CONTROL_PIP).setSrcId(9).setTargetId(9).setValue(new int[]{1, 0, 0, 0}).build());
        cmdMap.put(AvmManager.VoiceEvent.STOP_PIP, new CmdBuilder().setId(CONTROL_PIP).setSrcId(9).setTargetId(9).setValue(new int[]{0, 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.NONE, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.NONE.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_FRONT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_REAR.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_LEFT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_LEFT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_RIGHT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_RIGHT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_WIDE_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_WIDE_FRONT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_WIDE_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_WIDE_REAR.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_MULTI_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_MULTI_FRONT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_MULTI_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_MULTI_REAR.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_2D_MULTI_VIEW, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_2D_MULTI_VIEW.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_3D_FRONT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_FRONT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_3D_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_REAR.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_3D_LEFT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_LEFT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_3D_RIGHT, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_RIGHT.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.VIEW_3D_FOREHOLD, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.VIEW_3D_FOREHOLD.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.RADAR_FLOATING, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.RADAR_FLOATING.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.TRACTION_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.TRACTION_REAR.getNum(), 0, 0, 0}).build());
        cmdMap.put(AvmManager.ViewMode.TRACTION_WIDE_REAR, new CmdBuilder().setId(196608).setSrcId(9).setTargetId(7).setValue(new int[]{AvmManager.ViewMode.TRACTION_WIDE_REAR.getNum(), 0, 0, 0}).build());
    }

    public static CmdBean getCmd(AvmManager.Command command) {
        if (!cmdMap.containsKey(command)) {
            LogManager.loge(TAG, "Command ERROR ", new Throwable());
            return cmdMap.get(AvmManager.Default.NONE);
        }
        return cmdMap.get(command);
    }
}
