package com.javalive09.rxipc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by peter on 2018/12/17
 */
public interface IMethod {

    Bundle onCall(@NonNull final String method, @Nullable final String arg, @Nullable final Bundle extras);

}
