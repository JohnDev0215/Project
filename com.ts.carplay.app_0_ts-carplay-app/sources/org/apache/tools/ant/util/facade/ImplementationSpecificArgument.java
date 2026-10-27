package org.apache.tools.ant.util.facade;

import org.apache.tools.ant.types.Commandline;

/* JADX INFO: loaded from: classes.dex */
public class ImplementationSpecificArgument extends Commandline.Argument {
    private String impl;

    public void setImplementation(String impl) {
        this.impl = impl;
    }

    public final String[] getParts(String chosenImpl) {
        String str = this.impl;
        if (str == null || str.equals(chosenImpl)) {
            return super.getParts();
        }
        return new String[0];
    }
}
