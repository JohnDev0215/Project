package org.apache.tools.ant.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.CharacterIterator;
import java.text.DecimalFormat;
import java.text.StringCharacterIterator;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.filters.util.ChainReaderHelper;
import org.apache.tools.ant.launch.Locator;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.FilterSetCollection;

/* JADX INFO: loaded from: classes.dex */
public class FileUtils {
    private static final int BUF_SIZE = 8192;
    public static final long FAT_FILE_TIMESTAMP_GRANULARITY = 2000;
    public static final long UNIX_FILE_TIMESTAMP_GRANULARITY = 1000;
    private static final FileUtils PRIMARY_INSTANCE = new FileUtils();
    private static Random rand = new Random(System.currentTimeMillis() + Runtime.getRuntime().freeMemory());
    private static boolean onNetWare = Os.isFamily("netware");
    private static boolean onDos = Os.isFamily("dos");
    private static boolean[] isSpecial = new boolean[256];
    private static char[] escapedChar1 = new char[256];
    private static char[] escapedChar2 = new char[256];

    static {
        for (int i = 0; i <= 32; i++) {
            isSpecial[i] = true;
            escapedChar1[i] = Character.forDigit(i >> 4, 16);
            escapedChar2[i] = Character.forDigit(i & 15, 16);
        }
        isSpecial[127] = true;
        escapedChar1[127] = '7';
        escapedChar2[127] = 'F';
        char[] escChs = {'<', '>', '#', '%', '\"', '{', '}', '|', '\\', '^', '~', '[', ']', '`'};
        for (char ch : escChs) {
            isSpecial[ch] = true;
            escapedChar1[ch] = Character.forDigit(ch >> 4, 16);
            escapedChar2[ch] = Character.forDigit(ch & 15, 16);
        }
    }

    public static FileUtils newFileUtils() {
        return new FileUtils();
    }

    public static FileUtils getFileUtils() {
        return PRIMARY_INSTANCE;
    }

    protected FileUtils() {
    }

    public URL getFileURL(File file) throws MalformedURLException {
        return new URL(toURI(file.getAbsolutePath()));
    }

    public void copyFile(String sourceFile, String destFile) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), (FilterSetCollection) null, false, false);
    }

    public void copyFile(String sourceFile, String destFile, FilterSetCollection filters) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), filters, false, false);
    }

    public void copyFile(String sourceFile, String destFile, FilterSetCollection filters, boolean overwrite) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), filters, overwrite, false);
    }

    public void copyFile(String sourceFile, String destFile, FilterSetCollection filters, boolean overwrite, boolean preserveLastModified) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), filters, overwrite, preserveLastModified);
    }

    public void copyFile(String sourceFile, String destFile, FilterSetCollection filters, boolean overwrite, boolean preserveLastModified, String encoding) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), filters, overwrite, preserveLastModified, encoding);
    }

    public void copyFile(String sourceFile, String destFile, FilterSetCollection filters, Vector filterChains, boolean overwrite, boolean preserveLastModified, String encoding, Project project) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), filters, filterChains, overwrite, preserveLastModified, encoding, project);
    }

    public void copyFile(String sourceFile, String destFile, FilterSetCollection filters, Vector filterChains, boolean overwrite, boolean preserveLastModified, String inputEncoding, String outputEncoding, Project project) throws Throwable {
        copyFile(new File(sourceFile), new File(destFile), filters, filterChains, overwrite, preserveLastModified, inputEncoding, outputEncoding, project);
    }

    public void copyFile(File sourceFile, File destFile) throws Throwable {
        copyFile(sourceFile, destFile, (FilterSetCollection) null, false, false);
    }

    public void copyFile(File sourceFile, File destFile, FilterSetCollection filters) throws Throwable {
        copyFile(sourceFile, destFile, filters, false, false);
    }

    public void copyFile(File sourceFile, File destFile, FilterSetCollection filters, boolean overwrite) throws Throwable {
        copyFile(sourceFile, destFile, filters, overwrite, false);
    }

    public void copyFile(File sourceFile, File destFile, FilterSetCollection filters, boolean overwrite, boolean preserveLastModified) throws Throwable {
        copyFile(sourceFile, destFile, filters, overwrite, preserveLastModified, (String) null);
    }

    public void copyFile(File sourceFile, File destFile, FilterSetCollection filters, boolean overwrite, boolean preserveLastModified, String encoding) throws Throwable {
        copyFile(sourceFile, destFile, filters, (Vector) null, overwrite, preserveLastModified, encoding, (Project) null);
    }

    public void copyFile(File sourceFile, File destFile, FilterSetCollection filters, Vector filterChains, boolean overwrite, boolean preserveLastModified, String encoding, Project project) throws Throwable {
        copyFile(sourceFile, destFile, filters, filterChains, overwrite, preserveLastModified, encoding, encoding, project);
    }

    public void copyFile(File sourceFile, File destFile, FilterSetCollection filters, Vector filterChains, boolean overwrite, boolean preserveLastModified, String inputEncoding, String outputEncoding, Project project) throws Throwable {
        BufferedWriter out;
        BufferedReader in;
        if (overwrite || !destFile.exists() || destFile.lastModified() < sourceFile.lastModified()) {
            if (destFile.exists() && destFile.isFile()) {
                destFile.delete();
            }
            File parent = destFile.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            boolean filterSetsAvailable = filters != null && filters.hasFilters();
            boolean filterChainsAvailable = filterChains != null && filterChains.size() > 0;
            if (filterSetsAvailable) {
                in = null;
                out = null;
                try {
                    if (inputEncoding == null) {
                        in = new BufferedReader(new FileReader(sourceFile));
                    } else {
                        InputStreamReader isr = new InputStreamReader(new FileInputStream(sourceFile), inputEncoding);
                        in = new BufferedReader(isr);
                    }
                    if (outputEncoding == null) {
                        out = new BufferedWriter(new FileWriter(destFile));
                    } else {
                        OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(destFile), outputEncoding);
                        out = new BufferedWriter(osw);
                    }
                    if (filterChainsAvailable) {
                        ChainReaderHelper crh = new ChainReaderHelper();
                        crh.setBufferSize(8192);
                        crh.setPrimaryReader(in);
                        crh.setFilterChains(filterChains);
                        crh.setProject(project);
                        Reader rdr = crh.getAssembledReader();
                        in = new BufferedReader(rdr);
                    }
                    LineTokenizer lineTokenizer = new LineTokenizer();
                    lineTokenizer.setIncludeDelims(true);
                    for (String line = lineTokenizer.getToken(in); line != null; line = lineTokenizer.getToken(in)) {
                        if (line.length() == 0) {
                            out.newLine();
                        } else {
                            String newline = filters.replaceTokens(line);
                            out.write(newline);
                        }
                    }
                } finally {
                    close(out);
                    close(in);
                }
            } else if (filterChainsAvailable || ((inputEncoding != null && !inputEncoding.equals(outputEncoding)) || (inputEncoding == null && outputEncoding != null))) {
                BufferedReader in2 = null;
                BufferedWriter out2 = null;
                try {
                    if (inputEncoding == null) {
                        in2 = new BufferedReader(new FileReader(sourceFile));
                    } else {
                        in2 = new BufferedReader(new InputStreamReader(new FileInputStream(sourceFile), inputEncoding));
                    }
                    if (outputEncoding == null) {
                        out2 = new BufferedWriter(new FileWriter(destFile));
                    } else {
                        out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(destFile), outputEncoding));
                    }
                    if (filterChainsAvailable) {
                        ChainReaderHelper crh2 = new ChainReaderHelper();
                        crh2.setBufferSize(8192);
                        crh2.setPrimaryReader(in2);
                        crh2.setFilterChains(filterChains);
                        crh2.setProject(project);
                        Reader rdr2 = crh2.getAssembledReader();
                        in2 = new BufferedReader(rdr2);
                    }
                    char[] buffer = new char[8192];
                    while (true) {
                        int nRead = in.read(buffer, 0, buffer.length);
                        if (nRead == -1) {
                            break;
                        }
                        try {
                            out.write(buffer, 0, nRead);
                        } catch (Throwable th) {
                            th = th;
                            throw th;
                        }
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            } else {
                FileInputStream in3 = null;
                FileOutputStream out3 = null;
                try {
                    in3 = new FileInputStream(sourceFile);
                    out3 = new FileOutputStream(destFile);
                    byte[] buffer2 = new byte[8192];
                    int count = 0;
                    do {
                        out3.write(buffer2, 0, count);
                        count = in3.read(buffer2, 0, buffer2.length);
                    } while (count != -1);
                } finally {
                    close(out3);
                    close(in3);
                }
            }
            if (preserveLastModified) {
                setFileLastModified(destFile, sourceFile.lastModified());
            }
        }
    }

    public void setFileLastModified(File file, long time) {
        file.setLastModified(time < 0 ? System.currentTimeMillis() : time);
    }

    public File resolveFile(File file, String filename) {
        String filename2 = filename.replace('/', File.separatorChar).replace('\\', File.separatorChar);
        if (isAbsolutePath(filename2)) {
            return normalize(filename2);
        }
        if (file == null) {
            return new File(filename2);
        }
        File helpFile = new File(file.getAbsolutePath());
        StringTokenizer tok = new StringTokenizer(filename2, File.separator);
        while (tok.hasMoreTokens()) {
            String part = tok.nextToken();
            if (part.equals("..")) {
                helpFile = helpFile.getParentFile();
                if (helpFile == null) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("The file or path you specified (");
                    stringBuffer.append(filename2);
                    stringBuffer.append(") is invalid relative to ");
                    stringBuffer.append(file.getPath());
                    String msg = stringBuffer.toString();
                    throw new BuildException(msg);
                }
            } else if (!part.equals(".")) {
                helpFile = new File(helpFile, part);
            }
        }
        return new File(helpFile.getAbsolutePath());
    }

    public static boolean isAbsolutePath(String filename) {
        if (filename.startsWith(File.separator)) {
            return true;
        }
        if (onDos && filename.length() >= 2 && Character.isLetter(filename.charAt(0)) && filename.charAt(1) == ':') {
            return true;
        }
        return onNetWare && filename.indexOf(":") > -1;
    }

    public File normalize(String path) {
        String root;
        boolean dosWithDrive;
        String path2;
        String path3 = path.replace('/', File.separatorChar).replace('\\', File.separatorChar);
        int colon = path3.indexOf(":");
        if (!isAbsolutePath(path3)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(path3);
            stringBuffer.append(" is not an absolute path");
            String msg = stringBuffer.toString();
            throw new BuildException(msg);
        }
        if ((onDos && path3.length() >= 2 && Character.isLetter(path3.charAt(0)) && path3.charAt(1) == ':') || (onNetWare && colon > -1)) {
            dosWithDrive = true;
            char[] ca = path3.replace('/', '\\').toCharArray();
            StringBuffer sbRoot = new StringBuffer();
            for (int i = 0; i < colon; i++) {
                sbRoot.append(Character.toUpperCase(ca[i]));
            }
            sbRoot.append(':');
            if (colon + 1 < path3.length()) {
                sbRoot.append(File.separatorChar);
            }
            root = sbRoot.toString();
            StringBuffer sbPath = new StringBuffer();
            for (int i2 = colon + 1; i2 < ca.length; i2++) {
                if (ca[i2] != '\\' || (ca[i2] == '\\' && ca[i2 - 1] != '\\')) {
                    sbPath.append(ca[i2]);
                }
            }
            path2 = sbPath.toString().replace('\\', File.separatorChar);
        } else if (path3.length() == 1) {
            root = File.separator;
            dosWithDrive = false;
            path2 = "";
        } else if (path3.charAt(1) == File.separatorChar) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(File.separator);
            stringBuffer2.append(File.separator);
            root = stringBuffer2.toString();
            dosWithDrive = false;
            path2 = path3.substring(2);
        } else {
            root = File.separator;
            dosWithDrive = false;
            path2 = path3.substring(1);
        }
        Stack s = new Stack();
        s.push(root);
        StringTokenizer tok = new StringTokenizer(path2, File.separator);
        while (tok.hasMoreTokens()) {
            String thisToken = tok.nextToken();
            if (!".".equals(thisToken)) {
                if ("..".equals(thisToken)) {
                    if (s.size() < 2) {
                        StringBuffer stringBuffer3 = new StringBuffer();
                        stringBuffer3.append("Cannot resolve path ");
                        stringBuffer3.append(path);
                        throw new BuildException(stringBuffer3.toString());
                    }
                    s.pop();
                } else {
                    s.push(thisToken);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for (int i3 = 0; i3 < s.size(); i3++) {
            if (i3 > 1) {
                sb.append(File.separatorChar);
            }
            sb.append(s.elementAt(i3));
        }
        String path4 = sb.toString();
        if (dosWithDrive) {
            path4 = path4.replace('/', '\\');
        }
        return new File(path4);
    }

    public String toVMSPath(File f) {
        String string;
        String string2;
        String path = normalize(f.getAbsolutePath()).getPath();
        String name = f.getName();
        boolean isAbsolute = path.charAt(0) == File.separatorChar;
        boolean isDirectory = f.isDirectory() && !name.regionMatches(true, name.length() + (-4), ".DIR", 0, 4);
        String device = null;
        StringBuffer directory = null;
        String file = null;
        int index = 0;
        if (isAbsolute) {
            int index2 = path.indexOf(File.separatorChar, 1);
            if (index2 == -1) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(path.substring(1));
                stringBuffer.append(":[000000]");
                return stringBuffer.toString();
            }
            device = path.substring(1, index2);
            index = index2 + 1;
        }
        if (isDirectory) {
            directory = new StringBuffer(path.substring(index).replace(File.separatorChar, '.'));
        } else {
            int dirEnd = path.lastIndexOf(File.separatorChar, path.length());
            if (dirEnd == -1 || dirEnd < index) {
                file = path.substring(index);
            } else {
                directory = new StringBuffer(path.substring(index, dirEnd).replace(File.separatorChar, '.'));
                int index3 = dirEnd + 1;
                if (path.length() > index3) {
                    file = path.substring(index3);
                }
            }
        }
        if (!isAbsolute && directory != null) {
            directory.insert(0, '.');
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        if (device != null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(device);
            stringBuffer3.append(":");
            string = stringBuffer3.toString();
        } else {
            string = "";
        }
        stringBuffer2.append(string);
        if (directory != null) {
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append("[");
            stringBuffer4.append((Object) directory);
            stringBuffer4.append("]");
            string2 = stringBuffer4.toString();
        } else {
            string2 = "";
        }
        stringBuffer2.append(string2);
        stringBuffer2.append(file != null ? file : "");
        String osPath = stringBuffer2.toString();
        return osPath;
    }

    public File createTempFile(String prefix, String suffix, File parentDir) {
        File result;
        String parent = parentDir == null ? System.getProperty("java.io.tmpdir") : parentDir.getPath();
        DecimalFormat fmt = new DecimalFormat("#####");
        synchronized (rand) {
            do {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(prefix);
                stringBuffer.append(fmt.format(Math.abs(rand.nextInt())));
                stringBuffer.append(suffix);
                result = new File(parent, stringBuffer.toString());
            } while (result.exists());
        }
        return result;
    }

    public boolean contentEquals(File f1, File f2) throws IOException {
        return contentEquals(f1, f2, false);
    }

    public boolean contentEquals(File f1, File f2, boolean textfile) throws IOException {
        if (f1.exists() != f2.exists()) {
            return false;
        }
        if (!f1.exists()) {
            return true;
        }
        if (f1.isDirectory() || f2.isDirectory()) {
            return false;
        }
        if (fileNameEquals(f1, f2)) {
            return true;
        }
        return textfile ? textEquals(f1, f2) : binaryEquals(f1, f2);
    }

    private boolean binaryEquals(File f1, File f2) throws IOException {
        if (f1.length() != f2.length()) {
            return false;
        }
        InputStream in1 = null;
        InputStream in2 = null;
        try {
            in1 = new BufferedInputStream(new FileInputStream(f1));
            in2 = new BufferedInputStream(new FileInputStream(f2));
            for (int expectedByte = in1.read(); expectedByte != -1; expectedByte = in1.read()) {
                if (expectedByte != in2.read()) {
                    return false;
                }
            }
            if (in2.read() != -1) {
                return false;
            }
            return true;
        } finally {
            close(in1);
            close(in2);
        }
    }

    private boolean textEquals(File f1, File f2) throws IOException {
        BufferedReader in1 = null;
        BufferedReader in2 = null;
        try {
            in1 = new BufferedReader(new FileReader(f1));
            in2 = new BufferedReader(new FileReader(f2));
            for (String expected = in1.readLine(); expected != null; expected = in1.readLine()) {
                if (!expected.equals(in2.readLine())) {
                    return false;
                }
            }
            if (in2.readLine() != null) {
                return false;
            }
            return true;
        } finally {
            close(in1);
            close(in2);
        }
    }

    public File getParentFile(File f) {
        if (f == null) {
            return null;
        }
        return f.getParentFile();
    }

    public static final String readFully(Reader rdr) throws IOException {
        return readFully(rdr, 8192);
    }

    public static final String readFully(Reader rdr, int bufferSize) throws IOException {
        if (bufferSize <= 0) {
            throw new IllegalArgumentException("Buffer size must be greater than 0");
        }
        char[] buffer = new char[bufferSize];
        int bufferLength = 0;
        StringBuffer textBuffer = null;
        while (bufferLength != -1) {
            bufferLength = rdr.read(buffer);
            if (bufferLength > 0) {
                textBuffer = textBuffer == null ? new StringBuffer() : textBuffer;
                textBuffer.append(new String(buffer, 0, bufferLength));
            }
        }
        if (textBuffer == null) {
            return null;
        }
        return textBuffer.toString();
    }

    public boolean createNewFile(File f) throws IOException {
        return f.createNewFile();
    }

    public boolean createNewFile(File f, boolean mkdirs) throws IOException {
        File parent = f.getParentFile();
        if (mkdirs && !parent.exists()) {
            parent.mkdirs();
        }
        return f.createNewFile();
    }

    public boolean isSymbolicLink(File parent, String name) throws IOException {
        if (parent == null) {
            File f = new File(name);
            parent = f.getParentFile();
            name = f.getName();
        }
        File toTest = new File(parent.getCanonicalPath(), name);
        return !toTest.getAbsolutePath().equals(toTest.getCanonicalPath());
    }

    public String removeLeadingPath(File leading, File path) {
        String l = normalize(leading.getAbsolutePath()).getAbsolutePath();
        String p = normalize(path.getAbsolutePath()).getAbsolutePath();
        if (l.equals(p)) {
            return "";
        }
        if (!l.endsWith(File.separator)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(l);
            stringBuffer.append(File.separator);
            l = stringBuffer.toString();
        }
        return p.startsWith(l) ? p.substring(l.length()) : p;
    }

    public String toURI(String path) {
        boolean isDir = new File(path).isDirectory();
        StringBuffer sb = new StringBuffer("file:");
        try {
            path = normalize(path).getAbsolutePath();
            sb.append("//");
            if (!path.startsWith(File.separator)) {
                sb.append("/");
            }
        } catch (BuildException e) {
        }
        String path2 = path.replace('\\', '/');
        CharacterIterator iter = new StringCharacterIterator(path2);
        for (char c = iter.first(); c != 65535; c = iter.next()) {
            if (c < 256 && isSpecial[c]) {
                sb.append('%');
                sb.append(escapedChar1[c]);
                sb.append(escapedChar2[c]);
            } else {
                sb.append(c);
            }
        }
        if (isDir && !path2.endsWith("/")) {
            sb.append('/');
        }
        return sb.toString();
    }

    public String fromURI(String uri) {
        String path = Locator.fromURI(uri);
        try {
            return normalize(path).getAbsolutePath();
        } catch (BuildException e) {
            return path;
        }
    }

    public boolean fileNameEquals(File f1, File f2) {
        return normalize(f1.getAbsolutePath()).equals(normalize(f2.getAbsolutePath()));
    }

    public void rename(File from, File to) throws Throwable {
        if (to.exists() && !to.delete()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Failed to delete ");
            stringBuffer.append(to);
            stringBuffer.append(" while trying to rename ");
            stringBuffer.append(from);
            throw new IOException(stringBuffer.toString());
        }
        File parent = to.getParentFile();
        if (parent != null && !parent.exists() && !parent.mkdirs()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("Failed to create directory ");
            stringBuffer2.append(parent);
            stringBuffer2.append(" while trying to rename ");
            stringBuffer2.append(from);
            throw new IOException(stringBuffer2.toString());
        }
        if (!from.renameTo(to)) {
            copyFile(from, to);
            if (!from.delete()) {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("Failed to delete ");
                stringBuffer3.append(from);
                stringBuffer3.append(" while trying to rename it.");
                throw new IOException(stringBuffer3.toString());
            }
        }
    }

    public long getFileTimestampGranularity() {
        if (onDos) {
            return FAT_FILE_TIMESTAMP_GRANULARITY;
        }
        return 1000L;
    }

    public boolean isUpToDate(File source, File dest, long granularity) {
        if (!dest.exists()) {
            return false;
        }
        long sourceTime = source.lastModified();
        long destTime = dest.lastModified();
        return isUpToDate(sourceTime, destTime, granularity);
    }

    public boolean isUpToDate(File source, File dest) {
        return isUpToDate(source, dest, getFileTimestampGranularity());
    }

    public boolean isUpToDate(long sourceTime, long destTime, long granularity) {
        return destTime != -1 && destTime >= sourceTime + granularity;
    }

    public boolean isUpToDate(long sourceTime, long destTime) {
        return isUpToDate(sourceTime, destTime, getFileTimestampGranularity());
    }

    public static void close(Writer device) {
        if (device != null) {
            try {
                device.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(Reader device) {
        if (device != null) {
            try {
                device.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(OutputStream device) {
        if (device != null) {
            try {
                device.close();
            } catch (IOException e) {
            }
        }
    }

    public static void close(InputStream device) {
        if (device != null) {
            try {
                device.close();
            } catch (IOException e) {
            }
        }
    }

    public static void delete(File file) {
        if (file != null) {
            file.delete();
        }
    }
}
