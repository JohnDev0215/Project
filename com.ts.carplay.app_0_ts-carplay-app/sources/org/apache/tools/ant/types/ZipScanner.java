package org.apache.tools.ant.types;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;
import java.util.zip.ZipException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

/* JADX INFO: loaded from: classes.dex */
public class ZipScanner extends DirectoryScanner {
    private String encoding;
    private Resource lastScannedResource;
    private Hashtable myentries;
    protected File srcFile;

    public void setSrc(File srcFile) {
        this.srcFile = srcFile;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getIncludedFiles() {
        if (this.srcFile != null) {
            Vector myvector = new Vector();
            scanme();
            Enumeration e = this.myentries.elements();
            while (e.hasMoreElements()) {
                Resource myresource = (Resource) e.nextElement();
                if (!myresource.isDirectory() && match(myresource.getName())) {
                    myvector.addElement(myresource.getName());
                }
            }
            String[] files = new String[myvector.size()];
            myvector.copyInto(files);
            Arrays.sort(files);
            return files;
        }
        return super.getIncludedFiles();
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.FileScanner
    public String[] getIncludedDirectories() {
        if (this.srcFile != null) {
            Vector myvector = new Vector();
            scanme();
            Enumeration e = this.myentries.elements();
            while (e.hasMoreElements()) {
                Resource myresource = (Resource) e.nextElement();
                if (myresource.isDirectory() && match(myresource.getName())) {
                    myvector.addElement(myresource.getName());
                }
            }
            String[] files = new String[myvector.size()];
            myvector.copyInto(files);
            Arrays.sort(files);
            return files;
        }
        return super.getIncludedDirectories();
    }

    public void init() {
        if (this.includes == null) {
            this.includes = new String[1];
            this.includes[0] = "**";
        }
        if (this.excludes == null) {
            this.excludes = new String[0];
        }
    }

    public boolean match(String path) {
        String vpath = path.replace('/', File.separatorChar).replace('\\', File.separatorChar);
        return isIncluded(vpath) && !isExcluded(vpath);
    }

    @Override // org.apache.tools.ant.DirectoryScanner, org.apache.tools.ant.types.ResourceFactory
    public Resource getResource(String name) {
        if (this.srcFile == null) {
            return super.getResource(name);
        }
        if (name.equals("")) {
            return new Resource("", true, Long.MAX_VALUE, true);
        }
        scanme();
        if (this.myentries.containsKey(name)) {
            return (Resource) this.myentries.get(name);
        }
        Hashtable hashtable = this.myentries;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(name);
        stringBuffer.append("/");
        if (hashtable.containsKey(stringBuffer.toString())) {
            Hashtable hashtable2 = this.myentries;
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append(name);
            stringBuffer2.append("/");
            return (Resource) hashtable2.get(stringBuffer2.toString());
        }
        return new Resource(name);
    }

    private void scanme() {
        Resource thisresource = new Resource(this.srcFile.getAbsolutePath(), this.srcFile.exists(), this.srcFile.lastModified());
        Resource resource = this.lastScannedResource;
        if (resource != null && resource.getName().equals(thisresource.getName()) && this.lastScannedResource.getLastModified() == thisresource.getLastModified()) {
            return;
        }
        ZipFile zf = null;
        this.myentries = new Hashtable();
        try {
            try {
                try {
                    ZipFile zf2 = new ZipFile(this.srcFile, this.encoding);
                    Enumeration e = zf2.getEntries();
                    while (e.hasMoreElements()) {
                        ZipEntry entry = (ZipEntry) e.nextElement();
                        this.myentries.put(new String(entry.getName()), new Resource(entry.getName(), true, entry.getTime(), entry.isDirectory(), entry.getSize()));
                    }
                    try {
                        zf2.close();
                    } catch (IOException e2) {
                    }
                    this.lastScannedResource = thisresource;
                } catch (ZipException ex) {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append("problem reading ");
                    stringBuffer.append(this.srcFile);
                    throw new BuildException(stringBuffer.toString(), ex);
                }
            } catch (IOException ex2) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("problem opening ");
                stringBuffer2.append(this.srcFile);
                throw new BuildException(stringBuffer2.toString(), ex2);
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    zf.close();
                } catch (IOException e3) {
                }
            }
            throw th;
        }
    }
}
