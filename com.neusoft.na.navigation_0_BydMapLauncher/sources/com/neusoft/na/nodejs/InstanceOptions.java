package com.neusoft.na.nodejs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class InstanceOptions {
    final List<String> args;
    final boolean captureConsole;
    final boolean interactive;
    final String logtag;
    final boolean runFromArgs;

    public static class Builder {
        private List<String> mArgs;
        private String mLogtag;
        private boolean mInteractive = false;
        private boolean mCaptureConsole = false;
        private boolean mRunFromArgs = false;

        public Builder logtag(String str) {
            this.mLogtag = str;
            return this;
        }

        public Builder interactive() {
            this.mInteractive = true;
            return this;
        }

        public Builder interactive(boolean z) {
            this.mInteractive = z;
            return this;
        }

        public Builder captureConsole() {
            this.mCaptureConsole = true;
            return this;
        }

        public Builder captureConsole(boolean z) {
            this.mCaptureConsole = z;
            return this;
        }

        public Builder runFromArgs() {
            this.mRunFromArgs = true;
            return this;
        }

        public Builder runFromArgs(boolean z) {
            this.mRunFromArgs = z;
            return this;
        }

        public Builder args(List<String> list) {
            this.mArgs = list;
            return this;
        }

        public Builder args(String... strArr) {
            this.mArgs = Arrays.asList(strArr);
            return this;
        }

        public InstanceOptions build() {
            String str = this.mLogtag;
            boolean z = this.mInteractive;
            boolean z2 = this.mCaptureConsole;
            boolean z3 = this.mRunFromArgs;
            List arrayList = this.mArgs;
            if (arrayList == null) {
                arrayList = new ArrayList();
            }
            return new InstanceOptions(str, z, z2, z3, arrayList);
        }
    }

    public InstanceOptions(String str, boolean z, boolean z2, boolean z3, List<String> list) {
        this.logtag = str;
        this.interactive = z;
        this.captureConsole = z2;
        this.runFromArgs = z3;
        this.args = list;
    }
}
