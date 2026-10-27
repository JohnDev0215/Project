package net.lingala.zip4j.tasks;

import net.lingala.zip4j.model.Zip4jConfig;

/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractZipTaskParameters {
    public Zip4jConfig zip4jConfig;

    public AbstractZipTaskParameters(Zip4jConfig zip4jConfig) {
        this.zip4jConfig = zip4jConfig;
    }
}
