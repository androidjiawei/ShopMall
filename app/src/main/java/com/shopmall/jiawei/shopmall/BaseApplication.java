package com.shopmall.jiawei.shopmall;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.shopmall.jiawei.shopmall.bean.User;
import com.shopmall.jiawei.shopmall.utils.UserLocalData;

/**
 * Created by jiawei on 2017/5/3.
 */

public class BaseApplication extends Application {

    private User user;
    private static  BaseApplication mInstance;

    public static  BaseApplication getInstance(){
        return  mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        Fresco.initialize(this);
        initUser();
    }

    private void initUser(){

        this.user = UserLocalData.getUser(this);
    }

    public User getUser(){

        return user;
    }

    public void putUser(User user,String token){
        this.user = user;
        UserLocalData.putUser(this,user);
        UserLocalData.putToken(this,token);
    }

    public void clearUser(){
        this.user =null;
        UserLocalData.clearUser(this);
        UserLocalData.clearToken(this);
    }

    public String getToken(){
        return  UserLocalData.getToken(this);
    }

    private Intent intent;
    public void putIntent(Intent intent){
        this.intent = intent;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void jumpToTargetActivity(Context context){

        context.startActivity(intent);
        this.intent =null;
    }
}
