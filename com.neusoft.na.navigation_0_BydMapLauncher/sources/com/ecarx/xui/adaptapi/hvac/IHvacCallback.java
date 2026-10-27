package com.ecarx.xui.adaptapi.hvac;

/* JADX INFO: loaded from: classes2.dex */
public interface IHvacCallback {
    void onAQSStateChanged(IAQSState iAQSState);

    void onHvacAvailable(boolean z);

    void onHvacWarning(IHvacReminder iHvacReminder);

    void onStateChanged(IHvacState iHvacState);
}
