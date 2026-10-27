package com.blankj.utilcode.util;

import android.content.ClipData;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.IntRange;
import androidx.annotation.RequiresApi;
import androidx.collection.SimpleArrayMap;
import com.alibaba.android.arouter.utils.Consts;
import com.blankj.utilcode.util.f;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Formatter;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/* JADX INFO: loaded from: classes2.dex */
public final class LogUtils {
    public static final int A = 7;
    private static final String ARGS = "args";
    private static final String BOTTOM_BORDER = "└────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String BOTTOM_CORNER = "└";
    public static final int D = 3;
    public static final int E = 6;
    private static final int FILE = 16;
    public static final int I = 4;
    private static final int JSON = 32;
    private static final String LEFT_BORDER = "│ ";
    private static final int MAX_LEN = 1100;
    private static final String MIDDLE_BORDER = "├┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String MIDDLE_CORNER = "├";
    private static final String MIDDLE_DIVIDER = "┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄┄";
    private static final String NOTHING = "log nothing";
    private static final String NULL = "null";
    private static final String PLACEHOLDER = " ";
    private static final String SIDE_DIVIDER = "────────────────────────────────────────────────────────";
    private static final String TOP_BORDER = "┌────────────────────────────────────────────────────────────────────────────────────────────────────────────────";
    private static final String TOP_CORNER = "┌";
    public static final int V = 2;
    public static final int W = 5;
    private static final int XML = 48;
    private static SimpleDateFormat simpleDateFormat;
    private static final char[] T = {'V', 'D', 'I', 'W', 'E', 'A'};
    private static final String FILE_SEP = System.getProperty("file.separator");
    private static final String LINE_SEP = System.getProperty("line.separator");
    private static final Config CONFIG = new Config(null);
    private static final ExecutorService EXECUTOR = Executors.newSingleThreadExecutor();
    private static final SimpleArrayMap<Class, IFormatter> I_FORMATTER_MAP = new SimpleArrayMap<>();

    public static final class Config {
        private int mConsoleFilter;
        private String mDefaultDir;
        private String mDir;
        private String mFileExtension;
        private int mFileFilter;
        private f.a mFileHead;
        private String mFilePrefix;
        private IFileWriter mFileWriter;
        private String mGlobalTag;
        private boolean mLog2ConsoleSwitch;
        private boolean mLog2FileSwitch;
        private boolean mLogBorderSwitch;
        private boolean mLogHeadSwitch;
        private boolean mLogSwitch;
        private OnConsoleOutputListener mOnConsoleOutputListener;
        private OnFileOutputListener mOnFileOutputListener;
        private String mProcessName;
        private int mSaveDays;
        private boolean mSingleTagSwitch;
        private int mStackDeep;
        private int mStackOffset;
        private boolean mTagIsSpace;

        public /* synthetic */ Config(a aVar) {
            this();
        }

        public final Config addFileExtraHead(Map<String, String> map) {
            this.mFileHead.a(map);
            return this;
        }

        public final <T> Config addFormatter(IFormatter<T> iFormatter) {
            if (iFormatter != null) {
                LogUtils.I_FORMATTER_MAP.put(LogUtils.getTypeClassFromParadigm(iFormatter), iFormatter);
            }
            return this;
        }

        public final char getConsoleFilter() {
            return LogUtils.T[this.mConsoleFilter - 2];
        }

        public final String getDefaultDir() {
            return this.mDefaultDir;
        }

        public final String getDir() {
            String str = this.mDir;
            return str == null ? this.mDefaultDir : str;
        }

        public final String getFileExtension() {
            return this.mFileExtension;
        }

        public final char getFileFilter() {
            return LogUtils.T[this.mFileFilter - 2];
        }

        public final String getFilePrefix() {
            return this.mFilePrefix;
        }

        public final String getGlobalTag() {
            return StringUtils.isSpace(this.mGlobalTag) ? "" : this.mGlobalTag;
        }

        public final String getProcessName() {
            String str = this.mProcessName;
            return str == null ? "" : str.replace(":", "_");
        }

        public final int getSaveDays() {
            return this.mSaveDays;
        }

        public final int getStackDeep() {
            return this.mStackDeep;
        }

        public final int getStackOffset() {
            return this.mStackOffset;
        }

        public final boolean haveSetOnConsoleOutputListener() {
            return this.mOnConsoleOutputListener != null;
        }

        public final boolean haveSetOnFileOutputListener() {
            return this.mOnFileOutputListener != null;
        }

        public final boolean isLog2ConsoleSwitch() {
            return this.mLog2ConsoleSwitch;
        }

        public final boolean isLog2FileSwitch() {
            return this.mLog2FileSwitch;
        }

        public final boolean isLogBorderSwitch() {
            return this.mLogBorderSwitch;
        }

        public final boolean isLogHeadSwitch() {
            return this.mLogHeadSwitch;
        }

        public final boolean isLogSwitch() {
            return this.mLogSwitch;
        }

        public final boolean isSingleTagSwitch() {
            return this.mSingleTagSwitch;
        }

        public final Config setBorderSwitch(boolean z) {
            this.mLogBorderSwitch = z;
            return this;
        }

        public final Config setConsoleFilter(int i) {
            this.mConsoleFilter = i;
            return this;
        }

        public final Config setConsoleSwitch(boolean z) {
            this.mLog2ConsoleSwitch = z;
            return this;
        }

        public final Config setDir(String str) {
            if (StringUtils.isSpace(str)) {
                this.mDir = null;
            } else {
                if (!str.endsWith(LogUtils.FILE_SEP)) {
                    StringBuilder sbK = spi.c.k(str);
                    sbK.append(LogUtils.FILE_SEP);
                    str = sbK.toString();
                }
                this.mDir = str;
            }
            return this;
        }

        public final Config setFileExtension(String str) {
            if (StringUtils.isSpace(str)) {
                this.mFileExtension = ".txt";
            } else if (str.startsWith(Consts.DOT)) {
                this.mFileExtension = str;
            } else {
                this.mFileExtension = spi.c.f(Consts.DOT, str);
            }
            return this;
        }

        public final Config setFileFilter(int i) {
            this.mFileFilter = i;
            return this;
        }

        public final Config setFilePrefix(String str) {
            if (StringUtils.isSpace(str)) {
                this.mFilePrefix = "util";
            } else {
                this.mFilePrefix = str;
            }
            return this;
        }

        public final Config setFileWriter(IFileWriter iFileWriter) {
            this.mFileWriter = iFileWriter;
            return this;
        }

        public final Config setGlobalTag(String str) {
            if (StringUtils.isSpace(str)) {
                this.mGlobalTag = "";
                this.mTagIsSpace = true;
            } else {
                this.mGlobalTag = str;
                this.mTagIsSpace = false;
            }
            return this;
        }

        public final Config setLog2FileSwitch(boolean z) {
            this.mLog2FileSwitch = z;
            return this;
        }

        public final Config setLogHeadSwitch(boolean z) {
            this.mLogHeadSwitch = z;
            return this;
        }

        public final Config setLogSwitch(boolean z) {
            this.mLogSwitch = z;
            return this;
        }

        public final Config setOnConsoleOutputListener(OnConsoleOutputListener onConsoleOutputListener) {
            this.mOnConsoleOutputListener = onConsoleOutputListener;
            return this;
        }

        public final Config setOnFileOutputListener(OnFileOutputListener onFileOutputListener) {
            this.mOnFileOutputListener = onFileOutputListener;
            return this;
        }

        public final Config setSaveDays(@IntRange(from = 1) int i) {
            this.mSaveDays = i;
            return this;
        }

        public final Config setSingleTagSwitch(boolean z) {
            this.mSingleTagSwitch = z;
            return this;
        }

        public final Config setStackDeep(@IntRange(from = 1) int i) {
            this.mStackDeep = i;
            return this;
        }

        public final Config setStackOffset(@IntRange(from = 0) int i) {
            this.mStackOffset = i;
            return this;
        }

        public String toString() {
            StringBuilder sbK = spi.c.k("process: ");
            sbK.append(getProcessName());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("logSwitch: ");
            sbK.append(isLogSwitch());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("consoleSwitch: ");
            sbK.append(isLog2ConsoleSwitch());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("tag: ");
            sbK.append(getGlobalTag().equals("") ? LogUtils.NULL : getGlobalTag());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("headSwitch: ");
            sbK.append(isLogHeadSwitch());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("fileSwitch: ");
            sbK.append(isLog2FileSwitch());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("dir: ");
            sbK.append(getDir());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("filePrefix: ");
            sbK.append(getFilePrefix());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("borderSwitch: ");
            sbK.append(isLogBorderSwitch());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("singleTagSwitch: ");
            sbK.append(isSingleTagSwitch());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("consoleFilter: ");
            sbK.append(getConsoleFilter());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("fileFilter: ");
            sbK.append(getFileFilter());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("stackDeep: ");
            sbK.append(getStackDeep());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("stackOffset: ");
            sbK.append(getStackOffset());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("saveDays: ");
            sbK.append(getSaveDays());
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("formatter: ");
            sbK.append(LogUtils.I_FORMATTER_MAP);
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("fileWriter: ");
            sbK.append(this.mFileWriter);
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("onConsoleOutputListener: ");
            sbK.append(this.mOnConsoleOutputListener);
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("onFileOutputListener: ");
            sbK.append(this.mOnFileOutputListener);
            sbK.append(LogUtils.LINE_SEP);
            sbK.append("fileExtraHeader: ");
            sbK.append(this.mFileHead.c());
            return sbK.toString();
        }

        private Config() {
            this.mFilePrefix = "util";
            this.mFileExtension = ".txt";
            this.mLogSwitch = true;
            this.mLog2ConsoleSwitch = true;
            this.mGlobalTag = "";
            this.mTagIsSpace = true;
            this.mLogHeadSwitch = true;
            this.mLog2FileSwitch = false;
            this.mLogBorderSwitch = true;
            this.mSingleTagSwitch = true;
            this.mConsoleFilter = 2;
            this.mFileFilter = 2;
            this.mStackDeep = 1;
            this.mStackOffset = 0;
            this.mSaveDays = -1;
            this.mProcessName = ProcessUtils.getCurrentProcessName();
            this.mFileHead = new f.a("Log");
            if (!SDCardUtils.isSDCardEnableByEnvironment() || Utils.getApp().getExternalFilesDir(null) == null) {
                this.mDefaultDir = Utils.getApp().getFilesDir() + LogUtils.FILE_SEP + "log" + LogUtils.FILE_SEP;
                return;
            }
            this.mDefaultDir = Utils.getApp().getExternalFilesDir(null) + LogUtils.FILE_SEP + "log" + LogUtils.FILE_SEP;
        }

        public final Config addFileExtraHead(String str, String str2) {
            f.a aVar = this.mFileHead;
            aVar.b(aVar.c, str, str2);
            return this;
        }

        public final Config setDir(File file) {
            String str;
            if (file == null) {
                str = null;
            } else {
                str = file.getAbsolutePath() + LogUtils.FILE_SEP;
            }
            this.mDir = str;
            return this;
        }
    }

    public interface IFileWriter {
        void write(String str, String str2);
    }

    public static abstract class IFormatter<T> {
        public abstract String format(T t);
    }

    public interface OnConsoleOutputListener {
        void onConsoleOutput(int i, String str, String str2);
    }

    public interface OnFileOutputListener {
        void onFileOutput(String str, String str2);
    }

    @Retention(RetentionPolicy.SOURCE)
    public @interface TYPE {
    }

    public static class a implements Runnable {
        public final /* synthetic */ int a;
        public final /* synthetic */ f b;
        public final /* synthetic */ String c;

        public a(int i, f fVar, String str) {
            this.a = i;
            this.b = fVar;
            this.c = str;
        }

        @Override // java.lang.Runnable
        public final void run() {
            LogUtils.print2File(this.a, this.b.a, this.b.c + this.c);
        }
    }

    public static class b implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return LogUtils.isMatchLogFileName(str);
        }
    }

    public static class c implements FilenameFilter {
        @Override // java.io.FilenameFilter
        public final boolean accept(File file, String str) {
            return LogUtils.isMatchLogFileName(str);
        }
    }

    public static class d implements Runnable {
        public final /* synthetic */ File a;

        public d(File file) {
            this.a = file;
        }

        @Override // java.lang.Runnable
        public final void run() {
            if (this.a.delete()) {
                return;
            }
            StringBuilder sbK = spi.c.k("delete ");
            sbK.append(this.a);
            sbK.append(" failed!");
            Log.e("LogUtils", sbK.toString());
        }
    }

    public static final class e {
        private e() {
        }

        public static String a(Bundle bundle) {
            Iterator<String> it = bundle.keySet().iterator();
            if (!it.hasNext()) {
                return "Bundle {}";
            }
            StringBuilder sb = new StringBuilder(128);
            sb.append("Bundle { ");
            while (true) {
                String next = it.next();
                Object obj = bundle.get(next);
                sb.append(next);
                sb.append('=');
                if (obj instanceof Bundle) {
                    sb.append(obj == bundle ? "(this Bundle)" : a((Bundle) obj));
                } else {
                    sb.append(LogUtils.formatObject(obj));
                }
                if (!it.hasNext()) {
                    sb.append(" }");
                    return sb.toString();
                }
                sb.append(',');
                sb.append(' ');
            }
        }

        @RequiresApi(api = 16)
        public static void b(ClipData clipData, StringBuilder sb) {
            ClipData.Item itemAt = clipData.getItemAt(0);
            if (itemAt == null) {
                sb.append("ClipData.Item {}");
                return;
            }
            sb.append("ClipData.Item { ");
            String htmlText = itemAt.getHtmlText();
            if (htmlText != null) {
                sb.append("H:");
                sb.append(htmlText);
                sb.append("}");
                return;
            }
            CharSequence text = itemAt.getText();
            if (text != null) {
                sb.append("T:");
                sb.append(text);
                sb.append("}");
                return;
            }
            Uri uri = itemAt.getUri();
            if (uri != null) {
                sb.append("U:");
                sb.append(uri);
                sb.append("}");
                return;
            }
            Intent intent = itemAt.getIntent();
            if (intent == null) {
                sb.append("NULL");
                sb.append("}");
            } else {
                sb.append("I:");
                sb.append(c(intent));
                sb.append("}");
            }
        }

        public static String c(Intent intent) {
            boolean z;
            StringBuilder sb = new StringBuilder(128);
            sb.append("Intent { ");
            String action = intent.getAction();
            boolean z2 = true;
            boolean z3 = false;
            if (action != null) {
                sb.append("act=");
                sb.append(action);
                z = false;
            } else {
                z = true;
            }
            Set<String> categories = intent.getCategories();
            if (categories != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("cat=[");
                for (String str : categories) {
                    if (!z2) {
                        sb.append(',');
                    }
                    sb.append(str);
                    z2 = false;
                }
                sb.append("]");
                z = false;
            }
            Uri data = intent.getData();
            if (data != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("dat=");
                sb.append(data);
                z = false;
            }
            String type = intent.getType();
            if (type != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("typ=");
                sb.append(type);
                z = false;
            }
            int flags = intent.getFlags();
            if (flags != 0) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("flg=0x");
                sb.append(Integer.toHexString(flags));
                z = false;
            }
            String str2 = intent.getPackage();
            if (str2 != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("pkg=");
                sb.append(str2);
                z = false;
            }
            ComponentName component = intent.getComponent();
            if (component != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("cmp=");
                sb.append(component.flattenToShortString());
                z = false;
            }
            Rect sourceBounds = intent.getSourceBounds();
            if (sourceBounds != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("bnds=");
                sb.append(sourceBounds.toShortString());
                z = false;
            }
            ClipData clipData = intent.getClipData();
            if (clipData != null) {
                if (!z) {
                    sb.append(' ');
                }
                b(clipData, sb);
                z = false;
            }
            Bundle extras = intent.getExtras();
            if (extras != null) {
                if (!z) {
                    sb.append(' ');
                }
                sb.append("extras={");
                sb.append(a(extras));
                sb.append('}');
            } else {
                z3 = z;
            }
            Intent selector = intent.getSelector();
            if (selector != null) {
                if (!z3) {
                    sb.append(' ');
                }
                sb.append("sel={");
                sb.append(selector == intent ? "(this Intent)" : c(selector));
                sb.append("}");
            }
            sb.append(" }");
            return sb.toString();
        }

        public static String d(Object obj, int i) {
            if (obj.getClass().isArray()) {
                if (obj instanceof Object[]) {
                    return Arrays.deepToString((Object[]) obj);
                }
                if (obj instanceof boolean[]) {
                    return Arrays.toString((boolean[]) obj);
                }
                if (obj instanceof byte[]) {
                    return Arrays.toString((byte[]) obj);
                }
                if (obj instanceof char[]) {
                    return Arrays.toString((char[]) obj);
                }
                if (obj instanceof double[]) {
                    return Arrays.toString((double[]) obj);
                }
                if (obj instanceof float[]) {
                    return Arrays.toString((float[]) obj);
                }
                if (obj instanceof int[]) {
                    return Arrays.toString((int[]) obj);
                }
                if (obj instanceof long[]) {
                    return Arrays.toString((long[]) obj);
                }
                if (obj instanceof short[]) {
                    return Arrays.toString((short[]) obj);
                }
                StringBuilder sbK = spi.c.k("Array has incompatible type: ");
                sbK.append(obj.getClass());
                throw new IllegalArgumentException(sbK.toString());
            }
            if (obj instanceof Throwable) {
                return ThrowableUtils.getFullStackTrace((Throwable) obj);
            }
            if (obj instanceof Bundle) {
                return a((Bundle) obj);
            }
            if (obj instanceof Intent) {
                return c((Intent) obj);
            }
            if (i == 32) {
                if (obj instanceof CharSequence) {
                    return JsonUtils.formatJson(obj.toString());
                }
                try {
                    return GsonUtils.getGson4LogUtils().h(obj);
                } catch (Throwable unused) {
                    return obj.toString();
                }
            }
            if (i != 48) {
                return obj.toString();
            }
            String string = obj.toString();
            try {
                StreamSource streamSource = new StreamSource(new StringReader(string));
                StreamResult streamResult = new StreamResult(new StringWriter());
                Transformer transformerNewTransformer = TransformerFactory.newInstance().newTransformer();
                transformerNewTransformer.setOutputProperty("indent", "yes");
                transformerNewTransformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
                transformerNewTransformer.transform(streamSource, streamResult);
                return streamResult.getWriter().toString().replaceFirst(">", ">" + LogUtils.LINE_SEP);
            } catch (Exception e) {
                e.printStackTrace();
                return string;
            }
        }
    }

    public static final class f {
        public String a;
        public String[] b;
        public String c;

        public f(String str, String[] strArr, String str2) {
            this.a = str;
            this.b = strArr;
            this.c = str2;
        }
    }

    private LogUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static void a(Object... objArr) {
        log(7, CONFIG.getGlobalTag(), objArr);
    }

    public static void aTag(String str, Object... objArr) {
        log(7, str, objArr);
    }

    private static boolean createOrExistsFile(String str, String str2) {
        File file = new File(str);
        if (file.exists()) {
            return file.isFile();
        }
        if (!FileUtils.createOrExistsDir(file.getParentFile())) {
            return false;
        }
        try {
            deleteDueLogs(str, str2);
            boolean zCreateNewFile = file.createNewFile();
            if (zCreateNewFile) {
                printDeviceInfo(str, str2);
            }
            return zCreateNewFile;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public static void d(Object... objArr) {
        log(3, CONFIG.getGlobalTag(), objArr);
    }

    public static void dTag(String str, Object... objArr) {
        log(3, str, objArr);
    }

    private static void deleteDueLogs(String str, String str2) {
        File[] fileArrListFiles;
        Config config = CONFIG;
        if (config.getSaveDays() > 0 && (fileArrListFiles = new File(str).getParentFile().listFiles(new c())) != null && fileArrListFiles.length > 0) {
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("yyyy_MM_dd", Locale.getDefault());
            try {
                long time = simpleDateFormat2.parse(str2).getTime() - (((long) config.getSaveDays()) * 86400000);
                for (File file : fileArrListFiles) {
                    String name = file.getName();
                    Objects.requireNonNull(name);
                    if (simpleDateFormat2.parse(findDate(name)).getTime() <= time) {
                        EXECUTOR.execute(new d(file));
                    }
                }
            } catch (ParseException e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void e(Object... objArr) {
        log(6, CONFIG.getGlobalTag(), objArr);
    }

    public static void eTag(String str, Object... objArr) {
        log(6, str, objArr);
    }

    public static void file(Object obj) {
        log(19, CONFIG.getGlobalTag(), obj);
    }

    private static String findDate(String str) {
        Matcher matcher = Pattern.compile("[0-9]{4}_[0-9]{2}_[0-9]{2}").matcher(str);
        return matcher.find() ? matcher.group() : "";
    }

    private static String formatObject(int i, Object obj) {
        return obj == null ? NULL : i == 32 ? e.d(obj, 32) : i == 48 ? e.d(obj, 48) : formatObject(obj);
    }

    private static Class getClassFromObject(Object obj) {
        String string;
        Class<?> cls = obj.getClass();
        if (cls.isAnonymousClass() || cls.isSynthetic()) {
            Type[] genericInterfaces = cls.getGenericInterfaces();
            if (genericInterfaces.length == 1) {
                Type rawType = genericInterfaces[0];
                while (rawType instanceof ParameterizedType) {
                    rawType = ((ParameterizedType) rawType).getRawType();
                }
                string = rawType.toString();
            } else {
                Type genericSuperclass = cls.getGenericSuperclass();
                while (genericSuperclass instanceof ParameterizedType) {
                    genericSuperclass = ((ParameterizedType) genericSuperclass).getRawType();
                }
                string = genericSuperclass.toString();
            }
            if (string.startsWith("class ")) {
                string = string.substring(6);
            } else if (string.startsWith("interface ")) {
                string = string.substring(10);
            }
            try {
                return Class.forName(string);
            } catch (ClassNotFoundException e2) {
                e2.printStackTrace();
            }
        }
        return cls;
    }

    public static Config getConfig() {
        return CONFIG;
    }

    public static String getCurrentLogFilePath() {
        return getCurrentLogFilePath(new Date());
    }

    private static String getFileName(StackTraceElement stackTraceElement) {
        String fileName = stackTraceElement.getFileName();
        if (fileName != null) {
            return fileName;
        }
        String className = stackTraceElement.getClassName();
        String[] strArrSplit = className.split("\\.");
        if (strArrSplit.length > 0) {
            className = strArrSplit[strArrSplit.length - 1];
        }
        int iIndexOf = className.indexOf(36);
        if (iIndexOf != -1) {
            className = className.substring(0, iIndexOf);
        }
        return spi.c.f(className, ".java");
    }

    public static List<File> getLogFiles() {
        File file = new File(CONFIG.getDir());
        if (!file.exists()) {
            return new ArrayList();
        }
        File[] fileArrListFiles = file.listFiles(new b());
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fileArrListFiles);
        return arrayList;
    }

    private static SimpleDateFormat getSdf() {
        if (simpleDateFormat == null) {
            simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd HH:mm:ss.SSS ", Locale.getDefault());
        }
        return simpleDateFormat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T> Class getTypeClassFromParadigm(IFormatter<T> iFormatter) {
        Type[] genericInterfaces = iFormatter.getClass().getGenericInterfaces();
        Type rawType = ((ParameterizedType) (genericInterfaces.length == 1 ? genericInterfaces[0] : iFormatter.getClass().getGenericSuperclass())).getActualTypeArguments()[0];
        while (rawType instanceof ParameterizedType) {
            rawType = ((ParameterizedType) rawType).getRawType();
        }
        String string = rawType.toString();
        if (string.startsWith("class ")) {
            string = string.substring(6);
        } else if (string.startsWith("interface ")) {
            string = string.substring(10);
        }
        try {
            return Class.forName(string);
        } catch (ClassNotFoundException e2) {
            e2.printStackTrace();
            return null;
        }
    }

    public static void i(Object... objArr) {
        log(4, CONFIG.getGlobalTag(), objArr);
    }

    public static void iTag(String str, Object... objArr) {
        log(4, str, objArr);
    }

    private static void input2File(String str, String str2) {
        Config config = CONFIG;
        if (config.mFileWriter == null) {
            FileIOUtils.writeFileFromString(str, str2, true);
        } else {
            config.mFileWriter.write(str, str2);
        }
        if (config.mOnFileOutputListener != null) {
            config.mOnFileOutputListener.onFileOutput(str, str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMatchLogFileName(String str) {
        StringBuilder sbK = spi.c.k("^");
        sbK.append(CONFIG.getFilePrefix());
        sbK.append("_[0-9]{4}_[0-9]{2}_[0-9]{2}_.*$");
        return str.matches(sbK.toString());
    }

    public static void json(Object obj) {
        log(35, CONFIG.getGlobalTag(), obj);
    }

    public static void log(int i, String str, Object... objArr) {
        Config config = CONFIG;
        if (config.isLogSwitch()) {
            int i2 = i & 15;
            int i3 = i & 240;
            if (config.isLog2ConsoleSwitch() || config.isLog2FileSwitch() || i3 == 16) {
                if (i2 >= config.mConsoleFilter || i2 >= config.mFileFilter) {
                    f fVarProcessTagAndHead = processTagAndHead(str);
                    String strProcessBody = processBody(i3, objArr);
                    if (config.isLog2ConsoleSwitch() && i3 != 16 && i2 >= config.mConsoleFilter) {
                        print2Console(i2, fVarProcessTagAndHead.a, fVarProcessTagAndHead.b, strProcessBody);
                    }
                    if ((config.isLog2FileSwitch() || i3 == 16) && i2 >= config.mFileFilter) {
                        EXECUTOR.execute(new a(i2, fVarProcessTagAndHead, strProcessBody));
                    }
                }
            }
        }
    }

    private static void print2Console(int i, String str, String[] strArr, String str2) {
        if (CONFIG.isSingleTagSwitch()) {
            printSingleTagMsg(i, str, processSingleTagMsg(i, str, strArr, str2));
            return;
        }
        printBorder(i, str, true);
        printHead(i, str, strArr);
        printMsg(i, str, str2);
        printBorder(i, str, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void print2File(int i, String str, String str2) {
        Date date = new Date();
        String str3 = getSdf().format(date);
        String strSubstring = str3.substring(0, 10);
        String currentLogFilePath = getCurrentLogFilePath(date);
        if (!createOrExistsFile(currentLogFilePath, strSubstring)) {
            Log.e("LogUtils", "create " + currentLogFilePath + " failed!");
            return;
        }
        StringBuilder sbK = spi.c.k(str3.substring(11));
        sbK.append(T[i - 2]);
        sbK.append("/");
        sbK.append(str);
        sbK.append(str2);
        sbK.append(LINE_SEP);
        input2File(currentLogFilePath, sbK.toString());
    }

    private static void printBorder(int i, String str, boolean z) {
        if (CONFIG.isLogBorderSwitch()) {
            print2Console(i, str, z ? TOP_BORDER : BOTTOM_BORDER);
        }
    }

    private static void printDeviceInfo(String str, String str2) {
        Config config = CONFIG;
        f.a aVar = config.mFileHead;
        aVar.b(aVar.b, "Date of Log", str2);
        input2File(str, config.mFileHead.toString());
    }

    private static void printHead(int i, String str, String[] strArr) {
        if (strArr != null) {
            for (String strF : strArr) {
                if (CONFIG.isLogBorderSwitch()) {
                    strF = spi.c.f(LEFT_BORDER, strF);
                }
                print2Console(i, str, strF);
            }
            if (CONFIG.isLogBorderSwitch()) {
                print2Console(i, str, MIDDLE_BORDER);
            }
        }
    }

    private static void printMsg(int i, String str, String str2) {
        int length = str2.length();
        int i2 = length / 1100;
        if (i2 <= 0) {
            printSubMsg(i, str, str2);
            return;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < i2) {
            int i5 = i4 + 1100;
            printSubMsg(i, str, str2.substring(i4, i5));
            i3++;
            i4 = i5;
        }
        if (i4 != length) {
            printSubMsg(i, str, str2.substring(i4, length));
        }
    }

    private static void printSingleTagMsg(int i, String str, String str2) {
        int length = str2.length();
        Config config = CONFIG;
        int i2 = 1100;
        int i3 = config.isLogBorderSwitch() ? (length - 113) / 1100 : length / 1100;
        if (i3 <= 0) {
            print2Console(i, str, str2);
            return;
        }
        int i4 = 1;
        if (!config.isLogBorderSwitch()) {
            print2Console(i, str, str2.substring(0, 1100));
            while (i4 < i3) {
                StringBuilder sbK = spi.c.k(PLACEHOLDER);
                sbK.append(LINE_SEP);
                int i5 = i2 + 1100;
                sbK.append(str2.substring(i2, i5));
                print2Console(i, str, sbK.toString());
                i4++;
                i2 = i5;
            }
            if (i2 != length) {
                StringBuilder sbK2 = spi.c.k(PLACEHOLDER);
                sbK2.append(LINE_SEP);
                sbK2.append(str2.substring(i2, length));
                print2Console(i, str, sbK2.toString());
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(str2.substring(0, 1100));
        print2Console(i, str, spi.c.h(sb, LINE_SEP, BOTTOM_BORDER));
        while (i4 < i3) {
            StringBuilder sbK3 = spi.c.k(PLACEHOLDER);
            String str3 = LINE_SEP;
            sbK3.append(str3);
            sbK3.append(TOP_BORDER);
            sbK3.append(str3);
            sbK3.append(LEFT_BORDER);
            int i6 = i2 + 1100;
            sbK3.append(str2.substring(i2, i6));
            sbK3.append(str3);
            sbK3.append(BOTTOM_BORDER);
            print2Console(i, str, sbK3.toString());
            i4++;
            i2 = i6;
        }
        if (i2 != length - 113) {
            StringBuilder sbK4 = spi.c.k(PLACEHOLDER);
            String str4 = LINE_SEP;
            sbK4.append(str4);
            sbK4.append(TOP_BORDER);
            sbK4.append(str4);
            sbK4.append(LEFT_BORDER);
            sbK4.append(str2.substring(i2, length));
            print2Console(i, str, sbK4.toString());
        }
    }

    private static void printSubMsg(int i, String str, String str2) {
        if (!CONFIG.isLogBorderSwitch()) {
            print2Console(i, str, str2);
            return;
        }
        for (String str3 : str2.split(LINE_SEP)) {
            print2Console(i, str, spi.c.f(LEFT_BORDER, str3));
        }
    }

    private static String processBody(int i, Object... objArr) {
        String string;
        if (objArr != null) {
            if (objArr.length == 1) {
                string = formatObject(i, objArr[0]);
            } else {
                StringBuilder sb = new StringBuilder();
                int length = objArr.length;
                for (int i2 = 0; i2 < length; i2++) {
                    Object obj = objArr[i2];
                    sb.append(ARGS);
                    sb.append("[");
                    sb.append(i2);
                    sb.append("]");
                    sb.append(" = ");
                    sb.append(formatObject(obj));
                    sb.append(LINE_SEP);
                }
                string = sb.toString();
            }
        } else {
            string = NULL;
        }
        return string.length() == 0 ? NOTHING : string;
    }

    private static String processSingleTagMsg(int i, String str, String[] strArr, String str2) {
        StringBuilder sb = new StringBuilder();
        int i2 = 0;
        if (CONFIG.isLogBorderSwitch()) {
            sb.append(PLACEHOLDER);
            String str3 = LINE_SEP;
            sb.append(str3);
            sb.append(TOP_BORDER);
            sb.append(str3);
            if (strArr != null) {
                for (String str4 : strArr) {
                    sb.append(LEFT_BORDER);
                    sb.append(str4);
                    sb.append(LINE_SEP);
                }
                sb.append(MIDDLE_BORDER);
                sb.append(LINE_SEP);
            }
            String[] strArrSplit = str2.split(LINE_SEP);
            int length = strArrSplit.length;
            while (i2 < length) {
                String str5 = strArrSplit[i2];
                sb.append(LEFT_BORDER);
                sb.append(str5);
                sb.append(LINE_SEP);
                i2++;
            }
            sb.append(BOTTOM_BORDER);
        } else {
            if (strArr != null) {
                sb.append(PLACEHOLDER);
                sb.append(LINE_SEP);
                int length2 = strArr.length;
                while (i2 < length2) {
                    sb.append(strArr[i2]);
                    sb.append(LINE_SEP);
                    i2++;
                }
            }
            sb.append(str2);
        }
        return sb.toString();
    }

    private static f processTagAndHead(String str) {
        String strSubstring;
        String globalTag;
        String strSubstring2;
        Config config = CONFIG;
        if (config.mTagIsSpace || config.isLogHeadSwitch()) {
            StackTraceElement[] stackTrace = new Throwable().getStackTrace();
            int stackOffset = config.getStackOffset() + 3;
            if (stackOffset >= stackTrace.length) {
                String fileName = getFileName(stackTrace[3]);
                if (config.mTagIsSpace && StringUtils.isSpace(str)) {
                    int iIndexOf = fileName.indexOf(46);
                    strSubstring2 = iIndexOf == -1 ? fileName : fileName.substring(0, iIndexOf);
                } else {
                    strSubstring2 = str;
                }
                return new f(strSubstring2, null, ": ");
            }
            StackTraceElement stackTraceElement = stackTrace[stackOffset];
            String fileName2 = getFileName(stackTraceElement);
            if (config.mTagIsSpace && StringUtils.isSpace(str)) {
                int iIndexOf2 = fileName2.indexOf(46);
                strSubstring = iIndexOf2 == -1 ? fileName2 : fileName2.substring(0, iIndexOf2);
            } else {
                strSubstring = str;
            }
            if (config.isLogHeadSwitch()) {
                String name = Thread.currentThread().getName();
                String string = new Formatter().format("%s, %s.%s(%s:%d)", name, stackTraceElement.getClassName(), stackTraceElement.getMethodName(), fileName2, Integer.valueOf(stackTraceElement.getLineNumber())).toString();
                String strG = spi.c.g(" [", string, "]: ");
                if (config.getStackDeep() <= 1) {
                    return new f(strSubstring, new String[]{string}, strG);
                }
                int iMin = Math.min(config.getStackDeep(), stackTrace.length - stackOffset);
                String[] strArr = new String[iMin];
                strArr[0] = string;
                int length = name.length() + 2;
                String string2 = new Formatter().format("%" + length + "s", "").toString();
                for (int i = 1; i < iMin; i++) {
                    StackTraceElement stackTraceElement2 = stackTrace[i + stackOffset];
                    strArr[i] = new Formatter().format("%s%s.%s(%s:%d)", string2, stackTraceElement2.getClassName(), stackTraceElement2.getMethodName(), getFileName(stackTraceElement2), Integer.valueOf(stackTraceElement2.getLineNumber())).toString();
                }
                return new f(strSubstring, strArr, strG);
            }
            globalTag = strSubstring;
        } else {
            globalTag = config.getGlobalTag();
        }
        return new f(globalTag, null, ": ");
    }

    public static void v(Object... objArr) {
        log(2, CONFIG.getGlobalTag(), objArr);
    }

    public static void vTag(String str, Object... objArr) {
        log(2, str, objArr);
    }

    public static void w(Object... objArr) {
        log(5, CONFIG.getGlobalTag(), objArr);
    }

    public static void wTag(String str, Object... objArr) {
        log(5, str, objArr);
    }

    public static void xml(String str) {
        log(51, CONFIG.getGlobalTag(), str);
    }

    public static void file(int i, Object obj) {
        log(i | 16, CONFIG.getGlobalTag(), obj);
    }

    private static String getCurrentLogFilePath(Date date) {
        String strSubstring = getSdf().format(date).substring(0, 10);
        StringBuilder sb = new StringBuilder();
        Config config = CONFIG;
        sb.append(config.getDir());
        sb.append(config.getFilePrefix());
        sb.append("_");
        sb.append(strSubstring);
        sb.append("_");
        sb.append(config.getProcessName());
        sb.append(config.getFileExtension());
        return sb.toString();
    }

    public static void json(int i, Object obj) {
        log(i | 32, CONFIG.getGlobalTag(), obj);
    }

    public static void xml(int i, String str) {
        log(i | 48, CONFIG.getGlobalTag(), str);
    }

    public static void file(String str, Object obj) {
        log(19, str, obj);
    }

    public static void json(String str, Object obj) {
        log(35, str, obj);
    }

    public static void xml(String str, String str2) {
        log(51, str, str2);
    }

    public static void file(int i, String str, Object obj) {
        log(i | 16, str, obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String formatObject(Object obj) {
        IFormatter iFormatter;
        if (obj == null) {
            return NULL;
        }
        SimpleArrayMap<Class, IFormatter> simpleArrayMap = I_FORMATTER_MAP;
        if (!simpleArrayMap.isEmpty() && (iFormatter = simpleArrayMap.get(getClassFromObject(obj))) != null) {
            return iFormatter.format(obj);
        }
        return e.d(obj, -1);
    }

    public static void json(int i, String str, Object obj) {
        log(i | 32, str, obj);
    }

    public static void xml(int i, String str, String str2) {
        log(i | 48, str, str2);
    }

    private static void print2Console(int i, String str, String str2) {
        Log.println(i, str, str2);
        Config config = CONFIG;
        if (config.mOnConsoleOutputListener != null) {
            config.mOnConsoleOutputListener.onConsoleOutput(i, str, str2);
        }
    }
}
