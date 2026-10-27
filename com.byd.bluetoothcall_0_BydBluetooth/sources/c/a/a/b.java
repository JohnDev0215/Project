package c.a.a;

import com.byd.constant.StateConstant$SCO_STATE;
import com.byd.info.BtCallInfo;
import java.util.List;

/* JADX INFO: compiled from: UIListener_Call.java */
/* JADX INFO: loaded from: classes.dex */
public interface b {
    void BT_Card_Slides_up();

    void BlueSwitch(int i);

    void CallState_Change(List<BtCallInfo> list);

    void Call_Activity_is_Top(boolean z);

    void Database_contact_already_exists(boolean z);

    void InvalidCallNumber(String str);

    void NewTopTaskInfo(String str);

    void Update_Call_Channel(StateConstant$SCO_STATE stateConstant$SCO_STATE);

    void Update_Call_Time(BtCallInfo btCallInfo);

    void WidgetCardShow(boolean z);

    void WidgetKeyBoardShow(boolean z);

    void endCall(String str);
}
