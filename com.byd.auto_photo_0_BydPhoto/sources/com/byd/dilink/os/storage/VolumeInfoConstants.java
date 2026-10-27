package com.byd.dilink.os.storage;

/* JADX INFO: loaded from: classes.dex */
public class VolumeInfoConstants {
    public static final int state = 0;

    public static class MountFlag {
        public static final int MOUNT_FLAG_PRIMARY = 1;
        public static final int MOUNT_FLAG_SDCARD = 4;
        public static final int MOUNT_FLAG_USB = 8;
        public static final int MOUNT_FLAG_VISIBLE = 2;
    }

    public static class State {
        public static final int STATE_BAD_REMOVAL = 8;
        public static final int STATE_CHECKING = 1;
        public static final int STATE_EJECTING = 5;
        public static final int STATE_FORMATTING = 4;
        public static final int STATE_MOUNTED = 2;
        public static final int STATE_MOUNTED_READ_ONLY = 3;
        public static final int STATE_REMOVED = 7;
        public static final int STATE_UNMOUNTABLE = 6;
        public static final int STATE_UNMOUNTED = 0;
    }

    public static class Type {
        public static final int TYPE_ASEC = 3;
        public static final int TYPE_EMULATED = 2;
        public static final int TYPE_OBB = 4;
        public static final int TYPE_PRIVATE = 1;
        public static final int TYPE_PUBLIC = 0;
        public static final int TYPE_STUB = 5;
    }
}
