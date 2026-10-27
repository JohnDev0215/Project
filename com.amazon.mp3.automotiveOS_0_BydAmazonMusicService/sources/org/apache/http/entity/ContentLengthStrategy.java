package org.apache.http.entity;

import org.apache.http.HttpException;
import org.apache.http.HttpMessage;

/* JADX INFO: loaded from: classes7.dex */
public interface ContentLengthStrategy {
    long determineLength(HttpMessage httpMessage) throws HttpException;
}
