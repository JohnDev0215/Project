package com.loostone.libserver.version2.bean;

import java.util.ArrayList;
import java.util.List;
import kotlin.jvm.internal.h;

/* JADX INFO: loaded from: classes.dex */
public final class UpgradeData extends BaseResponseData<UpgradeList> {

    public final class UpgradeItem {
        private String fileMd5;
        private String fileUrl;
        public final /* synthetic */ UpgradeData this$0;
        private int versionCode;
        private String versionDescribe;

        public UpgradeItem(UpgradeData upgradeData) {
            h.d(upgradeData, "this$0");
            this.this$0 = upgradeData;
            this.versionDescribe = "no describe";
            this.fileMd5 = "no md5";
        }

        public final String getFileMd5() {
            return this.fileMd5;
        }

        public final String getFileUrl() {
            return this.fileUrl;
        }

        public final int getVersionCode() {
            return this.versionCode;
        }

        public final String getVersionDescribe() {
            return this.versionDescribe;
        }

        public final void setFileMd5(String str) {
            h.d(str, "<set-?>");
            this.fileMd5 = str;
        }

        public final void setFileUrl(String str) {
            this.fileUrl = str;
        }

        public final void setVersionCode(int i) {
            this.versionCode = i;
        }

        public final void setVersionDescribe(String str) {
            h.d(str, "<set-?>");
            this.versionDescribe = str;
        }
    }

    public final class UpgradeList {
        public final /* synthetic */ UpgradeData this$0;
        private List<UpgradeItem> versions;

        public UpgradeList(UpgradeData upgradeData) {
            h.d(upgradeData, "this$0");
            this.this$0 = upgradeData;
            this.versions = new ArrayList();
        }

        public final List<UpgradeItem> getVersions() {
            return this.versions;
        }

        public final void setVersions(List<UpgradeItem> list) {
            h.d(list, "<set-?>");
            this.versions = list;
        }
    }
}
