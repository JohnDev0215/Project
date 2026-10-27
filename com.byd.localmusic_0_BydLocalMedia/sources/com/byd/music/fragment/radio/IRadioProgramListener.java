package com.byd.music.fragment.radio;

import com.android.car.radio.bands.ProgramType;
import com.byd.music.beans.RadioDBBean;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public interface IRadioProgramListener {
    void onRadioProgramUpdated(ProgramType programType, List<RadioDBBean> list);
}
