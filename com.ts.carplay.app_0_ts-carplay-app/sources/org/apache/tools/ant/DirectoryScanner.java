package org.apache.tools.ant;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.condition.Os;
import org.apache.tools.ant.types.Resource;
import org.apache.tools.ant.types.ResourceFactory;
import org.apache.tools.ant.types.selectors.FileSelector;
import org.apache.tools.ant.types.selectors.SelectorScanner;
import org.apache.tools.ant.types.selectors.SelectorUtils;
import org.apache.tools.ant.util.FileUtils;

/* JADX INFO: loaded from: classes.dex */
public class DirectoryScanner implements FileScanner, SelectorScanner, ResourceFactory {
    protected File basedir;
    protected Vector dirsDeselected;
    protected Vector dirsExcluded;
    protected Vector dirsIncluded;
    protected Vector dirsNotIncluded;
    private String[] excludePatterns;
    protected String[] excludes;
    protected Vector filesDeselected;
    protected Vector filesExcluded;
    protected Vector filesIncluded;
    protected Vector filesNotIncluded;
    private String[] includePatterns;
    protected String[] includes;
    private static final boolean ON_VMS = Os.isFamily("openvms");
    protected static final String[] DEFAULTEXCLUDES = {"**/*~", "**/#*#", "**/.#*", "**/%*%", "**/._*", "**/CVS", "**/CVS/**", "**/.cvsignore", "**/SCCS", "**/SCCS/**", "**/vssver.scc", "**/.svn", "**/.svn/**", "**/.DS_Store"};
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();
    private static final boolean[] CS_SCAN_ONLY = {true};
    private static final boolean[] CS_THEN_NON_CS = {true, false};
    private static Vector defaultExcludes = new Vector();
    protected FileSelector[] selectors = null;
    protected boolean haveSlowResults = false;
    protected boolean isCaseSensitive = true;
    private boolean followSymlinks = true;
    protected boolean everythingIncluded = true;
    private Map fileListMap = new HashMap();
    private Set scannedDirs = new HashSet();
    private Set includeNonPatterns = new HashSet();
    private Set excludeNonPatterns = new HashSet();
    private boolean areNonPatternSetsReady = false;
    private boolean scanning = false;
    private Object scanLock = new Object();
    private boolean slowScanning = false;
    private Object slowScanLock = new Object();
    private IllegalStateException illegal = null;

    static {
        resetDefaultExcludes();
    }

    protected static boolean matchPatternStart(String pattern, String str) {
        return SelectorUtils.matchPatternStart(pattern, str);
    }

    protected static boolean matchPatternStart(String pattern, String str, boolean isCaseSensitive) {
        return SelectorUtils.matchPatternStart(pattern, str, isCaseSensitive);
    }

    protected static boolean matchPath(String pattern, String str) {
        return SelectorUtils.matchPath(pattern, str);
    }

    protected static boolean matchPath(String pattern, String str, boolean isCaseSensitive) {
        return SelectorUtils.matchPath(pattern, str, isCaseSensitive);
    }

    public static boolean match(String pattern, String str) {
        return SelectorUtils.match(pattern, str);
    }

    protected static boolean match(String pattern, String str, boolean isCaseSensitive) {
        return SelectorUtils.match(pattern, str, isCaseSensitive);
    }

    public static String[] getDefaultExcludes() {
        Vector vector = defaultExcludes;
        return (String[]) vector.toArray(new String[vector.size()]);
    }

    public static boolean addDefaultExclude(String s) {
        if (defaultExcludes.indexOf(s) == -1) {
            defaultExcludes.add(s);
            return true;
        }
        return false;
    }

    public static boolean removeDefaultExclude(String s) {
        return defaultExcludes.remove(s);
    }

    public static void resetDefaultExcludes() {
        defaultExcludes = new Vector();
        int i = 0;
        while (true) {
            String[] strArr = DEFAULTEXCLUDES;
            if (i < strArr.length) {
                defaultExcludes.add(strArr[i]);
                i++;
            } else {
                return;
            }
        }
    }

    @Override // org.apache.tools.ant.FileScanner
    public void setBasedir(String basedir) {
        setBasedir(new File(basedir.replace('/', File.separatorChar).replace('\\', File.separatorChar)));
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setBasedir(File basedir) {
        this.basedir = basedir;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized File getBasedir() {
        return this.basedir;
    }

    public synchronized boolean isCaseSensitive() {
        return this.isCaseSensitive;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setCaseSensitive(boolean isCaseSensitive) {
        this.isCaseSensitive = isCaseSensitive;
    }

    public synchronized boolean isFollowSymlinks() {
        return this.followSymlinks;
    }

    public synchronized void setFollowSymlinks(boolean followSymlinks) {
        this.followSymlinks = followSymlinks;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setIncludes(String[] includes) {
        if (includes == null) {
            this.includes = null;
        } else {
            this.includes = new String[includes.length];
            for (int i = 0; i < includes.length; i++) {
                this.includes[i] = normalizePattern(includes[i]);
            }
        }
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void setExcludes(String[] excludes) {
        if (excludes == null) {
            this.excludes = null;
        } else {
            this.excludes = new String[excludes.length];
            for (int i = 0; i < excludes.length; i++) {
                this.excludes[i] = normalizePattern(excludes[i]);
            }
        }
    }

    public synchronized void addExcludes(String[] excludes) {
        if (excludes != null) {
            if (excludes.length > 0) {
                if (this.excludes != null && this.excludes.length > 0) {
                    String[] tmp = new String[excludes.length + this.excludes.length];
                    System.arraycopy(this.excludes, 0, tmp, 0, this.excludes.length);
                    for (int i = 0; i < excludes.length; i++) {
                        tmp[this.excludes.length + i] = normalizePattern(excludes[i]);
                    }
                    this.excludes = tmp;
                } else {
                    setExcludes(excludes);
                }
            }
        }
    }

    private static String normalizePattern(String p) {
        String pattern = p.replace('/', File.separatorChar).replace('\\', File.separatorChar);
        if (pattern.endsWith(File.separator)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(pattern);
            stringBuffer.append("**");
            return stringBuffer.toString();
        }
        return pattern;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorScanner
    public synchronized void setSelectors(FileSelector[] selectors) {
        this.selectors = selectors;
    }

    public synchronized boolean isEverythingIncluded() {
        return this.everythingIncluded;
    }

    @Override // org.apache.tools.ant.FileScanner
    public void scan() throws IllegalStateException {
        boolean nullIncludes;
        synchronized (this.scanLock) {
            if (this.scanning) {
                while (this.scanning) {
                    try {
                        this.scanLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                if (this.illegal != null) {
                    throw this.illegal;
                }
                return;
            }
            boolean nullExcludes = true;
            this.scanning = true;
            try {
                synchronized (this) {
                    String[] strArr = null;
                    this.illegal = null;
                    clearResults();
                    if (this.includes == null) {
                        nullIncludes = true;
                    } else {
                        nullIncludes = false;
                    }
                    this.includes = nullIncludes ? new String[]{"**"} : this.includes;
                    if (this.excludes != null) {
                        nullExcludes = false;
                    }
                    this.excludes = nullExcludes ? new String[0] : this.excludes;
                    if (this.basedir == null) {
                        this.illegal = new IllegalStateException("No basedir set");
                    } else {
                        if (!this.basedir.exists()) {
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append("basedir ");
                            stringBuffer.append(this.basedir);
                            stringBuffer.append(" does not exist");
                            this.illegal = new IllegalStateException(stringBuffer.toString());
                        }
                        if (!this.basedir.isDirectory()) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("basedir ");
                            stringBuffer2.append(this.basedir);
                            stringBuffer2.append(" is not a directory");
                            this.illegal = new IllegalStateException(stringBuffer2.toString());
                        }
                    }
                    if (this.illegal != null) {
                        throw this.illegal;
                    }
                    if (isIncluded("")) {
                        if (!isExcluded("")) {
                            if (isSelected("", this.basedir)) {
                                this.dirsIncluded.addElement("");
                            } else {
                                this.dirsDeselected.addElement("");
                            }
                        } else {
                            this.dirsExcluded.addElement("");
                        }
                    } else {
                        this.dirsNotIncluded.addElement("");
                    }
                    checkIncludePatterns();
                    clearCaches();
                    this.includes = nullIncludes ? null : this.includes;
                    if (!nullExcludes) {
                        strArr = this.excludes;
                    }
                    this.excludes = strArr;
                }
                synchronized (this.scanLock) {
                    this.scanning = false;
                    this.scanLock.notifyAll();
                }
            } catch (Throwable th) {
                synchronized (this.scanLock) {
                    this.scanning = false;
                    this.scanLock.notifyAll();
                    throw th;
                }
            }
        }
    }

    private void checkIncludePatterns() {
        File f;
        Hashtable newroots = new Hashtable();
        int icounter = 0;
        while (true) {
            String[] strArr = this.includes;
            if (icounter >= strArr.length) {
                break;
            }
            newroots.put(SelectorUtils.rtrimWildcardTokens(strArr[icounter]), this.includes[icounter]);
            icounter++;
        }
        if (newroots.containsKey("")) {
            scandir(this.basedir, "", true);
            return;
        }
        Enumeration enum2 = newroots.keys();
        try {
            File canonBase = this.basedir.getCanonicalFile();
            while (enum2.hasMoreElements()) {
                String currentelement = (String) enum2.nextElement();
                String originalpattern = (String) newroots.get(currentelement);
                File myfile = new File(this.basedir, currentelement);
                if (myfile.exists()) {
                    try {
                        File canonFile = myfile.getCanonicalFile();
                        String path = FILE_UTILS.removeLeadingPath(canonBase, canonFile);
                        if ((!path.equals(currentelement) || ON_VMS) && (myfile = findFile(this.basedir, currentelement, true)) != null) {
                            currentelement = FILE_UTILS.removeLeadingPath(this.basedir, myfile);
                        }
                    } catch (IOException ex) {
                        throw new BuildException(ex);
                    }
                }
                if ((myfile == null || !myfile.exists()) && !isCaseSensitive() && (f = findFile(this.basedir, currentelement, false)) != null && f.exists()) {
                    currentelement = FILE_UTILS.removeLeadingPath(this.basedir, f);
                    myfile = f;
                }
                if (myfile != null && myfile.exists() && (this.followSymlinks || !isSymlink(this.basedir, currentelement))) {
                    if (myfile.isDirectory()) {
                        if (isIncluded(currentelement) && currentelement.length() > 0) {
                            accountForIncludedDir(currentelement, myfile, true);
                        } else {
                            if (currentelement.length() > 0 && currentelement.charAt(currentelement.length() - 1) != File.separatorChar) {
                                StringBuffer stringBuffer = new StringBuffer();
                                stringBuffer.append(currentelement);
                                stringBuffer.append(File.separatorChar);
                                currentelement = stringBuffer.toString();
                            }
                            scandir(myfile, currentelement, true);
                        }
                    } else {
                        boolean included = isCaseSensitive() ? originalpattern.equals(currentelement) : originalpattern.equalsIgnoreCase(currentelement);
                        if (included) {
                            accountForIncludedFile(currentelement, myfile);
                        }
                    }
                }
            }
        } catch (IOException ex2) {
            throw new BuildException(ex2);
        }
    }

    protected synchronized void clearResults() {
        this.filesIncluded = new Vector();
        this.filesNotIncluded = new Vector();
        this.filesExcluded = new Vector();
        this.filesDeselected = new Vector();
        this.dirsIncluded = new Vector();
        this.dirsNotIncluded = new Vector();
        this.dirsExcluded = new Vector();
        this.dirsDeselected = new Vector();
        this.everythingIncluded = this.basedir != null;
        this.scannedDirs.clear();
    }

    protected void slowScan() {
        boolean nullIncludes;
        boolean nullExcludes;
        synchronized (this.slowScanLock) {
            if (this.haveSlowResults) {
                return;
            }
            if (this.slowScanning) {
                while (this.slowScanning) {
                    try {
                        this.slowScanLock.wait();
                    } catch (InterruptedException e) {
                    }
                }
                return;
            }
            this.slowScanning = true;
            try {
                synchronized (this) {
                    if (this.includes == null) {
                        nullIncludes = true;
                    } else {
                        nullIncludes = false;
                    }
                    this.includes = nullIncludes ? new String[]{"**"} : this.includes;
                    if (this.excludes == null) {
                        nullExcludes = true;
                    } else {
                        nullExcludes = false;
                    }
                    this.excludes = nullExcludes ? new String[0] : this.excludes;
                    String[] excl = new String[this.dirsExcluded.size()];
                    this.dirsExcluded.copyInto(excl);
                    String[] notIncl = new String[this.dirsNotIncluded.size()];
                    this.dirsNotIncluded.copyInto(notIncl);
                    for (int i = 0; i < excl.length; i++) {
                        if (!couldHoldIncluded(excl[i])) {
                            File file = new File(this.basedir, excl[i]);
                            StringBuffer stringBuffer = new StringBuffer();
                            stringBuffer.append(excl[i]);
                            stringBuffer.append(File.separator);
                            scandir(file, stringBuffer.toString(), false);
                        }
                    }
                    for (int i2 = 0; i2 < notIncl.length; i2++) {
                        if (!couldHoldIncluded(notIncl[i2])) {
                            File file2 = new File(this.basedir, notIncl[i2]);
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append(notIncl[i2]);
                            stringBuffer2.append(File.separator);
                            scandir(file2, stringBuffer2.toString(), false);
                        }
                    }
                    clearCaches();
                    String[] strArr = null;
                    this.includes = nullIncludes ? null : this.includes;
                    if (!nullExcludes) {
                        strArr = this.excludes;
                    }
                    this.excludes = strArr;
                }
                synchronized (this.slowScanLock) {
                    this.haveSlowResults = true;
                    this.slowScanning = false;
                    this.slowScanLock.notifyAll();
                }
            } catch (Throwable th) {
                synchronized (this.slowScanLock) {
                    this.haveSlowResults = true;
                    this.slowScanning = false;
                    this.slowScanLock.notifyAll();
                    throw th;
                }
            }
        }
    }

    protected void scandir(File dir, String vpath, boolean fast) {
        String[] newfiles;
        if (dir == null) {
            throw new BuildException("dir must not be null.");
        }
        if (!dir.exists()) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(dir);
            stringBuffer.append(" doesn't exists.");
            throw new BuildException(stringBuffer.toString());
        }
        if (!dir.isDirectory()) {
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(dir);
            stringBuffer2.append(" is not a directory.");
            throw new BuildException(stringBuffer2.toString());
        }
        if (fast && hasBeenScanned(vpath)) {
            return;
        }
        String[] newfiles2 = dir.list();
        if (newfiles2 == null) {
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("IO error scanning directory ");
            stringBuffer3.append(dir.getAbsolutePath());
            throw new BuildException(stringBuffer3.toString());
        }
        if (this.followSymlinks) {
            newfiles = newfiles2;
        } else {
            Vector noLinks = new Vector();
            for (int i = 0; i < newfiles2.length; i++) {
                try {
                    if (FILE_UTILS.isSymbolicLink(dir, newfiles2[i])) {
                        StringBuffer stringBuffer4 = new StringBuffer();
                        stringBuffer4.append(vpath);
                        stringBuffer4.append(newfiles2[i]);
                        (new File(dir, newfiles2[i]).isDirectory() ? this.dirsExcluded : this.filesExcluded).addElement(stringBuffer4.toString());
                    } else {
                        noLinks.addElement(newfiles2[i]);
                    }
                } catch (IOException e) {
                    System.err.println("IOException caught while checking for links, couldn't get canonical path!");
                    noLinks.addElement(newfiles2[i]);
                }
            }
            String[] newfiles3 = new String[noLinks.size()];
            noLinks.copyInto(newfiles3);
            newfiles = newfiles3;
        }
        for (int i2 = 0; i2 < newfiles.length; i2++) {
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(vpath);
            stringBuffer5.append(newfiles[i2]);
            String name = stringBuffer5.toString();
            File file = new File(dir, newfiles[i2]);
            if (file.isDirectory()) {
                if (isIncluded(name)) {
                    accountForIncludedDir(name, file, fast);
                } else {
                    this.everythingIncluded = false;
                    this.dirsNotIncluded.addElement(name);
                    if (fast && couldHoldIncluded(name)) {
                        StringBuffer stringBuffer6 = new StringBuffer();
                        stringBuffer6.append(name);
                        stringBuffer6.append(File.separator);
                        scandir(file, stringBuffer6.toString(), fast);
                    }
                }
                if (!fast) {
                    StringBuffer stringBuffer7 = new StringBuffer();
                    stringBuffer7.append(name);
                    stringBuffer7.append(File.separator);
                    scandir(file, stringBuffer7.toString(), fast);
                }
            } else if (file.isFile()) {
                if (isIncluded(name)) {
                    accountForIncludedFile(name, file);
                } else {
                    this.everythingIncluded = false;
                    this.filesNotIncluded.addElement(name);
                }
            }
        }
    }

    private void accountForIncludedFile(String name, File file) {
        if (this.filesIncluded.contains(name) || this.filesExcluded.contains(name) || this.filesDeselected.contains(name)) {
            return;
        }
        boolean included = false;
        if (isExcluded(name)) {
            this.filesExcluded.addElement(name);
        } else if (isSelected(name, file)) {
            included = true;
            this.filesIncluded.addElement(name);
        } else {
            this.filesDeselected.addElement(name);
        }
        this.everythingIncluded &= included;
    }

    private void accountForIncludedDir(String name, File file, boolean fast) {
        if (this.dirsIncluded.contains(name) || this.dirsExcluded.contains(name) || this.dirsDeselected.contains(name)) {
            return;
        }
        boolean included = false;
        if (isExcluded(name)) {
            this.dirsExcluded.addElement(name);
        } else if (isSelected(name, file)) {
            included = true;
            this.dirsIncluded.addElement(name);
        } else {
            this.dirsDeselected.addElement(name);
        }
        this.everythingIncluded &= included;
        if (fast && couldHoldIncluded(name) && !contentsExcluded(name)) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(name);
            stringBuffer.append(File.separator);
            scandir(file, stringBuffer.toString(), fast);
        }
    }

    protected boolean isIncluded(String name) {
        ensureNonPatternSetsReady();
        if (!isCaseSensitive() ? !this.includeNonPatterns.contains(name.toUpperCase()) : !this.includeNonPatterns.contains(name)) {
            return true;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.includePatterns;
            if (i < strArr.length) {
                if (matchPath(strArr[i], name, isCaseSensitive())) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    protected boolean couldHoldIncluded(String name) {
        int i = 0;
        while (true) {
            String[] strArr = this.includes;
            if (i < strArr.length) {
                if (!matchPatternStart(strArr[i], name, isCaseSensitive()) || !isMorePowerfulThanExcludes(name, this.includes[i]) || !isDeeper(this.includes[i], name)) {
                    i++;
                } else {
                    return true;
                }
            } else {
                return false;
            }
        }
    }

    private boolean isDeeper(String pattern, String name) {
        Vector p = SelectorUtils.tokenizePath(pattern);
        Vector n = SelectorUtils.tokenizePath(name);
        return p.contains("**") || p.size() > n.size();
    }

    private boolean isMorePowerfulThanExcludes(String name, String includepattern) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name);
        stringBuffer.append(File.separator);
        stringBuffer.append("**");
        String soughtexclude = stringBuffer.toString();
        int counter = 0;
        while (true) {
            String[] strArr = this.excludes;
            if (counter < strArr.length) {
                if (!strArr[counter].equals(soughtexclude)) {
                    counter++;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        }
    }

    private boolean contentsExcluded(String name) {
        String string;
        if (name.endsWith(File.separator)) {
            string = name;
        } else {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(name);
            stringBuffer.append(File.separator);
            string = stringBuffer.toString();
        }
        String name2 = string;
        int i = 0;
        while (true) {
            String[] strArr = this.excludes;
            if (i >= strArr.length) {
                return false;
            }
            String e = strArr[i];
            if (!e.endsWith("**") || !SelectorUtils.matchPath(e.substring(0, e.length() - 2), name2, isCaseSensitive())) {
                i++;
            } else {
                return true;
            }
        }
    }

    protected boolean isExcluded(String name) {
        ensureNonPatternSetsReady();
        if (!isCaseSensitive() ? !this.excludeNonPatterns.contains(name.toUpperCase()) : !this.excludeNonPatterns.contains(name)) {
            return true;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.excludePatterns;
            if (i < strArr.length) {
                if (matchPath(strArr[i], name, isCaseSensitive())) {
                    return true;
                }
                i++;
            } else {
                return false;
            }
        }
    }

    protected boolean isSelected(String name, File file) {
        if (this.selectors != null) {
            int i = 0;
            while (true) {
                FileSelector[] fileSelectorArr = this.selectors;
                if (i < fileSelectorArr.length) {
                    if (fileSelectorArr[i].isSelected(this.basedir, name, file)) {
                        i++;
                    } else {
                        return false;
                    }
                } else {
                    return true;
                }
            }
        } else {
            return true;
        }
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getIncludedFiles() {
        String[] files;
        if (this.filesIncluded == null) {
            throw new IllegalStateException();
        }
        files = new String[this.filesIncluded.size()];
        this.filesIncluded.copyInto(files);
        Arrays.sort(files);
        return files;
    }

    public synchronized int getIncludedFilesCount() {
        if (this.filesIncluded == null) {
            throw new IllegalStateException();
        }
        return this.filesIncluded.size();
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getNotIncludedFiles() {
        String[] files;
        slowScan();
        files = new String[this.filesNotIncluded.size()];
        this.filesNotIncluded.copyInto(files);
        return files;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getExcludedFiles() {
        String[] files;
        slowScan();
        files = new String[this.filesExcluded.size()];
        this.filesExcluded.copyInto(files);
        return files;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorScanner
    public synchronized String[] getDeselectedFiles() {
        String[] files;
        slowScan();
        files = new String[this.filesDeselected.size()];
        this.filesDeselected.copyInto(files);
        return files;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getIncludedDirectories() {
        String[] directories;
        if (this.dirsIncluded == null) {
            throw new IllegalStateException();
        }
        directories = new String[this.dirsIncluded.size()];
        this.dirsIncluded.copyInto(directories);
        Arrays.sort(directories);
        return directories;
    }

    public synchronized int getIncludedDirsCount() {
        if (this.dirsIncluded == null) {
            throw new IllegalStateException();
        }
        return this.dirsIncluded.size();
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getNotIncludedDirectories() {
        String[] directories;
        slowScan();
        directories = new String[this.dirsNotIncluded.size()];
        this.dirsNotIncluded.copyInto(directories);
        return directories;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized String[] getExcludedDirectories() {
        String[] directories;
        slowScan();
        directories = new String[this.dirsExcluded.size()];
        this.dirsExcluded.copyInto(directories);
        return directories;
    }

    @Override // org.apache.tools.ant.types.selectors.SelectorScanner
    public synchronized String[] getDeselectedDirectories() {
        String[] directories;
        slowScan();
        directories = new String[this.dirsDeselected.size()];
        this.dirsDeselected.copyInto(directories);
        return directories;
    }

    @Override // org.apache.tools.ant.FileScanner
    public synchronized void addDefaultExcludes() {
        int excludesLength = this.excludes == null ? 0 : this.excludes.length;
        String[] newExcludes = new String[defaultExcludes.size() + excludesLength];
        if (excludesLength > 0) {
            System.arraycopy(this.excludes, 0, newExcludes, 0, excludesLength);
        }
        String[] defaultExcludesTemp = getDefaultExcludes();
        for (int i = 0; i < defaultExcludesTemp.length; i++) {
            newExcludes[i + excludesLength] = defaultExcludesTemp[i].replace('/', File.separatorChar).replace('\\', File.separatorChar);
        }
        this.excludes = newExcludes;
    }

    @Override // org.apache.tools.ant.types.ResourceFactory
    public synchronized Resource getResource(String name) {
        File f;
        f = FILE_UTILS.resolveFile(this.basedir, name);
        return new Resource(name, f.exists(), f.lastModified(), f.isDirectory(), f.length());
    }

    private String[] list(File file) {
        String[] files = (String[]) this.fileListMap.get(file);
        if (files == null && (files = file.list()) != null) {
            this.fileListMap.put(file, files);
        }
        return files;
    }

    private File findFile(File base, String path, boolean cs) {
        return findFile(base, SelectorUtils.tokenizePath(path), cs);
    }

    private File findFile(File base, Vector pathElements, boolean cs) {
        if (pathElements.size() == 0) {
            return base;
        }
        if (!base.isDirectory()) {
            return null;
        }
        String[] files = list(base);
        if (files == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("IO error scanning directory ");
            stringBuffer.append(base.getAbsolutePath());
            throw new BuildException(stringBuffer.toString());
        }
        String current = (String) pathElements.remove(0);
        boolean[] matchCase = cs ? CS_SCAN_ONLY : CS_THEN_NON_CS;
        for (boolean z : matchCase) {
            for (int j = 0; j < files.length; j++) {
                if (z) {
                    if (files[j].equals(current)) {
                        return findFile(new File(base, files[j]), pathElements, cs);
                    }
                } else {
                    if (files[j].equalsIgnoreCase(current)) {
                        return findFile(new File(base, files[j]), pathElements, cs);
                    }
                }
            }
        }
        return null;
    }

    private boolean isSymlink(File base, String path) {
        return isSymlink(base, SelectorUtils.tokenizePath(path));
    }

    private boolean isSymlink(File base, Vector pathElements) {
        if (pathElements.size() > 0) {
            String current = (String) pathElements.remove(0);
            try {
                if (!FILE_UTILS.isSymbolicLink(base, current)) {
                    if (!isSymlink(new File(base, current), pathElements)) {
                        return false;
                    }
                }
                return true;
            } catch (IOException e) {
                System.err.println("IOException caught while checking for links, couldn't get canonical path!");
            }
        }
        return false;
    }

    private boolean hasBeenScanned(String vpath) {
        return !this.scannedDirs.add(vpath);
    }

    Set getScannedDirs() {
        return this.scannedDirs;
    }

    private synchronized void clearCaches() {
        this.fileListMap.clear();
        this.includeNonPatterns.clear();
        this.excludeNonPatterns.clear();
        this.includePatterns = null;
        this.excludePatterns = null;
        this.areNonPatternSetsReady = false;
    }

    private synchronized void ensureNonPatternSetsReady() {
        if (!this.areNonPatternSetsReady) {
            this.includePatterns = fillNonPatternSet(this.includeNonPatterns, this.includes);
            this.excludePatterns = fillNonPatternSet(this.excludeNonPatterns, this.excludes);
            this.areNonPatternSetsReady = true;
        }
    }

    private String[] fillNonPatternSet(Set set, String[] patterns) {
        ArrayList al = new ArrayList(patterns.length);
        for (int i = 0; i < patterns.length; i++) {
            if (!SelectorUtils.hasWildcards(patterns[i])) {
                set.add(isCaseSensitive() ? patterns[i] : patterns[i].toUpperCase());
            } else {
                al.add(patterns[i]);
            }
        }
        return set.size() == 0 ? patterns : (String[]) al.toArray(new String[al.size()]);
    }
}
