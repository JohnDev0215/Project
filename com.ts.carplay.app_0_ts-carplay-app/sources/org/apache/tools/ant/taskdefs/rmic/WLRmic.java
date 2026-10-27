package org.apache.tools.ant.taskdefs.rmic;

/* JADX INFO: loaded from: classes.dex */
public class WLRmic extends DefaultRmicAdapter {
    static /* synthetic */ Class array$Ljava$lang$String;

    /* JADX WARN: Removed duplicated region for block: B:39:0x00aa  */
    @Override // org.apache.tools.ant.taskdefs.rmic.RmicAdapter
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean execute() throws java.lang.Exception {
        /*
            r10 = this;
            org.apache.tools.ant.taskdefs.Rmic r0 = r10.getRmic()
            java.lang.String r1 = "Using WebLogic rmic"
            r2 = 3
            r0.log(r1, r2)
            java.lang.String r0 = "-noexit"
            java.lang.String[] r0 = new java.lang.String[]{r0}
            org.apache.tools.ant.types.Commandline r0 = r10.setupRmicCommand(r0)
            r1 = 0
            r2 = 0
            r3 = 0
            org.apache.tools.ant.taskdefs.Rmic r4 = r10.getRmic()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            org.apache.tools.ant.types.Path r4 = r4.getClasspath()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            java.lang.String r5 = "weblogic.rmic"
            r6 = 1
            if (r4 != 0) goto L2a
            java.lang.Class r4 = java.lang.Class.forName(r5)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            r2 = r4
            goto L44
        L2a:
            org.apache.tools.ant.taskdefs.Rmic r4 = r10.getRmic()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            org.apache.tools.ant.Project r4 = r4.getProject()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            org.apache.tools.ant.taskdefs.Rmic r7 = r10.getRmic()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            org.apache.tools.ant.types.Path r7 = r7.getClasspath()     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            org.apache.tools.ant.AntClassLoader r4 = r4.createClassLoader(r7)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            r1 = r4
            java.lang.Class r4 = java.lang.Class.forName(r5, r6, r1)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            r2 = r4
        L44:
            java.lang.String r4 = "main"
            java.lang.Class[] r5 = new java.lang.Class[r6]     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            java.lang.Class r7 = org.apache.tools.ant.taskdefs.rmic.WLRmic.array$Ljava$lang$String     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            if (r7 != 0) goto L55
            java.lang.String r7 = "[Ljava.lang.String;"
            java.lang.Class r7 = class$(r7)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            org.apache.tools.ant.taskdefs.rmic.WLRmic.array$Ljava$lang$String = r7     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            goto L57
        L55:
            java.lang.Class r7 = org.apache.tools.ant.taskdefs.rmic.WLRmic.array$Ljava$lang$String     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
        L57:
            r8 = 0
            r5[r8] = r7     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            java.lang.reflect.Method r4 = r2.getMethod(r4, r5)     // Catch: java.lang.Throwable -> L79 java.lang.Exception -> L7b java.lang.ClassNotFoundException -> L96
            java.lang.Object[] r5 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L71 java.lang.ClassNotFoundException -> L76 java.lang.Exception -> L7b
            java.lang.String[] r7 = r0.getArguments()     // Catch: java.lang.Throwable -> L71 java.lang.ClassNotFoundException -> L76 java.lang.Exception -> L7b
            r5[r8] = r7     // Catch: java.lang.Throwable -> L71 java.lang.ClassNotFoundException -> L76 java.lang.Exception -> L7b
            r4.invoke(r3, r5)     // Catch: java.lang.Throwable -> L71 java.lang.ClassNotFoundException -> L76 java.lang.Exception -> L7b
            r3 = r4
            if (r1 == 0) goto L6f
            r1.cleanup()
        L6f:
            return r6
        L71:
            r3 = move-exception
            r9 = r4
            r4 = r3
            r3 = r9
            goto La8
        L76:
            r2 = move-exception
            r3 = r4
            goto L97
        L79:
            r4 = move-exception
            goto La8
        L7b:
            r3 = move-exception
            boolean r4 = r3 instanceof org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L94
            if (r4 == 0) goto L84
            r4 = r3
            org.apache.tools.ant.BuildException r4 = (org.apache.tools.ant.BuildException) r4     // Catch: java.lang.Throwable -> L94
            throw r4     // Catch: java.lang.Throwable -> L94
        L84:
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> L94
            java.lang.String r5 = "Error starting WebLogic rmic: "
            org.apache.tools.ant.taskdefs.Rmic r6 = r10.getRmic()     // Catch: java.lang.Throwable -> L94
            org.apache.tools.ant.Location r6 = r6.getLocation()     // Catch: java.lang.Throwable -> L94
            r4.<init>(r5, r3, r6)     // Catch: java.lang.Throwable -> L94
            throw r4     // Catch: java.lang.Throwable -> L94
        L94:
            r4 = move-exception
            goto La8
        L96:
            r2 = move-exception
        L97:
            org.apache.tools.ant.BuildException r4 = new org.apache.tools.ant.BuildException     // Catch: java.lang.Throwable -> La7
            java.lang.String r5 = "Cannot use WebLogic rmic, as it is not available.  A common solution is to set the environment variable CLASSPATH."
            org.apache.tools.ant.taskdefs.Rmic r6 = r10.getRmic()     // Catch: java.lang.Throwable -> La7
            org.apache.tools.ant.Location r6 = r6.getLocation()     // Catch: java.lang.Throwable -> La7
            r4.<init>(r5, r6)     // Catch: java.lang.Throwable -> La7
            throw r4     // Catch: java.lang.Throwable -> La7
        La7:
            r4 = move-exception
        La8:
            if (r1 == 0) goto Lad
            r1.cleanup()
        Lad:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.rmic.WLRmic.execute():boolean");
    }

    static /* synthetic */ Class class$(String x0) {
        try {
            return Class.forName(x0);
        } catch (ClassNotFoundException x1) {
            throw new NoClassDefFoundError(x1.getMessage());
        }
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    public String getStubClassSuffix() {
        return "_WLStub";
    }

    @Override // org.apache.tools.ant.taskdefs.rmic.DefaultRmicAdapter
    public String getSkelClassSuffix() {
        return "_WLSkel";
    }
}
