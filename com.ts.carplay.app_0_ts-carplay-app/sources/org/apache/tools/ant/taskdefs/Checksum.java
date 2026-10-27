package org.apache.tools.ant.taskdefs;

import com.ts.lib.car.VehicleVendorProperty;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Vector;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.taskdefs.condition.Condition;
import org.apache.tools.ant.types.FileSet;

/* JADX INFO: loaded from: classes.dex */
public class Checksum extends MatchingTask implements Condition {
    private String fileext;
    private boolean forceOverwrite;
    private boolean isCondition;
    private MessageDigest messageDigest;
    private String property;
    private File todir;
    private String totalproperty;
    private String verifyProperty;
    private File file = null;
    private String algorithm = "MD5";
    private String provider = null;
    private Map allDigests = new HashMap();
    private Map relativeFilePaths = new HashMap();
    private Vector filesets = new Vector();
    private Hashtable includeFileMap = new Hashtable();
    private int readBufferSize = VehicleVendorProperty.Health.OIL_HINT;

    public void setFile(File file) {
        this.file = file;
    }

    public void setTodir(File todir) {
        this.todir = todir;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public void setFileext(String fileext) {
        this.fileext = fileext;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public void setTotalproperty(String totalproperty) {
        this.totalproperty = totalproperty;
    }

    public void setVerifyproperty(String verifyProperty) {
        this.verifyProperty = verifyProperty;
    }

    public void setForceOverwrite(boolean forceOverwrite) {
        this.forceOverwrite = forceOverwrite;
    }

    public void setReadBufferSize(int size) {
        this.readBufferSize = size;
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        this.isCondition = false;
        boolean value = validateAndExecute();
        if (this.verifyProperty != null) {
            getProject().setNewProperty(this.verifyProperty, new Boolean(value).toString());
        }
    }

    @Override // org.apache.tools.ant.taskdefs.condition.Condition
    public boolean eval() throws BuildException {
        this.isCondition = true;
        return validateAndExecute();
    }

    private boolean validateAndExecute() throws BuildException {
        String savedFileExt = this.fileext;
        if (this.file == null && this.filesets.size() == 0) {
            throw new BuildException("Specify at least one source - a file or a fileset.");
        }
        File file = this.file;
        if (file != null && file.exists() && this.file.isDirectory()) {
            throw new BuildException("Checksum cannot be generated for directories");
        }
        if (this.file != null && this.totalproperty != null) {
            throw new BuildException("File and Totalproperty cannot co-exist.");
        }
        if (this.property != null && this.fileext != null) {
            throw new BuildException("Property and FileExt cannot co-exist.");
        }
        if (this.property != null) {
            if (this.forceOverwrite) {
                throw new BuildException("ForceOverwrite cannot be used when Property is specified");
            }
            if (this.file != null) {
                if (this.filesets.size() > 0) {
                    throw new BuildException("Multiple files cannot be used when Property is specified");
                }
            } else if (this.filesets.size() > 1) {
                throw new BuildException("Multiple files cannot be used when Property is specified");
            }
        }
        if (this.verifyProperty != null) {
            this.isCondition = true;
        }
        if (this.verifyProperty != null && this.forceOverwrite) {
            throw new BuildException("VerifyProperty and ForceOverwrite cannot co-exist.");
        }
        if (this.isCondition && this.forceOverwrite) {
            throw new BuildException("ForceOverwrite cannot be used when conditions are being used.");
        }
        this.messageDigest = null;
        String str = this.provider;
        if (str != null) {
            try {
                this.messageDigest = MessageDigest.getInstance(this.algorithm, str);
            } catch (NoSuchAlgorithmException noalgo) {
                throw new BuildException(noalgo, getLocation());
            } catch (NoSuchProviderException noprovider) {
                throw new BuildException(noprovider, getLocation());
            }
        } else {
            try {
                this.messageDigest = MessageDigest.getInstance(this.algorithm);
            } catch (NoSuchAlgorithmException noalgo2) {
                throw new BuildException(noalgo2, getLocation());
            }
        }
        if (this.messageDigest == null) {
            throw new BuildException("Unable to create Message Digest", getLocation());
        }
        String str2 = this.fileext;
        if (str2 == null) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append(".");
            stringBuffer.append(this.algorithm);
            this.fileext = stringBuffer.toString();
        } else if (str2.trim().length() == 0) {
            throw new BuildException("File extension when specified must not be an empty string");
        }
        try {
            int sizeofFileSet = this.filesets.size();
            for (int i = 0; i < sizeofFileSet; i++) {
                FileSet fs = (FileSet) this.filesets.elementAt(i);
                DirectoryScanner ds = fs.getDirectoryScanner(getProject());
                String[] srcFiles = ds.getIncludedFiles();
                for (int j = 0; j < srcFiles.length; j++) {
                    File src = new File(fs.getDir(getProject()), srcFiles[j]);
                    if (this.totalproperty != null || this.todir != null) {
                        String relativePath = srcFiles[j].replace(File.separatorChar, '/');
                        this.relativeFilePaths.put(src, relativePath);
                    }
                    addToIncludeFileMap(src);
                }
            }
            addToIncludeFileMap(this.file);
            return generateChecksums();
        } finally {
            this.fileext = savedFileExt;
            this.includeFileMap.clear();
        }
    }

    private void addToIncludeFileMap(File file) throws BuildException {
        if (file != null) {
            if (file.exists()) {
                String str = this.property;
                if (str == null) {
                    File checksumFile = getChecksumFile(file);
                    if (this.forceOverwrite || this.isCondition || file.lastModified() > checksumFile.lastModified()) {
                        this.includeFileMap.put(file, checksumFile);
                        return;
                    }
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(file);
                    stringBuffer.append(" omitted as ");
                    stringBuffer.append(checksumFile);
                    stringBuffer.append(" is up to date.");
                    log(stringBuffer.toString(), 3);
                    if (this.totalproperty != null) {
                        try {
                            BufferedReader diskChecksumReader = new BufferedReader(new FileReader(checksumFile));
                            String checksum = diskChecksumReader.readLine();
                            byte[] digest = decodeHex(checksum.toCharArray());
                            this.allDigests.put(file, digest);
                            return;
                        } catch (IOException e) {
                            StringBuffer stringBuffer2 = new StringBuffer();
                            stringBuffer2.append("Couldn't read checksum file ");
                            stringBuffer2.append(checksumFile);
                            throw new BuildException(stringBuffer2.toString(), e);
                        }
                    }
                    return;
                }
                this.includeFileMap.put(file, str);
                return;
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append("Could not find file ");
            stringBuffer3.append(file.getAbsolutePath());
            stringBuffer3.append(" to generate checksum for.");
            String message = stringBuffer3.toString();
            log(message);
            throw new BuildException(message, getLocation());
        }
    }

    private File getChecksumFile(File file) {
        File directory;
        if (this.todir != null) {
            String path = (String) this.relativeFilePaths.get(file);
            directory = new File(this.todir, path).getParentFile();
            directory.mkdirs();
        } else {
            directory = file.getParentFile();
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(file.getName());
        stringBuffer.append(this.fileext);
        File checksumFile = new File(directory, stringBuffer.toString());
        return checksumFile;
    }

    /* JADX WARN: Removed duplicated region for block: B:108:? A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x0176 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x017f A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean generateChecksums() throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 391
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Checksum.generateChecksums():boolean");
    }

    private String createDigestString(byte[] fileDigest) {
        StringBuffer checksumSb = new StringBuffer();
        for (byte b : fileDigest) {
            String hexStr = Integer.toHexString(b & 255);
            if (hexStr.length() < 2) {
                checksumSb.append("0");
            }
            checksumSb.append(hexStr);
        }
        return checksumSb.toString();
    }

    public static byte[] decodeHex(char[] data) throws BuildException {
        int l = data.length;
        if ((l & 1) != 0) {
            throw new BuildException("odd number of characters.");
        }
        byte[] out = new byte[l >> 1];
        int i = 0;
        int j = 0;
        while (j < l) {
            int j2 = j + 1;
            int f = Character.digit(data[j], 16) << 4;
            out[i] = (byte) ((f | Character.digit(data[j2], 16)) & 255);
            i++;
            j = j2 + 1;
        }
        return out;
    }
}
