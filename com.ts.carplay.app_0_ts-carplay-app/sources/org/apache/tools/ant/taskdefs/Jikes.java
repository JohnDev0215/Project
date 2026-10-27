package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.Project;

/* JADX INFO: loaded from: classes.dex */
public class Jikes {
    protected String command;
    protected JikesOutputParser jop;
    protected Project project;

    protected Jikes(JikesOutputParser jop, String command, Project project) {
        System.err.println("As of Ant 1.2 released in October 2000, the Jikes class");
        System.err.println("is considered to be dead code by the Ant developers and is unmaintained.");
        System.err.println("Don't use it!");
        this.jop = jop;
        this.command = command;
        this.project = project;
    }

    /* JADX WARN: Removed duplicated region for block: B:51:0x0095 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void compile(java.lang.String[] r12) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.tools.ant.taskdefs.Jikes.compile(java.lang.String[]):void");
    }
}
