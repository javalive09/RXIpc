package com.javalive09.ipc;

import com.javalive09.rxipc.IMethod;
import com.javalive09.rxipc.IPCHelper;

import android.app.Application;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by peter on 2018/12/18
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("IPC", "onCreate");
        IPCHelper.registerMethod(new IMethod() {
            @Override
            public Bundle onCall(@NonNull String s, @Nullable String s1, @Nullable Bundle bundle) {
                SystemClock.sleep(5000);
                Bundle bundle1 = new Bundle();
                bundle1.putString("abc", "ipc.....");
                return bundle1;
            }
        }, "ipc-test");
    }
}
