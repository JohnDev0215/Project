package cn.hutool.core.io;

import java.io.BufferedInputStream;
import java.io.File;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: FileUtil.java */
/* JADX INFO: loaded from: classes.dex */
public class d extends cn.hutool.core.io.i.a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final String f2925a = File.separator;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final String f2926b = File.pathSeparator;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final Pattern f2927c = Pattern.compile("^[a-zA-Z]:([/\\\\].*)?");

    public static BufferedInputStream a(File file) throws IORuntimeException {
        return e.i(e.j(file));
    }
}
