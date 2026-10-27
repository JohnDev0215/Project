package org.apache.tools.ant.util.regexp;

/* JADX INFO: loaded from: classes.dex */
public class RegexpUtil {
    public static boolean hasFlag(int options, int flag) {
        return (options & flag) > 0;
    }

    public static int removeFlag(int options, int flag) {
        return ((-1) - flag) & options;
    }
}
