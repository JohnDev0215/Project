package org.apache.tools.ant.taskdefs;

import org.apache.tools.ant.taskdefs.email.EmailTask;

/* JADX INFO: loaded from: classes.dex */
public class SendEmail extends EmailTask {
    public void setMailport(Integer value) {
        setMailport(value.intValue());
    }
}
