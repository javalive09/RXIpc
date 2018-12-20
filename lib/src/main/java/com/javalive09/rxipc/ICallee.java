package com.javalive09.rxipc;

import android.os.Bundle;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;

/**
 * Created by peter on 2018/12/17
 */
public interface ICallee {

    Bundle onCall(@NonNull final String order, @Nullable final String arg, @Nullable final Bundle extras);

}
