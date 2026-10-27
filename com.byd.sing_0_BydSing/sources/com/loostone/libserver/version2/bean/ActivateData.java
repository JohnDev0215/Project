package com.loostone.libserver.version2.bean;

import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class ActivateData extends BaseResponseData<ActivateItem> {

    public final class ActivateItem {
        private String deviceUuid;
        private String modelUuid;
        private String modelVersion;
        public final /* synthetic */ ActivateData this$0;

        public ActivateItem(ActivateData activateData) {
            h.d(activateData, "this$0");
            this.this$0 = activateData;
        }

        public final String getDeviceUuid() {
            return this.deviceUuid;
        }

        public final String getModelUuid() {
            return this.modelUuid;
        }

        public final String getModelVersion() {
            return this.modelVersion;
        }

        public final void setDeviceUuid(String str) {
            this.deviceUuid = str;
        }

        public final void setModelUuid(String str) {
            this.modelUuid = str;
        }

        public final void setModelVersion(String str) {
            this.modelVersion = str;
        }
    }
}
