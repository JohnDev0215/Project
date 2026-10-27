package org.apache.tools.ant.taskdefs.rmic;

import java.io.File;
import java.util.Random;
import java.util.Vector;
import org.apache.tools.ant.taskdefs.Rmic;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.util.FileNameMapper;
import org.apache.tools.ant.util.JavaEnvUtils;

/* JADX INFO: loaded from: classes.dex */
public abstract class DefaultRmicAdapter implements RmicAdapter {
    private static final Random rand = new Random();
    private Rmic attributes;
    private FileNameMapper mapper;

    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    public void setRmic(Rmic attributes) {
        this.attributes = attributes;
        this.mapper = new RmicFileNameMapper();
    }

    public Rmic getRmic() {
        return this.attributes;
    }

    protected String getStubClassSuffix() {
        return "_Stub";
    }

    protected String getSkelClassSuffix() {
        return "_Skel";
    }

    protected String getTieClassSuffix() {
        return "_Tie";
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    public FileNameMapper getMapper() {
        return this.mapper;
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    public Path getClasspath() {
        return getCompileClasspath();
    }

    protected Path getCompileClasspath() {
        Path classpath = new Path(this.attributes.getProject());
        classpath.setLocation(this.attributes.getBase());
        Path cp = this.attributes.getClasspath();
        if (cp == null) {
            cp = new Path(this.attributes.getProject());
        }
        if (this.attributes.getIncludeantruntime()) {
            classpath.addExisting(cp.concatSystemClasspath("last"));
        } else {
            classpath.addExisting(cp.concatSystemClasspath("ignore"));
        }
        if (this.attributes.getIncludejavaruntime()) {
            classpath.addJavaRuntime();
        }
        return classpath;
    }

    protected Commandline setupRmicCommand() {
        return setupRmicCommand(null);
    }

    protected Commandline setupRmicCommand(String[] options) {
        Commandline cmd = new Commandline();
        if (options != null) {
            for (String str : options) {
                cmd.createArgument().setValue(str);
            }
        }
        Path classpath = getCompileClasspath();
        cmd.createArgument().setValue("-d");
        cmd.createArgument().setFile(this.attributes.getBase());
        if (this.attributes.getExtdirs() != null) {
            if (JavaEnvUtils.isJavaVersion("1.1")) {
                classpath.addExtdirs(this.attributes.getExtdirs());
            } else {
                cmd.createArgument().setValue("-extdirs");
                cmd.createArgument().setPath(this.attributes.getExtdirs());
            }
        }
        cmd.createArgument().setValue("-classpath");
        cmd.createArgument().setPath(classpath);
        String stubVersion = this.attributes.getStubVersion();
        if (stubVersion != null) {
            if ("1.1".equals(stubVersion)) {
                cmd.createArgument().setValue("-v1.1");
            } else if ("1.2".equals(stubVersion)) {
                cmd.createArgument().setValue("-v1.2");
            } else {
                cmd.createArgument().setValue("-vcompat");
            }
        }
        if (this.attributes.getSourceBase() != null) {
            cmd.createArgument().setValue("-keepgenerated");
        }
        if (this.attributes.getIiop()) {
            this.attributes.log("IIOP has been turned on.", 2);
            cmd.createArgument().setValue("-iiop");
            if (this.attributes.getIiopopts() != null) {
                Rmic rmic = this.attributes;
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append("IIOP Options: ");
                stringBuffer.append(this.attributes.getIiopopts());
                rmic.log(stringBuffer.toString(), 2);
                cmd.createArgument().setValue(this.attributes.getIiopopts());
            }
        }
        if (this.attributes.getIdl()) {
            cmd.createArgument().setValue("-idl");
            this.attributes.log("IDL has been turned on.", 2);
            if (this.attributes.getIdlopts() != null) {
                cmd.createArgument().setValue(this.attributes.getIdlopts());
                Rmic rmic2 = this.attributes;
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("IDL Options: ");
                stringBuffer2.append(this.attributes.getIdlopts());
                rmic2.log(stringBuffer2.toString(), 2);
            }
        }
        if (this.attributes.getDebug()) {
            cmd.createArgument().setValue("-g");
        }
        cmd.addArguments(this.attributes.getCurrentCompilerArgs());
        logAndAddFilesToCompile(cmd);
        return cmd;
    }

    protected void logAndAddFilesToCompile(Commandline cmd) {
        Vector compileList = this.attributes.getCompileList();
        Rmic rmic = this.attributes;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Compilation ");
        stringBuffer.append(cmd.describeArguments());
        rmic.log(stringBuffer.toString(), 3);
        StringBuffer niceSourceList = new StringBuffer("File");
        if (compileList.size() != 1) {
            niceSourceList.append("s");
        }
        niceSourceList.append(" to be compiled:");
        for (int i = 0; i < compileList.size(); i++) {
            String arg = (String) compileList.elementAt(i);
            cmd.createArgument().setValue(arg);
            StringBuffer stringBuffer2 = new StringBuffer();
            stringBuffer2.append("    ");
            stringBuffer2.append(arg);
            niceSourceList.append(stringBuffer2.toString());
        }
        this.attributes.log(niceSourceList.toString(), 3);
    }

    private class RmicFileNameMapper implements FileNameMapper {
        RmicFileNameMapper() {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setFrom(String s) {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public void setTo(String s) {
        }

        @Override // org.apache.tools.ant.util.FileNameMapper
        public String[] mapFileName(String name) {
            String[] target;
            int index;
            String[] target2;
            StringBuffer stringBuffer;
            String base;
            int iIndex;
            StringBuffer stringBuffer2;
            if (name == null || !name.endsWith(".class")) {
                return null;
            }
            StringBuffer stringBuffer3 = new StringBuffer();
            stringBuffer3.append(DefaultRmicAdapter.this.getStubClassSuffix());
            stringBuffer3.append(".class");
            if (name.endsWith(stringBuffer3.toString())) {
                return null;
            }
            StringBuffer stringBuffer4 = new StringBuffer();
            stringBuffer4.append(DefaultRmicAdapter.this.getSkelClassSuffix());
            stringBuffer4.append(".class");
            if (name.endsWith(stringBuffer4.toString())) {
                return null;
            }
            StringBuffer stringBuffer5 = new StringBuffer();
            stringBuffer5.append(DefaultRmicAdapter.this.getTieClassSuffix());
            stringBuffer5.append(".class");
            if (name.endsWith(stringBuffer5.toString())) {
                return null;
            }
            String base2 = name.substring(0, name.length() - 6);
            String classname = base2.replace(File.separatorChar, '.');
            if (DefaultRmicAdapter.this.attributes.getVerify() && !DefaultRmicAdapter.this.attributes.isValidRmiRemote(classname)) {
                return null;
            }
            StringBuffer stringBuffer6 = new StringBuffer();
            stringBuffer6.append(name);
            stringBuffer6.append(".tmp.");
            stringBuffer6.append(DefaultRmicAdapter.rand.nextLong());
            String[] target3 = {stringBuffer6.toString()};
            if (!DefaultRmicAdapter.this.attributes.getIiop() && !DefaultRmicAdapter.this.attributes.getIdl()) {
                if ("1.2".equals(DefaultRmicAdapter.this.attributes.getStubVersion())) {
                    StringBuffer stringBuffer7 = new StringBuffer();
                    stringBuffer7.append(base2);
                    stringBuffer7.append(DefaultRmicAdapter.this.getStubClassSuffix());
                    stringBuffer7.append(".class");
                    return new String[]{stringBuffer7.toString()};
                }
                StringBuffer stringBuffer8 = new StringBuffer();
                stringBuffer8.append(base2);
                stringBuffer8.append(DefaultRmicAdapter.this.getStubClassSuffix());
                stringBuffer8.append(".class");
                StringBuffer stringBuffer9 = new StringBuffer();
                stringBuffer9.append(base2);
                stringBuffer9.append(DefaultRmicAdapter.this.getSkelClassSuffix());
                stringBuffer9.append(".class");
                return new String[]{stringBuffer8.toString(), stringBuffer9.toString()};
            }
            if (DefaultRmicAdapter.this.attributes.getIdl()) {
                target = target3;
            } else {
                int lastSlash = base2.lastIndexOf(File.separatorChar);
                String dirname = "";
                if (lastSlash == -1) {
                    index = 0;
                } else {
                    index = lastSlash + 1;
                    dirname = base2.substring(0, index);
                }
                String filename = base2.substring(index);
                try {
                    try {
                        try {
                            try {
                                Class<?> clsLoadClass = DefaultRmicAdapter.this.attributes.getLoader().loadClass(classname);
                                try {
                                    try {
                                        if (clsLoadClass.isInterface()) {
                                            try {
                                                try {
                                                    target2 = new String[1];
                                                    stringBuffer = new StringBuffer();
                                                    stringBuffer.append(dirname);
                                                    stringBuffer.append("_");
                                                    stringBuffer.append(filename);
                                                    stringBuffer.append(DefaultRmicAdapter.this.getStubClassSuffix());
                                                    stringBuffer.append(".class");
                                                } catch (ClassNotFoundException e) {
                                                    target = target3;
                                                    Rmic rmic = DefaultRmicAdapter.this.attributes;
                                                    StringBuffer stringBuffer10 = new StringBuffer();
                                                    stringBuffer10.append("Unable to verify class ");
                                                    stringBuffer10.append(classname);
                                                    stringBuffer10.append(". It could not be found.");
                                                    rmic.log(stringBuffer10.toString(), 1);
                                                    return target;
                                                } catch (NoClassDefFoundError e2) {
                                                    target = target3;
                                                    Rmic rmic2 = DefaultRmicAdapter.this.attributes;
                                                    StringBuffer stringBuffer11 = new StringBuffer();
                                                    stringBuffer11.append("Unable to verify class ");
                                                    stringBuffer11.append(classname);
                                                    stringBuffer11.append(". It is not defined.");
                                                    rmic2.log(stringBuffer11.toString(), 1);
                                                    return target;
                                                }
                                                try {
                                                    target2[0] = stringBuffer.toString();
                                                    return target2;
                                                } catch (Throwable th) {
                                                    t = th;
                                                    target = target3;
                                                    Rmic rmic3 = DefaultRmicAdapter.this.attributes;
                                                    StringBuffer stringBuffer12 = new StringBuffer();
                                                    stringBuffer12.append("Unable to verify class ");
                                                    stringBuffer12.append(classname);
                                                    stringBuffer12.append(". Loading caused Exception: ");
                                                    stringBuffer12.append(t.getMessage());
                                                    rmic3.log(stringBuffer12.toString(), 1);
                                                    return target;
                                                }
                                            } catch (Throwable th2) {
                                                t = th2;
                                                target = target3;
                                            }
                                        } else {
                                            try {
                                                try {
                                                    Class interf = DefaultRmicAdapter.this.attributes.getRemoteInterface(clsLoadClass);
                                                    try {
                                                        String iName = interf.getName();
                                                        int lastDot = iName.lastIndexOf(".");
                                                        if (lastDot == -1) {
                                                            iIndex = 0;
                                                            base = "";
                                                        } else {
                                                            int iIndex2 = lastDot + 1;
                                                            try {
                                                                String iDir = iName.substring(0, iIndex2);
                                                                base = iDir.replace('.', File.separatorChar);
                                                                iIndex = iIndex2;
                                                            } catch (ClassNotFoundException e3) {
                                                                target = target3;
                                                                Rmic rmic4 = DefaultRmicAdapter.this.attributes;
                                                                StringBuffer stringBuffer102 = new StringBuffer();
                                                                stringBuffer102.append("Unable to verify class ");
                                                                stringBuffer102.append(classname);
                                                                stringBuffer102.append(". It could not be found.");
                                                                rmic4.log(stringBuffer102.toString(), 1);
                                                                return target;
                                                            } catch (NoClassDefFoundError e4) {
                                                                target = target3;
                                                                Rmic rmic22 = DefaultRmicAdapter.this.attributes;
                                                                StringBuffer stringBuffer112 = new StringBuffer();
                                                                stringBuffer112.append("Unable to verify class ");
                                                                stringBuffer112.append(classname);
                                                                stringBuffer112.append(". It is not defined.");
                                                                rmic22.log(stringBuffer112.toString(), 1);
                                                                return target;
                                                            } catch (Throwable th3) {
                                                                t = th3;
                                                                target = target3;
                                                                Rmic rmic32 = DefaultRmicAdapter.this.attributes;
                                                                StringBuffer stringBuffer122 = new StringBuffer();
                                                                stringBuffer122.append("Unable to verify class ");
                                                                stringBuffer122.append(classname);
                                                                stringBuffer122.append(". Loading caused Exception: ");
                                                                stringBuffer122.append(t.getMessage());
                                                                rmic32.log(stringBuffer122.toString(), 1);
                                                                return target;
                                                            }
                                                        }
                                                        String[] target4 = new String[2];
                                                        target = target3;
                                                        try {
                                                            stringBuffer2 = new StringBuffer();
                                                            stringBuffer2.append(dirname);
                                                            stringBuffer2.append("_");
                                                            stringBuffer2.append(filename);
                                                        } catch (ClassNotFoundException e5) {
                                                        } catch (NoClassDefFoundError e6) {
                                                        } catch (Throwable th4) {
                                                            t = th4;
                                                        }
                                                        try {
                                                            stringBuffer2.append(DefaultRmicAdapter.this.getTieClassSuffix());
                                                            stringBuffer2.append(".class");
                                                            target4[0] = stringBuffer2.toString();
                                                            StringBuffer stringBuffer13 = new StringBuffer();
                                                            stringBuffer13.append(base);
                                                            stringBuffer13.append("_");
                                                            stringBuffer13.append(iName.substring(iIndex));
                                                            stringBuffer13.append(DefaultRmicAdapter.this.getStubClassSuffix());
                                                            stringBuffer13.append(".class");
                                                            target4[1] = stringBuffer13.toString();
                                                            return target4;
                                                        } catch (ClassNotFoundException e7) {
                                                            Rmic rmic42 = DefaultRmicAdapter.this.attributes;
                                                            StringBuffer stringBuffer1022 = new StringBuffer();
                                                            stringBuffer1022.append("Unable to verify class ");
                                                            stringBuffer1022.append(classname);
                                                            stringBuffer1022.append(". It could not be found.");
                                                            rmic42.log(stringBuffer1022.toString(), 1);
                                                            return target;
                                                        } catch (NoClassDefFoundError e8) {
                                                            Rmic rmic222 = DefaultRmicAdapter.this.attributes;
                                                            StringBuffer stringBuffer1122 = new StringBuffer();
                                                            stringBuffer1122.append("Unable to verify class ");
                                                            stringBuffer1122.append(classname);
                                                            stringBuffer1122.append(". It is not defined.");
                                                            rmic222.log(stringBuffer1122.toString(), 1);
                                                            return target;
                                                        } catch (Throwable th5) {
                                                            t = th5;
                                                            Rmic rmic322 = DefaultRmicAdapter.this.attributes;
                                                            StringBuffer stringBuffer1222 = new StringBuffer();
                                                            stringBuffer1222.append("Unable to verify class ");
                                                            stringBuffer1222.append(classname);
                                                            stringBuffer1222.append(". Loading caused Exception: ");
                                                            stringBuffer1222.append(t.getMessage());
                                                            rmic322.log(stringBuffer1222.toString(), 1);
                                                            return target;
                                                        }
                                                    } catch (Throwable th6) {
                                                        t = th6;
                                                        target = target3;
                                                    }
                                                } catch (Throwable th7) {
                                                    t = th7;
                                                    target = target3;
                                                }
                                            } catch (Throwable th8) {
                                                t = th8;
                                                target = target3;
                                            }
                                        }
                                    } catch (Throwable th9) {
                                        t = th9;
                                        target = target3;
                                    }
                                } catch (NoClassDefFoundError e9) {
                                    target = target3;
                                }
                            } catch (NoClassDefFoundError e10) {
                                target = target3;
                            }
                        } catch (NoClassDefFoundError e11) {
                            target = target3;
                        }
                    } catch (ClassNotFoundException e12) {
                        target = target3;
                    }
                } catch (Throwable th10) {
                    t = th10;
                    target = target3;
                }
            }
            return target;
        }
    }
}
