package com.byd.data.common.sqlite;

import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public abstract class Table<T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected String f3478b;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    protected final String f3477a = getClass().getSimpleName();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final String f3479c = "name";

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected final String f3480d = "path";

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected List<n> f3481e = new ArrayList();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected String f3482f = "_id";

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f3483g = -1;
}
