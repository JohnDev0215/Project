package com.ts.avm.sdk.d;

import com.ts.avm.bean.CmdBean;
import com.ts.avm.sdk.AvmManager;
import java.util.HashMap;

/* JADX INFO: compiled from: CmdPool.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final String f2628a = "b";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final HashMap<AvmManager.d, CmdBean> f2629b;

    static {
        HashMap<AvmManager.d, CmdBean> map = new HashMap<>();
        f2629b = map;
        map.put(AvmManager.Default.NONE, new a().a());
        AvmManager.State state = AvmManager.State.START;
        map.put(state, new a().b(33554432).c(9).d(7).e(new int[]{0, 0, 0, state.getNum()}).a());
        AvmManager.State state2 = AvmManager.State.STOP;
        map.put(state2, new a().b(33554432).c(9).d(7).e(new int[]{0, 0, 0, state2.getNum()}).a());
        AvmManager.Back back = AvmManager.Back.DEFAULT;
        map.put(back, new a().b(33558528).c(9).d(7).e(new int[]{back.getNum(), 0, 0, 0}).a());
        AvmManager.Calibration calibration = AvmManager.Calibration.START;
        map.put(calibration, new a().b(67108864).c(9).d(7).e(new int[]{calibration.getNum(), 0, 0, 0}).a());
        AvmManager.Calibration calibration2 = AvmManager.Calibration.STOP;
        map.put(calibration2, new a().b(67108864).c(9).d(7).e(new int[]{calibration2.getNum(), 0, 0, 0}).a());
        AvmManager.Kill kill = AvmManager.Kill.DEFAULT;
        map.put(kill, new a().b(67108864).c(9).d(7).e(new int[]{kill.getNum(), 0, 0, 0}).a());
        AvmManager.Orientation orientation = AvmManager.Orientation.HORIZONTAL;
        map.put(orientation, new a().b(33554432).c(9).d(7).e(new int[]{0, 0, 0, orientation.getNum()}).a());
        AvmManager.Orientation orientation2 = AvmManager.Orientation.VERTICAL;
        map.put(orientation2, new a().b(33554432).c(9).d(7).e(new int[]{0, 0, 0, orientation2.getNum()}).a());
        AvmManager.Theme theme = AvmManager.Theme.LIGHT;
        map.put(theme, new a().b(33554432).c(9).d(7).e(new int[]{0, 0, 0, theme.getNum()}).a());
        AvmManager.Theme theme2 = AvmManager.Theme.DARK;
        map.put(theme2, new a().b(33554432).c(9).d(7).e(new int[]{0, 0, 0, theme2.getNum()}).a());
        AvmManager.Language language = AvmManager.Language.CHINESE;
        map.put(language, new a().b(100663296).c(9).d(7).e(new int[]{language.getNum(), 0, 0, 0}).a());
        AvmManager.Language language2 = AvmManager.Language.ENGLISH;
        map.put(language2, new a().b(100663296).c(9).d(7).e(new int[]{language2.getNum(), 0, 0, 0}).a());
        AvmManager.Language language3 = AvmManager.Language.OTHER_1;
        map.put(language3, new a().b(100663296).c(9).d(7).e(new int[]{language3.getNum(), 0, 0, 0}).a());
        AvmManager.Language language4 = AvmManager.Language.OTHER_2;
        map.put(language4, new a().b(100663296).c(9).d(7).e(new int[]{language4.getNum(), 0, 0, 0}).a());
        AvmManager.Language language5 = AvmManager.Language.OTHER_3;
        map.put(language5, new a().b(100663296).c(9).d(7).e(new int[]{language5.getNum(), 0, 0, 0}).a());
        AvmManager.Language language6 = AvmManager.Language.OTHER_4;
        map.put(language6, new a().b(100663296).c(9).d(7).e(new int[]{language6.getNum(), 0, 0, 0}).a());
        AvmManager.Language language7 = AvmManager.Language.OTHER_5;
        map.put(language7, new a().b(100663296).c(9).d(7).e(new int[]{language7.getNum(), 0, 0, 0}).a());
        AvmManager.Language language8 = AvmManager.Language.OTHER_6;
        map.put(language8, new a().b(100663296).c(9).d(7).e(new int[]{language8.getNum(), 0, 0, 0}).a());
        AvmManager.Language language9 = AvmManager.Language.OTHER_7;
        map.put(language9, new a().b(100663296).c(9).d(7).e(new int[]{language9.getNum(), 0, 0, 0}).a());
        AvmManager.Language language10 = AvmManager.Language.OTHER_8;
        map.put(language10, new a().b(100663296).c(9).d(7).e(new int[]{language10.getNum(), 0, 0, 0}).a());
        AvmManager.Language language11 = AvmManager.Language.OTHER_9;
        map.put(language11, new a().b(100663296).c(9).d(7).e(new int[]{language11.getNum(), 0, 0, 0}).a());
        AvmManager.Language language12 = AvmManager.Language.OTHER_10;
        map.put(language12, new a().b(100663296).c(9).d(7).e(new int[]{language12.getNum(), 0, 0, 0}).a());
        AvmManager.Language language13 = AvmManager.Language.OTHER_11;
        map.put(language13, new a().b(100663296).c(9).d(7).e(new int[]{language13.getNum(), 0, 0, 0}).a());
        AvmManager.Language language14 = AvmManager.Language.OTHER_12;
        map.put(language14, new a().b(100663296).c(9).d(7).e(new int[]{language14.getNum(), 0, 0, 0}).a());
        AvmManager.Language language15 = AvmManager.Language.OTHER_13;
        map.put(language15, new a().b(100663296).c(9).d(7).e(new int[]{language15.getNum(), 0, 0, 0}).a());
        AvmManager.Language language16 = AvmManager.Language.OTHER_14;
        map.put(language16, new a().b(100663296).c(9).d(7).e(new int[]{language16.getNum(), 0, 0, 0}).a());
        AvmManager.Language language17 = AvmManager.Language.OTHER_15;
        map.put(language17, new a().b(100663296).c(9).d(7).e(new int[]{language17.getNum(), 0, 0, 0}).a());
        AvmManager.Language language18 = AvmManager.Language.OTHER_16;
        map.put(language18, new a().b(100663296).c(9).d(7).e(new int[]{language18.getNum(), 0, 0, 0}).a());
        AvmManager.Language language19 = AvmManager.Language.OTHER_17;
        map.put(language19, new a().b(100663296).c(9).d(7).e(new int[]{language19.getNum(), 0, 0, 0}).a());
        AvmManager.Language language20 = AvmManager.Language.OTHER_18;
        map.put(language20, new a().b(100663296).c(9).d(7).e(new int[]{language20.getNum(), 0, 0, 0}).a());
        AvmManager.Language language21 = AvmManager.Language.OTHER_19;
        map.put(language21, new a().b(100663296).c(9).d(7).e(new int[]{language21.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent = AvmManager.VoiceEvent.START_AVM;
        map.put(voiceEvent, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent2 = AvmManager.VoiceEvent.STOP_AVM;
        map.put(voiceEvent2, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent2.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent3 = AvmManager.VoiceEvent.MODE_3D;
        map.put(voiceEvent3, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent3.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent4 = AvmManager.VoiceEvent.MODE_2D;
        map.put(voiceEvent4, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent4.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent5 = AvmManager.VoiceEvent.TRANSPORT_START;
        map.put(voiceEvent5, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent5.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent6 = AvmManager.VoiceEvent.TRANSPOR_STOP;
        map.put(voiceEvent6, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent6.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent7 = AvmManager.VoiceEvent.LEFT_MODE;
        map.put(voiceEvent7, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent7.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent8 = AvmManager.VoiceEvent.RIGHT_MODE;
        map.put(voiceEvent8, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent8.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent9 = AvmManager.VoiceEvent.FRONT_MODE;
        map.put(voiceEvent9, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent9.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent10 = AvmManager.VoiceEvent.REAR_MODE;
        map.put(voiceEvent10, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent10.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent11 = AvmManager.VoiceEvent.FRONT_WIDE_MODE;
        map.put(voiceEvent11, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent11.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent12 = AvmManager.VoiceEvent.REAR_WIDE_MODE;
        map.put(voiceEvent12, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent12.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent13 = AvmManager.VoiceEvent.MULTI_MODE;
        map.put(voiceEvent13, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent13.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent14 = AvmManager.VoiceEvent.MULTI_FRONT_MODE;
        map.put(voiceEvent14, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent14.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent15 = AvmManager.VoiceEvent.MULTI_REAR_MODE;
        map.put(voiceEvent15, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent15.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent16 = AvmManager.VoiceEvent.STOP_RADAR;
        map.put(voiceEvent16, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent16.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent17 = AvmManager.VoiceEvent.START_FOREHOLD;
        map.put(voiceEvent17, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent17.getNum(), 0, 0, 0}).a());
        AvmManager.VoiceEvent voiceEvent18 = AvmManager.VoiceEvent.STOP_FOREHOLD;
        map.put(voiceEvent18, new a().b(16777216).c(9).d(7).e(new int[]{voiceEvent18.getNum(), 0, 0, 0}).a());
        HashMap<AvmManager.d, CmdBean> map2 = f2629b;
        AvmManager.ViewMode viewMode = AvmManager.ViewMode.NONE;
        map2.put(viewMode, new a().b(196608).c(9).d(7).e(new int[]{viewMode.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode2 = AvmManager.ViewMode.VIEW_2D_FRONT;
        map2.put(viewMode2, new a().b(196608).c(9).d(7).e(new int[]{viewMode2.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode3 = AvmManager.ViewMode.VIEW_2D_REAR;
        map2.put(viewMode3, new a().b(196608).c(9).d(7).e(new int[]{viewMode3.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode4 = AvmManager.ViewMode.VIEW_2D_LEFT;
        map2.put(viewMode4, new a().b(196608).c(9).d(7).e(new int[]{viewMode4.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode5 = AvmManager.ViewMode.VIEW_2D_RIGHT;
        map2.put(viewMode5, new a().b(196608).c(9).d(7).e(new int[]{viewMode5.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode6 = AvmManager.ViewMode.VIEW_2D_WIDE_FRONT;
        map2.put(viewMode6, new a().b(196608).c(9).d(7).e(new int[]{viewMode6.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode7 = AvmManager.ViewMode.VIEW_2D_WIDE_REAR;
        map2.put(viewMode7, new a().b(196608).c(9).d(7).e(new int[]{viewMode7.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode8 = AvmManager.ViewMode.VIEW_2D_MULTI_FRONT;
        map2.put(viewMode8, new a().b(196608).c(9).d(7).e(new int[]{viewMode8.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode9 = AvmManager.ViewMode.VIEW_2D_MULTI_REAR;
        map2.put(viewMode9, new a().b(196608).c(9).d(7).e(new int[]{viewMode9.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode10 = AvmManager.ViewMode.VIEW_2D_MULTI_VIEW;
        map2.put(viewMode10, new a().b(196608).c(9).d(7).e(new int[]{viewMode10.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode11 = AvmManager.ViewMode.VIEW_3D_FRONT;
        map2.put(viewMode11, new a().b(196608).c(9).d(7).e(new int[]{viewMode11.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode12 = AvmManager.ViewMode.VIEW_3D_REAR;
        map2.put(viewMode12, new a().b(196608).c(9).d(7).e(new int[]{viewMode12.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode13 = AvmManager.ViewMode.VIEW_3D_LEFT;
        map2.put(viewMode13, new a().b(196608).c(9).d(7).e(new int[]{viewMode13.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode14 = AvmManager.ViewMode.VIEW_3D_RIGHT;
        map2.put(viewMode14, new a().b(196608).c(9).d(7).e(new int[]{viewMode14.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode15 = AvmManager.ViewMode.VIEW_3D_FOREHOLD;
        map2.put(viewMode15, new a().b(196608).c(9).d(7).e(new int[]{viewMode15.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode16 = AvmManager.ViewMode.RADAR_FLOATING;
        map2.put(viewMode16, new a().b(196608).c(9).d(7).e(new int[]{viewMode16.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode17 = AvmManager.ViewMode.TRACTION_REAR;
        map2.put(viewMode17, new a().b(196608).c(9).d(7).e(new int[]{viewMode17.getNum(), 0, 0, 0}).a());
        AvmManager.ViewMode viewMode18 = AvmManager.ViewMode.TRACTION_WIDE_REAR;
        map2.put(viewMode18, new a().b(196608).c(9).d(7).e(new int[]{viewMode18.getNum(), 0, 0, 0}).a());
    }

    public static CmdBean a(AvmManager.d dVar) {
        HashMap<AvmManager.d, CmdBean> map = f2629b;
        if (map.containsKey(dVar)) {
            return map.get(dVar);
        }
        com.ts.avm.sdk.c.a.d(f2628a, "Command ERROR ", new Throwable());
        return map.get(AvmManager.Default.NONE);
    }
}
