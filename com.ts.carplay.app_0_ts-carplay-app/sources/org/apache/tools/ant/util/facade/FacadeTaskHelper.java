package org.apache.tools.ant.util.facade;

import java.util.Enumeration;
import java.util.Vector;

/* JADX INFO: loaded from: classes.dex */
public class FacadeTaskHelper {
    private Vector args;
    private String defaultValue;
    private String magicValue;
    private String userChoice;

    public FacadeTaskHelper(String defaultValue) {
        this(defaultValue, null);
    }

    public FacadeTaskHelper(String defaultValue, String magicValue) {
        this.args = new Vector();
        this.defaultValue = defaultValue;
        this.magicValue = magicValue;
    }

    public void setMagicValue(String magicValue) {
        this.magicValue = magicValue;
    }

    public void setImplementation(String userChoice) {
        this.userChoice = userChoice;
    }

    public String getImplementation() {
        String str = this.userChoice;
        if (str != null) {
            return str;
        }
        String str2 = this.magicValue;
        return str2 != null ? str2 : this.defaultValue;
    }

    public String getExplicitChoice() {
        return this.userChoice;
    }

    public void addImplementationArgument(ImplementationSpecificArgument arg) {
        this.args.addElement(arg);
    }

    public String[] getArgs() {
        Vector tmp = new Vector(this.args.size());
        Enumeration e = this.args.elements();
        while (e.hasMoreElements()) {
            ImplementationSpecificArgument arg = (ImplementationSpecificArgument) e.nextElement();
            String[] curr = arg.getParts(getImplementation());
            for (String str : curr) {
                tmp.addElement(str);
            }
        }
        String[] res = new String[tmp.size()];
        tmp.copyInto(res);
        return res;
    }

    public boolean hasBeenSet() {
        return (this.userChoice == null && this.magicValue == null) ? false : true;
    }
}
