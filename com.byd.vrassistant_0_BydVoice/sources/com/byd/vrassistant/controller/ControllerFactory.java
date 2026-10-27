package com.byd.vrassistant.controller;

import cerence.ark.assistant.api.controller.IHostController;
import com.byd.vrassistant.customizedimpl.CustomController;
import com.byd.vrassistant.manager.DataManager;

/* JADX INFO: loaded from: classes.dex */
public class ControllerFactory {

    /* JADX INFO: renamed from: com.byd.vrassistant.controller.ControllerFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$byd$vrassistant$controller$DemoType;

        static {
            int[] iArr = new int[DemoType.values().length];
            $SwitchMap$com$byd$vrassistant$controller$DemoType = iArr;
            try {
                iArr[DemoType.custom.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public IHostController getController() {
        if (AnonymousClass1.$SwitchMap$com$byd$vrassistant$controller$DemoType[DataManager.get().getType().ordinal()] == 1) {
            return new CustomController();
        }
        return new CustomController();
    }
}
