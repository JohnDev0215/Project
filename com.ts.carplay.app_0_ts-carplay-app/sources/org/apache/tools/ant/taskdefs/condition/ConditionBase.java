package org.apache.tools.ant.taskdefs.condition;

import java.util.Enumeration;
import java.util.Vector;
import org.apache.tools.ant.ProjectComponent;
import org.apache.tools.ant.taskdefs.Available;
import org.apache.tools.ant.taskdefs.Checksum;
import org.apache.tools.ant.taskdefs.UpToDate;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConditionBase extends ProjectComponent {
    private Vector conditions = new Vector();

    protected int countConditions() {
        return this.conditions.size();
    }

    protected final Enumeration getConditions() {
        return this.conditions.elements();
    }

    public void addAvailable(Available a) {
        this.conditions.addElement(a);
    }

    public void addChecksum(Checksum c) {
        this.conditions.addElement(c);
    }

    public void addUptodate(UpToDate u) {
        this.conditions.addElement(u);
    }

    public void addNot(Not n) {
        this.conditions.addElement(n);
    }

    public void addAnd(And a) {
        this.conditions.addElement(a);
    }

    public void addOr(Or o) {
        this.conditions.addElement(o);
    }

    public void addEquals(Equals e) {
        this.conditions.addElement(e);
    }

    public void addOs(Os o) {
        this.conditions.addElement(o);
    }

    public void addIsSet(IsSet i) {
        this.conditions.addElement(i);
    }

    public void addHttp(Http h) {
        this.conditions.addElement(h);
    }

    public void addSocket(Socket s) {
        this.conditions.addElement(s);
    }

    public void addFilesMatch(FilesMatch test) {
        this.conditions.addElement(test);
    }

    public void addContains(Contains test) {
        this.conditions.addElement(test);
    }

    public void addIsTrue(IsTrue test) {
        this.conditions.addElement(test);
    }

    public void addIsFalse(IsFalse test) {
        this.conditions.addElement(test);
    }

    public void addIsReference(IsReference i) {
        this.conditions.addElement(i);
    }

    public void add(Condition c) {
        this.conditions.addElement(c);
    }
}
