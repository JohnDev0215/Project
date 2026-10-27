package org.apache.tools.ant.taskdefs;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DirectoryScanner;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public class SignJar extends Task {
    protected String alias;
    protected Vector filesets = new Vector();
    protected boolean internalsf;
    protected File jar;
    protected String keypass;
    private String keystore;
    protected boolean lazy;
    private String maxMemory;
    protected boolean sectionsonly;
    protected String sigfile;
    protected File signedjar;
    protected String storepass;
    protected String storetype;
    protected boolean verbose;

    public void setMaxmemory(String max) {
        this.maxMemory = max;
    }

    public void setJar(File jar) {
        this.jar = jar;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setKeystore(String keystore) {
        this.keystore = keystore;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }

    public void setStoretype(String storetype) {
        this.storetype = storetype;
    }

    public void setKeypass(String keypass) {
        this.keypass = keypass;
    }

    public void setSigfile(String sigfile) {
        this.sigfile = sigfile;
    }

    public void setSignedjar(File signedjar) {
        this.signedjar = signedjar;
    }

    public void setVerbose(boolean verbose) {
        this.verbose = verbose;
    }

    public void setInternalsf(boolean internalsf) {
        this.internalsf = internalsf;
    }

    public void setSectionsonly(boolean sectionsonly) {
        this.sectionsonly = sectionsonly;
    }

    public void setLazy(boolean lazy) {
        this.lazy = lazy;
    }

    public void addFileset(FileSet set) {
        this.filesets.addElement(set);
    }

    @Override // org.apache.tools.ant.Task
    public void execute() throws BuildException {
        if (this.jar == null && this.filesets.size() == 0) {
            throw new BuildException("jar must be set through jar attribute or nested filesets");
        }
        if (this.jar != null) {
            if (this.filesets.size() != 0) {
                log("nested filesets will be ignored if the jar attribute has been specified.", 1);
            }
            doOneJar(this.jar, this.signedjar);
            return;
        }
        for (int i = 0; i < this.filesets.size(); i++) {
            FileSet fs = (FileSet) this.filesets.elementAt(i);
            DirectoryScanner ds = fs.getDirectoryScanner(getProject());
            String[] jarFiles = ds.getIncludedFiles();
            for (String str : jarFiles) {
                doOneJar(new File(fs.getDir(getProject()), str), null);
            }
        }
    }

    private void doOneJar(File jarSource, File jarTarget) throws BuildException {
        if (this.alias == null) {
            throw new BuildException("alias attribute must be set");
        }
        if (this.storepass == null) {
            throw new BuildException("storepass attribute must be set");
        }
        if (isUpToDate(jarSource, jarTarget)) {
            return;
        }
        ExecTask cmd = (ExecTask) getProject().createTask("exec");
        cmd.setExecutable(JavaEnvUtils.getJdkExecutable("jarsigner"));
        if (this.maxMemory != null) {
            Commandline.Argument argumentCreateArg = cmd.createArg();
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("-J-Xmx");
            stringBuffer.append(this.maxMemory);
            argumentCreateArg.setValue(stringBuffer.toString());
        }
        if (this.keystore != null) {
            File keystoreFile = getProject().resolveFile(this.keystore);
            if (keystoreFile.exists()) {
                cmd.createArg().setValue("-keystore");
                cmd.createArg().setValue(keystoreFile.getPath());
            } else {
                cmd.createArg().setValue("-keystore");
                cmd.createArg().setValue(this.keystore);
            }
        }
        if (this.storepass != null) {
            cmd.createArg().setValue("-storepass");
            cmd.createArg().setValue(this.storepass);
        }
        if (this.storetype != null) {
            cmd.createArg().setValue("-storetype");
            cmd.createArg().setValue(this.storetype);
        }
        if (this.keypass != null) {
            cmd.createArg().setValue("-keypass");
            cmd.createArg().setValue(this.keypass);
        }
        if (this.sigfile != null) {
            cmd.createArg().setValue("-sigfile");
            cmd.createArg().setValue(this.sigfile);
        }
        if (jarTarget != null) {
            cmd.createArg().setValue("-signedjar");
            cmd.createArg().setValue(jarTarget.toString());
        }
        if (this.verbose) {
            cmd.createArg().setValue("-verbose");
        }
        if (this.internalsf) {
            cmd.createArg().setValue("-internalsf");
        }
        if (this.sectionsonly) {
            cmd.createArg().setValue("-sectionsonly");
        }
        cmd.createArg().setValue(jarSource.toString());
        cmd.createArg().setValue(this.alias);
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("Signing JAR: ");
        stringBuffer2.append(jarSource.getAbsolutePath());
        log(stringBuffer2.toString());
        cmd.setFailonerror(true);
        cmd.setTaskName(getTaskName());
        cmd.execute();
    }

    protected boolean isUpToDate(File jarFile, File signedjarFile) {
        if (jarFile == null) {
            return false;
        }
        if (signedjarFile != null) {
            if (jarFile.exists() && signedjarFile.exists() && !jarFile.equals(signedjarFile) && signedjarFile.lastModified() > jarFile.lastModified()) {
                return true;
            }
        } else if (this.lazy) {
            return isSigned(jarFile);
        }
        return false;
    }

    protected boolean isSigned(File file) throws Throwable {
        ZipFile zipFile;
        if (!file.exists()) {
            return false;
        }
        ZipFile zipFile2 = null;
        try {
            zipFile = new ZipFile(file);
        } catch (IOException e) {
        } catch (Throwable th) {
            th = th;
        }
        try {
            if (this.alias != null) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("META-INF/");
                stringBuffer.append(this.alias.toUpperCase());
                stringBuffer.append(".SF");
                boolean z = zipFile.getEntry(stringBuffer.toString()) != null;
                try {
                    zipFile.close();
                } catch (IOException e2) {
                }
                return z;
            }
            Enumeration<? extends ZipEntry> enumerationEntries = zipFile.entries();
            while (enumerationEntries.hasMoreElements()) {
                String name = enumerationEntries.nextElement().getName();
                if (name.startsWith("META-INF/") && name.endsWith(".SF")) {
                    try {
                        zipFile.close();
                    } catch (IOException e3) {
                    }
                    return true;
                }
            }
            try {
                zipFile.close();
            } catch (IOException e4) {
            }
            return false;
        } catch (IOException e5) {
            zipFile2 = zipFile;
            if (zipFile2 != null) {
                try {
                    zipFile2.close();
                } catch (IOException e6) {
                }
            }
            return false;
        } catch (Throwable th2) {
            th = th2;
            zipFile2 = zipFile;
            if (zipFile2 != null) {
                try {
                    zipFile2.close();
                } catch (IOException e7) {
                }
            }
            throw th;
        }
    }
}
